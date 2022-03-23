/**
 * 
 */
package com.lc.sk.inventory.security.util;

/**
 * @author Mastanvali Shaik LittleCarrotsInventorySecurityService 2020
 */
public interface URLMapping {


	final static String VERSION = "/v1";
	static final String USER_LOGIN_ROOT_PATH = "/users";
	final static String AUTHORIZED_LOGIN = "/login";
// TODO Remove unused code found by UCDetector
// 	final static String AUTHORIZED_LOGOUT = "/logout";
	final static String GET_ALL_ROLES = "/aur";
	
	
	
	// ROOT PATHS
// TODO Remove unused code found by UCDetector
// 	final static String ADMIN_ROOT_PATH = "/admin";

	// AUTHORIZATION SERVICE URLS
// TODO Remove unused code found by UCDetector
// 	final static String AUTHORIZATION_ROOT_PATH = "/authorization";
// TODO Remove unused code found by UCDetector
// 	final static String PATH_1 = "/roles";
// TODO Remove unused code found by UCDetector
// 	final static String PATH_2 = "/roles/{rolename}";
// TODO Remove unused code found by UCDetector
// 	final static String PATH_3 = "/roles/{rolename}/{status}";
	final static String ROOT_PATH = "/";

	// AUTH USER SERVICE URLS
// TODO Remove unused code found by UCDetector
// 	final static String AUTH_USER_ROOT_PATH = "/authuser";

// TODO Remove unused code found by UCDetector
// 	final static String AUTH_USER_GET_PATH = "/authusers";
// TODO Remove unused code found by UCDetector
// 	final static String AUTH_USER_GET_WITH_PATH_VARIABLE = "/authusers/{username}";
// TODO Remove unused code found by UCDetector
// 	final static String AUTH_USER_LOGIN_PATH = "/authuserslogin";

// TODO Remove unused code found by UCDetector
// 	final static String AUTH_USER_BLOCK = "/authusers/{username}/{status}";

// TODO Remove unused code found by UCDetector
// 	final static String LOG_ROOT_PATH = "/log";
// TODO Remove unused code found by UCDetector
// 	final static String LOGGER_PATH = "/logger";

// TODO Remove unused code found by UCDetector
// 	final static String SELLER_ROOT_PATH = "/seller";
// TODO Remove unused code found by UCDetector
// 	final static String SELLER_GET_PATH = "/sellerdetails";
// TODO Remove unused code found by UCDetector
// 	final static String SELLER_GET_PATH_WITH_VARIABLE = "/sellerdetails/{sellerid}";

// TODO Remove unused code found by UCDetector
// 	final static String SALLER_SELLER_ROOT_PATH = "/selleruser";
// TODO Remove unused code found by UCDetector
// 	final static String SALLER_SELLER_GET_PATH = "/salesuser";
// TODO Remove unused code found by UCDetector
// 	final static String SALLER_SELLER_GET_PATH_WITH_PATHVARIABLE = "/salesuser/{username}";
// TODO Remove unused code found by UCDetector
// 	final static String SALLER_SALLER_LOGIN_PATH = "/salesuser";
// TODO Remove unused code found by UCDetector
// 	final static String SALLER_SALLER_PUT_STATUS = "/salesuser/{username}/status";


// TODO Remove unused code found by UCDetector
// 	static final String MATERIAL_GET_PATH = "/material";

// TODO Remove unused code found by UCDetector
// 	static final String MATERIALTYPE_GET_PATH_WITH_VARIABLE = "/material/{materialid}";

// TODO Remove unused code found by UCDetector
// 	final static String INVENTORY_ROOT_PATH = "/inventory";
// TODO Remove unused code found by UCDetector
// 	final static String COUNTRY_GET_PATH = "/countries";
// TODO Remove unused code found by UCDetector
// 	final static String COUNTRY_GET_PATH_WITH_VARIABLE = "/countries/{isocode}";
// TODO Remove unused code found by UCDetector
// 	final static String COUNTRY_PUT_PATH_WITH_VARIABLE = "/countries/{isocode}/{status}";
// TODO Remove unused code found by UCDetector
// 	final static String COUNTRY_ACTIVE_GET = "/activecountries";

// TODO Remove unused code found by UCDetector
// 	static final String GENDERS_GET_PATH = "/genders";

// TODO Remove unused code found by UCDetector
// 	static final String GENDERS_MAPPING_PATH_WITH_PATH_VARIABLE = "/genders/{genderid}";

// TODO Remove unused code found by UCDetector
// 	static final String COLOR_PATH = "/color";
// TODO Remove unused code found by UCDetector
// 	final static String COLOR_PATH_WITH_COLOR_ID = "/color/{colorid}";
// TODO Remove unused code found by UCDetector
// 	final static String COLOR_ACTIVE_PATH = "/activecolors";

// TODO Remove unused code found by UCDetector
// 	final static String SUBCATEGORIES_GET_PATH = "/subcategories";
// TODO Remove unused code found by UCDetector
// 	final static String SUBCATEGORIES_MAPPING_PATH_WITH_VARIABLE = "/subcategories/{subcatid}";

// TODO Remove unused code found by UCDetector
// 	final static String SEASON_WEAR = "/seasonwear";
// TODO Remove unused code found by UCDetector
// 	final static String SEASON_WEAR_BY_ID = "/seasonwear/{seasonid}";
/*
	final static String PRODUCTTYPE_GET_PATH = "/producttype";
	final static String PRODUCTTYPE_PATH_WITH_ID = "/producttype/{protypeid}";

	final static String OCCASIONALWEAR_PATH = "/occasionalwear";
	final static String OCCASIONALWEAR_PATH_WITH_ID = "/occasionalwear/{occasionid}";

	static final String PRODUCTAGE_PATH = "/productage";
	static final String PRODUCTAGE_PATH_WITH_ID = "/productage/{ageid}";

	static final String BATCHES_GET_PATH = "/batches";
	static final String BATCHID_PUT_PATH_WITH_VARIABLE = "/batches/{batchid}/{status}";
	static final String BATCHES_MAPPING_PATH_WITH_PATH_VARIABLE = "/batches/{batchid}";

	final static String PATTERNS_PATH = "/patterns";
	final static String PATTERNS_PATH_WITH_ID = "/patterns/{patid}";

	static final String WAREHOUSES_GET_PATH = "/warehouses";
	static final String WAREHOUSES_MAPPING_PATH_WITH_PATH_VARIABLE = "/warehouses/{warehouseid}";

	static final String MATERIALCOMP_GET_PATH = "/materialcomp";
	static final String MATERIALCOMPOSITION_GET_PATH_WITH_VARIABLE = "/materialcomp/{materialid}";

	static final String PRODUCTQUANTITIES_GET_PATH = "/productquantities";
	static final String PRODUCTQUANTITIES_GET_PATH_WITH_VARIABLE = "/productquantities/{custid}";

	static final String CATEGORIES_MAPPING_PATH = "/categories";
	static final String CATEGORIES_MAPPING_PATH_WITH_ID = "/categories/{catid}";

	static final String SIZES_MAPPING_PATH = "/sizes";
	static final String SIZES_MAPPING_PATH_WITH_ID = "/sizes/{sizeid}";

	static final String PRICES_TABLE_PATH = "/pricestable";
	static final String PRICES_TABLE_PATH_WITH_ID = "/pricestable/{productid}";

	static final String PRODUCTDESCRIPTION_MAPPING_PATH = "/productdescriptions";
	static final String PRODUCTDESCRIPTION_MAPPING_PATH_WITH_ID = "/productdescriptions/{descriptionid}";

	static final String WAREHOUSETOSELLER_POST = "/sellertowarehouse";
	static final String WAREHOUSETOSELLER_GET = "/sellertowarehouse";

	static final String PRODUCTS_MAPPING_PATH = "/products";
	static final String PRODUCTS_MAPPING_PATH_WITH_PATH_VARIABLE_1 = "/products/{productid}/{status}";
	static final String PRODUCTS_MAPPING_PATH_ID = "/products/{productid}";

	static final String NO_OF_PIECES_PATH = "/noofpieces";
	static final String NO_OF_PIECES_PATH_WITH_ID = "/noofpieces/{pieceid}";

	final static String BACK_END_SERVER_NO_RESPONSE = "Back End Server not Responding...";

	final static int ACTIVE_USER = 1;
	final static String NOT_ACCEPTABLE = "N/A";
	final static String USER_ID_INVALID = "Invalid Username/password";

	*/

	
	//URLS FOR SUPERUSER ENTRYPOINT START
	
