/**
 * 
 */
package com.lc.sk.inventory.security.util;

import java.io.Serializable;

/**
 * @author Mastanvali Shaik LittleCarrotsInventorySecurityService 2020
 */
public interface ConstantValues extends Serializable {

// TODO Remove unused code found by UCDetector
// 	final static String HEADER_KEY_DB_ID = "SECU_API_ACCESS_KEY";
	final static String AUTH_HEADER_KEY_DB_ID = "AUTH_API_ACCESS_KEY";
	final static String INVENTORY_API_ACCESS_KEY = "INVENTORY_API_ACCESS_KEY";
	final static String EMAIL_API_ACCESS_KEY = "EMAIL_API_ACCESS_KEY";

// TODO Remove unused code found by UCDetector
// 	final static String FORBIDDEN_403_INFO = "The API key was not found or not the expected value.";

	static final String MATERIAL_NAME = "materialname";
	static final String DESCRIPTION = "description";
	static final String OCCASION_ID = "occasionid";
	static final String SEASON_ID = "seasonid";
	static final String CATEGORY_ID = "catid";

	static final String MATERIAL_ID = "materialid";

	final static String ERROR_BEAN_RETURNED = "Returned Error Bean";

	final static String SLASH_TAG = "/";

	final static String AUTHORIZATION_ROLE_NAME_VARIABLE_NAME = "rolename";
	final static String AUTHORIZATION_ROLE_DESCRIPTIONS_VARIABLE_NAME = "descriptions";
	final static String AUTHORIZATION_ROLE_STATUS_VARIABLE_NAME = "rolestatus";

// TODO Remove unused code found by UCDetector
// 	final static String DEFAULT_INT_VALUE = "0";
// TODO Remove unused code found by UCDetector
// 	final static String DEFAULT_STRING_VALUE = "noname";

	final static String AUTHORIZED_USERNAME = "username";
	final static String AUTHORIZED_PASSWORD = "password";
	final static String AUTHORIZED_NEW_PASSWORD = "newpassword";
	final static String AUTHORIZED_STATUS = "status";
	final static String AUTHORIZED_USER_EMAIL = "email";

	final static String SELLER_ID = "sellerid";
	final static String SELLER_NAME = "sellername";
	final static String SELLER_COMPANY_NAME = "sellercompanyname";
	final static String PHONE_NUMBER = "phonenumber";
	final static String ADDRESS = "address";
	final static String EMAIL = "email";
	final static String ISOCODE = "isocode";
	final static String STATUS = "status";
	final static String FULLNAME = "fullname";

	final static String REGEX = "[]";

// TODO Remove unused code found by UCDetector
// 	final static String SESSION_USERNAME = "username";
// TODO Remove unused code found by UCDetector
// 	final static String SESSION_TOKENID = "tokenid";
// TODO Remove unused code found by UCDetector
// 	final static String SESSION_ROLENAME = "rolename";
// TODO Remove unused code found by UCDetector
// 	final static String SESSION_EMAIL = "email";

	static final String COLOR_NAME = "colorname";
	static final String HASHCODE = "hashcode";
	static final String COLOR_ID = "colorid";

	final static String SUBCATID = "subcatid";
	final static String CATID = "catid";
	final static String CATEGORYNAME = "categoryname";

	final static String SEASON = "season";

	final static String PROTYPE_ID = "protypeid";
	final static String SUBCAT_ID = "subcatid";
	final static String PRODUCTTYPE_NAME = "producttypename";

	final static String OCCANAME = "occaname";
	final static String OCCASIONID = "occasionid";

	static final String AGE_ID = "ageid";
	static final String DES = "description";

	static final String WAREHOUSE_ID = "warehouseid";
	static final String DATE_OF_PURCHASE = "dateofpurchase";
	static final String PURCHASE_BY = "purchasedby";
	static final String INV_AMOUNT = "invamount";
	static final String WHO_INSERTED = "whoinserted";
	static final String WAREHOUSE_STATUS = "status";
	static final String QUANTITY = "qty";

	final static String PATID = "patid";
	final static String PRODUCTID = "productid";

	static final String WAREHOUSE_NAME = "warehousename";
	static final String CONTACT_PERSON_NAME = "contactpersonname";
	static final String CONTACT_PERSON_PHONE = "contactpersonphone";
	static final String WAREHOUSE_EMAIL_ADDRESS = "email";
	static final String WAREHOUSE_ADDRESS = "address";
	static final String WHAREHOUSE_PIN = "pincode";
	static final String WAREHOUSE_EST = "est";
	static final String WHO_CREATED = "whocreated";
	static final String WHO_UPDATED = "whoupdated";

