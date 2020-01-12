/*
 * @Denisenko Artur
 */

package com.epam.dao.impl;

import com.epam.dao.PeriodicalDao;
import com.epam.dao.SubscriptionDao;
import com.epam.dao.SubscriptionTypeDao;
import com.epam.dao.UserDao;
import com.epam.exception.NotExistEntityException;
import com.epam.filters.ModelFilter;
import com.epam.filters.SubscriptionSelectByCategoryFilter;
import com.epam.model.subscription.Subscription;
import com.epam.model.subscription.SubscriptionType;
import com.epam.pool.ConnectionPool;
import com.epam.util.ExceptionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class SubscriptionDaoImpl implements SubscriptionDao {

    private final static Logger LOGGER = LoggerFactory.getLogger(SubscriptionDaoImpl.class);

    private static final String INSERT_SUBSCRIPTION_SQL =
            "INSERT INTO subscriptions (id, user_id, periodical_id,start_date,end_date, cost, subscription_type_id) " +
                    "VALUES " + " (DEFAULT, ?, ?, ?, ?, ?, ?);";
    private static final String SELECT_SUBSCRIPTION_BY_ID = "SELECT * FROM subscriptions WHERE id =?;";
    private static final String SELECT_ALL_SUBSCRIPTIONS = "SELECT * FROM subscriptions;";
    private static final String DELETE_SUBSCRIPTION_SQL = "DELETE FROM subscriptions where id = ?;";
    private static final String UPDATE_SUBSCRIPTIONS_SQL =
            "UPDATE subscriptions SET user_id = ?, periodical_id = ?,start_date = ?,end_date = ?, cost = ?, " +
                    "subscription_type_id =? where id = ?;";
    private static final String CLEAR_TABLE_SUBSCRIPTION_SQL = "DELETE FROM subscriptions";

    private UserDao userDao = UserDaoImpl.getInstance();
    private PeriodicalDao periodicalDao = PeriodicalDaoImpl.getInstance();
    private SubscriptionTypeDao subscriptionTypeDao = SubscriptionTypeDaoImpl.getInstance();

    public static SubscriptionDaoImpl getInstance() {
        return SubscriptionDaoImpl.SubscriptionDaoImplHolder.HOLDER_INSTANCE;
    }

    @Override
    public void insert(Subscription subscription) {
        LOGGER.info("INSERT SUBSCRIPTION  {}", subscription.toString());
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SUBSCRIPTION_SQL)) {
            InsertUpdate(subscription, preparedStatement);
            System.out.println(preparedStatement);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                LOGGER.info("SUBSCRIPTION CREATION FAILED");
            } else {
                LOGGER.info("SUBSCRIPTION CREATION SUCCESSFUL");
            }

        } catch (SQLException e) {
            throw ExceptionUtil.convertException(e);
        }
    }

    private void InsertUpdate(Subscription subscription, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setLong(1, subscription.getUser().getId());
        preparedStatement.setLong(2, subscription.getPeriodical().getId());
        preparedStatement.setDate(3, Date.valueOf(subscription.getStartDate()));
        preparedStatement.setDate(4, Date.valueOf(subscription.getEndDate()));
        preparedStatement.setFloat(5, subscription.getCost());
        preparedStatement.setLong(6, subscription.getType().getId());
    }

    @Override
    public Subscription select(Long id) {
        LOGGER.info("SELECT FROM SUBSCRIPTION TYPE ID {}", id);
        Subscription subscription = new Subscription();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SUBSCRIPTION_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (!rs.next()) {
                LOGGER.warn("SUBSCRIPTION WITH ID {} ISN'T EXISTS", id);
                throw new NotExistEntityException(id);
            } else {
                subscription = subscriptionInitialize(rs);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return subscription;
    }

    @Override
    public List<Subscription> selectAll() {
        LOGGER.info("SELECT ALL FROM SUBSCRIPTIONS");
        List<Subscription> subscriptions = new CopyOnWriteArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_SUBSCRIPTIONS)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                subscriptions.add(subscriptionInitialize(rs));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return subscriptions;

    }

    @Override
    public boolean delete(Long id) {
        LOGGER.info("DELETE SUBSCRIPTION TYPE WITH ID {}", id);
        boolean rowDeleted = false;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_SUBSCRIPTION_SQL)) {
            statement.setLong(1, id);
            rowDeleted = statement.executeUpdate() > 0;
            if (!rowDeleted) {
                LOGGER.warn("SUBSCRIPTION WITH ID {} ISN'T DELETED", id);
                throw new NotExistEntityException(id);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return rowDeleted;
    }

    @Override
    public boolean update(Subscription subscription) {
        LOGGER.info("UPDATE SUBSCRIPTION {}", subscription.toString());
        boolean rowUpdated = false;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_SUBSCRIPTIONS_SQL)) {
            InsertUpdate(subscription, statement);
            statement.setLong(7, subscription.getId());
            rowUpdated = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return rowUpdated;
    }

    @Override
    public void clear() {
        LOGGER.info("DELETE ALL SUBSCRIPTIONS TABLE");
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(CLEAR_TABLE_SUBSCRIPTION_SQL)) {
            statement.execute();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }

    }

    private Subscription subscriptionInitialize(ResultSet rs) {
        LOGGER.info("SUBSCRIPTION INITIALIZE");
        Subscription subscription = new Subscription();
        try {
            subscription.setId(rs.getLong("id"));
            subscription.setUser(userDao.select(rs.getLong("user_id")));
            subscription.setPeriodical(periodicalDao.select(rs.getLong("periodical_id")));
            subscription.setStartDate(rs.getDate("start_date").toLocalDate());
            subscription.setEndDate(rs.getDate("end_date").toLocalDate());
            subscription.setCost(rs.getFloat("cost"));
            subscription.setType(subscriptionTypeDao.select(rs.getLong("subscription_type_id")));
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return subscription;
    }

    @Override
    public List<Subscription> selectBySubscriptionCategory(SubscriptionType subscriptionType) {
        LOGGER.info("SELECT Subscription by SubscriptionType {}", subscriptionType.toString());
        ModelFilter filter = new SubscriptionSelectByCategoryFilter();
        return filter.meetCriteria(this.selectAll(), subscriptionType);
    }

    private static class SubscriptionDaoImplHolder {
        private static final SubscriptionDaoImpl HOLDER_INSTANCE = new SubscriptionDaoImpl();
    }

}
