package com.beauty.api.serviceImple;

import com.beauty.api.collection.User;
import com.beauty.api.repository.UserRepository;
import com.beauty.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user).get_id();
    }

    @Override
    public boolean delete(String userId) {
        if (!userRepository.existsById(userId)) {
            return false;
        }

        userRepository.deleteById(userId);
        return true;
    }

    @Override
    public User update(User user, String userId) {
        User existingUserData = userRepository.findById(userId).orElse(null);

        if (existingUserData != null) {
            existingUserData.setName(user.getName());
            existingUserData.setUsername(user.getUsername());
            existingUserData.setRole(user.getRole());
            existingUserData.setPhone(user.getPhone());

            return userRepository.save(existingUserData);
        } else {
            return null;
        }
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(String userId) {
        return userRepository.findById(userId);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User "+username+" not found."));
    }

    @Override
    public String getUserIdByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(User::get_id)
                .orElse(null);
    }

}
