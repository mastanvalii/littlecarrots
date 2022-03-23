/**
 * 
 */
package com.lc.sk.inventory.security.repositories;

import org.springframework.data.repository.CrudRepository;

import com.lc.sk.inventory.security.entities.SecurityKeyManagement;

/**
 * @author Mastanvali Shaik
 * LittleCarrotsInventorySecurityService
 * 2020
 */
public interface SecurityKeyManagementRepository extends CrudRepository<SecurityKeyManagement, Long> {

}
