package org.openbox.sf5.json;

import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.openbox.sf5.json.endpoints.SatellitesService;
import org.openbox.sf5.json.endpoints.SettingsService;
import org.openbox.sf5.json.endpoints.TranspondersService;
import org.openbox.sf5.json.endpoints.UsersService;

@ApplicationPath("/json/")
public class ApplicationConfig extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> resources = new java.util.HashSet<>();
		resources.add(SatellitesService.class);
		resources.add(SettingsService.class);
		resources.add(TranspondersService.class);
		resources.add(UsersService.class);

		// http://stackoverflow.com/questions/18252990/uploading-file-using-jersey-over-restfull-service-and-the-resource-configuration
        // Add additional features such as support for Multipart.
        resources.add(MultiPartFeature.class);
		return resources;


	}

}
