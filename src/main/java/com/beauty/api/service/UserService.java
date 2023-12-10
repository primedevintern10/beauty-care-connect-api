package com.beauty.api.service;

import com.beauty.api.collection.User;

import java.util.List;

public interface UserService {
    String save(User user);

    List<User> getPersonStartWith(String name);
}
