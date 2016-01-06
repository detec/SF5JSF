package org.openbox.sf5.common;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.stream.Stream;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;
import org.junit.Before;
import org.junit.Test;
import org.openbox.sf5.json.endpoints.AbstractServiceTest;

public class SendTransponderFilesJSONIT extends AbstractServiceTest {

	private static final String servicePath = "transponders";

	@Before
	public void setUp() {
		setUpAbstractTestUser();
		serviceTarget = commonTarget.path(servicePath);
	}

	@Test
	public void shouldSendFileJsonEndpoint() throws URISyntaxException, IOException {
		Stream<Path> transponderFilesPathes = IntersectionsTests.getTransponderFilesStreamPath();

		// configureMapper();

		transponderFilesPathes.forEach(t -> {

			FileDataBodyPart filePart = new FileDataBodyPart("file", t.toFile());

			@SuppressWarnings("resource")
			final FormDataMultiPart multipart = (FormDataMultiPart) new FormDataMultiPart().field("foo", "bar")
					.bodyPart(filePart);

			// 05.01.2016, trying to specify explicitly return type as for XML
			// there is no direct converter
			Invocation.Builder invocationBuilder = serviceTarget.path("upload").request(MediaType.APPLICATION_JSON);
			// Response responsePost =
			// invocationBuilder.post(Entity.entity(setting,
			// MediaType.APPLICATION_JSON));

			Response responsePost = invocationBuilder.post(Entity.entity(multipart, multipart.getMediaType()));
			int returnStatus = responsePost.getStatus();

			if (returnStatus == 500) {
				String errorMsg = responsePost.readEntity(String.class);
				assertEquals("", errorMsg); // to print it in common report for
											// sure.
			}
			assertEquals(Status.OK.getStatusCode(), returnStatus);

			// we return 500 status and error message or just 200 status.

			// Boolean result = responsePost.readEntity(Boolean.class);
			//
			// assertThat(result.booleanValue()).isTrue();

			// Response lambdaResponse =
			// target.request().post(Entity.entity(multipart,
			// multipart.getMediaType()));
		});
	}

}
