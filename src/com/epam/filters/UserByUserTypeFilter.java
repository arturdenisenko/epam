/*
 * @Denisenko Artur
 */

package com.epam.filters;

import com.epam.model.user.User;
import com.epam.model.user.UserType;

import java.util.ArrayList;
import java.util.List;

public class UserByUserTypeFilter implements ModelFilter<User, UserType> {
    @Override
    public List<User> meetCriteria(List<User> allUsersFromDatabase, UserType userType) {
        List<User> users = new ArrayList<>();
        for (User element : allUsersFromDatabase) {
            if (element.getUserType() == userType) {
                users.add(element);
            }
        }
        return users;
    }
}
