package com.lc.sk.inventory.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lc.sk.inventory.bean.ResponseBean;
import com.lc.sk.inventory.entities.Selectedproducts;
import com.lc.sk.inventory.exception.subexception.NullRequestReceivedException;
import com.lc.sk.inventory.exception.subexception.SelectedproductsException;
import com.lc.sk.inventory.repositories.SelectedProductsPagingRepository;
import com.lc.sk.inventory.repositories.SelectedproductsRepository;
import com.lc.sk.inventory.util.ConstantValues;
import com.lc.sk.inventory.util.SecurityHttpStatus;
import com.lc.sk.inventory.util.URLMapping;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Validated
@RequestMapping(path = URLMapping.INVENTORY_ROOT_PATH)
public class SelectedproductsService {

	@Autowired
	SelectedproductsRepository selectedprodrepo;
	
	@Autowired
	SelectedProductsPagingRepository selectedprodpagingrepo;
	
	@GetMapping(path = URLMapping.SELECTEDPRODUCTS_MAPPING_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Selectedproducts>> getallSelectedproducts() {
		List<Selectedproducts> selprod = (List<Selectedproducts>) this.selectedprodrepo.getallSelectedproducts();
	/*	List<Selectedproducts> selpr = this.selectedprodrepo.getSelectedexpiry();
		//System.out.println(selpr);
		if(!selpr.isEmpty())
		{
			this.selectedprodrepo.deleteAll(selpr);
		}
	*/
		if (!selprod.isEmpty()) {
			return new ResponseEntity<>(selprod,   HttpStatus.ACCEPTED);

		} else {
			throw new SelectedproductsException(ConstantValues.SELECTEDPRODUCTS_NOT_FOUND);
		}

	}
	
	@GetMapping(path = URLMapping.SELECTEDPRODUCTS_MAPPING_PATH_WITH_PATH_VARIABLE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Optional<Selectedproducts>> getAllBatchList(@PathVariable long spid) {
		Optional<Selectedproducts> selprod = this.selectedprodrepo.getSelectedproductsById(spid);
		if (!selprod.isPresent()||selprod.get()==null) {
			
			throw new SelectedproductsException(ConstantValues.SELECTEDPRODUCTS_NOT_FOUND);
		} else {
			return new ResponseEntity<Optional<Selectedproducts>>(selprod,   HttpStatus.ACCEPTED);
		}

	}
	
	@PostMapping(path = URLMapping.SELECTEDPRODUCTS_MAPPING_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> insertSelectedproducts(
			@RequestParam(name = ConstantValues.GENDER_ID, required=true, defaultValue=ConstantValues.DEFAULT_INT)String genderid,
			@RequestParam("productid[]") Long productid[],
			@RequestParam(name = ConstantValues.START_DATE, required = true, defaultValue = ConstantValues.DEFAULT_STRING) String startdate,
			@RequestParam(name = ConstantValues.END_DATE, required = true, defaultValue = ConstantValues.DEFAULT_STRING) String enddate
	) 
	{
		ResponseBean responseBean=null;
		List<Selectedproducts> entities = new CopyOnWriteArrayList<Selectedproducts>();
		if (genderid.equals(ConstantValues.DEFAULT_STRING)||startdate.equals(ConstantValues.DEFAULT_STRING)
				|| enddate.equals(ConstantValues.DEFAULT_STRING)) {
			throw new NullRequestReceivedException(ConstantValues.RECEIVED_NULL_VALUES);
		} else {
			if(productid.length != 0) {
				System.out.println(productid);
			for(Long id:productid)
			{
			Date date1 = null;
			Date date2= null;
			try {
				date1 = new SimpleDateFormat("dd/MM/yyyy").parse(startdate);
				date2 = new SimpleDateFormat("dd/MM/yyyy").parse(enddate);

			} catch (ParseException e) {
				
				date1 = Date.from(Instant.parse(startdate));
				date2 = Date.from(Instant.parse(enddate));
			}
			java.sql.Timestamp startdate_1 = new java.sql.Timestamp(date1.getTime());
			java.sql.Timestamp enddate_1 = new java.sql.Timestamp(date2.getTime());
			entities.add(new Selectedproducts(genderid,id,startdate_1,enddate_1));
			}
			System.out.println(entities);
			this.selectedprodrepo.saveAll(entities);
				responseBean = new ResponseBean(ConstantValues.DATA_INSERTED_IN_DB,
						SecurityHttpStatus.ACCEPTED, System.currentTimeMillis());
		}else
				{
			responseBean = new ResponseBean("No selectedproducts inserted",4000, System.currentTimeMillis());
				}
				return new ResponseEntity<ResponseBean>(responseBean,   HttpStatus.ACCEPTED);
				
		}

	}
	
	@GetMapping(path = URLMapping.SELECTEDPRODUCTS_MAPPING_PATH_LIMIT_4, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Selectedproducts>> getallSelectedproducts4() {
		List<Selectedproducts> selprod = (List<Selectedproducts>) this.selectedprodrepo.getallSelectedproducts4();
		/*List<Selectedproducts> selpr = this.selectedprodrepo.getSelectedexpiry();
		if(!selpr.isEmpty())
		{
			this.selectedprodrepo.deleteAll(selpr);
		}*/

		if (!selprod.isEmpty()) {
			return new ResponseEntity<>(selprod,   HttpStatus.ACCEPTED);

		} else {
			throw new SelectedproductsException(ConstantValues.SELECTEDPRODUCTS_NOT_FOUND);
		}

	}
	
	
	@GetMapping(path=URLMapping.SP_PAGE_COUNT,  produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Integer> getSPPageCount(@PathVariable int page, @PathVariable int count){
		Pageable pageable = PageRequest.of(page, count);	
		Page<Selectedproducts> pf = this.selectedprodpagingrepo.getPageCount(pageable);
		
		return new ResponseEntity<Integer>(pf.getTotalPages(), HttpStatus.ACCEPTED);
	}
	
	@GetMapping(path=URLMapping.SP_PAGE_DATA,  produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Selectedproducts>> getSPPageData(@PathVariable int page, @PathVariable int count){
		Pageable pageable = PageRequest.of(page, count);

		List<Selectedproducts> products = this.selectedprodpagingrepo.getallSelectedproductsPaging(pageable);
		
		return new ResponseEntity<List<Selectedproducts>>(products, HttpStatus.ACCEPTED);
	}
	
}
