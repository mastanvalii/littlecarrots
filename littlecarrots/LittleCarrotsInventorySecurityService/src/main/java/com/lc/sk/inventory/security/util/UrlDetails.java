/**
 * 
 */
package com.lc.sk.inventory.security.util;

import java.io.Serializable;

/**
 * @author Mastanvali Shaik
 * LittleCarrotsInventorySecurityService
 * 2020
 */
public interface UrlDetails extends Serializable {
	
	final static String AUTHORIZATION_SERVICE_URL_GET_ID = "AUTHORIZATION_GET";
	final static String AUTHORIZATION_SERVICE_URL_POST_ID = "AUTHORIZATION_POST";
	final static String AUTHORIZATION_SERVICE_URL_PUT_ID = "AUTHORIZATION_PUT";
	final static String AUTHORIZED_USER_SERVICE_URL_GET_ID = "AUTHORIZED_USER_GET";
	final static String AUTHORIZED_USER_SERVICE_URL_POST_ID = "AUTHORIZED_USER_POST";
	final static String AUTHORIZED_USER_SERVICE_URL_LOGIN_ID = "AUTHORIZED_USER_LOGIN_POST";
	final static String AUTHORIZED_USER_SERVICE_URL_PUT_ID = "AUTHORIZED_USER_PUT";
	final static String SELLER_SERVICE_URL_GET_ID = "SELLER_GET";
	final static String SELLER_SERVICE_URL_POST_ID = "SELLER_POST";
	final static String SELLER_SERVICE_URL_PUT_ID = "SELLER_PUT";
	final static String SELLER_USER_GET = "SELLER_USER_GET";
	final static String SELLER_USER_PUT = "SELLER_USER_PUT";
	final static String SELLER_USER_POST_LOGIN = "SELLER_USER_POST_LOGIN";
	final static String SELLER_USER_POST = "SELLER_USER_POST";
	final static String COUNTRIES_GET = "COUNTRIES_GET";
	final static String COUNTRIES_PUT = "COUNTRIES_PUT";
	final static String COUNTRIES_ACTIVE_GET = "COUNTRIES_ACTIVE_GET";
	static final String GENDERS_GET = "GENDERS_GET";
	final static String CATEGORIES_POST = "CATEGORIES_POST";
	static final String CATEGORIES_PUT = "CATEGORIES_PUT";
	static final String COLORSERVICE_POST = "COLORSERVICE_POST";
	static final String CATEGORIES_GET = "CATEGORIES_GET";
	static final String COLORSERVICE_GET = "COLORSERVICE_GET";
	static final String MATERIALTYPES_GET = "MATERIALTYPES_GET";
	static final String MATERIALTYPES_POST = "MATERIALTYPES_POST";
	static final String MATERIAL_PUT_ID = "MATERIALTYPES_PUT";	
	static final String COLORSERVICE_PUT = "COLORSERVICE_PUT";
// TODO Remove unused code found by UCDetector
// 	static final String COLOR_ACTIVE_GET = "COLOR_ACTIVE_GET";
	static final String SIZES_POST = "SIZES_POST";
	static final String SIZES_PUT = "SIZES_PUT";
	static final String SIZES_GET = "SIZES_GET";	
	final static String SUBCATEGORIES_GET = "SUBCATEGORIES_GET";
	final static String SUBCATEGORIES_POST = "SUBCATEGORIES_POST";
	final static String SUBCATEGORIES_PUT = "SUBCATEGORIES_PUT";
	static final String BATCHES_GET = "BATCHES_GET";
	static final String BATCHES_PUT = "BATCHES_PUT";	
	static final String BATCHES_POST = "BATCHES_POST";
	final static String SEASONWEAR_GET = "SEASONWEAR_GET";
	final static String SEASONWEAR_POST = "SEASONWEAR_POST";
	final static String SEASONWEAR_PUT = "SEASONWEAR_PUT";
	final static String PRODUCTTYPE_GET = "PRODUCTTYPE_GET";
	final static String PRODUCTTYPE_POST = "PRODUCTTYPE_POST";
	final static String PRODUCTTYPE_PUT = "PRODUCTTYPE_PUT";
	final static String OCCASIONALWEAR_GET = "OCCASIONALWEAR_GET";
	final static String OCCASIONALWEAR_POST = "OCCASIONALWEAR_POST";
	final static String OCCASIONALWEAR_PUT = "OCCASIONALWEAR_PUT";
	static final String MATERIALCOMP_GET = "MATERIALCOMPOSITION_GET";
	static final String MATERIALCOMP_PUT_ID = "MATERIALCOMPOSITION_PUT";	
	static final String MATERIALCOMP_POST = "MATERIALCOMPOSITION_POST";
	static final String PRICESTABLE_GET = "PRICESTABLE_GET";
	static final String PRICESTABLE_POST = "PRICESTABLE_POST";
	static final String PRICESTABLE_PUT = "PRICESTABLE_PUT";
	static final String PRODUCTAGE_POST = "PRODUCTAGE_POST";
	static final String PRODUCTAGE_GET = "PRODUCTAGE_GET";
	static final String PRODUCTAGE_PUT = "PRODUCTAGE_PUT";	
	static final String PRODUCTQUANTITIES_GET = "PRODUCTQUANTITIES_GET";
	static final String PRODUCTQUANTITIES_PUT = "PRODUCTQUANTITIES_PUT";
	static final String PRODUCTQUANTITIES_POST = "PRODUCTQUANTITIES_POST";
	static final String PRODUCTDESCRIPTION_POST = "PRODUCTDESCRIPTION_POST";
	static final String PRODUCTDESCRIPTION_PUT = "PRODUCTDESCRIPTION_PUT";
	static final String PRODUCTDESCRIPTION_GET = "PRODUCTDESCRIPTION_GET";
	static final String NOOFPIECES_POST = "NOOFPIECES_POST";
	static final String NOOFPIECES_GET = "NOOFPIECES_GET";
	static final String NOOFPIECES_PUT = "NOOFPIECES_PUT";
	static final String WAREHOUSES_GET = "WAREHOUSES_GET";
	static final String WAREHOUSES_POST = "WAREHOUSES_POST";
	static final String WAREHOUSES_PUT = "WAREHOUSES_PUT";
	final static String PATTERNS_GET = "PATTERNS_GET";
	final static String PATTERNS_POST = "PATTERNS_POST";
	final static String PATTERNS_PUT = "PATTERNS_PUT";
	static final String WAREHOUSETOSELLER_POST = "WAREHOUSETOSELLER_POST";
	static final String WAREHOUSETOSELLER_GET = "WAREHOUSETOSELLER_GET";
	static final String PRODUCTS_POST = "PRODUCTS_POST";
	static final String PRODUCTS_PUT = "PRODUCTS_PUT";
	static final String PRODUCTS_GET = "PRODUCTS_GET";
	
