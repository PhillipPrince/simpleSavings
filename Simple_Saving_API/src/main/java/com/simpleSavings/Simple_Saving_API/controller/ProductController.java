package com.simpleSavings.Simple_Saving_API.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.simpleSavings.Simple_Saving_API.model.Product;
import com.simpleSavings.Simple_Saving_API.request.ProductRequest;
import com.simpleSavings.Simple_Saving_API.services.ProductService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api/products")
@Api(tags = "Products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/createProduct")
    @ApiOperation(value = "Create a new product")
    public ResponseEntity<Product> createProduct(
            @Valid @RequestBody ProductRequest request
    ) {
        Product product = productService.createProduct(request);
        if (product == null) {
            return ResponseEntity.badRequest().build();
        }
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @GetMapping("/getProduct/{productId}")
    @ApiOperation(value = "Get a product by ID")
    public ResponseEntity<Product> getProduct(
            @PathVariable
            @ApiParam(value = "Product ID", example = "1")
                    Long productId
    ) {
        Product product = productService.getProduct(productId);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/update/{productId}")
    @ApiOperation(value = "Update a product by ID")
    public ResponseEntity<Product> updateProduct(
            @PathVariable
            @ApiParam(value = "Product ID", example = "1")
                    Long productId,
            @RequestBody ProductRequest request
    ) {
        Product product = productService.updateProduct(productId, request);
        if (product == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/delete/{productId}")
    @ApiOperation(value = "Delete a product by ID")
    public ResponseEntity<Void> deleteProduct(
            @PathVariable
            @ApiParam(value = "Product ID", example = "1")
                    Long productId
    ) {
        productService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }
}
