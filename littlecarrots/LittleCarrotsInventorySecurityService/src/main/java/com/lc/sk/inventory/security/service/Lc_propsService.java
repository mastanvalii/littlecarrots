package com.lc.sk.inventory.security.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.lc.sk.inventory.security.beans.Lcprops;
import com.lc.sk.inventory.security.beans.ResponseBean;
import com.lc.sk.inventory.security.rest.LcpropsRestService;
import com.lc.sk.inventory.security.util.HeaderComponent;
import com.lc.sk.inventory.security.util.PropertiesURLMapping;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Validated
@RequestMapping(value = PropertiesURLMapping.PM+PropertiesURLMapping.VERSION)
public class Lc_propsService {
	
	
	@Autowired
	LcpropsRestService lcpropsRestService;
	@GetMapping(path=PropertiesURLMapping.LC_PROPS_GET)
	@ResponseBody
	public ResponseEntity<List<Lcprops>> getAll()
	{
    	List<Lcprops> lcprops = lcpropsRestService.getAll();
	
		return new ResponseEntity<List<Lcprops>>(lcprops,  HttpStatus.ACCEPTED);
	}
	
	@PostMapping(path=PropertiesURLMapping.LC_PROPS_GET)
	@ResponseBody
	public ResponseEntity<ResponseBean> insert(@RequestBody Lcprops lcprops)
					
	{
		ResponseBean responseBean = lcpropsRestService.insert(lcprops.getProp() +"", lcprops.isStatus() +"");
		return new ResponseEntity<ResponseBean>(responseBean,  HttpStatus.ACCEPTED);
	}
	
	@PutMapping(path=PropertiesURLMapping.LC_PROPS_PUT)
	@ResponseBody
	public ResponseEntity<ResponseBean> updateStatus(@PathVariable String id,@PathVariable boolean status) {
		  lcpropsRestService.updateStatus(id, status);
		return new ResponseEntity<ResponseBean>(lcpropsRestService.updateStatus(id, status),  HttpStatus.ACCEPTED);
	}
	
	@GetMapping(PropertiesURLMapping.LC_PROPS_GET1)
	@ResponseBody
	public ResponseEntity<Lcprops> getById(@PathVariable String id)
	{
		Lcprops lcprops = lcpropsRestService.getlcprops(id);
	
		return new ResponseEntity<Lcprops>(lcprops,  HttpStatus.ACCEPTED);
	}
	
	
	
	
}
