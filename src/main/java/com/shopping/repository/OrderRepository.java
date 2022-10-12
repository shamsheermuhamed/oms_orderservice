package com.shopping.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.shopping.model.Order;

public interface OrderRepository extends MongoRepository<Order, String> {

	List<Order> findByProduct(String productId);

}
