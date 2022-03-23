package com.little.carrots.order.exception;

import org.apache.log4j.Logger;
import org.apache.log4j.BasicConfigurator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.little.carrots.order.bean.ResponseBean;
import com.little.carrots.order.exception.subexceptions.BadRequestException;
import com.little.carrots.order.exception.subexceptions.CartmanagementException;
import com.little.carrots.order.exception.subexceptions.CouponException;
import com.little.carrots.order.exception.subexceptions.DBInsertException;
import com.little.carrots.order.exception.subexceptions.DBValueInsertException;
import com.little.carrots.order.exception.subexceptions.InvoiceNotFoundException;
import com.little.carrots.order.exception.subexceptions.NullRequestReceivedException;
import com.little.carrots.order.exception.subexceptions.OrderNotFoundException;
import com.little.carrots.order.exception.subexceptions.PackageShippingNotFoundException;
import com.little.carrots.order.exception.subexceptions.RazorpayTransactionNotFoundException;
import com.little.carrots.order.exception.subexceptions.ShippingNotFoundException;
import com.little.carrots.order.exception.subexceptions.SoldProductsNotFoundException;
import com.little.carrots.order.exception.subexceptions.SourcesNotFoundException;
import com.little.carrots.order.util.HeaderComponent;
import com.little.carrots.order.util.SecurityHttpStatus;

@ControllerAdvice
public class GlobalException {
	
	static Logger log = Logger.getLogger(GlobalException.class);

	@Autowired
	HeaderComponent headers;

	public GlobalException() {
		BasicConfigurator.configure();
	}
	
	@ExceptionHandler
	public ResponseEntity<ResponseBean> handleException(ShippingNotFoundException e) {
		return loggerMessage("URL:/shipping---- handleException(ShippingNotFoundException)-", e.getMessage(),
				SecurityHttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<ResponseBean> handleException(NullRequestReceivedException e) {
		return loggerMessage("URL:/warehouses---- handleException(NullRequestReceivedException))-", e.getMessage(),
				SecurityHttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler
	public ResponseEntity<ResponseBean> handleException(DBInsertException e) {
		return loggerMessage("URL:/warehouses---- handleException(NullRequestReceivedException))-", e.getMessage(),
				SecurityHttpStatus.INTERNAL_SERVER_ERROR);
	}
	@ExceptionHandler
	public ResponseEntity<ResponseBean> handleException(CartmanagementException e) {
		return loggerMessage("URL:/cart---- handleException(CartmanagementException)-", e.getMessage(),
				SecurityHttpStatus.NOT_FOUND);
	}
	
	public ResponseEntity<ResponseBean> loggerMessage(String url, String message, int status)
	{
		ResponseBean seb = new ResponseBean();
		seb.setMessage(message);
		seb.setResponsecode(status);
		seb.setTiemstamp(System.currentTimeMillis());
		log.error(url+ seb.toString());
		return new ResponseEntity<ResponseBean>(seb, headers.getHeader(), HttpStatus.OK);
	}

	//order
	  @ExceptionHandler
     	public ResponseEntity<ResponseBean> handleException(DBValueInsertException e) {
	        return loggerMessage("URL:/ordermanagement---- handleException(DBValueInsertException)-", e.getMessage(),
			SecurityHttpStatus.INTERNAL_SERVER_ERROR);

	  }

	   @ExceptionHandler
    	public ResponseEntity<ResponseBean> handleException(OrderNotFoundException e) {
	        return loggerMessage("URL:/ordermanagement---- handleException(OrderNotFoundException)-", e.getMessage(),
			SecurityHttpStatus.NOT_FOUND);
	   }
	   @ExceptionHandler
	   public ResponseEntity<ResponseBean> handleException(RazorpayTransactionNotFoundException e) {
		     return loggerMessage("URL:/razorpay---- handleException(RazorpayTransactionNotFoundException)-", e.getMessage(),
		SecurityHttpStatus.INTERNAL_SERVER_ERROR);
	   }


	   @ExceptionHandler
	   public ResponseEntity<ResponseBean> handleException(BadRequestException e) {
		     return loggerMessage("URL:/charge---- handleException(BadRequestException)-", e.getMessage(),
		SecurityHttpStatus.INTERNAL_SERVER_ERROR);
	   }
	   
	   @ExceptionHandler
	   public ResponseEntity<ResponseBean> handleException(PackageShippingNotFoundException e) {
		     return loggerMessage("URL:/ps100---- handleException(PackageShippingNotFoundException)-", e.getMessage(),
		SecurityHttpStatus.NOT_FOUND);
	   }
	   
	   @ExceptionHandler
	   public ResponseEntity<ResponseBean> handleException(CouponException e) {
		     return loggerMessage("URL:/coupons---- handleException(CouponException)-", e.getMessage(),
		SecurityHttpStatus.NOT_FOUND);
	   }
	   
	   @ExceptionHandler
	   public ResponseEntity<ResponseBean> handleException(SourcesNotFoundException e) {
		     return loggerMessage("URL:/sources---- handleException(SourcesNotFoundException)-", e.getMessage(),
		SecurityHttpStatus.NOT_FOUND);
	   }
	   
	   @ExceptionHandler
	   public ResponseEntity<ResponseBean> handleException(InvoiceNotFoundException e) {
		     return loggerMessage("URL:/invoices---- handleException(InvoiceNotFoundException)-", e.getMessage(),
		SecurityHttpStatus.NOT_FOUND);
	   }
	
	   @ExceptionHandler
	   public ResponseEntity<ResponseBean> handleException(SoldProductsNotFoundException e) {
		     return loggerMessage("URL:/soldproducts---- handleException(SoldProductsNotFoundException)-", e.getMessage(),
		SecurityHttpStatus.NOT_FOUND);
	   }
}
