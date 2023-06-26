package com.simpleSavings.Simple_Saving_API.Repository;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.simpleSavings.Simple_Saving_API.model.Customer;
import com.simpleSavings.Simple_Saving_API.repository.CustomerRepository;

@DataJpaTest
//@AutoConfigureTestDatabase
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CustomerReositoryTest {
	@Autowired
	private CustomerRepository customerRepository;
	
	@Test
	public void CustomerRepositorySave() {
		
		//Arrange
		Customer customer= new Customer();
		customer.setName("test");
		customer.setEmail("@mail");
		
		//Act
		Customer savedCust=customerRepository.save(customer);
				
		//Assert
		Assertions.assertThat(savedCust).isNotNull();
		Assertions.assertThat(savedCust.getId()).isGreaterThan(0);
				
		
	}
	

}
