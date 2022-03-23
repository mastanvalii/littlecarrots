package com.lc.sk.inventory.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import com.lc.sk.inventory.entities.Combos;

public interface CombosRepository extends CrudRepository<Combos, Long>{
	
	@Query(value="select * from combos where startdate<now() and enddate>now()",nativeQuery=true)
	public List<Combos> getCombos();
	
	@Query(value="select *  from combos where combos.comboid not in (select comboid from combos_productdetails)",nativeQuery=true)
	public List<Combos> getComboidwithoutproductdetails();
}
