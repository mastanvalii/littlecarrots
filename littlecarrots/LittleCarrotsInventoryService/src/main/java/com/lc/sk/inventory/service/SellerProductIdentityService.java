package com.lc.sk.inventory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.ResponseBody;

import com.lc.sk.inventory.bean.ResponseBean;
import com.lc.sk.inventory.entities.Rackidentity;
import com.lc.sk.inventory.entities.Racks;
import com.lc.sk.inventory.entities.Sellerproductidentity;
import com.lc.sk.inventory.exception.subexception.DBInsertException;
import com.lc.sk.inventory.exception.subexception.ProductQuantityException;
import com.lc.sk.inventory.exception.subexception.SelectedproductsException;
import com.lc.sk.inventory.repositories.RackIdentityRepository;
import com.lc.sk.inventory.repositories.RacksRepository;
import com.lc.sk.inventory.repositories.SellerProductIdentityRepository;
import com.lc.sk.inventory.util.ConstantValues;
import com.lc.sk.inventory.util.SecurityHttpStatus;
import com.lc.sk.inventory.util.URLMapping;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Validated
@RequestMapping(path = URLMapping.INVENTORY_ROOT_PATH)
public class SellerProductIdentityService {

	@Autowired
	SellerProductIdentityRepository spir;
	
	@Autowired
	RacksRepository rackRepo;
	
	@Autowired
	RackIdentityRepository rackIdentityRepo;
	
	/* SellerProductIdentityRepository code start here */
	
