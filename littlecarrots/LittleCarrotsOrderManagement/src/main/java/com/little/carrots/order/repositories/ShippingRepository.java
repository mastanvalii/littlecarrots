package com.little.carrots.order.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.little.carrots.order.entity.Shipping;

@Repository
public interface ShippingRepository extends CrudRepository<Shipping,Long> {
	
	@Query(value="Select * from shipping",nativeQuery=true)
	public List<Shipping> getAllShipping();

	@Query(value="Select * from shipping where customerid=:customerid",nativeQuery=true)
	public Optional<Shipping> getByCustomerId(@Param("customerid")Long customerid);

	@Query(value="Select * from shipping where courierid=:courierid",nativeQuery=true)
	public Optional<Shipping> getByCourierId(@Param("courierid")Long courierid);

}
