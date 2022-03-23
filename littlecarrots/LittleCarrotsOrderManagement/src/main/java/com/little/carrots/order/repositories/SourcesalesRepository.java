package com.little.carrots.order.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.little.carrots.order.entity.Sourcesales;

public interface SourcesalesRepository extends JpaRepository<Sourcesales,Long> {

	@Query (value ="select * from sourcesales",nativeQuery=true)
	public List<Sourcesales> getAllSources();
}
