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
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.lc.sk.inventory.security.beans.GBlogBean;
import com.lc.sk.inventory.security.beans.GBlogBean1;
import com.lc.sk.inventory.security.beans.GrannyBlog;
import com.lc.sk.inventory.security.beans.Key;
import com.lc.sk.inventory.security.beans.ResponseBean;
import com.lc.sk.inventory.security.beans.UrlBean;
import com.lc.sk.inventory.security.dao.HeaderKeyManagement;
import com.lc.sk.inventory.security.dao.ServiceUrlsDao;
import com.lc.sk.inventory.security.exceptions.subexceptions.BackEndDataException;
import com.lc.sk.inventory.security.exceptions.subexceptions.DataNotFoundException;
import com.lc.sk.inventory.security.exceptions.subexceptions.NullRequestReceivedException;
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
public class GrannyBlogRestService {

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

	public String getAllGranyBlogDetails() {
		RestTemplate restTemplate;
		key = headerKeyManagement.getKey(ConstantValues.INVENTORY_API_ACCESS_KEY);
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.GRANNYBLOG_GET);
		String reply = "";
		// List<GrannyBlog> grannyblog = null;
		// try {
		if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
			restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
			headers = new HttpHeaders();
			headers.set(key.getName(), key.getValue());
			HttpEntity<Object> request = new HttpEntity<>(headers);
			response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
			if (response.getStatusCode() == HttpStatus.ACCEPTED) {
				reply = response.getBody();
				// GrannyBlog[] garannylist = (GrannyBlog[])
				// JsonToBeanConverter.convert(response.getBody(),
				// GrannyBlog[].class);
				// grannyblog = ArrayToListConverter.convertArrayToList(garannylist);
			} else if (response.getStatusCode() == HttpStatus.OK) {
				ResponseBean resBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(),
						ResponseBean.class);
				throw new DataNotFoundException(resBean.getMessage());
			}

		} else {
			throw new RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED);
		}

		return reply;
	}

	public String getGrannyBlogById(String blogid) {
		RestTemplate restTemplate;
		key = headerKeyManagement.getKey(ConstantValues.INVENTORY_API_ACCESS_KEY);
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.GRANNYBLOG_GET);
		String reply = "";
		// GrannyBlog grannyblog = null;
		// try {
		if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
			restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
			headers.set(key.getName(), key.getValue());
			urls.setUrl(urls.getUrl() + ConstantValues.SLASH_TAG + blogid);
			HttpEntity<Object> request = new HttpEntity<>(headers);
			response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
			if (response.getStatusCode() == HttpStatus.ACCEPTED) {
				reply = response.getBody();
				// grannyblog = (GrannyBlog) JsonToBeanConverter.convert(response.getBody(),
				// GrannyBlog.class);
			} else if (response.getStatusCode() == HttpStatus.OK) {
				ResponseBean resBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(),
						ResponseBean.class);
				throw new DataNotFoundException(resBean.getMessage());
			}

		} else {
			throw new RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED);
		}

		return reply;
	}

	// POST Insertion
	public String insertGrannyBlogInformation(long bid, String title, String subtitle, String information,
			MultipartFile thumbimage, MultipartFile mobilebanner, MultipartFile desktopbanner) {
		RestTemplate restTemplate;
		key = headerKeyManagement.getKey(ConstantValues.INVENTORY_API_ACCESS_KEY);
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.GRANNYBLOG_POST);
		String reply = "";
