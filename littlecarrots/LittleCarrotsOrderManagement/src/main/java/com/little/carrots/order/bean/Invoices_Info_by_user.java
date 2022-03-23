package com.little.carrots.order.bean;

import java.io.Serializable;
import java.util.List;





public class Invoices_Info_by_user implements Serializable{
	

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6642695625674062260L;
	private String user;
	private List<Numerous_Invoices> invoice;
	public Invoices_Info_by_user() {
		super();
		// TODO Auto-generated constructor stub
	}
// TODO Remove unused code found by UCDetector
// 	public Invoices_Info_by_user(String user, List<Numerous_Invoices> invoice) {
// 		super();
// 		this.user = user;
// 		this.invoice = invoice;
// 	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public List<Numerous_Invoices> getInvoice() {
		return invoice;
	}
	public void setInvoice(List<Numerous_Invoices> invoice) {
		this.invoice = invoice;
	}
	@Override
	public String toString() {
		return "Invoices_Info_by_user [user=" + user + ", invoice=" + invoice + "]";
	}
	

	
	
	
}
