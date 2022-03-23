package com.lc.sk.inventory.security.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

import com.kafka.email.bean.CommonMail;
import com.kafka.email.bean.CommonMail1;
import com.kafka.email.bean.OrderEmailService;
import com.kafka.email.bean.OrderedData;
import com.kafka.email.bean.OrderedItems;
import com.kafka.email.bean.ProductSuggestionBean;
import com.kafka.email.bean.SuggestedProducts;
import com.lc.sk.inventory.security.beans.ApprovalPaging;
import com.lc.sk.inventory.security.beans.Blogreport;
import com.lc.sk.inventory.security.beans.BulkMailBean;
import com.lc.sk.inventory.security.beans.CalenderBean;
import com.lc.sk.inventory.security.beans.Cartmanagement;
import com.lc.sk.inventory.security.beans.CartmanagementPostBean;
import com.lc.sk.inventory.security.beans.Categories;
import com.lc.sk.inventory.security.beans.CollectionSalePack1Bean;
import com.lc.sk.inventory.security.beans.CollectionSalePackBean;
import com.lc.sk.inventory.security.beans.CollectionsaleBean;
import com.lc.sk.inventory.security.beans.ComplaintboxBean;
import com.lc.sk.inventory.security.beans.ContestBean;
import com.lc.sk.inventory.security.beans.Coupons;
import com.lc.sk.inventory.security.beans.CustAddBean;
import com.lc.sk.inventory.security.beans.CustomerAddressBean;
import com.lc.sk.inventory.security.beans.CustomerAddressPostBean;
import com.lc.sk.inventory.security.beans.EmailSubscribeBean;
import com.lc.sk.inventory.security.beans.GBlogBean;
import com.lc.sk.inventory.security.beans.GBlogBean1;
import com.lc.sk.inventory.security.beans.Genders;
import com.lc.sk.inventory.security.beans.GrannyBlog;
import com.lc.sk.inventory.security.beans.IdsUpdateBean;
import com.lc.sk.inventory.security.beans.LilCustomerBean;
import com.lc.sk.inventory.security.beans.MultiProductCartBean;
import com.lc.sk.inventory.security.beans.OrderFullDetailsSecurityBean;
import com.lc.sk.inventory.security.beans.OrderFullDetailsSecurityBean1;
import com.lc.sk.inventory.security.beans.OrderItemPostBean;
import com.lc.sk.inventory.security.beans.OrderItemsBean;
import com.lc.sk.inventory.security.beans.OrderedBeanSecurity;
import com.lc.sk.inventory.security.beans.Orderitemsstatus;
import com.lc.sk.inventory.security.beans.OrdersBean;
import com.lc.sk.inventory.security.beans.PackageshippingBean;
import com.lc.sk.inventory.security.beans.PageCountBean;
import com.lc.sk.inventory.security.beans.ProductAge;
import com.lc.sk.inventory.security.beans.ProductFullDetails;
import com.lc.sk.inventory.security.beans.ProductFullDetailsWithImage;
import com.lc.sk.inventory.security.beans.ProductVariationFullInfoBean;
import com.lc.sk.inventory.security.beans.ProductidsBean;
import com.lc.sk.inventory.security.beans.Products;
import com.lc.sk.inventory.security.beans.Rackidentity;
import com.lc.sk.inventory.security.beans.Racks;
import com.lc.sk.inventory.security.beans.Razorpay;
import com.lc.sk.inventory.security.beans.RazorpayOrderedSecurityBean;
import com.lc.sk.inventory.security.beans.RazorpayTransactionPostBean;
import com.lc.sk.inventory.security.beans.ReceiptBean;
import com.lc.sk.inventory.security.beans.ReceiptBeanSort;
import com.lc.sk.inventory.security.beans.ResponseBean;
import com.lc.sk.inventory.security.beans.Selectedproducts;
import com.lc.sk.inventory.security.beans.Sellerproductidentity;
import com.lc.sk.inventory.security.beans.WishDeleteBean;
import com.lc.sk.inventory.security.beans.WishlistBean;
import com.lc.sk.inventory.security.cache.Partition;
import com.lc.sk.inventory.security.cache.ProductCacheLoader;
import com.lc.sk.inventory.security.cache.SearchBean;
import com.lc.sk.inventory.security.entities.Notifications;
import com.lc.sk.inventory.security.exceptions.subexceptions.BackEndDataException;
import com.lc.sk.inventory.security.exceptions.subexceptions.DataNotFoundException;
import com.lc.sk.inventory.security.exceptions.subexceptions.NotificationNotFoundException;
import com.lc.sk.inventory.security.exceptions.subexceptions.RestURLReaderException;
import com.lc.sk.inventory.security.kafka.EmailProducer;
import com.lc.sk.inventory.security.kafka.OrderItemReduceProducer;
import com.lc.sk.inventory.security.repositories.NotificationsRepository;
import com.lc.sk.inventory.security.rest.BlogReportRestService;
import com.lc.sk.inventory.security.rest.CartmanagementRestService;
import com.lc.sk.inventory.security.rest.CategoriesRestService;
import com.lc.sk.inventory.security.rest.CollectionsaleRestService;
import com.lc.sk.inventory.security.rest.ComplaintBoxRestService;
import com.lc.sk.inventory.security.rest.ContestRestService;
import com.lc.sk.inventory.security.rest.CouponsRestService;
import com.lc.sk.inventory.security.rest.CustomerAddressRestService;
import com.lc.sk.inventory.security.rest.EmailSubscribeRestService;
import com.lc.sk.inventory.security.rest.GendersRestService;
import com.lc.sk.inventory.security.rest.GrannyBlogRestService;
import com.lc.sk.inventory.security.rest.ImagesResetService;
import com.lc.sk.inventory.security.rest.LilCustomerRestService;
import com.lc.sk.inventory.security.rest.OccasionalWearRestService;
import com.lc.sk.inventory.security.rest.OrderManageMentRestService;
import com.lc.sk.inventory.security.rest.PackageshippingRest;
import com.lc.sk.inventory.security.rest.ProductAgeRestService;
import com.lc.sk.inventory.security.rest.ProductPaginationRestService;
import com.lc.sk.inventory.security.rest.ProductRackSellerRestService;
import com.lc.sk.inventory.security.rest.ProductVariationRestServie;
import com.lc.sk.inventory.security.rest.ProductquantitiesRestService;
import com.lc.sk.inventory.security.rest.ProductsRestService;
import com.lc.sk.inventory.security.rest.SalesReportRestService;
import com.lc.sk.inventory.security.rest.SeasonWearRestService;
import com.lc.sk.inventory.security.rest.SelectedproductsRestService;
import com.lc.sk.inventory.security.rest.ShippingRestService;
import com.lc.sk.inventory.security.rest.WishlistRestService;
import com.lc.sk.inventory.security.util.ConstantValues;
import com.lc.sk.inventory.security.util.ConsumerURLMapping;
import com.lc.sk.inventory.security.util.CustomerURLMapping;;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Validated
@RequestMapping(value = CustomerURLMapping.CUSTOMERENDPOINT + CustomerURLMapping.VERSION)
public class CustomerEndPoint {

	@Autowired
	CategoriesRestService categoriesRestService;

	@Autowired
	ProductPaginationRestService productPaginationRestService;

	@Autowired
	GendersRestService GENDER_SERVICE;

	@Autowired
	ProductAgeRestService AGE_SERVICE;

	@Autowired
	CustomerAddressRestService CUSTADD_SERVICE;

	@Autowired
	LilCustomerRestService lilCustomerRestervice;

	@Autowired
	SelectedproductsRestService selectedproductsRestService;

	@Autowired
	ProductsRestService productsRestService;

	@Autowired
	CollectionsaleRestService collectionsaleRestService;

	@Autowired
	ShippingRestService shippingRestService;

	@Autowired
	CartmanagementRestService cartRestService;

	@Autowired
	SeasonWearRestService seasonWearRestService;

	@Autowired
	OccasionalWearRestService occasionalWearRestService;

	@Autowired
	OrderManageMentRestService orderManageMentRestService;

	@Autowired
	WishlistRestService wishlistRestService;

	@Autowired
	ProductVariationRestServie productVariationRestService;

	@Autowired
	BlogReportRestService blogreportRestService;

	@Autowired
	GrannyBlogRestService grannyblogRestService;

	@Autowired
	ComplaintBoxRestService cbr;

	@Autowired
	ProductquantitiesRestService productQuantityRestService;

	@Autowired
	PackageshippingRest packageshippingRest;

	@Autowired
	EmailSubscribeRestService emailSubscribeRestService;
	
	@Autowired
	private ImagesResetService imageResetService;
	
	@Autowired
	ProductCacheLoader pcl;
	
	@Autowired
	EmailProducer emailProducer;
	
	@Autowired
	ContestRestService contestRest;
	
	@Autowired
	CouponsRestService couponRest;
	
	@Autowired
	OrderItemReduceProducer orderItemReduceProdure;
	
	@Autowired
	NotificationsRepository notificationrepo;
	
	@Autowired
	ProductRackSellerRestService productRackSellerRestService;
	
	@Autowired
	SalesReportRestService salesReportRestService;

