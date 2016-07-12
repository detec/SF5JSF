package org.openbox.sf5.json.endpoints;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

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
import org.openbox.sf5.model.Satellites;

@RunWith(JUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SatellitesServiceIT extends AbstractServiceTest {

	private static final String servicePath = "satellites";

	private long satelliteId;

	@Before
	public void setUp() {
		setUpAbstractTestUser();

		serviceTarget = commonTarget.path(servicePath);
	}

	@Test
	public void shouldgetAllSatellites() {

		Response response = null;
		// target = client.target(appLocation + "satellites/all/");

		Invocation.Builder invocationBuilder = serviceTarget

				// .path("all")

				.request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		// response =
		// target.request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).get();
		response = invocationBuilder.get();

		assertEquals(Status.OK.getStatusCode(), response.getStatus());

		GenericType<List<Satellites>> genList = new GenericType<List<Satellites>>() {
		};

		List<Satellites> satList = invocationBuilder.get(genList);

		assertThat(satList).isNotNull();
		assertThat(satList.size()).isGreaterThan(0);

		Satellites satellite = satList.get(0);
		assertTrue(satellite instanceof Satellites);

	}

	@Test
	public void shouldgetAllSatellitesXML() {

		Response response = null;

		Invocation.Builder invocationBuilder = serviceTarget

				// .path("all")

				.request(MediaType.APPLICATION_XML).accept(MediaType.APPLICATION_XML);
		response = invocationBuilder.get();

		assertEquals(Status.OK.getStatusCode(), response.getStatus());

		GenericType<List<Satellites>> genList = new GenericType<List<Satellites>>() {
		};

		List<Satellites> satList = invocationBuilder.get(genList);

		assertThat(satList).isNotNull();
		assertThat(satList.size()).isGreaterThan(0);

		Satellites satellite = satList.get(0);
		assertTrue(satellite instanceof Satellites);
	}

	public long getSatelliteId() {
		Response response = null;

		Invocation.Builder invocationBuilder = serviceTarget.path("filter").path("Name").path("13E")
				.request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		response = invocationBuilder.get();

		assertEquals(Status.OK.getStatusCode(), response.getStatus());

		GenericType<List<Satellites>> genList = new GenericType<List<Satellites>>() {
		};

		List<Satellites> satList = invocationBuilder.get(genList);

		assertThat(satList).isNotNull();
		assertThat(satList.size()).isGreaterThan(0);

		Satellites satellite = satList.get(0);
		assertTrue(satellite instanceof Satellites);
		satelliteId = satellite.getId();

		return satelliteId;
	}

	@Test
	public void shouldgetSatelliteByArbitraryFilter() {
		Response response = null;

		getSatelliteId();

		// test getting by id
		Invocation.Builder invocationBuilder = serviceTarget

				// .path("filter").path("id")

				.path(String.valueOf(satelliteId)).request(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		response = invocationBuilder.get();

		assertEquals(Status.OK.getStatusCode(), response.getStatus());

		Satellites sat = response.readEntity(Satellites.class);

		assertThat(sat).isNotNull();

		assertTrue(sat instanceof Satellites);
	}

	public long getSatelliteIdXML() {
		Response response = null;
		Invocation.Builder invocationBuilder = serviceTarget.path("filter").path("Name").path("13E")
				.request(MediaType.APPLICATION_XML).accept(MediaType.APPLICATION_XML);

		response = invocationBuilder.get();

		assertEquals(Status.OK.getStatusCode(), response.getStatus());

		GenericType<List<Satellites>> genList = new GenericType<List<Satellites>>() {
		};

		List<Satellites> satList = invocationBuilder.get(genList);

		assertThat(satList).isNotNull();
		assertThat(satList.size()).isGreaterThan(0);

		Satellites satellite = satList.get(0);
		assertTrue(satellite instanceof Satellites);
		satelliteId = satellite.getId();

		return satelliteId;
	}

	@Test
	public void shouldGetSatelliteByArbitraryFilterXML() {
		Response response = null;
		getSatelliteIdXML();

		Invocation.Builder invocationBuilder = serviceTarget

				// .path("filter").path("id")

				.path(String.valueOf(satelliteId)).request(MediaType.APPLICATION_XML).accept(MediaType.APPLICATION_XML);

		response = invocationBuilder.get();

		assertEquals(Status.OK.getStatusCode(), response.getStatus());

		Satellites sat = response.readEntity(Satellites.class);

		assertThat(sat).isNotNull();
		assertTrue(sat instanceof Satellites);

	}

}
