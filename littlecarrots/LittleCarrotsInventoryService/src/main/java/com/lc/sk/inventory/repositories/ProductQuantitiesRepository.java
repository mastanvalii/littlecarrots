package com.lc.sk.inventory.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.lc.sk.inventory.entities.ProductQuantities;
import com.lc.sk.inventory.util.Queries;

public interface ProductQuantitiesRepository  extends CrudRepository <ProductQuantities, Long> {

	@Query(value=Queries.queryforproductquantity, nativeQuery=true)
	public List<ProductQuantities> getProductQuantityWhichNotInProductTable();
	
	@Query(value="select custid,quantity,sizeid from productquantities where custid=:custid", nativeQuery=true)
	public Optional<ProductQuantities> getcustid(@Param("custid") Long custid);
	
	@Query(value="select custid,quantity,sizeid from productquantities", nativeQuery=true)
	public List<ProductQuantities> getallcustid();
}

