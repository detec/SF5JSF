package org.openbox.sf5.json.endpoints;

import java.util.logging.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.openbox.sf5.json.config.JacksonObjectMapperConfiguration;
import org.openbox.sf5.json.config.MyApplicationResourceConfig;
import org.openbox.sf5.json.config.MyObjectMapperProvider;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class AbstractServiceTest {

	public Logger LOGGER = Logger.getLogger(MyApplicationResourceConfig.class.getName());

	public static final String appLocation = "http://localhost:8080/SF5JSF-test/";

	public static final String jsonPath = "json";

	public Client client;

	public ObjectMapper mapper = new ObjectMapper();

	public WebTarget commonTarget;

	public WebTarget serviceTarget;

	public String testUsername = "ITUser";

	public Client createTestUserClient() {

		configureMapper();

		//JacksonJsonProvider provider = new JacksonJsonProvider(mapper);

		return ClientBuilder.newBuilder()

				// 18.12.2015, will try MOXy, not Jackson
				// .register(MoxyJsonFeature.class)

				//.register(JacksonJaxbJsonProvider.class)

				// without Jackson feature Strings are not unmarshalled
				// But MOXy doesn't understand this date format
				.register(JacksonFeature.class)
				.register(MyObjectMapperProvider.class)

				//.register(JacksonJsonProvider.class)

				// registering configured provider with mapper
				//.register(provider)

				// https://cwiki.apache.org/confluence/display/CXF20DOC/JAX-RS+Data+Bindings
				// As per JAX-RS 2.0 specification, the support of JSR-353 Java API for JSON Processing is mandatory requirement and implies the presence of message body reader(s)/writer(s) for following types: JsonStructure, JsonArray and JsonObject.
				// It doesn't help
//				.register(MessageBodyReader.class)
//				.register(MessageBodyWriter.class)

				.register(MultiPartFeature.class)

				// we have the same objectmapper config for client and server.
				//.register(MarshallingFeature.class)

				// Log showed that transponders come OK.
				// .register(new LoggingFilter(LOGGER, true))

				.build();
	}

	public void setUpAbstractTestUser() {
		client = createTestUserClient();
		commonTarget = client.target(appLocation).path(jsonPath);
	}

	public void configureMapper() {
		// mapper.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING,
		// true);
		// mapper.configure(DeserializationFeature.READ_ENUMS_USING_TO_STRING,
		// true);
		// mapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
		// mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
		// false);

		JacksonObjectMapperConfiguration.configureMapper(mapper);
	}

}
