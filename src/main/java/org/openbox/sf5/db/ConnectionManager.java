package org.openbox.sf5.db;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.openbox.sf5.model.AbstractDbEntity;
import org.reflections.Reflections;

@Named("ConnectionManager")
@ApplicationScoped
public class ConnectionManager implements Serializable {

	public void disableLogsWhenTesting() {
		java.util.logging.Logger.getLogger("org.hibernate").setLevel(java.util.logging.Level.OFF);

	}

	public SessionFactory getSessionFactroy() {
		// SessionFactory sessionFactoryVariable = null;
		//
		// Configuration configuration = new Configuration();
		// configuration.configure();
		//
		// String propertyName = "hibernate.connection.url";
		// String variableConnectionString = "${db.jdbcUrl}";
		// String devDBConnectionString =
		// "jdbc:h2:tcp://localhost/~/sf5jsftest;MVCC=true";
		//
		// // this code is left to Hibernate 4.3 compatibility.
		// if
		// (configuration.getProperty(propertyName).equals(variableConnectionString))
		// {
		// // manually override property with test server
		// configuration.setProperty(propertyName, devDBConnectionString);
		//
		// }
		//
		// // This also doesn't work in Hibernate 5.0.x !!!
		//
		// StandardServiceRegistry serviceRegistry = new
		// StandardServiceRegistryBuilder()
		// .applySettings(configuration.getProperties()).build();
		//
		// // builds a session factory from the service registry
		// sessionFactoryVariable =
		// configuration.buildSessionFactory(serviceRegistry);
		//
		// return sessionFactoryVariable;

		// Example of configuration from the official docs
		// http://docs.jboss.org/hibernate/orm/5.0/userGuide/en-US/html_single/#bootstrap-native-sessionfactory

		// StandardServiceRegistry standardRegistry = new
		// StandardServiceRegistryBuilder()
		// .configure( "org/hibernate/example/MyCfg.xml" )
		// .build();
		//
		// Metadata metadata = new MetadataSources( standardRegistry )
		// .addAnnotatedClass( MyEntity.class )
		// .addAnnotatedClassName( "org.hibernate.example.Customer" )
		// .addResource( "org/hibernate/example/Order.hbm.xml" )
		// .addResource( "org/hibernate/example/Product.orm.xml" )
		// .getMetadataBuilder()
		// .applyImplicitNamingStrategy(
		// ImplicitNamingStrategyJpaCompliantImpl.INSTANCE )
		// .build();
		//
		// SessionFactory sessionFactory = metadata.getSessionFactoryBuilder()
		// .applyBeanManager( getBeanManagerFromSomewhere() )
		// .build();
		//

		// Second attempt to use Hibernate 5. Begin.
		// SessionFactory sessionFactoryVariable = null;
		//
		// StandardServiceRegistryBuilder standardRegistryBuilder = new
		// StandardServiceRegistryBuilder();
		// Configuration configuration = new Configuration();
		// configuration.configure();
		//
		// String propertyName = "hibernate.connection.url";
		// String variableConnectionString = "${db.jdbcUrl}";
		// String devDBConnectionString =
		// "jdbc:h2:tcp://localhost/~/sf5jsftest;MVCC=true";
		//
		// if
		// (configuration.getProperty(propertyName).equals(variableConnectionString))
		// {
		// // manually override property with test server
		// configuration.setProperty(propertyName, devDBConnectionString);
		// }
		//
		// StandardServiceRegistry standardRegistry =
		// standardRegistryBuilder.applySettings(configuration.getProperties())
		// .build();
		//
		// MetadataSources metadataSources = new
		// MetadataSources(standardRegistry);
		//
		// // Getting annotated classes that are extending AbstractDbEntity
		// Set<Class<?>> annotatedSet =
		// getAllExtendedOrImplementedTypesRecursively(AbstractDbEntity.class);
		// // adding classes as annotated.
		// annotatedSet.stream().forEach(t ->
		// metadataSources.addAnnotatedClass(t));
		//
		// Metadata metadata = metadataSources.getMetadataBuilder().build();
		// sessionFactoryVariable = metadata.getSessionFactoryBuilder().build();
		//
		// return sessionFactoryVariable;
		// Second attempt to use Hibernate 5. End.

		SessionFactory sessionFactoryVariable = null;

		Configuration configuration = new Configuration();
		configuration.configure();

		String propertyName = "hibernate.connection.url";
		String variableConnectionString = "${db.jdbcUrl}";
		String devDBConnectionString = "jdbc:h2:tcp://localhost/~/sf5jsfdev;MVCC=true";

		// this code is left to Hibernate 4.3 compatibility.
		if (configuration.getProperty(propertyName).equals(variableConnectionString)) {
			// manually override property with test server
			configuration.setProperty(propertyName, devDBConnectionString);

		}

		// Getting annotated classes that are extending AbstractDbEntity
		// Set<Class<?>> annotatedSet =
		// getAllExtendedOrImplementedTypesRecursively(AbstractDbEntity.class);
		Set<Class<? extends AbstractDbEntity>> annotatedSet = getAllSubclassesAbstractDbEntity();

		// adding classes as annotated.
		annotatedSet.stream().forEach(t -> configuration.addAnnotatedClass(t));

		// This also doesn't work in Hibernate 5.0.x !!!

		StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();

		// builds a session factory from the service registry
		sessionFactoryVariable = configuration.buildSessionFactory(serviceRegistry);

		return sessionFactoryVariable;
	}

	public static Set<Class<? extends AbstractDbEntity>> getAllSubclassesAbstractDbEntity() {
		Reflections reflections = new Reflections("org.openbox.sf5");

		Set<Class<? extends AbstractDbEntity>> subTypes = reflections.getSubTypesOf(AbstractDbEntity.class);
		return subTypes;

	}

	// method to get all classes that extend the chosen class
	// http://stackoverflow.com/questions/22031207/find-all-classes-and-interfaces-a-class-extends-or-implements-recursively
	public static Set<Class<?>> getAllExtendedOrImplementedTypesRecursively(Class<?> clazz) {
		List<Class<?>> res = new ArrayList<>();

		do {
			res.add(clazz);

			// First, add all the interfaces implemented by this class
			Class<?>[] interfaces = clazz.getInterfaces();
			if (interfaces.length > 0) {
				res.addAll(Arrays.asList(interfaces));

				for (Class<?> interfaze : interfaces) {
					res.addAll(getAllExtendedOrImplementedTypesRecursively(interfaze));
				}
			}

			// Add the super class
			Class<?> superClass = clazz.getSuperclass();

			// Interfaces does not have java,lang.Object as superclass, they
			// have null, so break the cycle and return
			if (superClass == null) {
				break;
			}

			// Now inspect the superclass
			clazz = superClass;
		} while (!"java.lang.Object".equals(clazz.getCanonicalName()));

		return new HashSet<Class<?>>(res);
	}

	private static final long serialVersionUID = -393036417948357440L;

}
