package com.lc.sk.inventory.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lc.sk.inventory.entities.Displaytype;

@Repository
public interface DisplaytypeRepository extends JpaRepository<Displaytype, String> {

	@Query(value="select display, description from displaytype", nativeQuery=true)
	public List<Displaytype> getAllDisplays();
	
	@Query(value="select display, description from displaytype where display=:display", nativeQuery=true)
	public Optional<Displaytype> getAllDisplaysbyid(@Param(value="display") String display);
	
	
}
