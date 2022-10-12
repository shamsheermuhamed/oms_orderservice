package com.shopping.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.shopping.model.Product;

public interface ProductService {

	List<Product> getAllProducts();

	List<Product> getDeatilsByProductName(String productName);

	ResponseEntity<Object> addNewProduct(Product product);

	ResponseEntity<Object> updateProductDetailsById(String productId, Product product);

	ResponseEntity<Object> deleteProductDetailsById(String productId);

}
