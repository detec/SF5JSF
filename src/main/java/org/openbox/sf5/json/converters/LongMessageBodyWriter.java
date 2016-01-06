package org.openbox.sf5.json.converters;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

@Provider
@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
public class LongMessageBodyWriter implements MessageBodyWriter<Long> {

	@Override
	public boolean isWriteable(Class type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return type == Long.class;
	}

	@Override
	public void writeTo(Long myLong, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, Object> arg5, OutputStream entityStream)
					throws IOException, WebApplicationException {
		StringBuilder sb = new StringBuilder();
		if (mediaType.equals(MediaType.APPLICATION_XML)) {
			sb.append("<Long>");
		}
		sb.append(myLong.toString());

		if (mediaType.equals(MediaType.APPLICATION_XML)) {
			sb.append("</Long>");
		}
		DataOutputStream dos = new DataOutputStream(entityStream);
		dos.writeUTF(sb.toString());
	}

	@Override
	public long getSize(Long arg0, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		// deprecated by JAX-RS 2.0 and ignored by Jersey runtime
		return 0;
	}

}
