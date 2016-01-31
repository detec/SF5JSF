package org.openbox.sf5.json.config;

import java.util.logging.Logger;

import javax.ws.rs.ApplicationPath;

// http://stackoverflow.com/questions/18872931/custom-objectmapper-with-jersey-2-2-and-jackson-2-1

// http://blog.dejavu.sk/2013/11/19/registering-resources-and-providers-in-jersey-2/

//@ApplicationPath("/json/")
@ApplicationPath("/" + AppPathReader.JAXRS_PATH)
public class MyApplicationResourceConfig extends javax.ws.rs.core.Application
// extends ResourceConfig

{

	private Logger LOGGER = Logger.getLogger(MyApplicationResourceConfig.class.getName());

	public MyApplicationResourceConfig() {

//		// Register resources and providers using package-scanning. Provider is
//		// registered if it is in package.
//		packages("org.openbox.sf5.json");
//
//		// register(new LoggingFilter(LOGGER, true));
//		register(MultiPartFeature.class);
//
//		register(MoxyXmlFeature.class);
//		register(MOXyJsonContextResolver.class);
//
//		// Enable Tracing support.
//		property(ServerProperties.TRACING, "ALL");
//
//
//		// it is active by default
//		property(ServerProperties.PROVIDER_SCANNING_RECURSIVE, true);
//
//
//		property(ServerProperties.APPLICATION_NAME, "Openbox SF5 JavaEE 7 rest app");
//
//		property(ServerProperties.METAINF_SERVICES_LOOKUP_DISABLE, false);
//
//		property(ServerProperties.MOXY_JSON_FEATURE_DISABLE, false);
//



	}

}

