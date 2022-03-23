package com.lc.sk.auth.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.lc.sk.auth.entities.Contest;



public interface ContestPaginationRepository  extends PagingAndSortingRepository<Contest, Long>  {
	
	@Query(value="select * from contest order by dateinsertion asc ", nativeQuery=true)
	List<Contest> getAllDetails(Pageable pageable );
	
	@Query(value="select * from contest order by dateinsertion asc ", nativeQuery=true)
	Page<Contest> getPageCount(Pageable pageable );	
	
	@Query(value="select * from contest where contestmonthyear=:contestmonthyear  order by dateinsertion asc ", nativeQuery=true)
	List<Contest> getAllDetailsOfContest(Pageable pageable,@Param("contestmonthyear")String contestmonthyear );
	
	@Query(value="select * from contest where contestmonthyear=:contestmonthyear  order by dateinsertion asc", nativeQuery=true)
	Page<Contest> getPageCount2(Pageable pageable,@Param("contestmonthyear")String contestmonthyear );	
	

}
