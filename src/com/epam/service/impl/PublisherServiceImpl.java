/*
 * @Denisenko Artur
 */

/*
 * @Denisenko Artur
 */

package com.epam.service.impl;

import com.epam.dao.PublisherDao;
import com.epam.exception.DaoException;
import com.epam.model.periodical.Publisher;
import com.epam.service.PublisherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class PublisherServiceImpl implements PublisherService {

    private final static Logger LOGGER = LoggerFactory.getLogger(PublisherServiceImpl.class);
    private PublisherDao publisherDao;

    public PublisherServiceImpl(PublisherDao publisherDao) {
        LOGGER.info("INITIALIZING PUBLISHER SERVICE IMPL");
        this.publisherDao = publisherDao;
    }

    @Override
    public Publisher createPublisher(Publisher publisher) {
        LOGGER.info("CREATE PUBLISHER {}", publisher.toString());
        try {
            return publisherDao.insertPublisher(publisher);
        } catch (DaoException e) {
            LOGGER.warn("CREATE PUBLISHER FAILED {}" + e.getMessage(), e);
        }
        return null;
    }

    @Override
    public Publisher getPublisherById(Long id) {
        LOGGER.info("GET PUBLISHER WITH ID {}", id);
        try {
            return publisherDao.select(id);
        } catch (DaoException e) {
            LOGGER.warn("GET PUBLISHER WITH ID {} FAILED {}" + e.getMessage(), e);
        }
        return null;
    }

    @Override
    public List<Publisher> getAll() {
        LOGGER.info("GETTING ALL PUBLISHERS");
        try {
            return publisherDao.selectAll();
        } catch (DaoException e) {
            LOGGER.warn("GET ALL PUBLISHERS  {} FAILED {}" + e.getMessage(), e);
        }
        return null;
    }

    @Override
    public boolean deletePublisherById(Long id) {
        LOGGER.info("DELETE PUBLISHER WITH ID {} ", id);

        try {
            return id != null && publisherDao.delete(id);
        } catch (DaoException e) {
            LOGGER.warn("DELETE PUBLISHER WITH ID  {} FAILED {} {}", id, e.getMessage(), e);
        }
        return false;

    }

    @Override
    public boolean update(Publisher publisher) {
        LOGGER.info("UPDATE PUBLISHER WITH ID {}", publisher.getId());

        try {
            return publisherDao.update(publisher);
        } catch (DaoException e) {
            LOGGER.warn("UPDATE PUBLISHER WITH ID  {} FAILED {} {}", publisher.getId(), e.getMessage(), e);
        }
        return false;
    }
}
