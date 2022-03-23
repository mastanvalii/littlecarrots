package com.little.carrots.order.util;



public interface ConstantValues {

	final static String NOT_ACCEPTABLE = "N/A";
	final static String ORDER_KEY_ID_DB = "ORDER_API_ACCESS_KEY";
	static final String BAD_CREDENTIALS = "The API key was not found \" + \"or not the expected value.";
	
	static final String SHIPPING_ID = "shippingid";
	static final String ORDER_ID = "orderid";
	static final String CUSTOMER_ID = "customerid";
	static final String STATUS = "status";
	static final String SHIPPING_STATUS = "shippingstatus";
	static final String COURIER_ID = "courierid";
	static final String COURIER_COMPANY = "couriercompany";
	static final String SHIPPING_DATE = "shippingdate";
	static final String DELIVERY_DATE = "deliverydate";
	static final String RECEIVED_NULL_VALUES = "Received null values";
	static final String DATA_UPDATED_IN_DB = "Data updated in database";
	static final String DATA_NOT_UPDATED_IN_DB = "Data not updated in database";
	static final String SHIPPING_ID_NOT_FOUND = "Shipping id not found";
	static final String SHIPPING_DATA_NOT_FOUND = "Data not found";
	static final String CUSTOMER_ID_NOT_FOUND = "Customer_id not found";
	static final String COURIER_ID_NOT_FOUND = "Courier id not found";
	static final String INTEGER = "0";
	
	static final String CART_NOT_FOUND = "cart not found";
	static final String PRODUCT_ID = "productid";
	static final String PRODUCT_PRICE = "productprice";
	static final String QUANTITY = "quantity";
// TODO Remove unused code found by UCDetector
// 	static final String CART_DATE = "cartdate";
	static final String TOTAL_PRICE = "totalprice";

	final static String DEFAULT_STRING = "no name";
	final static String DEFAULT_INT = "0";
 	final static String DEFAULT_FLOAT = "0";
	final static boolean DEFAULT_BOOLEAN = false;

	static final String DATA_INSERTED_IN_DB = "Data inserted";
	static final String DATA_NOT_INSERTED_IN_DB = "Data not inserted";
	static final String CARTMANAGEMENT_CARTID = "cartid was deleted";
	static final String CARTMANAGEMENT_PRODUCTID = "cartid and productid was deleted";
	
	
	//order management

// TODO Remove unused code found by UCDetector
// 	static final String ORDER_STATUS = "orderstatus";
// TODO Remove unused code found by UCDetector
// 	static final String PAYMENT_TRANSACTION_ID = "paymenttransactionid";

	static final String ORDER_ITEMS_ID = "orderitemsid";
	static final String ORDERS_NOT_FOUND = "order not found";
	static final String ORDERS_ITEMS_NOT_FOUND = "order id not found";

	static final String TOTAL_AMOUNT = "totalamount";
 	static final String ORDER_DATE = "orderdate";
// TODO Remove unused code found by UCDetector
// 	static final String DELIVERYDATE = "deliverydate";
	static final String ORDER_ACCEPT_STATUS = "order_accept_status";

	static final String REMARK = "remark";
	static final String ORDERCODEID = "ordercodeid";
	static final String ORDERITMES_ID = "orderitemsid";
	
	
	static final int ESTIMATED_DELIVERY_DAYS = 10;
	static final String CURRENCY_FORMAT = "INR";
	static final String ADDRESSID = "addressid";
	static final String GATEWAY_ORDERID = "gateway_orderid";
	
	static final String RAZORPAY_ORDER_ID = "razorpay_order_id";
	static final String RAZORPAY_SIGNATURE = "razorpay_signature";
	static final String RAZORPAY_PAYMENT_ID = "razorpay_payment_id";
	
	static final String RAZORPAY_TRANSACTION_NOT_FOUND = "Razorpay Transaction Not Found Exception";
	
	static final String BAD_REQUEST = "BAD request";
	
	static final String COMPLAIN_RAISE_SUCCESS="Complaint Raised Successfully, Soon our team will call you";
	
	
	
	static final long ORDER_PAYMENT_PENDING = 1001;
 	static final long ORDER_PAYMENT_SUCESS = 1002;
 	static final long ORDER_ACCEPTED = 1003;
 	static final long ORDER_READY_TO_DISPATCH = 1004;
 	static final long ORDER_DISPATCHED = 1005;
 	static final long ORDER_DELIVERED = 1006;
 	static final long ORDER_CANCELLED = 2001;
	static final long ORDER_RETURN = 2002;
	static final long ORDER_PAYMENT_FAILED = 3001;
	
	static final String MESSAGE = "msg";
	static final String PACKAGE_SHIPPING_NOT_FOUND = "Package Shipping not found";
	static final String ORDER_ITEMSID = "orderitemsid";
	static final String SHIPPING_ORDERID = "shippingorderid";
	static final String SHIPPING_SERVICE = "shippingservice";
	static final String AWB = "awb";
	static final String TRACKING_URL = "trackingurl";
	static final String INFORMATION = "information";
	static final String SID = "sid";
	static final String COUPON = "coupon";
	

	static final String STARTDATE = "startdate";
	static final String ENDDATE = "enddate";
	static final String DISCOUNT = "discount";
	static final String MINBILL = "minbill";
	static final String TITLE = "title";
	static final String SOURCES_NOT_FOUND = "Sources not found";
	static final String SOURCE = "source";
	static final String INVOICE_NOT_FOUND = "Invoice not found";
	static final String ODERID = "orid";
	static final String ORDERDATE = "dateoforder";
	static final String INVOICEDATE = "dateofinvoice";
	static final String TOTAL = "total";
	static final String PAYMENTMODE = "paymentmode";
	static final String TRANSACTIONID = "transactionid";
	static final String USER = "user";
	static final String INVOICEID = "invoiceid";
	static final long LITTLE_CARROTS = 1;
	static final long MARKETING = 2;
	static final long LOCAL = 3;
	static final String SHIPPING = "shipping";
	static final String STATUSOFTHEINVOICE = "statusoftheinvoice";
	static final String SOLD_PRODUCTS_NOT_FOUND = "Sold products not found";
	static final String USERID = "userid";
	
	}
