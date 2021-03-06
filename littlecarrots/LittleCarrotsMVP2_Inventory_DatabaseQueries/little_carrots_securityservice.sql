#drop database little_carrots_securityservice;
create database little_carrots_securityservice;
#CreatING User 'little_carrots_authentications'
#create user 'littlesecurity'@'localhost' identified by 'littlesecurity';
#Granting all permission to the user 'littlesecurity'
#GRANT ALL PRIVILEGES ON little_carrots_securityservice.* TO 'littlesecurity'@'localhost';

use little_carrots_securityservice;

create table securitykeymanagement(
keyid bigint auto_increment,
keyname varchar(100),
keyvalue varchar(1000),
constraint securitykeymanagement_pk primary key (keyid)
);

ALTER TABLE securitykeymanagement AUTO_INCREMENT = 1000;

insert securitykeymanagement(keyname, keyvalue) values('AUTH_API_ACCESS_KEY', 'LRtt3I-ARtlos-51TCOi-er1212-1819TE-Tlcr92-01820L-Sa2015');
insert securitykeymanagement(keyname, keyvalue) values('SECU_API_ACCESS_KEY', 'LSt15I-Tlos18-20TOir-121819-TRtr91-LRta20-3EALC2-05ce12');
insert securitykeymanagement(keyname, keyvalue) values('INVENTORY_API_ACCESS_KEY', 'Li20Il-t920TS-t1212T-Tls5LO-et319E-Rco120-CRar18-15Ar18');


create table serviceurls
(
servicename varchar(30) unique,
url varchar(1000),
timeout bigint,
method varchar(20),
constraint serviceurls_pk2 primary key (servicename)

);

alter table serviceurls add column serviceip varchar(1000);



insert serviceurls(servicename, serviceip, url,timeout,method) values
('AUTHORIZATION_GET','http://localhost:8282','/authorization',1000,'GET');

insert serviceurls(servicename, serviceip, url,timeout,method) values
('AUTHORIZATION_POST','http://localhost:8282','/authorization',1000,'POST');

insert serviceurls(servicename, serviceip, url,timeout,method) values
('AUTHORIZATION_PUT','http://localhost:8282','/authorization',1000,'PUT');

insert serviceurls(servicename, serviceip, url,timeout,method) values
('AUTHORIZED_USER_GET','http://localhost:8282','/authorizeduser',1000,'GET');

insert serviceurls(servicename, serviceip, url,timeout,method) values
('AUTHORIZED_USER_POST','http://localhost:8282','/authorizeduser',1000,'POST');

insert serviceurls(servicename, serviceip, url,timeout,method) values
('AUTHORIZED_USER_LOGIN_POST','http://localhost:8282','/authuserlogin',1000,'POST');

insert serviceurls(servicename, serviceip, url,timeout,method) values
('AUTHORIZED_USER_PUT','http://localhost:8282','/authorizeduser',1000,'PUT');


insert serviceurls(servicename, serviceip, url,timeout,method) values
('SELLER_POST','http://localhost:8282','/sellers',1000,'POST');

insert serviceurls(servicename, serviceip, url,timeout,method) values
('SELLER_PUT','http://localhost:8282','/sellers',1000,'PUT');


insert serviceurls(servicename, serviceip, url,timeout,method) values
('SELLER_USER_GET','http://localhost:8282','/salesusers',1000,'GET');

insert serviceurls(servicename, serviceip, url,timeout,method) values
('SELLER_USER_PUT','http://localhost:8282','/salesusers',1000,'PUT');

insert serviceurls(servicename, serviceip, url,timeout,method) values
('SELLER_USER_POST_LOGIN','http://localhost:8282','/salesuserslogin',1000,'POST');

insert serviceurls(servicename, serviceip, url,timeout,method) values
('SELLER_USER_POST','http://localhost:8282','/salesusers',1000,'POST');

insert serviceurls(servicename, serviceip, url,timeout,method) values
('COUNTRIES_GET','http://localhost:8383','/countries',1000,'GET');

insert serviceurls(servicename, serviceip, url,timeout,method) values
('COUNTRIES_PUT','http://localhost:8383','/countries',1000,'PUT');

insert serviceurls(servicename, serviceip, url,timeout,method) values
('COUNTRIES_ACTIVE_GET','http://localhost:8383','/activecountries',1000,'GET');



insert serviceurls(servicename, serviceip, url,timeout,method) values
('COLORSERVICE_POST','http://localhost:8383','/color',1000,'POST');

insert serviceurls(servicename, serviceip, url,timeout,method) values
('COLORSERVICE_GET','http://localhost:8383','/color',1000,'GET');
insert serviceurls(servicename, serviceip, url,timeout,method) values
('COLORSERVICE_PUT','http://localhost:8383','/color',1000,'PUT');
insert serviceurls(servicename, serviceip, url,timeout,method) values
('COLOR_ACTIVE_GET','http://localhost:8383','/color',1000,'GET');


# satya 

insert serviceurls(servicename, serviceip, url,timeout,method) values
('BATCHES_GET','http://localhost:8383','/batches',1000,'GET');

insert serviceurls(servicename, serviceip, url,timeout,method) values
('BATCHES_PUT','http://localhost:8383','/batches',1000,'PUT');

insert serviceurls(servicename, serviceip, url,timeout,method) values
('BATCHES_POST','http://localhost:8383','/batches',1000,'POST');


------------ # Satya-----------
insert serviceurls(servicename, serviceip, url,timeout,method) values
('GENDERS_GET','http://localhost:8383','/genders',1000,'GET');

--------     # Phani (colors) --------
#insert serviceurls(servicename, serviceip, url,timeout,method) values
#('COLORSERVICE_POST','http://localhost:8383','/color',1000,'POST');
#insert serviceurls(servicename, serviceip, url,timeout,method) values
#('COLORSERVICE_GET','http://localhost:8383','/color',1000,'GET');
#insert serviceurls(servicename, serviceip, url,timeout,method) values
#('COLORSERVICE_PUT','http://localhost:8383','/color',1000,'PUT');
#insert serviceurls(servicename, serviceip, url,timeout,method) values
#('COLOR_ACTIVE_GET','http://localhost:8383','/color',1000,'GET');

