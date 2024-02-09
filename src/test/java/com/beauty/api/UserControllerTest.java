package com.beauty.api;

import com.beauty.api.collection.User;
import com.beauty.api.controller.UserController;
import com.beauty.api.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllUsers() {
        User user1 = User.builder()._id("1").username("John").build();
        User user2 = User.builder()._id("2").username("Doe").build();
        List<User> users = Arrays.asList(user1, user2);

        when(userService.getAllUsers()).thenReturn(users);

        ResponseEntity<List<User>> response = userController.getAllUsers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(users, response.getBody());
    }

    @Test
    void testGetUserById() {
        String userId = "1";
        User user = User.builder()._id(userId).username("John").build();

        when(userService.getUserById(userId)).thenReturn(Optional.of(user));

        ResponseEntity<User> response = userController.getUserById(userId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    void testGetUserByIdNotFound() {
        String userId = "1";

        when(userService.getUserById(userId)).thenReturn(Optional.empty());

        ResponseEntity<User> response = userController.getUserById(userId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testGetLoggedInUser() {
        Principal principal = () -> "testUser";

        ResponseEntity<String> response = userController.getLoggedInUser(principal);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("testUser", response.getBody());
    }

}

