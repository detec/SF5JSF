package org.openbox.sf5.application;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.openbox.sf5.db.HibernateUtil;
import org.openbox.sf5.db.Users;
import org.openbox.sf5.service.ObjectsController;
import org.openbox.sf5.service.ObjectsListService;

@Named(value = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 4615292844760430977L;

	// add default constructor
	public LoginBean() {

	}

	@NotNull(message = "Please enter login")
	private String name;

	@NotNull(message = "Please enter your full name")
	private String fullName;

	private Users user;

	private Object CurrentObject;

	public Object getCurrentObject() {
		return CurrentObject;
	}

	public void setCurrentObject(Object currentObject) {
		CurrentObject = currentObject;
	}

	private boolean loggedIn;

	public String getName() {
		return name;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String FullName) {
		fullName = FullName;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public String getCurrentUserStringId() {
		long id = user.getId();
		return Long.toString(id);
	}

	public String doLogin() {

		// check if name is filled
		if (name.equals("")) {

			// Bring the error message using the Faces Context
			String errorMessage = "You should enter login!";

			// Add View Faces Message
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, errorMessage, errorMessage);
			// Add the message into context for a specific component
			FacesContext.getCurrentInstance().addMessage("form:loginMessage",
					message);
		}

		if (!userExists(name)) {
			// Set login ERROR
			FacesMessage msg = new FacesMessage("Login error!",
					"User not found in database: " + name);
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, msg);

			// To to login page
			return "/login.xhtml";
		}

		else {
			loggedIn = true;
			Session session = HibernateUtil.openSession();
			List<Users> rec = session.createCriteria(Users.class)
					.add(Restrictions.eq("Login", name)).list();
			user = rec.get(0);

			// set faces parameter
			// Map<String, String> params =
			// FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
			// params.put("currentuserid", getCurrentUserStringId());

			return "/SettingsList.xhtml?faces-redirect=true";
		}
	}

	public String doRegister() {

		if (userExists(name)) {
			FacesMessage msg = new FacesMessage("Registration error!",
					"This login has already been registered in database: "
							+ name);
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, msg);

			// To to login page
			return "/login.xhtml";
		} else {
			Users newUser = new Users(fullName, name);

			// Let's save user in database
			ObjectsController contr = new ObjectsController();
			contr.saveOrUpdate(newUser);

			// set current user
			user = newUser;

			return "/SettingsList.xhtml?faces-redirect=true";
		}

	}

	public String logout() {
		loggedIn = false;
		fullName = "";
		name = "";
		user = null;

		return "login";
	}

	public boolean userExists(String username) {

		Criterion criterion = Restrictions.eq("Login", username);
//		Session session = HibernateUtil.openSession();
//		List<Users> rec = session.createCriteria(Users.class)
//				.add(Restrictions.eq("Login", username)).list();
		List<Users> rec = (List<Users>) ObjectsListService.ObjectsCriterionList(Users.class, criterion);
		if (rec.isEmpty()) {
			return false;
		} else {
			return true;
		}

	}
}
