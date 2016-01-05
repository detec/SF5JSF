package org.openbox.sf5.json.endpoints;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.openbox.sf5.model.Transponders;

@RunWith(JUnit4.class)
public class TranspondersServiceIT extends AbstractServiceTest {

	private static final String servicePath = "transponders";

	private Validator validator;

	@Before
	public void setUp() {
		setUpAbstractTestUser();

		serviceTarget = commonTarget.path(servicePath);

		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void shouldGetTranspondersByArbitraryFilter() {

		Response response = null;

		// target = client.target(appLocation +
		// "transponders/filter/Speed/27500");
		//
		// response =
		// target.request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).get();

		Invocation.Builder invocationBuilder = serviceTarget.path("filter").path("Speed").path("27500")
				.request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		response = invocationBuilder.get();

		assertEquals(Status.OK.getStatusCode(), response.getStatus());

		GenericType<List<Transponders>> genList = new GenericType<List<Transponders>>() {
		};

		List<Transponders> transList = invocationBuilder.get(genList);

		assertThat(transList).isNotNull();
		assertThat(transList.size()).isGreaterThan(0);
		validateTranspondersList(transList);

	}

	@Test
	public void shouldGetTranspondersByArbitraryFilterXML() {
		Response response = null;
		Invocation.Builder invocationBuilder = serviceTarget.path("filter").path("Speed").path("27500")
				.request(MediaType.APPLICATION_XML).accept(MediaType.APPLICATION_XML);

		response = invocationBuilder.get();

		assertEquals(Status.OK.getStatusCode(), response.getStatus());

		GenericType<List<Transponders>> genList = new GenericType<List<Transponders>>() {
		};

		List<Transponders> transList = invocationBuilder.get(genList);

		assertThat(transList).isNotNull();
		assertThat(transList.size()).isGreaterThan(0);
		validateTranspondersList(transList);
	}

	@Test
	public void shouldGetTransponderById() {

		Response response = null;

		// target = client.target(appLocation + "transponders/filter/id/1");
		// response =
		// target.request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).get();

		Invocation.Builder invocationBuilder = serviceTarget.path("filter").path("id").path("1")
				.request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		response = invocationBuilder.get();

		assertEquals(Status.OK.getStatusCode(), response.getStatus());

		Transponders trans = response.readEntity(Transponders.class);

		assertThat(trans).isNotNull();
		Set<ConstraintViolation<Transponders>> constraintViolations = validator.validate(trans);
		assertEquals(0, constraintViolations.size());

	}

	@Test
	public void shouldGetTransponderByIdXML() {
		Response response = null;

		Invocation.Builder invocationBuilder = serviceTarget.path("filter").path("id").path("1")
				.request(MediaType.APPLICATION_XML).accept(MediaType.APPLICATION_XML);
		response = invocationBuilder.get();

		assertEquals(Status.OK.getStatusCode(), response.getStatus());

		Transponders trans = response.readEntity(Transponders.class);

		assertThat(trans).isNotNull();
		Set<ConstraintViolation<Transponders>> constraintViolations = validator.validate(trans);
		assertEquals(0, constraintViolations.size());

	}

	@Test
	public void shouldGetTranspondersBySatelliteId() {

		Response response = null;

		// target = client.target(appLocation + "transponders/filter;satId=1");
		// response =
		// target.request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).get();

		Invocation.Builder invocationBuilder = serviceTarget.path("filter").matrixParam("satId", "1")
				.request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		response = invocationBuilder.get();
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		GenericType<List<Transponders>> genList = new GenericType<List<Transponders>>() {
		};

		List<Transponders> transList = invocationBuilder.get(genList);

		assertThat(transList).isNotNull();
		assertThat(transList.size()).isGreaterThan(0);
		validateTranspondersList(transList);
	}

	@Test
	public void shouldGetTranspondersBySatelliteIdXML() {
		Response response = null;
		Invocation.Builder invocationBuilder = serviceTarget.path("filter").matrixParam("satId", "1")
				.request(MediaType.APPLICATION_XML).accept(MediaType.APPLICATION_XML);
		response = invocationBuilder.get();
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		GenericType<List<Transponders>> genList = new GenericType<List<Transponders>>() {
		};

		List<Transponders> transList = invocationBuilder.get(genList);

		assertThat(transList).isNotNull();
		assertThat(transList.size()).isGreaterThan(0);
		validateTranspondersList(transList);
	}

	@Test
	public void shouldGetAllTransponders() {

		Response response = null;

		// target = client.target(appLocation + "transponders/all/");
		// response =
		// target.request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).get();

		Invocation.Builder invocationBuilder = serviceTarget.path("all").request(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		response = invocationBuilder.get();
		assertEquals(Status.OK.getStatusCode(), response.getStatus());

		GenericType<List<Transponders>> genList = new GenericType<List<Transponders>>() {
		};

		List<Transponders> transList = invocationBuilder.get(genList);

		assertThat(transList).isNotNull();
		assertThat(transList.size()).isGreaterThan(0);
		validateTranspondersList(transList);
	}

	@Test
	public void shouldGetAllTranspondersXML() {
		Response response = null;
		Invocation.Builder invocationBuilder = serviceTarget.path("all").request(MediaType.APPLICATION_XML)
				.accept(MediaType.APPLICATION_XML);
		response = invocationBuilder.get();
		assertEquals(Status.OK.getStatusCode(), response.getStatus());

		GenericType<List<Transponders>> genList = new GenericType<List<Transponders>>() {
		};

		List<Transponders> transList = invocationBuilder.get(genList);

		assertThat(transList).isNotNull();
		assertThat(transList.size()).isGreaterThan(0);
		validateTranspondersList(transList);

	}

	public void validateTranspondersList(List<Transponders> transList) {
		int[] resultArray = new int[1];

		transList.forEach(t -> {
			Set<ConstraintViolation<Transponders>> constraintViolations = validator.validate(t);
			resultArray[0] = resultArray[0] + constraintViolations.size();

		});

		assertEquals(0, resultArray[0]);
	}

}