	static final String CUST_ID = "custid";

	static final String SIZE_ID = "sizeid";

	final static String CATEGORY = "category";
	final static String GENDER = "gender";
	static final String CAT_ID = "catid";

	static final String SIZE_NO = "sizeno";
	static final String HEIGHT = "height";
	static final String WEIGHT = "weight";
	static final String CHEST = "chest";
	static final String WAIST = "waist";
	static final String HIP = "hip";

	static final String MRP = "mrp";
	static final String PRODUCT_ID = "productid";
	static final String lC_PRICE = "lcprice";
	static final String TAX = "tax";
	static final String SELLING_PRICE = "sellingprice";
	static final String FINAL_PRICE = "finalprice";
	static final String PRICE_ID = "priceid";
	static final String DISCOUNT = "discount";
	static final String PRODUCT_ID1 = "productid[]";

	static final String DESCRIPTION_ID = "descriptionid";

	static final String BATCH_ID = "batchid";
	static final String GENDER_ID = "genderid";
	static final String CUSTOMER_ID = "custid";

	static final String NOOFSETS = "noofsets";

	static final String PIECE_ID = "pieceid";

	final static String BACK_END_SERVER_NO_RESPONSE = "Back End Server not Responding...";

// TODO Remove unused code found by UCDetector
// 	final static int ACTIVE_USER = 1;
// TODO Remove unused code found by UCDetector
// 	final static String NOT_ACCEPTABLE = "N/A";
	final static String USER_ID_INVALID = "Invalid username/password...";

	// AADARSH
	final static String NO_PRODUCT_TYPE_DETAILS_NOT_FOUND = "no product type details found";
	final static String NO_PRODUCT_TYPE_NOT_FOUND = "no product type found with given id";
	final static String PRICE_ID_NOT_FOUND = "Price id not found";
	static final String NO_PRODUCT_AGES_FOUND = "No ProductAges List Found...";
	static final String NO_PRODUCT_DESCRIPTIONS_FOUND = "No Product Descriptions found....";
	static final String PRODUCT_QUANTITIES_NOT_FOUND = "ProductQuantities Details not found";
	static final String NO_PRODUCTS_FOUND = "NO PRODUCTS FOUND";

	static final String DATA_NOT_UPDATED = "Data not updated";
	static final String NO_GENDERS_LIST_FOUND = "No GendersList Found...";
	static final String NO_CATEGORIES_LIST_FOUND = "Categories Details not found";
	static final String NO_CATEGORIES_LIST_FOUND_GIVEN_CATID = "CatID detail not found";
	static final String DATA_NOT_INSERTED_IN_DB = "Data insert failed..";
	static final String CATEGORIES_UPDATE_FAIL = "Categories Update Fail..";
	static final String EMPTY_MATERIALCOMPOSITION_LIST = "materialcomposition list is empty...";
	static final String MATERIALCOMPOSITION_NOT_FOUND_WITH_GIVEN_ID = "materialcomposition not found with given id...";
	static final String EMPTY_MATERIALTYPES_LIST = "materialtype list is empty...";
	static final String MATERIALTYPES_NOT_FOUND_WITH_GIVEN_ID = "materialtypes not found with given id...";
	static final String NO_OF_SET_NOT_FOUND = "no of sets not found";
	static final String NO_OF_PIECES_NOT_FOUND = "no of pieces not found with given id.. ";
	static final String NO_OCCASIONALWEAR_LIST_FOUND = "No OccasionWear List Found...";
	static final String OCCASIONID_NOT_FOUND = "Occasion id not Found";

	// rekha
	static final String NO_COUNTRIES_LIST_FOUND_GIVEN_ISOCODE = "Not Countries List found for Given isocode.";
	static final String NO_COUNTRIES_LIST_FOUND = "No Countries List Found...";
	static final String COUNTRY_STATUS_UPDATE_FAIL = "Status update fail for given ISOCODE:";
	static final String COLOR_DETAILS_NOT_FOUND = "color details not found";
	static final String RECEIVED_NULL_VALUES = "Received Null values from Client...";
	static final String AUTHORIZATION_ROLES_NOT_FOUND = "Authorized Roles not Found...";
	static final String NULL_VALUES_RECEIVED_FROM_CLIENT = "Received Null Values from Client...";
	static final String NO_AUTHORIZED_USER_FOUND = "No Authorized User Found....";
	static final String BATCH_DETAILS_NOT_FOUND = "Batch details not found";
	static final String NO_PATTERNS_LIST_FOUND = "Patterns Details not found";

