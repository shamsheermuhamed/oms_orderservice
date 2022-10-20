package com.shopping.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.shopping.exception.ProductNotFoundException;
import com.shopping.exception.ProductOutOfStockException;
import com.shopping.exception.RecordNotFoundException;
import com.shopping.exception.UserNotFoundException;
import com.shopping.model.Order;
import com.shopping.model.Product;
import com.shopping.repository.OrderRepository;
import com.shopping.repository.ProductRepository;
import com.shopping.repository.UserRepository;
import com.shopping.request.MessageResponse;
import com.shopping.request.OrderRequest;
import com.shopping.request.OrderResponse;
import com.shopping.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	
	private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
	
	private static String topic = "ordertopic";
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	private KafkaTemplate<String, Object> template;
	
	@Override
	public ResponseEntity<Object> createOrder(OrderRequest orderrequest) {
		logger.info("Entering into service-createOrder");
		Order order=new Order();
		order.setProductList(orderrequest.getProductList());
		order.setUser(orderrequest.getUser());
		order.setOrderPrice(orderrequest.getOrderPrice());
		if((userRepository.findById(order.getUser().getId())).isEmpty()){
			throw new UserNotFoundException();
		}	
		for(Product product: orderrequest.getProductList()) {
			Optional<Product> productOptional= productRepository.findById(product.getId());
			if(productRepository.findById(product.getId()).isEmpty()){
				throw new ProductNotFoundException();
			}
			else if(productOptional.get().getUnitStock()==null || productOptional.get().getUnitStock()<= 0 ) {
				throw new ProductOutOfStockException();
			}
			else {
				Product getproduct= productOptional.get();
				product.setUnitStock(getproduct.getUnitStock()-1);	
				productRepository.save(getproduct);
			}
		}
		
		orderRepository.save(order);
//		template.send(topic,orderRepository.findById(orderRepository.save(order).getId()));
		return  ResponseEntity.ok(new MessageResponse("Order created successfully"));
	}

	@Override
	public List<OrderResponse> getAllOrders() {
		logger.info("Entering into service-getAllOrders");
		List<OrderResponse> orderResponseList= new ArrayList<>();
		List<Order> orderList= orderRepository.findAll();
		try {
			if(!orderList.isEmpty()) {
				for(Order order: orderList) {
					OrderResponse orderResponse= new OrderResponse();
					orderResponse.setOrderId(order.getId());
					orderResponse.setUserName(order.getUser().getUsername());
					orderResponse.setEmail(order.getUser().getEmail());
					orderResponse.setProductList(order.getProductList());
					orderResponseList.add(orderResponse);
				}
			}
			else {
				throw new RecordNotFoundException();
			}
		} catch(Exception e) {
			throw new RecordNotFoundException();
		}
		
		return orderResponseList;
	}

	@Override
	public List<OrderResponse> getOrdersForProduct(String productId) {
		logger.info("Entering into getOrdersForProduct");
		List<OrderResponse> orderResponseList= new ArrayList<>();
//		List<Order> orderList= orderRepository.findByProductList(productId);
//		try {
//			if(!orderList.isEmpty()) {
//				for(Order order: orderList) {
//					OrderResponse orderResponse= new OrderResponse();
//					orderResponse.setOrderId(order.getId());
//					orderResponse.setUserName(order.getUser().getUsername());
//					orderResponse.setEmail(order.getUser().getEmail());
//					orderResponse.setProduct(order.getProductList());
//					orderResponseList.add(orderResponse);
//				}
//			}
//			else {
//				throw new RecordNotFoundException();
//			}
//		} catch(Exception e) {
//			throw new RecordNotFoundException();
//		}
		
		return orderResponseList;
	}

	@Override
	public List<OrderResponse> getOrdersByuser(String userId) {
		logger.info("Entering into service-getAllOrders");
		List<OrderResponse> orderResponseList= new ArrayList<>();
		List<Order> orderList= orderRepository.findByUser(userId);
		try {
			if(!orderList.isEmpty()) {
				for(Order order: orderList) {
					OrderResponse orderResponse= new OrderResponse();
					orderResponse.setOrderId(order.getId());
					orderResponse.setUserName(order.getUser().getUsername());
					orderResponse.setEmail(order.getUser().getEmail());
					orderResponse.setProductList(order.getProductList());
					orderResponse.setOrderPrice(order.getOrderPrice());
					orderResponseList.add(orderResponse);
				}
			}
			else {
				throw new RecordNotFoundException();
			}
		} catch(Exception e) {
			throw new RecordNotFoundException();
		}
		
		return orderResponseList;
	}

}
