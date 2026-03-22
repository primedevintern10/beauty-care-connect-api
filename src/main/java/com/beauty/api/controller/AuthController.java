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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private JwtHelper helper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Operation(summary = "Login/Sign In")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody JwtRequest request) {
                // DEBUG LOGGING START
                String dbName = "<unknown>";
                String collectionName = "<unknown>";
                try {
                    org.springframework.data.mongodb.core.MongoTemplate mongoTemplate = (org.springframework.data.mongodb.core.MongoTemplate) org.springframework.beans.factory.BeanFactoryUtils.beanOfTypeIncludingAncestors(
                        org.springframework.web.context.ContextLoader.getCurrentWebApplicationContext(),
                        org.springframework.data.mongodb.core.MongoTemplate.class
                    );
                    dbName = mongoTemplate.getDb().getName();
                    collectionName = mongoTemplate.getCollectionName(com.beauty.api.collection.User.class);
                } catch (Exception e) {
                    System.out.println("[DEBUG] Could not fetch MongoDB info: " + e.getMessage());
                }
                System.out.println("[DEBUG] Database: " + dbName);
                System.out.println("[DEBUG] Collection: " + collectionName);
                // DEBUG LOGGING END
        if (request.getUsername() == null || request.getPassword() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of("message", "Username and password are required"));
        }


        try {
                Optional<User> userOpt = userRepository.findByUsername(request.getUsername());
                System.out.println("[DEBUG] Query username: " + request.getUsername());
                if (userOpt.isEmpty()) {
                System.out.println("[DEBUG] No user found for username: " + request.getUsername());
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "User not found"));
                }

                User user = userOpt.get();
                System.out.println("[DEBUG] User document: " + user);
                System.out.println("[DEBUG] Entered password: '" + request.getPassword() + "'");
                System.out.println("[DEBUG] DB password:     '" + user.getPassword() + "'");
                boolean match = user.getPassword().equals(request.getPassword());
                System.out.println("[DEBUG] Password match: " + match);

                if (!match) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "Password incorrect"));
                }

                String token = helper.generateToken(user);

                JwtResponse response = JwtResponse.builder()
                    .jwtToken(token)
                    .username(user.getUsername())
                    ._id(user.get_id())
                    .role(user.getRole())
                    .build();

                return ResponseEntity.ok(response);
        } catch (org.springframework.data.mongodb.UncategorizedMongoDbException e) {
            // Database not found
            System.out.println("[DEBUG] Database not found error: " + e.getMessage());
            return ResponseEntity.status(402).body(Map.of("message", "Database not found"));
        } catch (Exception e) {
            System.out.println("[DEBUG] Internal server error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", "Internal server error"));
        }
    }

    @Operation(summary = "Register/Sign Up")
    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of("message", "Username already exists"));
        }
        String id = userRepository.save(user).get_id();
        return ResponseEntity.ok(Map.of("id", id));
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
        user.setPassword(request.getPassword());
        user.setUpdated_at(new Date());
        userRepository.save(user);

        tokenEntity.setUsed(true);
        passwordResetTokenRepository.save(tokenEntity);

        response.put("message", "Password reset successful.");
        return ResponseEntity.ok(response);
    }
}
