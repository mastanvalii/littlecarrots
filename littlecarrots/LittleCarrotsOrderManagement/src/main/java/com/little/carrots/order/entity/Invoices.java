package com.little.carrots.order.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="invoices")
public class Invoices implements Serializable{

//	create table invoices(
//			inid bigint auto_increment,
//	        sid bigint,
//			orid varchar(100),
//	        dateoforder date,
//	        dateofinvoice date,
//			total float,
//	        shipping float,
//	        paymentmode varchar(100),
//	        transactionid varchar(100),
//	        statusoftheinvoice varchar(100),
//	        user varchar(60),
//	        invoiceid varchar(100),
//			constraint invoices_pk primary key (inid),
//	        constraint invoices_fk1 foreign key (sid) references sourcesales(sid)
//			);
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "inid")
	private long inid;
	
	@Column(name = "sid")
	private long sid;

	@Column(name = "orid")
	private String orid;
	
	@Column(name="dateoforder")
	private Date dateoforder;
	
	@Column(name="dateofinvoice")
	private Date dateofinvoice;
	
	
	@Column(name = "total")
	private float total;
	
	@Column(name = "shipping")
	private float shipping;
	
	@Column(name = "paymentmode")
	private String paymentmode;
	
	@Column(name = "transactionid")
	private String transactionid;
	
	@Column(name = "statusoftheinvoice")
	private String statusoftheinvoice;
	
	@Column(name = "user")
	private String user;
	
	@Column(name = "invoiceid")
	private String invoiceid;

	public Invoices() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Invoices(long sid, String orid, Date dateoforder, Date dateofinvoice, float total, float shipping,
			String paymentmode, String transactionid, String statusoftheinvoice, String user, String invoiceid) {
		super();
		this.sid = sid;
		this.orid = orid;
		this.dateoforder = dateoforder;
		this.dateofinvoice = dateofinvoice;
		this.total = total;
		this.shipping = shipping;
		this.paymentmode = paymentmode;
		this.transactionid = transactionid;
		this.statusoftheinvoice = statusoftheinvoice;
		this.user = user;
		this.invoiceid = invoiceid;
	}

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
		return "Invoices [inid=" + inid + ", sid=" + sid + ", orid=" + orid + ", dateoforder=" + dateoforder
				+ ", dateofinvoice=" + dateofinvoice + ", total=" + total + ", shipping=" + shipping + ", paymentmode="
				+ paymentmode + ", transactionid=" + transactionid + ", statusoftheinvoice=" + statusoftheinvoice
				+ ", user=" + user + ", invoiceid=" + invoiceid + "]";
	}

	

	
	

	
	
	
	
	
	
	
	
}
