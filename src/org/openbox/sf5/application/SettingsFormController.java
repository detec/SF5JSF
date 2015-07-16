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

	public Settings getSetting() {
		return setting;
	}

	public void setSetting(Settings setting) {
		this.setting = setting;
	}

	public String getName() {
		return setting.getName();
	}

	public void setName(String pName) {
		setting.setName(pName);
	}

	public Timestamp getTheLastEntry() {
		return setting.getTheLastEntry();
	}

	public void setTheLastEntry() {
		setting.setTheLastEntry((Timestamp) new Date());
	}

	public long getId() {
		return setting.getId();
	}

	public void setId(long pId) {
		setting.setId(pId);
	}

	@PostConstruct
	public void init() {
		if (this.CurrentLogin == null) {
			return;
		}
		this.currentUser = this.CurrentLogin.getUser();

		if (setting == null) {
			setting = new Settings();
			setting.setUser(currentUser);
			setting.setName("New setting");
		}

		else {
			if (this.Id != 0) {
				ObjectsController contr = new ObjectsController();
				setting = (Settings) contr.select(Settings.class, this.Id);
			}
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
		setting.setTheLastEntry(new java.sql.Timestamp(System
				.currentTimeMillis()));

		this.setting.setName(this.Name);
		contr.saveOrUpdate(setting);

		FacesMessage msg = new FacesMessage("Setting saved!");
		msg.setSeverity(FacesMessage.SEVERITY_INFO);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

}
