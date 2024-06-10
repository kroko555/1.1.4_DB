package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;


public class UserDaoJDBCImpl extends Util implements UserDao {
    public UserDaoJDBCImpl() {

    }

    Connection con = getConnection();

    public void createUsersTable() throws SQLException {
        Statement statement = null;
        String sql = "CREATE TABLE IF NOT EXISTS USERS(id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255) NOT NULL, lastName VARCHAR(255) NOT NULL, age INT NOT NULL)";
        try {
            statement = con.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(statement != null){
                statement.close();
            }
        }

    }

    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS USERS";
        Statement statement = null;
        try {
            statement = con.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "insert into users (name, lastName, age) values (?, ?, ?)";
        try {
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(preparedStatement != null){
                preparedStatement.close();
            }
        }

    }

    public void removeUserById(long id) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "delete from users where id = ?";
        try {
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(preparedStatement != null){
                preparedStatement.close();
            }
        }
    }

    public List<User> getAllUsers() throws SQLException {
        String sql = "select * from users";
        List<User> users = new ArrayList<User>();
        Statement statement = null;
        try {
            statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(statement != null){
                statement.close();
            }
        }
        return users;
    }

    public void cleanUsersTable() throws SQLException {
        String sql = "delete from users";
        Statement statement = null;
        try {
            statement = con.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(statement != null){
                statement.close();
            }
        }
    }
}
