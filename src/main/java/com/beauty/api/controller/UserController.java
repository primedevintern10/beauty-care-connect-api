package com.beauty.api.controller;

import com.beauty.api.collection.User;
import com.beauty.api.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@Tag(name = "Users")
public class UserController {
    @Autowired
    private UserService userService;

    @Operation(summary = "Get All Users")
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @Operation(summary = "Get User by ID")
    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable("id") String userId) {
        return userService.getUserById(userId);
    }

    @Operation(summary = "Get Current User")
    @GetMapping("/current-user")
    public String getLoggedInUser(Principal principal) {
        return principal.getName();
    }

    @Operation(summary = "Update User")
    @PutMapping("/{id}")
    public User update(@PathVariable("id") String userId, @RequestBody User user) {
        return userService.update(user, userId);
    }

    @Operation(summary = "Remove User")
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") String userId) {
        userService.delete(userId);
        return "Deleted Successfully";
    }
}