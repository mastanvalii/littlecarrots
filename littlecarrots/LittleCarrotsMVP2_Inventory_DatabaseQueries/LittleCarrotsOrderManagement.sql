#drop database little_carrots_orders;
create database little_carrots_orders;

use little_carrots_orders;


create table cart (
cartid bigint auto_increment,
customerid bigint,
productid bigint,
productprice double,
quantity bigint,
cartdate date, 
totalprice bigint,
constraint cart_pk primary key (cartid)
);


create table cod(
paymenttransactionid bigint auto_increment,
orderdate datetime not null,
customerid bigint,
totalamount bigint,
deliverydate datetime,
constraint orders_pk primary key (paymenttransactionid)
);


create table ordercodes(
ordercodeid bigint,
remark text,
constraint ordercode_pk primary key (ordercodeid)
);


create table orders(
orderid bigint auto_increment,
orderdate datetime not null,
customerid bigint,
totalprice float,
ordercodeid bigint,
gateway_orderid varchar(500) ,
addressid bigint,
constraint orders_pk primary key (orderid),
constraint orders_fk foreign key (ordercodeid) references ordercodes (ordercodeid)
);


create table orderitems (
orderitemsid bigint auto_increment,
orderid bigint,
productid bigint,
qty bigint,
productprice float not null,
deliverydate datetime not null,
ordercodeid bigint,
constraint orderitems_pk primary key (orderitemsid),
constraint orderitems_fk1 foreign key (orderid) references orders(orderid),
constraint orderitems_fk2 foreign key (ordercodeid) references ordercodes (ordercodeid)
);


create table shipping(
shippingid bigint,
orderid bigint,
customerid bigint,
shippingstatus text,
courierid bigint,
couriercompany text,
shippingdate datetime,
deliverydate datetime,
constraint shipping_pk primary key (shippingid),
constraint shipping_fk1 foreign key (orderid) references orders(orderid)
);


create table razorpaytransaction(
razorpay_order_id varchar(500),
razorpay_signature varchar(500),
razorpay_payment_id varchar(500),
customerid bigint,
orderid bigint,
constraint razorpay_pk primary key (razorpay_order_id)
);


create table complaintbox(
		complaintid bigint auto_increment,
		fullname varchar(100),
		email varchar(100),
		phone bigint,
		issuecategory varchar(100),
		issuesubcategory varchar(100),
		reffile longblob,
		details varchar(1000),
		status varchar(1000),
		constraint complaintbox_pk primary key (complaintid)
		);
        
        
create table orderstatus(
statusid bigint  auto_increment, 
orderid bigint,
ordercodeid bigint,
ondate timestamp,
information varchar(2000),
constraint statusid_pk primary key (statusid),
constraint orderstatus_fk1 foreign key (orderid) references orders(orderid),
constraint orderstatus_fk2 foreign key (ordercodeid) references ordercodes (ordercodeid)
);

create table orderitemsstatus(
itemstatusid bigint  auto_increment, 
orderitemsid bigint,
orderid bigint,
ordercodeid bigint,
ondate timestamp,
information varchar(2000),
constraint itemstatusid_pk primary key (itemstatusid),
constraint orderstatus_fk11 foreign key (orderid) references orders(orderid),
constraint orderstatus_fk21 foreign key (ordercodeid) references ordercodes (ordercodeid),
constraint orderstatus_fk31 foreign key (orderitemsid) references orderitems (orderitemsid)
);

create table packageshipping(
sid bigint auto_increment,
orderid bigint,
orderitemsid bigint,
shippingorderid varchar(100),
shippingservice varchar(1000),
awb varchar(100),
trackingurl varchar(2000),
information varchar(2000),
constraint shipping_pk11 primary key (sid),
constraint orderstatus_fk111 foreign key (orderid) references orders(orderid),
constraint orderstatus_fk311 foreign key (orderitemsid) references orderitems (orderitemsid)
);



insert into ordercodes(ordercodeid,remark)values(1001, 'PAYMENT PENDING');
insert into ordercodes(ordercodeid,remark)values(1002, 'PAYMENT SUCCESS');
insert into ordercodes(ordercodeid,remark)values(1003, 'ORDER ACCEPTED');
insert into ordercodes(ordercodeid,remark)values(1004, 'READY TO SHIP');
insert into ordercodes(ordercodeid,remark)values(1005, 'DISPATCHED');
insert into ordercodes(ordercodeid,remark)values(1006, 'DELIVERED');
insert into ordercodes(ordercodeid,remark)values(2001, 'ORDER CANCELLED');
insert into ordercodes(ordercodeid,remark)values(2002, 'ORDER RETURN');
insert into ordercodes(ordercodeid,remark)values(3001, 'PAYMENT FAILED');

