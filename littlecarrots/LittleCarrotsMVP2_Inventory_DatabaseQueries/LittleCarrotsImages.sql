#drop database little_carrots_images;
create database little_carrots_images;
#create user 'little_carrots_images'@'localhost' identified by 'littleinventory';
#Granting all permission to the user 'LittleInventory'
#GRANT ALL PRIVILEGES ON little_carrots_images.* TO 'littleinventory'@'localhost';
use little_carrots_images;


create table images(imgid  bigint AUTO_INCREMENT, productid bigint,images longblob,imageurl varchar(50),
constraint images_pk1 primary key (imgid));


create index images_index on images(imgid,productid);
create index images_index_productid on images(productid);
create index images_index_imgid on images(imgid);
ALTER TABLE images MODIFY imageurl VARCHAR(2000) ;
select * from images;


create table imagelocation(imgid  bigint AUTO_INCREMENT, productid bigint,image varchar(1000),
constraint imagelocation_pk1 primary key (imgid));

select distinct productid from imagelocation;
