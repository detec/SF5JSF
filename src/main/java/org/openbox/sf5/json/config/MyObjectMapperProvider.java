package org.openbox.sf5.json.config;

import javax.ws.rs.ext.ContextResolver;

import com.fasterxml.jackson.databind.ObjectMapper;


// Taken from official
// https://jersey.java.net/documentation/latest/media.html#json
public class MyObjectMapperProvider implements ContextResolver<ObjectMapper> {
	final ObjectMapper defaultObjectMapper;

    public MyObjectMapperProvider() {
        defaultObjectMapper = createDefaultMapper();
    }

    @Override
    public ObjectMapper getContext(Class<?> type) {
            return defaultObjectMapper;
        }


    private static ObjectMapper createDefaultMapper() {
        final ObjectMapper result = new ObjectMapper();

        JacksonObjectMapperConfiguration.configureMapper(result);

        return result;
    }

}
