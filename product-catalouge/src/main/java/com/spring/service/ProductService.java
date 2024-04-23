package com.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.spring.model.Product;
import com.spring.repository.ProductRepository;

@Service
public class ProductService {

	 @Autowired
	    private ProductRepository productRepository;

	    public Product addProduct(Product product) {
	        return productRepository.save(product);
	    }

	    public Product getProduct(Long productId) {
	        return productRepository.findById(Long.valueOf(productId)).orElse(null);
	    }
	    
	    public Page<Product> getAllProducts(Pageable pageable) {
	        return productRepository.findAll(pageable);
	    }

	    public Product updateProduct(Long productId, Product updatedProduct) {
	        Product existingProduct = getProduct(productId);
	        if (existingProduct == null) {
	            return null; // or throw an exception
	        }

	        existingProduct.setRatings(updatedProduct.getRatings());

	        return productRepository.save(existingProduct);
	    }

	    public void deleteProduct(Long productId) {

	        productRepository.deleteById(Long.valueOf(productId));
	    }

	    public List<Product> searchProducts(String name, String category, String attributeKey, String attributeValue) {
	        // Construct the search query based on the provided filters
	        if (name != null && !name.isEmpty()) {
	            // Search by name
	            return productRepository.findByName(name);
	        } else if (category != null && !category.isEmpty()) {
	            // Search by category
	            return productRepository.findByCategoriesContaining(category);
	        } else if (attributeKey != null && attributeValue != null && !attributeKey.isEmpty() && !attributeValue.isEmpty()) {
	            // Search by attribute key and value
	            return productRepository.findByAttributesKeyAndAttributesValue(attributeKey, attributeValue);
	        } else {
	            // No search criteria provided, return all products
	            return productRepository.findAll();
	        }
	    }
}
