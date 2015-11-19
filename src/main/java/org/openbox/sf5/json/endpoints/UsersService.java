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

import org.openbox.sf5.json.service.UsersJsonizer;

@Named
@SessionScoped
@Path("users/")
public class UsersService implements Serializable {

	@GET
	@Produces("application/json")
	@Path("filter/login/{login}")
	public Response getUserByLogin(@PathParam("login") String login) {

		Response returnResponse = null;
		String result = usersJsonizer.getUserByLogin(login);
		if (result.isEmpty()) {
			return Response.status(404).build();
		} else {
			returnResponse = Response.status(200).entity(result).build();
		}

		return returnResponse;

	}

	@Inject
	private UsersJsonizer usersJsonizer;

	private static final long serialVersionUID = 1933163250712959368L;

}
