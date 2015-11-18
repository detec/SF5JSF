package org.openbox.sf5.db;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

@Named("ConnectionManager")
@ApplicationScoped
public class ConnectionManager implements Serializable {

	public void disableLogsWhenTesting() {
		java.util.logging.Logger.getLogger("org.hibernate").setLevel(java.util.logging.Level.OFF);

	}

	public SessionFactory getSessionFactroy() {
		SessionFactory sessionFactoryVariable = null;

		Configuration configuration = new Configuration();
		configuration.configure();

		String propertyName = "hibernate.connection.url";
		String variableConnectionString = "${db.jdbcUrl}";
		String devDBConnectionString = "jdbc:h2:tcp://localhost/~/sf5jsftest;MVCC=true";

		// this code is left to Hibernate 4.3 compatibility.
		if (configuration.getProperty(propertyName).equals(variableConnectionString)) {
			// manually override property with test server
			configuration.setProperty(propertyName, devDBConnectionString);

		}

		// This also doesn't work in Hibernate 5.0.x !!!

		StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();

		// builds a session factory from the service registry
		sessionFactoryVariable = configuration.buildSessionFactory(serviceRegistry);

		return sessionFactoryVariable;
	}

	private static final long serialVersionUID = -393036417948357440L;

}
