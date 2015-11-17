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

	/*
	 * private ConnectionManager cm;
	 *
	 * private DAO DAO;
	 *
	 * private ObjectService service;
	 *
	 * private ObjectsController contr;
	 *
	 * @Before public void setUp() { cm = new ConnectionManager();
	 *
	 * DAO = new DAOImpl(); DAO.setCm(cm);
	 *
	 * service = new ObjectServiceImpl(); service.setDAO(DAO);
	 *
	 * contr = new ObjectsController(); contr.setService(service); }
	 */

	@Test
	public void shouldgetSatelliteById() {

		WebTarget target = null;
		Response response = null;

		// create and save satellite.

		// it is useless to save in momory database as it parses from the real
		// database.
		// Satellites sat = new Satellites();
		// sat.setName("test");
		// contr.saveOrUpdate(sat); // as it is saved it should have id 1.

		// http://localhost:8080/SF5JSF-test/json/satellites/all/

		Client client = createClient();

		// let's get all satellites.
		// WebTarget target =
		// client.target("http://localhost:8080/SF5JSF-test/json/satellites/all/");
		// Response response =
		// target.request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).get();
		// List<Satellites> satList = response.readEntity(List.class);

		target = client.target("http://localhost:8080/SF5JSF-test/json/satellites/filter/id/1");

		response = target.request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).get();

		// Satellites gotSatellite = response.readEntity(Satellites.class);

		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		// assertEquals(1, gotSatellite.getId());
		// assertEquals("test", gotSatellite.getName());

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

	// WebTarget target = null;
	// Response response = null;

	Client createClient() {
		return ClientBuilder.newBuilder().register(JacksonJaxbJsonProvider.class).build();
	}

}
