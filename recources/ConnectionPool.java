/*
 * @Denisenko Artur
 */

ррpackage com.epam.pool;

import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool implements ConnectionBuilder{
    private static final Logger LOGGER = Logger.getLogger(ConnectionPool.class);

    private static ConnectionPool connectionPool = null;
    private DataSource dataSource = null;

    private ConnectionPool() {
        LOGGER.info("Initializing connectionFactory class");

        try {
            Context ctx = new InitialContext();
            dataSource =(DataSource) ctx.lookup("java:comp/env/jdbc/periodicalsRes");

        } catch (NamingException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public Connection getConnection() throws SQLException {
        LOGGER.info("Getting connection");
        return dataSource.getConnection();
    }

    public static synchronized ConnectionPool getInstance() {
        if(connectionPool == null) {
            connectionPool = new ConnectionPool();
        }
        return connectionPool;
    }
}
