/*
 * @Denisenko Artur
 */

package com.epam.dao.impl;

import com.epam.dao.PeriodicalCategoryDao;
import com.epam.dao.PeriodicalDao;
import com.epam.dao.PublisherDao;
import com.epam.exception.NotExistEntityException;
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

import static com.epam.dao.impl.SQLQueries.*;

public class PeriodicalDaoImpl implements PeriodicalDao {
    private final static Logger LOGGER = LoggerFactory.getLogger(PeriodicalDaoImpl.class);

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
            PeriodicalCreate(periodical, preparedStatement);

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

    private void PeriodicalCreate(Periodical periodical, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, periodical.getName());
        preparedStatement.setString(2, periodical.getAbout());
        preparedStatement.setLong(3, periodical.getPublisher().getId());
        preparedStatement.setLong(4, periodical.getPeriodicalCategory().getId());
        preparedStatement.setInt(5, periodical.getPeriodicityInSixMonth());
        preparedStatement.setInt(6, periodical.getMinSubscriptionPeriod());
        preparedStatement.setFloat(7, periodical.getCostPerMonth());
        preparedStatement.setBoolean(8, periodical.isActive());
        preparedStatement.setString(9, periodical.getImageLink());
    }

    @Override
    public Periodical selectPeriodicalById(Long id) {
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
                PeriodicalInit(periodical, rs);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return periodical;
    }

    private void PeriodicalInit(Periodical periodical, ResultSet rs) throws SQLException {
        periodical.setName(rs.getString("name"));
        periodical.setAbout(rs.getString("about"));
        periodical.setPublisher(publisherDao.select(rs.getLong("publisher_fk")));
        periodical.setPeriodicalCategory(periodicalCategoryDao.select(rs.getLong("periodical_category")));
        periodical.setPeriodicityInSixMonth(rs.getInt("periodicity_in_six_month"));
        periodical.setMinSubscriptionPeriod(rs.getInt("min_subscription_period"));
        periodical.setCostPerMonth(rs.getBigDecimal("cost_per_month").floatValue());
        periodical.setActive(rs.getBoolean("active"));
        periodical.setImageLink(rs.getString("imageLink"));
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
                PeriodicalInit(periodical, rs);
                periodicals.add(periodical);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return periodicals;
    }

    @Override
    public boolean deletePeriodicalById(Long id) {
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
    public boolean updatePeriodical(Periodical periodical) {
        LOGGER.info("UPDATE PERIODICAL {}", periodical.toString());
        boolean rowUpdated = false;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_PERIODICAL_SQL)) {
            PeriodicalCreate(periodical, statement);
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

    //FOR TESTS ONLY!
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

    @Override
    public List<Periodical> selectLastPeriodicals(Integer limit) {
        LOGGER.info("SELECT " + limit + " LAST PERIODICALS");
        List<Periodical> periodicals = new CopyOnWriteArrayList();

        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(FIND_LAST_PERIODICALS);
            StatementSet(limit, periodicals, statement);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return periodicals;
    }

    private void StatementSet(Integer limit, List<Periodical> periodicals, PreparedStatement statement) throws SQLException {
        statement.setInt(1, limit);

        ResultSet result = statement.executeQuery();
        while (result.next()) {
            Periodical periodical = new Periodical();
            periodical.setId(result.getLong("id"));
            PeriodicalInit(periodical, result);
            periodicals.add(periodical);
        }
    }

    @Override
    public List<Periodical> selectPageByCategory(Long categoryId, Integer offset, Integer size) {
        LOGGER.info("GET PAGE WITH OFFSET {} SIZE {} CATEGORY ID  {}  ", offset, size, categoryId);
        List<Periodical> periodicals = new CopyOnWriteArrayList<>();

        return getPeriodicals(categoryId, size, offset, periodicals, SELECT_PAGE_BY_CATEGORY_QUERY);
    }

    @Override
    public List<Periodical> selectPageByPublisher(Long publisherId, Integer offset, Integer size) {
        LOGGER.info("GET PAGE WITH OFFSET {} SIZE {} PUBLISHER ID  {}  ", offset, size, publisherId);
        List<Periodical> periodicals = new CopyOnWriteArrayList();
        return getPeriodicals(publisherId, size, offset, periodicals, SELECT_PAGE_BY_PUBLISHER_ID);

    }

    private List<Periodical> getPeriodicals(Long publisherId, Integer offset, Integer size, List<Periodical> periodicals, String selectPageByPublisherId) {
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(selectPageByPublisherId);
            statement.setLong(1, publisherId);
            StatementSet(size, offset, periodicals, statement);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return periodicals;
    }

    private void StatementSet(Integer offset, Integer size, List<Periodical> periodicals, PreparedStatement statement) throws SQLException {
        statement.setInt(2, offset);
        statement.setInt(3, size);
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            Periodical periodical = new Periodical();
            periodical.setId(result.getLong("id"));
            PeriodicalInit(periodical, result);
            periodicals.add(periodical);
        }
    }

    @Override
    public List<Periodical> selectPage(Integer offset, Integer size) {
        LOGGER.info("GET PAGE WITH OFFSET {} size {} ", offset, size);
        List<Periodical> periodicals = new CopyOnWriteArrayList<>();

        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SELECT_PAGE);
            statement.setInt(2, offset);
            StatementSet(size, periodicals, statement);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }

        return periodicals;
    }

    @Override
    public List<Periodical> selectPageByNameQuery(String query, Integer offset, Integer size) {
        LOGGER.info("GET PAGE BY {} QUERY WITH OFFSET {} size {} ", query, offset, size);

        List<Periodical> periodicals = new CopyOnWriteArrayList();

        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SELECT_PAGE_BY_NAME);
            statement.setString(1, "%" + query + "%");
            StatementSet(size, offset, periodicals, statement);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }

        return periodicals;
    }

    private static class PeriodicalDaoImplHolder {
        private static final PeriodicalDaoImpl HOLDER_INSTANCE = new PeriodicalDaoImpl();
    }
}
