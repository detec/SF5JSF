package org.openbox.sf5.json.config;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import javax.ws.rs.ApplicationPath;

import org.eclipse.persistence.jaxb.rs.MOXyJsonProvider;
import org.openbox.sf5.json.endpoints.SatellitesService;
import org.openbox.sf5.json.endpoints.SettingsService;
import org.openbox.sf5.json.endpoints.TranspondersService;
import org.openbox.sf5.json.endpoints.UsersService;

// http://stackoverflow.com/questions/18872931/custom-objectmapper-with-jersey-2-2-and-jackson-2-1

// http://blog.dejavu.sk/2013/11/19/registering-resources-and-providers-in-jersey-2/

//@ApplicationPath("/json/")
@ApplicationPath("/" + AppPathReader.JAXRS_PATH)
public class MyApplicationResourceConfig extends javax.ws.rs.core.Application
// extends ResourceConfig

{

	private Logger LOGGER = Logger.getLogger(MyApplicationResourceConfig.class.getName());

	// @Override
	// public Set<Class<?>> getClasses() {
	// HashSet<Class<?>> set = new HashSet<Class<?>>();
	// set.add(MOXyJsonProvider.class);
	// return set;
	//
	// }

	@Override
	public Set<Class<?>> getClasses() {
		HashSet<Class<?>> set = new HashSet<Class<?>>();
		set.add(SatellitesService.class);
		set.add(SettingsService.class);
		set.add(TranspondersService.class);
		set.add(UsersService.class);
		return set;
	}

	@Override
	public Set<Object> getSingletons() {
		MOXyJsonProvider moxyJsonProvider = new MOXyJsonProvider();
		moxyJsonProvider.setFormattedOutput(true);
		moxyJsonProvider.setMarshalEmptyCollections(true);
		moxyJsonProvider.setIncludeRoot(true);

		HashSet<Object> set = new HashSet<Object>(2);
		set.add(moxyJsonProvider);
		return set;
	}

	public MyApplicationResourceConfig() {

		// // Register resources and providers using package-scanning. Provider
		// is
		// // registered if it is in package.
		// packages("org.openbox.sf5.json");
		//
		// // register(new LoggingFilter(LOGGER, true));
		// register(MultiPartFeature.class);
		//
		// register(MoxyXmlFeature.class);
		// register(MOXyJsonContextResolver.class);
		//
		// // Enable Tracing support.
		// property(ServerProperties.TRACING, "ALL");
		//
		//
		// // it is active by default
		// property(ServerProperties.PROVIDER_SCANNING_RECURSIVE, true);
		//
		//
		// property(ServerProperties.APPLICATION_NAME, "Openbox SF5 JavaEE 7
		// rest app");
		//
		// property(ServerProperties.METAINF_SERVICES_LOOKUP_DISABLE, false);
		//
		// property(ServerProperties.MOXY_JSON_FEATURE_DISABLE, false);
		//

	}

}
