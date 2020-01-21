/*
 * @Denisenko Artur
 */

/*
 * @Denisenko Artur
 */

package com.epam.service;

import com.epam.model.user.User;
import com.epam.model.user.UserType;
import com.epam.util.Page;

import java.util.List;

public interface UserService {

    boolean create(User user);

    User getById(Long id);

    List<User> getByName(String name);

    User getByEmail(String email);

    Page<User> getAllByUsersType(Integer page, Integer size, UserType userTyp);

    List<User> getAll();

    boolean delete(Long id);

    boolean checkEmailAvailability(String email);

    boolean update(User user);
}
