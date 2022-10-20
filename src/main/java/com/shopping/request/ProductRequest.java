package com.shopping.request;

import java.util.List;

public class ProductRequest {

	private String id;
	private String productName;
	private String description;
	private double price;
	private List<String> features;
	private boolean active;
	private Integer unitStock;
	private String imageUrl;
	public ProductRequest() {
	}
	public ProductRequest(String productName, String description, double price, List<String> features, boolean active,
			Integer unitStock, String imageUrl) {
		super();
		this.productName = productName;
		this.description = description;
		this.price = price;
		this.features = features;
		this.active = active;
		this.unitStock = unitStock;
		this.imageUrl = imageUrl;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public List<String> getFeatures() {
		return features;
	}
	public void setFeatures(List<String> features) {
		this.features = features;
	}
	public boolean getActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public Integer getUnitStock() {
		return unitStock;
	}
	public void setUnitStock(Integer unitStock) {
		this.unitStock = unitStock;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
}
