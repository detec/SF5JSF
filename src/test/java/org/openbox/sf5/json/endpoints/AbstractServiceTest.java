package org.openbox.sf5.json.endpoints;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.media.multipart.MultiPartFeature;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;

public abstract class AbstractServiceTest {
	public static final String appLocation = "http://localhost:8080/SF5JSF-test/";

	public static final String jsonPath = "json";

	public Client client;

	public ObjectMapper mapper = new ObjectMapper();

	public WebTarget commonTarget;

	public WebTarget serviceTarget;

	public String testUsername = "ITUser";

	public Client createTestUserClient() {

		return ClientBuilder.newBuilder().register(JacksonJaxbJsonProvider.class).register(JacksonFeature.class)
				.register(MultiPartFeature.class)

				// .register(new LoggingFilter())

				.build();
	}

	public void setUpAbstractTestUser() {
		client = createTestUserClient();
		commonTarget = client.target(appLocation).path(jsonPath);
	}

	public void configureMapper() {
		mapper.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
		mapper.configure(DeserializationFeature.READ_ENUMS_USING_TO_STRING, true);
		mapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

}
