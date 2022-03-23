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
import com.lc.sk.inventory.security.beans.Sizes;
import com.lc.sk.inventory.security.beans.SizesDetails;
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
public class SizesRestService {

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
	public ResponseBean insertSizes(String ageid,String gender, String sizeno, String height, String weight, String chest,
			String waist, String hip,String subcatid,String isocode) {
		RestTemplate restTemplate;
		key = headerKeyManagement.getKey(ConstantValues.INVENTORY_API_ACCESS_KEY);
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.SIZES_POST);
		ResponseBean responseBean = null;
		//try {
			if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
				restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
				headers.set(key.getName(), key.getValue());

				MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
				map.add(ConstantValues.AGE_ID, ageid);
				map.add(ConstantValues.GENDER, gender);
				map.add(ConstantValues.SIZE_NO, sizeno);
				map.add(ConstantValues.HEIGHT, height);
				map.add(ConstantValues.WEIGHT, weight);
				map.add(ConstantValues.CHEST, chest);
				map.add(ConstantValues.WAIST, waist);
				map.add(ConstantValues.HIP, hip);
				map.add(ConstantValues.SUBCAT_ID, subcatid);
				map.add(ConstantValues.ISOCODE,isocode);
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
//			if (response.getStatusCode() == HttpStatus.FAILED_DEPENDENCY
//					|| response.getStatusCode() == HttpStatus.BAD_REQUEST
//					|| response.getStatusCode() == HttpStatus.UNAUTHORIZED) {
//				throw new BackEndDataException(resBean.getMessage());
//			} else {
//				throw new RestResponseException(resBean.getMessage());
//			}
//		}
		return responseBean;
	}

	// Updation
	public ResponseBean updatesize(String sizeid, String ageid,String gender, String sizeno, String height, String weight,
			String chest, String waist, String hip,String subcatid,String isocode) {
		RestTemplate restTemplate;
		key = headerKeyManagement.getKey(ConstantValues.INVENTORY_API_ACCESS_KEY);
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.SIZES_PUT);
		ResponseBean responseBean = null;
		//try {
			if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
				restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
				headers.set(key.getName(), key.getValue());

				MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
				map.add(ConstantValues.SIZE_ID, sizeid);
				map.add(ConstantValues.AGE_ID, ageid);
				map.add(ConstantValues.GENDER, gender);
				map.add(ConstantValues.SIZE_NO, sizeno);
				map.add(ConstantValues.HEIGHT, height);
				map.add(ConstantValues.WEIGHT, weight);
				map.add(ConstantValues.CHEST, chest);
				map.add(ConstantValues.WAIST, waist);
				map.add(ConstantValues.HIP, hip);
				map.add(ConstantValues.SUBCAT_ID, subcatid);
				map.add(ConstantValues.ISOCODE,isocode);

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

	// Details By id
	public Sizes getsizebyid(String sizeid) {
		RestTemplate restTemplate;
		key = headerKeyManagement.getKey(ConstantValues.INVENTORY_API_ACCESS_KEY);
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.SIZES_GET);
		Sizes sizes = null;
		//try {
			if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
				restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
				headers.set(key.getName(), key.getValue());
				urls.setUrl(urls.getUrl() + ConstantValues.SLASH_TAG + sizeid);
				
				HttpEntity<Object> request = new HttpEntity<>(headers);
				response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
				
				if (response.getStatusCode() == HttpStatus.ACCEPTED) {
					sizes = (Sizes) JsonToBeanConverter.convert(response.getBody(), Sizes.class);
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

		return sizes;
	}

	// Get All Sizes
	public List<Sizes> getAllSizes() {
		RestTemplate restTemplate;
		key = headerKeyManagement.getKey(ConstantValues.INVENTORY_API_ACCESS_KEY);
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.SIZES_GET);
		List<Sizes> sizes = null;
	//	try {
			if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
				restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
				headers.set(key.getName(), key.getValue());

				HttpEntity<Object> request = new HttpEntity<>(headers);
				response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
				if (response.getStatusCode() == HttpStatus.ACCEPTED) {
					Sizes[] sizes_temp = (Sizes[]) JsonToBeanConverter.convert(response.getBody(), Sizes[].class);
					sizes = ArrayToListConverter.convertArrayToList(sizes_temp);
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
		return sizes;
	}

	// Get All Sizes List
			public List<SizesDetails> getAllSizesList() {
				RestTemplate restTemplate;
				key = headerKeyManagement.getKey(ConstantValues.INVENTORY_API_ACCESS_KEY);
				ResponseEntity<String> response;
				UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.SIZES_LIST_GET);
				List<SizesDetails> sizes = null;
					if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
						restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
						headers.set(key.getName(), key.getValue());

						HttpEntity<Object> request = new HttpEntity<>(headers);
						response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
						if (response.getStatusCode() == HttpStatus.ACCEPTED) {
							SizesDetails[] sizes_temp = (SizesDetails[]) JsonToBeanConverter.convert(response.getBody(), SizesDetails[].class);
							sizes = ArrayToListConverter.convertArrayToList(sizes_temp);
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
				return sizes;
			}
}
