/*
 * @Denisenko Artur
 */

package com.epam.dao;

import com.epam.exception.DaoException;
import com.epam.exception.ExistEntityException;
import com.epam.exception.NotExistEntityException;
import com.epam.model.periodical.Publisher;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static com.epam.TestData.*;

public class PublisherDaoTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(PublisherDaoTest.class);

    @Before
    public void setUp() {
        LOGGER.info("SETUP PUBLISHERS TEST");
        try {
            PUBLISHER_DAO.clear();
            PUBLISHER_DAO.insert(PUBLISHER);
            PUBLISHER_DAO.insert(PUBLISHER_1);
            PUBLISHER_DAO.insert(PUBLISHER_2);
            publisherList = PUBLISHER_DAO.selectAll();
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void insert() {
        try {
            LOGGER.info("INSERT PUBLISHER TESTING");
            PUBLISHER_DAO.insert(PUBLISHER_3);
            Assert.assertEquals(4, PUBLISHER_DAO.selectAll().size());
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void select() {
        try {
            LOGGER.info("SELECT FROM DATABASE PUBLISHER TESTING");
            Publisher publisherTest = PUBLISHER_DAO.select(publisherList.get(0).getId());
            Assert.assertEquals(publisherTest, PUBLISHER);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void selectAll() {
        try {
            LOGGER.info("SELECT ALL FROM DATABASE PUBLISHER TESTING");
            List<Publisher> publishers2 = PUBLISHER_DAO.selectAll();
            Assert.assertEquals(publishers2.get(0).getName(), PUBLISHER.getName());
            Assert.assertEquals(publishers2.get(1).getName(), PUBLISHER_1.getName());
            Assert.assertEquals(publishers2.get(2).getName(), PUBLISHER_2.getName());
        } catch (
                DaoException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void delete() {
        try {
            LOGGER.info("DELETE PUBLISHER TESTING");
            Assert.assertTrue(PUBLISHER_DAO.delete(publisherList.get(0).getId()));
            Assert.assertEquals(2, PUBLISHER_DAO.selectAll().size());
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void update() {
        try {
            LOGGER.info("UPDATE PUBLISHER TESTING");
            Publisher publisherForUpdate = publisherList.get(0);
            publisherForUpdate.setName("TESTING UPDATE NAME");
            PUBLISHER_DAO.update(publisherForUpdate);
            Assert.assertEquals(publisherForUpdate.getName(), PUBLISHER_DAO.select(publisherForUpdate.getId()).getName());
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @Test(expected = ExistEntityException.class)
    public void insertExisting() {
        try {
            LOGGER.info("INSERT EXISTING PUBLISHER TESTING");
            PUBLISHER_DAO.insert(publisherList.get(0));
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @Test(expected = NotExistEntityException.class)
    public void deleteNotExist() {
        try {
            LOGGER.info("DELETE NOT EXIST PUBLISHER TESTING");
            PUBLISHER_DAO.delete(525235L);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @Test(expected = NotExistEntityException.class)
    public void selectNotExist() {
        try {
            LOGGER.info("SELECT NOT EXIST PUBLISHER TESTING");
            PUBLISHER_DAO.select(525235L);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void clear() {
        try {
            LOGGER.info("CLEAR ALL PUBLISHERS TESTING");
            PUBLISHER_DAO.clear();
            Assert.assertEquals(0, PUBLISHER_DAO.selectAll().size());
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
}