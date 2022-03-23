package com.little.carrots.order.services;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import com.little.carrots.order.bean.DateInfo;
import com.little.carrots.order.bean.Invoice_full_detailsBean;
import com.little.carrots.order.bean.Invoices_Info_by_user;
import com.little.carrots.order.bean.Numerous_Invoices;
import com.little.carrots.order.bean.ResponseBean;
import com.little.carrots.order.bean.SalesTimeBean;
import com.little.carrots.order.bean.YearBean;

import com.little.carrots.order.entity.Invoices;
import com.little.carrots.order.entity.Invoicespdf;

import com.little.carrots.order.entity.Soldproducts;
import com.little.carrots.order.entity.Soldproductstatus;
import com.little.carrots.order.entity.Sourcesales;

import com.little.carrots.order.exception.subexceptions.DBInsertException;
import com.little.carrots.order.exception.subexceptions.DBValueInsertException;
import com.little.carrots.order.exception.subexceptions.InvoiceNotFoundException;
import com.little.carrots.order.exception.subexceptions.NullRequestReceivedException;

import com.little.carrots.order.exception.subexceptions.SoldProductsNotFoundException;
import com.little.carrots.order.exception.subexceptions.SourcesNotFoundException;
import com.little.carrots.order.repositories.InvoicesRepository;
import com.little.carrots.order.repositories.InvoicespdfRepository;
import com.little.carrots.order.repositories.SoldproductsRepository;
import com.little.carrots.order.repositories.SoldproductstatusRepository;
import com.little.carrots.order.repositories.SourcesalesRepository;
import com.little.carrots.order.util.ConstantValues;
import com.little.carrots.order.util.URLMapping;



@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Validated
@RequestMapping(path = URLMapping.ORDER_ROOT_PATH)
public class InvoiceManagementService {
	
	@Autowired
	SourcesalesRepository sourcesalesRepo;
	
	@Autowired
	InvoicesRepository invoicesRepo;
	
	@Autowired
	SoldproductstatusRepository soldproductsStatusRepo;
	
	@Autowired
	SoldproductsRepository soldproductsRepo;
	
	@Autowired
	InvoicespdfRepository repo;
	
	File folder = new File("E:/Files/Backend/GIT/lil_invetory_services/LittleCarrotsOrderManagement/INVOICES");

	
	
	

	@CrossOrigin(origins = "*")
	@PostMapping(path=URLMapping.UPLOAD,headers = "Content-Type= multipart/form-data")	
	public ResponseEntity<ResponseBean> PostMethod1(@RequestParam("img") MultipartFile img,
			@PathVariable long inid) throws Exception{
		System.out.println("Entered ---------------------"+inid);
		ResponseBean response = null;
		try{
		
		if(!this.folder.exists()) {
			this.folder.mkdir();
		}else {
			System.err.println("CONTENT FOLDER EXIST");
			System.err.println("Path:"+this.folder.getAbsolutePath());
		}
		File subfolder = new File(this.folder.getAbsoluteFile()+"/"+inid+"");
		if(!subfolder.exists()) {
			subfolder.mkdir();
			System.err.println("Sub Folder["+subfolder.getName()+"] CREATED..");
			System.err.println("Path:"+subfolder.getAbsolutePath());
		}else {
			System.err.println("Sub Folder["+subfolder.getName()+"] FOLDER EXIST");
		}
		
		
			InputStream in = img.getInputStream();
			System.err.println("FileName: "+img.getOriginalFilename());
			File imagefilename = new File(subfolder+"/"+img.getOriginalFilename());
			OutputStream out = new FileOutputStream(imagefilename);
			FileCopyUtils.copy(in, out);
			out.close();
			in.close();
			repo.save(new Invoicespdf(inid, img.getOriginalFilename()));	
		
		response = new ResponseBean("Success", 2000, System.currentTimeMillis());
		}catch(IOException ee) {
			response = new ResponseBean(ee.getMessage(), 4000, System.currentTimeMillis());
			ee.printStackTrace();
		}
		return new ResponseEntity<ResponseBean>(response, HttpStatus.ACCEPTED);
	}
	
