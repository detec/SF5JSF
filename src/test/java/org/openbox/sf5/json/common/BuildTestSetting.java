package org.openbox.sf5.json.common;

import java.util.ArrayList;
import java.util.List;

import org.openbox.sf5.model.Settings;
import org.openbox.sf5.model.SettingsConversion;
import org.openbox.sf5.model.SettingsSatellites;
import org.openbox.sf5.model.Transponders;
import org.openbox.sf5.model.Users;

public class BuildTestSetting {

	public static Settings buildSetting(Users adminUser, List<Transponders> newTransList) {

		Settings setting = new Settings();
		setting.setName("Simple");
		setting.setUser(adminUser);
		setting.setTheLastEntry(new java.sql.Timestamp(System.currentTimeMillis()));

		setting.setPropsFile("file");

		List<SettingsConversion> scList = new ArrayList<>();

		// filter up to 32 transponders
		newTransList.stream().filter(t -> newTransList.indexOf(t) <= 31).forEach(t -> {
			int currentIndex = newTransList.indexOf(t);
			int currentNumber = currentIndex + 1;
			int satIndex = (int) Math.ceil((double) currentNumber / 4);
			// int tpIndex = currentNumber - (satIndex * 4);
			int tpIndex = (currentNumber % 4 == 0) ? 4 : currentNumber % 4; // %
			// is
			// remainder

			SettingsConversion sc = new SettingsConversion(setting, t, satIndex, tpIndex,
					Long.toString(t.getFrequency()), 0);
			sc.setLineNumber(currentNumber);
			// List<SettingsConversion> scConv = setting.getConversion();
			// scConv.add(sc);
			scList.add(sc);

		});

		setting.setConversion(scList);
		setting.setSatellites(new ArrayList<SettingsSatellites>());

		return setting;

	}

}
