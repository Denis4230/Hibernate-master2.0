package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.PersistenceException;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Session session = Util.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            session.createSQLQuery("CREATE TABLE User (id INT NOT NULL AUTO_INCREMENT, name VARCHAR(45) NULL, lastName VARCHAR(45) NULL, age INT NULL, PRIMARY KEY (id));")
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (PersistenceException | IllegalArgumentException ignore){
        } catch (Exception e) {
            e.getStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void dropUsersTable() {
        Session session = Util.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS user").executeUpdate();
            session.getTransaction().commit();

        } catch (IllegalArgumentException | PersistenceException ignore){
        } catch (Exception e) {
            e.getStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = Util.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            User user = new User(name, lastName, age);
            session.save(user);
            session.getTransaction().commit();
            System.out.println("User - " + name + " добавлен");

        } catch (IllegalArgumentException ignore){
        } catch (Exception e) {
            e.getStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }

    }

    @Override
    public void removeUserById(long id) {
        Session session = Util.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            session.getTransaction().commit();

        } catch (IllegalArgumentException ignore){
        } catch (Exception e) {
            e.getStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }


    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = null;
        Session session = Util.getSessionFactory().openSession();
        try {
            Transaction transaction = session.beginTransaction();
            list = session.createCriteria(User.class).list();
            transaction.commit();
        } catch (Exception e) {
            e.getStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return list;
    }

    @Override
    public void cleanUsersTable() {
        Session session = Util.getSessionFactory().getCurrentSession();
        try {

            session.beginTransaction();
            session.createQuery("DELETE FROM User").executeUpdate();
            session.getTransaction().commit();

        } catch (IllegalArgumentException ignore){
        } catch (Exception e) {
            e.getStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }

    }
}
