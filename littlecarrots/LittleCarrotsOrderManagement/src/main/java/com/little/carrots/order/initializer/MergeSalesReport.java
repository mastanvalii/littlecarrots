package com.little.carrots.order.initializer;

import java.util.List;

import com.little.carrots.order.bean.ReceiptBean;
import com.little.carrots.order.bean.SoldProducts;
import com.little.carrots.order.entity.SalesReport;
import com.little.carrots.order.exception.subexceptions.OrderNotFoundException;

public class MergeSalesReport {

	public MergeSalesReport() {
		// TODO Auto-generated constructor stub
	}
	
	public static ReceiptBean merge(SalesReport sr, List<SoldProducts> si ) {
		ReceiptBean rb = new ReceiptBean();
		if(sr!=null&&si.size()!=0) {
			rb.setSoldProducts(si);
			rb.setInvoiceid(sr.getInvoiceid());
			rb.setSoldfrom(sr.getSoldfrom());
			rb.setOrderid(sr.getOrderid());
			rb.setSellerinvoice(sr.getSellerinvoice());
			rb.setAwb(sr.getAwb());
			rb.setTrackingid(sr.getTrackingid());
			rb.setState(sr.getState());
			rb.setHsn(sr.getHsn());
			rb.setTax(sr.getTax());
			rb.setTaxtype(sr.getTaxtype());
			rb.setDiscount(sr.getDiscount());
			rb.setTaxamount(sr.getTaxamount());
			rb.setTotalamount(sr.getTotalamount());
			rb.setPaidtype(sr.getPaidtype());
			rb.setTransactionid(sr.getTransactionid());
			rb.setInvoicedate(sr.getInvoicedate());
			rb.setOrderdate(sr.getOrderdate());
			rb.setShippingcharges(sr.getShippingcharges());
			rb.setShippingtaxamount(sr.getShippingtaxamount());
			rb.setShippingtotalamount(sr.getShippingtotalamount());
		
		}else {
			throw new OrderNotFoundException("Receipt Information Not Found for Merge..");
		}
		
		return rb;
	}

}
