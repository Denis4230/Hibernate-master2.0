package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.PersistenceException;
import java.sql.SQLException;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    Util hibernateConnect = new Util();
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try (Session session = hibernateConnect.getSessionFactory().getCurrentSession()){

            session.beginTransaction();
            session.createSQLQuery("CREATE TABLE User (id INT NOT NULL AUTO_INCREMENT, name VARCHAR(45) NULL, lastName VARCHAR(45) NULL, age INT NULL, PRIMARY KEY (id));")
                    .executeUpdate();
            session.getTransaction().commit();

        }catch (PersistenceException | IllegalArgumentException e){

        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = hibernateConnect.getSessionFactory().getCurrentSession()){

            session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS user").executeUpdate();
            session.getTransaction().commit();

        }catch (IllegalArgumentException | PersistenceException ignore){
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        try(Session session = hibernateConnect.getSessionFactory().openSession()) {

            session.beginTransaction();
            User user = new User(name, lastName, age);
            session.save(user);
            session.getTransaction().commit();
            System.out.println("User - " + name + " добавлен");

        }catch (IllegalArgumentException e){

        }

    }

    @Override
    public void removeUserById(long id) {

        try (Session session = hibernateConnect.getSessionFactory().openSession()){

            session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            session.getTransaction().commit();

        }catch (IllegalArgumentException e){}


    }

    @Override
    public List<User> getAllUsers() {

        List<User> list;
        Session session = hibernateConnect.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        list = session.createCriteria(User.class).list();
        transaction.commit();
        session.close();

        return list;
    }

    @Override
    public void cleanUsersTable() {

        try (Session session = hibernateConnect.getSessionFactory().getCurrentSession()){

            session.beginTransaction();
            session.createQuery("DELETE FROM User").executeUpdate();
            session.getTransaction().commit();

        }catch (IllegalArgumentException e){

        }

    }
}
