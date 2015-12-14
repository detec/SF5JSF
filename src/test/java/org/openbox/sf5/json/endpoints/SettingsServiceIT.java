package org.openbox.sf5.json.endpoints;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.openbox.sf5.model.Settings;

@RunWith(JUnit4.class)
public class SettingsServiceIT extends AbstractServiceTest {

	private static final String servicePath = "usersettings";

	@Before
	public void setUp() {
		setUpAbstractTestUser();

		serviceTarget = commonTarget.path(servicePath);
	}

	@Test
	public void shouldGetSettingById() {

		Response response = null;

		List<Settings> settList = getUserSettings();
		if (settList.size() == 0) {
			return;
		}

		Settings sett = settList.get(0);

		// target = client.target(appLocation + "usersettings/filter/id/" +
		// Long.toString(sett.getId()) + ";login=admin");
		//
		// response =
		// target.request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).get();
		Invocation.Builder invocationBuilder = serviceTarget.path("filter").path("id").path(Long.toString(sett.getId()))
				.matrixParam("login", this.testUsername).request(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		response = invocationBuilder.get();

		assertEquals(Status.OK.getStatusCode(), response.getStatus());

		Settings setting = response.readEntity(Settings.class);

		assertThat(setting).isNotNull();

	}

	private List<Settings> getUserSettings() {
		List<Settings> settList = new ArrayList<Settings>();

		// Let's check, if there is user with login admin
		// WebTarget target = client.target(appLocation +
		// "users/filter/login/admin");
		// Response response =
		// target.request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).get();

		Invocation.Builder invocationBuilder = this.commonTarget.path("users").path("filter").path("login")
				.path(this.testUsername).request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		Response response = invocationBuilder.get();

		if (response.getStatus() == (Status.NOT_FOUND.getStatusCode())) {
			return settList; // no user with login admin
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
