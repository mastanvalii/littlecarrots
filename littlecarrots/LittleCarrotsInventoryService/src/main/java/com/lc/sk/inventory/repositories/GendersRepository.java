package com.lc.sk.inventory.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.lc.sk.inventory.entities.Genders;

public interface GendersRepository extends CrudRepository<Genders,String> {

	@Query(value="select genderid, Gender from genders", nativeQuery=true)
	public List<Genders> getAllGenders();

	@Query(value="select genderid, Gender from genders where genderid=:genderid", nativeQuery=true)
	public Optional<Genders> getGendersById(@Param("genderid")String genderid);	
}
