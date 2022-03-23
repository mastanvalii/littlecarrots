package com.little.carrots.order.exception.subexceptions;

public class DBValueInsertException extends RuntimeException{

// TODO Remove unused code found by UCDetector
// 	public DBValueInsertException(String message, Throwable cause) {
// 		super(message, cause);
// 	}

 	/**
	 * 
	 */
	private static final long serialVersionUID = -175655825907267378L;

	public DBValueInsertException(String message) {
 		super(message);
 	}

//	public DBValueInsertException(Throwable cause) {
	//	super(cause);
	//}
		
}
