/**
 * 
 */
package com.lc.sk.inventory.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.lc.sk.inventory.entities.Warehouses;

/**
 * @author Mastanvali Shaik
 * LittleCarrotsInventoryService
 * 2020
 */
public interface WarehousesRepository extends CrudRepository<Warehouses, Long> {
	
	@Query(value="select warehouseid,warehousename,ContactPersonName,ContactPhone,Email,Address,PinCode,EST,warehouse_Status,WhoCreated,DateofCreation,WhoUpdated,ModifyDate,isocode from warehouses", nativeQuery=true)
	public List<Warehouses> getAllWarehouses();
	
	
	@Query(value="select warehouseid,warehousename,ContactPersonName,ContactPhone,Email,Address,PinCode,EST,warehouse_Status,WhoCreated,DateofCreation,WhoUpdated,ModifyDate,isocode from warehouses where warehouseid=:warehouseid", nativeQuery=true)
	public Optional<Warehouses> getWarehouseById(@Param("warehouseid")long warehouseid);	
}
