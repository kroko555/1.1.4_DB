package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getSessionFactory;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        String sql = "CREATE TABLE Users (id INT PRIMARY KEY auto_increment, " +
                "name VARCHAR(50) NOT NULL, " +
                "lastName VARCHAR(50) NOT NULL , " +
                "age TINYINT NOT NULL)";
        Transaction tx = null;
        try (Session session = getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.createSQLQuery(sql).executeUpdate();
            tx.commit();
            session.close();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        }
    }

    @Override
    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS USERS";
        Transaction tx = null;
        try (Session session = getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.createSQLQuery(sql).executeUpdate();
            tx.commit();
            session.close();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction tx = null;
        try (Session session = getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            User user = new User(name, lastName, age);
            session.persist(user);
            tx.commit();
            session.close();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction tx = null;
        try (Session session = getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            tx.commit();
            session.close();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        Transaction tx = null;
        List<User> users = new ArrayList<>();
        String sql = "FROM User";
        try (Session session = getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            users = session.createQuery(sql, User.class).getResultList();
            tx.commit();
            session.close();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Transaction tx = null;
        try (Session session = getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.createQuery("delete User").executeUpdate();
            tx.commit();
            session.close();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        }
    }
}
