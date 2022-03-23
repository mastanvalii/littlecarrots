package com.little.carrots.order.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.little.carrots.order.entity.Couponused;

public interface CouponusedRepository extends CrudRepository<Couponused, Long> {

	
	/*
	create table couponused(
			couponusedid bigint auto_increment,
		    couponid bigint,
		    customerid bigint,
		    constraint couponused_pk primary key (couponusedid),
		    constraint couponused_fk foreign key (couponid) references coupons(couponid)
		    );
	*/
	
	
	@Query(value="select couponusedid,couponid,customerid from couponused where couponid=:couponid", nativeQuery=true)
	public List<Couponused> getCouponusedByCouponID(@Param("couponid")Long couponid);
	
	@Query(value="select couponusedid,couponid,customerid from couponused where customerid=:customerid",nativeQuery=true)
	public List<Couponused> getCouponusedByCustomerID(@Param("customerid")Long customerid);
	
	
	
}
