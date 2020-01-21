/*
 * @Denisenko Artur
 */

/*
 * @Denisenko Artur
 */

package com.epam.dao;

import com.epam.model.user.User;
import com.epam.model.user.UserType;

import java.util.List;

public interface UserDao {

    User createUser(User user);

    User getUserById(Long id);

    User getByEmail(String email);

    List<User> getUserByName(String name);

    List<User> getAll();

    boolean deleteUser(Long id);

    boolean updateUser(User user);

    /**
     * For tests only drop all users from database
     */
    void clear();

    List<User> findPageByUserType(UserType userType, Integer offset, Integer size);
}
