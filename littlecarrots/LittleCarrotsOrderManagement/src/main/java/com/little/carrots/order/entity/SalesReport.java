package com.little.carrots.order.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "salesreport")
public class SalesReport implements Serializable {

	/*
	 * create table salesreport( invoiceid bigint auto_increment, #lc id soldfrom
	 * varchar(100), #sold from [web, local, amazon, flipkart, other] orderid
	 * varchar(100), # order id only for third party sellers sellerinvoice
	 * varchar(100), # invoice id only for third party sellers awb varchar(100), #
	 * awb number only for third party sellers trackingid varchar(100), # tracking
	 * id for both third party and lc state varchar(100), # sold to the specific
	 * state hsn varchar(100), # hsn code tax bigint, # tax percentage taxtype
	 * varchar(100), # tax type IGST itemssold bigint, # foreign key for sold item
	 * table discount float, # discount amount taxamount float, # tax amount
	 * totalamount float, # total amount paidtype varchar(200), # paid type [gpay,
	 * phonepe, cash, thirdparty sells] transactionid varchar(200), # only
	 * transaction id constraint salesreport_pk primary key (invoiceid), constraint
	 * salesreport_fk foreign key(itemssold) references solditems(itemssoldid) );
	 * ALTER TABLE salesreport ADD invoicedate datetime , add orderdate datetime;
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "invoiceid")
	private long invoiceid;

	@Column(name = "soldfrom")
	private String soldfrom;

	@Column(name = "orderid")
	private String orderid;

	@Column(name = "sellerinvoice")
	private String sellerinvoice;

	@Column(name = "awb")
	private String awb;

	@Column(name = "trackingid")
	private String trackingid;

	@Column(name = "state")
	private String state;

	@Column(name = "hsn")
	private String hsn;

	@Column(name = "tax")
	private long tax;

	@Column(name = "taxtype")
	private String taxtype;

	@Column(name = "discount")
	private double discount;

	@Column(name = "taxamount")
	private double taxamount;

	@Column(name = "totalamount")
	private double totalamount;

	@Column(name = "paidtype")
	private String paidtype;

	@Column(name = "transactionid")
	private String transactionid;

	@Column(name = "invoicedate")
	private Timestamp invoicedate;

	@Column(name = "orderdate")
	private Timestamp orderdate; 
	
	
	@Column(name="shippingcharges")
	private double shippingcharges;
	
	@Column(name="shippingtaxamount")
	private double shippingtaxamount;
	
	@Column(name="shippingtotalamount")
	private double shippingtotalamount;

	public SalesReport() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SalesReport( String soldfrom, String orderid, String sellerinvoice, String awb,
			String trackingid, String state, String hsn, long tax, String taxtype, double discount, double taxamount,
			double totalamount, String paidtype, String transactionid, Timestamp invoicedate, Timestamp orderdate,
			double shippingcharges, double shippingtaxamount, double shippingtotalamount) {
		super();
		//this.invoiceid = invoiceid;
		this.soldfrom = soldfrom;
		this.orderid = orderid;
		this.sellerinvoice = sellerinvoice;
		this.awb = awb;
		this.trackingid = trackingid;
		this.state = state;
		this.hsn = hsn;
		this.tax = tax;
		this.taxtype = taxtype;
		this.discount = discount;
		this.taxamount = taxamount;
		this.totalamount = totalamount;
		this.paidtype = paidtype;
		this.transactionid = transactionid;
		this.invoicedate = invoicedate;
		this.orderdate = orderdate;
		this.shippingcharges = shippingcharges;
		this.shippingtaxamount = shippingtaxamount;
		this.shippingtotalamount = shippingtotalamount;
	}

	public long getInvoiceid() {
		return invoiceid;
	}

	public void setInvoiceid(long invoiceid) {
		this.invoiceid = invoiceid;
	}

	public String getSoldfrom() {
		return soldfrom;
	}

	public void setSoldfrom(String soldfrom) {
		this.soldfrom = soldfrom;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getSellerinvoice() {
		return sellerinvoice;
	}

	public void setSellerinvoice(String sellerinvoice) {
		this.sellerinvoice = sellerinvoice;
	}

	public String getAwb() {
		return awb;
	}

	public void setAwb(String awb) {
		this.awb = awb;
	}

	public String getTrackingid() {
		return trackingid;
	}

	public void setTrackingid(String trackingid) {
		this.trackingid = trackingid;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getHsn() {
		return hsn;
	}

	public void setHsn(String hsn) {
		this.hsn = hsn;
	}

	public long getTax() {
		return tax;
	}

	public void setTax(long tax) {
		this.tax = tax;
	}

	public String getTaxtype() {
		return taxtype;
	}

	public void setTaxtype(String taxtype) {
		this.taxtype = taxtype;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public double getTaxamount() {
		return taxamount;
	}

	public void setTaxamount(double taxamount) {
		this.taxamount = taxamount;
	}

	public double getTotalamount() {
		return totalamount;
	}

	public void setTotalamount(double totalamount) {
		this.totalamount = totalamount;
	}

	public String getPaidtype() {
		return paidtype;
	}

	public void setPaidtype(String paidtype) {
		this.paidtype = paidtype;
	}

	public String getTransactionid() {
		return transactionid;
	}

	public void setTransactionid(String transactionid) {
		this.transactionid = transactionid;
	}

	public Timestamp getInvoicedate() {
		return invoicedate;
	}

	public void setInvoicedate(Timestamp invoicedate) {
		this.invoicedate = invoicedate;
	}

	public Timestamp getOrderdate() {
		return orderdate;
	}

	public void setOrderdate(Timestamp orderdate) {
		this.orderdate = orderdate;
	}

	public double getShippingcharges() {
		return shippingcharges;
	}

	public void setShippingcharges(double shippingcharges) {
		this.shippingcharges = shippingcharges;
	}

	public double getShippingtaxamount() {
		return shippingtaxamount;
	}

	public void setShippingtaxamount(double shippingtaxamount) {
		this.shippingtaxamount = shippingtaxamount;
	}

	public double getShippingtotalamount() {
		return shippingtotalamount;
	}

	public void setShippingtotalamount(double shippingtotalamount) {
		this.shippingtotalamount = shippingtotalamount;
	}

	@Override
	public String toString() {
		return "SalesReport [invoiceid=" + invoiceid + ", soldfrom=" + soldfrom + ", orderid=" + orderid
				+ ", sellerinvoice=" + sellerinvoice + ", awb=" + awb + ", trackingid=" + trackingid + ", state="
				+ state + ", hsn=" + hsn + ", tax=" + tax + ", taxtype=" + taxtype + ", discount=" + discount
				+ ", taxamount=" + taxamount + ", totalamount=" + totalamount + ", paidtype=" + paidtype
				+ ", transactionid=" + transactionid + ", invoicedate=" + invoicedate + ", orderdate=" + orderdate
				+ ", shippingcharges=" + shippingcharges + ", shippingtaxamount=" + shippingtaxamount
				+ ", shippingtotalamount=" + shippingtotalamount + "]";
	}
	
	

	

}
