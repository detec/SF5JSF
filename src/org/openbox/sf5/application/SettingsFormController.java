package org.openbox.sf5.application;

import javax.faces.bean.ManagedBean;

import java.sql.Timestamp;
import java.util.Date;

import org.openbox.sf5.db.Settings;

@ManagedBean(name="setting")
public class SettingsFormController{
	
	private Settings setting;

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
}
