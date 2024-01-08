package com.beauty.api.serviceImple;

import com.beauty.api.collection.UserGroup;
import com.beauty.api.repository.UserGroupRepository;
import com.beauty.api.service.UserGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserGroupServiceImpl implements UserGroupService{
    @Autowired
    private UserGroupRepository userGroupRepository;

    @Override
    public String save(UserGroup userGroup) {
        return userGroupRepository.save(userGroup).getName();
    }

    @Override
    public List<UserGroup> getAllUserGroups() {
        return userGroupRepository.findAll();
    }

    @Override
    public Optional<UserGroup> getUserGroupById(String userGroupId) {
        return userGroupRepository.findById(userGroupId);
    }

    @Override
    public void delete(String userGroupId) {
        userGroupRepository.deleteById(userGroupId);
    }

    @Override
    public UserGroup update(UserGroup userGroup, String userGroupId) {
        UserGroup existingUserGroupData = userGroupRepository.findById(userGroupId).orElse(null);

        if (existingUserGroupData != null) {
            existingUserGroupData.setName(userGroup.getName());
            existingUserGroupData.setPermission(userGroup.getPermission());

            return userGroupRepository.save(existingUserGroupData);
        } else {
            return null;
        }
    }
}
