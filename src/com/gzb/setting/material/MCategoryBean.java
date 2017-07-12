package com.gzb.setting.material;

import java.util.ArrayList;
import java.util.List;

public class MCategoryBean {
	
	private String catoryID;
	private String catoryFID;
	private String catoryName;
	private String catoryBH;
	
	private List<MCategoryBean> list = new ArrayList<MCategoryBean>();
	
	public MCategoryBean(){}

	public MCategoryBean(String catoryID, String catoryName, String catoryFID, String catoryBH) {
		this.catoryID = catoryID;
		this.catoryFID = catoryFID;
		this.catoryName = catoryName;
		this.catoryBH = catoryBH;
	}
	
	public void addMCategoryBean(MCategoryBean bean){
		list.add(bean);
	}
	
	public String getCatoryID() {
		return catoryID;
	}

	public void setCatoryID(String catoryID) {
		this.catoryID = catoryID;
	}

	public String getCatoryFID() {
		return catoryFID;
	}

	public void setCatoryFID(String catoryFID) {
		this.catoryFID = catoryFID;
	}

	public String getCatoryName() {
		return catoryName;
	}

	public void setCatoryName(String catoryName) {
		this.catoryName = catoryName;
	}


	public String getCatoryBH() {
		return catoryBH;
	}

	public void setCatoryBH(String catoryBH) {
		this.catoryBH = catoryBH;
	}

	public List<MCategoryBean> getList() {
		return list;
	}

	@Override
	public String toString() {
		return "MCategoryBean [catoryID=" + catoryID + ", catoryFID="
				+ catoryFID + ", catoryName=" + catoryName + ", catoryBH="
				+ catoryBH + ", list=" + list + "]";
	}

	

	
	
}
