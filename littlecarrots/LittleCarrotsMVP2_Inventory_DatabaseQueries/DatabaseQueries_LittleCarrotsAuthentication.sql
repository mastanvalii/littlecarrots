create database little_carrots_authentications;
#CreatING User 'little_carrots_authentications'
#create user 'littleauthentication'@'localhost' identified by 'littleauthentication';
#Granting all permission to the user 'LittleInventory'
#GRANT ALL PRIVILEGES ON little_carrots_authentications.* TO 'littleauthentication'@'localhost';

use little_carrots_authentications;


create table authorization(
RoleName 	varchar(20),	
Descriptions	varchar(200),
authorization_status boolean,
constraint authorization_pk1 primary key (ROLENAME));

create table authorizedusers(	
username	varchar(60)	,	
email	varchar(60)	Unique Not Null	,
password	varchar(60)	,	
authuserstatus	boolean	,	
rolename varchar(50)	,
constraint authorizedusers_pk primary key (username),
constraint authorizedusers_fk foreign key (rolename) references authorization(rolename)
);

#----13-01-2020--- data queries
alter table authorizedusers add column lastlogindate datetime;
ALTER TABLE authorizedusers MODIFY password VARCHAR(1500);

select * from authorization;
select * from authorizedUsers;

create table Seller
(
sellerid	bigint auto_increment,
sellername	varchar(100),	
sellercompanyname	varchar(100) unique,	
phonenumber	bigint,	
address	varchar(2000)	,
email	varchar(100)	,
isocode	varchar(10)	,
status boolean,
constraint seller_pk primary key (sellerid)
);

create table sellerusers
(
username varchar(100), 
password varchar(2000),
email varchar(200) unique,
sellerid bigint, 
fullname varchar(100),
status boolean,
rolename varchar(100),
lastlogin	date,		
constraint selleruser_pk primary key (username),
constraint selleruser_fk foreign key (sellerid) references seller(sellerid),
constraint selleruser_fk1 foreign key (rolename) references authorization(rolename)
);

#use little_carrots_authentications;
#select * from sellerusers;
create table securitykeymanagement(
keyid bigint auto_increment,
keyname varchar(100),
keyvalue varchar(1000),
constraint securitykeymanagement_pk primary key (keyid)
);

ALTER TABLE securitykeymanagement AUTO_INCREMENT = 1000;

insert securitykeymanagement(keyname, keyvalue) values('AUTH_API_ACCESS_KEY', 'LRtt3I-ARtlos-51TCOi-er1212-1819TE-Tlcr92-01820L-Sa2015');
insert securitykeymanagement(keyname, keyvalue) values('SECU_API_ACCESS_KEY', 'LSt15I-Tlos18-20TOir-121819-TRtr91-LRta20-3EALC2-05ce12');
insert securitykeymanagement(keyname, keyvalue) values('CLIENT_API_ACCESS_KEY', 'Li20Il-t920TS-t1212T-Tls5LO-et319E-Rco120-CRar18-15Ar18');

#09-MAR-2020 SHAIK ADDED QUERIES
insert securitykeymanagement(keyname, keyvalue) values('EMAIL_API_ACCESS_KEY', 'Li20Il-t920TS-t1212T-Tls5LO-et319E-Rco120-CRar18-15Ar18');


create table serviceurls
(
servicename varchar(30) unique,
url varchar(1000),
timeout bigint,
method varchar(20),
constraint serviceurls_pk2 primary key (servicename)

);
alter table serviceurls add column serviceip varchar(1000);
insert serviceurls(servicename, serviceip, url, timeout, method) values
('EMAIL_POST', 'http://localhost:8281', '/mail/v1/push1', 10000, 'POST');


create table lc_props(
propid bigint auto_increment,
prop varchar(100) unique,
status boolean,
constraint lcprop_pk primary key (propid)
);

insert into lc_props(prop, status) values('EMAIL_SERVICE', TRUE);

create table lcprops(
propid bigint auto_increment,
prop varchar(100) unique,
status boolean,
constraint lcprop_pk1 primary key (propid)
);

insert into lcprops(prop, status) values('EMAIL_SERVICE', TRUE);



create table UserEmails(
id bigint auto_increment,
username varchar(100),
rolename varchar(100),
email varchar(1000),
subject varchar(1000),
message varchar(1000),
sentdate datetime,
constraint useremail_pk primary key (id),
constraint useremail_fk foreign key (rolename) references authorization(rolename)
);


            
#lilcustomer table by phani

create table lilcustomer(
userid bigint auto_increment,
uniqueid varchar(150) unique,
email varchar(150) unique ,
firstname varchar(50),
lastname varchar(50),
imageurl varchar(150),
phone bigint(15) ,
usertype varchar(50),
status boolean,
constraint lilcustomer_pk primary key (userid),
constraint lilcustomer_fk1 foreign key (usertype) references authorization(rolename));

alter table lilcustomer modify column uniqueid varchar(150);

#Customer Address table by Satya
create table customeraddress (
			addressid  bigint auto_increment,
            name varchar(100),
			email varchar(150),
			flat varchar(500),
			streetaddress varchar(500),
			landmark varchar(300),
			pincode bigint(7),
			city varchar(200),
			state varchar(200),
            country varchar(50),
			mobile1 bigint(11),
			mobile2 bigint(11),
			addresstype varchar(100),
			constraint customeraddress_pk primary key (addressid),
			constraint customeraddress_fk foreign key (email) references lilcustomer(email)
			);
            
            
            update serviceurls set serviceip='https://localhost:8181' where serviceip='http://localhost:8181';
update serviceurls set serviceip='https://localhost:8282' where serviceip='http://localhost:8282';
update serviceurls set serviceip='https://localhost:8383' where serviceip='http://localhost:8383';
update serviceurls set serviceip='https://localhost:8281' where serviceip='http://localhost:8281';
update serviceurls set serviceip='https://localhost:8686' where serviceip='http://localhost:8686';
update serviceurls set serviceip='https://localhost:8283' where serviceip='http://localhost:8283';


#Email Subscribe By Satya

create table emailsubscribe(
emailid varchar(200),
constraint useremail_pk primary key (emailid)
);

use little_carrots_authentications;
#Contest
create table contest(
emailid varchar(200) unique,
phone bigint(11) unique,
instaid varchar(200) unique,
childname varchar(200),
age varchar(200),
tc boolean,
images longblob,
dateinsertion	DATEtime,
contestmonthyear varchar(200),
constraint contest_pk primary key (emailid)
);
drop table contest;
use little_carrots_authentications;
select * from contest order by emailid;
select * from contest where emailid="saisatyasiripurapu1@gmail.com"
delete  from contest where emailid="saisatya1@littlecarrots.com"