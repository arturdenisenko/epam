package com.epam.dao;

import com.epam.dao.impl.PublisherDaoImpl;
import com.epam.exception.ExistEntityException;
import com.epam.exception.NotExistEntityException;
import com.epam.model.periodical.Publisher;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class PublisherDaoTest {

    public static final Publisher PUBLISHER = new Publisher(45, "ООО ИКАР");
    public static final Publisher PUBLISHER_1 = new Publisher(46, "ОДО ЭЛЕГАНТ");
    public static final Publisher PUBLISHER_2 = new Publisher(47, "ОАО НОВАЯ РУСЬ");
    public static final Publisher PUBLISHER_3 = new Publisher(48, "publisher_4");
    private static final Logger LOGGER = LoggerFactory.getLogger(PublisherDaoTest.class);
    private static final PublisherDao PUBLISHER_DAO = new PublisherDaoImpl();
    private static List<Publisher> PUBLISHER_LIST_FROM_DATABASE = PUBLISHER_DAO.selectAll();

    @Before
    public void setUp() {
        LOGGER.info("SETUP PUBLISHERS TEST");
        PUBLISHER_DAO.clear();
        PUBLISHER_DAO.insert(PUBLISHER);
        PUBLISHER_DAO.insert(PUBLISHER_1);
        PUBLISHER_DAO.insert(PUBLISHER_2);
        PUBLISHER_LIST_FROM_DATABASE = PUBLISHER_DAO.selectAll();
    }

    @Test
    public void insert() {
        LOGGER.info("INSERT PUBLISHER TESTING");
        PUBLISHER_DAO.insert(PUBLISHER_3);
        Assert.assertEquals(4, PUBLISHER_DAO.selectAll().size());
    }

    @Test
    public void select() {
        LOGGER.info("SELECT FROM DATABASE PUBLISHER TESTING");
        Publisher publisherTest = PUBLISHER_DAO.select(PUBLISHER_LIST_FROM_DATABASE.get(0).getId());
        Assert.assertEquals(publisherTest, PUBLISHER);
    }

    @Test
    public void selectAll() {
        LOGGER.info("SELECT ALL FROM DATABASE PUBLISHER TESTING");
        List<Publisher> publishers2 = PUBLISHER_DAO.selectAll();
        Assert.assertEquals(publishers2.get(0).getName(), PUBLISHER.getName());
        Assert.assertEquals(publishers2.get(1).getName(), PUBLISHER_1.getName());
        Assert.assertEquals(publishers2.get(2).getName(), PUBLISHER_2.getName());
    }

    @Test
    public void delete() {
        LOGGER.info("DELETE PUBLISHER TESTING");
        Assert.assertTrue(PUBLISHER_DAO.delete(PUBLISHER_LIST_FROM_DATABASE.get(0).getId()));
        Assert.assertEquals(2, PUBLISHER_DAO.selectAll().size());
    }

    @Test
    public void update() {
        LOGGER.info("UPDATE PUBLISHER TESTING");
        Publisher publisherForUpdate = PUBLISHER_LIST_FROM_DATABASE.get(0);
        publisherForUpdate.setName("TESTING UPDATE NAME");
        PUBLISHER_DAO.update(publisherForUpdate);
        Assert.assertEquals(publisherForUpdate.getName(), PUBLISHER_DAO.select(publisherForUpdate.getId()).getName());
    }

    @Test(expected = ExistEntityException.class)
    public void insertExisting() {
        LOGGER.info("INSERT EXISTING PUBLISHER TESTING");
        PUBLISHER_DAO.insert(PUBLISHER_LIST_FROM_DATABASE.get(0));
    }

    @Test(expected = NotExistEntityException.class)
    public void deleteNotExist() {
        LOGGER.info("DELETE NOT EXIST PUBLISHER TESTING");
        PUBLISHER_DAO.delete(525235);
    }

    @Test(expected = NotExistEntityException.class)
    public void selectNotExist() {
        LOGGER.info("SELECT NOT EXIST PUBLISHER TESTING");
        PUBLISHER_DAO.select(525235);
    }

    @Test
    public void clear() {
        LOGGER.info("CLEAR ALL PUBLISHERS TESTING");
        PUBLISHER_DAO.clear();
        Assert.assertEquals(0, PUBLISHER_DAO.selectAll().size());
    }
}