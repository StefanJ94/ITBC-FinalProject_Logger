package com.example.ITBC_Logger_Endpoints.controllers;


import com.example.ITBC_Logger_Endpoints.RegexPassword;
import com.example.ITBC_Logger_Endpoints.enums.LogType;
import com.example.ITBC_Logger_Endpoints.model.Admin;
import com.example.ITBC_Logger_Endpoints.model.Log;
import com.example.ITBC_Logger_Endpoints.model.User;
import com.example.ITBC_Logger_Endpoints.repository.TestJpaRepository;
import com.example.ITBC_Logger_Endpoints.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLDataException;
import java.time.LocalDate;
import java.util.UUID;

@RestController
public class UserActionController {

    private UserRepository userRepository;
    private TestJpaRepository testJpaRepository;

    static int num = 0;

    @Autowired
    public UserActionController(UserRepository userRepository, TestJpaRepository testJpaRepository) {
        this.userRepository = userRepository;
        this.testJpaRepository = testJpaRepository;
    }

    @PostMapping("/api/clients/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {

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
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username at least 3 characters!");

        // Password must contain at least one digit [0-9], at least one lowercase Latin character [a-z],
        // at least one uppercase Latin character [A-Z], and at least one special character like ! (JAVA REGEX)
        if (!RegexPassword.isValidPassword(user.getPassword()))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Password must be JAVA REGEX password!");


        // Email must be valid
        if (!user.getEmail().contains("@") || user.getEmail().length() < 6 || !user.getEmail().contains("."))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email must be valid!");

        // Generate random id and add it to Client-User from request
        user.setId(UUID.randomUUID());

        userRepository.insertUser(user);


        return ResponseEntity.status(HttpStatus.CREATED).body("User created!");

    }

    @PostMapping("/api/clients/register/admin")
    public ResponseEntity<String> registerAdmin(@RequestBody Admin admin) {

        // Check if there is already a Admin with the forwarded username
        if (testJpaRepository.isDuplicateUserName(admin.getUsername()) != 0) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
        // Check if there is already a Admin with the forwarded email
        if (testJpaRepository.isDuplicateEmail(admin.getEmail()) != 0) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
        // Username at least 3 characters
        if (admin.getUsername().length() < 3)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username at least 3 characters!");

        // Password must contain at least one digit [0-9], at least one lowercase Latin character [a-z],
        // at least one uppercase Latin character [A-Z], and at least one special character like ! (JAVA REGEX)
        if (!RegexPassword.isValidPassword(admin.getPassword()))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Password must be JAVA REGEX password!");


        // Email must be valid
        if (!admin.getEmail().contains("@") || admin.getEmail().length() < 6 || !admin.getEmail().contains("."))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email must be valid!");

        // Generate random id and add it to User-Admin from request
        admin.setId(UUID.randomUUID());

        userRepository.insertUserAdmin(admin);


        return ResponseEntity.status(HttpStatus.CREATED).body("Admin created!");
    }


    @PostMapping("/api/logs/create")
    public ResponseEntity<UUID> createLog(@RequestHeader("id") UUID id, @RequestBody Log log) {

        if (log.getLogType().getValue() != LogType.ERROR.getValue() &&
                log.getLogType().getValue() != LogType.WARNING.getValue()
                && log.getLogType().getValue() != LogType.INFO.getValue()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } else if (log.getMessage().length() > 1024) {
            ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).body(null);
        } else if (testJpaRepository.isIdExist(id) == 0) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
      log.setLogId(num);
      log.setDateTime(LocalDate.now());

        userRepository.insertLog(log);
        return ResponseEntity.status(HttpStatus.CREATED).body(log.getId());
    }

    @PostMapping("/api/clients/login")
    public ResponseEntity<?> loginClient (@RequestBody User user) throws SQLDataException {
        if (userRepository.userLogin(user) == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username or password incorrect");
        }
        userRepository.userLogin(user);
            return ResponseEntity.status(HttpStatus.OK).body(user.getId());
            // user.getId == TOKEN = UUID
        }

    @PatchMapping("/api/clients/{clientId}/reset-password")
    public ResponseEntity<String> changePassword(@RequestParam(value = "id") UUID id,
                                                 @RequestBody User user) {
        if (user.getPassword() == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No content!");
        }
        userRepository.changePassword(id,user);
        return ResponseEntity.status(HttpStatus.OK).body("Password changed!");
    }
}
