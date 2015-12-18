package org.openbox.sf5.json.endpoints;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
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
public class SettingsServiceIT extends AbstractServiceTest {

	private static final String servicePath = "usersettings";

	@Before
	public void setUp() {
		setUpAbstractTestUser();

		serviceTarget = commonTarget.path(servicePath);
	}

	private Users getTestUser() {
		Invocation.Builder invocationBuilder = commonTarget.path("users").path("filter").path("login")
				.path(this.testUsername).request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		Response response = invocationBuilder.get();
		assertEquals(Status.OK.getStatusCode(), response.getStatus());

		Users testUser = response.readEntity(Users.class);

		return testUser;

	}

	@Test
	public void shouldCreateAndGetSettingById() {

		Response response = null;

		// here we should create a setting.
		Users adminUser = getTestUser();

		assertThat(adminUser).isNotNull();

		// get some transponders to make lines objects.
		Invocation.Builder invocationBuilder = commonTarget.path("transponders").path("filter").path("Speed")
				.path("27500").request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		response = invocationBuilder.get();

		assertEquals(Status.OK.getStatusCode(), response.getStatus());

		GenericType<List<Transponders>> genList = new GenericType<List<Transponders>>() {
		};

		List<Transponders> newTransList = response.readEntity(genList);
		assertThat(newTransList.size()).isGreaterThanOrEqualTo(0);

		Settings setting = BuildTestSetting.buildSetting(adminUser, newTransList);

		// http://howtodoinjava.com/2015/08/07/jersey-restful-client-examples/#post
		invocationBuilder = serviceTarget.path("create").matrixParam("login", this.testUsername)
				.request(MediaType.APPLICATION_JSON);

		// trying to use test endpoint.
		// invocationBuilder =
		// serviceTarget.path("create").request(MediaType.APPLICATION_JSON)
		// .accept(MediaType.APPLICATION_JSON);

		Response responsePost = invocationBuilder.post(Entity.entity(setting, MediaType.APPLICATION_JSON));
		assertEquals(Status.CREATED.getStatusCode(), responsePost.getStatus());

		// get setting id
		MultivaluedMap<String, String> headersMap = responsePost.getStringHeaders();
		List<String> locStringList = headersMap.get("SettingId");
		assertEquals(1, locStringList.size());

		String settingIdString = locStringList.get(0);
		long id = Long.parseLong(settingIdString);

		assertThat(id).isGreaterThan(0);
		setting.setId(id);

		// Here we test getting setting by id.
		invocationBuilder = serviceTarget.path("filter").path("id").path(Long.toString(setting.getId()))
				.matrixParam("login", this.testUsername).request(MediaType.APPLICATION_JSON);

		response = invocationBuilder.get();
		assertEquals(Status.OK.getStatusCode(), response.getStatus());

		Settings settingRead = response.readEntity(Settings.class);
		assertThat(settingRead).isNotNull();
	}

	// This test is done in create test.
	// @Test
	// public void shouldGetSettingById() {
	//
	// Response response = null;
	//
	// List<Settings> settList = getUserSettings();
	// if (settList.size() == 0) {
	// return;
	// }
	//
	// Settings sett = settList.get(0);
	//
	// Invocation.Builder invocationBuilder =
	// serviceTarget.path("filter").path("id").path(Long.toString(sett.getId()))
	// .matrixParam("login",
	// this.testUsername).request(MediaType.APPLICATION_JSON)
	// .accept(MediaType.APPLICATION_JSON);
	//
	// response = invocationBuilder.get();
	//
	// assertEquals(Status.OK.getStatusCode(), response.getStatus());
	//
	// Settings setting = response.readEntity(Settings.class);
	//
	// assertThat(setting).isNotNull();
	//
	// }

	private List<Settings> getUserSettings() {
		Response response = null;
		List<Settings> settList = new ArrayList<Settings>();

		// Let's check, if there is user with login admin
		// WebTarget target = client.target(appLocation +
		// "users/filter/login/admin");
		// Response response =
		// target.request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).get();

		Invocation.Builder invocationBuilder = commonTarget.path("users").path("exists").path("login")
				.path(testUsername).request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		response = invocationBuilder.get();

		if (response.getStatus() == Status.ACCEPTED.getStatusCode()) {
			return settList;
		}

		GenericType<List<Settings>> genList = new GenericType<List<Settings>>() {
		};

		// getting settings by userlogin
		// target = client.target(appLocation +
		// "usersettings/filter/login/admin");
		//
		// response =
		// target.request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).get();

		invocationBuilder = serviceTarget.path("filter").path("login").path(this.testUsername)
				.request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		response = invocationBuilder.get();

		assertEquals(Status.OK.getStatusCode(), response.getStatus());

		// http://stackoverflow.com/questions/27643822/marshal-un-marshal-list-objects-in-jersey-jax-rs-using-jaxb
		// Jersey 2

		settList = response.readEntity(genList);

		assertThat(settList).isNotNull();
		assertThat(settList.size()).isGreaterThan(0);
		return settList;

	}

	@Test
	public void shouldGetSettingsByArbitraryFilter() {

		Response response = null;

		// here we should check, if such user exists and find only his settings.
		List<Settings> settList = getUserSettings();
		if (settList == null || settList.size() == 0) {
			return;
		}

		Settings sett = settList.get(0);

		// target = client.target(appLocation + "usersettings/filter/Name/" +
		// sett.getName() + ";login=admin");
		//
		// response =
		// target.request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).get();

		Invocation.Builder invocationBuilder = serviceTarget.path("filter").path("Name").path(sett.getName())
				.matrixParam("login", this.testUsername).request(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		response = invocationBuilder.get();

		assertEquals(Status.OK.getStatusCode(), response.getStatus());

		GenericType<List<Settings>> genList = new GenericType<List<Settings>>() {
		};

		settList = response.readEntity(genList);

		assertThat(settList).isNotNull();
		assertThat(settList.size()).isGreaterThan(0);

	}

}
