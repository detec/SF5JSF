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

import org.openbox.sf5.json.endpoints.SettingsService;

// https://docs.oracle.com/javaee/6/tutorial/doc/bnazc.html
// Types Supported by JAX-WS

@Named
@SessionScoped
@WebService(serviceName = "SettingsService", targetNamespace = "http://sf5.openbox.org/settingsservice/1.0")
public class Settings extends AbstractWSEndpoint implements Serializable {

	public Settings() {

	}

	@WebMethod
	public long createSetting(org.openbox.sf5.model.Settings setting, @WebParam(name = "login") String login) {
		Response RSResponse = settingsService.createSetting(setting, login);
		sendErrorByRSResponse(RSResponse);

		String newIdString = (String) RSResponse.getEntity();

		return Long.parseLong(newIdString);
	}

	@WebMethod
	public List<org.openbox.sf5.model.Settings> getSettingsByUserLogin(@WebParam(name = "login") String login) {
		Response RSResponse = settingsService.getSettingsByUserLogin(login);
		sendErrorByRSResponse(RSResponse);

		List<org.openbox.sf5.model.Settings> settList = (List<org.openbox.sf5.model.Settings>) RSResponse.getEntity();

		return settList;
	}

	@WebMethod
	public List<org.openbox.sf5.model.Settings> getSettingsByArbitraryFilter(@WebParam(name = "type") String fieldName,
			@WebParam(name = "typeValue") String typeValue, @WebParam(name = "login") String login) {

		Response RSResponse = settingsService.getSettingsByArbitraryFilter(fieldName, typeValue, login);
		sendErrorByRSResponse(RSResponse);

		List<org.openbox.sf5.model.Settings> settList = (List<org.openbox.sf5.model.Settings>) RSResponse.getEntity();

		return settList;
	}

	@WebMethod
	public org.openbox.sf5.model.Settings getSettingById(@WebParam(name = "settingId") long settingId,
			@WebParam(name = "login") String login) {

		Response RSResponse = settingsService.getSettingById(settingId, login);
		sendErrorByRSResponse(RSResponse);

		org.openbox.sf5.model.Settings setting = (org.openbox.sf5.model.Settings) RSResponse.getEntity();

		return setting;
	}

	@Inject
	private SettingsService settingsService;

	private static final long serialVersionUID = -7159247061772260449L;

}
