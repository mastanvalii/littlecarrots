package com.lc.sk.inventory.security.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.lc.sk.inventory.security.entities.Notifications;



public interface NotificationsRepository extends CrudRepository<Notifications, Long> {
	
	@Query(value="select * from notifications where  delaydate = curdate()",nativeQuery=true)
	public List<Notifications> getNotification();

}
