/**
 * 
 */
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

import com.lc.sk.inventory.security.beans.AuthenticationBean;
import com.lc.sk.inventory.security.beans.AuthorizedUsers;
import com.lc.sk.inventory.security.beans.Key;
import com.lc.sk.inventory.security.beans.ResponseBean;
import com.lc.sk.inventory.security.beans.UrlBean;
import com.lc.sk.inventory.security.dao.HeaderKeyManagement;
import com.lc.sk.inventory.security.dao.ServiceUrlsDao;
import com.lc.sk.inventory.security.exceptions.subexceptions.AuthorizedUserNotFoundException;
import com.lc.sk.inventory.security.exceptions.subexceptions.BackEndDataException;
import com.lc.sk.inventory.security.exceptions.subexceptions.DataNotFoundException;
import com.lc.sk.inventory.security.exceptions.subexceptions.RestResponseException;
import com.lc.sk.inventory.security.exceptions.subexceptions.RestURLReaderException;
import com.lc.sk.inventory.security.factory.ServiceHttpRequestFactory;
import com.lc.sk.inventory.security.util.ArrayToListConverter;
import com.lc.sk.inventory.security.util.ConstantValues;
import com.lc.sk.inventory.security.util.JsonToBeanConverter;
import com.lc.sk.inventory.security.util.NextServiceURLMapping;
import com.lc.sk.inventory.security.util.SecurityHttpStatus;
import com.lc.sk.inventory.security.util.UrlDetails;

/**
 * @author Mastanvali Shaik LittleCarrotsInventorySecurityService 2020
 */

@Component
public class AuthorizedUsersRestService {

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
	

	// URL id 103
	public List<AuthorizedUsers> getAllAuthorizedUsers() {
		RestTemplate restTemplate;
		key = headerKeyManagement.getKey(ConstantValues.AUTH_HEADER_KEY_DB_ID);
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.AUTHORIZED_USER_SERVICE_URL_GET_ID);

		List<AuthorizedUsers> authorizedUsers = null;
		
			if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
				restTemplate = new RestTemplate(requestFacotry.validateAuthenticationSsl(urls.getTimeout()));
				headers.set(key.getName(), key.getValue());
	
