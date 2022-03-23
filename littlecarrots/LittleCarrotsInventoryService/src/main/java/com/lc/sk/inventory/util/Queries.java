package com.lc.sk.inventory.util;

public interface Queries {

	final static String query1 = "select a.productid as productid, a.descriptionid as descriptionid, a.batchid as batchid, a.genderid as genderid, a.catid as categoryid, a.subcatid as subcategoryid," + 
			" a.seasonid as seasionid, a.occasionid as occasionid, a.ageid as ageid, a.materialid as materialid, a.colorid as colorid, a.custid as custid, " + 
			"a.status as productstatus, a.title as producttitle, a.subtitle as productsubtitle, b.description as productdescription, c.WarehouseID as warehouseid, c.Status as batchstatus," + 
			" c.Sellerid as sellerid, e.category as category, f.categoryname as subcategory, g.season as season, h.occaName as occasion," + 
			" i.des as agedescription, j.materialname as materialused, k.colorname as color, k.hashcode as colorhashcode, l.quantity as quantity, l.sizeid as sizeid, " + 
			" n.pieceid as pieceid, n.noofset as setofpieces, o.patid as patternid, o.description as pattern," + 
			"p.priceid as priceid, p.mrp as mrp, p.lcprice as lcprice, p.tax as tax, p.sellingprice as sellingprice, p.finalprice as finalprice, p.discount as discount " + 
			"from products a, productdescriptions b, batch c, genders d, categories e,subcategories f, seasonwear g, occasionalwear h, productage i, materialtype j, color k, productquantities l," + 
			"sizes m,  noofpieces n, patterns o, pricestable p where " + 
			"a.productid = :productid and a.status = :status and b.descriptionid = a.descriptionid and c.batchid = a.batchid and d.genderid = a.genderid and" + 
			" e.catid = a.catid and e.gender = a.genderid and f.subcatid = a.subcatid and f.catid = a.catid and g.seasonid = a.seasonid and g.subcatid = a.subcatid " + 
			"and h.occasionid = a.occasionid and h.subcatid = a.subcatid and i.ageid = a.ageid and j.materialid = a.materialid and j.occasionid= a.occasionid and " + 
			"j.seasonid = a.seasonid and j.catid = a.catid and k.colorid = a.colorid and l.custid = a.custid and m.sizeid = l.sizeid and m.ageid = a.ageid and " + 
			"m.gender = a.genderid and m.subcatid = a.subcatid and n.productid = a.productid and o.productid = a.productid and p.productid = a.productid";
	
	
	final static String query2 = "select a.productid as productid, a.descriptionid as descriptionid, a.batchid as batchid, a.genderid as genderid, a.catid as categoryid, a.subcatid as subcategoryid, a.seasonid as seasionid, a.occasionid as occasionid, a.ageid as ageid, a.materialid as materialid, a.colorid as colorid, a.custid as custid, a.status as productstatus, a.title as producttitle, a.subtitle as productsubtitle, b.description as productdescription, c.WarehouseID as warehouseid, c.Status as batchstatus, c.Sellerid as sellerid, e.category as category, f.categoryname as subcategory, g.season as season, h.occaName as occasion, i.des as agedescription, j.materialname as materialused, k.colorname as color, k.hashcode as colorhashcode, l.quantity as quantity, l.sizeid as sizeid, n.pieceid as pieceid, n.noofset as setofpieces, o.patid as patternid, o.description as pattern, p.priceid as priceid, p.mrp as mrp, p.lcprice as lcprice, p.tax as tax, p.sellingprice as sellingprice, p.finalprice as finalprice, p.discount as discount from products a, productdescriptions b, batch c, genders d, categories e,subcategories f, seasonwear g, occasionalwear h, productage i, materialtype j, color k, productquantities l, sizes m,  noofpieces n, patterns o, pricestable p where b.descriptionid = a.descriptionid and c.batchid = a.batchid and d.genderid = a.genderid and e.catid = a.catid and e.gender = a.genderid and f.subcatid = a.subcatid and f.catid = a.catid and g.seasonid = a.seasonid and g.subcatid = a.subcatid and h.occasionid = a.occasionid and h.subcatid = a.subcatid and i.ageid = a.ageid and j.materialid = a.materialid and j.occasionid= a.occasionid and j.seasonid = a.seasonid and j.catid = a.catid and k.colorid = a.colorid and l.custid = a.custid and m.sizeid = l.sizeid and m.ageid = a.ageid and m.gender = a.genderid and m.subcatid = a.subcatid and n.productid = a.productid and o.productid = a.productid and p.productid = a.productid ORDER BY a.productid ASC";
	
	
	
