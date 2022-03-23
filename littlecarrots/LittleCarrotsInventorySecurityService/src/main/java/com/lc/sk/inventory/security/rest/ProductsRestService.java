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
import com.lc.sk.inventory.security.beans.ProductFullDetails;
import com.lc.sk.inventory.security.beans.Products;
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
public class ProductsRestService {

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
	public ResponseBean insert(String descriptionid, String batchid, String genderid, String catid, String subcatid,
			String seasonid, String occasionid, String ageid, String materialid, String colorid, String custid,
			boolean status,String title,String subtitle) {
		RestTemplate restTemplate;
		key = headerKeyManagement.getKey(ConstantValues.INVENTORY_API_ACCESS_KEY);
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.PRODUCTS_POST);
		ResponseBean responseBean = null;
		//try {
			if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
				restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
				headers.set(key.getName(), key.getValue());

				MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
				map.add(ConstantValues.DESCRIPTION_ID, descriptionid);
				map.add(ConstantValues.BATCH_ID, batchid);
				map.add(ConstantValues.GENDER_ID, genderid);
				map.add(ConstantValues.CATEGORY_ID, catid);
				map.add(ConstantValues.SUBCATID, subcatid);
				map.add(ConstantValues.SEASON_ID, seasonid);
				map.add(ConstantValues.OCCASION_ID, occasionid);
				map.add(ConstantValues.AGE_ID, ageid);
				map.add(ConstantValues.MATERIAL_ID, materialid);
				map.add(ConstantValues.COLOR_ID, colorid);
				map.add(ConstantValues.CUSTOMER_ID, custid);
				map.add(ConstantValues.STATUS, status+"");
				map.add(ConstantValues.TITLE, title);
				map.add(ConstantValues.SUB_TITLE, subtitle);
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
//		} catch (Exception e) {
//			ResponseBean resBean = (ResponseBean) JsonToBeanConverter.convert(StringTrimmer.trim(e.getMessage()),
//					ResponseBean.class);
//			if (resBean.getResponsecode() == HttpStatus.NOT_FOUND.value()) {
//				throw new BackEndDataException(resBean.getMessage());
//			} else {
//				throw new RestResponseException(resBean.getMessage());
//			}
//		}
		return responseBean;
	}

	// Updation
	public ResponseBean update(String productid, String descriptionid, String batchid, String genderid, String catid,
			String subcatid, String seasonid, String occasionid, String ageid, String materialid, String colorid,
			String custid, boolean status,String title,String subtitle) {
		RestTemplate restTemplate;
		key = headerKeyManagement.getKey(ConstantValues.INVENTORY_API_ACCESS_KEY);
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.PRODUCTS_PUT);
		ResponseBean responseBean = null;
		//try {
			if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
				restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
				headers.set(key.getName(), key.getValue());

				MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
				map.add(ConstantValues.PRODUCT_ID, productid);
				map.add(ConstantValues.DESCRIPTION_ID, descriptionid);
				map.add(ConstantValues.BATCH_ID, batchid);
				map.add(ConstantValues.GENDER_ID, genderid);
				map.add(ConstantValues.CATEGORY_ID, catid);
				map.add(ConstantValues.SUBCATID, subcatid);
				map.add(ConstantValues.SEASON_ID, seasonid);
				map.add(ConstantValues.OCCASION_ID, occasionid);
				map.add(ConstantValues.AGE_ID, ageid);
				map.add(ConstantValues.MATERIAL_ID, materialid);
				map.add(ConstantValues.COLOR_ID, colorid);
				map.add(ConstantValues.CUSTOMER_ID, custid);
				map.add(ConstantValues.STATUS, status+"");
				map.add(ConstantValues.TITLE, title);
				map.add(ConstantValues.SUB_TITLE, subtitle);
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
		return responseBean;
	}

	// Put
	// Enabling Batch Status
	public ResponseBean enableBatchStatus(String productid, boolean status) {
		RestTemplate restTemplate;
		key = headerKeyManagement.getKey(ConstantValues.INVENTORY_API_ACCESS_KEY);
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.PRODUCTS_PUT);
		ResponseBean responseBean = null;
		//try {
			if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
				restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
				headers.set(key.getName(), key.getValue());

				HttpEntity<Object> request = new HttpEntity<>(headers);
				urls.setUrl(urls.getUrl() + ConstantValues.SLASH_TAG + productid + ConstantValues.SLASH_TAG + status);
				response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
				if (response.getStatusCode() == HttpStatus.ACCEPTED) {
					responseBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(), ResponseBean.class);
				} else if (response.getStatusCode() == HttpStatus.FAILED_DEPENDENCY) {
					throw new RestResponseException(response.getBody());
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
//			throw new RestResponseException(e.getMessage());
//		}

		return responseBean;
	}

	// Details By id
	public Products getbyid(String productid) {
		RestTemplate restTemplate;
		key = headerKeyManagement.getKey(ConstantValues.INVENTORY_API_ACCESS_KEY);
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.PRODUCTS_GET);
		Products products = null;
		//try {
			if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
				restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
				headers.set(key.getName(), key.getValue());
				urls.setUrl(urls.getUrl() + ConstantValues.SLASH_TAG + productid);

				HttpEntity<Object> request = new HttpEntity<>(headers);
				response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
				if (response.getStatusCode() == HttpStatus.ACCEPTED) {
					products = (Products) JsonToBeanConverter.convert(response.getBody(), Products.class);
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

		return products;
	}

	// GetAll
	public List<Products> getall() {
		RestTemplate restTemplate;
		key = headerKeyManagement.getKey(ConstantValues.INVENTORY_API_ACCESS_KEY);
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.PRODUCTS_GET);
		List<Products> products = null;
		//try {
			if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
				restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
				headers.set(key.getName(), key.getValue());

				HttpEntity<Object> request = new HttpEntity<>(headers);
				response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
				if (response.getStatusCode() == HttpStatus.ACCEPTED) {
					Products[] products_temp = (Products[]) JsonToBeanConverter.convert(response.getBody(),
							Products[].class);
					products = ArrayToListConverter.convertArrayToList(products_temp);
				}
					else if(response.getStatusCode()==HttpStatus.OK)
					{
						ResponseBean resBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(),
								ResponseBean.class);
						throw new DataNotFoundException(resBean.getMessage());
					}
				}
			else {
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
		return products;
	}
	
	public String getProductCount() {
		RestTemplate restTemplate;
		key = headerKeyManagement.getKey(ConstantValues.INVENTORY_API_ACCESS_KEY);
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.PRODUCT_COUNT);
	//	Long count = null;
		//try {
			if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
				restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
				headers.set(key.getName(), key.getValue());

				HttpEntity<Object> request = new HttpEntity<>(headers);
				response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
				if (response.getStatusCode() == HttpStatus.ACCEPTED) {
				
				//	Long count_temp = (Long) JsonToBeanConverter.convert(response.getBody(),Long.class);
				return response.getBody();
				//	count = count_temp;
				}
					else if(response.getStatusCode()==HttpStatus.OK)
					{
						ResponseBean resBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(),
								ResponseBean.class);
						throw new DataNotFoundException(resBean.getMessage());
					}
				}
			else {
				throw new RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED);
			}
