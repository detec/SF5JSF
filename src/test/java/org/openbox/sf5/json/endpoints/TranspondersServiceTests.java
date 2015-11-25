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
public class TranspondersServiceTests extends AbstractServiceTest {

	@Test
	public void shouldGetTranspondersByArbitraryFilter() {
		WebTarget target = null;
		Response response = null;
		Client client = createClient();
		target = client.target(appLocation + "transponders/filter/Speed/27500");

		response = target.request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).get();
		assertEquals(Status.OK.getStatusCode(), response.getStatus());

	}

	@Test
	public void shouldGetTransponderById() {
		WebTarget target = null;
		Response response = null;
		Client client = createClient();
		target = client.target(appLocation + "transponders/filter/id/1");

		response = target.request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).get();
		assertEquals(Status.OK.getStatusCode(), response.getStatus());

	}

	@Test
	public void shouldGetTranspondersBySatelliteId() {
		WebTarget target = null;
		Response response = null;
		Client client = createClient();

		target = client.target(appLocation + "transponders/filter;satId=1");
		response = target.request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).get();
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
	}

	@Test
	public void shouldGetAllTransponders() {
		WebTarget target = null;
		Response response = null;
		Client client = createClient();

		target = client.target(appLocation + "transponders/all/");

		response = target.request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).get();
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
	}

	Client createClient() {
		return ClientBuilder.newBuilder().register(JacksonJaxbJsonProvider.class).build();
	}

}