	final static String query3 = "select products.productid as productid, products.descriptionid as descriptionid, products.batchid as batchid, products.genderid as genderid, products.catid as categoryid, products.subcatid as subcategoryid, products.seasonid as seasionid, products.occasionid as occasionid, products.ageid as ageid, products.materialid as materialid, products.colorid as colorid, products.custid as custid, products.status as productstatus, products.title as producttitle, products.subtitle as productsubtitle, productdescriptions.description as productdescription, batch.WarehouseID as warehouseid, batch.Status as batchstatus, batch.Sellerid as sellerid, categories.category as category, subcategories.categoryname as subcategory, seasonwear.season as season, occasionalwear.occaName as occasion, productage.des as agedescription, materialtype.materialname as materialused, color.colorname as color, color.hashcode as colorhashcode, productquantities.quantity as quantity, productquantities.sizeid as sizeid, noofpieces.pieceid as pieceid, noofpieces.noofset as setofpieces, patterns.patid as patternid, patterns.description as pattern, pricestable.priceid as priceid, pricestable.mrp as mrp, pricestable.lcprice as lcprice, pricestable.tax as tax, pricestable.sellingprice as sellingprice, pricestable.finalprice as finalprice, pricestable.discount as discount, approval.serialid as approvalid, approval.status as approvalstatus, approval.statustext as approvaltext, approval.comment as approvalcomment from products, productdescriptions , batch , genders , categories ,subcategories , seasonwear , occasionalwear , productage , materialtype , color , productquantities , sizes ,  noofpieces , patterns , pricestable, approval where products.productid=:productid and  productdescriptions.descriptionid = products.descriptionid and batch.batchid = products.batchid and genders.genderid = products.genderid and  categories.catid = products.catid and categories.gender = products.genderid and subcategories.subcatid = products.subcatid and subcategories.catid = products.catid and seasonwear.seasonid = products.seasonid and seasonwear.subcatid = products.subcatid and  occasionalwear.occasionid = products.occasionid and occasionalwear.subcatid = products.subcatid and productage.ageid = products.ageid and materialtype.materialid = products.materialid and materialtype.occasionid= products.occasionid and materialtype.seasonid = products.seasonid and  materialtype.catid = products.catid and color.colorid = products.colorid and productquantities.custid = products.custid and sizes.sizeid = productquantities.sizeid and sizes.ageid = products.ageid and sizes.gender = products.genderid and sizes.subcatid = products.subcatid  and noofpieces.productid = products.productid and patterns.productid = products.productid and pricestable.productid = products.productid and approval.productid=products.productid";
	
	
	
	
	final static String queryforprice = "select products.productid as productid, products.title as title, products.subtitle as subtitle from products where products.productid not in (select pricestable.productid from pricestable)";
	final static String queryforpatterns = "select products.productid as productid, products.title as title, products.subtitle as subtitle from products where products.productid not in (select patterns.productid from patterns)";
	final static String queryforpieces = "select products.productid as productid, products.title as title, products.subtitle as subtitle from products where products.productid not in (select noofpieces.productid from noofpieces)";
	
	
	final static String queryforproductdescritpion = "select a.descriptionid, a.description from productdescriptions a where a.descriptionid not in (select b.descriptionid from products b)";
	final static String queryforproductquantity = "select a.custid, a.quantity, a.sizeid from productquantities a where a.custid not in (select b.custid from products b)";
	
	final static String categoriesbygender = "select catid, category, gender from categories where gender = :genderid";
	
	
	final static String queryforPaging1 = "select products.productid as productid, products.descriptionid as descriptionid, products.batchid as batchid, products.genderid as genderid, products.catid as categoryid, products.subcatid as subcategoryid, products.seasonid as seasionid, products.occasionid as occasionid, products.ageid as ageid, products.materialid as materialid, products.colorid as colorid, products.custid as custid, products.status as productstatus, products.title as producttitle, products.subtitle as productsubtitle, productdescriptions.description as productdescription, batch.WarehouseID as warehouseid, batch.Status as batchstatus, batch.Sellerid as sellerid, categories.category as category, subcategories.categoryname as subcategory, seasonwear.season as season, occasionalwear.occaName as occasion, productage.des as agedescription, materialtype.materialname as materialused, color.colorname as color, color.hashcode as colorhashcode, productquantities.quantity as quantity, productquantities.sizeid as sizeid, noofpieces.pieceid as pieceid, noofpieces.noofset as setofpieces, patterns.patid as patternid, patterns.description as pattern, pricestable.priceid as priceid, pricestable.mrp as mrp, pricestable.lcprice as lcprice, pricestable.tax as tax, pricestable.sellingprice as sellingprice, pricestable.finalprice as finalprice, pricestable.discount as discount, approval.serialid as approvalid, approval.status as approvalstatus, approval.statustext as approvaltext, approval.comment as approvalcomment from products, productdescriptions , batch , genders , categories ,subcategories , seasonwear , occasionalwear , productage , materialtype , color , productquantities , sizes ,  noofpieces , patterns , pricestable, approval where productdescriptions.descriptionid = products.descriptionid and batch.batchid = products.batchid and genders.genderid = products.genderid and  categories.catid = products.catid and categories.gender = products.genderid and subcategories.subcatid = products.subcatid and subcategories.catid = products.catid and seasonwear.seasonid = products.seasonid and seasonwear.subcatid = products.subcatid and  occasionalwear.occasionid = products.occasionid and occasionalwear.subcatid = products.subcatid and productage.ageid = products.ageid and materialtype.materialid = products.materialid and materialtype.occasionid= products.occasionid and materialtype.seasonid = products.seasonid and  materialtype.catid = products.catid and color.colorid = products.colorid and productquantities.custid = products.custid and sizes.sizeid = productquantities.sizeid and sizes.ageid = products.ageid and sizes.gender = products.genderid and sizes.subcatid = products.subcatid  and noofpieces.productid = products.productid and patterns.productid = products.productid and pricestable.productid = products.productid and approval.productid=products.productid ORDER BY products.productid ASC";
	
