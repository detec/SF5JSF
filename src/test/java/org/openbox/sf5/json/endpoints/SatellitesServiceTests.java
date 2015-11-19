package org.openbox.sf5.json.endpoints;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;

@RunWith(JUnit4.class)
public class SatellitesServiceTests {

	@Test
	public void shouldgetSatelliteById() {

		WebTarget target = null;
		Response response = null;

		Client client = createClient();

		target = client.target("http://localhost:8080/SF5JSF-test/json/satellites/filter/id/1");

		response = target.request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).get();

		assertEquals(Status.OK.getStatusCode(), response.getStatus());

	}

	@Test
	public void shouldgetAllSatellites() {

		WebTarget target = null;
		Response response = null;
		Client client = createClient();

		target = client.target("http://localhost:8080/SF5JSF-test/json/satellites/all/");

		response = target.request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).get();
		assertEquals(Status.OK.getStatusCode(), response.getStatus());

	}

	@Test
	public void getSatellitesByArbitraryFilter() {
		WebTarget target = null;
		Response response = null;
		Client client = createClient();

		target = client.target("http://localhost:8080/SF5JSF-test/json/satellites/filter/Name/13E");

		response = target.request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).get();
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
	}

	Client createClient() {
		return ClientBuilder.newBuilder().register(JacksonJaxbJsonProvider.class).build();
	}

}
