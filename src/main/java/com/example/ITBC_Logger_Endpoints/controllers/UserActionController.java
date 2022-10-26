package com.example.ITBC_Logger_Endpoints.controllers;


import com.example.ITBC_Logger_Endpoints.RegexPassword;
import com.example.ITBC_Logger_Endpoints.model.User;
import com.example.ITBC_Logger_Endpoints.repository.TestJpaRepository;
import com.example.ITBC_Logger_Endpoints.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class UserActionController {

    private UserRepository userRepository;
    private TestJpaRepository testJpaRepository;

    @Autowired
    public UserActionController(UserRepository userRepository, TestJpaRepository testJpaRepository) {
        this.userRepository = userRepository;
        this.testJpaRepository = testJpaRepository;
    }

    @PostMapping("/api/clients/register")
    public ResponseEntity<Void> registerUser(@RequestBody User user) {

        // Check if there is already a user with the forwarded username
        if (testJpaRepository.isDuplicateUserName(user.getUsername()) != 0) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
        // Check if there is already a user with the forwarded email
        if (testJpaRepository.isDuplicateEmail(user.getEmail()) != 0) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
        // Username at least 3 characters
        if (user.getUsername().length() < 3)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

        // Password must contain at least one digit [0-9], at least one lowercase Latin character [a-z],
        // at least one uppercase Latin character [A-Z], and at least one special character like ! (JAVA REGEX)
        if (!RegexPassword.isValidPassword(user.getPassword()))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);


        // Email must be valid
        if (!user.getEmail().contains("@") || user.getEmail().length() < 6 || !user.getEmail().contains("."))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

        // Generate random id and add it to Client-User from request
        user.setId(UUID.randomUUID());

        userRepository.insertUser(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(null);

    }



}
