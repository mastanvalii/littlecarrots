package com.little.carrots.order.services;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;


import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import com.little.carrots.order.util.ConstantValues;
import com.little.carrots.order.bean.Order_Full_Details;
import com.little.carrots.order.bean.Order_Full_Details1;
import com.little.carrots.order.bean.OrderedBean;
import com.little.carrots.order.bean.RazorpayOrderedBean;
import com.little.carrots.order.bean.ResponseBean;
import com.little.carrots.order.entity.Cod;
import com.little.carrots.order.entity.OrderItems;
import com.little.carrots.order.entity.Orderitemsstatus;
import com.little.carrots.order.entity.Orders;
import com.little.carrots.order.entity.Razorpaytransaction;
import com.little.carrots.order.exception.subexceptions.BadRequestException;
import com.little.carrots.order.exception.subexceptions.DBInsertException;
import com.little.carrots.order.exception.subexceptions.DBValueInsertException;
import com.little.carrots.order.exception.subexceptions.NullRequestReceivedException;
import com.little.carrots.order.exception.subexceptions.OrderNotFoundException;
import com.little.carrots.order.exception.subexceptions.RazorpayTransactionNotFoundException;
import com.little.carrots.order.filter.PaiseConverter;
import com.little.carrots.order.kafka.beans.CustomerOrderBean;
import com.little.carrots.order.kafka.producer.CustomerOrderProducter;
import com.little.carrots.order.razonpay.RazorpayConfig;
import com.little.carrots.order.repositories.CodRepository;
import com.little.carrots.order.repositories.OrderItemsRepository;
import com.little.carrots.order.repositories.OrdercodesRepository;
import com.little.carrots.order.repositories.OrderitemsstatusRepository;
import com.little.carrots.order.repositories.OrdersRepository;
import com.little.carrots.order.repositories.RazorpaytransactionRepository;
import com.little.carrots.order.util.URLMapping;
import com.razorpay.*;




@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Validated
@RequestMapping(path = URLMapping.ORDER_ROOT_PATH)
public class OrderManagementService {

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

	
	
	@Autowired
	CustomerOrderProducter orderProducer;
	
	private int PAYMENT_CAPTURE = 1;
	
	
	private RazorpayClient client= null;
	
	 
	 
	 
	//get all method order from order table entity

	public OrderManagementService() {
		    try {
		    	System.err.println("Key:"+RazorpayConfig.KEY_ID);
		    	System.err.println("Value:"+RazorpayConfig.KEY_VALUE);
		      this.client = new RazorpayClient(RazorpayConfig.KEY_ID, RazorpayConfig.KEY_VALUE);
		    } catch (RazorpayException e) {
		      // TODO Auto-generated catch block
		      e.printStackTrace();
		    }
		    
		
	}

