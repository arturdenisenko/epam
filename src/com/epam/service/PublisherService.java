package com.epam.service;

import com.epam.model.periodical.Publisher;

import java.util.List;

public interface PublisherService {
    //This method insert Publisher
    void insert(Publisher publisher);

    //This method select Publisher by ID
    Publisher select(Long id);

    List<Publisher> selectAll();

    boolean delete(Long id);

    boolean update(Publisher publisher);
}
