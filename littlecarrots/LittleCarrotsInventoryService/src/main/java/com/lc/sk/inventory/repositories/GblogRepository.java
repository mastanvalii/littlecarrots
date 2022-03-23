package com.lc.sk.inventory.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.lc.sk.inventory.bean.GBlogBean;
import com.lc.sk.inventory.bean.GBlogBean1;
import com.lc.sk.inventory.entities.GrannyBlog;

public interface GblogRepository extends CrudRepository <GrannyBlog,Long> {

	

		@Query(value="select blogid,bid,title,subtitle,information,dateofinsertion,thumbimage,mobilebanner,desktopbanner,status from grannyblog",nativeQuery = true)
		public List<GrannyBlog> getAllGrannyBlogListPaging(Pageable pageable);
		
		@Query(value="select blogid,bid,title,subtitle,information,dateofinsertion,thumbimage,mobilebanner,desktopbanner,status from grannyblog",nativeQuery=true)
		public Page<GrannyBlog> getallGrannyBlogpagingcount(Pageable pageable);
		
		@Query(value="select * from grannyblog ",nativeQuery=true)
		public List<GrannyBlog> getAll();
		
		@Query(value="select * from grannyblog where blogid=:blogid",nativeQuery=true)
		public Optional<GrannyBlog> getByblogid(@Param("blogid")Long blogid);
		
		@Query(value="select blogid, bid, title,information, dateofinsertion, thumbimage from grannyblog order by blogid desc limit 4",nativeQuery=true)
		public List<GBlogBean1> getLatest5();
		
		@Query(value="select blogid, title, dateofinsertion,thumbimage from grannyblog where blogid <:blogid and blogid >= (:blogid-3) order by blogid desc",nativeQuery=true)
		public List<GBlogBean> getNextlatest5(@Param("blogid")Long blogid);
}
		

