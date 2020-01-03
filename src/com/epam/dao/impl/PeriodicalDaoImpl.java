package com.epam.dao.impl;

import com.epam.dao.PeriodicalCategoryDao;
import com.epam.dao.PeriodicalDao;
import com.epam.dao.PublisherDao;
import com.epam.exception.NotExistEntityException;
import com.epam.filters.Filter;
import com.epam.filters.PeriodicalSelectByNameFilter;
import com.epam.model.periodical.Periodical;
import com.epam.pool.ConnectionPool;
import com.epam.util.ExceptionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class PeriodicalDaoImpl implements PeriodicalDao {

    private final static Logger LOGGER = LoggerFactory.getLogger(PeriodicalDaoImpl.class);

    private static final String INSERT_PERIODICAL_SQL =
            "INSERT INTO periodicals (id, name, about, publisher_fk, periodical_category, " +
                    "periodicity_in_six_month, min_subscription_period, cost_per_month) VALUES " + " " +
                    "(DEFAULT, ?,?,?,?,?,?,?);";
    private static final String SELECT_PERIODICAL_BY_ID = "SELECT * FROM periodicals WHERE id =?;";
    private static final String SELECT_ALL_PERIODICALS = "SELECT * FROM periodicals;";
    private static final String DELETE_PERIODICAL_SQL = "DELETE FROM periodicals where id = ?;";
    private static final String UPDATE_PERIODICAL_SQL = "UPDATE periodicals SET name = ?, about = ?,publisher_fk= ?" +
            ", periodical_category = ?, periodicity_in_six_month = ?, min_subscription_period=?, cost_per_month=? " +
            "where id = ?;";
    private static final String CLEAR_TABLE_PERIODICAL_SQL = "DELETE FROM periodicals";

    private PeriodicalCategoryDao periodicalCategoryDao = PeriodicalCategoryDaoImpl.getInstance();
    private PublisherDao publisherDao = PublisherDaoImpl.getInstance();

    public PeriodicalDaoImpl() {
    }

    public static PeriodicalDaoImpl getInstance() {
        return PeriodicalDaoImpl.PeriodicalDaoImplHolder.HOLDER_INSTANCE;
    }

    @Override
    public void insert(Periodical periodical) {
        LOGGER.info("CREATE NEW PERIODICAL {}", periodical.toString());
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PERIODICAL_SQL)) {
            preparedStatement.setString(1, periodical.getName());
            preparedStatement.setString(2, periodical.getAbout());
            preparedStatement.setLong(3, periodical.getPublisher().getId());
            preparedStatement.setLong(4, periodical.getPeriodicalCategory().getId());
            preparedStatement.setInt(5, periodical.getPeriodicityInSixMonth());
            preparedStatement.setInt(6, periodical.getMinSubscriptionPeriod());
            preparedStatement.setFloat(7, periodical.getCostPerMonth());

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                LOGGER.warn("PERIODICAL CREATION FAILED");
            } else {
                LOGGER.info("PERIODICAL CREATION SUCCESSFUL");
            }
        } catch (SQLException e) {
            throw ExceptionUtil.convertException(e);
        }
    }

    @Override
    public Periodical select(Long id) {
        LOGGER.info("SELECT PERIODICAL WITH ID = {}", id);
        Periodical periodical = null;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PERIODICAL_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (!rs.next()) {
                throw new NotExistEntityException(id);
            } else {
                periodical = new Periodical();
                periodical.setId(id);
                periodical.setName(rs.getString("name"));
                periodical.setAbout(rs.getString("about"));
                periodical.setPublisher(publisherDao.select(rs.getLong("publisher_fk")));
                periodical.setPeriodicalCategory(periodicalCategoryDao.select(rs.getLong("periodical_category")));
                periodical.setPeriodicityInSixMonth(rs.getInt("periodicity_in_six_month"));
                periodical.setMinSubscriptionPeriod(rs.getInt("min_subscription_period"));
                periodical.setCostPerMonth(rs.getBigDecimal("cost_per_month").floatValue());
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return periodical;
    }

    @Override
    public List<Periodical> selectByName(String name) {
        LOGGER.info("SELECT PERIODICAL BY NAME {}", name);
        Filter filter = new PeriodicalSelectByNameFilter();
        return filter.meetCriteria(selectAll(), name);
    }


    @Override
    public List<Periodical> selectAll() {
        LOGGER.info("SELECT ALL PERIODICALS");
        List<Periodical> periodicals = new CopyOnWriteArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PERIODICALS)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Periodical periodical = new Periodical();
                periodical.setId(rs.getLong("id"));
                periodical.setName(rs.getString("name"));
                periodical.setAbout(rs.getString("about"));
                periodical.setPublisher(publisherDao.select(rs.getLong("publisher_fk")));
                periodical.setPeriodicalCategory(periodicalCategoryDao.select(rs.getLong("periodical_category")));
                periodical.setPeriodicityInSixMonth(rs.getInt("periodicity_in_six_month"));
                periodical.setMinSubscriptionPeriod(rs.getInt("min_subscription_period"));
                periodical.setCostPerMonth(rs.getBigDecimal("cost_per_month").floatValue());
                periodicals.add(periodical);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return periodicals;
    }

    @Override
    public boolean delete(Long id) {
        LOGGER.info("DELETE PERIODICAL WITH ID = {} ", id);
        boolean rowDeleted = false;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_PERIODICAL_SQL)) {
            statement.setLong(1, id);
            rowDeleted = statement.executeUpdate() > 0;
            if (!rowDeleted) {
                LOGGER.warn("PERIODICAL WITH ID {} ISN'T DELETED", id);
                throw new NotExistEntityException(id);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return false;
    }

    @Override
    public boolean update(Periodical periodical) {
        LOGGER.info("UPDATE PERIODICAL {}", periodical.toString());
        boolean rowUpdated = false;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_PERIODICAL_SQL)) {
            statement.setString(1, periodical.getName());
            statement.setString(2, periodical.getAbout());
            statement.setLong(3, periodical.getPublisher().getId());
            statement.setLong(4, periodical.getPeriodicalCategory().getId());
            statement.setInt(5, periodical.getPeriodicityInSixMonth());
            statement.setInt(6, periodical.getMinSubscriptionPeriod());
            statement.setFloat(7, periodical.getCostPerMonth());
            statement.setLong(8, periodical.getId());
            rowUpdated = statement.executeUpdate() > 0;
            if (!rowUpdated) {
                throw new NotExistEntityException(periodical.getId());
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return rowUpdated;
    }

    //FOR TESTS ONLY
    @Override
    public void clear() {
        LOGGER.info("DELETE ALL PERIODICALS");
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(CLEAR_TABLE_PERIODICAL_SQL)) {
            statement.execute();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    private static class PeriodicalDaoImplHolder {
        private static final PeriodicalDaoImpl HOLDER_INSTANCE = new PeriodicalDaoImpl();
    }
}
