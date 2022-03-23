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
import com.lc.sk.inventory.security.beans.ResponseBean;
import com.lc.sk.inventory.security.beans.ShippingBean;
import com.lc.sk.inventory.security.beans.UrlBean;
import com.lc.sk.inventory.security.dao.HeaderKeyManagement;
import com.lc.sk.inventory.security.dao.ServiceUrlsDao;
import com.lc.sk.inventory.security.exceptions.subexceptions.BackEndDataException;
import com.lc.sk.inventory.security.exceptions.subexceptions.DataNotFoundException;
import com.lc.sk.inventory.security.exceptions.subexceptions.RestURLReaderException;
import com.lc.sk.inventory.security.factory.ServiceHttpRequestFactory;
import com.lc.sk.inventory.security.util.ArrayToListConverter;
import com.lc.sk.inventory.security.util.ConstantValues;
import com.lc.sk.inventory.security.util.JsonToBeanConverter;
import com.lc.sk.inventory.security.util.NextServiceURLMapping;
import com.lc.sk.inventory.security.util.UrlDetails;

@Component
public class ShippingRestService {

	@Autowired
	private HeaderKeyManagement headerKeyManagement;

	@Autowired
	private ServiceUrlsDao serviceUrlsDao;

	@Autowired
	private ServiceHttpRequestFactory requestFacotry;

	private UrlBean urls;
	private RestTemplate restTemplate;
	private HttpHeaders headers = new HttpHeaders();
	private Key key;
	private ResponseEntity<String> response;
// TODO Remove unused code found by UCDetector
// 	ObjectMapper jsondata = new ObjectMapper();

	// Insertion
		public ResponseBean insertShipping(String orderid,String customerid,String shippingstatus, String courierid,
				 String couriercompany,String shippingdate, String deliverydate) {
			RestTemplate restTemplate;
			key = headerKeyManagement.getKey(ConstantValues.ORDER_API_ACCESS_KEY);
			ResponseEntity<String> response;
			UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.SHIPPING_POST);
			ResponseBean responseBean = null;
				if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
					restTemplate = new RestTemplate(requestFacotry.validateOrdersSsl(urls.getTimeout()));
					headers.set(key.getName(), key.getValue());

					MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
					map.add(ConstantValues.ORDER_ID, orderid);
					map.add(ConstantValues.CUSTOMER_ID1, customerid);
					//map.add(ConstantValues.STATUS, status);
					map.add(ConstantValues.SHIPPING_STATUS, shippingstatus);
					map.add(ConstantValues.COURIER_ID, courierid);
					map.add(ConstantValues.COURIER_COMPANY, couriercompany);
					map.add(ConstantValues.SHIPPING_DATE, shippingdate);
					map.add(ConstantValues.DELIVERY_DATE, deliverydate);
					HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

