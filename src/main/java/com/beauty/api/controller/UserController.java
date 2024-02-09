package com.beauty.api.controller;

import com.beauty.api.collection.User;
import com.beauty.api.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @Operation(summary = "Get User by ID")
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") String userId) {
        Optional<User> user = userService.getUserById(userId);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Get Current User")
    @GetMapping("/current-user")
    public ResponseEntity<String> getLoggedInUser(Principal principal) {
        return ResponseEntity.ok(principal.getName());
    }

    @Operation(summary = "Update User")
    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable("id") String userId, @RequestBody User user) {
        User updatedUser = userService.update(user, userId);
        return (updatedUser != null) ?
                ResponseEntity.ok(updatedUser) :
                ResponseEntity.notFound().build();
    }

    @Operation(summary = "Remove User")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") String userId) {
        boolean isDeleted = userService.delete(userId);
        if (isDeleted) {
            return ResponseEntity.ok("Deleted Successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
