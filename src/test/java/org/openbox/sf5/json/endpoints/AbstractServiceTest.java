package org.openbox.sf5.json.endpoints;

import java.util.logging.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.moxy.xml.MoxyXmlFeature;
import org.openbox.sf5.json.config.MOXyJsonContextResolver;
import org.openbox.sf5.json.config.MyApplicationResourceConfig;
import org.openbox.sf5.json.converters.BooleanMessageBodyReader;
import org.openbox.sf5.json.converters.LongMessageBodyReader;

public abstract class AbstractServiceTest {

	public Logger LOGGER = Logger.getLogger(MyApplicationResourceConfig.class.getName());

	public static final String appLocation = "http://localhost:8080/SF5JSF-test/";

	public static final String jsonPath = "json";

	public Client client;

	// public ObjectMapper mapper = new ObjectMapper();

	public WebTarget commonTarget;

	public WebTarget serviceTarget;

	public String testUsername = "ITUser";

	public Client createTestUserClient() {

		configureMapper();

		// JacksonJsonProvider provider = new JacksonJsonProvider(mapper);

		return ClientBuilder.newBuilder()

				// 18.12.2015, will try MOXy, not Jackson
				// .register(MoxyJsonFeature.class)

				// .register(JacksonJaxbJsonProvider.class)

				// without Jackson feature Strings are not unmarshalled
				// But MOXy doesn't understand this date format
				// .register(JacksonFeature.class)

				// .register(MyObjectMapperProvider.class)

				// 05.01.2016, trying to use MOXy
				.register(MoxyXmlFeature.class)

				.register(MOXyJsonContextResolver.class)

				// .register(JacksonJsonProvider.class)

				// registering configured provider with mapper
				// .register(provider)

				.register(MultiPartFeature.class)

				.register(BooleanMessageBodyReader.class).register(LongMessageBodyReader.class)

				// we have the same objectmapper config for client and server.
				// .register(MarshallingFeature.class)

				// Log showed that transponders come OK.
				// .register(new LoggingFilter(LOGGER, true))

				.build();
	}

	public void setUpAbstractTestUser() {
		client = createTestUserClient();
		commonTarget = client.target(appLocation).path(jsonPath);
	}

	public void configureMapper() {

		// JacksonObjectMapperConfiguration.configureMapper(mapper);
	}

}
