package com.little.carrots.order.repositories;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.little.carrots.order.entity.Coupons;



public interface CouponsRepository extends CrudRepository<Coupons, Long> {
	
	//
//	create table coupons(
//			couponid bigint auto_increment,
//			coupon varchar(200),
//			startdate DATEtime,
//			enddate DATEtime,
//			discount int,
//			minbill int,
//			title varchar(200),
//			constraint coupons_pk primary key (couponid)
//			);
	
	@Query(value="select couponid,coupon,startdate,enddate,discount,minbill,title from coupons where coupon=:coupon", nativeQuery=true)
	public Optional<Coupons> getByCoupons(@Param("coupon")String coupon);

	@Query(value="select couponid,coupon,startdate,enddate,discount,minbill,title from coupons", nativeQuery=true)
	public List<Coupons> getAllCouponsData();
	
	
}
