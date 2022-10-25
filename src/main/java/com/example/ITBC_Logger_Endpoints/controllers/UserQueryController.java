package com.example.ITBC_Logger_Endpoints.controllers;

import com.example.ITBC_Logger_Endpoints.model.User;
import com.example.ITBC_Logger_Endpoints.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserQueryController {

    private UserRepository userRepository;

    @Autowired
    public UserQueryController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/api/clients")
    public List<User> getAllUsers() {
       return userRepository.getAllUsers();
    }
}
