package com.carddemo.controller;

import com.carddemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String getAllUsers() {
        return "User Controller - Get All Users";
    }

    @GetMapping("/{id}")
    public String getUser(@PathVariable Long id) {
        return "User Controller - Get User: " + id;
    }

    @PostMapping
    public String createUser() {
        return "User Controller - Create User";
    }

    @PutMapping("/{id}")
    public String updateUser(@PathVariable Long id) {
        return "User Controller - Update User: " + id;
    }
}
