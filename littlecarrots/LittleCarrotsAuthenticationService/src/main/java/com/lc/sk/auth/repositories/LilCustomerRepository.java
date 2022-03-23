package com.lc.sk.auth.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.lc.sk.auth.entities.LilCustomer;



public interface LilCustomerRepository extends CrudRepository<LilCustomer, Long>{

	@Query(value="select userid,uniqueid,email,firstname,lastname,imageurl,phone,usertype,status  from lilcustomer", nativeQuery=true)
	public List<LilCustomer> findAll();

	@Query(value="select userid,uniqueid,email,firstname,lastname,imageurl,phone,usertype,status  from lilcustomer where email=:email", nativeQuery=true)
	public Optional<LilCustomer> findByEmail(@Param("email")String email);
	
	@Query(value="select userid,uniqueid,email,firstname,lastname,imageurl,phone,usertype,status  from lilcustomer where uniqueid=:uniqueid", nativeQuery=true)
	public Optional<LilCustomer> findByUniqueid(@Param("uniqueid")String uniqueid);
	
	@Query(value="select userid,uniqueid,email,firstname,lastname,imageurl,phone,usertype,status  from lilcustomer where uniqueid=:uniqueid", nativeQuery=true)
	public Optional<LilCustomer> findByphone(@Param("uniqueid")String uniqueid);
	
	@Query(value="select userid,uniqueid,email,firstname,lastname,imageurl,phone,usertype,status  from lilcustomer",nativeQuery = true)
	public List<LilCustomer> getAllCustomersListPaging(Pageable pageable);
	
	@Query(value="select userid,uniqueid,email,firstname,lastname,imageurl,phone,usertype,status  from lilcustomer", nativeQuery=true)
	public Page<LilCustomer> getallcustomerspagingcount(Pageable pageable);
	
	@Query(value="select *  from lilcustomer where email=:email and phone=:phone", nativeQuery=true)
	public Optional<LilCustomer> EmailValidation(@Param("email")String email,@Param("phone")long phone);
	
	
	
}
