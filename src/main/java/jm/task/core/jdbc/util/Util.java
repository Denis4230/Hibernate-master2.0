package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Util {
    private static SessionFactory sessionFactory = null;

    public static SessionFactory getSessionFactory() {
        return sessionFactory = new Configuration().addAnnotatedClass(User.class).buildSessionFactory();
    }

    public static void closeSessionFacto() {
        if (sessionFactory != null)
        sessionFactory.close();
    }
}