	@GetMapping(path=URLMapping.GET_ORDERS,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Orders>> getAll(@PathVariable Long ordercodeid, @PathVariable String dummy) {
		List<Orders> orders = (List<Orders>) ordersRepository.getOrdersList(ordercodeid);
		if (!orders.isEmpty()) {
			return new ResponseEntity<List<Orders>>(orders,  HttpStatus.ACCEPTED);
		} else {
			throw new OrderNotFoundException(ConstantValues.ORDERS_NOT_FOUND);
		}
	}
		
	//get by orderid from orders table entity
	
	
	@GetMapping(path=URLMapping.ORDER_MAPPING_BY_ORDERID,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Optional<Orders>> getOrderById(@PathVariable long orderid) {
		Optional<Orders> orders = ordersRepository.getOrdersListByOrderid(orderid);
				
		if (!orders.isPresent()) {
			throw new OrderNotFoundException(ConstantValues.ORDERS_ITEMS_NOT_FOUND);
			
			
		} else {
			
			return new ResponseEntity<Optional<Orders>>(orders,  HttpStatus.ACCEPTED);
		}
	}
	
	//get by customerid
	
	@GetMapping(path=URLMapping.ORDER_MAPPING_BY_CUSTOMERID,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Orders>> getOrderBycustomerId(@PathVariable long customerid) {
		List<Orders> orders = ordersRepository.getOrdersListByCustomerid(customerid);
				
		if (orders.isEmpty()) {
			throw new OrderNotFoundException(ConstantValues.ORDERS_NOT_FOUND);
			
			
		} else {
			
			return new ResponseEntity<List<Orders>>(orders,  HttpStatus.ACCEPTED);
		}
	}
	
	//Post method for orders
	
		@PostMapping(path = URLMapping.CREATE_ORDER,produces = MediaType.APPLICATION_JSON_VALUE)
		@ResponseBody
		public ResponseEntity<OrderedBean> createOrder(
				
				@RequestParam(name = ConstantValues.CUSTOMER_ID, required = true, defaultValue = ConstantValues.DEFAULT_INT) long customerid,
				@RequestParam(name = ConstantValues.TOTAL_PRICE, required = true, defaultValue = ConstantValues.DEFAULT_FLOAT) double totalprice,
				@RequestParam(name = ConstantValues.ORDERCODEID, required = true, defaultValue = ConstantValues.DEFAULT_INT) long ordercodeid,
				@RequestParam(name = ConstantValues.ADDRESSID, required = true, defaultValue = ConstantValues.DEFAULT_INT) long addressid,
				
	            //orderitem request params
				//@RequestParam(name = ConstantValues.ORDER_ID, required = true, defaultValue = ConstantValues.DEFAULT_INT) long orderid,
				@RequestParam(name="productid[]") long productid[],
				@RequestParam(name = "quantity[]") long quantity[],
				@RequestParam(name = "productprice[]") long productprice[]
				//@RequestParam(name = ConstantValues.DELIVERY_DATE, required = true, defaultValue = ConstantValues.DEFAULT_STRING) String deliverydate
				//@RequestParam(name = ConstantValues.ORDERCODEID, required = true, defaultValue = ConstantValues.DEFAULT_INT) long ordercodeid
				){
            //System.out.println(totalprice);
			//System.out.println(ordercodeid);
			//System.out.println(productid.length);
			//System.out.println(quantity.length);
			//System.out.println(productprice.length);
			
			if (//customerid == Long.parseLong(ConstantValues.DEFAULT_INT)||
					totalprice == Long.parseLong(ConstantValues.DEFAULT_INT)
					||ordercodeid == Long.parseLong(ConstantValues.DEFAULT_INT)
					//||addressid == Long.parseLong(ConstantValues.DEFAULT_INT)
				  	||productprice.length!=productid.length && productprice.length!=quantity.length
					) {
				throw new NullRequestReceivedException(ConstantValues.RECEIVED_NULL_VALUES);
			} else {
				deleteOrderItems(customerid);
				//ResponseBean response=new ResponseBean();
				java.sql.Timestamp orderdate = new java.sql.Timestamp(new java.util.Date().getTime());
				orderdate.toLocalDateTime();
				//Getting current date
				Calendar deliverydate = Calendar.getInstance();
				//Displaying current date in the desired format
				//System.out.println("Current Date: "+sdf.format(deliverydate.getTime()));
				   
				//Number of Days to add
				deliverydate.add(Calendar.DAY_OF_MONTH, ConstantValues.ESTIMATED_DELIVERY_DAYS);  
				//System.out.println(deliverydate);
				//Date after adding the days to the current date
				//java.sql.Timestamp deliverydate1 = new java.sql.Timestamp(new java.util.Date().getTime());
				ordersRepository.save(new Orders(orderdate, customerid,totalprice,ordercodeid,null,addressid));
				Long receipt_id=ordersRepository.getId(ordercodeid,customerid);
				
  
				
				List<OrderItems> entities = new CopyOnWriteArrayList<OrderItems>();
				if(productid.length!=0) {
					int x= productid.length;
					for(int i=0;i<x;i++)
					{
					
					entities.add(new OrderItems(receipt_id,productid[i],quantity[i],productprice[i],deliverydate,ordercodeid));
					
					}
				//	System.err.println(entities);
					
					
					orderItemsRepository.saveAll(entities);
					
				    JSONObject options = new JSONObject();
				    Order razorpayOrder = null;
				    try {
			//	    System.err.println("Paise: "+PaiseConverter.convert(totalprice));
					options.put("amount", PaiseConverter.convert(totalprice));					
				    options.put("currency", ConstantValues.CURRENCY_FORMAT);
				    options.put("receipt", receipt_id+"");
				    options.put("payment_capture", PAYMENT_CAPTURE);  				
				    razorpayOrder = client.Orders.create(options);
				    } catch (JSONException | RazorpayException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} // Note: The amount should be in paise.
				    String razorpayOrder_id =(String) razorpayOrder.get("id");
				    /*
				     * must return
				     * 1. razorpayOrder_id
				     * 2. recepit_id
				     * 3. totalprice in paise
				     * 4. key
				     * 5. customerid
				     */
				    OrderedBean details= new OrderedBean();
				    
				    details.setRazorpayOrder_id(razorpayOrder_id);
				    details.setOrderid(receipt_id);
				    details.setTotalprice(PaiseConverter.convert(totalprice));
				    details.setKey(RazorpayConfig.KEY_ID);
				    details.setCustomerid(customerid);
				   
					
					return new ResponseEntity<OrderedBean>(details,  HttpStatus.ACCEPTED);
					}
				    else
				    {
				    	throw new  DBValueInsertException(ConstantValues.DATA_NOT_INSERTED_IN_DB);
				    }}
			
	
		}
		
		
	
	//Put Method  to update the status
		@PutMapping(path=URLMapping.CANCEL_ORDER)
		@ResponseBody
		public ResponseEntity<ResponseBean> cancelOrder(
				@RequestParam(name = ConstantValues.ORDER_ID, required = true, defaultValue = ConstantValues.DEFAULT_INT) long orderid,
				@RequestParam(name = ConstantValues.CUSTOMER_ID, required = true, defaultValue = ConstantValues.DEFAULT_INT) long customerid,
				@RequestParam(name = ConstantValues.TOTAL_PRICE, required = true, defaultValue = ConstantValues.DEFAULT_INT) long totalprice,
				@RequestParam(name = ConstantValues.ORDERCODEID, required = true, defaultValue = ConstantValues.DEFAULT_INT) long ordercodeid,
				@RequestParam(name = ConstantValues.GATEWAY_ORDERID, required = true, defaultValue = ConstantValues.DEFAULT_STRING) String gateway_orderid)
	          
	{

			if (
					orderid==  Long.parseLong(ConstantValues.DEFAULT_INT)
					||customerid == Long.parseLong(ConstantValues.DEFAULT_INT)
					||totalprice == Long.parseLong(ConstantValues.DEFAULT_INT)
					||ordercodeid == Long.parseLong(ConstantValues.DEFAULT_INT)
					||gateway_orderid.equals(ConstantValues.DEFAULT_STRING)
					) {
				throw new NullRequestReceivedException(ConstantValues.RECEIVED_NULL_VALUES);
			} else {
		        // ResponseBean responseBean = new ResponseBean();
		         Optional<Orders> orders = ordersRepository.getOrdersListByOrderid(orderid);
		         if(orders.isPresent()) {
		        	 orders.get().setOrdercodeid(ordercodeid);
		        	 orders.get().setTotalprice(totalprice);
		        	 orders.get().setGateway_orderid(gateway_orderid);
		        	 Orders order_put =ordersRepository.save(orders.get());
		        	 if(order_put.getOrderid()== orderid) {
		        		 ResponseBean response=new ResponseBean();
		        		 response.setMessage("Data updated in DB");
		 				response.setResponsecode(2000);
		 				response.setTiemstamp(System.currentTimeMillis());
		 				return new ResponseEntity<ResponseBean>(response,  HttpStatus.ACCEPTED);
		        	 }
		        	 else {
					    	throw new  DBValueInsertException(ConstantValues.DATA_NOT_INSERTED_IN_DB);
		        	 }
		        	 } else {
		        	 throw new OrderNotFoundException(ConstantValues.ORDERS_ITEMS_NOT_FOUND);
		 			
		         }
			}
	}


	//get all method  for orderitems table entity
	
	
	@GetMapping(path=URLMapping.ORDERS_ITEMS,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<OrderItems>> getAllOrdereditems() {
		List<OrderItems> orderitems = (List<OrderItems>) orderItemsRepository.getAllOrderItems();
		if (!orderitems.isEmpty()) {
			return new ResponseEntity<List<OrderItems>>(orderitems,  HttpStatus.ACCEPTED);
		} else {
			throw new OrderNotFoundException(ConstantValues.ORDERS_NOT_FOUND);
		}
	}
	

	//get by orderitems by orderitems id
	
	
	@GetMapping(path=URLMapping.ORDER_MAPPING_BY_ORDERITEMSID,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Optional<OrderItems>> getOrderItemsById(@PathVariable long orderitemsid) {
		Optional<OrderItems> ordersitems = orderItemsRepository.getAllOrderItemId(orderitemsid);
				
		if (!ordersitems.isPresent()) {
			throw new OrderNotFoundException(ConstantValues.ORDERS_NOT_FOUND);
			
		} else {
			return new ResponseEntity<Optional<OrderItems>>(ordersitems,  HttpStatus.ACCEPTED);
		}
	}
	

	//get orderitems by order id
	
	@GetMapping(path=URLMapping.ORDERITEMS_BY_ORDERID,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<OrderItems>> getOrderItemsByOrderId(@PathVariable long orderid) {
		List<OrderItems> ordersitems = orderItemsRepository.getAllOrders(orderid);
				
		if (ordersitems.isEmpty()) {
			throw new OrderNotFoundException(ConstantValues.ORDERS_ITEMS_NOT_FOUND);
			
		} else {
			return new ResponseEntity<List<OrderItems>>(ordersitems,  HttpStatus.ACCEPTED);
		}
	}
	
			/*
			//Put Method  to update the status in order items
			@PutMapping(URLMapping.ORDERS_ITEMS)
			@ResponseBody
			public ResponseEntity<ResponseBean> cancelOrder(
					@RequestParam(name = ConstantValues.ORDERITMES_ID, required = true, defaultValue = ConstantValues.DEFAULT_INT) long orderitemsid,
					@RequestParam(name = ConstantValues.ORDER_ID, required = true, defaultValue = ConstantValues.DEFAULT_INT) long orderid,
				//	@RequestParam(name="productid[]") long productid[],
					@RequestParam(name = ConstantValues.QUANTITY, required = true, defaultValue = ConstantValues.DEFAULT_INT) long qty,
					@RequestParam(name = ConstantValues.PRODUCT_PRICE, required = true, defaultValue = ConstantValues.DEFAULT_INT) long productprice,
					@RequestParam(name = ConstantValues.DELIVERY_DATE, required = true, defaultValue = ConstantValues.DEFAULT_STRING) String deliverydate,
					@RequestParam(name = ConstantValues.ORDERCODEID, required = true, defaultValue = ConstantValues.DEFAULT_INT) long ordercodeid)
		          
		{

				if (orderitemsid == Long.parseLong(ConstantValues.DEFAULT_INT)
						||orderid == Long.parseLong(ConstantValues.DEFAULT_INT)
						||qty == Long.parseLong(ConstantValues.DEFAULT_INT)
						||productprice == Long.parseLong(ConstantValues.DEFAULT_INT)
						||deliverydate.equals(ConstantValues.DEFAULT_STRING)
						||ordercodeid == Long.parseLong(ConstantValues.DEFAULT_INT)
						
						) {
					throw new NullRequestReceivedException(ConstantValues.RECEIVED_NULL_VALUES);
				} else {
			         ResponseBean responseBean = new ResponseBean();
			         Optional<OrderItems> ordersitems = orderItemsRepository.getAllOrderItemId(orderitemsid);			        
			         if(ordersitems.isPresent()) {
			        	 Date date1 = null;
			 			try {
			 				date1 = new SimpleDateFormat("dd/MM/yyyy").parse(deliverydate);
			 				
			 			} catch (ParseException e) {
			 				
			 				date1 = Date.from(Instant.parse(deliverydate));
			 				
			 			}
			 			java.sql.Timestamp dateofpurchase_1 = new java.sql.Timestamp(date1.getTime());
			        	 ordersitems.get().setQty(qty);
			        	 ordersitems.get().setProductprice(productprice);
			        	 ordersitems.get().setDeliverydate(dateofpurchase_1);
			        	 ordersitems.get().setOrdercodeid(ordercodeid);
			        	 OrderItems order_items_put =orderItemsRepository.save(ordersitems.get());
			        	 if(order_items_put.getOrderitemsid()== orderitemsid) {
			        		 ResponseBean response=new ResponseBean();
			        		 response.setMessage("Data updated in DB");
			 				response.setResponsecode(2000);
			 				response.setTiemstamp(System.currentTimeMillis());
			 				return new ResponseEntity<ResponseBean>(response,  HttpStatus.ACCEPTED);
			        	 }
			        	 else {
						    	throw new  DBValueInsertException(ConstantValues.DATA_NOT_INSERTED_IN_DB);
			        	 }
			        	 } else {
			        	 throw new OrderNotFoundException(ConstantValues.ORDERS_ITEMS_NOT_FOUND);
			 			
			         }
				}
		}
*/
		

			//post method for cod from cod table
			
			@PostMapping(path = URLMapping.COD,produces = MediaType.APPLICATION_JSON_VALUE)
			@ResponseBody
			public ResponseEntity<ResponseBean> cod(
					//cod params
					@RequestParam(name = ConstantValues.ORDER_DATE, required = true, defaultValue = ConstantValues.DEFAULT_INT) String orderdate,	
					@RequestParam(name = ConstantValues.CUSTOMER_ID, required = true, defaultValue = ConstantValues.DEFAULT_INT) long customerid,
					@RequestParam(name = ConstantValues.TOTAL_AMOUNT, required = true, defaultValue = ConstantValues.DEFAULT_INT) long totalamount,
					@RequestParam(name = ConstantValues.DELIVERY_DATE, required = true, defaultValue = ConstantValues.DEFAULT_INT) String deliverydate
					
					
					){
				if (customerid == Long.parseLong(ConstantValues.DEFAULT_INT)
						||totalamount == Long.parseLong(ConstantValues.DEFAULT_INT)
						||orderdate.equals(ConstantValues.DEFAULT_INT)
						||deliverydate.equals(ConstantValues.DEFAULT_INT)
					
				
						) {
					throw new NullRequestReceivedException(ConstantValues.RECEIVED_NULL_VALUES);
				} 
				
				else {
					ResponseBean response=new ResponseBean();
					Date orderdate1=null;
					Date deliverydate1=null;
					try {
						orderdate1 = new SimpleDateFormat("dd/MM/yyyy").parse(orderdate);
						deliverydate1 = new SimpleDateFormat("dd/MM/yyyy").parse(deliverydate);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
					java.sql.Timestamp orderdate2 = new java.sql.Timestamp(orderdate1.getTime());
					java.sql.Timestamp deliverydate2 = new java.sql.Timestamp(deliverydate1.getTime());
					Cod cod=new Cod(orderdate2,customerid,totalamount,
							deliverydate2);
					Cod cod1=codRepository.save(cod);
				
					
				 
				    if(cod1.getCustomerid()==customerid)
				    {
				    	response.setMessage("Data inserted in DB");
						response.setResponsecode(2000);
						response.setTiemstamp(System.currentTimeMillis());
						return new ResponseEntity<ResponseBean>(response,  HttpStatus.ACCEPTED);
				    }
				    else
				    {
				    	throw new  DBValueInsertException(ConstantValues.DATA_NOT_INSERTED_IN_DB);
				    }
		}
			}
			
			@GetMapping(path=URLMapping.ORDER_FULL_MAPPING,produces = MediaType.APPLICATION_JSON_VALUE)
			@ResponseBody
			public ResponseEntity<Order_Full_Details> getFullOrders(@PathVariable long orderid) {
				Order_Full_Details details= new Order_Full_Details();
				Optional<Orders> ord = ordersRepository.getOrdersListByOrderid(orderid);
				
				if (!ord.isPresent()) {
					throw new OrderNotFoundException(ConstantValues.ORDERS_ITEMS_NOT_FOUND);
					
					
				} else {	
						List<OrderItems> ordersitems = orderItemsRepository.getAllOrders(ord.get().getOrderid());
			
						if (ordersitems.isEmpty()) {
							details.setOrderid(ord.get().getOrderid());
							details.setOrderdate(ord.get().getOrderdate());
							details.setCustomerid(ord.get().getCustomerid());
							details.setTotalprice(ord.get().getTotalprice());
							details.setOrdercodeid(ord.get().getOrdercodeid());
							details.setGateway_orderid(ord.get().getGateway_orderid());
							
						} else {
							details.setOrderid(ord.get().getOrderid());
							details.setOrderdate(ord.get().getOrderdate());
							details.setCustomerid(ord.get().getCustomerid());
							details.setTotalprice(ord.get().getTotalprice());
							details.setOrdercodeid(ord.get().getOrdercodeid());
							details.setGateway_orderid(ord.get().getGateway_orderid());
							details.setAddressid(ord.get().getAddressid());
						List<OrderItems> abc = new ArrayList<>();
						for(OrderItems efg:ordersitems)
						{
							abc.add(efg);
						}
						details.setOrderitems(abc);
					
						}
					return new ResponseEntity<Order_Full_Details> (details, HttpStatus.ACCEPTED);
					
				
			}
				
			}
			

			@GetMapping(path=URLMapping.ORDER_FULL_MAPPING_BY_CUSTOMERID,produces = MediaType.APPLICATION_JSON_VALUE)
			@ResponseBody
			public ResponseEntity<List<Order_Full_Details>> getFullOrdersBy_Customerid(@PathVariable long customerid) {
				List<Order_Full_Details> details= new CopyOnWriteArrayList<>();
				List<Orders> ord = ordersRepository.getOrdersListByCustomerid(customerid);
				
				if (ord.isEmpty()) {
					throw new OrderNotFoundException(ConstantValues.ORDERS_ITEMS_NOT_FOUND);
					
					
				} else {	
					for(Orders abc:ord) {
						List<OrderItems> ordersitems = orderItemsRepository.getAllOrders(abc.getOrderid());
						Order_Full_Details dummy= new Order_Full_Details();
						if (ordersitems.isEmpty()) {
							dummy.setOrderid(abc.getOrderid());
							dummy.setOrderdate(abc.getOrderdate());
							dummy.setCustomerid(abc.getCustomerid());
							dummy.setTotalprice(abc.getTotalprice());
							dummy.setOrdercodeid(abc.getOrdercodeid());
							dummy.setGateway_orderid(abc.getGateway_orderid());
							dummy.setAddressid(abc.getAddressid());
							
						} else {
							dummy.setOrderid(abc.getOrderid());
							dummy.setOrderdate(abc.getOrderdate());
							dummy.setCustomerid(abc.getCustomerid());
							dummy.setTotalprice(abc.getTotalprice());
							dummy.setOrdercodeid(abc.getOrdercodeid());
							dummy.setGateway_orderid(abc.getGateway_orderid());
							dummy.setAddressid(abc.getAddressid());
						List<OrderItems> xyz = new ArrayList<>();
						for(OrderItems efg:ordersitems)
						{
							xyz.add(efg);
						}
						dummy.setOrderitems(xyz);
					
						}
						details.add(dummy);
					}
					return new ResponseEntity<List<Order_Full_Details>> (details, HttpStatus.ACCEPTED);
					
				
			}
				
			}
			
			@GetMapping(path=URLMapping.GET_ALL_RazorPay_Transaction,produces = MediaType.APPLICATION_JSON_VALUE)
			@ResponseBody
			public ResponseEntity<List<Razorpaytransaction>> getAllRazorPay() {
				List<Razorpaytransaction> transaction = (List<Razorpaytransaction>) razorpaytransactionRepository.getalltransactions();
				if (!transaction.isEmpty()) {
					return new ResponseEntity<List<Razorpaytransaction>>(transaction,  HttpStatus.ACCEPTED);
				} else {
					throw new RazorpayTransactionNotFoundException(ConstantValues.RAZORPAY_TRANSACTION_NOT_FOUND);
				}
			}
			
			//get by orderid from orders table entity
			
			
			@GetMapping(path=URLMapping.GET_ALL_RazorPay_Transaction_ByID,produces = MediaType.APPLICATION_JSON_VALUE)
			@ResponseBody
			public ResponseEntity<Optional<Razorpaytransaction>> getById(@PathVariable String razorpay_order_id) {
				Optional<Razorpaytransaction> transaction = razorpaytransactionRepository.getalltransactionsbyId(razorpay_order_id);
				if (!transaction.isPresent()) {
					throw new RazorpayTransactionNotFoundException(ConstantValues.RAZORPAY_TRANSACTION_NOT_FOUND);
					
					
				} else {
					
					return new ResponseEntity<Optional<Razorpaytransaction>>(transaction,  HttpStatus.ACCEPTED);
				}
			}
			
			
			
			@PostMapping(path=URLMapping.GET_ALL_RazorPay_Transaction,produces = MediaType.APPLICATION_JSON_VALUE)
			@ResponseBody
			public ResponseEntity<RazorpayOrderedBean> charge(
					@RequestParam(name = ConstantValues.RAZORPAY_ORDER_ID, required = true, defaultValue = ConstantValues.DEFAULT_STRING) String razorpay_order_id,
					@RequestParam(name = ConstantValues.RAZORPAY_SIGNATURE, required = true, defaultValue = ConstantValues.DEFAULT_STRING) String razorpay_signature,
					@RequestParam(name = ConstantValues.RAZORPAY_PAYMENT_ID, required = true, defaultValue = ConstantValues.DEFAULT_STRING) String razorpay_payment_id,
					@RequestParam(name = ConstantValues.CUSTOMER_ID, required = true, defaultValue = ConstantValues.DEFAULT_INT) Long customerid,
					@RequestParam(name = ConstantValues.ORDER_ID, required = true, defaultValue = ConstantValues.DEFAULT_INT) Long orderid,
					@RequestParam(name = ConstantValues.ADDRESSID, required = true, defaultValue = ConstantValues.DEFAULT_INT) Long addressid
					) {
			  // String paymentId = formParams.getFirst("razorpay_payment_id");
			   //String razorpaySignature = formParams.getFirst("razorpay_signature");
			 // String orderId = formParams.getFirst("razorpay_order_id");
				/*System.err.println("------------------------------------------------------------------------------------------------");
				System.err.println("Received data");
				System.err.println("razorpay_order_id=="+razorpay_order_id);
				System.err.println("razorpay_signature=="+razorpay_signature);
				System.err.println("razorpay_payment_id=="+razorpay_payment_id);
				System.err.println("customerid=="+customerid);
				System.err.println("orderid=="+orderid);
				System.err.println("addressid=="+addressid);
				System.err.println("------------------------------------------------------------------------------------------------");*/
			   JSONObject options = new JSONObject();
    		    if (StringUtils.isNotBlank(razorpay_payment_id) && StringUtils.isNotBlank(razorpay_signature)
			        && StringUtils.isNotBlank(razorpay_order_id) //&& customerid!= Long.parseLong(ConstantValues.DEFAULT_INT) 
			        && orderid!=  Long.parseLong(ConstantValues.DEFAULT_INT)) {
			      try {
			        options.put("razorpay_payment_id", razorpay_payment_id);
			        options.put("razorpay_order_id", razorpay_order_id);
			        options.put("razorpay_signature", razorpay_signature);
			        boolean isEqual = Utils.verifyPaymentSignature(options, RazorpayConfig.KEY_VALUE);
			        CustomerOrderBean orderbean = new CustomerOrderBean(razorpay_order_id, razorpay_signature, razorpay_payment_id, customerid, orderid, addressid, isEqual);
			        orderProducer.orderAssign(orderbean);
			        if (isEqual) {
			        	//System.out.println(options);
			        	 RazorpayOrderedBean details=new RazorpayOrderedBean();
		        		 details.setMessage("Transaction successful");
		        		 details.setRazorpay_payment_id(razorpay_payment_id);
		        		 return new ResponseEntity<RazorpayOrderedBean>(details,  HttpStatus.ACCEPTED);
			        }
			        else {
			        	 RazorpayOrderedBean details=new RazorpayOrderedBean();
		        		 details.setMessage("Transaction Failed");
		        		 details.setRazorpay_payment_id(razorpay_payment_id);
		        		 return new ResponseEntity<RazorpayOrderedBean>(details,  HttpStatus.ACCEPTED);
			        }
			        
			      } catch (RazorpayException e) {
			        System.out.println("Exception caused because of " + e.getMessage());
			       throw new BadRequestException(ConstantValues.BAD_REQUEST);
			      } catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    }
    		    throw new BadRequestException(ConstantValues.BAD_REQUEST);
			  }
			
			
			
			/* Kafka Queue test code */

			@PostMapping(path="/razorpay_test",produces = MediaType.APPLICATION_JSON_VALUE)
			@ResponseBody
			public ResponseEntity<String> charge1(
				@RequestBody CustomerOrderBean orderbean
					) {
			  
			   //     CustomerOrderBean orderbean = new CustomerOrderBean(razorpay_order_id, razorpay_signature, razorpay_payment_id, customerid, orderid, addressid, isEqual);
			        return new ResponseEntity<String>(  orderProducer.orderAssign(orderbean),  HttpStatus.ACCEPTED);
			  }
			
			/* Kafka Queue test code end */
			
			/*
			public void addOrderItemsStatus(long orderid, long orderitemid, long ordercodeid, String msg) {
				
				java.sql.Timestamp dateinsertion = new java.sql.Timestamp(new java.util.Date().getTime());				
				orderitemsstatus.save(new Orderitemsstatus( orderitemid, orderid, ordercodeid, dateinsertion, msg));
			} 
	
			*/
			@GetMapping(path=URLMapping.ORDER_ITEMS_STATUS1,produces = MediaType.APPLICATION_JSON_VALUE)
			@ResponseBody
			public ResponseEntity<List<Orderitemsstatus>> getByOrderItemStatusById(@PathVariable long orderid){
				List<Orderitemsstatus> orr = orderitemsstatus.getAllOrderStatus(orderid);
				if(orr.size()>0) {
					return new ResponseEntity<List<Orderitemsstatus>>(orr,  HttpStatus.ACCEPTED);
				}else {
					throw new OrderNotFoundException(ConstantValues.ORDERS_ITEMS_NOT_FOUND);
				}
				
			}
			
			@GetMapping(path=URLMapping.ORDER_ITEMS_STATUS2,produces = MediaType.APPLICATION_JSON_VALUE)
			@ResponseBody
			public ResponseEntity<List<Orderitemsstatus>> getByOrderItemStatusByORDERITEMSId(@PathVariable long orderitemsid){
				List<Orderitemsstatus> orr = orderitemsstatus.getAllOrderStatusBYOrderitemsId(orderitemsid);
				if(orr.size()>0) {
					return new ResponseEntity<List<Orderitemsstatus>>(orr,  HttpStatus.ACCEPTED);
				}else {
					throw new OrderNotFoundException(ConstantValues.ORDERS_ITEMS_NOT_FOUND);
				}
				
			}
			@PostMapping(path=URLMapping.ORDER_ITEMS_STATUS,produces = MediaType.APPLICATION_JSON_VALUE)
			@ResponseBody
			public ResponseEntity<ResponseBean> insertItemOrderStatus(
					@RequestParam(name = ConstantValues.ORDER_ID, required = true, defaultValue = ConstantValues.DEFAULT_INT) Long orderid,
					@RequestParam(name = ConstantValues.ORDERCODEID, required = true, defaultValue = ConstantValues.DEFAULT_INT) Long ordercodeid,
					@RequestParam(name = ConstantValues.ORDERITMES_ID, required = true, defaultValue = ConstantValues.DEFAULT_INT) Long orderitemsid,
					@RequestParam(name = ConstantValues.MESSAGE, required = true, defaultValue = ConstantValues.DEFAULT_INT) String msg
					)
			{
				java.sql.Timestamp dateinsertion = new java.sql.Timestamp(new java.util.Date().getTime());	
				Orderitemsstatus oit = orderitemsstatus.save(new Orderitemsstatus( orderitemsid, orderid, ordercodeid, dateinsertion, msg));
				if(oit.getOrderid() == orderid) {
					
					Optional<OrderItems> ordersitems = orderItemsRepository.getAllOrderItemId(orderitemsid);
					
					if (!ordersitems.isPresent()) {
						throw new OrderNotFoundException(ConstantValues.ORDERS_NOT_FOUND);
						
					} else {
						ordersitems.get().setOrdercodeid(ordercodeid);
		        		 OrderItems orderitems_put =orderItemsRepository.save(ordersitems.get());
		        		 if(orderitems_put.getOrderid()== orderid) {
		        			 ResponseBean response = new ResponseBean();
								response.setMessage("Data inserted in DB");
								response.setResponsecode(2000);
								response.setTiemstamp(System.currentTimeMillis());
								return new ResponseEntity<ResponseBean>(response,  HttpStatus.ACCEPTED);
		        		 }
			        	 else {
						    	throw new  DBValueInsertException(ConstantValues.DATA_NOT_UPDATED_IN_DB);
			        	 }
						}
					
				}else {
					throw new DBInsertException(ConstantValues.DATA_NOT_INSERTED_IN_DB);
				}
			}
			
			public void deleteOrderItems(long customerid)
			{
				
				List<Long> receipt_id=ordersRepository.getIds(ConstantValues.ORDER_PAYMENT_PENDING,customerid);
				if(receipt_id.size()>0) {
					for(Long i:receipt_id) {					
						List<OrderItems> oi  = orderItemsRepository.getAllOrders(i);
						if(oi.size()>0) {
							for(OrderItems o:oi) {
								orderItemsRepository.deleteById(o.getOrderitemsid());
							}
						}
						ordersRepository.deleteById(i);
					}
				}
				
			}
	
			@PutMapping(path=URLMapping.OrderUpadate)
			@ResponseBody
			public ResponseEntity<ResponseBean> ordercodeUpadeForOrders(
					@RequestParam(name = ConstantValues.ORDER_ID, required = true, defaultValue = ConstantValues.DEFAULT_INT) Long orderid,
					@RequestParam(name = ConstantValues.ORDERCODEID, required = true, defaultValue = ConstantValues.DEFAULT_INT) Long ordercodeid)
			{
				     Optional<Orders> orders = ordersRepository.getOrdersListByOrderid(orderid);
	        		 List<OrderItems> ordersitems = orderItemsRepository.getAllOrders(orderid);
	        		 boolean flag = false;
	        		
	        		 if(ordersitems.size()>0)
		        	 for(OrderItems oi:ordersitems)
		        	 {
		        		 if(oi.getOrdercodeid()== ordercodeid) {
		        			
		        			 orders.get().setOrdercodeid(ordercodeid);		        			 
		        			 ordersRepository.save(orders.get());
		        			 flag=true;
		        			 break;
		        		 }
		        		 else {
		        			flag=false;
		        		 }
		        		
		        	 }
		        	
		        	 if(flag) {
		        	
		        		ResponseBean response=new ResponseBean();
		        		response.setMessage("Data updated in DB");
		 				response.setResponsecode(2000);
		 				response.setTiemstamp(System.currentTimeMillis());
		 				return new ResponseEntity<ResponseBean>(response,  HttpStatus.ACCEPTED);
		        	 }
		        	 else {
					    	throw new  DBValueInsertException(ConstantValues.DATA_NOT_UPDATED_IN_DB);
		        	 }
				
			}
			
			
			
			@GetMapping(path=URLMapping.NOTIFICATION_INFO)
			@ResponseBody
			public ResponseEntity<Order_Full_Details1> getFullOrders1(@PathVariable long orderid) {
				Order_Full_Details1 details= new Order_Full_Details1();
				Optional<Orders> ord = ordersRepository.getOrdersListByOrderid(orderid);
				
				if (!ord.isPresent()) {
					throw new OrderNotFoundException(ConstantValues.ORDERS_ITEMS_NOT_FOUND);
					
					
				} else {	
						List<OrderItems> ordersitems = orderItemsRepository.getAllOrders(ord.get().getOrderid());
						System.err.println("-------------------------------------------------");
						System.err.println("Order ITems:");
						System.err.println(ordersitems);
						System.err.println("-------------------------------------------------");
						if (ordersitems.isEmpty()) {
							details.setOrderid(ord.get().getOrderid()+"");
							details.setOrderdate(ord.get().getOrderdate());
							details.setCustomerid(ord.get().getCustomerid()+"");
							details.setTotalprice(ord.get().getTotalprice()+"");
							details.setOrdercodeid(orercodesRepository.getOrdercodesByOrderCodeid(ord.get().getOrdercodeid()).get().getRemark()); 
							details.setGatewayorderid(ord.get().getGateway_orderid());
							details.setPaymenttransactionid(razorpaytransactionRepository.getalltransactionsbyId(ord.get().getGateway_orderid()).get().getRazorpay_payment_id());
							
						} else {
							details.setOrderid(ord.get().getOrderid()+"");
							details.setOrderdate(ord.get().getOrderdate());
							details.setCustomerid(ord.get().getCustomerid()+"");
							details.setTotalprice(ord.get().getTotalprice()+"");
							details.setOrdercodeid(orercodesRepository.getOrdercodesByOrderCodeid(ord.get().getOrdercodeid()).get().getRemark());
							details.setGatewayorderid(ord.get().getGateway_orderid());
							details.setAddressid(ord.get().getAddressid()+"");
							details.setPaymenttransactionid(razorpaytransactionRepository.getalltransactionsbyId(ord.get().getGateway_orderid()).get().getRazorpay_payment_id());
						List<OrderItems> abc = new ArrayList<>();
						for(OrderItems efg:ordersitems)
						{
							abc.add(efg);
						}
						System.err.println("-------------------------------------------------");
						System.err.println("Order ITems in ABC:");
						System.err.println(abc);
						System.err.println("-------------------------------------------------");
						details.setOrderitems(abc);
					
						}
					return new ResponseEntity<Order_Full_Details1> (details, HttpStatus.ACCEPTED);
					
				
			}
				
			}
			
			//LAtest code to update userid and addressid
			@PutMapping(path=URLMapping.DETAILS_UPDATE)
			@ResponseBody
			public ResponseEntity<ResponseBean> update_details(
					@RequestParam(name = ConstantValues.ORDER_ID, required = true, defaultValue = ConstantValues.DEFAULT_INT) Long orderid,
					@RequestParam(name = ConstantValues.USERID, required = true, defaultValue = ConstantValues.DEFAULT_INT) Long userid,
					@RequestParam(name = ConstantValues.ADDRESSID, required = true, defaultValue = ConstantValues.DEFAULT_INT) Long addressid)
			{
				     Optional<Orders> orders = ordersRepository.getOrdersListByOrderid(orderid);
	        		 orders.get().setCustomerid(userid);
	        		 orders.get().setAddressid(addressid);
		        	 ordersRepository.save(orders.get());
		        	
		        	 Optional<Razorpaytransaction> razorpay = razorpaytransactionRepository.getalltransactionsbyOrderID(orderid+"");
		        	 razorpay.get().setCustomerid(userid);
		        	 razorpaytransactionRepository.save(razorpay.get());     	
		        		ResponseBean response=new ResponseBean();
		        		response.setMessage("Data updated in DB");
		 				response.setResponsecode(2000);
		 				response.setTiemstamp(System.currentTimeMillis());
		 				return new ResponseEntity<ResponseBean>(response,  HttpStatus.ACCEPTED);
		        	
				
			}
			
	
	
}
