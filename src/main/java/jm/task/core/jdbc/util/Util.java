package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;

public class Util {
    private Connection connection;
    private SessionFactory sessionFactory;

    public Util() {
        sessionFactory = new Configuration().addAnnotatedClass(User.class).buildSessionFactory();
    }

    public Connection getConnection() {
        return connection;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
