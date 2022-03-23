package com.lc.sk.inventory.security.beans;

public class SellerToWarehouseBean {
	private long warehouseid;

	private long sellerid;

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

// TODO Remove unused code found by UCDetector
// 	public SellerToWarehouseBean(long warehouseid, long sellerid) {
// 		super();
// 		this.warehouseid = warehouseid;
// 		this.sellerid = sellerid;
// 	}

	public SellerToWarehouseBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "SellerToWarehouseBean [warehouseid, sellerid]";
	}
	
}
