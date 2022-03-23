package com.lc.sk.inventory.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.lc.sk.inventory.entities.ProductType;

public interface ProductTypeRepository extends CrudRepository<ProductType, Long> {
	
	@Query(value = "Select protypeid,subcatid,producttypename from producttype where protypeid=:protypeid", nativeQuery=true)
	public Optional<ProductType> getid(@Param("protypeid") Long protypeid);
	
	@Query(value = "Select protypeid,subcatid,producttypename from producttype", nativeQuery=true)
	public List<ProductType> getall();

}
