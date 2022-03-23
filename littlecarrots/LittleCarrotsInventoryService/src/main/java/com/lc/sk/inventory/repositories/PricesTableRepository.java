package com.lc.sk.inventory.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.lc.sk.inventory.entities.PricesTable;

public interface PricesTableRepository extends CrudRepository<PricesTable,Long> {
	 
// TODO Remove unused code found by UCDetector
// 	@Query( value ="select a.priceid,a.productid,a.mrp,a.lcprice,a.tax,a.sellingprice,a.discount,a.finalprice from pricestable a WHERE a.productid=:productid", nativeQuery = true)
// 		PricesTable find(@Param("productid") Long productid);
	
	@Query(value="select priceid,productid,mrp,lcprice,tax,sellingprice,discount,finalprice from pricestable where priceid=:priceid", nativeQuery = true)
	public Optional<PricesTable> getptabid(@Param("priceid") Long priceid);
	
	@Query(value="select priceid,productid,mrp,lcprice,tax,sellingprice,discount,finalprice from pricestable", nativeQuery = true)
	public List<PricesTable> getallptab();

}
