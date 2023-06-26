package com.simpleSavings.Simple_Saving_API.model;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Product {

    public Product(Long id, String productName) {
		super();
		this.id = id;
		this.productName = productName;
	}

	public Product() {
	}

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Basic
    private Long id;

    private String productName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

    
}
