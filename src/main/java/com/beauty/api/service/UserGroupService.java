package com.beauty.api.service;

import com.beauty.api.collection.UserGroup;

import java.util.List;
import java.util.Optional;

public interface UserGroupService {
    UserGroup save(UserGroup userGroup);

    List<UserGroup> getAllUserGroups();

    Optional<UserGroup> getUserGroupById(String userGroupId);

    boolean delete(String userGroupId);

    UserGroup update(UserGroup userGroup, String userGroupId);
}
