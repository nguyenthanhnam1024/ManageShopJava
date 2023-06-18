package com.example.manage_shops.repository;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            // Tạo đối tượng Configuration từ file cấu hình
            Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
            // Tạo ServiceRegistry từ Configuration
            StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties());
            // Xây dựng SessionFactory từ ServiceRegistry
            return configuration.buildSessionFactory(registryBuilder.build());
        } catch (Throwable ex) {
            System.err.println("Failed to initialize Hibernate SessionFactory: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }
}
