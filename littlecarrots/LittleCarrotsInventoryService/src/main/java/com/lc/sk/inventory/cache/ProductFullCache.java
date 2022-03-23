package com.lc.sk.inventory.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.lc.sk.inventory.bean.ProductFullDetails;
import com.lc.sk.inventory.repositories.ProductPagingRepository;
import com.lc.sk.inventory.repositories.ProductsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class ProductFullCache {
	private final Logger log = LoggerFactory.getLogger(ProductFullCache.class);
	
	private @Autowired
	ProductsRepository productsRepository;
	
	private @Autowired 
	ProductPagingRepository productPagingRepository;
	
	private Map<Long,ProductFullDetails> cache = new ConcurrentHashMap<>();
	private List<ProductFullDetails> forClient = new ArrayList<ProductFullDetails>();
	@PostConstruct
	 @Scheduled(fixedDelay=900000)
	public void getAllProductDetails() { // NO_UCD (used code)
		cache = new ConcurrentHashMap<>();
		log.info("---Inventory cache started..------");
		List<ProductFullDetails> pfd = this.productsRepository.getAllProductDetails();
		
		for(ProductFullDetails pf:pfd) {
			cache.put(Long.parseLong(pf.getProductid()), pf);
		}
		log.info(cache.size()+ " Products in Cache");
		log.info("------Inventory cache intialization complete....");
		log.info("---Inventory cache started for Client System..------");
		forClient = this.productPagingRepository.getForCache();
		log.info(forClient.size()+ " Products in Cache for client");
		log.info("------Inventory cache intialization complete for client system....");
	}
	
	public List<ProductFullDetails> getAllDetails(){
		return new CopyOnWriteArrayList<>(cache.values());
	}
	
	public ProductFullDetails getProductDetails(Long productid) {
		return cache.get(productid);
	}
	
	public List<ProductFullDetails> getAllProductsForClient(){
		return forClient;
	}
}
