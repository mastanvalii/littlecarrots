package com.little.carrots.order.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.little.carrots.order.entity.Soldproducts;



public interface SoldproductsRepository extends JpaRepository<Soldproducts,Long> {
	
	@Query (value ="select * from soldproducts",nativeQuery=true)
	public List<Soldproducts> getAllSoldproducts();

}
