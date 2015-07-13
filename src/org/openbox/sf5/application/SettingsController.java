package org.openbox.sf5.application;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


import javax.faces.context.FacesContext;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.openbox.sf5.db.HibernateUtil;
import org.openbox.sf5.db.Settings;
import org.openbox.sf5.db.Users;
import org.openbox.sf5.service.ObjectsController;

@ManagedBean(name = "settingsController")
@SessionScoped
public class SettingsController {
	
	private Users currentUser;

	public Users getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(Users currentUser) {
		this.currentUser = currentUser;
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
