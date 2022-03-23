/**
 * 
 */
package com.lc.sk.inventory.security.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lc.sk.inventory.security.beans.AuthenticationBean;
import com.lc.sk.inventory.security.beans.Authorization;
import com.lc.sk.inventory.security.beans.User;
import com.lc.sk.inventory.security.config.AppUsers;
import com.lc.sk.inventory.security.exceptions.subexceptions.NullRequestReceivedException;
import com.lc.sk.inventory.security.rest.AuthorizationRestService;
import com.lc.sk.inventory.security.util.ConstantValues;
import com.lc.sk.inventory.security.util.HeaderComponent;
import com.lc.sk.inventory.security.util.URLMapping;

/**
 * @author Mastanvali Shaik LittleCarrotsInventorySecurityService 2020
 */

@RestController
//@CrossOrigin
@RequestMapping(value = URLMapping.USER_LOGIN_ROOT_PATH+URLMapping.VERSION)
public class UserLogin {

	@Autowired
	AppUsers users;


	
	@Autowired
	private AuthorizationRestService authorizationRestService;

	/*
	 * @PostMapping(URLMapping.AUTHORIZED_LOGIN)
	 * 
	 * @ResponseBody public ResponseEntity<AuthenticationBean> login(
	 * 
	 * @RequestParam(name = ConstantValues.AUTHORIZED_USERNAME, required = true,
	 * defaultValue = ConstantValues.DEFAULT_STRING_VALUE) String username,
	 * 
	 * @RequestParam(name = ConstantValues.AUTHORIZED_PASSWORD, required = true,
	 * defaultValue = ConstantValues.DEFAULT_STRING_VALUE) String password,
	 * HttpSession session) { if (username != null && username != null &&
	 * username.length() > 0 &&
	 * !username.equals(ConstantValues.DEFAULT_STRING_VALUE)) {
	 * System.out.println("Username:" + username + ":Password:" + password); return
	 * new ResponseEntity<AuthenticationBean>(users.checkUser(username, password),
	 *  HttpStatus.ACCEPTED); } else { throw new
	 * NullRequestReceivedException(ConstantValues.USER_ID_INVALID); } }
	 */

	@SuppressWarnings("null")
	@PostMapping(URLMapping.AUTHORIZED_LOGIN)
	@ResponseBody
	public ResponseEntity<AuthenticationBean> login1(@RequestBody User user) {
		if (user != null || user.getUsername() != null || user.getUsername().length() > 0 || user.getPassword() != null
				|| !user.getPassword().equals("") || user.getPassword().length() > 0 || user.getRolename() != null
				|| !user.getRolename().equals("") || user.getRolename().length() > 0) {
			
			return new ResponseEntity<AuthenticationBean>(users.checkUser(user.getUsername(), user.getPassword(), user.getRolename()),
					 HttpStatus.ACCEPTED);
		} else {
			throw new NullRequestReceivedException(ConstantValues.USER_ID_INVALID);
		}
	}
	
	@GetMapping(URLMapping.GET_ALL_ROLES)
	@ResponseBody
	public ResponseEntity<List<Authorization>> getAllAuthorizationsList() {
		List<Authorization> authroizations = authorizationRestService.getAllRoleList();
		return new ResponseEntity<List<Authorization>>(authroizations,  HttpStatus.ACCEPTED);
	}

}
