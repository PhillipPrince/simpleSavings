package com.simpleSavings.Simple_Saving_API.request;

import java.sql.Date;

public class TransactionRequest {

    private Date date;
    private String paymentMethod;
    private double amount;
    
    
	
	
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

    
}
