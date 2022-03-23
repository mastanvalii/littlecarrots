/**
 * 
 */
package com.lc.sk.auth.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


import com.lc.sk.auth.entities.SellerUsers;

/**
 * @author Mastanvali Shaik
 * LittleCarrotsAuthenticationService
 * 2020
 */
public interface SellerUserRepository extends CrudRepository<SellerUsers, String>{
	
	@Query(value="select username, password, email, sellerid, fullname, status, rolename, lastlogin from sellerusers", nativeQuery=true)
	public List<SellerUsers> getAllSellerUser();

	@Query(value=" select username, password, email, sellerid, fullname, status, rolename, lastlogin from sellerusers where username=:username", nativeQuery=true)
	public Optional<SellerUsers> getByUsername(@Param("username")String username);	

}
