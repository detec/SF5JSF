package org.openbox.sf5.jaxws.endpoints;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.ws.rs.core.Response;

import org.openbox.sf5.json.endpoints.UsersService;

// https://docs.oracle.com/javaee/6/tutorial/doc/bnayn.html
// Requirements of a JAX-WS Endpoint

// http://stackoverflow.com/questions/19297722/how-to-make-jax-ws-webservice-respond-with-specific-http-code

@Named
@SessionScoped
@WebService(targetNamespace = "http://sf5.openbox.org/usersservice/1.0")
public class Users implements Serializable {

	public Users() {

	}

	@WebMethod
	public Response createUser(org.openbox.sf5.model.Users user) {
		return usersService.createUser(user);
	}

	@WebMethod
	public Response ifSuchLoginExists(@WebParam(name = "login") String login) {
		return usersService.ifSuchLoginExists(login);
	}

	@WebMethod
	public Response getUserByLogin(@WebParam(name = "login") String login) {
		return usersService.getUserByLogin(login);
	}

	@Inject
	private UsersService usersService;

	private static final long serialVersionUID = 5489673952334788685L;

}
