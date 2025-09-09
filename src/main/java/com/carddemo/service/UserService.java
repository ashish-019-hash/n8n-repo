package com.carddemo.service;

import com.carddemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void getAllUsers() {
    }

    public void getUserById(Long id) {
    }

    public void createUser() {
    }

    public void updateUser(Long id) {
    }
}
