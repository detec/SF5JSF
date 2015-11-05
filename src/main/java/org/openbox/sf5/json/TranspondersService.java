package org.openbox.sf5.json;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.openbox.sf5.db.Transponders;
import org.openbox.sf5.service.ObjectsListService;

@Named
@SessionScoped
@Path("transponders")
public class TranspondersService implements Serializable {

	private static final long serialVersionUID = 330376972384785311L;

	@GET
	@Produces("application/json")
	public Response getTransponders() {

		List<Transponders> transList = (List<Transponders>) listService.ObjectsList(Transponders.class);

		Field fields[];
		fields = Transponders.class.getDeclaredFields();

		JsonObjectBuilder listObject = Json.createObjectBuilder();
		JsonArrayBuilder arrayOfTransponders = Json.createArrayBuilder();
		transList.stream().forEach(t -> {
			JsonObjectBuilder trans = Json.createObjectBuilder();
			// use reflection
			// arrayOfTransponders.add(arg0)
			for (int i = 0; i < fields.length; i++) {


				String fieldName = fields[i].getName();
				if (fieldName.equals("serialVersionUID")) {
					continue;
				}
				try {
					fields[i].setAccessible(true);
					String strValue = fields[i].get(t).toString();
					trans.add(fieldName, strValue);
					//System.out.println(strValue);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				arrayOfTransponders.add(trans);
			}
		});
		listObject.add("transponders", arrayOfTransponders);

		// String result = listObject.;
		// JsonObject JObject = listObject.build();
		// String result = JObject.toString();

		JsonArray JObject = arrayOfTransponders.build();
		String result = JObject.toString();
		return Response.status(200).entity(result).build();

	}

	@Inject
	private ObjectsListService listService;

}
