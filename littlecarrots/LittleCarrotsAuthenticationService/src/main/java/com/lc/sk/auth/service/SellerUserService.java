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

import com.lc.sk.auth.bean.MessageGenerator;
import com.lc.sk.auth.entities.SellerUsers;
import com.lc.sk.auth.exceptions.subexceptions.DBValueInsertException;
import com.lc.sk.auth.exceptions.subexceptions.NullRequestReceivedException;
import com.lc.sk.auth.exceptions.subexceptions.SellerNotFoundException;
import com.lc.sk.auth.exceptions.subexceptions.SellerUserNotFoundException;
import com.lc.sk.auth.rbeans.AuthenticationBean;
import com.lc.sk.auth.rbeans.ResponseBean;
import com.lc.sk.auth.repositories.SellerUserRepository;
import com.lc.sk.auth.rest.EmailRestService;
import com.lc.sk.auth.security.SecretSecurity;
import com.lc.sk.auth.util.ConstantVariables;
import com.lc.sk.auth.util.HeaderComponent;
import com.lc.sk.auth.util.SecurityHttpStatus;
import com.lc.sk.auth.util.URLMapping;

/**
 * @author Mastanvali Shaik LittleCarrotsAuthenticationService 2020
 */


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Validated
@RequestMapping(path = URLMapping.AUTHORIZATION_ROOT_PATH)
public class SellerUserService { // NO_UCD (unused code)

	@Autowired
	SellerUserRepository sellerUserRepository;
	
	@Autowired
	EmailRestService emailRestService;

	

	public SellerUserService() {
	}

