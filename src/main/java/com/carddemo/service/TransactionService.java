package com.carddemo.service;

import com.carddemo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public void getAllTransactions() {
    }

    public void getTransactionById(Long id) {
    }

    public void createTransaction() {
    }
}
