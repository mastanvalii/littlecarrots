package com.lc.sk.inventory.bean;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;

public interface CollectionSalePack {

	public long getId();
	public String getTitle();
	public String getGenderid();
	public String getBadge();
	public String getDisplay();
	public Timestamp getStartdate();
	public Timestamp getEnddate();
	public byte[] getIcon();
	public byte[] getMobileview1();
	public byte[] getMobileview2();
	public byte[] getDesktopview1();
	public byte[] getDesktopview2();
	


}
