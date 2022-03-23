/**
 * 
 */
package com.lc.sk.inventory.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lc.sk.inventory.bean.SizesDetails;
import com.lc.sk.inventory.entities.Sizes;



@Repository
public interface SizesRepository extends JpaRepository<Sizes, Long> {

// TODO Remove unused code found by UCDetector
// 	@Query(value="select a.sizeid, a.ageid, a.sizeno, a.height, a.weight, a.chest, a.waist, a.hip, a.gender,a.subcatid, a.isocode from sizes a where a.ageid=:ageid and a.gender=:gender and a.subcatid=:subcatid", nativeQuery=true)
// 	Sizes find(@Param("ageid") String ageid, @Param("gender") String gender, @Param("subcatid") String subcatid);
	
	@Query(value="select a.sizeid,a.ageid,a.sizeno,a.height,a.weight,a.chest,a.waist,a.hip,a.gender,CONCAT(a.subcatid,'[',b.categoryname,']')as subcategory,a.isocode from sizes a, subcategories b where a.subcatid=b.subcatid",nativeQuery=true)
	public List<SizesDetails> getDetails();
	
	@Query(value="select sizeid,ageid,sizeno,height,weight,chest,waist,hip,gender,subcatid,isocode from sizes",nativeQuery=true)
	public List<Sizes> getAllSizes();	
	
	@Query(value="select sizeid,ageid,sizeno,height,weight,chest,waist,hip,gender,subcatid,isocode from sizes where sizeid=:sizeid",nativeQuery=true)
	public Optional<Sizes> getSizesById(@Param("sizeid")long sizeid);	
}

