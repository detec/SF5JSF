package org.openbox.sf5.json.endpoints;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.openbox.sf5.json.service.UsersJsonizer;
import org.openbox.sf5.model.Users;

@Named
@SessionScoped
@Path("users/")
public class UsersService implements Serializable {

	@POST
	@Path("create")
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response createUser(Users user) {
		Response returnResponse = null;

		long result = usersJsonizer.checkIfUsernameExists(user.getLogin());

		if (result != 0) {
			returnResponse = Response.status(202).entity(new Long(result).toString()).build();
			return returnResponse;
		}

		int statusResult = usersJsonizer.saveNewUser(user);
		if (statusResult == 409) {
			returnResponse = Response.status(409).entity(new Boolean(false).toString()).build();
			return returnResponse;
		}

		// HttpHeaders headers = new HttpHeaders();
		// headers.add("UserId", Long.toString(user.getId()));
		// return new ResponseEntity<Void>(headers, HttpStatus.CREATED);

		// https://jersey.java.net/documentation/latest/user-guide.html#d0e6615
		// Building responses.

		// returnResponse = Response.created(user.getId()).build();
		// returning id as result
		returnResponse = Response.status(201).entity(new Long(user.getId()).toString()).build();
		return returnResponse;
	}

	@GET
	@Produces("application/json")
	@Path("exists/login/{login}")
	public Response ifSuchLoginExists(@PathParam("login") String login) {
		Response returnResponse = null;
		long result = usersJsonizer.checkIfUsernameExists(login);
		if (result == 0) {

			returnResponse = Response.status(204).entity(new Long(result).toString()).build();
		} else {
			returnResponse = Response.status(202).entity(new Long(result).toString()).build();
		}
		return returnResponse;
	}

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

	public UsersJsonizer getUsersJsonizer() {
		return usersJsonizer;
	}

	public void setUsersJsonizer(UsersJsonizer usersJsonizer) {
		this.usersJsonizer = usersJsonizer;
	}

	private static final long serialVersionUID = 1933163250712959368L;

}