----------------- # Satya -----------
insert serviceurls(servicename, serviceip, url,timeout,method) values
('MATERIALTYPES_GET','http://localhost:8383','/material',1000,'GET');
insert serviceurls(servicename, serviceip, url,timeout,method) values
('MATERIALTYPES_POST','http://localhost:8383','/material',1000,'POST');
insert serviceurls(servicename, serviceip, url,timeout,method) values
('MATERIALTYPES_PUT','http://localhost:8383','/material',1000,'PUT');


---------# satya (Batches)----------
#insert serviceurls(servicename, serviceip, url,timeout,method) values
#('BATCHES_GET','http://localhost:8383','/batches',1000,'GET');

#insert serviceurls(servicename, serviceip, url,timeout,method) values
#('BATCHES_PUT','http://localhost:8383','/batches',1000,'PUT');

#insert serviceurls(servicename, serviceip, url,timeout,method) values
#('BATCHES_POST','http://localhost:8383','/batches',1000,'POST');



---------------- # Trinath (seasonwear, productType & Occasional Wear) -------------

insert serviceurls(servicename, serviceip, url,timeout,method) values
('SEASONWEAR_GET','http://localhost:8383','/seasonwear',1000,'GET');

insert serviceurls(servicename, serviceip, url,timeout,method) values
('SEASONWEAR_POST','http://localhost:8383','/seasonwear',1000,'POST');

insert serviceurls(servicename, serviceip, url,timeout,method) values
('SEASONWEAR_PUT','http://localhost:8383','/seasonwear',1000,'PUT');

insert serviceurls(servicename, serviceip, url,timeout,method) values
('PRODUCTTYPE_GET','http://localhost:8383','/producttype',1000,'GET');

insert serviceurls(servicename, serviceip, url,timeout,method) values
('PRODUCTTYPE_POST','http://localhost:8383','/producttype',1000,'POST');

insert serviceurls(servicename, serviceip, url,timeout,method) values
('PRODUCTTYPE_PUT','http://localhost:8383','/producttype',1000,'PUT');

insert serviceurls(servicename, serviceip, url,timeout,method) values
('OCCASIONALWEAR_GET','http://localhost:8383','/occasionalwear',1000,'GET');

insert serviceurls(servicename, serviceip, url,timeout,method) values
('OCCASIONALWEAR_POST','http://localhost:8383','/occasionalwear',1000,'POST');

insert serviceurls(servicename, serviceip, url,timeout,method) values
('OCCASIONALWEAR_PUT','http://localhost:8383','/occasionalwear',1000,'PUT');


------------------------------- # Rekha (Material Composition) ------------------

insert serviceurls(servicename, serviceip, url,timeout,method) values
('MATERIALCOMPOSITION_GET','http://localhost:8383','/materialcomp',1000,'GET');

insert serviceurls(servicename, serviceip, url,timeout,method) values
('MATERIALCOMPOSITION_PUT','http://localhost:8383','/materialcomp',1000,'PUT');

insert serviceurls(servicename, serviceip, url,timeout,method) values
('MATERIALCOMPOSITION_POST','http://localhost:8383','/materialcomp',1000,'GET');


---------------------- # Aadarsh (PriceTable) -------------------

insert serviceurls(servicename, serviceip, url,timeout,method) values
('PRICESTABLE_POST','http://localhost:8383','/pricestable',1000,'POST');

insert serviceurls(servicename, serviceip, url,timeout,method) values
('PRICESTABLE_PUT','http://localhost:8383','/pricestable',1000,'PUT');

insert serviceurls(servicename, serviceip, url,timeout,method) values
('PRICESTABLE_GET','http://localhost:8383','/pricestable',1000,'GET');

------------------------------- # Rekha (Product Quantities)------------------
insert serviceurls(servicename, serviceip, url,timeout,method) values
('PRODUCTQUANTITIES_GET','http://localhost:8383','/productquantities',1000,'GET');

insert serviceurls(servicename, serviceip, url,timeout,method) values
('PRODUCTQUANTITIES_PUT','http://localhost:8383','/productquantities',1000,'PUT');

insert serviceurls(servicename, serviceip, url,timeout,method) values
('PRODUCTQUANTITIES_POST','http://localhost:8383','/productquantities',1000,'POST');

----------------------# Aadarsh ----------------------------
insert serviceurls(servicename, serviceip, url,timeout,method) values
('PRODUCTDESCRIPTION_POST','http://localhost:8383','/productdescriptions',1000,'POST');

insert serviceurls(servicename, serviceip, url,timeout,method) values
('PRODUCTDESCRIPTION_PUT','http://localhost:8383','/productdescriptions',1000,'PUT');

insert serviceurls(servicename, serviceip, url,timeout,method) values
('PRODUCTDESCRIPTION_GET','http://localhost:8383','/productdescriptions',1000,'GET');


--------- # satya (wareHouses)----------
insert serviceurls(servicename, serviceip, url,timeout,method) values
('WAREHOUSES_GET','http://localhost:8383','/warehouses',1000,'GET');
insert serviceurls(servicename, serviceip, url,timeout,method) values
('WAREHOUSES_POST','http://localhost:8383','/warehouses',1000,'POST');
insert serviceurls(servicename, serviceip, url,timeout,method) values
('WAREHOUSES_PUT','http://localhost:8383','/warehouses',1000,'PUT');

----------------- # Trinath (Patterns) --------------

insert serviceurls(servicename, serviceip, url,timeout,method) values
('PATTERNS_GET','http://localhost:8383','/patterns',1000,'GET');

insert serviceurls(servicename, serviceip, url,timeout,method) values
('PATTERNS_POST','http://localhost:8383','/patterns',1000,'POST');

