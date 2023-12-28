package com.beauty.api.serviceImple;

import com.beauty.api.collection.User;
import com.beauty.api.collection.UserGroup;
import com.beauty.api.repository.UserGroupRepository;
import com.beauty.api.repository.UserRepository;
import com.beauty.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserGroupRepository userGroupRepository;

//    private final BCryptPasswordEncoder passwordEncoder;
//
//    public UserServiceImpl(BCryptPasswordEncoder passwordEncoder) {
//        this.passwordEncoder = passwordEncoder;
//    }

    @Override
    public String save(User user) {
//        String encryptedPassword = passwordEncoder.encode(user.getPassword());
//        user.setPassword(encryptedPassword);

        return userRepository.save(user).getUserName();
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();

        users.forEach(user -> {
            UserGroup userGroup = userGroupRepository.findById(user.getUserGroup().get_id()).orElse(null);
            user.setUserGroup(userGroup);
        });

        return users;
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
//        User existingUserData = userRepository.findById(userId).get();
//
//        existingUserData.setUserName(user.getUserName());
//        existingUserData.setNicPassport(user.getNicPassport());
//        existingUserData.setFirstName(user.getFirstName());
//        existingUserData.setLastName(user.getLastName());
//        existingUserData.setEmail(user.getEmail());
//        existingUserData.setContactNo(user.getContactNo());
//        existingUserData.setUserGroup(user.getUserGroup());
//
//        User updatedUserData = userRepository.save(existingUserData);
//
//        return userRepository.save(updatedUserData);

//        Optional<User> optionalExistingUserData = userRepository.findById(userId);
//
//        if (optionalExistingUserData.isPresent()) {
//            User existingUserData = optionalExistingUserData.get();
//
//            if (user.getUserName() != null) {
//                existingUserData.setUserName(user.getUserName());
//            }
//
//            if (user.getNicPassport() != null) {
//                existingUserData.setNicPassport(user.getNicPassport());
//            }
//
//            if (user.getFirstName() != null) {
//                existingUserData.setFirstName(user.getFirstName());
//            }
//
//            if (user.getLastName() != null) {
//                existingUserData.setLastName(user.getLastName());
//            }
//
//            if (user.getEmail() != null) {
//                existingUserData.setEmail(user.getEmail());
//            }
//
//            if (user.getContactNo() != null) {
//                existingUserData.setContactNo(user.getContactNo());
//            }
//
//            if (user.getUserGroup() != null) {
//                existingUserData.setUserGroup(user.getUserGroup());
//            }
//
//            User updatedUserData = userRepository.save(existingUserData);
//
//            return updatedUserData;
//        } else {
//            return null;
//        }

        Optional<User> optionalExistingUserData = userRepository.findById(userId);

        if (optionalExistingUserData.isPresent()) {
            User existingUserData = optionalExistingUserData.get();

            if (user.getUserName() != null) {
                existingUserData.setUserName(user.getUserName());
            }

            if (user.getNicPassport() != null) {
                existingUserData.setNicPassport(user.getNicPassport());
            }

            if (user.getFirstName() != null) {
                existingUserData.setFirstName(user.getFirstName());
            }

            if (user.getLastName() != null) {
                existingUserData.setLastName(user.getLastName());
            }

            if (user.getEmail() != null) {
                existingUserData.setEmail(user.getEmail());
            }

            if (user.getContactNo() != null) {
                existingUserData.setContactNo(user.getContactNo());
            }

            // Update the embedded UserGroup
            if (user.getUserGroup() != null) {
                UserGroup newUserGroup = user.getUserGroup();

                UserGroup existingUserGroupData = userGroupRepository.findById(newUserGroup.get_id()).orElse(null);

                if (existingUserGroupData != null) {
                    existingUserData.setUserGroup(UserGroup.builder()
                            ._id(newUserGroup.get_id())
                            .name(newUserGroup.getName())
                            .permission(newUserGroup.getPermission())
                            .build());
                } else {
                    return null;
                }
            }

            User updatedUserData = userRepository.save(existingUserData);
            return updatedUserData;
        } else {
            return null;
        }
    }

}
