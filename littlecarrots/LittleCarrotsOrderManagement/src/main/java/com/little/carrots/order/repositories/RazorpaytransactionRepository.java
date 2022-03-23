package com.little.carrots.order.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


import com.little.carrots.order.entity.Razorpaytransaction;

public interface RazorpaytransactionRepository extends CrudRepository<Razorpaytransaction, String> {

	
	@Query (value ="select * from razorpaytransaction ",nativeQuery=true)
	public List<Razorpaytransaction> getalltransactions();
	
	@Query (value ="select * from razorpaytransaction where razorpay_order_id=:razorpay_order_id ",nativeQuery=true)
	public Optional<Razorpaytransaction> getalltransactionsbyId(@Param("razorpay_order_id")String razorpay_order_id);
	
	@Query (value ="select * from razorpaytransaction where orderid=:orderid ",nativeQuery=true)
	public Optional<Razorpaytransaction> getalltransactionsbyOrderID(@Param("orderid")String orderid);

}
