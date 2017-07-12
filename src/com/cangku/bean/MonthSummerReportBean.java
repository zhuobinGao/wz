package com.cangku.bean;

public class MonthSummerReportBean {

	private String lineNumber;
	private String categoryBH;
	private String categoryID;
	private String categoryName;
	
	private String materialID;
	private String materialName;
	
	private String yearMonth;
	private Double bCount = 0d;
	private Double bSum = 0d;
	private Double iCount = 0d;
	private Double iSum = 0d;
	private Double oCount = 0d;
	private Double oSum = 0d;
	private Double eCount = 0d;
	private Double eSum = 0d;
	private String storehouseNO;
	
	public MonthSummerReportBean() {
		
	}
	
	
	
	
	public MonthSummerReportBean(String categoryID, String materialID,
			String materialName, String yearMonth) {
		this.categoryID = categoryID;
		this.materialID = materialID;
		this.materialName = materialName;
		this.yearMonth = yearMonth;
	}




	public String getMaterialID() {
		return materialID;
	}
	public void setMaterialID(String materialID) {
		this.materialID = materialID;
	}
	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	public String getLineNumber() {
		return lineNumber;
	}
	public void setLineNumber(String lineNumber) {
		this.lineNumber = lineNumber;
	}
	public String getCategoryBH() {
		return categoryBH;
	}
	public void setCategoryBH(String categoryBH) {
		this.categoryBH = categoryBH;
	}
	public String getCategoryID() {
		return categoryID;
	}
	public void setCategoryID(String categoryID) {
		this.categoryID = categoryID;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getYearMonth() {
		return yearMonth;
	}
	public void setYearMonth(String yearMonth) {
		this.yearMonth = yearMonth;
	}
	public Double getbCount() {
		return bCount;
	}
	public void setbCount(Double bCount) {
		this.bCount = bCount;
	}
	public Double getbSum() {
		return bSum;
	}
	public void setbSum(Double bSum) {
		this.bSum = bSum;
	}
	public Double getiCount() {
		return iCount;
	}
	public void setiCount(Double iCount) {
		this.iCount = iCount;
	}
	public Double getiSum() {
		return iSum;
	}
	public void setiSum(Double iSum) {
		this.iSum = iSum;
	}
	public Double getoCount() {
		return oCount;
	}
	public void setoCount(Double oCount) {
		this.oCount = oCount;
	}
	public Double getoSum() {
		return oSum;
	}
	public void setoSum(Double oSum) {
		this.oSum = oSum;
	}
	public Double geteCount() {
		return eCount;
	}
	public void seteCount(Double eCount) {
		this.eCount = eCount;
	}
	public Double geteSum() {
		return eSum;
	}
	public void seteSum(Double eSum) {
		this.eSum = eSum;
	}
	
	public String getStorehouseNO() {
		return storehouseNO;
	}
	public void setStorehouseNO(String storehouseNO) {
		this.storehouseNO = storehouseNO;
	}
	@Override
	public String toString() {
		return "MonthSummerReportBean [lineNumber=" + lineNumber
				+ ", categoryBH=" + categoryBH + ", categoryID=" + categoryID
				+ ", categoryName=" + categoryName + ", yearMonth=" + yearMonth
				+ ", bCount=" + bCount + ", bSum=" + bSum + ", iCount="
				+ iCount + ", iSum=" + iSum + ", oCount=" + oCount + ", oSum="
				+ oSum + ", eCount=" + eCount + ", eSum=" + eSum + "]\n\n";
	}
	
	
	
	
}
