package org.openbox.sf5.json.endpoints;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.openbox.sf5.db.Satellites;
import org.openbox.sf5.json.service.CommonJsonizer;
import org.openbox.sf5.json.service.SatellitesJsonizer;

@Named
@SessionScoped
@Path("satellites/")
public class SatellitesService implements Serializable {

	// http://localhost:8080/SF5JSF-test/json/satellites/all/
	@GET
	@Produces("application/json")
	@Path("all/")
	public Response getAllSatellites() {
		Response returnResponse = null;

		String result = jsonizer.getSatellitesList();
		if (result.isEmpty()) {
			return Response.status(404).build();
		} else {
			returnResponse = Response.status(200).entity(result).build();
		}
		return returnResponse;
	}

	@GET
	@Produces("application/json")
	@Path("filter/{type}/{typeValue}")
	public Response getSatellitesByArbitraryFilter(@PathParam("type") String fieldName,
			@PathParam("typeValue") String typeValue) {

		Response returnResponse = null;

		String result = jsonizer.getSatellitesByArbitraryFilter(fieldName, typeValue);
		if (result.isEmpty()) {
			return Response.status(404).build();
		} else {
			returnResponse = Response.status(200).entity(result).build();
		}
		return returnResponse;

	}

	@GET
	@Produces("application/json")
	@Path("filter/id/{satelliteId}")
	public Response getSatelliteById(@PathParam("satelliteId") long satId) {
		// return JsonObjectFiller.buildResponseByTypeAndId(contr, satId,
		// Satellites.class);
		Response returnResponse = null;
		String result = commonJsonizer.buildJsonStringByTypeAndId(satId, Satellites.class);
		if (result.isEmpty()) {
			return Response.status(404).build();
		} else {
			returnResponse = Response.status(200).entity(result).build();
		}
		return returnResponse;

	}

	private static final long serialVersionUID = 5840312078955871320L;

	@Inject
	private CommonJsonizer commonJsonizer;

	public CommonJsonizer getCommonJsonizer() {
		return commonJsonizer;
	}

	public void setCommonJsonizer(CommonJsonizer commonJsonizer) {
		this.commonJsonizer = commonJsonizer;
	}

	@Inject
	private SatellitesJsonizer jsonizer;

	public SatellitesJsonizer getJsonizer() {
		return jsonizer;
	}

	public void setJsonizer(SatellitesJsonizer jsonizer) {
		this.jsonizer = jsonizer;
	}

}
