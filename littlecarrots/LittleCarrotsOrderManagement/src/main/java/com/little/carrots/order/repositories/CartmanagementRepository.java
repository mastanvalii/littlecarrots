package com.little.carrots.order.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.little.carrots.order.entity.Cartmanagement;

public interface CartmanagementRepository extends JpaRepository<Cartmanagement,Long>{

	@Query(value="select cartid,customerid,productid,productprice,quantity,cartdate,totalprice from cart ",nativeQuery=true)
	public List<Cartmanagement> getAllCart();


	@Query(value="select cartid,customerid,productid,productprice,quantity,cartdate,totalprice from cart where cartid=:cartid ",nativeQuery=true)
	public Optional<Cartmanagement> getCartById(@Param("cartid")Long cartid);


	@Query(value="select cartid,customerid,productid,productprice,quantity,cartdate,totalprice from cart where customerid=:customerid ",nativeQuery=true)
	public List<Cartmanagement> getByCustomerId(@Param("customerid")Long customerid);

	@Query(value="select cartid,customerid,productid,productprice,quantity,cartdate,totalprice from cart where cartid=:cartid ",nativeQuery=true)
	public Optional<Cartmanagement> deleteByCartid(@Param("cartid")Long cartid);


	@Query(value="select cartid,customerid,productid,productprice,quantity,cartdate,totalprice from cart where cartid=:cartid and productid=:productid and customerid=:customerid",nativeQuery=true)
	public Optional<Cartmanagement> deleteByCustomerid(@Param("cartid")Long cartid,@Param("productid")Long productid,@Param("customerid")Long customerid );


	

	
	

}
