package org.openbox.sf5.json.endpoints;

import java.io.Serializable;
import java.net.URI;
import java.util.List;

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
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.jboss.resteasy.annotations.providers.jaxb.XmlHeader;
import org.openbox.sf5.common.XMLExporter;
import org.openbox.sf5.json.config.Pretty;
import org.openbox.sf5.json.service.SettingsJsonizer;
import org.openbox.sf5.json.service.UsersJsonizer;
import org.openbox.sf5.model.Sat;
import org.openbox.sf5.model.Settings;
import org.openbox.sf5.model.SettingsConversion;
import org.openbox.sf5.service.CriterionService;
import org.openbox.sf5.service.ObjectsListService;

// http://localhost:8080/SF5JSF-test/json/usersettings/filter/login/admin

@Named
@SessionScoped
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Path("usersettings/")
public class SettingsService implements Serializable {

	@Context
	private UriInfo uriInfo;

	@POST
	// @Path("create")
	public Response createSetting(Settings setting, @MatrixParam("login") String login) {

		// http://stackoverflow.com/questions/20119108/why-is-my-jersey-jax-rs-server-throwing-a-illegalstateexception-about-not-being
		Response returnResponse = null;
		long result = usersJsonizer.checkIfUsernameExists(login);

		if (result == 0) {
			// UNAUTHORIZED
			returnResponse = Response.status(401).entity("Login " + login + " does not exist!").build();

		} else if (setting == null) {
			returnResponse = Response.status(401).entity("Setting deserialized is null!").build();
		} else {
			// if user and login do not coincide
			if (!setting.getUser().getLogin().equals(login)) {
				returnResponse = Response.status(406)
						// NOT ACCEPTABLE
						.entity("Login provided and login in user setting do not coincide!").build();
			}

			else {
				// for unsaved references parent_id is null
				if (setting.getConversion() != null) {
					setting.getConversion().forEach(t -> t.setparent_id(setting));
				}

				if (setting.getSatellites() != null) {
					// There is problem with MOXy XML that it converts empty
					// collection to null.
					setting.getSatellites().forEach(t -> t.setparent_id(setting));
				}

				int statusResult = settingsJsonizer.saveNewSetting(setting);
				if (statusResult == 409) {
					// return new ResponseEntity<Void>(HttpStatus.CONFLICT);
					returnResponse = Response.status(409).entity("Error saving setting to database!").build();
				} else {

					// setting successfully saved, should add headers.

					URI settingUri = null;

					try {
						// we also call JAX-WS and id doesn't make response
						UriBuilder ub = uriInfo.getAbsolutePathBuilder();
						settingUri = ub

								// .path("filter").path("id")

								.path(Long.toString(setting.getId())).matrixParam("login", login).build();
					} catch (Exception e) {

					}

					returnResponse = Response.status(201).header("SettingId", Long.toString(setting.getId()))
							.entity(Long.toString(setting.getId()))

							.location(settingUri)

							.build();
				}
			}
		}

		return returnResponse;
	}

	@GET
	@Path("filter/login/{typeValue}")
	@Pretty
	public Response getSettingsByUserLogin(@PathParam("typeValue") String typeValue) {

		Response returnResponse = null;

		Criterion userCriterion = criterionService.getUserCriterion(typeValue, Settings.class);
		if (userCriterion == null) {
			returnResponse = Response.status(204).entity("User criterion not returned by login " + typeValue).build();
		} else {
			List<Settings> settList = listService.ObjectsCriterionList(Settings.class, userCriterion);

			if (settList.isEmpty()) {
				returnResponse = Response.status(204).entity("No settings returned for user with login " + typeValue)
						.build();

			} else {
				GenericEntity<List<Settings>> gsettList = new GenericEntity<List<Settings>>(settList) {
				};
				returnResponse = Response.status(200).entity(gsettList).build();
			}

		}

		return returnResponse;
	}

