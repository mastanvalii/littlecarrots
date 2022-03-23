package com.lc.sk.inventory.security.beans;

import java.io.Serializable;
import java.sql.Timestamp;

public class ProductsActive implements Serializable{
	
	long productid;
	long descriptionid;
	String description;
	long batchid;
	long warehouseid;
	Timestamp dateofpurchase;
	String purchasedby;
	double invamount;
	String whoinserted;
	Timestamp dateinsertion;
	boolean status;
	String isocode;
	long qty;
	long sellerid;
	//long warehouseid;
	String warehousename;
	String warehousecontactpersonname;
	long warehousecontactphone;
	String warehouseemail;
	String warehouseaddress;
	long warehousepincode;
	long warehouseest;
	boolean warehousestatus;
	String warehousewhocreated;
	Timestamp warehousedateofcreation;
	String warehousewhoupdated;
	Timestamp warehousemodifydate;
	String warehouseisocode;
	//long sellerid;
	String sellername;
	String sellercompanyname;
	long sellerphone;
	String selleraddress;
	String selleremail;
	String sellerisocode;
	boolean sellerstatus;
//	String genderid;
//	String gender;
	long catid;
	String category;
	String gender;
	//long catid;
	long subcatid;
	String categoryname;
	long seasonId;
	//long subcatId;
	String season;
	int occasionid;
	String occaname;
	//private long subcatid;
//	String ageid;
//	String des;
	long materialid;
	String materialname;
	String materialdescription;
//	int ocid;
//	long seasonid;
//	long catid;
	long colorid;
	String colorname;
	String hashcode;
	long custid;
	long quantity;
	long pqsizeid;
	long sizeid;
	//String ageid;
	//String gender;
	String sizeno;
	String ageid;
	String height;
	String weight;
	String chest;
	String waist;
	String hip;
	Boolean productstatus;
	
	
	public ProductsActive() {
		super();
		// TODO Auto-generated constructor stub
	}


	


	public ProductsActive(long productid, long descriptionid, String description, long batchid, long warehouseid,
			Timestamp dateofpurchase, String purchasedby, double invamount, String whoinserted, Timestamp dateinsertion,
			boolean status, String isocode, long qty, long sellerid, String warehousename,
			String warehousecontactpersonname, long warehousecontactphone, String warehouseemail,
			String warehouseaddress, long warehousepincode, long warehouseest, boolean warehousestatus,
			String warehousewhocreated, Timestamp warehousedateofcreation, String warehousewhoupdated,
			Timestamp warehousemodifydate, String warehouseisocode, String sellername, String sellercompanyname,
			long sellerphone, String selleraddress, String selleremail, String sellerisocode, boolean sellerstatus,
			long catid, String category, String gender, long subcatid, String categoryname, long seasonId,
			String season, int occasionid, String occaname, long materialid, String materialname,
			String materialdescription, long colorid, String colorname, String hashcode, long custid, long quantity,
			long pqsizeid, long sizeid, String sizeno, String ageid, String height, String weight, String chest,
			String waist, String hip, Boolean productstatus) {
		super();
		this.productid = productid;
		this.descriptionid = descriptionid;
		this.description = description;
		this.batchid = batchid;
		this.warehouseid = warehouseid;
		this.dateofpurchase = dateofpurchase;
		this.purchasedby = purchasedby;
		this.invamount = invamount;
		this.whoinserted = whoinserted;
		this.dateinsertion = dateinsertion;
		this.status = status;
		this.isocode = isocode;
		this.qty = qty;
		this.sellerid = sellerid;
		this.warehousename = warehousename;
		this.warehousecontactpersonname = warehousecontactpersonname;
		this.warehousecontactphone = warehousecontactphone;
		this.warehouseemail = warehouseemail;
		this.warehouseaddress = warehouseaddress;
		this.warehousepincode = warehousepincode;
		this.warehouseest = warehouseest;
		this.warehousestatus = warehousestatus;
		this.warehousewhocreated = warehousewhocreated;
		this.warehousedateofcreation = warehousedateofcreation;
		this.warehousewhoupdated = warehousewhoupdated;
		this.warehousemodifydate = warehousemodifydate;
		this.warehouseisocode = warehouseisocode;
		this.sellername = sellername;
		this.sellercompanyname = sellercompanyname;
		this.sellerphone = sellerphone;
		this.selleraddress = selleraddress;
		this.selleremail = selleremail;
		this.sellerisocode = sellerisocode;
		this.sellerstatus = sellerstatus;
		this.catid = catid;
		this.category = category;
		this.gender = gender;
		this.subcatid = subcatid;
		this.categoryname = categoryname;
		this.seasonId = seasonId;
		this.season = season;
		this.occasionid = occasionid;
		this.occaname = occaname;
		this.materialid = materialid;
		this.materialname = materialname;
		this.materialdescription = materialdescription;
		this.colorid = colorid;
		this.colorname = colorname;
		this.hashcode = hashcode;
		this.custid = custid;
		this.quantity = quantity;
		this.pqsizeid = pqsizeid;
		this.sizeid = sizeid;
		this.sizeno = sizeno;
		this.ageid = ageid;
		this.height = height;
		this.weight = weight;
		this.chest = chest;
		this.waist = waist;
		this.hip = hip;
		this.productstatus = productstatus;
	}





