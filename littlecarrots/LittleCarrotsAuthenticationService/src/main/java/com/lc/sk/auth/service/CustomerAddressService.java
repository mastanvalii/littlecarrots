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

import com.lc.sk.auth.bean.CustADDBean;
import com.lc.sk.auth.entities.CustomerAddress;
import com.lc.sk.auth.entities.LilCustomer;
import com.lc.sk.auth.exceptions.subexceptions.AddressNotFoundException;
import com.lc.sk.auth.exceptions.subexceptions.DBValueInsertException;
import com.lc.sk.auth.exceptions.subexceptions.NoAddressLinkedException;
import com.lc.sk.auth.exceptions.subexceptions.NullRequestReceivedException;
import com.lc.sk.auth.rbeans.ResponseBean;
import com.lc.sk.auth.repositories.CustomerAddressRepository;
import com.lc.sk.auth.repositories.LilCustomerRepository;
import com.lc.sk.auth.util.ConstantVariables;
import com.lc.sk.auth.util.HeaderComponent;
import com.lc.sk.auth.util.SecurityHttpStatus;
import com.lc.sk.auth.util.URLMapping;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Validated
@RequestMapping(path = URLMapping.AUTHORIZATION_ROOT_PATH)
public class CustomerAddressService { // NO_UCD (unused code)

	@Autowired
	CustomerAddressRepository customerAddressRepository;
	

	
	public CustomerAddressService(){
		super();
	}
	
