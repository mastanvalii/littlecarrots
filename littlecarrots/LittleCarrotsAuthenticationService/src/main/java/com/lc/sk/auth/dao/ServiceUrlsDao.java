/**
 * 
 */
package com.lc.sk.auth.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import com.lc.sk.auth.bean.ErrorBean;
import com.lc.sk.auth.bean.UrlBean;
import com.lc.sk.auth.entities.ServiceURLs;
import com.lc.sk.auth.repositories.ServiceUrlRepository;

/**
 * @author Mastanvali Shaik
 * LittleCarrotsAuthenticationService
 * 2020
 */
@Component
public class ServiceUrlsDao {
	private @Autowired
	ServiceUrlRepository serviceUrlRepository;

	public UrlBean getAccessUrl(String urlid) {
		Optional<ServiceURLs> serviceUrl = serviceUrlRepository.findById(urlid);

		if (serviceUrl.isPresent()) {
			return new UrlBean(serviceUrl.get().getServiceip(), serviceUrl.get().getUrl(), serviceUrl.get().getMethod(),
					serviceUrl.get().getTimeout(), serviceUrl.get().getServicename());
		} else {
			return getErrorBean();
		}

	}

	public UrlBean getErrorBean() {
		return new ErrorBean("localhost", "/error", HttpMethod.GET.toString(), 1000, "error");
	}

}
