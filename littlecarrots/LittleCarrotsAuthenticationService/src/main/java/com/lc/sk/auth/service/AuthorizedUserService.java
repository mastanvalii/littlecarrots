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

import com.lc.sk.auth.bean.MessageBean;
import com.lc.sk.auth.bean.MessageGenerator;
import com.lc.sk.auth.dao.UserEmailDao;
import com.lc.sk.auth.entities.AuthorizedUsers;
import com.lc.sk.auth.entities.UserEmails;
import com.lc.sk.auth.exceptions.subexceptions.AuthorizedUserNotFoundException;
import com.lc.sk.auth.exceptions.subexceptions.DBPasswordUpdateException;
import com.lc.sk.auth.exceptions.subexceptions.DBValueInsertException;
import com.lc.sk.auth.exceptions.subexceptions.InvalidEmailFomatException;
import com.lc.sk.auth.exceptions.subexceptions.NullRequestReceivedException;
import com.lc.sk.auth.rbeans.AuthenticationBean;
import com.lc.sk.auth.rbeans.ResponseBean;
import com.lc.sk.auth.repositories.AuthorizedUsersRepository;
import com.lc.sk.auth.repositories.UserEmailsRespository;
import com.lc.sk.auth.rest.EmailRestService;
import com.lc.sk.auth.security.SecretSecurity;
import com.lc.sk.auth.util.ConstantVariables;
import com.lc.sk.auth.util.EmailValidation;
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
public class AuthorizedUserService { // NO_UCD (unused code)

	@Autowired
	private AuthorizedUsersRepository authorizedUserRepository;

	@Autowired
	private EmailRestService emailRestService;
	
	
	
	@Autowired
	private static UserEmailDao emaildao;

	@Autowired
	UserEmailsRespository useremails;
	
	public AuthorizedUserService() {
	}

