/**
 * 
 */
package com.lc.sk.inventory.exception.subexception;

/**
 * @author Mastanvali Shaik LittleCarrotsInventoryService 2020
 */
public class DBDataNotUpdatedException extends RuntimeException {

	private static final long serialVersionUID = 4524289448799652453L;

	public DBDataNotUpdatedException() {
		super();
	}

// TODO Remove unused code found by UCDetector
// 	public DBDataNotUpdatedException(String message, Throwable cause, boolean enableSuppression,
// 			boolean writableStackTrace) {
// 		super(message, cause, enableSuppression, writableStackTrace);
// 	}

// TODO Remove unused code found by UCDetector
// 	public DBDataNotUpdatedException(String message, Throwable cause) {
// 		super(message, cause);
// 	}

	public DBDataNotUpdatedException(String message) {
		super(message);
	}

// TODO Remove unused code found by UCDetector
// 	public DBDataNotUpdatedException(Throwable cause) {
// 		super(cause);
// 	}

}
