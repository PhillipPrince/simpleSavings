package com.simpleSavings.Simple_Saving_API;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.simpleSavings.Simple_Saving_API.controller.CustomerController;
import com.simpleSavings.Simple_Saving_API.model.Customer;
import com.simpleSavings.Simple_Saving_API.repository.CustomerRepository;
import com.simpleSavings.Simple_Saving_API.services.CustomerService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
@AutoConfigureMockMvc
public class CustomerControllerTests {

    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private CustomerService customerService;
    

    
    

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllCustomers() throws Exception {
        Customer customer1 = new Customer((long) 1,  "Test1", "1234", "7657", "@test", "847746");
        Customer customer2 = new Customer((long) 2,  "test2", "85957", "64746", "@test2", "65746");
        List<Customer> customers = Arrays.asList(customer1, customer2);

        
        when(customerService.getAllCustomers()).thenReturn(customers);

        
        mockMvc.perform(get("/customers/getAllCustomers"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
              
    }
    
    @Test
    public void testGetAllCustomersNotFound() throws Exception {

        when(customerService.getAllCustomers()).thenReturn(null);

        
        mockMvc.perform(get("/customers/getAllCustomers"))
                .andExpect(status().isNotFound());
              
    }

    @Test
    public void testGetCustomerById() throws Exception {
    	Long id = 1L;
        Customer customer = new Customer(id,  "Test1", "1234", "7657", "@test", "847746");

        when(customerService.getCustomerById(id)).thenReturn(customer);

        
        mockMvc.perform(get("/customers/getcustomer/1", id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Test1"))
                .andExpect(jsonPath("$.idNumber").value("1234"))
                .andExpect(jsonPath("$.phoneNumber").value("7657"))
                .andExpect(jsonPath("$.email").value("@test"))
                .andExpect(jsonPath("$.memberNumber").value("847746"));
    }
    
    @Test
    public void testGetCustomerByIdNotFound() throws Exception {
    	Long id = 1L;
        Customer customer = new Customer(id,  "Test1", "1234", "7657", "@test", "847746");

        when(customerService.getCustomerById(id)).thenReturn(null);

        
        mockMvc.perform(get("/customers/getcustomer/3", id))
                .andExpect(status().isNotFound());
    }
    
    
    @Test
    public void testGetNonExistentCustomerById() throws Exception {
    	Long id = 1L;
        Customer customer = new Customer(id,  "Test1", "1234", "7657", "@test", "847746");

        when(customerService.getCustomerById(id)).thenReturn(customer);

        
        mockMvc.perform(get("/customers/{id}", 2l))
                .andExpect(status().isNotFound());
    }

    
    @Test
    public void testCreateCustomer() throws Exception {
       
        Customer customer = new Customer((long) 1,  "Test1", "1234", "7657", "@test", "847746");

        
        when(customerService.createCustomer(any(Customer.class))).thenReturn(customer);

        mockMvc.perform(post("/customers/createCustomer")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Test1\",\"idNumber\": \"1234\",\"phoneNumber\": \"7657\",\"email\": \"@test\",\"memberNumber\": \"1256\"}"))
        
                .andExpect(status().is(201))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Test1"))
                .andExpect(jsonPath("$.idNumber").value("1234"))
                .andExpect(jsonPath("$.phoneNumber").value("7657"))
                .andExpect(jsonPath("$.email").value("@test"))
                .andExpect(jsonPath("$.memberNumber").value("847746"));
    
    }
 
    
    
    
    @Test
    public void testeCustomerNotCreated() throws Exception {
        
        Customer customer = new Customer((long) 1,  "Test1", "1234", "7657", "@test", "847746");

        
        when(customerService.createCustomer(any(Customer.class))).thenReturn(null);

        mockMvc.perform(post("/customers/createCustomer")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Test1\",\"idNumber\": \"\",\"phoneNumber\": \"7657\",\"email\": \"@test\",\"memberNumber\": \"1256\"}"))
                .andExpect(status().isBadRequest());
                
    
    }
    
    @Test
    public void testUpdateCustomer() throws Exception {
       Long id=1L;
        Customer customer = new Customer(id,  "Test1", "1234", "7657", "@test", "847746");

        
        when(customerService.updateCustomer(anyLong(), any(Customer.class))).thenReturn(customer);

        mockMvc.perform(patch("/customers/update/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Test1\",\"idNumber\": \"1234\",\"phoneNumber\": \"7657\",\"email\": \"@test\",\"memberNumber\": \"1256\"}"))
                .andExpect(status().is(201))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Test1"))
                .andExpect(jsonPath("$.idNumber").value("1234"))
                .andExpect(jsonPath("$.phoneNumber").value("7657"))
                .andExpect(jsonPath("$.email").value("@test"))
                .andExpect(jsonPath("$.memberNumber").value("847746"));
    
    }
    
    @Test
    public void testUpdateCustomerFailed() throws Exception {
       Long id=1L;
        Customer customer = new Customer(id,  "Test1", "1234", "7657", "@test", "847746");

        
        when(customerService.updateCustomer(anyLong(), any(Customer.class))).thenReturn(null);

        mockMvc.perform(patch("/customers/update/{id}", 10L)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isNotFound());
                
    
    }
    
    @Test
    public void deleteCustomer() throws Exception {
    	Long id = 1L;

        when(customerService.deleteCustomer(id)).thenReturn(true);

        
        mockMvc.perform(delete("/customers/delete/1", id))
                .andExpect(status().isNoContent());
    }
    @Test
    public void deleteCustomerFailed() throws Exception {
    	Long id = 1L;

        when(customerService.deleteCustomer(id)).thenReturn(false);

        
        mockMvc.perform(delete("/customers/delete/1", id))
                .andExpect(status().isNotFound());
    }
    
}

