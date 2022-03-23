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

import com.lc.sk.inventory.security.beans.CollectionSalePackBean;
import com.lc.sk.inventory.security.beans.Coupons;
import com.lc.sk.inventory.security.beans.Key;
import com.lc.sk.inventory.security.beans.ResponseBean;
import com.lc.sk.inventory.security.beans.UrlBean;
import com.lc.sk.inventory.security.dao.HeaderKeyManagement;
import com.lc.sk.inventory.security.dao.ServiceUrlsDao;
import com.lc.sk.inventory.security.exceptions.subexceptions.BackEndDataException;
import com.lc.sk.inventory.security.exceptions.subexceptions.CouponServiceException;
import com.lc.sk.inventory.security.exceptions.subexceptions.RestResponseException;
import com.lc.sk.inventory.security.exceptions.subexceptions.RestURLReaderException;
import com.lc.sk.inventory.security.factory.ServiceHttpRequestFactory;
import com.lc.sk.inventory.security.util.ArrayToListConverter;
import com.lc.sk.inventory.security.util.ConstantValues;
import com.lc.sk.inventory.security.util.JsonToBeanConverter;
import com.lc.sk.inventory.security.util.NextServiceURLMapping;
import com.lc.sk.inventory.security.util.UrlDetails;

@Component
public class CouponsRestService {

	@Autowired
	private HeaderKeyManagement headerKeyManagement;

	@Autowired
	private ServiceUrlsDao serviceUrlsDao;

	@Autowired
	private ServiceHttpRequestFactory requestFacotry;
	
	private UrlBean urls;
	private Key key;
	private HttpHeaders headers = new HttpHeaders();
	
	public ResponseBean insertNewCoupon(Coupons coupons) {
		RestTemplate restTemplate = null;
		key = headerKeyManagement.getKey(ConstantValues.ORDER_API_ACCESS_KEY);
		ResponseEntity<String> response = null;
		UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.COUPON_POST);
		ResponseBean reply = null;
		System.err.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		System.err.println(urls.getUrl());
		System.err.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		headers = new HttpHeaders();
		if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
			restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
			headers.set(key.getName(), key.getValue());
			MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
			map.add("coupon", coupons.getCoupon());
			map.add("startdate", coupons.getStartdate()+"");
			map.add("enddate", coupons.getEnddate()+"");
			map.add("discount", coupons.getDiscount()+"");
			map.add("minbill", coupons.getMinbill()+"");
			map.add("title", coupons.getTitle());
			HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
			response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
			if (response.getStatusCode() == HttpStatus.ACCEPTED) {
				reply = (ResponseBean) JsonToBeanConverter.convert(response.getBody(), ResponseBean.class);
			}
			/*else if (response.getStatusCode() == HttpStatus.FAILED_DEPENDENCY
					|| response.getStatusCode() == HttpStatus.BAD_REQUEST
					|| response.getStatusCode() == HttpStatus.UNAUTHORIZED) {
				throw new BackEndDataException(response.getBody());
			} */
			else if(response.getStatusCode()==HttpStatus.OK)
			{
				ResponseBean resBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(),
						ResponseBean.class);
				throw new RestResponseException(resBean.getMessage());
			}
		}else {
			throw new RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED);
		}
		return reply;
	}
	
	public List<Coupons> getAllCoupons(){
		RestTemplate restTemplate;
		key = headerKeyManagement.getKey(ConstantValues.ORDER_API_ACCESS_KEY);
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.COUPON_GET);
		List<Coupons> coupons = null;
		if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
			restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
			headers.set(key.getName(), key.getValue());
			urls.setUrl(urls.getUrl());
			HttpEntity<Object> request = new HttpEntity<>(headers);				
			response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);				
			if (response.getStatusCode() == HttpStatus.ACCEPTED) {
				Coupons[] temp = (Coupons[])JsonToBeanConverter.convert(response.getBody(), Coupons[].class);
				coupons = ArrayToListConverter.convertArrayToList(temp);
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
		return coupons;
		
	}
	
	public Coupons verify(String couponcode, String customerid, String totalamount) {
		RestTemplate restTemplate;
		key = headerKeyManagement.getKey(ConstantValues.ORDER_API_ACCESS_KEY);
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.COUPON_GET);
		Coupons coupons = null;
		if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
			restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
			headers.set(key.getName(), key.getValue());
			urls.setUrl(urls.getUrl()+"/"+couponcode+"/"+customerid+"/"+totalamount);
			HttpEntity<Object> request = new HttpEntity<>(headers);				
			response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);				
			if (response.getStatusCode() == HttpStatus.ACCEPTED) {
				coupons = (Coupons)JsonToBeanConverter.convert(response.getBody(), Coupons.class);
			}else if(response.getStatusCode()==HttpStatus.OK)
			{
				ResponseBean resBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(),
						ResponseBean.class);
				System.err.println("-----------------------------------------------");
				System.err.println(resBean);
				System.err.println("-----------------------------------------------");
				throw new CouponServiceException(resBean.getMessage());
			}
		}
		else {
			throw new RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED);
		}
		return coupons;
	}
	
	
	public void updateAppliedCoupon(String couponid, String customerid) {
		RestTemplate restTemplate;
		key = headerKeyManagement.getKey(ConstantValues.ORDER_API_ACCESS_KEY);
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.COUPON_PUT);
		Coupons coupons = null;
		if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
			restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
			headers.set(key.getName(), key.getValue());
			urls.setUrl(urls.getUrl()+"/"+couponid+"/"+customerid);
			HttpEntity<Object> request = new HttpEntity<>(headers);				
			restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);				
		}
		else {
			throw new RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED);
		}
	}
	
}
