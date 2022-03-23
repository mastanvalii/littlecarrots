package com.lc.sk.inventory.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lc.sk.inventory.bean.CollectionSalePack;
import com.lc.sk.inventory.bean.CollectionSalewithProductIds;
import com.lc.sk.inventory.entities.Collectionsale;

public interface CollectionsaleRepository extends JpaRepository<Collectionsale,Long>  {


		@Query(value="select id,title,genderid,badge,display,startdate,enddate from collectionsale where startdate<now() and enddate>now()",nativeQuery=true)
		public List<Collectionsale> getCollectionsale();
		
		@Query(value="select id,title,genderid,badge,display,startdate,enddate from collectionsale where id=:id ",nativeQuery=true)
		public Optional<Collectionsale> getCollectionsalebyid(@Param("id")Long id);
	
		@Query(value="select a.id,a.title,a.genderid,a.badge,a.display,a.startdate,a.enddate,b.productid from collectionsale a, collectionsaleprod b where a.id=b.id and  genderid=:genderid and startdate<now() and enddate>now(); ",nativeQuery=true)
		public List<CollectionSalewithProductIds> getCollectionsalebygenderid(@Param("genderid")String genderid);
		
		@Query(value="select a.id as id, a.title as title, a.genderid as gender, a.badge as badge, a.display as display, a.startdate as startdate, a.enddate as enddate, b.icon as icon, b.mobileview1 as mobileview1, b.mobileview2 as mobileview2, b.desktopview1 as desktopview1, b.desktopview2 as desktopview2 from collectionsale a, collectionsaleimages b  where b.id = a.id  and a.genderid= :genderid and a.startdate <= now() and  a.enddate >= now()", nativeQuery = true)
		public List<CollectionSalePack> getCollectionSalesInformation(@Param("genderid")String genderid);
		
		@Query(value="select a.id as id, a.title as title, a.genderid as genderid, a.badge as badge, a.display as display, a.startdate as startdate, a.enddate as enddate, b.icon as icon, b.mobileview1 as mobileview1, b.mobileview2 as mobileview2, b.desktopview1 as desktopview1, b.desktopview2 as desktopview2 from collectionsale a, collectionsaleimages b  where b.id = a.id  and a.startdate <= now() and  a.enddate >= now()", nativeQuery=true)
		public List<CollectionSalePack> getAllGenderSalesInformation();
		
		@Query(value="select a.id as id, a.title as title, a.genderid as genderid, a.badge as badge, a.display as display, a.startdate as startdate, a.enddate as enddate, b.icon as icon from collectionsale a, collectionsaleimages b  where b.id = a.id  and a.startdate <= now() and  a.enddate >= now()", nativeQuery=true)
		public List<CollectionSalePack> getAllGenderSalesInformation1();
		
		@Query(value="select a.id as id, a.title as title, a.genderid as genderid, a.badge as badge, a.display as display, a.startdate as startdate, a.enddate as enddate, b.mobileview1 as mobileview1 from collectionsale a, collectionsaleimages b  where b.id = a.id and a.genderid=:genderid and a.startdate <= now() and  a.enddate >= now() ORDER BY RAND() LIMIT 4", nativeQuery=true)
		public List<CollectionSalePack> getAllSalesInformationMobileView1(@Param("genderid")String genderid);
		
		@Query(value="select a.id as id, a.title as title, a.genderid as genderid, a.badge as badge, a.display as display, a.startdate as startdate, a.enddate as enddate,  b.desktopview1 as desktopview1 from collectionsale a, collectionsaleimages b  where b.id = a.id and a.genderid=:genderid and a.startdate <= now() and  a.enddate >= now() ORDER BY RAND() LIMIT 4", nativeQuery=true)
		public List<CollectionSalePack> getAllSalesInformationDesktopView1(@Param("genderid")String genderid);
	
}