insert serviceurls(servicename, serviceip, url,timeout,method) values
('PATTERNS_PUT','http://localhost:8383','/patterns',1000,'PUT');


---------------------- # Aadarsh (Warehouses & Products) -------------------
insert serviceurls(servicename, serviceip, url,timeout,method) values
('WAREHOUSETOSELLER_POST','http://localhost:8383','/sellertowarehouse',1000,'POST');

insert serviceurls(servicename, serviceip, url,timeout,method) values
('WAREHOUSETOSELLER_GET','http://localhost:8383','/sellertowarehouse',1000,'GET');

insert serviceurls(servicename, serviceip, url,timeout,method) values
('PRODUCTS_POST','http://localhost:8383','/products',1000,'POST');

insert serviceurls(servicename, serviceip, url,timeout,method) values
('PRODUCTS_PUT','http://localhost:8383','/products',1000,'PUT');

insert serviceurls(servicename, serviceip, url,timeout,method) values
('PRODUCTS_GET','http://localhost:8383','/products',1000,'GET');


---------------------- # Phani (no of pieces) -------------------
insert serviceurls(servicename, serviceip, url,timeout,method) values
('NOOFPIECES_GET','http://localhost:8383','/noofpieces',1000,'GET');

insert serviceurls(servicename, serviceip, url,timeout,method) values
('NOOFPIECES_POST','http://localhost:8383','/noofpieces',1000,'POST');

insert serviceurls(servicename, serviceip, url,timeout,method) values
('NOOFPIECES_PUT','http://localhost:8383','/noofpieces',1000,'PUT');




---------------------- # Phani (productage) -------------------
insert serviceurls(servicename, serviceip, url,timeout,method) values
('PRODUCTAGE_GET','http://localhost:8383','/productage',1000,'GET');

insert serviceurls(servicename, serviceip, url,timeout,method) values
('PRODUCTAGE_POST','http://localhost:8383','/productage',1000,'POST');

insert serviceurls(servicename, serviceip, url,timeout,method) values
('PRODUCTAGE_PUT','http://localhost:8383','/productage',1000,'PUT');




---------------------- # Aadarsh (categories) -------------------
insert serviceurls(servicename, serviceip, url,timeout,method) values
('CATEGORIES_GET','http://localhost:8383','/categories',1000,'GET');

insert serviceurls(servicename, serviceip, url,timeout,method) values
('CATEGORIES_POST','http://localhost:8383','/categories',1000,'POST');

insert serviceurls(servicename, serviceip, url,timeout,method) values
('CATEGORIES_PUT','http://localhost:8383','/categories',1000,'PUT');


---------------------- # Trinath (SubCategories) -------------------
insert serviceurls(servicename, serviceip, url,timeout,method) values
('SUBCATEGORIES_GET','http://localhost:8383','/subcategories',1000,'GET');

insert serviceurls(servicename, serviceip, url,timeout,method) values
('SUBCATEGORIES_POST','http://localhost:8383','/subcategories',1000,'POST');

insert serviceurls(servicename, serviceip, url,timeout,method) values
('SUBCATEGORIES_PUT','http://localhost:8383','/subcategories',1000,'PUT');



---------------------- # Aadarsh (sizes) -------------------
insert serviceurls(servicename, serviceip, url,timeout,method) values
('SIZES_GET','http://localhost:8383','/sizes',1000,'GET');

insert serviceurls(servicename, serviceip, url,timeout,method) values
('SIZES_POST','http://localhost:8383','/sizes',1000,'POST');

insert serviceurls(servicename, serviceip, url,timeout,method) values
('SIZES_PUT','http://localhost:8383','/sizes',1000,'PUT');


insert serviceurls(servicename, serviceip, url,timeout,method) values
('SELLER_GET','http://localhost:8282','/sellers',1000,'GET');
#insert serviceurls(servicename, serviceip, url,timeout,method) values
#('SELLER_POST','http://localhost:8282','/sellers',1000,'POST');
insert serviceurls(servicename, serviceip, url,timeout,method) values
('SELLER_PUT','http://localhost:8282','/sellers',1000,'PUT');

#09-MAR-2020 SHAIK ADDED QUERIES
insert securitykeymanagement(keyname, keyvalue) values('EMAIL_API_ACCESS_KEY', 'Li20Il-t920TS-t1212T-Tls5LO-et319E-Rco120-CRar18-15Ar18');

insert serviceurls(servicename, serviceip, url, timeout, method) values
('EMAIL_POST', 'http://localhost:8281', '/mail/v1/push1', 1000, 'POST');


insert serviceurls(servicename, serviceip, url, timeout, method) values
('IMAGE_POST', 'http://localhost:8686', '/img/v1/upload', 1000, 'POST');

insert serviceurls(servicename, serviceip, url, timeout, method) values
('IMAGE_GET', 'http://localhost:8686', '/img/v1/getallimg', 1000, 'GET');

insert serviceurls(servicename, serviceip, url, timeout, method) values
('IMAGE_GET1', 'http://localhost:8686', '/img/v1/getbyid', 1000, 'GET');

insert serviceurls(servicename, serviceip, url, timeout, method) values
('IMAGE_GET2', 'http://localhost:8686', '/img/v1/getallimg1', 1000, 'GET');

insert serviceurls(servicename, serviceip, url, timeout, method) values
('IMAGE_GET3', 'http://localhost:8686', '/img/v1/getimgs', 1000, 'GET');

#SHAIK ADDED 03-APR-2020
insert serviceurls(servicename, serviceip, url,timeout,method) values
('PRODUCT_DETAILS','http://localhost:8383','/AP100',1000,'GET');

#Satya & Aadarsh ADDED 07-APR-2020 
insert serviceurls(servicename, serviceip, url,timeout,method) values
('PRODUCT_COUNT','http://localhost:8383','/PC101',1000,'GET');
 
 insert serviceurls(servicename, serviceip, url,timeout,method) values
