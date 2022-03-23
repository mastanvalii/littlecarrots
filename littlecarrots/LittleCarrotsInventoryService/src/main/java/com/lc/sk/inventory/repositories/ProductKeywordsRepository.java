package com.lc.sk.inventory.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.lc.sk.inventory.entities.ProductKeywords;


public interface ProductKeywordsRepository extends JpaRepository<ProductKeywords,Long> {

	@Query(value="select keywordid,productid,keywords from productkeywords where productid=:productid ",nativeQuery=true)
	public Optional<ProductKeywords> getProductKeywordsbypid(@Param("productid")Long productid);
	
	
	
	
	
	
	
}
