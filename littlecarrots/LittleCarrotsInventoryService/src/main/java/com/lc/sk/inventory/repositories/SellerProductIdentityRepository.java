package com.lc.sk.inventory.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.lc.sk.inventory.entities.Sellerproductidentity;

public interface SellerProductIdentityRepository extends CrudRepository<Sellerproductidentity, Long> {

	//@Query(value="select uid, flipkartid, amazonid, meeshoid, paytmid, s1,s2,s3,skuid from sellerproductidentity")
	//public List<Sellerproductidentity> getAllSellerProductIdentityDetails();
	
	//@Query(value="select uid, flipkartid, amazonid, meeshoid, paytmid, s1,s2,s3,skuid from sellerproductidentity where skuid=:productid")
	//public Optional<SellerProductIdentity> getIdsBySkuId(@Param("productid")Long skuid);
}
