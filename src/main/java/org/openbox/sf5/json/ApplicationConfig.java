package org.openbox.sf5.json;

import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/json/")
public class ApplicationConfig extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> resources = new java.util.HashSet<>();
		resources.add(SatellitesService.class);
		resources.add(SettingsService.class);
		resources.add(TranspondersService.class);
		return resources;
	}

}
