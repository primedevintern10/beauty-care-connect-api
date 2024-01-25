package com.beauty.api.service;

import com.beauty.api.collection.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    String save(User user);

    List<User> getAllUsers();

    Optional<User> getUserById(String userId);

    String getUserIdByUsername(String username);

    void delete(String userId);

    User update(User user, String userId);
}
