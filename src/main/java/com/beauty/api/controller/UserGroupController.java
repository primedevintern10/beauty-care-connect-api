package com.beauty.api.controller;

import com.beauty.api.collection.UserGroup;
import com.beauty.api.service.UserGroupService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/userGroup")
@Tag(name = "User Groups")
public class UserGroupController {

    @Autowired
    private UserGroupService userGroupService;

    @Operation(summary = "Create a User Group")
    @PostMapping
    public ResponseEntity<UserGroup> save(@RequestBody UserGroup userGroup) {
        UserGroup savedUserGroup = userGroupService.save(userGroup);
        return new ResponseEntity<>(savedUserGroup, HttpStatus.CREATED);
    }

    @Operation(summary = "Get All User Groups")
    @GetMapping
    public ResponseEntity<List<UserGroup>> getAllUserGroups() {
        List<UserGroup> userGroups = userGroupService.getAllUserGroups();
        return ResponseEntity.ok(userGroups);
    }

    @Operation(summary = "Get User Group by ID")
    @GetMapping("/{id}")
    public ResponseEntity<UserGroup> getUserGroupById(@PathVariable("id") String userGroupId) {
        Optional<UserGroup> userGroup = userGroupService.getUserGroupById(userGroupId);
        return userGroup.map(value -> ResponseEntity.ok(value))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Update a User Group")
    @PutMapping("/{id}")
    public ResponseEntity<UserGroup> update(@PathVariable("id") String userGroupId, @RequestBody UserGroup userGroup) {
        UserGroup updatedUserGroup = userGroupService.update(userGroup, userGroupId);
        return (updatedUserGroup != null) ?
                ResponseEntity.ok(updatedUserGroup) :
                ResponseEntity.notFound().build();
    }

    @Operation(summary = "Remove a User Group")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") String userGroupId) {
        boolean isDeleted = userGroupService.delete(userGroupId);
        if (isDeleted) {
            return ResponseEntity.ok("User Group deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Group not found");
        }
    }
}