('OUT_OF_STOCK','http://localhost:8383','/PC102',1000,'GET');

insert serviceurls(servicename, serviceip, url,timeout,method) values
('LOW_STOCK','http://localhost:8383','/PC103',1000,'GET');

#Satya ADDED 10-APR-2020

insert serviceurls(servicename, serviceip, url,timeout,method) values
('SUBCAT_DETAILS','http://localhost:8383','/subcatdetails',1000,'GET'); 
 
 CREATE INDEX serviceurls_index ON serviceurls (servicename);
 
 
 #Rekha Added 09-Apr-2020
 insert serviceurls(servicename, serviceip, url,timeout,method) values
('LC_PROPS_GET','http://localhost:8282','/lc/get',1000,'GET');

insert serviceurls(servicename, serviceip, url,timeout,method) values
('LC_PROPS_POST','http://localhost:8282','/lc/get',1000,'POST');

insert serviceurls(servicename, serviceip, url,timeout,method) values
('LC_PROPS_PUT','http://localhost:8282','/lc/putprop',1000,'PUT');

insert serviceurls(servicename, serviceip, url,timeout,method) values
('LC_PROPS_GET1','http://localhost:8282','/lc/getbyid',1000,'GET');

insert serviceurls(servicename, serviceip, url, timeout, method) values
('LC_PROPS_GET2', 'http://localhost:8282', '/lc/get12', 1000, 'GET');
 
 #Satya and Rekha ADDED 14-APR-2020

insert serviceurls(servicename, serviceip, url,timeout,method) values
('PROUDCT_DETAILS_GET','http://localhost:8383','/AP100',1000,'GET'); 
 
 
 
 #April 14th 2020:Added by Aadarsh and Phani.
 insert serviceurls(servicename, serviceip, url,timeout,method) values
('APPROVAL_POST','http://localhost:8383','/approve',1000,'POST');

insert serviceurls(servicename, serviceip, url,timeout,method) values
('APPROVAL_PUT','http://localhost:8383','/approve',1000,'PUT');

insert serviceurls(servicename, serviceip, url,timeout,method) values
('APPROVAL_GET','http://localhost:8383','/approve',1000,'GET');

#Satya added 16-04-2020 sizeslist
insert serviceurls(servicename, serviceip, url,timeout,method) values
('SIZES_LIST_GET','http://localhost:8383','/sizeslist',1000,'GET');


#Queries added by Shaik
insert serviceurls(servicename, serviceip, url,timeout,method) values
('NO_PATTERNS','http://localhost:8383','/PAT001',1000,'GET');

insert serviceurls(servicename, serviceip, url,timeout,method) values
('NO_PRICE','http://localhost:8383','/PRI001',1000,'GET');

insert serviceurls(servicename, serviceip, url,timeout,method) values
('NO_PIECES','http://localhost:8383','/PIE001',1000,'GET');


insert serviceurls(servicename, serviceip, url,timeout,method) values
('PRO_DESCRIPTION','http://localhost:8383','/productdescriptions1',1000,'GET');


insert serviceurls(servicename, serviceip, url,timeout,method) values
('PRO_QUANTITY','http://localhost:8383','/productquantities1',1000,'GET');


insert serviceurls(servicename, serviceip, url,timeout,method) values
('CAT_BGU','http://localhost:8383','/catbgu',1000,'GET');

#use little_carrots_securityservice
#select * from securitykeymanagement
 CREATE INDEX securitykeymanagement_index1 ON securitykeymanagement (keyname, keyid);
#select * from serviceurls
#select servicename,url, timeout,method,serviceip from serviceurls where servicename="IMAGE_GET";

#SHOW VARIABLES LIKE 'have_query_cache';
#SET GLOBAL query_cache_size = 40000;


#28/apr/2020 by shaik
insert serviceurls(servicename, serviceip, url,timeout,method) values
('PAGENATION1','http://localhost:8383','/pp1',1000,'GET');
insert serviceurls(servicename, serviceip, url,timeout,method) values
('PAGENATION','http://localhost:8383','/pp',1000,'GET');

# 05/05/2020 added badges by rekha
insert serviceurls(servicename, serviceip, url,timeout,method) values
('BADGES_POST','http://localhost:8383','/bad',1000,'POST');

insert serviceurls(servicename, serviceip, url,timeout,method) values
('BADGES_GET','http://localhost:8383','/bad',1000,'GET');

#May 5th 2020 DisplayTitle By Aadarsh.
insert serviceurls(servicename, serviceip, url,timeout,method) values
('DISPLAY_GET','http://localhost:8383','/display',1000,'GET');

#May 6th collection sale.
insert serviceurls(servicename, serviceip, url,timeout,method) values
('COLLECTIONSALE_GET','http://localhost:8383','/collection',1000,'GET');
insert serviceurls(servicename, serviceip, url,timeout,method) values
('COLLECTIONSALE_POST','http://localhost:8383','/collection',1000,'POST');

#may 12 user by phani
insert serviceurls(servicename, serviceip, url,timeout,method) values
('USER_GET','http://localhost:8282','/user',1000,'GET');

insert serviceurls(servicename, serviceip, url,timeout,method) values
('USER_POST','http://localhost:8282','/user',1000,'POST');

insert serviceurls(servicename, serviceip, url,timeout,method) values
('USER_GET_EMAIL','http://localhost:8282','/ok',1000,'GET');

insert serviceurls(servicename, serviceip, url,timeout,method) values
('USER_GET_UNIQUEID','http://localhost:8282','/un',1000,'GET');


insert serviceurls(servicename, serviceip, url,timeout,method) values
('USER_PUT','http://localhost:8282','/user',1000,'PUT');

#select * from serviceurls;

