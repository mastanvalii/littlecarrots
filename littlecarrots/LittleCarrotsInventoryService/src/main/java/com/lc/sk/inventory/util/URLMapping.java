/**
 * 
 */
package com.lc.sk.inventory.util;

/**
 * @author Mastanvali Shaik
 * LittleCarrotsInventoryService
 * 2020
 */
public interface URLMapping {

	
	final static String INVENTORY_ROOT_PATH = "/";
	final static String COUNTRY_PATH="/countries";
	final static String COUNTRY_PATH_WITH_COUNTRY_ID = "/countries/{isocode}";
	final static String UPDATE_COUNTRY_STATUS = "/countries/{isocode}/{status}";
	final static String GET_COUNTRIES_ENABLED_LIST = "/activecountries";
	
	final static String GET_ALL_WAREHOUSE_LIST = "/warehouses";
	final static String GET_ALL_WAREHOUSE_LIST_BY_ID = "/warehouses/{warehouseid}";
// TODO Remove unused code found by UCDetector
// 	final static String GET_ALL_WAREHOUSE_LIST_BY_NAME = "/warehouses/{warehousename}";
	
	final static String POST_SUBMIT_WAREHOUSE_TO_SELLER = "/sellertowarehouse";
	
	final static String BATCH_MAPPING_PATH = "/batches";
	final static String BATCH_MAPPING_PATH_WITH_PATH_VARIABLE = "/batches/{batchid}";
	final static String BATCH_MAPPING_PATH_WITH_PATH_VARIABLE_1 = "/batches/{batchid}/{status}";
	
	final static String GENDERS_MAPPING_PATH = "/genders";
	final static String GENDERS_MAPPING_PATH_WITH_PATH_VARIABLE = "/genders/{genderid}";
	
	final static String PRODUCTAGE_PATH="/productage";
	final static String PRODUCTAGE_PATH_WITH_ID="/productage/{ageid}";
	
	
	final static String SIZES="/sizes";
	final static String SIZE_BY_ID="/sizes/{sizeid}";
	
	final static String COLOR_PATH="/color";
	final static String COLOR_PATH_WITH_COLOR_ID="/color/{colorid}";
	
	
	final static String CATEGORIES_MAPPING_PATH = "/categories";	
	final static String CATEGORIES_MAPPING_PATH_BY_CID = "/categories/{catid}";
	
	
	final static String SEASON_WEAR="/seasonwear";
	final static String SEASON_WEAR_BY_ID="/seasonwear/{seasonId}";
	
	final static String PRODUCTTYPE_PATH="/producttype";
	final static String PRODUCTTYPE_PATH_WITH_ID="/producttype/{protypeid}";
	

	final static String MATERIALTYPES_MAPPING_PATH = "/material";
	final static String GET_ALL_MATERIALTYPES_LIST = "/material";
	final static String GET_ALL_MATERIALTYPES_LIST_BY_ID = "/material/{materialid}";
	
	final static String SUBCATEGORIES_MAPPING_PATH = "/subcategories";
	final static String SUBCATEGORIES_MAPPING_PATH_WITH_ID = "/subcategories/{subcatid}";
	
	final static String OCCASIONALWEAR_PATH="/occasionalwear";
	final static String OCCASIONALWEAR_PATH_WITH_ID="/occasionalwear/{occasionid}";
	
	
	
	final static String NO_OF_PIECES_PATH_WITH_ID="/noofpieces/{pieceid}";
	static final String NO_OF_PIECES_PATH = "/noofpieces";
	
	final static String ROOT_PATH_FOR_VALIDATION = "/**";
	
	final static String PRODUCTDESCRIPTIONS_PATH="/productdescriptions";
	static final String PRODUCTDESCRIPTIONS_PATH_WITH_ID = "/productdescriptions/{descriptionid}";
	final static String PRODUCTDESCRIPTIONS_PATH_NOT_IN_PRODUCT="/productdescriptions1";


	final static String PATTERNS_PATH="/patterns";
	final static String PATTERNS_PATH_WITH_ID="/patterns/{patid}";
	
