package com.little.carrots.order.bean;

import java.io.Serializable;

public class NewPayment implements Serializable {
	/*JSONObject options = new JSONObject();
options.put("amount", 5000);
options.put("currency", "INR");
options.put("receipt", "txn_123456");
Order order = razorpayClient.Orders.create(options);
*/
	
	private Integer amount;
	private String currency;
	private String receipt;
	public NewPayment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public NewPayment(Integer amount, String currency, String receipt) {
		super();
		this.amount = amount;
		this.currency = currency;
		this.receipt = receipt;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getReceipt() {
		return receipt;
	}
	public void setReceipt(String receipt) {
		this.receipt = receipt;
	}
	@Override
	public String toString() {
		return "NewPayment [amount=" + amount + ", currency=" + currency + ", receipt=" + receipt + "]";
	}
	
	
}
