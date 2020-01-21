/*
 * @Denisenko Artur
 */

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
import com.epam.util.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        LOGGER.info("USER SERVICE INIT");

        this.userDao = userDao;
    }

    @Override
    public boolean checkEmailAvailability(String email) {
        LOGGER.info("Checking availability of email");

        if (email == null) {
            return false;
        }

        User user = userDao.getByEmail(email);
        return user == null;
    }

    @Override
    public boolean create(User user) {
        LOGGER.info("New user registration");

        return user != null && userDao.createUser(user).getId() != null;

    }

    @Override
    public User getById(Long id) {
        LOGGER.info("Finding user by id " + id);

        if (id == null) {
            return null;
        }

        return userDao.getUserById(id);
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

        return userDao.getByEmail(email);
    }

    @Override
    public Page<User> getAllByUsersType(Integer page, Integer size, UserType userType) {
        LOGGER.info("Getting page number {}, of size {} , for user type {}", page, size, userType.name());

        if (page == null || size == null || page < 1 || size < 1) {
            return null;
        }
        List<User> items = userDao.findPageByUserType(userType, (page - 1) * size, size);
        return new Page(items, page, size);
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
