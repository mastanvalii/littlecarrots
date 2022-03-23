package com.lc.sk.inventory.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lc.sk.inventory.bean.ProductFullDetails;
import com.lc.sk.inventory.bean.UnFilledProductsBean;
import com.lc.sk.inventory.entities.Products;
import com.lc.sk.inventory.util.Queries;

@Repository
public interface ProductsRepository extends CrudRepository<Products, Long> {

	@Query( value ="select sum(b.quantity) from productquantities b, products a where b.custid = a.custid and a.status = true", nativeQuery = true)
	public Long getProductCountDetails();


	@Query (value = "select count(*) from products a, productquantities b where a.custid = b.custid and b.quantity = 0  and a.status = true", nativeQuery=true)
	public Long outofStockProducts();
	
	@Query (value = "select count(*) from products a, productquantities b where a.custid = b.custid and b.quantity < 5 and b.quantity > 0 and a.status = true;", nativeQuery = true)
	public Long lowStockProducts();
	
	@Query(value=Queries.query1, nativeQuery = true)
	public ProductFullDetails getProductDetails(@Param("productid") String productid, @Param("status") boolean status);
	
	@Query(value=Queries.query2, nativeQuery = true)
	public List<ProductFullDetails> getAllProductDetails();
	
	
	
// TODO Remove unused code found by UCDetector
// 	@Query(value=Queries.query3, nativeQuery = true)
// 	public ProductFullDetails getProductDetailsById(@Param("productid") String productid);
	
	@Query(value=Queries.queryforprice, nativeQuery=true)
	public List<UnFilledProductsBean> getDatafromPrice();
	
	@Query(value=Queries.queryforpatterns, nativeQuery=true)
	public List<UnFilledProductsBean> getDatafromPatterns();
	
	@Query(value=Queries.queryforpieces, nativeQuery=true)
	public List<UnFilledProductsBean> getDatafromPieces();
	
	@Query(value=Queries.queryforcollections,nativeQuery= true)
	public List<ProductFullDetails> getCollection(@Param("catid")long catid,@Param("season")String season,@Param("occaName") String occaName);
	
// TODO Remove unused code found by UCDetector
// 	@Query(value=Queries.queryforselectedproducts,nativeQuery= true)
// 	public List<ProductFullDetails> getSelectedproducts(@Param("genderid")String genderid);
	
	
	@Query(value=Queries.searchquery, nativeQuery = true)
	List<ProductFullDetails> getSearchProducts(@Param("search") String search,@Param("des") int des);
	
	@Query(value=Queries.queryforselectedproductgender, nativeQuery=true)
	public List<ProductFullDetails> getSelectedProductsBylimproducts(@Param("products") List<Long> products);
	
	@Query(value=Queries.queryforselectedproductsmultiselect,nativeQuery= true)
	public List<ProductFullDetails> getSelectedproductsformultiselect(@Param("genderid")String genderid);
	
	@Query(value=Queries.queryformultiselectinsimilarproducts,nativeQuery=true)
	public List<ProductFullDetails> getProductsForMultiselectInSimilarProd();
	
	@Query(value="select productid from products where descriptionid=:descriptionid", nativeQuery=true)
	public Long getProductidByDesid(@Param("descriptionid")Long descriptionid);
}
