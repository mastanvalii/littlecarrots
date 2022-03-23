package com.lc.sk.inventory.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.lc.sk.inventory.bean.SubCatBean;
import com.lc.sk.inventory.entities.SubCategories;

public interface SubCategoriesRepository extends CrudRepository<SubCategories, Long>{
	
	@Query (value="select CONCAT(c.category,'--',s.categoryname,'--',c.gender) as details, s.subcatid as subcatid from categories c,subcategories s where c.catid=s.catid",nativeQuery = true)
	public List<SubCatBean> getDetails();

	@Query (value="select subcatid,catid,categoryname from subcategories",nativeQuery = true)
	public List<SubCategories> getAllSubcategories();
	
	@Query (value="select subcatid,catid,categoryname from subcategories where subcatid=:subcatid",nativeQuery = true)
	public Optional<SubCategories>getSubcategoriesById(@Param("subcatid")long subcatid);
}
