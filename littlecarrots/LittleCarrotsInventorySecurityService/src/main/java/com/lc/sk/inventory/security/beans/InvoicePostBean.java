package com.lc.sk.inventory.security.beans;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

public class InvoicePostBean implements Serializable{
	
	private String sid;

	private String orid;
	
	private String dateoforder;
	
	private String dateofinvoice;
	
	private String total;
	
	private String shipping;
	
	private String paymentmode;
	
	private String transactionid;
	
	private String statusoftheinvoice;
	
	private String user;
	
	private String invoiceid;
	
	private SoldProductsPostBean[] soldproditem;
	
	private String status;

	public InvoicePostBean() {
		super();
		// TODO Auto-generated constructor stub
	}

// TODO Remove unused code found by UCDetector
// 	public InvoicePostBean(String sid, String orid, String dateoforder, String dateofinvoice, String total,
// 			String shipping, String paymentmode, String transactionid, String statusoftheinvoice, String user,
// 			String invoiceid, SoldProductsPostBean[] soldproditem, String status) {
// 		super();
// 		this.sid = sid;
// 		this.orid = orid;
// 		this.dateoforder = dateoforder;
// 		this.dateofinvoice = dateofinvoice;
// 		this.total = total;
// 		this.shipping = shipping;
// 		this.paymentmode = paymentmode;
// 		this.transactionid = transactionid;
// 		this.statusoftheinvoice = statusoftheinvoice;
// 		this.user = user;
// 		this.invoiceid = invoiceid;
// 		this.soldproditem = soldproditem;
// 		this.status = status;
// 	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getOrid() {
		return orid;
	}

	public void setOrid(String orid) {
		this.orid = orid;
	}

	public String getDateoforder() {
		return dateoforder;
	}

	public void setDateoforder(String dateoforder) {
		this.dateoforder = dateoforder;
	}

	public String getDateofinvoice() {
		return dateofinvoice;
	}

	public void setDateofinvoice(String dateofinvoice) {
		this.dateofinvoice = dateofinvoice;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getShipping() {
		return shipping;
	}

	public void setShipping(String shipping) {
		this.shipping = shipping;
	}

	public String getPaymentmode() {
		return paymentmode;
	}

	public void setPaymentmode(String paymentmode) {
		this.paymentmode = paymentmode;
	}

	public String getTransactionid() {
		return transactionid;
	}

	public void setTransactionid(String transactionid) {
		this.transactionid = transactionid;
	}

	public String getStatusoftheinvoice() {
		return statusoftheinvoice;
	}

	public void setStatusoftheinvoice(String statusoftheinvoice) {
		this.statusoftheinvoice = statusoftheinvoice;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getInvoiceid() {
		return invoiceid;
	}

	public void setInvoiceid(String invoiceid) {
		this.invoiceid = invoiceid;
	}

	public SoldProductsPostBean[] getSoldproditem() {
		return soldproditem;
	}

	public void setSoldproditem(SoldProductsPostBean[] soldproditem) {
		this.soldproditem = soldproditem;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "InvoicePostBean [sid=" + sid + ", orid=" + orid + ", dateoforder=" + dateoforder + ", dateofinvoice="
				+ dateofinvoice + ", total=" + total + ", shipping=" + shipping + ", paymentmode=" + paymentmode
				+ ", transactionid=" + transactionid + ", statusoftheinvoice=" + statusoftheinvoice + ", user=" + user
				+ ", invoiceid=" + invoiceid + ", soldproditem=" + Arrays.toString(soldproditem) + ", status=" + status
				+ "]";
	}

	
	
	
	
	

}
