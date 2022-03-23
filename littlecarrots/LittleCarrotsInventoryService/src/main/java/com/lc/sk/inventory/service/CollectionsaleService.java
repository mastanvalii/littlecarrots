package com.lc.sk.inventory.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lc.sk.inventory.bean.CollectionSalePack;
import com.lc.sk.inventory.bean.CollectionSalePack1;
import com.lc.sk.inventory.bean.CollectionSalewithProductIds;
import com.lc.sk.inventory.bean.ResponseBean;
import com.lc.sk.inventory.entities.Collectionsale;
import com.lc.sk.inventory.entities.CollectionsaleImages;
import com.lc.sk.inventory.entities.Collectionsaleprod;
import com.lc.sk.inventory.exception.subexception.CollectionSaleException;
import com.lc.sk.inventory.exception.subexception.NullRequestReceivedException;
import com.lc.sk.inventory.repositories.CollectionsaleImagesRepository;
import com.lc.sk.inventory.repositories.CollectionsaleRepository;
import com.lc.sk.inventory.repositories.CollectionsaleprodRepository;
import com.lc.sk.inventory.util.ConstantValues;
import com.lc.sk.inventory.util.SecurityHttpStatus;
import com.lc.sk.inventory.util.URLMapping;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Validated
@RequestMapping(path = URLMapping.INVENTORY_ROOT_PATH)
public class CollectionsaleService {

