/*
 * @Denisenko Artur
 */

package com.epam.dao;

import com.epam.exception.DaoException;
import com.epam.model.periodical.Publisher;

import java.util.List;

public interface PublisherDao {
    //Insert publisher in database
    void insert(Publisher publisher) throws DaoException;

    //select publisher from database by id
    Publisher select(Long id) throws DaoException;

    //select all publishers from database
    List<Publisher> selectAll() throws DaoException;

    //delete publisher by id
    boolean delete(Long id) throws DaoException;

    //update publisher
    boolean update(Publisher publisher) throws DaoException;

    //clear all publishers for test only
    void clear() throws DaoException;

}

