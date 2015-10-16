package org.openbox.sf5.db;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		// try {

		// Configuration configuration = new Configuration();
		// configuration.configure();
		//
		// if
		// (configuration.getProperty("hibernate.connection.url").equals("${db.jdbcUrl}"))
		// {
		// // manually override property with test server
		// configuration.setProperty("hibernate.connection.url",
		// "jdbc:h2:tcp://localhost/~/sf5jsftest;MVCC=true");
		// }
		//
		// // This code is obsolete for Hibernate 5
		// StandardServiceRegistryBuilder ssrb = new
		// StandardServiceRegistryBuilder()
		// .applySettings(configuration.getProperties());
		// SessionFactory sessionFactory =
		// configuration.buildSessionFactory(ssrb.build());
		//
		// sessionFactory.openSession();
		// return sessionFactory;
		// // logger.info("Test connection with the database created
		// // successfully.");
		// } catch (Throwable ex) {
		// // Make sure you log the exception, as it might be swallowed
		// System.err.println("Initial SessionFactory creation failed." + ex);
		//
		// throw new ExceptionInInitializerError(ex);
		// }

		// http://docs.jboss.org/hibernate/orm/5.0/quickstart/html/#hibernate-gsg-tutorial-basic-config
		// A SessionFactory is set up once for an application!

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

		// StandardServiceRegistryBuilder ssrb = new
		// StandardServiceRegistryBuilder();
		// ssrb.configure(); // read configuration fron XML file
		//
		// if
		// (configuration.getProperty(propertyName).equals(variableConnectionString))
		// {
		// ssrb.applySetting(propertyName, devDBConnectionString);
		// }

		// This is purely Hibernate 5 approach
		// StandardServiceRegistry registry = ssrb.build();
		//
		// // try {
		// sessionFactoryVariable = new
		// MetadataSources(registry).buildMetadata().buildSessionFactory();
		// } catch (Exception e) {
		// The registry would be destroyed by the SessionFactory, but we had
		// trouble building the SessionFactory
		// so destroy it manually.
		// StandardServiceRegistryBuilder.destroy(registry);
		// // }
		//
		// return sessionFactoryVariable;

		// ===========================================================================================================
		// Here we see that it doesn't load mapped classes.
		// StandardServiceRegistry registry = new
		// StandardServiceRegistryBuilder().configure() // configures
		// // settings
		// // from
		// // hibernate.cfg.xml
		// .build();
		//
		// MetadataSources metadata = new MetadataSources(registry);
		//
		// Collection<Class<?>> clazzlist = metadata.getAnnotatedClasses();
		// // sessionFactoryVariable = new
		// // MetadataSources(registry).buildMetadata().buildSessionFactory();
		//
		// return sessionFactoryVariable;

		// ===========================================================================================================

		// ============================================================================================================
		// This also doesn't work in Hibernate 5.0.x !!!

		StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();

		// builds a session factory from the service registry
		sessionFactoryVariable = configuration.buildSessionFactory(serviceRegistry);

		return sessionFactoryVariable;

		// ============================================================================================================

		// =============================================================================================================
		// http://www.codejava.net/frameworks/hibernate/building-hibernate-sessionfactory-from-service-registry
		// for year 2014
		// Doesn't work!!!!!!!!!! ServiceRegistry is deprecated.

		// ServiceRegistry serviceRegistry = new
		// StandardServiceRegistryBuilder()
		// .applySettings(configuration.getProperties()).build();
		//
		// // builds a session factory from the service registry
		// sessionFactoryVariable =
		// configuration.buildSessionFactory(serviceRegistry);
		//
		// return sessionFactoryVariable;

		// =============================================================================================================
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static Session openSession() {
		return sessionFactory.openSession();
	}

}
