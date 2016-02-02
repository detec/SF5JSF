package org.openbox.sf5.json.config;

import java.lang.annotation.Annotation;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.core.MediaType;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;

import org.jboss.resteasy.annotations.DecorateTypes;
import org.jboss.resteasy.spi.interception.DecoratorProcessor;

@DecorateTypes({ "text/*+xml", "application/*+xml" })
public class PrettyProcessor implements DecoratorProcessor<Marshaller, Pretty> {

	private Logger LOGGER = Logger.getLogger(PrettyProcessor.class.getName());

	@Override
	public Marshaller decorate(Marshaller target, Pretty annotation, Class type, Annotation[] annotations,
			MediaType mediaType) {
		try {
			target.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			target.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			target.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
		} catch (PropertyException e) {
			LOGGER.log(Level.SEVERE, "Custom JAXBContext - " + e.getMessage(), e);
		}
		return target;
	}

}

// https://docs.jboss.org/resteasy/docs/3.0.9.Final/userguide/html_single/#decorators