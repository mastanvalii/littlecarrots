package com.lc.sk.inventory.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Arrays;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name="collectionsaleimages")
public class CollectionsaleImages implements Serializable{
	
	/*create table collectionsaleimages(
imageid bigint auto_increment,
id bigint unique,
icon longblob,
mobileview1 longblob,
mobileview2 longblob,
desktopview1 longblob,
desktopview2 longblob,
constraint collectionsaleimages_pk primary key (imageid),
constraint collectionsaleimages_fk foreign key (id) references collectionsale(id));*/
	
	@Id
	@Column(name="imageid")
	private long imageid;
	
	@Column(name="id")
	private long id;

	
	@Column(name="icon", columnDefinition = "LONGBLOB")
	@Lob
	@Basic(fetch = FetchType.LAZY)
	private byte[] icon;
	
	@Column(name="mobileview1", columnDefinition = "LONGBLOB")
	@Lob
	@Basic(fetch = FetchType.LAZY)
	private byte[] mobileview1;
	
	@Column(name="mobileview2", columnDefinition = "LONGBLOB")
	@Lob
	@Basic(fetch = FetchType.LAZY)
	private byte[] mobileview2;
	
	@Column(name="desktopview1", columnDefinition = "LONGBLOB")
	@Lob
	@Basic(fetch = FetchType.LAZY)
	private byte[] desktopview1;
	
	@Column(name="desktopview2", columnDefinition = "LONGBLOB")
	@Lob
	@Basic(fetch = FetchType.LAZY)
	private byte[] desktopview2;

	public CollectionsaleImages() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CollectionsaleImages(long id, byte[] icon, byte[] mobileview1, byte[] mobileview2, byte[] desktopview1,
			byte[] desktopview2) {
		super();
		this.id = id;
		this.icon = icon;
		this.mobileview1 = mobileview1;
		this.mobileview2 = mobileview2;
		this.desktopview1 = desktopview1;
		this.desktopview2 = desktopview2;
	}

	public long getImageid() {
		return imageid;
	}

	public void setImageid(long imageid) {
		this.imageid = imageid;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public byte[] getIcon() {
		return icon;
	}

	public void setIcon(byte[] icon) {
		this.icon = icon;
	}

	public byte[] getMobileview1() {
		return mobileview1;
	}

	public void setMobileview1(byte[] mobileview1) {
		this.mobileview1 = mobileview1;
	}

	public byte[] getMobileview2() {
		return mobileview2;
	}

	public void setMobileview2(byte[] mobileview2) {
		this.mobileview2 = mobileview2;
	}

	public byte[] getDesktopview1() {
		return desktopview1;
	}

	public void setDesktopview1(byte[] desktopview1) {
		this.desktopview1 = desktopview1;
	}

	public byte[] getDesktopview2() {
		return desktopview2;
	}

	public void setDesktopview2(byte[] desktopview2) {
		this.desktopview2 = desktopview2;
	}

	@Override
	public String toString() {
		return "CollectionsaleImages [imageid=" + imageid + ", id=" + id + ", icon=" + Arrays.toString(icon)
				+ ", mobileview1=" + Arrays.toString(mobileview1) + ", mobileview2=" + Arrays.toString(mobileview2)
				+ ", desktopview1=" + Arrays.toString(desktopview1) + ", desktopview2=" + Arrays.toString(desktopview2)
				+ "]";
	}

	
	
	
}
