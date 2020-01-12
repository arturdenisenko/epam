/*
 * @Denisenko Artur
 */

/*
 * @Denisenko Artur
 */

package com.epam.service.impl;

import com.epam.dao.UserDao;
import com.epam.model.user.User;
import com.epam.model.user.UserType;
import com.epam.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        LOGGER.info("Initializing UserServiceImpl");

        this.userDao = userDao;
    }

    @Override
    public boolean checkEmailAvailability(String email) {
        LOGGER.info("Checking availability of email");

        if (email == null) {
            return false;
        }

        User user = userDao.selectByEmail(email);
        return user == null;
    }

    @Override
    public boolean create(User user) {
        LOGGER.info("New user registration");

        return user != null && userDao.insert(user).getId() != null;

    }

    @Override
    public User getById(Long id) {
        LOGGER.info("Finding user by id " + id);

        if (id == null) {
            return null;
        }

        return userDao.select(id);
    }

    @Override
    public List<User> getByName(String name) {
        return null;
    }

    @Override
    public User getByEmail(String email) {
        LOGGER.info("Finding user by email " + email);

        if (email == null) {
            return null;
        }

        return userDao.selectByEmail(email);
    }

    @Override
    public List<User> getAllByUsersType(UserType userType) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public boolean update(User user) {
        return false;
    }
}
