package com.little.carrots.order.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="cart")
public class Cartmanagement implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1883534882354958732L;

	@Id
	@Column(name="cartid")
	private long cartid;
	
	@Column(name="customerid")
	private long customerid;
	
	@Column(name="productid")
	private long productid;

	@Column(name="productprice")
	private double productprice;

	@Column(name="quantity")
	private long quantity;
	
	@Column(name="cartdate")
	private Timestamp cartdate;
	
	
	@Column(name="totalprice")
	private long totalprice;


	public long getCartid() {
		return cartid;
	}


	public void setCartid(long cartid) {
		this.cartid = cartid;
	}


	public long getCustomerid() {
		return customerid;
	}


	public void setCustomerid(long customerid) {
		this.customerid = customerid;
	}


	public long getProductid() {
		return productid;
	}


	public void setProductid(long productid) {
		this.productid = productid;
	}


	public double getProductprice() {
		return productprice;
	}


	public void setProductprice(long productprice) {
		this.productprice = productprice;
	}


	public long getQuantity() {
		return quantity;
	}


	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}


	public Timestamp getCartdate() {
		return cartdate;
	}


	public void setCartdate(Timestamp cartdate) {
		this.cartdate = cartdate;
	}


	public long getTotalprice() {
		return totalprice;
	}


	public void setTotalprice(long totalprice) {
		this.totalprice = totalprice;
	}


// TODO Remove unused code found by UCDetector
// 	public Cartmanagement(long cartid, long customerid, long productid, double productprice, long quantity, Timestamp cartdate,
// 			long totalprice) {
// 		super();
// 		this.cartid = cartid;
// 		this.customerid = customerid;
// 		this.productid = productid;
// 		this.productprice = productprice;
// 		this.quantity = quantity;
// 		this.cartdate = cartdate;
// 		this.totalprice = totalprice;
// 	}

	public Cartmanagement() {
		super();
		// TODO Auto-generated constructor stub
	}


	



	public Cartmanagement(long customerid, long productid, double productprice, long quantity, Timestamp cartdate,
			long totalprice) {
		super();
		this.customerid = customerid;
		this.productid = productid;
		this.productprice = productprice;
		this.quantity = quantity;
		this.cartdate = cartdate;
		this.totalprice = totalprice;
	}


	@Override
	public String toString() {
		return "Cartmanagement [cartid=" + cartid + ", customerid=" + customerid + ", productid=" + productid
				+ ", productprice=" + productprice + ", quantity=" + quantity + ", cartdate=" + cartdate
				+ ", totalprice=" + totalprice + "]";
	}

}