	final static String queryforapproval = "select serialid,productid,approvaluser,approvedate,qcdate,status,statustext,comment from approval";
			
	final static String queryforcollections="select a.productid as productid, a.descriptionid as descriptionid, a.batchid as batchid, a.genderid as genderid, a.catid as \r\n" + 
			"categoryid, a.subcatid as subcategoryid,a.seasonid as seasionid, a.occasionid as occasionid, a.ageid as ageid, a.materialid as\r\n" + 
			" materialid, a.colorid as colorid, a.custid as custid, a.status as productstatus, a.title as producttitle, a.subtitle as\r\n" + 
			" productsubtitle,b.description as productdescription, c.WarehouseID as warehouseid, c.Status as batchstatus,c.Sellerid as\r\n" + 
			" sellerid, e.category as category, f.categoryname as subcategory, g.season as season, h.occaName as occasion,i.des as\r\n" + 
			" agedescription, j.materialname as materialused, k.colorname as color, k.hashcode as colorhashcode, l.quantity as\r\n" + 
			" quantity, l.sizeid as sizeid,n.pieceid as pieceid, n.noofset as setofpieces, o.patid as patternid, o.description as \r\n" + 
			" pattern,p.priceid as priceid, p.mrp as mrp, p.lcprice as lcprice, p.tax as tax, p.sellingprice as sellingprice, \r\n" + 
			" p.finalprice as finalprice, p.discount as discount from products a, productdescriptions b, batch c, genders d,\r\n" + 
			" categories e,subcategories f, seasonwear g, occasionalwear h, productage i, materialtype j, color k, \r\n" + 
			" productquantities l,sizes m,  noofpieces n, patterns o, pricestable p where \r\n" + 
			" a.catid=:catid  and g.season=:season and h.occaName=:occaName and a.status=true\r\n" + 
			" and b.descriptionid = a.descriptionid and c.batchid = a.batchid and d.genderid = a.genderid \r\n" + 
			" and e.catid = a.catid and e.gender = a.genderid and f.subcatid = a.subcatid and f.catid = a.catid and g.seasonid = a.seasonid\r\n" + 
			" and g.subcatid = a.subcatid and h.occasionid = a.occasionid and h.subcatid = a.subcatid and i.ageid = a.ageid and \r\n" + 
			" j.materialid = a.materialid and j.occasionid= a.occasionid and j.seasonid = a.seasonid and j.catid = a.catid and \r\n" + 
			" k.colorid = a.colorid and l.custid = a.custid and m.sizeid = l.sizeid and m.ageid = a.ageid and m.gender = a.genderid\r\n" + 
			" and m.subcatid = a.subcatid and n.productid = a.productid and o.productid = a.productid and p.productid = a.productid;\r\n" ;
	
	
	final static String searchquery = "select distinct p.productid as productid, p.descriptionid as descriptionid, p.batchid as batchid,  p.genderid as genderid, \r\n" + 
			"p.catid as categoryid, p.subcatid as subcategoryid, p.seasonid as seasionid,  p.occasionid as occasionid, p.ageid as ageid,\r\n" + 
			" p.materialid as materialid, p.colorid as colorid,  p.custid as custid, p.status as productstatus, p.title as producttitle,\r\n" + 
			" p.subtitle as productsubtitle,  pd.description as productdescription, b.WarehouseID as warehouseid, b.Status as batchstatus,\r\n" + 
			" b.Sellerid as sellerid,  c.category as category, s.categoryname as subcategory, sea.season as season, occ.occaName as occasion,\r\n" + 
			" pa.des as agedescription, mt.materialname as materialused, co.colorname as color, co.hashcode as colorhashcode,\r\n" + 
			" pq.quantity as quantity, pq.sizeid as sizeid, nop.pieceid as pieceid, nop.noofset as setofpieces,\r\n" + 
			" pat.patid as patternid, pat.description as pattern, pr.priceid as priceid, pr.mrp as mrp, pr.lcprice as lcprice,\r\n" + 
			" pr.tax as tax,  pr.sellingprice as sellingprice, pr.finalprice as finalprice, pr.discount as discount, cs.badge\r\n" + 
			" from ((((((((((((((products p\r\n" + 
			" INNER JOIN productdescriptions pd ON pd.descriptionid = p.descriptionid)\r\n" + 
			" INNER JOIN batch b ON b.batchid = p.batchid )\r\n" + 
			" INNER JOIN genders g ON g.genderid = p.genderid) \r\n" + 
			" INNER JOIN categories c ON c.catid = p.catid and   c.gender = p.genderid )\r\n" + 
			" INNER JOIN subcategories s ON s.subcatid = p.subcatid and s.catid = p.catid) \r\n" + 
			" INNER JOIN  seasonwear sea ON sea.seasonid = p.seasonid and sea.subcatid = p.subcatid )\r\n" + 
			" INNER JOIN occasionalwear occ ON occ.occasionid = p.occasionid and  occ.subcatid = p.subcatid  )\r\n" + 
			" INNER JOIN productage pa ON pa.ageid = p.ageid )\r\n" + 
			" INNER JOIN materialtype mt ON mt.materialid = p.materialid and mt.occasionid= p.occasionid and  mt.seasonid = p.seasonid and  mt.catid = p.catid) \r\n" + 
			" INNER JOIN color co ON co.colorid = p.colorid )\r\n" + 
			"INNER JOIN productquantities pq ON pq.custid = p.custid\r\n" + 
			" INNER JOIN sizes si ON si.sizeid = pq.sizeid and   si.ageid = p.ageid and si.gender = p.genderid  and si.subcatid = p.subcatid)\r\n" + 
			"  INNER JOIN noofpieces nop ON nop.productid = p.productid )\r\n" + 
			"INNER JOIN patterns pat ON pat.productid = p.productid)\r\n" + 
			" INNER JOIN pricestable pr ON pr.productid = p.productid )\r\n" + 
			" INNER JOIN collectionsaleprod csp ON csp.productid=p.productid \r\n" + 
			" INNER JOIN collectionsale cs ON  cs.id= csp.id\r\n" + 
			"\r\n" + 
			"where p.productid IN (select  x.productid  from similarproducts x \r\n" + 
			"INNER JOIN productvariations y  on x.pvid= y.pvid where x.productid in (select min(productid) from similarproducts group by pvid) order by y.pvid) and\r\n" + 
			" p.status=true and ( match(co.colorname) against(:search in natural language mode) or   match(p.title, p.subtitle, p.ageid)\r\n" + 
			" against(:search in natural language mode) or   match(g.Gender, g.genderid) against(:search in natural language mode) or   match(c.category) \r\n" + 
			" against(:search in natural language mode) or   match(pa.des, pa.ageid) against(:search in natural language mode) or   match(s.categoryname)\r\n" + 
			" against(:search in natural language mode) or   match(sea.season) against(:search in natural language mode) or   match(occ.occaName) \r\n" + 
			" against(:search in natural language mode) or   match(mt.materialname,mt.description ) against(:search in natural language mode) or   \r\n" + 
			" match(pd.description) against(:search in natural language mode) or\r\n" + 
			"   match(cs.title, cs.badge, cs.display) against(:search in natural language mode) or \r\n" + 
			" match(pat.description) against(:search in natural language mode) ) limit :des";

