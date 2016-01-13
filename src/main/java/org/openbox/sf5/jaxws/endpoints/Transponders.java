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

import org.openbox.sf5.json.endpoints.TranspondersService;

@Named
@SessionScoped
@WebService(targetNamespace = "http://sf5.openbox.org/transpondersservice/1.0")
public class Transponders extends AbstractWSEndpoint implements Serializable {

	public Transponders() {

	}

	public List<org.openbox.sf5.model.Transponders> getTranspondersByArbitraryFilter(
			@WebParam(name = "type") String fieldName, @WebParam(name = "typeValue") String typeValue) {
		// return
		// transpondersService.getTranspondersByArbitraryFilter(fieldName,
		// typeValue);

		Response RSResponse = transpondersService.getTranspondersByArbitraryFilter(fieldName, typeValue);
		sendErrorByRSResponse(RSResponse);
		List<org.openbox.sf5.model.Transponders> transList = (List<org.openbox.sf5.model.Transponders>) RSResponse
				.getEntity();

		return transList;
	}

	@WebMethod
	public org.openbox.sf5.model.Transponders getTransponderById(@WebParam(name = "transponderId") long tpId) {
		Response RSResponse = transpondersService.getTransponderById(tpId);
		sendErrorByRSResponse(RSResponse);

		org.openbox.sf5.model.Transponders trans = (org.openbox.sf5.model.Transponders) RSResponse.getEntity();
		return trans;
	}

	@WebMethod
	public List<org.openbox.sf5.model.Transponders> getTranspondersBySatelliteId(@WebParam(name = "satId") long satId) {
		Response RSResponse = transpondersService.getTranspondersBySatelliteId(satId);
		sendErrorByRSResponse(RSResponse);

		List<org.openbox.sf5.model.Transponders> transList = (List<org.openbox.sf5.model.Transponders>) RSResponse
				.getEntity();

		return transList;

	}

	@WebMethod
	public List<org.openbox.sf5.model.Transponders> getTransponders() {
		Response RSResponse = transpondersService.getTransponders();
		sendErrorByRSResponse(RSResponse);

		List<org.openbox.sf5.model.Transponders> transList = (List<org.openbox.sf5.model.Transponders>) RSResponse
				.getEntity();

		return transList;
	}

	private static final long serialVersionUID = 4615677591584895454L;

	@Inject
	private TranspondersService transpondersService;
}
