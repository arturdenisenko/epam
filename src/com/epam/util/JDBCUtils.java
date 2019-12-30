package com.epam.util;

import com.epam.Main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtils {

    private final static Logger LOGGER = LoggerFactory.getLogger(JDBCUtils.class);

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
            LOGGER.error(e.getMessage(), e);
        }
    }

    public static Connection getConnection() {
        init();
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return connection;
    }
}
