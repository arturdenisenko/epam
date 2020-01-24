/*
 * @Denisenko Artur
 */

/*
 * @Denisenko Artur
 */

/*
 * @Denisenko Artur
 */

package com.epam.dao;

import com.epam.Main;
import com.epam.pool.ConnectionBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Connection Pool for Test Dao, need to change it
 */
public class ConnectionPool implements ConnectionBuilder {
    private final static Logger LOGGER = LoggerFactory.getLogger(ConnectionPool.class);
    private static final String PROPS = "periodicals.properties";
    protected static String jdbcURL;
    protected static String jdbcUsername;
    protected static String jdbcPassword;
    private static ConnectionPool instance = null;

    private ConnectionPool() {
        //private constructor
    }

    public static ConnectionPool getInstance() {
        if (instance == null)
            instance = new ConnectionPool();
        return instance;
    }

    private static void init() {
        LOGGER.info("Connection properties initialize");
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

    public Connection getConnection() {
        init();
        Connection connection = null;
        LOGGER.info("GET CONNECTION");
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return connection;
    }
}
