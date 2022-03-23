package com.lc.sk.inventory.service;

import java.util.List;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lc.sk.inventory.bean.ApprovalBean;
import com.lc.sk.inventory.bean.PageCountBean;
import com.lc.sk.inventory.bean.ProductFullDetails;
import com.lc.sk.inventory.cache.ProductFullCache;
import com.lc.sk.inventory.exception.subexception.ProductException;
import com.lc.sk.inventory.repositories.ProductPagingRepository;
import com.lc.sk.inventory.repositories.ProductsRepository;
import com.lc.sk.inventory.repositories.SimilarproductsRepository;
import com.lc.sk.inventory.util.ConstantValues;
import com.lc.sk.inventory.util.NumberToTextConvert;
import com.lc.sk.inventory.util.URLMapping;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Validated
@RequestMapping(path = URLMapping.INVENTORY_ROOT_PATH)
public class ProductPagingService {


	
	@Autowired ProductPagingRepository productPagingRepository;
	
	@Autowired ProductsRepository productRepository;
	
	@Autowired
	SimilarproductsRepository similarproductRepo;
	
	@Autowired
	ProductFullCache pfc;
	
	
	@GetMapping(path = URLMapping.PAGEABLE1, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<ProductFullDetails>> getAllDetails(@PathVariable int page, @PathVariable int count){
		Pageable pageable = PageRequest.of(page, count);
		List<ProductFullDetails> pf = this.productPagingRepository.getAllProductDetails(pageable);
		return new ResponseEntity<List<ProductFullDetails>>(pf,   HttpStatus.ACCEPTED);
	}

	
	@GetMapping(path = URLMapping.PAGEABLE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Integer> getPageCount(@PathVariable int page, @PathVariable int count){
		Pageable pageable = PageRequest.of(page, count);
		Page<ProductFullDetails> pf = this.productPagingRepository.getPageCount(pageable);
	//	pf.getTotalPages()
		return new ResponseEntity<Integer>(pf.getTotalPages(),   HttpStatus.ACCEPTED);
	}
	
	@GetMapping(path = "/pp2/{page}/{count}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<ApprovalBean>> getApprovals(@PathVariable int page, @PathVariable int count){
		Pageable pageable = PageRequest.of(page, count);
		List<ApprovalBean> p = this.productPagingRepository.getAllApprovals(pageable);
	//	pf.getTotalPages()
		return new ResponseEntity<List<ApprovalBean>>(p,   HttpStatus.ACCEPTED);
	}
	
	
	@GetMapping(path = "/pp3/{page}/{count}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Integer> getApprovalsPageNumbers(@PathVariable int page, @PathVariable int count){
		Pageable pageable = PageRequest.of(page, count);
		Page<ApprovalBean> p1 = this.productPagingRepository.getAllApprovalsPageNo(pageable);
	//	pf.getTotalPages()
		return new ResponseEntity<Integer>(p1.getTotalPages(),   HttpStatus.ACCEPTED);
	}
	
	
	@GetMapping(path = URLMapping.SEARCH0, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Integer> getAllSearchDetails(@PathVariable int page, @PathVariable int count, @PathVariable String search, @PathVariable String dummy){
		Pageable pageable = PageRequest.of(page, count);
		Page<ProductFullDetails> pf = this.productPagingRepository.getSearchProductsPageCount(pageable, search);
			return new ResponseEntity<Integer>(pf.getTotalPages(),   HttpStatus.ACCEPTED);
	}
	
	
	@GetMapping(path = URLMapping.SEARCH1, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<ProductFullDetails>> getAllSearchDetails(@PathVariable String search,@PathVariable int des){
		search = NumberToTextConvert.checkNumbers(search);
		List<ProductFullDetails> pf = this.productRepository.getSearchProducts(search,des);
		if(!pf.isEmpty()) {
			return new ResponseEntity<List<ProductFullDetails>>(pf,   HttpStatus.ACCEPTED);
		}else {
			throw new ProductException(ConstantValues.NO_PRODUCTS_FOUND);
		}
		
	}
	
	
	/* load data on scroll */
	@SuppressWarnings("boxing")
	@GetMapping(path= URLMapping.SCROOL_SEARCH_COUNT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<PageCountBean> getSearchQueryCount(@PathVariable String query, @PathVariable int page, @PathVariable int count){
	//	Pageable pages = PageRequest.of(page, count);
	//	System.err.println("CALLING GET SEARCH QUERY COUNT METHOD....");
		return new ResponseEntity<PageCountBean>(new PageCountBean(this.productPagingRepository.getSearchProductsPageCount(PageRequest.of(page, count), query).getTotalPages()),   HttpStatus.ACCEPTED);
	}
	
	@GetMapping(path=URLMapping.SCROLL_SEARCH_DATA, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<ProductFullDetails>> getSearchQueryByPage(@PathVariable String query, @PathVariable int page, @PathVariable int count){
	//	Pageable pages = PageRequest.of(page, count);
	//	System.err.println("CALLING GET SEARCH QUERY BY PAGE METHOD....");
		return new ResponseEntity<List<ProductFullDetails>>(this.productPagingRepository.getSearchProducts(PageRequest.of(page, count), query),   HttpStatus.ACCEPTED);
	}
	
	
	@GetMapping(path=URLMapping.FROM_CACHE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<ProductFullDetails>> getSearchQueryByPage1(){
	//	return new ResponseEntity<List<ProductFullDetails>>(this.productPagingRepository.getForCache(),   HttpStatus.ACCEPTED);
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		System.err.println("Reading products from Inventory Cache.....");
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
	//	return new ResponseEntity<List<ProductFullDetails>>(this.pfc.getAllDetails(),   HttpStatus.ACCEPTED);
		return new ResponseEntity<List<ProductFullDetails>>(this.pfc.getAllProductsForClient(),   HttpStatus.ACCEPTED);
	}
	
	
}
