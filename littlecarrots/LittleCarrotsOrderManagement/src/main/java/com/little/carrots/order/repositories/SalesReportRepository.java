package com.little.carrots.order.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.little.carrots.order.bean.CalenderBean;
import com.little.carrots.order.entity.SalesReport;

@Repository
public interface SalesReportRepository extends JpaRepository<SalesReport, Long> {

	@Query(value="SELECT DISTINCT MONTH(`invoicedate`) AS month, YEAR(`invoicedate`) AS year FROM salesreport GROUP BY MONTH(`invoicedate`), YEAR(`invoicedate`)", nativeQuery = true)
	public List<CalenderBean> getAvaialbleMonths();
	
	@Query(value="SELECT invoiceid, soldfrom, orderid, sellerinvoice, awb, "
			+ "trackingid, state, hsn, tax, taxtype, discount, taxamount, "
			+ "totalamount, paidtype, transactionid, invoicedate, orderdate, shippingcharges, shippingtaxamount, shippingtotalamount "
			+ "FROM salesreport WHERE MONTH(`invoicedate`) =:month AND YEAR(`invoicedate`) = :year", nativeQuery=true)
	public List<SalesReport> getSalesReportByMonthYear(@Param("month") String month, @Param("year") String year);

}
