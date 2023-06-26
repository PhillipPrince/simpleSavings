package com.simpleSavings.Simple_Saving_API.request;

import javax.validation.constraints.NotBlank;

public class ProductRequest {

	@NotBlank(message = "Name is required")
    private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    
}