	final static String PRODUCTQUANTITIES_PATH="/productquantities";
	final static String PRODUCTQUANTITIES_PATH_WITH_ID="/productquantities/{custid}";
	final static String PRODUCTQUANTITIES_PATH1="/productquantities1";

	final static String PRICES_TABLE="/pricestable";
	static final String PRICES_TABLE_BY_ID = "/pricestable/{priceId}" ;
	
	final static String MATERIALCOMPOSITION_MAPPING_PATH = "/materialcomp";
	final static String GET_ALL_MATERIALCOMPOSITION_LIST_BY_ID = "/materialcomp/{materialid}";
	
	final static String PRODUCTS_MAPPING_PATH = "/products";
	static final String PRODUCTS_MAPPING_PATH_ID = "/products/{productid}";
	static final String PRODUCTS_MAPPING_PATH_WITH_PATH_VARIABLE_1 ="/products/{productid}/{status}" ;
	
	
	final static String PROUDCT_DETAILS_GET = "/AP100";
	final static String PRODUCT_DETAILS_GET_WITH_PATH = "/AP100/{productid}";
	final static String PRODUCT_DETAILS_GET_WITH_PATH1 = "/AP100/{productid}/{status}";
	
	final static String PRODUCT_COUNT_LIST = "/PC101";
	final static String OUTOF_STOCK_PRODUCTS = "/PC102";
	final static String LOW_STOCK_PRODUCTS = "/PC103";
	static final String SUBCATEGORIESLIST = "/subcatdetails";
	
	static final String APPROVAL = "/approve";
	static final String APPROVAL_BY_ID = "/approve/{serialid}/{dummy}";
	static final String APPROVAL_BY_PRODUCT_ID = "/approve/{productid}";
	static final String SIZESLIST = "/sizeslist";
	
	static final String PRI_URL = "/PRI001";
	static final String PAT_URL = "/PAT001";
	static final String PIE_URL = "/PIE001";
	
	static final String CATBGU = "/catbgu/{genderid}";
	
	/* Pagging url varaibles  */
	static final String PAGEABLE = "/pp/{page}/{count}";
	static final String PAGEABLE1 = "/pp1/{page}/{count}";
	
	static final String DISPLAYTYPE= "/display";
	static final String DISPLAYTYPE_BY_ID = "/display/{display}";
	
	//badges//
	static final String BADGE = "/bad";
	static final String GET_BY_BADGE = "/bad/{badge}";
	
	//collection
	static final String COLLECTIONSALE_MAPPING_PATH = "/collection";
	static final String COLLECTIONSALE_MAPPING_PATH_WITH_PATH_VARIABLE = "/collection/{id}";
	static final String PRODUCTS_MAPPING_PATH_COLLECTIONS ="/products/{catid}/{season}/{occaName}" ;
	
	static final String SEARCH1 = "/find/{search}/{des}";
	static final String SEARCH0 = "/find/{page}/{count}/{search}/{dummy}";
	
	//selected products
	static final String SELECTEDPRODUCTS_MAPPING_PATH = "/selprod";
	static final String SELECTEDPRODUCTS_MAPPING_PATH_WITH_PATH_VARIABLE = "/selprod/{spid}";
	static final String PRODUCTS_MAPPING_PATH_SELECTEDPRODUCTS = "/product/{genderid}";
	
	//Wishlist
	static final String WISHLIST = "/wishlist";
	static final String WISHLIST_BY_CUSTOMERID = "/wishlist/{customerid}";
	static final String WISHLIST_BY_PRODUCTID = "/wishlistprod/{productid}/{customerid}";
// TODO Remove unused code found by UCDetector
// 	static final String WISHLIST_BY_CUSTOMER_PID = "/wishlist/{productid}";
// TODO Remove unused code found by UCDetector
// 	static final String WISHLIST_BY_CUSTOMER_WPID = "/wishlist/{wishlistid}/{productid}";
	