	static final String EMAIL_POST = "EMAIL_POST";
	
// TODO Remove unused code found by UCDetector
// 	final static String IMAGE_POST = "IMAGE_POST";
// TODO Remove unused code found by UCDetector
// 	final static String IMAGE_GET = "IMAGE_GET";
// TODO Remove unused code found by UCDetector
// 	final static String IMAGE_GET1 = "IMAGE_GET1";
// TODO Remove unused code found by UCDetector
// 	final static String IMAGE_GET2 = "IMAGE_GET2";
// TODO Remove unused code found by UCDetector
// 	final static String IMAGE_GET3 = "IMAGE_GET3";
	
// TODO Remove unused code found by UCDetector
// 	final static String PRODUCT_DETAILS = "PRODUCT_DETAILS";
	static final String PRODUCT_COUNT = "PRODUCT_COUNT";
	static final String OUT_OF_STOCK = "OUT_OF_STOCK";
	static final String LOW_STOCK = "LOW_STOCK";
	
	static final String LC_PROPS_PUT = "LC_PROPS_PUT";
	static final String LC_PROPS_POST = "LC_PROPS_POST";
	static final String LC_PROPS_GET = "LC_PROPS_GET";
	static final String LC_PROPS_GET1 = "LC_PROPS_GET1";
	static final String LC_PROPS_GET2 = "LC_PROPS_GET2";
	static final String SUBCAT_DETAILS = "SUBCAT_DETAILS";
	
	static final String APPROVAL_POST = "APPROVAL_POST";
	static final String APPROVAL_GET = "APPROVAL_GET";
	static final String APPROVAL_PUT = "APPROVAL_PUT";
	
	static final String PRODUCT_DETAILS_GET = "PROUDCT_DETAILS_GET";
// TODO Remove unused code found by UCDetector
// 	static final String PRODUCT_DETAILS_GET_WITH_PATH = "PRODUCT_DETAILS_GET_WITH_PATH";
// TODO Remove unused code found by UCDetector
// 	static final String PRODUCT_DETAILS_GET_WITH_PATH1 = "PRODUCT_DETAILS_GET_WITH_PATH1";
	static final String SIZES_LIST_GET = "SIZES_LIST_GET";

