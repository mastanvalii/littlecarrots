package com.lc.sk.inventory.bean;

import java.sql.Timestamp;

public interface CollectionSalewithProductIds {
	
	public long getId();
	public String getTitle();
	public String getGenderid();
	public String getBadge();
	public String getDisplay();
	public Timestamp getStartdate();
	public Timestamp getEnddate();
	public long getProductid();

}
