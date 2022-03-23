package com.little.carrots.order.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.little.carrots.order.entity.SoldItems;

@Repository
public interface SoldItemsRepository extends JpaRepository<SoldItems, Long> {

	@Query(value="select * from solditems where invoiceid=:invoiceid",nativeQuery=true)
	public List<SoldItems> getListByInvoiceId(@Param("invoiceid")Long invoiceid);
}