	@PostMapping(path=URLMapping.SELLER_USER_MAPPING_PATH,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> insertNewSellerUser(
			@RequestParam(name = ConstantVariables.AUTHORIZED_USERNAME, required = true, defaultValue = ConstantVariables.DEFAULT_STRING_VALUE) String username,
			@RequestParam(name = ConstantVariables.AUTHORIZED_PASSWORD, required = true, defaultValue = ConstantVariables.DEFAULT_STRING_VALUE) String password,
			@RequestParam(name = ConstantVariables.EMAIL, required = true, defaultValue = ConstantVariables.DEFAULT_STRING_VALUE) String email,
			@RequestParam(name = ConstantVariables.FULLNAME, required = true, defaultValue = ConstantVariables.DEFAULT_STRING_VALUE) String fullname,
			@RequestParam(name = ConstantVariables.AUTHORIZATION_ROLE_NAME_VARIABLE_NAME, required = true, defaultValue = ConstantVariables.DEFAULT_STRING_VALUE) String rolename,
			@RequestParam(name = ConstantVariables.SELLER_ID, required = true, defaultValue = ConstantVariables.DEFAULT_INT_VALUE) long sellerid,
			@RequestParam(name = ConstantVariables.STATUS, required = true, defaultValue = ConstantVariables.DEFAULT_BOOLEAN+"") boolean status

	) {
		ResponseBean responseBean = new ResponseBean();

		if (username.equals(ConstantVariables.DEFAULT_STRING_VALUE)
				|| password.equals(ConstantVariables.DEFAULT_STRING_VALUE)
				|| email.equals(ConstantVariables.DEFAULT_STRING_VALUE)
				|| fullname.equals(ConstantVariables.DEFAULT_STRING_VALUE)
				|| rolename.equals(ConstantVariables.DEFAULT_STRING_VALUE)
				|| sellerid == new Long(ConstantVariables.DEFAULT_INT_VALUE).longValue()) {
			throw new NullRequestReceivedException(ConstantVariables.NULL_VALUES_RECEIVED_FROM_CLIENT);
		} else {
			java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
			SellerUsers salesUser = sellerUserRepository.save(new SellerUsers(username, SecretSecurity.enc(password),
					email, sellerid, fullname, status, rolename, date));

			if (salesUser.getUsername().equals(username)) {
				if(emailRestService.authEmailStatus()) {
					emailRestService.sendMail(MessageGenerator.newSalesuser(username, password, email, fullname, rolename, sellerid+" "));
				}
				responseBean.setMessage(ConstantVariables.NEW_SELLER_INSERTED_SUCCESS);
				responseBean.setResponsecode(SecurityHttpStatus.ACCEPTED);
				responseBean.setTiemstamp(System.currentTimeMillis());

				return new ResponseEntity<ResponseBean>(responseBean,  HttpStatus.ACCEPTED);

			} else {
				throw new DBValueInsertException(ConstantVariables.NEW_SALES_USER_INSERTED_FAILED);
			}
		}

	}

	@GetMapping(path=URLMapping.SELLER_USER_MAPPING_PATH,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<SellerUsers>> getAllUsers() {
		List<SellerUsers> sellerUsers = (List<SellerUsers>) sellerUserRepository.getAllSellerUser();

		if (sellerUsers.size() > 0) {
			return new ResponseEntity<List<SellerUsers>>(sellerUsers,  HttpStatus.ACCEPTED);
		} else {
			throw new SellerUserNotFoundException(ConstantVariables.SALES_USER_NOT_FOUND);
		}
	}

	@GetMapping(path=URLMapping.SELLER_USER_MAPPING_PATH_WITH_PATH_VARIABLE,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Optional<SellerUsers>> getUsersByUsername(@PathVariable String username) {
		Optional<SellerUsers> sellerUsers = sellerUserRepository.getByUsername(username);

		if (!sellerUsers.isPresent()) {			
			throw new SellerUserNotFoundException(ConstantVariables.SALES_USER_NOT_FOUND);
		} else {
			return new ResponseEntity<Optional<SellerUsers>>(sellerUsers,  HttpStatus.ACCEPTED);
		}
	}

	@PutMapping(path=URLMapping.SELLER_USER_MAPPING_PATH,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> updatePassword(
			@RequestParam(name = ConstantVariables.AUTHORIZED_USERNAME, required = true, defaultValue = ConstantVariables.DEFAULT_STRING_VALUE) String username,
			@RequestParam(name = ConstantVariables.AUTHORIZED_PASSWORD, required = true, defaultValue = ConstantVariables.DEFAULT_STRING_VALUE) String password,
			@RequestParam(name = ConstantVariables.AUTHORIZED_NEW_PASSWORD, required = true, defaultValue = ConstantVariables.DEFAULT_STRING_VALUE) String newpassword) {
		ResponseBean responseBean = new ResponseBean();
		Optional<SellerUsers> sellerUsers = sellerUserRepository.findById(username);

		if (sellerUsers.get().getUsername().equals(username)) {
			if (SecretSecurity.des(sellerUsers.get().getPassword()).equals(password)) {
				sellerUsers.get().setPassword(SecretSecurity.enc(newpassword));
				SellerUsers postUserDetails = sellerUserRepository.save(sellerUsers.get());
				if (postUserDetails.getUsername().equals(username)) {
					if(emailRestService.authEmailStatus()) {
						emailRestService.sendMail(MessageGenerator.passwordUpdate(username, newpassword, sellerUsers.get().getEmail(), sellerUsers.get().getRolename()));
					}
					responseBean.setMessage(ConstantVariables.PASSWORD_UPDATE_SUCCESS);
					responseBean.setResponsecode(SecurityHttpStatus.ACCEPTED);
					responseBean.setTiemstamp(System.currentTimeMillis());
					return new ResponseEntity<ResponseBean>(responseBean,  HttpStatus.ACCEPTED);
				} else {
					throw new DBValueInsertException(ConstantVariables.NEW_PASSWORD_NOT_UPDATED);
				}
			} else {
				throw new SellerNotFoundException(ConstantVariables.INVALID_USERNAME);
			}
		} else {
			throw new SellerNotFoundException(ConstantVariables.INVALID_USERNAME);
		}

	}

	@PostMapping(path=URLMapping.SELLER_USER_LOGIN_MAPPING_PATH,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<AuthenticationBean> login(
			@RequestParam(name = ConstantVariables.AUTHORIZED_USERNAME, required = true, defaultValue = ConstantVariables.DEFAULT_STRING_VALUE) String username,
			@RequestParam(name = ConstantVariables.AUTHORIZED_PASSWORD, required = true, defaultValue = ConstantVariables.DEFAULT_STRING_VALUE) String password) {
		AuthenticationBean responseBean = new AuthenticationBean();
		Optional<SellerUsers> sellerUsers = sellerUserRepository.findById(username);

		if(sellerUsers.isPresent()) {
		if (sellerUsers.get().getUsername().equals(username)) {
			if (SecretSecurity.des(sellerUsers.get().getPassword()).equals(password)) {

				java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
				sellerUsers.get().setLastlogin(date);
				sellerUserRepository.save(sellerUsers.get());
				responseBean = new AuthenticationBean(sellerUsers.get().getUsername(), 123456789,
						sellerUsers.get().getEmail(), sellerUsers.get().getRolename(), SecurityHttpStatus.ACCEPTED,
						sellerUsers.get().getLastlogin().getTime(), sellerUsers.get().getStatus()); // REQUIRED TO CODE FOR TOKENID
				return new ResponseEntity<AuthenticationBean>(responseBean,  HttpStatus.ACCEPTED);

			} else {
				throw new SellerNotFoundException(ConstantVariables.INVALID_USERNAME);
			}
		} else {
			throw new SellerNotFoundException(ConstantVariables.INVALID_USERNAME);
		}
		}else
		{
			throw new SellerNotFoundException(ConstantVariables.INVALID_USERNAME);
		}

	}

	@PutMapping(path=URLMapping.SELLER_USER_UPDATE_STATUS_MAPPING_PATH,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> updateStatus(@PathVariable String username, @PathVariable boolean status) {
		ResponseBean responseBean = new ResponseBean();

		Optional<SellerUsers> user = sellerUserRepository.findById(username);
		if(user.isPresent())
		{
		if (user.get().getUsername().equals(username)) {
			user.get().setStatus(status);
			SellerUsers sellUsers = sellerUserRepository.save(user.get());
			if (sellUsers.getStatus() == status) {
				if(emailRestService.authEmailStatus()) {
					emailRestService.sendMail(MessageGenerator.accountStatusActive(username,sellUsers.getRolename() ,sellUsers.getEmail(), status));
				}
				responseBean.setMessage(ConstantVariables.SELLER_STATUS_UPDATE_SUCCESS);
				responseBean.setResponsecode(SecurityHttpStatus.ACCEPTED);
				responseBean.setTiemstamp(System.currentTimeMillis());
				return new ResponseEntity<ResponseBean>(responseBean,  HttpStatus.ACCEPTED);
			} else {
				throw new DBValueInsertException(ConstantVariables.SELLER_STATUS_UPDATE_FAILED);
			}
		} else {
			throw new SellerNotFoundException(ConstantVariables.SELLER_NOT_FOUND);
		}
		}
		else
		{
		throw new SellerUserNotFoundException(ConstantVariables.USER_NAME_NOT_FOUND);
		}
		
	}

}
