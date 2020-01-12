/*
 * @Denisenko Artur
 */

package com.epam.dao;

import com.epam.model.user.User;
import com.epam.model.user.UserType;

import java.util.List;

public interface UserDao {

    User insert(User user);

    User select(Long id);

    List<User> selectByName(String name);

    User selectByEmail(String email);

    List<User> selectByUsersType(UserType userType);

    List<User> selectAll();

    boolean delete(Long id);

    boolean update(User user);
    //for test only
    void clear();
}