	@GetMapping(path=URLMapping.PDFLOCATION,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> getLocation(@PathVariable long inid) {
		String filename = repo.getAllInid(inid);
		if (filename.equals(null)) {
			throw new InvoiceNotFoundException(ConstantValues.INVOICE_NOT_FOUND);
			
		} else {
			
			String subfolder1 = this.folder+"/"+inid+"/"+filename;
			

			return new ResponseEntity<String>(subfolder1,  HttpStatus.ACCEPTED);
		}
	}
	
	//Get All Source Sales
	@GetMapping(path=URLMapping.GET_SOURCES,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Sourcesales>> getAll() {
		List<Sourcesales> source = (List<Sourcesales>) sourcesalesRepo.getAllSources();
		if (!source.isEmpty()) {
			return new ResponseEntity<List<Sourcesales>>(source,  HttpStatus.ACCEPTED);
		} else {
			throw new SourcesNotFoundException(ConstantValues.SOURCES_NOT_FOUND);
		}
	}
	
	// Insert Source Sales
	@PostMapping(path=URLMapping.GET_SOURCES,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> insertCoupons(
			@RequestParam(name=ConstantValues.SOURCE,required = true, defaultValue = ConstantValues.DEFAULT_STRING) String source)
	{
		if( source.equals(ConstantValues.DEFAULT_STRING))
		{
			throw new NullRequestReceivedException(ConstantValues.RECEIVED_NULL_VALUES);
		}
		else
		{
			ResponseBean response=new ResponseBean();
			Sourcesales sources=new Sourcesales(source);
			sourcesalesRepo.save(sources);			
			if(sources.getSource().equals(source))
			{
				response.setMessage("Data inserted in DB");
				response.setResponsecode(2000);
				response.setTiemstamp(System.currentTimeMillis());
			}
			else
			{
				throw new DBInsertException("DB insert exception");
			}
			
			return new ResponseEntity<ResponseBean>(response,  HttpStatus.ACCEPTED);
		}
	}
	
	//Get all Invoices
	@GetMapping(path=URLMapping.INVOICES,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Invoices>> getAllInvoices() {
		List<Invoices> invoices = (List<Invoices>) invoicesRepo.getAllInvoices();
		if (!invoices.isEmpty()) {
			return new ResponseEntity<List<Invoices>>(invoices,  HttpStatus.ACCEPTED);
		} else {
			throw new InvoiceNotFoundException(ConstantValues.INVOICE_NOT_FOUND);
		}
	}
	
	// Insert Invoices
		@PostMapping(path=URLMapping.INVOICES,produces = MediaType.APPLICATION_JSON_VALUE)
		@ResponseBody
		public ResponseEntity<ResponseBean> insertInvoices(
				@RequestParam(name=ConstantValues.SID,required = true, defaultValue = ConstantValues.DEFAULT_INT) long sid,
				@RequestParam(name=ConstantValues.ODERID,required = true, defaultValue = ConstantValues.DEFAULT_STRING) String orid,
				@RequestParam(name=ConstantValues.ORDERDATE,required = true, defaultValue = ConstantValues.DEFAULT_STRING)String dateoforder,
				@RequestParam(name=ConstantValues.INVOICEDATE,required = true, defaultValue = ConstantValues.DEFAULT_STRING)String dateofinvoice,
				@RequestParam(name=ConstantValues.TOTAL,required = true, defaultValue = ConstantValues.DEFAULT_FLOAT) float total,
				@RequestParam(name=ConstantValues.SHIPPING,required = true, defaultValue = ConstantValues.DEFAULT_FLOAT) float shipping,
				@RequestParam(name=ConstantValues.PAYMENTMODE,required = true, defaultValue = ConstantValues.DEFAULT_STRING) String paymentmode,
				@RequestParam(name=ConstantValues.TRANSACTIONID,required = true, defaultValue = ConstantValues.DEFAULT_STRING) String transactionid,
				@RequestParam(name=ConstantValues.STATUSOFTHEINVOICE,required = true, defaultValue = ConstantValues.DEFAULT_STRING) String statusoftheinvoice,
				@RequestParam(name=ConstantValues.USER,required = true, defaultValue = ConstantValues.DEFAULT_STRING) String user,
				@RequestParam(name=ConstantValues.INVOICEID,required = true, defaultValue = ConstantValues.DEFAULT_STRING) String invoiceid,
				@RequestParam(name="prid[]") long prid[],
				@RequestParam(name="qty[]") long qty[],
				@RequestParam(name="netamount[]") float netamount[],
				@RequestParam(name="gst[]") float gst[],
				@RequestParam(name="discount[]") float discount[],
				@RequestParam(name="totalproductprice[]") float totalproductprice[],
				@RequestParam(name=ConstantValues.STATUS,required = true, defaultValue = ConstantValues.DEFAULT_STRING) String status)
		{
			if( sid==Long.parseLong(ConstantValues.DEFAULT_INT) ||
				dateoforder.equals(ConstantValues.DEFAULT_STRING)||
				dateofinvoice.equals(ConstantValues.DEFAULT_STRING)||
				total==Float.parseFloat(ConstantValues.DEFAULT_FLOAT) ||
						shipping==Float.parseFloat(ConstantValues.DEFAULT_FLOAT) ||
				paymentmode.equals(ConstantValues.DEFAULT_STRING)||
				transactionid.equals(ConstantValues.DEFAULT_STRING)||
				statusoftheinvoice.equals(ConstantValues.DEFAULT_STRING)||
				user.equals(ConstantValues.DEFAULT_STRING)||
				(prid.length!=qty.length && prid.length!=netamount.length && prid.length!=gst.length && prid.length!=discount.length && prid.length!=totalproductprice.length) )
			{
				throw new NullRequestReceivedException(ConstantValues.RECEIVED_NULL_VALUES);
			} else if((orid.equals(ConstantValues.DEFAULT_STRING)||  invoiceid.equals(ConstantValues.DEFAULT_STRING)) && (sid!=ConstantValues.LITTLE_CARROTS& sid!=ConstantValues.MARKETING & sid!= ConstantValues.LOCAL)) {
				
					throw new NullRequestReceivedException(ConstantValues.RECEIVED_NULL_VALUES);
				
				
			}else {
				if(sid==ConstantValues.LITTLE_CARROTS|| sid==ConstantValues.MARKETING|| sid== ConstantValues.LOCAL) {
					
					List<Invoices> invoices = (List<Invoices>) invoicesRepo.getAllInvoices();
					String Letters="";
					if(sid==ConstantValues.LITTLE_CARROTS) {Letters = "LCW"; }
					if(sid==ConstantValues.MARKETING ) {Letters = "LCM"; }
					if(sid== ConstantValues.LOCAL) {Letters = "LCS"; }
					orid=checkandGenerateOrderid(invoices,Letters);
					invoiceid=orid;
				}
				Date date1 = null;
				Date date2 = null;
				try {
					date1 = new SimpleDateFormat("dd/MM/yyyy").parse(dateoforder);
					date2 = new SimpleDateFormat("dd/MM/yyyy").parse(dateofinvoice);
				} catch (ParseException e) {
				
					date1 = Date.from(Instant.parse(dateoforder));
					date2 =Date.from(Instant.parse(dateofinvoice));
				}
							
				ResponseBean response=new ResponseBean();
				invoicesRepo.save(new Invoices(sid,orid,date1,date2,total,shipping,paymentmode,transactionid,statusoftheinvoice,user,invoiceid));
				Long inid=invoicesRepo.getId(orid,user);
				
				List<Soldproducts> entities = new CopyOnWriteArrayList<Soldproducts>();
				if(prid.length!=0) {
					int x= prid.length;
					for(int i=0;i<x;i++)
					{
					
					entities.add(new Soldproducts(inid,prid[i],qty[i],netamount[i],gst[i],discount[i],totalproductprice[i],status));
					
					}
				
					soldproductsRepo.saveAll(entities);
			
					response.setMessage(inid+"");
					response.setResponsecode(2000);
					response.setTiemstamp(System.currentTimeMillis());
				
				return new ResponseEntity<ResponseBean>(response,  HttpStatus.ACCEPTED);
				}
				 else
				    {
				    	throw new  DBValueInsertException(ConstantValues.DATA_NOT_INSERTED_IN_DB);
				    }
			}
		}
		
		
		public String newInvoice(String Letters) {
			char[] chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".toCharArray();
	        StringBuilder sb = new StringBuilder();
	        sb.append(Letters);
	        Random random = new SecureRandom();
	        for (int i = 0; i < 5; i++) {
	            char c = chars[random.nextInt(chars.length)];
	            sb.append(c);
	        }
	        String output = sb.toString();
	       
	        return output;
		}
		
		public String checkandGenerateOrderid( List<Invoices> oldorderids, String Letters) {		
			String neworid =newInvoice(Letters);
			for(Invoices temp:oldorderids) {
				if(temp.getOrid()==neworid) {
					checkandGenerateOrderid(oldorderids,Letters);
					break;
				}
			}
			return neworid;
		}
		
		//Status
		@GetMapping(path=URLMapping.GET_STATUS,produces = MediaType.APPLICATION_JSON_VALUE)
		@ResponseBody
		public ResponseEntity<List<Soldproductstatus>> getStatus() {
			List<Soldproductstatus> status = (List<Soldproductstatus>) soldproductsStatusRepo.getAllStatus();
			if (!status.isEmpty()) {
				return new ResponseEntity<List<Soldproductstatus>>(status,  HttpStatus.ACCEPTED);
			} else {
				throw new SourcesNotFoundException(ConstantValues.SOURCES_NOT_FOUND);
			}
		}
		
		//Sold Products
		@GetMapping(path=URLMapping.GET_SOLD_PRODUCTS,produces = MediaType.APPLICATION_JSON_VALUE)
		@ResponseBody
		public ResponseEntity<List<Soldproducts>> getSoldProducts() {
			List<Soldproducts> status = (List<Soldproducts>) soldproductsRepo.getAllSoldproducts();
			if (!status.isEmpty()) {
				return new ResponseEntity<List<Soldproducts>>(status,  HttpStatus.ACCEPTED);
			} else {
				throw new SoldProductsNotFoundException(ConstantValues.SOLD_PRODUCTS_NOT_FOUND);
			}
		}
		
		
		//Get method dates info
		@GetMapping(path=URLMapping.SALESTIME,produces = MediaType.APPLICATION_JSON_VALUE)
		@ResponseBody
		public ResponseEntity<List<SalesTimeBean>> getDatesInfo(@PathVariable long sid) {
			List<DateInfo> dates = (List<DateInfo>) invoicesRepo.getAllDatesInfo(sid);
			
			Set<String> users = new HashSet<>();
			for (DateInfo sc : dates) {
				users.add(sc.getUser());
			}
			List<String> user = new ArrayList<String>(users);
			List<SalesTimeBean> stbs = new ArrayList<>();
			for(String x : user) {
				SalesTimeBean stb= new SalesTimeBean();
				stb.setUser(x);
				List<YearBean> ybs= new ArrayList<>();
				
				Set<String> years = new HashSet<>();
				for (DateInfo sc : dates) {
					if(sc.getUser().equals(x)) {
						years.add(sc.getYear());
					}
				}
			
				List<String> year = new ArrayList<String>(years);
			
				for(String y : year) {
					YearBean yb= new YearBean();
					System.out.println("Year:------"+y);
					yb.setYear(y);
					Set<String> months = new HashSet<>();
					for (DateInfo sc : dates) {
						if(sc.getUser().equals(x) && sc.getYear().equals(y)) {
							months.add(sc.getMonth());
						}
					}
					List<String> month = new ArrayList<String>(months);
				
					yb.setMonth(month);
					ybs.add(yb);
				}
				stb.setYearwise(ybs);
				stbs.add(stb);
			}
			
			
			
			if (!dates.isEmpty()) {
				return new ResponseEntity<List<SalesTimeBean>>(stbs,  HttpStatus.ACCEPTED);
			} else {
				throw new SourcesNotFoundException(ConstantValues.SOURCES_NOT_FOUND);
			}
		}			
		
		//Get entire information
		@GetMapping(path=URLMapping.FULL_INVOICES,produces = MediaType.APPLICATION_JSON_VALUE)
		@ResponseBody
		public ResponseEntity<List<Invoices_Info_by_user>> getFullInfo() {
			List<Invoice_full_detailsBean> status = (List<Invoice_full_detailsBean>) invoicesRepo.getFullDetails();
			
			Set<String> users = new HashSet<>();
			for (Invoice_full_detailsBean sc : status) {
				users.add(sc.getUser());
			}
			List<String> user = new ArrayList<String>(users);
			List<Invoices_Info_by_user> invs = new ArrayList<>();
			
			for(String x : user) {
				Invoices_Info_by_user inv= new Invoices_Info_by_user();
				inv.setUser(x);
				
				Set<Long> inid = new HashSet<>();
				for (Invoice_full_detailsBean sc : status) {
					if(sc.getUser().equals(x)) {
						inid.add(sc.getInid());
					}
				}
			
				List<Long> inids = new ArrayList<Long>(inid);
				
				List<Numerous_Invoices> nis = new ArrayList<>();
				System.out.println("--------------------------------------------");
				System.out.println("Filtering invoices");
				for(long y:inids) {
					Numerous_Invoices ni= new Numerous_Invoices(); 		
					ni.setInid(y);
					long test=0;
					List<Soldproducts> sps = new ArrayList<>();
					for(Invoice_full_detailsBean sc : status) {
						if(sc.getInid()==y && sc.getSaleprid()!=test) {
							if(test!=0){
								ni.setSid(sc.getSid());
								ni.setSource(sc.getSource());
								ni.setOrid(sc.getOrid());
								ni.setDateoforder(sc.getDateoforder());
								ni.setDateofinvoice(sc.getDateofinvoice());
								ni.setYear(sc.getYear());
								ni.setMonth(sc.getMonth());
								ni.setTotal(sc.getTotal());
								ni.setShipping(sc.getShipping());
								ni.setPaymentmode(sc.getPaymentmode());
								ni.setTransactionid(sc.getTransactionid());
								ni.setStatusoftheinvoice(sc.getStatusoftheinvoice());
								ni.setInvoiceid(sc.getInvoiceid());
										
							}
							Soldproducts sp= new Soldproducts(); 
							sp.setSaleprid(sc.getSaleprid());
							sp.setInid(y);
							sp.setPrid(sc.getPrid());
							sp.setQty(sc.getQty());
							sp.setNetamount(sc.getNetamount());
							sp.setGst(sc.getGST());
							sp.setDiscount(sc.getDiscount());
							sp.setTotalproductprice(sc.getTotalproductprice());
							sp.setStatus(sc.getstatus());
							test=sc.getSaleprid();
							sps.add(sp);
						}
						ni.setSoldproducts(sps);
						
					}
					nis.add(ni);
				}
				inv.setInvoice(nis);
				invs.add(inv);
			}
				
			
			
			if (!invs.isEmpty()) {
				return new ResponseEntity<List<Invoices_Info_by_user>>(invs,  HttpStatus.ACCEPTED);
			} else {
				throw new SoldProductsNotFoundException(ConstantValues.SOLD_PRODUCTS_NOT_FOUND);
			}
		}
		
		//Get user info for that year and month
		@GetMapping(path=URLMapping.INVOICES_BY_USER,produces = MediaType.APPLICATION_JSON_VALUE)
		@ResponseBody
		public ResponseEntity<Invoices_Info_by_user> getFullInfoByUser(@PathVariable String user, @PathVariable String year, @PathVariable String month) {
			List<Invoice_full_detailsBean> status = (List<Invoice_full_detailsBean>) invoicesRepo.filter(user,year,month);
				Invoices_Info_by_user inv= new Invoices_Info_by_user();
				inv.setUser(user);
				
				Set<Long> inid = new HashSet<>();
				for (Invoice_full_detailsBean sc : status) {
					inid.add(sc.getInid());
				}
			
				List<Long> inids = new ArrayList<Long>(inid);
				
				List<Numerous_Invoices> nis = new ArrayList<>();
			
				for(long y:inids) {
					Numerous_Invoices ni= new Numerous_Invoices(); 		
					ni.setInid(y);
					long test=0;
					List<Soldproducts> sps = new ArrayList<>();
					for(Invoice_full_detailsBean sc : status) {
						System.out.println("Entered ---------------03");
						if(sc.getInid()==y && sc.getSaleprid()!=test) {
							System.out.println("Matched ---------------03 and test is  "+ test +"ANd result is "+(test!=0));
							if(test == 0){
								System.out.println("Storing invoice details ---------------03");
								ni.setSid(sc.getSid());
								ni.setSource(sc.getSource());
								ni.setOrid(sc.getOrid());
								ni.setDateoforder(sc.getDateoforder());
								ni.setDateofinvoice(sc.getDateofinvoice());
								ni.setYear(sc.getYear());
								ni.setMonth(sc.getMonth());
								ni.setTotal(sc.getTotal());
								ni.setShipping(sc.getShipping());
								ni.setPaymentmode(sc.getPaymentmode());
								ni.setTransactionid(sc.getTransactionid());
								ni.setStatusoftheinvoice(sc.getStatusoftheinvoice());
								ni.setInvoiceid(sc.getInvoiceid());
										
							}
							System.out.println("Storing product details ---------------03");
							Soldproducts sp= new Soldproducts(); 
							sp.setSaleprid(sc.getSaleprid());
							sp.setInid(y);
							sp.setPrid(sc.getPrid());
							sp.setQty(sc.getQty());
							sp.setNetamount(sc.getNetamount());
							sp.setGst(sc.getGST());
							sp.setDiscount(sc.getDiscount());
							sp.setTotalproductprice(sc.getTotalproductprice());
							sp.setStatus(sc.getstatus());
							test=sc.getSaleprid();
							sps.add(sp);
						}
						ni.setSoldproducts(sps);
						
					}
					nis.add(ni);
				}
				inv.setInvoice(nis);		
			
			
			if (!status.isEmpty()) {
				return new ResponseEntity<Invoices_Info_by_user>(inv,  HttpStatus.ACCEPTED);
			} else {
				throw new SoldProductsNotFoundException(ConstantValues.SOLD_PRODUCTS_NOT_FOUND);
			}
		}
}
