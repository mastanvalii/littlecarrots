package com.little.carrots.order.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.little.carrots.order.entity.OrderItems;

@Repository
public interface OrderItemsRepository extends CrudRepository<OrderItems, Long>{

	@Query(value="select * from orderitems",nativeQuery=true)
	public List<OrderItems> getAllOrderItems();
	
	@Query(value="select * from orderitems where orderitemsid=:orderitemsid",nativeQuery=true)
	public Optional<OrderItems> getAllOrderItemId(@Param("orderitemsid") Long orderitemsid);
	
	@Query(value="select * from orderitems where orderid=:orderid",nativeQuery=true)
	public List<OrderItems> getAllOrders(@Param("orderid") Long orderid);
	
//	@Modifying
//	@Query(value="delete from orderitems where orderid=:orderid", nativeQuery=true)
//	public int deletebyOrderId(@Param("orderid")Long orderid);
	
	
	
	
}
