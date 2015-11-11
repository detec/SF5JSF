package org.openbox.sf5.json;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.openbox.sf5.common.JsonObjectFiller;
import org.openbox.sf5.db.Satellites;
import org.openbox.sf5.db.Transponders;
import org.openbox.sf5.service.ObjectsController;
import org.openbox.sf5.service.ObjectsListService;

@Named
@SessionScoped
@Path("transponders/")
public class TranspondersService implements Serializable {

	private static final long serialVersionUID = 330376972384785311L;

	// different types of params
	// https://www-01.ibm.com/support/knowledgecenter/SS7K4U_8.5.5/com.ibm.websphere.base.doc/ae/twbs_jaxrs_defresource_parmexchdata.html

	// http://localhost:8080/SF5JSF-test/json/transponders/filter/id/56
	@GET
	@Produces("application/json")
	@Path("filter/id/{transponderId}")
	public Response getTransponderById(@PathParam("transponderId") long tpId) {
		Transponders transponder = (Transponders) contr.select(Transponders.class, tpId);

		//JsonObjectBuilder transJOB = getJsonObjectBuilderFromTransponder(transponder);
		JsonObjectBuilder transJOB;
		String result = "";
		try {
			transJOB = JsonObjectFiller.getJsonObjectBuilderFromClassInstance(transponder);

			JsonObject JObject = transJOB.build();
			result = JObject.toString();
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return Response.status(200).entity(result).build();

	}

	// http://localhost:8080/SF5JSF-test/json/transponders/filter;satId=1
	@GET
	@Produces("application/json")
	@Path("filter/")
	public Response getTranspondersBySatelliteId(@MatrixParam("satId") long satId) {

		Satellites filterSatellite = (Satellites) contr.select(Satellites.class, satId);
		Criterion criterion = Restrictions.eq("Satellite", filterSatellite);

		List<Transponders> transList = (List<Transponders>) listService.ObjectsCriterionList(Transponders.class,
				criterion);

		String result = getJsonFromTranspondersList(transList);
		return Response.status(200).entity(result).build();
	}

	// http://localhost:8080/SF5JSF-test/json/transponders/all/
	@GET
	@Produces("application/json")
	@Path("all/")
	public Response getTransponders() {

		List<Transponders> transList = (List<Transponders>) listService.ObjectsList(Transponders.class);

		String result = getJsonFromTranspondersList(transList);
		return Response.status(200).entity(result).build();

	}

	private JsonObjectBuilder getJsonObjectBuilderFromTransponder(Transponders transponder) {
		Field fields[];
		fields = Transponders.class.getDeclaredFields();

		JsonObjectBuilder trans = Json.createObjectBuilder();
		// use reflection
		// arrayOfTransponders.add(arg0)
		for (int i = 0; i < fields.length; i++) {

			String fieldName = fields[i].getName();
			if (fieldName.equals("serialVersionUID")) {
				continue;
			}

			fields[i].setAccessible(true);
			String strValue;
			try {
				strValue = fields[i].get(transponder).toString();
				trans.add(fieldName, strValue);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} // end of loop

		return trans;
	}

	private String getJsonFromTranspondersList(List<Transponders> transList) {

		JsonObjectBuilder listObject = Json.createObjectBuilder();
		JsonArrayBuilder arrayOfTransponders = Json.createArrayBuilder();
		transList.stream().forEach(t -> {
			JsonObjectBuilder trans = getJsonObjectBuilderFromTransponder(t);
			arrayOfTransponders.add(trans);
		});

		listObject.add("transponders", arrayOfTransponders);

		JsonObject JObject = listObject.build();
		String result = JObject.toString();
		return result;
	}

	@Inject
	private ObjectsListService listService;

	@Inject
	private ObjectsController contr;

}