	static final String queryforselectedproducts ="select a.productid as productid, a.descriptionid as descriptionid, a.batchid as batchid, a.genderid as genderid, a.catid as \r\n" + 
			"categoryid, a.subcatid as subcategoryid,a.seasonid as seasionid, a.occasionid as occasionid, a.ageid as ageid, a.materialid as\r\n" + 
			" materialid, a.colorid as colorid, a.custid as custid, a.status as productstatus, a.title as producttitle, a.subtitle as\r\n" + 
			" productsubtitle,b.description as productdescription, c.WarehouseID as warehouseid, c.Status as batchstatus,c.Sellerid as\r\n" + 
			" sellerid, e.category as category, f.categoryname as subcategory, g.season as season, h.occaName as occasion,i.des as\r\n" + 
			" agedescription, j.materialname as materialused, k.colorname as color, k.hashcode as colorhashcode, l.quantity as\r\n" + 
			" quantity, l.sizeid as sizeid,n.pieceid as pieceid, n.noofset as setofpieces, o.patid as patternid, o.description as \r\n" + 
			" pattern,p.priceid as priceid, p.mrp as mrp, p.lcprice as lcprice, p.tax as tax, p.sellingprice as sellingprice, \r\n" + 
			" p.finalprice as finalprice, p.discount as discount from products a, productdescriptions b, batch c, genders d,\r\n" + 
			" categories e,subcategories f, seasonwear g, occasionalwear h, productage i, materialtype j, color k, \r\n" + 
			" productquantities l,sizes m,  noofpieces n, patterns o, pricestable p where \r\n" + 
			" a.genderid=:genderid \r\n" + 
			" and b.descriptionid = a.descriptionid and c.batchid = a.batchid and d.genderid = a.genderid \r\n" + 
			" and e.catid = a.catid and e.gender = a.genderid and f.subcatid = a.subcatid and f.catid = a.catid and g.seasonid = a.seasonid\r\n" + 
			" and g.subcatid = a.subcatid and h.occasionid = a.occasionid and h.subcatid = a.subcatid and i.ageid = a.ageid and \r\n" + 
			" j.materialid = a.materialid and j.occasionid= a.occasionid and j.seasonid = a.seasonid and j.catid = a.catid and \r\n" + 
			" k.colorid = a.colorid and l.custid = a.custid and m.sizeid = l.sizeid and m.ageid = a.ageid and m.gender = a.genderid\r\n" + 
			" and m.subcatid = a.subcatid and n.productid = a.productid and o.productid = a.productid and p.productid = a.productid;\r\n" ;
	
	
	static final String queryforselectedproductgender="select a.productid as productid, a.descriptionid as descriptionid, "
			+ "a.batchid as batchid, a.genderid as genderid, a.catid as categoryid, a.subcatid as subcategoryid,a.seasonid as seasionid,"
			+ " a.occasionid as occasionid, a.ageid as ageid, a.materialid as  materialid, a.colorid as colorid, a.custid as custid,"
			+ " a.status as productstatus, a.title as producttitle, a.subtitle as productsubtitle,b.description as productdescription, "
			+ "c.WarehouseID as warehouseid, c.Status as batchstatus,c.Sellerid as sellerid, e.category as category, "
			+ "f.categoryname as subcategory, g.season as season, h.occaName as occasion,i.des as agedescription, "
			+ "j.materialname as materialused, k.colorname as color, k.hashcode as colorhashcode, l.quantity as quantity, "
			+ "l.sizeid as sizeid,n.pieceid as pieceid, n.noofset as setofpieces, o.patid as patternid, o.description as pattern,"
			+ "p.priceid as priceid, p.mrp as mrp, p.lcprice as lcprice, p.tax as tax, p.sellingprice as sellingprice,"
			+ " p.finalprice as finalprice, p.discount as discount from products a, productdescriptions b, batch c, "
			+ "genders d, categories e,subcategories f, seasonwear g, occasionalwear h, productage i, materialtype j, "
			+ "color k, productquantities l,sizes m,  noofpieces n, patterns o, pricestable p where a.productid in (:products) "
			+ "and b.descriptionid = a.descriptionid and c.batchid = a.batchid and d.genderid = a.genderid  and e.catid = a.catid "
			+ "and e.gender = a.genderid and f.subcatid = a.subcatid and f.catid = a.catid and g.seasonid = a.seasonid and "
			+ "g.subcatid = a.subcatid and h.occasionid = a.occasionid and h.subcatid = a.subcatid and i.ageid = a.ageid and "
			+ "j.materialid = a.materialid and j.occasionid= a.occasionid and j.seasonid = a.seasonid and j.catid = a.catid and"
			+ " k.colorid = a.colorid and l.custid = a.custid and m.sizeid = l.sizeid and m.ageid = a.ageid and m.gender = a.genderid "
			+ "and m.subcatid = a.subcatid and n.productid = a.productid and o.productid = a.productid and p.productid = a.productid; ";
	
	
	static final String queryforselectedproductsmultiselect ="select a.productid as productid, a.descriptionid as descriptionid, a.batchid as batchid, a.genderid as genderid, a.catid as \r\n"  +
			"categoryid, a.subcatid as subcategoryid,a.seasonid as seasionid, a.occasionid as occasionid, a.ageid as ageid, a.materialid as\r\n" + 
			" materialid, a.colorid as colorid, a.custid as custid, a.status as productstatus, a.title as producttitle, a.subtitle as\r\n" + 
				" productsubtitle,b.description as productdescription, c.WarehouseID as warehouseid, c.Status as batchstatus,c.Sellerid as\r\n" + 
				" sellerid, e.category as category, f.categoryname as subcategory, g.season as season, h.occaName as occasion,i.des as\r\n" + 
			" agedescription, j.materialname as materialused, k.colorname as color, k.hashcode as colorhashcode, l.quantity as\r\n" + 
				" quantity, l.sizeid as sizeid,n.pieceid as pieceid, n.noofset as setofpieces, o.patid as patternid, o.description as \r\n" + 
				" pattern,p.priceid as priceid, p.mrp as mrp, p.lcprice as lcprice, p.tax as tax, p.sellingprice as sellingprice, \r\n" + 
				" p.finalprice as finalprice, p.discount as discount from products a, productdescriptions b, batch c, genders d,\r\n" + 
				" categories e,subcategories f, seasonwear g, occasionalwear h, productage i, materialtype j, color k, \r\n" + 
				" productquantities l,sizes m,  noofpieces n, patterns o, pricestable p where \r\n" + 
				" a.genderid=:genderid and a.status=true\r\n" + 
				" and b.descriptionid = a.descriptionid and c.batchid = a.batchid and d.genderid = a.genderid \r\n" + 
				" and e.catid = a.catid and e.gender = a.genderid and f.subcatid = a.subcatid and f.catid = a.catid and g.seasonid = a.seasonid\r\n" + 
				" and g.subcatid = a.subcatid and h.occasionid = a.occasionid and h.subcatid = a.subcatid and i.ageid = a.ageid and \r\n" + 
				" j.materialid = a.materialid and j.occasionid= a.occasionid and j.seasonid = a.seasonid and j.catid = a.catid and \r\n" + 
				" k.colorid = a.colorid and l.custid = a.custid and m.sizeid = l.sizeid and m.ageid = a.ageid and m.gender = a.genderid\r\n" + 
				" and m.subcatid = a.subcatid and n.productid = a.productid and o.productid = a.productid and p.productid = a.productid;\r\n" ;
	
