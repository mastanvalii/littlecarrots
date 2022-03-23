package com.lc.sk.inventory.security.beans;

import java.io.Serializable;

public class ProductQuantities implements Serializable {
	private long custid;
	private long quantity;
	private long sizeid;

	public long getCustid() {
		return custid;
	}

	public void setCustid(long custid) {
		this.custid = custid;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	public long getSizeid() {
		return sizeid;
	}

	public void setSizeid(long sizeid) {
		this.sizeid = sizeid;
	}

// TODO Remove unused code found by UCDetector
// 	public ProductQuantities(long custid, long quantity, long sizeid) {
// 		super();
// 		this.custid = custid;
// 		this.quantity = quantity;
// 		this.sizeid = sizeid;
// 	}

	public ProductQuantities() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "ProductQuantities [custid=" + custid + ", quantity=" + quantity + ", sizeid=" + sizeid + "]";
	}

// TODO Remove unused code found by UCDetector
// 	public ProductQuantities(long quantity, long sizeid) {
// 		super();
// 		this.quantity = quantity;
// 		this.sizeid = sizeid;
// 	}

}