	public long getproductid() {
		return productid;
	}


	public void setproductid(long productid) {
		this.productid = productid;
	}


	public long getdescriptionid() {
		return descriptionid;
	}


	public void setdescriptionid(long descriptionid) {
		this.descriptionid = descriptionid;
	}


	public String getdescription() {
		return description;
	}


	public void setdescription(String description) {
		this.description = description;
	}


	public long getbatchid() {
		return batchid;
	}


	public void setbatchid(long batchid) {
		this.batchid = batchid;
	}


	public long getwarehouseid() {
		return warehouseid;
	}


	public void setwarehouseid(long warehouseid) {
		this.warehouseid = warehouseid;
	}


	public Timestamp getdateofpurchase() {
		return dateofpurchase;
	}


	public void setdateofpurchase(Timestamp dateofpurchase) {
		this.dateofpurchase = dateofpurchase;
	}


	public String getpurchasedby() {
		return purchasedby;
	}


	public void setpurchasedby(String purchasedby) {
		this.purchasedby = purchasedby;
	}


	public double getinvamount() {
		return invamount;
	}


	public void setinvamount(double invamount) {
		this.invamount = invamount;
	}


	public String getwhoinserted() {
		return whoinserted;
	}


	public void setwhoinserted(String whoinserted) {
		this.whoinserted = whoinserted;
	}


	public Timestamp getdateinsertion() {
		return dateinsertion;
	}


	public void setdateinsertion(Timestamp dateinsertion) {
		this.dateinsertion = dateinsertion;
	}


	public boolean isstatus() {
		return status;
	}


	public void setstatus(boolean status) {
		this.status = status;
	}


	public String getisocode() {
		return isocode;
	}


	public void setisocode(String isocode) {
		this.isocode = isocode;
	}


	public long getqty() {
		return qty;
	}


	public void setqty(long qty) {
		this.qty = qty;
	}


	public long getsellerid() {
		return sellerid;
	}


	public void setsellerid(long sellerid) {
		this.sellerid = sellerid;
	}


	public String getwarehousename() {
		return warehousename;
	}


	public void setwarehousename(String warehousename) {
		this.warehousename = warehousename;
	}


	public String getwarehousecontactpersonname() {
		return warehousecontactpersonname;
	}


	public void setwarehousecontactpersonname(String warehousecontactpersonname) {
		this.warehousecontactpersonname = warehousecontactpersonname;
	}


	public long getwarehousecontactphone() {
		return warehousecontactphone;
	}


	public void setwarehousecontactphone(long warehousecontactphone) {
		this.warehousecontactphone = warehousecontactphone;
	}


	public String getwarehouseemail() {
		return warehouseemail;
	}


	public void setwarehouseemail(String warehouseemail) {
		this.warehouseemail = warehouseemail;
	}


	public String getwarehouseaddress() {
		return warehouseaddress;
	}


	public void setwarehouseaddress(String warehouseaddress) {
		this.warehouseaddress = warehouseaddress;
	}


	public long getwarehousepincode() {
		return warehousepincode;
	}


	public void setwarehousepincode(long warehousepincode) {
		this.warehousepincode = warehousepincode;
	}


	public long getwarehouseest() {
		return warehouseest;
	}


