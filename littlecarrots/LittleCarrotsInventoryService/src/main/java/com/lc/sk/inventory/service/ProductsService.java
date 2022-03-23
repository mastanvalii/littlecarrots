package com.lc.sk.inventory.service;

import java.util.ArrayList;
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

import com.lc.sk.inventory.bean.CollectionSalewithProductIds;
import com.lc.sk.inventory.bean.ProductFullDetails;
import com.lc.sk.inventory.bean.ResponseBean;
import com.lc.sk.inventory.bean.UnFilledProductsBean;
import com.lc.sk.inventory.entities.Approval;
import com.lc.sk.inventory.entities.Products;
import com.lc.sk.inventory.entities.Selectedproducts;
import com.lc.sk.inventory.exception.subexception.BatchException;
import com.lc.sk.inventory.exception.subexception.DBDataNotUpdatedException;
import com.lc.sk.inventory.exception.subexception.DBInsertException;

import com.lc.sk.inventory.exception.subexception.NullRequestReceivedException;
import com.lc.sk.inventory.exception.subexception.ProductException;
import com.lc.sk.inventory.repositories.ApprovalRepository;
import com.lc.sk.inventory.repositories.CollectionsaleRepository;
import com.lc.sk.inventory.repositories.ProductsRepository;
import com.lc.sk.inventory.repositories.SelectedproductsRepository;
import com.lc.sk.inventory.util.ConstantValues;
import com.lc.sk.inventory.util.URLMapping;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Validated
@RequestMapping(path = URLMapping.INVENTORY_ROOT_PATH)

public class ProductsService {
	@Autowired
	ProductsRepository productsrepository;

	@Autowired
	SelectedproductsRepository selectedprodrepo;
	
	@Autowired
	CollectionsaleRepository collectionsale;


	
	@Autowired
	ApprovalRepository approvalRepository;

	public ProductsService() {

	}

