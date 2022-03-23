package com.little.carrots.order.util;

public interface URLMapping {

	final static String ROOT_PATH_FOR_VALIDATION = "/**";
	final static String ORDER_ROOT_PATH = "/";
	
	// TODO Remove unused code found by UCDetector
// 	final static String TEST_PATH ="/test";
	
	static final String SHIPPING = "/shipping";
	static final String SHIPPING_BY_ID = "/shipping/{shippingid}";
	static final String SHIPPING_BY_CUSTOMER_ID = "shipcustomerid/{customerid}";
	static final String SHIPPING_BY_COURIER_ID = "shipcourierid/{courierid}";
	
static final String CART_MANAGEMENT_MAPPING_PATH = "/cart";
	
	static final String CARTMANAGEMENT_MAPPING_PATH_WITH_PATH_VARIABLE = "/cart/{cartid}";
	static final String CARTMANAGEMENT_MAPPING_PATH_VARIABLE_CUSTOMERID = "/car/{customerid}";
	
	static final String CARTMANANGEMENT_BY_CARTID = "/ca/{cartid}";
	
	
	static final String CARTMANAGEMENT_BY_CUSTOMERID = "/cm/{cartid}/{productid}/{customerid}";
	
	
	//order management
	static final String CREATE_ORDER = "/order";
	static final String GET_ORDERS = "/order/{ordercodeid}/{dummy}";
	static final String CANCEL_ORDER = "/order";
	static final String ORDERS_ITEMS = "/orderitems";
	static final String ORDER_MAPPING_BY_ORDERID = "/order/{orderid}";
	static final String ORDER_MAPPING_BY_ORDERITEMSID = "/orderitems/{orderitemsid}";
	static final String ORDERITEMS_BY_ORDERID = "/orderitems1/{orderid}";
	static final String COD = "/orders";
	static final String ORDER_MAPPING_BY_CUSTOMERID = "/order1/{customerid}";
	static final String GET_ORDERCODES = "/ordercodes";
	static final String ORDER_FULL_MAPPING = "/o101/{orderid}";
	static final String ORDER_FULL_MAPPING_BY_CUSTOMERID = "/o100/{customerid}";
	static final String GET_ALL_RazorPay_Transaction = "/razorpay";
	static final String GET_ALL_RazorPay_Transaction_ByID = "/razorpay/{razorpay_order_id}";
	
	
	static final String GET_ALL_COMPLAINTS= "/complaints";
	static final String GET_ALL_COMPLAINTS_BY_ID= "/complaints/{complaintid}";
	
	static final String ORDER_ITEMS_STATUS ="/orderitemstatus";
	static final String ORDER_ITEMS_STATUS1 ="/orderitemstatus/{orderid}";
	static final String GET_PACKAGE_SHIIPING = "/ps100";
	static final String GET_PACKAGE_SHIIPING_BY_SID = "/ps100/{sid}";
	static final String GET_PACKAGE_SHIIPING_BY_ORDERITEMSID = "/ps101/{orderitemsid}";
	
	static final String OrderUpadate = "/ou";
	static final String ORDER_ITEMS_STATUS2 = "/orderitemstatus1/{orderitemsid}";
	static final String GET_PACKAGE_SHIIPING_BY_ORDERID = "/ps102/{orderid}";
	
	static final String NOTIFICATION_INFO="/em/{orderid}";
	static final String COUPONS = "/coupon";
	static final String COUPONS1 = "/coupon1/{page}/{count}";
	
	static final String COUPONS_VERIFY = "/coupon/{couponcode}/{customerid}/{totalamount}";
	static final String APPLIED_COUPON = "/couponapplied/{couponid}/{customerid}";
	

	
	static final String GET_SOURCES = "/sources";
	static final String INVOICES = "/invoices";
	static final String GET_STATUS = "/status";
	static final String GET_SOLD_PRODUCTS = "/soldproducts";
	static final String SALESTIME = "/st/{sid}";
	static final String FULL_INVOICES = "/fi";
	static final String INVOICES_BY_USER = "/fi/{user}/{year}/{month}";
	static final String UPLOAD = "/uploadpdf/{inid}";
	static final String PDFLOCATION = "/pdfloc/{inid}";
	static final String DETAILS_UPDATE = "/du";
	
	
	
	final static String KAFKA_SERVER_URL="65.0.30.10:9092";
//	final static String KAFKA_SERVER_URL= "img.littlecarrots.com:9092";
//	final static String KAFKA_SERVER_URL= "kafka_kafka_1:29092";
}
	
	
	
	
	
	
	
	
	
	
	


