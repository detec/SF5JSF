package org.openbox.sf5.json.converters;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Consumes;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;

@Provider
@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
public class LongMessageBodyReader implements MessageBodyReader<Long> {

	@Override
	public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return type == Long.class;
	}

	@Override
	public Long readFrom(Class<Long> myLong, Type genericType, Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, String> arg4, InputStream entityStream) throws IOException, WebApplicationException {
		InputStreamReader reader = new InputStreamReader(entityStream);
		BufferedReader br = new BufferedReader(reader);
		String readLine = br.readLine();
		// remove tags
		readLine = readLine.replace("<Long>", "");
		readLine = readLine.replace("</Long>", "");

		return Long.parseLong(readLine);
	}

}
