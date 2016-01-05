package org.openbox.sf5.json.endpoints;

import java.io.Serializable;
import java.util.List;

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

import org.hibernate.criterion.Criterion;
import org.openbox.sf5.json.service.UsersJsonizer;
import org.openbox.sf5.model.Users;
import org.openbox.sf5.service.CriterionService;
import org.openbox.sf5.service.ObjectsListService;

@Named
@SessionScoped
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Path("users/")
public class UsersService implements Serializable {

	@POST
	@Path("create")
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

		// returning id as result
		returnResponse = Response.status(201).entity(new Long(user.getId()).toString()).build();
		return returnResponse;
	}

	@GET
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

	// @GET
	// @Path("filter/login/{login}")
	// public Response getUserByLogin(@PathParam("login") String login) {
	//
	// Response returnResponse = null;
	// String result = usersJsonizer.getUserByLogin(login);
	// if (result.isEmpty()) {
	// return Response.status(404).build();
	// } else {
	// returnResponse = Response.status(200).entity(result).build();
	// }
	//
	// return returnResponse;
	//
	// }

	@GET
	@Path("filter/login/{login}")
	public Response getUserByLogin(@PathParam("login") String login) {

		Response returnResponse = null;
		Criterion criterion = criterionService.getCriterionByClassFieldAndStringValue(Users.class, "Login", login);

		if (criterion == null) {
			returnResponse = Response.status(204).entity("Could not construct valid criterion for login " + login)
					.build();
		}

		else {
			List<Users> userList = listService.ObjectsCriterionList(Users.class, criterion);
			if (userList.size() == 0) {
				returnResponse = Response.status(204).entity("User not found with login " + login).build();
			} else {
				Users returnUser = userList.get(0);
				returnResponse = Response.status(200).entity(returnUser).build();
			}
		}

		return returnResponse;

	}

	@Inject
	private UsersJsonizer usersJsonizer;

	@Inject
	private ObjectsListService listService;

	@Inject
	private CriterionService criterionService;

	public UsersJsonizer getUsersJsonizer() {
		return usersJsonizer;
	}

	public void setUsersJsonizer(UsersJsonizer usersJsonizer) {
		this.usersJsonizer = usersJsonizer;
	}

	private static final long serialVersionUID = 1933163250712959368L;

}
