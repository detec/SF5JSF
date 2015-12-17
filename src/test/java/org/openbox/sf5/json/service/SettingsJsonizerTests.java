package org.openbox.sf5.json.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.runners.MethodSorters;
import org.openbox.sf5.json.common.BuildTestSetting;
import org.openbox.sf5.model.Settings;
import org.openbox.sf5.model.Transponders;
import org.openbox.sf5.model.Users;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RunWith(JUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SettingsJsonizerTests extends AbstractJsonizerTest {

	@Test
	public void shouldCreateNewSetting() throws JsonParseException, JsonMappingException, IOException {
		// get user
		String result = usersJsonizer.getUserByLogin(testUsername);
		assertThat(result).isNotEmpty();
		Users readUser = mapper.readValue(result, Users.class);
		assertThat(readUser).isNotNull();

		// get transponders
		result = transpondersJsonizer.getTransponders();

		List<Transponders> transList = mapper.readValue(result,
				mapper.getTypeFactory().constructCollectionType(List.class, Transponders.class));

		assertThat(transList).isNotNull();
		assertThat(transList.size()).isNotEqualTo(0);

		Settings setting = BuildTestSetting.buildSetting(readUser, transList);

		int status = settingsJsonizer.saveNewSetting(setting);
		assertEquals(201, status);

		result = settingsJsonizer.getSettingById(setting.getId(), testUsername);
		Settings readSetting = mapper.readValue(result, Settings.class);
		assertThat(readSetting).isNotNull();

	}

	@Test
	public void shouldGetSettingsByArbitraryFilter() throws JsonParseException, JsonMappingException, IOException {
		String result = settingsJsonizer.getSettingsByArbitraryFilter("Name", "Simple", this.testUsername);
		List<Settings> settList = mapper.readValue(result,
				mapper.getTypeFactory().constructCollectionType(List.class, Settings.class));

		assertThat(settList).isNotNull();
		assertThat(settList.size()).isNotEqualTo(0);
	}

	@Test
	public void shouldGetSettingsByUserLogin() throws JsonParseException, JsonMappingException, IOException {
		String result = settingsJsonizer.getSettingsByUserLogin(testUsername);

		List<Settings> settList = mapper.readValue(result,
				mapper.getTypeFactory().constructCollectionType(List.class, Settings.class));

		assertThat(settList).isNotNull();
		assertThat(settList.size()).isNotEqualTo(0);
	}

	@Before
	public void setUp() {

		super.setUpAbstract();

		settingsJsonizer = new SettingsJsonizer();
		settingsJsonizer.setCm(cm);
		settingsJsonizer.setCommonJsonizer(commonJsonizer);
		settingsJsonizer.setCriterionService(criterionService);
		settingsJsonizer.setListService(listService);
		settingsJsonizer.setObjectsController(objectsController);

		usersJsonizer = new UsersJsonizer();
		usersJsonizer.setCommonJsonizer(commonJsonizer);
		usersJsonizer.setCriterionService(criterionService);
		usersJsonizer.setListService(listService);
		usersJsonizer.setObjectsController(objectsController);

		transpondersJsonizer = new TranspondersJsonizer();
		transpondersJsonizer.setObjectsController(objectsController);
		transpondersJsonizer.setCriterionService(criterionService);
		transpondersJsonizer.setListService(listService);
	}

	private SettingsJsonizer settingsJsonizer;

	private UsersJsonizer usersJsonizer;

	private TranspondersJsonizer transpondersJsonizer;

}
