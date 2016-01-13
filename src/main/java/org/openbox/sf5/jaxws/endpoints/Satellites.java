package org.openbox.sf5.jaxws.endpoints;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.ws.rs.core.Response;

import org.openbox.sf5.json.endpoints.SatellitesService;

@Named
@SessionScoped
@WebService(targetNamespace = "http://sf5.openbox.org/satelliteservice/1.0")
public class Satellites extends AbstractWSEndpoint implements Serializable {

	private static final long serialVersionUID = 6621262760055117578L;

	public Satellites() {

	}

	@WebMethod
	public List<org.openbox.sf5.model.Satellites> getAllSatellites() {
		Response RSResponse = satellitesService.getAllSatellites();
		sendErrorByRSResponse(RSResponse);

		List<org.openbox.sf5.model.Satellites> satList = (List<org.openbox.sf5.model.Satellites>) RSResponse
				.getEntity();
		// return getWrappedList(satList, Satellites.class);

		return satList;

	}

	@WebMethod
	public List<org.openbox.sf5.model.Satellites> getSatellitesByArbitraryFilter(
			@WebParam(name = "type") String fieldName, @WebParam(name = "typeValue") String typeValue) {
		Response RSResponse = satellitesService.getSatellitesByArbitraryFilter(fieldName, typeValue);
		sendErrorByRSResponse(RSResponse);

		List<org.openbox.sf5.model.Satellites> satList = (List<org.openbox.sf5.model.Satellites>) RSResponse
				.getEntity();

		return satList;
	}

	@WebMethod
	public org.openbox.sf5.model.Satellites getSatelliteById(@WebParam(name = "satelliteId") long satId) {
		Response RSResponse = satellitesService.getSatelliteById(satId);
		sendErrorByRSResponse(RSResponse);
		org.openbox.sf5.model.Satellites satellite = (org.openbox.sf5.model.Satellites) RSResponse.getEntity();

		return satellite;
	}

	@Inject
	private SatellitesService satellitesService;

}
