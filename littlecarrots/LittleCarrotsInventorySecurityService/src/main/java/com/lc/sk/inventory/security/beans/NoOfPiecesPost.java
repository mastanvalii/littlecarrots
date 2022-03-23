package com.lc.sk.inventory.security.beans;

import java.io.Serializable;

public class NoOfPiecesPost implements Serializable {
	
	private long productid;

	private long noofset;

	public NoOfPiecesPost() {
		super();
		// TODO Auto-generated constructor stub
	}

// TODO Remove unused code found by UCDetector
// 	public NoOfPiecesPost(long productid, long noofset) {
// 		super();
// 		this.productid = productid;
// 		this.noofset = noofset;
// 	}

	public long getProductid() {
		return productid;
	}

	public void setProductid(long productid) {
		this.productid = productid;
	}

	public long getNoofset() {
		return noofset;
	}

	public void setNoofset(long noofset) {
		this.noofset = noofset;
	}

	@Override
	public String toString() {
		return "NoOfPiecesPost [productid=" + productid + ", noofset=" + noofset + "]";
	}
	
	

}
