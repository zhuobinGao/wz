package com.gzb.db_source;

import java.security.Key;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.gzb.util.ThreeDes;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.DataSources;

public final class BMConnectionManager {
	 private static BMConnectionManager instance;

	 public ComboPooledDataSource ds;
	 private static final String c3p0Properties = "bm.properties";

	 private BMConnectionManager() throws Exception {
	  Properties p = new Properties();
	  p.load(this.getClass().getResourceAsStream(c3p0Properties));
	  ds = new ComboPooledDataSource();
	  ThreeDes  td=new ThreeDes();
      Key k=td.getKey(p.getProperty("pwd2"));
      String user = td.getDecString(p.getProperty("user"), k);
      String pwd = td.getDecString(p.getProperty("pwd"), k);
	  String url = td.getDecString(p.getProperty("url"), k);
//	  String class_ = td.getDecString(p.getProperty("class"), k);
	  ds.setUser(user);
	  ds.setPassword(pwd);
	  ds.setJdbcUrl(url);
	  ds.setDriverClass("oracle.jdbc.driver.OracleDriver");
//	  ds.setInitialPoolSize(2);
//	  ds.setMinPoolSize(Integer.parseInt(p.getProperty("minPoolSize")));
//	  ds.setMaxPoolSize(Integer.parseInt(p.getProperty("maxPoolSize")));
//	  ds.setMaxStatements(Integer.parseInt(p.getProperty("maxStatements")));
//	  ds.setMaxIdleTime(Integer.parseInt(p.getProperty("maxIdleTime")));
	 }

	 public static final BMConnectionManager getInstance() {
	  if (instance == null) {
	   try {
	    instance = new BMConnectionManager();
	   } catch (Exception e) {
	    e.printStackTrace();
	   }
	  }
	
	
	  return instance;
	 }

	 public synchronized final Connection getConnection() {
	  try {
	   return ds.getConnection();
	  } catch (SQLException e) {
	   e.printStackTrace();
	  }
	  return null;
	 }

	 protected void finalize() throws Throwable {
	  DataSources.destroy(ds); // ¹Ø±Õdatasource
	  super.finalize();
	 }
	 
	 public static void main(String[] args){
		 Connection conn = BMConnectionManager.getInstance().getConnection();
		 System.out.println(conn);
	}

	}
	
	

