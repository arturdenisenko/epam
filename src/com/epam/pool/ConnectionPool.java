/*
 * @Denisenko Artur
 */

/*
 * @Denisenko Artur
 */


package com.epam.pool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Connection Pool
 */
public class ConnectionPool implements ConnectionBuilder {
    private final static Logger LOGGER = LoggerFactory.getLogger(ConnectionPool.class);

    private static ConnectionPool connectionPool = null;
    private DataSource dataSource = null;

    private ConnectionPool() {
        LOGGER.info("Initializing Connection Pool class");

        try {
            Context ctx = new InitialContext();
            dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/periodicalsRes");

        } catch (NamingException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public static synchronized ConnectionPool getInstance() {
        if (connectionPool == null) {
            connectionPool = new ConnectionPool();
        }
        return connectionPool;
    }

    public Connection getConnection() throws SQLException {
        LOGGER.info("GETTING CONNECTION");
        return dataSource.getConnection();
    }
}
