package org.openbox.sf5.json.endpoints;

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

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.openbox.sf5.model.Settings;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;

@RunWith(JUnit4.class)
public class SettingsServiceTests {

	@Test
	public void shouldGetSettings() {
		WebTarget target = null;
		Response response = null;
		Client client = createClient();

		List<Settings> settList = getUserSettings(target, response, client);
		if (settList.size() == 0) {
			return;
		}

		Settings sett = settList.get(0);

		target = client.target("http://localhost:8080/SF5JSF-test/json/usersettings/filter/id/"
				+ Long.toString(sett.getId()) + ";login=admin");

		response = target.request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).get();
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
	}

	private List<Settings> getUserSettings(WebTarget target, Response response, Client client) {
		List<Settings> settList = new ArrayList<Settings>();

		// Let's check, if there is user with login admin
		target = client.target("http://localhost:8080/SF5JSF-test/json/users/filter/login/admin");
		response = target.request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).get();

		if (response.getStatus() == (Status.NOT_FOUND.getStatusCode())) {
			return settList; // no user with login admin
		}

		// getting settings by userlogin
		target = client.target("http://localhost:8080/SF5JSF-test/json/usersettings/filter/login/admin");
		response = target.request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).get();
		assertEquals(Status.OK.getStatusCode(), response.getStatus());

		// Jersey 2
		settList = response.readEntity(new GenericType<List<Settings>>() {
		});

		return settList;

	}

	@Test
	public void shouldGetSettingsByArbitraryFilter() {
		WebTarget target = null;
		Response response = null;
		Client client = createClient();

		// here we should check, if such user exists and find only his settings.
		List<Settings> settList = getUserSettings(target, response, client);
		if (settList.size() == 0) {
			return;
		}

		Settings sett = settList.get(0);

		target = client.target(
				"http://localhost:8080/SF5JSF-test/json/usersettings/filter/Name/" + sett.getName() + ";login=admin");

		response = target.request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).get();
		assertEquals(Status.OK.getStatusCode(), response.getStatus());

	}

	Client createClient() {
		return ClientBuilder.newBuilder().register(JacksonJaxbJsonProvider.class).build();
	}

}
