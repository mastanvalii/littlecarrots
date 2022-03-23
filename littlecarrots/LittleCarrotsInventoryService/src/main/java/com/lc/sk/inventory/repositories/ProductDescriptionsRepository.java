package com.lc.sk.inventory.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.lc.sk.inventory.entities.ProductDescriptions;
import com.lc.sk.inventory.util.Queries;

public interface ProductDescriptionsRepository extends CrudRepository<ProductDescriptions, Long> {

	@Query(value=Queries.queryforproductdescritpion, nativeQuery=true)
	public List<ProductDescriptions> getProductDescriptionsWhichNotInProductTable();
	
	@Query(value="select descriptionid,description from productdescriptions where descriptionid=:descriptionid", nativeQuery=true)
	public Optional<ProductDescriptions> getdescbyid(@Param("descriptionid") Long descriptionid);
	
	@Query(value="select descriptionid,description from productdescriptions", nativeQuery=true)
	public List<ProductDescriptions> getalldesc();
}
