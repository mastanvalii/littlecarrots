package com.little.carrots.order.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.little.carrots.order.entity.Ordercodes;


public interface OrdercodesRepository extends CrudRepository<Ordercodes, Long> {


	@Query(value="select * from ordercodes",nativeQuery=true)
	public List<Ordercodes> getOrdercodes();
	
	@Query(value="select * from ordercodes where ordercodeid=:ordercodeid",nativeQuery=true)
	public Optional<Ordercodes> getOrdercodesByOrderCodeid(@Param("ordercodeid")Long ordercodeid);
}
