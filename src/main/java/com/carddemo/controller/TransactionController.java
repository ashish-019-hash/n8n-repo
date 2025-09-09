package com.carddemo.controller;

import com.carddemo.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping
    public String getAllTransactions() {
        return "Transaction Controller - Get All Transactions";
    }

    @GetMapping("/{id}")
    public String getTransaction(@PathVariable Long id) {
        return "Transaction Controller - Get Transaction: " + id;
    }

    @PostMapping
    public String createTransaction() {
        return "Transaction Controller - Create Transaction";
    }
}
