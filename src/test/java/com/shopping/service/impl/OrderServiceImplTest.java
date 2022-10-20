//package com.shopping.service.impl;
//
//import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.mockito.Mockito.when;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import com.shopping.model.Order;
//import com.shopping.model.Product;
//import com.shopping.model.User;
//import com.shopping.repository.OrderRepository;
//import com.shopping.repository.ProductRepository;
//import com.shopping.repository.UserRepository;
//import com.shopping.request.OrderRequest;
//import com.shopping.request.OrderResponse;
//
//@ExtendWith(SpringExtension.class)
//public class OrderServiceImplTest {
//	
//	@InjectMocks
//	private OrderServiceImpl orderServiceImpl;
//	
//	@Mock
//	private OrderRepository orderRepository;
//	
//	@Mock
//	private ProductRepository productRepository;
//	
//	@Mock
//	private UserRepository userRepository;
//
//	@Test
//	void testCreateOrder() {
//		OrderRequest orderrequest=new OrderRequest();
//		User user=new User();
//		Product product=new Product();
//		product.setId("1");
//		product.setUnitStock(2);
//		user.setId("1");
//		orderrequest.setId("123");
//		orderrequest.setUser(user);
//		orderrequest.setProduct(product);
//		Optional<User> optionalUser= Optional.of(user);
//		Optional<Product> optionalProduct= Optional.of(product);
//		when(userRepository.findById("1")).thenReturn(optionalUser);
//		when(productRepository.findById("1")).thenReturn(optionalProduct);
//		assertNotNull(orderServiceImpl.createOrder(orderrequest));
//		assertDoesNotThrow(()->{
//			orderServiceImpl.createOrder(orderrequest);
//		});
//	}
//	
//	@Test
//	void testGetAllOrders() {
//		List<Order> orderList= new ArrayList<>();
//		List<OrderResponse> orderResponseList= new ArrayList<>();
//		OrderResponse orderResponse=new OrderResponse();
//		orderResponse.setOrderId("1");
//		orderResponse.setEmail("abc@gmail.com");
//		orderResponse.setUserName("abc");
//		orderResponse.setProduct(new Product());
//		orderResponseList.add(orderResponse);
//		Order order=new Order();
//		User user=new User();
//		user.setUsername("abc");
//		user.setEmail("abc@gmail.com");
//		Product product=new Product();
//		order.setId("1");
//		order.setUser(user);
//		order.setProduct(product);
//		orderList.add(order);
//		when(orderRepository.findAll()).thenReturn(orderList);
//		List<OrderResponse> actual= orderServiceImpl.getAllOrders();
//		assertNotNull(actual);
//		assertEquals(actual.get(0).getEmail(),orderResponseList.get(0).getEmail());
//	}
//}
