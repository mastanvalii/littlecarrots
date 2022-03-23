package com.little.carrots.order.services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.little.carrots.order.repositories.SalesReportRepository;
import com.little.carrots.order.repositories.SoldItemsRepository;
import com.little.carrots.order.util.URLMapping;

import com.little.carrots.order.bean.SoldProducts;
import com.little.carrots.order.entity.SalesReport;
import com.little.carrots.order.entity.SoldItems;
import com.little.carrots.order.exception.subexceptions.OrderNotFoundException;
import com.little.carrots.order.initializer.MergeSalesReport;
import com.little.carrots.order.bean.CalenderBean;
import com.little.carrots.order.bean.ReceiptBean;
import com.little.carrots.order.bean.ResponseBean;

@CrossOrigin(origins="*", allowedHeaders="*")
@RestController
@Validated
@RequestMapping(path=URLMapping.ORDER_ROOT_PATH)
public class BillingService {


	@Autowired
	SalesReportRepository salesReport;
	
	@Autowired
	SoldItemsRepository soldItemsRepo;
	
	public BillingService() {
		// TODO Auto-generated constructor stub
	}

	@GetMapping(path="/bill", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Set<ReceiptBean>> getAllReceipt(){
		List<SoldItems> soldproductsList = soldItemsRepo.findAll();
		List<SalesReport> salesData = salesReport.findAll();
		Set<ReceiptBean> report = new HashSet<>();
		
		if(!soldproductsList.isEmpty()&&!salesData.isEmpty()) {			
			salesData.forEach((item)->{
				List<SoldProducts> newList = new ArrayList<>();
				soldproductsList.forEach((subitem)->{
					if(item.getInvoiceid()==subitem.getInvoiceid()) {
						newList.add(new SoldProducts(subitem.getItemssoldid(), subitem.getSkuid(), subitem.getQty(), subitem.getNetamount(),subitem.getInvoiceid() ));
					}
				});
				report.add(MergeSalesReport.merge(item, newList));
				
			});
		}
		else {
			throw new OrderNotFoundException("Receipt Information Not Found to get..");
		}
		
		return new ResponseEntity<Set<ReceiptBean>>(report,  HttpStatus.ACCEPTED);
	}
	
	@GetMapping(path="/bill/{invoiceid}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ReceiptBean> getAllReceiptById(@PathVariable long invoiceid){
		List<SoldItems> soldproductsList = soldItemsRepo.getListByInvoiceId(invoiceid);
		Optional<SalesReport> salesData = salesReport.findById(invoiceid);
	
		ReceiptBean report = new ReceiptBean();
	
		
		System.out.println("---------------------------------------|||||------------------------------------------------");
		System.out.println(soldproductsList);
		System.out.println("--------------------------------------------|||||-------------------------------------------");			
		System.out.println("---------------------------------------------------------------------------------------");
		System.out.println(salesData.get());
		System.out.println("---------------------------------------------------------------------------------------");
		
		
		if(!soldproductsList.isEmpty()&&salesData.isPresent()) {			
		
				List<SoldProducts> newList = new ArrayList<>();
				soldproductsList.forEach((subitem)->{
					if(salesData.get().getInvoiceid()==subitem.getInvoiceid()) {
						newList.add(new SoldProducts(subitem.getItemssoldid(), subitem.getSkuid(), subitem.getQty(), subitem.getNetamount(),subitem.getInvoiceid() ));
					}
				});
				report=MergeSalesReport.merge(salesData.get(), newList);
				
			
		}
		else {
			throw new OrderNotFoundException("Receipt Information Not Found to get..");
		}
		
		return new ResponseEntity<ReceiptBean>(report,  HttpStatus.ACCEPTED);
	}
	
	/*
	 * 			invoiceid bigint auto_increment,    	#lc id
			soldfrom varchar(100),					#sold from [web, local, amazon, flipkart, other]
			orderid varchar(100),					# order id only for third party sellers
			sellerinvoice varchar(100),				# invoice id only for third party sellers
			awb varchar(100),						# awb number only for third party sellers
			trackingid varchar(100),				# tracking id for both third party and lc
			state varchar(100),						# sold to the specific state
			hsn varchar(100),						# hsn code
			tax bigint,								# tax percentage
			taxtype varchar(100),					# tax type IGST
			itemssold bigint,						# foreign key for sold item table
			discount float,							# discount amount
			taxamount float,						# tax amount
			totalamount float,						# total amount
			paidtype varchar(200),					# paid type [gpay, phonepe, cash, thirdparty sells]
			transactionid varchar(200),				# only transaction id 
			Timestamp invoicedate, Timestamp orderdate
			
			
							itemssoldid bigint auto_increment,
				skuid bigint,							# lc skuid
				qty bigint,								# qty sold
				netamount float,		
			
	 */
	
	@PostMapping(path="/bill", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> insertData(
			@RequestParam(name = "soldfrom", required = true, defaultValue = "") String soldfrom,
			@RequestParam(name = "orderid", required = true, defaultValue = "") String orderid,
			@RequestParam(name = "sellerinvoice", required = true, defaultValue = "") String sellerinvoice,
			@RequestParam(name = "awb", required = true, defaultValue = "") String awb,
			@RequestParam(name = "trackingid", required = true, defaultValue = "") String trackingid,
			@RequestParam(name = "state", required = true, defaultValue = "") String state,
			@RequestParam(name = "hsn", required = true, defaultValue = "") String hsn,
			@RequestParam(name = "tax", required = true, defaultValue = "") long tax,
			@RequestParam(name = "taxtype", required = true, defaultValue = "") String taxtype,			
			@RequestParam(name = "discount", required = true, defaultValue = "") float discount,
			@RequestParam(name = "taxamount", required = true, defaultValue = "") float taxamount,
			@RequestParam(name = "totalamount", required = true, defaultValue = "") float totalamount,
			@RequestParam(name = "paidtype", required = true, defaultValue = "") String paidtype,
			@RequestParam(name = "transactionid", required = true, defaultValue = "") String transactionid,
			@RequestParam(name = "invoicedate", required = true, defaultValue = "") String invoicedate,
			@RequestParam(name = "orderdate", required = true, defaultValue = "") String orderdate,
			@RequestParam(name="skuid[]") long skuid[],
			@RequestParam(name="qty[]") long qty[],
			@RequestParam(name="netamount[]") float netamount[],
			@RequestParam(name="shippingcharges")float shippingcharges,
			@RequestParam(name="shippingtaxamount")float shippingtaxamount,
			@RequestParam(name="shippingtotalamount")float shippingtotalamount
			){
		
		/*
		 * 
		 * 	private double shippingcharges;	
	private double shippingtaxamount;	
	private double shippingtotalamount;
		 */
		ResponseBean response = new ResponseBean();
		SalesReport saleReport = new SalesReport(
				soldfrom, orderid, sellerinvoice, awb, 
				trackingid, state, hsn, tax, taxtype, discount, taxamount, totalamount, paidtype, transactionid, 
				Timestamp.valueOf(invoicedate), 
				Timestamp.valueOf(orderdate),shippingcharges, shippingtaxamount, shippingtotalamount );
		SalesReport savedReport = salesReport.save(saleReport);
		salesReport.flush();
		System.out.println("****************************************************************************************");
		System.out.println("Stored Information:");
		System.out.println(savedReport.toString());
		
		System.out.println("****************************************************************************************");
		if(skuid.length==qty.length&&skuid.length==netamount.length) {
			List<SoldItems> items = new ArrayList<>();
			for(int i=0;i<skuid.length;i++) {
				items.add(new SoldItems(skuid[i], qty[i], netamount[i], savedReport.getInvoiceid()));				
			}
			if(!items.isEmpty()) {
				items.forEach((item)->{
					SoldItems temp = soldItemsRepo.save(item);
					System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
					System.out.println("Stored Items Information:");
					System.out.println(temp.toString());
					System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
				});
			}
		}
		response.setMessage("Data inserted in DB");
		response.setResponsecode(2000);
		response.setTiemstamp(System.currentTimeMillis());
		return new ResponseEntity<ResponseBean>(response,  HttpStatus.ACCEPTED);
		
	}
		
	@PutMapping(path="/bill", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> updateData(
			@RequestParam(name="invoiceid", required=true, defaultValue="")long invoiceid,
			@RequestParam(name = "soldfrom", required = true, defaultValue = "") String soldfrom,
			@RequestParam(name = "orderid", required = true, defaultValue = "") String orderid,
			@RequestParam(name = "sellerinvoice", required = true, defaultValue = "") String sellerinvoice,
			@RequestParam(name = "awb", required = true, defaultValue = "") String awb,
			@RequestParam(name = "trackingid", required = true, defaultValue = "") String trackingid,
			@RequestParam(name = "state", required = true, defaultValue = "") String state,
			@RequestParam(name = "hsn", required = true, defaultValue = "") String hsn,
			@RequestParam(name = "tax", required = true, defaultValue = "") long tax,
			@RequestParam(name = "taxtype", required = true, defaultValue = "") String taxtype,			
			@RequestParam(name = "discount", required = true, defaultValue = "") float discount,
			@RequestParam(name = "taxamount", required = true, defaultValue = "") float taxamount,
			@RequestParam(name = "totalamount", required = true, defaultValue = "") float totalamount,
			@RequestParam(name = "paidtype", required = true, defaultValue = "") String paidtype,
			@RequestParam(name = "transactionid", required = true, defaultValue = "") String transactionid,
		//	@RequestParam(name = "invoicedate", required = true, defaultValue = "") String invoicedate,
		//	@RequestParam(name = "orderdate", required = true, defaultValue = "") String orderdate,
			@RequestParam(name="itemssoldid[]") long itemssoldid[],
			@RequestParam(name="skuid[]") long skuid[],
			@RequestParam(name="qty[]") long qty[],
			@RequestParam(name="netamount[]") float netamount[],
			@RequestParam(name="shippingcharges")float shippingcharges,
			@RequestParam(name="shippingtaxamount")float shippingtaxamount,
			@RequestParam(name="shippingtotalamount")float shippingtotalamount
		//	@RequestParam(name="invoiceids[]") long invoiceids[]
			){
		ResponseBean response = new ResponseBean();
		System.out.println("******************************************************************");
		System.out.println("invoiceid"+ invoiceid);
		System.out.println("soldfrom"+ soldfrom);
		System.out.println("orderid"+ orderid);
		System.out.println("sellerinvoice"+ sellerinvoice);
		System.out.println("awb"+ awb);
		System.out.println("trackingid"+ trackingid);
		System.out.println("state"+ state);
		System.out.println("hsn"+ hsn);
		System.out.println("tax"+ tax);
		System.out.println("taxtype"+ taxtype);
		System.out.println("discount"+ discount);
		System.out.println("taxamount"+ taxamount);
		System.out.println("totalamount"+ totalamount);
		System.out.println("paidtype"+ paidtype);
		System.out.println("transactionid"+ transactionid);
		System.out.println("itemssoldid"+ itemssoldid);
		System.out.println("skuid"+ skuid);
		System.out.println("qty"+ qty);
		System.out.println("netamount"+ netamount);
	//	System.out.println("invoiceids"+ invoiceids);
		System.out.println("******************************************************************");
		
		Optional<SalesReport> saleReport = salesReport.findById(invoiceid);
		saleReport.get().setAwb(awb);
		saleReport.get().setDiscount(discount);
		saleReport.get().setHsn(hsn);
		saleReport.get().setOrderid(orderid);
		saleReport.get().setPaidtype(paidtype);
		saleReport.get().setSellerinvoice(sellerinvoice);
		saleReport.get().setSoldfrom(soldfrom);
		saleReport.get().setState(state);
		saleReport.get().setTax(tax);
		saleReport.get().setTaxamount(taxamount);
		saleReport.get().setTaxtype(taxtype);
		saleReport.get().setTotalamount(totalamount);
		saleReport.get().setPaidtype(paidtype);
		saleReport.get().setTrackingid(trackingid);
		saleReport.get().setTransactionid(transactionid);
		saleReport.get().setShippingcharges(shippingcharges);
		saleReport.get().setShippingtaxamount(shippingtaxamount);
		saleReport.get().setShippingtotalamount(shippingtotalamount);
		
		
		SalesReport savedReport = salesReport.save(saleReport.get());
		System.out.println("****************************************************************************************");
		System.out.println("updated Information:");
		System.out.println(savedReport.toString());
		System.out.println("****************************************************************************************");
		if(skuid.length==qty.length&&skuid.length==netamount.length) {
			List<SoldItems> items = new ArrayList<>();
			for(int i=0;i<skuid.length;i++) {
				SoldItems tmp = soldItemsRepo.findById(itemssoldid[i]).get();
			//	tmp.setInvoiceid(invoiceids[i]);
				tmp.setNetamount(netamount[i]);
				tmp.setQty(qty[i]);
				tmp.setSkuid(skuid[i]);
				items.add(tmp);				
			}
		
			if(!items.isEmpty()) {
				items.forEach((item)->{
					SoldItems temp = soldItemsRepo.save(item);
					System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
					System.out.println("updated Items Information:");
					System.out.println(temp.toString());
					System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
				});
			}
		}
		response.setMessage("Data updated in DB");
		response.setResponsecode(2000);
		response.setTiemstamp(System.currentTimeMillis());
		return new ResponseEntity<ResponseBean>(response,  HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping(path="/bill1",  produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<CalenderBean>> getListofMonths(){
		return new ResponseEntity<List<CalenderBean>>(salesReport.getAvaialbleMonths(), HttpStatus.ACCEPTED);
	}
	
	@GetMapping(path="/bill1/{month}/{year}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<ReceiptBean>> getAllReceiptByMonthYear(@PathVariable String month, @PathVariable String year){
		
		List<SalesReport> salesData = salesReport.getSalesReportByMonthYear(month, year);
		
		
		
		List<ReceiptBean> report = new ArrayList<>();
		salesData.forEach((sale)->{
			List<SoldItems> soldproductsList = soldItemsRepo.getListByInvoiceId(sale.getInvoiceid());
			List<SoldProducts> newList = new ArrayList<>();
			soldproductsList.forEach((subitem)->{
				if(sale.getInvoiceid()==subitem.getInvoiceid()) {
					newList.add(new SoldProducts(subitem.getItemssoldid(), subitem.getSkuid(), subitem.getQty(), subitem.getNetamount(),subitem.getInvoiceid() ));
				}
			});
			report.add(MergeSalesReport.merge(sale, newList));
		});
		
		printBean(report);
		return new ResponseEntity<List<ReceiptBean>>(report,  HttpStatus.ACCEPTED);
	}
	
	public void printBean(List<ReceiptBean> bean) {
		bean.forEach((item)->{
			System.out.println(item);
		});
	}
}
