package org.openbox.sf5.jaxws.endpoints;

import java.io.Serializable;

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
public class Transponders implements Serializable {

	public Transponders() {

	}

	public Response getTranspondersByArbitraryFilter(@WebParam(name = "type") String fieldName,
			@WebParam(name = "typeValue") String typeValue) {
		return transpondersService.getTranspondersByArbitraryFilter(fieldName, typeValue);
	}

	@WebMethod
	public Response getTransponderById(@WebParam(name = "transponderId") long tpId) {
		return transpondersService.getTransponderById(tpId);
	}

	@WebMethod
	public Response getTranspondersBySatelliteId(@WebParam(name = "satId") long satId) {
		return transpondersService.getTranspondersBySatelliteId(satId);
	}

	@WebMethod
	public Response getTransponders() {
		return transpondersService.getTransponders();
	}

	private static final long serialVersionUID = 4615677591584895454L;

	@Inject
	private TranspondersService transpondersService;
}
