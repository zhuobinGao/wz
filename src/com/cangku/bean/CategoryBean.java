package com.cangku.bean;

import java.util.ArrayList;
import java.util.List;

public class CategoryBean {

	private String id ;
	private String name;
	private String fid;
	
	
	
	public CategoryBean(String id, String name, String fid) {
		super();
		this.id = id;
		this.name = name;
		this.fid = fid;
	}
	public List<CategoryBean> list = new ArrayList<CategoryBean>();
	
	
	
	
	public static String createString(CategoryBean bean){
		StringBuffer sbBuffer = new StringBuffer();
		sbBuffer.append("<li id='"+bean.getId()+"'>"+bean.getName()+"");
		System.out.println(bean.getName());
		if(bean.list.size()>0){
			sbBuffer.append("<ul>");
			for(CategoryBean tempBean: bean.list){
				sbBuffer.append(createString(tempBean));
			}
			sbBuffer.append("</ul>");
		}
		sbBuffer.append("</li>");
		return sbBuffer.toString();
	}
	
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFid() {
		return fid;
	}
	public void setFid(String fid) {
		this.fid = fid;
	}
	
	
	
}
