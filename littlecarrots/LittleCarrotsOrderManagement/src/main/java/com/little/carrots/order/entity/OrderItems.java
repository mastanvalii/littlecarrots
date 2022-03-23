package com.little.carrots.order.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="orderitems")
public class OrderItems implements Serializable{
	
	/*
	 
	 create table orderitems (
	orderitemsid bigint auto_increment,
	orderid bigint,
	productid bigint,
	qty bigint,
	productprice bigint not null,
	deliverydate datetime not null,
	ordercodeid bigint,
	constraint orderitems_pk primary key (orderitemsid),
	constraint orderitems_fk1 foreign key (orderid) references orders(orderid),
	constraint orderitems_fk2 foreign key (ordercodeid) references ordercodes (ordercodeid)
	);
 
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "orderitemsid")
	private long orderitemsid;
	
	@Column(name = "orderid")
	private long orderid;
	
	@Column(name = "productid")
	private long productid;
	
	@Column(name = "qty")
	private long qty;
	
	@Column(name = "productprice")
	private long productprice;
	
	@Column(name="deliverydate")
	private Calendar  deliverydate;
	
	@Column(name="ordercodeid")
	private long ordercodeid;

	public OrderItems() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrderItems(long orderid, long productid, long qty, long productprice,
			Calendar deliverydate, long ordercodeid) {
		super();
		
		this.orderid = orderid;
		this.productid = productid;
		this.qty = qty;
		this.productprice = productprice;
		this.deliverydate = deliverydate;
		this.ordercodeid = ordercodeid;
	}

	public long getOrderitemsid() {
		return orderitemsid;
	}

	public void setOrderitemsid(long orderitemsid) {
		this.orderitemsid = orderitemsid;
	}

	public long getOrderid() {
		return orderid;
	}

	public void setOrderid(long orderid) {
		this.orderid = orderid;
	}

	public long getProductid() {
		return productid;
	}

	public void setProductid(long productid) {
		this.productid = productid;
	}

	public long getQty() {
		return qty;
	}

	public void setQty(long qty) {
		this.qty = qty;
	}

	public long getProductprice() {
		return productprice;
	}

	public void setProductprice(long productprice) {
		this.productprice = productprice;
	}

	public Calendar getDeliverydate() {
		return deliverydate;
	}

	public void setDeliverydate(Calendar deliverydate) {
		this.deliverydate = deliverydate;
	}

	public long getOrdercodeid() {
		return ordercodeid;
	}

	public void setOrdercodeid(long ordercodeid) {
		this.ordercodeid = ordercodeid;
	}

	@Override
	public String toString() {
		return "OrderItems [orderitemsid=" + orderitemsid + ", orderid=" + orderid + ", productid=" + productid
				+ ", qty=" + qty + ", productprice=" + productprice + ", deliverydate=" + deliverydate
				+ ", ordercodeid=" + ordercodeid + "]";
	}

	

	

	
}
