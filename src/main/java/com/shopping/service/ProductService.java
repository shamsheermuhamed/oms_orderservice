package com.shopping.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.shopping.model.Product;
import com.shopping.request.ProductRequest;

public interface ProductService {

	List<Product> getAllProducts();

	List<Product> getDeatilsByProductName(String productName);

	ResponseEntity<Object> addNewProduct(ProductRequest product);

	ResponseEntity<Object> updateProductDetailsById(String productId, ProductRequest product);

	ResponseEntity<Object> deleteProductDetailsById(String productId);

	Product getDeatilsByProductId(String productId);

}
