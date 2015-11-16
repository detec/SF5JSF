package org.openbox.sf5.json;

import java.io.Serializable;
import java.io.StringWriter;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;
import javax.ws.rs.GET;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.openbox.sf5.common.JsonObjectFiller;
import org.openbox.sf5.db.ConnectionManager;
import org.openbox.sf5.db.Settings;
import org.openbox.sf5.service.ObjectsController;
import org.openbox.sf5.service.ObjectsListService;

// http://localhost:8080/SF5JSF-test/json/usersettings/filter/id/1;login=admin

@Named
@SessionScoped
@Path("usersettings/")
public class SettingsService implements Serializable {

	// http://localhost:8080/SF5JSF-test/json/usersettings/filter/Name/First;login=admin
	@GET
	@Produces("application/json")
	@Path("filter/{type}/{typeValue}")
	public Response getSettingsByArbitraryFilter(@PathParam("type") String fieldName,
			@PathParam("typeValue") String typeValue, @MatrixParam("login") String login) {

		// building user criterion
		Criterion userCriterion = JsonObjectFiller.getUserCriterion(login, listService, contr, Settings.class);
		if (userCriterion == null) {
			return Response.status(404).build();
		}

		// building arbitrary criterion
		Criterion arbitraryCriterion = JsonObjectFiller.getCriterionByClassFieldAndStringValue(Settings.class,
				fieldName, typeValue, contr);

		if (arbitraryCriterion == null) {
			return Response.status(404).build();
		}

		Session session = cm.getSessionFactroy().openSession();
		Criteria criteria = session.createCriteria(Settings.class).add(userCriterion).add(arbitraryCriterion);
		criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);

		List<Settings> records = criteria.list();

		String result = JsonObjectFiller.getJsonFromObjectsList(records);
		Response returnResponse = Response.status(200).entity(result).build();

		return returnResponse;

	}

	@GET
	@Produces("application/json")
	@Path("filter/id/{settingId}")
	public Response getSettingById(@PathParam("settingId") long settingId, @MatrixParam("login") String login) {

		Criterion userCriterion = JsonObjectFiller.getUserCriterion(login, listService, contr, Settings.class);
		if (userCriterion == null) {
			return Response.status(404).build();
		}

		Criterion settingIdCriterion = Restrictions.eq("id", settingId);

		Session session = cm.getSessionFactroy().openSession();
		List<Settings> records = session.createCriteria(Settings.class).add(userCriterion).add(settingIdCriterion)
				.list();

		Settings settingsObject = null;

		if (records.size() == 0) {
			// There is no such setting with username
			return Response.status(404).build();
		} else {
			settingsObject = records.get(0);
		}

		// returning setting as JSON Object.
		JsonObjectBuilder transJOB;
		String result = "";

		try {
			transJOB = JsonObjectFiller.getJsonObjectBuilderFromClassInstance(settingsObject);

			JsonObject JObject = transJOB.build();

			StringWriter sw = new StringWriter();
			try (JsonWriter writer = Json.createWriter(sw)) {
				writer.writeObject(JObject);
			}
			result = sw.toString();

		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Response returnResponse = Response.status(200).entity(result).build();

		return returnResponse;
	}

	@Inject
	private ObjectsListService listService;

	@Inject
	private ObjectsController contr;

	@Inject
	private ConnectionManager cm;

	private static final long serialVersionUID = 3347470276152176597L;

}
