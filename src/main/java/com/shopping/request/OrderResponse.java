package com.shopping.request;

import com.shopping.model.Product;

public class OrderResponse {
	
	String orderId;
	String userName;
	String email;
	Product product;
	
	public OrderResponse() {
	}
	
	public OrderResponse(String orderId, String userName, String email, Product product) {
		this.orderId = orderId;
		this.userName = userName;
		this.email = email;
		this.product = product;
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
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
}
