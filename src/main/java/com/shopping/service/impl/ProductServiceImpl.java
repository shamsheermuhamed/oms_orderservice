package com.shopping.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.shopping.exception.ProductNotFoundException;
import com.shopping.model.Product;
import com.shopping.repository.ProductRepository;
import com.shopping.request.MessageResponse;
import com.shopping.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
	
	@Autowired
	ProductRepository productRepository;
	
	@Override
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	@Override
	public List<Product> getDeatilsByProductName(String productName) {
		logger.info("Entering into service-getDeatilsByProductName");
		List<Product> productList=productRepository.findByProductName(productName);
		if(productList.isEmpty()) {
			throw new ProductNotFoundException();
		}
		else {
			return productList;
		}
	}

	@Override
	public ResponseEntity<Object> addNewProduct(Product product) {
		productRepository.save(product);
		return  ResponseEntity.ok(new MessageResponse("Product details added successfully"));
	}

	@Override
	public ResponseEntity<Object> updateProductDetailsById(String productId, Product product) {
		if(productRepository.findById(productId).isPresent()) {
			product.setId(productId);
			productRepository.save(product);
			return  ResponseEntity.ok(new MessageResponse("Product details updated successfully"));
		}
		else {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: No product found with the given product Id!"));
		}
	}

	@Override
	public ResponseEntity<Object> deleteProductDetailsById(String productId) {
		if(productRepository.findById(productId).isPresent()) {
			productRepository.deleteById(productId);
			return  ResponseEntity.ok(new MessageResponse("Product successfully remove from the system"));
		}
		else {
			return ResponseEntity
			.badRequest()
			.body(new MessageResponse("Error: No product found with the given product Id!"));
		}
		
	}

}