	// --product paging code sample
	@GetMapping(path = "pp1/{page}/{count}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<ProductFullDetailsWithImage>> getAllProducts(@PathVariable int page, @PathVariable int count) {
//		return new ResponseEntity<List<ProductFullDetails>>(
//				productPaginationRestService.getAllProductByPage(page, count),  HttpStatus.ACCEPTED);
		List<ProductFullDetails> x=productPaginationRestService.getAllProductByPage(page, count);
		List<ProductFullDetailsWithImage> fullset=new CopyOnWriteArrayList<>();
		for (ProductFullDetails y:x) {
			fullset.add(new ProductFullDetailsWithImage(y,pcl.getImageInfo(y.getProductid())));
		}
		return new ResponseEntity<List<ProductFullDetailsWithImage>>(
				fullset, 
				HttpStatus.ACCEPTED);
	}

	@GetMapping(path = "pp/{page}/{count}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Integer> getAllProductsPageCount(@PathVariable int page, @PathVariable int count) {
		return new ResponseEntity<Integer>(productPaginationRestService.getAllProductPageCount(page, count),
				 HttpStatus.ACCEPTED);
	}

	@GetMapping(path = "pp2/{page}/{count}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<ApprovalPaging>> getAllApprovals(@PathVariable int page, @PathVariable int count) {
		return new ResponseEntity<List<ApprovalPaging>>(productPaginationRestService.getAllApprovals(page, count),
				 HttpStatus.ACCEPTED);
	}

	@GetMapping(path = "pp3/{page}/{count}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Integer> getAllApprovalsPageCount(@PathVariable int page, @PathVariable int count) {
		return new ResponseEntity<Integer>(productPaginationRestService.getAllApprovalsPageCount(page, count),
				 HttpStatus.ACCEPTED);
	}

	@GetMapping(path = "pp4/{page}/{count}")
	@ResponseBody
	public ResponseEntity<List<LilCustomerBean>> getAllUsersList(@PathVariable int page, @PathVariable int count) {
		return new ResponseEntity<List<LilCustomerBean>>(lilCustomerRestervice.getAllUsersListByPage(page, count),
				 HttpStatus.ACCEPTED);
	}

	@GetMapping(path = "pp5/{page}/{count}")
	@ResponseBody
	public ResponseEntity<Integer> getAllUsersListPageCount(@PathVariable int page, @PathVariable int count) {
		return new ResponseEntity<Integer>(lilCustomerRestervice.getAllUsersPageCount(page, count), 
				HttpStatus.ACCEPTED);
	}

	// --- FILTER CODE START FROM HERE --------
	// @GetMapping

	// --- FILTER FOR CATEGORY START FROM HERE -----------

