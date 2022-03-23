package com.lc.sk.inventory.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.lc.sk.inventory.entities.Similarproducts;

public interface SimilarproductsRepository extends CrudRepository<Similarproducts, Long> {

	
	@Query(value="select * from similarproducts", nativeQuery=true)
	public List<Similarproducts> getAllSimilarProducts();
	

	@Query(value="select * from similarproducts where productid=:productid", nativeQuery=true)
	public List<Similarproducts> getAllSimilarProductsbyid(@Param("productid") Long productid);
	
	@Query(value="select * from similarproducts where pvid=:pvid", nativeQuery=true)
	public List<Similarproducts> getAllSimilarProductsbypvid(@Param("pvid") Long pvid);
}
