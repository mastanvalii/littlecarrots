package com.lc.sk.inventory.security.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lc.sk.inventory.security.beans.PageCountBean;
import com.lc.sk.inventory.security.beans.ProductFullDetails;
import com.lc.sk.inventory.security.beans.ProductFullDetailsWithImage;
import com.lc.sk.inventory.security.cache.Partition;
import com.lc.sk.inventory.security.cache.ProductCacheLoader;
import com.lc.sk.inventory.security.cache.SearchBean;
import com.lc.sk.inventory.security.exceptions.subexceptions.DataNotFoundException;
import com.lc.sk.inventory.security.util.ConstantValues;
import com.lc.sk.inventory.security.util.CustomerURLMapping;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Validated
@RequestMapping(value = CustomerURLMapping.FEP + CustomerURLMapping.VERSION)
public class FrontEndPoint {

	@Autowired
	ProductCacheLoader pcl;
	
	
	@GetMapping(path=CustomerURLMapping.CT099 , produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<ProductFullDetailsWithImage>> getAllDetails()
	{
		return new ResponseEntity<List<ProductFullDetailsWithImage>>(pcl.getAllProducts(),  HttpStatus.ACCEPTED );
	}
	
	@GetMapping(path=CustomerURLMapping.CT099_1 , produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ProductFullDetailsWithImage> getProductDetails(@PathVariable Long productid)
	{
		return new ResponseEntity<ProductFullDetailsWithImage>(pcl.getByProductID(productid),  HttpStatus.ACCEPTED );
	}
	
	@PostMapping(path=CustomerURLMapping.SEARCH001, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<ProductFullDetailsWithImage>> getSearchData(@RequestBody SearchBean sb,@PathVariable int page, @PathVariable int count){
		//ProductFullDetailsWithImage
		
		return new ResponseEntity<List<ProductFullDetailsWithImage>>(getPagesData(sb,page,count),  HttpStatus.ACCEPTED );
	}
	
	@PostMapping(path=CustomerURLMapping.SEARCH000, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<PageCountBean> getSearchDataCount(@RequestBody SearchBean sb,@PathVariable int page, @PathVariable int count){
		
		
		return new ResponseEntity<PageCountBean>(getPagesCount(sb,page,count),  HttpStatus.ACCEPTED );
	}
	
	
	public List<ProductFullDetailsWithImage> getPagesData(SearchBean sb, int page, int count){
		System.out.println("_--------------------------RECEIVED DATA--------------------------");
		System.err.println(sb.toString());
		System.out.println("------------------------------------------------------------------");
		List<List<ProductFullDetailsWithImage>> data1=null;
		List<ProductFullDetailsWithImage> data = null;
		try {
		data = pcl.getProductsBySearch(sb);
		data = data.stream()
                .collect( java.util.stream.Collectors.collectingAndThen( java.util.stream.Collectors.toCollection(() -> new TreeSet<>(java.util.Comparator.comparing(ProductFullDetailsWithImage::getProductid))),
                        ArrayList::new));
		data.removeIf((info)->Integer.parseInt(info.getQuantity())==0);
		data1 =Partition.ofSize(data, count);
		
		}catch(IndexOutOfBoundsException e) {
			throw new DataNotFoundException(ConstantValues.NO_PRODUCTS_FOUND);
		}
		return data1.get(page);
	}
	
	public PageCountBean getPagesCount(SearchBean sb, int page, int count){
		
		List<ProductFullDetailsWithImage> data = pcl.getProductsBySearch(sb);
		data.removeIf((info)->Integer.parseInt(info.getQuantity())==0);
		List<List<ProductFullDetailsWithImage>> data1 =Partition.ofSize(data, count);
		return new PageCountBean(data1.size());
	}
	
}
