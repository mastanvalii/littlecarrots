/**
 * 
 */
package com.lc.sk.notification.config;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lc.sk.notification.bean.KeyBean;
import com.lc.sk.notification.bean.SecurityKeyManagement;
import com.lc.sk.notification.repositories.SecurityKeyManagementRepository;

/**
 * @author Mastanvali Shaik
 * LittleCarrotsEMailService
 * 2020
 */
@Component
public class KeyManagementComponent {

	private @Autowired
	SecurityKeyManagementRepository keyRepository;
	
	private List<SecurityKeyManagement> keys = null;
	
	public List<SecurityKeyManagement> getAllKeys() {
		List<SecurityKeyManagement> keys = (List<SecurityKeyManagement>) keyRepository.findAll();
		return keys;
	}

	public Optional<SecurityKeyManagement> getByKeyId(long id) {
		Optional<SecurityKeyManagement> key = keyRepository.findById(id);
		return key;
	}

	@SuppressWarnings("null")
	public KeyBean getKey(String id) {
		KeyBean headerData = new KeyBean();
		keys = getAllKeys();
		if(id!=null || ! id.equals("")) {
			if(keys !=null ||keys.size()>0|| !keys.isEmpty()) {
				for(SecurityKeyManagement key: keys) {
					if(key.getKeyName().equals(id)) {
							headerData.setName(key.getKeyName());
							headerData.setValue(key.getKeyValue());
					}
				}
			}
		}
		return headerData;
	}
}