				HttpEntity<Object> request = new HttpEntity<>(headers);
				response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
				if (response.getStatusCode() == HttpStatus.ACCEPTED) {
					AuthorizedUsers[] authorizedUsersList = (AuthorizedUsers[]) JsonToBeanConverter
							.convert(response.getBody(), AuthorizedUsers[].class);
					authorizedUsers = ArrayToListConverter.convertArrayToList(authorizedUsersList);					
				} 
				else if(response.getStatusCode() == HttpStatus.OK) {
						throw new DataNotFoundException(response.getBody());
				}
			} else {
				throw new RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED);
			}
		return authorizedUsers;
	}

	// URL id 103 parth variable
	public AuthorizedUsers getAuthorizedUserByUsername(String username) {
		RestTemplate restTemplate;
		key = headerKeyManagement.getKey(ConstantValues.AUTH_HEADER_KEY_DB_ID);
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.AUTHORIZED_USER_SERVICE_URL_GET_ID);
		AuthorizedUsers authorizedUsers = null;
			if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
				restTemplate = new RestTemplate(requestFacotry.validateAuthenticationSsl(urls.getTimeout()));
				headers.set(key.getName(), key.getValue());
				HttpEntity<Object> request = new HttpEntity<>(headers);
				urls.setUrl(urls.getUrl() + ConstantValues.SLASH_TAG + username);
				response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
				if (response.getStatusCode() == HttpStatus.ACCEPTED) {
					authorizedUsers = (AuthorizedUsers) JsonToBeanConverter.convert(response.getBody(),
							AuthorizedUsers.class);
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
	
		return authorizedUsers;
	}

	//url 104
	public ResponseBean insertNewUser(String username, String password, String email, boolean status, String rolename) {
		RestTemplate restTemplate;
		key = headerKeyManagement.getKey(ConstantValues.AUTH_HEADER_KEY_DB_ID);
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.AUTHORIZED_USER_SERVICE_URL_POST_ID);
		ResponseBean responseBean = null;
			if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
				restTemplate = new RestTemplate(requestFacotry.validateAuthenticationSsl(urls.getTimeout()));
				headers.set(key.getName(), key.getValue());

				MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
				map.add(ConstantValues.AUTHORIZED_USERNAME, username);
				map.add(ConstantValues.AUTHORIZED_PASSWORD, password);
				map.add(ConstantValues.AUTHORIZED_USER_EMAIL, email);
				map.add(ConstantValues.AUTHORIZED_STATUS, status+"");
				map.add(ConstantValues.AUTHORIZATION_ROLE_NAME_VARIABLE_NAME, rolename);

				HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

				response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
				if (response.getStatusCode() == HttpStatus.ACCEPTED) {
				//	notification.sendMail(MessageGenerator.newUser(username, password, email, rolename));
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
		return responseBean;
	}
	
	//url 105
	public AuthenticationBean loginCheck(String username, String password)
	{
		RestTemplate restTemplate;
		key = headerKeyManagement.getKey(ConstantValues.AUTH_HEADER_KEY_DB_ID);
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.AUTHORIZED_USER_SERVICE_URL_LOGIN_ID);
		AuthenticationBean responseBean = null;
		ResponseBean failMsgBean = null;
			if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
				restTemplate = new RestTemplate(requestFacotry.validateAuthenticationSsl(urls.getTimeout()));
				headers.set(key.getName(), key.getValue());

				MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
				map.add(ConstantValues.AUTHORIZED_USERNAME, username);
				map.add(ConstantValues.AUTHORIZED_PASSWORD, password);

				HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

				response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
				if (response.getStatusCode() == HttpStatus.ACCEPTED) {
					responseBean = (AuthenticationBean) JsonToBeanConverter.convert(response.getBody(), AuthenticationBean.class);
					responseBean.setResponsecode(SecurityHttpStatus.ACCEPTED);
				} else if(response.getStatusCode()==HttpStatus.OK)
				{
					failMsgBean = (ResponseBean)JsonToBeanConverter.convert(response.getBody(), ResponseBean.class);
					responseBean = new AuthenticationBean("", 0,"", "", failMsgBean.getResponsecode(), System.currentTimeMillis(),false);
				}
			} else {
				throw new RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED);
			}
		return responseBean;
		
	}
	
	// URL id 106 parth variable
	public ResponseBean blockUser(String username, boolean status) {
		RestTemplate restTemplate;
		key = headerKeyManagement.getKey(ConstantValues.AUTH_HEADER_KEY_DB_ID);
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.AUTHORIZED_USER_SERVICE_URL_PUT_ID);
		ResponseBean responseBean = null;
			if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
				restTemplate = new RestTemplate(requestFacotry.validateAuthenticationSsl(urls.getTimeout()));
				headers.set(key.getName(), key.getValue());
				HttpEntity<Object> request = new HttpEntity<>(headers);
				urls.setUrl(urls.getUrl() + ConstantValues.SLASH_TAG + username  + ConstantValues.SLASH_TAG + status );
				response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
	
				if (response.getStatusCode() == HttpStatus.ACCEPTED) {
					responseBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(),
							ResponseBean.class);
				} else if (response.getStatusCode() == HttpStatus.OK) {
					throw new RestResponseException(response.getBody());
				}
			} else {
				throw new AuthorizedUserNotFoundException(ConstantValues.USER_NAME_NOT_FOUND);
			}
		return responseBean;
	}

	
	// URL id 106 parth variable
	public ResponseBean passwordUpdate(String username, String password, String newpassword) {
		RestTemplate restTemplate;
		key = headerKeyManagement.getKey(ConstantValues.AUTH_HEADER_KEY_DB_ID);
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.AUTHORIZED_USER_SERVICE_URL_PUT_ID);
		ResponseBean responseBean = null;
			if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
				restTemplate = new RestTemplate(requestFacotry.validateAuthenticationSsl(urls.getTimeout()));
				headers.set(key.getName(), key.getValue());
				
				MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
				map.add(ConstantValues.AUTHORIZED_USERNAME, username);
				map.add(ConstantValues.AUTHORIZED_PASSWORD, password);
				map.add(ConstantValues.AUTHORIZED_NEW_PASSWORD, newpassword);
				HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

				response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
				
				if (response.getStatusCode() == HttpStatus.ACCEPTED) {
					responseBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(),
							ResponseBean.class);
				} else if (response.getStatusCode() == HttpStatus.OK) {
					throw new RestResponseException(response.getBody());
				}
			} else {
				throw new RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED);
			}
		return responseBean;
	}
}

