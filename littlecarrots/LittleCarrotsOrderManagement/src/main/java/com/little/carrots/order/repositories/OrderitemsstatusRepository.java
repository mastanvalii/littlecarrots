package com.little.carrots.order.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.little.carrots.order.entity.Orderitemsstatus;

@Repository
public interface OrderitemsstatusRepository extends CrudRepository<Orderitemsstatus, Long> {

	@Query(value="select itemstatusid,orderitemsid,orderid,ordercodeid,ondate,information from orderitemsstatus where orderid=:orderid" ,nativeQuery=true)
	public List<Orderitemsstatus> getAllOrderStatus(@Param("orderid")Long orderid);
	
	@Query(value="select itemstatusid,orderitemsid,orderid,ordercodeid,ondate,information from orderitemsstatus where orderitemsid=:orderitemsid" ,nativeQuery=true)
	public List<Orderitemsstatus> getAllOrderStatusBYOrderitemsId(@Param("orderitemsid")Long orderitemsid);
}
