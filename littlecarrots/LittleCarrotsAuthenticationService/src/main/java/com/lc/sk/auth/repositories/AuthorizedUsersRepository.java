/**
 * 
 */
package com.lc.sk.auth.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


import com.lc.sk.auth.entities.AuthorizedUsers;

/**
 * @author Mastanvali Shaik
 * LittleCarrotsAuthenticationService
 * 2020
 */
public interface AuthorizedUsersRepository extends CrudRepository<AuthorizedUsers, String> {
	@Query(value="select username, email, password, authuserstatus, rolename, lastlogindate from authorizedusers", nativeQuery=true)
	public List<AuthorizedUsers> getAllAuthorizedUser();

	@Query(value="select username, email, password, authuserstatus, rolename, lastlogindate from authorizedusers where username=:username", nativeQuery=true)
	public Optional<AuthorizedUsers> getAuthUsersByUsername(@Param("username")String username);	
	
	
	
}
