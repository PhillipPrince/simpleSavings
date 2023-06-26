package com.simpleSavings.Simple_Saving_API.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simpleSavings.Simple_Saving_API.model.Customer;
import com.simpleSavings.Simple_Saving_API.model.Transaction;
import com.simpleSavings.Simple_Saving_API.repository.CustomerRepository;
import com.simpleSavings.Simple_Saving_API.repository.TransactionRepository;
import com.simpleSavings.Simple_Saving_API.request.TransactionRequest;

import javassist.NotFoundException;

@Service
public class TransactionService {

    private final CustomerRepository customerRepository;
    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(CustomerRepository customerRepository, TransactionRepository transactionRepository) {
        this.customerRepository = customerRepository;
        this.transactionRepository = transactionRepository;
    }

    public Transaction createTransaction(Long customerId, TransactionRequest request) {
        Customer customer = null;
		try {
			customer = customerRepository.findById(customerId)
			        .orElseThrow(() -> new NotFoundException("Customer not found with ID: " + customerId));
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        // Create a new transaction
        Transaction transaction = new Transaction();
        transaction.setCustomerId(customerId);
        transaction.setDate(request.getDate());
        transaction.setPaymentMethod(request.getPaymentMethod());
        transaction.setAmount(request.getAmount());

        // Save the transaction
        return transactionRepository.save(transaction);
    }

	public Double getTotalSavingsAmount(Long customerId) {
		// TODO Auto-generated method stub
		return transactionRepository.findAllById(customerId);
	}

	public List<Transaction> getTotalSavingsAccount() {
		// TODO Auto-generated method stub
		return transactionRepository.findAll();
	}

	
}
