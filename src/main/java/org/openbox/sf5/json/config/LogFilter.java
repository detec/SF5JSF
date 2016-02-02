package org.openbox.sf5.json.config;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.logging.Logger;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;

import org.apache.commons.io.IOUtils;

// http://stackoverflow.com/questions/24180221/logging-json-posts-with-resteasy

@Provider
public class LogFilter implements ContainerRequestFilter {

	private Logger LOGGER = Logger.getLogger(CustomMarshaller.class.getName());

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {

		if (!"POST".equals(requestContext.getMethod())
				|| !MediaType.APPLICATION_JSON_TYPE.equals(requestContext.getMediaType())
				|| requestContext.getEntityStream() == null) {
			return;
		}

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		IOUtils.copy(requestContext.getEntityStream(), baos);
		byte[] bytes = baos.toByteArray();
		LOGGER.info("Posted: " + new String(bytes, "UTF-8"));
		requestContext.setEntityStream(new ByteArrayInputStream(bytes));

	}

}
