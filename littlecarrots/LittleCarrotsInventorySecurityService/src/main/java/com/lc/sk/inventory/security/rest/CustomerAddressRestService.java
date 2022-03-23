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

import com.lc.sk.inventory.security.beans.CustAddBean;
import com.lc.sk.inventory.security.beans.CustomerAddressBean;
import com.lc.sk.inventory.security.beans.Key;
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
public class CustomerAddressRestService {
	
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
	
	//get method
		public List<CustomerAddressBean> getAllCustomerAddress() {
			RestTemplate restTemplate;
			key = headerKeyManagement.getKey(ConstantValues.AUTH_HEADER_KEY_DB_ID);
			ResponseEntity<String> response;
			UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.CUSTOMERADDRESS_GET);
			List<CustomerAddressBean> custadd = null;
				if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
					restTemplate = new RestTemplate(requestFacotry.validateAuthenticationSsl(urls.getTimeout()));
					headers.set(key.getName(), key.getValue());

					HttpEntity<Object> request = new HttpEntity<>(headers);
					response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
					if (response.getStatusCode() == HttpStatus.ACCEPTED) {
						CustomerAddressBean[] custadd_temp = (CustomerAddressBean[]) JsonToBeanConverter.convert(response.getBody(), CustomerAddressBean[].class);
						custadd = ArrayToListConverter.convertArrayToList(custadd_temp);
					}else if(response.getStatusCode()==HttpStatus.OK)
					{
						ResponseBean resBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(),
								ResponseBean.class);
						throw new BackEndDataException(resBean.getMessage());
					}

				} 
				else {
					throw new RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED);
				}

			return custadd;
		}
	
		public List<CustomerAddressBean> getCustAddByEmail(String email) {
			RestTemplate restTemplate;
			key = headerKeyManagement.getKey(ConstantValues.AUTH_HEADER_KEY_DB_ID);
			ResponseEntity<String> response;
			UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.CUSTOMERADDRESS_GET);
			List<CustomerAddressBean> custadd = null;
				if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
					restTemplate = new RestTemplate(requestFacotry.validateAuthenticationSsl(urls.getTimeout()));
					headers.set(key.getName(), key.getValue());
					urls.setUrl(urls.getUrl() + ConstantValues.SLASH_TAG + email);
					HttpEntity<Object> request = new HttpEntity<>(headers);
					response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
					if (response.getStatusCode() == HttpStatus.ACCEPTED) {
						CustomerAddressBean[] custadd_temp = (CustomerAddressBean[]) JsonToBeanConverter.convert(response.getBody(), CustomerAddressBean[].class);
						custadd = ArrayToListConverter.convertArrayToList(custadd_temp);
					}else if(response.getStatusCode()==HttpStatus.OK)
					{
						ResponseBean resBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(),
								ResponseBean.class);
						throw new BackEndDataException(resBean.getMessage());
					}

				}
				else {
					throw new RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED);
				}
			return custadd;

		}
		
		// POST Insertion
		public CustAddBean insertCustAdd( String name,String email,String flat,String streetAdddress,String landmark,String pincode,
			String city,String state,String country,String mobile1,String mobile2,String addressType) {
			RestTemplate restTemplate;
			key = headerKeyManagement.getKey(ConstantValues.AUTH_HEADER_KEY_DB_ID);
			ResponseEntity<String> response;
			UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.CUSTOMERADDRESS_POST);
			CustAddBean responseBean = null;
			//try {
				if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
					restTemplate = new RestTemplate(requestFacotry.validateAuthenticationSsl(urls.getTimeout()));
					headers.set(key.getName(), key.getValue());

					MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
					map.add(ConstantValues.NAME, name);
					map.add(ConstantValues.EMAIL,email);
					map.add(ConstantValues.FLAT,flat);
					map.add(ConstantValues.STREETADDRESS,streetAdddress);
					map.add(ConstantValues.LANDMARK,landmark);
					map.add(ConstantValues.PINCODE,pincode);
					map.add(ConstantValues.CITY,city);
					map.add(ConstantValues.STATE,state);
					map.add(ConstantValues.COUNTRY,country);
					map.add(ConstantValues.MOBILE1,mobile1);
					map.add(ConstantValues.MOBILE2,mobile2);
					map.add(ConstantValues.ADDRESSTYPE,addressType);
					HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
					System.out.println(request);
					response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
					System.out.println(response);
					if (response.getStatusCode() == HttpStatus.ACCEPTED) {
						responseBean = (CustAddBean) JsonToBeanConverter.convert(response.getBody(), CustAddBean.class);
					} else if (response.getStatusCode() == HttpStatus.FAILED_DEPENDENCY
							|| response.getStatusCode() == HttpStatus.BAD_REQUEST
							|| response.getStatusCode() == HttpStatus.UNAUTHORIZED) {
						throw new BackEndDataException(response.getBody());
					}
					else if(response.getStatusCode()==HttpStatus.OK)
					{
						ResponseBean resBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(),
								ResponseBean.class);
						throw new RestResponseException(resBean.getMessage());
					}
				} else {
					throw new RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED);
				}
			return responseBean;
		}
		
		// Put Insertion
		public ResponseBean UpdateCustAdd(String addressid,String name,String email,String flat,String streetAdddress,String landmark,String pincode,
				String city,String state,String country,String mobile1,String mobile2,String addressType)
		{
			RestTemplate restTemplate;
			key = headerKeyManagement.getKey(ConstantValues.AUTH_HEADER_KEY_DB_ID);
			ResponseEntity<String> response;
			UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.CUSTOMERADDRESS_PUT);
			ResponseBean responseBean = null;
			//try {
				if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
					restTemplate = new RestTemplate(requestFacotry.validateAuthenticationSsl(urls.getTimeout()));
					headers.set(key.getName(), key.getValue());

					MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
					map.add(ConstantValues.ADDRESSID, addressid);
					map.add(ConstantValues.NAME, name);
					map.add(ConstantValues.EMAIL,email);
					map.add(ConstantValues.FLAT,flat);
					map.add(ConstantValues.STREETADDRESS,streetAdddress);
					map.add(ConstantValues.LANDMARK,landmark);
					map.add(ConstantValues.PINCODE,pincode);
					map.add(ConstantValues.CITY,city);
					map.add(ConstantValues.STATE,state);
					map.add(ConstantValues.COUNTRY,country);
					map.add(ConstantValues.MOBILE1,mobile1);
					map.add(ConstantValues.MOBILE2,mobile2);
					map.add(ConstantValues.ADDRESSTYPE,addressType);
					HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

					response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
					if (response.getStatusCode() == HttpStatus.ACCEPTED) {
						responseBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(), ResponseBean.class);
					} else if (response.getStatusCode() == HttpStatus.FAILED_DEPENDENCY
							|| response.getStatusCode() == HttpStatus.BAD_REQUEST
							|| response.getStatusCode() == HttpStatus.UNAUTHORIZED) {
						throw new BackEndDataException(response.getBody());
					}
					else if(response.getStatusCode()==HttpStatus.OK)
					{
						ResponseBean resBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(),
								ResponseBean.class);
						throw new RestResponseException(resBean.getMessage());
					}
				} else {
					throw new RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED);
				}
			
			return responseBean;
		}
		
		public CustomerAddressBean getCustAddByID(String addressid) {
			RestTemplate restTemplate;
			key = headerKeyManagement.getKey(ConstantValues.AUTH_HEADER_KEY_DB_ID);
			ResponseEntity<String> response;
			UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.CUSTOMERADDRESS_GET_BY_ADDRESS);
			CustomerAddressBean ad = null;
				if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
					restTemplate = new RestTemplate(requestFacotry.validateAuthenticationSsl(urls.getTimeout()));
					headers.set(key.getName(), key.getValue());
					urls.setUrl(urls.getUrl() + ConstantValues.SLASH_TAG + addressid);
					HttpEntity<Object> request = new HttpEntity<>(headers);
					response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
					if (response.getStatusCode() == HttpStatus.ACCEPTED) {
						ad = (CustomerAddressBean) JsonToBeanConverter.convert(response.getBody(), CustomerAddressBean.class);
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
			return ad;
		}

}
