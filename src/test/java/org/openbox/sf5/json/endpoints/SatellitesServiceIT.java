package org.openbox.sf5.json.endpoints;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class SatellitesServiceIT extends AbstractServiceTest {

	private static final String servicePath = "satellites";

	@Before
	public void setUp() {
		setUpAbstractTestUser();

		serviceTarget = commonTarget.path(servicePath);
	}

	@Test
	public void shouldgetSatelliteById() {

		Response response = null;

		Invocation.Builder invocationBuilder = serviceTarget.path("filter").path("id").path("1")
				.request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
				// target = client.target(appLocation +
				// "satellites/filter/id/1");

		// response =
		// target.request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).get();

		response = invocationBuilder.get();

		assertEquals(Status.OK.getStatusCode(), response.getStatus());

	}

	@Test
	public void shouldgetAllSatellites() {

		Response response = null;
		// target = client.target(appLocation + "satellites/all/");

		Invocation.Builder invocationBuilder = serviceTarget.path("all").request(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		// response =
		// target.request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).get();
		response = invocationBuilder.get();

		assertEquals(Status.OK.getStatusCode(), response.getStatus());

	}

	@Test
	public void getSatellitesByArbitraryFilter() {

		Response response = null;

		// target = client.target(appLocation + "satellites/filter/Name/13E");

		// response =
		// target.request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).get();

		Invocation.Builder invocationBuilder = serviceTarget.path("filter").path("Name").path("13E")
				.request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		response = invocationBuilder.get();

		assertEquals(Status.OK.getStatusCode(), response.getStatus());
	}

}
