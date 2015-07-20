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
import org.openbox.sf5.db.Settings;
import org.openbox.sf5.db.Users;
import org.openbox.sf5.service.ObjectsController;
import org.openbox.sf5.service.ObjectsListService;

@Named(value = "settingsController")
@ViewScoped
public class SettingsController implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 7396280960466432737L;

	// let's place default constructor
	public SettingsController() {

	}

	// @ManagedProperty(value="#{loginBean}")
	@Inject
	private LoginBean CurrentLogin;

	private Users currentUser;

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

	public List<Settings> getSettingsbyUser() {
		List<Settings> settingsList = new ArrayList<Settings>();

		// check if current user is null
		if (currentUser == null) {
			return settingsList;
		}

		// Session session = HibernateUtil.openSession();
		// settingsList = session.createCriteria(Settings.class)
		// .add(Restrictions.eq("User", currentUser)).list();

		Criterion criterion = Restrictions.eq("User", currentUser);
		settingsList = (List<Settings>) ObjectsListService
				.ObjectsCriterionList(Settings.class, criterion);
		return settingsList;
	}

	public String addSetting() {

		Settings setting = new Settings();
		setting.setUser(currentUser);
		setting.setName("New setting");
		// sending newly created setting to session bean
		this.CurrentLogin.setCurrentObject(setting);

		// redirect to settings page
		return "Setting";
	}

	public String removeSetting(long pId) {
		ObjectsController contr = new ObjectsController();
		contr.remove(Settings.class, pId);
		return "";
	}

}
