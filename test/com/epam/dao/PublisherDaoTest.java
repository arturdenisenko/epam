package com.epam.dao;

import com.epam.model.periodical.Publisher;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class PublisherDaoTest {
    public static Publisher publisher = new Publisher(45, "Артур");
    public static Publisher publisher_1 = new Publisher(46, "publisher_2");
    public static Publisher publisher_2 = new Publisher(47, "publisher_3");
    public static Publisher publisher_3 = new Publisher(48, "publisher_4");
    PublisherDao publisherDao = new PublisherDao();
    List<Publisher> publishers = new CopyOnWriteArrayList<>();

    @Before
    public void setUp() throws Exception {
        publishers.clear();
        publisherDao.clear();
        publishers.add(publisher);
        publishers.add(publisher_1);
        publishers.add(publisher_2);
        publisherDao.insert(publisher);
        publisherDao.insert(publisher_1);
        publisherDao.insert(publisher_2);
    }

    @Test
    public void insertPublisher() throws SQLException {
        publisherDao.insert(publisher_3);
        Publisher publisherTest = publisherDao.select(publisher_3.getId());
        Assert.assertEquals(publisher_3.getName(), publisherTest.getName());

    }

    @Test
    public void selectPublisher() {
        Publisher publisherTest = publisherDao.select(publisher.getId());
        Assert.assertEquals(publisher.getId(), publisherTest.getId());
    }

    @Test
    public void selectAllPublishers() {
        List<Publisher> publishers2 = publisherDao.selectAll();
        Assert.assertEquals(publishers2.get(0).getName(), publishers.get(0).getName());
        Assert.assertEquals(publishers2.get(1).getName(), publishers.get(1).getName());
        Assert.assertEquals(publishers2.get(2).getName(), publishers.get(2).getName());
    }

    @Test
    public void deletePublisher() throws SQLException {
        publisherDao.delete(publisher.getId());
        Assert.assertEquals(2, publisherDao.selectAll().size());
    }

    @Test
    public void updatePublisher() throws SQLException {
        publisher.setName("JJJ");
        publisherDao.update(publisher);
        Publisher testPublisher = publisherDao.select(publisher.getId());
        Assert.assertEquals("JJJ", testPublisher.getName());
    }

    @Test
    public void clear() throws SQLException {
        publisherDao.clear();
        Assert.assertEquals(0, publisherDao.selectAll().size());
    }
}