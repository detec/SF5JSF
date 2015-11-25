package org.openbox.sf5.json.endpoints;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.openbox.sf5.model.Settings;

@RunWith(JUnit4.class)
public class SettingsServiceTests {

	@Test
	public void shouldGetSettings() {
		WebTarget target = null;
		Response response = null;
		Client client = createClient();

		List<Settings> settList = getUserSettings(client);
		if (settList.size() == 0) {
			return;
		}

		Settings sett = settList.get(0);

		target = client.target("http://localhost:8080/SF5JSF-test/json/usersettings/filter/id/"
				+ Long.toString(sett.getId()) + ";login=admin");

		response = target.request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).get();
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
	}

	private List<Settings> getUserSettings(Client client) {
		List<Settings> settList = new ArrayList<Settings>();

		// Let's check, if there is user with login admin
		WebTarget target = client.target("http://localhost:8080/SF5JSF-test/json/users/filter/login/admin");
		Response response = target.request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).get();

		if (response.getStatus() == (Status.NOT_FOUND.getStatusCode())) {
			return settList; // no user with login admin
		}

		GenericType<List<Settings>> genList = new GenericType<List<Settings>>() {
		};

		// getting settings by userlogin
		target = client.target("http://localhost:8080/SF5JSF-test/json/usersettings/filter/login/admin");

		response = target.request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).get();
		// response =
		// target.request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).get(genList);
		assertEquals(Status.OK.getStatusCode(), response.getStatus());

		// http://stackoverflow.com/questions/27643822/marshal-un-marshal-list-objects-in-jersey-jax-rs-using-jaxb
		// Jersey 2

		// List<Book> books =
		// client.target(REST_SERVICE_URL).request().get(bookType);

		// settList = response.readEntity(genList);
		settList = target.request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).get(genList);

		assertThat(settList).isNotNull();
		return settList;

	}

	@Test
	public void shouldGetSettingsByArbitraryFilter() {
		WebTarget target = null;
		Response response = null;
		Client client = createClient();

		// here we should check, if such user exists and find only his settings.
		List<Settings> settList = getUserSettings(client);
		if (settList == null || settList.size() == 0) {
			return;
		}

		Settings sett = settList.get(0);

		target = client.target(
				"http://localhost:8080/SF5JSF-test/json/usersettings/filter/Name/" + sett.getName() + ";login=admin");

		response = target.request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).get();
		assertEquals(Status.OK.getStatusCode(), response.getStatus());

	}

	Client createClient() {
		// return
		// ClientBuilder.newBuilder().register(JacksonJaxbJsonProvider.class).build();

		return ClientBuilder.newBuilder().register(JacksonFeature.class).build();
	}

}
