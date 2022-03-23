package com.lc.sk.inventory.security.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lc.sk.inventory.security.beans.Key;
import com.lc.sk.inventory.security.beans.ProductFullDetails;
import com.lc.sk.inventory.security.beans.ResponseBean;
import com.lc.sk.inventory.security.beans.UrlBean;
import com.lc.sk.inventory.security.dao.HeaderKeyManagement;
import com.lc.sk.inventory.security.dao.ServiceUrlsDao;
import com.lc.sk.inventory.security.exceptions.subexceptions.DataNotFoundException;
import com.lc.sk.inventory.security.exceptions.subexceptions.RestURLReaderException;
import com.lc.sk.inventory.security.factory.ServiceHttpRequestFactory;
import com.lc.sk.inventory.security.util.ArrayToListConverter;
import com.lc.sk.inventory.security.util.ConstantValues;
import com.lc.sk.inventory.security.util.JsonToBeanConverter;
import com.lc.sk.inventory.security.util.NextServiceURLMapping;
import com.lc.sk.inventory.security.util.UrlDetails;

@Component
public class ProductDetailsRestService {
	@Autowired
	private HeaderKeyManagement headerKeyManagement;

	@Autowired
	private ServiceUrlsDao serviceUrlsDao;

	@Autowired
	private ServiceHttpRequestFactory requestFacotry;

	//private UrlBean urls;
//	private RestTemplate restTemplate;
	private HttpHeaders headers = new HttpHeaders();
	private Key key;
	//private ResponseEntity<String> response;
// TODO Remove unused code found by UCDetector
// 	ObjectMapper jsondata = new ObjectMapper();
	
	
	public List<ProductFullDetails> getAllProductsDetails(){
		RestTemplate restTemplate;
	//	long x = System.currentTimeMillis();
	//	long a1= System.currentTimeMillis();
		key = headerKeyManagement.getKey(ConstantValues.INVENTORY_API_ACCESS_KEY);
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.PRODUCT_DETAILS_GET);
//		long a2 = System.currentTimeMillis();
//		System.err.println("Headerloading msec:"+(a2-a1));
		List<ProductFullDetails> productsfulldetails = null;
		if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
			restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
			headers.set(key.getName(), key.getValue());

			HttpEntity<Object> request = new HttpEntity<>(headers);

		//	System.out.println(request);
//		long a =System.currentTimeMillis();
			response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
			//long b =System.currentTimeMillis();
			//System.err.println("Hit msec:"+(b-a));
		//	System.out.println(response);
//			long c = System.currentTimeMillis();
			if (response.getStatusCode() == HttpStatus.ACCEPTED) {
				
				ProductFullDetails[] pd = (ProductFullDetails[])JsonToBeanConverter.convert(response.getBody(), ProductFullDetails[].class);
				productsfulldetails = ArrayToListConverter.convertArrayToList(pd);
//				long d = System.currentTimeMillis();
//				System.err.println("conve msec:"+(d-c));
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
		//long y = System.currentTimeMillis();
		//System.err.println("Total Execution:"+(y-x));
		return productsfulldetails;
	}
	
	
	public ProductFullDetails getProductsDetails(String productid){
		RestTemplate restTemplate;
		key = headerKeyManagement.getKey(ConstantValues.INVENTORY_API_ACCESS_KEY);
		UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.PRODUCT_DETAILS_GET);
		 ResponseEntity<String> response;
		ProductFullDetails productsdetails = null;
		if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
			restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
			headers.set(key.getName(), key.getValue());

			HttpEntity<Object> request = new HttpEntity<>(headers);
			response = restTemplate.exchange(urls.getUrl()+"/"+productid, urls.getMethod(), request, String.class);
			if (response.getStatusCode() == HttpStatus.ACCEPTED) {
				productsdetails = (ProductFullDetails)JsonToBeanConverter.convert(response.getBody(), ProductFullDetails.class);
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
		return productsdetails;
	}
	
	public ProductFullDetails getProductsDetailsbystatus(String productid,String productstatus){
		RestTemplate restTemplate;
		key = headerKeyManagement.getKey(ConstantValues.INVENTORY_API_ACCESS_KEY);
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.PRODUCT_DETAILS_GET);
		ProductFullDetails productsdetails = null;
		if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
			restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
			headers.set(key.getName(), key.getValue());

			HttpEntity<Object> request = new HttpEntity<>(headers);
			response = restTemplate.exchange(urls.getUrl()+"/"+productid+"/"+ productstatus,urls.getMethod(), request, String.class);
			if (response.getStatusCode() == HttpStatus.ACCEPTED) {
				productsdetails = (ProductFullDetails)JsonToBeanConverter.convert(response.getBody(), ProductFullDetails.class);
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
		return productsdetails;
	}
	
	
}
