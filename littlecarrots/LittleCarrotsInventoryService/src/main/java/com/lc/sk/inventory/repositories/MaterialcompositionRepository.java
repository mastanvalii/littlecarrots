package com.lc.sk.inventory.repositories;

import java.util.*;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.lc.sk.inventory.entities.Materialcomposition;

public interface MaterialcompositionRepository extends CrudRepository<Materialcomposition,Long>{

	@Query(value="select materialid, description from materialcomposition", nativeQuery=true)
	public List<Materialcomposition> getAllMaterialComposition();
	
	@Query(value="select materialid, description from materialcomposition where materialid=:materialid", nativeQuery=true)
	public Optional<Materialcomposition> getMaterialCompositionById(@Param("materialid")Long materialid);
	
}

