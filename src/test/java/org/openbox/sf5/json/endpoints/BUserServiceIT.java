package org.openbox.sf5.json.endpoints;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.runners.MethodSorters;
import org.openbox.sf5.model.Users;

@RunWith(JUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BUserServiceIT extends AbstractServiceTest {

	private static final String servicePath = "users";

	@Before
	public void setUp() {
		setUpAbstractTestUser();

		serviceTarget = commonTarget.path(servicePath);
	}

	@Test
	public void loginShouldNotBeFound() {
		Response response = null;

		Invocation.Builder invocationBuilder = serviceTarget.path("exists").path("login").path("loginxxf")
				.request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		response = invocationBuilder.get();

		// there is no such login.
		assertEquals(Status.NO_CONTENT.getStatusCode(), response.getStatus());

	}

	@Test
	public void loginShouldNotBeFoundXML() {
		Response response = null;

		Invocation.Builder invocationBuilder = serviceTarget.path("exists").path("login").path("loginxxf")
				.request(MediaType.APPLICATION_XML).accept(MediaType.APPLICATION_XML);

		response = invocationBuilder.get();

		// there is no such login.
		assertEquals(Status.NO_CONTENT.getStatusCode(), response.getStatus());
	}

	@Test
	public void shouldCheckCreateTestLogin() {
		Response response = null;

		Invocation.Builder invocationBuilder = serviceTarget.path("exists").path("login").path(testUsername)
				.request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		response = invocationBuilder.get();

		if (response.getStatus() == Status.ACCEPTED.getStatusCode()) {
			return;
		}

		// here we create user
		Users testUser = new Users("Test user", testUsername);

		invocationBuilder = serviceTarget.path("create").request(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		Response responsePost = invocationBuilder.post(Entity.entity(testUser, MediaType.APPLICATION_JSON));
		assertEquals(Status.CREATED.getStatusCode(), responsePost.getStatus());

		long userId = responsePost.readEntity(Long.class);
		assertThat(userId).isNotZero();
	}

	@Test
	public void shouldCheckCreateAnotherTestLoginXML() {
		Response response = null;

		Invocation.Builder invocationBuilder = serviceTarget.path("exists").path("login").path("Fake")
				.request(MediaType.APPLICATION_XML).accept(MediaType.APPLICATION_XML);
		response = invocationBuilder.get();
		if (response.getStatus() == Status.ACCEPTED.getStatusCode()) {
			return;
		}

		// here we create user
		Users testUser = new Users("Fake user", "Fake");

		invocationBuilder = serviceTarget.path("create").request(MediaType.APPLICATION_XML)
				.accept(MediaType.APPLICATION_XML);
		Response responsePost = invocationBuilder.post(Entity.entity(testUser, MediaType.APPLICATION_XML));
		assertEquals(Status.CREATED.getStatusCode(), responsePost.getStatus());

		long userId = responsePost.readEntity(Long.class);
		assertThat(userId).isNotZero();

	}

}
