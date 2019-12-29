package com.epam.filters;

import com.epam.model.user.User;

import java.util.ArrayList;
import java.util.List;

public class UserByFirstNameLastNameFilter implements Filter<User, String> {
    @Override
    public List<User> meetCriteria(List<User> allUsersFromDatabase, String name) {
        List<User> users = new ArrayList<>();
        for (User user : allUsersFromDatabase) {
            if (user.getFirstName().contains(name) || user.getLastName().contains(name)) {
                users.add(user);
            }
        }
        return users;
    }
}
