package com.beauty.api.controller;

import com.beauty.api.collection.UserGroup;
import com.beauty.api.service.UserGroupService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/userGroup")
@Tag(name = "User Groups")
public class UserGroupController {
    @Autowired
    private UserGroupService userGroupService;

    @Operation(
            description = "Create an User Group",
            summary = "Create an User Group"
    )
    @PostMapping
    public String save(@RequestBody UserGroup userGroup) {
        return userGroupService.save(userGroup);
    }

    @Operation(
            description = "Get All User Groups",
            summary = "Get All User Groups"
    )
    @GetMapping
    public List<UserGroup> getAllUserGroups() {
        return userGroupService.getAllUserGroups();
    }

    @Operation(
            description = "Get User Group by Providing User Group ID as Parameter",
            summary = "Get User Group by ID"
    )
    @GetMapping("/{id}")
    public Optional<UserGroup> getUserGroupById(@PathVariable("id") String userGroupId) {
        return userGroupService.getUserGroupById(userGroupId);
    }

    @Operation(
            description = "Update an User Group by Providing User Group ID as a Parameter and rest of the fields as Body Parameters",
            summary = "Update an User Group"
    )
    @PutMapping("/{id}")
    public UserGroup update(@PathVariable("id") String userGroupId, @RequestBody UserGroup userGroup) {
        return userGroupService.update(userGroup, userGroupId);
    }

    @Operation(
            description = "Remove User Group by Providing User Group ID as a Parameter",
            summary = "Remove an User Group"
    )
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") String userGroupId) {
        userGroupService.delete(userGroupId);
        return "Deleted Successfully";
    }
}
