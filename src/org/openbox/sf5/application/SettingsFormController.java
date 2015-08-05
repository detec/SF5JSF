package org.openbox.sf5.application;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.openbox.sf5.db.CarrierFrequency;
import org.openbox.sf5.db.Polarization;
import org.openbox.sf5.db.Satellites;
import org.openbox.sf5.db.Settings;
import org.openbox.sf5.db.SettingsConversion;
import org.openbox.sf5.db.Transponders;
import org.openbox.sf5.db.TypesOfFEC;
import org.openbox.sf5.db.Users;
import org.openbox.sf5.service.ObjectsController;

@Named(value = "setting")
@ViewScoped
public class SettingsFormController implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -4706281624885080396L;
	private Settings setting;

	// here we will receive parameter from page
	private long Id;

	private Users currentUser;

	private boolean SelectionMode;

	public boolean isSelectionMode() {
		return SelectionMode;
	}

	public void setSelectionMode(boolean selectionMode) {
		SelectionMode = selectionMode;
	}

	public Users getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(Users currentUser) {
		this.currentUser = currentUser;
	}

	public List<SettingsConversionPresentation> getDataSettingsConversion() {
		return dataSettingsConversion;
	}

	public void setDataSettingsConversion(
			List<SettingsConversionPresentation> dataSettingsConversion) {
		this.dataSettingsConversion = dataSettingsConversion;
	}

	private List<SettingsConversionPresentation> dataSettingsConversion = new ArrayList<SettingsConversionPresentation>();

	private String Name;

	private Timestamp TheLastEntry;

	public Settings getSetting() {
		return setting;
	}

	public void setSetting(Settings setting) {
		this.setting = setting;
	}

	public String getName() {
		// return setting.getName();
		return Name;
	}

	public void setName(String pName) {
		// setting.setName(pName);
		Name = pName;
	}

	public Date getTheLastEntry() {
		// return setting.getTheLastEntry();
		// return this.TheLastEntry;
		return TheLastEntry == null ? null : new Date(TheLastEntry.getTime());
	}

	public void setTheLastEntry() {
		setting.setTheLastEntry(new java.sql.Timestamp(System
				.currentTimeMillis()));
	}

	public long getId() {
		// return setting.getId();
		return Id;
	}

	public void setId(long pId) {
		// setting.setId(pId);
		Id = pId;
	}

	public void removwRow(SettingsConversionPresentation row) {
		Long currentRowId = row.getId();
		row.setNote("");
		dataSettingsConversion.remove(row);

		List<SettingsConversion> tpConversion = setting.getConversion();
		ArrayList<SettingsConversion> deleteArray = new ArrayList<SettingsConversion>();

		// define elements to be deleted
		for (SettingsConversion e : tpConversion) {
			if (e.getId() == currentRowId) {
				deleteArray.add(e);
			}
		}

		// iterate deleteArray to actually delete elements
		for (SettingsConversion e : deleteArray) {
			tpConversion.remove(e);
		}
		renumerateLines();

		// save if row should be deleted from database.
		if (deleteArray.size() > 0) {
			ObjectsController contr = new ObjectsController();
			contr.saveOrUpdate(setting);
		}
	}

	public void removeSelectedRows() {
		List<SettingsConversionPresentation> toRemove = new ArrayList<SettingsConversionPresentation>();
		Map<Long, SettingsConversionPresentation> tpMap = new HashMap<>();

		for (SettingsConversionPresentation e : dataSettingsConversion) {
			if (!e.checked) {
				continue;
			} else {
				toRemove.add(e);
				tpMap.put(new Long(e.getId()), e);
			}

		}

		dataSettingsConversion.removeAll(toRemove);

		List<SettingsConversion> tpConversion = setting.getConversion();
		ArrayList<SettingsConversion> deleteArray = new ArrayList<SettingsConversion>();

		// define elements to be deleted
		for (SettingsConversion e : tpConversion) {
			if (tpMap.get(new Long(e.getId())) != null) {
				deleteArray.add(e);
			}
		}

		tpConversion.removeAll(deleteArray);
		renumerateLines();

		if (deleteArray.size() > 0) {
			ObjectsController contr = new ObjectsController();
			contr.saveOrUpdate(setting);
		}
	}

	public void renumerateLines() {

		int i = 1;
		for (SettingsConversionPresentation e : dataSettingsConversion) {
			e.setLineNumber(i);
			i++;
		}
	}

	public void addNewLine(Transponders trans) {
		long newLine = new Long(dataSettingsConversion.size() + 1).longValue();

		SettingsConversionPresentation newLineObject = new SettingsConversionPresentation(
				setting);

		newLineObject.setLineNumber(newLine);
		// newLineObject.setTransponder(new Transponders()); // to prevent null
		// pointer Exception
		newLineObject.setTransponder(trans);
		newLineObject.setNote("");
		newLineObject.editable = true;

		dataSettingsConversion.add(newLineObject);

	}

	// this method seem to be called 2 times, first time nothing is initialized
	// @PostConstruct
	public void init() {
		if (CurrentLogin == null) {
			return;
		}
		currentUser = CurrentLogin.getUser();

		// Analyze if we have current object set in session bean
		if (CurrentLogin.getCurrentObject() != null
				&& CurrentLogin.getCurrentObject() instanceof Settings) {

			setting = (Settings) CurrentLogin.getCurrentObject();
		}

		// load passed settings id
		if (Id != 0) {
			ObjectsController contr = new ObjectsController();
			setting = (Settings) contr.select(Settings.class, Id);
			Name = setting.getName();
			TheLastEntry = setting.getTheLastEntry();
		}

		// fill in fields
		if (setting != null) {
			Name = setting.getName();
			TheLastEntry = setting.getTheLastEntry();
			// load transponders and so on

			dataSettingsConversion.clear();
			List<SettingsConversion> listRead = setting.getConversion();

			// for new item it is null
			if (listRead != null) {
				// sort in ascending order
				Collections.sort(listRead, (b1, b2) -> (int) (b1
						.getLineNumber() - b2.getLineNumber()));

				for (SettingsConversion e : listRead) {
					dataSettingsConversion
							.add(new SettingsConversionPresentation(e));
				}

			}

			renumerateLines();

			if (this.SelectionMode) {
				List<Transponders> transList = (List<Transponders>) this.CurrentLogin
						.getCurrentObject();
				for (Transponders e : transList) {
					addNewLine(e);
				}

				renumerateLines();
				// clear result
				this.SelectionMode = false;
				transList.clear();

			} // end check selection mode

		}

	}

	@Inject
	private LoginBean CurrentLogin;

	// private Users currentUser;

	public LoginBean getCurrentLogin() {
		return CurrentLogin;
	}

	public void setCurrentLogin(LoginBean currentLogin) {
		CurrentLogin = currentLogin;
	}

	public void setEditRow(SettingsConversionPresentation row) {
		row.setEditable(true);
	}

	public void setCancelEdit(SettingsConversionPresentation row) {
		row.setEditable(false);
	}

	public void saveSetting() {
		ObjectsController contr = new ObjectsController();
		setting.setTheLastEntry(new java.sql.Timestamp(System
				.currentTimeMillis()));

		setting.setName(Name);

		// remove editable mark
		for (SettingsConversionPresentation e : dataSettingsConversion) {
			e.editable = false;
		}

		// unload table with transponders.
		setting.setConversion(unloadTableSettingsConversion());
		contr.saveOrUpdate(setting);

	}

	public List<SettingsConversion> unloadTableSettingsConversion() {

		List<SettingsConversion> unloadSettingsConversion = new ArrayList<SettingsConversion>();
		for (SettingsConversion e : dataSettingsConversion) {
			// we need to check if transponder is null
			if (e.getTransponder().getId() == 0) {
				continue;
			}
			unloadSettingsConversion.add(e);

		}

		return unloadSettingsConversion;
	}

	// http://stackoverflow.com/questions/21988598/how-to-get-selected-tablecell-in-javafx-tableview
	// If you need data from multiple sources in single table, it is better to
	// make a new class that aggregates all the data and use that as a TableView
	// source.
	public class SettingsConversionPresentation extends SettingsConversion {

		/**
		 *
		 */
		private static final long serialVersionUID = 7708887469121053073L;

		private Polarization Polarization;

		private transient boolean editable;

		private transient boolean checked;

		public boolean isChecked() {
			return checked;
		}

		public void setChecked(boolean checked) {
			this.checked = checked;
		}

		public boolean isEditable() {
			return editable;
		}

		public void setEditable(boolean editable) {
			this.editable = editable;
		}

		public SettingsConversionPresentation(Settings object) {
			// TODO Auto-generated constructor stub
			super.setparent_id(object);

			// Note shouldn't be null
			super.setNote("");
		}

		// we have to manually construct presentation class from super
		public SettingsConversionPresentation(SettingsConversion superItem) {
			super(superItem);
			Carrier = superItem.getTransponder().getCarrier();
			FEC = superItem.getTransponder().getFEC();
			Polarization = superItem.getTransponder().getPolarization();
			Satellite = superItem.getTransponder().getSatellite();
			Speed = superItem.getTransponder().getSpeed();
		}

		public Polarization getPolarization() {
			Transponders currentTransponder = super.getTransponder();
			Polarization currentPolarization = null;

			if (currentTransponder != null) {
				currentPolarization = currentTransponder.getPolarization();
			}
			return currentPolarization;
		}

		public void setPolarization(Polarization Polarization) {
			this.Polarization = Polarization;
		}

		private CarrierFrequency Carrier;

		public CarrierFrequency getCarrier() {
			Transponders currentTransponder = super.getTransponder();
			CarrierFrequency currentCarrier = null;
			if (currentTransponder != null) {
				currentCarrier = currentTransponder.getCarrier();
			}
			return currentCarrier;
		}

		public void setCarrier(CarrierFrequency Carrier) {
			this.Carrier = Carrier;
		}

		private long Speed;

		public long getSpeed() {
			Transponders currentTransponder = super.getTransponder();
			long currentSpeed = 0;
			if (currentTransponder != null) {
				currentSpeed = currentTransponder.getSpeed();
			}
			return currentSpeed;
		}

		public void setSpeed(long Speed) {
			this.Speed = Speed;
		}

		private Satellites Satellite;

		public Satellites getSatellite() {
			Transponders currentTransponder = super.getTransponder();
			Satellites currentSatellite = null;
			if (currentTransponder != null) {
				currentSatellite = currentTransponder.getSatellite();
			}
			return currentSatellite;
		}

		public void setSatellite(Satellites Satellite) {
			this.Satellite = Satellite;
		}

		private TypesOfFEC FEC;

		public TypesOfFEC getFEC() {
			Transponders currentTransponder = super.getTransponder();
			TypesOfFEC currentFEC = null;
			if (currentTransponder != null) {
				currentFEC = currentTransponder.getFEC();
			}
			return currentFEC;
		}

		public void setFEC(TypesOfFEC FEC) {
			this.FEC = FEC;
		}

		public SettingsConversionPresentation(
				SettingsConversionPresentation original, Settings parent) {
			Carrier = original.Carrier;
			FEC = original.FEC;
			Polarization = original.Polarization;
			Satellite = original.Satellite;
			Speed = original.Speed;
			this.setTransponder(original.getTransponder());
			this.setparent_id(parent);
			this.setNote(original.getNote());

		}

	}

}
