package com.beauty.api.serviceImple;

import com.beauty.api.collection.User;
import com.beauty.api.repository.UserRepository;
import com.beauty.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public String save(User user) {
        return userRepository.save(user).getUserName();
    }

    @Override
    public List<User> getPersonStartWith(String name) {
        return userRepository.findByFirstNameStartsWith(name);
    }
}
