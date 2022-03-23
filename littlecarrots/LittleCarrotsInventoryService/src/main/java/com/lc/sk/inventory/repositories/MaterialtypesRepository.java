package com.lc.sk.inventory.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


import com.lc.sk.inventory.entities.Materialtypes;

public interface MaterialtypesRepository extends CrudRepository<Materialtypes,Long>  {


	@Query( value="select materialid, materialname, description, occasionid, seasonid, catid from materialtype", nativeQuery = true)
	public List<Materialtypes> getAllMaterialTypeDetails();
	

	@Query( value="select materialid, materialname, description, occasionid, seasonid, catid from materialtype where materialid=:materialid", nativeQuery = true)
	public Optional<Materialtypes> getMaterialTypeById(@Param("materialid")Long materialid);
		

}
