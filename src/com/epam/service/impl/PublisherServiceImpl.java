/*
 * @Denisenko Artur
 */

package com.epam.service.impl;

import com.epam.dao.PublisherDao;
import com.epam.exception.DaoException;
import com.epam.exception.ServiceException;
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
    public void insert(Publisher publisher) throws ServiceException {
        LOGGER.info("CREATE PUBLISHER {}", publisher.toString());
        try {
            publisherDao.insert(publisher);
        } catch (DaoException e) {
            LOGGER.warn("CREATE PUBLISHER FAILED {}" + e.getMessage(), e);
            throw new ServiceException(e);
        }

    }

    @Override
    public Publisher select(Long id) throws ServiceException {
        try {
            return publisherDao.select(id);
        } catch (DaoException e) {
            LOGGER.error("SELECT PUBLISHER FAILED {}" + e, e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Publisher> selectAll() throws ServiceException {
        LOGGER.info("GETTING ALL PUBLISHERS");
        try {
            return publisherDao.selectAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean delete(Long id) throws ServiceException {
        LOGGER.info("DELETE PUBLISHER WITH ID {} ", id);
        try {
            return id != null && publisherDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean update(Publisher publisher) throws ServiceException {
        LOGGER.info("UPDATE PUBLISHER WITH ID {}", publisher.getId());
        try {
            return publisherDao.update(publisher);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
