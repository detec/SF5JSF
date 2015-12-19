package org.openbox.sf5.json.config;

import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;

import com.fasterxml.jackson.databind.ObjectMapper;

public class MarshallingFeature implements Feature {

	// @Override
	// public boolean configure(FeatureContext context) {
	// context.register(CustomJsonProvider.class, MessageBodyReader.class,
	// MessageBodyWriter.class);
	// return true;
	// }

	// https://metabroadcast.com/blog/using-jackson-2-with-jersey

	private static final ObjectMapper mapper = new ObjectMapper() {

		private static final long serialVersionUID = 1560545338671814607L;

		{

			// We want ISO dates, not Unix timestamps!:

			// configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

		}
	};

//	private static final JacksonJaxbJsonProvider provider = new JacksonJaxbJsonProvider() {
//		{
//
//			JacksonObjectMapperConfiguration.configureMapper(mapper);
//			setMapper(mapper);
//		}
//	};

	/**
	 * This method is what actually gets called, when your ResourceConfig
	 * registers a Feature.
	 */
	@Override
	public boolean configure(FeatureContext context) {
		//context.register(provider);
		return true;
	}

}