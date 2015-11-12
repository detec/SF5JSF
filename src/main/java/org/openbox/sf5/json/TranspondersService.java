package org.openbox.sf5.json;

import java.io.Serializable;
import java.util.HashMap;
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

	// Good combined params example
	// http://www.mkyong.com/webservices/jax-rs/jax-rs-matrixparam-example/

	// http://localhost:8080/SF5JSF-test/json/transponders/filter/Speed/27500
	@GET
	@Produces("application/json")
	// @Path("filter/any")
	@Path("filter/{type}/{typeValue}")
	public Response getTranspondersByArbitraryFilter(@PathParam("type") String fieldName,
			@PathParam("typeValue") String typeValue) {

		Response returnResponse = null;
		Criterion criterion = null;

		// We have the following situation
		// 1. Field name is of primitive type. Then we use simple Criterion.
		// 2. Field is enum. Then it should be String representation of an enum.
		// 3. Field is String.
		// 4. Filed is entity class, retrieved from database. Then we select
		// object by id, that came as typeValue.

		Class<?> fieldClazz = JsonObjectFiller.getFieldClass(Transponders.class, fieldName);
		// check if this field has some class, not null
		if (fieldClazz == null) {
			// Return not found error
			return Response.status(404).build();
		}

		else if (fieldClazz.isPrimitive()) {
			criterion = Restrictions.eq(fieldName, Long.parseLong(typeValue));
		}

		// check that it is an enum
		else if (Enum.class.isAssignableFrom(fieldClazz)) {
			// must select from HashMap where key is String representation of
			// enum

			// http://stackoverflow.com/questions/1626901/java-enums-list-enumerated-values-from-a-class-extends-enum
			List<?> enumList = JsonObjectFiller.enum2list((Class<? extends Enum>) fieldClazz);
			HashMap<String, Object> hm = new HashMap<>();
			enumList.stream().forEach(t -> hm.put(t.toString(), t));

			// now get enum value by string representation
			criterion = Restrictions.eq(fieldName, hm.get(typeValue));
		}
		// } else if (isPrimitive) {
		// criterion = Restrictions.eq(fieldName, Long.parseLong(typeValue));
		// }

		else if (fieldClazz == String.class) {
			// we build rather primitive criterion
			criterion = Restrictions.eq(fieldName, typeValue);
		}

		else {
			// it is a usual class
			Object filterObject = contr.select(fieldClazz, Long.parseLong(typeValue));
			criterion = Restrictions.eq(fieldName, filterObject);

		}
		// o.getClass().getField("fieldName").getType().isPrimitive(); for
		// primitives

		// fieldClazz filterSatellite = (fieldClazz)
		// contr.select(Satellites.class, typeValue);

		List<Transponders> transList = (List<Transponders>) listService.ObjectsCriterionList(Transponders.class,
				criterion);

		String result = getJsonFromTranspondersList(transList);
		returnResponse = Response.status(200).entity(result).build();

		return returnResponse;
	}

	// http://localhost:8080/SF5JSF-test/json/transponders/filter/id/56
	@GET
	@Produces("application/json")
	@Path("filter/id/{transponderId}")
	public Response getTransponderById(@PathParam("transponderId") long tpId) {
		Transponders transponder = (Transponders) contr.select(Transponders.class, tpId);

		// JsonObjectBuilder transJOB =
		// getJsonObjectBuilderFromTransponder(transponder);
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

	private String getJsonFromTranspondersList(List<Transponders> transList) {

		JsonObjectBuilder listObject = Json.createObjectBuilder();
		JsonArrayBuilder arrayOfTransponders = Json.createArrayBuilder();
		transList.stream().forEach(t -> {

			// JsonObjectBuilder trans = getJsonObjectBuilderFromTransponder(t);

			try {
				JsonObjectBuilder trans = JsonObjectFiller.getJsonObjectBuilderFromClassInstance(t);
				arrayOfTransponders.add(trans);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

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