return null;
	//	return count;
	}
	
	public String getOutOfStock() {
		RestTemplate restTemplate;
		key = headerKeyManagement.getKey(ConstantValues.INVENTORY_API_ACCESS_KEY);
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.OUT_OF_STOCK);
		//Long outofstock = null;
		//try {
			if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
				restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
				headers.set(key.getName(), key.getValue());

				HttpEntity<Object> request = new HttpEntity<>(headers);
				response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
				if (response.getStatusCode() == HttpStatus.ACCEPTED) {
					
				//	Long count_temp = (Long) JsonToBeanConverter.convert(response.getBody(),Long.class);
					return response.getBody();
				//	outofstock = count_temp;
				}
					else if(response.getStatusCode()==HttpStatus.OK)
					{
						ResponseBean resBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(),
								ResponseBean.class);
						throw new DataNotFoundException(resBean.getMessage());
					}
				}
			else {
				throw new RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED);
			}
			return null;

	//	return outofstock;
	}
	
	public String getLowStock() {
		RestTemplate restTemplate;
		key = headerKeyManagement.getKey(ConstantValues.INVENTORY_API_ACCESS_KEY);
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.LOW_STOCK);
		//Long lowstock = null;
		//try {
			if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
				restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
				headers.set(key.getName(), key.getValue());

				HttpEntity<Object> request = new HttpEntity<>(headers);
				response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
				if (response.getStatusCode() == HttpStatus.ACCEPTED) {
					
					//Long count_temp = (Long) JsonToBeanConverter.convert(response.getBody(),Long.class);
					return response.getBody();
					//lowstock = count_temp;
				}
					else if(response.getStatusCode()==HttpStatus.OK)
					{
						ResponseBean resBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(),
								ResponseBean.class);
						throw new DataNotFoundException(resBean.getMessage());
					}
				}
			else {
				throw new RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED);
			}
		return null;
		//return lowstock;
	}
	
	// Details By categories,season & occasion
		public List<ProductFullDetails> getbycategories(String catid,String season,String occasion) {
			RestTemplate restTemplate;
			key = headerKeyManagement.getKey(ConstantValues.INVENTORY_API_ACCESS_KEY);
			ResponseEntity<String> response;
			UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.PRODUCTS_GET);
			List<ProductFullDetails> products = null;
			//try {
				if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
					restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
					headers.set(key.getName(), key.getValue());
					urls.setUrl(urls.getUrl() + ConstantValues.SLASH_TAG + catid + ConstantValues.SLASH_TAG + season +ConstantValues.SLASH_TAG + occasion);

					HttpEntity<Object> request = new HttpEntity<>(headers);
					response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
					if (response.getStatusCode() == HttpStatus.ACCEPTED) {
						System.out.println("---------------hi------------");
						ProductFullDetails[] products_temp=(ProductFullDetails[])JsonToBeanConverter.convert(response.getBody(), ProductFullDetails[].class);
						
						products = ArrayToListConverter.convertArrayToList(products_temp);
						System.out.println("------"+products);
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
			return products;
		}
		public List<ProductFullDetails> getByGender(String genderid) {
			RestTemplate restTemplate;
			key = headerKeyManagement.getKey(ConstantValues.INVENTORY_API_ACCESS_KEY);
			ResponseEntity<String> response;
			UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.SELECTEDPRODUCTS_GENDER_GET);
			List<ProductFullDetails> products = null;
			//try {
				if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
					restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
					headers.set(key.getName(), key.getValue());
					urls.setUrl(urls.getUrl() + ConstantValues.SLASH_TAG + genderid);

					HttpEntity<Object> request = new HttpEntity<>(headers);
					response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
					if (response.getStatusCode() == HttpStatus.ACCEPTED) {
						System.out.println("---------------hi------------");
						ProductFullDetails[] products_temp=(ProductFullDetails[])JsonToBeanConverter.convert(response.getBody(), ProductFullDetails[].class);
						
						products = ArrayToListConverter.convertArrayToList(products_temp);
						System.out.println("------"+products);
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
			return products;
		}
		
		public List<ProductFullDetails> getByCOLLECTIONPRODINFO(String genderid) {
			RestTemplate restTemplate;
			key = headerKeyManagement.getKey(ConstantValues.INVENTORY_API_ACCESS_KEY);
			ResponseEntity<String> response;
			UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.COLLECTION_PRODUCT_INFO_GET);
			List<ProductFullDetails> products = null;
			//try {
				if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
					restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
					headers.set(key.getName(), key.getValue());
					urls.setUrl(urls.getUrl() + ConstantValues.SLASH_TAG + genderid);
					HttpEntity<Object> request = new HttpEntity<>(headers);
					response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
					if (response.getStatusCode() == HttpStatus.ACCEPTED) {
						ProductFullDetails[] products_temp=(ProductFullDetails[])JsonToBeanConverter.convert(response.getBody(), ProductFullDetails[].class);
						products = ArrayToListConverter.convertArrayToList(products_temp);
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
			return products;
		}

		
		public List<ProductFullDetails> getByPRODUCTSGender(String genderid) {
			RestTemplate restTemplate;
			key = headerKeyManagement.getKey(ConstantValues.INVENTORY_API_ACCESS_KEY);
			ResponseEntity<String> response;
			UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.SELECTED_PRODUCTS_MULTISELECT);
			List<ProductFullDetails> products = null;
			//try {
				if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
					restTemplate = new RestTemplate(requestFacotry.getClientHttpRequestFactory(urls.getTimeout()));
					headers.set(key.getName(), key.getValue());
					urls.setUrl(urls.getUrl() + ConstantValues.SLASH_TAG + genderid);

					HttpEntity<Object> request = new HttpEntity<>(headers);
					response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
					if (response.getStatusCode() == HttpStatus.ACCEPTED) {
						ProductFullDetails[] products_temp=(ProductFullDetails[])JsonToBeanConverter.convert(response.getBody(), ProductFullDetails[].class);
						products = ArrayToListConverter.convertArrayToList(products_temp);
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
			return products;
		}

		public List<ProductFullDetails> getallPRODUCTSFORSIMILARPRODUCTS() {
			RestTemplate restTemplate;
			key = headerKeyManagement.getKey(ConstantValues.INVENTORY_API_ACCESS_KEY);
			ResponseEntity<String> response;
			UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.SIMILAR_PRODUCTS_MULTISELECT);
			List<ProductFullDetails> products = null;
			//try {
				if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
					restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
					headers.set(key.getName(), key.getValue());

					HttpEntity<Object> request = new HttpEntity<>(headers);
					response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
					if (response.getStatusCode() == HttpStatus.ACCEPTED) {
						ProductFullDetails[] products_temp = (ProductFullDetails[]) JsonToBeanConverter.convert(response.getBody(),
								ProductFullDetails[].class);
						products = ArrayToListConverter.convertArrayToList(products_temp);
					}
						else if(response.getStatusCode()==HttpStatus.OK)
						{
							ResponseBean resBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(),
									ResponseBean.class);
							throw new DataNotFoundException(resBean.getMessage());
						}
					}
				else {
					throw new RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED);
				}

		
			return products;
		}

}

