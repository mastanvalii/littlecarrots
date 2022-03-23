package com.lc.sk.inventory.security.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.lc.sk.inventory.security.beans.*;
import com.lc.sk.inventory.security.cache.ProductCacheLoader;
import com.lc.sk.inventory.security.entities.Notifications;
import com.lc.sk.inventory.security.exceptions.subexceptions.DataNotFoundException;
import com.lc.sk.inventory.security.exceptions.subexceptions.NotificationNotFoundException;
import com.lc.sk.inventory.security.exceptions.subexceptions.NullRequestReceivedException;
import com.lc.sk.inventory.security.repositories.NotificationsRepository;
import com.lc.sk.inventory.security.rest.ApprovalRestService;
import com.lc.sk.inventory.security.rest.AuthorizationRestService;
import com.lc.sk.inventory.security.rest.AuthorizedUsersRestService;
import com.lc.sk.inventory.security.rest.BadgesRestService;
import com.lc.sk.inventory.security.rest.BatchRestService;
import com.lc.sk.inventory.security.rest.BlogReportRestService;
import com.lc.sk.inventory.security.rest.CategoriesRestService;
import com.lc.sk.inventory.security.rest.CollectionsaleImagesRestService;
import com.lc.sk.inventory.security.rest.CollectionsaleRestService;
import com.lc.sk.inventory.security.rest.CollectionsaleprodRest;
import com.lc.sk.inventory.security.rest.ColorRestService;
import com.lc.sk.inventory.security.rest.CombosRestService;
import com.lc.sk.inventory.security.rest.ComplaintBoxRestService;
import com.lc.sk.inventory.security.rest.ContestRestService;
import com.lc.sk.inventory.security.rest.CountriesRestService;
import com.lc.sk.inventory.security.rest.CouponsRestService;
import com.lc.sk.inventory.security.rest.CustomerAddressRestService;
import com.lc.sk.inventory.security.rest.DisplayTypeRestService;
import com.lc.sk.inventory.security.rest.EmailSubscribeRestService;
import com.lc.sk.inventory.security.rest.GendersRestService;
import com.lc.sk.inventory.security.rest.GrannyBlogRestService;
import com.lc.sk.inventory.security.rest.ImagesResetService;
import com.lc.sk.inventory.security.rest.InvoiceManagementRestService;
import com.lc.sk.inventory.security.rest.LilCustomerRestService;
import com.lc.sk.inventory.security.rest.MaterialcompositionRestService;
import com.lc.sk.inventory.security.rest.MaterialtypesRestService;
import com.lc.sk.inventory.security.rest.NoOfPiecesRestService;
import com.lc.sk.inventory.security.rest.OccasionalWearRestService;
import com.lc.sk.inventory.security.rest.OrderManageMentRestService;
import com.lc.sk.inventory.security.rest.OrdercodesRestService;
import com.lc.sk.inventory.security.rest.PackageshippingRest;
import com.lc.sk.inventory.security.rest.PatternsRestService;
import com.lc.sk.inventory.security.rest.PricesTableRestService;
import com.lc.sk.inventory.security.rest.ProductAgeRestService;
import com.lc.sk.inventory.security.rest.ProductDescriptionsRestService;
import com.lc.sk.inventory.security.rest.ProductDetailsRestService;
import com.lc.sk.inventory.security.rest.ProductKeywordsRestService;
import com.lc.sk.inventory.security.rest.ProductPaginationRestService;
import com.lc.sk.inventory.security.rest.ProductTypeRestService;
import com.lc.sk.inventory.security.rest.ProductVariationRestServie;
import com.lc.sk.inventory.security.rest.ProductquantitiesRestService;
import com.lc.sk.inventory.security.rest.ProductsRestService;
import com.lc.sk.inventory.security.rest.SalesSellerRestService;
import com.lc.sk.inventory.security.rest.SeasonWearRestService;
import com.lc.sk.inventory.security.rest.SelectedproductsRestService;
import com.lc.sk.inventory.security.rest.SellerRestService;
import com.lc.sk.inventory.security.rest.SellerToWarehouseRestService;
import com.lc.sk.inventory.security.rest.SizesRestService;
import com.lc.sk.inventory.security.rest.SubCategoriesRestService;
import com.lc.sk.inventory.security.rest.UnFilledProductsRestServices;
import com.lc.sk.inventory.security.rest.WarehousesRestService;
import com.lc.sk.inventory.security.rest.WishlistRestService;
import com.lc.sk.inventory.security.util.ConstantValues;
import com.lc.sk.inventory.security.util.ConsumerURLMapping;
import com.lc.sk.inventory.security.util.CustomerURLMapping;
import com.lc.sk.inventory.security.util.HeaderComponent;
import com.lc.sk.inventory.security.util.SecurityHttpStatus;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Validated
@RequestMapping(value = ConsumerURLMapping.CEP + ConsumerURLMapping.VERSION)
public class ConsumerEndPoint {

	@Autowired
	private HeaderComponent headers;
	@Autowired
	BatchRestService batchRestService;
	@Autowired
	WarehousesRestService warehousesRestService;
	@Autowired
	private AuthorizedUsersRestService authorizedUsersRestService;
	@Autowired
	CategoriesRestService categoriesRestService;
	@Autowired
	ColorRestService colorRestService;
	@Autowired
	CountriesRestService countriesRestService;
	@Autowired
	GendersRestService gendersRestService;
	@Autowired
	MaterialcompositionRestService materialcompositionRestService;
	@Autowired
	MaterialtypesRestService materialtypesRestService;
	@Autowired
	NoOfPiecesRestService noOfPiecesRestService;
	@Autowired
	OccasionalWearRestService occasionalwearRestService;
	@Autowired
	PatternsRestService patternsRestService;
	@Autowired
	PricesTableRestService pricesTableRestService;
	@Autowired
	ProductAgeRestService productAgeRestService;
	@Autowired
	ProductDescriptionsRestService productDescriptionsRestService;
	@Autowired
	ProductquantitiesRestService productquantitiesRestService;
	@Autowired
	ProductsRestService productsRestService;
	@Autowired
	ProductTypeRestService producttypeRestService;
	@Autowired
	private SalesSellerRestService salesSellerRestService;
	@Autowired
	SeasonWearRestService seasonwearRestService;
	@Autowired
	SellerToWarehouseRestService sellerToWarehouseRestService;
	@Autowired
	SizesRestService sizesRestService;
	@Autowired
	SubCategoriesRestService subcategoriesRestService;
	@Autowired
	private AuthorizationRestService authorizationRestService;
	@Autowired
	private SellerRestService sellerRestService;

	@Autowired
	private ImagesResetService imageResetService;

	@Autowired
	private ApprovalRestService approvalRestService;

	@Autowired
	private ProductDetailsRestService productDetailsRestSErvice;

	@Autowired
	private UnFilledProductsRestServices unFilledProductsRestServices;

	@Autowired
	ProductPaginationRestService productPaginationRestService;

	@Autowired
	DisplayTypeRestService displayTypeRestService;

	@Autowired
	BadgesRestService badgesrestservice;

	@Autowired
	CollectionsaleRestService collectionsaleRestService;

	@Autowired
	SelectedproductsRestService selectedproductsRestService;

	@Autowired
	WishlistRestService wishlistRestService;

	@Autowired
	BlogReportRestService blogreportRestService;

	@Autowired
	GrannyBlogRestService grannyblogRestService;

	@Autowired
	CollectionsaleprodRest collectionsaleprodrest;

	@Autowired
	CollectionsaleImagesRestService collectionsaleimagerest;

	@Autowired
	ProductVariationRestServie productvariationRestService;

	@Autowired
	ProductKeywordsRestService productKeywordsRestService;

	@Autowired
	OrdercodesRestService ordercodesRestService;

	@Autowired
	OrderManageMentRestService ordermanagementRestService;

	@Autowired
	CustomerAddressRestService CUSTADD_SERVICE;

	@Autowired
	LilCustomerRestService lilCustomerRestervice;

	@Autowired
	PackageshippingRest packageshippingRest;

	@Autowired
	EmailSubscribeRestService emailSubscribeRestService;
	
	@Autowired
	ProductCacheLoader pcl;
	
	@Autowired
	ContestRestService contestRest;
	
	
	@Autowired
	CouponsRestService couponRest;
	
	@Autowired
	ComplaintBoxRestService complaintBoxRest;
	
	@Autowired
	InvoiceManagementRestService invoiceManagemenrRest;

	@Autowired
	NotificationsRepository notificationrepo;
	
	
	@Autowired
	CombosRestService comborest;
	/* authorization role code start */

