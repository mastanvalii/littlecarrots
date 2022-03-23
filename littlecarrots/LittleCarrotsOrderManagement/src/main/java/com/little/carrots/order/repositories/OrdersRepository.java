package com.little.carrots.order.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.little.carrots.order.entity.Orders;
@Repository
public interface OrdersRepository extends CrudRepository<Orders, Long>{

	@Query (value ="select * from orders where ordercodeid=:ordercodeid order by orderdate desc",nativeQuery=true)
	public List<Orders> getOrdersList(@Param("ordercodeid")Long ordercodeid);
	
	@Query (value ="select * from orders where orderid=:orderid",nativeQuery=true)
	public Optional<Orders> getOrdersListByOrderid(@Param("orderid")Long orderid);
	
	@Query (value ="select * from orders where customerid=:customerid order by orderid desc",nativeQuery=true)
	public List<Orders> getOrdersListByCustomerid(@Param("customerid")Long customerid);
	
	@Query(value="select orderid from orders where ordercodeid=:ordercodeid and customerid=:customerid",nativeQuery=true)
	public Long getId(@Param("ordercodeid")Long paymenttransactionid,@Param("customerid")Long customerid);
	
	
	@Query(value="select orderid from orders where ordercodeid=:ordercodeid and customerid=:customerid",nativeQuery=true)
	public List<Long> getIds(@Param("ordercodeid")Long paymenttransactionid,@Param("customerid")Long customerid);
	
	
	
	
	
	
	
	
}
