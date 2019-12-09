package com.epam.dao;

import com.epam.exception.DAOException;
import com.epam.model.user.User;

import java.util.List;

public interface UserDAO {

    List<User> findAllUsers() throws DAOException;

    User findEntityByLogin(String login) throws DAOException;

    double findBalanceById(Long id) throws DAOException;

    String findPassById(Long id) throws DAOException;

    boolean updatePassById(Long id, String pass) throws DAOException;

    boolean updateUserBalanceAdd(Long id, double money) throws DAOException;

    boolean updateUserBalance(Long id, double newBalance) throws DAOException;
}
