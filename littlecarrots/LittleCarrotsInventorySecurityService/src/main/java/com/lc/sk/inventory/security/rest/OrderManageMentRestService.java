package com.lc.sk.inventory.security.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lc.sk.inventory.security.beans.Key;
import com.lc.sk.inventory.security.beans.OrderFullDetailsSecurityBean;
import com.lc.sk.inventory.security.beans.OrderFullDetailsSecurityBean1;
import com.lc.sk.inventory.security.beans.OrderedBeanSecurity;
import com.lc.sk.inventory.security.beans.Orderitemsstatus;
import com.lc.sk.inventory.security.beans.OrdersBean;
import com.lc.sk.inventory.security.beans.Razorpay;
import com.lc.sk.inventory.security.beans.RazorpayOrderedSecurityBean;
import com.lc.sk.inventory.security.beans.ResponseBean;
import com.lc.sk.inventory.security.beans.UrlBean;
import com.lc.sk.inventory.security.dao.HeaderKeyManagement;
import com.lc.sk.inventory.security.dao.ServiceUrlsDao;
import com.lc.sk.inventory.security.exceptions.subexceptions.BackEndDataException;
import com.lc.sk.inventory.security.exceptions.subexceptions.DataNotFoundException;
import com.lc.sk.inventory.security.exceptions.subexceptions.RestResponseException;
import com.lc.sk.inventory.security.exceptions.subexceptions.RestURLReaderException;
import com.lc.sk.inventory.security.factory.ServiceHttpRequestFactory;
import com.lc.sk.inventory.security.util.ArrayToListConverter;
import com.lc.sk.inventory.security.util.ConstantValues;
import com.lc.sk.inventory.security.util.JsonToBeanConverter;
import com.lc.sk.inventory.security.util.NextServiceURLMapping;
import com.lc.sk.inventory.security.util.UrlDetails;

@Component
public class OrderManageMentRestService {

	@Autowired
	private HeaderKeyManagement headerKeyManagement;

	@Autowired
	private ServiceUrlsDao serviceUrlsDao;

	@Autowired
	private ServiceHttpRequestFactory requestFacotry;

	private HttpHeaders headers = new HttpHeaders();
	private Key key;
	//private ResponseEntity<String> response;
// TODO Remove unused code found by UCDetector
// 	ObjectMapper jsondata = new ObjectMapper();

