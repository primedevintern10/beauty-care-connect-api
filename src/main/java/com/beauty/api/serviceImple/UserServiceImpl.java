package com.beauty.api.serviceImple;

import com.beauty.api.collection.User;
import com.beauty.api.repository.UserRepository;
import com.beauty.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public String save(User user) {
        return userRepository.save(user).getUserName();
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
    public void delete(String userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public User update(User user, String userId) {
        User existingUserData = userRepository.findById(userId).get();

        existingUserData.setUserName(user.getUserName());
        existingUserData.setNicPassport(user.getNicPassport());
        existingUserData.setFirstName(user.getFirstName());
        existingUserData.setLastName(user.getLastName());
        existingUserData.setEmail(user.getEmail());
        existingUserData.setContactNo(user.getContactNo());
        existingUserData.setUserGroup(user.getUserGroup());

        User updatedUserData = userRepository.save(existingUserData);

        return userRepository.save(updatedUserData);
    }

}
