package com.carddemo.controller;

import com.carddemo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping
    public String getAllAccounts() {
        return "Account Controller - Get All Accounts";
    }

    @GetMapping("/{id}")
    public String getAccount(@PathVariable Long id) {
        return "Account Controller - Get Account: " + id;
    }

    @PostMapping
    public String createAccount() {
        return "Account Controller - Create Account";
    }

    @PutMapping("/{id}")
    public String updateAccount(@PathVariable Long id) {
        return "Account Controller - Update Account: " + id;
    }
}
