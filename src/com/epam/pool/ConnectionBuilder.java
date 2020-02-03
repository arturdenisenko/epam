/*
 * @Denisenko Artur
 */

package com.epam.pool;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * think about it for dao 2 connection pool
 */
public interface ConnectionBuilder {
    Connection getConnection() throws SQLException;
}
