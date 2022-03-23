package com.lc.sk.inventory.security.beans;

import java.io.Serializable;

public class SellerToWarehouse implements Serializable {

	private long serialid;

	private long warehouseid;

	private long sellerid;

	public SellerToWarehouse() {
		super();
		// TODO Auto-generated constructor stub
	}

// TODO Remove unused code found by UCDetector
// 	public SellerToWarehouse(long serialid, long warehouseid, long sellerid) {
// 		super();
// 		this.serialid = serialid;
// 		this.warehouseid = warehouseid;
// 		this.sellerid = sellerid;
// 	}

	public long getSerialid() {
		return serialid;
	}

	public void setSerialid(long serialid) {
		this.serialid = serialid;
	}

	public long getWarehouseid() {
		return warehouseid;
	}

	public void setWarehouseid(long warehouseid) {
		this.warehouseid = warehouseid;
	}

	public long getSellerid() {
		return sellerid;
	}

	public void setSellerid(long sellerid) {
		this.sellerid = sellerid;
	}

	@Override
	public String toString() {
		return "SellerToWarehouse [serialid=" + serialid + ", warehouseid=" + warehouseid + ", sellerid=" + sellerid
				+ "]";
	}

}