	static final String NO_PATTERNS = "NO_PATTERNS";
	static final String NO_PRICE = "NO_PRICE";
	static final String NO_PIECES = "NO_PIECES";
	
	static final String PRO_DESCRIPTION = "PRO_DESCRIPTION";
	static final String PRO_QUANTITY = "PRO_QUANTITY";
	static final String PRO_QUANTITY_PUT = "PRO_QUANTITY_PUT";
	
	static final String CAT_BGU = "CAT_BGU";
	static final String PAGENATION1 = "PAGENATION1";
	static final String PAGENATION = "PAGENATION";
	static final String DISPLAY_GET = "DISPLAY_GET";
	
	static final String BADGES_GET = "BADGES_GET";
	static final String BADGES_POST = "BADGES_POST";
	
	static final String COLLECTIONSALE_GET = "COLLECTIONSALE_GET";
	static final String COLLECTIONSALE_POST = "COLLECTIONSALE_POST";
	
	static final String SEARCH_GET = "SEARCH_GET";
	
	static final String SELECTEDPRODUCTS_GET = "SELECTEDPRODUCTS_GET";
	static final String SELECTEDPRODUCTS_POST = "SELECTEDPRODUCTS_POST";
	static final String SELECTEDPRODUCTS_GENDER_GET = "SELECTEDPRODUCTS_GENDER_GET";
	
	//Wishlist
	static final String WISHLIST_POST = "WISHLIST_POST";
	static final String WISHLIST_GET = "WISHLIST_GET";
	static final String WISHLIST_DELETE_PRODUCT_ID = "WISHLIST_DELETE_PRODUCT_ID";
// TODO Remove unused code found by UCDetector
// 	static final String WISHLIST_DELETE = "WISHLIST_DELETE";
// TODO Remove unused code found by UCDetector
// 	static final String WISHLIST_GET_PRODUCT_ID = "WISHLIST_GET_PRODUCT_ID";
	
	static final String CUSTOMERADDRESS_GET = "CUSTOMERADDRESS_GET";
	static final String CUSTOMERADDRESS_POST = "CUSTOMERADDRESS_POST";
	static final String CUSTOMERADDRESS_PUT = "CUSTOMERADDRESS_PUT";
	static final String CUSTOMERADDRESS_GET_BY_ADDRESS = "CUSTOMERADDRESS_GET_BY_ADDRESS";
	static final String USER_GET_EMAIL = "USER_GET_EMAIL";
	static final String USER_GET_UNIQUEID = "USER_GET_UNIQUEID";
	static final String USER_GET = "USER_GET";
	static final String USER_PUT = "USER_PUT";
	static final String USER_POST = "USER_POST";
	static final String PAGENATION3 = "PAGENATION3";
	static final String PAGENATION2 = "PAGENATION2";
	static final String SHIPPING_POST = "SHIPPING_POST";
	static final String SHIPPING_PUT = "SHIPPING_PUT";
	static final String SHIPPING_GET = "SHIPPING_GET";
	static final String SHIPPING_GET_BY_CUSTOMER_ID = "SHIPPING_GET_BY_CUSTOMER_ID";
	static final String SHIPPING_GET_BY_COURIER_ID = "SHIPPING_GET_BY_COURIER_ID";
	static final String PAGENATION4 = "PAGENATION4";
	static final String PAGENATION5 = "PAGENATION5";
	
	static final String CART_GET = "CART_GET";
	static final String CART_GET_CUSTOMERID = "CART_GET_CUSTOMERID";
	static final String CART_POST = "CART_POST";
	static final String CART_DELETE_CARTID = "CART_DELETE_CARTID";
	static final String CART_DELETE_PRODUCTID = "CART_DELETE_PRODUCTID";
	static final String CART_PUT = "CART_PUT";

	
	
	//order
	
	static final String ORDER_POST = "ORDER_POST";
	static final String ORDER_PUT = "ORDER_PUT";
	static final String ORDER_GET = "ORDER_GET";
// TODO Remove unused code found by UCDetector
// 	static final String ORDER_GET_ID = "ORDER_GET_ID";
// TODO Remove unused code found by UCDetector
// 	static final String ORDER_ITEMS_GET = "ORDER_ITEMS_GET";
// TODO Remove unused code found by UCDetector
// 	static final String ORDER_GET_ITEMS_ID = "ORDER_GET_ITEMS_ID";
// TODO Remove unused code found by UCDetector
// 	static final String ORDER_GET_ITEMS_BY_ORDER_ID = "ORDER_GET_ITEMS_BY_ORDER_ID";
	
