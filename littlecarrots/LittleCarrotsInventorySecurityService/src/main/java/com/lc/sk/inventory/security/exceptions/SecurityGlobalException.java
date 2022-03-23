/**
 * 
 */
package com.lc.sk.inventory.security.exceptions;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.lc.sk.inventory.security.beans.ResponseBean;
import com.lc.sk.inventory.security.exceptions.subexceptions.AuthenticationFailedException;
import com.lc.sk.inventory.security.exceptions.subexceptions.AuthorizationNotFoundException;
import com.lc.sk.inventory.security.exceptions.subexceptions.AuthorizedUserNotFoundException;
import com.lc.sk.inventory.security.exceptions.subexceptions.BackEndDataException;
import com.lc.sk.inventory.security.exceptions.subexceptions.CouponServiceException;
import com.lc.sk.inventory.security.exceptions.subexceptions.DataNotFoundException;
import com.lc.sk.inventory.security.exceptions.subexceptions.EncDecException;
import com.lc.sk.inventory.security.exceptions.subexceptions.MailServiceException;
import com.lc.sk.inventory.security.exceptions.subexceptions.NotAuthorizedException;
import com.lc.sk.inventory.security.exceptions.subexceptions.NotificationNotFoundException;
import com.lc.sk.inventory.security.exceptions.subexceptions.NullRequestReceivedException;
import com.lc.sk.inventory.security.exceptions.subexceptions.RestResponseException;
import com.lc.sk.inventory.security.exceptions.subexceptions.RestURLReaderException;
import com.lc.sk.inventory.security.exceptions.subexceptions.ServiceRequestTimeOutException;
import com.lc.sk.inventory.security.exceptions.subexceptions.SessionExpiredException;
import com.lc.sk.inventory.security.util.ConstantValues;
import com.lc.sk.inventory.security.util.HeaderComponent;
import com.lc.sk.inventory.security.util.JsonToBeanConverter;
import com.lc.sk.inventory.security.util.SecurityHttpStatus;

/**
 * @author Mastanvali Shaik LittleCarrotsInventorySecurityService 2020
 */
@ControllerAdvice
public class SecurityGlobalException {
	static Logger log = Logger.getLogger(SecurityGlobalException.class);

	@Autowired
	HeaderComponent headers;

	public SecurityGlobalException() {
		super();
		BasicConfigurator.configure();
	}

	@ExceptionHandler
	public ResponseEntity<ResponseBean> handleException(RestURLReaderException e) {
		ResponseBean seb = new ResponseBean();
		seb.setMessage(e.getMessage());
		seb.setResponsecode(SecurityHttpStatus.BAD_GATEWAY);
		seb.setTiemstamp(System.currentTimeMillis());
		log.error("Security Service RestURLReaderException:" + seb.toString());
		return new ResponseEntity<ResponseBean>(seb, headers.getHeader(), HttpStatus.OK);
	}

	@ExceptionHandler
	public ResponseEntity<ResponseBean> handleException(EncDecException e) {
		return loggerMessage("Security Service EncDecException:", e.getMessage(), SecurityHttpStatus.BAD_GATEWAY);
	}

	@ExceptionHandler
	public ResponseEntity<ResponseBean> handleException(RestResponseException e) {
		return loggerMessage("Security Service RestResponseException:", e.getMessage(),
				SecurityHttpStatus.INTERNAL_SERVER_ERROR);

	}

	@ExceptionHandler
	public ResponseEntity<ResponseBean> handleException(AuthorizationNotFoundException e) {
		return loggerMessage("Security Service AuthorizationNotFoundException:", e.getMessage(),
				SecurityHttpStatus.NOT_FOUND);
	}

	@ExceptionHandler
	public ResponseEntity<ResponseBean> handleException(BackEndDataException e) {
		return loggerMessage("Security Service BackEndDataException:", e.getMessage(),
				SecurityHttpStatus.FAILED_DEPENDENCY);
	}

