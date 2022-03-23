package com.lc.sk.inventory.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.lc.sk.inventory.entities.Selectedproducts;


public interface SelectedProductsPagingRepository extends PagingAndSortingRepository<Selectedproducts, Long>{

	@Query(value="select spid,genderid,productid,startdate,enddate from selectedproducts",nativeQuery=true)
	public List<Selectedproducts> getallSelectedproductsPaging(Pageable pageable);
	
	
	@Query(value="select spid,genderid,productid,startdate,enddate from selectedproducts",nativeQuery=true)
	Page<Selectedproducts> getPageCount(Pageable pageable);	
	
	
}
