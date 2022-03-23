/**
 * 
 */
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
import com.lc.sk.inventory.security.beans.Seller;
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

/**
 * @author Mastanvali Shaik
 * LittleCarrotsInventorySecurityService
 * 2020
 */
@Component
public class SellerRestService {
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
	
	
	//URL ID 107
	public List<Seller> getAllSellerDetails()
	{
		RestTemplate restTemplate;
		key = headerKeyManagement.getKey(ConstantValues.AUTH_HEADER_KEY_DB_ID);
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.SELLER_SERVICE_URL_GET_ID);
		List<Seller> sellers = null;

			if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
				restTemplate = new RestTemplate(requestFacotry.validateAuthenticationSsl(urls.getTimeout()));
				headers.set(key.getName(), key.getValue());

				HttpEntity<Object> request = new HttpEntity<>(headers);
				response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
			
				if (response.getStatusCode() == HttpStatus.ACCEPTED) {
					Seller[] seller = (Seller[]) JsonToBeanConverter.convert(response.getBody(), Seller[].class);
					sellers = ArrayToListConverter.convertArrayToList(seller);
				}
				else if(response.getStatusCode()==HttpStatus.OK)
				{
					ResponseBean resBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(),
							ResponseBean.class);
					throw new RestResponseException(resBean.getMessage());
				}
				else if(response.getStatusCode() == HttpStatus.NOT_FOUND)
				{
					throw new DataNotFoundException(response.getBody());
				}
			}
			else
			{
				throw new RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED);
			}

		return sellers;
	}
	
	//URL ID 107 with path value
	public Seller getSellerById(Long sellerid)
	{
		RestTemplate restTemplate;
		key = headerKeyManagement.getKey(ConstantValues.AUTH_HEADER_KEY_DB_ID);
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.SELLER_SERVICE_URL_GET_ID);
		Seller seller = null;
	//	try 
		{
			if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
				restTemplate = new RestTemplate(requestFacotry.validateAuthenticationSsl(urls.getTimeout()));
				headers.set(key.getName(), key.getValue());

				HttpEntity<Object> request = new HttpEntity<>(headers);
				urls.setUrl(urls.getUrl() + ConstantValues.SLASH_TAG + sellerid);
				
				response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
				
				if (response.getStatusCode() == HttpStatus.ACCEPTED) {
					 seller = (Seller) JsonToBeanConverter.convert(response.getBody(), Seller.class);
				}
				else if(response.getStatusCode()==HttpStatus.OK)
				{			
					//ResponseBean resBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(),
						//	ResponseBean.class);
					throw new DataNotFoundException(response.getBody());
				}
			}
			else
			{
				throw new RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED);
			}
//		}
//		catch(Exception e)
//		{
//			throw new RestResponseException(e.getMessage());
//		}
		return seller;		
	}
	}
	
	//108
	public ResponseBean insertSeller(String sellercompanyname,String sellername,
			String phonenumber,String email,String address,String isocode,boolean status)
	{
		RestTemplate restTemplate;
		key = headerKeyManagement.getKey(ConstantValues.AUTH_HEADER_KEY_DB_ID);
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.SELLER_SERVICE_URL_POST_ID);
		ResponseBean responseBean = null;
		//try {
			if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
				restTemplate = new RestTemplate(requestFacotry.validateAuthenticationSsl(urls.getTimeout()));
				headers.set(key.getName(), key.getValue());

				MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
				map.add(ConstantValues.SELLER_COMPANY_NAME, sellercompanyname);
				map.add(ConstantValues.SELLER_NAME, sellername);
				map.add(ConstantValues.PHONE_NUMBER, phonenumber);
				map.add(ConstantValues.EMAIL, email);
				map.add(ConstantValues.ADDRESS, address);
				map.add(ConstantValues.ISOCODE, isocode);
				map.add(ConstantValues.STATUS, status+"");

				HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

				response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
				if (response.getStatusCode() == HttpStatus.ACCEPTED) {
					responseBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(), ResponseBean.class);
				}
				else if(response.getStatusCode()==HttpStatus.OK)
				{
					ResponseBean resBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(),
							ResponseBean.class);
					throw new RestResponseException(resBean.getMessage());
				}else if (response.getStatusCode() == HttpStatus.FAILED_DEPENDENCY ||response.getStatusCode() == HttpStatus.BAD_REQUEST || response.getStatusCode() == HttpStatus.UNAUTHORIZED) {
					throw new BackEndDataException(response.getBody());
				}
			} else {
				throw new RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED);
			}
//		} catch (Exception e) {
//			throw new RestResponseException(e.getMessage());
//		}
		return responseBean;
	}
	
	//109
	public ResponseBean updateSeller(String sellerid,String sellercompanyname,String sellername,
			String phonenumber,String email,String address,String isocode,String status)
	{
		RestTemplate restTemplate;
		key = headerKeyManagement.getKey(ConstantValues.AUTH_HEADER_KEY_DB_ID);
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.SELLER_SERVICE_URL_PUT_ID);
		ResponseBean responseBean = null;
	//	try {
			if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
				restTemplate = new RestTemplate(requestFacotry.validateAuthenticationSsl(urls.getTimeout()));
				headers.set(key.getName(), key.getValue());

				MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
				map.add(ConstantValues.SELLER_ID, sellerid);
				map.add(ConstantValues.SELLER_COMPANY_NAME, sellercompanyname);
				map.add(ConstantValues.SELLER_NAME, sellername);
				map.add(ConstantValues.PHONE_NUMBER, phonenumber);
				map.add(ConstantValues.EMAIL, email);
				map.add(ConstantValues.ADDRESS, address);
				map.add(ConstantValues.ISOCODE, isocode);
				map.add(ConstantValues.STATUS, status);

				HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

				response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
				if (response.getStatusCode() == HttpStatus.ACCEPTED) {
					responseBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(), ResponseBean.class);
				} 
				else if(response.getStatusCode()==HttpStatus.OK)
				{
					ResponseBean resBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(),
							ResponseBean.class);
					throw new RestResponseException(resBean.getMessage());
				}else if (response.getStatusCode() == HttpStatus.FAILED_DEPENDENCY ||response.getStatusCode() == HttpStatus.BAD_REQUEST || response.getStatusCode() == HttpStatus.UNAUTHORIZED) {
					throw new BackEndDataException(response.getBody());
				}
			} else {
				throw new RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED);
			}
//		} catch (Exception e) {
//			throw new RestResponseException(e.getMessage());
//		}
		return responseBean;
	}
	
	
}
