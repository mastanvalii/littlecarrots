package com.lc.sk.inventory.exception.subexception;

/**
 * @author Mastanvali Shaik
 * LittleCarrotsInventoryService
 * 2020
 */
public class BatchException extends RuntimeException {

	private static final long serialVersionUID = -3873413436427277545L;

	public BatchException() {
		super();
	}

// TODO Remove unused code found by UCDetector
// 	public BatchException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
// 		super(message, cause, enableSuppression, writableStackTrace);
// 	}

// TODO Remove unused code found by UCDetector
// 	public BatchException(String message, Throwable cause) {
// 		super(message, cause);
// 	}

	public BatchException(String message) {
		super(message);
	}

// TODO Remove unused code found by UCDetector
// 	public BatchException(Throwable cause) {
// 		super(cause);
// 	}

	
	
}
