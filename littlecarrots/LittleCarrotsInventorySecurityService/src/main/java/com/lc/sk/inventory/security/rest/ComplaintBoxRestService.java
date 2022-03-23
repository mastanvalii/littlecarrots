package com.lc.sk.inventory.security.rest;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.lc.sk.inventory.security.beans.ComplaintboxBean;
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
import com.lc.sk.inventory.security.util.MultipartInputStreamFileResource;
import com.lc.sk.inventory.security.util.NextServiceURLMapping;
import com.lc.sk.inventory.security.util.UrlDetails;

@Component
public class ComplaintBoxRestService {

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
	
	public List<ComplaintboxBean> getAllComplaints(){
		RestTemplate restTemplate;
		key = headerKeyManagement.getKey(ConstantValues.ORDER_API_ACCESS_KEY);
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.COMPLAINT_GET);
		List<ComplaintboxBean> boxBean = null;
		if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
			restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
			headers = new HttpHeaders();
			headers.set(key.getName(), key.getValue());

			HttpEntity<Object> request = new HttpEntity<>(headers);
			System.out.println(request);
			response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
			if (response.getStatusCode() == HttpStatus.ACCEPTED) {
				ComplaintboxBean[] collection_temp = (ComplaintboxBean[]) JsonToBeanConverter.convert(response.getBody(), ComplaintboxBean[].class);
				boxBean = ArrayToListConverter.convertArrayToList(collection_temp);
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

	return boxBean;
	}
	
	public ComplaintboxBean getAllComplaintsById(long id){
		RestTemplate restTemplate;
		key = headerKeyManagement.getKey(ConstantValues.ORDER_API_ACCESS_KEY);
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.COMPLAINT_GET);
		ComplaintboxBean boxBean = null;
		if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
			restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
			headers = new HttpHeaders();
			headers.set(key.getName(), key.getValue());
			urls.setUrl(urls.getUrl() + ConstantValues.SLASH_TAG + id);
			HttpEntity<Object> request = new HttpEntity<>(headers);
			System.out.println(request);
			response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
			if (response.getStatusCode() == HttpStatus.ACCEPTED) {
				boxBean = (ComplaintboxBean) JsonToBeanConverter.convert(response.getBody(), ComplaintboxBean.class);
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

	return boxBean;
	}
	
	public ResponseBean insertNewComplaint(ComplaintboxBean cb) {
		RestTemplate restTemplate;
		key = headerKeyManagement.getKey(ConstantValues.ORDER_API_ACCESS_KEY);
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.COMPLAINT_POST);
		ResponseBean responseBean = null;
		if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
			restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
			headers.set(key.getName(), key.getValue());
			headers.setContentType(MediaType.MULTIPART_FORM_DATA);
			System.err.println(cb);
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			
			map.add("fullname", cb.getFullname());
			map.add("email", cb.getEmail());
			map.add("phone", cb.getPhone());
			map.add("issuecategory", cb.getIssuecategory());
			map.add("issuesubcategory", cb.getIssuesubcategory());
			map.add("details", cb.getDetails());
			map.add("status", cb.getStatus());
			/*try {
				map.add("reffile", new MultipartInputStreamFileResource(cb.getReffile().getInputStream(), cb.getReffile().getOriginalFilename()));
			}catch(IOException e) {
				e.printStackTrace();
			}*/
			
			HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(map, headers);
			System.out.println(request);
			System.err.println(urls);
		response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
		System.out.println(response);
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
			}else {
				throw new RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED);
			}
		return responseBean;
	}
	
	public ResponseBean update(String complaintid, String email, String status) {
		RestTemplate restTemplate;
		key = headerKeyManagement.getKey(ConstantValues.ORDER_API_ACCESS_KEY);
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.COMPLAINT_PUT);
		ResponseBean responseBean = null;
		//try {
			if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
				restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
				headers.set(key.getName(), key.getValue());

				MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
				System.out.println("Complaintid: "+complaintid+" Email: "+ email+" Status: "+status );
				map.add(ConstantValues.COMPLAINTID, complaintid+"");
				map.add(ConstantValues.EMAIL, email);
				map.add(ConstantValues.STATUS, status);

				HttpEntity<Object> request = new HttpEntity<>(map, headers);

				response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
				if (response.getStatusCode() == HttpStatus.ACCEPTED) {
					responseBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(), ResponseBean.class);
				}
				else if(response.getStatusCode()==HttpStatus.OK)
				{
					responseBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(), ResponseBean.class);
				//	ResponseBean resBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(),ResponseBean.class);
					throw new DataNotFoundException(responseBean.getMessage());
				}
			} else {
				throw new RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED);
			}
		return responseBean;

	}

}
