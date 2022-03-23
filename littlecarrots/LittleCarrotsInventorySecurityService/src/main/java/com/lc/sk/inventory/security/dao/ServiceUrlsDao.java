/**
 * 
 */
package com.lc.sk.inventory.security.dao;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import com.lc.sk.inventory.security.beans.ErrorBean;
import com.lc.sk.inventory.security.beans.UrlBean;
import com.lc.sk.inventory.security.entities.ServiceURLs;
import com.lc.sk.inventory.security.repositories.ServiceUrlRepository;

/**
 * @author Mastanvali Shaik LittleCarrotsInventorySecurityService 2020
 */

@Component
public class ServiceUrlsDao {

	@Autowired
	ServiceUrlRepository serviceUrlRepository;
	UrlBean ub = null;
	
	Map<String, ServiceURLs> urlsdata = new ConcurrentHashMap<>();
	
	@PostConstruct
	public void loadUrls() {
		System.err.println("Loading URLS....");
		List<ServiceURLs> temp = serviceUrlRepository.getServiceAllServiceName();
		System.err.println("size of temp:"+temp.size());
		for(ServiceURLs service: temp) {
			urlsdata.put(service.getServicename(), service);
		}
		System.err.println("Size of URLS:"+urlsdata.size());
		System.err.println("Loading Complete....");
	}

	public UrlBean getAccessUrl(String urlid) {
		ServiceURLs serviceUrl = urlsdata.get(urlid);
		if (serviceUrl!=null) {
			ub = new UrlBean(serviceUrl.getServiceip(), serviceUrl.getUrl(), serviceUrl.getMethod(),
					serviceUrl.getTimeout(), serviceUrl.getServicename());
		} else {
			return getErrorBean();
		}
		return ub;
	}

	public UrlBean getErrorBean() {
		return new ErrorBean("localhost", "/error", HttpMethod.GET.toString(), 1000, "error");
	}

}
