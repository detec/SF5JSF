package org.openbox.sf5.db;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
//import org.hibernate.annotations.common.util.impl.Log_.logger;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
     //       logger.info("Trying to create a test connection with the database.");
            Configuration configuration = new Configuration();
            configuration.configure();
            
            if (configuration.getProperty("hibernate.connection.url").equals("${db.jdbcUrl}")) {
            	// manually override property with test server
            	configuration.setProperty("hibernate.connection.url"
            			, "jdbc:h2:tcp://localhost/~/test;MVCC=true");
            }
            StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
            SessionFactory sessionFactory = configuration.buildSessionFactory(ssrb.build());
            sessionFactory.openSession();
            return sessionFactory;
       //     logger.info("Test connection with the database created successfuly.");
        }
        catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
    public static Session openSession() {
        return sessionFactory.openSession();
   }


}
