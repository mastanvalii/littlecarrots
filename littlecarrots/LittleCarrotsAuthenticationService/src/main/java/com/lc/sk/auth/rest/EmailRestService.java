/**
 * 
 */
package com.lc.sk.auth.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.lc.sk.auth.bean.Key;
import com.lc.sk.auth.bean.MessageBean;
import com.lc.sk.auth.bean.UrlBean;
import com.lc.sk.auth.dao.LcPropsDao;
import com.lc.sk.auth.dao.ServiceUrlsDao;
import com.lc.sk.auth.exceptions.subexceptions.MailServiceException;
import com.lc.sk.auth.exceptions.subexceptions.RestURLReaderException;
import com.lc.sk.auth.initializer.ServiceHttpRequestFactory;
import com.lc.sk.auth.rbeans.ResponseBean;
import com.lc.sk.auth.util.ConstantVariables;
import com.lc.sk.auth.util.NextServiceURLMapping;
import com.lc.sk.auth.util.UrlDetails;


/**
 * @author Mastanvali Shaik
 * LittleCarrotsAuthenticationService
 * 2020
 */
@Component
public class EmailRestService {
	
	@Autowired
	private ServiceUrlsDao serviceUrlsDao;

	@Autowired
	private ServiceHttpRequestFactory requestFacotry;
	
	@Autowired
	private LcPropsDao lcpropdao;

	private UrlBean urls;
	private RestTemplate restTemplate;
	private HttpHeaders headers = new HttpHeaders();
	private Key key;
	private ResponseEntity<String> response;
	private ResponseBean responseBean = new ResponseBean();
	
	
	public void sendMail(MessageBean mBean) {
		key = new Key("EMAIL_API_ACCESS_KEY", "Li20Il-t920TS-t1212T-Tls5LO-et319E-Rco120-CRar18-15Ar18");
		urls = serviceUrlsDao.getAccessUrl(UrlDetails.EMAIL_POST);
		if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
			restTemplate = new RestTemplate(requestFacotry.getClientHttpRequestFactory(urls.getTimeout()));
			headers.set(key.getName(), key.getValue());
			headers.setContentType(MediaType.APPLICATION_JSON);
			
			HttpEntity<Object> request = new HttpEntity<>(mBean,headers);
			response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
			if(response.getStatusCode() == HttpStatus.ACCEPTED) {
				//responseBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(), ResponseBean.class);
			}else {
				throw new MailServiceException(ConstantVariables.MAIL_NOT_SENT);
			}
		}else {
			throw new RestURLReaderException(ConstantVariables.ERROR_BEAN_RETURNED);
		}
		
		
	//	return responseBean;
	}
	
	public boolean authEmailStatus() {
		return lcpropdao.getPropStatus(ConstantVariables.MAIL_SEND_STATUS);
	}
}
