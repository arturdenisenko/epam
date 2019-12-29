package com.epam.util;

import com.epam.exception.ExistEntityException;
import com.epam.exception.PeriodicalException;
import org.postgresql.util.PSQLException;

import java.sql.SQLException;

public class ExceptionUtil {
    private ExceptionUtil() {
    }

    public static PeriodicalException convertException(SQLException e) {
        if (e instanceof PSQLException) {

//            http://www.postgresql.org/docs/9.3/static/errcodes-appendix.html
            if (e.getSQLState().equals("23505")) {
                return new ExistEntityException("null");
            }
        }
        return new PeriodicalException(e);
    }
}
