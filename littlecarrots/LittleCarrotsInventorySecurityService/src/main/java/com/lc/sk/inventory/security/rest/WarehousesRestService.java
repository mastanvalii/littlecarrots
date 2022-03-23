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
import com.lc.sk.inventory.security.beans.UrlBean;
import com.lc.sk.inventory.security.beans.Warehouses;
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

public class WarehousesRestService {

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

	public List<Warehouses> getAllWarehouses() {
		RestTemplate restTemplate;
		key = headerKeyManagement.getKey(ConstantValues.INVENTORY_API_ACCESS_KEY);
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.WAREHOUSES_GET);
		List<Warehouses> warehouses = null;
			if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
				restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
				headers.set(key.getName(), key.getValue());

				HttpEntity<Object> request = new HttpEntity<>(headers);
				response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
				if (response.getStatusCode() == HttpStatus.ACCEPTED) {
					Warehouses[] warehouse = (Warehouses[]) JsonToBeanConverter.convert(response.getBody(),
							Warehouses[].class);
					warehouses = ArrayToListConverter.convertArrayToList(warehouse);
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

		return warehouses;
	}

	public Warehouses getById(String warehouseid) {
		RestTemplate restTemplate;
		key = headerKeyManagement.getKey(ConstantValues.INVENTORY_API_ACCESS_KEY);
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.WAREHOUSES_GET);
		Warehouses warehouses = null;
		//try {
			if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
				restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
				headers.set(key.getName(), key.getValue());
				urls.setUrl(urls.getUrl() + ConstantValues.SLASH_TAG + warehouseid);
				HttpEntity<Object> request = new HttpEntity<>(headers);
				response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
				if (response.getStatusCode() == HttpStatus.ACCEPTED) {
					warehouses = (Warehouses) JsonToBeanConverter.convert(response.getBody(), Warehouses.class);
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
//		} catch (Exception e) {
//			ResponseBean resBean = (ResponseBean) JsonToBeanConverter.convert(StringTrimmer.trim(e.getMessage()),
//					ResponseBean.class);
//			if (resBean.getResponsecode() == HttpStatus.NOT_FOUND.value()) {
//				throw new DataNotFoundException(resBean.getMessage());
//			} else {
//				throw new RestResponseException(resBean.getMessage());
//			}
//		}

		return warehouses;
	}

	// insert
	public ResponseBean insertNewWarehouse(String warehousename, String contactpersonname, String contactphone,
			String email, String address, String pincode, String est, boolean status, String username, String whoupdated,
			String isocode) {
		RestTemplate restTemplate;
		key = headerKeyManagement.getKey(ConstantValues.INVENTORY_API_ACCESS_KEY);
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.WAREHOUSES_POST);
		ResponseBean responseBean = null;
		//try {
			if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
				restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
				headers.set(key.getName(), key.getValue());

				MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
				map.add(ConstantValues.WAREHOUSE_NAME, warehousename);
				map.add(ConstantValues.CONTACT_PERSON_NAME, contactpersonname);
				map.add(ConstantValues.CONTACT_PERSON_PHONE, contactphone);
				map.add(ConstantValues.WAREHOUSE_EMAIL_ADDRESS, email);
				map.add(ConstantValues.WAREHOUSE_ADDRESS, address);
				map.add(ConstantValues.WHAREHOUSE_PIN, pincode);
				map.add(ConstantValues.WAREHOUSE_EST, est);
				map.add(ConstantValues.WAREHOUSE_STATUS, status+"");
				map.add(ConstantValues.WHO_CREATED, username);
				map.add(ConstantValues.ISOCODE, isocode);
				map.add(ConstantValues.WHO_UPDATED, whoupdated);

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
				}else if (response.getStatusCode() == HttpStatus.FAILED_DEPENDENCY
						|| response.getStatusCode() == HttpStatus.BAD_REQUEST
						|| response.getStatusCode() == HttpStatus.UNAUTHORIZED) {
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

	// update
	public ResponseBean update(String warehouseid, String warehousename, String contactpersonname, String contactphone,
			String email, String address, String pincode, String est, String status, String username, String isocode) {
		RestTemplate restTemplate;
		key = headerKeyManagement.getKey(ConstantValues.INVENTORY_API_ACCESS_KEY);
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.WAREHOUSES_PUT);
		ResponseBean responseBean = null;
		//try {
			if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
				restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
				headers.set(key.getName(), key.getValue());

				MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
				map.add(ConstantValues.WAREHOUSE_ID, warehouseid);
				map.add(ConstantValues.WAREHOUSE_NAME, warehousename);
				map.add(ConstantValues.CONTACT_PERSON_NAME, contactpersonname);
				map.add(ConstantValues.CONTACT_PERSON_PHONE, contactphone);
				map.add(ConstantValues.WAREHOUSE_EMAIL_ADDRESS, email);
				map.add(ConstantValues.WAREHOUSE_ADDRESS, address);
				map.add(ConstantValues.WHAREHOUSE_PIN, pincode);
				map.add(ConstantValues.WAREHOUSE_EST, est);
				map.add(ConstantValues.WAREHOUSE_STATUS, status);
				map.add(ConstantValues.WHO_CREATED, username);
				map.add(ConstantValues.WHO_UPDATED, username);
				map.add(ConstantValues.ISOCODE, isocode);

				HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

				response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
				if (response.getStatusCode() == HttpStatus.ACCEPTED) {
					responseBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(), ResponseBean.class);
				} 
				else if(response.getStatusCode()==HttpStatus.OK)
				{
					ResponseBean resBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(),
							ResponseBean.class);
					throw new DataNotFoundException(resBean.getMessage());
				}
				else if(response.getStatusCode()==HttpStatus.OK)
				{
					ResponseBean resBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(),
							ResponseBean.class);
					throw new RestResponseException(resBean.getMessage());
				}else if (response.getStatusCode() == HttpStatus.FAILED_DEPENDENCY
						|| response.getStatusCode() == HttpStatus.BAD_REQUEST
						|| response.getStatusCode() == HttpStatus.UNAUTHORIZED) {
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
