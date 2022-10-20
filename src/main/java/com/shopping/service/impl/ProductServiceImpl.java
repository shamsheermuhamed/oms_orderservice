package com.shopping.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.shopping.exception.ProductNotFoundException;
import com.shopping.model.Product;
import com.shopping.repository.ProductRepository;
import com.shopping.request.MessageResponse;
import com.shopping.request.ProductRequest;
import com.shopping.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	private KafkaTemplate<String, Object> template;
	
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
	public ResponseEntity<Object> addNewProduct(ProductRequest productrequest) {
		Product product=new Product();
		product.setProductName(productrequest.getProductName());
		product.setActive(productrequest.getActive());
		product.setDescription(productrequest.getDescription());
		product.setFeatures(productrequest.getFeatures());
		product.setPrice(productrequest.getPrice());
		product.setUnitStock(productrequest.getUnitStock());
		product.setImageUrl(productrequest.getImageUrl());
		productRepository.save(product);
		return  ResponseEntity.ok(new MessageResponse("Product details added successfully"));
	}

	@Override
	public ResponseEntity<Object> updateProductDetailsById(String productId, ProductRequest productrequest) {
		Product product=new Product();
		product.setProductName(productrequest.getProductName());
		product.setActive(productrequest.getActive());
		product.setDescription(productrequest.getDescription());
		product.setFeatures(productrequest.getFeatures());
		product.setPrice(productrequest.getPrice());
		product.setUnitStock(productrequest.getUnitStock());
		product.setImageUrl(productrequest.getImageUrl());
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
//			template.send("producttopic",productId+" has been deleted");
			return  ResponseEntity.ok(new MessageResponse("Product successfully remove from the system"));
		}
		else {
			return ResponseEntity
			.badRequest()
			.body(new MessageResponse("Error: No product found with the given product Id!"));
		}
		
	}

	@Override
	public Product getDeatilsByProductId(String productId) {
		Optional<Product> optionalProduct= productRepository.findById(productId);
		if(optionalProduct.isPresent()) {
			return optionalProduct.get();
		}
		else {
			return null;
		}		
	}

}
