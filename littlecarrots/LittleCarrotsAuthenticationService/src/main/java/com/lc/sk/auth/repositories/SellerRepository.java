/**
 * 
 */
package com.lc.sk.auth.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


import com.lc.sk.auth.entities.Seller;

/**
 * @author Mastanvali Shaik
 * LittleCarrotsAuthenticationService
 * 2020
 */
public interface SellerRepository extends CrudRepository<Seller, Long>{

	@Query(value="select sellerid, sellername, sellercompanyname, phonenumber, address, email, isocode, status from seller", nativeQuery=true)
	public List<Seller> getAllSeller();

	@Query(value=" select sellerid, sellername, sellercompanyname, phonenumber, address, email, isocode, status from seller where sellerid=:sellerid", nativeQuery=true)
	public Optional<Seller> getBySellerId(@Param("sellerid")Long sellerid);	
}
