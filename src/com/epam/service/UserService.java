/*
 * @Denisenko Artur
 */

package com.epam.service;

import com.epam.model.user.User;
import com.epam.model.user.UserType;

import java.util.List;

public interface UserService {

    boolean create(User user);

    User getById(Long id);

    List<User> getByName(String name);

    User getByEmail(String email);

    List<User> getAllByUsersType(UserType userType);

    List<User> getAll();

    boolean delete(Long id);

    boolean checkEmailAvailability(String email);

    boolean update(User user);
}