	// get method for orders
	public List<OrdersBean> getAllOrders(Long codeid) {
		RestTemplate restTemplate;
		key = headerKeyManagement.getKey(ConstantValues.ORDER_API_ACCESS_KEY);
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.ORDER_GET);
		List<OrdersBean> orders = null;
		if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
			restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
			headers.set(key.getName(), key.getValue());
			urls.setUrl(urls.getUrl() + ConstantValues.SLASH_TAG + codeid + ConstantValues.SLASH_TAG + "dummy");
			HttpEntity<Object> request = new HttpEntity<>(headers);
			response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
			if (response.getStatusCode() == HttpStatus.ACCEPTED) {
				OrdersBean[] ob = (OrdersBean[]) JsonToBeanConverter.convert(response.getBody(), OrdersBean[].class);
				orders = ArrayToListConverter.convertArrayToList(ob);
			} else if (response.getStatusCode() == HttpStatus.OK) {
				ResponseBean resBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(),
						ResponseBean.class);
				throw new DataNotFoundException(resBean.getMessage());
			}

		} else {
			throw new RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED);
		}
		return orders;
	}

	/*
	 * //get orders by orderid public List<OrdersBean> getORdersByOrderId(String
	 * orderid) {
	 * 
	 * key = headerKeyManagement.getKey(ConstantValues.ORDER_API_ACCESS_KEY); urls =
	 * serviceUrlsDao.getAccessUrl(UrlDetails.ORDER_GET); List<OrdersBean> orders =
	 * null; if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
	 * restTemplate = new
	 * RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
	 * headers.set(key.getName(), key.getValue()); urls.setUrl(urls.getUrl() +
	 * ConstantValues.SLASH_TAG + orderid); HttpEntity<Object> request = new
	 * HttpEntity<>(headers); response = restTemplate.exchange(urls.getUrl(),
	 * urls.getMethod(), request, String.class); if (response.getStatusCode() ==
	 * HttpStatus.ACCEPTED) { OrdersBean[] orders_temp = (OrdersBean[])
	 * JsonToBeanConverter.convert(response.getBody(), OrdersBean[].class); orders =
	 * ArrayToListConverter.convertArrayToList(orders_temp); }else
	 * if(response.getStatusCode()==HttpStatus.OK) { ResponseBean resBean =
	 * (ResponseBean) JsonToBeanConverter.convert(response.getBody(),
	 * ResponseBean.class); throw new BackEndDataException(resBean.getMessage()); }
	 * 
	 * } else { throw new
	 * RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED); } return orders;
	 * 
	 * }
	 * 
	 * 
	 * 
	 * // get orders by customerid public List<OrdersBean>
	 * getOrdersByCustomerId(String customerid) {
	 * 
	 * key = headerKeyManagement.getKey(ConstantValues.ORDER_API_ACCESS_KEY); urls =
	 * serviceUrlsDao.getAccessUrl(UrlDetails.ORDER_GET_CUSTOMERID);
	 * List<OrdersBean> orders = null; if
	 * (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) { restTemplate = new
	 * RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
	 * headers.set(key.getName(), key.getValue()); urls.setUrl(urls.getUrl() +
	 * ConstantValues.SLASH_TAG + customerid); HttpEntity<Object> request = new
	 * HttpEntity<>(headers); response = restTemplate.exchange(urls.getUrl(),
	 * urls.getMethod(), request, String.class); if (response.getStatusCode() ==
	 * HttpStatus.ACCEPTED) { OrdersBean[] orders_temp = (OrdersBean[])
	 * JsonToBeanConverter.convert(response.getBody(), OrdersBean[].class); orders =
	 * ArrayToListConverter.convertArrayToList(orders_temp); }else
	 * if(response.getStatusCode()==HttpStatus.OK) { ResponseBean resBean =
	 * (ResponseBean) JsonToBeanConverter.convert(response.getBody(),
	 * ResponseBean.class); throw new BackEndDataException(resBean.getMessage()); }
	 * 
	 * } else { throw new
	 * RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED); } return orders;
	 * 
	 * }
	 */
	// POST for orders
	public OrderedBeanSecurity InsertOrders(String customerid, String totalprice, String ordercodeid, String addressid,
			String productid[], String quantity[], String productprice[]) {
		RestTemplate restTemplate;
		key = headerKeyManagement.getKey(ConstantValues.ORDER_API_ACCESS_KEY);
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.ORDER_POST);
		OrderedBeanSecurity responseBean = null;
		if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
			restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
			headers.set(key.getName(), key.getValue());

			MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
			map.add(ConstantValues.CUSTOMER_ID1, customerid);
			map.add(ConstantValues.TOTALPRICE, totalprice);
			map.add(ConstantValues.ORDERCODEID, ordercodeid);
			map.add(ConstantValues.ADDRESSID, addressid);
			System.out.println(customerid + "-----" + totalprice + "------" + ordercodeid + "------" + productid
					+ "---------------" + quantity + "-----" + productprice);
			for (String ids : productid) {
				map.add(ConstantValues.PRODUCT_ID1, ids);
			}
			for (String x : quantity) {
				map.add(ConstantValues.QUANTITYARRAY, x);
			}
			for (String y : productprice) {
				map.add(ConstantValues.PRODUCTPRICEARRAY, y);
			}
			HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
			System.out.println(request);
			response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
			// System.out.println(response);
			if (response.getStatusCode() == HttpStatus.ACCEPTED) {
				responseBean = (OrderedBeanSecurity) JsonToBeanConverter.convert(response.getBody(),
						OrderedBeanSecurity.class);

			} else if (response.getStatusCode() == HttpStatus.FAILED_DEPENDENCY
					|| response.getStatusCode() == HttpStatus.BAD_REQUEST
					|| response.getStatusCode() == HttpStatus.UNAUTHORIZED) {
				throw new BackEndDataException(response.getBody());
			} else if (response.getStatusCode() == HttpStatus.OK) {
				ResponseBean resBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(),
						ResponseBean.class);
				throw new RestResponseException(resBean.getMessage());
			}
		} else {
			throw new RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED);
		}
		return responseBean;
	}

	// get Full details 14215273
	public OrderFullDetailsSecurityBean getFULLDETAILS(String orderid) {
		RestTemplate restTemplate;
		key = headerKeyManagement.getKey(ConstantValues.ORDER_API_ACCESS_KEY);
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.ORDER_FULL_DETAILS_BY_ORDERID);
		OrderFullDetailsSecurityBean genders = null;
		if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
			restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
			headers.set(key.getName(), key.getValue());
			urls.setUrl(urls.getUrl() + ConstantValues.SLASH_TAG + orderid);
			HttpEntity<Object> request = new HttpEntity<>(headers);
			response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);

			if (response.getStatusCode() == HttpStatus.ACCEPTED) {
				genders = (OrderFullDetailsSecurityBean) JsonToBeanConverter.convert(response.getBody(),
						OrderFullDetailsSecurityBean.class);
			} else if (response.getStatusCode() == HttpStatus.OK) {
				ResponseBean resBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(),
						ResponseBean.class);
				throw new DataNotFoundException(resBean.getMessage());
			}

		} else {
			throw new RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED);
		}
		return genders;
	}

	// Get full details by cutomerid
	public List<OrderFullDetailsSecurityBean> getFULLDETAILSByCustomerid(String customerid) {
		RestTemplate restTemplate;
		key = headerKeyManagement.getKey(ConstantValues.ORDER_API_ACCESS_KEY);
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.ORDER_FULL_DETAILS_CUSTOMERID);
		List<OrderFullDetailsSecurityBean> genders = null;
		if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
			restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
			headers.set(key.getName(), key.getValue());
			urls.setUrl(urls.getUrl() + ConstantValues.SLASH_TAG + customerid);
			HttpEntity<Object> request = new HttpEntity<>(headers);
			response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);

			if (response.getStatusCode() == HttpStatus.ACCEPTED) {
				OrderFullDetailsSecurityBean[] orders_temp = (OrderFullDetailsSecurityBean[]) JsonToBeanConverter
						.convert(response.getBody(), OrderFullDetailsSecurityBean[].class);
				genders = ArrayToListConverter.convertArrayToList(orders_temp);

			} else if (response.getStatusCode() == HttpStatus.OK) {
				ResponseBean resBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(),
						ResponseBean.class);
				throw new DataNotFoundException(resBean.getMessage());
			}

		} else {
			throw new RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED);
		}
		return genders;
	}

	
	  // Update method for orders 
	public ResponseBean update(String orderid,String
	  customerid,String totalprice,String ordercodeid,String paymenttransactionid)
	  {
		RestTemplate restTemplate;
		key = headerKeyManagement.getKey(ConstantValues.ORDER_API_ACCESS_KEY); ResponseEntity<String> response;
		UrlBean urls
	  = serviceUrlsDao.getAccessUrl(UrlDetails.ORDER_PUT); ResponseBean
	  responseBean = null; if
	  (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) { restTemplate = new
	  RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
	  headers.set(key.getName(), key.getValue());
	  
	  MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
	  map.add(ConstantValues.ORDER_ID, orderid);
	  map.add(ConstantValues.CUSTOMER_ID1, customerid);
	  map.add(ConstantValues.TOTALPRICE, totalprice);
	  map.add(ConstantValues.ORDERCODEID, ordercodeid);
	  map.add(ConstantValues.PAYMENT_TRANSACTION_ID, paymenttransactionid);
	  HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map,
	  headers);
	  
	  response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request,
	  String.class); if (response.getStatusCode() == HttpStatus.ACCEPTED) {
	  responseBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(),
	  ResponseBean.class); } else if(response.getStatusCode()==HttpStatus.OK) {
	  ResponseBean resBean = (ResponseBean)
	  JsonToBeanConverter.convert(response.getBody(), ResponseBean.class); throw
	  new DataNotFoundException(resBean.getMessage()); } } else { throw new
	  RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED); } return
	  responseBean; }
	 
	/*
	 * //Get all method for orderitems
	 * 
	 * public List<OrderItemsBean> getAllOrderItems() { key =
	 * headerKeyManagement.getKey(ConstantValues.ORDER_API_ACCESS_KEY); urls =
	 * serviceUrlsDao.getAccessUrl(UrlDetails.ORDER_ITEMS_GET); List<OrderItemsBean>
	 * orders = null; if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
	 * restTemplate = new
	 * RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
	 * headers.set(key.getName(), key.getValue());
	 * 
	 * HttpEntity<Object> request = new HttpEntity<>(headers); response =
	 * restTemplate.exchange(urls.getUrl(), urls.getMethod(), request,
	 * String.class); if (response.getStatusCode() == HttpStatus.ACCEPTED) {
	 * OrderItemsBean[] ob = (OrderItemsBean[])
	 * JsonToBeanConverter.convert(response.getBody(), OrderItemsBean[].class);
	 * orders = ArrayToListConverter.convertArrayToList(ob); } else
	 * if(response.getStatusCode()==HttpStatus.OK) { ResponseBean resBean =
	 * (ResponseBean) JsonToBeanConverter.convert(response.getBody(),
	 * ResponseBean.class); throw new DataNotFoundException(resBean.getMessage()); }
	 * 
	 * } else { throw new
	 * RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED); } return orders;
	 * }
	 * 
	 * 
	 * //Get orderitems by orderitemsid public List<OrderItemsBean>
	 * getOrderItemsByOrderITEMSID(String orderitemsid) {
	 * 
	 * key = headerKeyManagement.getKey(ConstantValues.ORDER_API_ACCESS_KEY); urls =
	 * serviceUrlsDao.getAccessUrl(UrlDetails.ORDER_ITEMS_GET); List<OrderItemsBean>
	 * orders = null; if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
	 * restTemplate = new
	 * RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
	 * headers.set(key.getName(), key.getValue()); urls.setUrl(urls.getUrl() +
	 * ConstantValues.SLASH_TAG + orderitemsid); HttpEntity<Object> request = new
	 * HttpEntity<>(headers); response = restTemplate.exchange(urls.getUrl(),
	 * urls.getMethod(), request, String.class); if (response.getStatusCode() ==
	 * HttpStatus.ACCEPTED) { OrderItemsBean[] orders_temp = (OrderItemsBean[])
	 * JsonToBeanConverter.convert(response.getBody(), OrderItemsBean[].class);
	 * orders = ArrayToListConverter.convertArrayToList(orders_temp); }else
	 * if(response.getStatusCode()==HttpStatus.OK) { ResponseBean resBean =
	 * (ResponseBean) JsonToBeanConverter.convert(response.getBody(),
	 * ResponseBean.class); throw new BackEndDataException(resBean.getMessage()); }
	 * 
	 * } else { throw new
	 * RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED); } return orders;
	 * 
	 * }
	 * 
	 * //Get OrderItems by order id public List<OrderItemsBean>
	 * getOrderItemsByOrderID(String orderid) {
	 * 
	 * key = headerKeyManagement.getKey(ConstantValues.ORDER_API_ACCESS_KEY); urls =
	 * serviceUrlsDao.getAccessUrl(UrlDetails.ORDER_ITEMS_GET_BY_ORDERID);
	 * List<OrderItemsBean> orders = null; if
	 * (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) { restTemplate = new
	 * RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
	 * headers.set(key.getName(), key.getValue()); urls.setUrl(urls.getUrl() +
	 * ConstantValues.SLASH_TAG + orderid); HttpEntity<Object> request = new
	 * HttpEntity<>(headers); response = restTemplate.exchange(urls.getUrl(),
	 * urls.getMethod(), request, String.class); if (response.getStatusCode() ==
	 * HttpStatus.ACCEPTED) { OrderItemsBean[] orders_temp = (OrderItemsBean[])
	 * JsonToBeanConverter.convert(response.getBody(), OrderItemsBean[].class);
	 * orders = ArrayToListConverter.convertArrayToList(orders_temp); }else
	 * if(response.getStatusCode()==HttpStatus.OK) { ResponseBean resBean =
	 * (ResponseBean) JsonToBeanConverter.convert(response.getBody(),
	 * ResponseBean.class); throw new BackEndDataException(resBean.getMessage()); }
	 * 
	 * } else { throw new
	 * RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED); } return orders;
	 * 
	 * }
	 */

	/*
	 * //Put Method for order items public ResponseBean updateOrderItems(String
	 * orderitemsid,String orderid,String quantity,String productprice,String
	 * deliverydate,String ordercodeid ) { key =
	 * headerKeyManagement.getKey(ConstantValues.ORDER_API_ACCESS_KEY); urls =
	 * serviceUrlsDao.getAccessUrl(UrlDetails.ORDER_PUT); ResponseBean responseBean
	 * = null; if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
	 * restTemplate = new
	 * RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
	 * headers.set(key.getName(), key.getValue());
	 * 
	 * MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
	 * map.add(ConstantValues.ORDEr_ITEMS_ID, orderitemsid);
	 * map.add(ConstantValues.ORDER_ID, orderid); map.add(ConstantValues.QUANTITY1,
	 * quantity); map.add(ConstantValues.PRODUCT_PRICE, productprice);
	 * map.add(ConstantValues.DELIVERY_DATE,deliverydate);
	 * map.add(ConstantValues.ORDERCODEID, ordercodeid);
	 * HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map,
	 * headers);
	 * 
	 * response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request,
	 * String.class); if (response.getStatusCode() == HttpStatus.ACCEPTED) {
	 * responseBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(),
	 * ResponseBean.class); } else if(response.getStatusCode()==HttpStatus.OK) {
	 * ResponseBean resBean = (ResponseBean)
	 * JsonToBeanConverter.convert(response.getBody(), ResponseBean.class); throw
	 * new DataNotFoundException(resBean.getMessage()); } } else { throw new
	 * RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED); } return
	 * responseBean; }
	 */

	
	// Get All List Razorpay
	public List<Razorpay> getAllRazorpaylist() {
		RestTemplate restTemplate;
		key = headerKeyManagement.getKey(ConstantValues.ORDER_API_ACCESS_KEY);
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.RAZORPAY_GET);
		List<Razorpay> razorpay = null;
		if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
			restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
			headers.set(key.getName(), key.getValue());

			HttpEntity<Object> request = new HttpEntity<>(headers);
			response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
			System.out.println("jfghd");
			if (response.getStatusCode() == HttpStatus.ACCEPTED) {
				Razorpay[] ob = (Razorpay[]) JsonToBeanConverter.convert(response.getBody(), Razorpay[].class);
				razorpay = ArrayToListConverter.convertArrayToList(ob);
			} else if (response.getStatusCode() == HttpStatus.OK) {
				ResponseBean resBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(),
						ResponseBean.class);
				throw new DataNotFoundException(resBean.getMessage());
			}

		} else {
			throw new RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED);
		}
		return razorpay;
	}


	//Get by RazorpayID								
	public Razorpay getRazorpayById(String razorpay_order_id) {
		RestTemplate restTemplate;
		key = headerKeyManagement.getKey(ConstantValues.ORDER_API_ACCESS_KEY);
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.RAZORPAY_GET);
		Razorpay razorpay = null;
