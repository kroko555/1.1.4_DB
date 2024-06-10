package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/sys";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "Martinov99!";

    static Connection conn = null;

    public static Connection getConnection() {
        try {
            Class.forName(DB_DRIVER);
            conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            System.out.println("Connected to database");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error connecting to database");
            e.printStackTrace();
        }
        return conn;
    }
    public static void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Connection closed");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