	@ExceptionHandler
	public ResponseEntity<ResponseBean> handleException(AuthorizedUserNotFoundException e) {
		ResponseBean seb = new ResponseBean();
		if (e.getMessage().equals(ConstantValues.USER_ID_INVALID)) {
			seb.setMessage(e.getMessage());
			seb.setResponsecode(SecurityHttpStatus.NOT_FOUND);
			seb.setTiemstamp(System.currentTimeMillis());
		} else {
			seb = (ResponseBean) JsonToBeanConverter.convert(e.getMessage(), ResponseBean.class);
		}
		log.error("Security Service AuthorizedUserNotFoundException:" + seb.toString());
		return new ResponseEntity<ResponseBean>(seb, headers.getHeader(), HttpStatus.OK);
	}

	@ExceptionHandler
	public ResponseEntity<ResponseBean> handleException(DataNotFoundException e) {
		return loggerMessage("Security Service DataNotFoundException:", e.getMessage(), SecurityHttpStatus.NOT_FOUND);

	}

	// NotAuthorizedException
	@ExceptionHandler
	public ResponseEntity<ResponseBean> handleException(NotAuthorizedException e) {
		return loggerMessage("Security Service NotAuthorizedException:", e.getMessage(),
				SecurityHttpStatus.UNAUTHORIZED);
	}

	// SessionExpiredException
	@ExceptionHandler
	public ResponseEntity<ResponseBean> handleException(SessionExpiredException e) {
		return loggerMessage("Security Service SessionExpiredException:", e.getMessage(),
				SecurityHttpStatus.BAD_REQUEST);
	}

	// AuthenticationFailedException
	@ExceptionHandler
	public ResponseEntity<ResponseBean> handleException(AuthenticationFailedException e) {
		return loggerMessage("Security Service AuthenticationFailedException:", e.getMessage(),
				SecurityHttpStatus.UNAUTHORIZED);
	}

	// NullRequestReceivedException

