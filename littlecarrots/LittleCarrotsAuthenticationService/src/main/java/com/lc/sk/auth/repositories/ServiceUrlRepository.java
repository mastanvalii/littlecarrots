/**
 * 
 */
package com.lc.sk.auth.repositories;

import org.springframework.data.repository.CrudRepository;

import com.lc.sk.auth.entities.ServiceURLs;


/**
 * @author Mastanvali Shaik
 * LittleCarrotsAuthenticationService
 * 2020
 */
public interface ServiceUrlRepository extends CrudRepository<ServiceURLs, String> {

}
