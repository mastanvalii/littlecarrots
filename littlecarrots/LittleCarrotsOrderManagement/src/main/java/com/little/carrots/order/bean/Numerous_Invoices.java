package com.little.carrots.order.bean;

import java.io.Serializable;
import java.util.List;

import com.little.carrots.order.entity.Soldproducts;
public class Numerous_Invoices implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7237481037454893L;
	private long inid;
	private String sid;
	private String source;
	private String orid;
	private String dateoforder;
	private String dateofinvoice;
	private String year;
	private String month;
	private String total;
	private String shipping;
	private String paymentmode;
	private String transactionid;
	private String statusoftheinvoice;
	private String invoiceid;
	private List<Soldproducts> soldproducts ;
	public Numerous_Invoices() {
		super();
		// TODO Auto-generated constructor stub
	}
// TODO Remove unused code found by UCDetector
// 	public Numerous_Invoices(Long inid, String sid, String source, String orid, String dateoforder,
// 			String dateofinvoice, String year, String month, String total, String shipping, String paymentmode,
// 			String transactionid, String statusoftheinvoice,  String invoiceid,
// 			List<Soldproducts> soldproducts) {
// 		super();
// 		this.inid = inid;
// 		this.sid = sid;
// 		this.source = source;
// 		this.orid = orid;
// 		this.dateoforder = dateoforder;
// 		this.dateofinvoice = dateofinvoice;
// 		this.year = year;
// 		this.month = month;
// 		this.total = total;
// 		this.shipping = shipping;
// 		this.paymentmode = paymentmode;
// 		this.transactionid = transactionid;
// 		this.statusoftheinvoice = statusoftheinvoice;
// 		
// 		this.invoiceid = invoiceid;
// 		this.soldproducts = soldproducts;
// 	}
	public Long getInid() {
		return inid;
	}
	public void setInid(Long inid) {
		this.inid = inid;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
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
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
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
	
	public String getInvoiceid() {
		return invoiceid;
	}
	public void setInvoiceid(String invoiceid) {
		this.invoiceid = invoiceid;
	}
	public List<Soldproducts> getSoldproducts() {
		return soldproducts;
	}
	public void setSoldproducts(List<Soldproducts> soldproducts) {
		this.soldproducts = soldproducts;
	}
	@Override
	public String toString() {
		return "Numerous_Invoices [inid=" + inid + ", sid=" + sid + ", source=" + source + ", orid=" + orid
				+ ", dateoforder=" + dateoforder + ", dateofinvoice=" + dateofinvoice + ", year=" + year + ", month="
				+ month + ", total=" + total + ", shipping=" + shipping + ", paymentmode=" + paymentmode
				+ ", transactionid=" + transactionid + ", statusoftheinvoice=" + statusoftheinvoice +  ", invoiceid=" + invoiceid + ", soldproducts=" + soldproducts + "]";
	}
	
	
}
