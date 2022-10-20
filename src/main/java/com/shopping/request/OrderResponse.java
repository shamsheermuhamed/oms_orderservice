package com.shopping.request;

import java.util.List;

import com.shopping.model.Product;

public class OrderResponse {
	
	String orderId;
	String userName;
	String email;
	List<Product> productList;
	Integer orderPrice;
	
	public OrderResponse() {
	}

	public OrderResponse(String userName, String email, List<Product> productList, Integer orderPrice) {
		
		this.userName = userName;
		this.email = email;
		this.productList = productList;
		this.orderPrice = orderPrice;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