	@GetMapping(path=URLMapping.CUSTOMER_ADDRESS,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<CustomerAddress>> getAllAddress() {
		List<CustomerAddress> address = (List<CustomerAddress>) customerAddressRepository.getAllAddress();
		if (!address.isEmpty()) {
			return new ResponseEntity<List<CustomerAddress>>(address,  HttpStatus.ACCEPTED);
		} else {
			throw new AddressNotFoundException(ConstantVariables.ADDRESS_NOT_FOUND);
		}
	}
	
	@GetMapping(path=URLMapping.CUSTOMER_ADDRESS_PATH_PATH_ID,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Optional<CustomerAddress>> getSelectedSellers(@PathVariable Long addressid) {
		Optional<CustomerAddress> add = customerAddressRepository.getAdressById(addressid);
		if (!add.isPresent()) {
			throw new AddressNotFoundException(ConstantVariables.ADDRESS_NOT_FOUND);
			
		} else {
			return new ResponseEntity<Optional<CustomerAddress>>(add,  HttpStatus.ACCEPTED);
		}
	}

	
	@GetMapping(path=URLMapping.CUSTOMER_ADDRESS_PATH_PATH_VARIABLE,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<CustomerAddress>> getAddress(@PathVariable String email) {
		List<CustomerAddress> customer = customerAddressRepository.getAdressByEmail(email);
		if (customer.isEmpty()) {
			throw new NoAddressLinkedException(ConstantVariables.NO_EMAIL_LINKED_TO_THE_ADDRESS);
			
		} else {
			return new ResponseEntity<List<CustomerAddress>>(customer,  HttpStatus.ACCEPTED);
		}
	}
	
	
	@Autowired
	LilCustomerRepository lilCustomerRepository;
	
	@PostMapping(path=URLMapping.CUSTOMER_ADDRESS,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<CustADDBean> insertCustomerAddress(
		@RequestParam(name=ConstantVariables.NAME,required= true, defaultValue=ConstantVariables.DEFAULT_STRING_VALUE)String name,
		@RequestParam(name=ConstantVariables.EMAIL,required=true,defaultValue=ConstantVariables.DEFAULT_STRING_VALUE)String email,
		@RequestParam(name=ConstantVariables.FLAT,required=true,defaultValue=ConstantVariables.DEFAULT_STRING_VALUE)String flat,
		@RequestParam(name=ConstantVariables.STREET_ADDRESS,required=true,defaultValue=ConstantVariables.DEFAULT_STRING_VALUE)String streetaddress,
		@RequestParam(name=ConstantVariables.LAND_MARK,required=true,defaultValue=ConstantVariables.DEFAULT_STRING_VALUE)String landmark,
		@RequestParam(name=ConstantVariables.PINCODE,required=true,defaultValue=ConstantVariables.DEFAULT_INT_VALUE)long pincode,
		@RequestParam(name=ConstantVariables.CITY,required=true,defaultValue=ConstantVariables.DEFAULT_STRING_VALUE)String city,
		@RequestParam(name=ConstantVariables.STATE,required=true,defaultValue=ConstantVariables.DEFAULT_STRING_VALUE)String state,
		@RequestParam(name=ConstantVariables.COUNTRY,required=true,defaultValue=ConstantVariables.DEFAULT_STRING_VALUE)String country,
		@RequestParam(name=ConstantVariables.MOBILE1,required=true,defaultValue=ConstantVariables.DEFAULT_INT_VALUE) long mobile1,
		@RequestParam(name=ConstantVariables.MOBILE2,required=true,defaultValue=ConstantVariables.DEFAULT_INT_VALUE) long mobile2,
		@RequestParam(name=ConstantVariables.ADRESS_TYPE,required=true,defaultValue=ConstantVariables.DEFAULT_STRING_VALUE) String addresstype)	
	{
ResponseBean responseBean = new ResponseBean();

		boolean flag = false;
		
		if(!lilCustomerRepository.findByEmail(email).isPresent()) {
			LilCustomer lil = new LilCustomer(email, email, name, "", "",mobile1, "customer",false);
			lilCustomerRepository.save(lil);
		}
		Optional<LilCustomer> lilcustomer = lilCustomerRepository.findByEmail(email);
		CustADDBean bean=new CustADDBean();
		bean.setUserid(lilcustomer.get().getUserid()+"");
		if (name.equals(ConstantVariables.DEFAULT_STRING_VALUE)
				|| email.equals(ConstantVariables.DEFAULT_STRING_VALUE)
				|| flat.equals(ConstantVariables.DEFAULT_STRING_VALUE)
				|| streetaddress.equals(ConstantVariables.DEFAULT_STRING_VALUE)
				|| landmark.equals(ConstantVariables.DEFAULT_STRING_VALUE)
				|| pincode == Long.parseLong(ConstantVariables.DEFAULT_INT_VALUE)
				|| city.equals(ConstantVariables.DEFAULT_STRING_VALUE)
				|| state.equals(ConstantVariables.DEFAULT_STRING_VALUE)
				|| country.equals(ConstantVariables.DEFAULT_STRING_VALUE)
				|| mobile1 == Long.parseLong(ConstantVariables.DEFAULT_INT_VALUE)
				|| addresstype.equals(ConstantVariables.DEFAULT_STRING_VALUE)
				) {
			throw new NullRequestReceivedException(ConstantVariables.NULL_VALUES_RECEIVED_FROM_CLIENT);
		} else {
			System.out.println(name+"--"+email+"--"+flat+"--"+streetaddress+"--"+landmark+"--"+pincode+"--"+city+"--"+state+"--"+country+"--"+mobile1+"--"+mobile2+"--"+addresstype);
			CustomerAddress address=new CustomerAddress(name, email, flat, streetaddress, landmark, pincode, city, state, country, mobile1, mobile2, addresstype);
			address=customerAddressRepository.save(address);
			List<CustomerAddress> addressid = customerAddressRepository.getAdressByEmail(email);
			for(CustomerAddress efg:addressid) {
				if(efg.getPincode()==pincode) {
					bean.setAddressid(efg.getAddressid()+"");
				}
			}
			
		
		if (address.getEmail().equals(email)) {
//			responseBean.setMessage(ConstantVariables.NEW_ADDRESS_INSERTED_SUCCESSFULLY);
//			responseBean.setResponsecode(SecurityHttpStatus.ACCEPTED);
//			responseBean.setTiemstamp(System.currentTimeMillis());
//			return new ResponseEntity<ResponseBean>(responseBean,  HttpStatus.ACCEPTED);
			bean.setMsg(ConstantVariables.NEW_ADDRESS_INSERTED_SUCCESSFULLY);
			return new ResponseEntity<CustADDBean>(bean,  HttpStatus.ACCEPTED);
		} else {
			throw new DBValueInsertException(ConstantVariables.NEW_ADDRESS_INSERTED_FAILED);
		}
		}
	}
	
	@PutMapping(path=URLMapping.CUSTOMER_ADDRESS,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> updateCustomerAddress(
		@RequestParam(name=ConstantVariables.ADDRESSID,required=true,defaultValue=ConstantVariables.DEFAULT_INT_VALUE)long addressid,
		@RequestParam(name=ConstantVariables.NAME,required= true, defaultValue=ConstantVariables.DEFAULT_STRING_VALUE)String name,
		@RequestParam(name=ConstantVariables.EMAIL,required=true,defaultValue=ConstantVariables.DEFAULT_STRING_VALUE)String email,
		@RequestParam(name=ConstantVariables.FLAT,required=true,defaultValue=ConstantVariables.DEFAULT_STRING_VALUE)String flat,
		@RequestParam(name=ConstantVariables.STREET_ADDRESS,required=true,defaultValue=ConstantVariables.DEFAULT_STRING_VALUE)String streetaddress,
		@RequestParam(name=ConstantVariables.LAND_MARK,required=true,defaultValue=ConstantVariables.DEFAULT_STRING_VALUE)String landmark,
		@RequestParam(name=ConstantVariables.PINCODE,required=true,defaultValue=ConstantVariables.DEFAULT_INT_VALUE)long pincode,
		@RequestParam(name=ConstantVariables.CITY,required=true,defaultValue=ConstantVariables.DEFAULT_STRING_VALUE)String city,
		@RequestParam(name=ConstantVariables.STATE,required=true,defaultValue=ConstantVariables.DEFAULT_STRING_VALUE)String state,
		@RequestParam(name=ConstantVariables.COUNTRY,required=true,defaultValue=ConstantVariables.DEFAULT_STRING_VALUE)String country,
		@RequestParam(name=ConstantVariables.MOBILE1,required=true,defaultValue=ConstantVariables.DEFAULT_INT_VALUE) long mobile1,
		@RequestParam(name=ConstantVariables.MOBILE2,required=true,defaultValue=ConstantVariables.DEFAULT_INT_VALUE) long mobile2,
		@RequestParam(name=ConstantVariables.ADRESS_TYPE,required=true,defaultValue=ConstantVariables.DEFAULT_STRING_VALUE) String addresstype)	
	{
		if (addressid == Long.parseLong(ConstantVariables.DEFAULT_INT_VALUE)
				||name.equals(ConstantVariables.DEFAULT_STRING_VALUE)
				|| email.equals(ConstantVariables.DEFAULT_STRING_VALUE)
				|| flat.equals(ConstantVariables.DEFAULT_STRING_VALUE)
				|| streetaddress.equals(ConstantVariables.DEFAULT_STRING_VALUE)
				|| landmark.equals(ConstantVariables.DEFAULT_STRING_VALUE)
				|| pincode == Long.parseLong(ConstantVariables.DEFAULT_INT_VALUE)
				|| city.equals(ConstantVariables.DEFAULT_STRING_VALUE)
				|| state.equals(ConstantVariables.DEFAULT_STRING_VALUE)
				|| country.equals(ConstantVariables.DEFAULT_STRING_VALUE)
				|| mobile1 == Long.parseLong(ConstantVariables.DEFAULT_INT_VALUE)
				|| addresstype.equals(ConstantVariables.DEFAULT_STRING_VALUE)
				) {
			throw new NullRequestReceivedException(ConstantVariables.NULL_VALUES_RECEIVED_FROM_CLIENT);
		} else {
			ResponseBean responseBean = new ResponseBean();
			
			Optional<CustomerAddress> address = customerAddressRepository.findById(addressid);
			if (address.isPresent()) {
				address.get().setName(name);
				address.get().setEmail(email);
				address.get().setFlat(flat);
				address.get().setStreetAddress(streetaddress);
				address.get().setLandmark(landmark);
				address.get().setPincode(pincode);
				address.get().setCity(city);
				address.get().setState(state);
				address.get().setCountry(country);
				address.get().setMobile1(mobile1);
				address.get().setMobile2(mobile2);
				address.get().setAddressType(addresstype);
				
				CustomerAddress customer = customerAddressRepository.save(address.get());
				if (customer.getName().equals(name)) {
					responseBean.setMessage(ConstantVariables.CUSTOMER_ADDRESS_UPDATE_SUCCESS);
					responseBean.setResponsecode(SecurityHttpStatus.ACCEPTED);
					responseBean.setTiemstamp(System.currentTimeMillis());
					return new ResponseEntity<ResponseBean>(responseBean,  HttpStatus.ACCEPTED);
				}
				else
				{
					throw new DBValueInsertException(ConstantVariables.CUSTOMER_UPDATE_FAILED);
					
				}}
			 else {
				 throw new AddressNotFoundException(ConstantVariables.ADDRESS_NOT_FOUND);
			}
			}
		}

}
