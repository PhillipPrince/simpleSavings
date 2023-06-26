package com.simpleSavings.Simple_Saving_API;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.simpleSavings.Simple_Saving_API.controller.ProductController;
import com.simpleSavings.Simple_Saving_API.model.Customer;
import com.simpleSavings.Simple_Saving_API.model.Product;
import com.simpleSavings.Simple_Saving_API.request.ProductRequest;
import com.simpleSavings.Simple_Saving_API.services.ProductService;



import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;
    

    @MockBean
    private ProductService productService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

 

    @Test
    public void testGetProductById() throws Exception {
    	
    	Long id = 1L;
        Product product = new Product(id, "Product1");

        when(productService.getProduct(id)).thenReturn(product);

        mockMvc.perform(get("/api/products/getProduct/1", id))
                .andExpect(status().is(200))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.productName").value("Product1"));

    }
    
    
    @Test
    public void testCreateProduct() throws Exception {
    	Long id = 1L;
        ProductRequest productRequest = new ProductRequest();
        productRequest.setName("Product1");
        
        Product savedProduct = new Product();
        savedProduct.setProductName("Product1");
        savedProduct.setId(id);
        
        when(productService.createProduct(any(ProductRequest.class))).thenReturn(savedProduct);

        mockMvc.perform(post("/api/products/createProduct")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Product1\"}"))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.productName").value("Product1"));

        
    }
    
    @Test
    public void testCreateProductFailed() throws Exception {
    	Long id = 1L;
        ProductRequest productRequest = new ProductRequest();
        productRequest.setName("Product1");
        
        Product savedProduct = new Product();
        savedProduct.setProductName("Product1");
        savedProduct.setId(id);
        
        when(productService.createProduct(any(ProductRequest.class))).thenReturn(null);

        mockMvc.perform(post("/api/products/createProduct")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        
    }
    

    @Test
    public void testUpdateProduct() throws Exception {
    	Long id = 1L;
        ProductRequest productRequest = new ProductRequest();
        productRequest.setName("Product1");
        
        Product savedProduct = new Product();
        savedProduct.setProductName("Product1");
        savedProduct.setId(id);
        when(productService.updateProduct(anyLong(), any(ProductRequest.class))).thenReturn(savedProduct);

        mockMvc.perform(patch("/api/products/update/1", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Product1\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.productName").value("Product1"));
        
      
    }
    @Test
    public void testUpdateProductFailed() throws Exception {
    	Long id = 1L;
        ProductRequest productRequest = new ProductRequest();
        productRequest.setName("Product1");
        
        Product savedProduct = new Product();
        savedProduct.setProductName("Product1");
        savedProduct.setId(id);
        when(productService.updateProduct(anyLong(), any(ProductRequest.class))).thenReturn(null);

        mockMvc.perform(patch("/api/products/update/1", id)
        		.contentType(MediaType.APPLICATION_JSON))
        		.andExpect(status().isBadRequest());

        
      
    }

    @Test
    public void testDeleteProduct() throws Exception {
    	Long id = 1L;
        mockMvc.perform(delete("/api/products/delete/1", id))
                .andExpect(status().isNoContent());

     
    }
}

