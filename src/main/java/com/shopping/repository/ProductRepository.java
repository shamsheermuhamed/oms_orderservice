package com.shopping.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.shopping.model.Product;

public interface ProductRepository extends MongoRepository<Product, String> {
	
	 @Query("{'productName':{'$regex':'?0','$options':'i'}}")  
	List<Product> findByProductName(String productName);

}