ALTER TABLE complaintbox DROP COLUMN reffile;

create table coupons(
		couponid bigint auto_increment,
			coupon varchar(200) unique,
			startdate DATEtime,
			enddate DATEtime,
			discount long,
			minbill long,
			title varchar(200),
			constraint coupons_pk primary key (couponid)
		);

create table couponused(
	couponusedid bigint auto_increment,
    couponid bigint,
    customerid bigint,
    constraint couponused_pk primary key (couponusedid),
    constraint couponused_fk foreign key (couponid) references coupons(couponid)
    );

//February 10 -- Satya  
drop table complaintbox;
create table complaintbox(
		complaintid bigint auto_increment,
		fullname varchar(100),
		email varchar(100),
		phone bigint,
		issuecategory varchar(100),
		issuesubcategory varchar(100),
        dateinsertion dateTime,
		#reffile blob,
		details varchar(1000),
		status varchar(1000),
		constraint complaintbox_pk primary key (complaintid)
		);

create table sourcesales(
		sid bigint auto_increment,
		source varchar(100),
		constraint sourcesales_pk primary key (sid)
		);
		

create table invoices(
		inid bigint auto_increment,
        sid bigint,
		orid varchar(100),
        dateoforder date,
        dateofinvoice date,
		total float,
        shipping float,
        paymentmode varchar(100),
        transactionid varchar(100),
        statusoftheinvoice varchar(100),
        user varchar(60),
        invoiceid varchar(100),
		constraint invoices_pk primary key (inid),
        constraint invoices_fk1 foreign key (sid) references sourcesales(sid)
		);
		

create table soldproductstatus(
	 status varchar(100),
	 constraint soldproductstatus_pk primary key (status)
);

INSERT INTO soldproductstatus VALUES('DELIVERED');
INSERT INTO soldproductstatus VALUES('RETURN');
INSERT INTO soldproductstatus VALUES('CANCELLED');
INSERT INTO soldproductstatus VALUES('EXCHANGE');

create table soldproducts(
		saleprid bigint auto_increment,
		inid bigint,
        prid bigint,
        qty bigint,
		netamount float,
        gst float,
		discount float,
        totalproductprice float,
        status varchar(100),
		constraint soldproducts_pk primary key (saleprid),
        constraint soldproducts_fk1 foreign key (inid) references invoices(inid),
        constraint soldproducts_fk2 foreign key (status) references soldproductstatus(status)
		);
        

create table invoicespdf(
		pdfid bigint auto_increment,
		inid bigint,
        filename varchar(200),
		constraint invoicespdf_pk primary key (pdfid),
        constraint invoicespdf_fk1 foreign key (inid) references invoices(inid)
		);
		


		# report state by Shiekh  03-Nov-2021
		
create table solditems(
	itemssoldid bigint auto_increment,
	skuid bigint,							# lc skuid
	qty bigint,								# qty sold
	netamount float,						# product amount 
    invoiceid bigint,
    constraint solditems_pk1 primary key (itemssoldid),
    constraint salesreport_fk2 foreign key(invoiceid) references salesreport(invoiceid)
);

create table salesreport(
invoiceid bigint auto_increment,    	#lc id
soldfrom varchar(100),					#sold from [web, local, amazon, flipkart, other]
orderid varchar(100),					# order id only for third party sellers
sellerinvoice varchar(100),				# invoice id only for third party sellers
awb varchar(100),						# awb number only for third party sellers
trackingid varchar(100),				# tracking id for both third party and lc
state varchar(100),						# sold to the specific state
hsn varchar(100),						# hsn code
tax bigint,								# tax percentage
taxtype varchar(100),					# tax type IGST
discount float,							# discount amount
taxamount float,						# tax amount
totalamount float,						# total amount
paidtype varchar(200),					# paid type [gpay, phonepe, cash, thirdparty sells]
transactionid varchar(200),				# only transaction id 
constraint salesreport_pk1 primary key (invoiceid)
);

ALTER TABLE salesreport ADD invoicedate datetime , add orderdate datetime;
