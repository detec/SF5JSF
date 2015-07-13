package org.openbox.sf5.application;

import java.util.List;
import java.util.Map;

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

	public List<Settings> getSettingsbyUser() {
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String currentUserStringId = params.get("currentuserid");
		//convert string to long
		long currentUserid = Long.valueOf(currentUserStringId);
		
		ObjectsController contr = new ObjectsController();
		Users currentUser = (Users) contr.select(Users.class, currentUserid);
		
		Session session = HibernateUtil.openSession();
		List<Settings> settingsList = (List<Settings>) session.createCriteria(Settings.class)
				.add(Restrictions.eq("User", currentUser)).list();
		  
		return settingsList;
	}
	
}
