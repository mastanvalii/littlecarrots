package com.lc.sk.inventory.security.util;

public interface CustomerURLMapping {

	final static String CUSTOMERENDPOINT = "/CUST";
	final static String VERSION = "/V1";
	
	final static String CATEGORIES = "/CATE001";
	final static String CATEGORIESBYID = "/CATE001/{catid}";
	
	final static String CATEGORIESBYGENDERID = "/CATE001/{genderid}/{dummy}"; 
	
	//-- full catalog code start here ---
	final static String GET_GENDER_URL="/GENDERS";
	final static String GET_AGE_URL = "/AGE";
	
	//-- gull catalog code end here ---
	
	// -- SEARCH PRODUCTS
	static final String SEARCH1 = "/search/{search}/{des}";
	static final String SEARCH0 = "/find/{page}/{count}/{search}/{dummy}";
	
	static final String GETALLCUSTOMERADDRESS = "/caddress";
	static final String GETCUSTADDBYEMAIL="/caddress/{email}";
	static final String GETALLCUSTOMERADDRESSWITHID = "/caddress1/{addressid}";

	        
			static final String CUSTOMERLOGIN = "/user";
			static final String CUSTOMERLOGIN_EMAIL = "/ce/{email}";
			static final String CUSTOMERLOGIN_UNIQUEID = "/cu";
			static final String CLOGIN = "/user";
			static final String CPHONE = "/user";
			
			static final String SELECTEDPRODUCTS = "/sp";
			static final String SELECTEDPRODUCTS_BY_ID = "/sp/{spid}";
			static final String PRODUCTS_MAPPING_PATH_SELECTEDPRODUCTS = "prod2/{genderid}";
			
			static final String COLLECTION = "cs";
			static final String GET_BY_COLLECTION = "cs/{id}";
			
			
			static final String PRODUCTS_MAPPING_PATH_CAT = "prod1/{catid}/{season}/{occasion}";
			
			
			static final String CART_GET = "/cam";
			static final String GET_BY_CARTID = "/cam/{cartid}";
			static final String GET_BY_CUSTOMERID = "/car/{customerid}";
			static final String CARTMANAGEMENT_POST = "/cam";
			static final String DELETE_BY_CARTID = "/ca/{cartid}";
			static final String DELETE_BY_PRODUCTID = "/cm/{cartid}/{productid}/{customerid}";
			
			
			
	//order
			static final String ORDERS = "/od100";
// TODO Remove unused code found by UCDetector
// 			static final String ORDER_ITEMS = "/odt100";
// TODO Remove unused code found by UCDetector
// 			static final String ORDER_BY_CUSTOMER_ID = "/od101/{customerid}";
// TODO Remove unused code found by UCDetector
// 			static final String ORDER_ITEMS_BY_ORDERID ="/odt101/{orderid}";
// TODO Remove unused code found by UCDetector
// 			static final String COD = "/cod";
			
			
			static final String WISHLIST = "/wl";
			static final String WISHLIST_BY_CUSTOMER_ID = "/wl/{customerid}";
			static final String WISHLIST_BY_PRODUCT_ID = "/wlp/{productid}/{customerid}";	
			static final String WISHLIST_BY_PRODUCT_ID1 = "/wlp1";	
			//static final String WISHLIST_BY_CUSTOMER_PID = "/wl/{productid}";
			
			static final String PRODUCTS_MAPPING_PATH_COLLECTIONPRODUCTINFO = "/cprod/{genderid}";
			
			static final String COLLECTIONSALEFULLINFO = "/cs3/{id}/{siteview}/{genderid}";
			static final String COLLECTIONINFO = "cs2/{genderid}/{siteview}";
			
			static final String PRODUCTVARIATION = "/pv/{productid}";
	
			
			static final String NEW_COLLECTION = "/NEWCOL";
			static final String COLLECTION_BASED_ON_ICON = "/cs100";
			static final String COLLECTION_BASED_ON_SITEVIEW = "/cs101/{siteview}/{gender}";
			static final String COLLECTION_BASED_ON_LANDINGVIEW = "/cs102/{id}/{siteview}";
			static final String FULL_DETAILS = "/ods100/{orderid}";
			static final String FULL_DETAILSBYCUSTOMERID = "/ods101/{customerid}";
			
			
			 static final String RazorPay_Transaction = "/rpay";
			 static final String RazorPay_Transaction_ByID = "/rpay/{razorpay_order_id}";
			static final String PACKAGESHIPPING = "/psh101/{orderitemsid}";
			static final String EMAILSUBSCRIBE = "/es100";
			static final String SELECTEDPRODUCTS4 = "/sp4";
			
			static final String AP101="/AP101/{productid}";
			static final String SCROL_SEARCH_DATA_COUNT = "/SS100/{query}/{page}/{count}";
			static final String SCROL_SEARCH_DATA = "/SS101/{query}/{page}/{count}";

	
			static final String FEP = "/FEP";
			static final String CT099 = "/CT099";
			static final String CT099_1 = "/CT099/{productid}";
			static final String SEARCH000="/SEARCH000/{page}/{count}";
			static final String SEARCH001="/SEARCH001/{page}/{count}";
			
		
			static final String CARTMANAGEMENT_POST1 = "/cam1";
			static final String COLLECTION_BASED_ON_LANDINGVIEW0 = "/cs1020/{id}/{siteview}/{page}/{count}";
			static final String COLLECTION_BASED_ON_LANDINGVIEW1 = "/cs1021/{id}/{siteview}/{page}/{count}";
			
			static final String PUSH_EMAIL_NOTIFICATION="/ok/{orderid}";
			static final String CONTEST = "/con";
			static final String CONTEST2 = "/con2/{emailid}";
			static final String CONTEST3 = "/con3/{instaid}";
			static final String CONTEST4 = "/con4/{phone}";
			
			static final String COUPON = "/coupon/{couponcode}/{customerid}/{totalamount}";
			static final String COUPON_APPLIED = "/couponapplied/{couponid}/{customerid}";
			
			static final String SUGGESTION_PRODUCTS="/sugges/{productid}/{name}";
			static final String NOTIFICATIONS = "/notify10days";
			static final String UPDATE_ID_DETAILS = "/du";
			
			static final String SPP1 = "/spp1/{page}/{count}";
			static final String SPP2 = "/spp2/{page}/{count}";
			static final String VALIDATEPHONE_EMAIL = "/validate/{email}/{phone}";
}
