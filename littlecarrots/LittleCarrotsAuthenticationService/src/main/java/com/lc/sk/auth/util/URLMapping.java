/**
 * 
 */
package com.lc.sk.auth.util;

/**
 * @author Mastanvali Shaik
 * LittleCarrotsAuthenticationService
 * 2020
 */
public interface URLMapping {

	final static String AUTHORIZATION_ROOT_PATH = "/";
	final static String AUTHORIZATION_MAPPING_PATH="/authorization";
	final static String AUTHORIZATION_MAPPING_WITH_PATH_VARIABLE = "/authorization/{rolename}";
	final static String AUTHORIZATION_MAPPING_PATH_UPDATE_STATUS="/authorization/{rolename}/{status}";
	
	final static String AUTHORIZED_USER_MAPPING_PATH = "/authorizeduser";
	final static String AUTHORIZED_USER_MAPPING_WITH_PATH_VARIABLE = "/authorizeduser/{username}";
	final static String AUTHORIZED_USER_LOGIN_PATH="/authuserlogin";
	final static String AUTHORIZED_USER_STATUS_UPDATE = "/authorizeduser/{username}/{status}";
	
	final static String SELLER_MAPPING_PATH="/sellers";
	final static String SELLER_MAPPING_PATH_PATH_VARIABLE="/sellers/{sellerid}";
// TODO Remove unused code found by UCDetector
// 	final static String SELLER_UPDATE_MAPPING_PATH="/updatesellers";
	
	final static String SELLER_USER_MAPPING_PATH="/salesusers";
	final static String SELLER_USER_MAPPING_PATH_WITH_PATH_VARIABLE="/salesusers/{username}";
	final static String SELLER_USER_LOGIN_MAPPING_PATH="/salesuserslogin";
	final static String SELLER_USER_UPDATE_STATUS_MAPPING_PATH="/salesusers/{username}/{status}";
	
	final static String ROOT_PATH_FOR_VALIDATION = "/**";
	final static String LC_PATH = "/lc";
	static final String LCPROP = "/get";
	static final String ABC = "/get12/{prop}";
	static final String PROPID = "/getbyid/{propid}";
	static final String UPDATEPROP = "/putprop/{id}/{status}";
	
	
	static final String CUSTOMER_ADDRESS = "/custadd";
	static final String CUSTOMER_ADDRESS_PATH_PATH_VARIABLE = "/custadd/{email}";
	static final String CUSTOMER_ADDRESS_PATH_PATH_ID = "/custadd1/{addressid}";
	
	static final String LIL_USER_MAPPING_PATH1 = "/user";
	static final String GET_USERS_MAPPING_PATH = "/user";
	static final String USER_MAPPING_PATH_PATH_VARIABLE_EMAIL= "/ok/{email}";
// TODO Remove unused code found by UCDetector
// 	static final String LIL_USER_MAPPING_PATH2 = "/lp";
	
	static final String USER_MAPPING_UNIQUEID = "/un/{uniqueid}";
	static final String LIL_USER_MAPPING_PATH = "/user";
	static final String EMAIL_SUBSCRIBE = "/es";
	static final String EMAIL_SUBSCRIBE_BY_EMAIL_ID = "/es/{emailid}";
	static final String CONTEST = "/contest";
	static final String CONTESTPAGE = "/contest/{page}/{count}";
	static final String CONTESTPAGE1 = "/contest1/{page}/{count}";
	static final String CONTESTWITHEMAILID = "/contest2/{emailid}";
	static final String CONTESTWITHEINSTAID = "/contest3/{instaid}";
	static final String CONTESTWITHEPHONENUMBER = "/contest4/{phone}";
	static final String CONTESTPAGEWITHMONTH = "/contestMonth/{contestmonthyear}/{page}/{count}";
	static final String CONTESTPAGE1WITHMONTH = "/contestMonth1/{contestmonthyear}/{page}/{count}";
	static final String CONTESTALL = "/conmon";
// TODO Remove unused code found by UCDetector
// 	static final String GET = "/getimg/{emailid}/{name}";
	static final String IMGLOCATION = "/imgloc/{emailid}/{filename}";
// TODO Remove unused code found by UCDetector
// 	static final String IMGLOCATION1 = "/imgloc1/{emailid}";
	static final String EMAIL_VALIDATION = "/uservali/{email}/{phone}";;
	
	
	
	
	
	
	
	
}