	static final String BLOGREPORT_GET = "BLOGREPORT_GET";
	static final String BLOGREPORT_POST = "BLOGREPORT_POST";
// TODO Remove unused code found by UCDetector
// 	static final String BLOGREPORT_PUT = "BLOGREPORT_PUT";	
	
	static final String GRANNYBLOG_GET = "GRANNYBLOG_GET";
	static final String GRANNYBLOG_PUT = "GRANNYBLOG_PUT";
	static final String GRANNYBLOG_POST = "GRANNYBLOG_POST";
	static final String COLLECTIONSALE_GET_BY_GENDER = "COLLECTIONSALE_GET_BY_GENDER";
	static final String PAGENATION7 = "PAGENATION7";
	static final String PAGENATION8 = "PAGENATION8";
	static final String COLLECTIONSALEPROD_GET = "COLLECTIONSALEPROD_GET";
	static final String COLLECTIONSALEPROD_POST = "COLLECTIONSALEPROD_POST";
	static final String COLLECTIONSALEPROD_GET_SID = "COLLECTIONSALEPROD_GET_SID";
	static final String COLLECTIONSALE_IMAGES_GET = "COLLECTIONSALE_IMAGES_GET";
	static final String COLLECTIONSALE_IMAGES_POST = "COLLECTIONSALE_IMAGES_POST";
	static final String COLLECTION_PRODUCT_INFO_GET = "COLLECTION_PRODUCT_INFO_GET";
	static final String COLLECTION3 = "COLLECTION3";
	static final String COLLECTION2 = "COLLECTION2";
	static final String SELECTED_PRODUCTS_MULTISELECT = "SELECTED_PRODUCTS_MULTISELECT";
	static final String PRODUCTVARIATION_GET = "PRODUCTVARIATION_GET";
	static final String PRODUCTVARIATION_POST = "PRODUCTVARIATION_POST";
	static final String SIMILARPRODUCTS_POST = "SIMILARPRODUCTS_POST";
	static final String SIMILARPRODUCTS_GET = "SIMILARPRODUCTS_GET";
	static final String SIMILAR_PRODUCTS_MULTISELECT = "SIMILAR_PRODUCTS_MULTISELECT";
	static final String PRODUCT_VARITION_INFO = "PRODUCT_VARITION_INFO";
	
	
	static final String NEW_COLLECTION = "NEW_COLLECTION";
	
	static final String COLLECTION_ICONMENU = "COLLECTION_ICONMENU";
	static final String COLLECTION_SITEVIEW = "COLLECTION_SITEVIEW";
	static final String COLLECTION_LANDING_VIEW = "COLLECTION_LANDING_VIEW";
	
	static final String PRODUCTKEYWORDS_GET = "PRODUCTKEYWORDS_GET";
	static final String PRODUCTKEYWORDS_PUT = "PRODUCTKEYWORDS_PUT";
	static final String PRODUCTKEYWORDS_POST = "PRODUCTKEYWORDS_POST";
	
	static final String ORDERCODES_GET = "ORDERCODES_GET";
	static final String ORDERCODES_POST = "ORDERCODES_POST";
// TODO Remove unused code found by UCDetector
// 	static final String ORDER_ITEMS_GET_BY_ORDERID = "ORDER_ITEMS_GET_BY_ORDERID";
// TODO Remove unused code found by UCDetector
// 	static final String ORDER_ITEMS_POST = "ORDER_ITEMS_POST";
// TODO Remove unused code found by UCDetector
// 	static final String ORDER_GET_CUSTOMERID = "ORDER_GET_CUSTOMERID";
	static final String ORDER_FULL_DETAILS_BY_ORDERID = "ORDER_FULL_DETAILS_BY_ORDERID";
	static final String ORDER_FULL_DETAILS_CUSTOMERID = "ORDER_FULL_DETAILS_CUSTOMERID";
	static final String ORDERCODES_PUT = "ORDERCODES_PUT";
	
	
	static final String RAZORPAY_GET = "RAZORPAY_GET";
	static final String RAZORPAY_POST ="RAZORPAY_POST";

	static final String COMPLAINT_GET = "COMPLAINT_GET";
	static final String COMPLAINT_POST = "COMPLAINT_POST";
	