	// GETTING ALL USERS INFO
	@GetMapping(path = URLMapping.AUTHORIZED_USER_MAPPING_PATH,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Iterable<AuthorizedUsers>> getAllAuthorizedUsers() {
		List<AuthorizedUsers> authUsers = (List<AuthorizedUsers>) authorizedUserRepository.getAllAuthorizedUser();
		if (authUsers.isEmpty()) {
			throw new AuthorizedUserNotFoundException(ConstantVariables.NO_AUTHORIZED_USER_FOUND);
		} else {
			return new ResponseEntity<Iterable<AuthorizedUsers>>(authUsers,  HttpStatus.ACCEPTED);
		}
	}

	// GETTING USERS INFORMATION BY USERNAME
	@GetMapping(path = URLMapping.AUTHORIZED_USER_MAPPING_WITH_PATH_VARIABLE,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Optional<AuthorizedUsers>> getAuthUserByUsername(@PathVariable String username) {
		Optional<AuthorizedUsers> authUser = Optional.empty();
		authUser = authorizedUserRepository.getAuthUsersByUsername(username);
		if (authUser.isPresent()) {
			return new ResponseEntity<Optional<AuthorizedUsers>>(authUser,  HttpStatus.ACCEPTED);
		} else {
			throw new AuthorizedUserNotFoundException(ConstantVariables.NO_AUTHORIZED_USER_FOUND);
		}

	}

	// ADDING NEW USER
	@PostMapping(path = URLMapping.AUTHORIZED_USER_MAPPING_PATH,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> insertNewUser(
			@RequestParam(name = ConstantVariables.AUTHORIZED_USERNAME, required = true, defaultValue = ConstantVariables.DEFAULT_STRING_VALUE) String username,
			@RequestParam(name = ConstantVariables.AUTHORIZED_PASSWORD, required = true, defaultValue = ConstantVariables.DEFAULT_STRING_VALUE) String password,
			@RequestParam(name = ConstantVariables.AUTHORIZED_USER_EMAIL, required = true, defaultValue = ConstantVariables.DEFAULT_STRING_VALUE) String email,
			@RequestParam(name = ConstantVariables.AUTHORIZED_STATUS, required = true, defaultValue = ConstantVariables.DEFAULT_BOOLEAN+"") boolean status,
			@RequestParam(name = ConstantVariables.AUTHORIZATION_ROLE_NAME_VARIABLE_NAME, required = true, defaultValue = ConstantVariables.DEFAULT_STRING_VALUE) String rolename) {
		ResponseBean responseBean = new ResponseBean();
		if (username == null || username.equals(ConstantVariables.DEFAULT_STRING_VALUE) || password == null
				|| password.equals(ConstantVariables.DEFAULT_STRING_VALUE) || email == null
				|| email.equals(ConstantVariables.DEFAULT_STRING_VALUE) || rolename == null
				|| rolename.equals(ConstantVariables.DEFAULT_STRING_VALUE)) {
			throw new NullRequestReceivedException( ConstantVariables.NULL_VALUES_RECEIVED_FROM_CLIENT);
		} else {
			if (EmailValidation.isValid(email)) {
				java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
				AuthorizedUsers authorizedUser = authorizedUserRepository.save(
						new AuthorizedUsers(username, email, SecretSecurity.enc(password), status, rolename, date));
				if (authorizedUser.getUsername().equals(username)) {
					if(emailRestService.authEmailStatus()) {						
						MessageBean mb = MessageGenerator.newUser(username, password, email, rolename);
						System.err.println(mb);
						java.sql.Timestamp date1 = new java.sql.Timestamp(new java.util.Date().getTime());
						UserEmails  ue=  new UserEmails(username, rolename, email, mb.getSubject(), mb.getInformation(), date1);
						System.err.println(ue);
						useremails.save(ue);
						emailRestService.sendMail(mb);
					}
					responseBean.setMessage(ConstantVariables.NEW_AUTHORIZED_USER_INSERTED_SUCCESS);
					responseBean.setTiemstamp(System.currentTimeMillis());
					responseBean.setResponsecode(SecurityHttpStatus.ACCEPTED);
					return new ResponseEntity<ResponseBean>(responseBean,  HttpStatus.ACCEPTED);
				} else {
					throw new DBValueInsertException(ConstantVariables.NEW_AUTHORIZATION_ROLE_INSERTED_FAILED);
				}
			} else {
				throw new InvalidEmailFomatException(ConstantVariables.NOT_VALID_EMAIL);
			}
		}

	}

	// LOGIN CODE
	@PostMapping(path = URLMapping.AUTHORIZED_USER_LOGIN_PATH,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<AuthenticationBean> authorizationLogin(
			@RequestParam(name = ConstantVariables.AUTHORIZED_USERNAME, required = true) String username,
			@RequestParam(name = ConstantVariables.AUTHORIZED_PASSWORD, required = true) String password) {
		AuthenticationBean authenticationBean = new AuthenticationBean();
		Optional<AuthorizedUsers> authUser = null;
		boolean flag = false;
		if (EmailValidation.isValid(username)) {

			List<AuthorizedUsers> authUsers = (List<AuthorizedUsers>) authorizedUserRepository.findAll();
			for (AuthorizedUsers user : authUsers) {
				if (username.equals(user.getEmail())) {
					username = user.getUsername();
					authUser = Optional.of(user);
					flag = true;
					break;
				}
			}

		} else {
			authUser = authorizedUserRepository.findById(username);

			flag = true;
			if (!authUser.isPresent()) {
				flag = false;
			}
		}
		if (flag) {
			if (username.equals(authUser.get().getUsername())
					&& password.equals(SecretSecurity.des(authUser.get().getPassword()))) {
				java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
				authUser.get().setLastlogindate(date);
				authenticationBean = new AuthenticationBean(authUser.get().getUsername(), 123456789,
						authUser.get().getEmail(), authUser.get().getRolename(), SecurityHttpStatus.ACCEPTED,
						authUser.get().getLastlogindate().getTime(), authUser.get().getAuthuserstatus()); // REQUIRED TO CODE FOR TOKENID
				updateLastLoginTime(authUser.get());
				return new ResponseEntity<AuthenticationBean>(authenticationBean,  HttpStatus.ACCEPTED);
			} else {
				throw new AuthorizedUserNotFoundException(ConstantVariables.INVALID_USERNAME);
			}
		} else {
			throw new AuthorizedUserNotFoundException(ConstantVariables.INVALID_USERNAME);
		}
	}

	// last login time update process
	public void updateLastLoginTime(AuthorizedUsers aUser) {
		authorizedUserRepository.save(aUser);
	}

	// update password
	@PutMapping(path = URLMapping.AUTHORIZED_USER_MAPPING_PATH,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> userPasswordUpdate(
			@RequestParam(name = ConstantVariables.AUTHORIZED_USERNAME, required = true) String username,
			@RequestParam(name = ConstantVariables.AUTHORIZED_PASSWORD, required = true) String password,
			@RequestParam(name = ConstantVariables.AUTHORIZED_NEW_PASSWORD, required = true) String newpassword) {
		ResponseBean responseBean = new ResponseBean();
		Optional<AuthorizedUsers> authUser = null;
		boolean flag = false;
		if (EmailValidation.isValid(username)) {

			List<AuthorizedUsers> authUsers = (List<AuthorizedUsers>) authorizedUserRepository.findAll();
			for (AuthorizedUsers user : authUsers) {
				if (username.equals(user.getEmail())) {
					username = user.getUsername();
					authUser = Optional.of(user);
					flag = true;
					break;
				}
			}

		} else {
			authUser = authorizedUserRepository.findById(username);

			flag = true;
			if (!authUser.isPresent()) {
				flag = false;
			}
		}
		if (flag) {
			if (username.equals(authUser.get().getUsername())
					&& password.equals(SecretSecurity.des(authUser.get().getPassword()))) {
				authUser.get().setPassword(SecretSecurity.enc(newpassword));
				AuthorizedUsers modifiedUser = authorizedUserRepository.save(authUser.get());
				if (modifiedUser.getUsername().equals(username)) {
					if(emailRestService.authEmailStatus()) {
						java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
						MessageBean mb = MessageGenerator.passwordUpdate(username, newpassword,authUser.get().getEmail(),authUser.get().getRolename());
						useremails.save(new UserEmails(username, authUser.get().getRolename(), authUser.get().getEmail(), mb.getSubject(), mb.getInformation(), date));
						emailRestService.sendMail(mb);
					}
					responseBean.setMessage(ConstantVariables.PASSWORD_UPDATE_SUCCESS);
					responseBean.setResponsecode(SecurityHttpStatus.OK);
					responseBean.setTiemstamp(System.currentTimeMillis());

					return new ResponseEntity<ResponseBean>(responseBean,  HttpStatus.ACCEPTED);
				} else {
					throw new DBPasswordUpdateException(ConstantVariables.NEW_PASSWORD_NOT_UPDATED);
				}

			} else {
				throw new AuthorizedUserNotFoundException(ConstantVariables.INVALID_USERNAME);
			}
		} else {
			throw new AuthorizedUserNotFoundException(ConstantVariables.INVALID_USERNAME);
		}

	}

	// update status of user
	@PutMapping(path = URLMapping.AUTHORIZED_USER_STATUS_UPDATE,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> updateStatus(@PathVariable String username, @PathVariable boolean status) {
		ResponseBean responseBean = new ResponseBean();
		Optional<AuthorizedUsers> authUser = Optional.empty();
		authUser = authorizedUserRepository.findById(username);
		if(authUser.isPresent())
		{
		authUser.get().setAuthuserstatus(status);
		AuthorizedUsers aUser = authorizedUserRepository.save(authUser.get());

		if (aUser.getUsername().equals(username) && aUser.getAuthuserstatus()== status) {
			responseBean.setMessage(ConstantVariables.STATUS_CHANGE_SUCESS);
			responseBean.setResponsecode(SecurityHttpStatus.ACCEPTED);
			responseBean.setTiemstamp(System.currentTimeMillis());
			if(emailRestService.authEmailStatus()) {
				emailRestService.sendMail(MessageGenerator.accountStatusActive(username, authUser.get().getRolename(),authUser.get().getEmail(), status));
			}
		} else {
			throw new DBValueInsertException(ConstantVariables.STATUS_NOT_UPDATED_EXCEPTION_MSG);
		}
		return new ResponseEntity<ResponseBean>(responseBean,  HttpStatus.ACCEPTED);
	}
		else
		{
		throw new AuthorizedUserNotFoundException(ConstantVariables.USER_NAME_NOT_FOUND);
		
		}
	}

}
