package com.kafka.email.bean;

import java.io.Serializable;

public class OrderedData implements Serializable {
	
	private long orderid;

	public OrderedData() {
		super();
	}

	public OrderedData(long orderid) {
		super();
		this.orderid = orderid;
	}

	public long getOrderid() {
		return orderid;
	}

	public void setOrderid(long orderid) {
		this.orderid = orderid;
	}

	@Override
	public String toString() {
		return "OrderedData [orderid=" + orderid + "]";
	}
 
	
 
}
