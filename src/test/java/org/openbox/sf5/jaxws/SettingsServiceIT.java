package org.openbox.sf5.jaxws;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.runners.MethodSorters;
import org.openbox.sf5.json.common.BuildTestSetting;
import org.openbox.sf5.wsmodel.Settings;
import org.openbox.sf5.wsmodel.Transponders;
import org.openbox.sf5.wsmodel.Users;

@RunWith(JUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SettingsServiceIT extends AbstractWSTest {

	@Test
	public void shouldCreateAndGetSettingById() {
		Users adminUser = getTestUser();

		assertThat(adminUser).isNotNull();

		List<Transponders> newTransList = SF5Port.getTranspondersByArbitraryFilter("Speed", "27500");
		assertThat(newTransList.size()).isGreaterThan(0);

		Settings setting = BuildTestSetting.buildSetting(adminUser, newTransList, "Simple");

		long newSettID = SF5Port.createSetting(setting, testUsername);
		assertThat(newSettID).isGreaterThan(0);
		setting.setId(newSettID);

		Settings settingRead = SF5Port.getSettingById(newSettID, testUsername);
		assertThat(settingRead).isNotNull();
		assertTrue(settingRead instanceof Settings);

	}

	private Users getTestUser() {

		Users testUser = SF5Port.getUserByLogin(testUsername);

		return testUser;
	}

	@Before
	public void setUp() throws Exception {
		setUpAbstract();
	}

}
