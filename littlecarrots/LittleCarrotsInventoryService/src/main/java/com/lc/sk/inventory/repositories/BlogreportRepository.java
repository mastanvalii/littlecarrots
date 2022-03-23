package com.lc.sk.inventory.repositories;

import org.springframework.data.repository.CrudRepository;


import com.lc.sk.inventory.entities.Blogreport;

public interface BlogreportRepository extends CrudRepository <Blogreport,Long>
{

// TODO Remove unused code found by UCDetector
// 	@Query(value="select bid,authorname,authorimage,description from blogreport",nativeQuery = true)
// 	public List<Blogreport> getAllBlogreportListPaging(Pageable pageable);
	
// TODO Remove unused code found by UCDetector
// 	@Query(value="select bid,authorname,authorimage,description from blogreport",nativeQuery=true)
// 	public Page<Blogreport> getallBlogreportpagingcount(Pageable pageable);
}
	
	
	
	
	
	
