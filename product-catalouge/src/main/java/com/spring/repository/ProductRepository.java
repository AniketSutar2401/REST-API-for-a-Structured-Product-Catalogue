package com.spring.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.model.Product;
@Repository
public interface ProductRepository extends MongoRepository<Product, Long>{

	 	List<Product> findByName(String name);
	    
	    List<Product> findByCategoriesContaining(String category);
	    
	    @Query("{'attributes.key': ?0, 'attributes.value': ?1}")
	    List<Product> findByAttributesKeyAndAttributesValue(String attributeKey, String attributeValue);
}
