package com.little.carrots.order.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.little.carrots.order.entity.Packageshipping;


public interface PackageshippingRepository extends CrudRepository<Packageshipping, Long>{

	@Query (value ="select * from packageshipping",nativeQuery=true)
	public List<Packageshipping> getPackageshippingList();
	
	@Query (value ="select * from packageshipping where sid=:sid",nativeQuery=true)
	public Optional<Packageshipping> getPackageshippingListBySid(@Param("sid")Long sid);
	
	@Query (value ="select * from packageshipping where orderitemsid=:orderitemsid",nativeQuery=true)
	public Optional<Packageshipping> getPackageshippingListByOrderItemsID(@Param("orderitemsid")Long orderitemsid);
	
	@Query (value ="select * from packageshipping where orderid=:orderid",nativeQuery=true)
	public List<Packageshipping> getPackageshippingListByOrderID(@Param("orderid")Long orderid);
	
	
}
