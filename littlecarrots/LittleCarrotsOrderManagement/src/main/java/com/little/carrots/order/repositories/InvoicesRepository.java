package com.little.carrots.order.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.little.carrots.order.bean.DateInfo;
import com.little.carrots.order.bean.Invoice_full_detailsBean;
import com.little.carrots.order.entity.Invoices;




public interface InvoicesRepository extends JpaRepository<Invoices,Long> {
	
	@Query (value ="select * from invoices",nativeQuery=true)
	public List<Invoices> getAllInvoices();
	
	@Query(value="select inid from invoices where orid=:orid && user=:user",nativeQuery=true)
	public Long getId(@Param("orid")String orid,@Param("user")String user);
	
	@Query (value ="select user,year(dateoforder) as year,MONTHNAME(dateoforder) as month from invoices where sid=:sid",nativeQuery=true)
	public List<DateInfo> getAllDatesInfo(@Param("sid")long sid);

	@Query (value ="  select   i.inid, i.sid,x.source, i.orid, i.dateoforder, i.dateofinvoice, year(i.dateoforder) as year, MONTHNAME(i.dateoforder) as month , i.total, i.shipping, i.paymentmode, i.transactionid, i.statusoftheinvoice, i.user, i.invoiceid,\r\n"
			+ "           sp.saleprid, sp.prid, sp.qty, sp.netamount, sp.gst, sp.discount, sp.totalproductprice, sp.status\r\n"
			+ "           from invoices i, soldproducts sp, sourcesales x\r\n"
			+ "           where (i.inid = sp.inid) && (i.sid=x.sid);",nativeQuery=true)
	public List<Invoice_full_detailsBean> getFullDetails();
	
	@Query (value ="  select   i.inid, i.sid,x.source, i.orid, i.dateoforder, i.dateofinvoice, year(i.dateoforder) as year, MONTHNAME(i.dateoforder) as month , i.total, i.shipping, i.paymentmode, i.transactionid, i.statusoftheinvoice, i.user, i.invoiceid,\r\n"
			+ "           sp.saleprid, sp.prid, sp.qty, sp.netamount, sp.gst, sp.discount, sp.totalproductprice, sp.status\r\n"
			+ "          from invoices i, soldproducts sp, sourcesales x\r\n"
			+ "           where (i.inid = sp.inid) && (i.sid=x.sid) && i.user =:user && year(i.dateoforder)=:year && MONTHNAME(i.dateoforder)=:month",nativeQuery=true)
	public List<Invoice_full_detailsBean> filter(@Param("user")String user, @Param("year")String year, @Param("month")String month);


}
