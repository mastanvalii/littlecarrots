package com.lc.sk.auth.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

import com.lc.sk.auth.entities.LilCustomer;
import com.lc.sk.auth.exceptions.subexceptions.NullRequestReceivedException;
import com.lc.sk.auth.exceptions.subexceptions.UserNotFoundException;
import com.lc.sk.auth.rbeans.ResponseBean;
import com.lc.sk.auth.repositories.LilCustomerRepository;
import com.lc.sk.auth.util.ConstantVariables;
import com.lc.sk.auth.util.HeaderComponent;
import com.lc.sk.auth.util.SecurityHttpStatus;
import com.lc.sk.auth.util.URLMapping;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Validated
@RequestMapping(path = URLMapping.AUTHORIZATION_ROOT_PATH)
public class LilCustomerService { // NO_UCD (unused code)

	@Autowired
	LilCustomerRepository lilCustomerRepository;

	

	@PostMapping(path=URLMapping.LIL_USER_MAPPING_PATH,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> insertUser(
			@RequestParam(name = ConstantVariables.UNIQUE_ID, required = true, defaultValue = ConstantVariables.DEFAULT_STRING_VALUE) String uniqueid,
			@RequestParam(name = ConstantVariables.EMAIL, required = true, defaultValue = ConstantVariables.DEFAULT_STRING_VALUE) String email,
			@RequestParam(name = ConstantVariables.FIRST_NAME, required = true, defaultValue = ConstantVariables.DEFAULT_STRING_VALUE) String firstname,
			@RequestParam(name = ConstantVariables.LAST_NAME, required = true, defaultValue = ConstantVariables.DEFAULT_STRING_VALUE) String lastname,
			@RequestParam(name = ConstantVariables.IMAGE_URL, required = true, defaultValue = ConstantVariables.DEFAULT_STRING_VALUE) String imageurl) {
		// @RequestParam(name = ConstantVariables.PHONE, required = true, defaultValue =
		// ConstantVariables.DEFAULT_INT_VALUE) long phone,
		// @RequestParam(name = ConstantVariables.USER_TYPE, required = true,
		// defaultValue = ConstantVariables.DEFAULT_STRING_VALUE) String usertype,
		// @RequestParam(name = ConstantVariables.STATUS, required = true, defaultValue
		// = ConstantVariables.DEFAULT_BOOLEAN) boolean status) {
		ResponseBean responseBean = new ResponseBean();

		if (uniqueid .equals(ConstantVariables.DEFAULT_STRING_VALUE)
				|| email.equals(ConstantVariables.DEFAULT_STRING_VALUE)
//			|| firstname.equals(ConstantVariables.DEFAULT_STRING_VALUE)
//			|| lastname.equals(ConstantVariables.DEFAULT_STRING_VALUE)
//			|| imageurl.equals(ConstantVariables.DEFAULT_STRING_VALUE)
		// || phone==new Long(ConstantVariables.DEFAULT_INT_VALUE).longValue()
		// || usertype.equals(ConstantVariables.DEFAULT_STRING_VALUE)
		// || status==new Boolean(ConstantVariables.DEFAULT_BOOLEAN).booleanValue()

		) {
			throw new NullRequestReceivedException(ConstantVariables.NULL_VALUES_RECEIVED_FROM_CLIENT);
		} else {
			String usertype = "customer";
			Optional<LilCustomer> lilcustomer = lilCustomerRepository.findByUniqueid(uniqueid);
			if (!lilcustomer.isPresent()) {
				LilCustomer lil = new LilCustomer(uniqueid, email, firstname, lastname, imageurl, usertype);
				lilCustomerRepository.save(lil);
				responseBean.setMessage("verify your phone");
				responseBean.setResponsecode(SecurityHttpStatus.ACCEPTED);
				responseBean.setTiemstamp(System.currentTimeMillis());
				return new ResponseEntity<ResponseBean>(responseBean,  HttpStatus.ACCEPTED);
			} else {
				responseBean.setMessage("existing user");
				responseBean.setResponsecode(SecurityHttpStatus.ACCEPTED);
				responseBean.setTiemstamp(System.currentTimeMillis());
				return new ResponseEntity<ResponseBean>(responseBean,  HttpStatus.ACCEPTED);
			}
		}
	}

	@PutMapping(path=URLMapping.LIL_USER_MAPPING_PATH1,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> insertPhone(
			@RequestParam(name = ConstantVariables.USER_ID, required = true, defaultValue = ConstantVariables.DEFAULT_INT_VALUE) long userid,
			@RequestParam(name = ConstantVariables.UNIQUE_ID, required = true, defaultValue = ConstantVariables.DEFAULT_STRING_VALUE) String uniqueid,
			@RequestParam(name = ConstantVariables.EMAIL, required = true, defaultValue = ConstantVariables.DEFAULT_STRING_VALUE) String email,
			@RequestParam(name = ConstantVariables.FIRST_NAME, required = true, defaultValue = ConstantVariables.DEFAULT_STRING_VALUE) String firstname,
			@RequestParam(name = ConstantVariables.LAST_NAME, required = true, defaultValue = ConstantVariables.DEFAULT_STRING_VALUE) String lastname,
			@RequestParam(name = ConstantVariables.IMAGE_URL, required = true, defaultValue = ConstantVariables.DEFAULT_STRING_VALUE) String imageurl,
			@RequestParam(name = ConstantVariables.PHONE, required = true, defaultValue = ConstantVariables.DEFAULT_INT_VALUE) long phone,
			// @RequestParam(name = ConstantVariables.USER_TYPE, required = true,
			// defaultValue = ConstantVariables.DEFAULT_STRING_VALUE) String usertype,
			@RequestParam(name = ConstantVariables.STATUS, required = true, defaultValue = ConstantVariables.DEFAULT_BOOLEAN+"") boolean status)

	// @PathVariable long userid

	{
		ResponseBean responseBean = new ResponseBean();

		Optional<LilCustomer> lilcustomer = lilCustomerRepository.findByphone(uniqueid);
	//	Optional<LilCustomer> lil2 = lilCustomerRepository.findByphone(uniqueid);

		if (lilcustomer.isPresent()) {

			lilcustomer.get().setPhone(phone);
			// lilcustomer.get().setEmail(email);
			lilcustomer.get().setImageurl(imageurl);
			lilcustomer.get().setFirstname(firstname);
			lilcustomer.get().setLastname(lastname);
			lilcustomer.get().setStatus(status);
			System.err.println(lilcustomer.get());
			// lilcustomer.get().setUsertype(usertype);
			// LilCustomer l2=new LilCustomer(phone,imageurl,lastname,status);
			LilCustomer lil3 = lilCustomerRepository.save(lilcustomer.get());
			responseBean.setMessage("phone number updated");
			responseBean.setResponsecode(SecurityHttpStatus.ACCEPTED);
			responseBean.setTiemstamp(System.currentTimeMillis());
			return new ResponseEntity<ResponseBean>(responseBean,  HttpStatus.ACCEPTED);
		} else {
			responseBean.setMessage("481");
			responseBean.setResponsecode(SecurityHttpStatus.ACCEPTED);
			responseBean.setTiemstamp(System.currentTimeMillis());
			return new ResponseEntity<ResponseBean>(responseBean,  HttpStatus.ACCEPTED);
		}
	}

	@GetMapping(path = "/pp4/{page}/{count}",produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<LilCustomer>> getAllUsersPaging(@PathVariable int page, @PathVariable int count) {
		Pageable pageable = PageRequest.of(page, count);
		List<LilCustomer> lilcustomer = lilCustomerRepository.getAllCustomersListPaging(pageable);
		if (lilcustomer.size() > 0) {
			return new ResponseEntity<List<LilCustomer>>(lilcustomer,  HttpStatus.ACCEPTED);
		} else {
			throw new UserNotFoundException(ConstantVariables.USER_NOT_FOUND);
		}
	}

	@GetMapping(path = "/pp5/{page}/{count}",produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Integer> getUsersPageNumbers(@PathVariable int page, @PathVariable int count) {
		Pageable pageable = PageRequest.of(page, count);
		Page<LilCustomer> p1 = lilCustomerRepository.getallcustomerspagingcount(pageable);
		return new ResponseEntity<Integer>(p1.getTotalPages(),  HttpStatus.ACCEPTED);
	}

	@GetMapping(path=URLMapping.GET_USERS_MAPPING_PATH,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<LilCustomer>> getAllUsers() {
		List<LilCustomer> lilcustomer = (List<LilCustomer>) lilCustomerRepository.findAll();
		if (lilcustomer.size() > 0) {
			return new ResponseEntity<List<LilCustomer>>(lilcustomer,  HttpStatus.ACCEPTED);
		} else {
			throw new UserNotFoundException(ConstantVariables.USER_NOT_FOUND);
		}
	}

	@GetMapping(path=URLMapping.USER_MAPPING_PATH_PATH_VARIABLE_EMAIL,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Optional<LilCustomer>> getSelectedUser(@PathVariable String email) {
		Optional<LilCustomer> lilcustomer = lilCustomerRepository.findByEmail(email);
		if (!lilcustomer.isPresent()) {
			throw new UserNotFoundException(ConstantVariables.USER_NOT_FOUND);

		} else {
			return new ResponseEntity<Optional<LilCustomer>>(lilcustomer,  HttpStatus.ACCEPTED);
		}
	}

	@GetMapping(path=URLMapping.USER_MAPPING_UNIQUEID,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Optional<LilCustomer>> getSelectedUserID(@PathVariable String uniqueid) {
		Optional<LilCustomer> lilcustomer = lilCustomerRepository.findByUniqueid(uniqueid);
		if (!lilcustomer.isPresent()) {
			throw new UserNotFoundException(ConstantVariables.USER_NOT_FOUND);

		} else {
			return new ResponseEntity<Optional<LilCustomer>>(lilcustomer,  HttpStatus.ACCEPTED);
		}
	}
	
	@GetMapping(path=URLMapping.EMAIL_VALIDATION,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Optional<LilCustomer>> validateEmail(@PathVariable String email,@PathVariable long phone) {
		Optional<LilCustomer> lilcustomer = lilCustomerRepository.EmailValidation(email, phone);
		
		if (lilcustomer.isPresent()) {
			return new ResponseEntity<Optional<LilCustomer>>(lilcustomer,  HttpStatus.ACCEPTED);

		} else {
			
			throw new UserNotFoundException(ConstantVariables.USER_NOT_FOUND);
		}
	}

}
