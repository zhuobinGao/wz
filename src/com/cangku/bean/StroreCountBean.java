package com.cangku.bean;

public class StroreCountBean {
	
	private String supplyString;
	private String materialName;
	private String materialStyle;
	private String materialSize;
	private Double materialCount = new Double(0);
	private Double sum = new Double(0);
	
	private String materialID;
	private String categoryID;
	private String categoryFID;
	public String getSupplyString() {
		return supplyString;
	}
	public void setSupplyString(String supplyString) {
		this.supplyString = supplyString;
	}
	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	public String getMaterialStyle() {
		return materialStyle;
	}
	public void setMaterialStyle(String materialStyle) {
		this.materialStyle = materialStyle;
	}
	public String getMaterialSize() {
		return materialSize;
	}
	public void setMaterialSize(String materialSize) {
		this.materialSize = materialSize;
	}
	public Double getMaterialCount() {
		return materialCount;
	}
	public void setMaterialCount(Double materialCount) {
		this.materialCount = materialCount;
	}
	public Double getSum() {
		return sum;
	}
	public void setSum(Double sum) {
		this.sum = sum;
	}
	public String getMaterialID() {
		return materialID;
	}
	public void setMaterialID(String materialID) {
		this.materialID = materialID;
	}
	public String getCategoryID() {
		return categoryID;
	}
	public void setCategoryID(String categoryID) {
		this.categoryID = categoryID;
	}
	public String getCategoryFID() {
		return categoryFID;
	}
	public void setCategoryFID(String categoryFID) {
		this.categoryFID = categoryFID;
	}
	
	
	
	
	
}
