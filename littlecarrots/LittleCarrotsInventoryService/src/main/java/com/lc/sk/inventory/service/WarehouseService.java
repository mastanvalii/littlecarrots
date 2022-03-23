/**
 * 
 */
package com.lc.sk.inventory.service;

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

import com.lc.sk.inventory.bean.ResponseBean;
import com.lc.sk.inventory.entities.Warehouses;
import com.lc.sk.inventory.exception.subexception.DBDataNotUpdatedException;
import com.lc.sk.inventory.exception.subexception.DBInsertException;
import com.lc.sk.inventory.exception.subexception.NullRequestReceivedException;
import com.lc.sk.inventory.exception.subexception.WarehouseNotFoundExcpetion;
import com.lc.sk.inventory.repositories.WarehousesRepository;
import com.lc.sk.inventory.util.ConstantValues;
import com.lc.sk.inventory.util.SecurityHttpStatus;
import com.lc.sk.inventory.util.URLMapping;

/**
 * @author Mastanvali Shaik LittleCarrotsInventoryService 2020
 */

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Validated
@RequestMapping(path = URLMapping.INVENTORY_ROOT_PATH)
public class WarehouseService {

	@Autowired
	WarehousesRepository warehousesRepository;



	/**
	 * 
	 */
	public WarehouseService() {
		super();
	}

