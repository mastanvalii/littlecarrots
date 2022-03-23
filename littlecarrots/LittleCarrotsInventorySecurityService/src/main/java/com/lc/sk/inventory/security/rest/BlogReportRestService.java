package com.lc.sk.inventory.security.rest;

import java.io.IOException;
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
import org.springframework.web.multipart.MultipartFile;


import com.lc.sk.inventory.security.beans.Blogreport;
import com.lc.sk.inventory.security.beans.Key;
import com.lc.sk.inventory.security.beans.ResponseBean;
import com.lc.sk.inventory.security.beans.UrlBean;
import com.lc.sk.inventory.security.dao.HeaderKeyManagement;
import com.lc.sk.inventory.security.dao.ServiceUrlsDao;
import com.lc.sk.inventory.security.exceptions.subexceptions.BackEndDataException;
import com.lc.sk.inventory.security.exceptions.subexceptions.DataNotFoundException;
import com.lc.sk.inventory.security.exceptions.subexceptions.NullRequestReceivedException;
import com.lc.sk.inventory.security.exceptions.subexceptions.RestURLReaderException;
import com.lc.sk.inventory.security.factory.ServiceHttpRequestFactory;
import com.lc.sk.inventory.security.util.ArrayToListConverter;
import com.lc.sk.inventory.security.util.ConstantValues;
import com.lc.sk.inventory.security.util.JsonToBeanConverter;
import com.lc.sk.inventory.security.util.MultipartInputStreamFileResource;
import com.lc.sk.inventory.security.util.NextServiceURLMapping;
import com.lc.sk.inventory.security.util.UrlDetails;

@Component
public class BlogReportRestService {

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

	// Get All Blogreport
	public List<Blogreport> getAllBlogreport() {
		RestTemplate restTemplate;
		key = headerKeyManagement.getKey(ConstantValues.INVENTORY_API_ACCESS_KEY);
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.BLOGREPORT_GET);
		List<Blogreport> blogreport = null;
		if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
			restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
			headers = new HttpHeaders();
			headers.set(key.getName(), key.getValue());

			HttpEntity<Object> request = new HttpEntity<>(headers);
			response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
			if (response.getStatusCode() == HttpStatus.ACCEPTED) {
				Blogreport[] blogreport_temp = (Blogreport[]) JsonToBeanConverter.convert(response.getBody(),
						Blogreport[].class);
				blogreport = ArrayToListConverter.convertArrayToList(blogreport_temp);
			} else if (response.getStatusCode() == HttpStatus.OK) {
				ResponseBean resBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(),
						ResponseBean.class);
				throw new DataNotFoundException(resBean.getMessage());
			}
		} else {
			throw new RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED);
		}
		return blogreport;
	}

	// Details By id
	public Blogreport getById(String bid) {
		RestTemplate restTemplate;
		key = headerKeyManagement.getKey(ConstantValues.INVENTORY_API_ACCESS_KEY);
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.BLOGREPORT_GET);
		Blogreport blogreport = null;
		if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
			restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
			headers.set(key.getName(), key.getValue());
			urls.setUrl(urls.getUrl() + ConstantValues.SLASH_TAG + bid);
			HttpEntity<Object> request = new HttpEntity<>(headers);
			response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
			if (response.getStatusCode() == HttpStatus.ACCEPTED) {
				blogreport = (Blogreport) JsonToBeanConverter.convert(response.getBody(), Blogreport.class);
			} else if (response.getStatusCode() == HttpStatus.OK) {
				ResponseBean resBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(),
						ResponseBean.class);
				throw new DataNotFoundException(resBean.getMessage());
			}

		} else {
			throw new RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED);
		}

		return blogreport;
	}

	// Insertion
	public ResponseBean addBlogreport(String authorname, MultipartFile authorimage, String description) {
		RestTemplate restTemplate;
		key = headerKeyManagement.getKey(ConstantValues.INVENTORY_API_ACCESS_KEY);
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.BLOGREPORT_POST);
		ResponseBean responseBean = null;
		if (authorname.equals(ConstantValues.DEFAULT_STRING) || authorimage.isEmpty()
				|| description.equals(ConstantValues.DEFAULT_STRING)) {
			throw new NullRequestReceivedException(ConstantValues.RECEIVED_NULL_VALUES);
		} else {
		
		
		if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
			restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
			headers.set(key.getName(), key.getValue());

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add(ConstantValues.AUTHOR_NAME, authorname);
			
			try {
				map.add(ConstantValues.AUTHOR_IMAGE, new MultipartInputStreamFileResource(authorimage.getInputStream(), authorimage.getOriginalFilename()));
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			map.add(ConstantValues.DESCRIPTION, description);
			HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(map, headers);

			response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
			if (response.getStatusCode() == HttpStatus.ACCEPTED) {
				responseBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(), ResponseBean.class);
			} else if (response.getStatusCode() == HttpStatus.OK) {
				ResponseBean resBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(),
						ResponseBean.class);
				throw new BackEndDataException(resBean.getMessage());
			}
		} else {
			throw new RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED);
		}
		return responseBean;
	}
	}
	/*
	 * // Updation public ResponseBean update(String catid, String category, String
	 * gender) { key =
	 * headerKeyManagement.getKey(ConstantValues.INVENTORY_API_ACCESS_KEY); urls =
	 * serviceUrlsDao.getAccessUrl(UrlDetails.CATEGORIES_PUT); ResponseBean
	 * responseBean = null; if
	 * (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) { restTemplate = new
	 * RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
	 * headers.set(key.getName(), key.getValue());
	 * 
	 * MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
	 * map.add(ConstantValues.CAT_ID, catid); map.add(ConstantValues.CATEGORY,
	 * category); map.add(ConstantValues.GENDER, gender);
	 * 
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

	
}