	static final String SEASONWEAR_NOT_FOUND = "Seasonwear not found";
	static final String SIZES_NOT_FOUND_EXCEPTION = "sizes not found";
	static final String SUBCAT_ID_NOT_FOUND = "SubCatID detail not found";
	static final String WAREHOUSE_NOT_FOUND = "Warehouse not found with given id...";
	static final String NO_SALES_USER_FOUND = "No Sales User Found....";
	static final String VALUE_NOT_FOUND = " No role name present";
	static final String USER_NAME_NOT_FOUND = "User name not found";
	static final String WAREHOUSE_DATA_NOT_FOUND = "Warehouse list is empty...";
// TODO Remove unused code found by UCDetector
// 	static final String DESCRIPTION_ID_NOT_FOUND = "Description id not found";

	final static String MAIL_NOT_SENT = "Mail Server has issue and mail not sent to destination";
	static final String COUNTRY_NOT_FOUND = "Country not found with given isocode";

	static final String NO_BATCH_SERVICE_FOUND = "no batch id found";
	static final Object PRODUCT_NOT_FOUND = "product details not found";

// TODO Remove unused code found by UCDetector
// 	final static String IMAGE_PARAM = "img";
// TODO Remove unused code found by UCDetector
// 	final static String IMAGE_PRODUCTID = "productid";
	final static String NOT_VALID_EMAIL_ADDRESS = "Not Valid Email address...";
	final static String NO_SELLER_FOUND = "No Sellers Found....";
	final static String SUB_CATEGORY_DETAILS_NOT_FOUND = "SubCategories Details not found";
	static final String TITLE = "title";
	static final String PROP = "prop";
	static final String PROP_STATUS = "status";
	static final String SUB_TITLE = "subtitle";

	static final String INSERTED_USER = "inserteduser";
	static final String STATUS_TEXT = "statustext";
	static final String COMMENT = "comment";
	static final String SERIAL_ID = "serialid";
	static final String APPROVAL_USER = "approvaluser";
	static final String DISPLAY_DETAILS_NOT_FOUND = "Display details not found";
	static final String DISPLAY_DETAILS_NOT_FOUND_BY_ID = "Display details not found with given id";
	static final Object NO_PRODUCT_FOUND = "No Products Found...";
	static final Object NO_APPROVALS_FOUND = "No Approvals found";

	static final Object NO_BADGES_FOUND = "no badges found";
	static final String BADGE = "badge";
	static final String DISPLAY = "display";
	// collection
	static final String STARTDATE = "startdate";
	static final String ENDDATE = "enddate";
	static final String COLLECTIONSALE_NOT_FOUND = "Collection sale not found";

// TODO Remove unused code found by UCDetector
// 	static final String PRODUCT = "productid[]";
	static final String CUSTOMER_ID1 = "customerid";
	static final String NO_WISHLIST_FOUND = "No Wishlist Found";

	static final String ADDRESSID = "addressid";
	static final String NAME = "name";
	static final String FLAT = "flat";
	static final String LANDMARK = "landmark";
	static final String STREETADDRESS = "streetaddress";
	static final String CITY = "city";
	static final String STATE = "state";
	static final String COUNTRY = "country";
	static final String MOBILE1 = "mobile1";
	static final String MOBILE2 = "mobile2";
	static final String PINCODE = "pincode";
	static final String ADDRESSTYPE = "addresstype";
	static final Object ADDRESS_NOT_FOUND = "There are no addresses";
	static final Object NO_EMAIL_LINKED_TO_THE_ADDRESS = "No address  is linked to the this email id";

	static final String UNIQUE_ID = "uniqueid";
	static final String FIRST_NAME = "firstname";
	static final String LAST_NAME = "lastname";
	static final String IMAGE_URL = "imageurl";

// TODO Remove unused code found by UCDetector
// 	static final String USER_TYPE = "usertype";
	static final String USER_ID = "userid";
	static final String PHONE = "phone";
	static final String SELECTEDPRODUCTS_NOT_FOUND = "selected products not found";

