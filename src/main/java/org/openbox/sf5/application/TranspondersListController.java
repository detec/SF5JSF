package org.openbox.sf5.application;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.openbox.sf5.db.Satellites;
import org.openbox.sf5.db.Transponders;
import org.openbox.sf5.service.ObjectsController;
import org.openbox.sf5.service.ObjectsListService;

@Named("transpondersListController")
@ViewScoped
public class TranspondersListController implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -1000488396477162309L;

	@Inject
	private LoginBean loginBean;

	public boolean isSelectionMode() {
		return SelectionMode;
	}

	public void setSelectionMode(boolean selectionMode) {
		SelectionMode = selectionMode;
	}

	private boolean SelectionMode;

	private boolean multiple;

	private long scId;

	public long getScId() {
		return scId;
	}

	public void setScId(long scId) {
		this.scId = scId;
	}

	public boolean isMultiple() {
		return multiple;
	}

	public void setMultiple(boolean multiple) {
		this.multiple = multiple;
	}

	private long SettingId;

	public long getSettingId() {
		return SettingId;
	}

	public void setSettingId(long settingId) {
		SettingId = settingId;
	}

	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

	private Satellites filterSatellite;

	public void setTranspondersList(List<Transponders> transpondersList) {
		TranspondersList = transpondersList;
	}

	private List<Transponders> TranspondersList;

	private List<TransponderChoice> TransponderChoiceList = new ArrayList<TransponderChoice>();

	private List<Transponders> selectedTranspondersList = new ArrayList<Transponders>();

	public List<Transponders> getSelectedTranspondersList() {
		return selectedTranspondersList;
	}

	public void setSelectedTranspondersList(List<Transponders> selectedTranspondersList) {
		this.selectedTranspondersList = selectedTranspondersList;
	}

	public List<TransponderChoice> getTransponderChoiceList() {
		return TransponderChoiceList;
	}

	public void setTransponderChoiceList(List<TransponderChoice> transponderChoiceList) {
		TransponderChoiceList = transponderChoiceList;
	}

	public Satellites getFilterSatellite() {
		return filterSatellite;
	}

	public void setFilterSatellite(Satellites filterSatellite) {
		this.filterSatellite = filterSatellite;
	}

	@PostConstruct
	public void init() {

		if (loginBean.getFilterSatId() != 0) {
			filterSatellite = ((Satellites) ObjectsController.select(Satellites.class, loginBean.getFilterSatId()));

		}

		if (filterSatellite != null) {
			Criterion criterion = Restrictions.eq("Satellite", filterSatellite);
			// return (List<Transponders>) ObjectsListService
			TranspondersList = (List<Transponders>) ObjectsListService.ObjectsCriterionList(Transponders.class,
					criterion);
		} else {
			// return (List<Transponders>) ObjectsListService
			// .ObjectsList(Transponders.class);
			TranspondersList = (List<Transponders>) ObjectsListService.ObjectsList(Transponders.class);
		}
	}

	public List<Transponders> getTranspondersList() {

		return TranspondersList;
	}

	public List<Satellites> getSatellites() {

		return (List<Satellites>) ObjectsListService.ObjectsList(Satellites.class);
	}

	public String saveSelectedTransponders() {
		selectedTranspondersList.clear();

		for (TransponderChoice e : TransponderChoiceList) {
			if (e.checked) {
				selectedTranspondersList.add(e);
			}
		}

		loginBean.setCurrentObject(selectedTranspondersList);

		String addressString = "/Setting.xhtml?faces-redirect=true&id=" + Long.toString(SettingId) + "&SelectionMode="
				+ Boolean.toString(SelectionMode) + "&multiple=" + Boolean.toString(multiple) + "&scId="
				+ Long.toString(scId);

		// let's try to clear selected satellite
		filterSatellite = null;

		return addressString;
	}

	public String processMultipleSingleSelection(TransponderChoice row) {

		// doesn't work as expected
		if (SelectionMode && !multiple) {

			// just add to selected transponders and quit.
			selectedTranspondersList.clear();
			selectedTranspondersList.add(row);

			loginBean.setCurrentObject(selectedTranspondersList);

			String addressString = "/Setting.xhtml?faces-redirect=true&id=" + Long.toString(SettingId)
					+ "&SelectionMode=" + Boolean.toString(SelectionMode) + "&multiple=" + Boolean.toString(multiple)
					+ "&scId=" + Long.toString(scId);
			return addressString;

		}

		return "";
	}

	// in order to select transponders we should implement wrapper class to
	// store 'check' property

	public List<TransponderChoice> getTranspondersChoice() {

		TransponderChoiceList.clear();

		for (Transponders e : TranspondersList) {
			TransponderChoiceList.add(new TransponderChoice(e));
		}

		return TransponderChoiceList;
	}

	public class TransponderChoice extends Transponders {

		/**
		 *
		 */
		private static final long serialVersionUID = 3262084796351763445L;
		private boolean checked;

		public boolean isChecked() {
			return checked;
		}

		public void setChecked(boolean checked) {
			this.checked = checked;
		}

		public TransponderChoice(Transponders transponder) {
			super(transponder);
			checked = false;
		}
	}

}
