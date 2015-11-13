package org.openbox.sf5.json;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
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
	@Path("filter/{type}/{typeValue}")
	public Response getTranspondersByArbitraryFilter(@PathParam("type") String fieldName,
			@PathParam("typeValue") String typeValue) {

		Response returnResponse = null;

		Criterion criterion = JsonObjectFiller.getCriterionByClassFieldAndStringValue(Transponders.class, fieldName,
				typeValue, contr);

		if (criterion == null) {
			return Response.status(404).build();
		}

		List<Transponders> transList = (List<Transponders>) listService.ObjectsCriterionList(Transponders.class,
				criterion);

		String result = JsonObjectFiller.getJsonFromObjectsList(transList);

		returnResponse = Response.status(200).entity(result).build();

		return returnResponse;
	}

	// http://localhost:8080/SF5JSF-test/json/transponders/filter/id/56
	@GET
	@Produces("application/json")
	@Path("filter/id/{transponderId}")
	public Response getTransponderById(@PathParam("transponderId") long tpId) {
		return JsonObjectFiller.buildResponseByTypeAndId(contr, tpId, Transponders.class);
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

		// String result = getJsonFromTranspondersList(transList);

		String result = JsonObjectFiller.getJsonFromObjectsList(transList);
		return Response.status(200).entity(result).build();
	}

	// http://localhost:8080/SF5JSF-test/json/transponders/all/
	@GET
	@Produces("application/json")
	@Path("all/")
	public Response getTransponders() {

		List<Transponders> transList = (List<Transponders>) listService.ObjectsList(Transponders.class);

		// String result = getJsonFromTranspondersList(transList);

		String result = JsonObjectFiller.getJsonFromObjectsList(transList);
		return Response.status(200).entity(result).build();

	}

	@Inject
	private ObjectsListService listService;

	@Inject
	private ObjectsController contr;

}
