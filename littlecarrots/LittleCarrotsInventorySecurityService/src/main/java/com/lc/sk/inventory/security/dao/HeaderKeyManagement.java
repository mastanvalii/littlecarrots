/**
 * 
 */
package com.lc.sk.inventory.security.dao;

//import java.util.List;
//import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lc.sk.inventory.security.beans.Key;
//import com.lc.sk.inventory.security.entities.SecurityKeyManagement;
//import com.lc.sk.inventory.security.repositories.SecurityKeyManagementRepository;

/**
 * @author Mastanvali Shaik
 * LittleCarrotsInventorySecurityService
 * 2020
 */

@Component
public class HeaderKeyManagement {

///	@Autowired
//	private SecurityKeyManagementRepository securityKeyManagementRepository;
//	private List<SecurityKeyManagement> keys = null;
	
	private ConcurrentHashMap<String, String> data = new ConcurrentHashMap<>();
	
	
	
	public HeaderKeyManagement() {
		super();
		data.put("AUTH_API_ACCESS_KEY", "LRtt3I-ARtlos-51TCOi-er1212-1819TE-Tlcr92-01820L-Sa2015");
		data.put("SECU_API_ACCESS_KEY", "LSt15I-Tlos18-20TOir-121819-TRtr91-LRta20-3EALC2-05ce12");
		data.put("INVENTORY_API_ACCESS_KEY", "Li20Il-t920TS-t1212T-Tls5LO-et319E-Rco120-CRar18-15Ar18");
		data.put("EMAIL_API_ACCESS_KEY", "Li20Il-t920TS-t1212T-Tls5LO-et319E-Rco120-CRar18-15Ar18");
		data.put("ORDER_API_ACCESS_KEY", "Li20Il-t920TS-t1212T-Tls5LO-et319E-Rco120-CRar18-15Ar18");
	}

	/*
	 * public Optional<SecurityKeyManagement> getKeysById(long id) { return
	 * securityKeyManagementRepository.findById(id); }
	 * 
	 * public List<SecurityKeyManagement> getAllKeys(){ return
	 * (List<SecurityKeyManagement>) securityKeyManagementRepository.findAll(); }
	 */
	
	public Key getKey(String id)
	{
		return new Key(id,data.get(id));		
		 
	}
}
