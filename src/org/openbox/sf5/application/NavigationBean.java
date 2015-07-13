package org.openbox.sf5.application;


import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class NavigationBean implements Serializable{
	
	private static final long serialVersionUID = 1520318172495977648L;
	
    /**
     * Go to login page.
     * @return Login page name.
     */
    public String toLogin() {
        return "/login.xhtml";
    }
    
    /**
     * Redirect to welcome page.
     * @return Welcome page name.
     */

    public String redirectToWelcome() {
        return "/SettingsList.xhtml?faces-redirect=true";
    }

    /**
     * Go to welcome page.
     * @return Welcome page name.
     */

    public String toWelcome() {
        return "/SettingsList.xhtml";
    }
    

}
