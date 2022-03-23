package com.little.carrots.order.bean;

import java.io.Serializable;

public class Key implements Serializable {
	
	private String name;

	private String value;

	public Key() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Key(String name, String value) {
		super();
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Key [name=" + name + ", value=" + value + "]";
	}
	
	
}
