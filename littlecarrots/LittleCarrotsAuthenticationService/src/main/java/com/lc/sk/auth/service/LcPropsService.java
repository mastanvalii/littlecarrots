/**
 * 
 */
package com.lc.sk.auth.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lc.sk.auth.dao.LcPropsDao;
import com.lc.sk.auth.entities.Lcprops;
import com.lc.sk.auth.rbeans.ResponseBean;
import com.lc.sk.auth.util.ConstantVariables;
import com.lc.sk.auth.util.HeaderComponent;
import com.lc.sk.auth.util.URLMapping;

/**
 * @author Mastanvali Shaik
 * LittleCarrotsAuthenticationService
 * 2020
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Validated
@RequestMapping(path = URLMapping.LC_PATH)
public class LcPropsService { // NO_UCD (unused code)

	@Autowired
	private LcPropsDao lcpropdao;


	
	@GetMapping(path=URLMapping.LCPROP,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Lcprops>> getAll(){
		return new ResponseEntity<List<Lcprops>>(lcpropdao.getAll(), HttpStatus.ACCEPTED);
	}
	

	@GetMapping(path=URLMapping.ABC,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Boolean> getPropStatus(@PathVariable String prop) {
		return new ResponseEntity<Boolean>(lcpropdao.getPropStatus(prop), HttpStatus.ACCEPTED);
	}
	
	@GetMapping(path=URLMapping.PROPID,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Lcprops> getById(@PathVariable long propid){
		return new ResponseEntity<>(lcpropdao.getById(propid).get(), HttpStatus.ACCEPTED);
	}
	
	@PutMapping(path=URLMapping.UPDATEPROP,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> updateStatus(@PathVariable long id,@PathVariable boolean status) {
		return new ResponseEntity<ResponseBean>(lcpropdao.updateStatus(id, status),  HttpStatus.ACCEPTED);
	}
	
	
	@PostMapping(path = URLMapping.LCPROP,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> insert(
			@RequestParam(name = "prop", required = true, defaultValue = ConstantVariables.DEFAULT_STRING_VALUE) String prop,
			@RequestParam(name = "status", required = true, defaultValue = ConstantVariables.DEFAULT_STRING_VALUE) boolean status
			) {
		return new ResponseEntity<ResponseBean>(lcpropdao.insertNewProp(prop, status),  HttpStatus.ACCEPTED);
	}
	
	
	
	
}
