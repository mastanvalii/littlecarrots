package com.lc.sk.inventory.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.lc.sk.inventory.entities.Wishlist;

public interface WishlistRepository extends CrudRepository<Wishlist, Long> {
	
	@Query(value="select wishlistid,customerid,productid from wishlist where productid=:productid and customerid=:customerid", nativeQuery=true)
	public Optional<Wishlist> getByProductId(@Param("productid") Long productid,@Param("customerid") Long customerid);

     @Query(value="select wishlistid,customerid,productid from wishlist where customerid=:customerid", nativeQuery=true)
     public List<Wishlist> byCustomerId(@Param("customerid") Long customerid);
//	
// TODO Remove unused code found by UCDetector
//      @Query(value="select wishlistid,customerid,productid from wishlist where productid=:productid", nativeQuery=true)
//      public List<Wishlist> getbyWishlistProductId(@Param("productid") Long productid);
	
	
}
