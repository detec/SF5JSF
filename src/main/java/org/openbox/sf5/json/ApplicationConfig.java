package org.openbox.sf5.json;

import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

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
		return resources;
	}

}
