package com.epam.dao;

import com.epam.model.periodical.Publisher;

import java.util.List;

public interface PublisherDao {
    //Insert publisher in database
    void insert(Publisher publisher);

    //select publisher from database by id
    Publisher select(Long id);

    //select all publishers from database
    List<Publisher> selectAll();

    //delete publisher by id
    boolean delete(Long id);

    //update publisher
    boolean update(Publisher publisher);

    //clear all publishers for test only
    void clear();

}

