package org.openbox.sf5.json.endpoints;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

import org.eclipse.persistence.jaxb.rs.MOXyJsonProvider;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.openbox.sf5.json.config.AppPathReader;
import org.openbox.sf5.json.converters.BooleanMessageBodyReader;
import org.openbox.sf5.json.converters.LongMessageBodyReader;

public abstract class AbstractServiceTest {

	public Logger LOGGER = Logger.getLogger(getClass().getName());

	// public static final String appLocation =
	// "http://localhost:8080/SF5JSF-test/";
	public static final String appLocation = "http://localhost:8080/";

	public static final String jsonPath = AppPathReader.JAXRS_PATH;

	public Client client;

	// public ObjectMapper mapper = new ObjectMapper();

	public WebTarget commonTarget;

	public WebTarget serviceTarget;

	public String testUsername = "ITUser";

	private Properties property = new Properties();

	// http://stackoverflow.com/questions/8740234/postconstruct-checked-exceptions
	public void loadProperties() {

		// using try with resources
		try (InputStream in = getClass().getResourceAsStream("/application.properties")) {
			property.load(in);
		} catch (IOException e) {

			LOGGER.log(Level.SEVERE, e.getMessage(), e);
		}

	}

	public Client createTestUserClient() {

		configureMapper();

		// loading context path
		loadProperties();

		// JacksonJsonProvider provider = new JacksonJsonProvider(mapper);

		// return ClientBuilder.newBuilder()
		//
		// .register(MoxyXmlFeature.class)
		//
		// .register(MOXyJsonContextResolver.class)
		//
		// .register(MultiPartFeature.class)
		//
		// .register(BooleanMessageBodyReader.class).register(LongMessageBodyReader.class)
		//
		// .build();

		MOXyJsonProvider moxyJsonProvider = new MOXyJsonProvider();
		moxyJsonProvider.setFormattedOutput(true);
		moxyJsonProvider.setMarshalEmptyCollections(true);
		moxyJsonProvider.setIncludeRoot(true);

		return new ResteasyClientBuilder().connectionPoolSize(20).register(BooleanMessageBodyReader.class)
				.register(LongMessageBodyReader.class).register(moxyJsonProvider).build();
	}

	public void setUpAbstractTestUser() {
		client = createTestUserClient();
		commonTarget = client.target(appLocation).path(property.getProperty("context.path")).path(jsonPath);
	}

	public void configureMapper() {

		// JacksonObjectMapperConfiguration.configureMapper(mapper);
	}

}
