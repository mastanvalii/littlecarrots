package com.lc.sk.auth.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.lc.sk.auth.entities.Contest;



public interface ContestRepository extends CrudRepository<Contest,String>{
	
	@Query(value="select * from contest where emailid=:emailid", nativeQuery=true)
	public Optional<Contest> getByEmailId(@Param("emailid")String emailid);	
	
	@Query(value="select * from contest where instaid=:instaid", nativeQuery=true)
	public Optional<Contest> getByInstaId(@Param("instaid")String instaid);	
	
	@Query(value="select * from contest where phone=:phone", nativeQuery=true)
	public Optional<Contest> getByPhone(@Param("phone")long phone);	
	
	@Query(value="select * from contest ", nativeQuery=true)
	public List<Contest> getAll();	
	

	
}
