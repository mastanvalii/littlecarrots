package com.lc.sk.inventory.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lc.sk.inventory.entities.Badges;

@Repository
public interface BadgesRepository extends JpaRepository<Badges, String> {

	@Query(value="select badge, description from badges", nativeQuery=true)
	public List<Badges> getAllBadges();

	@Query(value="select  badge, description from badges where badge=:badge", nativeQuery=true)
	public Optional<Badges> getByBadge(@Param("badge")String badge);
}