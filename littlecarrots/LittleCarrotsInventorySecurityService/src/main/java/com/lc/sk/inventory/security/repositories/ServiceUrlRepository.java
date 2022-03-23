/**
 * 
 */
package com.lc.sk.inventory.security.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import com.lc.sk.inventory.security.entities.ServiceURLs;

/**
 * @author Mastanvali Shaik
 * LittleCarrotsInventorySecurityService
 * 2020
 */

public interface ServiceUrlRepository extends JpaRepository<ServiceURLs, String>{

	@Query(value="select servicename,url, timeout,method,serviceip from serviceurls where servicename=:servicename",  nativeQuery = true)
	public ServiceURLs getServiceByServiceName(@Param("servicename")String servicename);
	
	@Query(value="select servicename,url, timeout,method,serviceip from serviceurls",  nativeQuery = true)
	public List<ServiceURLs> getServiceAllServiceName();	
}