	static final String ORDER_ITEMS_STATUS_POST = "ORDER_ITEMS_STATUS_POST";
	static final String ORDER_ITEMS_STATUS_GET = "ORDER_ITEMS_STATUS_GET";
	
	static final String PACKAGESHIPPING_GET = "PACKAGESHIPPING_GET";
	static final String PACKAGESHIPPING_POST = "PACKAGESHIPPING_POST";
	static final String PACKAGESHIPPING_PUT = "PACKAGESHIPPING_PUT";
	static final String PACKAGESHIPPINGBYORDERSITEMSID = "PACKAGESHIPPINGBYORDERSITEMSID";
	static final String ORDERITEMSTATUS_BY_ITEMSID = "ORDERITEMSTATUS_BY_ITEMSID";
	static final String ORDER_TABLE_STAUS_PUT = "ORDER_TABLE_STAUS_PUT";
	static final String PACKAGESHIPPINGBYORDERID = "PACKAGESHIPPINGBYORDERID";
	static final String SUBSCRIBE_POST = "SUBSCRIBE_POST";
	static final String SUBSCRIBE_GET = "SUBSCRIBE_GET";
	static final String GRANNYBLOG_LATEST5_GET = "GRANNYBLOG_LATEST5_GET";
	static final String SELECTEDPRODUCTS_GET_4 = "SELECTEDPRODUCTS_GET_4";
	
	final static String IMAGE_GET4 = "IMAGE_GET4";
	static final String SCROL_SEARCH_GET = "SCROL_SEARCH_GET";
	static final String SCROL_SEARCH_GET_INFO = "SCROL_SEARCH_GET_INFO";
	
	static final String CACHE_DATA1 = "CACHE_DATA1";
	static final String PRODUCTVARIATION_PRODUCTIDS = "PRODUCTVARIATION_PRODUCTIDS";
	
	
	
	final static String IMAGE_GET_S2 = "IMAGE_GET_S2";
	final static String ORDER_CHK = "ORDER_CHK";
	static final String CONTEST = "CONTEST";
	static final String CONTEST_GET = "CONTEST_GET";
	static final String CONTEST_GET1 = "CONTEST_GET1";
	static final String CONTEST_GET2 = "CONTEST_GET2";
	static final String CONTEST_GET3 = "CONTEST_GET3";
	static final String CONTEST_GET4 = "CONTEST_GET4";
	static final String CONTEST_GET_MONTH = "CONTEST_GET_MONTH";
	static final String CONTEST_GET_MONTH1 = "CONTEST_GET_MONTH1";
	static final String MONTHS = "MONTHS";
// TODO Remove unused code found by UCDetector
// 	static final String GET_IMG = "GET_IMG";
	static final String IMGLOC = "IMGLOC";
// TODO Remove unused code found by UCDetector
// 	static final String IMGLOC1 = "IMGLOC1";
	
	
	static final String COUPON_POST = "COUPON_POST";
	 static final String COUPON_GET ="COUPON_GET";
	 static final String COUPON_PUT ="COUPON_PUT";
	static final String COMPLAINT_PUT = "COMPLAINT_PUT";
	static final String SOURCE_GET = "SOURCE_GET";
	static final String INVOICES_GET = "INVOICES_GET";
	static final String STATUS_GET = "STATUS_GET";
	static final String SOLD_PRODUCTS_GET = "SOLD_PRODUCTS_GET";
	static final String INVOICE_POST = "INVOICE_POST";
	static final String SALE_TIME_GET = "SALE_TIME_GET";
	static final String FULL_INFO = "FULL_INFO";
	static final String PDFUPLOAD = "PDFUPLOAD";
	static final String PDFLOC = "PDFLOC";
	static final String DETAILS_PUT = "DETAILS_PUT";
	
	
	static final String SP_GET1_PAGE = "SP_GET1_PAGE";
	static final String SP_GET2_PAGE = "SP_GET2_PAGE";
// TODO Remove unused code found by UCDetector
// 	static final String COMBOS_GET = "COMBOS_GET";
	static final String COMBOS_POST = "COMBOS_POST";
	static final String COMBOS__PROD_POST = "COMBOS__PROD_POST";
	static final String COMBOS_FULL_DETAILS = "COMBOS_FULL_DETAILS";
	static final String COMBOID_WITHOUT_DETAILS = "COMBOID_WITHOUT_DETAILS";
	
	
	final static String IMAGE_GET5 = "IMAGE_GET5";
	static final String VALIDATE_MAIL_PHONE = "VALIDATE_MAIL_PHONE";
}
