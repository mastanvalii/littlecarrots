package com.lc.sk.inventory.security.beans;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Arrays;

public class OrderFullDetailsSecurityBean1 implements Serializable {

	private String paymenttransactionid;
	 private String orderid;
		private Timestamp orderdate;
		private String customerid;
		private String totalprice;
		private String ordercodeid;
		private String gatewayorderid;
		private String addressid;
		private OrderItemsBean[] orderitems;
		public OrderFullDetailsSecurityBean1() {
			super();
			// TODO Auto-generated constructor stub
		}
// TODO Remove unused code found by UCDetector
// 		public OrderFullDetailsSecurityBean1(String paymenttransactionid, String orderid, Timestamp orderdate,
// 				String customerid, String totalprice, String ordercodeid, String gatewayorderid, String addressid,
// 				OrderItemsBean[] orderitems) {
// 			super();
// 			this.paymenttransactionid = paymenttransactionid;
// 			this.orderid = orderid;
// 			this.orderdate = orderdate;
// 			this.customerid = customerid;
// 			this.totalprice = totalprice;
// 			this.ordercodeid = ordercodeid;
// 			this.gatewayorderid = gatewayorderid;
// 			this.addressid = addressid;
// 			this.orderitems = orderitems;
// 		}
		public String getPaymenttransactionid() {
			return paymenttransactionid;
		}
		public void setPaymenttransactionid(String paymenttransactionid) {
			this.paymenttransactionid = paymenttransactionid;
		}
		public String getOrderid() {
			return orderid;
		}
		public void setOrderid(String orderid) {
			this.orderid = orderid;
		}
		public Timestamp getOrderdate() {
			return orderdate;
		}
		public void setOrderdate(Timestamp orderdate) {
			this.orderdate = orderdate;
		}
		public String getCustomerid() {
			return customerid;
		}
		public void setCustomerid(String customerid) {
			this.customerid = customerid;
		}
		public String getTotalprice() {
			return totalprice;
		}
		public void setTotalprice(String totalprice) {
			this.totalprice = totalprice;
		}
		public String getOrdercodeid() {
			return ordercodeid;
		}
		public void setOrdercodeid(String ordercodeid) {
			this.ordercodeid = ordercodeid;
		}
		public String getGatewayorderid() {
			return gatewayorderid;
		}
		public void setGatewayorderid(String gatewayorderid) {
			this.gatewayorderid = gatewayorderid;
		}
		public String getAddressid() {
			return addressid;
		}
		public void setAddressid(String addressid) {
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
			return "OrderFullDetailsSecurityBean1 [paymenttransactionid=" + paymenttransactionid + ", orderid="
					+ orderid + ", orderdate=" + orderdate + ", customerid=" + customerid + ", totalprice=" + totalprice
					+ ", ordercodeid=" + ordercodeid + ", gatewayorderid=" + gatewayorderid + ", addressid=" + addressid
					+ ", orderitems=" + Arrays.toString(orderitems) + "]";
		}
	
		
		
}