	@ExceptionHandler
	public ResponseEntity<ResponseBean> handleException(NullRequestReceivedException e) {
		return loggerMessage("Security Service NullRequestReceivedException:", e.getMessage(),
				SecurityHttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler
	public ResponseEntity<ResponseBean> handleException(ServiceRequestTimeOutException e) {
		return loggerMessage("Security Service ServiceRequestTimeOutException:", e.getMessage(),
				SecurityHttpStatus.REQUEST_TIMEOUT);
	}
	
	@ExceptionHandler
	public ResponseEntity<ResponseBean> handleException(CouponServiceException e){
		ResponseBean seb = new ResponseBean();
		seb.setMessage(e.getMessage());
		seb.setResponsecode(4004);
		seb.setTiemstamp(System.currentTimeMillis());
		log.error("/couponservice" + seb.toString());
		return new ResponseEntity<ResponseBean>(seb, headers.getHeader(), HttpStatus.OK);
	}

	public ResponseEntity<ResponseBean> loggerMessage(String url, String message, int serviceStatus) {
		ResponseBean seb = new ResponseBean();
		if(message.equals(ConstantValues.ERROR_BEAN_RETURNED)
				|| message.equals(ConstantValues.BACK_END_SERVER_NO_RESPONSE)
				|| message.equals(ConstantValues.ERROR_BEAN_RETURNED)
				|| message.equals(ConstantValues.USER_ID_INVALID)
				|| message.equals(ConstantValues.NO_PRODUCT_TYPE_NOT_FOUND)
				|| message.equals(ConstantValues.PRICE_ID_NOT_FOUND )
				|| message.equals(ConstantValues.NO_PRODUCT_AGES_FOUND)
				|| message.equals(ConstantValues.NO_PRODUCT_DESCRIPTIONS_FOUND)
				|| message.equals(ConstantValues.PRODUCT_QUANTITIES_NOT_FOUND)
				||  message.equals(ConstantValues.NO_PRODUCTS_FOUND)
				|| message.equals(ConstantValues.DATA_NOT_UPDATED)
				||message.equals(ConstantValues.NO_GENDERS_LIST_FOUND)
				||message.equals(ConstantValues.NO_CATEGORIES_LIST_FOUND)
				||message.equals(ConstantValues.NO_CATEGORIES_LIST_FOUND_GIVEN_CATID)
				||message.equals(ConstantValues.CATEGORIES_UPDATE_FAIL)
				||message.equals(ConstantValues.DATA_NOT_INSERTED_IN_DB)
				||message.equals(ConstantValues.EMPTY_MATERIALCOMPOSITION_LIST)
				||message.equals(ConstantValues.MATERIALCOMPOSITION_NOT_FOUND_WITH_GIVEN_ID)
				||message.equals(ConstantValues.EMPTY_MATERIALTYPES_LIST)
				||message.equals(ConstantValues.MATERIALTYPES_NOT_FOUND_WITH_GIVEN_ID)
				||message.equals(ConstantValues.NO_OF_SET_NOT_FOUND)
				||message.equals(ConstantValues.NO_OF_PIECES_NOT_FOUND)
				||message.equals(ConstantValues.NO_OCCASIONALWEAR_LIST_FOUND)
				|| message.equals(ConstantValues.OCCASIONID_NOT_FOUND)
				|| message.equals(ConstantValues.NO_COUNTRIES_LIST_FOUND_GIVEN_ISOCODE)
				|| message.equals(ConstantValues.NO_COUNTRIES_LIST_FOUND)
				|| message.equals(ConstantValues.COUNTRY_STATUS_UPDATE_FAIL)
				|| message.equals(ConstantValues.COLOR_DETAILS_NOT_FOUND)
				|| message.equals(ConstantValues.RECEIVED_NULL_VALUES)
				|| message.equals(ConstantValues.AUTHORIZATION_ROLES_NOT_FOUND)
				|| message.equals(ConstantValues.NULL_VALUES_RECEIVED_FROM_CLIENT)
				|| message.equals(ConstantValues.NO_AUTHORIZED_USER_FOUND)
                || message.equals(ConstantValues.BATCH_DETAILS_NOT_FOUND)
				|| message.equals(ConstantValues.NO_PATTERNS_LIST_FOUND)
				|| message.equals(ConstantValues.SEASONWEAR_NOT_FOUND)
				|| message.equals(ConstantValues.SIZES_NOT_FOUND_EXCEPTION)
				|| message.equals(ConstantValues.SUBCAT_ID_NOT_FOUND)
				|| message.equals(ConstantValues.WAREHOUSE_NOT_FOUND)
				|| message.equals(ConstantValues.NO_SALES_USER_FOUND)
				|| message.equals(ConstantValues.NO_PRODUCT_TYPE_DETAILS_NOT_FOUND)
				|| message.equals(ConstantValues.VALUE_NOT_FOUND)
				|| message.equals(ConstantValues.USER_NAME_NOT_FOUND)
				|| message.equals(ConstantValues.WAREHOUSE_DATA_NOT_FOUND)
				|| message.equals(ConstantValues.MAIL_NOT_SENT)
				|| message.equals(ConstantValues.COUNTRY_NOT_FOUND)
				|| message.equals(ConstantValues.NO_BATCH_SERVICE_FOUND)
				|| message.equals(ConstantValues.PRODUCT_NOT_FOUND)
				|| message.equals(ConstantValues.NOT_VALID_EMAIL_ADDRESS)
				|| message.equals(ConstantValues.NO_SELLER_FOUND)
				|| message.equals(ConstantValues.SUB_CATEGORY_DETAILS_NOT_FOUND)
				|| message.equals(ConstantValues.NO_PRODUCT_FOUND)
				|| message.equals(ConstantValues.NO_APPROVALS_FOUND)
				|| message.equals(ConstantValues.DISPLAY_DETAILS_NOT_FOUND)
				|| message.equals(ConstantValues.DISPLAY_DETAILS_NOT_FOUND_BY_ID)
				|| message.equals(ConstantValues.COLLECTIONSALE_NOT_FOUND)
				|| message.equals(ConstantValues.NO_WISHLIST_FOUND)
				|| message.equals(ConstantValues.ADDRESS_NOT_FOUND)
				|| message.equals(ConstantValues.NO_EMAIL_LINKED_TO_THE_ADDRESS)
				|| message.equals(ConstantValues.NO_BADGES_FOUND)
				|| message.equals(ConstantValues.SELECTEDPRODUCTS_NOT_FOUND)
				|| message.equals(ConstantValues.CART_NOT_FOUND)
				|| message.equals(ConstantValues.COLLECTIONSALE_PRODUCTIDS_NOT_FOUND)
				|| message.equals(ConstantValues.COLLECTIONSALE_IMAGES_NOT_FOUND)
				|| message.equals(ConstantValues.BLOG_REPORTS_NOT_FOUND)
				|| message.equals(ConstantValues.BLOGREPORT_LIST_NOT_FOUND_GIVEN_BID)
				|| message.equals(ConstantValues.GRANNY_BLOG_NOT_FOUND)
				|| message.equals(ConstantValues.GRANNYBLOG_LIST_NOT_FOUND_GIVEN_BLOGID )
				|| message.equals(ConstantValues.ORDERS_ITEMS_NOT_FOUND)
				|| message.equals(ConstantValues.DATA_NOT_FOUND)
				|| message.equals(ConstantValues.PRODUCT_KEYWORDS_LIST_NOT_FOUND_GIVEN_PID)
				||message.equals(ConstantValues.ORDER_NOT_FOUND)
				||message.equals(ConstantValues.ORDER_ITEMS_NOT_FOUND)
				||message.equals(ConstantValues.USER_NOT_FOUND)
				||message.equals(ConstantValues.NO_ORDER_CODE_FOUND)
				||message.equals(ConstantValues.RAZORPAY_TRANSACTION_NOT_FOUND)
				||message.equals(ConstantValues.PACKAGE_SHIPPING_NOT_FOUND)
				||message.equals(ConstantValues.SUBSCRIBER_NOT_FOUND)
				||message.equals(ConstantValues.YOU_HAVE_ALREDY_SUBSCRIBED)
				||message.equals(ConstantValues.EMAIL_ID_NOT_FOUND)
				||message.equals(ConstantValues.INSTA_ID_NOT_FOUND_EXCEPTION)
				||message.equals(ConstantValues.PHONE_NUMBER_NOT_FOUND)
				||message.equals(ConstantValues.CONTEST_NOT_FOUND)
				||message.equals("Coupon Already Used")
				||message.equals("Invalid Coupon")
				||message.equals("Coupon Expired")
				||message.equals("Coupon code length must be 8")
				||message.equals("Sorry, This coupon work only for 1500/- and above worth purchases")
				||message.equals(ConstantValues.SOURCES_NOT_FOUND)
				||message.equals(ConstantValues.INVOICE_NOT_FOUND)
				||message.equals(ConstantValues.SOLD_PRODUCTS_NOT_FOUND)
				||message.equals(ConstantValues.NOTIFICATION_NOT_FOUND_EXCEPTION))
				
		{
			seb.setMessage(message);
			seb.setResponsecode(serviceStatus);
			seb.setTiemstamp(System.currentTimeMillis());
		}else {
			seb = (ResponseBean) JsonToBeanConverter.convert(message, ResponseBean.class);
		}
			log.error(url + seb.toString());
		return new ResponseEntity<ResponseBean>(seb, headers.getHeader(), HttpStatus.OK);
	}

	//MailServiceException
	@ExceptionHandler
	public ResponseEntity<ResponseBean> handleException(MailServiceException e) {
		return loggerMessage("Security Service MailServiceException:", e.getMessage(),
				SecurityHttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler
	public ResponseEntity<ResponseBean> handleException(NotificationNotFoundException e) {
		return loggerMessage("URL:/notify---- handleException(NotificationNotFoundException)-", e.getMessage(),
				SecurityHttpStatus.BAD_REQUEST);
	}
}