	@GetMapping(path=URLMapping.SELLER_PRODUCT_IDENTITY_LIST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Sellerproductidentity>> getAllSellerProductIdList()
	{
		List<Sellerproductidentity> obj = (List<Sellerproductidentity>) spir.findAll();
		if(!obj.isEmpty()) {
			return new ResponseEntity<List<Sellerproductidentity>>(obj, HttpStatus.ACCEPTED);
		}else {
			throw new SelectedproductsException(ConstantValues.NO_PRODUCTS_LIST_FOUND);
		}
	}
	
	@PostMapping(path=URLMapping.SELLER_PRODUCT_IDENTITY_LIST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> insertData(
			@RequestParam(name="skuid", required=true,defaultValue="0")long skuid,
			@RequestParam(name="amazonid", required=true,defaultValue="0")String amazonid,
			@RequestParam(name="flipkartid", required=true,defaultValue="0")String flipkartid,
			@RequestParam(name="meeshoid", required=true,defaultValue="0")String meeshoid,
			@RequestParam(name="paytmid", required=true,defaultValue="0")String paytmid,
			@RequestParam(name="s1", required=true,defaultValue="0")String s1,
			@RequestParam(name="s2", required=true,defaultValue="0")String s2,
			@RequestParam(name="s3", required=true,defaultValue="0")String s3
			){
		Sellerproductidentity obj = new Sellerproductidentity(flipkartid,amazonid,meeshoid,paytmid,s1,s2,s3,skuid);
		Sellerproductidentity obj1 = spir.save(obj);
		
		if(obj1!=null&&obj1.getSkuid()==obj.getSkuid()) 
		{
			ResponseBean responseBean = new ResponseBean(ConstantValues.DATA_INSERTED_IN_DB,
					SecurityHttpStatus.ACCEPTED, System.currentTimeMillis());
			return new ResponseEntity<ResponseBean>(responseBean,   HttpStatus.ACCEPTED);
		} else {
			throw new DBInsertException(ConstantValues.DATA_NOT_INSERTED_IN_DB);
		}
	}
	
	
	@PutMapping(path=URLMapping.SELLER_PRODUCT_IDENTITY_LIST, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> updateData(
			@RequestParam(name="uid", required=true, defaultValue="0")long uid,
			@RequestParam(name="skuid", required=true,defaultValue="0")long skuid,
			@RequestParam(name="amazonid", required=true,defaultValue="0")String amazonid,
			@RequestParam(name="flipkartid", required=true,defaultValue="0")String flipkartid,
			@RequestParam(name="meeshoid", required=true,defaultValue="0")String meeshoid,
			@RequestParam(name="paytmid", required=true,defaultValue="0")String paytmid,
			@RequestParam(name="s1", required=true,defaultValue="0")String s1,
			@RequestParam(name="s2", required=true,defaultValue="0")String s2,
			@RequestParam(name="s3", required=true,defaultValue="0")String s3
			){
		Optional<Sellerproductidentity> existingData = spir.findById(uid);
		System.err.println(existingData.get().toString());
		if(existingData.isPresent()) {
			existingData.get().setAmazonid(amazonid);
			existingData.get().setFlipkartid(flipkartid);
			existingData.get().setMeeshoid(meeshoid);
			existingData.get().setPaytmid(paytmid);
			existingData.get().setS1(s1);
			existingData.get().setS2(s2);
			existingData.get().setS3(s3);
			Sellerproductidentity obj1 = spir.save(existingData.get());
			
			if(obj1!=null&&obj1.getSkuid()==existingData.get().getSkuid()) 
			{
				ResponseBean responseBean = new ResponseBean(ConstantValues.DATA_INSERTED_IN_DB,
						SecurityHttpStatus.ACCEPTED, System.currentTimeMillis());
				return new ResponseEntity<ResponseBean>(responseBean,   HttpStatus.ACCEPTED);
			} else {
				throw new DBInsertException(ConstantValues.DATA_NOT_INSERTED_IN_DB);
			}
		}else {
			throw new ProductQuantityException(ConstantValues.NO_PRODUCTS_LIST_FOUND);
		}
		
	}
	
	/* SellerProductIdentityRepository code end here */
	
	
	/* Rack Repository code start here */
	
	@GetMapping(path=URLMapping.RACK_DETAILS, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Racks>> getAllRackList()
	{
		List<Racks> obj = (List<Racks>) rackRepo.findAll();
		if(!obj.isEmpty()) {
			return new ResponseEntity<List<Racks>>(obj, HttpStatus.ACCEPTED);
		}else {
			throw new SelectedproductsException(ConstantValues.NO_PRODUCTS_LIST_FOUND);
		}
	}
	
	@PostMapping(path=URLMapping.RACK_DETAILS, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> insertData(
			@RequestParam(name="rackid", required=true,defaultValue="0")String rackid
			){
		Racks obj = new Racks(rackid.toUpperCase());
		Racks obj1 = rackRepo.save(obj);
		
		if(obj1!=null&&obj1.getRackid().equals(obj.getRackid())) 
		{
			ResponseBean responseBean = new ResponseBean(ConstantValues.DATA_INSERTED_IN_DB,
					SecurityHttpStatus.ACCEPTED, System.currentTimeMillis());
			return new ResponseEntity<ResponseBean>(responseBean,   HttpStatus.ACCEPTED);
		} else {
			throw new DBInsertException(ConstantValues.DATA_NOT_INSERTED_IN_DB);
		}
	}
	
	
	/* Rack Repository code end here */
	
	
	
	/*Rack Identity Repository code start here */
	@GetMapping(path=URLMapping.RACK_IDENTITY_DETAILS, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Rackidentity>> getAllRackIdentityList()
	{
		List<Rackidentity> obj = (List<Rackidentity>) rackIdentityRepo.findAll();
		if(!obj.isEmpty()) {
			return new ResponseEntity<List<Rackidentity>>(obj, HttpStatus.ACCEPTED);
		}else {
			throw new SelectedproductsException(ConstantValues.NO_PRODUCTS_LIST_FOUND);
		}
	}
	
	@PostMapping(path=URLMapping.RACK_IDENTITY_DETAILS, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> insertData(
			@RequestParam(name="rackid", required=true,defaultValue="0")String rackid,
			@RequestParam(name="skuid", required=true,defaultValue="0")String skuid
			){
		Rackidentity obj = new Rackidentity(rackid.toUpperCase(), skuid );
		Rackidentity obj1 = rackIdentityRepo.save(obj);
		
		if(obj1!=null&&obj1.getRackid().equals(obj.getRackid())) 
		{
			ResponseBean responseBean = new ResponseBean(ConstantValues.DATA_INSERTED_IN_DB,
					SecurityHttpStatus.ACCEPTED, System.currentTimeMillis());
			return new ResponseEntity<ResponseBean>(responseBean,   HttpStatus.ACCEPTED);
		} else {
			throw new DBInsertException(ConstantValues.DATA_NOT_INSERTED_IN_DB);
		}
	}
	
	
	@PutMapping(path=URLMapping.RACK_IDENTITY_DETAILS, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> updateData(
			@RequestParam(name="rackid", required=true,defaultValue="0")String rackid,
			@RequestParam(name="skuid", required=true,defaultValue="0")String skuid,
			@RequestParam(name="uid", required=true,defaultValue="0")long uid
			){
		Optional<Rackidentity> existingData = rackIdentityRepo.findById(uid);
		System.err.println(existingData.get().toString());
		if(existingData.isPresent()) {
			existingData.get().setRackid(rackid.toUpperCase());
			existingData.get().setSkuid(skuid);

			Rackidentity obj1 = rackIdentityRepo.save(existingData.get());
			
			if(obj1!=null&&obj1.getSkuid()==existingData.get().getSkuid()) 
			{
				ResponseBean responseBean = new ResponseBean(ConstantValues.DATA_INSERTED_IN_DB,
						SecurityHttpStatus.ACCEPTED, System.currentTimeMillis());
				return new ResponseEntity<ResponseBean>(responseBean,   HttpStatus.ACCEPTED);
			} else {
				throw new DBInsertException(ConstantValues.DATA_NOT_INSERTED_IN_DB);
			}
		}else {
			throw new ProductQuantityException(ConstantValues.NO_PRODUCTS_LIST_FOUND);
		}
		
	}
	/*Rack Identity Repository code end here */
}
