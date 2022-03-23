package com.lc.sk.inventory.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.lc.sk.inventory.entities.Patterns;

public interface PatternsRepository  extends JpaRepository <Patterns, Long> {

// TODO Remove unused code found by UCDetector
// 	@Query(value="select a.patid, a.description, a.productid from patterns a where a.productid=:productid", nativeQuery=true)
// 	Patterns find(@Param("productid")Long productid);
	
	@Query(value="select patid, description, productid from patterns where patid=:patid",nativeQuery = true)
	public Optional<Patterns> getpatid(@Param("patid") Long patid);
	
	@Query(value="select patid, description, productid from patterns",nativeQuery = true)
	public List<Patterns> getallpat();
	
}