	public void setwarehouseest(long warehouseest) {
		this.warehouseest = warehouseest;
	}


	public boolean iswarehousestatus() {
		return warehousestatus;
	}


	public void setwarehousestatus(boolean warehousestatus) {
		this.warehousestatus = warehousestatus;
	}


	public String getwarehousewhocreated() {
		return warehousewhocreated;
	}


	public void setwarehousewhocreated(String warehousewhocreated) {
		this.warehousewhocreated = warehousewhocreated;
	}


	public Timestamp getwarehousedateofcreation() {
		return warehousedateofcreation;
	}


	public void setwarehousedateofcreation(Timestamp warehousedateofcreation) {
		this.warehousedateofcreation = warehousedateofcreation;
	}


	public String getwarehousewhoupdated() {
		return warehousewhoupdated;
	}


	public void setwarehousewhoupdated(String warehousewhoupdated) {
		this.warehousewhoupdated = warehousewhoupdated;
	}


	public Timestamp getwarehousemodifydate() {
		return warehousemodifydate;
	}


	public void setwarehousemodifydate(Timestamp warehousemodifydate) {
		this.warehousemodifydate = warehousemodifydate;
	}


	public String getwarehouseisocode() {
		return warehouseisocode;
	}


	public void setwarehouseisocode(String warehouseisocode) {
		this.warehouseisocode = warehouseisocode;
	}


	public String getSellername() {
		return sellername;
	}


	public void setSellername(String sellername) {
		this.sellername = sellername;
	}


	public String getSellercompanyname() {
		return sellercompanyname;
	}


	public void setSellercompanyname(String sellercompanyname) {
		this.sellercompanyname = sellercompanyname;
	}


	public long getSellerphone() {
		return sellerphone;
	}


	public void setSellerphone(long sellerphone) {
		this.sellerphone = sellerphone;
	}


	public String getSelleraddress() {
		return selleraddress;
	}


	public void setSelleraddress(String selleraddress) {
		this.selleraddress = selleraddress;
	}


	public String getSelleremail() {
		return selleremail;
	}


	public void setSelleremail(String selleremail) {
		this.selleremail = selleremail;
	}


	public String getSellerisocode() {
		return sellerisocode;
	}


	public void setSellerisocode(String sellerisocode) {
		this.sellerisocode = sellerisocode;
	}


	public boolean isSellerstatus() {
		return sellerstatus;
	}


	public void setSellerstatus(boolean sellerstatus) {
		this.sellerstatus = sellerstatus;
	}


	public long getcatid() {
		return catid;
	}


	public void setcatid(long catid) {
		this.catid = catid;
	}


	public String getcategory() {
		return category;
	}


	public void setcategory(String category) {
		this.category = category;
	}


	public String getgender() {
		return gender;
	}


	public void setgender(String gender) {
		this.gender = gender;
	}


	public long getsubcatid() {
		return subcatid;
	}


	public void setsubcatid(long subcatid) {
		this.subcatid = subcatid;
	}


	public String getcategoryname() {
		return categoryname;
	}


	public void setcategoryname(String categoryname) {
		this.categoryname = categoryname;
	}


	public long getseasonId() {
		return seasonId;
	}


	public void setseasonId(long seasonId) {
		this.seasonId = seasonId;
	}


	public String getseason() {
		return season;
	}


	public void setseason(String season) {
		this.season = season;
	}


	public int getoccasionid() {
		return occasionid;
	}


	public void setoccasionid(int occasionid) {
		this.occasionid = occasionid;
	}


	public String getOccaname() {
		return occaname;
	}


	public void setOccaname(String occaname) {
		this.occaname = occaname;
	}


	public long getmaterialid() {
		return materialid;
	}


	public void setmaterialid(long materialid) {
		this.materialid = materialid;
	}


	public String getmaterialname() {
		return materialname;
	}


	public void setmaterialname(String materialname) {
		this.materialname = materialname;
	}


	public String getmaterialdescription() {
		return materialdescription;
	}


	public void setmaterialdescription(String materialdescription) {
		this.materialdescription = materialdescription;
	}


	public long getcolorid() {
		return colorid;
	}


	public void setcolorid(long colorid) {
		this.colorid = colorid;
	}


