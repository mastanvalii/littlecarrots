/**
 * 
 */
package com.lc.sk.inventory.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import com.lc.sk.inventory.entities.Batch;

/**
 * @author Mastanvali Shaik
 * LittleCarrotsInventoryService
 * 2020
 */
public interface BatchRepository extends CrudRepository<Batch, Long> {

	@Query(value="select a.batchid, a.WarehouseID, a.DateofPurchase, a.purchasedby, a.InvAmount, a.WhoInserted, a.DateInsertion, a.Status, a.isocode, a.QTY, a.Sellerid  from batch a", nativeQuery=true)
	public List<Batch> getAllBatches();
	
	@Query(value="select a.batchid, a.WarehouseID, a.DateofPurchase, a.purchasedby, a.InvAmount, a.WhoInserted, a.DateInsertion, a.Status, a.isocode, a.QTY, a.Sellerid  from batch a where a.batchid=:batchid", nativeQuery=true)
	public Optional<Batch> getAllBatchesbyId(@Param("batchid")Long batchid);
	
}
