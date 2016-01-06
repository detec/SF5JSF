package org.openbox.sf5.json.config;

import java.util.logging.Logger;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.moxy.xml.MoxyXmlFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

// http://stackoverflow.com/questions/18872931/custom-objectmapper-with-jersey-2-2-and-jackson-2-1

// http://blog.dejavu.sk/2013/11/19/registering-resources-and-providers-in-jersey-2/

@ApplicationPath("/json/")
public class MyApplicationResourceConfig extends ResourceConfig {

	private Logger LOGGER = Logger.getLogger(MyApplicationResourceConfig.class.getName());

	public MyApplicationResourceConfig() {

		// Register resources and providers using package-scanning. Provider is
		// registered if it is in package.
		packages("org.openbox.sf5.json");

		// register(new LoggingFilter(LOGGER, true));
		register(MultiPartFeature.class);

		// https://jersey.java.net/documentation/latest/media.html#d0e9110
		// Enabling MOXy
		register(MoxyXmlFeature.class);
		register(MOXyJsonContextResolver.class);

		// register(new LoggingFilter(LOGGER, true));

		// LOGGER.info("Registering custom MarshallingFeature");
		// register(MarshallingFeature.class);

		// register(JacksonFeature.class);
		// register(MyObjectMapperProvider.class);

		// Enable Tracing support.
		property(ServerProperties.TRACING, "ALL");

		// it is active by default
		property(ServerProperties.PROVIDER_SCANNING_RECURSIVE, true);

		property(ServerProperties.APPLICATION_NAME, "Openbox SF5 JavaEE 7 rest app");

		// 18.12.2015 - let's try to use built-in MOXy.
		// disable automatic discovery of providers
		property(ServerProperties.METAINF_SERVICES_LOOKUP_DISABLE, true);

		// it finally works with
		// property(ServerProperties.MOXY_JSON_FEATURE_DISABLE, true);
		// 05.01.2016
		property(ServerProperties.MOXY_JSON_FEATURE_DISABLE, false);

		// https://jersey.java.net/documentation/latest/media.html#d0e9110
		// property(MarshallerProperties.JSON_MARSHAL_EMPTY_COLLECTIONS, false);
		// property(MarshallerProperties.INDENT_STRING, true);

	}

}