insert serviceurls(servicename, serviceip, url,timeout,method) values
('CART_GET','http://localhost:8283','/cart',1000,'GET');
insert serviceurls(servicename, serviceip, url,timeout,method) values
('CART_GET_CUSTOMERID','http://localhost:8283','/car',1000,'GET');
insert serviceurls(servicename, serviceip, url,timeout,method) values
('CART_POST','http://localhost:8283','/cart',1000,'POST');
insert serviceurls(servicename, serviceip, url,timeout,method) values
('CART_DELETE_CARTID','http://localhost:8283','/ca',1000,'DELETE');
insert serviceurls(servicename, serviceip, url,timeout,method) values
('CART_DELETE_PRODUCTID','http://localhost:8283','/cm',1000,'DELETE');

update serviceurls set serviceip ='https://localhost:8283' where servicename='CART_GET';
update serviceurls set serviceip ='https://localhost:8283' where servicename='CART_GET_CUSTOMERID';
update serviceurls set serviceip ='https://localhost:8283' where servicename='CART_POST';
update serviceurls set serviceip ='https://localhost:8283' where servicename='CART_DELETE_CARTID';
update serviceurls set serviceip ='https://localhost:8283' where servicename='CART_DELETE_PRODUCTID';




#phani ordermanagement
insert serviceurls(servicename, serviceip, url,timeout,method) values
('ORDER_PUT','https://localhost:8283','/order',1000,'PUT');

insert serviceurls(servicename, serviceip, url,timeout,method) values
('ORDER_POST','https://localhost:8283','/order',1000,'POST');

insert serviceurls(servicename, serviceip, url,timeout,method) values
('ORDER_GET','https://localhost:8283','/order',1000,'GET');

insert serviceurls(servicename, serviceip, url,timeout,method) values
('ORDER_ITEMS_GET','https://localhost:8283','/orderitems',1000,'GET');

insert serviceurls(servicename, serviceip, url,timeout,method) values
('ORDER_GET_ORDERID','https://localhost:8283','/orderid',1000,'GET');
insert serviceurls(servicename, serviceip, url,timeout,method) values
('ORDER_GET_ITEMS_ID','https://localhost:8283','/orderitemsid',1000,'GET');

insert serviceurls(servicename, serviceip, url,timeout,method) values
('ORDER_GET_ITEMS_BY_ORDER_ID','https://localhost:8283','/orderitemsorders',1000,'GET');


------------------# Trinath (Granny Blog & Blog Report) -----------------
insert serviceurls(servicename, serviceip, url,timeout,method) values
('BLOGREPORT_GET','https://localhost:8383','/blogreport',1000,'GET');

insert serviceurls(servicename, serviceip, url,timeout,method) values
('BLOGREPORT_PUT','https://localhost:8383','/blogreport',1000,'PUT');

insert serviceurls(servicename, serviceip, url,timeout,method) values
('BLOGREPORT_POST','https://localhost:8383','/blogreport',1000,'POST');

insert serviceurls(servicename, serviceip, url,timeout,method) values
('GRANNYBLOG_GET','https://localhost:8383','/grannyblog',1000,'GET');

insert serviceurls(servicename, serviceip, url,timeout,method) values
('GRANNYBLOG_PUT','https://localhost:8383','/grannyblog',1000,'PUT');

insert serviceurls(servicename, serviceip, url,timeout,method) values
('GRANNYBLOG_POST','https://localhost:8383','/grannyblog',1000,'POST');

insert serviceurls(servicename, serviceip, url,timeout,method) values
('COLLECTIONSALE_GET_BY_GENDER','https://localhost:8383','/collection1',5000,'GET');


insert serviceurls(servicename, serviceip, url,timeout,method) values
('COLLECTIONSALEPROD_GET','http://localhost:8383','/collectionprod',1000,'GET');

insert serviceurls(servicename, serviceip, url,timeout,method) values
('COLLECTIONSALEPROD_POST','http://localhost:8383','/collectionprod',1000,'POST');

insert serviceurls(servicename, serviceip, url,timeout,method) values
('COLLECTIONSALEPROD_GET_SID','http://localhost:8383','/collectionprod1',1000,'GET');



insert serviceurls(servicename, serviceip, url,timeout,method) values
('COLLECTIONSALE_IMAGES_GET','http://localhost:8383','/collectionimage',1000,'GET');

insert serviceurls(servicename, serviceip, url,timeout,method) values
('COLLECTIONSALE_IMAGES_POST','http://localhost:8383','/collectionimage',1000,'POST');


insert serviceurls(servicename, serviceip, url,timeout,method) values
('COLLECTION_PRODUCT_INFO_GET','http://localhost:8383','/prod3',1000,'GET');

insert serviceurls(servicename, serviceip, url,timeout,method) values
('SELECTED_PRODUCTS_MULTISELECT','http://localhost:8383','/products1',1000,'GET');

insert serviceurls(servicename, serviceip, url,timeout,method) values
('COLLECTION3','http://localhost:8383','/collection3',1000,'GET');

insert serviceurls(servicename, serviceip, url,timeout,method) values
('COLLECTION2','http://localhost:8383','/collection2',1000,'GET');


insert serviceurls(servicename, serviceip, url,timeout,method) values
('PRODUCTVARIATION_GET','http://localhost:8383','/pvr1',1000,'GET');

insert serviceurls(servicename, serviceip, url,timeout,method) values
('PRODUCTVARIATION_POST','http://localhost:8383','/pvr1',1000,'POST');

insert serviceurls(servicename, serviceip, url,timeout,method) values
('SIMILARPRODUCTS_GET','http://localhost:8383','/pvsm1',1000,'GET');

insert serviceurls(servicename, serviceip, url,timeout,method) values
('SIMILARPRODUCTS_POST','http://localhost:8383','/pvsm1',1000,'POST');

insert serviceurls(servicename, serviceip, url,timeout,method) values
('SIMILAR_PRODUCTS_MULTISELECT','http://localhost:8383','/productsm',1000,'GET');

insert serviceurls(servicename, serviceip, url,timeout,method) values
('PRODUCT_VARITION_INFO','http://localhost:8383','/pvc',1000,'GET');

