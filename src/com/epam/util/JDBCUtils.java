package com.epam.util;

import com.epam.Main;
import com.epam.exception.DAOException;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtils {
    private static final String PROPS = "periodicals.properties";
    protected static String jdbcURL;
    protected static String jdbcUsername;
    protected static String jdbcPassword;

    private static void init() {
        ClassLoader loader = Main.class.getClassLoader();
        try (InputStream is = loader.getResourceAsStream(PROPS)) {
            Properties props = new Properties();
            props.load(is);
            jdbcUsername = props.getProperty("db.user");
            jdbcPassword = props.getProperty("db.password");
            jdbcURL = props.getProperty("db.url");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws DAOException {
        init();
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /*public static void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }*/
}
