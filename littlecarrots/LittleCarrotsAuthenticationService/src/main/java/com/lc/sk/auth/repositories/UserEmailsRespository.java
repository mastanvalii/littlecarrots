package com.lc.sk.auth.repositories;

import org.springframework.data.repository.CrudRepository;

import com.lc.sk.auth.entities.UserEmails;

public interface UserEmailsRespository extends CrudRepository<UserEmails, Long> {

}
