package com.epam.dao;

import com.epam.exception.DaoException;

public interface Dao<T> {
    T read(Long id) throws DaoException;

    Long create(T entity) throws DaoException;

    void update(T entity) throws DaoException;

    void delete(Long id) throws DaoException;
}

