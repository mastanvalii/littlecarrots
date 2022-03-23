/**
 * 
 */
package com.lc.sk.inventory.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.lc.sk.inventory.entities.Countries;

/**
 * @author Mastanvali Shaik
 * LittleCarrotsInventoryService
 * 2020
 */
public interface CountriesRepository extends CrudRepository<Countries, String> {

	
	@Query(value="select isocode, country, isdcode, status from countries", nativeQuery=true)
	public List<Countries> getAllCountries();
	
	@Query(value="select isocode, country, isdcode, status from countries where isocode=:isocode", nativeQuery=true)
	public Optional<Countries> getCountryById(@Param("isocode")String isocode);
	
	@Query(value="select isocode, country, isdcode, status from countries where status=true", nativeQuery=true)
	public List<Countries> getActiveCountries();	
	
}
