package com.lc.sk.inventory.security.cache;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.lc.sk.inventory.security.beans.FileData;
import com.lc.sk.inventory.security.beans.ProductCardDetailsForInvoicesBean;
import com.lc.sk.inventory.security.beans.ProductFullDetails;
import com.lc.sk.inventory.security.beans.ProductFullDetailsWithImage;
import com.lc.sk.inventory.security.beans.ProductidsBean;
import com.lc.sk.inventory.security.exceptions.subexceptions.DataNotFoundException;
import com.lc.sk.inventory.security.kafka.EmailProducer;
import com.lc.sk.inventory.security.rest.ImagesResetService;
import com.lc.sk.inventory.security.rest.ProductPaginationRestService;
import com.lc.sk.inventory.security.rest.ProductVariationRestServie;
import com.lc.sk.inventory.security.util.ConstantValues;

@Component
public class ProductCacheLoader {

	private @Autowired
	ProductPaginationRestService repo;

	private @Autowired
	ProductVariationRestServie pvr;

	private @Autowired
	SearchScan scan;
	
	@Autowired
	private ImagesResetService imageResetService;
	
	private @Autowired
	EmailProducer emailProducer;

	private ConcurrentHashMap<String, ProductFullDetailsWithImage> data = new ConcurrentHashMap<>();
	private List<ProductidsBean> pvBean = new CopyOnWriteArrayList<>();
	private ConcurrentHashMap<String, ProductFullDetailsWithImage> data1 = new ConcurrentHashMap<>();
	private ConcurrentHashMap<String, FileData> imgdata = new ConcurrentHashMap<>();
	
	@PostConstruct
	 @Scheduled(fixedDelay=600000)
	public void ProductCacheLoader1() {
		 data = new ConcurrentHashMap<>();
		 pvBean = new CopyOnWriteArrayList<>();
		 data1 = new ConcurrentHashMap<>();
		 List<FileData> imgtemp = this.imageResetService.getProductList2();
		 System.err.println("----------------------------CACHING IMAGE SERVICE INFORMATION---------------------------");
			for(FileData tt:imgtemp) {
				this.imgdata.put(tt.getProductid(), tt);
			}
			System.err.println("----------------------------CACHING END---------------------------");
		System.err.println(
				"----------------------[SECURITY SERVICE] Product Cache Loader staring....-------------------");
		List<ProductFullDetails> temp = this.repo.getAllCacheInformation();
		
		System.err.println("Size of DB Repo: " + temp.size());
		System.err.println("---------------CALLING PV ENTITY ----------");
		List<ProductidsBean> prid = pvr.getAllProductVariationsProductids();
		this.pvBean = prid;
		System.err.println("pv size: "+prid.size());
		List<ProductFullDetails> tempc = new CopyOnWriteArrayList<>();
		System.err.println("Created dummy entity list");
		for(ProductFullDetails pfd:temp) {
				if(pfd!=null&&this.imgdata.get(pfd.getProductid())!=null) {
				data1.put(pfd.getProductid(), new ProductFullDetailsWithImage(pfd, this.imgdata.get(pfd.getProductid())));
				}
			}
		for(ProductidsBean pid:prid) {
			for(ProductFullDetails pfd:temp) {
			if(pid.getProductid()==Long.parseLong(pfd.getProductid())) {
				tempc.add(pfd);
			}
			}
		}
		System.err.println("Iteration Scan complete...");
		System.err.println("Dummy eitity size:"+tempc.size());
		System.err.println("Main eitity size:"+temp.size());
		System.err.println("---------------PV ENTITY SCAN COMPLETE.---------------");
		temp = tempc;
		System.err.println("After remove pv data Size of Main entity:"+temp.size());
		System.err.println("Generating Map....");
		for (ProductFullDetails pfd : temp) {
			this.data.put(pfd.getProductid(), new ProductFullDetailsWithImage(pfd, this.imgdata.get(pfd.getProductid())));
		}
		System.err.println("Map Generated...");
		System.err.println("Map Size:" + this.data.size());
		System.err.println("----------------------[SECURITY SERVICE] Product Cache Loader end....-------------------");
		
			
	}
	
