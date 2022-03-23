/**
 * 
 */
package com.lc.sk.inventory.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.lc.sk.inventory.entities.SellerToWarehouse;

/**
 * @author Mastanvali Shaik
 * LittleCarrotsInventoryService
 * 2020
 */
public interface SellerToWarehouseRepository extends CrudRepository<SellerToWarehouse, Long> {
	
	@Query(value="select serialid,warehouseid,sellerid from sellertowarehouse",nativeQuery=true)
	public List<SellerToWarehouse> getAllSellertoWarehouse();
	

}
