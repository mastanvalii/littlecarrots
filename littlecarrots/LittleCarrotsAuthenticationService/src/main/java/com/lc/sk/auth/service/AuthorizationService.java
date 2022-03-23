/**
 * 
 */
package com.lc.sk.auth.service;

import java.util.List;
import java.util.Optional;

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

import com.lc.sk.auth.entities.Authorization;
import com.lc.sk.auth.exceptions.subexceptions.AuthorizationNotFountException;
import com.lc.sk.auth.exceptions.subexceptions.DBValueInsertException;
import com.lc.sk.auth.exceptions.subexceptions.NullRequestReceivedException;
import com.lc.sk.auth.rbeans.ResponseBean;
import com.lc.sk.auth.repositories.AuthorizationRepository;
import com.lc.sk.auth.util.ConstantVariables;
import com.lc.sk.auth.util.SecurityHttpStatus;
import com.lc.sk.auth.util.URLMapping;

/**
 * @author Mastanvali Shaik LittleCarrotsAuthenticationService 2020
 */

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Validated
@RequestMapping(path = URLMapping.AUTHORIZATION_ROOT_PATH)
public class AuthorizationService { // NO_UCD (unused code)

	// REPOSITORIES DECLARATION
	@Autowired
	private AuthorizationRepository authorizationrepository;
	
	
	

	public AuthorizationService() {
		super();
	}

	// GET ALL AUTHORIZATION ROLES LIST
	@GetMapping(path=URLMapping.AUTHORIZATION_MAPPING_PATH,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Iterable<Authorization>> getAllAuthorizationInfo() {
		List<Authorization> authorization = (List<Authorization>) authorizationrepository.getAllAuthorization();
		if (authorization.isEmpty()) {
			throw new AuthorizationNotFountException(ConstantVariables.AUTHORIZATION_ROLES_NOT_FOUND);
		}
		return new ResponseEntity<Iterable<Authorization>>(authorization,  HttpStatus.ACCEPTED);
	}

	// GET AUTHORIZATION ROLE BY ROLENAME
	@GetMapping(path=URLMapping.AUTHORIZATION_MAPPING_WITH_PATH_VARIABLE,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Optional<Authorization>> getAuthorizationByRoleName(@PathVariable String rolename) {
		Optional<Authorization> authorization = authorizationrepository.getAuthorizationsById(rolename);
		if (authorization.isPresent()) {
			return new ResponseEntity<Optional<Authorization>>(authorization,  HttpStatus.ACCEPTED);
		} else {
			throw new AuthorizationNotFountException(ConstantVariables.AUTHORIZATION_ROLES_NOT_FOUND);
		}
	}

	// INSERTING NEW AUTHORIZATION ROLE
	@PostMapping(path=URLMapping.AUTHORIZATION_MAPPING_PATH,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> addNewRole(
			@RequestParam(name = ConstantVariables.AUTHORIZATION_ROLE_NAME_VARIABLE_NAME, required = true, defaultValue = ConstantVariables.DEFAULT_STRING_VALUE) String rolename,
			@RequestParam(name = ConstantVariables.AUTHORIZATION_ROLE_DESCRIPTIONS_VARIABLE_NAME, required = true, defaultValue = ConstantVariables.DEFAULT_STRING_VALUE) String descriptions,
			@RequestParam(name = ConstantVariables.AUTHORIZATION_ROLE_STATUS_VARIABLE_NAME, required = true, defaultValue = ConstantVariables.DEFAULT_BOOLEAN+"") boolean authorization_status) {
		ResponseBean responseBean = new ResponseBean();
		if (rolename == null || rolename.equals(ConstantVariables.DEFAULT_STRING_VALUE) || descriptions == null
				|| descriptions.equals(ConstantVariables.DEFAULT_STRING_VALUE)) {
			throw new NullRequestReceivedException(ConstantVariables.NULL_VALUES_RECEIVED_FROM_CLIENT);
		} else {
			Authorization auth = authorizationrepository
					.save(new Authorization(rolename, descriptions, authorization_status));
			if (auth.getRoleName().equals(rolename)) {
				responseBean.setMessage(ConstantVariables.NEW_AUTHORIZATION_ROLE_INSERTED_SUCCESS);
				responseBean.setTiemstamp(System.currentTimeMillis());
				responseBean.setResponsecode(SecurityHttpStatus.ACCEPTED);
				return new ResponseEntity<ResponseBean>(responseBean,  HttpStatus.ACCEPTED);
			} else {
				throw new DBValueInsertException(ConstantVariables.NEW_AUTHORIZATION_ROLE_INSERTED_FAILED);
			}
		}

	}

	// ROLE STATUS CHANGE METHOD
	@PutMapping(path=URLMapping.AUTHORIZATION_MAPPING_PATH_UPDATE_STATUS,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> updateRoleStatus(@PathVariable String rolename, @PathVariable boolean status) {
		ResponseBean responseBean = new ResponseBean();
		Optional<Authorization> authorization = authorizationrepository.findById(rolename);
		if(authorization.isPresent())
		{
		authorization.get().setAuthorization_status(status);
		Authorization auth = authorizationrepository.save(authorization.get());
		if (auth.getRoleName().equals(rolename) && auth.getAuthorization_status() == status) {
			responseBean.setMessage(ConstantVariables.STATUS_CHANGE_SUCESS);
			responseBean.setResponsecode(SecurityHttpStatus.ACCEPTED);
			responseBean.setTiemstamp(System.currentTimeMillis());
			return new ResponseEntity<ResponseBean>(responseBean,  HttpStatus.ACCEPTED);
		} else {
			throw new DBValueInsertException(ConstantVariables.STATUS_NOT_UPDATED_EXCEPTION_MSG);
		}
		}
		else
		{
		throw new AuthorizationNotFountException(ConstantVariables.STATUS_NOT_UPDATED);
		
		}
	}
}
