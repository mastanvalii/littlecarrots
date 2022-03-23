package com.little.carrots.order.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.little.carrots.order.entity.Soldproductstatus;




public interface SoldproductstatusRepository extends JpaRepository<Soldproductstatus,Long>{
	
	@Query (value ="select * from soldproductstatus",nativeQuery=true)
	public List<Soldproductstatus> getAllStatus();

}