	static final String ORDER_ID = "orderid";
	static final String SHIPPING_STATUS = "shippingstatus";
	static final String COURIER_ID = "courierid";
	static final String COURIER_COMPANY = "couriercompany";
	static final String SHIPPING_DATE = "shippingdate";
	static final String DELIVERY_DATE = "deliverydate";
	static final String SHIPPING_ID = "shippingdate";
	static final String ORDER_API_ACCESS_KEY = "ORDER_API_ACCESS_KEY";

	static final String PRODUCT_PRICE = "productprice";
// TODO Remove unused code found by UCDetector
// 	static final String CART_DATE = "cartdate";
	static final String TOTAL_PRICE = "totalprice";
	static final String CART_NOT_FOUND = "cart not found";

	// order
// TODO Remove unused code found by UCDetector
// 	static final String ORDER_API_ACCESS_KEY1 = "ORDER_API_ACCESS_KEY";
	static final String CUSTOMERR_ID = "customerid";
// TODO Remove unused code found by UCDetector
// 	static final String TOTAL_PRICE1 = "totalprice";
// TODO Remove unused code found by UCDetector
// 	static final String ORDER_STATUS = "orderstatus";
// TODO Remove unused code found by UCDetector
// 	static final String DELIVERY_DATE1 = "deliverydate";
	static final String PAYMENT_TRANSACTION_ID = "paymenttransactionid";
// TODO Remove unused code found by UCDetector
// 	static final String ORDER_DATE = "orderdate";
// TODO Remove unused code found by UCDetector
// 	static final String PRODUCT_PRICE1 = "productprice";
// TODO Remove unused code found by UCDetector
// 	static final String ORDER_ID1 = "orderid";
// TODO Remove unused code found by UCDetector
// 	static final String ORDEr_ITEMS_ID = "orderitemsid";
// TODO Remove unused code found by UCDetector
// 	static final String TOTAL_AMOUNT = "totalamount";

	// Blog Report
	static final String BID = "bid";
	static final String AUTHOR_NAME = "authorname";
	static final String AUTHOR_IMAGE = "authorimage";
	static final String BLOG_REPORTS_NOT_FOUND = "Blog reports not Found";
	static final String BLOGREPORT_LIST_NOT_FOUND_GIVEN_BID = "Blog report not found with given id";
// TODO Remove unused code found by UCDetector
// 	static final String BLOGREPORT_LIST_NOT_FOUND_GIVEN_AUTHORNAME = "Blog report not found with given name";

	// Granny Blog
// TODO Remove unused code found by UCDetector
// 	static final String BLOG_ID = "blogid";
	static final String INFORMATION = "information";
	static final String POSTER_IMAGE = "thumbimage";
	static final String MOBILE_IMAGE = "mobileimage";
	static final String DESKTOP_IMAGE = "desktopimage";
// TODO Remove unused code found by UCDetector
// 	static final String DATEOFINSERTION = "dateofinsertion";
	static final String GRANNY_BLOG_NOT_FOUND = "Granny blog not Found";
	static final String GRANNYBLOG_LIST_NOT_FOUND_GIVEN_BLOGID = "Granny blog not found with given id";
// TODO Remove unused code found by UCDetector
// 	static final String GRANNYBLOG_LIST_NOT_FOUND_GIVEN_AUTHORNAME = "Grannyblog  not found with given name";
	static final String GENDERID = "genderid";
	
	static final String COLLECTIONSALE_PRODUCTIDS_NOT_FOUND = "Collectionsale productsids not found exception";
	static final String COLLECTIONSALE_IMAGES_NOT_FOUND = "Collectionsale images not found exception";
	static final String ID = "id";
	static final String ICON = "icon";
	static final String MOBILEVIEW1 = "mobileview1";
	static final String MOBILEVIEW2 = "mobileview2";
	static final String DESKTOPVIEW1 = "desktopview1";
	static final String DESKTOPVIEW2 = "desktopview2";
	static final String DEFAULT_INT = "0";
	static final String DEFAULT_STRING = "no name";
	static final String PVID = "pvid";
	static final String PVTYPE = "pvtype";
	static final String DATA_NOT_FOUND =  "Data Not Found";
	static final String ORDERS_ITEMS_NOT_FOUND = "order id not found";
	
// TODO Remove unused code found by UCDetector
// 	static final String KEYWORD_ID = "keyid";
	static final String KEYWORDS = "keywords";
// TODO Remove unused code found by UCDetector
// 	static final String PRODUCT_KEYWORDS_NOT_FOUND = "Product keywords not Found";
	static final String PRODUCT_KEYWORDS_LIST_NOT_FOUND_GIVEN_PID = "Product keywords not found with given product id";
	static final String QUANTITY1 = "quantity";
	
