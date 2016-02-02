package org.openbox.sf5.json.config;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.Produces;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

@Provider
@Produces("application/xml")
public class CustomMarshaller implements ContextResolver<Marshaller>
// implements ContextResolver<JAXBContext>

{

	private Logger LOGGER = Logger.getLogger(CustomMarshaller.class.getName());
	// http://stackoverflow.com/questions/18436782/specifying-jaxb-2-context-in-jersey-1-17

	private JAXBContext context = null;

	private Marshaller marshaller = null;

	// @Override
	// public JAXBContext getContext(Class<?> arg0) {
	// try {
	// context = JAXBContext.newInstance(AppPathReader.JAXB_PACKAGE_NAME);
	// } catch (JAXBException e) {
	// LOGGER.log(Level.SEVERE, "Custom JAXBContext - " + e.getMessage(), e);
	// }
	// return context;
	// }

	@Override
	public Marshaller getContext(Class<?> type) {

		if (marshaller == null) {

			if (context == null) {
				try {
					context = JAXBContext.newInstance(AppPathReader.JAXB_PACKAGE_NAME);
					marshaller = context.createMarshaller();
					marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
					marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
					marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE); // no
																					// <[?xml
																					// version="1.0"
																					// encoding="UTF-8"?>

				} catch (JAXBException e) {
					// log warning/error; null will be returned which indicates
					// that
					// this
					// provider won't/can't be used.
					LOGGER.log(Level.SEVERE, "CustomMarshaller - " + e.getMessage());
				}
			}
		}

		return marshaller;
	}

}
