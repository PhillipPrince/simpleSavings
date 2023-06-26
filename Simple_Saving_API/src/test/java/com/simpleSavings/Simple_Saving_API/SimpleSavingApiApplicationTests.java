package com.simpleSavings.Simple_Saving_API;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;


import com.simpleSavings.Simple_Saving_API.services.CustomerService;
import com.simpleSavings.Simple_Saving_API.model.Customer;

@SpringBootTest
class SimpleSavingApiApplicationTests {

	@Test
	void contextLoads() {
	}
	

}