insert serviceurls(servicename, serviceip, url,timeout,method) values
('NEW_COLLECTION','http://localhost:8383','/NEWCOL',1000,'GET');

insert serviceurls(servicename, serviceip, url,timeout,method) values
('COLLECTION_ICONMENU','http://localhost:8383','/iconmenu',1000,'GET');

insert serviceurls(servicename, serviceip, url,timeout,method) values
('COLLECTION_SITEVIEW','http://localhost:8383','/view1menu',1000,'GET');

insert serviceurls(servicename, serviceip, url,timeout,method) values
('COLLECTION_LANDING_VIEW','http://localhost:8383','/collection4',1000,'GET');



insert serviceurls(servicename, serviceip, url,timeout,method) values
('PRODUCTKEYWORDS_POST','https://localhost:8383','/productkeywords',1000,'POST');

insert serviceurls(servicename, serviceip, url,timeout,method) values
('PRODUCTKEYWORDS_GET','https://localhost:8383','/productkeywords',1000,'GET');

insert serviceurls(servicename, serviceip, url,timeout,method) values
('PRODUCTKEYWORDS_PUT','https://localhost:8383','/productkeywords',1000,'PUT');


#Order Management
insert serviceurls(servicename, serviceip, url,timeout,method) values
('ORDERCODES_GET','https://localhost:8283','/ordercodes',1000,'GET');

insert serviceurls(servicename, serviceip, url,timeout,method) values
('ORDERCODES_POST','https://localhost:8283','/ordercodes',1000,'POST');
#select * from serviceurls where serviceip = "https://localhost:8283" and servicename like"ORDER%";

insert serviceurls(servicename, serviceip, url,timeout,method) values
('ORDER_ITEMS_GET_BY_ORDERID','https://localhost:8283','/orderitems1',1000,'GET');

insert serviceurls(servicename, serviceip, url,timeout,method) values
('ORDER_ITEMS_POST','https://localhost:8283','/orderitems',1000,'POST');
#select * from serviceurls where servicename="ORDER_ITEMS_POST";
update serviceurls set   method="POST" where servicename="ORDER_ITEMS_POST";

insert serviceurls(servicename, serviceip, url,timeout,method) values
('ORDER_GET_CUSTOMERID','https://localhost:8283','/order1',1000,'GET');

insert serviceurls(servicename, serviceip, url,timeout,method) values
('ORDER_FULL_DETAILS_BY_ORDERID','https://localhost:8283','/o101',1000,'GET');
update serviceurls set   url="/o101" where servicename="ORDER_FULL_DETAILS_BY_ORDERID";
insert serviceurls(servicename, serviceip, url,timeout,method) values
('ORDER_FULL_DETAILS_CUSTOMERID','https://localhost:8283','/o100',1000,'GET');

insert serviceurls(servicename, serviceip, url,timeout,method) values
('ORDERCODES_PUT','https://localhost:8283','/ordercodes',1000,'PUT');

insert serviceurls(servicename, serviceip, url,timeout,method) values
('RAZORPAY_GET','https://localhost:8283','/razorpay',1000,'GET');

insert serviceurls(servicename, serviceip, url,timeout,method) values
('RAZORPAY_POST','https://localhost:8283','/charge',1000,'POST');


insert serviceurls(servicename, serviceip, url,timeout,method) values
('COMPLAINT_GET','https://localhost:8283','/complaints',1000,'GET');

insert serviceurls(servicename, serviceip, url,timeout,method) values
('COMPLAINT_POST','https://localhost:8283','/complaints',1000,'POST');


insert serviceurls(servicename, serviceip, url,timeout,method) values
('CART_PUT','https://localhost:8283','/cart',1000,'PUT');

insert serviceurls(servicename, serviceip, url,timeout,method) values
('PRO_QUANTITY_PUT','https://localhost:8383','/productquantities1',1000,'PUT');


insert serviceurls(servicename, serviceip, url,timeout,method) values
('ORDER_ITEMS_STATUS_GET','https://localhost:8283','/orderitemstatus',1000,'GET');


insert serviceurls(servicename, serviceip, url,timeout,method) values
('ORDER_ITEMS_STATUS_POST','https://localhost:8283','/orderitemstatus',1000,'POST');

#Package Shipping
insert serviceurls(servicename, serviceip, url,timeout,method) values
('PACKAGESHIPPING_GET','https://localhost:8283','/ps100',1000,'GET');

insert serviceurls(servicename, serviceip, url,timeout,method) values
('PACKAGESHIPPINGBYORDERSITEMSID','https://localhost:8283','/ps101',1000,'GET');

insert serviceurls(servicename, serviceip, url,timeout,method) values
('PACKAGESHIPPING_POST','https://localhost:8283','/ps100',1000,'POST');

insert serviceurls(servicename, serviceip, url,timeout,method) values
('PACKAGESHIPPING_PUT','https://localhost:8283','/ps100',1000,'PUT');



update serviceurls set serviceip='https://localhost:8181' where serviceip='http://localhost:8181';
update serviceurls set serviceip='https://localhost:8282' where serviceip='http://localhost:8282';
update serviceurls set serviceip='https://localhost:8383' where serviceip='http://localhost:8383';
update serviceurls set serviceip='https://localhost:8281' where serviceip='http://localhost:8281';
update serviceurls set serviceip='https://localhost:8686' where serviceip='http://localhost:8686';
update serviceurls set serviceip='https://localhost:8283' where serviceip='http://localhost:8283';

use little_carrots_securityservice
update serviceurls set timeout=10000;

insert serviceurls(servicename, serviceip, url,timeout,method) values
('ORDERITEMSTATUS_BY_ITEMSID','https://localhost:8283','/orderitemstatus1',1000,'GET');

insert serviceurls(servicename, serviceip, url,timeout,method) values
('ORDER_TABLE_STAUS_PUT','https://localhost:8283','/ou',1000,'PUT');

