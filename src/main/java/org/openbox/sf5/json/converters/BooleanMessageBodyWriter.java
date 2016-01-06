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
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
public class BooleanMessageBodyWriter implements MessageBodyWriter<Boolean> {

    @Override
    public boolean isWriteable(Class type, Type genericType, Annotation[] annotations, MediaType mediaType) {
       // System.out.println("isWriteable called...");
        return type == Boolean.class;
    }

    @Override
    public long getSize(Boolean myBool, Class type, Type genericType,
                        Annotation[] annotations, MediaType mediaType) {
        // deprecated by JAX-RS 2.0 and ignored by Jersey runtime
        return 0;
    }

//    @Override
//    public void writeTo(Boolean myBool,
//                        Class type,
//                        Type genericType,
//                        Annotation[] annotations,
//                        MediaType mediaType,
//                        MultivaluedMap<string object=""> httpHeaders,
//                        OutputStream entityStream)
//                        throws IOException, WebApplicationException {
//
//        StringBuilder sb = new StringBuilder();
//        sb.append("<boolean><boolean>").append(myBool.toString()).append("</boolean></boolean>");
//        DataOutputStream dos = new DataOutputStream(entityStream);
//        dos.writeUTF(sb.toString());
//    }


	@Override
	public void writeTo(Boolean myBool, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, Object> arg5, OutputStream entityStream) throws IOException, WebApplicationException {
        StringBuilder sb = new StringBuilder();
        if (mediaType.equals(MediaType.APPLICATION_XML)) {
        	sb.append("<Boolean>");
        }
        sb.append(myBool.toString());

        if (mediaType.equals(MediaType.APPLICATION_XML)) {
        	sb.append("</Boolean>");
        }
        DataOutputStream dos = new DataOutputStream(entityStream);
        dos.writeUTF(sb.toString());

	}}
