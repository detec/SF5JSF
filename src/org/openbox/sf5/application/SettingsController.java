package org.openbox.sf5.application;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.inject.*;


import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.openbox.sf5.db.HibernateUtil;
import org.openbox.sf5.db.Settings;
import org.openbox.sf5.db.Users;
import org.openbox.sf5.service.ObjectsController;

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
	
	//@ManagedProperty(value="#{loginBean}")
	//@Inject
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
		if (this.CurrentLogin == null) return;
		this.currentUser = this.CurrentLogin.getUser(); 
		
	}
	
    
	public List<Settings> getSettingsbyUser() {
		List<Settings> settingsList = new ArrayList<Settings>();
		
//		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
//		String currentUserStringId = params.get("currentuserid");
//		if (currentUserStringId.equals("null")) {
//	        FacesMessage msg = new FacesMessage("Settings read error!"
//	        		, "Current user unknown, currentUserStringId equals null!");
//	        msg.setSeverity(FacesMessage.SEVERITY_ERROR);
//	        FacesContext.getCurrentInstance().addMessage(null, msg);
//	        
//	        return settingsList;
//		
//		}
//		
//		//convert string to long
//		long currentUserid = Long.valueOf(currentUserStringId);
//		
//		ObjectsController contr = new ObjectsController();
//		Users currentUser = (Users) contr.select(Users.class, currentUserid);
		
		
		// check if current user is null
		if (currentUser == null) return settingsList;
		
		Session session = HibernateUtil.openSession();
		settingsList = (List<Settings>) session.createCriteria(Settings.class)
				.add(Restrictions.eq("User", currentUser)).list();
		  
		return settingsList;
	}



	
}
