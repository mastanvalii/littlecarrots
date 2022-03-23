package com.lc.sk.inventory.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lc.sk.inventory.entities.Selectedproducts;

public interface SelectedproductsRepository extends JpaRepository<Selectedproducts,Long> {

	@Query(value="select spid,genderid,productid,startdate,enddate from selectedproducts where startdate < NOW() AND enddate > NOW(); ",nativeQuery=true)
	public List<Selectedproducts> getallSelectedproducts();
	
	@Query(value="select spid,genderid,productid,startdate,enddate from selectedproducts where startdate < NOW() AND enddate > NOW() and spid=:spid ;",nativeQuery=true)
	public Optional<Selectedproducts> getSelectedproductsById(@Param("spid")Long spid);
	
	@Query(value="select spid,genderid,productid,startdate,enddate from selectedproducts where enddate<now()",nativeQuery=true)
	public List<Selectedproducts> getSelectedexpiry();
	
	@Query(value="select spid,genderid,productid,startdate,enddate from selectedproducts where startdate < NOW() AND enddate > NOW() AND genderid= :gender",nativeQuery=true)
	public List<Selectedproducts> getSelectedProductsByGender(@Param("gender") String gender);
	
	@Query(value="select spid,genderid,productid,startdate,enddate from selectedproducts where startdate < NOW() AND enddate > NOW() limit 4 ",nativeQuery=true)
	public List<Selectedproducts> getallSelectedproducts4();

}