//try {
		if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
			restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
			headers.set(key.getName(), key.getValue());
			urls.setUrl(urls.getUrl() + ConstantValues.SLASH_TAG + razorpay_order_id);
			HttpEntity<Object> request = new HttpEntity<>(headers);
			response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
			if (response.getStatusCode() == HttpStatus.ACCEPTED) {
				razorpay = (Razorpay) JsonToBeanConverter.convert(response.getBody(), Razorpay.class);
			} else if (response.getStatusCode() == HttpStatus.OK) {
				ResponseBean resBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(),
						ResponseBean.class);
				throw new DataNotFoundException(resBean.getMessage());
			}

		} else {
			throw new RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED);
		}

		return razorpay;
	}
	
	//POST Insertion
			public RazorpayOrderedSecurityBean InsertRazorpay(String razorpay_order_id,String razorpay_payment_id,String razorpay_signature,
					String customerid,String orderid,String addressid)
			{
				RestTemplate restTemplate;
				key = headerKeyManagement.getKey(ConstantValues.ORDER_API_ACCESS_KEY);
				ResponseEntity<String> response;
				UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.RAZORPAY_POST);
				RazorpayOrderedSecurityBean responseBean = null;
				//try {
					if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
						restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
						headers.set(key.getName(), key.getValue());

						MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
						map.add(ConstantValues.RAZORPAY_ORDER_ID, razorpay_order_id);
						map.add(ConstantValues.RAZORPAY_SIGNATURE, razorpay_signature);
						map.add(ConstantValues.RAZORPAY_PAYMENT_ID, razorpay_payment_id);
						map.add(ConstantValues.ORDER_ID, orderid);
						map.add(ConstantValues.CUSTOMERR_ID, customerid);
						map.add(ConstantValues.ADDRESSID, addressid);
						HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

						response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
						if (response.getStatusCode() == HttpStatus.ACCEPTED) {
							
							responseBean = (RazorpayOrderedSecurityBean) JsonToBeanConverter.convert(response.getBody(),
									RazorpayOrderedSecurityBean.class);
						} else if (response.getStatusCode() == HttpStatus.OK) {
							throw new BackEndDataException(response.getBody());
						}
					} else {
						throw new RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED);
					}
					
				/*} catch (Exception e) {
					throw new RestResponseException(e.getMessage());
				}*/
				return responseBean;
			}
	
			
			
			//ordersitems list
			public ResponseBean InsertOrdersItemsStatus(String orderid,  String orderitemid,String ordercodeid,String msg ) {
				RestTemplate restTemplate;
				key = headerKeyManagement.getKey(ConstantValues.ORDER_API_ACCESS_KEY);
				ResponseEntity<String> response;
				UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.ORDER_ITEMS_STATUS_POST);
				ResponseBean responseBean = null;
			
				if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
					restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
					headers.set(key.getName(), key.getValue());

					MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
					map.add(ConstantValues.ORDER_ID, orderid);
					map.add(ConstantValues.ORDERCODEID, ordercodeid);
					map.add(ConstantValues.ORDER_ITEMS_ID, orderitemid);
					map.add(ConstantValues.MESSAGE, msg);
					
					HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
			
					response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
				//	 System.out.println(response);
					if (response.getStatusCode() == HttpStatus.ACCEPTED) {
						responseBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(),
								ResponseBean.class);

					} else if (response.getStatusCode() == HttpStatus.FAILED_DEPENDENCY
							|| response.getStatusCode() == HttpStatus.BAD_REQUEST
							|| response.getStatusCode() == HttpStatus.UNAUTHORIZED) {
						throw new BackEndDataException(response.getBody());
					} else if (response.getStatusCode() == HttpStatus.OK) {
						ResponseBean resBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(),
								ResponseBean.class);
						throw new RestResponseException(resBean.getMessage());
					}
				} else {
					throw new RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED);
				}
				return responseBean;
			}
	
			
			// Get All ordersitems list
			public List<Orderitemsstatus> getAllOrderItemsListlist(String orderid) {
				RestTemplate restTemplate;
				key = headerKeyManagement.getKey(ConstantValues.ORDER_API_ACCESS_KEY);
				ResponseEntity<String> response;
				UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.ORDER_ITEMS_STATUS_GET);
				List<Orderitemsstatus> razorpay = null;
				if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
					restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
					headers.set(key.getName(), key.getValue());
					urls.setUrl(urls.getUrl() + ConstantValues.SLASH_TAG + orderid);
					HttpEntity<Object> request = new HttpEntity<>(headers);
					response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
					System.out.println("jfghd");
					if (response.getStatusCode() == HttpStatus.ACCEPTED) {
						Orderitemsstatus[] ob = (Orderitemsstatus[]) JsonToBeanConverter.convert(response.getBody(), Orderitemsstatus[].class);
						razorpay = ArrayToListConverter.convertArrayToList(ob);
					} else if (response.getStatusCode() == HttpStatus.OK) {
						ResponseBean resBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(),
								ResponseBean.class);
						throw new DataNotFoundException(resBean.getMessage());
					}

				} else {
					throw new RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED);
				}
				return razorpay;
			}
			
			
			//  get all order items status by orderitems id
			public List<Orderitemsstatus> getAllOrderItemsStatusListByOrderitemsList(String orderitemsid) {
				RestTemplate restTemplate;
				key = headerKeyManagement.getKey(ConstantValues.ORDER_API_ACCESS_KEY);
				ResponseEntity<String> response;
				UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.ORDERITEMSTATUS_BY_ITEMSID);
				List<Orderitemsstatus> status = null;
				if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
					restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
					headers.set(key.getName(), key.getValue());
					urls.setUrl(urls.getUrl() + ConstantValues.SLASH_TAG + orderitemsid);
					HttpEntity<Object> request = new HttpEntity<>(headers);
					response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
					System.out.println("jfghd");
					if (response.getStatusCode() == HttpStatus.ACCEPTED) {
						Orderitemsstatus[] ob = (Orderitemsstatus[]) JsonToBeanConverter.convert(response.getBody(), Orderitemsstatus[].class);
						status = ArrayToListConverter.convertArrayToList(ob);
					} else if (response.getStatusCode() == HttpStatus.OK) {
						ResponseBean resBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(),
								ResponseBean.class);
						throw new DataNotFoundException(resBean.getMessage());
					}

				} else {
					throw new RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED);
				}
				return status;
			}
			
			//Put Method for order items 
			public ResponseBean updateOrderCODE(String orderid,String ordercodeid ) 
			{ 
				RestTemplate restTemplate;
				key = headerKeyManagement.getKey(ConstantValues.ORDER_API_ACCESS_KEY); 
				ResponseEntity<String> response;
				UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.ORDER_TABLE_STAUS_PUT);
				ResponseBean responseBean= null;
				if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
			 restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
			  headers.set(key.getName(), key.getValue());
			 
			  MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
			  map.add(ConstantValues.ORDERCODEID, ordercodeid);
			  map.add(ConstantValues.ORDER_ID, orderid);
			 
			HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map,
			 headers);
			  
			  response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request,String.class); 
			  if (response.getStatusCode() == HttpStatus.ACCEPTED) {
			 responseBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(),
			  ResponseBean.class); 
			 } else if(response.getStatusCode()==HttpStatus.OK) {
			  ResponseBean resBean = (ResponseBean)JsonToBeanConverter.convert(response.getBody(), ResponseBean.class);
			  throw new DataNotFoundException(resBean.getMessage()); 
			  } } else { 
				  throw new  RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED);
				  }
				return responseBean;
				}
			 
			
			
			
			
			public OrderFullDetailsSecurityBean1 getNotificationInfo(String orderid) {
				RestTemplate restTemplate;
				key = headerKeyManagement.getKey(ConstantValues.ORDER_API_ACCESS_KEY);
				ResponseEntity<String> response;
				UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.ORDER_CHK);
				OrderFullDetailsSecurityBean1 genders = null;
				if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
					restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
					headers.set(key.getName(), key.getValue());
					urls.setUrl(urls.getUrl() + ConstantValues.SLASH_TAG + orderid);
					HttpEntity<Object> request = new HttpEntity<>(headers);
					System.err.println("URL"+urls.getUrl()+" ::Method:"+urls.getMethod());
					response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);

					if (response.getStatusCode() == HttpStatus.ACCEPTED) {
						genders = (OrderFullDetailsSecurityBean1) JsonToBeanConverter.convert(response.getBody(),
								OrderFullDetailsSecurityBean1.class);
					} else if (response.getStatusCode() == HttpStatus.OK) {
						ResponseBean resBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(),
								ResponseBean.class);
						throw new DataNotFoundException(resBean.getMessage());
					}

				} else {
					throw new RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED);
				}
				return genders;
			}
			
			
			 // Update method for customerid and addressid 
			public ResponseBean update(String orderid,String userid,String addressid)
			  {
				RestTemplate restTemplate;
				key = headerKeyManagement.getKey(ConstantValues.ORDER_API_ACCESS_KEY); ResponseEntity<String> response;
				UrlBean urls
			  = serviceUrlsDao.getAccessUrl(UrlDetails.DETAILS_PUT); ResponseBean
			  responseBean = null; if
			  (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) { restTemplate = new
			  RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
			  headers.set(key.getName(), key.getValue());
			  
			  MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
			  map.add(ConstantValues.ORDER_ID, orderid);
			  map.add(ConstantValues.USERID, userid);
			  map.add(ConstantValues.ADDRESSID, addressid);
			  
			  HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map,
			  headers);
			//  System.err.println("---------------------------------------------------");
			//  System.err.println(urls);
			//  System.err.println("---------------------------------------------------");
			  response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request,
			  String.class); if (response.getStatusCode() == HttpStatus.ACCEPTED) {
			  responseBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(),
			  ResponseBean.class); } else if(response.getStatusCode()==HttpStatus.OK) {
			  ResponseBean resBean = (ResponseBean)
			  JsonToBeanConverter.convert(response.getBody(), ResponseBean.class); throw
			  new DataNotFoundException(resBean.getMessage()); } } else { throw new
			  RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED); } return
			  responseBean; }
}