//		ResponseBean responseBean = null;

		if (bid == Long.parseLong(ConstantValues.DEFAULT_INT) || title.equals(ConstantValues.DEFAULT_STRING)
				|| subtitle.equals(ConstantValues.DEFAULT_STRING) || information.equals(ConstantValues.DEFAULT_STRING)
				|| thumbimage.isEmpty())

		{
			throw new NullRequestReceivedException(ConstantValues.RECEIVED_NULL_VALUES);

		} else {

			if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
				restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
				headers.set(key.getName(), key.getValue());
				headers.setContentType(MediaType.MULTIPART_FORM_DATA);

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
				map.add(ConstantValues.BID, bid);
				map.add(ConstantValues.TITLE, title);
				map.add(ConstantValues.SUB_TITLE, subtitle);
				map.add(ConstantValues.INFORMATION, information);
				try {
					map.add(ConstantValues.POSTER_IMAGE, new MultipartInputStreamFileResource(
							thumbimage.getInputStream(), thumbimage.getOriginalFilename()));
					map.add(ConstantValues.MOBILE_IMAGE, new MultipartInputStreamFileResource(
							mobilebanner.getInputStream(), mobilebanner.getOriginalFilename()));
					map.add(ConstantValues.DESKTOP_IMAGE, new MultipartInputStreamFileResource(
							desktopbanner.getInputStream(), desktopbanner.getOriginalFilename()));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(map, headers);

				response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
				if (response.getStatusCode() == HttpStatus.ACCEPTED) {
					reply = response.getBody();
					// responseBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(),
					// ResponseBean.class);
				} else if (response.getStatusCode() == HttpStatus.OK) {
					ResponseBean resBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(),
							ResponseBean.class);
					throw new RestResponseException(resBean.getMessage());
				} else if (response.getStatusCode() == HttpStatus.FAILED_DEPENDENCY
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
			return reply;
		}
	}

	// UPDATE
	public String updateGBlog(String blogid, String status) {
		RestTemplate restTemplate;
		key = headerKeyManagement.getKey(ConstantValues.INVENTORY_API_ACCESS_KEY);
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.GRANNYBLOG_PUT);
		String reply = "";
		// ResponseBean responseBean = null;
		// try {

		if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
			restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
			headers.set(key.getName(), key.getValue());
			urls.setUrl(urls.getUrl() + ConstantValues.SLASH_TAG + blogid + ConstantValues.SLASH_TAG + status);
			HttpEntity<Object> request = new HttpEntity<>(headers);
			response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
			if (response.getStatusCode() == HttpStatus.ACCEPTED) {
				// responseBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(),
				// ResponseBean.class);
				reply = response.getBody();
			} else if (response.getStatusCode() == HttpStatus.OK) {
				ResponseBean resBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(),
						ResponseBean.class);
				throw new DataNotFoundException(resBean.getMessage());
			}
		} else {
			throw new RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED);
		}

		return reply;
	}

	public String getAllGrannyUsersListByPage(int page, int count) {
		RestTemplate restTemplate;
		key = headerKeyManagement.getKey(ConstantValues.INVENTORY_API_ACCESS_KEY);
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.PAGENATION7);
		String reply = "";
		// List<GrannyBlog> pages = null;

		if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
			restTemplate = new RestTemplate(requestFacotry.validateAuthenticationSsl(urls.getTimeout()));
			headers.set(key.getName(), key.getValue());

			restTemplate = new RestTemplate(requestFacotry.validateAuthenticationSsl(urls.getTimeout()));
			headers.set(key.getName(), key.getValue());
			urls.setUrl(urls.getUrl() + ConstantValues.SLASH_TAG + page + ConstantValues.SLASH_TAG + count);
			HttpEntity<Object> request = new HttpEntity<>(headers);
			response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
			if (response.getStatusCode() == HttpStatus.ACCEPTED) {
				reply = response.getBody();
				// GrannyBlog[] temp1 = (GrannyBlog[])
				// JsonToBeanConverter.convert(response.getBody(),
				// GrannyBlog[].class);
				// pages = ArrayToListConverter.convertArrayToList(temp1);
			}

		} else {
			throw new RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED);
		}
		return reply;

	}

	public int getAllGrannyUsersPageCount(int page, int count) {
		RestTemplate restTemplate;
		key = headerKeyManagement.getKey(ConstantValues.INVENTORY_API_ACCESS_KEY);
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.PAGENATION8);

		int pages = 0;

		if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
			restTemplate = new RestTemplate(requestFacotry.validateAuthenticationSsl(urls.getTimeout()));
			headers.set(key.getName(), key.getValue());

			restTemplate = new RestTemplate(requestFacotry.validateAuthenticationSsl(urls.getTimeout()));
			headers.set(key.getName(), key.getValue());
			urls.setUrl(urls.getUrl() + ConstantValues.SLASH_TAG + page + ConstantValues.SLASH_TAG + count);
			HttpEntity<Object> request = new HttpEntity<>(headers);
			response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
			if (response.getStatusCode() == HttpStatus.ACCEPTED) {
				int temp1 = (Integer) JsonToBeanConverter.convert(response.getBody(), Integer.class);
				pages = temp1;
			}

		} else {
			throw new RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED);
		}
		return pages;

	}

	// latest5
	public String getAllGranyBlogLatest5() {
		RestTemplate restTemplate;
		key = headerKeyManagement.getKey(ConstantValues.INVENTORY_API_ACCESS_KEY);
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.GRANNYBLOG_LATEST5_GET);
		String reply = "";
		// List<GBlogBean1> grannyblog = null;
		if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
			restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
			headers = new HttpHeaders();
			headers.set(key.getName(), key.getValue());

			HttpEntity<Object> request = new HttpEntity<>(headers);
			response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
			if (response.getStatusCode() == HttpStatus.ACCEPTED) {
				reply = response.getBody();
				// GBlogBean1[] garannylist = (GBlogBean1[])
				// JsonToBeanConverter.convert(response.getBody(),
				// GBlogBean1[].class);
				// grannyblog = ArrayToListConverter.convertArrayToList(garannylist);
			} else if (response.getStatusCode() == HttpStatus.OK) {
				ResponseBean resBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(),
						ResponseBean.class);
				throw new DataNotFoundException(resBean.getMessage());
			}

		} else {
			throw new RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED);
		}

		return reply;
	}

	public String getNextLatest5GrannyBlogById(String blogid) {
		RestTemplate restTemplate;
		key = headerKeyManagement.getKey(ConstantValues.INVENTORY_API_ACCESS_KEY);
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.GRANNYBLOG_LATEST5_GET);
		String reply = "";
		// List<GBlogBean> grannyblog = null;
		if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
			restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
			headers.set(key.getName(), key.getValue());
			urls.setUrl(urls.getUrl() + ConstantValues.SLASH_TAG + blogid);
			HttpEntity<Object> request = new HttpEntity<>(headers);
			response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
			if (response.getStatusCode() == HttpStatus.ACCEPTED) {
				reply = response.getBody();
				// GBlogBean[] garannylist = (GBlogBean[])
				// JsonToBeanConverter.convert(response.getBody(),
				// GBlogBean[].class);
				// grannyblog = ArrayToListConverter.convertArrayToList(garannylist);
			} else if (response.getStatusCode() == HttpStatus.OK) {
				ResponseBean resBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(),
						ResponseBean.class);
				throw new DataNotFoundException(resBean.getMessage());
			}

		} else {
			throw new RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED);
		}

		return reply;
	}

}
