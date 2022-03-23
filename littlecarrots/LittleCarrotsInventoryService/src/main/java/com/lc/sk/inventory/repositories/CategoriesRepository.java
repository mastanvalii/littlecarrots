package com.lc.sk.inventory.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import com.lc.sk.inventory.entities.Categories;
import com.lc.sk.inventory.util.Queries;

public interface CategoriesRepository  extends CrudRepository<Categories, Long> {

	@Query(value=Queries.categoriesbygender, nativeQuery = true)
	public List<Categories> getCategoriesByGenderId(@Param("genderid") String genderid);
	
	@Query(value="select catid, category, gender from categories", nativeQuery=true)
	public List<Categories> getAllCategories();
	
	@Query(value="select catid, category, gender from categories where catid=:catid", nativeQuery=true)
	public Optional<Categories> getAllCategoriesbyId(@Param("catid")Long catid);	
	
}
