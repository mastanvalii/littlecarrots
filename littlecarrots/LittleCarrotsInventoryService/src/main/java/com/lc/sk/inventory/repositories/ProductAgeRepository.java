package com.lc.sk.inventory.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.lc.sk.inventory.entities.ProductAge;

public interface ProductAgeRepository extends CrudRepository<ProductAge, String> {

	@Query(value="select ageid,des from productage where ageid=:ageid", nativeQuery=true)
	public Optional<ProductAge> getbyageid(@Param("ageid") String ageid);
	
	@Query(value="select ageid,des from productage", nativeQuery=true)
	public List<ProductAge> getallages();
	
}