	/*
	final static String SELLER_USER_ENTRY_POINT_ROOT_PATH = "/salesuser";
	final static String MANAGER_USER_ENTRY_POINT_ROOT_PATH = "/managers";
	final static String CUSTOMER_USER_ENTRY_POINT_ROOT_PATH = "/customers";
	
	


	static final String SALES_SELLER_GET_PATH_WITH_VARIABLE = "/salesuser/{sellerid}";

	static final String INSERT_SALLER_SELLER_GET_PATH = "/insertsalesuser";
	
	
	// Blog report
	static final String BLOGREPORT_MAPPING_PATH = "/blogreport";
	static final String BLOGREPORT_MAPPING_PATH_ID = "/blogreport/{bid}";
	static final String BLOGREPORT_MAPPING_PATH_NAME = "/blogreport/{authorname}";
	

	// GrannyBlog
	static final String GRANNYBLOG_MAPPING_PATH = "/grannyblog";
	static final String GRANNYBLOG_MAPPING_PATH_BY_ID = "/grannyblog/{blogid}";
	static final String GRANNYBLOG_MAPPING_PATH_STATUS = "/grannyblog/{blogid}/{status}";
	
	//Product Keywords
	static final String PRODUCT_KEYWORDS = "/pk";
	static final String PRODUCT_KEYWORDS_ID= "/pk/{productid}";
	*/
	final static String KAFKA_SERVER_URL="65.0.30.10:9092";
//	final static String KAFKA_SERVER_URL= "img.littlecarrots.com:9092";
//	final static String KAFKA_SERVER_URL= "localhost:9092";
	
}
