/**
 *
 */
package org.openbox.sf5.json.config;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotAcceptableException;
import javax.ws.rs.NotAllowedException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.NotSupportedException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Handling exceptions from Jersey
 *
 * @author Andrii Duplyk
 *
 */
@Provider
public class UncaughtExceptionHandler implements ExceptionMapper<Throwable> {

	private static final Map<Class<? extends Throwable>, Response.Status> exceptionStatusMap = initializeExceptions();

	private static Map<Class<? extends Throwable>, Response.Status> initializeExceptions() {

		Map<Class<? extends Throwable>, Response.Status> returnMap = new HashMap<>();
		returnMap.put(IllegalArgumentException.class, Response.Status.BAD_REQUEST);
		// we should not lose standard exceptions.
		returnMap.put(NotFoundException.class, Response.Status.METHOD_NOT_ALLOWED);
		returnMap.put(BadRequestException.class, Response.Status.BAD_REQUEST);
		returnMap.put(NotAcceptableException.class, Response.Status.NOT_ACCEPTABLE);
		returnMap.put(NotAllowedException.class, Response.Status.METHOD_NOT_ALLOWED);
		returnMap.put(NotSupportedException.class, Response.Status.UNSUPPORTED_MEDIA_TYPE);

		return returnMap;
	}

	@Override
	public Response toResponse(Throwable exception) {
		ExceptionJSONInfo response = new ExceptionJSONInfo("", (Exception) exception);

		Response.Status responseStatus = exceptionStatusMap.getOrDefault(exception.getClass(),
				Response.Status.INTERNAL_SERVER_ERROR);

		return Response.status(responseStatus).entity(response).type(MediaType.APPLICATION_JSON).build();

	}

}
