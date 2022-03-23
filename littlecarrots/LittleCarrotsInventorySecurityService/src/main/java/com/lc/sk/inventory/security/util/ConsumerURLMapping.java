package com.lc.sk.inventory.security.util;

public interface ConsumerURLMapping {

	final static String CEP = "/cep";
	final static String VERSION = "/v1";

	static final String USERROLES = "/ur";
	static final String USERROLES_BY_NAME = "/ur/{rolename}";
	static final String USERROLES_BY_STATUS = "/ur/{rolename}/{status}";

	static final String AUTH_USERS = "/au";
	static final String AUTH_USERS_PATH_VARIABLE = "/au/{username}";
	static final String AUTH_USER_BLOCK = "/au/{username}/{status}";

	static final String SELLER_GET_PATH = "/sell";
	static final String SELLER_GET_PATH_WITH_VARIABLE = "/sell/{username}";
	static final String SELLER_PUT_STATUS = "/sell/{username}/{status}";

	static final String SELLERROLE_GET_PATH = "/sr";
	static final String SELLERROLE_GET_PATH_WITH_VARIABLE = "/sr/{sellerid}";

	static final String WAREHOUSES_GET_PATH = "/wh";
	static final String WAREHOUSES_GET_PATH1 = "/wh1";
	static final String WAREHOUSES_MAPPING_PATH_WITH_PATH_VARIABLE = "/wh/{warehouseid}";

	static final String BATCHES_GET_PATH = "/bat";
	static final String BATCHES_MAPPING_PATH_WITH_PATH_VARIABLE = "/bat/{batchid}";
	static final String BATCHID_PUT_PATH_WITH_VARIABLE = "/bat/{batchid}/{status}";

	static final String CATEGORIES_MAPPING_PATH_WITH_ID = "/cat/{catid}";
	static final String CATEGORIES_MAPPING_PATH = "/cat";

	static final String COLOR_PATH = "/col";
	static final String COLOR_PATH_WITH_COLOR_ID = "/col/{colorid}";

	static final String COUNTRY_GET_PATH = "/cou";
	static final String COUNTRY_GET_PATH_WITH_VARIABLE = "/cou/{isocode}";
	static final String COUNTRY_PUT_PATH_WITH_VARIABLE = "/cou/{isocode}/{status}";
	static final String COUNTRY_ACTIVE_GET = "/actcou";

	static final String GENDERS_GET_PATH = "/gen";
	static final String GENDERS_MAPPING_PATH_WITH_PATH_VARIABLE = "/gen/{genderid}";

	static final String MATERIALCOMP_GET_PATH = "/mcomp";
	static final String MATERIALCOMPOSITION_GET_PATH_WITH_VARIABLE = "/mcomp/{materialid}";

	static final String MATERIAL_GET_PATH = "/mat";
	static final String MATERIALTYPE_GET_PATH_WITH_VARIABLE = "/mat/{materialid}";
	static final String MATERIAL_GET_PATH1 = "/mat/{sss}/{sss1}";
	static final String METERIAL_GET_PATH3 = "/mat1";

	static final String NO_OF_PIECES_PATH = "/nop";
	static final String NO_OF_PIECES_PATH_WITH_ID = "/nop/{pieceid}";

	static final String OCCASIONALWEAR_PATH = "/occ";
	static final String OCCASIONALWEAR_PATH1 = "/occ1";
	static final String OCCASIONALWEAR_PATH_WITH_ID = "/occ/{occasionid}";

	static final String PATTERNS_PATH = "/pat";
	static final String PATTERNS_PATH_WITH_ID = "/pat/{patid}";

	static final String PRICES_TABLE_PATH_WITH_ID = "/ptab/{productid}";
	static final String PRICES_TABLE_PATH = "/ptab";

	static final String PRODUCTAGE_PATH = "/pa";
	static final String PRODUCTAGE_PATH_WITH_ID = "/pa/{ageid}";

	static final String PRODUCTDESCRIPTION_MAPPING_PATH_WITH_ID = "/pd/{descriptionid}";
	static final String PRODUCTDESCRIPTION_MAPPING_PATH = "/pd";

	static final String PRODUCTQUANTITIES_GET_PATH = "/pq";
	static final String PRODUCTQUANTITIES_GET_PATH_WITH_VARIABLE = "/pq/{custid}";

	static final String PRODUCTS_MAPPING_PATH = "/prod";
	static final String PRODUCTS_MAPPING_PATH_PRODUCT_ID = "/prod/{productid}";
	static final String PRODUCTS_MAPPING_PATH_WITH_PATH_VARIABLE_1 = "/prod/{productid}/{status}";

