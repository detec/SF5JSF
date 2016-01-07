package org.openbox.sf5.json.config;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.hibernate.cfg.Settings;
import org.openbox.sf5.model.Satellites;
import org.openbox.sf5.model.SettingsConversion;
import org.openbox.sf5.model.Transponders;
import org.openbox.sf5.model.Users;

@Provider
public class CustomMarshaller implements ContextResolver<Marshaller> {

	// http://stackoverflow.com/questions/18436782/specifying-jaxb-2-context-in-jersey-1-17

	private JAXBContext context = null;

	private Marshaller marshaller = null;

	@Override
	public Marshaller getContext(Class<?> type) {

		if (marshaller == null) {

			if (context == null) {
				try {
					context = JAXBContext.newInstance(Transponders.class, Satellites.class, Users.class,
							Settings.class, SettingsConversion.class);
					marshaller = context.createMarshaller();
					marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
					marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");

				} catch (JAXBException e) {
					// log warning/error; null will be returned which indicates
					// that
					// this
					// provider won't/can't be used.
				}
			}
		}

		return marshaller;
	}

}
