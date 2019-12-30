package com.epam.dao.impl;

import com.epam.dao.SubscriptionDao;
import com.epam.filters.Filter;
import com.epam.filters.SubscriptionSelectByCategoryFilter;
import com.epam.model.subscription.Subscription;
import com.epam.model.subscription.SubscriptionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class SubscriptionDaoImpl implements SubscriptionDao {

    private final static Logger LOGGER = LoggerFactory.getLogger(SubscriptionDaoImpl.class);

    private static final String INSERT_PUBLISHER_SQL = "INSERT INTO publishers (id, name) VALUES " + " (DEFAULT, ?);";
    private static final String SELECT_PUBLISHER_BY_ID = "SELECT id,name FROM publishers WHERE id =?;";
    private static final String SELECT_ALL_PUBLISHERS = "SELECT * FROM publishers;";
    private static final String DELETE_PUBLISHERS_SQL = "DELETE FROM publishers where id = ?;";
    private static final String UPDATE_PUBLISHER_SQL = "UPDATE publishers SET name = ? where id = ?;";
    private static final String CLEAR_TABLE_PUBLISHER_SQL = "DELETE FROM publishers";

    @Override
    public void insert(Subscription subscription) {

    }

    @Override
    public Subscription select(int id) {
        return null;
    }

    @Override
    public List<Subscription> selectAll() {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public boolean update(Subscription subscription) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public List<Subscription> selectBySubscriptionCategory(SubscriptionType subscriptionType) {
        LOGGER.info("SELECT Subscription by SubscriptionType {}", subscriptionType.toString());
        Filter filter = new SubscriptionSelectByCategoryFilter();
        return filter.meetCriteria(this.selectAll(), subscriptionType);
    }

}
