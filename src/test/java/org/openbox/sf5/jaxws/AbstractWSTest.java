package org.openbox.sf5.jaxws;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AbstractWSTest {

	public static final String appLocation = "http://localhost:8080/";

	public Logger LOGGER = Logger.getLogger(getClass().getName());

	public Properties property = new Properties();

	public void loadProperties() {

		// using try with resources
		try (InputStream in = getClass().getResourceAsStream("/application.properties")) {
			property.load(in);
		} catch (IOException e) {
			e.printStackTrace();

			// put exception into log.
			LOGGER.log(Level.SEVERE, e.getMessage(), e);

		}

	}

}