	static final String PRODUCTTYPE_GET_PATH = "/pt";
	static final String PRODUCTTYPE_PATH_WITH_ID = "/pt/{protypeid}";

	static final String SALES_SELLER_GET_PATH = "/su";
	static final String SALES_SELLER_GET_PATH_WITH_VARIABLE = "/su/{username}";

	static final String SEASON_WEAR = "/sw";
	static final String SEASON_WEAR1 = "/sw1";
	static final String SEASON_WEAR_BY_ID = "/sw/{seasonid}";

	static final String WAREHOUSETOSELLER_POST = "/stw";
	static final String WAREHOUSETOSELLER_GET = "/stw";

	static final String SIZES_MAPPING_PATH_WITH_ID = "/sizes/{sizeid}";
	static final String SIZES_MAPPING_PATH = "/sizes";

	static final String SUBCATEGORIES_GET_PATH = "/sc";
	static final String SUBCATEGORIES_GET_PATH1 = "/sc1";
	static final String SUBCATEGORIES_MAPPING_PATH_WITH_VARIABLE = "/sc/{subcatid}";

	static final String PRODUCTDESCRIPTION_MAPPING_PATH1 = "/pd/{sss}/{sss1}";
	static final String PRODUCTQUANTITIES_MAPPING_PATH1 = "/pq/{sss}/{sss1}";

	final static String PRODUCT_IMG_LIST = "/imgl";
	final static String NO_IMG_PROD = "/noimg";

	final static String ACTIVE_PRODUCT_DETAILS = "/AP100";
	final static String ACTIVE_PRODUCT_DETAILS_WITH_PATH = "/AP100/{productid}";
	static final String ACTIVE_PRODUCT_DETAILS_WITH_PATH1 = "/AP100/{productid}/{status}";

	static final String PRODUCTS1 = "/prodpiece";
// TODO Remove unused code found by UCDetector
// 	static final String TOTALPRODUCTS = "/totalproducts";
// TODO Remove unused code found by UCDetector
// 	static final String OUTOFSTOCK = "/os";
	static final String LOWQUANTITY = "/lq";
	static final String PRODUCTSPRICES = "/prodprice";
	static final String PRODUCTSCOUNT = "/pc";
	static final String OUT_OF_STOCK = "/ofs";
	static final String LOW_STOCK = "/ls";
	static final String SUBCATDETAILS = "/scd";
	static final String SIZESDETAILS = "sd";
	static final String APPROVAL = "/approve";
	static final String APPROVAL_BY_ID = "/approve/{serialid}/{dummy}";
	static final String APPROVAL_BY_PRODUCT_ID = "/approve/{productid}";
	static final String PRODUCTSPATTERNS = "/prodpat";

	static final String NO_PATTERNS = "PAT001";
	static final String NO_PRICE = "PRI001";
	static final String NO_PIECES = "PIE001";

	static final String PRODUCT_PAGE_COUNT = "PPC100/{page}/{count}";
	static final String PRODUCT_IN_PAGE = "PIN100/{page}/{count}";
	static final String DISPLAYTYPE = "display";
	static final String DISPLAYTYPE_BY_ID = "display/{display}";

	static final String BADGE = "/bad";
	static final String GET_BY_BADGE = "/bad/{badge}";

	static final String COLLECTION = "cs";
	static final String GET_BY_COLLECTION = "cs/{id}";

	static final String SEASON_BY_CAT = "scat/{catid}";
	static final String OCCASION_BY_CAT = "ocat/{catid}";
	static final String PRODUCTS_MAPPING_PATH_CAT = "prod1/{catid}/{season}/{occasion}";

	static final String SELECTEDPRODUCTS = "/sp";
	static final String SELECTEDPRODUCTS_BY_ID = "/sp/{spid}";
	static final String PRODUCTS_MAPPING_PATH_SELECTEDPRODUCTS = "prod2/{genderid}";

	static final String WISHLIST = "/wl";
	//static final String WISHLIST_BY_CUSTOMER_ID = "/wl/{customerid}";
	//static final String WISHLIST_BY_PRODUCT_ID = "/wlp/{productid}/{customerid}";			
	//static final String WISHLIST_BY_CUSTOMER_PID = "/wl/{productid}";

	// Blog
	static final String BLOGREPORT = "/br";
	static final String BLOGREPORT_BY_ID = "/br/{bid}";
	static final String NEWAUTHOR_POST= "/br1/{authorname}/{description}";
	

