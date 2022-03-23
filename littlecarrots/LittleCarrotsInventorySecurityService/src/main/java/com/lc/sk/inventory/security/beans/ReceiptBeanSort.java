package com.lc.sk.inventory.security.beans;

import java.util.Comparator;

public class ReceiptBeanSort implements Comparator<ReceiptBean> {

	@Override
	public int compare(ReceiptBean o1, ReceiptBean o2) {
		return (int) (o1.getInvoiceid()-o2.getInvoiceid());
	}

}