	/* Get All warehouse list */
	@GetMapping(path = URLMapping.GET_ALL_WAREHOUSE_LIST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Warehouses>> getAllWarehouses() {
		List<Warehouses> warehouses = (List<Warehouses>) warehousesRepository.getAllWarehouses();
		if (warehouses.isEmpty()) {
			throw new WarehouseNotFoundExcpetion(ConstantValues.EMPTY_WAREHOUSE_LIST);
		} else {
			return new ResponseEntity<List<Warehouses>>(warehouses,   HttpStatus.ACCEPTED);
		}

	}

	/* warehouse details by id */
	@GetMapping(path = URLMapping.GET_ALL_WAREHOUSE_LIST_BY_ID, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Optional<Warehouses>> getById(@PathVariable long warehouseid) {
		Optional<Warehouses> warehouse = warehousesRepository.getWarehouseById(warehouseid);
		if (!warehouse.isPresent()||warehouse.get() == null) {
			throw new WarehouseNotFoundExcpetion(ConstantValues.WAREHOUSE_NOT_FOUND_WITH_GIVEN_ID);
		} else {
			return new ResponseEntity<Optional<Warehouses>>(warehouse,   HttpStatus.ACCEPTED);
		}
	}

	/* insert new warehouse to db */
	@PostMapping(path = URLMapping.GET_ALL_WAREHOUSE_LIST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> insertNewWarehouse(
			@RequestParam(name = ConstantValues.WAREHOUSE_NAME, required = true, defaultValue = ConstantValues.DEFAULT_STRING) String warehousename,
			@RequestParam(name = ConstantValues.CONTACT_PERSON_NAME, required = true, defaultValue = ConstantValues.DEFAULT_STRING) String contactpersonname,
			@RequestParam(name = ConstantValues.CONTACT_PERSON_PHONE, required = true, defaultValue = ConstantValues.DEFAULT_INT) long contactphone,
			@RequestParam(name = ConstantValues.WAREHOUSE_EMAIL_ADDRESS, required = true, defaultValue = ConstantValues.DEFAULT_STRING) String email,
			@RequestParam(name = ConstantValues.WAREHOUSE_ADDRESS, required = true, defaultValue = ConstantValues.DEFAULT_STRING) String address,
			@RequestParam(name = ConstantValues.WHAREHOUSE_PIN, required = true, defaultValue = ConstantValues.DEFAULT_INT) long pincode,
			@RequestParam(name = ConstantValues.WAREHOUSE_EST, required = true, defaultValue = ConstantValues.DEFAULT_INT) long est,
			@RequestParam(name = ConstantValues.WAREHOUSE_STATUS, required = true, defaultValue = ConstantValues.DEFAULT_BOOLEAN+"") boolean status,
			@RequestParam(name = ConstantValues.WHO_CREATED, required = true, defaultValue = ConstantValues.DEFAULT_STRING) String username,
			@RequestParam(name = ConstantValues.ISOCODE, required = true, defaultValue = ConstantValues.DEFAULT_STRING) String isocode) {
		ResponseBean responseBean = new ResponseBean();
		if (warehousename.equals(ConstantValues.DEFAULT_STRING)
				|| contactpersonname.equals(ConstantValues.DEFAULT_STRING)
				|| contactphone == Long.parseLong(ConstantValues.DEFAULT_INT)
				|| email.equals(ConstantValues.DEFAULT_STRING) || address.equals(ConstantValues.DEFAULT_STRING)
				|| pincode == Long.parseLong(ConstantValues.DEFAULT_INT)
				|| est == Integer.parseInt(ConstantValues.DEFAULT_INT)				
				|| username.equals(ConstantValues.DEFAULT_STRING) || isocode.equals(ConstantValues.DEFAULT_STRING)) {
			throw new NullRequestReceivedException(ConstantValues.RECEIVED_NULL_VALUES);
		} else {
			java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
			Warehouses entity = new Warehouses(warehousename, contactpersonname, contactphone, email, address, pincode,
					est, status, username, date, username, date, isocode);
			entity = warehousesRepository.save(entity);
			if (entity.getWarehousename().equals(warehousename)) {
				responseBean.setMessage(ConstantValues.WAREHOUSE_INSERTION_SUCCESS);
				responseBean.setResponsecode(SecurityHttpStatus.ACCEPTED);
				responseBean.setTiemstamp(System.currentTimeMillis());
				return new ResponseEntity<ResponseBean>(responseBean,   HttpStatus.ACCEPTED);
			} else {
				throw new DBInsertException(ConstantValues.WAREHOUSE_INSERTION_HAS_ISSUE);
			}

		}

	}

	/* warehouse details udpate by id */
	@PutMapping(path = URLMapping.GET_ALL_WAREHOUSE_LIST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> update(
			@RequestParam(name = ConstantValues.WAREHOUSE_ID, required = true) long warehouseid,
			@RequestParam(name = ConstantValues.WAREHOUSE_NAME, required = true, defaultValue = ConstantValues.DEFAULT_STRING) String warehousename,
			@RequestParam(name = ConstantValues.CONTACT_PERSON_NAME, required = true, defaultValue = ConstantValues.DEFAULT_STRING) String contactpersonname,
			@RequestParam(name = ConstantValues.CONTACT_PERSON_PHONE, required = true, defaultValue = ConstantValues.DEFAULT_INT) long contactphone,
			@RequestParam(name = ConstantValues.WAREHOUSE_EMAIL_ADDRESS, required = true, defaultValue = ConstantValues.DEFAULT_STRING) String email,
			@RequestParam(name = ConstantValues.WAREHOUSE_ADDRESS, required = true, defaultValue = ConstantValues.DEFAULT_STRING) String address,
			@RequestParam(name = ConstantValues.WHAREHOUSE_PIN, required = true, defaultValue = ConstantValues.DEFAULT_INT) long pincode,
			@RequestParam(name = ConstantValues.WAREHOUSE_EST, required = true, defaultValue = ConstantValues.DEFAULT_INT) long est,
			@RequestParam(name = ConstantValues.WAREHOUSE_STATUS, required = true, defaultValue = ConstantValues.DEFAULT_BOOLEAN+"") boolean status,
			@RequestParam(name = ConstantValues.WHO_UPDATED, required = true, defaultValue = ConstantValues.DEFAULT_STRING) String username,
			@RequestParam(name = ConstantValues.ISOCODE, required = true, defaultValue = ConstantValues.DEFAULT_STRING) String isocode) {
		
		if (warehouseid == Long.parseLong(ConstantValues.DEFAULT_INT)
				||warehousename.equals(ConstantValues.DEFAULT_STRING)
				|| contactpersonname.equals(ConstantValues.DEFAULT_STRING)
				|| contactphone == Long.parseLong(ConstantValues.DEFAULT_INT)
				|| email.equals(ConstantValues.DEFAULT_STRING) || address.equals(ConstantValues.DEFAULT_STRING)
				|| pincode == Long.parseLong(ConstantValues.DEFAULT_INT)
				|| est == Integer.parseInt(ConstantValues.DEFAULT_INT)				
				|| username.equals(ConstantValues.DEFAULT_STRING) || isocode.equals(ConstantValues.DEFAULT_STRING)) {
			throw new NullRequestReceivedException(ConstantValues.RECEIVED_NULL_VALUES);
		} else {
		ResponseBean responseBean = new ResponseBean();
		Optional<Warehouses> warehouse = warehousesRepository.findById(warehouseid);
		if (warehouse.get() == null) {
			throw new WarehouseNotFoundExcpetion(ConstantValues.WAREHOUSE_NOT_FOUND_WITH_GIVEN_ID);
		} else {
			warehouse.get().setWarehousename(warehousename);
			warehouse.get().setContactpersonname(contactpersonname);
			warehouse.get().setContactphone(contactphone);
			warehouse.get().setEmail(email);
			warehouse.get().setAddress(address);
			warehouse.get().setPincode(pincode);
			warehouse.get().setEst(est);
			warehouse.get().setWarehouse_status(status);
			warehouse.get().setWhoupdated(username);
			java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
			warehouse.get().setModifydate(date);
			warehouse.get().setIsocode(isocode);
			Warehouses wareHouse = warehousesRepository.save(warehouse.get());
			if (wareHouse.getWarehousename().equals(warehouse.get().getWarehousename())) {
				responseBean.setMessage(ConstantValues.WAREHOUSE_UPDATE_SUCCESS);
				responseBean.setResponsecode(SecurityHttpStatus.ACCEPTED);
				responseBean.setTiemstamp(System.currentTimeMillis());
				return new ResponseEntity<ResponseBean>(responseBean,   HttpStatus.ACCEPTED);
			} else {
				throw new DBDataNotUpdatedException(ConstantValues.WAREHOUSE_UPDATE_FAIL);
			}

		}}
	}
}
