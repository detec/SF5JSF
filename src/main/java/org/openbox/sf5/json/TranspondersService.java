package org.openbox.sf5.json;

import java.lang.reflect.Field;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.openbox.sf5.db.Transponders;
import org.openbox.sf5.service.ObjectsListService;

@Path("/transponders")
public class TranspondersService {

	@GET
	@Produces("application/json")
	public Response getTransponders() throws JSONException {
		List<Transponders> transList = (List<Transponders>) ObjectsListService.ObjectsList(Transponders.class);

		Field fields[];
		fields = Transponders.class.getDeclaredFields();

		JsonObjectBuilder listObject = Json.createObjectBuilder();
		JsonArrayBuilder arrayOfTransponders = Json.createArrayBuilder();
		transList.stream().forEach(t -> {
			 JsonObjectBuilder trans = Json.createObjectBuilder();
			 // use reflection
			//arrayOfTransponders.add(arg0)
			 for (int i = 0; i < fields.length; i++) {
				 String fieldName = fields[i].getName();
					if (fieldName.equals("serialVersionUID")) {
						continue;
					}
				 try {
					trans.add(fieldName, fields[i].get(t).toString());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 }
		});
		listObject.add("transponders", arrayOfTransponders);

		String result = listObject.toString();
		return Response.status(200).entity(result).build();


	}

}
