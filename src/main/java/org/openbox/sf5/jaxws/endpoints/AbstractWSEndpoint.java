package org.openbox.sf5.jaxws.endpoints;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import org.openbox.sf5.model.listwrappers.ChangeAnnotation;
import org.openbox.sf5.model.listwrappers.GenericXMLListWrapper;

// http://stackoverflow.com/questions/19297722/how-to-make-jax-ws-webservice-respond-with-specific-http-code

public abstract class AbstractWSEndpoint {

	private static final Logger LOG = Logger.getLogger(AbstractWSEndpoint.class.getName());

	@Resource
	private WebServiceContext context;

	public HttpServletResponse getResponse() {
		MessageContext ctx = context.getMessageContext();
		HttpServletResponse response = (HttpServletResponse) ctx.get(MessageContext.SERVLET_RESPONSE);

		return response;
	}

	// find if there is an error code and throw error
	public void sendErrorByRSResponse(Response response) {
		// checking status of response
		int statusCode = response.getStatus();
		if (statusCode != 200) {
			HttpServletResponse servletResponse = getResponse();
			try {
				servletResponse.sendError(statusCode, response.readEntity(String.class));
			}

			catch (IOException e) {
				LOG.severe("Never happens, or yes?");
			}

		}
	}

	public <T> GenericXMLListWrapper<T> getWrappedList(List<T> listToWrap, Class<T> type) {
		GenericXMLListWrapper<T> wrapper = new GenericXMLListWrapper<T>();
		wrapper.setWrappedList(listToWrap);

		// We should replace stub for satellites in root element
		final XmlRootElement classAnnotation = wrapper.getClass().getAnnotation(XmlRootElement.class);
		ChangeAnnotation.changeAnnotationValue(classAnnotation, "name",
				type.getSimpleName().toLowerCase());
				// this seems to work

		// we should also change annotation of @XmlSeeAlso
		final XmlSeeAlso classSeeAlsoAnnotation = wrapper.getClass().getAnnotation(XmlSeeAlso.class);
		Class[] clazzArray = new Class[1];
		// clazzArray[0] = wrapper.getEntityBeanType();
		clazzArray[0] = type;
		ChangeAnnotation.changeAnnotationValue(classSeeAlsoAnnotation, "value", clazzArray);

		return wrapper;
	}

}
