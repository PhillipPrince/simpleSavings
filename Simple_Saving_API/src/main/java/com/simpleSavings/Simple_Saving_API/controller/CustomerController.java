package com.simpleSavings.Simple_Saving_API.controller;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simpleSavings.Simple_Saving_API.model.Customer;
import com.simpleSavings.Simple_Saving_API.model.Transaction;
import com.simpleSavings.Simple_Saving_API.services.CustomerService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/customers")
@Api(tags = "Customers")
public class CustomerController {
    @Autowired
    private final CustomerService customerService;
    
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    
    @PostMapping("/createCustomer")
    @ApiOperation(value = "Create a new customer with their bio data")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        Customer createdCustomer = customerService.createCustomer(customer);
        if (createdCustomer == null) {
            return ResponseEntity.badRequest().build();
        }
        return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
    }
    
    @GetMapping("/getcustomer/{customerId}")
    @ApiOperation(value = "Get a customer by their ID")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long customerId) {
        Customer customer = customerService.getCustomerById(customerId);
        if (customer != null) {
            return ResponseEntity.ok(customer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PatchMapping("/update/{customerId}")
    @ApiOperation(value = "Update a customer by their ID")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long customerId, @RequestBody Customer customer) {
        Customer updatedCustomer = customerService.updateCustomer(customerId, customer);
        if (updatedCustomer == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(updatedCustomer, HttpStatus.CREATED);
    }
    
    @DeleteMapping("/delete/{customerId}")
    @ApiOperation(value = "Delete a customer by their ID")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long customerId) {
        boolean deleted = customerService.deleteCustomer(customerId);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/{customerId}/transactions")
    @ApiOperation(value = "Get transactions for a customer by their ID")
    public ResponseEntity<Optional<Customer>> getCustomerTransactions(@PathVariable Long customerId) {
        Optional<Customer> transactions = customerService.getCustomerTransactions(customerId);
        return ResponseEntity.ok(transactions);
    }
    
    @GetMapping("/getAllCustomers")
    @ApiOperation(value = "Get all customers")
    public ResponseEntity<List<Customer>> getCustomerAll() {
        List<Customer> customers = customerService.getAllCustomers();
        if (customers == null || customers.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(customers);
    }
}
