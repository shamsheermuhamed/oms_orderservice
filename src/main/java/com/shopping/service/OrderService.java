package com.shopping.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.shopping.model.Order;
import com.shopping.request.OrderResponse;

public interface OrderService {

	ResponseEntity<Object> createOrder(Order order);

	List<OrderResponse> getAllOrders();

	List<OrderResponse> getOrdersForProduct(String productId);

}