	@GetMapping(path = URLMapping.PRODUCTS_MAPPING_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Products>> getallProducts() {
		List<Products> products = (List<Products>) this.productsrepository.findAll();
		if (products.isEmpty()) {
			throw new ProductException(ConstantValues.NO_PRODUCTS_FOUND);

		} else {
			return new ResponseEntity<List<Products>>(products,   HttpStatus.ACCEPTED);
		}
	}

	// Get by id
	@GetMapping(path = URLMapping.PRODUCTS_MAPPING_PATH_ID, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Optional<Products>> getProductId(@PathVariable Long productid) {
		Optional<Products> products = this.productsrepository.findById(productid);
		if (products.isPresent()) {
			return new ResponseEntity<Optional<Products>>(products,   HttpStatus.ACCEPTED);
		} else {
			throw new ProductException(ConstantValues.NO_PRODUCTS_FOUND);
		}
	}

	@PostMapping(path = URLMapping.PRODUCTS_MAPPING_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> addList(

			@RequestParam(name = ConstantValues.DESCRIPTION_ID, required = true, defaultValue = ConstantValues.DEFAULT_INT) long descriptionid,
			@RequestParam(name = ConstantValues.BATCH_ID, required = true, defaultValue = ConstantValues.DEFAULT_INT) long batchid,
			@RequestParam(name = ConstantValues.GENDER_ID, required = true, defaultValue = ConstantValues.DEFAULT_STRING) String genderid,
			@RequestParam(name = ConstantValues.CATEGORY_ID, required = true, defaultValue = ConstantValues.DEFAULT_INT) long catid,
			@RequestParam(name = ConstantValues.SUBCATID, required = true, defaultValue = ConstantValues.DEFAULT_INT) long subcatid,
			@RequestParam(name = ConstantValues.SEASON_ID, required = true, defaultValue = ConstantValues.DEFAULT_INT) long seasonid,
			@RequestParam(name = ConstantValues.OCCASION_ID, required = true, defaultValue = ConstantValues.DEFAULT_INT) int occasionid,
			@RequestParam(name = ConstantValues.AGE_ID, required = true, defaultValue = ConstantValues.DEFAULT_STRING) String ageid,
			@RequestParam(name = ConstantValues.MATERIAL_ID, required = true, defaultValue = ConstantValues.DEFAULT_INT) long materialid,
			@RequestParam(name = ConstantValues.COLOR_ID, required = true, defaultValue = ConstantValues.DEFAULT_INT) long colorid,
			@RequestParam(name = ConstantValues.CUSTOMER_ID, required = true, defaultValue = ConstantValues.DEFAULT_INT) long custid,
			@RequestParam(name = ConstantValues.STATUS, required = true, defaultValue = ConstantValues.DEFAULT_BOOLEAN
					+ "") boolean status,
			@RequestParam(name = ConstantValues.TITLE, required = true, defaultValue = ConstantValues.DEFAULT_STRING) String title,
			@RequestParam(name = ConstantValues.SUB_TITLE, required = true, defaultValue = ConstantValues.DEFAULT_STRING) String subtitle) {
		ResponseBean responseBean = new ResponseBean();

		if (descriptionid == Long.parseLong(ConstantValues.DEFAULT_INT)
				|| batchid == Long.parseLong(ConstantValues.DEFAULT_INT)
				|| genderid.equals(ConstantValues.DEFAULT_STRING)
				|| catid == Long.parseLong(ConstantValues.DEFAULT_INT)
				|| subcatid == Long.parseLong(ConstantValues.DEFAULT_INT)
				|| seasonid == Long.parseLong(ConstantValues.DEFAULT_INT)
				|| occasionid == Long.parseLong(ConstantValues.DEFAULT_INT)
				|| ageid.equals(ConstantValues.DEFAULT_STRING)
				|| materialid == Long.parseLong(ConstantValues.DEFAULT_INT)
				|| colorid == Long.parseLong(ConstantValues.DEFAULT_INT)
				|| custid == Long.parseLong(ConstantValues.DEFAULT_INT)
				|| title.equals(ConstantValues.DEFAULT_STRING) || subtitle.equals(ConstantValues.DEFAULT_STRING))
		// || status.equals(ConstantValues.DEFAULT_BOOLEAN + "")
		{
			throw new NullRequestReceivedException(ConstantValues.RECEIVED_NULL_VALUES);
		} else {

			Products products = new Products(descriptionid, batchid, genderid, catid, subcatid, seasonid, occasionid,
					ageid, materialid, colorid, custid, status, title, subtitle);
			products = this.productsrepository.save(products);
			if (products.getDescriptionid() == (descriptionid)) {
				java.sql.Timestamp orderdate = new java.sql.Timestamp(new java.util.Date().getTime());
				System.err.println("Order Date:"+orderdate);
			//	SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yy");
				
				orderdate.toLocalDateTime();
				Approval ap = new Approval(this.productsrepository.getProductidByDesid(descriptionid), "shaikmastanvali41",orderdate, false, "New Product", "New Product");
				this.approvalRepository.save(ap);
				responseBean.setMessage(ConstantValues.PRODUCTS_INSERTION_SUCCESS);
				responseBean.setResponsecode(HttpStatus.ACCEPTED.value());
				responseBean.setTiemstamp(System.currentTimeMillis());
				
				return new ResponseEntity<ResponseBean>(responseBean,   HttpStatus.ACCEPTED);
			} else {
				throw new DBInsertException(ConstantValues.PRODUCTS_INSERTION_HAS_ISSUE);
			}

		}

	}

	@PutMapping(path = URLMapping.PRODUCTS_MAPPING_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> update(
			@RequestParam(name = ConstantValues.PRODUCT_ID, required = true, defaultValue = ConstantValues.DEFAULT_INT) long productid,
			@RequestParam(name = ConstantValues.DESCRIPTION_ID, required = true, defaultValue = ConstantValues.DEFAULT_INT) long descriptionid,
			@RequestParam(name = ConstantValues.BATCH_ID, required = true, defaultValue = ConstantValues.DEFAULT_INT) long batchid,
			@RequestParam(name = ConstantValues.GENDER_ID, required = true, defaultValue = ConstantValues.DEFAULT_STRING) String genderid,
			@RequestParam(name = ConstantValues.CATEGORY_ID, required = true, defaultValue = ConstantValues.DEFAULT_INT) long catid,
			@RequestParam(name = ConstantValues.SUBCATID, required = true, defaultValue = ConstantValues.DEFAULT_INT) long subcatid,
			@RequestParam(name = ConstantValues.SEASON_ID, required = true, defaultValue = ConstantValues.DEFAULT_INT) long seasonid,
			@RequestParam(name = ConstantValues.OCCASION_ID, required = true, defaultValue = ConstantValues.DEFAULT_INT) int occasionid,
			@RequestParam(name = ConstantValues.AGE_ID, required = true, defaultValue = ConstantValues.DEFAULT_STRING) String ageid,
			@RequestParam(name = ConstantValues.MATERIAL_ID, required = true, defaultValue = ConstantValues.DEFAULT_INT) long materialid,
			@RequestParam(name = ConstantValues.COLOR_ID, required = true, defaultValue = ConstantValues.DEFAULT_INT) long colorid,
			@RequestParam(name = ConstantValues.CUSTOMER_ID, required = true, defaultValue = ConstantValues.DEFAULT_INT) long custid,
			@RequestParam(name = ConstantValues.STATUS, required = true, defaultValue = ConstantValues.DEFAULT_BOOLEAN
					+ "") boolean status,
			@RequestParam(name = ConstantValues.TITLE, required = true, defaultValue = ConstantValues.DEFAULT_STRING) String title,
			@RequestParam(name = ConstantValues.SUB_TITLE, required = true, defaultValue = ConstantValues.DEFAULT_STRING) String subtitle) {
		if (productid == Long.parseLong(ConstantValues.DEFAULT_INT)
				|| descriptionid == Long.parseLong(ConstantValues.DEFAULT_INT)
				|| batchid == Long.parseLong(ConstantValues.DEFAULT_INT)
				|| genderid.equals(ConstantValues.DEFAULT_STRING)
				|| catid == Long.parseLong(ConstantValues.DEFAULT_INT)
				|| subcatid == Long.parseLong(ConstantValues.DEFAULT_INT)
				|| seasonid == Long.parseLong(ConstantValues.DEFAULT_INT)
				|| occasionid == Long.parseLong(ConstantValues.DEFAULT_INT)
				|| ageid.equals(ConstantValues.DEFAULT_STRING)
				|| materialid == Long.parseLong(ConstantValues.DEFAULT_INT)
				|| colorid == Long.parseLong(ConstantValues.DEFAULT_INT)
				|| custid == Long.parseLong(ConstantValues.DEFAULT_INT)
				|| title.equals(ConstantValues.DEFAULT_STRING) || subtitle.equals(ConstantValues.DEFAULT_STRING))
		// || status.equals(ConstantValues.DEFAULT_BOOLEAN + "")
		{
			throw new NullRequestReceivedException(ConstantValues.RECEIVED_NULL_VALUES);
		} else {
			Optional<Products> products = this.productsrepository.findById(productid);
			if (products.isPresent()) {
				products.get().setMaterialid(productid);
				products.get().setDescriptionid(descriptionid);
				products.get().setBatchid(batchid);
				products.get().setGenderid(genderid);
				products.get().setCatid(catid);
				products.get().setSubcatid(subcatid);
				products.get().setSeasonid(seasonid);
				products.get().setOccasionid(occasionid);
				products.get().setAgeid(ageid);
				products.get().setMaterialid(materialid);
				products.get().setColorid(colorid);
				products.get().setCustid(custid);
				products.get().setStatus(status);
				products.get().setTitle(title);
				products.get().setSubtitle(subtitle);
			}
			Products product = this.productsrepository.save(products.get());
			if (product.getProductid() == productid) {
				ResponseBean responseBean = new ResponseBean(ConstantValues.DATA_UPDATED_IN_DB,
						HttpStatus.ACCEPTED.value(), System.currentTimeMillis());
				return new ResponseEntity<ResponseBean>(responseBean,   HttpStatus.ACCEPTED);
			} else {
				throw new DBDataNotUpdatedException(ConstantValues.DATA_NOT_UPDATED_IN_DB);
			}
		}
	}

	@PutMapping(path = URLMapping.PRODUCTS_MAPPING_PATH_WITH_PATH_VARIABLE_1, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> enableBatchStatus(@PathVariable long productid, @PathVariable boolean status) {
		Optional<Products> products = this.productsrepository.findById(productid);

		if (products.isPresent()) {
			if (products.get().getBatchid() != 0) {
				products.get().setStatus(status);
				Products product = this.productsrepository.save(products.get());
				if (product.getStatus() == status) {
					ResponseBean responseBean = new ResponseBean(ConstantValues.DATA_UPDATED_IN_DB,
							HttpStatus.ACCEPTED.value(), System.currentTimeMillis());
					return new ResponseEntity<ResponseBean>(responseBean,   HttpStatus.ACCEPTED);
				} else {
					throw new DBInsertException(ConstantValues.DATA_NOT_UPDATED_IN_DB);
				}

			} else {
				throw new BatchException(ConstantValues.BATCH_DETAILS_NOT_FOUND);
			}
		} else {
			throw new ProductException(ConstantValues.PRODUCT_NOT_FOUND);
		}
	}

	@GetMapping(path = URLMapping.PRODUCT_COUNT_LIST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Long> getAllProductCount() {
		return new ResponseEntity<Long>(this.productsrepository.getProductCountDetails(),  
				HttpStatus.ACCEPTED);
	}

	@GetMapping(path = URLMapping.OUTOF_STOCK_PRODUCTS, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Long> getOutofStockProductsCount() {
		return new ResponseEntity<Long>(this.productsrepository.outofStockProducts(),  
				HttpStatus.ACCEPTED);
	}

	@GetMapping(path = URLMapping.LOW_STOCK_PRODUCTS, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Long> getLowStockProducts() {
		return new ResponseEntity<Long>(this.productsrepository.lowStockProducts(),  
				HttpStatus.ACCEPTED);
	}

	@GetMapping(path = URLMapping.PAT_URL, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<UnFilledProductsBean>> getDataPatterns() {
		List<UnFilledProductsBean> obj = this.productsrepository.getDatafromPatterns();
		if (obj.size() > 0) {
			return new ResponseEntity<List<UnFilledProductsBean>>(obj,   HttpStatus.ACCEPTED);
		} else {
			throw new ProductException(ConstantValues.PRODUCT_NOT_FOUND);
		}
	}

	@GetMapping(path = URLMapping.PRI_URL, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<UnFilledProductsBean>> getDataPrices() {
		List<UnFilledProductsBean> obj = this.productsrepository.getDatafromPrice();
		if (obj.size() > 0) {
			return new ResponseEntity<List<UnFilledProductsBean>>(obj,   HttpStatus.ACCEPTED);
		} else {
			throw new ProductException(ConstantValues.PRODUCT_NOT_FOUND);
		}
	}

	@GetMapping(path = URLMapping.PIE_URL, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<UnFilledProductsBean>> getDataPieces() {
		List<UnFilledProductsBean> obj = this.productsrepository.getDatafromPieces();
		if (obj.size() > 0) {
			return new ResponseEntity<List<UnFilledProductsBean>>(obj,   HttpStatus.ACCEPTED);
		} else {
			throw new ProductException(ConstantValues.PRODUCT_NOT_FOUND);
		}
	}

	@GetMapping(path = URLMapping.PRODUCTS_MAPPING_PATH_COLLECTIONS, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<ProductFullDetails>> getProductId(@PathVariable long catid, @PathVariable String season,
			@PathVariable String occaName) {
		List<ProductFullDetails> products = this.productsrepository.getCollection(catid, season, occaName);
		if (!products.isEmpty()) {
			return new ResponseEntity<List<ProductFullDetails>>(products,   HttpStatus.ACCEPTED);
		} else {
			throw new ProductException(ConstantValues.NO_PRODUCTS_FOUND);
		}
	}

	@GetMapping(path = URLMapping.PRODUCTS_MAPPING_PATH_SELECTEDPRODUCTS, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<ProductFullDetails>> getProductId(@PathVariable String genderid) {
		List<Selectedproducts> sp = this.selectedprodrepo.getSelectedProductsByGender(genderid);
		if (sp.size() > 0) {
			List<Long> sb = new ArrayList<>();
			for(Selectedproducts s: sp) {
				sb.add(s.getProductid());
				//sb.append(",");
			}
			//prs = sb.toString();
			//prs = prs.substring(0, prs.length()-1);
			
			List<ProductFullDetails> products = this.productsrepository.getSelectedProductsBylimproducts(sb);
			if (!products.isEmpty()) {
				return new ResponseEntity<List<ProductFullDetails>>(products,  
						HttpStatus.ACCEPTED);
			} else {
				throw new ProductException(ConstantValues.NO_PRODUCTS_FOUND);
			}
		} else {
			throw new ProductException(ConstantValues.NO_PRODUCTS_FOUND);
		}
	}
	
	
	//COLLECTION PRODUCT INFORMATION
	
	  @GetMapping(path = URLMapping.PRODUCTS_MAPPING_PATH_COLLECTIONS_PRODUCT_IDS, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<ProductFullDetails>> getCOLLECTIONPRODUCTS(@PathVariable String genderid) {
		List<CollectionSalewithProductIds> collection = this.collectionsale.getCollectionsalebygenderid(genderid);
		if (collection.size() > 0) {
			//String prs = "";
			List<Long> sb = new ArrayList<>();
			for(CollectionSalewithProductIds s: collection) {
				sb.add(s.getProductid());
				//sb.append(",");
			
			}
			//prs = sb.toString();
			//prs = prs.substring(0, prs.length()-1);
			List<ProductFullDetails> products = this.productsrepository.getSelectedProductsBylimproducts(sb);
			if (!products.isEmpty()) {
				return new ResponseEntity<List<ProductFullDetails>>(products,  
						HttpStatus.ACCEPTED);
			} else {
				throw new ProductException(ConstantValues.NO_PRODUCTS_FOUND);
			}
		} else {
			throw new ProductException(ConstantValues.NO_PRODUCTS_FOUND);
		}
	}
	
	  
	  @GetMapping(path = URLMapping.PRODUCTS_MAPPING_PATH_SELECTEDPRODUCTS_MULTISELECT, produces = MediaType.APPLICATION_JSON_VALUE)
		@ResponseBody
		public ResponseEntity<List<ProductFullDetails>> getProductIdformultiselect(@PathVariable String genderid) {
			List<ProductFullDetails> products = this.productsrepository.getSelectedproductsformultiselect(genderid);
			if (!products.isEmpty()) {
				return new ResponseEntity<List<ProductFullDetails>>(products,   HttpStatus.ACCEPTED);
			} else {
				throw new ProductException(ConstantValues.NO_PRODUCTS_FOUND);
			}
		}

	
	  @GetMapping(path = URLMapping.PRODUCTS_MAPPING_PATH_SIMILARPRODUCTS_MULTISELECT, produces = MediaType.APPLICATION_JSON_VALUE)
		@ResponseBody
		public ResponseEntity<List<ProductFullDetails>> getProductIdformultiselectSimilarProducts() {
			List<ProductFullDetails> products = this.productsrepository.getProductsForMultiselectInSimilarProd();
			if (!products.isEmpty()) {
				return new ResponseEntity<List<ProductFullDetails>>(products,   HttpStatus.ACCEPTED);
			} else {
				throw new ProductException(ConstantValues.NO_PRODUCTS_FOUND);
			}
		}

}
