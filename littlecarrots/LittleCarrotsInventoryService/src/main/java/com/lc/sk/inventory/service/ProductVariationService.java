package com.lc.sk.inventory.service;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lc.sk.inventory.bean.ProductFullDetails;
import com.lc.sk.inventory.bean.ProductVariationFullInfo;
import com.lc.sk.inventory.bean.ProductidsBean;
import com.lc.sk.inventory.bean.ResponseBean;
import com.lc.sk.inventory.entities.Productvariations;
import com.lc.sk.inventory.entities.Similarproducts;
import com.lc.sk.inventory.exception.subexception.DBInsertException;
import com.lc.sk.inventory.exception.subexception.NullRequestReceivedException;
import com.lc.sk.inventory.repositories.ProductVariationRepository;
import com.lc.sk.inventory.repositories.ProductsRepository;
import com.lc.sk.inventory.repositories.SimilarproductsRepository;
import com.lc.sk.inventory.util.ConstantValues;
import com.lc.sk.inventory.util.SecurityHttpStatus;
import com.lc.sk.inventory.util.URLMapping;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Validated
@RequestMapping(path = URLMapping.INVENTORY_ROOT_PATH)
public class ProductVariationService {

	

	
	@Autowired
	ProductVariationRepository productVariationRepo;
	
	@Autowired
	SimilarproductsRepository similarproductRepo;
	
	@Autowired
	ProductsRepository productsrepository;
	
	@PostMapping(path="/pvr1", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> insert(		
	@RequestParam(name = "pvtype", required = true, defaultValue = ConstantValues.DEFAULT_INT) String pvtype){
		Productvariations pv = new Productvariations(pvtype);
		pv = this.productVariationRepo.save(pv);
		if(pv.getPvtype().equals(pvtype)) {
			return new ResponseEntity<ResponseBean>(new ResponseBean("Product Variation Type added",SecurityHttpStatus.ACCEPTED , System.currentTimeMillis()),   HttpStatus.ACCEPTED);
		}else
		{
			throw new DBInsertException(ConstantValues.DATA_NOT_INSERTED_IN_DB);
		}
	}
	
	@GetMapping(path="/pvr1", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Productvariations>> getAll(){
		List<Productvariations> pv = this.productVariationRepo.getAllVariations();
		if(pv.size()>0) {
			return new ResponseEntity<List<Productvariations>>(pv,   HttpStatus.ACCEPTED);
		}
		else {
			throw new DBInsertException("Data Not Found");
		}
	}
	
	
	@PostMapping(path="/pvsm1", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> insertSimilarity(
			@RequestParam(name = "pvid", required=true, defaultValue=ConstantValues.DEFAULT_INT)long pvid,
			@RequestParam(name="productid[]") long productid[]){
		List<Similarproducts> sp = new CopyOnWriteArrayList<Similarproducts>();
		if(pvid!=0 && productid.length!=0) {
			for(Long prid: productid) {
				sp.add(new Similarproducts(pvid, prid));
			}
			this.similarproductRepo.saveAll(sp);
			return new ResponseEntity<ResponseBean>(new ResponseBean(ConstantValues.DATA_INSERTED_IN_DB, SecurityHttpStatus.ACCEPTED, System.currentTimeMillis()),   HttpStatus.ACCEPTED);
		}
		else {
			throw new NullRequestReceivedException(ConstantValues.RECEIVED_NULL_VALUES);
		}
	}
	
	@GetMapping(path="/pvsm1", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Similarproducts>> getAllSimilarProducts(){
		List<Similarproducts> sp = this.similarproductRepo.getAllSimilarProducts();
		if(sp.size()>0) {
			return new ResponseEntity<List<Similarproducts>>(sp,   HttpStatus.ACCEPTED );
		}else
		{
			throw new DBInsertException("Data Not Found");
		}
	}
	
	@GetMapping(path="/pvsm1/{productid}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Similarproducts>> getAllSimilarProductsbyid(@PathVariable long productid){
		List<Similarproducts> sp = this.similarproductRepo.getAllSimilarProductsbyid(productid);
		if(sp.size()>0) {
			return new ResponseEntity<List<Similarproducts>>(sp,   HttpStatus.ACCEPTED );
		}else
		{
			throw new DBInsertException("Data Not Found");
		}
	}
	
	@GetMapping(path="/pvsm12/{pvid}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Similarproducts>> getAllSimilarProductsbypvid(@PathVariable long pvid){
		List<Similarproducts> sp = this.similarproductRepo.getAllSimilarProductsbypvid(pvid);
		if(sp.size()>0) {
			return new ResponseEntity<List<Similarproducts>>(sp,   HttpStatus.ACCEPTED );
		}else
		{
			throw new DBInsertException("Data Not Found");
		}
	}
	
	@GetMapping(path="/pvc/{productid}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<ProductVariationFullInfo>> getAllProductInformation(@PathVariable long productid){
		List<ProductVariationFullInfo> pvi =  new CopyOnWriteArrayList<ProductVariationFullInfo>();
		List<Similarproducts> sp = this.similarproductRepo.getAllSimilarProductsbyid(productid);
		//System.out.println(sp);
		if(sp.size()>0) {
			for(Similarproducts ex:sp) {
			List<Similarproducts> sp1 = this.similarproductRepo.getAllSimilarProductsbypvid(ex.getPvid());
			List<Long> prids = new ArrayList<>();
			Set<Long> pridx = new HashSet<>();
			for(Similarproducts v1: sp1) {
				pridx.add(v1.getProductid());
			}
			for(Long s:pridx) {
				prids.add(s);
			}
				List<ProductFullDetails> products = this.productsrepository.getSelectedProductsBylimproducts(prids);
				//System.out.println(products);
				for(ProductFullDetails i:products) 
				{
						
					pvi.add(new ProductVariationFullInfo(i.getProductid(),i.getAgeid(),i.getSizeid(),i.getColorhashcode()));
				}
			}
			return new ResponseEntity<List<ProductVariationFullInfo>>(pvi,  HttpStatus.ACCEPTED);
		}else
		{
			throw new DBInsertException("Data Not Found");
		}
	}
	
	@GetMapping(path="/pvp", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<ProductidsBean>> getAllProductids(){
		List<ProductidsBean> pv = this.productVariationRepo.getAllProductids();
		if(pv.size()>0) {
			return new ResponseEntity<List<ProductidsBean>>(pv,   HttpStatus.ACCEPTED);
		}
		else {
			throw new DBInsertException("Data Not Found");
		}
	}
	
}
