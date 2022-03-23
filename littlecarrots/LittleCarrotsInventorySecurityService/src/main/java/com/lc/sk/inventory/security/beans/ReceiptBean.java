package com.lc.sk.inventory.security.beans;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;



public class ReceiptBean implements Serializable {
	private long invoiceid;
	private String soldfrom;
	private String orderid;
	private String sellerinvoice;
	private String awb;
	private String trackingid;
	private String state;
	private String hsn;
	private long tax;
	private String taxtype;

	private double discount;
	private double taxamount;
	private double totalamount;
	private String paidtype;
	private String transactionid;
	private Timestamp invoicedate;
	private Timestamp orderdate;
	private List<SoldProducts> soldProducts;
	private double shippingcharges;	
	private double shippingtaxamount;	
	private double shippingtotalamount;

	public ReceiptBean() {
		// TODO Auto-generated constructor stub
	}


// TODO Remove unused code found by UCDetector
// 	public ReceiptBean(long invoiceid, String soldfrom, String orderid, String sellerinvoice, String awb,
// 			String trackingid, String state, String hsn, long tax, String taxtype, double discount, double taxamount,
// 			double totalamount, String paidtype, String transactionid, Timestamp invoicedate, Timestamp orderdate,
// 			List<SoldProducts> soldProducts, double shippingcharges, double shippingtaxamount,
// 			double shippingtotalamount) {
// 		super();
// 		this.invoiceid = invoiceid;
// 		this.soldfrom = soldfrom;
// 		this.orderid = orderid;
// 		this.sellerinvoice = sellerinvoice;
// 		this.awb = awb;
// 		this.trackingid = trackingid;
// 		this.state = state;
// 		this.hsn = hsn;
// 		this.tax = tax;
// 		this.taxtype = taxtype;
// 		this.discount = discount;
// 		this.taxamount = taxamount;
// 		this.totalamount = totalamount;
// 		this.paidtype = paidtype;
// 		this.transactionid = transactionid;
// 		this.invoicedate = invoicedate;
// 		this.orderdate = orderdate;
// 		this.soldProducts = soldProducts;
// 		this.shippingcharges = shippingcharges;
// 		this.shippingtaxamount = shippingtaxamount;
// 		this.shippingtotalamount = shippingtotalamount;
// 	}


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

	public List<SoldProducts> getSoldProducts() {
		return soldProducts;
	}

	public void setSoldProducts(List<SoldProducts> soldProducts) {
		this.soldProducts = soldProducts;
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

	@Override
	public String toString() {
		return "ReceiptBean [invoiceid=" + invoiceid + ", soldfrom=" + soldfrom + ", orderid=" + orderid
				+ ", sellerinvoice=" + sellerinvoice + ", awb=" + awb + ", trackingid=" + trackingid + ", state="
				+ state + ", hsn=" + hsn + ", tax=" + tax + ", taxtype=" + taxtype + ", discount=" + discount
				+ ", taxamount=" + taxamount + ", totalamount=" + totalamount + ", paidtype=" + paidtype
				+ ", transactionid=" + transactionid + ", invoicedate=" + invoicedate + ", orderdate=" + orderdate
				+ ", soldProducts=" + soldProducts + "]";
	}



}
