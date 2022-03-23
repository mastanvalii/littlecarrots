package com.little.carrots.order.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.little.carrots.order.entity.Invoicespdf;



public interface InvoicespdfRepository extends JpaRepository<Invoicespdf,Long> {
	
	@Query (value ="  select filename from invoicespdf where inid=:inid ",nativeQuery=true)
	public String getAllInid(@Param("inid")long inid);

}
