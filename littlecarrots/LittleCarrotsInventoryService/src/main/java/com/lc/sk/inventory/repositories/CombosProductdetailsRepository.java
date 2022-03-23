package com.lc.sk.inventory.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import com.lc.sk.inventory.entities.CombosProductdetails;

public interface CombosProductdetailsRepository extends CrudRepository<CombosProductdetails,Long>{

	@Query(value="select * from combos_productdetails",nativeQuery=true)
	public List<CombosProductdetails> getComboProductDetails();
}
