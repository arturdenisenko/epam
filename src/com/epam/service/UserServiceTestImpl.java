package com.epam.service;

import com.epam.model.user.User;

import java.util.ArrayList;
import java.util.List;

public class UserServiceTestImpl implements UserService {
    @Override
    public List<User> finaAll() {
        List<User> userList = new ArrayList<>();
        User user = new User();
        User user1 = new User();
        User user2 = new User();
        User user3 = new User();
        User user4 = new User();
        user.setFullName("Сережка");
        user1.setFullName("Петя");
        user2.setFullName("Анатолий");
        user3.setFullName("Володя");
        user4.setFullName("Геннадий");
        return userList;
    }
}
