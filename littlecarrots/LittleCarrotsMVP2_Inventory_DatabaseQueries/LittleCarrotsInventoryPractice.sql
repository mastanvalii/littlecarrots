select distinct products.productid as productid, products.descriptionid as descriptionid, products.batchid as batchid, 
products.genderid as genderid, products.catid as categoryid, products.subcatid as subcategoryid, products.seasonid as seasionid,
 products.occasionid as occasionid, products.ageid as ageid, products.materialid as materialid, products.colorid as colorid,
 products.custid as custid, products.status as productstatus, products.title as producttitle, products.subtitle as productsubtitle,
 productdescriptions.description as productdescription, batch.WarehouseID as warehouseid, batch.Status as batchstatus, batch.Sellerid as sellerid,
 categories.category as category, subcategories.categoryname as subcategory, seasonwear.season as season, occasionalwear.occaName as occasion, 
 productage.des as agedescription, materialtype.materialname as materialused, color.colorname as color, color.hashcode as colorhashcode, 
 productquantities.quantity as quantity, productquantities.sizeid as sizeid, noofpieces.pieceid as pieceid, noofpieces.noofset as setofpieces, 
 patterns.patid as patternid, patterns.description as pattern, pricestable.priceid as priceid, pricestable.mrp as mrp, pricestable.lcprice as lcprice, 
 pricestable.tax as tax,  pricestable.sellingprice as sellingprice, pricestable.finalprice as finalprice, pricestable.discount as discount from products,
 productdescriptions , batch , genders , categories ,subcategories , seasonwear , occasionalwear , productage , materialtype , color, productquantities, 
 sizes ,  noofpieces , patterns , pricestable, collectionsale, collectionsaleprod  where productdescriptions.descriptionid = products.descriptionid 
 and batch.batchid = products.batchid and genders.genderid = products.genderid and categories.catid = products.catid and 
 categories.gender = products.genderid and subcategories.subcatid = products.subcatid and subcategories.catid = products.catid and 
 seasonwear.seasonid = products.seasonid and seasonwear.subcatid = products.subcatid and occasionalwear.occasionid = products.occasionid and 
 occasionalwear.subcatid = products.subcatid and productage.ageid = products.ageid and materialtype.materialid = products.materialid and 
 materialtype.occasionid= products.occasionid and materialtype.seasonid = products.seasonid and materialtype.catid = products.catid and 
 color.colorid = products.colorid and productquantities.custid = products.custid and sizes.sizeid = productquantities.sizeid and 
 sizes.ageid = products.ageid and sizes.gender = products.genderid and sizes.subcatid = products.subcatid and 
 noofpieces.productid = products.productid and patterns.productid = products.productid and pricestable.productid = products.productid and products.status=true and ( 
match(color.colorname) against('round neck white top' in natural language mode) or 
 match(products.title, products.subtitle, products.ageid) against('round neck white top' in natural language mode) or 
 match(genders.Gender) against('round neck white top' in natural language mode) or 
 match(categories.category) against('round neck white top' in natural language mode) or 
 match(productage.des,productage.ageid) against('round neck white top' in natural language mode) or 
 match(subcategories.categoryname) against('round neck white top' in natural language mode) or 
 match(seasonwear.season) against('round neck white top' in natural language mode) or 
 match(occasionalwear.occaName) against('round neck white top' in natural language mode) or 
 match(materialtype.materialname,materialtype.description ) against('round neck white top' in natural language mode) or 
 match(productdescriptions.description) against('round neck white top' in natural language mode) or 
 match(collectionsale.title, collectionsale.badge, collectionsale.display) against('round neck white top' in natural language mode) or 
 match(patterns.description) against('round neck white top' in natural language mode) ) order by products.productid
 
 

 
 select distinct products.productid as productid, products.descriptionid as descriptionid, products.batchid as batchid, 
products.genderid as genderid, products.catid as categoryid, products.subcatid as subcategoryid, products.seasonid as seasionid,
 products.occasionid as occasionid, products.ageid as ageid, products.materialid as materialid, products.colorid as colorid,
 products.custid as custid, products.status as productstatus, products.title as producttitle, products.subtitle as productsubtitle,
 productdescriptions.description as productdescription, batch.WarehouseID as warehouseid, batch.Status as batchstatus, batch.Sellerid as sellerid,
 categories.category as category, subcategories.categoryname as subcategory, seasonwear.season as season, occasionalwear.occaName as occasion, 
 productage.des as agedescription, materialtype.materialname as materialused, color.colorname as color, color.hashcode as colorhashcode, 
 productquantities.quantity as quantity, productquantities.sizeid as sizeid, noofpieces.pieceid as pieceid, noofpieces.noofset as setofpieces, 
 patterns.patid as patternid, patterns.description as pattern, pricestable.priceid as priceid, pricestable.mrp as mrp, pricestable.lcprice as lcprice, 
 pricestable.tax as tax,  pricestable.sellingprice as sellingprice, pricestable.finalprice as finalprice, pricestable.discount as discount from products,
 productdescriptions , batch , genders , categories ,subcategories , seasonwear , occasionalwear , productage , materialtype , color, productquantities, 
 sizes ,  noofpieces , patterns , pricestable  where productdescriptions.descriptionid = products.descriptionid 
 and batch.batchid = products.batchid and genders.genderid = products.genderid and categories.catid = products.catid and 
 categories.gender = products.genderid and subcategories.subcatid = products.subcatid and subcategories.catid = products.catid and 
 seasonwear.seasonid = products.seasonid and seasonwear.subcatid = products.subcatid and occasionalwear.occasionid = products.occasionid and 
 occasionalwear.subcatid = products.subcatid and productage.ageid = products.ageid and materialtype.materialid = products.materialid and 
 materialtype.occasionid= products.occasionid and materialtype.seasonid = products.seasonid and materialtype.catid = products.catid and 
 color.colorid = products.colorid and productquantities.custid = products.custid and sizes.sizeid = productquantities.sizeid and 
 sizes.ageid = products.ageid and sizes.gender = products.genderid and sizes.subcatid = products.subcatid and 
 noofpieces.productid = products.productid and patterns.productid = products.productid and pricestable.productid = products.productid and products.status=true and ( 
 match(color.colorname) against('round neck white top' in natural language mode) or 
 match(products.title, products.subtitle, products.ageid) against('round neck white top' in natural language mode) or 
 match(genders.Gender) against('round neck white top' in natural language mode) or 
 match(categories.category) against('round neck white top' in natural language mode) or 
 match(productage.des,productage.ageid) against('round neck white top' in natural language mode) or 
 match(subcategories.categoryname) against('round neck white top' in natural language mode) or 
 match(seasonwear.season) against('round neck white top' in natural language mode) or 
 match(occasionalwear.occaName) against('round neck white top' in natural language mode) or 
 match(materialtype.materialname,materialtype.description ) against('round neck white top' in natural language mode) or 
 match(productdescriptions.description) against('round neck white top' in natural language mode) or 
 match(patterns.description) against('round neck white top' in natural language mode) ) order by products.productid
 
 
 
 

 