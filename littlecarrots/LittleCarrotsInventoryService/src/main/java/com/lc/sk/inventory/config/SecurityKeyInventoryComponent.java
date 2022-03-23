/**
 * 
 */
package com.lc.sk.inventory.config;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import com.lc.sk.inventory.bean.Key;

/**
 * @author Mastanvali Shaik
 * LittleCarrotsInventoryService
 * 2020
 */
@Component
public class SecurityKeyInventoryComponent {

	/*
	 * @Autowired private SecurityKeyInventoryRepository
	 * securityKeyInventoryRepository; private List<SecurityKeyInventory> keys =
	 * null;
	 */
	
	ConcurrentHashMap<String, String> data ;
	
	public SecurityKeyInventoryComponent() {
		super();
		data = new ConcurrentHashMap<>();
	//	data.put("AUTH_API_ACCESS_KEY", "LRtt3I-ARtlos-51TCOi-er1212-1819TE-Tlcr92-01820L-Sa2015");
	//	data.put("SECU_API_ACCESS_KEY", "LSt15I-Tlos18-20TOir-121819-TRtr91-LRta20-3EALC2-05ce12");
		data.put("INVENTORY_API_ACCESS_KEY", "Li20Il-t920TS-t1212T-Tls5LO-et319E-Rco120-CRar18-15Ar18"); //$NON-NLS-2$
	//	data.put("EMAIL_API_ACCESS_KEY", "Li20Il-t920TS-t1212T-Tls5LO-et319E-Rco120-CRar18-15Ar18");
	}

	/*
	 * public Optional<SecurityKeyInventory> getKeyById(long id) { return
	 * securityKeyInventoryRepository.findById(id); }
	 * 
	 * public List<SecurityKeyInventory> getAllKeys(){ return
	 * (List<SecurityKeyInventory>) securityKeyInventoryRepository.findAll(); }
	 */
	
	public Key getKey(String id)
	{
		return new Key(id,data.get(id));	
	}
}
