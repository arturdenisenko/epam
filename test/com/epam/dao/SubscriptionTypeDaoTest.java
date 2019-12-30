package com.epam.dao;

import com.epam.dao.impl.SubscriptionTypeDaoImpl;
import com.epam.model.subscription.SubscriptionType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class SubscriptionTypeDaoTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(SubscriptionTypeDaoTest.class);

    public static final SubscriptionType SUBSCRIPTION_TYPE = new SubscriptionType(45, "Месячная", 1, 1.0F);
    public static final SubscriptionType SUBSCRIPTION_TYPE1 = new SubscriptionType(46, "Квартальная", 3, 3.0F);
    public static final SubscriptionType SUBSCRIPTION_TYPE2 = new SubscriptionType(47, "Полугодие", 6, 6.0F);
    public static final SubscriptionType SUBSCRIPTION_TYPE3 = new SubscriptionType(48, "Годовая", 12, 12.0F);

    private static final SubscriptionTypeDao SUBSCRIPTION_TYPE_DAO = SubscriptionTypeDaoImpl.getInstance();
    private static List<SubscriptionType> subscriptionTypesFromDatabase;


    @Before
    public void setUp() throws Exception {
        LOGGER.info("SETUP SUBSCRIPTION TYPE TEST");
        SUBSCRIPTION_TYPE_DAO.clear();
        SUBSCRIPTION_TYPE_DAO.insert(SUBSCRIPTION_TYPE);
        SUBSCRIPTION_TYPE_DAO.insert(SUBSCRIPTION_TYPE1);
        SUBSCRIPTION_TYPE_DAO.insert(SUBSCRIPTION_TYPE2);
        subscriptionTypesFromDatabase = SUBSCRIPTION_TYPE_DAO.selectAll();
    }

    @Test
    public void insert() {
        LOGGER.info("INSERT SUBSCRIPTION TYPE TESTING");
        SUBSCRIPTION_TYPE_DAO.insert(SUBSCRIPTION_TYPE3);
        Assert.assertEquals(4, SUBSCRIPTION_TYPE_DAO.selectAll().size());
    }

    @Test
    public void select() {
        LOGGER.info("SELECT FROM DATABASE SUBSCRIPTION TYPE TESTING");
        SubscriptionType subscriptionType = SUBSCRIPTION_TYPE_DAO.select(subscriptionTypesFromDatabase.get(0).getId());
        Assert.assertEquals(subscriptionType, SUBSCRIPTION_TYPE);
    }

    @Test
    public void selectAll() {
        LOGGER.info("SELECT ALL FROM DATABASE SUBSCRIPTION TYPE TESTING");
        Assert.assertEquals(subscriptionTypesFromDatabase.get(0).getName(), SUBSCRIPTION_TYPE.getName());
        Assert.assertEquals(subscriptionTypesFromDatabase.get(1).getName(), SUBSCRIPTION_TYPE1.getName());
        Assert.assertEquals(subscriptionTypesFromDatabase.get(2).getName(), SUBSCRIPTION_TYPE2.getName());
    }

    @Test
    public void delete() {
        LOGGER.info("DELETE SUBSCRIPTION TYPE TESTING");
        Assert.assertTrue(SUBSCRIPTION_TYPE_DAO.delete(subscriptionTypesFromDatabase.get(0).getId()));
        Assert.assertEquals(2, SUBSCRIPTION_TYPE_DAO.selectAll().size());
    }

    @Test
    public void update() {
        LOGGER.info("UPDATE SUBSCRIPTION TYPE TESTING");
        SubscriptionType subscriptionType = subscriptionTypesFromDatabase.get(0);
        subscriptionType.setName("TESTING UPDATE NAME SUBSCRIPTION TYPE");
        SUBSCRIPTION_TYPE_DAO.update(subscriptionType);
        Assert.assertEquals(subscriptionType.getName(), SUBSCRIPTION_TYPE_DAO.select(subscriptionType.getId()).getName());
    }


    @Test
    public void clear() {
    }
}