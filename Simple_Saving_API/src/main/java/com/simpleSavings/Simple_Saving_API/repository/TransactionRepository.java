package com.simpleSavings.Simple_Saving_API.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.simpleSavings.Simple_Saving_API.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
	
	@Query(value="SELECT SUM(t.amount) FROM Transaction t,customers c WHERE c.id = :customerId and t.customer_id=c.id", nativeQuery = true )
    Double getTotalSavingsAmount(Long customerId);
	@Query("SELECT SUM(t.amount) FROM Transaction t")
    Double getTotalSavingsAmount();
	Double findAllById(Long customerId);
}

