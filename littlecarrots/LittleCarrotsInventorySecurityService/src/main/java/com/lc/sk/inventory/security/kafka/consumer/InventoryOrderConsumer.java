package com.lc.sk.inventory.security.kafka.consumer;

import java.util.Calendar;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.kafka.email.bean.OrderEmailService;
import com.kafka.email.bean.OrderedData;
import com.kafka.email.bean.OrderedItems;
import com.lc.sk.inventory.security.beans.CustomerAddressBean;
import com.lc.sk.inventory.security.beans.LilCustomerBean;
import com.lc.sk.inventory.security.beans.OrderFullDetailsSecurityBean;
import com.lc.sk.inventory.security.beans.OrderFullDetailsSecurityBean1;
import com.lc.sk.inventory.security.beans.OrderItemsBean;
import com.lc.sk.inventory.security.beans.ProductFullDetails;
import com.lc.sk.inventory.security.beans.ProductFullDetailsWithImage;
import com.lc.sk.inventory.security.beans.Products;
import com.lc.sk.inventory.security.cache.ProductCacheLoader;
import com.lc.sk.inventory.security.kafka.EmailProducer;
import com.lc.sk.inventory.security.rest.CustomerAddressRestService;
import com.lc.sk.inventory.security.rest.LilCustomerRestService;
import com.lc.sk.inventory.security.rest.OrderManageMentRestService;
import com.lc.sk.inventory.security.rest.ProductquantitiesRestService;
import com.lc.sk.inventory.security.rest.ProductsRestService;

@Service
public class InventoryOrderConsumer {

	@Autowired
	EmailProducer emailProducer;
	
	@Autowired
	CustomerAddressRestService CUSTADD_SERVICE;
	
	@Autowired
	ProductCacheLoader pcl;
	
	@Autowired
	OrderManageMentRestService orderManageMentRestService;
	
	@Autowired
	LilCustomerRestService lilCustomerRestervice;
	
	@Autowired
	ProductsRestService productsRestService;

	@Autowired
	ProductquantitiesRestService productQuantityRestService;
	
	@KafkaListener(topics="order1", groupId="group_id",containerFactory="kafkaJsonListenerFactory") 
	public void consume(OrderedData bean){
		System.out.println("Inventory Order Qty Reducer received information");
		// updating quantity
					OrderFullDetailsSecurityBean odsb = orderManageMentRestService.getFULLDETAILS(bean.getOrderid() + "");
					
					List<Products> products = new CopyOnWriteArrayList<Products>();
					for (OrderItemsBean oib : odsb.getOrderitems()) {
						products.add(new Products(oib.getProductid(), 0, 0, "", 0, 0, 0, 0, "", 0, 0, 0, true, "", ""));
					}
					System.err.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
					for(Products product:products) {
					System.err.println(product.getProductid());
					}
					System.err.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
					for (int i = 0; i < products.size(); i++) {
						products.set(i, productsRestService.getbyid(products.get(i).getProductid() + ""));
						for (OrderItemsBean oib : odsb.getOrderitems()) {
							if(oib.getProductid()==products.get(i).getProductid()) {
							System.err.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
							System.err.println(oib);
							System.err.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
							productQuantityRestService.updateProductOnlyQuantities(products.get(i).getCustid() + "",
									oib.getQty() + "");
							break;
							}
						}
					}
					//	this.pushEmailNotification(rpay.getOrderid());
					this.pushToEmail(bean.getOrderid());
	}
	
	
	public void pushToEmail(Long orderid) {
		OrderFullDetailsSecurityBean1 obj= orderManageMentRestService.getNotificationInfo(orderid+"");
		OrderEmailService bean = new OrderEmailService();
		
		bean.setFullname(this.getUserFullName(obj.getCustomerid()));
		bean.setOrderid(obj.getOrderid());
		bean.setOrderstatus(obj.getOrdercodeid());
		bean.setDateoforder(obj.getOrderdate()+"");
	Calendar calendar = Calendar.getInstance();
	calendar.setTimeInMillis(obj.getOrderdate().getTime());
	String xyz=calendar.get(Calendar.DATE)+"/"+calendar.get(Calendar.MONTH)+"/"+ calendar.get(Calendar.YEAR);
	bean.setDateoforder(xyz);	
	String abc=obj.getOrderitems()[0].getDeliverydate().get(Calendar.DATE)+"/"+obj.getOrderitems()[0].getDeliverydate().get(Calendar.MONTH)+"/"+obj.getOrderitems()[0].getDeliverydate().get(Calendar.YEAR);
		bean.setDateofdelivery(abc+"");  //need to add +10 days
		bean.setTransactionid(obj.getPaymenttransactionid());
		double inum = Double.parseDouble(obj.getTotalprice());
		bean.setTotal((int)inum+"");
		OrderedItems[] orderedItems = new OrderedItems[obj.getOrderitems().length];
		
		
		
		int i=0;
		for(OrderItemsBean oib: obj.getOrderitems()) {
			System.err.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			System.err.println(oib);
			System.err.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			OrderedItems temp= new OrderedItems();
			temp.setOrderitemid(oib.getProductid()+"");
			temp.setPrice(oib.getProductprice()+"");
			temp.setQuantity(oib.getQty()+"");
			ProductFullDetailsWithImage fpd = pcl.getByProductID1(oib.getProductid());
			temp.setOrderitemtitle(fpd.getProducttitle());
			temp.setSize(fpd.getAgedescription());
			orderedItems[i] = temp;
			i++;
		}
		bean.setOrderitems(orderedItems);
		CustomerAddressBean cab = CUSTADD_SERVICE.getCustAddByID(obj.getAddressid());
		String address = cab.getName()+" ,"+cab.getFlat()+" ,"+cab.getLandmark()+" ,"+cab.getStreetAddress()+" ,"+cab.getCity()+" ,"+cab.getState()+" - "+cab.getPincode();
		bean.setAddress(address);
		bean.setContact1(cab.getMobile1()+"");
		bean.setContact2(cab.getMobile2()+"");
		bean.setEmail(cab.getEmail());
		emailProducer.push(bean);
		
	//	return new ResponseEntity<ResponseBean>(new ResponseBean("ok",2002, System.currentTimeMillis()),HttpStatus.ACCEPTED);
	}
	
	public String getUserFullName(String customerid) {
		try {
			LilCustomerBean obj=	lilCustomerRestervice.getByUniqueId(customerid);
		return obj.getFirstname()+" "+obj.getLastname();
		}catch(Exception e) {
		//	this.loadUsers();
			return "No User Found";
		}
	}
}
