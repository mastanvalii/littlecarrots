package com.lc.sk.inventory.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.lc.sk.inventory.entities.CollectionsaleImages;

public interface CollectionsaleImagesRepository extends JpaRepository<CollectionsaleImages,Long> {

	@Query(value="select imageid,id,icon,mobileview1,mobileview2,desktopview1,desktopview2 from collectionsaleimages",nativeQuery=true)
	public List<CollectionsaleImages> getCollectionsaleImages();
	
	@Query(value="select imageid,id,icon,mobileview1,mobileview2,desktopview1,desktopview2 from collectionsaleimages where imageid=:imageid",nativeQuery=true)
	public Optional<CollectionsaleImages> getCollectionsaleImagebyid(@Param("imageid")Long imageid);
	
	@Query(value="select imageid,id,icon,mobileview1,mobileview2,desktopview1,desktopview2 from collectionsaleimages where id=:id",nativeQuery=true)
	public CollectionsaleImages getCollectionsaleImagesByIDS(@Param("id")Long id);

	
}
