package org.openbox.sf5.json.endpoints;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

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

@RunWith(JUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SettingsServiceXMLIT extends AbstractServiceTest {

	private static final String servicePath = "usersettings";

	@Before
	public void setUp() {
		setUpAbstractTestUser();

		serviceTarget = commonTarget.path(servicePath);
	}

	private Users getTestUserXML() {
		Invocation.Builder invocationBuilder = commonTarget.path("users").path("filter").path("login")
				.path(this.testUsername).request(MediaType.APPLICATION_XML).accept(MediaType.APPLICATION_XML);
		Response response = invocationBuilder.get();
		assertEquals(Status.OK.getStatusCode(), response.getStatus());

		Users testUser = response.readEntity(Users.class);

		return testUser;
	}

	@Test
	public void shouldCreateAndGetSettingByIdXML() {
		Response response = null;

		// here we should create a setting.
		Users adminUser = getTestUserXML();

		assertThat(adminUser).isNotNull();

		// get some transponders to make lines objects.
		Invocation.Builder invocationBuilder = commonTarget.path("transponders").path("filter").path("Speed")
				.path("27500").request(MediaType.APPLICATION_XML).accept(MediaType.APPLICATION_XML);
		response = invocationBuilder.get();

		assertEquals(Status.OK.getStatusCode(), response.getStatus());

		GenericType<List<Transponders>> genList = new GenericType<List<Transponders>>() {
		};

		List<Transponders> newTransList = response.readEntity(genList);
		assertThat(newTransList.size()).isGreaterThan(0);

		Settings setting = BuildTestSetting.buildSetting(adminUser, newTransList, "Second");

		// http://howtodoinjava.com/2015/08/07/jersey-restful-client-examples/#post
		invocationBuilder = serviceTarget.path("create").matrixParam("login", this.testUsername)
				.request(MediaType.APPLICATION_XML);

		Response responsePost = invocationBuilder.post(Entity.entity(setting, MediaType.APPLICATION_XML));
		BuildTestSetting.checkCreatedSetting(responsePost, setting);

		// Here we test getting setting by id.
		invocationBuilder = serviceTarget.path("filter").path("id").path(Long.toString(setting.getId()))
				.matrixParam("login", this.testUsername).request(MediaType.APPLICATION_XML);

		response = invocationBuilder.get();
		assertEquals(Status.OK.getStatusCode(), response.getStatus());

		Settings settingRead = response.readEntity(Settings.class);
		assertThat(settingRead).isNotNull();
		assertTrue(settingRead instanceof Settings);

	}

	@Test
	public void shouldGetSettingsByArbitraryFilterXML() {
		Response response = null;

		// here we should check, if such user exists and find only his settings.
		List<Settings> settList = getUserSettingsXML();
		if (settList == null || settList.size() == 0) {
			return;
		}

		Settings sett = settList.get(0);

		Invocation.Builder invocationBuilder = serviceTarget.path("filter").path("Name").path(sett.getName())
				.matrixParam("login", this.testUsername).request(MediaType.APPLICATION_XML)
				.accept(MediaType.APPLICATION_XML);

		response = invocationBuilder.get();

		assertEquals(Status.OK.getStatusCode(), response.getStatus());

		GenericType<List<Settings>> genList = new GenericType<List<Settings>>() {
		};

		List<Settings> newSettList = response.readEntity(genList);

		assertThat(newSettList).isNotNull();
		assertThat(newSettList.size()).isGreaterThan(0);

	}

	private List<Settings> getUserSettingsXML() {
		Response response = null;
		List<Settings> settList = new ArrayList<Settings>();

		Invocation.Builder invocationBuilder = commonTarget.path("users").path("exists").path("login")
				.path(testUsername).request(MediaType.APPLICATION_XML).accept(MediaType.APPLICATION_XML);
		response = invocationBuilder.get();

		if (response.getStatus() == Status.ACCEPTED.getStatusCode()) {
			return settList;
		}

		GenericType<List<Settings>> genList = new GenericType<List<Settings>>() {
		};

		invocationBuilder = serviceTarget.path("filter").path("login").path(this.testUsername)
				.request(MediaType.APPLICATION_XML).accept(MediaType.APPLICATION_XML);

		response = invocationBuilder.get();

		assertEquals(Status.OK.getStatusCode(), response.getStatus());

		settList = response.readEntity(genList);

		assertThat(settList).isNotNull();
		assertThat(settList.size()).isGreaterThan(0);
		return settList;
	}

}
