package com.shopping.service.impl;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.shopping.model.Product;
import com.shopping.repository.ProductRepository;
import com.shopping.request.ProductRequest;

@ExtendWith(SpringExtension.class)
public class ProductServiceImplTest {
	
	@InjectMocks
	private ProductServiceImpl productServiceImpl;
	
	@Mock
	private ProductRepository productRepository;
	
	@Test
	void testGetAllProducts() {
		List<Product> productList=new ArrayList<>();
		Product product=new Product();
		product.setId("1");
		productList.add(product);
		when(productRepository.findAll()).thenReturn(productList);
		assertNotNull(productServiceImpl.getAllProducts());
	}
	
	@Test
	void testGetDeatilsByProductName() {
		List<Product> productList=new ArrayList<>();
		String productName="football";
		Product product=new Product();
		product.setId("1");
		product.setProductName("football");
		productList.add(product);
		when(productRepository.findByProductName(productName)).thenReturn(productList);
		List<Product> actual= productServiceImpl.getDeatilsByProductName(productName);
		assertNotNull(actual);
		assertEquals(productList,actual);
	}
	
	@Test
	void testaddNewProduct() {
		ProductRequest productRequest=new ProductRequest();
		productRequest.setId("123");
		productRequest.setProductName("football");
		productRequest.setActive(true);
		assertNotNull(productServiceImpl.addNewProduct(productRequest));
		assertDoesNotThrow(()->{
			productServiceImpl.addNewProduct(productRequest);
		});
	}
}
