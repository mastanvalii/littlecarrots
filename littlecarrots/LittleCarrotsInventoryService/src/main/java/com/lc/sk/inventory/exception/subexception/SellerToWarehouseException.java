/**
 * 
 */
package com.lc.sk.inventory.exception.subexception;

/**
 * @author Mastanvali Shaik LittleCarrotsInventoryService 2020
 */
public class SellerToWarehouseException extends RuntimeException {

	private static final long serialVersionUID = -2603457208899845044L;

	public SellerToWarehouseException() {
		super();
	}

// TODO Remove unused code found by UCDetector
// 	public SellerToWarehouseException(String message, Throwable cause, boolean enableSuppression,
// 			boolean writableStackTrace) {
// 		super(message, cause, enableSuppression, writableStackTrace);
// 	}

// TODO Remove unused code found by UCDetector
// 	public SellerToWarehouseException(String message, Throwable cause) {
// 		super(message, cause);
// 	}

	public SellerToWarehouseException(String message) {
		super(message);
	}

// TODO Remove unused code found by UCDetector
// 	public SellerToWarehouseException(Throwable cause) {
// 		super(cause);
// 	}

}
