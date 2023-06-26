package com.simpleSavings.Simple_Saving_API.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import lombok.Builder;

import javax.persistence.Id;

@Builder
@Table(name="customers")
@Entity
public class Customer {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Basic
	private Long id;

    private String name;
    private String idNumber;
    private String phoneNumber;
    private String email;
    private String memberNumber;
    
    
    public Customer(Long id, String name, String idNumber, String phoneNumber, String email, String memberNumber) {
		super();
		this.id = id;
		this.name = name;
		this.idNumber = idNumber;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.memberNumber = memberNumber;
	}

	public Customer() {}
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMemberNumber() {
		return memberNumber;
	}
	public void setMemberNumber(String memberNumber) {
		this.memberNumber = memberNumber;
	}
    
}

