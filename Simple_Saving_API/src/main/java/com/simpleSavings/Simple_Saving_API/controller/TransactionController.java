package com.simpleSavings.Simple_Saving_API.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simpleSavings.Simple_Saving_API.model.Transaction;
import com.simpleSavings.Simple_Saving_API.request.TransactionRequest;
import com.simpleSavings.Simple_Saving_API.services.TransactionService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    
    @PostMapping("/{customerId}/create")
    public ResponseEntity<Transaction> createTransaction(
            @PathVariable Long customerId,
            @RequestBody TransactionRequest request
    ) {
        Transaction transaction = transactionService.createTransaction(customerId, request);
        if(transaction==null) {
        	return ResponseEntity.badRequest().build();	
        }
        return new ResponseEntity<>(transaction, HttpStatus.CREATED);
    }
    
    @GetMapping("/customerId/total-savings")
    public ResponseEntity<Double> getTotalSavingsAmount(@PathVariable Long customerId) {
        Double totalSavingsAmount = transactionService.getTotalSavingsAmount(customerId);
        if(totalSavingsAmount==null) {
        	return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(totalSavingsAmount);
    }
    
    @GetMapping("/total-savings")
    public ResponseEntity<List<Transaction>> getTotalSavingsAccount() {
        List<Transaction> totalSavingsAmount = transactionService.getTotalSavingsAccount();
        if(totalSavingsAmount==null) {
        	return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(totalSavingsAmount);
    }


}
