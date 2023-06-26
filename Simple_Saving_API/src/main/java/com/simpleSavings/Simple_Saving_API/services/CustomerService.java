package com.simpleSavings.Simple_Saving_API.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.simpleSavings.Simple_Saving_API.model.Customer;
import com.simpleSavings.Simple_Saving_API.model.Transaction;
import com.simpleSavings.Simple_Saving_API.repository.CustomerRepository;


@Service
@Transactional
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer getCustomerById(Long customerId) {
        Customer customer =  customerRepository.findById(customerId).orElse(null);
        return customer;
    }

    public Customer updateCustomer(Long customerId, Customer updatedCustomer) {
        Customer existingCustomer = customerRepository.findById(customerId).orElse(null);
        if (existingCustomer != null) {
            existingCustomer.setName(updatedCustomer.getName());
            existingCustomer.setIdNumber(updatedCustomer.getIdNumber());
            existingCustomer.setPhoneNumber(updatedCustomer.getPhoneNumber());
            existingCustomer.setEmail(updatedCustomer.getEmail());
            return customerRepository.save(existingCustomer);
        }
        return null;
    }

    public boolean deleteCustomer(Long customerId) {
        if (customerRepository.existsById(customerId)) {
            customerRepository.deleteById(customerId);
            return true;
        }
        return false;
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

	public Optional<Customer> getCustomerTransactions(Long customerId) {
		
		return customerRepository.findById(customerId);
	}

	

	

    // Other business methods as needed
}

