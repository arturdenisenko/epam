package com.epam.service.impl;

import com.epam.dao.PublisherDao;
import com.epam.model.periodical.Publisher;
import com.epam.service.PublisherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class PublisherServiceImpl implements PublisherService {

    private final static Logger LOGGER = LoggerFactory.getLogger(PublisherServiceImpl.class);
    private PublisherDao publisherDao;

    public PublisherServiceImpl(PublisherDao publisherDao) {
        LOGGER.info("Initializing PublisherServiceImpl");
        this.publisherDao = publisherDao;
    }

    @Override
    public void insert(Publisher publisher) {
        LOGGER.info("CREATE PUBLISHER {}", publisher.toString());
        if (publisherDao.select(publisher.getId()) == null) {
            publisherDao.insert(publisher);
        }
    }

    @Override
    public Publisher select(Long id) {
        return publisherDao.select(id);
    }

    @Override
    public List<Publisher> selectAll() {
        LOGGER.info("GETTING ALL PUBLISHERS");
        return publisherDao.selectAll();
    }

    @Override
    public boolean delete(Long id) {
        LOGGER.info("Deleting publisher by id " + id);

        return id != null && publisherDao.delete(id);
    }

    @Override
    public boolean update(Publisher publisher) {
        LOGGER.info("Updating publisher by id " + publisher.getId());

        if (publisher == null) {
            return false;
        }

        return publisherDao.update(publisher);

    }
}
