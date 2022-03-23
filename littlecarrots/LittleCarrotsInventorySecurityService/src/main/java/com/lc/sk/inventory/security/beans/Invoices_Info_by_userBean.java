package com.lc.sk.inventory.security.beans;

import java.io.Serializable;
import java.util.List;


public class Invoices_Info_by_userBean implements Serializable{
	
	private String user;
	private List<Numerous_InvoicesBean> invoice;
	public Invoices_Info_by_userBean() {
		super();
		// TODO Auto-generated constructor stub
	}
// TODO Remove unused code found by UCDetector
// 	public Invoices_Info_by_userBean(String user, List<Numerous_InvoicesBean> invoice) {
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
	public List<Numerous_InvoicesBean> getInvoice() {
		return invoice;
	}
	public void setInvoice(List<Numerous_InvoicesBean> invoice) {
		this.invoice = invoice;
	}
	@Override
	public String toString() {
		return "Invoices_Info_by_userBean [user=" + user + ", invoice=" + invoice + "]";
	}
	
	
	

}
