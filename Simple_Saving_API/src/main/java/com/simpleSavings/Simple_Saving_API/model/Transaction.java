package com.simpleSavings.Simple_Saving_API.model;

import javax.persistence.*;
import java.util.Date;

@Table(name="transactions")
@Entity
public class Transaction {

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Basic
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    private String paymentMethod;
    private double amount;
    @Column(name="customer_id")
    private Long customerId;
    
    
	
	public Transaction(Long id, Date date, String paymentMethod, double amount, Long customerId) {
		super();
		this.id = id;
		this.date = date;
		this.paymentMethod = paymentMethod;
		this.amount = amount;
		this.customerId = customerId;
	}
	public Transaction() {
		// TODO Auto-generated constructor stub
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
    
}
