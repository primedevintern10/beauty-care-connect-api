package com.beauty.api.controller;

import com.beauty.api.collection.User;
import com.beauty.api.collection.PasswordResetToken;
import com.beauty.api.models.ForgotPasswordRequest;
import com.beauty.api.models.JwtRequest;
import com.beauty.api.models.JwtResponse;
import com.beauty.api.models.ResetPasswordRequest;
import com.beauty.api.repository.PasswordResetTokenRepository;
import com.beauty.api.repository.UserRepository;
import com.beauty.api.security.JwtHelper;
import com.beauty.api.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication")
public class AuthController {
    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private JwtHelper helper;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Operation(summary = "Login/Sign In")
    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {
        this.doAuthenticate(request.getUsername(), request.getPassword());

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        String userId = userService.getUserIdByUsername(request.getUsername());
        String token = this.helper.generateToken(userDetails);

        // Fetch full user object to include role in response
        User user = userRepository.findByUsername(request.getUsername()).orElse(null);
        String userRole = (user != null) ? user.getRole() : "";

        JwtResponse response = JwtResponse.builder()
                .jwtToken(token)
                .username(userDetails.getUsername())
                ._id(userId)
                .role(userRole)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void doAuthenticate(String username, String password) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, password);

        try {
            manager.authenticate(authentication);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Invalid Username or Password.");
        }
    }

    @Operation(summary = "Register/Sign Up")
    @PostMapping("/register")
    public String createUser(@RequestBody User user){
        return userService.save(user);
    }

    @Operation(summary = "Request Password Reset")
    @PostMapping("/forgot-password")
    public ResponseEntity<Map<String, String>> forgotPassword(@RequestBody ForgotPasswordRequest request) {
        Map<String, String> response = new HashMap<>();

        if (request == null || request.getEmail() == null || request.getEmail().isBlank()) {
            response.put("message", "Email is required.");
            return ResponseEntity.badRequest().body(response);
        }

        Optional<User> userOptional = userRepository.findByEmail(request.getEmail());

        // Keep response generic to avoid account enumeration.
        if (userOptional.isEmpty()) {
            response.put("message", "If an account with this email exists, a reset token has been generated.");
            return ResponseEntity.ok(response);
        }

        User user = userOptional.get();
        String token = UUID.randomUUID().toString().replace("-", "");
        Date now = new Date();
        Date expiry = new Date(now.getTime() + (15 * 60 * 1000));

        passwordResetTokenRepository.deleteByEmail(user.getEmail());
        PasswordResetToken resetToken = PasswordResetToken.builder()
                .email(user.getEmail())
                .username(user.getUsername())
                .token(token)
                .createdAt(now)
                .expiresAt(expiry)
                .used(false)
                .build();
        passwordResetTokenRepository.save(resetToken);

        logger.info("Password reset token generated for {}: {}", user.getEmail(), token);

        response.put("message", "Reset token generated. Use this token on reset page.");
        response.put("token", token);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Reset Password")
    @PostMapping("/reset-password")
    public ResponseEntity<Map<String, String>> resetPassword(@RequestBody ResetPasswordRequest request) {
        Map<String, String> response = new HashMap<>();

        if (request == null || request.getToken() == null || request.getToken().isBlank()) {
            response.put("message", "Reset token is required.");
            return ResponseEntity.badRequest().body(response);
        }

        if (request.getPassword() == null || request.getPassword().isBlank()) {
            response.put("message", "New password is required.");
            return ResponseEntity.badRequest().body(response);
        }

        if (!request.getPassword().equals(request.getConfirmPassword())) {
            response.put("message", "Password and confirmation do not match.");
            return ResponseEntity.badRequest().body(response);
        }

        Optional<PasswordResetToken> tokenOptional = passwordResetTokenRepository.findByToken(request.getToken());
        if (tokenOptional.isEmpty()) {
            response.put("message", "Invalid reset token.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        PasswordResetToken tokenEntity = tokenOptional.get();
        if (Boolean.TRUE.equals(tokenEntity.getUsed())) {
            response.put("message", "This reset token has already been used.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        if (tokenEntity.getExpiresAt() == null || tokenEntity.getExpiresAt().before(new Date())) {
            response.put("message", "Reset token has expired.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        Optional<User> userOptional = userRepository.findByEmail(tokenEntity.getEmail());
        if (userOptional.isEmpty()) {
            response.put("message", "User account not found for this token.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        User user = userOptional.get();
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setUpdated_at(new Date());
        userRepository.save(user);

        tokenEntity.setUsed(true);
        passwordResetTokenRepository.save(tokenEntity);

        response.put("message", "Password reset successful.");
        return ResponseEntity.ok(response);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<String> handleBadCredentialsException(BadCredentialsException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Credentials.");
    }
}
