package com.beauty.api.controller;

import com.beauty.api.collection.UserGroup;
import com.beauty.api.service.UserGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/userGroup")
public class UserGroupController {
    @Autowired
    private UserGroupService userGroupService;

    @PostMapping
    public String save(@RequestBody UserGroup userGroup) {
        return userGroupService.save(userGroup);
    }

    @GetMapping
    public List<UserGroup> getAllUserGroups() {
        return userGroupService.getAllUserGroups();
    }

    @GetMapping("/{id}")
    public Optional<UserGroup> getUserGroupById(@PathVariable("id") String userGroupId) {
        return userGroupService.getUserGroupById(userGroupId);
    }

    @PutMapping("/{id}")
    public UserGroup update(@PathVariable("id") String userGroupId, @RequestBody UserGroup userGroup) {
        return userGroupService.update(userGroup, userGroupId);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") String userGroupId) {
        userGroupService.delete(userGroupId);
        return "Deleted Successfully";
    }
}