	public String getcolorname() {
		return colorname;
	}


	public void setcolorname(String colorname) {
		this.colorname = colorname;
	}


	public String gethashcode() {
		return hashcode;
	}


	public void sethashcode(String hashcode) {
		this.hashcode = hashcode;
	}


	public long getcustid() {
		return custid;
	}


	public void setcustid(long custid) {
		this.custid = custid;
	}


	public long getquantity() {
		return quantity;
	}


	public void setquantity(long quantity) {
		this.quantity = quantity;
	}


	public long getpqsizeid() {
		return pqsizeid;
	}


	public void setpqsizeid(long pqsizeid) {
		this.pqsizeid = pqsizeid;
	}


	public long getSizeid() {
		return sizeid;
	}


	public void setSizeid(long sizeid) {
		this.sizeid = sizeid;
	}


	public String getSizeno() {
		return sizeno;
	}


	public void setSizeno(String sizeno) {
		this.sizeno = sizeno;
	}


	public String getAgeid() {
		return ageid;
	}


	public void setAgeid(String ageid) {
		this.ageid = ageid;
	}


	public String getHeight() {
		return height;
	}


	public void setHeight(String height) {
		this.height = height;
	}


	public String getWeight() {
		return weight;
	}


	public void setWeight(String weight) {
		this.weight = weight;
	}


	public String getChest() {
		return chest;
	}


	public void setChest(String chest) {
		this.chest = chest;
	}


	public String getWaist() {
		return waist;
	}


	public void setWaist(String waist) {
		this.waist = waist;
	}


	public String getHip() {
		return hip;
	}


	public void setHip(String hip) {
		this.hip = hip;
	}

	public boolean getproductstatus() {
		return productstatus;
	}
	
	public void setproductstatus(boolean productstatus)
	{
		this.productstatus=productstatus;
	}





	@Override
	public String toString() {
		return "ProductsActive [productid=" + productid + ", descriptionid=" + descriptionid + ", description="
				+ description + ", batchid=" + batchid + ", warehouseid=" + warehouseid + ", dateofpurchase="
				+ dateofpurchase + ", purchasedby=" + purchasedby + ", invamount=" + invamount + ", whoinserted="
				+ whoinserted + ", dateinsertion=" + dateinsertion + ", status=" + status + ", isocode=" + isocode
				+ ", qty=" + qty + ", sellerid=" + sellerid + ", warehousename=" + warehousename
				+ ", warehousecontactpersonname=" + warehousecontactpersonname + ", warehousecontactphone="
				+ warehousecontactphone + ", warehouseemail=" + warehouseemail + ", warehouseaddress="
				+ warehouseaddress + ", warehousepincode=" + warehousepincode + ", warehouseest=" + warehouseest
				+ ", warehousestatus=" + warehousestatus + ", warehousewhocreated=" + warehousewhocreated
				+ ", warehousedateofcreation=" + warehousedateofcreation + ", warehousewhoupdated="
				+ warehousewhoupdated + ", warehousemodifydate=" + warehousemodifydate + ", warehouseisocode="
				+ warehouseisocode + ", sellername=" + sellername + ", sellercompanyname=" + sellercompanyname
				+ ", sellerphone=" + sellerphone + ", selleraddress=" + selleraddress + ", selleremail=" + selleremail
				+ ", sellerisocode=" + sellerisocode + ", sellerstatus=" + sellerstatus + ", catid=" + catid
				+ ", category=" + category + ", gender=" + gender + ", subcatid=" + subcatid + ", categoryname="
				+ categoryname + ", seasonId=" + seasonId + ", season=" + season + ", occasionid=" + occasionid
				+ ", occaname=" + occaname + ", materialid=" + materialid + ", materialname=" + materialname
				+ ", materialdescription=" + materialdescription + ", colorid=" + colorid + ", colorname=" + colorname
				+ ", hashcode=" + hashcode + ", custid=" + custid + ", quantity=" + quantity + ", pqsizeid=" + pqsizeid
				+ ", sizeid=" + sizeid + ", sizeno=" + sizeno + ", ageid=" + ageid + ", height=" + height + ", weight="
				+ weight + ", chest=" + chest + ", waist=" + waist + ", hip=" + hip + ", productstatus=" + productstatus
				+ "]";
	}

	

}