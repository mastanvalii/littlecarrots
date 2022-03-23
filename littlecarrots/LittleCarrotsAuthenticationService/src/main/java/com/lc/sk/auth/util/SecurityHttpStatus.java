package com.lc.sk.auth.util;
/**
 * @author Mastanvali Shaik
 * LittleCarrotsInventorySecurityService
 * 2020
 */
public interface SecurityHttpStatus {
	final static int OK = 2000;
	final static int ACCEPTED = 2000;
// TODO Remove unused code found by UCDetector
// 	final static int BAD_GATEWAY = 4001;
	final static int INTERNAL_SERVER_ERROR = 4002;
	final static int NOT_FOUND = 4004;
	final static int FAILED_DEPENDENCY = 4003;
	final static int UNAUTHORIZED = 4005;
// TODO Remove unused code found by UCDetector
// 	final static int REQUEST_TIMEOUT = 4006;
	final static int BAD_REQUEST = 4007;
	
}
