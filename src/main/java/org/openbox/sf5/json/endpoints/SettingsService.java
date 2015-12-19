package org.openbox.sf5.json.endpoints;

import java.io.Serializable;
import java.net.URI;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.openbox.sf5.json.service.SettingsJsonizer;
import org.openbox.sf5.json.service.UsersJsonizer;
import org.openbox.sf5.model.Settings;

// http://localhost:8080/SF5JSF-test/json/usersettings/filter/login/admin

@Named
@SessionScoped
@Path("usersettings/")
public class SettingsService implements Serializable {

	@Context
	UriInfo uriInfo;

	// @POST
	// @Path("testcreate")
	// @Consumes({ MediaType.APPLICATION_JSON })
	// @Produces(MediaType.APPLICATION_JSON)
	// public Response createSetting(SettingsSimple setting,
	// @MatrixParam("login") String login) {
	// Response returnResponse = Response.status(200).build();
	//
	// return returnResponse;
	//
	// }

	@POST
	@Path("create")
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response createSetting(Settings setting, @MatrixParam("login") String login

	) {

		// String login = "ITUser";
		// Users currentUser = securityContext.getCurrentlyAuthenticatedUser();
		//
		// if (currentUser == null) {
		// return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
		// }
		//
		// if (!currentUser.equals(setting.getUser())) {
		// // authenticated user and setting user do not coincide.
		// return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
		// }
		// HttpStatus statusResult = settingsJsonizer.saveNewSetting(setting);
		// if (statusResult.equals(HttpStatus.CONFLICT)) {
		// return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		// }
		//
		// HttpHeaders headers = new HttpHeaders();
		// headers.add("SettingId", Long.toString(setting.getId()));
		// headers.setLocation(
		// ucBuilder.path("/json/usersettings/filter/id/{id}").buildAndExpand(setting.getId()).toUri());
		// return new ResponseEntity<Void>(headers, HttpStatus.CREATED);

		Response returnResponse = null;
		long result = usersJsonizer.checkIfUsernameExists(login);

		if (result == 0) {
			// UNAUTHORIZED
			returnResponse = Response.status(401).entity("Login " + login + " does not exist!").build();


		}
		else if (setting == null) {
			returnResponse = Response.status(401).entity("Setting deserialized is null!").build();
		}
		else {
			// if user and login do not coincide
			if (!setting.getUser().getLogin().equals(login)) {
				returnResponse = Response.status(406)
						// NOT ACCEPTABLE
						.entity("Login provided and login in user setting do not coincide!").build();
			}

			else {
				// for unsaved references parent_id is null
				setting.getConversion().forEach(t -> t.setparent_id(setting));
				setting.getSatellites().forEach(t -> t.setparent_id(setting));

				int statusResult = settingsJsonizer.saveNewSetting(setting);
				if (statusResult == 409) {
					// return new ResponseEntity<Void>(HttpStatus.CONFLICT);
					returnResponse = Response.status(409).entity("Error saving setting to database!").build();
				} else {

					// setting successfully saved, should add headers.
					UriBuilder ub = uriInfo.getAbsolutePathBuilder();
					URI settingUri = ub

							.path("filter").path("id")

							.path(Long.toString(setting.getId())).matrixParam("login", login).build();

					returnResponse = Response.status(201).header("SettingId", Long.toString(setting.getId()))

							.location(settingUri)

							.build();
				}
			}
		}

		return returnResponse;
	}

	@GET
	@Produces("application/json")
	@Path("filter/login/{typeValue}")
	public Response getSettingsByUserLogin(@PathParam("typeValue") String typeValue) {

		Response returnResponse = null;
		String result = settingsJsonizer.getSettingsByUserLogin(typeValue);

		if (result.isEmpty()) {
			return Response.status(404).build();
		} else {
			returnResponse = Response.status(200).entity(result).build();
		}

		return returnResponse;
	}

	// http://localhost:8080/SF5JSF-test/json/usersettings/filter/Name/First;login=admin
	@GET
	@Produces("application/json")
	@Path("filter/{type}/{typeValue}")
	public Response getSettingsByArbitraryFilter(@PathParam("type") String fieldName,
			@PathParam("typeValue") String typeValue, @MatrixParam("login") String login) {

		Response returnResponse = null;
		String result = settingsJsonizer.getSettingsByArbitraryFilter(fieldName, typeValue, login);
		if (result.isEmpty()) {
			return Response.status(404).build();
		} else {
			returnResponse = Response.status(200).entity(result).build();
		}

		return returnResponse;

	}

	@GET
	@Produces("application/json")
	@Path("filter/id/{settingId}")
	public Response getSettingById(@PathParam("settingId") long settingId, @MatrixParam("login") String login) {

		Response returnResponse = null;
		String result = settingsJsonizer.getSettingById(settingId, login);
		if (result.isEmpty()) {
			return Response.status(404).build();
		} else {
			returnResponse = Response.status(200).entity(result).build();
		}

		return returnResponse;
	}

	@Inject
	private SettingsJsonizer settingsJsonizer;

	public SettingsJsonizer getSettingsJsonizer() {
		return settingsJsonizer;
	}

	public void setSettingsJsonizer(SettingsJsonizer settingsJsonizer) {
		this.settingsJsonizer = settingsJsonizer;
	}

	private static final long serialVersionUID = 3347470276152176597L;

	@Inject
	private UsersJsonizer usersJsonizer;

	public UsersJsonizer getUsersJsonizer() {
		return usersJsonizer;
	}

	public void setUsersJsonizer(UsersJsonizer usersJsonizer) {
		this.usersJsonizer = usersJsonizer;
	}

}
