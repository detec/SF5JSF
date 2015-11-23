package org.openbox.sf5.common;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.openbox.sf5.json.service.AbstractJsonizerTest;
import org.openbox.sf5.model.Settings;
import org.openbox.sf5.model.SettingsConversion;
import org.openbox.sf5.model.Transponders;
import org.openbox.sf5.model.Users;

public class IntersectionsTests extends AbstractJsonizerTest {

	@Before
	public void setUp() {
		super.setUpAbstract();

	}

	@Test
	public void shouldCheckIntersections() throws SQLException {
		Settings setting = new Settings();
		setting.setName("Intersections test");
		setting.setUser(new Users());
		contr.saveOrUpdate(setting);

		List<Transponders> transList = listService.ObjectsList(Transponders.class);

		List<SettingsConversion> scList = new ArrayList<SettingsConversion>();
		for (int i = 7; i < 39; i++) {
			// adding lines to setting
			SettingsConversion newLine = new SettingsConversion(setting);
			newLine.setLineNumber(i - 6);
			newLine.setTransponder(transList.get(i));
			scList.add(newLine);
		}

		Intersections intersections = new Intersections();
		intersections.setCm(cm);
		int rows = intersections.checkIntersection(scList, setting);

	}

}
