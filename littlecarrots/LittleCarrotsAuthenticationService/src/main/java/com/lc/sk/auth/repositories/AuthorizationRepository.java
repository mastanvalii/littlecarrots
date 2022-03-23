/**
 * 
 */
package com.lc.sk.auth.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.lc.sk.auth.entities.Authorization;


/**
 * @author Mastanvali Shaik
 * LittleCarrotsAuthenticationService
 * 2020
 */
public interface AuthorizationRepository extends CrudRepository<Authorization, String> {

	@Query(value="select rolename, descriptions, authorization_status from authorization", nativeQuery=true)
	public List<Authorization> getAllAuthorization();

	@Query(value="select rolename, descriptions, authorization_status from authorization where rolename=:rolename", nativeQuery=true)
	public Optional<Authorization> getAuthorizationsById(@Param("rolename")String rolename);	
	
}