	@Autowired
	CollectionsaleRepository collectionsale;
	@Autowired
	CollectionsaleprodRepository collectionsaleprod;
	@Autowired
	CollectionsaleImagesRepository collectionsaleimgrepo;
	
	

	
	@GetMapping(path = URLMapping.COLLECTIONSALE_MAPPING_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Collectionsale>> getAllCollectionsale() {
		List<Collectionsale> collection = (List<Collectionsale>) this.collectionsale.getCollectionsale();
		if (!collection.isEmpty()) {
			return new ResponseEntity<>(collection,  HttpStatus.ACCEPTED);

		} else {
			throw new CollectionSaleException(ConstantValues.COLLECTIONSALE_NOT_FOUND);
		}

	}

	@GetMapping(path = URLMapping.COLLECTIONSALE_MAPPING_PATH_WITH_PATH_VARIABLE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Optional<Collectionsale>> getAllCollectionByIdList(@PathVariable long id) {
		Optional<Collectionsale> collection = this.collectionsale.getCollectionsalebyid(id);
		if (!collection.isPresent()||collection.get()==null) {
			
			throw new CollectionSaleException(ConstantValues.COLLECTIONSALE_NOT_FOUND);
		} else {
			return new ResponseEntity<Optional<Collectionsale>>(collection,  HttpStatus.ACCEPTED);
		}

	}
	
	@GetMapping(path = URLMapping.COLLECTIONSALE_MAPPING_PATH_BY_GENDER, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<CollectionSalewithProductIds>> getAllCollectionList(@PathVariable String genderid) {
		List<CollectionSalewithProductIds> collection = this.collectionsale.getCollectionsalebygenderid(genderid);
		if (collection.isEmpty()) {
			
			throw new CollectionSaleException(ConstantValues.COLLECTIONSALE_NOT_FOUND);
		} else {
			return new ResponseEntity<List<CollectionSalewithProductIds>>(collection,  HttpStatus.ACCEPTED);
		}

	}
	

	@GetMapping(path = URLMapping.COLLECTIONSALE_BY_GENDER_VIEW, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<CollectionSalePack>> getAllCollectionList1(@PathVariable String genderid, @PathVariable String siteview) {
		List<CollectionSalePack> collection = this.collectionsale.getCollectionSalesInformation(genderid);
		if (collection.isEmpty()) {		
			
			throw new CollectionSaleException(ConstantValues.COLLECTIONSALE_NOT_FOUND);
		} else {			
			return new ResponseEntity<List<CollectionSalePack>>(collection,  HttpStatus.ACCEPTED);
		}

	}	
	
	@GetMapping(path = URLMapping.COLLECTIONSALE_BY_LANDING_VIEW, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<CollectionSalePack1> getAllCollectionList12(@PathVariable long id, @PathVariable String siteview,@PathVariable String genderid) {
		List<Collectionsaleprod> collection = this.collectionsaleprod.getCollectionsaleprodById(id);
		CollectionSalePack1 csp = new CollectionSalePack1();
		if (collection.isEmpty()) {		
			
			throw new CollectionSaleException(ConstantValues.COLLECTIONSALE_NOT_FOUND);
		} else {	
			CollectionsaleImages collection1 = this.collectionsaleimgrepo.getCollectionsaleImagesByIDS(id);
			if(collection1==null) {
				throw new CollectionSaleException(ConstantValues.COLLECTIONSALE_NOT_FOUND);
			}
			else {
				Optional<Collectionsale> collection2 = this.collectionsale.getCollectionsalebyid(id);
				if(!collection2.isPresent()||collection2.get()==null) {
					throw new CollectionSaleException(ConstantValues.COLLECTIONSALE_NOT_FOUND);
				}
				else {
					
					csp.setId(id);
					List<Long> prids = new ArrayList<>();
					for(Collectionsaleprod cs: collection) {
						prids.add(cs.getProductid());
					}
					csp.setProductids(prids);
					csp.setBadge(collection2.get().getBadge());
					csp.setDisplay(collection2.get().getDisplay());
					csp.setStartdate(collection2.get().getStartdate());
					csp.setEnddate(collection2.get().getEnddate());
					csp.setTitle(collection2.get().getTitle());
					csp.setGenderid(collection2.get().getGenderid());
					csp.setIcon(collection1.getIcon());
					csp.setMobileview1(collection1.getMobileview1());
					csp.setMovileview2(collection1.getMobileview2());
					csp.setDesktopview1(collection1.getDesktopview1());
					csp.setDesktopview2(collection1.getDesktopview2());
			
				}

			}
			//return new ResponseEntity<List<CollectionSalePack>>(collection, hearders.getHeader(), HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<CollectionSalePack1>(csp,  HttpStatus.ACCEPTED);

	}	
	
	
	//Post method 
	@PostMapping(path = URLMapping.COLLECTIONSALE_MAPPING_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> insertCollection(
			@RequestParam(name = ConstantValues.TITLE, required=true, defaultValue=ConstantValues.DEFAULT_STRING)String title,
			@RequestParam(name = ConstantValues.GENDERID, required=true, defaultValue=ConstantValues.DEFAULT_STRING) String genderid,
			
			@RequestParam(name = ConstantValues.BADGE, required = true, defaultValue = ConstantValues.DEFAULT_STRING) String badge,
			@RequestParam(name = ConstantValues.DISPLAY, required = true, defaultValue = ConstantValues.DEFAULT_STRING) String display,
			@RequestParam(name = ConstantValues.START_DATE, required = true, defaultValue = ConstantValues.DEFAULT_STRING) String startdate,
			@RequestParam(name = ConstantValues.END_DATE, required = true, defaultValue = ConstantValues.DEFAULT_STRING) String enddate
	) 
	{

		ResponseBean responseBean=null;
		
		if (title.equals(ConstantValues.DEFAULT_STRING)||genderid.equals(ConstantValues.DEFAULT_STRING)||startdate.equals(ConstantValues.DEFAULT_STRING)
				|| enddate.equals(ConstantValues.DEFAULT_STRING)
				|| badge.equals(ConstantValues.DEFAULT_STRING) || display.equals(ConstantValues.DEFAULT_STRING)) {
			throw new NullRequestReceivedException(ConstantValues.RECEIVED_NULL_VALUES);
		} else {
		
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
			Collectionsale col= this.collectionsale.save(new Collectionsale(title,genderid,badge,display,startdate_1,enddate_1));
			
		if(col.getTitle().equals(title)) {
				responseBean = new ResponseBean(ConstantValues.DATA_INSERTED_IN_DB,
						SecurityHttpStatus.ACCEPTED, System.currentTimeMillis());
		}else
				{
			responseBean = new ResponseBean("No collections inserted",4000, System.currentTimeMillis());
				}
				return new ResponseEntity<ResponseBean>(responseBean,  HttpStatus.ACCEPTED);
				
		}

	}
	
	@GetMapping(path= URLMapping.LATESTCOLLECTION, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<CollectionSalePack>> getAllGenderSalePage(){
		List<CollectionSalePack> collection = this.collectionsale.getAllGenderSalesInformation();
		if (collection.isEmpty()) {		
			
			throw new CollectionSaleException(ConstantValues.COLLECTIONSALE_NOT_FOUND);
		} else {			
			return new ResponseEntity<List<CollectionSalePack>>(collection,  HttpStatus.ACCEPTED);
		}
	}
	
	
	/* for customer phase code */
	
	@GetMapping(path= "/iconmenu", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<CollectionSalePack>> getAllGenderSalePage1(){
		List<CollectionSalePack> collection = this.collectionsale.getAllGenderSalesInformation1();
		if (collection.isEmpty()) {		
			
			throw new CollectionSaleException(ConstantValues.COLLECTIONSALE_NOT_FOUND);
		} else {			
			return new ResponseEntity<List<CollectionSalePack>>(collection,  HttpStatus.ACCEPTED);
		}
	}
	
	@GetMapping(path= "/view1menu/{siteview}/{genderid}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<CollectionSalePack>> getSalePage1SiteVue(@PathVariable String siteview, @PathVariable String genderid){
		List<CollectionSalePack> collection = null;
		if(siteview.equals("MV1")) {
			collection = this.collectionsale.getAllSalesInformationMobileView1(genderid);
		}else if(siteview.equals("DV1")) {
			collection = this.collectionsale.getAllSalesInformationDesktopView1(genderid);
		}
		 
		if (collection.isEmpty()) {		
			
			throw new CollectionSaleException(ConstantValues.COLLECTIONSALE_NOT_FOUND);
		} else {			
			return new ResponseEntity<List<CollectionSalePack>>(collection,  HttpStatus.ACCEPTED);
		}
	}
	
	
	@GetMapping(path = URLMapping.COLLECTIONSALE_BY_LANDING_VIEW1, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<CollectionSalePack1> getAllCollectionListForCustomerPhase(@PathVariable long id, @PathVariable String siteview) {
		List<Collectionsaleprod> collection = this.collectionsaleprod.getCollectionsaleprodById(id);
		CollectionSalePack1 csp = new CollectionSalePack1();
		if (collection.isEmpty()) {		
			
			throw new CollectionSaleException(ConstantValues.COLLECTIONSALE_NOT_FOUND);
		} else {	
			CollectionsaleImages collection1 = this.collectionsaleimgrepo.getCollectionsaleImagesByIDS(id);
			
			if(collection1==null) {
				throw new CollectionSaleException(ConstantValues.COLLECTIONSALE_NOT_FOUND);
			}
			else {
				if(siteview.equals("MV2")) {
					collection1.setIcon(null);
					collection1.setDesktopview1(null);
					collection1.setDesktopview2(null);
					collection1.setMobileview1(null);
				}else if(siteview.equals("DV2")){
					collection1.setIcon(null);
					collection1.setDesktopview1(null);				
					collection1.setMobileview1(null);
					collection1.setMobileview2(null);				
				}
				Optional<Collectionsale> collection2 = this.collectionsale.getCollectionsalebyid(id);
				if(!collection2.isPresent()||collection2.get()==null) {
					throw new CollectionSaleException(ConstantValues.COLLECTIONSALE_NOT_FOUND);
				}
				else {
					
					csp.setId(id);
					List<Long> prids = new ArrayList<>();
					for(Collectionsaleprod cs: collection) {
						prids.add(cs.getProductid());
					}
					csp.setProductids(prids);
					csp.setBadge(collection2.get().getBadge());
					csp.setDisplay(collection2.get().getDisplay());
					csp.setStartdate(collection2.get().getStartdate());
					csp.setEnddate(collection2.get().getEnddate());
					csp.setTitle(collection2.get().getTitle());
					csp.setGenderid(collection2.get().getGenderid());
					csp.setIcon(collection1.getIcon());
					csp.setMobileview1(collection1.getMobileview1());
					csp.setMovileview2(collection1.getMobileview2());
					csp.setDesktopview1(collection1.getDesktopview1());
					csp.setDesktopview2(collection1.getDesktopview2());
			
				}

			}
			//return new ResponseEntity<List<CollectionSalePack>>(collection, hearders.getHeader(), HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<CollectionSalePack1>(csp,  HttpStatus.ACCEPTED);

	}	

}
