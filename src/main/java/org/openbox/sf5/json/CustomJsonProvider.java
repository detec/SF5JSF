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

	static {
		// mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
		// false);
		//
		// mapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
		// mapper.setVisibility(PropertyAccessor.ALL,
		// JsonAutoDetect.Visibility.ANY);
		// mapper.enable(SerializationFeature.INDENT_OUTPUT);
		// mapper.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING,
		// true);
		// mapper.configure(DeserializationFeature.READ_ENUMS_USING_TO_STRING,
		// true);
		// mapper.configure(DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS,
		// true);
		// mapper.configure(ServerProperties.MOXY_JSON_FEATURE_DISABLE, true);

		JacksonObjectMapperConfiguration.configureMapper(mapper);

	}

	public CustomJsonProvider() {
		super();
		setMapper(mapper);
	}
}