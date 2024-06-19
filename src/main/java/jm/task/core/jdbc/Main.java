package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {

        UserDao service = new UserDaoJDBCImpl();

        service.createUsersTable();

        service.saveUser("Джон", "Джонов", (byte) 15);
        service.saveUser("Рик", "Риков", (byte) 55);
        service.saveUser("Дональд", "Дональдов", (byte) 67);
        service.saveUser("Эрик", "Эриков", (byte) 33);

        List<User> list = service.getAllUsers();
        System.out.println(Arrays.toString(list.toArray()));

        service.cleanUsersTable();

        service.dropUsersTable();

        Util.closeConnection();
    }
}