insert serviceurls(servicename, serviceip, url,timeout,method) values
('PACKAGESHIPPINGBYORDERID','https://localhost:8283','/ps102',1000,'GET');

insert serviceurls(servicename, serviceip, url,timeout,method) values
('SUBSCRIBE_POST','https://localhost:8282','/es',1000,'POST');


insert serviceurls(servicename, serviceip, url,timeout,method) values
('SUBSCRIBE_GET','https://localhost:8282','/es',1000,'GET');

insert into serviceurls(servicename,url,timeout,method,serviceip) values('SEARCH_GET','/find',10000,'GET','https://localhost:8383')

insert serviceurls(servicename, serviceip, url,timeout,method) values
('SELECTEDPRODUCTS_POST','http://localhost:8383','/selprod',1000,'POST');
insert serviceurls(servicename, serviceip, url,timeout,method) values
('SELECTEDPRODUCTS_GENDER_GET','http://localhost:8383','/product',1000,'GET');

#May 14th 2020 Wishlist by Aadarsh.
insert serviceurls(servicename, serviceip, url,timeout,method) values
('WISHLIST_POST','http://localhost:8383','/wishlist',1000,'POST');
insert serviceurls(servicename, serviceip, url,timeout,method) values
('WISHLIST_GET','http://localhost:8383','/wishlist',1000,'GET');
insert serviceurls(servicename, serviceip, url,timeout,method) values
('WISHLIST_GET_PRODUCT_ID','http://localhost:8383','/wishlistprod',1000,'GET');
insert serviceurls(servicename, serviceip, url,timeout,method) values
('WISHLIST_DELETE','http://localhost:8383','/wishlist',1000,'DELETE');
insert serviceurls(servicename, serviceip, url,timeout,method) values
('WISHLIST_DELETE_PRODUCT_ID','http://localhost:8383','/wishlistprod',1000,'DELETE');

#May 14th 2020 Customer Address by Satya

insert serviceurls(servicename, serviceip, url,timeout,method) values
('CUSTOMERADDRESS_GET','http://localhost:8282', '/custadd', '1000', 'GET' );

insert serviceurls(servicename, serviceip, url,timeout,method) values
('CUSTOMERADDRESS_POST','http://localhost:8282','/custadd',1000,'POST');

insert serviceurls(servicename, serviceip, url,timeout,method) values
('CUSTOMERADDRESS_PUT','http://localhost:8282','/custadd',1000,'PUT');

insert serviceurls(servicename, serviceip, url,timeout,method) values
('CUSTOMERADDRESS_GET_BY_ADDRESS','http://localhost:8282','/custadd1',1000,'GET');



#may 13 Search shaik
insert serviceurls(servicename, serviceip, url,timeout,method) values
('SEARCH_GET','http://localhost:8383','/find',1000,'GET');

#may 13th selectedproducts
insert serviceurls(servicename, serviceip, url,timeout,method) values
('SELECTEDPRODUCTS_GET','http://localhost:8383','/selprod',1000,'GET');


#grannyblog latest5
insert serviceurls(servicename, serviceip, url,timeout,method) values
('GRANNYBLOG_LATEST5_GET','https://localhost:8383','/grannyblog5',5000,'GET');

#Selected product to extarct only 4
insert serviceurls(servicename, serviceip, url,timeout,method) values
('SELECTEDPRODUCTS_GET_4','https://localhost:8383','/selprod4',5000,'GET');



# by shaik, security service get only byte[] of image
insert serviceurls(servicename, serviceip, url, timeout, method) values
('IMAGE_GET4', 'https://localhost:8686', '/img/v1/getallimg4', 10000, 'GET');



insert serviceurls(servicename, serviceip, url, timeout, method) values
('SCROL_SEARCH_GET', 'https://127.0.0.1:8383', '/SS100', 10000, 'GET');

insert serviceurls(servicename, serviceip, url, timeout, method) values
('SCROL_SEARCH_GET_INFO', 'https://127.0.0.1:8383', '/SS101', 10000, 'GET');


insert serviceurls(servicename, serviceip, url, timeout, method) values
('IMAGE_GET_S2', 'https://127.0.0.1:8080', '/img2/v1/getallimg', 10000, 'GET');


insert serviceurls(servicename, serviceip, url, timeout, method) values
('CACHE_DATA1', 'https://127.0.0.1:8383', '/CT099', 10000, 'GET');

insert serviceurls(servicename, serviceip, url, timeout, method) values
('PRODUCTVARIATION_PRODUCTIDS', 'https://127.0.0.1:8383', '/pvp', 10000, 'GET');


insert serviceurls(servicename, serviceip, url, timeout, method) values
('IMAGE_GET_S2', 'https://127.0.0.1:2053', '/img2/v1/getallimg', 10000, 'GET');


--#24-NOV-2020 db queries
use little_carrots_securityservice;
insert serviceurls(servicename, serviceip, url, timeout, method) values
('CONTEST', 'https://127.0.0.1:8282', '/contest', 10000, 'POST');
insert serviceurls(servicename, serviceip, url, timeout, method) values
('CONTEST_GET', 'https://127.0.0.1:8282', '/contest', 10000, 'GET');
insert serviceurls(servicename, serviceip, url, timeout, method) values
('CONTEST_GET1', 'https://127.0.0.1:8282', '/contest1', 10000, 'GET');
insert serviceurls(servicename, serviceip, url, timeout, method) values
('CONTEST_GET2', 'https://127.0.0.1:8282', '/contest2', 10000, 'GET');
insert serviceurls(servicename, serviceip, url, timeout, method) values
('CONTEST_GET3', 'https://127.0.0.1:8282', '/contest3', 10000, 'GET');
insert serviceurls(servicename, serviceip, url, timeout, method) values
('CONTEST_GET4', 'https://127.0.0.1:8282', '/contest4', 10000, 'GET');
insert serviceurls(servicename, serviceip, url, timeout, method) values
('CONTEST_GET_MONTH', 'https://127.0.0.1:8282', '/contestMonth', 10000, 'GET');
insert serviceurls(servicename, serviceip, url, timeout, method) values
('CONTEST_GET_MONTH1', 'https://127.0.0.1:8282', '/contestMonth1', 10000, 'GET');

