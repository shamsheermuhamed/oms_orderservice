package com.shopping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.model.Order;
import com.shopping.repository.OrderRepository;
import com.shopping.request.OrderRequest;
import com.shopping.request.OrderResponse;
import com.shopping.service.OrderService;

@RestController
@RequestMapping("/api/shopping")
@CrossOrigin
public class OrderController {
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	OrderRepository orderRepo;
	
	@PostMapping("/createorder")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Object> createOrder(@RequestBody OrderRequest order) {
		return orderService.createOrder(order);
	}	
	
	@GetMapping("/getallorders")
	@PreAuthorize("hasRole('ADMIN')")
	public  List<OrderResponse> getAllOrders() {
		return orderService.getAllOrders();
	}
	
	@GetMapping("/getordersforproduct/{productId}")
	@PreAuthorize("hasRole('ADMIN')")
	public  List<OrderResponse> getOrdersForProduct(@PathVariable String productId) {
		return orderService.getOrdersForProduct(productId);
	}
	
	@GetMapping("/getordersbyuser/{userId}")
	@PreAuthorize("hasRole('USER')")
	public  List<OrderResponse> getOrdersByuser(@PathVariable String userId) {
		return orderService.getOrdersByuser(userId);
	}
}
