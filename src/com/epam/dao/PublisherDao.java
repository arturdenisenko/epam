package com.epam.dao;

import com.epam.model.periodical.Publisher;

import java.util.List;

public interface PublisherDao {

    void insert(Publisher publisher);

    Publisher select(Long id);

    List<Publisher> selectAll();

    boolean delete(Long id);

    boolean update(Publisher publisher);

    //clear all publishers for test only
    void clear();

}

