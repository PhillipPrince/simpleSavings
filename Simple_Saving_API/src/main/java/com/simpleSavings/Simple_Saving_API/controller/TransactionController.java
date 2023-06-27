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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api/customers/transactions")
@Api(tags = "Transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/{customerId}/create")
    @ApiOperation(value = "Create a new transaction for a customer")
    public ResponseEntity<Transaction> createTransaction(
            @PathVariable
            @ApiParam(value = "Customer ID", example = "1")
                    Long customerId,
            @RequestBody TransactionRequest request
    ) {
        Transaction transaction = transactionService.createTransaction(customerId, request);
        if (transaction == null) {
            return ResponseEntity.badRequest().build();
        }
        return new ResponseEntity<>(transaction, HttpStatus.CREATED);
    }

    @GetMapping("/{customerId}/total-savings")
    @ApiOperation(value = "Get the total savings amount for a customer")
    public ResponseEntity<Double> getTotalSavingsAmount(
            @PathVariable
            @ApiParam(value = "Customer ID", example = "1")
                    Long customerId
    ) {
        Double totalSavingsAmount = transactionService.getTotalSavingsAmount(customerId);
        if (totalSavingsAmount == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(totalSavingsAmount);
    }

    @GetMapping("/total-savings")
    @ApiOperation(value = "Get the total savings accounts")
    public ResponseEntity<List<Transaction>> getTotalSavingsAccount() {
        List<Transaction> totalSavingsAmount = transactionService.getTotalSavingsAccount();
        if (totalSavingsAmount == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(totalSavingsAmount);
    }
}
