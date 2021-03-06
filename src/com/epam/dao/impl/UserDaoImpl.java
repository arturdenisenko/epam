/*
 * @Denisenko Artur
 */

/*
 * @Denisenko Artur
 */

/*
 * @Denisenko Artur
 */

/*
 * @Denisenko Artur
 */

package com.epam.dao.impl;

import com.epam.dao.UserDao;
import com.epam.exception.NotExistEntityException;
import com.epam.filters.ModelFilter;
import com.epam.filters.UserByFirstNameLastNameFilter;
import com.epam.model.user.User;
import com.epam.model.user.UserType;
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

public class UserDaoImpl implements UserDao {

    //TODO REFACT THIS DAO!!!, AND ADD PWD CRYPT

    private final static Logger LOGGER = LoggerFactory.getLogger(UserDaoImpl.class);

    private static class UserDAOImplHolder {
        private static final UserDaoImpl HOLDER_INSTANCE = new UserDaoImpl();
    }

    public static UserDaoImpl getInstance() {
        return UserDAOImplHolder.HOLDER_INSTANCE;
    }

    @Override
    public User createUser(User user) {
        LOGGER.info("CREATE NEW USER {}", user.toString());
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_SQL)) {
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getAddress());
            preparedStatement.setString(6, String.valueOf(user.getUserType()));
            preparedStatement.setFloat(7, user.getBalance());
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                LOGGER.info("USER CREATION FAILED");
                return null;
            } else {
                LOGGER.info("USER CREATION SUCCESSFUL");
            }
        } catch (SQLException e) {
            throw ExceptionUtil.convertException(e);
        }
        return user;
    }

    @Override
    public User getUserById(Long id) {
        LOGGER.info("SELECT USER WITH ID = {}", id);
        User user = null;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (!rs.next()) {
                throw new NotExistEntityException(id);
            } else {
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String password = rs.getString("password");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String userType = rs.getString("user_type");
                Float balance = rs.getFloat("balance");
                user = new User(id, firstName, lastName, email, password, UserType.valueOf(userType), address, balance);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return user;
    }

    @Override
    public User getByEmail(String email) {
        LOGGER.info("SELECT USER BY EMAIL {}", email);
        User user = null;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_EMAIL)) {
            preparedStatement.setString(1, email);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Long id = rs.getLong("id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String password = rs.getString("password");
                String address = rs.getString("address");
                String userType = rs.getString("user_type");
                Float balance = rs.getFloat("balance");
                user = new User(id, firstName, lastName, email, password, UserType.valueOf(userType), address, balance);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return user;
    }


    @Override
    public List<User> getUserByName(String name) {
        LOGGER.info("SELECT USER BY NAME {}", name);
        ModelFilter filter = new UserByFirstNameLastNameFilter();
        return filter.meetCriteria(getAll(), name);
    }

    @Override
    public List<User> getAll() {
        LOGGER.info("SELECT ALL USERS");
        List<User> users = new CopyOnWriteArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Long id = rs.getLong("id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String password = rs.getString("password");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String userType = rs.getString("user_type");
                Float balance = rs.getFloat("balance");
                users.add(new User(id, firstName, lastName, email, password, UserType.valueOf(userType), address, balance));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return users;
    }

    @Override
    public boolean updateUser(User user) {
        LOGGER.info("UPDATE USER {}", user.toString());
        boolean rowUpdated = false;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_USER_SQL)) {
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getAddress());
            statement.setString(6, user.getUserType().toString());
            statement.setFloat(7, user.getBalance());
            statement.setLong(8, user.getId());
            rowUpdated = statement.executeUpdate() > 0;
            if (!rowUpdated) {
                throw new NotExistEntityException(user.getId());
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return rowUpdated;
    }

    @Override
    public boolean deleteUser(Long id) {
        LOGGER.info("DELETE USER WITH ID = {} ", id);
        boolean rowDeleted = false;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_USER_SQL)) {
            statement.setLong(1, id);
            rowDeleted = statement.executeUpdate() > 0;
            if (!rowDeleted) {
                LOGGER.warn("USER WITH ID {} ISN'T DELETED", id);
                throw new NotExistEntityException(id);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return false;
    }

    @Override
    public void clear() {
        LOGGER.info("DELETE ALL USERS");
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(CLEAR_TABLE_USERS_SQL)) {
            statement.execute();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    public List<User> findPageByUserType(UserType userType, Integer offset, Integer size) {
        LOGGER.info("Getting page with offset {}, size {} of userType {}", offset, size, userType.name());
        List<User> users = new CopyOnWriteArrayList();

        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_BY_USER_TYPE);
            statement.setString(1, userType.toString());
            statement.setInt(2, size);
            statement.setInt(3, offset);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Long id = rs.getLong("id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String password = rs.getString("password");
                String email = rs.getString("email");
                String address = rs.getString("address");
                Float balance = rs.getFloat("balance");
                users.add(new User(id, firstName, lastName, email, password, userType, address, balance));
            }

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }

        return users;
    }
}
