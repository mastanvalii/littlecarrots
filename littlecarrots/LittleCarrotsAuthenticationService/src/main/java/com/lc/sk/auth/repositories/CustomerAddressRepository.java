package com.lc.sk.auth.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.lc.sk.auth.entities.CustomerAddress;

public interface CustomerAddressRepository extends CrudRepository<CustomerAddress,Long>{

	@Query(value="select addressid,name,email,flat,streetaddress,landmark,pincode,city,state,country,mobile1,mobile2,addresstype from customeraddress", nativeQuery=true)
	public List<CustomerAddress> getAllAddress();
	
	@Query(value="select addressid,name,email,flat,streetaddress,landmark,pincode,city,state,country,mobile1,mobile2,addresstype from customeraddress where email=:email ",nativeQuery=true)
	public List<CustomerAddress> getAdressByEmail(@Param("email")String email);
	
	@Query(value="select addressid,name,email,flat,streetaddress,landmark,pincode,city,state,country,mobile1,mobile2,addresstype from customeraddress where addressid=:addressid ",nativeQuery=true)
	public Optional<CustomerAddress> getAdressById(@Param("addressid")Long addressid);
}
