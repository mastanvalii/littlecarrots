/**
 * 
 */
package com.lc.sk.auth.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.lc.sk.auth.entities.Lcprops;
import com.lc.sk.auth.entities.SellerUsers;

/**
 * @author Mastanvali Shaik
 * LittleCarrotsAuthenticationService
 * 2020
 */
public interface LcpropsRepository extends CrudRepository<Lcprops, Long> {

	@Query(value="select propid, prop, status from lcprops", nativeQuery=true)
	public List<Lcprops> getAll();

	@Query(value=" select propid, prop, status from lcprops where propid=:id", nativeQuery=true)
	public Optional<Lcprops> getById(@Param("id")Long id);	
	
}
