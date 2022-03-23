package com.little.carrots.order.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;


import com.little.carrots.order.entity.Coupons;

public interface CouponsPaginationRepository  extends PagingAndSortingRepository<Coupons, Long> {

	@Query(value="select * from coupon ", nativeQuery=true)
	List<Coupons> getAllDetails(Pageable pageable );
	
	@Query(value="select * from coupon ", nativeQuery=true)
	Page<Coupons> getPageCount(Pageable pageable );	
	
}
