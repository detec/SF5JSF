package org.openbox.sf5.application;

import javax.faces.bean.ManagedBean;

import org.openbox.sf5.db.Settings;

@ManagedBean
public class SettingsFormController {
	
	private Settings setting;

	public Settings getSetting() {
		return setting;
	}

	public void setSetting(Settings setting) {
		this.setting = setting;
	}

}
