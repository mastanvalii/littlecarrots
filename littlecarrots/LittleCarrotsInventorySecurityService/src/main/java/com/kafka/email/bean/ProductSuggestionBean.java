package com.kafka.email.bean;

import java.io.Serializable;
import java.util.List;

public class ProductSuggestionBean implements Serializable {

	
	private String name;
	private String message;
	private List<SuggestedProducts> products;
	public ProductSuggestionBean() {
		super();
		// TODO Auto-generated constructor stub
	}
// TODO Remove unused code found by UCDetector
// 	public ProductSuggestionBean(String name, String message, List<SuggestedProducts> products) {
// 		super();
// 		this.name = name;
// 		this.message = message;
// 		this.products = products;
// 	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<SuggestedProducts> getProducts() {
		return products;
	}
	public void setProducts(List<SuggestedProducts> products) {
		this.products = products;
	}
	@Override
	public String toString() {
		return "ProductSuggestionBean [name=" + name + ", message=" + message + ", products=" + products + "]";
	}

	
}