					response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
					if (response.getStatusCode() == HttpStatus.ACCEPTED) {
						responseBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(), ResponseBean.class);
					}
					else if(response.getStatusCode()==HttpStatus.OK)
					{
						ResponseBean resBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(),
								ResponseBean.class);
						throw new BackEndDataException(resBean.getMessage());
					}
				} else {
					throw new RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED);
				}
			return responseBean;
		}
		
		
		
		// Updation
				public ResponseBean updateShipping(String shippingid,String orderid,String customerid, String shippingstatus,
						 String courierid, String couriercompany,String shippingdate, String deliverydate) {
					RestTemplate restTemplate;
					key = headerKeyManagement.getKey(ConstantValues.ORDER_API_ACCESS_KEY);
					ResponseEntity<String> response;
					UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.SHIPPING_PUT);
					ResponseBean responseBean = null;
						if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
							restTemplate = new RestTemplate(requestFacotry.validateOrdersSsl(urls.getTimeout()));
							headers.set(key.getName(), key.getValue());

							MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
							map.add(ConstantValues.SHIPPING_ID, shippingid);
							map.add(ConstantValues.ORDER_ID, orderid);
							map.add(ConstantValues.CUSTOMER_ID1, customerid);
							//map.add(ConstantValues.STATUS, status);
							map.add(ConstantValues.SHIPPING_STATUS, shippingstatus);
							map.add(ConstantValues.COURIER_ID, courierid);
							map.add(ConstantValues.COURIER_COMPANY, couriercompany);
							map.add(ConstantValues.SHIPPING_DATE, shippingdate);
							map.add(ConstantValues.DELIVERY_DATE, deliverydate);
							HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

							response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
							if (response.getStatusCode() == HttpStatus.ACCEPTED) {
								responseBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(), ResponseBean.class);
							}
							else if(response.getStatusCode()==HttpStatus.OK)
							{
								ResponseBean resBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(),
										ResponseBean.class);
								throw new BackEndDataException(resBean.getMessage());
							}
						} else {
							throw new RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED);
						}
					return responseBean;
				}
				
				
				// Get All Shipping
				public List<ShippingBean> getAllShipping() {
					RestTemplate restTemplate;
					key = headerKeyManagement.getKey(ConstantValues.ORDER_API_ACCESS_KEY);
					ResponseEntity<String> response;
					UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.SHIPPING_GET);
					List<ShippingBean> ship = null;
						if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
							restTemplate = new RestTemplate(requestFacotry.validateOrdersSsl(urls.getTimeout()));
							headers.set(key.getName(), key.getValue());

							HttpEntity<Object> request = new HttpEntity<>(headers);
							response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
							if (response.getStatusCode() == HttpStatus.ACCEPTED) {
								ShippingBean[] ship_temp = (ShippingBean[]) JsonToBeanConverter.convert(response.getBody(), ShippingBean[].class);
								ship = ArrayToListConverter.convertArrayToList(ship_temp);
							}
							else if(response.getStatusCode()==HttpStatus.OK)
							{
								ResponseBean resBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(),
										ResponseBean.class);
								throw new DataNotFoundException(resBean.getMessage());
							}
						} else {
							throw new RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED);
						}
					return ship;
				}
				
				
				// Details By shippingid
				public ShippingBean getShippingbyid(String shippingid) {
					RestTemplate restTemplate;
					key = headerKeyManagement.getKey(ConstantValues.ORDER_API_ACCESS_KEY);
					ResponseEntity<String> response;
					UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.SHIPPING_GET);
					ShippingBean ship = null;
						if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
							restTemplate = new RestTemplate(requestFacotry.validateOrdersSsl(urls.getTimeout()));
							headers.set(key.getName(), key.getValue());
							urls.setUrl(urls.getUrl() + ConstantValues.SLASH_TAG + shippingid);
							
							HttpEntity<Object> request = new HttpEntity<>(headers);
							response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
							
							if (response.getStatusCode() == HttpStatus.ACCEPTED) {
								ship = (ShippingBean) JsonToBeanConverter.convert(response.getBody(), ShippingBean.class);
							}
							else if(response.getStatusCode()==HttpStatus.OK)
							{
								ResponseBean resBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(),
										ResponseBean.class);
								throw new DataNotFoundException(resBean.getMessage());
							}

						} else {
							throw new RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED);
						}
					return ship;
				}
				
				
				// Details By customerid
				public ShippingBean getShippingByCustomerid(String customerid) {
					RestTemplate restTemplate;
					key = headerKeyManagement.getKey(ConstantValues.ORDER_API_ACCESS_KEY);
					ResponseEntity<String> response;
					UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.SHIPPING_GET_BY_CUSTOMER_ID);
					ShippingBean ship = null;
						if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
							restTemplate = new RestTemplate(requestFacotry.validateOrdersSsl(urls.getTimeout()));
							headers.set(key.getName(), key.getValue());
							urls.setUrl(urls.getUrl() + ConstantValues.SLASH_TAG + customerid);
							
							HttpEntity<Object> request = new HttpEntity<>(headers);
							response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
							
							if (response.getStatusCode() == HttpStatus.ACCEPTED) {
								ship = (ShippingBean) JsonToBeanConverter.convert(response.getBody(), ShippingBean.class);
							}
							else if(response.getStatusCode()==HttpStatus.OK)
							{
								ResponseBean resBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(),
										ResponseBean.class);
								throw new DataNotFoundException(resBean.getMessage());
							}

						} else {
							throw new RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED);
						}
					return ship;
				}
				
				
				// Details By courierid
				public ShippingBean getshippingbyCourierid(String courierid) {
					RestTemplate restTemplate;
					key = headerKeyManagement.getKey(ConstantValues.ORDER_API_ACCESS_KEY);
					ResponseEntity<String> response;
					UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.SHIPPING_GET_BY_COURIER_ID);
					ShippingBean ship = null;
						if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
							restTemplate = new RestTemplate(requestFacotry.validateOrdersSsl(urls.getTimeout()));
							headers.set(key.getName(), key.getValue());
							urls.setUrl(urls.getUrl() + ConstantValues.SLASH_TAG + courierid);
							
							HttpEntity<Object> request = new HttpEntity<>(headers);
							response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
							
							if (response.getStatusCode() == HttpStatus.ACCEPTED) {
								ship = (ShippingBean) JsonToBeanConverter.convert(response.getBody(), ShippingBean.class);
							}
							else if(response.getStatusCode()==HttpStatus.OK)
							{
								ResponseBean resBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(),
										ResponseBean.class);
								throw new DataNotFoundException(resBean.getMessage());
							}

						} else {
							throw new RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED);
						}
					return ship;
				}
				
				
}