	@GetMapping(path=ConsumerURLMapping.USERROLES,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Authorization>> getAllAuthorizationsList() {
		List<Authorization> authroizations = authorizationRestService.getAllRoleList();
		return new ResponseEntity<List<Authorization>>(authroizations, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@GetMapping(path=ConsumerURLMapping.USERROLES_BY_NAME,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Authorization> getAuthorizationByRolename(@PathVariable String rolename) {
		Authorization authorization = authorizationRestService.getRoleByName(rolename);
		return new ResponseEntity<Authorization>(authorization, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@PostMapping(path=ConsumerURLMapping.USERROLES,produces = MediaType.APPLICATION_JSON_VALUE) // CODE FOR POST METHOD
	@ResponseBody
	public ResponseEntity<ResponseBean> insertData(@RequestBody UserRoles userroles) {
		return new ResponseEntity<ResponseBean>(authorizationRestService.insertAuthorization(userroles.getRolename(),
				userroles.getDescription(), userroles.getStatus()), headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@PutMapping(path=ConsumerURLMapping.USERROLES_BY_STATUS,produces = MediaType.APPLICATION_JSON_VALUE) // CODE FOR PUT
	@ResponseBody
	public ResponseEntity<ResponseBean> updateStatus(@PathVariable String rolename, @PathVariable boolean status) {
		return new ResponseEntity<ResponseBean>(authorizationRestService.updateAuthorizationStatus(rolename, status),
				headers.getHeader(), HttpStatus.ACCEPTED);
	}

	/* authorization role code end */

	// AUTHORIZED USERS SERVICE ACCESS
	@GetMapping(path=ConsumerURLMapping.AUTH_USERS,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<AuthorizedUsers>> getAllAuthorizedUsersList() {
		List<AuthorizedUsers> authorizedUsers = authorizedUsersRestService.getAllAuthorizedUsers();
		return new ResponseEntity<List<AuthorizedUsers>>(authorizedUsers, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@GetMapping(path=ConsumerURLMapping.AUTH_USERS_PATH_VARIABLE,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<AuthorizedUsers> getAuthorizedUserByusername(@PathVariable String username) {
		AuthorizedUsers authorizedUser = authorizedUsersRestService.getAuthorizedUserByUsername(username);
		return new ResponseEntity<AuthorizedUsers>(authorizedUser, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@PostMapping(path=ConsumerURLMapping.AUTH_USERS,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> insertAuthorizedUser(@RequestBody AppUserBean user) {
		ResponseBean responseBean = authorizedUsersRestService.insertNewUser(user.getUsername(), user.getPassword(),
				user.getEmail(), user.getStatus(), user.getRolename());
		return new ResponseEntity<ResponseBean>(responseBean, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@PutMapping(path=ConsumerURLMapping.AUTH_USER_BLOCK)
	@ResponseBody
	public ResponseEntity<ResponseBean> blockAuthorizedUser(@PathVariable String username,
			@PathVariable boolean status) {
		ResponseBean responseBean = authorizedUsersRestService.blockUser(username, status);
		return new ResponseEntity<ResponseBean>(responseBean, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@PutMapping(path=ConsumerURLMapping.AUTH_USERS,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> passwordChange(@RequestBody PasswordUpdateBean pub) {
		ResponseBean responseBean = authorizedUsersRestService.passwordUpdate(pub.getUsername(), pub.getPassword(),
				pub.getNewPassword());
		return new ResponseEntity<ResponseBean>(responseBean, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	/* auth user code end */

	/* seller user code start */
	@GetMapping(path=ConsumerURLMapping.SELLER_GET_PATH,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<SellerUsers>> getAllSalesUsers() {
		List<SellerUsers> salesUsers = salesSellerRestService.getAllSalesUsers();
		return new ResponseEntity<List<SellerUsers>>(salesUsers, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@GetMapping(path=ConsumerURLMapping.SELLER_GET_PATH_WITH_VARIABLE,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<SellerUsers> getSalesUser(@PathVariable String username) {
		SellerUsers sallerUser = salesSellerRestService.getSalesUserByusername(username);
		return new ResponseEntity<SellerUsers>(sallerUser, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@PostMapping(path=ConsumerURLMapping.SELLER_GET_PATH,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> insertNewSalesUser(@RequestBody SalesUser salesUser) {
		ResponseBean responseBean = salesSellerRestService.insertNewSalesUser(salesUser.getUsername(),
				salesUser.getPassword(), salesUser.getEmail(), salesUser.getFullname(), salesUser.getRolename(),
				salesUser.getSellerid(), salesUser.getStatus());
		return new ResponseEntity<ResponseBean>(responseBean, headers.getHeader(), HttpStatus.ACCEPTED);

	}

	@PutMapping(path=ConsumerURLMapping.SELLER_PUT_STATUS,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> updateStatus(@PathVariable String username, @PathVariable String status) {
		ResponseBean responseBean = salesSellerRestService.updateSalesUserStatus(username, status);
		return new ResponseEntity<ResponseBean>(responseBean, headers.getHeader(), HttpStatus.ACCEPTED);

	}

	@PutMapping(path=ConsumerURLMapping.SELLER_GET_PATH,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> updateSalesUserPassword(@RequestBody PasswordUpdateBean pub) {
		ResponseBean responseBean = salesSellerRestService.updateSalesUserPasswrod(pub.getUsername(), pub.getPassword(),
				pub.getNewPassword());
		return new ResponseEntity<ResponseBean>(responseBean, headers.getHeader(), HttpStatus.ACCEPTED);

	}

	/* seller user code end */

	/* seller role code start */
	@GetMapping(path=ConsumerURLMapping.SELLERROLE_GET_PATH,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Seller>> getAllSellers() {
		List<Seller> sellers = sellerRestService.getAllSellerDetails();
		return new ResponseEntity<List<Seller>>(sellers, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@GetMapping(path=ConsumerURLMapping.SELLERROLE_GET_PATH_WITH_VARIABLE,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Seller> getAllSellers(@PathVariable Long sellerid) {
		Seller sellers = sellerRestService.getSellerById(sellerid);
		return new ResponseEntity<Seller>(sellers, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@PostMapping(path=ConsumerURLMapping.SELLERROLE_GET_PATH,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> insertNewSeller(@RequestBody NewSellerBean nsb) {

		ResponseBean responseBean = sellerRestService.insertSeller(nsb.getSellercompanyname(), nsb.getSellername(),
				nsb.getPhone() + "", nsb.getEmail(), nsb.getAddress(), nsb.getIsocode(), nsb.getStatus());
		return new ResponseEntity<ResponseBean>(responseBean, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@PutMapping(path=ConsumerURLMapping.SELLERROLE_GET_PATH,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> updateSellerDetails(@RequestBody Seller seller) {
		ResponseBean responseBean = sellerRestService.updateSeller(seller.getSellerid() + "",
				seller.getSellercompanyname(), seller.getSellername(), seller.getPhone() + "", seller.getEmail(),
				seller.getAddress(), seller.getIsocode(), seller.getStatus() + "");
		return new ResponseEntity<ResponseBean>(responseBean, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	/* seller role code end */

	/* warehouse code start */
	@GetMapping(path=ConsumerURLMapping.WAREHOUSES_GET_PATH,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Warehouses>> getAllWarehouseDetails() {
		List<Warehouses> warehouses = warehousesRestService.getAllWarehouses();
		return new ResponseEntity<List<Warehouses>>(warehouses, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@GetMapping(path=ConsumerURLMapping.WAREHOUSES_GET_PATH1,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Warehouses>> getAllWarehouseDetails1() {
		List<Warehouses> warehouses = warehousesRestService.getAllWarehouses();
		for (int i = 0; i < warehouses.size(); i++) {
			if (!warehouses.get(i).getWarehouse_status()) {
				warehouses.remove(i);
			}
		}
		return new ResponseEntity<List<Warehouses>>(warehouses, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@GetMapping(path=ConsumerURLMapping.WAREHOUSES_MAPPING_PATH_WITH_PATH_VARIABLE,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Warehouses> getByIdwarehouse(@PathVariable String warehouseid) {
		Warehouses warehouse = warehousesRestService.getById(warehouseid);
		return new ResponseEntity<Warehouses>(warehouse, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@PostMapping(path=ConsumerURLMapping.WAREHOUSES_GET_PATH,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> insertNewWarehouse(@RequestBody WarehouseBean wb) {
		ResponseBean responseBean = warehousesRestService.insertNewWarehouse(wb.getWarehousename(),
				wb.getContactpersonname(), wb.getContactphone(), wb.getEmail(), wb.getAddress(), wb.getPincode(),
				wb.getEst(), wb.getStatus(), wb.getUsername(), wb.getUsername(), wb.getIsocode());
		return new ResponseEntity<ResponseBean>(responseBean, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@PutMapping(path=ConsumerURLMapping.WAREHOUSES_GET_PATH,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> update(@RequestBody Warehouses wh) {

		ResponseBean responseBean = warehousesRestService.update(wh.getWarehouseid() + "", wh.getWarehousename() + "",
				wh.getContactpersonname(), wh.getContactphone() + "", wh.getEmail(), wh.getAddress(),
				wh.getPincode() + "", wh.getEst() + "", wh.getWarehouse_status() + "", wh.getWhoupdated(),
				wh.getIsocode());
		return new ResponseEntity<ResponseBean>(responseBean, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	/* warehouse code end */

	/* Batch code start */

	@PostMapping(path=ConsumerURLMapping.BATCHES_GET_PATH,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> insertNewBatch(@RequestBody NewBatch nb) {
		ResponseBean responseBean = batchRestService.insertBatch(nb.getWarehouseid(), nb.getDateofpurchase(),
				nb.getPurchasedby(), nb.getInvamount(), nb.getWhoinserted(), nb.getStatus(), nb.getIsocode(),
				nb.getQty(), nb.getSellerid());
		return new ResponseEntity<ResponseBean>(responseBean, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@GetMapping(path=ConsumerURLMapping.BATCHES_GET_PATH,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Batch>> getAllBatchList() {
		List<Batch> batches = batchRestService.getAllBatchList();
		return new ResponseEntity<List<Batch>>(batches, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@GetMapping(path=ConsumerURLMapping.BATCHES_MAPPING_PATH_WITH_PATH_VARIABLE,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Batch> getBatchById(@PathVariable String batchid) {
		Batch batch = batchRestService.getBatchById(batchid);
		return new ResponseEntity<Batch>(batch, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@PutMapping(path=ConsumerURLMapping.BATCHID_PUT_PATH_WITH_VARIABLE,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> updateBatch(@PathVariable String batchid, @PathVariable String status) {
		ResponseBean responseBean = batchRestService.updateBatch(batchid, status);
		return new ResponseEntity<ResponseBean>(responseBean, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	/* Batch code end */

	/* CategoriesService start end */
	@GetMapping(path=ConsumerURLMapping.CATEGORIES_MAPPING_PATH_WITH_ID,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Categories> getById(@PathVariable String catid) {
		Categories categories = categoriesRestService.getById(catid);
		return new ResponseEntity<Categories>(categories, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@GetMapping(path=ConsumerURLMapping.CATEGORIES_MAPPING_PATH,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Categories>> getAllCategories() {

		List<Categories> categories = categoriesRestService.getAllCategories();
		return new ResponseEntity<List<Categories>>(categories, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@PutMapping(path=ConsumerURLMapping.CATEGORIES_MAPPING_PATH,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> update(@RequestBody Categories cats) {
		ResponseBean responseBean = categoriesRestService.update(cats.getCatid() + "", cats.getCategory(),
				cats.getGender());
		return new ResponseEntity<ResponseBean>(responseBean, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@PostMapping(path=ConsumerURLMapping.CATEGORIES_MAPPING_PATH,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> insertNewCategory(@RequestBody NewCategory nc) {
		ResponseBean responseBean = categoriesRestService.addCategory(nc.getCategory(), nc.getGender());
		return new ResponseEntity<ResponseBean>(responseBean, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	/* CategoriesService code end */

	/* ColorRestService code start */

	@PostMapping(path=ConsumerURLMapping.COLOR_PATH,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> insertNewColor(@RequestBody NewColor nc) {
		ResponseBean responseBean = colorRestService.insertNewColor(nc.getColorname(), nc.getHashcode());
		return new ResponseEntity<ResponseBean>(responseBean, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@PutMapping(path=ConsumerURLMapping.COLOR_PATH,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> updateColor(@RequestBody Color color) {
		ResponseBean responseBean = colorRestService.updateColor(color.getColorid() + "", color.getColorname(),
				color.getHashcode());
		return new ResponseEntity<ResponseBean>(responseBean, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@GetMapping(path=ConsumerURLMapping.COLOR_PATH,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Color>> getAllColors() {
		List<Color> responseBean = colorRestService.getAllColors();
		return new ResponseEntity<List<Color>>(responseBean, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@GetMapping(path=ConsumerURLMapping.COLOR_PATH_WITH_COLOR_ID,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Color> getColorById(@PathVariable String colorid) {
		Color responseBean = colorRestService.getColorById(colorid);
		return new ResponseEntity<Color>(responseBean, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	/* ColorRestService code end */

	/* CountriesService code start */

	@GetMapping(path=ConsumerURLMapping.COUNTRY_GET_PATH,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Countries>> getAllCountries() {
		List<Countries> countries = countriesRestService.getAllCountries();
		return new ResponseEntity<List<Countries>>(countries, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@GetMapping(path=ConsumerURLMapping.COUNTRY_GET_PATH_WITH_VARIABLE,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Countries> getCountryById(@PathVariable String isocode) {
		Countries country = countriesRestService.getCountryById(isocode);
		return new ResponseEntity<Countries>(country, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@PutMapping(path=ConsumerURLMapping.COUNTRY_PUT_PATH_WITH_VARIABLE,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> enableCountry(@PathVariable String isocode, @PathVariable boolean status) {
		ResponseBean responseBean = countriesRestService.enableCountry(isocode, status);
		return new ResponseEntity<ResponseBean>(responseBean, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@GetMapping(path=ConsumerURLMapping.COUNTRY_ACTIVE_GET,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Countries>> getActivecountries() {
		List<Countries> countries = countriesRestService.getActiveCountries();
		return new ResponseEntity<List<Countries>>(countries, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	/* CountriesService code start */

	/* GendersService code start */
	@GetMapping(path=ConsumerURLMapping.GENDERS_GET_PATH,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Genders>> getAllGendersServiceDetails() {
		List<Genders> genders = gendersRestService.getAllGendersDetails();
		return new ResponseEntity<List<Genders>>(genders, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@GetMapping(path=ConsumerURLMapping.GENDERS_MAPPING_PATH_WITH_PATH_VARIABLE,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Genders> getGenderById(@PathVariable String genderid) {
		Genders genders = gendersRestService.getGenderById(genderid);

		return new ResponseEntity<Genders>(genders, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	/* GendersService code end */

	/* MaterialcompositionService code start */
	@PostMapping(path=ConsumerURLMapping.MATERIALCOMP_GET_PATH,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> insertMaterialcomposition(@RequestBody Materialcomposition matcomp)

	{
		ResponseBean responseBean = materialcompositionRestService
				.insertMaterialcomposition(matcomp.getMaterialid() + "", matcomp.getDescription() + "");
		return new ResponseEntity<ResponseBean>(responseBean, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@PutMapping(path=ConsumerURLMapping.MATERIALCOMP_GET_PATH,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> updateMaterialcomposition(@RequestBody Materialcomposition matcomp) {
		ResponseBean responseBean = materialcompositionRestService
				.updateMaterialcomposition(matcomp.getMaterialid() + "", matcomp.getDescription());
		return new ResponseEntity<ResponseBean>(responseBean, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@GetMapping(path=ConsumerURLMapping.MATERIALCOMP_GET_PATH,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Materialcomposition>> getAllMaterialcomposition() {
		List<Materialcomposition> materialcomposition = materialcompositionRestService.getAllMaterialcomposition();
		return new ResponseEntity<List<Materialcomposition>>(materialcomposition, headers.getHeader(),
				HttpStatus.ACCEPTED);
	}

	@GetMapping(path=ConsumerURLMapping.MATERIALCOMPOSITION_GET_PATH_WITH_VARIABLE,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Materialcomposition> getMaterialcompositionById(@PathVariable String materialid) {
		Materialcomposition materialcomposition = materialcompositionRestService.getMaterialcompositionById(materialid);
		return new ResponseEntity<Materialcomposition>(materialcomposition, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	/* MaterialcompositionService code end */

	/* MaterialtypesService code start */
	@PostMapping(path=ConsumerURLMapping.MATERIAL_GET_PATH,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> insertMaterialtype(@RequestBody NewMaterialtypes mattypes)

	{
		ResponseBean responseBean = materialtypesRestService.insertMaterialtype(mattypes.getMaterialname() + "",
				mattypes.getDescription() + "", mattypes.getOcid() + "", mattypes.getSeasonid() + "",
				mattypes.getCatid() + "");
		return new ResponseEntity<ResponseBean>(responseBean, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@PutMapping(path=ConsumerURLMapping.MATERIAL_GET_PATH,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> updateMaterialtype(@RequestBody Materialtypes mattypes) {
		ResponseBean responseBean = materialtypesRestService.updateMaterialtype(mattypes.getMaterialid() + "",
				mattypes.getMaterialname() + "", mattypes.getDescription() + "", mattypes.getOcid() + "",
				mattypes.getSeasonid() + "", mattypes.getCatid() + "");
		return new ResponseEntity<ResponseBean>(responseBean, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@GetMapping(path=ConsumerURLMapping.MATERIAL_GET_PATH,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Materialtypes>> getAllMaterialtypes() {
		List<Materialtypes> materialtypes = materialtypesRestService.getAllMaterialtypes();
		return new ResponseEntity<List<Materialtypes>>(materialtypes, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@GetMapping(path=ConsumerURLMapping.MATERIAL_GET_PATH1,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Materialtypessubcat>> getAllMaterialtypessubcat(@PathVariable String sss,
			@PathVariable String sss1) {

		long temp_ocid, season_id, cat_id, season_sub_cat = 0, occassion_sub_cat = 0;
		List<Materialtypes> materialtypes = materialtypesRestService.getAllMaterialtypes();
		List<OccasionalWear> occasionalwear = occasionalwearRestService.getAllOccasionalWearDetails();
		List<SeasonWear> seasonwears = seasonwearRestService.getAllSeasonWearDetails();
		List<SubCategories> subcategories = subcategoriesRestService.getAllSubCategoriesDetails();
		List<Categories> categories = categoriesRestService.getAllCategories();

		List<Materialtypessubcat> obj = new CopyOnWriteArrayList<>();

		if (!materialtypes.isEmpty()) {
			for (Materialtypes me : materialtypes) {
				Materialtypessubcat ob = new Materialtypessubcat();
				ob.setMaterial_Name(me.getMaterialname());
				ob.setMaterial_ID(me.getMaterialid());
				ob.setDescription(me.getDescription());
				season_id = me.getSeasonid();
				temp_ocid = me.getOcid();
				cat_id = me.getCatid();
				for (SeasonWear sw : seasonwears) {
					if (sw.getSeasonId() == season_id) {
						season_sub_cat = sw.getSubcatId();
						ob.setSeason(sw.getSeason());
					}
				}
				for (OccasionalWear ow : occasionalwear) {
					if (ow.getOccasionid() == temp_ocid) {
						occassion_sub_cat = ow.getSubcatid();
						ob.setOccasion(ow.getOccaname());
					}
				}
				for (Categories cats : categories) {
					if (cats.getCatid() == cat_id) {
						ob.setCategory(cats.getCategory());
					}
				}
				for (SubCategories sc : subcategories) {
					if (sc.getSubcatid() == season_sub_cat) {
						ob.setSeason_Subcategory(sc.getCategoryname());
					}
					if (sc.getSubcatid() == occassion_sub_cat) {
						ob.setOccasion_Subcategory(sc.getCategoryname());
					}
				}
				obj.add(ob);
			}
		}

		return new ResponseEntity<List<Materialtypessubcat>>(obj, headers.getHeader(), HttpStatus.ACCEPTED);

	}

	@GetMapping(path=ConsumerURLMapping.MATERIALTYPE_GET_PATH_WITH_VARIABLE,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Materialtypes> getMaterialtypeById(@PathVariable String materialid) {
		Materialtypes materialtypes = materialtypesRestService.getMaterialtypeById(materialid);
		return new ResponseEntity<Materialtypes>(materialtypes, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@PostMapping(path=ConsumerURLMapping.METERIAL_GET_PATH3,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Materialtypes>> getDetailedMaterialType(@RequestBody ProductsPost pp) {
		List<Materialtypes> materials = new CopyOnWriteArrayList<>();

		List<Materialtypes> materialtypes = materialtypesRestService.getAllMaterialtypes();
//				List<OccasionalWear> occasionalwear = occasionalwearRestService.getAllOccasionalWearDetails();
//				List<SeasonWear> seasonwears = seasonwearRestService.getAllSeasonWearDetails();
//				List<SubCategories> subcategories = subcategoriesRestService.getAllSubCategoriesDetails();
//				List<Categories> categories = categoriesRestService.getAllCategories();
		if (!materialtypes.isEmpty() && pp.getCatid() != 0 && pp.getOccasionid() != 0 && pp.getSeasonid() != 0) {
			for (Materialtypes mt : materialtypes) {
				if (mt.getCatid() == pp.getCatid() && mt.getOcid() == pp.getOccasionid()
						&& mt.getSeasonid() == pp.getSeasonid()) {
					materials.add(mt);
				}
			}
		}

		return new ResponseEntity<List<Materialtypes>>(materials, headers.getHeader(), HttpStatus.ACCEPTED);
	}
	/* MaterialtypesService code end */

	/* NoOfPiecesService code start */
	@PostMapping(path=ConsumerURLMapping.NO_OF_PIECES_PATH,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> insertNoOfPiecesList(@RequestBody NoOfPiecesPost npp) {
		ResponseBean responseBean = noOfPiecesRestService.insertNoOfPiecesList(npp.getProductid() + "",
				npp.getNoofset() + "");
		return new ResponseEntity<ResponseBean>(responseBean, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@GetMapping(path=ConsumerURLMapping.NO_OF_PIECES_PATH,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<NoOfPieces>> getAllNoOfPieces() {
		List<NoOfPieces> responseBean = noOfPiecesRestService.getAllNoOfPieces();

		return new ResponseEntity<List<NoOfPieces>>(responseBean, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@GetMapping(path=ConsumerURLMapping.NO_OF_PIECES_PATH_WITH_ID,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<NoOfPieces> getNoOfPiecesId(@PathVariable String pieceid)

	{
		NoOfPieces responseBean = noOfPiecesRestService.getNoOfPiecesId(pieceid);

		return new ResponseEntity<NoOfPieces>(responseBean, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@PutMapping(path=ConsumerURLMapping.NO_OF_PIECES_PATH,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> NoOfPiecesServiceupdate(@RequestBody NoOfPieces np) {
		ResponseBean responseBean = noOfPiecesRestService.update(np.getPieceid() + "", np.getProductid() + "",
				np.getNoofset() + "");

		return new ResponseEntity<ResponseBean>(responseBean, headers.getHeader(), HttpStatus.ACCEPTED);
	}
	/* NoOfPiecesService code end */

	/* OccasionalWearService code start */
	@PostMapping(path=ConsumerURLMapping.OCCASIONALWEAR_PATH,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> insert4(@RequestBody OccasionalWearBean OWB) {
		ResponseBean responseBean = occasionalwearRestService.InsertOccasionalWearDetails(OWB.getOccaname(),
				OWB.getSubcatid() + "");
		return new ResponseEntity<ResponseBean>(responseBean, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@GetMapping(path=ConsumerURLMapping.OCCASIONALWEAR_PATH,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<OccasionalWear>> getAllOccasionalWearDetails() {
		List<OccasionalWear> occasionalwear = occasionalwearRestService.getAllOccasionalWearDetails();

		return new ResponseEntity<List<OccasionalWear>>(occasionalwear, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@GetMapping(path=ConsumerURLMapping.OCCASIONALWEAR_PATH1,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<OccasionCats>> getDetailsOccasion() {
		List<OccasionCats> occcat = new CopyOnWriteArrayList<>();
		List<Categories> cat = categoriesRestService.getAllCategories();
		List<SubCategories> sc = subcategoriesRestService.getAllSubCategoriesDetails();
		List<OccasionalWear> ow = occasionalwearRestService.getAllOccasionalWearDetails();
		if (!cat.isEmpty() && !sc.isEmpty() && !ow.isEmpty()) {
			for (Categories ca : cat) {
				for (SubCategories s : sc) {
					for (OccasionalWear o : ow) {
						if (o.getSubcatid() == s.getSubcatid() && s.getCatid() == ca.getCatid()) {
							occcat.add(new OccasionCats(o.getOccasionid(), ca.getCategory(), s.getCategoryname(),
									o.getOccaname(), ca.getGender()));
						}
					}
				}
			}
		} else {
			throw new DataNotFoundException(ConstantValues.NO_OCCASIONALWEAR_LIST_FOUND);
		}

		return new ResponseEntity<List<OccasionCats>>(occcat, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@GetMapping(path=ConsumerURLMapping.OCCASIONALWEAR_PATH_WITH_ID,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<OccasionalWear> getOccasionalWearById(@PathVariable String occasionid) {
		OccasionalWear occasionalwear = occasionalwearRestService.getOccasionalWearById(occasionid);

		return new ResponseEntity<OccasionalWear>(occasionalwear, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@PutMapping(path=ConsumerURLMapping.OCCASIONALWEAR_PATH,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> update(@RequestBody OccasionalWear OW) {
		ResponseBean responseBean = occasionalwearRestService.UpdateOccasionalWearDetails(OW.getOccasionid() + "",
				OW.getOccaname(), OW.getSubcatid() + "");
		return new ResponseEntity<ResponseBean>(responseBean, headers.getHeader(), HttpStatus.ACCEPTED);
	}
	/* OccasionalWearService code end */

	/* PatternsService code start */
	@PostMapping(path=ConsumerURLMapping.PATTERNS_PATH,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> insertPatternsService(@RequestBody PatternsPOSTBean PPB)

	{
		ResponseBean responseBean = patternsRestService.InsertPatternsDetails(PPB.getDescription(),
				PPB.getProductid() + "");
		return new ResponseEntity<ResponseBean>(responseBean, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@GetMapping(path=ConsumerURLMapping.PATTERNS_PATH,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Patterns>> getAllPatternsDetails() {
		List<Patterns> patterns = patternsRestService.getAllPatternsDetails();

		return new ResponseEntity<List<Patterns>>(patterns, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@GetMapping(path=ConsumerURLMapping.PATTERNS_PATH_WITH_ID,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Patterns> getPatternsById(@PathVariable String patid) {
		Patterns patterns = patternsRestService.getPatternsById(patid);

		return new ResponseEntity<Patterns>(patterns, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@PutMapping(path=ConsumerURLMapping.PATTERNS_PATH,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> PatternsServiceupdate(@RequestBody Patterns PB) {
		ResponseBean responseBean = patternsRestService.UpdatePatternsDetails(PB.getPatid() + "", PB.getDescription(),
				PB.getProductid() + "");

		return new ResponseEntity<ResponseBean>(responseBean, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	/* PatternsService code end */

	/* PricesTableService code start */
	@PostMapping(path=ConsumerURLMapping.PRICES_TABLE_PATH,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> insertPrices(@RequestBody PricesTablePost PTP) {
		ResponseBean responseBean = pricesTableRestService.insertprices(PTP.getMrp() + "", PTP.getProductid() + "",
				PTP.getLcPrice() + "", PTP.getTax() + "", PTP.getSellingPrice() + "", PTP.getDiscount() + "",
				PTP.getFinalPrice() + "");
		return new ResponseEntity<ResponseBean>(responseBean, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@GetMapping(path=ConsumerURLMapping.PRICES_TABLE_PATH_WITH_ID,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<PricesTable> PricesTableServicegetById(@PathVariable String productid) {
		PricesTable prices = pricesTableRestService.getpricebyid(productid);

		return new ResponseEntity<PricesTable>(prices, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@GetMapping(path=ConsumerURLMapping.PRICES_TABLE_PATH,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<PricesTable>> getallprices() {
		List<PricesTable> prices = pricesTableRestService.getallprices();

		return new ResponseEntity<List<PricesTable>>(prices, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@PutMapping(path = ConsumerURLMapping.PRICES_TABLE_PATH,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> updatePrices(@RequestBody PricesTable PT) {
		ResponseBean responseBean = pricesTableRestService.updateprices(PT.getPriceId() + "", PT.getMrp() + "",
				PT.getProductid() + "", PT.getLcPrice() + "", PT.getTax() + "", PT.getSellingPrice() + "",
				PT.getDiscount() + "", PT.getFinalPrice() + "");

		return new ResponseEntity<ResponseBean>(responseBean, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	/* ProductAgeService code start */

	@PostMapping(path=ConsumerURLMapping.PRODUCTAGE_PATH,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> addProductAge(@RequestBody ProductAge PA) {
		ResponseBean responseBean = productAgeRestService.addProductAge(PA.getAgeid() + "", PA.getDes());
		return new ResponseEntity<ResponseBean>(responseBean, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@GetMapping(path=ConsumerURLMapping.PRODUCTAGE_PATH,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<ProductAge>> getAll() {
		List<ProductAge> responseBean = productAgeRestService.getAll();

		return new ResponseEntity<List<ProductAge>>(responseBean, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@GetMapping(path=ConsumerURLMapping.PRODUCTAGE_PATH_WITH_ID,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ProductAge> getProductAgeById(@PathVariable String ageid)

	{
		ProductAge responseBean = productAgeRestService.getProductAgeById(ageid);

		return new ResponseEntity<ProductAge>(responseBean, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@PutMapping(path=ConsumerURLMapping.PRODUCTAGE_PATH,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> ProductAgeServiceupdate(@RequestBody ProductAge PA) {
		ResponseBean responseBean = productAgeRestService.update(PA.getAgeid() + "", PA.getDes());
		return new ResponseEntity<ResponseBean>(responseBean, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	/* ProductAgeService code end */

	/* ProductDescriptionsService code start */
	@PostMapping(path = ConsumerURLMapping.PRODUCTDESCRIPTION_MAPPING_PATH,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> insertion(@RequestBody ProductDescriptionsPost PDP) {
		ResponseBean responseBean = productDescriptionsRestService.insert(PDP.getDescription());
		return new ResponseEntity<ResponseBean>(responseBean, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@GetMapping(path=ConsumerURLMapping.PRODUCTDESCRIPTION_MAPPING_PATH_WITH_ID,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ProductDescriptions> ProductDescriptionsServicegetById(@PathVariable String descriptionid) {
		ProductDescriptions productDescriptions = productDescriptionsRestService.getById(descriptionid);

		return new ResponseEntity<ProductDescriptions>(productDescriptions, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@GetMapping(path=ConsumerURLMapping.PRODUCTDESCRIPTION_MAPPING_PATH,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<ProductDescriptions>> getAllProductDescriptions() {
		List<ProductDescriptions> productDescriptions = productDescriptionsRestService.getAllProductDescriptions();

		return new ResponseEntity<List<ProductDescriptions>>(productDescriptions, headers.getHeader(),
				HttpStatus.ACCEPTED);
	}

	@GetMapping(path=ConsumerURLMapping.PRODUCTDESCRIPTION_MAPPING_PATH1,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<ProductDescriptions>> getDistinctProductDescription(@PathVariable String sss,
			@PathVariable String sss1) {
		return new ResponseEntity<List<ProductDescriptions>>(
				productDescriptionsRestService.getAllProductDescriptionsNotInProductTable(), headers.getHeader(),
				HttpStatus.ACCEPTED);
	}

	@PutMapping(path=ConsumerURLMapping.PRODUCTDESCRIPTION_MAPPING_PATH,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> ProductDescriptionsServiceupdate(@RequestBody ProductDescriptions PD) {
		ResponseBean responseBean = productDescriptionsRestService.update(PD.getDescriptionid() + "",
				PD.getDescription());
		return new ResponseEntity<ResponseBean>(responseBean, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	/* ProductDescriptionsService code end */

	/* ProductQuantitiesService code start */
	@PostMapping(path=ConsumerURLMapping.PRODUCTQUANTITIES_GET_PATH,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> insert5(@RequestBody ProductQuantitiesPost PQT) {
		ResponseBean responseBean = productquantitiesRestService.insertProductQuantities(PQT.getQuantity() + "",
				PQT.getSizeid() + "");
		return new ResponseEntity<ResponseBean>(responseBean, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@GetMapping(path=ConsumerURLMapping.PRODUCTQUANTITIES_GET_PATH,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<ProductQuantities>> getAllProductquantities() {
		List<ProductQuantities> productquantities = productquantitiesRestService.getAllProductQuantities();

		return new ResponseEntity<List<ProductQuantities>>(productquantities, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@GetMapping(path=ConsumerURLMapping.PRODUCTQUANTITIES_GET_PATH_WITH_VARIABLE,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ProductQuantities> getProductQuantitiesById(@PathVariable String custid) {
		ProductQuantities productquantities = productquantitiesRestService.getProductQuantitiesById(custid);

		return new ResponseEntity<ProductQuantities>(productquantities, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@GetMapping(path=ConsumerURLMapping.PRODUCTQUANTITIES_MAPPING_PATH1,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<ProductQuantities>> getAllProductquantities(@PathVariable String sss,
			@PathVariable String sss1) {
		return new ResponseEntity<List<ProductQuantities>>(
				productquantitiesRestService.getAllProductQuantitiesNotInProducts(), headers.getHeader(),
				HttpStatus.ACCEPTED);
	}

	@PutMapping(path=ConsumerURLMapping.PRODUCTQUANTITIES_GET_PATH,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> ProductDescriptionsServiceupdate(@RequestBody ProductQuantities PQ) {
		ResponseBean responseBean = productquantitiesRestService.updateProductQuantities(PQ.getCustid() + "",
				PQ.getQuantity() + "", PQ.getSizeid() + "");

		return new ResponseEntity<ResponseBean>(responseBean, headers.getHeader(), HttpStatus.ACCEPTED);
	}
	/* ProductQuantitiesService code end */

	/* ProductsService code start */
	@PostMapping(path=ConsumerURLMapping.PRODUCTS_MAPPING_PATH,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> insert(@RequestBody ProductsPost PBP) {

		ResponseBean responseBean = productsRestService.insert(PBP.getDescriptionid() + "", PBP.getBatchid() + "",
				PBP.getGenderid() + "", PBP.getCatid() + "", PBP.getSubcatid() + "", PBP.getSeasonid() + "",
				PBP.getOccasionid() + "", PBP.getAgeid() + "", PBP.getMaterialid() + "", PBP.getColorid() + "",
				PBP.getCustid() + "", PBP.getStatus(), PBP.getTitle(), PBP.getSubtitle());
		return new ResponseEntity<ResponseBean>(responseBean, headers.getHeader(), HttpStatus.ACCEPTED);

	}

	@GetMapping(path=ConsumerURLMapping.PRODUCTS_MAPPING_PATH_PRODUCT_ID,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Products> ProductsServicegetById(@PathVariable String productid) {
		Products products = productsRestService.getbyid(productid);

		return new ResponseEntity<Products>(products, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@GetMapping(path=ConsumerURLMapping.PRODUCTS_MAPPING_PATH,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Products>> getall() {
		List<Products> products = productsRestService.getall();

		return new ResponseEntity<List<Products>>(products, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@PutMapping(path=ConsumerURLMapping.PRODUCTS_MAPPING_PATH,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> updatesize(@RequestBody Products PBP) {
		ResponseBean responseBean = productsRestService.update(PBP.getProductid() + "", PBP.getDescriptionid() + "",
				PBP.getBatchid() + "", PBP.getGenderid() + "", PBP.getCatid() + "", PBP.getSubcatid() + "",
				PBP.getSeasonid() + "", PBP.getOccasionid() + "", PBP.getAgeid() + "", PBP.getMaterialid() + "",
				PBP.getColorid() + "", PBP.getCustid() + "", PBP.getStatus(), PBP.getTitle(), PBP.getSubtitle());
		return new ResponseEntity<ResponseBean>(responseBean, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@PutMapping(path=ConsumerURLMapping.PRODUCTS_MAPPING_PATH_WITH_PATH_VARIABLE_1,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> enableBatchStatus(@PathVariable String productid,
			@PathVariable boolean status) {
		ResponseBean response = productsRestService.enableBatchStatus(productid, status);

		return new ResponseEntity<ResponseBean>(response, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	/* ProductsService code end */

	/* ProductTypeService code start */
	@PostMapping(path=ConsumerURLMapping.PRODUCTTYPE_GET_PATH,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> insertProductTypeService(@RequestBody ProductTypePost PTP) {
		ResponseBean responseBean = producttypeRestService.InsertProductType(PTP.getSubcatid() + "",
				PTP.getProducttypename());
		return new ResponseEntity<ResponseBean>(responseBean, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@GetMapping(path=ConsumerURLMapping.PRODUCTTYPE_GET_PATH,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<ProductType>> getAllProductTypeDetails() {
		List<ProductType> producttype = producttypeRestService.getAllProductTypeDetails();

		return new ResponseEntity<List<ProductType>>(producttype, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@GetMapping(path=ConsumerURLMapping.PRODUCTTYPE_PATH_WITH_ID,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ProductType> ProductTypeServicegetProductTypeById(@PathVariable String protypeid) {
		ProductType producttype = producttypeRestService.getProductTypeById(protypeid);

		return new ResponseEntity<ProductType>(producttype, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@PutMapping(path=ConsumerURLMapping.PRODUCTTYPE_GET_PATH,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> ProductTypeServiceupdate(@RequestBody ProductType PT) {
		ResponseBean responseBean = producttypeRestService.UpdateProductType(PT.getProtypeid() + "",
				PT.getSubcatid() + "", PT.getProducttypename());
		return new ResponseEntity<ResponseBean>(responseBean, headers.getHeader(), HttpStatus.ACCEPTED);
	}
	/* ProductTypeService code end */

	/* SalesSellerService start here */

	@GetMapping(path=ConsumerURLMapping.SALES_SELLER_GET_PATH,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<SellerUsers>> getAllSalesUsers1() {
		List<SellerUsers> salesUsers = salesSellerRestService.getAllSalesUsers();
		return new ResponseEntity<List<SellerUsers>>(salesUsers, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@GetMapping(path=ConsumerURLMapping.SALES_SELLER_GET_PATH_WITH_VARIABLE,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<SellerUsers> getSalesUsers(@PathVariable String username) {
		SellerUsers sallerUser = salesSellerRestService.getSalesUserByusername(username);
		return new ResponseEntity<SellerUsers>(sallerUser, headers.getHeader(), HttpStatus.ACCEPTED);
	}
	/* SalesSellerService end here */

	/* SeasonWearService code start */
	@PostMapping(path=ConsumerURLMapping.SEASON_WEAR,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> insert6(@RequestBody SeasonWearNew SWN) {
		ResponseBean responseBean = seasonwearRestService.InsertSeasonWearDetails(SWN.getSubcatId() + "",
				SWN.getSeason());
		return new ResponseEntity<ResponseBean>(responseBean, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@GetMapping(path=ConsumerURLMapping.SEASON_WEAR,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<SeasonWear>> getAllSeasonWearDetails() {
		List<SeasonWear> seasonwear = seasonwearRestService.getAllSeasonWearDetails();

		return new ResponseEntity<List<SeasonWear>>(seasonwear, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@GetMapping(path=ConsumerURLMapping.SEASON_WEAR1,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<SeasonCats>> getDetailedSeasonwear() {
		List<SeasonCats> seasoncats = new CopyOnWriteArrayList<>();
		List<Categories> categories = categoriesRestService.getAllCategories();
		List<SubCategories> subcategories = subcategoriesRestService.getAllSubCategoriesDetails();
		List<SeasonWear> seasonwear = seasonwearRestService.getAllSeasonWearDetails();
		if (!categories.isEmpty() && !subcategories.isEmpty() && !seasonwear.isEmpty()) {
			for (SeasonWear sw : seasonwear) {
				for (SubCategories sc : subcategories) {
					for (Categories c : categories) {
						if (sw.getSubcatId() == sc.getSubcatid() && sc.getCatid() == c.getCatid()) {
							seasoncats.add(new SeasonCats(sw.getSeasonId(), c.getCategory(), sc.getCategoryname(),
									sw.getSeason(), c.getGender()));
						}
					}
				}
			}
		} else {
			throw new DataNotFoundException(ConstantValues.SEASONWEAR_NOT_FOUND);
		}

		return new ResponseEntity<List<SeasonCats>>(seasoncats, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@GetMapping(path=ConsumerURLMapping.SEASON_WEAR_BY_ID,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<SeasonWear> getSeasonWearById(@PathVariable String seasonid) {
		SeasonWear seasonwear = seasonwearRestService.getSeasonWearById(seasonid);

		return new ResponseEntity<SeasonWear>(seasonwear, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@PutMapping(path=ConsumerURLMapping.SEASON_WEAR,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> SeasonWearServiceupdate(@RequestBody SeasonWear Sw) {
		ResponseBean responseBean = seasonwearRestService.UpdateSeasonWearDetails(Sw.getSeasonId() + "",
				Sw.getSubcatId() + "", Sw.getSeason());

		return new ResponseEntity<ResponseBean>(responseBean, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@GetMapping(path=ConsumerURLMapping.SEASON_BY_CAT,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<String>> getSeasonWearByCatId(@PathVariable Long catid) {

		// List<Categories> categories = categoriesRestService.getAllCategories();
		List<SubCategories> subcategories = subcategoriesRestService.getAllSubCategoriesDetails();
		List<SubCategories> subcats = new ArrayList<SubCategories>();
		for (SubCategories sc : subcategories) {
			if (sc.getCatid() == catid) {
				subcats.add(sc);
			}
		}
		/* removing duplicates code from here */
		List<SeasonWear> seasonwear = seasonwearRestService.getAllSeasonWearDetails();
		Set<String> sw = new HashSet<>();
		for (SubCategories sc : subcats) {
			for (SeasonWear s : seasonwear) {
				if (sc.getSubcatid() == s.getSubcatId()) {
					sw.add(s.getSeason().trim());
				}
			}
		}
		List<String> list = new ArrayList<String>(sw);
		/* removing duplicates code end here */

		return new ResponseEntity<List<String>>(list, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@GetMapping(path=ConsumerURLMapping.OCCASION_BY_CAT,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<String>> getOccasionByCatId(@PathVariable Long catid) {

		// List<Categories> categories = categoriesRestService.getAllCategories();
		List<SubCategories> subcategories = subcategoriesRestService.getAllSubCategoriesDetails();
		List<SubCategories> subcats = new ArrayList<SubCategories>();
		for (SubCategories sc : subcategories) {
			if (sc.getCatid() == catid) {
				subcats.add(sc);
			}
		}
		/* removing duplicates code from here */
		List<OccasionalWear> occasion = occasionalwearRestService.getAllOccasionalWearDetails();
		Set<String> sw = new HashSet<>();
		for (SubCategories sc : subcats) {
			for (OccasionalWear s : occasion) {
				if (sc.getSubcatid() == s.getSubcatid()) {
					sw.add(s.getOccaname().trim());
				}
			}
		}
		List<String> list = new ArrayList<String>(sw);
		/* removing duplicates code end here */

		return new ResponseEntity<List<String>>(list, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	/* SeasonWearService code end */

	/* SellerToWarehouseService code start */
	@PostMapping(path=ConsumerURLMapping.WAREHOUSETOSELLER_POST,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> addList(@RequestBody SellerToWarehouseBean stwb)

	{
		ResponseBean responseBean = sellerToWarehouseRestService.addList(stwb.getWarehouseid() + "",
				stwb.getSellerid() + "");
		return new ResponseEntity<ResponseBean>(responseBean, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@GetMapping(path=ConsumerURLMapping.WAREHOUSETOSELLER_GET,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<SellerToWarehouse>> SellerToWarehouseServicegetAll() {
		List<SellerToWarehouse> sellerToWarehouse = sellerToWarehouseRestService.getAllData();

		return new ResponseEntity<List<SellerToWarehouse>>(sellerToWarehouse, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	/* SellerToWarehouseService code end */

	/* SizesService code start */
	@PostMapping(path = ConsumerURLMapping.SIZES_MAPPING_PATH,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> insertSeasonWear(@RequestBody SizesNew SNB) {
		ResponseBean responseBean = sizesRestService.insertSizes(SNB.getAgeid() + "", SNB.getGender() + "",
				SNB.getSizeno() + "", SNB.getHeight() + "", SNB.getWeight() + "", SNB.getChest() + "",
				SNB.getWaist() + "", SNB.getHip(), SNB.getSubcatid() + "", SNB.getIsocode());
		return new ResponseEntity<ResponseBean>(responseBean, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@GetMapping(path=ConsumerURLMapping.SIZES_MAPPING_PATH_WITH_ID,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Sizes> SizesServicegetById(@PathVariable String sizeid) {

		Sizes sizes = sizesRestService.getsizebyid(sizeid);

		return new ResponseEntity<Sizes>(sizes, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@GetMapping(path=ConsumerURLMapping.SIZES_MAPPING_PATH,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Sizes>> getAllSizes() {
		List<Sizes> sizes = sizesRestService.getAllSizes();

		return new ResponseEntity<List<Sizes>>(sizes, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@PutMapping(path = ConsumerURLMapping.SIZES_MAPPING_PATH,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> updatesize(@RequestBody Sizes SB) {
		ResponseBean responseBean = sizesRestService.updatesize(SB.getSizeid() + "", SB.getAgeid() + "",
				SB.getGender() + "", SB.getSizeno() + "", SB.getHeight() + "", SB.getWeight() + "", SB.getChest() + "",
				SB.getWaist() + "", SB.getHip(), SB.getSubcatid() + "", SB.getIsocode() + "");

		return new ResponseEntity<ResponseBean>(responseBean, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	/* SizesService code end */

	/* SubCategoriesService code start */

	@PostMapping(path=ConsumerURLMapping.SUBCATEGORIES_GET_PATH,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> insertSubcategorytype(@RequestBody NewSubCategory newsubcat)

	{
		ResponseBean responseBean = subcategoriesRestService.insertSubcategorytype(newsubcat.getCatid() + "",
				newsubcat.getCategoryname() + "");
		return new ResponseEntity<ResponseBean>(responseBean, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@GetMapping(path=ConsumerURLMapping.SUBCATEGORIES_GET_PATH,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<SubCategories>> getAllSubCategoriesDetails() {
		List<SubCategories> subcategories = subcategoriesRestService.getAllSubCategoriesDetails();
		return new ResponseEntity<List<SubCategories>>(subcategories, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@GetMapping(path=ConsumerURLMapping.SUBCATEGORIES_GET_PATH1,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<SubCats>> getAllSubcatswithDetails() {
		List<SubCats> data = new CopyOnWriteArrayList<>();
		List<SubCategories> subcategories = subcategoriesRestService.getAllSubCategoriesDetails();
		List<Categories> categories = categoriesRestService.getAllCategories();

		if (!subcategories.isEmpty() || !categories.isEmpty()) {
			for (SubCategories sc : subcategories) {
				for (Categories c : categories) {
					if (sc.getCatid() == c.getCatid()) {
						data.add(new SubCats(sc.getSubcatid(), c.getCategory(), sc.getCategoryname(), c.getGender()));
					}
				}
			}
		} else {
			throw new DataNotFoundException(ConstantValues.SUB_CATEGORY_DETAILS_NOT_FOUND);
		}
		return new ResponseEntity<List<SubCats>>(data, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@GetMapping(path=ConsumerURLMapping.SUBCATEGORIES_MAPPING_PATH_WITH_VARIABLE,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<SubCategories> getSubCategoriesById(@PathVariable String subcatid) {
		SubCategories subcategories = subcategoriesRestService.getSubCategoriesById(subcatid);

		return new ResponseEntity<SubCategories>(subcategories, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@PutMapping(path=ConsumerURLMapping.SUBCATEGORIES_GET_PATH,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> SubCategoriesServiceupdate(@RequestBody SubCategories subcat) {
		ResponseBean responseBean = subcategoriesRestService.updateSubcategorytype(subcat.getSubcatid() + "",
				subcat.getCatid() + "", subcat.getCategoryname() + "");

		return new ResponseEntity<ResponseBean>(responseBean, headers.getHeader(), HttpStatus.ACCEPTED);
	}
	/* SubCategoriesService code end */

	// PRODUCT MANAGEMENT CODE START
	@GetMapping(path=ConsumerURLMapping.PRODUCT_IMG_LIST,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Long>> getAllImageProductIds() {
		return new ResponseEntity<List<Long>>(imageResetService.getAllProductIdsFromImgService(), headers.getHeader(),
				HttpStatus.ACCEPTED);
	}

	@GetMapping(path=ConsumerURLMapping.NO_IMG_PROD,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody // returning product ids
	public ResponseEntity<List<Long>> getNoImgProducts() {
		List<Long> data = new CopyOnWriteArrayList<>();
		List<Long> imgprdid = imageResetService.getAllProductIdsFromImgService();
		List<Products> products = productsRestService.getall();

		for (Long ids : imgprdid) {
			for (int i = 0; i < products.size(); i++) {
				if (ids == products.get(i).getProductid()) {
					products.remove(i);
					break;
				}
			}
		}
		for (Products pp : products) {
			data.add(pp.getProductid());
		}
		return new ResponseEntity<List<Long>>(data, headers.getHeader(), HttpStatus.ACCEPTED);
	}
	// PRODUCT MANAGEMENT CODE END

	@GetMapping(path=ConsumerURLMapping.ACTIVE_PRODUCT_DETAILS,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<ProductFullDetails>> getAllActiveProducts() {

		return new ResponseEntity<List<ProductFullDetails>>(productDetailsRestSErvice.getAllProductsDetails(),
				headers.getHeader(), HttpStatus.ACCEPTED);
	}
	//For Inventory
	@GetMapping(path = ConsumerURLMapping.ACTIVE_PRODUCT_DETAILS1_WITH_PATH,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ProductFullDetails> getActiveProductsByProductid(@PathVariable String productid) {
		return new ResponseEntity<ProductFullDetails>(productDetailsRestSErvice.getProductsDetails(productid),
				headers.getHeader(), HttpStatus.ACCEPTED);
	}


	@GetMapping(path = ConsumerURLMapping.ACTIVE_PRODUCT_DETAILS_WITH_PATH,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ProductFullDetailsWithImage> getActiveProducts(@PathVariable String productid) {
		return new ResponseEntity<ProductFullDetailsWithImage>(pcl.getByProductID1(Long.parseLong(productid)), headers.getHeader(), HttpStatus.ACCEPTED);
		}

	@GetMapping(path = ConsumerURLMapping.ACTIVE_PRODUCT_DETAILS_WITH_PATH1,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ProductFullDetails> getActiveProductsbystatus(@PathVariable String productid,
			@PathVariable String status) {

		return new ResponseEntity<ProductFullDetails>(
				productDetailsRestSErvice.getProductsDetailsbystatus(productid, status), headers.getHeader(),
				HttpStatus.ACCEPTED);
	}

	/*
	 * 
	 * public ProductsDetails getFullDetails(Products product) { ProductsDetails pd
	 * = new ProductsDetails(); if(product!=null) {
	 * 
	 * pd.setProduct(product);
	 * pd.setProductDescription(productDescriptionsRestService.getById(product.
	 * getDescriptionid()+""));
	 * pd.setBatch(batchRestService.getBatchById(product.getBatchid()+""));
	 * pd.setGender(gendersRestService.getGenderById(product.getGenderid()+""));
	 * pd.setCategory(categoriesRestService.getById(product.getCatid()+""));
	 * pd.setSubCategory(subcategoriesRestService.getSubCategoriesById(product.
	 * getSubcatid()+""));
	 * pd.setSeasonWear(seasonwearRestService.getSeasonWearById(product.getSeasonid(
	 * )+""));
	 * pd.setOccasionalWear(occasionalwearRestService.getOccasionalWearById(product.
	 * getOccasionid()+""));
	 * pd.setAge(productAgeRestService.getProductAgeById(product.getAgeid()+""));
	 * pd.setMaterialTypes(materialtypesRestService.getMaterialtypeById(product.
	 * getMaterialid()+""));
	 * pd.setColor(colorRestService.getColorById(product.getColorid()+""));
	 * pd.setProductQuantities(productquantitiesRestService.getProductQuantitiesById
	 * (product.getCustid()+""));
	 * pd.setSeller(sellerRestService.getSellerById(pd.getBatch().getSellerid())); }
	 * return pd; }
	 * 
	 */

	@GetMapping(path = ConsumerURLMapping.PRODUCTSCOUNT,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ProductCountBean> gettotalproducts() {
		// Long count=productsRestService.getProductCount();
		ProductCountBean p = new ProductCountBean(productsRestService.getProductCount());

		return new ResponseEntity<ProductCountBean>(p, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@GetMapping(path = ConsumerURLMapping.OUT_OF_STOCK,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<OutOfStockBean> getOutOfStock() {
		// Long count=productsRestService.getOutOfStock();
		OutOfStockBean os = new OutOfStockBean(productsRestService.getOutOfStock());

		return new ResponseEntity<OutOfStockBean>(os, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@GetMapping(path = ConsumerURLMapping.LOW_STOCK,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<LowStockBean> getLowStock() {
		// Long count=productsRestService.getLowStock();
		return new ResponseEntity<LowStockBean>(new LowStockBean(productsRestService.getLowStock()),
				headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@GetMapping(path=ConsumerURLMapping.PRODUCTS1,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Products>> getDistinctProductIds() {
		List<NoOfPieces> nop = noOfPiecesRestService.getAllNoOfPieces();
		List<Products> products = productsRestService.getall();

		if (!products.isEmpty()) {
			for (NoOfPieces n : nop) {
				for (int i = 0; i < products.size(); i++) {

					if (n.getProductid() == products.get(i).getProductid()) {
						products.remove(i);
					}
				}

			}
		}

		return new ResponseEntity<List<Products>>(products, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@GetMapping(path = ConsumerURLMapping.LOWQUANTITY,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody // Method for low quantity and out of stock
	public ResponseEntity<List<ProductsLowQuantity>> lowquantity() {
		List<ProductsLowQuantity> pl = new CopyOnWriteArrayList<>();
		List<Products> products = productsRestService.getall();
		List<ProductQuantities> productquantities = productquantitiesRestService.getAllProductQuantities();
		ProductsLowQuantity a;
		for (int i = 0; i < products.size(); i++) {
			for (int j = 0; j < productquantities.size(); j++) {
				if (products.get(i).getCustid() == productquantities.get(j).getCustid()) {
					if (productquantities.get(j).getQuantity() <= 15 && productquantities.get(j).getQuantity() >= 1) {
						a = new ProductsLowQuantity(products.get(i).getProductid(),
								"Only " + productquantities.get(j).getQuantity() + " are available");
						pl.add(a);
					} else if (productquantities.get(j).getQuantity() == 0) {
						a = new ProductsLowQuantity(products.get(i).getProductid(), "Out of Stock");
						pl.add(a);
					}
				}
			}
		}
		return new ResponseEntity<List<ProductsLowQuantity>>(pl, headers.getHeader(), HttpStatus.ACCEPTED);

	}

	@GetMapping(path=ConsumerURLMapping.PRODUCTSPRICES,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Products>> getProductIdsforPrices() {
		List<PricesTable> prices = pricesTableRestService.getallprices();
		List<Products> products = productsRestService.getall();

		if (!products.isEmpty()) {
			for (PricesTable p : prices) {
				for (int i = 0; i < products.size(); i++) {

					if (p.getProductid() == products.get(i).getProductid()) {
						products.remove(i);
					}
				}

			}
		}
		return new ResponseEntity<List<Products>>(products, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@GetMapping(path = ConsumerURLMapping.SUBCATDETAILS,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<SubCatDetails>> getAllSubCatDetails() {
		return new ResponseEntity<List<SubCatDetails>>(subcategoriesRestService.getAllSubCatDetails(),
				headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@GetMapping(path = ConsumerURLMapping.SIZESDETAILS,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<SizesDetails>> getAllSizesList() {
		return new ResponseEntity<List<SizesDetails>>(sizesRestService.getAllSizesList(), headers.getHeader(),
				HttpStatus.ACCEPTED);
	}

	@PostMapping(path = ConsumerURLMapping.APPROVAL,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> insertApproval(@RequestBody ApprovalBean app) {
		ResponseBean responseBean = approvalRestService.insertApproval(app.getProductid() + "", app.getInserteduser(),
				app.isStatus(), app.getStatustext(), app.getComment());
		return new ResponseEntity<ResponseBean>(responseBean, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@PutMapping(path = ConsumerURLMapping.APPROVAL,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> updateApproval(@RequestBody ApprovalBean app) {
		ResponseBean responseBean = approvalRestService.updateApproval(app.getSerialid() + "", app.getProductid() + "",
				app.getApprovaluser(), app.isStatus(), app.getStatustext(), app.getComment());
		return new ResponseEntity<ResponseBean>(responseBean, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@GetMapping(path = ConsumerURLMapping.APPROVAL_BY_ID,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ApprovalBean> approvalGetBySerialId(@PathVariable String serialid,
			@PathVariable String dummy) {
		ApprovalBean approve = approvalRestService.getbyserialid(serialid);
		return new ResponseEntity<ApprovalBean>(approve, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@GetMapping(path = ConsumerURLMapping.APPROVAL_BY_PRODUCT_ID,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ApprovalBean> approvalGetByProductId(@PathVariable String productid) {
		ApprovalBean approve = approvalRestService.getByProductID(productid);
		return new ResponseEntity<ApprovalBean>(approve, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@GetMapping(path = ConsumerURLMapping.APPROVAL,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<ApprovalBean>> getAllApproved() {
		List<ApprovalBean> approve = approvalRestService.getAllApprovals();
		return new ResponseEntity<List<ApprovalBean>>(approve, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@GetMapping(path=ConsumerURLMapping.PRODUCTSPATTERNS,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Products>> getProductIdsforPatterns() {
		List<Patterns> patterns = patternsRestService.getAllPatternsDetails();
		List<Products> products = productsRestService.getall();

		if (!products.isEmpty()) {
			for (Patterns p : patterns) {
				for (int i = 0; i < products.size(); i++) {

					if (p.getProductid() == products.get(i).getProductid()) {
						products.remove(i);
					}
				}

			}
		}
		return new ResponseEntity<List<Products>>(products, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@GetMapping(path = ConsumerURLMapping.NO_PATTERNS,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<UnFilledProductsBean> getPatterns() {
		return unFilledProductsRestServices.PAT001();
	}

	@GetMapping(path = ConsumerURLMapping.NO_PRICE,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<UnFilledProductsBean> getPrice() {
		return unFilledProductsRestServices.PRI001();
	}

	@GetMapping(path = ConsumerURLMapping.NO_PIECES,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<UnFilledProductsBean> getPieces() {
		return unFilledProductsRestServices.PIE001();
	}

	@GetMapping(path = ConsumerURLMapping.PRODUCT_IN_PAGE,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<ProductFullDetails>> getAllProducts(@PathVariable int page, @PathVariable int count) {
		return new ResponseEntity<List<ProductFullDetails>>(
				productPaginationRestService.getAllProductByPage(page, count), new HttpHeaders(), HttpStatus.ACCEPTED);
	}

	@GetMapping(path = ConsumerURLMapping.PRODUCT_PAGE_COUNT,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Integer> getAllProductsPageCount(@PathVariable int page, @PathVariable int count) {
		return new ResponseEntity<Integer>(productPaginationRestService.getAllProductPageCount(page, count),
				new HttpHeaders(), HttpStatus.ACCEPTED);
	}

	/* DisplayType code starts here */
	@GetMapping(path = ConsumerURLMapping.DISPLAYTYPE,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<DisplayType>> getAllDisplays() {
		return new ResponseEntity<List<DisplayType>>(displayTypeRestService.getAllDetails(), headers.getHeader(),
				HttpStatus.ACCEPTED);
	}

	@GetMapping(path = ConsumerURLMapping.DISPLAYTYPE_BY_ID,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<DisplayType> getAllDisplaysById(@PathVariable String display) {
		return new ResponseEntity<DisplayType>(displayTypeRestService.getAllDetailsById(display), headers.getHeader(),
				HttpStatus.ACCEPTED);
	}

	// badges
	@GetMapping(path=ConsumerURLMapping.BADGE,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Badges>> getAllBadges() {
		List<Badges> badges = badgesrestservice.getAllBadges();

		return new ResponseEntity<List<Badges>>(badges, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@GetMapping(path=ConsumerURLMapping.GET_BY_BADGE,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Badges> getByBadges(@PathVariable String badge) {
		Badges badges = badgesrestservice.getByBadge(badge);
		return new ResponseEntity<Badges>(badges, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@PostMapping(path=ConsumerURLMapping.BADGE,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> insertBadge(@RequestBody Badges badge)

	{
		ResponseBean responseBean = badgesrestservice.insertBadge(badge.getBadge() + "", badge.getDescription() + "");
		return new ResponseEntity<ResponseBean>(responseBean, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	// collection
	@GetMapping(path=ConsumerURLMapping.COLLECTION,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<CollectionsaleBean>> getAllCOLLECTION() {
		List<CollectionsaleBean> collection = collectionsaleRestService.getAllCollectionsale();

		return new ResponseEntity<List<CollectionsaleBean>>(collection, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@GetMapping(path=ConsumerURLMapping.GET_BY_COLLECTION,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<CollectionsaleBean> getByID(@PathVariable String id) {
		CollectionsaleBean collection = collectionsaleRestService.getAllCollectionById(id);
		return new ResponseEntity<CollectionsaleBean>(collection, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@GetMapping(path=ConsumerURLMapping.GET_BY_COLLECTION1,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<CollectionSalewithProductIdsBean>> getByGenderID(@PathVariable String genderid) {
		List<CollectionSalewithProductIdsBean> collection = collectionsaleRestService
				.getAllCollectionByGenerId(genderid);
		return new ResponseEntity<List<CollectionSalewithProductIdsBean>>(collection, headers.getHeader(),
				HttpStatus.ACCEPTED);
	}

	@PostMapping(path=ConsumerURLMapping.COLLECTION,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> insertCollection(@RequestBody CollectionsaleBean collection)

	{
		ResponseBean responseBean = collectionsaleRestService.insertCollectionsale(collection.getTitle(),
				collection.getGenderid(), collection.getBadge(), collection.getDisplay(), collection.getStartdate(),
				collection.getEnddate());
		return new ResponseEntity<ResponseBean>(responseBean, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@GetMapping(path=ConsumerURLMapping.PRODUCTS_MAPPING_PATH_CAT,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<ProductFullDetails>> ProductsServicegetByCategories(@PathVariable String catid,
			@PathVariable String season, @PathVariable String occasion) {
		List<ProductFullDetails> products = productsRestService.getbycategories(catid, season, occasion);

		return new ResponseEntity<List<ProductFullDetails>>(products, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	// Selectedproducts//

	@GetMapping(path=ConsumerURLMapping.SELECTEDPRODUCTS,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Selectedproducts>> getAllSelectedProducts() {
		List<Selectedproducts> selprod = selectedproductsRestService.getAllSelectedProducts();

		return new ResponseEntity<List<Selectedproducts>>(selprod, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@GetMapping(path=ConsumerURLMapping.SELECTEDPRODUCTS_BY_ID,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Selectedproducts> getSelectedproductsById(@PathVariable String spid) {
		Selectedproducts selprod = selectedproductsRestService.getSelectedproductsById(spid);
		return new ResponseEntity<Selectedproducts>(selprod, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@PostMapping(path=ConsumerURLMapping.SELECTEDPRODUCTS,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> insertSelectedproducts(@RequestBody SelectedproductsPost selprod)

	{
		ResponseBean responseBean = selectedproductsRestService.insertSelectedproducts(selprod.getGenderid(),
				selprod.getProductid(), selprod.getStartdate(), selprod.getEnddate());
		return new ResponseEntity<ResponseBean>(responseBean, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@GetMapping(path=ConsumerURLMapping.PRODUCTS_MAPPING_PATH_SELECTEDPRODUCTS,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<ProductFullDetails>> ProductsServicegetBySelectedProducts(
			@PathVariable String genderid) {
		List<ProductFullDetails> products = productsRestService.getByGender(genderid);

		return new ResponseEntity<List<ProductFullDetails>>(products, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	// WishList
	@GetMapping(path=ConsumerURLMapping.WISHLIST,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<WishlistBean>> getAllWishlists() {
		List<WishlistBean> wishlist = wishlistRestService.getAllWishlists();
		return new ResponseEntity<List<WishlistBean>>(wishlist, headers.getHeader(), HttpStatus.ACCEPTED);
	}

//	@GetMapping(ConsumerURLMapping.WISHLIST_BY_CUSTOMER_ID)
//	@ResponseBody
//	public ResponseEntity<WishlistBean> getByCustomerId(@PathVariable String customerid)				
//	{
//		WishlistBean wishlist = wishlistRestService.getByCustomerId(customerid);
//		return new ResponseEntity<WishlistBean>(wishlist, headers.getHeader(), HttpStatus.ACCEPTED);
//	}

//	@DeleteMapping(ConsumerURLMapping.WISHLIST_BY_CUSTOMER_ID)
//	@ResponseBody
//	public ResponseEntity<ResponseBean> deleteByCustomerId(@PathVariable String customerid)				
//	{
//		ResponseBean responseBean = wishlistRestService.deleteByCustomerId(customerid);
//		return new ResponseEntity<ResponseBean>(responseBean, headers.getHeader(), HttpStatus.ACCEPTED);
//	}

//	@DeleteMapping(ConsumerURLMapping.WISHLIST_BY_PRODUCT_ID)
//	@ResponseBody
//	public ResponseEntity<ResponseBean> deleteByProductId(@PathVariable String productid,@PathVariable String customerid)				
//	{
//		ResponseBean responseBean = wishlistRestService.deleteByProductId(productid,customerid);
//		return new ResponseEntity<ResponseBean>(responseBean, headers.getHeader(), HttpStatus.ACCEPTED);
//	}
//	

	// wishlist code end

	// blogreport code start

	@GetMapping(path=ConsumerURLMapping.BLOGREPORT,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Blogreport>> getAllBlogReportDetail() {
		List<Blogreport> blogreport = blogreportRestService.getAllBlogreport();

		return new ResponseEntity<List<Blogreport>>(blogreport, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@GetMapping(path=ConsumerURLMapping.BLOGREPORT_BY_ID,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Blogreport> getByqId(@PathVariable String bid) {
		Blogreport blogreport = blogreportRestService.getById(bid);

		return new ResponseEntity<Blogreport>(blogreport, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@PostMapping(path=ConsumerURLMapping.NEWAUTHOR_POST,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> insertBlogreport(@RequestParam("items[]") MultipartFile[] authorimage,
			@PathVariable String authorname, @PathVariable String description) throws MaxUploadSizeExceededException {
		System.out.println(authorimage.length);
		if (authorimage.length == 0) {
			throw new NullRequestReceivedException(ConstantValues.RECEIVED_NULL_VALUES);
		} else {
			ResponseBean responseBean = blogreportRestService.addBlogreport(authorname, authorimage[0], description);
			return new ResponseEntity<ResponseBean>(responseBean, headers.getHeader(), HttpStatus.ACCEPTED);
		}
	}

	// blogreport code end

	/* grannyblog code start */

	@GetMapping(path=ConsumerURLMapping.GRANNYBLOG,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> getAllBlogReportDetails() {
	//	List<GrannyBlog> grannyblog = grannyblogRestService.getAllGranyBlogDetails();
		return new ResponseEntity<String>(grannyblogRestService.getAllGranyBlogDetails(), headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@GetMapping(path=ConsumerURLMapping.GRANNYBLOG_BY_ID,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> getByBId(@PathVariable String blogid) {
	//	GrannyBlog grannyblog = grannyblogRestService.getGrannyBlogById(blogid);
		return new ResponseEntity<String>(grannyblogRestService.getGrannyBlogById(blogid), headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@PostMapping(path=ConsumerURLMapping.GRANNYBLOG_POST,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> insertGrannyBlog(@RequestParam("items[]") MultipartFile[] gbimage,
			@RequestParam(name = "bid") long bid, @RequestParam(name = "title") String title,
			@RequestParam(name = "subtitle") String subtitle, @RequestParam(name = "information") String information)
			throws MaxUploadSizeExceededException {
		System.out.println(gbimage.length);
		if (gbimage.length < 3) {
			throw new NullRequestReceivedException(ConstantValues.RECEIVED_NULL_VALUES);
		} else {
			//ResponseBean responseBean = grannyblogRestService.insertGrannyBlogInformation(bid, title, subtitle,
			//		information, gbimage[0], gbimage[1], gbimage[2]);
			return new ResponseEntity<String>(grannyblogRestService.insertGrannyBlogInformation(bid, title, subtitle,
					information, gbimage[0], gbimage[1], gbimage[2]), headers.getHeader(), HttpStatus.ACCEPTED);
		}
	}

	@PutMapping(path=ConsumerURLMapping.GRANNYBLOG_STATUS,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> updateGrannyBlog(@PathVariable String blogid, @PathVariable String status) {
		//ResponseBean responseBean = grannyblogRestService.updateGBlog(blogid, status);
		return new ResponseEntity<String>(grannyblogRestService.updateGBlog(blogid, status), headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@GetMapping(path = "pp7/{page}/{count}",produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> getAllGrannyUsersList(@PathVariable int page, @PathVariable int count) {
		return new ResponseEntity<String>(grannyblogRestService.getAllGrannyUsersListByPage(page, count),
				new HttpHeaders(), HttpStatus.ACCEPTED);
	}

	@GetMapping(path = "pp8/{page}/{count}",produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Integer> getAllGrannyUsersListPageCount(@PathVariable int page, @PathVariable int count) {
		return new ResponseEntity<Integer>(grannyblogRestService.getAllGrannyUsersPageCount(page, count),
				new HttpHeaders(), HttpStatus.ACCEPTED);
	}

	/* granny blog code end */

	// collection sale productids
	@GetMapping(path=ConsumerURLMapping.COLLECTIONSALEPROD,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<CollectionsaleprodBean>> getAllCOLLECTIONSALEProductIds() {
		List<CollectionsaleprodBean> collection = collectionsaleprodrest.getAllCollectionsale(0);

		return new ResponseEntity<List<CollectionsaleprodBean>>(collection, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@PostMapping(path=ConsumerURLMapping.COLLECTIONSALEPROD,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> insertCollectionProductids(@RequestBody CollectionsaleprodPostBean collection)

	{

		ResponseBean responseBean = collectionsaleprodrest.insertCollectionsale(collection.getId(),
				collection.getProductid());
		return new ResponseEntity<ResponseBean>(responseBean, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@GetMapping(path=ConsumerURLMapping.COLLECTIONSALEPRODBYSID,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<CollectionsaleprodBean> getAllCOLLECTIONSALESIds(@PathVariable String csid) {
		CollectionsaleprodBean collection = collectionsaleprodrest.getAllCollectionSIds(csid);

		return new ResponseEntity<CollectionsaleprodBean>(collection, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@GetMapping(path=ConsumerURLMapping.COLLECTIONSALEPRODBYPRODID,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<CollectionsaleprodBean>> getAllCOLLECTIONSALeProductIds(@PathVariable String id) {
		List<CollectionsaleprodBean> collection = collectionsaleprodrest.getAllCollectionByProductId(id);

		return new ResponseEntity<List<CollectionsaleprodBean>>(collection, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	// CollectionSale Images
	@GetMapping(path=ConsumerURLMapping.COLLECTIONSALEIMAGES,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<CollectionsaleImagesBean>> getAllCOLLECTIONSALEImages() {
		List<CollectionsaleImagesBean> collection = collectionsaleimagerest.getAllCollectionsaleImages(0);

		return new ResponseEntity<List<CollectionsaleImagesBean>>(collection, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@PostMapping(path=ConsumerURLMapping.COLLECTIONSALEIMAGESPOST,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> insertCOLLECTIONSALEImages(@RequestParam("items[]") MultipartFile[] img,
			@PathVariable long id) throws MaxUploadSizeExceededException {
		if (img.length < 5) {
			throw new NullRequestReceivedException(ConstantValues.RECEIVED_NULL_VALUES);
		} else {
			ResponseBean responseBean = collectionsaleimagerest.insertCOllectoionImage(id, img[0], img[1], img[2],
					img[3], img[4]);
			return new ResponseEntity<ResponseBean>(responseBean, headers.getHeader(), HttpStatus.ACCEPTED);
		}
	}

	@GetMapping(path=ConsumerURLMapping.COLLECTIONSALEIMAGES_BY_IMAGEID,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<CollectionsaleImagesBean> getAllCOLLECTIONSALESIMAGEBYIMId(@PathVariable String imageid) {
		CollectionsaleImagesBean collection = collectionsaleimagerest.getAllCollectionIMAGEBYIMGID(imageid);

		return new ResponseEntity<CollectionsaleImagesBean>(collection, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@GetMapping(path=ConsumerURLMapping.COLLECTIONIDS,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<CollectionsaleBean>> getDistinctCollectionIds() {
		List<CollectionsaleprodBean> cprod = collectionsaleprodrest.getAllCollectionsale(1);
		List<CollectionsaleBean> cs = collectionsaleRestService.getAllCollectionsale();
		if (!cs.isEmpty()) {
			if (cprod.size() > 0) {
				for (CollectionsaleprodBean n : cprod) {
					for (int i = 0; i < cs.size(); i++) {
						if (n.getId().equals(cs.get(i).getId())) {
							cs.remove(i);
						}
					}
				}
			} else {
				return new ResponseEntity<List<CollectionsaleBean>>(cs, headers.getHeader(), HttpStatus.ACCEPTED);
			}
		}
		return new ResponseEntity<List<CollectionsaleBean>>(cs, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@GetMapping(path=ConsumerURLMapping.COLLECTIONIMGS,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<CollectionsaleBean>> getDistinctCollectionImages() {
		List<CollectionsaleImagesBean> cprod = collectionsaleimagerest.getAllCollectionsaleImages(1);
		List<CollectionsaleBean> cs = collectionsaleRestService.getAllCollectionsale();
		if (!cs.isEmpty()) {
			if (cprod.size() > 0) {
				for (CollectionsaleImagesBean n : cprod) {
					for (int i = 0; i < cs.size(); i++) {
						if (n.getId().equals(cs.get(i).getId())) {
							cs.remove(i);
						}
					}
				}
			} else {
				return new ResponseEntity<List<CollectionsaleBean>>(cs, headers.getHeader(), HttpStatus.ACCEPTED);
			}
		}
		return new ResponseEntity<List<CollectionsaleBean>>(cs, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@GetMapping(path=ConsumerURLMapping.PRODUCTS_MAPPING_PATH_SELECTEDPRODUCTS_FOR_MULTISELECT,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<ProductFullDetails>> ProductsServicegetByProductsforSelectedProducts(
			@PathVariable String genderid) {
		List<ProductFullDetails> products = productsRestService.getByPRODUCTSGender(genderid);

		return new ResponseEntity<List<ProductFullDetails>>(products, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	// Get method for product variations
	@GetMapping(path=ConsumerURLMapping.PRODUCTVARIATION,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<ProductvariationBean>> getAllProductVariations() {
		List<ProductvariationBean> products = productvariationRestService.getAllProductVariations();

		return new ResponseEntity<List<ProductvariationBean>>(products, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	// Get method for SimilarProducts
	@GetMapping(path=ConsumerURLMapping.SimilarProducts,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<SimilarproductsBean>> getAllSimilarProducts() {
		List<SimilarproductsBean> products = productvariationRestService.getAllSimilarProducts();

		return new ResponseEntity<List<SimilarproductsBean>>(products, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	// Post method Product Variations
	@PostMapping(path=ConsumerURLMapping.PRODUCTVARIATION,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> insertProductsVariations(@RequestBody ProductvariationBean pv)

	{

		ResponseBean responseBean = productvariationRestService.insertProductVariation(pv.getPvtype());
		return new ResponseEntity<ResponseBean>(responseBean, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	// Post method Similar Products
	@PostMapping(path=ConsumerURLMapping.SimilarProducts,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> insertCollectionProductids(@RequestBody SimilarproductsPostBean smb)

	{

		ResponseBean responseBean = productvariationRestService.insertSimilarProducts(smb.getPvid() + "",
				smb.getProductid());
		return new ResponseEntity<ResponseBean>(responseBean, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	// Products for multiselect in similar products
	@GetMapping(path=ConsumerURLMapping.PRODUCTS_MAPPING_PATH_SIMILARPRODUCTS_FOR_MULTISELECT,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<ProductFullDetails>> ProductsServicegetByProductsforSimilarProducts() {
		List<ProductFullDetails> products = productsRestService.getallPRODUCTSFORSIMILARPRODUCTS();

		return new ResponseEntity<List<ProductFullDetails>>(products, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	// Product Keywords
	@GetMapping(path=ConsumerURLMapping.PRODUCT_KEYWORDS_ID,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ProductKeywords> getProductKeywordsByPId(@PathVariable String productid) {
		ProductKeywords productkeyword = productKeywordsRestService.getProductKeywordsByPId(productid);

		return new ResponseEntity<ProductKeywords>(productkeyword, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@PostMapping(path=ConsumerURLMapping.PRODUCT_KEYWORDS,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> InsertProductKeywordsDetails(@RequestBody ProductKeywords PK)

	{
		ResponseBean responseBean = productKeywordsRestService.InsertProductKeywordsDetails(PK.getProductid() + "",
				PK.getKeywords() + "");
		return new ResponseEntity<ResponseBean>(responseBean, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@PutMapping(path=ConsumerURLMapping.PRODUCT_KEYWORDS_ID,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> UpdateProductKeywordsDetails(@PathVariable String productid,
			@RequestBody ProductKeywords PK) {
		ResponseBean responseBean = productKeywordsRestService.UpdateProductKeywordsDetails(productid,
				PK.getKeywords() + "");
		return new ResponseEntity<ResponseBean>(responseBean, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	// ordecodes

	@GetMapping(path=ConsumerURLMapping.ORDERCODES,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<OrdercodesBean>> getOrderCodes() {
		List<OrdercodesBean> ordercodes = ordercodesRestService.getAllOrderCodes();

		return new ResponseEntity<List<OrdercodesBean>>(ordercodes, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@PostMapping(path=ConsumerURLMapping.ORDERCODES,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> InsertOrderCodes(@RequestBody OrdercodesBean oc)

	{
		ResponseBean responseBean = ordercodesRestService.InsertOrdercodes(oc.getOrdercodeid() + "", oc.getRemark());
		return new ResponseEntity<ResponseBean>(responseBean, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	// order

	@GetMapping(path=ConsumerURLMapping.ORDERS,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<OrderDisplayBean>> getAllOrder1(@PathVariable String codeid,
			@PathVariable String dummy) {
		long userid, oredrcode, addressid = 0;
		List<OrdersBean> orders = ordermanagementRestService.getAllOrders(Long.parseLong(codeid));
		List<CustomerAddressBean> cust = CUSTADD_SERVICE.getAllCustomerAddress();
		List<OrdercodesBean> ordercodes = ordercodesRestService.getAllOrderCodes();
		List<LilCustomerBean> lilcustomer = lilCustomerRestervice.getAllUser();
		List<OrderDisplayBean> orderdisplay = new CopyOnWriteArrayList<>();
		if (!orders.isEmpty()) {
			for (OrdersBean ob : orders) {
				if (ob.getOrdercodeid() == Long.parseLong(codeid)) {
					OrderDisplayBean odb = new OrderDisplayBean();
					odb.setOrderid(ob.getOrderid() + "");
					odb.setOrderdate(ob.getOrderdate() + "");
					userid = ob.getCustomerid();
					odb.setOrderitems(ordermanagementRestService.getFULLDETAILS(ob.getOrderid() + "").getOrderitems());
					// System.out.println(userid);
					for (LilCustomerBean lb : lilcustomer) {
						if (lb.getUserid() == userid) {
							// System.out.println("dhc"+lb.getUserid()+userid);
							odb.setCustomername(lb.getFirstname() + " " + lb.getLastname());
						}
					}
					odb.setTotalprice(ob.getTotalprice() + "");
					oredrcode = ob.getOrdercodeid();
					for (OrdercodesBean cb : ordercodes)

					{
						if (cb.getOrdercodeid() == oredrcode) {

							odb.setOrder_status(cb.getRemark());
							odb.setOrdercodeid(cb.getOrdercodeid() + "");
						}
					}
					// System.out.println(ob.getGateway_orderid());
					odb.setGateway_orderid(ob.getGateway_orderid());
					addressid = ob.getAddressid();
					for (CustomerAddressBean sw : cust) {
						if (sw.getAddressid() == addressid) {

							odb.setAddrestype(sw.getAddressType());
							odb.setAddressid(sw.getAddressid());
						}
					}
					odb.setCustomerid(userid);
					orderdisplay.add(odb);
				} // if end here
			}
		}
		return new ResponseEntity<List<OrderDisplayBean>>(orderdisplay, headers.getHeader(), HttpStatus.ACCEPTED);
	}
	// Get order information based on id

	@GetMapping(path=ConsumerURLMapping.ORDERS_BY_ID,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<OrderFullDetailsSecurityBean> getAllOrder(@PathVariable String orderid) {
		OrderFullDetailsSecurityBean orders = ordermanagementRestService.getFULLDETAILS(orderid);

		return new ResponseEntity<OrderFullDetailsSecurityBean>(orders, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@PutMapping(path=ConsumerURLMapping.ORDERCODES,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> Ordercodesupdate(@RequestBody OrdercodesBean ocb) {
		ResponseBean responseBean = ordercodesRestService.update(ocb.getOrdercodeid() + "", ocb.getRemark());

		return new ResponseEntity<ResponseBean>(responseBean, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	// ORDER STATUS TRACKING CODE START HERE
	@PostMapping(path = "/ORT111",produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> insertOrderItemStatus(@RequestBody Orderitemsstatus oi) {
		return new ResponseEntity<ResponseBean>(ordermanagementRestService.InsertOrdersItemsStatus(oi.getOrderid() + "",
				oi.getOrderitemsid() + "", oi.getOrdercodeid() + "", oi.getInformation()), headers.getHeader(),
				HttpStatus.ACCEPTED);
	}

	// ORDER STATUS TRACKING CODE END HERE

	// Package Shipping Code
	@PostMapping(path=ConsumerURLMapping.PACKAGESHIPPING_GET,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> insertPackageShipping(@RequestBody PackageshippingBean pb)

	{
		ResponseBean responseBean = packageshippingRest.insertMaterialtype(pb.getOrderid() + "",
				pb.getOrderitemsid() + "", pb.getShippingorderid() + "", pb.getShippingservice(), pb.getAwb() + "",
				pb.getTrackingurl() + "", pb.getInformation() + "");
		return new ResponseEntity<ResponseBean>(responseBean, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@PutMapping(path=ConsumerURLMapping.PACKAGESHIPPING_GET,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> updatePackageShipping(@RequestBody PackageshippingBean pb) {
		ResponseBean responseBean = packageshippingRest.updateMaterialtype(pb.getSid() + "",
				pb.getShippingorderid() + "", pb.getShippingservice(), pb.getAwb(), pb.getTrackingurl(),
				pb.getInformation());
		return new ResponseEntity<ResponseBean>(responseBean, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@GetMapping(path=ConsumerURLMapping.PACKAGESHIPPING_GET,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<PackageshippingBean>> getAllPackageShipping() {
		List<PackageshippingBean> packageship = packageshippingRest.getAllPackageShipping();
		return new ResponseEntity<List<PackageshippingBean>>(packageship, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@GetMapping(path=ConsumerURLMapping.PACKAGESHIPPING_GET_BY_SID,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<PackageshippingBean> getAllPackageShippingById(@PathVariable String sid) {
		PackageshippingBean packageship = packageshippingRest.getAllPackageShippingbySid(sid);
		return new ResponseEntity<PackageshippingBean>(packageship, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@GetMapping(path=ConsumerURLMapping.PACKAGESHIPPING_GET_BY_ORDERITEMSID,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<PackageshippingBean> getAllPackageShippingByORDERITEMSId(@PathVariable String orderitemsid) {
		PackageshippingBean packageship = packageshippingRest.getAllPackageShippingbyOrderitemsid(orderitemsid);
		return new ResponseEntity<PackageshippingBean>(packageship, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@GetMapping(path=ConsumerURLMapping.PACKAGESHIPPING_GET_BY_ORDERID,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<PackageshippingBean>> getAllPackageShippingByORDERId(@PathVariable String orderid) {
		List<PackageshippingBean> packageship = packageshippingRest.getAllPackageShippingbyOrderid(orderid);
		return new ResponseEntity<List<PackageshippingBean>>(packageship, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@GetMapping(path=ConsumerURLMapping.ORDERITEMSSTATUS_BY_ITEMSID,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Orderitemsstatus>> getOrderItemsStatusByItemsid(@PathVariable String orderitemsid) {
		List<Orderitemsstatus> status = ordermanagementRestService
				.getAllOrderItemsStatusListByOrderitemsList(orderitemsid);
		return new ResponseEntity<List<Orderitemsstatus>>(status, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@PutMapping(path=ConsumerURLMapping.ORDER_STATUS_TABLE_PUT,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> updateORDERSTATUS(@RequestBody OrdersBean ob) {
		ResponseBean responseBean = ordermanagementRestService.updateOrderCODE(ob.getOrderid() + "",
				ob.getOrdercodeid() + "");
		return new ResponseEntity<ResponseBean>(responseBean, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@GetMapping(path=ConsumerURLMapping.RAZORPAYTRANSACTION,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Razorpay>> getAllRazorTransaction() {
		List<Razorpay> orders = ordermanagementRestService.getAllRazorpaylist();

		return new ResponseEntity<List<Razorpay>>(orders, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	// Razor Pay info by payment gateway id

	@GetMapping(path=ConsumerURLMapping.RAZORPAYTRANSACTIONBYRazorPAY_ORDERID,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Razorpay> getRazorPayInfoByPaymentgatewayid(@PathVariable String razorpay_order_id) {
		Razorpay status = ordermanagementRestService.getRazorpayById(razorpay_order_id);
		return new ResponseEntity<Razorpay>(status, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@GetMapping(path=ConsumerURLMapping.EMAILSUBSCRIBE,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<EmailSubscribeBean>> getAllSubscribersList() {
		List<EmailSubscribeBean> subscribe = emailSubscribeRestService.getAllSubscribedEmails();

		return new ResponseEntity<List<EmailSubscribeBean>>(subscribe, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@GetMapping(path = ConsumerURLMapping.CONTEST_IN_PAGE,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> getAllContestDetails(@PathVariable int page, @PathVariable int count) {
		return new ResponseEntity<String>(
				contestRest.getAllDetailsByPage(page, count), new HttpHeaders(), HttpStatus.ACCEPTED);
	}

	@GetMapping(path = ConsumerURLMapping.CONTEST_IN_COUNT,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> getCOuntOFCONTEST(@PathVariable int page, @PathVariable int count) {
		return new ResponseEntity<String>(
				contestRest.getPageCount(page, count), new HttpHeaders(), HttpStatus.ACCEPTED);
	}
	
	@GetMapping(path = ConsumerURLMapping.CONTEST2,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> getContestantsByEmailid(@PathVariable String emailid) {
		return new ResponseEntity<String>(
				contestRest.getbyEmailid(emailid), new HttpHeaders(), HttpStatus.ACCEPTED);
	}
	
	@GetMapping(path = ConsumerURLMapping.CONTEST3,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> getContestantsByInstaid(@PathVariable String instaid) {
		return new ResponseEntity<String>(
				contestRest.getbyInstaid(instaid), new HttpHeaders(), HttpStatus.ACCEPTED);
	}
	
	@GetMapping(path = ConsumerURLMapping.CONTEST4,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> getContestantsByPhone(@PathVariable String phone) {
		return new ResponseEntity<String>(
				contestRest.getbyphone(phone), new HttpHeaders(), HttpStatus.ACCEPTED);
	}
	
	@GetMapping(path = ConsumerURLMapping.CONTEST_IN_PAGE_CONTESTWISE,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> getAllContestDetailsMonthWise(@PathVariable String contestmonthyear,@PathVariable int page, @PathVariable int count) {
		return new ResponseEntity<String>(
				contestRest.getAllDetailsforContest(contestmonthyear, page, count), new HttpHeaders(), HttpStatus.ACCEPTED);
	}

	@GetMapping(path = ConsumerURLMapping.CONTEST_IN_CONTESTWISE,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> getCOuntOFCONTESTWISe(@PathVariable String contestmonthyear,@PathVariable int page, @PathVariable int count) {
		return new ResponseEntity<String>(
				contestRest.getPageCountcontestwise(contestmonthyear, page, count), new HttpHeaders(), HttpStatus.ACCEPTED);
	}
	
	@GetMapping(path = ConsumerURLMapping.MONTHS,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> getMonthsName() {
		return new ResponseEntity<String>(
				contestRest.getContestNames(), new HttpHeaders(), HttpStatus.ACCEPTED);
	}
	

	@GetMapping(path = ConsumerURLMapping.IMGLOC,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public void getContestantsIMGLOC(HttpServletResponse response,@PathVariable String emailid,@PathVariable String filename) {
		
		try {
			
			
			InputStream in = new FileInputStream(contestRest.getImgLoc(emailid, filename));
			response.setContentType(MediaType.IMAGE_JPEG_VALUE);	
			IOUtils.copy(in, response.getOutputStream());
			
			
			
			}catch(Exception e) {
				ResponseBean rb = new ResponseBean(e.getMessage(),4000, System.currentTimeMillis());
				
				try {
					String reply = new Gson().toJson(rb);
					response.setContentType("application/json");
					response.setCharacterEncoding("UTF-8");
					response.getWriter().print(reply);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
			}
		
	}
	
	
	@PostMapping(path="/coupon",produces = MediaType.APPLICATION_JSON_VALUE )
	@ResponseBody
	public ResponseEntity<ResponseBean> insert(@RequestBody Coupons coupons){
		return new ResponseEntity<ResponseBean>(couponRest.insertNewCoupon(coupons), HttpStatus.ACCEPTED);
	}
	
	@GetMapping(path="/coupon", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Coupons>> getAllCoupons(){
		return new ResponseEntity<List<Coupons>>(couponRest.getAllCoupons(), HttpStatus.ACCEPTED);
	}
	
	
	@GetMapping(path="/coupon1", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Coupons> generateCoupon(){
		Coupons coupon = new Coupons(0, "", "", "", 0, 0, "");
		List<Coupons> dbCoupons = couponRest.getAllCoupons();
		coupon.setCoupon(checkandGenerateNewCoupon(dbCoupons));        
		return new ResponseEntity<Coupons>(coupon, HttpStatus.ACCEPTED);
	}

	public String newCoupon() {
		char[] chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new SecureRandom();
        for (int i = 0; i < 8; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        String output = sb.toString();
        System.out.println(output);
        return output;
	}
	
	public String checkandGenerateNewCoupon( List<Coupons> oldCoupons) {		
		String newcoupon =newCoupon();
		for(Coupons temp:oldCoupons) {
			if(temp.getCoupon()==newcoupon) {
				checkandGenerateNewCoupon(oldCoupons);
				break;
			}
		}
		return newcoupon;
	}
	
	@GetMapping(path=ConsumerURLMapping.COMPLAINTS,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<ComplaintboxBean>> getAllComplaintsList() {
		List<ComplaintboxBean> complaints = complaintBoxRest.getAllComplaints();

		return new ResponseEntity<List<ComplaintboxBean>>(complaints, headers.getHeader(), HttpStatus.ACCEPTED);
	}

	@PutMapping(path=ConsumerURLMapping.COMPLAINTS,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> Statusupdate(@RequestBody ComplaintboxBean cb) {
		ResponseBean responseBean = complaintBoxRest.update(cb.getComplaintid()+"",cb.getEmail(),cb.getStatus());

		return new ResponseEntity<ResponseBean>(responseBean, headers.getHeader(), HttpStatus.ACCEPTED);
	}
	
	//Get Method for all the sources
	@GetMapping(path=ConsumerURLMapping.SOURCES,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<SourcesalesBean>> getAllSources() {
		List<SourcesalesBean> complaints = invoiceManagemenrRest.getAllSources();

		return new ResponseEntity<List<SourcesalesBean>>(complaints, headers.getHeader(), HttpStatus.ACCEPTED);
	}
	
	//Get Method for all INVOICES
	@GetMapping(path=ConsumerURLMapping.INVOICES,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<InvoicesBean>> getAllInvoices() {
		List<InvoicesBean> complaints = invoiceManagemenrRest.getAllInvoices();
		return new ResponseEntity<List<InvoicesBean>>(complaints, headers.getHeader(), HttpStatus.ACCEPTED);
	}


		//Get Status
	@GetMapping(path=ConsumerURLMapping.STATUS,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<SoldproductstatusBean>> getStatus() {
		List<SoldproductstatusBean> complaints = invoiceManagemenrRest.getStatus();
		return new ResponseEntity<List<SoldproductstatusBean>>(complaints, headers.getHeader(), HttpStatus.ACCEPTED);
	}


	//Get Sold Products
	@GetMapping(path=ConsumerURLMapping.SOLD_PRODUCTS_INFO,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<SoldproductsBean>> getSold_Products() {
		List<SoldproductsBean> complaints = invoiceManagemenrRest.getSoldProductsInfo();
		return new ResponseEntity<List<SoldproductsBean>>(complaints, headers.getHeader(), HttpStatus.ACCEPTED);
	}
	
	//Post method for invoices
	@PostMapping(path=ConsumerURLMapping.INVOICES,produces = MediaType.APPLICATION_JSON_VALUE )
	@ResponseBody
	public ResponseEntity<ResponseBean> insert(@RequestBody InvoicePostBean ipb){
		String[] prid= new String[ipb.getSoldproditem().length];
		String[] qty= new String[ipb.getSoldproditem().length];
		String[] netamount= new String[ipb.getSoldproditem().length];
		String[] gst= new String[ipb.getSoldproditem().length];
		String[] discount= new String[ipb.getSoldproditem().length];
		String[] totalproductprice= new String[ipb.getSoldproditem().length];
		int i=0;
		for(SoldProductsPostBean spb:ipb.getSoldproditem() ) {
			prid[i]=spb.getPrid();
			qty[i]=spb.getQty();
			netamount[i]=spb.getNetamount();
			gst[i]=spb.getGst();
			discount[i]=spb.getDiscount();
			totalproductprice[i]=spb.getTotalproductprice();
			i++;
		}
		
		return new ResponseEntity<ResponseBean>(invoiceManagemenrRest.InsertOrders(ipb.getSid(),ipb.getOrid(),ipb.getDateoforder(),
				ipb.getDateofinvoice(),ipb.getTotal(),ipb.getShipping(),ipb.getPaymentmode(),ipb.getTransactionid(),ipb.getStatusoftheinvoice(),
				ipb.getUser(),ipb.getInvoiceid(),prid,qty,netamount,gst,discount,totalproductprice,ipb.getStatus()), 
				HttpStatus.ACCEPTED);
	}
	
	//Get Year & mmoths
	@GetMapping(path=ConsumerURLMapping.SALES_TIME,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<SalesTime>> getYearTime(@PathVariable String sid) {
		List<SalesTime> complaints = invoiceManagemenrRest.getAllDates(sid);
		return new ResponseEntity<List<SalesTime>>(complaints, headers.getHeader(), HttpStatus.ACCEPTED);
	}
	
	
	//Get All users along with thier invoices
	@GetMapping(path=ConsumerURLMapping.FULL_INFO,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Invoices_Info_by_userBean>> getFullInfo() {
		List<Invoices_Info_by_userBean> complaints = invoiceManagemenrRest.getAllInfo();
		return new ResponseEntity<List<Invoices_Info_by_userBean>>(complaints, headers.getHeader(), HttpStatus.ACCEPTED);
	}
	
	//Get user invoices info based on the year and month
	@GetMapping(path=ConsumerURLMapping.FULL_INFO_BY_DATE,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Invoices_Info_by_userBean> getFullInfoByUser(@PathVariable String user,@PathVariable String year, @PathVariable String month) {
		System.out.println("Entered");
		Invoices_Info_by_userBean complaints = invoiceManagemenrRest.getAllInfoByUser(user,year,month);
		return new ResponseEntity<Invoices_Info_by_userBean>(complaints, headers.getHeader(), HttpStatus.ACCEPTED);
	}
	
	
	@GetMapping(path="/C122", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<ProductCardDetailsForInvoicesBean>> getAllProductIds(){
		return new ResponseEntity<List<ProductCardDetailsForInvoicesBean>>(pcl.getAllProductIds(), headers.getHeader(), HttpStatus.ACCEPTED);
	}
	
	
	@PostMapping(path=ConsumerURLMapping.UPLOAD_PDF,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> UploadPdf(@RequestParam(name="img")MultipartFile img,@PathVariable long inid){
		return new ResponseEntity<ResponseBean>(invoiceManagemenrRest.uploadPDF(inid,img), HttpStatus.ACCEPTED);
	}
	
	@GetMapping(path=ConsumerURLMapping.PDF_LOC, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public void getPDFLOC(HttpServletResponse response,@PathVariable long inid){
		
try {
			
			
			InputStream in = new FileInputStream(invoiceManagemenrRest.getImgLoc(inid));
			response.setContentType(MediaType.APPLICATION_PDF_VALUE);	
			IOUtils.copy(in, response.getOutputStream());
			
			}catch(Exception e) {
				ResponseBean rb = new ResponseBean(e.getMessage(),4000, System.currentTimeMillis());
				
				try {
					String reply = new Gson().toJson(rb);
					response.setContentType("application/pdf");
					response.setCharacterEncoding("UTF-8");
					response.getWriter().print(reply);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
			}
		
	}
	
	@PostMapping(path=ConsumerURLMapping.NOTIFICATION,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> insertNotifications(@RequestParam("title") String title,@RequestParam("img") MultipartFile img,
			@RequestParam("description") String description, @RequestParam("date") String date,@RequestParam("delaydate") String delaydate, @RequestParam("url") String url) 
					throws MaxUploadSizeExceededException {
		
		if(title.equals(ConstantValues.DEFAULT_STRING)||img.isEmpty()||description.equals(ConstantValues.DEFAULT_STRING)||date.equals(ConstantValues.DEFAULT_STRING)||delaydate.equals(ConstantValues.DEFAULT_STRING)||url.equals(ConstantValues.DEFAULT_STRING))
		{
			throw new NullRequestReceivedException(ConstantValues.RECEIVED_NULL_VALUES);
		} else {
			Date date1 = null;
			Date date2 = null;
			try {
				date1 = new SimpleDateFormat("dd/MM/yyyy").parse(date);
				date2 = new SimpleDateFormat("dd/MM/yyyy").parse(delaydate);
			} catch (ParseException e) {
			
				date1 = Date.from(Instant.parse(date));
				date2 = Date.from(Instant.parse(delaydate));
			}
			ResponseBean response=new ResponseBean();
			try {
				System.out.println(title);
				System.out.println(description);
				System.out.println(date1);
				System.out.println(date2);
				System.out.println(url);
				this.notificationrepo.save(new Notifications(title,img.getBytes(),description,date1,date2,url));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.setMessage(ConstantValues.DATA_INSERTED_IN_DB);
			response.setResponsecode(SecurityHttpStatus.ACCEPTED);
			response.setTiemstamp(System.currentTimeMillis());
			return new ResponseEntity<ResponseBean>(response,  HttpStatus.ACCEPTED);
		}
	}
	
	@GetMapping(path = ConsumerURLMapping.NOTIFICATION,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Notifications>> getAllNotifications() {
		List<Notifications> notify= (List<Notifications>)this.notificationrepo.findAll();
		if(!notify.isEmpty()) {		
			return new ResponseEntity<List<Notifications>>(notify,HttpStatus.ACCEPTED);
		}
		else {
			throw new NotificationNotFoundException(ConstantValues.NOTIFICATION_NOT_FOUND_EXCEPTION);
		}
	}
	@PostMapping(path=ConsumerURLMapping.COMBOS,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> insertCombos(@RequestBody CombosBean combos)

	{

		ResponseBean responseBean = comborest.insertCombos(combos.getTotal_amount(),combos.getDiscount(),combos.getTotal_amt_after_discount(),
				combos.getStartdate(),combos.getEnddate(),combos.getStatus(),combos.getTitle(),combos.getDescription(),combos.getAgeid(),combos.getGenderid());
		return new ResponseEntity<ResponseBean>(responseBean, headers.getHeader(), HttpStatus.ACCEPTED);
	}
	
	@PostMapping(path=ConsumerURLMapping.COMBOSPROD,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> insertCombos(@RequestBody ComboProdDetailsBean combos)

	{
		System.out.println("Entered=-------");
		String[] productid= new String[combos.getProductidfinalprice().length];
		String[] finalprice= new String[combos.getProductidfinalprice().length];
		
		int i=0;
		for(ProductidsfinalidsBean spb:combos.getProductidfinalprice() ) {
			productid[i]=spb.getProductid();
			finalprice[i]=spb.getFinalprice();
			i++;
		}
		

		ResponseBean responseBean = comborest.insertComboProductDetails(combos.getComboid(),productid,finalprice);
		return new ResponseEntity<ResponseBean>(responseBean, headers.getHeader(), HttpStatus.ACCEPTED);
	}
	
	// combo full info
		@GetMapping(path=ConsumerURLMapping.COMBO_FULL_DETAILS,produces = MediaType.APPLICATION_JSON_VALUE)
		@ResponseBody
		public ResponseEntity<List<ComboFullDetailsBean>> getAllComboInfo() {
			List<ComboFullDetailsBean> combo = comborest.getAllCombosfulldetails();

			return new ResponseEntity<List<ComboFullDetailsBean>>(combo, headers.getHeader(), HttpStatus.ACCEPTED);
		}
	
		// combo with product details
		@GetMapping(path=ConsumerURLMapping.COMBO_Productdetails_DETAILS,produces = MediaType.APPLICATION_JSON_VALUE)
		@ResponseBody
		public ResponseEntity<List<CombosBean>> getAllComboidwithoutdetails() {
			List<CombosBean> combo = comborest.getAllComboidwithoutproductdetails();

			return new ResponseEntity<List<CombosBean>>(combo, headers.getHeader(), HttpStatus.ACCEPTED);
		}
		
		@GetMapping(path=ConsumerURLMapping.PRODUCT_BY_AGE_GEN,produces = MediaType.APPLICATION_JSON_VALUE)
		@ResponseBody
		public ResponseEntity<List<ProductFullDetails>> getAllProductDetailsByage(@PathVariable String genderid,
				@PathVariable String ageid) {
			List<ProductFullDetails> x=productDetailsRestSErvice.getAllProductsDetails();
			List<ProductFullDetails> y = new CopyOnWriteArrayList<>();
			if (!x.isEmpty()) {
				for(ProductFullDetails pfd:x) {
					if(pfd.getAgeid().equals(ageid) && pfd.getGenderid().equals(genderid)) {
						y.add(pfd);
					}
				}
			}
			return new ResponseEntity<List<ProductFullDetails>>(y,
					headers.getHeader(), HttpStatus.ACCEPTED);
		}
		

}
