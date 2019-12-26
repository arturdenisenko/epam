package com.epam.dao;

import com.epam.exception.DAOException;

import java.util.List;

public interface AbstractDao<K, T> {
    //TODO Дописать общий интерфейс сюда
    // public void insert(<T> publisher);
    void create(T entity) throws DAOException;

    void read(K id) throws DAOException;

    List<T> readAll() throws DAOException;

    void delete(K id) throws DAOException;

    void update(T entity) throws DAOException;

    void clear();
}
