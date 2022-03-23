package com.lc.sk.inventory.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.lc.sk.inventory.bean.ProductidsBean;
import com.lc.sk.inventory.entities.Productvariations;

public interface ProductVariationRepository extends CrudRepository<Productvariations, Long> {

	@Query(value="select * from productvariations;", nativeQuery=true)
	public List<Productvariations> getAllVariations();
	
	@Query(value="select  x.productid  from similarproducts x INNER JOIN productvariations y  on x.pvid= y.pvid where x.productid in (select min(productid) from similarproducts group by pvid) order by y.pvid",nativeQuery=true )
	public List<ProductidsBean> getAllProductids();
	
}
