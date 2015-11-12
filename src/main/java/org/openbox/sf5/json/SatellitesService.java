package org.openbox.sf5.json;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.hibernate.criterion.Criterion;
import org.openbox.sf5.common.JsonObjectFiller;
import org.openbox.sf5.db.Satellites;
import org.openbox.sf5.service.ObjectsController;
import org.openbox.sf5.service.ObjectsListService;

@Named
@SessionScoped
@Path("satellites/")
public class SatellitesService implements Serializable {

	@GET
	@Produces("application/json")
	@Path("filter/{type}/{typeValue}")
	public Response getSatellitesByArbitraryFilter(@PathParam("type") String fieldName,
			@PathParam("typeValue") String typeValue) {

		Response returnResponse = null;

		Criterion criterion = JsonObjectFiller.getCriterionByClassFieldAndStringValue(Satellites.class, fieldName,
				typeValue, contr);

		if (criterion == null) {
			return Response.status(404).build();
		}

		List<Satellites> satList = (List<Satellites>) listService.ObjectsCriterionList(Satellites.class, criterion);

		// String result = getJsonFromTranspondersList(transList);
		// String result = JsonObjectFiller.getJsonFromObjectsList(transList);
		String result = JsonObjectFiller.getJsonFromObjectsList(satList);

		returnResponse = Response.status(200).entity(result).build();

		return returnResponse;

	}

	private static final long serialVersionUID = 5840312078955871320L;

	@Inject
	private ObjectsListService listService;

	@Inject
	private ObjectsController contr;

}
