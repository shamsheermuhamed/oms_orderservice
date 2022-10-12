package com.shopping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.model.Product;
import com.shopping.service.ProductService;

@RestController
@RequestMapping("/api/shopping")
public class ProductController {

	@Autowired
	ProductService productService;
	
	@GetMapping("/all")
	public List<Product> getAllProducts() {
		return productService.getAllProducts();
	}
	
	@GetMapping("/product/search/{productName}")
	public List<Product> getDeatilsByProductName(@PathVariable String productName) {
		return productService.getDeatilsByProductName(productName);
	}	
	
	@PostMapping("/product/add")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Object> addNewProduct(@RequestBody Product product) {
		return productService.addNewProduct(product);
	}	
	
	@PutMapping("/product/update/{productId}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Object> updateProductDetails(@PathVariable String productId, 
			@RequestBody Product product) {
		return productService.updateProductDetailsById(productId,product);
	}	
	
	@DeleteMapping("/product/delete/{productId}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Object> deleteProductDetails(@PathVariable String productId) {
		return productService.deleteProductDetailsById(productId);
	}
}