	// http://localhost:8080/SF5JSF-test/json/usersettings/filter/Name/First;login=admin
	@GET
	@Path("filter/{type}/{typeValue}")
	@Pretty
	public Response getSettingsByArbitraryFilter(@PathParam("type") String fieldName,
			@PathParam("typeValue") String typeValue, @MatrixParam("login") String login) {

		Response returnResponse = null;

		Criterion userCriterion = criterionService.getUserCriterion(login, Settings.class);
		if (userCriterion == null) {
			returnResponse = Response.status(204).entity("User criterion not returned by login " + typeValue).build();
		} else {
			// building arbitrary criterion
			Criterion arbitraryCriterion = criterionService.getCriterionByClassFieldAndStringValue(Settings.class,
					fieldName, typeValue);
			if (arbitraryCriterion == null) {
				returnResponse = Response.status(204)
						.entity("Setting criterion not returned for field " + fieldName + " and value " + typeValue)
						.build();
			} else {
				List<Settings> settList = settingsJsonizer.getListOfSettingsByUserAndArbitraryCriterion(userCriterion,
						arbitraryCriterion);
				if (settList.isEmpty()) {
					returnResponse = Response.status(204)
							.entity("No settings returned for request parameters specified").build();
				} else {
					GenericEntity<List<Settings>> gsettList = new GenericEntity<List<Settings>>(settList) {
					};
					returnResponse = Response.status(200).entity(gsettList).build();
				}
			}
		}

		return returnResponse;

	}

	@GET
	@Path("{settingId}")
	@Pretty
	public Response getSettingById(@PathParam("settingId") long settingId, @MatrixParam("login") String login) {

		Response returnResponse = null;
		Criterion userCriterion = criterionService.getUserCriterion(login, Settings.class);
		if (userCriterion == null) {
			returnResponse = Response.status(204).entity("User criterion not returned by login " + login).build();
		}

		else {

			Criterion settingIdCriterion = Restrictions.eq("id", settingId);

			List<Settings> settList = settingsJsonizer.getListOfSettingsByUserAndArbitraryCriterion(userCriterion,
					settingIdCriterion);
			if (settList.isEmpty()) {
				returnResponse = Response.status(204).entity("No settings returned for request parameters specified")
						.build();
			} else {
				Settings settingsObject = settList.get(0);
				// let's marshall manually, because we are receiving 500 error
				// without log. Even after manual marshalling with @XmlTransient
				// This causes successful unmarshalling of JSON.

				// List<MediaType> mediaTypes =
				// headers.getAcceptableMediaTypes();
				// // for JSON we use this
				// if (mediaTypes.contains(new MediaType("application",
				// "json"))) {
				//
				// returnResponse =
				// Response.status(200).entity(settingsObject).build();
				// }
				//
				// else if (mediaTypes.contains(new MediaType("application",
				// "xml"))) {
				//
				// // No, somehow it changes JAXB context and gives 2 errors,
				// including 400 Bad request.
				// StringWriter outputBuffer = new StringWriter();
				// JAXB.marshal(settingsObject, outputBuffer);
				// String str = outputBuffer.toString();
				// returnResponse = Response.status(200).entity(str).build();
				//
				//
				// // returnResponse =
				// Response.status(200).entity(settingsObject).build();
				// }

				returnResponse = Response.status(200).entity(settingsObject).build();

			}
		}

		return returnResponse;
	}

	@GET
	@Path("{settingId}/sf5")
	@Produces(MediaType.APPLICATION_XML)
	@Pretty
	@XmlHeader("")
	public Response getSettingByIdSF5(@PathParam("settingId") long settingId, @MatrixParam("login") String login) {
		Response returnResponse = null;
		Criterion userCriterion = criterionService.getUserCriterion(login, Settings.class);
		if (userCriterion == null) {
			returnResponse = Response.status(204).entity("User criterion not returned by login " + login).build();
		}

		else {

			Criterion settingIdCriterion = Restrictions.eq("id", settingId);

			List<Settings> settList = settingsJsonizer.getListOfSettingsByUserAndArbitraryCriterion(userCriterion,
					settingIdCriterion);
			if (settList.isEmpty()) {
				returnResponse = Response.status(204).entity("No settings returned for request parameters specified")
						.build();
			} else {
				Settings setting = settList.get(0);
				List<SettingsConversion> conversionLines = setting.getConversion();
				Sat sat = XMLExporter.exportSettingsConversionPresentationToSF5Format(conversionLines);

				returnResponse = Response.status(200).entity(sat).build();

			}
		}

		return returnResponse;
	}

	@Inject
	private SettingsJsonizer settingsJsonizer;

	@Inject
	private ObjectsListService listService;

	private static final long serialVersionUID = 3347470276152176597L;

	@Inject
	private UsersJsonizer usersJsonizer;

	@Inject
	private CriterionService criterionService;

}
