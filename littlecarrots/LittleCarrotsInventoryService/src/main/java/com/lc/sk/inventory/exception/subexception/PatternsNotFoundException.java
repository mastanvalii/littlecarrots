package com.lc.sk.inventory.exception.subexception;

public class PatternsNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 7541190016150040131L;

	public PatternsNotFoundException() {
		super();
	}

// TODO Remove unused code found by UCDetector
// 	public PatternsNotFoundException(String message, Throwable cause, boolean enableSuppression,
// 			boolean writableStackTrace) {
// 		super(message, cause, enableSuppression, writableStackTrace);
// 	}

// TODO Remove unused code found by UCDetector
// 	public PatternsNotFoundException(String message, Throwable cause) {
// 		super(message, cause);
// 	}

	public PatternsNotFoundException(String message) {
		super(message);
	}

// TODO Remove unused code found by UCDetector
// 	public PatternsNotFoundException(Throwable cause) {
// 		super(cause);
// 	}

}
