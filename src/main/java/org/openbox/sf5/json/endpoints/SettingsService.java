package org.openbox.sf5.json.endpoints;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.openbox.sf5.json.service.SettingsJsonizer;
import org.openbox.sf5.service.CriterionService;
import org.openbox.sf5.service.ObjectsListService;

// http://localhost:8080/SF5JSF-test/json/usersettings/filter/login/admin

@Named
@SessionScoped
@Path("usersettings/")
public class SettingsService implements Serializable {

	@GET
	@Produces("application/json")
	@Path("filter/login/{typeValue}")
	public Response getSettingsByUserLogin(@PathParam("typeValue") String typeValue) {

		/*
		 * List<Book> books = bookRepository.getAll(); GenericEntity<List<Book>>
		 * bookWrapper = new GenericEntity<List<Book>>( books) { }; return
		 * Response.ok(bookWrapper).build();
		 */

		Response returnResponse = null;
		String result = settingsJsonizer.getSettingsByUserLogin(typeValue);

		if (result.isEmpty()) {
			return Response.status(404).build();
		} else {
			returnResponse = Response.status(200).entity(result).build();
		}

		// Criterion userCriterion =
		// criterionService.getUserCriterion(typeValue, Settings.class);
		// if (userCriterion == null) {
		// return Response.status(404).build();
		// }
		//
		// List<Settings> Settings =
		// listService.ObjectsCriterionList(Settings.class, userCriterion);
		// returnResponse = Response.ok(new
		// GenericEntity<List<Settings>>(Settings) {
		// }).build();
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
	private ObjectsListService listService;

	@Inject
	private CriterionService criterionService;

	@Inject
	private SettingsJsonizer settingsJsonizer;

	public SettingsJsonizer getSettingsJsonizer() {
		return settingsJsonizer;
	}

	public void setSettingsJsonizer(SettingsJsonizer settingsJsonizer) {
		this.settingsJsonizer = settingsJsonizer;
	}

	private static final long serialVersionUID = 3347470276152176597L;

}
