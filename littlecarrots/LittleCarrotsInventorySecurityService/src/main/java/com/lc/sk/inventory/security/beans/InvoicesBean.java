package com.lc.sk.inventory.security.beans;

import java.io.Serializable;
import java.util.Date;



public class InvoicesBean implements Serializable {
	
	private long inid;
	
	private long sid;

	private String orid;
	
	private Date dateoforder;
	
	private Date dateofinvoice;
	
	private float total;
	
	private float shipping;
	
	private String paymentmode;
	
	private String transactionid;
	
	private String statusoftheinvoice;
	
	private String user;
	
	private String invoiceid;

	public InvoicesBean() {
		super();
		// TODO Auto-generated constructor stub
	}

// TODO Remove unused code found by UCDetector
// 	public InvoicesBean(long inid, long sid, String orid, Date dateoforder, Date dateofinvoice, float total,
// 			float shipping, String paymentmode, String transactionid, String statusoftheinvoice, String user,
// 			String invoiceid) {
// 		super();
// 		this.inid = inid;
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
// 	}

	public long getInid() {
		return inid;
	}

	public void setInid(long inid) {
		this.inid = inid;
	}

	public long getSid() {
		return sid;
	}

	public void setSid(long sid) {
		this.sid = sid;
	}

	public String getOrid() {
		return orid;
	}

	public void setOrid(String orid) {
		this.orid = orid;
	}

	public Date getDateoforder() {
		return dateoforder;
	}

	public void setDateoforder(Date dateoforder) {
		this.dateoforder = dateoforder;
	}

	public Date getDateofinvoice() {
		return dateofinvoice;
	}

	public void setDateofinvoice(Date dateofinvoice) {
		this.dateofinvoice = dateofinvoice;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public float getShipping() {
		return shipping;
	}

	public void setShipping(float shipping) {
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

	@Override
	public String toString() {
		return "InvoicesBean [inid=" + inid + ", sid=" + sid + ", orid=" + orid + ", dateoforder=" + dateoforder
				+ ", dateofinvoice=" + dateofinvoice + ", total=" + total + ", shipping=" + shipping + ", paymentmode="
				+ paymentmode + ", transactionid=" + transactionid + ", statusoftheinvoice=" + statusoftheinvoice
				+ ", user=" + user + ", invoiceid=" + invoiceid + "]";
	}
	
	

}
