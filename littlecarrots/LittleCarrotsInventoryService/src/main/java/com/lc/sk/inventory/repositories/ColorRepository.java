package com.lc.sk.inventory.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import com.lc.sk.inventory.entities.Color;

public interface ColorRepository extends CrudRepository<Color, Long> {

	
	@Query(value="select colorid, colorname, hashcode from color", nativeQuery=true)
	public List<Color> getAllColors();

	@Query(value="select colorid, colorname, hashcode from color where colorid=:colorid", nativeQuery=true)
	public Optional<Color> getColorsById(@Param("colorid")Long colorid);
	
}
