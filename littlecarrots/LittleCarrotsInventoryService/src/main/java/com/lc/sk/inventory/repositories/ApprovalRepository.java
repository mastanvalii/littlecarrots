package com.lc.sk.inventory.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.lc.sk.inventory.entities.Approval;

public interface ApprovalRepository extends CrudRepository<Approval, Long> {

	@Query( value ="select a.serialid, a.productid, a.inserteduser, a.approvaluser, a.approvedate,a.qcdate, a.status,a.statustext, a.comment from approval a where a.productid=:productid", nativeQuery = true)
	public Approval getApprovalByProductId(@Param("productid")Long productid);
	
	@Query( value="select a.serialid, a.productid, a.inserteduser, a.approvaluser, a.approvedate, a.qcdate, a.status, a.statustext, a.comment from approval a where a.serialid = :serialid", nativeQuery = true)
	public Optional<Approval> getApprovalBySerialId(@Param("serialid")Long serialid);
	
	@Query( value="select a.serialid, a.productid, a.inserteduser, a.approvaluser, a.approvedate, a.qcdate, a.status, a.statustext, a.comment from approval a", nativeQuery = true)
	public List<Approval> getAllDetails();
}