use little_carrots_securityservice
insert serviceurls(servicename, serviceip, url, timeout, method) values
('MONTHS', 'https://127.0.0.1:8282', '/conmon', 10000, 'GET');


insert serviceurls(servicename, serviceip, url, timeout, method) values
('IMGLOC', 'https://127.0.0.1:8282', '/imgloc', 10000, 'GET');


#sheikh coupons rest templage urls
insert serviceurls(servicename, serviceip, url, timeout, method) values
('COUPON_POST', 'https://127.0.0.1:8283', '/coupon', 10000, 'POST');
insert serviceurls(servicename, serviceip, url, timeout, method) values
('COUPON_GET', 'https://127.0.0.1:8283', '/coupon', 10000, 'GET');

insert serviceurls(servicename, serviceip, url, timeout, method) values
('COUPON_PUT', 'https://127.0.0.1:8283', '/couponapplied', 10000, 'PUT');

//February 10 -- Satya  
insert serviceurls(servicename, serviceip, url,timeout,method) values
('COMPLAINT_PUT','https://localhost:8283','/complaints',1000,'PUT');

insert serviceurls(servicename, serviceip, url, timeout, method) values
('SOURCE_GET', 'https://127.0.0.1:8283', '/sources', 10000, 'GET');

insert serviceurls(servicename, serviceip, url, timeout, method) values
('INVOICES_GET', 'https://127.0.0.1:8283', '/invoices', 10000, 'GET');

insert serviceurls(servicename, serviceip, url, timeout, method) values
('STATUS_GET', 'https://127.0.0.1:8283', '/status', 10000, 'GET');

insert serviceurls(servicename, serviceip, url, timeout, method) values
('SOLD_PRODUCTS_GET', 'https://127.0.0.1:8283', '/soldproducts', 10000, 'GET');

use little_carrots_securityservice;
insert serviceurls(servicename, serviceip, url, timeout, method) values
('INVOICE_POST', 'https://127.0.0.1:8283', '/invoices', 10000, 'POST');

insert serviceurls(servicename, serviceip, url, timeout, method) values
('SALE_TIME_GET', 'https://127.0.0.1:8283', '/st', 10000, 'GET');

insert serviceurls(servicename, serviceip, url, timeout, method) values
('FULL_INFO', 'https://127.0.0.1:8283', '/fi', 10000, 'GET');
update serviceurls set timeout=10000 where servicename="FULL_INFO";

insert serviceurls(servicename, serviceip, url, timeout, method) values
('PDFUPLOAD', 'https://127.0.0.1:8283', '/uploadpdf', 10000, 'POST');

insert serviceurls(servicename, serviceip, url, timeout, method) values
('PDFLOC', 'https://127.0.0.1:8283', '/pdfloc', 10000, 'GET');

//Februrary 12-02-2021
create table notifications(
		id bigint auto_increment,
		title varchar(100),
        image longblob,
        description varchar(1000),
        date date,
        delaydate date,
        url varchar(200),
		constraint notifications_pk primary key (id)
		);
		
		
		
		
insert into serviceurls(servicename, url, timeout,method,serviceip) values('IMAGE_GET5', '/img2/v1/IMG13', 10000, 'GET', 'https://lc-image-service:2053');


insert serviceurls(servicename, serviceip, url, timeout, method) values
('COMBOS_GET', 'https://localhost:8383', '/combos', 10000, 'GET');

        
insert serviceurls(servicename, serviceip, url, timeout, method) values
('COMBOS_POST', 'https://localhost:8383', '/combos', 10000, 'POST');

insert serviceurls(servicename, serviceip, url, timeout, method) values
('COMBOS__PROD_POST', 'https://localhost:8383', '/comboprod', 10000, 'POST');


insert serviceurls(servicename, serviceip, url, timeout, method) values
('COMBOS_FULL_DETAILS', 'https://localhost:8383', '/combo101', 10000, 'GET');
SELECT 
    *
FROM
    serviceurls;
insert serviceurls(servicename, serviceip, url, timeout, method) values
('COMBOID_WITHOUT_DETAILS', 'https://localhost:8383', '/ncombo', 10000, 'GET');

insert serviceurls(servicename, serviceip, url, timeout, method) values
('VALIDATE_MAIL_PHONE', 'https://localhost:8282', '/uservali', 10000, 'GET');


#seller details, rack details 23-Oct-2021
insert serviceurls(servicename, serviceip, url,timeout,method) values
('SELLER_IDENTITY_GET','https://localhost:8383','/spil',1000,'GET');

insert serviceurls(servicename, serviceip, url,timeout,method) values
('SELLER_IDENTITY_POST','https://localhost:8383','/spil',1000,'POST');

insert serviceurls(servicename, serviceip, url,timeout,method) values
('SELLER_IDENTITY_PUT','https://localhost:8383','/spil',1000,'PUT');


insert serviceurls(servicename, serviceip, url,timeout,method) values
('RACKS_GET','https://localhost:8383','/rack',1000,'GET');

insert serviceurls(servicename, serviceip, url,timeout,method) values
('RACKS_POST','https://localhost:8383','/rack',1000,'POST');


insert serviceurls(servicename, serviceip, url,timeout,method) values
('RACK_IDENTITY_GET','https://localhost:8383','/rackidentity',1000,'GET');

insert serviceurls(servicename, serviceip, url,timeout,method) values
('RACK_IDENTITY_POST','https://localhost:8383','/rackidentity',1000,'POST');

insert serviceurls(servicename, serviceip, url,timeout,method) values
('RACK_IDENTITY_PUT','https://localhost:8383','/rackidentity',1000,'PUT');