	@Scheduled(fixedDelay=100000)
	public void runKafkaHit() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		   LocalDateTime now = LocalDateTime.now();  
		System.out.println(emailProducer.pingService("Security Service Hit for Activate Kafka")+" @:"+dtf.format(now));
	}
	
	public List<ProductidsBean> getPvIDBean(){
		return this.pvBean;
	}

	public ProductFullDetailsWithImage getByProductID(Long prid) {
		if (this.data.containsKey(prid + "")) {
			return this.data.get(prid + "");
		} else if(this.data1.containsKey(prid+"")){
			return this.data1.get(prid + "");
		}else{
			throw new DataNotFoundException(ConstantValues.NO_PRODUCTS_FOUND);
		}
	}
	
	public ProductFullDetailsWithImage getByProductID1(Long prid) {
		if (this.data1.containsKey(prid + "")) {
			return this.data1.get(prid + "");
		} else {
			throw new DataNotFoundException(ConstantValues.NO_PRODUCTS_FOUND);
		}
	}

	public List<ProductFullDetailsWithImage> getAllProducts() {
		if (this.data.size() > 0) {
			return new CopyOnWriteArrayList<ProductFullDetailsWithImage>(this.data.values());
		} else {
			throw new DataNotFoundException(ConstantValues.NO_PRODUCTS_FOUND);
		}
	}
	
	public List<ProductFullDetailsWithImage> getAllProductsForAge() {
		if (this.data1.size() > 0) {
			return new CopyOnWriteArrayList<ProductFullDetailsWithImage>(this.data1.values());
		} else {
			throw new DataNotFoundException(ConstantValues.NO_PRODUCTS_FOUND);
		}
	}
	

	public List<ProductFullDetailsWithImage> getProductsBySearch(SearchBean sb) {
		List<ProductFullDetailsWithImage> pfd = new CopyOnWriteArrayList<>();
		Set<ProductFullDetailsWithImage> temp1 = null;
		Set<ProductFullDetailsWithImage> temp2 = null;
		Set<ProductFullDetailsWithImage> temp = null;

		Set<ProductFullDetailsWithImage> datatemp = new CopyOnWriteArraySet<>();
	//	Set<ProductFullDetailsWithImage> datascanned = scan.getExactCategory(sb.getCategory(), getAllProducts());
		if(sb.getAge()!=null&&sb.getAge().length > 0) {
			for (ProductFullDetailsWithImage prd : getAllProductsForAge()) {
				datatemp.add(prd);
			}
		}
		else {
			for (ProductFullDetailsWithImage prd : getAllProducts()) {
				datatemp.add(prd);
			}
		}
		
		
		System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		System.err.println("Requested Search Bean Data:"+sb);
		System.err.println("Class: ProductCacheLoader.java, Method: getProductsBySearch(), ActualDAta Size:"+datatemp.size());
		System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		temp = new HashSet<>();
		
		
		

		for(ProductFullDetailsWithImage pf: datatemp) {
			temp.add(pf);
		}
		if (sb.getGender()!=null&&sb.getGender().length > 0) {
			System.err.println("*******************CALLING scan.getSearchByGender() ********************************");
			temp = scan.getSearchByGender(sb.getGender(), temp);
			if(temp.isEmpty()) {
				temp = datatemp;
			}
		}
		System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		System.err.println("Class: ProductCacheLoader.java, Method: getProductsBySearch(), After Gender Filter data Size:"+temp.size());
		System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		
		if (sb.getAge()!=null&&sb.getAge().length > 0) {
			System.err.println("*******************CALLING scan.getSearchByAge() ********************************");
			temp = scan.getSearchByAge(sb.getAge(), temp);
			if(temp.isEmpty()) {
				temp = datatemp;
			}
			System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
			System.err.println("Class: ProductCacheLoader.java, Method: getProductsBySearch(), After Age filter data Size:"+temp.size());
			System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		}
		
		
		/*
		if(sb.getPrice()!=null&&sb.getPrice().length>0) {
			if(temp==null) {
				temp = scan.getSearchByPrice(sb.getPrice(), datatemp);
			}else {
				temp = scan.getSearchByPrice(sb.getPrice(), temp);
			}
		}
		*/
		if(sb.getCategory()!=null&&sb.getCategory().length>0) {	
			System.err.println("*******************CALLING scan.getExactCategory() ["+sb.getCategory().length+"] ********************************");
			temp = scan.getExactCategory(sb.getCategory(), temp);
			if(temp.isEmpty()) {
				temp = datatemp;
			}
			System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
			System.err.println("Class: ProductCacheLoader.java, Method: getProductsBySearch(), After subtitle as category filter data Size:"+temp.size());
			System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		}
		/*
		if(sb.getFashion()!=null&&sb.getFashion().length>0) {
			if(temp==null) {
				temp = scan.getSearchCategory(sb.getFashion(), datatemp);				
			}else {
				temp1 = scan.getSearchCategory(new String[] {"Girls","Boys"}, datascanned);				
				temp2 = scan.getSearchByGender(new String[] {"u"},  datatemp);
				temp = scan.mergeSet(temp1, temp2);
			}
		}
		
		if(sb.getSpecial()!=null&&sb.getSpecial().length>0) {
			if(temp==null) {
				temp = scan.getSearchCategory(sb.getSpecial(), datascanned);				
			}else {
				temp1 = scan.getSearchCategory(new String[] {"Girls","Boys"}, datascanned);				
				temp2 = scan.getSearchByGender(new String[] {"u"},  datatemp);
				temp = scan.mergeSet(temp1, temp2);
			}
		}
		if(temp==null) {
			temp = scan.getSearchCategory(new String[] {"Girls","Boys"}, datascanned);	
		}
		*/
		
		Iterator<ProductFullDetailsWithImage> iterator = temp.iterator();

		while (iterator.hasNext()) {
			pfd.add(iterator.next());
		}
	
		Collections.sort(pfd, new SortProductDetails());
		return pfd;
	}
	
	public List<ProductCardDetailsForInvoicesBean> getAllProductIds(){
		List<ProductCardDetailsForInvoicesBean> ids = new ArrayList<>();
		
		Iterator<String> it = data1.keySet().iterator();
		while(it.hasNext()) {
			ProductCardDetailsForInvoicesBean pib = new ProductCardDetailsForInvoicesBean();
			ProductFullDetailsWithImage x = data1.get(it.next());
			pib.setAgeid(x.getAgeid());
			pib.setDiscount(x.getDiscount());
			pib.setFinalprice(x.getFinalprice());
			pib.setGenderid(x.getGenderid());
			pib.setLcprice(x.getLcprice());
			pib.setMrp(x.getMrp());
			pib.setProductid(x.getProductid());
			pib.setQuantity(x.getQuantity());
			pib.setSellingprice(x.getSellingprice());
			pib.setTax(x.getTax());
			pib.setProducttitle(x.getProducttitle());
			ids.add(pib);
		}
		
		return ids;
	}
	
	public FileData getImageInfo(String productid) {
		return this.imgdata.get(productid);
	}

}
