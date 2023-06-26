package com.simpleSavings.Simple_Saving_API;import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.simpleSavings.Simple_Saving_API.controller.TransactionController;
import com.simpleSavings.Simple_Saving_API.model.Product;
import com.simpleSavings.Simple_Saving_API.model.Transaction;
import com.simpleSavings.Simple_Saving_API.request.ProductRequest;
import com.simpleSavings.Simple_Saving_API.request.TransactionRequest;
import com.simpleSavings.Simple_Saving_API.services.TransactionService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(TransactionController.class)
@AutoConfigureMockMvc
public class TransactionControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransactionService transactionService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    

    @Test
    public void testCreateTransaction() throws Exception {
    	Long customerId=1L;
        TransactionRequest request = new TransactionRequest();
        request.setAmount(1.0);
        request.setDate(new Date(12/12/2022));
        request.setPaymentMethod("pmethod");
        
        
        Transaction savedtransaction = new Transaction();
        savedtransaction.setAmount(1.0);
        savedtransaction.setId((long)1);
        savedtransaction.setCustomerId(customerId);
        savedtransaction.setDate(new Date(12/12/2022));
        savedtransaction.setPaymentMethod("pmethod");
        
        when(transactionService.createTransaction(anyLong(), any(TransactionRequest.class))).thenReturn(savedtransaction);

        mockMvc.perform(post("/api/customers/transactions/{customerId}/create", customerId)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"paymentMethod\":\"pmethod\",\"amount\": 1.0,\"customerId\": 1}"))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.paymentMethod").value("pmethod"));
    
    }
    @Test
    public void testCreateTransactionFailed() throws Exception {
    	Long customerId=1L;
        TransactionRequest request = new TransactionRequest();
        request.setAmount(1.0);
        request.setDate(new Date(12/12/2022));
        request.setPaymentMethod("pmethod");
        
        
        Transaction savedtransaction = new Transaction();
        savedtransaction.setAmount(1.0);
        savedtransaction.setId((long)1);
        savedtransaction.setCustomerId(customerId);
        savedtransaction.setDate(new Date(12/12/2022));
        savedtransaction.setPaymentMethod("pmethod");
        
        when(transactionService.createTransaction(anyLong(), any(TransactionRequest.class))).thenReturn(null);

        mockMvc.perform(post("/api/customers/transactions/{customerId}/create", customerId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    
    }
    
    
    
    
    
  

    @Test
    public void testGetTotalSavingsAmount() throws Exception {
        double totalSavingsAmount = 500.0;

        when(transactionService.getTotalSavingsAmount(anyLong())).thenReturn(totalSavingsAmount);

        mockMvc.perform(get("/api/customers/transactions/total-savings"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

      
    }
   
    public void testGetTotalSavingsAmountFailed() throws Exception {
        
        when(transactionService.getTotalSavingsAmount(anyLong())).thenReturn(null);

       
        
        mockMvc.perform(get("/api/customers/transactions/total-savings"))
                .andExpect(status().isBadRequest());
    
      
    }


    @Test
    public void testGetTotalSavingsAccount() throws Exception {
        List<Transaction> totalSavingsAccount = new ArrayList<>();
        Transaction transaction=new Transaction();
        transaction.setAmount(1.0);
        transaction.setCustomerId(1L);
        transaction.setId(1L);
        transaction.setPaymentMethod("pmethod");
        totalSavingsAccount.add(transaction);

        when(transactionService.getTotalSavingsAccount()).thenReturn(totalSavingsAccount);

        mockMvc.perform(get("/api/customers/transactions/total-savings"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        
    }
    @Test
    public void testGetTotalSavingsAccountFailed() throws Exception {
        List<Transaction> totalSavingsAccount = new ArrayList<>();
        Transaction transaction=new Transaction();
        transaction.setAmount(1.0);
        transaction.setCustomerId(1L);
        transaction.setId(1L);
        transaction.setPaymentMethod("pmethod");
        totalSavingsAccount.add(transaction);

        when(transactionService.getTotalSavingsAccount()).thenReturn(null);

        mockMvc.perform(get("/api/customers/transactions/total-savings"))
                .andExpect(status().isBadRequest());

        
    }
}
