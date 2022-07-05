package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;

public class Util {
    private static Connection connection;
    private static final SessionFactory sessionFactory;

    static  {
        sessionFactory = new Configuration().addAnnotatedClass(User.class).buildSessionFactory();
    }

    public static Connection getConnection() {
        return connection;
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void closeSessionFacto() {
        sessionFactory.close();
    }
}
