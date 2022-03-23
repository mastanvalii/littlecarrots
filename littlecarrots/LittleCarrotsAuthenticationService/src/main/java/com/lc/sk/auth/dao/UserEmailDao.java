package com.lc.sk.auth.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lc.sk.auth.entities.UserEmails;
import com.lc.sk.auth.repositories.UserEmailsRespository;

@Component
public class UserEmailDao {

	private @Autowired
	UserEmailsRespository useremails;
	
// TODO Remove unused code found by UCDetector
// 	public Optional<UserEmails> getByEmailId(long id) {
// 		return useremails.findById(id);
// 	}
	
	
	public List<UserEmails> getAll(){
		return (List<UserEmails>) useremails.findAll();
	}
	
// TODO Remove unused code found by UCDetector
// 	public void insertNewMail(UserEmails email) {
// 		System.err.println(email);
// 		useremails.save(email);
// 	}
}
