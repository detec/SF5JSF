package org.openbox.sf5.application;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.openbox.sf5.model.Settings;
import org.openbox.sf5.model.Users;
import org.openbox.sf5.service.ObjectsController;
import org.openbox.sf5.service.ObjectsListService;

@Named("settingsController")
@ViewScoped
public class SettingsController implements Serializable {

	private static final long serialVersionUID = 7396280960466432737L;

	// let's place default constructor
	public SettingsController() {

	}

	@Inject
	private LoginBean CurrentLogin;

	private Users currentUser;

	private boolean showRemovalConfirmation;

	private Settings currentSetting;

	public Settings getCurrentSetting() {
		return currentSetting;
	}

	public void setCurrentSetting(Settings currentSetting) {
		this.currentSetting = currentSetting;
	}

	public boolean isShowRemovalConfirmation() {
		return showRemovalConfirmation;
	}

	public void setShowRemovalConfirmation(boolean showRemovalConfirmation) {
		this.showRemovalConfirmation = showRemovalConfirmation;
	}

	private boolean SelectionMode;

	private long settingId;

	public long getSettingId() {
		return this.settingId;
	}

	public void setSettingId(long settingId) {
		this.settingId = settingId;
	}

	public boolean isSelectionMode() {
		return SelectionMode;
	}

	public void setSelectionMode(boolean selectionMode) {
		SelectionMode = selectionMode;
	}

	public LoginBean getCurrentLogin() {
		return this.CurrentLogin;
	}

	public void setCurrentLogin(LoginBean currentLogin) {
		this.CurrentLogin = currentLogin;
	}

	public Users getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(Users currentUser) {
		this.currentUser = currentUser;
	}

	@PostConstruct
	public void init() {

		// let's initialize current user
		if (this.CurrentLogin == null) {
			return;
		}
		this.currentUser = this.CurrentLogin.getUser();

	}

	public String startRemovalOfSetting(Settings pSetting) {
		this.showRemovalConfirmation = true;
		this.currentSetting = pSetting;
		return "";
	}

	public void cancelRemoval() {
		this.showRemovalConfirmation = false;
		this.currentSetting = null;
	}

	public List<Settings> getSettingsbyUser() {
		List<Settings> settingsList = new ArrayList<Settings>();

		// check if current user is null
		if (currentUser == null) {
			return settingsList;
		}

		Criterion criterion = Restrictions.eq("User", currentUser);
		settingsList = listService.ObjectsCriterionList(Settings.class, criterion);
		return settingsList;
	}

	public String addSetting() {

		Settings setting = new Settings();
		setting.setUser(currentUser);
		setting.setName("New setting");
		// sending newly created setting to session bean
		this.CurrentLogin.setCurrentObject(setting);

		// redirect to settings page
		return "/Setting.xhtml?faces-redirect=true&SelectionMode=false&multiple=false";
	}

	public String removeSetting() {

		if (this.currentSetting != null) {

			contr.remove(Settings.class, this.currentSetting.getId());

			String mesString = "Setting remove success!";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Setting removal", mesString);

			// Add the message into context for a specific component
			FacesContext.getCurrentInstance().addMessage("messages", message);
		} else {

			String mesString = "No current setting set - nothing removed!";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Setting removal", mesString);

			// Add the message into context for a specific component
			FacesContext.getCurrentInstance().addMessage("messages", message);
		}

		this.showRemovalConfirmation = false;
		this.currentSetting = null;

		return "";
	}

	@Inject
	private transient ObjectsController contr;

	@Inject
	private ObjectsListService listService;
}
