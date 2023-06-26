package com.simpleSavings.Simple_Saving_API.controller;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.simpleSavings.Simple_Saving_API.model.Product;
import com.simpleSavings.Simple_Saving_API.request.ProductRequest;
import com.simpleSavings.Simple_Saving_API.services.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/createProduct")
    public ResponseEntity<Product> createProduct(@Valid @RequestBody ProductRequest request) {
        Product product = productService.createProduct(request);
        if(product==null) {
        	return ResponseEntity.badRequest().build();
        	
        }
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @GetMapping("/getProduct/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable Long productId) {
        Product product = productService.getProduct(productId);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    
    @PatchMapping("/update/{productId}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable Long productId,
            @RequestBody ProductRequest request
    ) {
        Product product = productService.updateProduct(productId, request);
        if(product==null) {
        	return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }
}
