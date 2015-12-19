package org.openbox.sf5.json;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;

@Provider
@Produces(MediaType.APPLICATION_JSON)
public class CustomJsonProvider extends JacksonJaxbJsonProvider {

	private static ObjectMapper mapper = new ObjectMapper();

	// http://stackoverflow.com/questions/18872931/custom-objectmapper-with-jersey-2-2-and-jackson-2-1/18917918#18917918

	static {
		JacksonObjectMapperConfiguration.configureMapper(mapper);
	}

	public CustomJsonProvider() {
		super();
		setMapper(mapper);
	}
}