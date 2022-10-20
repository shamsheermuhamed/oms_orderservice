package com.shopping.request;

import java.util.List;

import com.shopping.model.Product;
import com.shopping.model.User;

public class OrderRequest {

	private String id;
	
	private User user;
	  
	private List<Product> productList;
	
	private Integer orderPrice;
	  
	public OrderRequest() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

	public Integer getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(Integer orderPrice) {
		this.orderPrice = orderPrice;
	}

}
