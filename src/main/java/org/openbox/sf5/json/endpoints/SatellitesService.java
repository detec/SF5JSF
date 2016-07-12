package org.openbox.sf5.json.endpoints;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hibernate.criterion.Criterion;
import org.openbox.sf5.json.config.Pretty;
import org.openbox.sf5.model.Satellites;
import org.openbox.sf5.service.CriterionService;
import org.openbox.sf5.service.ObjectsController;
import org.openbox.sf5.service.ObjectsListService;

@Named
@SessionScoped
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Path("satellites/")
public class SatellitesService implements Serializable {

	// http://localhost:8080/SF5JSF-test/json/satellites/all/
	@GET
	// @Path("all/")
	@Pretty
	public Response getAllSatellites() {
		Response returnResponse = null;

		List<Satellites> satList = listService.ObjectsList(Satellites.class);
		if (satList.isEmpty()) {
			returnResponse = Response.status(204).build();
		}

		else {

			// returnResponse = Response.status(200).entity(satList).build();

			// http://www.adam-bien.com/roller/abien/entry/jax_rs_returning_a_list
			GenericEntity<List<Satellites>> gSatList = new GenericEntity<List<Satellites>>(satList) {
			};

			returnResponse = Response.status(200).entity(gSatList).build();

		}

		return returnResponse;
	}

	@GET
	@Path("filter/{type}/{typeValue}")
	@Pretty
	public Response getSatellitesByArbitraryFilter(@PathParam("type") String fieldName,
			@PathParam("typeValue") String typeValue) {

		Response returnResponse = null;
		Criterion criterion = criterionService.getCriterionByClassFieldAndStringValue(Satellites.class, fieldName,
				typeValue);
		List<Satellites> satList = listService.ObjectsCriterionList(Satellites.class, criterion);

		if (criterion == null) {
			returnResponse = Response.status(204).build();
		} else {
			GenericEntity<List<Satellites>> gSatList = new GenericEntity<List<Satellites>>(satList) {
			};

			returnResponse = Response.status(200).entity(gSatList).build();
		}
		return returnResponse;

	}

	@GET
	@Path("{satelliteId}")
	@Pretty
	public Response getSatelliteById(@PathParam("satelliteId") long satId) {

		Response returnResponse = null;

		Satellites sat = objectsController.select(Satellites.class, satId);
		if (sat == null) {
			returnResponse = Response.status(204).build();
		} else {
			returnResponse = Response.status(200).entity(sat).build();
		}
		return returnResponse;

	}

	private static final long serialVersionUID = 5840312078955871320L;

	@Inject
	private ObjectsListService listService;

	@Inject
	private CriterionService criterionService;

	@Inject
	private ObjectsController objectsController;

}
