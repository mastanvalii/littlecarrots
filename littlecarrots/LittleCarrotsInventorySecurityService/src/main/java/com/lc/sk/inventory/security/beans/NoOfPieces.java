package com.lc.sk.inventory.security.beans;

import java.io.Serializable;

public class NoOfPieces implements Serializable {

	private long pieceid;

	private long productid;

	private long noofset;

	public NoOfPieces() {
		super();
	}

// TODO Remove unused code found by UCDetector
// 	public NoOfPieces(long pieceid, long productid, long noofset) {
// 		super();
// 		this.pieceid = pieceid;
// 		this.productid = productid;
// 		this.noofset = noofset;
// 	}

	public long getPieceid() {
		return pieceid;
	}

	public void setPieceid(long pieceid) {
		this.pieceid = pieceid;
	}

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
		return "NoOfPieces [pieceid=" + pieceid + ", productid=" + productid + ", noofset=" + noofset + "]";
	}

}