	static final String queryformultiselectinsimilarproducts ="select a.productid as productid, a.descriptionid as descriptionid, a.batchid as batchid, a.genderid as genderid, a.catid as \r\n"  +
			"categoryid, a.subcatid as subcategoryid,a.seasonid as seasionid, a.occasionid as occasionid, a.ageid as ageid, a.materialid as\r\n" + 
			" materialid, a.colorid as colorid, a.custid as custid, a.status as productstatus, a.title as producttitle, a.subtitle as\r\n" + 
				" productsubtitle,b.description as productdescription, c.WarehouseID as warehouseid, c.Status as batchstatus,c.Sellerid as\r\n" + 
				" sellerid, e.category as category, f.categoryname as subcategory, g.season as season, h.occaName as occasion,i.des as\r\n" + 
			" agedescription, j.materialname as materialused, k.colorname as color, k.hashcode as colorhashcode, l.quantity as\r\n" + 
				" quantity, l.sizeid as sizeid,n.pieceid as pieceid, n.noofset as setofpieces, o.patid as patternid, o.description as \r\n" + 
				" pattern,p.priceid as priceid, p.mrp as mrp, p.lcprice as lcprice, p.tax as tax, p.sellingprice as sellingprice, \r\n" + 
				" p.finalprice as finalprice, p.discount as discount from products a, productdescriptions b, batch c, genders d,\r\n" + 
				" categories e,subcategories f, seasonwear g, occasionalwear h, productage i, materialtype j, color k, \r\n" + 
				" productquantities l,sizes m,  noofpieces n, patterns o, pricestable p where \r\n" + 
				" a.status=true \r\n" + 
				" and b.descriptionid = a.descriptionid and c.batchid = a.batchid and d.genderid = a.genderid \r\n" + 
				" and e.catid = a.catid and e.gender = a.genderid and f.subcatid = a.subcatid and f.catid = a.catid and g.seasonid = a.seasonid\r\n" + 
				" and g.subcatid = a.subcatid and h.occasionid = a.occasionid and h.subcatid = a.subcatid and i.ageid = a.ageid and \r\n" + 
				" j.materialid = a.materialid and j.occasionid= a.occasionid and j.seasonid = a.seasonid and j.catid = a.catid and \r\n" + 
				" k.colorid = a.colorid and l.custid = a.custid and m.sizeid = l.sizeid and m.ageid = a.ageid and m.gender = a.genderid\r\n" + 
				" and m.subcatid = a.subcatid and n.productid = a.productid and o.productid = a.productid and p.productid = a.productid;\r\n" ; 
//queries
	
