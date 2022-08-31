package com.example.newProject.util;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.example.newProject.model.Employee1;

public class HibernateUtil {

    //XML based configuration
    private static SessionFactory sessionFactory;

    //Annotation based configuration
    private static SessionFactory sessionAnnotationFactory;

    //Property based configuration
    private static SessionFactory sessionJavaConfigFactory;

//    public static SessionFactory buildNewSessionFactory() {
//
//    }

    private static SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            System.out.println("Hibernate Configuration loaded for sessionFactory");

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            System.out.println("Hibernate serviceRegistry created for sessionFactory");

            SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

            return sessionFactory;
        }
        catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed for sessionFactory." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    private static SessionFactory buildSessionAnnotationFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            Configuration configuration = new Configuration();
            configuration.configure("hibernate-annotation.cfg.xml");
            System.out.println("Hibernate Annotation Configuration loaded for sessionAnnotationFactory");

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            System.out.println("Hibernate Annotation serviceRegistry created for sessionAnnotationFactory");

            SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

            return sessionFactory;
        }
        catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed for sessionAnnotationFactory." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    private static SessionFactory buildSessionJavaConfigFactory() {
        try {
            Configuration configuration = new Configuration();

            //Create Properties, can be read from property files too
            Properties props = new Properties();
            props.put("hibernate.connection.driver_class", "com.microsoft.sqlserver.jdbc.SQLServerDriver");
            props.put("hibernate.connection.url", "jdbc:sqlserver://192.168.2.99:1433; databaseName=PRACTICE_DB");
            props.put("hibernate.connection.username", "sa");
            props.put("hibernate.connection.password", "Enosis123");
            props.put("hibernate.current_session_context_class", "thread");

            configuration.setProperties(props);

            //we can set mapping file or class with annotation
            //addClass(Employee1.class) will look for resource
            // com/journaldev/hibernate/model/Employee1.hbm.xml (not good)
            configuration.addAnnotatedClass(Employee1.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            System.out.println("Hibernate Java Config serviceRegistry created for sessionJavaConfigFactory");

            SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

            return sessionFactory;
        }
        catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed for sessionJavaConfigFactory." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        if(sessionFactory == null) sessionFactory = buildSessionFactory();
        return sessionFactory;
    }

    public static SessionFactory getSessionAnnotationFactory() {
        if(sessionAnnotationFactory == null) sessionAnnotationFactory = buildSessionAnnotationFactory();
        return sessionAnnotationFactory;
    }

    public static SessionFactory getSessionJavaConfigFactory() {
        if(sessionJavaConfigFactory == null) sessionJavaConfigFactory = buildSessionJavaConfigFactory();
        return sessionJavaConfigFactory;
    }

}