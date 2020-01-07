/*
 * @Denisenko Artur
 */

package com.epam.exception;

import java.sql.SQLException;
import java.util.Iterator;

public class DaoException extends SQLException {

    public DaoException(String reason, String SQLState, int vendorCode) {
        super(reason, SQLState, vendorCode);
    }

    public DaoException(String reason, String SQLState) {
        super(reason, SQLState);
    }

    public DaoException(String reason) {
        super(reason);
    }

    public DaoException() {
        super();
    }

    public DaoException(Throwable cause) {
        super(cause);
    }

    public DaoException(String reason, Throwable cause) {
        super(reason, cause);
    }

    public DaoException(String reason, String sqlState, Throwable cause) {
        super(reason, sqlState, cause);
    }

    public DaoException(String reason, String sqlState, int vendorCode, Throwable cause) {
        super(reason, sqlState, vendorCode, cause);
    }

    @Override
    public String getSQLState() {
        return super.getSQLState();
    }

    @Override
    public int getErrorCode() {
        return super.getErrorCode();
    }

    @Override
    public SQLException getNextException() {
        return super.getNextException();
    }

    @Override
    public void setNextException(SQLException ex) {
        super.setNextException(ex);
    }

    @Override
    public Iterator<Throwable> iterator() {
        return super.iterator();
    }
}
