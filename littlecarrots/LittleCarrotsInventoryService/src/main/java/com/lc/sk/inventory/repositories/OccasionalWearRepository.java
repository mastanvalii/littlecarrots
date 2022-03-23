package com.lc.sk.inventory.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.lc.sk.inventory.entities.OccasionalWear;

public interface OccasionalWearRepository extends CrudRepository<OccasionalWear,Integer> {
	
	@Query(value="select occasionid,occaName,subcatid from occasionalwear", nativeQuery=true)
	public List<OccasionalWear> getallocc();
	
	@Query(value="select occasionid,occaName,subcatid from occasionalwear where occasionid=:occasionid", nativeQuery=true)
	public Optional<OccasionalWear> getoccbyid(@Param("occasionid") Integer occasionid);

}