	//ON SCROLL PAGE LOADING PROCESS QUERIES
	
	
	//final static String searchqueryload = "select distinct products.productid as productid, products.descriptionid as descriptionid, products.batchid as batchid, products.genderid as genderid, products.catid as categoryid, products.subcatid as subcategoryid, products.seasonid as seasionid, products.occasionid as occasionid, products.ageid as ageid, products.materialid as materialid, products.colorid as colorid, products.custid as custid, products.status as productstatus, products.title as producttitle, products.subtitle as productsubtitle, productdescriptions.description as productdescription, batch.WarehouseID as warehouseid, batch.Status as batchstatus, batch.Sellerid as sellerid,  categories.category as category, subcategories.categoryname as subcategory, seasonwear.season as season, occasionalwear.occaName as occasion,   productage.des as agedescription, materialtype.materialname as materialused, color.colorname as color, color.hashcode as colorhashcode,   productquantities.quantity as quantity, productquantities.sizeid as sizeid, noofpieces.pieceid as pieceid, noofpieces.noofset as setofpieces,   patterns.patid as patternid, patterns.description as pattern, pricestable.priceid as priceid, pricestable.mrp as mrp, pricestable.lcprice as lcprice,   pricestable.tax as tax,  pricestable.sellingprice as sellingprice, pricestable.finalprice as finalprice, pricestable.discount as discount, collectionsale.badge as collectionsalebadge from products,  productdescriptions , batch , genders , categories ,subcategories , seasonwear , occasionalwear , productage , materialtype, color, productquantities,   sizes ,  noofpieces , patterns , pricestable, collectionsale, collectionsaleprod, similarproducts, productvariations  where productdescriptions.descriptionid = products.descriptionid  and batch.batchid = products.batchid and genders.genderid = products.genderid and categories.catid = products.catid and   categories.gender = products.genderid and subcategories.subcatid = products.subcatid and subcategories.catid = products.catid and   seasonwear.seasonid = products.seasonid and seasonwear.subcatid = products.subcatid and occasionalwear.occasionid = products.occasionid and   occasionalwear.subcatid = products.subcatid and productage.ageid = products.ageid and materialtype.materialid = products.materialid and   materialtype.occasionid= products.occasionid and materialtype.seasonid = products.seasonid and materialtype.catid = products.catid and   color.colorid = products.colorid and productquantities.custid = products.custid and sizes.sizeid = productquantities.sizeid and   sizes.ageid = products.ageid and sizes.gender = products.genderid and sizes.subcatid = products.subcatid and   noofpieces.productid = products.productid and patterns.productid = products.productid and pricestable.productid = products.productid and similarproducts.productid=products.productid and productvariations.pvid = similarproducts.pvid and products.status=true and (   match(color.colorname) against( :search in natural language mode) or match(products.title, products.subtitle, products.ageid) against( :search in natural language mode) or match(genders.Gender, genders.genderid) against( :search in natural language mode) or match(categories.category) against( :search in natural language mode) or match(productage.des, productage.ageid) against( :search in natural language mode) or match(subcategories.categoryname) against( :search in natural language mode) or match(seasonwear.season) against( :search in natural language mode) or match(occasionalwear.occaName) against( :search in natural language mode) or match(materialtype.materialname,materialtype.description ) against( :search in natural language mode) or match(productdescriptions.description) against( :search in natural language mode) or match(collectionsale.title, collectionsale.badge, collectionsale.display) against( :search in natural language mode) or match(patterns.description) against( :search in natural language mode) ) order by rand()";
	final static String searchqueryload ="select distinct p.productid as productid, p.descriptionid as descriptionid, p.batchid as batchid,  p.genderid as genderid, p.catid as categoryid, p.subcatid as subcategoryid, p.seasonid as seasionid,  p.occasionid as occasionid, p.ageid as ageid, p.materialid as materialid, p.colorid as colorid,  p.custid as custid, p.status as productstatus, p.title as producttitle, p.subtitle as productsubtitle,  pd.description as productdescription, b.WarehouseID as warehouseid, b.Status as batchstatus,  b.Sellerid as sellerid,  c.category as category, s.categoryname as subcategory, sea.season as season, occ.occaName as occasion, pa.des as agedescription, mt.materialname as materialused, co.colorname as color, co.hashcode as colorhashcode, pq.quantity as quantity, pq.sizeid as sizeid, nop.pieceid as pieceid, nop.noofset as setofpieces, pat.patid as patternid, pat.description as pattern, pr.priceid as priceid, pr.mrp as mrp, pr.lcprice as lcprice, pr.tax as tax,  pr.sellingprice as sellingprice, pr.finalprice as finalprice, pr.discount as discount, cs.badge from ((((((((((((((products p INNER JOIN productdescriptions pd ON pd.descriptionid = p.descriptionid) INNER JOIN batch b ON b.batchid = p.batchid ) INNER JOIN genders g ON g.genderid = p.genderid) INNER JOIN categories c ON c.catid = p.catid and   c.gender = p.genderid ) INNER JOIN subcategories s ON s.subcatid = p.subcatid and s.catid = p.catid) INNER JOIN  seasonwear sea ON sea.seasonid = p.seasonid and sea.subcatid = p.subcatid ) INNER JOIN occasionalwear occ ON occ.occasionid = p.occasionid and  occ.subcatid = p.subcatid  ) INNER JOIN productage pa ON pa.ageid = p.ageid ) INNER JOIN materialtype mt ON mt.materialid = p.materialid and mt.occasionid= p.occasionid and  mt.seasonid = p.seasonid and  mt.catid = p.catid) INNER JOIN color co ON co.colorid = p.colorid ) INNER JOIN productquantities pq ON pq.custid = p.custid INNER JOIN sizes si ON si.sizeid = pq.sizeid and   si.ageid = p.ageid and si.gender = p.genderid  and si.subcatid = p.subcatid)INNER JOIN noofpieces nop ON nop.productid = p.productid )INNER JOIN patterns pat ON pat.productid = p.productid) INNER JOIN pricestable pr ON pr.productid = p.productid ) INNER JOIN collectionsaleprod csp ON csp.productid=p.productid INNER JOIN collectionsale cs ON  cs.id= csp.id where p.status=true and ( match(co.colorname) against(:search in natural language mode) or   match(p.title, p.subtitle, p.ageid) against(:search in natural language mode) or   match(g.Gender, g.genderid) against(:search in natural language mode) or   match(c.category) against(:search in natural language mode) or   match(pa.des, pa.ageid) against(:search in natural language mode) or   match(s.categoryname) against(:search in natural language mode) or   match(sea.season) against(:search in natural language mode) or   match(occ.occaName) against(:search in natural language mode) or   match(mt.materialname,mt.description ) against(:search in natural language mode) or match(pd.description) against(:search in natural language mode) or match(cs.title, cs.badge, cs.display) against(:search in natural language mode) or match(pat.description) against(:search in natural language mode) ) order by rand()";
	
	
	final static String cacheproducts = "select distinct p.productid as productid, p.descriptionid as descriptionid, p.batchid as batchid,  p.genderid as genderid, p.catid as categoryid, p.subcatid as subcategoryid, p.seasonid as seasionid,  p.occasionid as occasionid, p.ageid as ageid, p.materialid as materialid, p.colorid as colorid,  p.custid as custid, p.status as productstatus, p.title as producttitle, p.subtitle as productsubtitle,  pd.description as productdescription, b.WarehouseID as warehouseid, b.Status as batchstatus,  b.Sellerid as sellerid,  c.category as category, s.categoryname as subcategory, sea.season as season, occ.occaName as occasion, pa.des as agedescription, mt.materialname as materialused, co.colorname as color, co.hashcode as colorhashcode, pq.quantity as quantity, pq.sizeid as sizeid, nop.pieceid as pieceid, nop.noofset as setofpieces, pat.patid as patternid, pat.description as pattern, pr.priceid as priceid, pr.mrp as mrp, pr.lcprice as lcprice, pr.tax as tax,  pr.sellingprice as sellingprice, pr.finalprice as finalprice, pr.discount as discount, cs.badge from ((((((((((((((products p INNER JOIN productdescriptions pd ON pd.descriptionid = p.descriptionid) INNER JOIN batch b ON b.batchid = p.batchid ) INNER JOIN genders g ON g.genderid = p.genderid) INNER JOIN categories c ON c.catid = p.catid and   c.gender = p.genderid ) INNER JOIN subcategories s ON s.subcatid = p.subcatid and s.catid = p.catid) INNER JOIN  seasonwear sea ON sea.seasonid = p.seasonid and sea.subcatid = p.subcatid ) INNER JOIN occasionalwear occ ON occ.occasionid = p.occasionid and  occ.subcatid = p.subcatid  ) INNER JOIN productage pa ON pa.ageid = p.ageid ) INNER JOIN materialtype mt ON mt.materialid = p.materialid and mt.occasionid= p.occasionid and  mt.seasonid = p.seasonid and  mt.catid = p.catid) INNER JOIN color co ON co.colorid = p.colorid ) INNER JOIN productquantities pq ON pq.custid = p.custid INNER JOIN sizes si ON si.sizeid = pq.sizeid and   si.ageid = p.ageid and si.gender = p.genderid  and si.subcatid = p.subcatid)INNER JOIN noofpieces nop ON nop.productid = p.productid )INNER JOIN patterns pat ON pat.productid = p.productid) INNER JOIN pricestable pr ON pr.productid = p.productid ) INNER JOIN collectionsaleprod csp ON csp.productid=p.productid INNER JOIN collectionsale cs ON  cs.id= csp.id where p.status=true";
}
