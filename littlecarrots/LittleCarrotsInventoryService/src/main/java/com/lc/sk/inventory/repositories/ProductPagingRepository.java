package com.lc.sk.inventory.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lc.sk.inventory.bean.ApprovalBean;
import com.lc.sk.inventory.bean.ProductFullDetails;
import com.lc.sk.inventory.entities.Products;
import com.lc.sk.inventory.util.Queries;

@Repository
public interface ProductPagingRepository extends PagingAndSortingRepository<Products, Long> 
{

	@Query(value=Queries.queryforPaging1, nativeQuery = true)
	List<ProductFullDetails> getAllProductDetails(Pageable pageable);
	
	@Query(value=Queries.queryforPaging1, nativeQuery = true)
	Page<ProductFullDetails> getPageCount(Pageable pageable);	
	
	@Query(value=Queries.queryforapproval,nativeQuery = true)
	List<ApprovalBean> getAllApprovals(Pageable pageable);
	
	@Query(value=Queries.queryforapproval, nativeQuery = true)
	Page<ApprovalBean> getAllApprovalsPageNo(Pageable pageable);
	
	@Query(value=Queries.searchqueryload, nativeQuery = true)
	List<ProductFullDetails> getSearchProducts(Pageable pageable, @Param("search") String search);
	
	@Query(value=Queries.searchqueryload, nativeQuery = true)
	Page<ProductFullDetails> getSearchProductsPageCount(Pageable pageable, @Param("search") String search);
	
	
	@Query(value=Queries.cacheproducts, nativeQuery = true)
	List<ProductFullDetails> getForCache();
	
}