	static final String ORDERCODEID = "ordercodeid";
	static final String REMARK = "remark";
	static final String TOTALPRICE = "totalprice";
	static final String ORDER_NOT_FOUND = "order not found";
	static final String ORDER_ITEMS_NOT_FOUND = "order id not found";
	static final String QUANTITYARRAY = "quantity[]";
	static final String PRODUCTPRICEARRAY = "productprice[]";
	static final Object USER_NOT_FOUND = "user not found";
	static final String NO_ORDER_CODE_FOUND = "No order codes available";
	
	static final String RAZORPAY_ORDER_ID = "razorpay_order_id";
	static final String RAZORPAY_SIGNATURE = "razorpay_signature";
	static final String RAZORPAY_PAYMENT_ID = "razorpay_payment_id";	
	static final String RAZORPAY_TRANSACTION_NOT_FOUND = "Razorpay Transaction Not Found Exception";


	static final String MESSAGE = "msg";
	static final String ORDER_ITEMS_ID = "orderitemsid";
	static final String SHIPPING_ORDERID = "shippingorderid";
	static final String SHIPPING_SERVICE = "shippingservice";
	static final String AWB = "awb";
	static final String TRACKING_URL = "trackingurl";
	static final String SID = "sid";
	static final String PACKAGE_SHIPPING_NOT_FOUND = "Package Shipping not found";
	static final String EMAILID = "emailid";
	static final String SUBSCRIBER_NOT_FOUND = "Subscriber not found";
	static final String YOU_HAVE_ALREDY_SUBSCRIBED = "You have already subscribed";
	static final String ALREADY_IN_YOUR_WISHLIST = "Already in your wishlist";
// TODO Remove unused code found by UCDetector
// 	static final int ESTIMATED_DELIVERY_DAYS = 10;
	static final String INSTAID = "instaid";
	static final String CHILDNAME = "childname";
	static final String AGE = "age";
	static final String TC = "tc";
	static final String IMAGES = "images";
// TODO Remove unused code found by UCDetector
// 	static final String DATEINSERTION = "dateinseryion";
	static final String CONTESTMONTHYEAR = "contestmonthyear";
	static final String INSTA_ID_NOT_FOUND_EXCEPTION = "Insta id not found";
	static final String PHONE_NUMBER_NOT_FOUND = "Phone number not found";
	static final String EMAIL_ID_NOT_FOUND = "Email id not found";
	static final String CONTEST_NOT_FOUND = "Contest not found";
	static final String COMPLAINTID = "complaintid";
	static final String ORDERID = "orid";
	static final String DATEOFORDER = "dateoforder";
	static final String DATEOFINVOICE = "dateofinvoice";
	static final String TOTAL = "total";
	static final String SHIPPING = "shipping";
	static final String PAYMENTMODE = "paymentmode";
	static final String TRANSACTIONID = "transactionid";
	static final String STATUSOFTHEINVOICE = "statusoftheinvoice";
	static final String USER = "user";
	static final String INVOICEID = "invoiceid";
	static final String PRODUCTIDS = "prid[]";
	static final String QUANTITYS = "qty[]";
	static final String NETAMOUNT = "netamount[]";
	static final String GST = "gst[]";
	static final String DISCOUNT_ARRAY = "discount[]";
	static final String TOTALPRODUCTPRICE = "totalproductprice[]";
	static final String SOURCES_NOT_FOUND = "Sources not found";
	static final String INVOICE_NOT_FOUND = "Invoice not found";
	static final String SOLD_PRODUCTS_NOT_FOUND = "Sold products not found";
// TODO Remove unused code found by UCDetector
// 	static final String INID = "inid";
	static final String IMG = "img";
	static final String DATA_INSERTED_IN_DB = "Data insert Success..";
	static final String NOTIFICATION_NOT_FOUND_EXCEPTION = "No Notifications";
	static final String USERID = "userid";
	static final String TOTALAMOUNT = "total_amount";
	static final String AGEID = "ageid";
	static final String COMBOID = "comboid";
	static final String FINALPRICE = "finalprice[]";
	static final String TOTALAMOUNT_AFTER_DISCOUNT = "total_amt_after_discount";
	}