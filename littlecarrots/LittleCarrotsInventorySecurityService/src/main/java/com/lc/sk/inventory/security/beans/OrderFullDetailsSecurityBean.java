package com.lc.sk.inventory.security.beans;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

public class OrderFullDetailsSecurityBean implements Serializable {
	 private long orderid;
		private Timestamp orderdate;
		private long customerid;
		private long totalprice;
		private long ordercodeid;
		private String gateway_orderid;
		private long addressid;
		private OrderItemsBean[] orderitems;
		public OrderFullDetailsSecurityBean() {
			super();
			// TODO Auto-generated constructor stub
		}
// TODO Remove unused code found by UCDetector
// 		public OrderFullDetailsSecurityBean(long orderid, Timestamp orderdate, long customerid, long totalprice,
// 				long ordercodeid, String gateway_orderid, long addressid, OrderItemsBean[] orderitems) {
// 			super();
// 			this.orderid = orderid;
// 			this.orderdate = orderdate;
// 			this.customerid = customerid;
// 			this.totalprice = totalprice;
// 			this.ordercodeid = ordercodeid;
// 			this.gateway_orderid = gateway_orderid;
// 			this.addressid = addressid;
// 			this.orderitems = orderitems;
// 		}
		public long getOrderid() {
			return orderid;
		}
		public void setOrderid(long orderid) {
			this.orderid = orderid;
		}
		public Timestamp getOrderdate() {
			return orderdate;
		}
		public void setOrderdate(Timestamp orderdate) {
			this.orderdate = orderdate;
		}
		public long getCustomerid() {
			return customerid;
		}
		public void setCustomerid(long customerid) {
			this.customerid = customerid;
		}
		public long getTotalprice() {
			return totalprice;
		}
		public void setTotalprice(long totalprice) {
			this.totalprice = totalprice;
		}
		public long getOrdercodeid() {
			return ordercodeid;
		}
		public void setOrdercodeid(long ordercodeid) {
			this.ordercodeid = ordercodeid;
		}
		public String getGateway_orderid() {
			return gateway_orderid;
		}
		public void setGateway_orderid(String gateway_orderid) {
			this.gateway_orderid = gateway_orderid;
		}
		public long getAddressid() {
			return addressid;
		}
		public void setAddressid(long addressid) {
			this.addressid = addressid;
		}
		public OrderItemsBean[] getOrderitems() {
			return orderitems;
		}
		public void setOrderitems(OrderItemsBean[] orderitems) {
			this.orderitems = orderitems;
		}
		@Override
		public String toString() {
			return "OrderFullDetailsSecurityBean [orderid=" + orderid + ", orderdate=" + orderdate + ", customerid="
					+ customerid + ", totalprice=" + totalprice + ", ordercodeid=" + ordercodeid + ", gateway_orderid="
					+ gateway_orderid + ", addressid=" + addressid + ", orderitems=" + Arrays.toString(orderitems)
					+ "]";
		}
		
		
		
}
