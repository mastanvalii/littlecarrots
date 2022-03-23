package com.lc.sk.inventory.repositories;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lc.sk.inventory.entities.Collectionsaleprod;

public interface CollectionsaleprodRepository extends JpaRepository<Collectionsaleprod,Long> {
	
	@Query(value="select csid,id,productid from collectionsaleprod",nativeQuery=true)
	public List<Collectionsaleprod> getCollectionsaleprod();
	
	@Query(value="select csid,id,productid from collectionsaleprod where id=:id",nativeQuery=true)
	public List<Collectionsaleprod> getCollectionsaleprodById(@Param("id")Long id);
	
	@Query(value="select csid,id,productid from collectionsaleprod where csid=:csid",nativeQuery=true)
	public Collectionsaleprod getCollectionsaleprodBySerialId(@Param("csid")Long csid);
	
	
	
	

}
