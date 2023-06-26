package com.simpleSavings.Simple_Saving_API.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.simpleSavings.Simple_Saving_API.model.Product;
import com.simpleSavings.Simple_Saving_API.repository.ProductRepository;
import com.simpleSavings.Simple_Saving_API.request.ProductRequest;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(ProductRequest request) {
        Product product = new Product();
        product.setProductName(request.getName());
        // Set other properties of the product as needed

        return productRepository.save(product);
    }

	
	

    public Product getProduct(Long productId) {
        try {
			return productRepository.findById(productId)
			        .orElseThrow(() -> new NotFoundException());
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    }

    public Product updateProduct(Long productId, ProductRequest request) {
        Product product = getProduct(productId);
        product.setProductName(request.getName());
        // Update other properties of the product as needed

        return productRepository.save(product);
    }

    public void deleteProduct(Long productId) {
        Product product = getProduct(productId);
        productRepository.delete(product);
    }
}

