package com.little.carrots.order.kafka.consumer;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.little.carrots.order.entity.OrderItems;
import com.little.carrots.order.entity.Ordercodes;
import com.little.carrots.order.entity.Orderitemsstatus;
import com.little.carrots.order.entity.Orders;
import com.little.carrots.order.entity.Razorpaytransaction;
import com.little.carrots.order.exception.subexceptions.DBInsertException;
import com.little.carrots.order.exception.subexceptions.DBValueInsertException;
import com.little.carrots.order.exception.subexceptions.OrderNotFoundException;
import com.little.carrots.order.kafka.beans.CustomerOrderBean;
import com.little.carrots.order.repositories.CodRepository;
import com.little.carrots.order.repositories.OrderItemsRepository;
import com.little.carrots.order.repositories.OrdercodesRepository;
import com.little.carrots.order.repositories.OrderitemsstatusRepository;
import com.little.carrots.order.repositories.OrdersRepository;
import com.little.carrots.order.repositories.RazorpaytransactionRepository;
import com.little.carrots.order.util.ConstantValues;

@Service
public class CustomerOrderConsumer {

	@Autowired
	OrdersRepository ordersRepository;

	@Autowired
	OrderItemsRepository orderItemsRepository;
	
	@Autowired
	CodRepository codRepository;
	
	@Autowired
	OrdercodesRepository orercodesRepository;
	
	@Autowired 
	RazorpaytransactionRepository razorpaytransactionRepository;


	@Autowired
	OrderitemsstatusRepository orderitemsstatus;
	
	
	@KafkaListener(topics="order2", groupId="group_id",containerFactory="kafkaJsonListenerFactory") 
	public void consume(CustomerOrderBean bean){
		System.out.println("Called Topic: Order2");
		System.err.println("RECEIVED BEAN-------------------------------------");
		System.err.println(bean);
		System.err.println("-----------------------------------------------------");
		Optional<Orders> orders = ordersRepository.getOrdersListByOrderid(bean.getOrderid());
		if(orders.isPresent()) {
		if(bean.isFlag()) {
			 Razorpaytransaction transaction=razorpaytransactionRepository.save(new Razorpaytransaction(bean.getRazorpay_order_id(),bean.getRazorpay_signature(),bean.getRazorpay_payment_id(),bean.getOrderid(),bean.getCustomerid()));
				if( transaction.getRazorpay_order_id()==(bean.getRazorpay_order_id())) {
					 if(orders.isPresent()) {
						 Optional<Ordercodes> code=orercodesRepository.getOrdercodesByOrderCodeid(ConstantValues.ORDER_PAYMENT_SUCESS);
			        	 orders.get().setOrdercodeid(code.get().getOrdercodeid());
			        	 orders.get().setGateway_orderid(bean.getRazorpay_order_id());
			        	 orders.get().setAddressid(bean.getAddressid());
			        	 Orders order_put =ordersRepository.save(orders.get());
			        	 
			        	 if(order_put.getOrderid()== bean.getOrderid()) {
			        		 List<OrderItems> ordersitems = orderItemsRepository.getAllOrders(bean.getOrderid());
				        	 for(int i=0; i<ordersitems.size();i++)
				        	 {
				        		 ordersitems.get(i).setOrdercodeid(code.get().getOrdercodeid());
				        		 OrderItems orderitems_put =orderItemsRepository.save(ordersitems.get(i));
				        		 addOrderItemsStatus(bean.getOrderid(), orderitems_put.getOrderitemsid(), code.get().getOrdercodeid(), code.get().getRemark());
				        	 }
				        	
				        	
			        	 }
			        	 else {
						    	throw new  DBValueInsertException(ConstantValues.DATA_NOT_INSERTED_IN_DB);
			        	 }
			        	 } else {
			        	 throw new OrderNotFoundException(ConstantValues.ORDERS_ITEMS_NOT_FOUND);
			 			
			         }
					
				}else {
					throw new DBInsertException(ConstantValues.DATA_NOT_INSERTED_IN_DB);
				}
		}else {
			
			

       	 if(orders.isPresent()) {
				 Optional<Ordercodes> code=orercodesRepository.getOrdercodesByOrderCodeid(ConstantValues.ORDER_PAYMENT_FAILED);
	        	 orders.get().setOrdercodeid(code.get().getOrdercodeid());
	        	 orders.get().setGateway_orderid(bean.getRazorpay_order_id());
	        	 orders.get().setAddressid(bean.getAddressid());
	        	 Orders order_put =ordersRepository.save(orders.get());
	        	 
	        	 if(order_put.getOrderid()== bean.getOrderid()) {
	        		 List<OrderItems> ordersitems = orderItemsRepository.getAllOrders(bean.getOrderid());
		        	 for(int i=0; i<ordersitems.size();i++)
		        	 {
		        		 ordersitems.get(i).setOrdercodeid(code.get().getOrdercodeid());
		        		 OrderItems orderitems_put =orderItemsRepository.save(ordersitems.get(i));
		        		 addOrderItemsStatus(bean.getOrderid(), orderitems_put.getOrderitemsid(), code.get().getOrdercodeid(), code.get().getRemark());
		        	 }
		        	
		        	
	        	 }
	        	 else {
				    	throw new  DBValueInsertException(ConstantValues.DATA_NOT_INSERTED_IN_DB);
	        	 }
	        	 } else {
	        	 throw new OrderNotFoundException(ConstantValues.ORDERS_ITEMS_NOT_FOUND);
	 			
	         }
		}
		}else {
			//throw new  DBValueInsertException(bean.toString());
			System.err.println("----------------------------------------------------------");
			System.err.println("Bean Issues: orderid not found:"+bean.toString());
			System.err.println("----------------------------------------------------------");
		}
		
	}
	
	
	public void addOrderItemsStatus(long orderid, long orderitemid, long ordercodeid, String msg) {
		
		java.sql.Timestamp dateinsertion = new java.sql.Timestamp(new java.util.Date().getTime());				
		orderitemsstatus.save(new Orderitemsstatus( orderitemid, orderid, ordercodeid, dateinsertion, msg));
	} 

}
