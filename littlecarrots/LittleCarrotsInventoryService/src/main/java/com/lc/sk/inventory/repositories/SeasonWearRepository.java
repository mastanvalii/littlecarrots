package com.lc.sk.inventory.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.lc.sk.inventory.entities.SeasonWear;

public interface SeasonWearRepository extends CrudRepository<SeasonWear, Long> {
	
	@Query(value="select seasonid,subcatid,season from seasonwear",nativeQuery=true)
	public List<SeasonWear> getAllSeasons();

	@Query(value="select seasonid,subcatid,season from seasonwear where seasonid=:seasonid",nativeQuery=true)
	public Optional<SeasonWear> getSeasonById(@Param("seasonid")long seasonid);
	
}
