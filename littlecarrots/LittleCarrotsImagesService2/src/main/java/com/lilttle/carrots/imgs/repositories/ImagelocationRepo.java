package com.lilttle.carrots.imgs.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lilttle.carrots.imgs.entity.Imagelocation;
import com.lilttle.carrots.imgs.util.Queries;

@Repository
public interface ImagelocationRepo extends JpaRepository<Imagelocation, Long> {

	 @Query( value = Queries.GET_QUERY1, nativeQuery = true)
	 public List<Imagelocation> getInfo1(@Param("productid") Long productid);
	 
	 @Query( value = Queries.GET_QUERY2, nativeQuery = true)
	 public Optional<Imagelocation> getInfo2(@Param("productid") Long productid);	 
	 
	 @Query( value = Queries.GET_QUERY3, nativeQuery = true)
	 public List<Long> getInfo3();	 
	 
	 @Query( value = Queries.GET_QUERY4, nativeQuery = true)
	 public List<Imagelocation> getAllInfo1();

}
