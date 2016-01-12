package org.openbox.sf5.jaxws.endpoints;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.ws.rs.core.Response;

import org.openbox.sf5.json.endpoints.SettingsService;

// https://docs.oracle.com/javaee/6/tutorial/doc/bnazc.html
// Types Supported by JAX-WS

@Named
@SessionScoped
@WebService(serviceName = "SettingsService", targetNamespace = "http://sf5.openbox.org/settingsservice/1.0")
public class Settings implements Serializable {

	public Settings() {

	}

	@WebMethod
	public Response createSetting(org.openbox.sf5.model.Settings setting, @WebParam(name = "login") String login) {
		return settingsService.createSetting(setting, login);
	}

	@WebMethod
	public Response getSettingsByUserLogin(@WebParam(name = "login") String login) {
		return settingsService.getSettingsByUserLogin(login);
	}

	@WebMethod
	public Response getSettingsByArbitraryFilter(@WebParam(name = "type") String fieldName,
			@WebParam(name = "typeValue") String typeValue, @WebParam(name = "login") String login) {
		return settingsService.getSettingsByArbitraryFilter(fieldName, typeValue, login);
	}

	@WebMethod
	public Response getSettingById(@WebParam(name = "settingId") long settingId,
			@WebParam(name = "login") String login) {

		return settingsService.getSettingById(settingId, login);
	}

	@Inject
	private SettingsService settingsService;

	private static final long serialVersionUID = -7159247061772260449L;

}
