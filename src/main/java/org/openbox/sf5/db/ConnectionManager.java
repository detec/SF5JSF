package org.openbox.sf5.db;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateful;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.openbox.sf5.model.AbstractDbEntity;
import org.reflections.Reflections;

@Named("ConnectionManager")
@ApplicationScoped
@Stateful
public class ConnectionManager implements Serializable {

	// https://docs.jboss.org/author/display/WFLY9/JPA+Reference+Guide
	@PersistenceUnit(unitName = "primary")
	private SessionFactory sessionFactory;

	@PersistenceContext(unitName = "primary")
	private EntityManager em;

	public void disableLogsWhenTesting() {
		java.util.logging.Logger.getLogger("org.hibernate").setLevel(java.util.logging.Level.OFF);

	}

	// now we use persistence unit injection.
	// @PostConstruct
	public void initializeSessionFactory() {
		Configuration configuration = new Configuration();
		configuration.configure();

		// Eclipse should filter resources as hibernate.cfg.xml is in resources
		// folder now.
		// String propertyName = "hibernate.connection.url";
		// String variableConnectionString = "${db.jdbcUrl}";
		// // String devDBConnectionString =
		// // "jdbc:h2:tcp://localhost/~/sf5jsfdev;MVCC=true";
		// String devDBConnectionString = "jdbc:h2:tcp://localhost/~/sf5jsfdev";
		//
		// // this code is left to Hibernate 4.3 compatibility.
		// if
		// (configuration.getProperty(propertyName).equals(variableConnectionString))
		// {
		// // manually override property with test server
		// configuration.setProperty(propertyName, devDBConnectionString);
		//
		// }

		// Getting annotated classes that are extending AbstractDbEntity
		Set<Class<? extends AbstractDbEntity>> annotatedSet = getAllSubclassesAbstractDbEntity();

		// adding classes as annotated.
		annotatedSet.stream().forEach(t -> configuration.addAnnotatedClass(t));

		// 28.01.2015
		// resource not found
		// configuration.addResource("java:jboss/datasources/PostgreSQLDS");

		// https://docs.jboss.org/hibernate/orm/4.3/manual/en-US/html/ch03.html
		// how to configure datasource.

		// // This also doesn't work in Hibernate 5.0.x !!!

		StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();

		// builds a session factory from the service registry
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);

		// A SessionFactory is set up once for an application!
		// StandardServiceRegistryBuilder serviceRegistry = new
		// StandardServiceRegistryBuilder();

		/////////////////////////////////////////////////////////////////////////////////////////////////////////////

		// HIBERNATE 5.0 doesn't work.

		// final StandardServiceRegistry registry = new
		// StandardServiceRegistryBuilder().configure() // configures
		// // settings
		// // from
		// // hibernate.cfg.xml
		// .build();
		//
		// // Getting annotated classes that are extending AbstractDbEntity
		// Set<Class<? extends AbstractDbEntity>> annotatedSet =
		// getAllSubclassesAbstractDbEntity();
		//
		// try {
		//
		// MetadataSources ms = new MetadataSources(registry);
		// // adding classes as annotated.
		// annotatedSet.stream().forEach(t -> ms.addAnnotatedClass(t));
		//
		// // sessionFactory = new
		// // MetadataSources(registry).buildMetadata().buildSessionFactory();
		//
		// sessionFactory = ms.buildMetadata().buildSessionFactory();
		// } catch (Exception e) {
		//
		// e.printStackTrace();
		// // The registry would be destroyed by the SessionFactory, but we had
		// // trouble building the SessionFactory
		// // so destroy it manually.
		// StandardServiceRegistryBuilder.destroy(registry);
		// }

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

		// SessionFactory sessionFactoryVariable = null;
		//
		// return sessionFactoryVariable;

		return sessionFactory;
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
