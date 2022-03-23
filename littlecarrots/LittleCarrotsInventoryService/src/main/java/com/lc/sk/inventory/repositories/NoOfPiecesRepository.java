package com.lc.sk.inventory.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.lc.sk.inventory.entities.NoOfPieces;

public interface NoOfPiecesRepository extends JpaRepository<NoOfPieces, Long> {

// TODO Remove unused code found by UCDetector
// 	@Query(value="select a.pieceid, a.productid, a.noofset from noofpieces a where a.productid =:productid", nativeQuery=true )
// 	public Optional<NoOfPieces> find(@Param("productid") Long productid);
	
	@Query(value="select pieceid,productid,noofset from noofpieces a where pieceid =:pieceid", nativeQuery=true )
	public Optional<NoOfPieces> findid(@Param("pieceid") Long pieceid);
	
	@Query(value="select pieceid,productid,noofset from noofpieces",nativeQuery = true)
	public List<NoOfPieces> getallpieces();
}
