package com.epam.dao;

import com.epam.exception.DAOException;

import java.util.List;

public interface AbstractDao<K, T> {
    //TODO Дописать общий интерфейс сюда
    // public void insert(<T> publisher);
    void insert(T entity) throws DAOException;

    void select(K id) throws DAOException;

    List<T> selectAll() throws DAOException;

    void delete(K id) throws DAOException;

    void update(T entity) throws DAOException;

    void clear();
}
