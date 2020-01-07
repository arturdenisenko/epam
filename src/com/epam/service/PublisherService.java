/*
 * @Denisenko Artur
 */

package com.epam.service;

import com.epam.exception.ServiceException;
import com.epam.model.periodical.Publisher;

import java.util.List;

public interface PublisherService {
    //This method insert Publisher
    void insert(Publisher publisher) throws ServiceException;

    //This method select Publisher by ID
    Publisher select(Long id) throws ServiceException;

    List<Publisher> selectAll() throws ServiceException;

    boolean delete(Long id) throws ServiceException;

    boolean update(Publisher publisher) throws ServiceException;
}
