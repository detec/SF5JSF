package org.openbox.sf5.application;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.openbox.sf5.db.Settings;
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
		return this.Name;
	}

	public void setName(String pName) {
		// setting.setName(pName);
		this.Name = pName;
	}

	public Date getTheLastEntry() {
		// return setting.getTheLastEntry();
		// return this.TheLastEntry;
		return this.TheLastEntry == null ? null : new Date(
				this.TheLastEntry.getTime());
	}

	public void setTheLastEntry() {
		setting.setTheLastEntry(new java.sql.Timestamp(System
				.currentTimeMillis()));
	}

	public long getId() {
		// return setting.getId();
		return this.Id;
	}

	public void setId(long pId) {
		// setting.setId(pId);
		this.Id = pId;
	}

	@PostConstruct
	public void init() {
		if (this.CurrentLogin == null) {
			return;
		}
		this.currentUser = this.CurrentLogin.getUser();

		// we have new idea how to create settings
		// if (this.Id != 0) {
		// ObjectsController contr = new ObjectsController();
		// this.setting = (Settings) contr.select(Settings.class, this.Id);
		// this.Name = setting.getName();
		// this.TheLastEntry = setting.getTheLastEntry();
		// }
		//
		// else {
		//
		// // setting == null) {
		// setting = new Settings();
		// setting.setUser(currentUser);
		// // setting.setName("New setting");
		// this.Name = "New setting";
		// }

		// Analyze if we have current object set in session bean
		if (this.CurrentLogin.getCurrentObject() != null
				&& this.CurrentLogin.getCurrentObject() instanceof Settings) {

			this.setting = (Settings) this.CurrentLogin.getCurrentObject();
		}

		// load passed settings id
		if (this.Id != 0) {
			ObjectsController contr = new ObjectsController();
			this.setting = (Settings) contr.select(Settings.class, this.Id);
			this.Name = setting.getName();
			this.TheLastEntry = setting.getTheLastEntry();
		}

		// fill in fields
		if (this.setting != null) {
			this.Name = setting.getName();
			this.TheLastEntry = setting.getTheLastEntry();
			// load transponders and so on
		}
	}

	@Inject
	private LoginBean CurrentLogin;

	private Users currentUser;

	public LoginBean getCurrentLogin() {
		return this.CurrentLogin;
	}

	public void setCurrentLogin(LoginBean currentLogin) {
		this.CurrentLogin = currentLogin;
	}

	public void saveSetting() {
		ObjectsController contr = new ObjectsController();
		this.setting.setTheLastEntry(new java.sql.Timestamp(System
				.currentTimeMillis()));

		this.setting.setName(this.Name);
		contr.saveOrUpdate(this.setting);

		FacesMessage msg = new FacesMessage("Setting saved!");
		msg.setSeverity(FacesMessage.SEVERITY_INFO);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

}