	// URL: http://localhost:8181/CUST/V1/CATE001/
	@GetMapping(path = CustomerURLMapping.CATEGORIES,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Categories>> getAllCategories() {
		return new ResponseEntity<List<Categories>>(categoriesRestService.getAllCategories(), 
				HttpStatus.ACCEPTED);
	}

	// URL: http://localhost:8181/CUST/V1/CATE001/{catid}
	@GetMapping(path = CustomerURLMapping.CATEGORIESBYID,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Categories> getCategory(@PathVariable("catid") String catid) {
		return new ResponseEntity<Categories>(categoriesRestService.getById(catid), 
				HttpStatus.ACCEPTED);
	}

	// URL: http://localhost:8181/CUST/V1/CATE001/{genderid}/{dummy}
	@GetMapping(path = CustomerURLMapping.CATEGORIESBYGENDERID,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Categories>> getCategory(@PathVariable("genderid") String genderid,
			@PathVariable("dummy") String dummy) {
		return new ResponseEntity<List<Categories>>(categoriesRestService.getByGenderId(genderid), 
				HttpStatus.ACCEPTED);
	}

	// --- FILTER FOR CATEGORY END HERE -----------

	// --- FILTER FOR SUBCATEGORY CODE START HERE --------

	// -- ALL PRODUCTS -> FASHION LIST -------------

	// -- ALL PRODUCTS -> FASHION LIST END ---------

	// --- FILTER FOR SUBCATEGORY CODE END HERE ---------

	// --- FILTER CODE HERE HERE --------

	// --- FULL CATELOGUE CODE START HERE ----

	// -- GENDER MENU CODE START HERE---
	@GetMapping(path = CustomerURLMapping.GET_GENDER_URL,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Genders>> getFullCatalogGenders() {
		return new ResponseEntity<List<Genders>>(GENDER_SERVICE.getAllGendersDetails(), 
				HttpStatus.ACCEPTED);
	}

	// -- GENDER MENU CODE END HERE---

	// -- Product age catalog menu code start here ----
	@GetMapping(path = CustomerURLMapping.GET_AGE_URL,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<ProductAge>> getFullCatalogAge() {
		return new ResponseEntity<List<ProductAge>>(AGE_SERVICE.getAll(),  HttpStatus.ACCEPTED);
	}

	// -- product age catalog menu code end here ---

	// --- FULL CATALOGUE CODE END HERE ----

	// -- SEARCH TAB --
	@GetMapping(path = CustomerURLMapping.SEARCH0,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Integer> getAllProductsSearchPageCount(@PathVariable int page, @PathVariable int count,
			@PathVariable String search, @PathVariable String dummy) {
		return new ResponseEntity<Integer>(
				productPaginationRestService.getAllProductSearchPageCount(page, count, search, dummy),
				 HttpStatus.ACCEPTED);
	}

	@GetMapping(path = CustomerURLMapping.SEARCH1,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<ProductFullDetailsWithImage>> getAllProducts(@PathVariable String search, @PathVariable int des) {
//		return new ResponseEntity<List<ProductFullDetails>>(
//				productPaginationRestService.getAllProductSearchByPage(search, des), 
//				HttpStatus.ACCEPTED);
		List<ProductFullDetails> x=productPaginationRestService.getAllProductSearchByPage(search, des);
		List<ProductFullDetailsWithImage> fullset=new CopyOnWriteArrayList<>();
		for (ProductFullDetails y:x) {
			fullset.add(new ProductFullDetailsWithImage(y,pcl.getImageInfo(y.getProductid())));
		}
		return new ResponseEntity<List<ProductFullDetailsWithImage>>(
				fullset, 
				HttpStatus.ACCEPTED);
	}

	// -- SEARCH TAB END --
	// --CUSTOMER ADDRESS--
	@GetMapping(path = CustomerURLMapping.GETALLCUSTOMERADDRESS,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<CustomerAddressBean>> getAllCustomerAddress() {
		List<CustomerAddressBean> cust = CUSTADD_SERVICE.getAllCustomerAddress();
		return new ResponseEntity<List<CustomerAddressBean>>(cust,  HttpStatus.ACCEPTED);
	}

	@GetMapping(path = CustomerURLMapping.GETCUSTADDBYEMAIL,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<CustomerAddressBean>> getByEmail(@PathVariable String email) {
		return new ResponseEntity<List<CustomerAddressBean>>(CUSTADD_SERVICE.getCustAddByEmail(email),
				 HttpStatus.ACCEPTED);
	}

	@PostMapping(path = CustomerURLMapping.GETALLCUSTOMERADDRESS,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<CustAddBean> instcustAdd(@RequestBody CustomerAddressPostBean cust) {
//		boolean flag = true;
//		try {
//		List<CustomerAddressBean> exits= CUSTADD_SERVICE.getCustAddByEmail(cust.getEmail());
//		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
//		System.out.println(exits);
//		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
//		
//		if(exits.size()>0) {
//		for(CustomerAddressBean acab:exits) {
//			if(acab.getPincode()==cust.getPincode()) {
//				flag= false;
//			}
//		}
//		}else {
//			flag = true;
//		}
//		}catch(BackEndDataException e) {
//			e.printStackTrace();
//			flag= true;
//		}
//		ResponseBean responseBean =null;
		CustAddBean bean=new CustAddBean();
//		if(flag) {
			bean=CUSTADD_SERVICE.insertCustAdd(cust.getName(), cust.getEmail(), cust.getFlat(),
				cust.getStreetAddress(), cust.getLandmark(), cust.getPincode() + "", cust.getCity(), cust.getState(),
				cust.getCountry(), cust.getMobile1() + "", cust.getMobile2() + "", cust.getAddressType());
//		}else {
//			responseBean = new ResponseBean("Pin code already registered", 4004, System.currentTimeMillis());
			
//			bean.setMsg("Pin code already registered");
//		}
		return new ResponseEntity<CustAddBean>(bean,  HttpStatus.ACCEPTED);
	}

	@PutMapping(path = CustomerURLMapping.GETALLCUSTOMERADDRESS,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> updatecustAdd(@RequestBody CustomerAddressBean cust) {
		ResponseBean responseBean = CUSTADD_SERVICE.UpdateCustAdd(cust.getAddressid() + "", cust.getName(),
				cust.getEmail(), cust.getFlat(), cust.getStreetAddress(), cust.getLandmark(), cust.getPincode() + "",
				cust.getCity(), cust.getState(), cust.getCountry(), cust.getMobile1() + "", cust.getMobile2() + "",
				cust.getAddressType());
		return new ResponseEntity<ResponseBean>(responseBean,  HttpStatus.ACCEPTED);
	}

	@GetMapping(path = CustomerURLMapping.GETALLCUSTOMERADDRESSWITHID,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<CustomerAddressBean> getAllCustomerByID(@PathVariable String addressid) {
		return new ResponseEntity<CustomerAddressBean>(CUSTADD_SERVICE.getCustAddByID(addressid), 
				HttpStatus.ACCEPTED);
	}
	// --CUSTOMER ADDRESS END

	@GetMapping(path = CustomerURLMapping.CUSTOMERLOGIN,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<LilCustomerBean>> getAllUser() {
		return new ResponseEntity<List<LilCustomerBean>>(lilCustomerRestervice.getAllUser(), 
				HttpStatus.ACCEPTED);
	}

	@GetMapping(path = CustomerURLMapping.CUSTOMERLOGIN_EMAIL,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<LilCustomerBean> getByEmail1(@PathVariable String email) {
		return new ResponseEntity<LilCustomerBean>(lilCustomerRestervice.getByEmail(email), 
				HttpStatus.ACCEPTED);
	}

	@GetMapping(path = CustomerURLMapping.CUSTOMERLOGIN_UNIQUEID,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<LilCustomerBean> getByUniqueId(@RequestParam(name = "uniqueid") String uniqueBean) {
		return new ResponseEntity<LilCustomerBean>(lilCustomerRestervice.getByUniqueId(uniqueBean), 
				HttpStatus.ACCEPTED);
	}

	@PostMapping(path = CustomerURLMapping.CLOGIN, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> insertApproval(@RequestBody LilCustomerBean lil) {
		ResponseBean responseBean = lilCustomerRestervice.insert(lil.getUniqueid() + "", lil.getEmail(),
				lil.getFirstname(), lil.getLastname(), lil.getImageurl());
		LilCustomerBean lcb = lilCustomerRestervice.getByUniqueId(lil.getUniqueid());
		responseBean.setMessage(lcb.getUserid()+"");
		return new ResponseEntity<ResponseBean>(responseBean,  HttpStatus.ACCEPTED);
	}

	@PutMapping(path = CustomerURLMapping.CPHONE,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> update(@RequestBody LilCustomerBean lil) {
		ResponseBean responseBean = lilCustomerRestervice.update(lil.getUserid() + "", lil.getUniqueid() + "",
				lil.getEmail(), lil.getFirstname(), lil.getLastname(), lil.getImageurl(), lil.getPhone() + "",
				lil.isStatus() + "");
		return new ResponseEntity<ResponseBean>(responseBean,  HttpStatus.ACCEPTED);
	}

	// TOP TEN PRODUCTS FOR BOY AND GIRL
	@GetMapping(path = CustomerURLMapping.SELECTEDPRODUCTS,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Selectedproducts>> getAllSelectedProducts() {
		return new ResponseEntity<List<Selectedproducts>>(selectedproductsRestService.getAllSelectedProducts(),
				 HttpStatus.ACCEPTED);
	}

	@GetMapping(path = CustomerURLMapping.SELECTEDPRODUCTS_BY_ID,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Selectedproducts> getSelectedproductsById(@PathVariable String spid) {
		return new ResponseEntity<Selectedproducts>(selectedproductsRestService.getSelectedproductsById(spid),
				 HttpStatus.ACCEPTED);
	}

	@GetMapping(path=CustomerURLMapping.PRODUCTS_MAPPING_PATH_SELECTEDPRODUCTS,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<ProductFullDetailsWithImage>> ProductsServicegetBySelectedProducts(
			@PathVariable String genderid) {
//		List<ProductFullDetails> products = productsRestService.getByGender(genderid);
//
//		return new ResponseEntity<List<ProductFullDetails>>(products,  HttpStatus.ACCEPTED);
		List<ProductFullDetails> x=productsRestService.getByGender(genderid);
		List<ProductFullDetailsWithImage> fullset=new CopyOnWriteArrayList<>();
		for (ProductFullDetails y:x) {
			fullset.add(new ProductFullDetailsWithImage(y,pcl.getImageInfo(y.getProductid())));
		}
		return new ResponseEntity<List<ProductFullDetailsWithImage>>(
				fullset, 
				HttpStatus.ACCEPTED);
	}

	// TOP TEN PRODUCTS FOR BOY AND GIRL END
	// Collection//
	@GetMapping(path = CustomerURLMapping.COLLECTION,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<CollectionsaleBean>> getAllCOLLECTION() {
		return new ResponseEntity<List<CollectionsaleBean>>(collectionsaleRestService.getAllCollectionsale(),
				 HttpStatus.ACCEPTED);
	}

	@GetMapping(path = CustomerURLMapping.GET_BY_COLLECTION,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<CollectionsaleBean> getByID(@PathVariable String id) {
		return new ResponseEntity<CollectionsaleBean>(collectionsaleRestService.getAllCollectionById(id),
				 HttpStatus.ACCEPTED);
	}

	@GetMapping(path = CustomerURLMapping.PRODUCTS_MAPPING_PATH_CAT,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<ProductFullDetailsWithImage>> ProductsServicegetByCategories(@PathVariable String catid,
			@PathVariable String season, @PathVariable String occasion) {
//		List<ProductFullDetails> products = productsRestService.getbycategories(catid, season, occasion);
//
//		return new ResponseEntity<List<ProductFullDetails>>(products,  HttpStatus.ACCEPTED);
		List<ProductFullDetails> x=productsRestService.getbycategories(catid, season, occasion);
		List<ProductFullDetailsWithImage> fullset=new CopyOnWriteArrayList<>();
		for (ProductFullDetails y:x) {
			fullset.add(new ProductFullDetailsWithImage(y,pcl.getImageInfo(y.getProductid())));
		}
		return new ResponseEntity<List<ProductFullDetailsWithImage>>(
				fullset, 
				HttpStatus.ACCEPTED);
	}

	// Shipping Code Start
	/*
	 * @PostMapping("/shipping")
	 * 
	 * @ResponseBody public ResponseEntity<ResponseBean> insertshipping(@RequestBody
	 * ShippingBean ship) { ResponseBean
	 * response=shippingRestService.insertShipping(ship.getOrderid()+"",ship.
	 * getCustomerid()+"",
	 * ship.getShippingstatus(),ship.getCourierid()+"",ship.getCouriercompany(),ship
	 * .getShippingdate()+"",ship.getDeliverydate()+""); return new
	 * ResponseEntity<ResponseBean>(response,
	 * HttpStatus.ACCEPTED); }
	 * 
	 * @PutMapping("/shipping")
	 * 
	 * @ResponseBody public ResponseEntity<ResponseBean> updateshipping(@RequestBody
	 * ShippingBean ship) { ResponseBean
	 * response=shippingRestService.updateShipping(ship.getShippingid()+"",ship.
	 * getOrderid()+"",ship.getCustomerid()+"",
	 * ship.getShippingstatus(),ship.getCourierid()+"",ship.getCouriercompany(),ship
	 * .getShippingdate()+"",ship.getDeliverydate()+""); return new
	 * ResponseEntity<ResponseBean>(response,
	 * HttpStatus.ACCEPTED); }
	 * 
	 * @GetMapping("/shipping")
	 * 
	 * @ResponseBody public ResponseEntity<List<ShippingBean>> getAllShipping(){
	 * return new
	 * ResponseEntity<List<ShippingBean>>(shippingRestService.getAllShipping(), new
	 * HttpHeaders(), HttpStatus.ACCEPTED); }
	 * 
	 * @GetMapping("/shipping/{shippingid}")
	 * 
	 * @ResponseBody public ResponseEntity<ShippingBean>
	 * getAllShippingByShippingId(@PathVariable String shippingid){ return new
	 * ResponseEntity<ShippingBean>(shippingRestService.getShippingbyid(shippingid),
	 *  HttpStatus.ACCEPTED); }
	 * 
	 * @GetMapping("/shippingcustomerid/{customerid}")
	 * 
	 * @ResponseBody public ResponseEntity<ShippingBean>
	 * getAllShippingByCustomerId(@PathVariable String customerid){ return new
	 * ResponseEntity<ShippingBean>(shippingRestService.getShippingByCustomerid(
	 * customerid),  HttpStatus.ACCEPTED); }
	 * 
	 * @GetMapping("/shippingcourierid/{courierid}")
	 * 
	 * @ResponseBody public ResponseEntity<ShippingBean>
	 * getAllShippingByCourierId(@PathVariable String courierid){ return new
	 * ResponseEntity<ShippingBean>(shippingRestService.getshippingbyCourierid(
	 * courierid),  HttpStatus.ACCEPTED); }
	 * 
	 */
	// Shipping Code End

	// cart management code start
	@GetMapping(path = CustomerURLMapping.CART_GET,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Cartmanagement>> getAllCart() {
		return new ResponseEntity<List<Cartmanagement>>(cartRestService.getAllCart(), 
				HttpStatus.ACCEPTED);
	}

	@GetMapping(path = CustomerURLMapping.GET_BY_CARTID,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Cartmanagement> getByCartid(@PathVariable String cartid) {
		return new ResponseEntity<Cartmanagement>(cartRestService.getByCartid(cartid), 
				HttpStatus.ACCEPTED);
	}

	@GetMapping(path = CustomerURLMapping.GET_BY_CUSTOMERID,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Cartmanagement>> getByProductid(@PathVariable String customerid) {
		return new ResponseEntity<List<Cartmanagement>>(cartRestService.getByCustomerid(customerid), 
				HttpStatus.ACCEPTED);
	}

	@PostMapping(path = CustomerURLMapping.CARTMANAGEMENT_POST, consumes="application/json")
	@ResponseBody
	public ResponseEntity<ResponseBean> insertCart(@RequestBody CartmanagementPostBean cart) {
		ResponseBean responseBean = cartRestService.insertCart(cart.getCustomerid(), cart.getProductid(),
				cart.getProductprice(), cart.getQuantity(), cart.getTotalprice());
		return new ResponseEntity<ResponseBean>(responseBean,  HttpStatus.ACCEPTED);
	}
	
	@PostMapping(path = CustomerURLMapping.CARTMANAGEMENT_POST1,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> insertCart(@RequestBody MultiProductCartBean carts) {
		ResponseBean responseBean = null;
	
		for(CartmanagementPostBean cart:carts.getMycarts()) {
		responseBean = cartRestService.insertCart(cart.getCustomerid(), cart.getProductid(),
				cart.getProductprice(), cart.getQuantity(), cart.getTotalprice());
		}
		return new ResponseEntity<ResponseBean>(responseBean,  HttpStatus.ACCEPTED);
	}

	@PutMapping(path = CustomerURLMapping.CARTMANAGEMENT_POST,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> updateCart(@RequestBody CartmanagementPostBean cart) {
		ResponseBean responseBean = cartRestService.updateCart(cart.getCartid(), cart.getCustomerid(),
				cart.getProductid(), cart.getProductprice(), cart.getQuantity(), cart.getTotalprice());
		return new ResponseEntity<ResponseBean>(responseBean,  HttpStatus.ACCEPTED);
	}

	@DeleteMapping(path = CustomerURLMapping.DELETE_BY_CARTID)
	@ResponseBody
	public ResponseEntity<ResponseBean> deleteByCartid(@PathVariable String cartid) {
		ResponseBean responseBean = cartRestService.deleteByCartid(cartid);
		return new ResponseEntity<ResponseBean>(responseBean,  HttpStatus.ACCEPTED);
	}

	@DeleteMapping(path = CustomerURLMapping.DELETE_BY_PRODUCTID)
	@ResponseBody
	public ResponseEntity<ResponseBean> deleteByProductId(@PathVariable String cartid, @PathVariable String productid,
			@PathVariable String customerid) {
		ResponseBean responseBean = cartRestService.deleteByProductId(cartid, productid, customerid);
		return new ResponseEntity<ResponseBean>(responseBean,  HttpStatus.ACCEPTED);
	}

	// Cart Management code end

	// ordermanagement

	// Post method for order
	@PostMapping(path = CustomerURLMapping.ORDERS,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<OrderedBeanSecurity> create(@RequestBody OrdersBean ob) {
		String[] prid = new String[ob.getOrderitem().length];
		String[] quantity = new String[ob.getOrderitem().length];
		String[] productprice = new String[ob.getOrderitem().length];
		int i = 0;
		for (OrderItemPostBean oip : ob.getOrderitem()) {
			prid[i] = oip.getProductid();
			quantity[i] = oip.getQuantity();
			productprice[i] = oip.getProductprice();
			i++;
		}

		OrderedBeanSecurity responseBean = orderManageMentRestService.InsertOrders(ob.getCustomerid() + "",
				ob.getTotalprice() + "", ob.getOrdercodeid() + "", ob.getAddressid() + "", prid, quantity,
				productprice);
		// System.out.println(responseBean.getMessage());
		return new ResponseEntity<OrderedBeanSecurity>(responseBean,  HttpStatus.ACCEPTED);
	}

	// Get full details by orderid
	@GetMapping(path = CustomerURLMapping.FULL_DETAILS,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<OrderFullDetailsSecurityBean> getFullDetails(@PathVariable String orderid) {
		return new ResponseEntity<OrderFullDetailsSecurityBean>(orderManageMentRestService.getFULLDETAILS(orderid),
				 HttpStatus.ACCEPTED);
	}

	// get full details by customerid
	@GetMapping(path = CustomerURLMapping.FULL_DETAILSBYCUSTOMERID,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<OrderFullDetailsSecurityBean>> getFullDetailsbycustomerid(
			@PathVariable String customerid) {
		return new ResponseEntity<List<OrderFullDetailsSecurityBean>>(
				orderManageMentRestService.getFULLDETAILSByCustomerid(customerid), 
				HttpStatus.ACCEPTED);
	}
	/*
	 * //Put method for orders
	 * 
	 * @PutMapping(path=CustomerURLMapping.ORDERS)
	 * 
	 * @ResponseBody public ResponseEntity<ResponseBean> update(@RequestBody
	 * OrdersBean ob) { ResponseBean
	 * responseBean=orderManageMentRestService.update(ob.getOrderid()+"",ob.
	 * getCustomerid()+"",ob.getTotalprice()+"",ob.getOrdercodeid()+"",ob.
	 * getPaymenttransactionid()+""); return new
	 * ResponseEntity<ResponseBean>(responseBean, 
	 * HttpStatus.ACCEPTED); }
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * // cod post method /*
	 * 
	 * @PostMapping(path=CustomerURLMapping.COD)
	 * 
	 * @ResponseBody public ResponseEntity<ResponseBean> cod(@RequestBody CodBean
	 * co) { ResponseBean
	 * responseBean=orderManageMentRestService.cod(co.getOrderdate()+"",co.
	 * getCustomerid()+"",co.getTotalamount()+"",co.getDeliverydate()+""); return
	 * new ResponseEntity<ResponseBean>(responseBean, 
	 * HttpStatus.ACCEPTED); }
	 */

	// Collection PRODUCT INFORMATION
	@GetMapping(path=CustomerURLMapping.PRODUCTS_MAPPING_PATH_COLLECTIONPRODUCTINFO,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<ProductFullDetailsWithImage>> ProductsServicegetByCollectionProductids(
			@PathVariable String genderid) {
//		List<ProductFullDetails> products = productsRestService.getByCOLLECTIONPRODINFO(genderid);
//
//		return new ResponseEntity<List<ProductFullDetails>>(products,  HttpStatus.ACCEPTED);
		List<ProductFullDetails> x=productsRestService.getByCOLLECTIONPRODINFO(genderid);
		List<ProductFullDetailsWithImage> fullset=new CopyOnWriteArrayList<>();
		for (ProductFullDetails y:x) {
			fullset.add(new ProductFullDetailsWithImage(y,pcl.getImageInfo(y.getProductid())));
		}
		return new ResponseEntity<List<ProductFullDetailsWithImage>>(
				fullset, 
				HttpStatus.ACCEPTED);
	}

	// Whishlist code start

	@GetMapping(path=CustomerURLMapping.WISHLIST_BY_CUSTOMER_ID,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<WishlistBean>> getByCustomerId(@PathVariable String customerid)

	{
		List<WishlistBean> wishlist = wishlistRestService.getByCustomerId(customerid);
		return new ResponseEntity<List<WishlistBean>>(wishlist,  HttpStatus.ACCEPTED);
	}

	@PostMapping(path=CustomerURLMapping.WISHLIST,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> insert(@RequestBody WishlistBean wishlist) {
		boolean flag = true;
		try {
		List<WishlistBean> wishlist_temp = wishlistRestService.getByCustomerId(wishlist.getCustomerid()+"");
		
		for(WishlistBean wlb:wishlist_temp) {
			if(wlb.getProductid()==wishlist.getProductid()) {
				flag=false;
			}
		}
		}catch(DataNotFoundException e) {
			flag=true;
		}
		ResponseBean responseBean =null;
		if(flag) {
		responseBean= wishlistRestService.insertWishlist(wishlist.getCustomerid() + "",
				wishlist.getProductid() + "");
		}
		else {
			throw new RestURLReaderException(ConstantValues.ALREADY_IN_YOUR_WISHLIST);
		}
		
		return new ResponseEntity<ResponseBean>(responseBean,  HttpStatus.ACCEPTED);
	}

	@DeleteMapping(path=CustomerURLMapping.WISHLIST_BY_PRODUCT_ID)
	@ResponseBody
	public ResponseEntity<ResponseBean> deleteByProductId(@PathVariable String productid,
			@PathVariable String customerid) {
		return new ResponseEntity<ResponseBean>(wishlistRestService.deleteByProductId(productid, customerid),
				 HttpStatus.ACCEPTED);
	}
	
	@PostMapping(path=CustomerURLMapping.WISHLIST_BY_PRODUCT_ID1)
	@ResponseBody
	public ResponseEntity<ResponseBean> deleteByProductId1(@RequestBody WishDeleteBean wdb) {
		System.err.println("-----------------------------------------------------------");
		System.err.println(wdb);
		System.err.println("-----------------------------------------------------------");
		
		ResponseBean rb = new ResponseBean();
		for(String s:wdb.getProductids()) {
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			System.out.println(s);
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		rb=wishlistRestService.deleteByProductId(s, wdb.getUniqueid());
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(rb);
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		
		}
		return new ResponseEntity<ResponseBean>(rb,
				 HttpStatus.ACCEPTED);
	}


	// Wishlist code End

	@GetMapping(path = CustomerURLMapping.COLLECTIONSALEFULLINFO,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<CollectionSalePack1Bean> getAllCollectionSaleInfo(@PathVariable long id,
			@PathVariable String siteview, @PathVariable String genderid) {
		return new ResponseEntity<CollectionSalePack1Bean>(
				collectionsaleRestService.getAllCollectionFullInformation(id + "", siteview, genderid),
				 HttpStatus.ACCEPTED);
	}

	@GetMapping(path=CustomerURLMapping.COLLECTIONINFO,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<CollectionSalePackBean>> ProductsServicegetByCollectionProductids(
			@PathVariable String genderid, @PathVariable String siteview) {
		return new ResponseEntity<List<CollectionSalePackBean>>(
				collectionsaleRestService.getAllCollectionInformation(genderid, siteview), 
				HttpStatus.ACCEPTED);
	}

	@GetMapping(path = CustomerURLMapping.PRODUCTVARIATION, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<ProductVariationFullInfoBean>> ProductVariations(@PathVariable String productid) {
		return new ResponseEntity<List<ProductVariationFullInfoBean>>(
				productVariationRestService.getAllInfoOfVariations(productid),  HttpStatus.ACCEPTED);
	}

	@GetMapping(path=CustomerURLMapping.NEW_COLLECTION, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<CollectionSalePackBean>> ProductsServicegetByGenderCollectionProductids() {
		return new ResponseEntity<List<CollectionSalePackBean>>(
				collectionsaleRestService.getAllGenderCollectionInformation(),  HttpStatus.ACCEPTED);
	}

	@GetMapping(path=CustomerURLMapping.COLLECTION_BASED_ON_ICON, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<CollectionSalePackBean>> CollectionByIcons() {
		return new ResponseEntity<List<CollectionSalePackBean>>(
				collectionsaleRestService.getAllCollectionsBasedOnIcons1(),  HttpStatus.ACCEPTED);
	}

	@GetMapping(path = CustomerURLMapping.COLLECTION_BASED_ON_SITEVIEW, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<CollectionSalePackBean>> CollectionBasedonSiteview(@PathVariable String siteview,
			@PathVariable String gender) {
		return new ResponseEntity<List<CollectionSalePackBean>>(
				collectionsaleRestService.getCollectionSaleBasedOnSiteview(siteview, gender), 
				HttpStatus.ACCEPTED);
	}

	@GetMapping(path=CustomerURLMapping.COLLECTION_BASED_ON_LANDINGVIEW, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<CollectionSalePack1Bean> CollectionBasedonLandingview(@PathVariable String id,
			@PathVariable String siteview) {
		return new ResponseEntity<CollectionSalePack1Bean>(
				collectionsaleRestService.getCollectionLandingView1(id, siteview), 
				HttpStatus.ACCEPTED);
	}
	
	
	/* SPECIAL COLLECTION PAGINATION */
	@GetMapping(path=CustomerURLMapping.COLLECTION_BASED_ON_LANDINGVIEW0, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public PageCountBean getPagesCountSpecialCollection1(@PathVariable String id,
			@PathVariable String siteview,@PathVariable int page,@PathVariable int count) {
		List<Long> ids = collectionsaleRestService.getCollectionLandingView1(id, siteview).getProductids();
		System.err.println("--------------------------------------------------------------------");
		System.err.println(ids);
		System.err.println("--------------------------------------------------------------------");
		System.err.println("-------------------------PCL.getPVID-------------------------------------------");
		System.err.println(pcl.getPvIDBean());
		System.err.println("--------------------------------------------------------------------");
		ids = getDataScan1(pcl.getPvIDBean(), ids);
		
		
		System.err.println("-------------------------after getDataScan1-------------------------------------------");
		System.err.println(ids);
		System.err.println("--------------------------------------------------------------------");
		
		List<ProductFullDetailsWithImage> products = new CopyOnWriteArrayList<>();
		for(Long id1: ids) {
			products.add(pcl.getByProductID(id1));
		}
		System.err.println("-------------------------after getDataScan1-------------------------------------------");
		System.err.println(products);
		System.err.println("--------------------------------------------------------------------");
		List<List<ProductFullDetailsWithImage>> data1 =Partition.ofSize(products, count);
		return new PageCountBean(data1.size());
	}
	
	@GetMapping(path=CustomerURLMapping.COLLECTION_BASED_ON_LANDINGVIEW1, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<ProductFullDetailsWithImage> getPagesDataSpecialCollection(@PathVariable String id,
	@PathVariable String siteview,@PathVariable int page,@PathVariable int count){
		List<Long> ids = collectionsaleRestService.getCollectionLandingView1(id, siteview).getProductids();
		ids = getDataScan1(pcl.getPvIDBean(), ids);
		List<ProductFullDetailsWithImage> products = new CopyOnWriteArrayList<>();
		for(Long id1: ids) {
			products.add(pcl.getByProductID(id1));
		}
		List<List<ProductFullDetailsWithImage>> data1=null;
		try {			
			data1 =Partition.ofSize(products, count);
			}catch(IndexOutOfBoundsException e) {
				throw new DataNotFoundException(ConstantValues.NO_PRODUCTS_FOUND);
			}
			return data1.get(page);
	}
	
	public List<Long> getDataScan1(List<ProductidsBean> pib, List<Long> cids){
		List<Long> data1 = new CopyOnWriteArrayList<>();
		for(ProductidsBean pi:pib) {
			for(Long tt:cids) {
				if(pi.getProductid().equals(tt)) {
					data1.add(tt);
				}
			}
		}
		
		return data1;
	}
	
	/* special collection pagination end */
	
	

	// razorpay code start
	@GetMapping(path = CustomerURLMapping.RazorPay_Transaction, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Razorpay>> getAllRazorpaylist() {
		return new ResponseEntity<List<Razorpay>>(orderManageMentRestService.getAllRazorpaylist(), 
				HttpStatus.ACCEPTED);
	}

	@GetMapping(path = CustomerURLMapping.RazorPay_Transaction_ByID, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Razorpay> getRazorpayById(@PathVariable String razorpay_order_id)

	{
		Razorpay razorpay = orderManageMentRestService.getRazorpayById(razorpay_order_id);
		return new ResponseEntity<Razorpay>(razorpay,  HttpStatus.ACCEPTED);
	}

	@PostMapping(path=CustomerURLMapping.RazorPay_Transaction, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<RazorpayOrderedSecurityBean> insert(@RequestBody RazorpayTransactionPostBean rpay) {
		RazorpayOrderedSecurityBean responseBean = orderManageMentRestService.InsertRazorpay(
				rpay.getRazorpay_order_id() + "", rpay.getRazorpay_payment_id() + "", rpay.getRazorpay_signature(),
				rpay.getCustomerid() + "", rpay.getOrderid() + "", rpay.getAddressid() + "");
		if (responseBean.getMessage().equals("Transaction successful")
				&& responseBean.getRazorpay_payment_id().equals(rpay.getRazorpay_payment_id())) {
			// Call Kafka Procedure with orderid
		//	orderItemReduceProdure.orderPush(new OrderedData( rpay.getOrderid()));
		}
		return new ResponseEntity<RazorpayOrderedSecurityBean>(responseBean,  HttpStatus.ACCEPTED);
	}
	
	@GetMapping(path="/test1/{orderid}")
	@ResponseBody
	public ResponseEntity<String> testMeter(@PathVariable long orderid){
		return new ResponseEntity<String>(orderItemReduceProdure.orderPush(new OrderedData( orderid)), HttpStatus.ACCEPTED);
	}

	@GetMapping(path=ConsumerURLMapping.GRANNYBLOG, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> getAllBlogReportDetails() {
	//	List<GrannyBlog> grannyblog = grannyblogRestService.getAllGranyBlogDetails();
		return new ResponseEntity<String>(grannyblogRestService.getAllGranyBlogDetails(),  HttpStatus.ACCEPTED);
	}

	@GetMapping(path=ConsumerURLMapping.GRANNYBLOG_BY_ID, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> getByBId(@PathVariable String blogid) {
	//	GrannyBlog grannyblog = grannyblogRestService.getGrannyBlogById(blogid);
		return new ResponseEntity<String>(grannyblogRestService.getGrannyBlogById(blogid),  HttpStatus.ACCEPTED);
	}

	// blogreport code start

	@GetMapping(path=ConsumerURLMapping.BLOGREPORT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Blogreport>> getAllBlogReportDetail() {
		List<Blogreport> blogreport = blogreportRestService.getAllBlogreport();

		return new ResponseEntity<List<Blogreport>>(blogreport,  HttpStatus.ACCEPTED);
	}

	@GetMapping(path=ConsumerURLMapping.BLOGREPORT_BY_ID, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Blogreport> getByqId(@PathVariable String bid) {
		Blogreport blogreport = blogreportRestService.getById(bid);

		return new ResponseEntity<Blogreport>(blogreport,  HttpStatus.ACCEPTED);
	}

	@PostMapping(path = "/complaint", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> insertNewComplaint(@RequestParam(name = "fullname") String fullname,
			@RequestParam(name = "email") String email, @RequestParam(name = "phone") long phone,
			@RequestParam(name = "issuecategory") String issuecategory,
			@RequestParam(name = "issuesubcategory") String issuesubcategory,
			//@RequestParam(name = "reffile") MultipartFile reffile,
			@RequestParam(name = "details") String details,
			@RequestParam(name = "status") String status) throws MaxUploadSizeExceededException {
		System.err.println("email:" + email);
		System.err.println("phone:" + phone);
		System.err.println("issuecategory:" + issuecategory);
		System.err.println("issuesubcategory:" + issuesubcategory);
		
	//	System.err.println("reffile:" + reffile);
		System.err.println("details:" + details);
		System.err.println("status:" + status);
		ComplaintboxBean cb = new ComplaintboxBean(0, fullname, email, phone, issuecategory, issuesubcategory, /*reffile,*/
				details, status);
		return new ResponseEntity<ResponseBean>(cbr.insertNewComplaint(cb),  HttpStatus.ACCEPTED);
	}

	// ORDER STATUS TRACKING CODE START
	@GetMapping(path = "/ORT112/{orderid}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Orderitemsstatus>> getAllOrderItemsStatusbyOrderId(@PathVariable Long orderid) {
		return new ResponseEntity<List<Orderitemsstatus>>(
				orderManageMentRestService.getAllOrderItemsListlist(orderid + ""), 
				HttpStatus.ACCEPTED);
	}

	// ORDER STATUS TRACKING CODE END

	// Packageshipping by orderitems id
	@GetMapping(path = CustomerURLMapping.PACKAGESHIPPING, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<PackageshippingBean> getAllPackageShippingbyOrderitemsid(@PathVariable String orderitemsid) {
		return new ResponseEntity<PackageshippingBean>(
				packageshippingRest.getAllPackageShippingbyOrderitemsid(orderitemsid), 
				HttpStatus.ACCEPTED);
	}

	// Email Subscribe

	@PostMapping(path=CustomerURLMapping.EMAILSUBSCRIBE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> insertSUBSCRIBE(@RequestBody EmailSubscribeBean esb) {
		ResponseBean responseBean = emailSubscribeRestService.InsertSUbscribers(esb.getEmailid());
		return new ResponseEntity<ResponseBean>(responseBean,  HttpStatus.ACCEPTED);
	}

	// get latest5 granny blog

	@GetMapping(path = ConsumerURLMapping.GRANNYBLOG_LATEST5, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> getLatest5Blogs() {
	//	List<GBlogBean1> grannyblog = grannyblogRestService.getAllGranyBlogLatest5();
		return new ResponseEntity<String>(grannyblogRestService.getAllGranyBlogLatest5(),  HttpStatus.ACCEPTED);
	}

	@GetMapping(path = ConsumerURLMapping.GRANNYBLOG_LATEST5_BY_BLOGID, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> getNextLatest5Blogs(@PathVariable String blogid) {
	//	List<GBlogBean> grannyblog = grannyblogRestService.getNextLatest5GrannyBlogById(blogid);
		return new ResponseEntity<String>(grannyblogRestService.getNextLatest5GrannyBlogById(blogid),  HttpStatus.ACCEPTED);
	}

	@GetMapping(path = CustomerURLMapping.SELECTEDPRODUCTS4, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Selectedproducts>> getAllSelectedProducts4() {
		return new ResponseEntity<List<Selectedproducts>>(selectedproductsRestService.getAllSelectedProducts4(),
				 HttpStatus.ACCEPTED);
	}
	
	
//	@GetMapping(path = CustomerURLMapping.AP101, produces = MediaType.APPLICATION_JSON_VALUE)
	@GetMapping(path = CustomerURLMapping.AP101)
	@ResponseBody
	public ResponseEntity<byte[]> getProductDetailswithImage(@PathVariable Long productid) {
		return new ResponseEntity<byte[]>(imageResetService.getProductImage(productid),	 HttpStatus.ACCEPTED);
	}
	
	@GetMapping(path = CustomerURLMapping.SCROL_SEARCH_DATA_COUNT , produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<PageCountBean> getCount(@PathVariable String query, @PathVariable int page, @PathVariable int count) {
		
			return new ResponseEntity<PageCountBean>(productPaginationRestService.getSearchQueryCount(query, page, count),  HttpStatus.ACCEPTED);
		}
	
	@GetMapping(path = CustomerURLMapping.SCROL_SEARCH_DATA, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<ProductFullDetailsWithImage>> getSearchData(@PathVariable String query, @PathVariable int page, @PathVariable int count) {
		
//			return new ResponseEntity<List<ProductFullDetails>>(productPaginationRestService.getSearchQueryByPage(query, page, count),  HttpStatus.ACCEPTED);
		List<ProductFullDetails> x=productPaginationRestService.getSearchQueryByPage(query, page, count);
		List<ProductFullDetailsWithImage> fullset=new CopyOnWriteArrayList<>();
		for (ProductFullDetails y:x) {
			fullset.add(new ProductFullDetailsWithImage(y,pcl.getImageInfo(y.getProductid())));
		}
		return new ResponseEntity<List<ProductFullDetailsWithImage>>(
				fullset, 
				HttpStatus.ACCEPTED);	
	}
	
	@GetMapping(path=CustomerURLMapping.PUSH_EMAIL_NOTIFICATION, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	
	public ResponseEntity<String> pushEmailNotification(@PathVariable Long orderid) {
		this.pushToEmail(orderid);
		return new ResponseEntity<String>("Queued",HttpStatus.ACCEPTED);
	}
	public void pushToEmail(Long orderid) {
		OrderFullDetailsSecurityBean1 obj= orderManageMentRestService.getNotificationInfo(orderid+"");
		OrderEmailService bean = new OrderEmailService();
		
		bean.setFullname(this.getUserFullName(obj.getCustomerid()));
		bean.setOrderid(obj.getOrderid());
		bean.setOrderstatus(obj.getOrdercodeid());
		bean.setDateoforder(obj.getOrderdate()+"");
	Calendar calendar = Calendar.getInstance();
	calendar.setTimeInMillis(obj.getOrderdate().getTime());
	String xyz=calendar.get(Calendar.DATE)+"/"+calendar.get(Calendar.MONTH)+"/"+ calendar.get(Calendar.YEAR);
	bean.setDateoforder(xyz);	
	String abc=obj.getOrderitems()[0].getDeliverydate().get(Calendar.DATE)+"/"+obj.getOrderitems()[0].getDeliverydate().get(Calendar.MONTH)+"/"+obj.getOrderitems()[0].getDeliverydate().get(Calendar.YEAR);
		bean.setDateofdelivery(abc+"");  //need to add +10 days
		bean.setTransactionid(obj.getPaymenttransactionid());
		double inum = Double.parseDouble(obj.getTotalprice());
		bean.setTotal((int)inum+"");
		OrderedItems[] orderedItems = new OrderedItems[obj.getOrderitems().length];
		
		
		
		int i=0;
		for(OrderItemsBean oib: obj.getOrderitems()) {
			System.err.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			System.err.println(oib);
			System.err.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			OrderedItems temp= new OrderedItems();
			temp.setOrderitemid(oib.getProductid()+"");
			temp.setPrice(oib.getProductprice()+"");
			temp.setQuantity(oib.getQty()+"");
			ProductFullDetailsWithImage fpd = pcl.getByProductID1(oib.getProductid());
			temp.setOrderitemtitle(fpd.getProducttitle());
			temp.setSize(fpd.getAgedescription());
			orderedItems[i] = temp;
			i++;
		}
		bean.setOrderitems(orderedItems);
		CustomerAddressBean cab = CUSTADD_SERVICE.getCustAddByID(obj.getAddressid());
		String address = cab.getName()+" ,"+cab.getFlat()+" ,"+cab.getLandmark()+" ,"+cab.getStreetAddress()+" ,"+cab.getCity()+" ,"+cab.getState()+" - "+cab.getPincode();
		bean.setAddress(address);
		bean.setContact1(cab.getMobile1()+"");
		bean.setContact2(cab.getMobile2()+"");
		bean.setEmail(cab.getEmail());
		emailProducer.push(bean);
		
	//	return new ResponseEntity<ResponseBean>(new ResponseBean("ok",2002, System.currentTimeMillis()),HttpStatus.ACCEPTED);
	}

	public Map<Long,LilCustomerBean> allUsers = new HashMap<>();
	int count = 1;
	@PostConstruct
	public void loadUsers() {
		 allUsers = new HashMap<>();
		List<LilCustomerBean> allUsersList= lilCustomerRestervice.getAllUser();
		if(!allUsersList.isEmpty()) {
			for(LilCustomerBean lib: allUsersList) {
				allUsers.put(lib.getUserid(), lib);
			}
		}
		System.err.println("------------------------------------------------------");
		System.err.println("Calling loadUsers Method:"+this.count);
		this.count+=1;
	}
	public String getUserFullName(String customerid) {
		try {
		return this.allUsers.get(Long.parseLong(customerid)).getFirstname()+" "+this.allUsers.get(Long.parseLong(customerid)).getLastname();
		}catch(Exception e) {
			this.loadUsers();
			return this.allUsers.get(Long.parseLong(customerid)).getFirstname()+" "+this.allUsers.get(Long.parseLong(customerid)).getLastname();
		}
	}
	
	@PostMapping(path=CustomerURLMapping.CONTEST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> insertContestDetails(@RequestParam("emailid") String emailid,
			@RequestParam(name = "phone") long phone, @RequestParam(name = "instaid") String instaid,
			@RequestParam(name = "childname") String childname, @RequestParam(name = "age") String age,@RequestParam(name = "tc") boolean tc,@RequestParam(name = "images") MultipartFile[] images,@RequestParam(name = "contestmonthyear") String contestmonthyear) {
		ResponseBean responseBean = contestRest.inserContestInformation(emailid, phone, instaid, childname, age, tc, images, contestmonthyear);
		
		if(responseBean.getMessage().equals("Successfully Registered")) {
			CommonMail cm = new CommonMail();
			cm.setTomail(emailid);
			cm.setSubject("Miss & Mr Carrot Winter Pageant Registration Successful");
			cm.setMessage("Thank you for registering your child for Miss & Mr Carrot Winter Pageant.Please feel free to reach out to us if you need anything.                   Love Little Carrots Team.");
			emailProducer.push1(cm);
		}
		return new ResponseEntity<ResponseBean>(responseBean,  HttpStatus.ACCEPTED);
	}

	@GetMapping(path = CustomerURLMapping.CONTEST2 , produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> getContestantsByEmailid(@PathVariable String emailid) {
		
			return new ResponseEntity<String>(	contestRest.getbyEmailid(emailid),  HttpStatus.ACCEPTED);
		}
	
	@GetMapping(path = CustomerURLMapping.CONTEST3 , produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> getContestantsByInstaid(@PathVariable String instaid) {
		
			return new ResponseEntity<String>(	contestRest.getbyInstaid(instaid),  HttpStatus.ACCEPTED);
		}
	
	
	@GetMapping(path = CustomerURLMapping.CONTEST4 , produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String>  getContestantsByPhone(@PathVariable String phone) {
		
			return new ResponseEntity<String>(	contestRest.getbyphone(phone),  HttpStatus.ACCEPTED);
		}
	
	
	
	@GetMapping(path = "/mailto", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> mailTo(@RequestBody CommonMail req){
		return new ResponseEntity<String>(	 emailProducer.push1(req),  HttpStatus.ACCEPTED);
	}
	
	
	
	/*COUPON CODE START HERE  */
	@GetMapping(path=CustomerURLMapping.COUPON, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Coupons> verify(@PathVariable String couponcode, @PathVariable String customerid, @PathVariable String totalamount){
		return new ResponseEntity<Coupons>(couponRest.verify(couponcode, customerid, totalamount), HttpStatus.ACCEPTED);
	}
	
	/*COUPON CODE START HERE  */
	@PutMapping(path=CustomerURLMapping.COUPON_APPLIED, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public void AppliedCoupon(@PathVariable String couponid, @PathVariable String customerid){
		couponRest.updateAppliedCoupon(couponid, customerid);
	}
	
	 
	@GetMapping(path=CustomerURLMapping.SUGGESTION_PRODUCTS, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ProductSuggestionBean> SuggestToCustomer(@PathVariable Long productid, @PathVariable String name) {
		ProductSuggestionBean psb = new ProductSuggestionBean();
		ProductFullDetailsWithImage pfd=null;
		psb.setName(name);
		psb.setMessage(name+" below trending products suits to your kid.");
		System.err.println("!!!!!!!!!!!!!!CHECKING FOR REQUESTED PRODUCT !!!!!!!!!!!!!!!!!!!!!!!!");
		try {
		pfd =pcl.getByProductID1(productid);
		}
		catch(DataNotFoundException e) {
			System.err.println("Product Not Found");
		
			
		}
		if(pfd!=null) {
		System.err.println("--------------------------------------------");
		System.err.println(pfd);
		System.err.println("--------------------------------------------");
		
		SearchBean sb = new SearchBean();
		sb.setGender(new String[] {pfd.getGenderid()});
		sb.setAge(new String[] {pfd.getAgeid()});
		sb.setCategory(new String[] {pfd.getCategory()});
		
		List<ProductFullDetailsWithImage> pfd1 = new ArrayList<>();
		pfd1 = pcl.getProductsBySearch(sb);
		
		SuggestedProducts sp1 = new SuggestedProducts();
		SuggestedProducts sp2 = null;
		SuggestedProducts sp3 = null;
		
		int DISPLAY_PRODUCTS_COUNT = 3;
		List<ProductFullDetailsWithImage> customsearchproductids = new ArrayList<>();
		for(int i=0;i<DISPLAY_PRODUCTS_COUNT;i++) {
			customsearchproductids.add(pfd1.get((pfd1.size()-1)-i));
		}
		sp1.setProductid(customsearchproductids);
		sp1.setTitle("New Collection 2021");
		sp1.setMessage1("");
		
		
		if(pfd.getAgeid().equals("0-3M")||
				pfd.getAgeid().equals("3-6M")||
				pfd.getAgeid().equals("6-9M")||
				pfd.getAgeid().equals("9-12M")||
				pfd.getAgeid().equals("1-2Y")||
				pfd.getAgeid().equals("2-3Y")) {
			sb = new SearchBean();
			sp2 = new SuggestedProducts();
			sb.setCategory(new String[] {"Diapers"});
			List<ProductFullDetailsWithImage> pfd2 = new ArrayList<>();
			pfd2 = pcl.getProductsBySearch(sb);
			
		//	int DISPLAY_PRODUCTS_COUNT = 5;
			List<ProductFullDetailsWithImage> daipersproductids = new ArrayList<>();
			for(int i=0;i<DISPLAY_PRODUCTS_COUNT;i++) {
				daipersproductids.add(pfd2.get((pfd2.size()-1)-i));
			}
			sp2.setProductid(daipersproductids);
			sp2.setTitle("Safe & Colorful Re-usable Diapers");
			sp2.setMessage1("Hurry Few left only");
			
		}
		
		sb = new SearchBean();
		sp3 = new SuggestedProducts();
		sb.setCategory(new String[] {"Food"});
		List<ProductFullDetailsWithImage> pfd3 = new ArrayList<>();
		pfd3 = pcl.getProductsBySearch(sb);
		
	//	int DISPLAY_PRODUCTS_COUNT = 5;
		List<ProductFullDetailsWithImage> foodproductids = new ArrayList<>();
		for(int i=0;i<DISPLAY_PRODUCTS_COUNT;i++) {
			foodproductids.add(pfd3.get((pfd3.size()-1)-i));
		}
		sp3.setProductid(foodproductids);
		sp3.setTitle("Organic Foods");
		sp3.setMessage1("Hurry Few left only");
		
		
		List<SuggestedProducts> list = new ArrayList<>();
		list.add(sp1);
		if(sp2!=null) {
		list.add(sp2);
		}
		if(sp3!=null) {
			list.add(sp3);
			}
		
		
		psb.setProducts(list);
		}
		
		return new ResponseEntity<ProductSuggestionBean>(psb, HttpStatus.ACCEPTED);
	}
	
	@GetMapping(path = CustomerURLMapping.NOTIFICATIONS,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Notifications>> getAllNotifications() {
		List<Notifications> notify= (List<Notifications>)this.notificationrepo.getNotification();
		if(!notify.isEmpty()) {		
			return new ResponseEntity<List<Notifications>>(notify,HttpStatus.ACCEPTED);
		}
		else {
			throw new NotificationNotFoundException(ConstantValues.NOTIFICATION_NOT_FOUND_EXCEPTION);
		}
	}
	
	@PutMapping(path=CustomerURLMapping.UPDATE_ID_DETAILS)	 
	 @ResponseBody public ResponseEntity<ResponseBean> update(@RequestBody IdsUpdateBean ib) { ResponseBean
	  responseBean=orderManageMentRestService.update(ib.getOrderid(),ib.getUserid(),ib.getAddressid());
	 // Call Kafka Procedure with orderid
	 orderItemReduceProdure.orderPush(new OrderedData( Long.parseLong(ib.getOrderid())));
	 return new  ResponseEntity<ResponseBean>(responseBean, HttpStatus.ACCEPTED); }

	
	
	@GetMapping(path=CustomerURLMapping.SPP1)
	@ResponseBody
	public ResponseEntity<PageCountBean> getSPPageCount(@PathVariable int page, @PathVariable int count){
		return new ResponseEntity<PageCountBean>(new PageCountBean(selectedproductsRestService.getSelectedProductsPageCount(page, count)) , HttpStatus.ACCEPTED);
	}
	
	@GetMapping(path=CustomerURLMapping.SPP2)
	@ResponseBody
	public ResponseEntity<List<Selectedproducts>> getSPPageData(@PathVariable int page, @PathVariable int count){
		return new ResponseEntity<List<Selectedproducts>>(selectedproductsRestService.getAllSelectedProductsPaging(page, count), HttpStatus.ACCEPTED);
	}
	
	
	
	@PostMapping(path="/marketing")
	@ResponseBody
	public ResponseEntity<ResponseBean> setMarketingMail(@RequestBody BulkMailBean bmb){
		String subject = bmb.getSubject();
		for(String s: bmb.getToEmail()) {
			System.err.println("#####################################################################################");
			CommonMail1 cm = new CommonMail1();
			cm.setTomail(s);
			cm.setSubject(subject);
			cm.setMessage(" ");
			System.err.println(cm.toString());
			emailProducer.push2(cm);
			System.err.println("#####################################################################################");
		}
		return new  ResponseEntity<ResponseBean>(new ResponseBean("Emails sending in Queue...", 2002, System.currentTimeMillis()), HttpStatus.ACCEPTED); 
		
	}
	
	@GetMapping(path = CustomerURLMapping.VALIDATEPHONE_EMAIL,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<LilCustomerBean> ValidateEMAIL_PHONE(@PathVariable String email,@PathVariable String phone) {
		return new ResponseEntity<LilCustomerBean>(lilCustomerRestervice.Validate(email, phone), 
				HttpStatus.ACCEPTED);
	}
	
	
	
	/* Product Rack Seller Rest Services code Start here */
	
	/* SellerProductIdentityRepository code start here for Inventory Service*/
	@PostMapping(path="/spil")
	@ResponseBody
	public ResponseEntity<ResponseBean> insertSellerProductIdentity(@RequestBody Sellerproductidentity obj){
		ResponseBean ob = productRackSellerRestService.insertSellerProductIdentity(obj.getSkuid(), obj.getFlipkartid(), obj.getAmazonid(), obj.getPaytmid(), obj.getMeeshoid(), obj.getS1(), obj.getS2(), obj.getS3());
		return new ResponseEntity<ResponseBean>(ob, HttpStatus.ACCEPTED);
	}
	
	@GetMapping(path="/spil")
	@ResponseBody
	public ResponseEntity<List<Sellerproductidentity>> getAllSellerProductIdentityList(){
		return new ResponseEntity<List<Sellerproductidentity>>(productRackSellerRestService.getAllProductIdentities(), HttpStatus.ACCEPTED);
	}
	
	@PutMapping(path="/spil")
	@ResponseBody
	public ResponseEntity<ResponseBean> updateSellerProductIdentity(@RequestBody Sellerproductidentity obj){
		ResponseBean ob = productRackSellerRestService.updateSellerProductIdentity(obj.getUid(),obj.getSkuid(), obj.getFlipkartid(), obj.getAmazonid(), obj.getPaytmid(), obj.getMeeshoid(), obj.getS1(), obj.getS2(), obj.getS3());
		return new ResponseEntity<ResponseBean>(ob, HttpStatus.ACCEPTED);
	}	
	/* SellerProductIdentityRepository code end here for Inventory Service*/
	
	/* SellerProductIdentityRepository code start here for Inventory Service*/
	@PostMapping(path="/rack")
	@ResponseBody
	public ResponseEntity<ResponseBean> insertRack(@RequestBody Racks obj){
		ResponseBean ob = productRackSellerRestService.insertRack(obj.getRackid());
		return new ResponseEntity<ResponseBean>(ob, HttpStatus.ACCEPTED);
	}
	
	@GetMapping(path="/rack")
	@ResponseBody
	public ResponseEntity<List<Racks>> getAllRackList(){
		return new ResponseEntity<List<Racks>>(productRackSellerRestService.getAllRacks(), HttpStatus.ACCEPTED);
	}
	
	
	/* SellerProductIdentityRepository code end here for Inventory Service*/
	
	/*Rack Identity Repository code start here  for Inventory Service */
	@PostMapping(path="/rackidentity")
	@ResponseBody
	public ResponseEntity<ResponseBean> insertRackIdentity(@RequestBody Rackidentity obj){
		ResponseBean ob = productRackSellerRestService.insertRackIdentity(obj.getSkuid(), obj.getRackid());
		return new ResponseEntity<ResponseBean>(ob, HttpStatus.ACCEPTED);
	}
	
	@GetMapping(path="/rackidentity")
	@ResponseBody
	public ResponseEntity<List<Rackidentity>> getAllRackIdentityList(){
		return new ResponseEntity<List<Rackidentity>>(productRackSellerRestService.getAllRackIdentities(), HttpStatus.ACCEPTED);
	}
	
	@PutMapping(path="/rackidentity")
	@ResponseBody
	public ResponseEntity<ResponseBean> updateSellerRackIdentity(@RequestBody Rackidentity obj){
		ResponseBean ob = productRackSellerRestService.updateRackIdentity(obj.getUid(), obj.getSkuid(), obj.getRackid());
		return new ResponseEntity<ResponseBean>(ob, HttpStatus.ACCEPTED);
	}	
	/*Rack Identity Repository code end here  for Inventory Service */
	/* Product Rack Seller Rest Services code End here */
	
	
	
	/*Sales Report code start here */
	@GetMapping(path="/bill")
	@ResponseBody
	public ResponseEntity<List<ReceiptBean>> getAllReceipts(){
		List<ReceiptBean> rbb = salesReportRestService.getAllSoldlist();
		Collections.sort(rbb, new ReceiptBeanSort());
		return new ResponseEntity<List<ReceiptBean>>(rbb, HttpStatus.ACCEPTED);
	}
	@GetMapping(path="/bill/{invoiceid}")
	@ResponseBody
	public ResponseEntity<ReceiptBean> getAllReceipts1(@PathVariable long invoiceid){
		return new ResponseEntity<ReceiptBean>(salesReportRestService.getAllSoldlistByInvoiceId(invoiceid), HttpStatus.ACCEPTED);
	}
	
	@PostMapping(path="/bill")
	@ResponseBody
	public ResponseEntity<ResponseBean> insertReportDetails(@RequestBody ReceiptBean req){
		System.err.println("Calling post bean...");
		System.err.println(req.toString());
		long skuid[] = new long[req.getSoldProducts().size()];
		long qty[] = new long[req.getSoldProducts().size()];
		double netamount[]= new double[req.getSoldProducts().size()];
		
		for(int i=0;i<req.getSoldProducts().size();i++) {
			skuid[i]=req.getSoldProducts().get(i).getSkuid();
			qty[i]=req.getSoldProducts().get(i).getQty();
			netamount[i]=req.getSoldProducts().get(i).getNetamount();
		}
		ResponseBean rb = salesReportRestService.insertSalesDetails(req.getSoldfrom(), req.getOrderid(), req.getSellerinvoice(), req.getAwb(), 
				req.getTrackingid(), req.getState(), req.getHsn(),req.getTax(), req.getTaxtype(), req.getDiscount(), req.getTaxamount(), 
				req.getTotalamount(), req.getPaidtype(), 
				req.getTransactionid(), req.getInvoicedate()+"", req.getOrderdate()+"",skuid, qty, netamount,
				req.getShippingcharges(), req.getShippingtaxamount(), req.getShippingtotalamount());
		return new ResponseEntity<ResponseBean>(rb, HttpStatus.ACCEPTED);		
	}
	
	@PutMapping(path="/bill")
	@ResponseBody
	public ResponseEntity<ResponseBean> updateReportDetails(@RequestBody ReceiptBean req){
		long skuid[] = new long[req.getSoldProducts().size()];
		long qty[] = new long[req.getSoldProducts().size()];
		double netamount[]= new double[req.getSoldProducts().size()];
		long itemsoldid[] = new long[req.getSoldProducts().size()];
		long invids[] = new long[req.getSoldProducts().size()];
		
		System.out.println("*****************************************************************");
		System.out.println(req);
		System.out.println("*****************************************************************");
		for(int i=0;i<req.getSoldProducts().size();i++) {
			skuid[i]=req.getSoldProducts().get(i).getSkuid();
			qty[i]=req.getSoldProducts().get(i).getQty();
			netamount[i]=req.getSoldProducts().get(i).getNetamount();
			itemsoldid[i]=req.getSoldProducts().get(i).getItemssoldid();
			invids[i]=req.getSoldProducts().get(i).getInvoiceid();
		}
		ResponseBean rb = salesReportRestService.updateSalesDetails(
				req.getInvoiceid(), 
				req.getSoldfrom(), 
				req.getOrderid(), 
				req.getSellerinvoice(), 
				req.getAwb(), 
				req.getTrackingid(), 
				req.getState(), 
				req.getHsn(),
				req.getTax(), 
				req.getTaxtype(), 
				req.getDiscount(),
				req.getTaxamount(), 
				req.getTotalamount(), 
				req.getPaidtype(), 
				req.getTransactionid(), 
				itemsoldid,
				skuid, 
				qty, 
				netamount, 
				invids,
				req.getShippingcharges(),
				req.getShippingtaxamount(),
				req.getShippingtotalamount());
		return new ResponseEntity<ResponseBean>(rb, HttpStatus.ACCEPTED);		
	}
	
	
	@GetMapping(path="/bill1")
	@ResponseBody
	public ResponseEntity<List<CalenderBean>> getAllCalenderList(){
		return new ResponseEntity<List<CalenderBean>>(salesReportRestService.getAllCalenderlist(), HttpStatus.ACCEPTED);
	}
	@GetMapping(path="/bill1/{month}/{year}")
	@ResponseBody
	public ResponseEntity<List<ReceiptBean>> getAllReceipts1(@PathVariable long month, @PathVariable long year){
		List<ReceiptBean> rbb = salesReportRestService.getAllSoldlist1(month, year);
		Collections.sort(rbb, new ReceiptBeanSort());
		return new ResponseEntity<List<ReceiptBean>>(rbb, HttpStatus.ACCEPTED);
	}
	
	@GetMapping(path="/billx/{custid}/{qty}")
	@ResponseBody
	public ResponseEntity<ResponseBean> reduceQtyForProduct(@PathVariable String custid, @PathVariable String qty){
		return new ResponseEntity<ResponseBean>(productQuantityRestService.updateProductOnlyQuantities(custid, qty), HttpStatus.ACCEPTED);
	}
	
	/*Sales report ends here */
}