	// GrannyBlog
	static final String GRANNYBLOG = "/gb";
	static final String GRANNYBLOG_BY_ID = "/gb/{blogid}";
	static final String GRANNYBLOG_STATUS = "/gb/{blogid}/{status}";
	static final String GRANNYBLOG_POST = "/gb1";
	
	static final String GET_BY_COLLECTION1 = "/cs1/{genderid}";
	
	static final String COLLECTIONSALEPROD = "csp";
	static final String COLLECTIONSALEPRODBYSID = "csp1/{csid}";
	static final String COLLECTIONSALEPRODBYPRODID = "/csp/{id}";
	static final String COLLECTIONSALEIMAGES = "csi";
	static final String COLLECTIONSALEIMAGES_BY_IMAGEID = "csi/{imageid}";
	static final String COLLECTIONIDS = "cs1";
	static final String COLLECTIONIMGS = "cs2";
	static final String COLLECTIONSALEIMAGESPOST = "csi1/{id}";
	static final String PRODUCTS_MAPPING_PATH_SELECTEDPRODUCTS_FOR_MULTISELECT = "/prod3/{genderid}";
	static final String PRODUCTVARIATION = "/pv";
	static final String SimilarProducts = "/sm";
	static final String PRODUCTS_MAPPING_PATH_SIMILARPRODUCTS_FOR_MULTISELECT = "/prod4";

	//Product Keywords
	static final String PRODUCT_KEYWORDS = "/pk";
	static final String PRODUCT_KEYWORDS_ID= "/pk/{productid}";
	
	static final String ORDERCODES = "/oc";
	static final String ORDERS = "/od/{codeid}/{dummy}";
	static final String ORDERS_BY_ID = "/od/{orderid}";
	static final String PACKAGESHIPPING_GET = "/psh100";
	static final String PACKAGESHIPPING_GET_BY_SID = "/psh100/{sid}";
	static final String PACKAGESHIPPING_GET_BY_ORDERITEMSID = "/psh101/{orderitemsid}";
	
	static final String ORDERITEMSSTATUS_BY_ITEMSID = "/od103/{orderitemsid}";
	static final String ORDER_STATUS_TABLE_PUT = "/od104";
	static final String RAZORPAYTRANSACTION = "/rp100";
	static final String RAZORPAYTRANSACTIONBYRazorPAY_ORDERID = "/rp100/{razorpay_order_id}";
	static final String PACKAGESHIPPING_GET_BY_ORDERID = "/psh102/{orderid}";
	static final String EMAILSUBSCRIBE = "/es100";
	static final String GRANNYBLOG_LATEST5 = "/gb5";
	static final String GRANNYBLOG_LATEST5_BY_BLOGID = "/gb5/{blogid}";
	static final String ACTIVE_PRODUCT_DETAILS1_WITH_PATH = "/AP100/{productid}/{sss}/{sss1}";
	static final String CONTEST_IN_PAGE = "/con/{page}/{count}";
	static final String CONTEST_IN_COUNT = "/con1/{page}/{count}";
	static final String CONTEST2 = "/con2/{emailid}";
	static final String CONTEST3 = "/con3/{instaid}";
	static final String CONTEST4 = "/con4/{phone}";
	static final String CONTEST_IN_PAGE_CONTESTWISE = "conMonth/{contestmonthyear}/{page}/{count}";
	static final String CONTEST_IN_CONTESTWISE = "conMonth1/{contestmonthyear}/{page}/{count}";
	static final String MONTHS ="/conmon";
	static final String IMGLOC = "/imgloc/{emailid}/{filename}";
// TODO Remove unused code found by UCDetector
// 	static final String IMGLOC1 = "/imgloc1/{emailid}";
	static final String COMPLAINTS = "/complaint";
	static final String SOURCES = "/sources";
	static final String INVOICES = "/invoices";
	static final String STATUS = "/status";
	static final String SOLD_PRODUCTS_INFO = "/spi";
	static final String SALES_TIME = "/st/{sid}";
	static final String FULL_INFO = "/fi";
	static final String FULL_INFO_BY_DATE = "/fi/{user}/{year}/{month}";
	static final String UPLOAD_PDF = "/pdf/{inid}";
	static final String PDF_LOC = "/pdfloc/{inid}";
	static final String NOTIFICATION = "/notify";
	static final String COMBOS = "/combo";
	static final String COMBOSPROD = "comboprod";
	static final String COMBO_FULL_DETAILS = "/combo101";
	static final String COMBO_Productdetails_DETAILS = "/ncombo";
	static final String PRODUCT_BY_AGE_GEN = "/prodag/{genderid}/{ageid}";
	
	
	
}
