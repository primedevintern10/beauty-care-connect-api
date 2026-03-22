package com.beauty.api;

import com.beauty.api.collection.User;
import com.beauty.api.controller.AuthController;
import com.beauty.api.models.JwtRequest;
import com.beauty.api.models.JwtResponse;
import com.beauty.api.security.JwtHelper;
import com.beauty.api.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class AuthControllerTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtHelper jwtHelper;

    @Mock
    private UserDetailsService userDetailsService;

    @Mock
    private UserService userService;

    @InjectMocks
    private AuthController authController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testLogin_ValidCredentials() {
        JwtRequest jwtRequest = new JwtRequest("username", "password");
        UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
                .username("username")
                .password("password")
                .build();
        when(userDetailsService.loadUserByUsername(anyString())).thenReturn(userDetails);
        when(userService.getUserIdByUsername(anyString())).thenReturn("userId");
        when(jwtHelper.generateToken(userDetails)).thenReturn("token");
    ResponseEntity<?> responseEntity = authController.login(jwtRequest);

    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    JwtResponse body = (JwtResponse) responseEntity.getBody();
    assertEquals("username", body.getUsername());
    assertEquals("userId", body.get_id());
    assertEquals("token", body.getJwtToken());
    }

}
