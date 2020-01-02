package com.epam.dao;

import com.epam.model.subscription.Subscription;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static com.epam.TestData.*;

public class SubscriptionDaoTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(SubscriptionDao.class);

    @Before
    public void setUp() throws Exception {
        LOGGER.info("SETUP SUBSCRIPTION TEST");
        SUBSCRIPTION_DAO.clear();
        SUBSCRIPTION_DAO.insert(SUBSCRIPTION);
        SUBSCRIPTION_DAO.insert(SUBSCRIPTION1);
        SUBSCRIPTION_DAO.insert(SUBSCRIPTION2);
        subscriptionListFromDatabase = SUBSCRIPTION_DAO.selectAll();
    }

    @Test
    public void insert() {
        LOGGER.info("INSERT SUBSCRIPTION TESTING");
        SUBSCRIPTION_DAO.insert(SUBSCRIPTION3);
        Assert.assertEquals(4, SUBSCRIPTION_DAO.selectAll().size());
    }

    @Test
    public void select() {
        LOGGER.info("SELECT FROM DATABASE SUBSCRIPTION TESTING");
        Subscription subscription = SUBSCRIPTION_DAO.select(subscriptionListFromDatabase.get(0).getId());
        Assert.assertEquals(subscription, SUBSCRIPTION);
    }

    @Test
    public void selectAll() {
        LOGGER.info("SELECT ALL FROM DATABASE SUBSCRIPTION TESTING");
        Assert.assertEquals(subscriptionListFromDatabase.get(0), SUBSCRIPTION);
        Assert.assertEquals(subscriptionListFromDatabase.get(1), SUBSCRIPTION1);
        Assert.assertEquals(subscriptionListFromDatabase.get(2), SUBSCRIPTION2);
    }

    @Test
    public void selectBySubscriptionCategory() {
        LOGGER.info("SEARCH BY SUBSCRIPTION CATEGORY");
        List<Subscription> subscriptionList = SUBSCRIPTION_DAO.selectBySubscriptionCategory(SUBSCRIPTION_TYPE);
        Assert.assertEquals(1, subscriptionList.size());
    }

    @Test
    public void delete() {
        LOGGER.info("DELETE SUBSCRIPTION TESTING");
        Assert.assertTrue(SUBSCRIPTION_DAO.delete(subscriptionListFromDatabase.get(0).getId()));
        Assert.assertEquals(2, SUBSCRIPTION_DAO.selectAll().size());
    }

    @Test
    public void update() {
        LOGGER.info("UPDATE SUBSCRIPTION TESTING");
        Subscription subscription = subscriptionListFromDatabase.get(0);
        subscription.setCost(340F);
        SUBSCRIPTION_DAO.update(subscription);
        Assert.assertEquals(subscription.getCost(), SUBSCRIPTION_DAO.select(subscription.getId()).getCost());
    }

    @Test
    public void clear() {
        LOGGER.info("CLEAR ALL SUBSCRIPTIONS TESTING");
        SUBSCRIPTION_DAO.clear();
        Assert.assertEquals(0, SUBSCRIPTION_DAO.selectAll().size());
    }
}