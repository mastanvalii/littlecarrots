package com.lc.sk.auth.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.lc.sk.auth.entities.EmailSubscribe;

public interface EmailSubscribeRepository extends CrudRepository<EmailSubscribe, String> {
	
	@Query(value="select * from emailsubscribe", nativeQuery=true)
	public List<EmailSubscribe> getAllEmails();
	
	@Query(value="select * from emailsubscribe where emailid=:emailid", nativeQuery=true)
	public Optional<EmailSubscribe> getAllEmailsBYID(@Param("emailid")String emailid);
	
}
