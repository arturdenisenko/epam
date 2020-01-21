/*
 * @Denisenko Artur
 */

/*
 * @Denisenko Artur
 */

package com.epam.service;

import com.epam.model.periodical.Publisher;

import java.util.List;

public interface PublisherService {
    //This method insert Publisher
    Publisher createPublisher(Publisher publisher);

    //This method select Publisher by ID
    Publisher getPublisherById(Long id);

    List<Publisher> getAll();

    boolean deletePublisherById(Long id);

    boolean update(Publisher publisher);
}