	//Blog
	static final String BLOGREPORT_MAPPING_PATH = "/blogreport";
	static final String BLOGREPORT_MAPPING_PATH_ID = "/blogreport/{bid}";
// TODO Remove unused code found by UCDetector
// 	static final String BLOGREPORT_MAPPING_PATH_ID_Name = "/blogreport/{authorname}";	
			
	//GrannyBlog
	static final String GRANNYBLOG_MAPPING_PATH = "/grannyblog";	
	static final String GRANNYBLOG_MAPPING_PATH_BY_ID = "/grannyblog/{blogid}";	 
	static final String GRANNYBLOG_MAPPING_PATH_STATUS = "/grannyblog/{blogid}/{status}";
	
	static final String COLLECTIONSALE_MAPPING_PATH_BY_GENDER = "/collection1/{genderid}";
	static final String COLLECTIONSALE_IMAGE_MAPPING_PATH = "/collectionimage";
	static final String COLLECTIONSALEPROD_MAPPING_PATH = "collectionprod";
	static final String COLLECTIONSALEPROD_MAPPING_PATH_WITH_PATH_VARIABLE = "collectionprod/{id}";
	static final String COLLECTIONSALEPROD_MAPPING_PATH_WITH_PATH_SERIAL_ID = "collectionprod1/{csid}";
	static final String COLLECTIONSALE_IMAGES_MAPPING_PATH_WITH_PATH_VARIABLE = "/collectionimage/{imageid}";
// TODO Remove unused code found by UCDetector
// 	static final String COLLECTIONSALE_IMAGES_MAPPING_PATH_WITH_CSID = "/collectionimage1/{id}";
	static final String PRODUCTS_MAPPING_PATH_COLLECTIONS_PRODUCT_IDS = "/prod3/{genderid}";
	
	static final String COLLECTIONSALE_BY_GENDER_VIEW = "/collection2/{genderid}/{siteview}";
	static final String COLLECTIONSALE_BY_LANDING_VIEW = "/collection3/{id}/{siteview}/{genderid}";
	static final String COLLECTIONSALE_BY_LANDING_VIEW1 = "/collection4/{id}/{siteview}";
	static final String COLLECTIONSALE_IMAGES_MAPPING_PATH_WITH_ID = "/collectionimage2/{id}";
	static final String PRODUCTS_MAPPING_PATH_SELECTEDPRODUCTS_MULTISELECT = "/products1/{genderid}";
	static final String PRODUCTS_MAPPING_PATH_SIMILARPRODUCTS_MULTISELECT = "/productsm";
	
	
	static final String LATESTCOLLECTION = "/NEWCOL";
	
	static final String PRODUCT_KEYWORDS = "/productkeywords";
	static final String PRODUCT_KEYWORDS_ID= "/productkeywords/{productid}";
	
	static final String GRANNYBLOG_LATEST_5_MAPPING_PATH = "/grannyblog5";
	static final String GRANNYBLOG__NEXT_LATEST_5_MAPPING_PATH = "/grannyblog5/{blogid}";
	static final String SELECTEDPRODUCTS_MAPPING_PATH_LIMIT_4 = "/selprod4";
	
	
	static final String SCROOL_SEARCH_COUNT = "SS100/{query}/{page}/{count}";
	static final String SCROLL_SEARCH_DATA = "SS101/{query}/{page}/{count}";
	
	
	static final String FROM_CACHE = "/CT099";
	
	static final String SP_PAGE_COUNT="/spp1/{page}/{count}";
	static final String SP_PAGE_DATA="/spp2/{page}/{count}";
	static final String COMBOS_GET = "/combos";
	static final String COMBOS_PROD_GET = "/comboprod";
	static final String COMBOS_FULL_DETAILS = "/combo101";
	static final String NO_COMBO_PRODUCTDETAILS = "/ncombo";
	
	static final String SELLER_PRODUCT_IDENTITY_LIST= "/spil";
	static final String RACK_DETAILS="/rack";
	static final String RACK_IDENTITY_DETAILS="/rackidentity";
	
	
}
