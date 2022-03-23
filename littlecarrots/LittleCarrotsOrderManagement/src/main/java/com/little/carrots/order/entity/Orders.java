package com.little.carrots.order.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="orders")
public class Orders implements Serializable{
	
	/*create table orders(
orderid bigint auto_increment,
orderdate datetime not null,
customerid bigint,
totalprice float,
ordercodeid bigint,
gateway_orderid varchar(500) ,
addressid bigint,
constraint orders_pk primary key (orderid),
constraint orders_fk foreign key (ordercodeid) references ordercodes (ordercodeid)
);
*/

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "orderid")
	private long orderid;
	
	@Column(name = "orderdate")
	private Timestamp orderdate;
	
	@Column(name = "customerid")
	private long customerid;
	
	@Column(name = "totalprice")
	private double totalprice;
	
	@Column(name = "ordercodeid")
	private long ordercodeid;
	
	@Column(name = "gateway_orderid")
	private String gateway_orderid;
	
	@Column(name = "addressid")
	private long addressid;

	public Orders() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Orders(Timestamp orderdate, long customerid, double totalprice, long ordercodeid, String gateway_orderid,
			long addressid) {
		super();
		this.orderdate = orderdate;
		this.customerid = customerid;
		this.totalprice = totalprice;
		this.ordercodeid = ordercodeid;
		this.gateway_orderid = gateway_orderid;
		this.addressid = addressid;
	}

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

	public double getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(double totalprice) {
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

	@Override
	public String toString() {
		return "Orders [orderid=" + orderid + ", orderdate=" + orderdate + ", customerid=" + customerid
				+ ", totalprice=" + totalprice + ", ordercodeid=" + ordercodeid + ", gateway_orderid=" + gateway_orderid
				+ ", addressid=" + addressid + "]";
	}

	

	

	

}
