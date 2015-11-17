package org.openbox.sf5.json.endpoints;

import java.io.Serializable;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;
import javax.json.JsonWriterFactory;
import javax.json.stream.JsonGenerator;
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
import org.openbox.sf5.service.CriterionService;
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
		Criterion userCriterion = criterionService.getUserCriterion(login, Settings.class);
		if (userCriterion == null) {
			return Response.status(404).build();
		}

		// building arbitrary criterion
		Criterion arbitraryCriterion = criterionService.getCriterionByClassFieldAndStringValue(Settings.class,
				fieldName, typeValue);

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

		Criterion userCriterion = criterionService.getUserCriterion(login, Settings.class);
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

			Map<String, Boolean> config = new HashMap<>();
			config.put(JsonGenerator.PRETTY_PRINTING, true);
			JsonWriterFactory factory = Json.createWriterFactory(config);

			// http://blog.eisele.net/2013/02/test-driving-java-api-for-processing.html
			StringWriter sw = new StringWriter();
			try (JsonWriter jw = factory.createWriter(sw)) {
				jw.writeObject(JObject);
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

	@Inject
	private CriterionService criterionService;

	public ObjectsListService getListService() {
		return listService;
	}

	public void setListService(ObjectsListService listService) {
		this.listService = listService;
	}

	public ObjectsController getContr() {
		return contr;
	}

	public void setContr(ObjectsController contr) {
		this.contr = contr;
	}

	public ConnectionManager getCm() {
		return cm;
	}

	public void setCm(ConnectionManager cm) {
		this.cm = cm;
	}

	public CriterionService getCriterionService() {
		return criterionService;
	}

	public void setCriterionService(CriterionService criterionService) {
		this.criterionService = criterionService;
	}

	private static final long serialVersionUID = 3347470276152176597L;

}
