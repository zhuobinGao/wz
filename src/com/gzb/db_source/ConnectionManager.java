package com.gzb.db_source;

import java.security.Key;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.gzb.util.ThreeDes;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.DataSources;

public final class ConnectionManager {
	 private static ConnectionManager instance;

	 public ComboPooledDataSource ds;
	 private static String c3p0Properties = "db.properties";

	 private ConnectionManager() throws Exception {
	  Properties p = new Properties();
	  p.load(this.getClass().getResourceAsStream(c3p0Properties));
	  ds = new ComboPooledDataSource();
	 
	  ThreeDes  td=new ThreeDes();
	  
	  
	 
	  Key k=td.getKey(p.getProperty("pwd2"));
	  
      String user = p.getProperty("user");
      String pwd = td.getDecString(p.getProperty("pwd"), k);
      System.out.println(pwd);
	  String url = p.getProperty("url");
	  
	  System.out.println(user+"\n"+pwd+"\n"+url);
	  
	  
      ds.setUser(user);
	  ds.setPassword(pwd);
	  ds.setJdbcUrl(url);
	  ds.setDriverClass("oracle.jdbc.OracleDriver");
	  
//	  ds.setInitialPoolSize(Integer.parseInt(p.getProperty("initialPoolSize")));
//	  ds.setMinPoolSize(Integer.parseInt(p.getProperty("minPoolSize")));
//	  ds.setMaxPoolSize(Integer.parseInt(p.getProperty("maxPoolSize")));
//	  ds.setMaxStatements(Integer.parseInt(p.getProperty("maxStatements")));
//	  ds.setMaxIdleTime(Integer.parseInt(p.getProperty("maxIdleTime")));
	 }

	 public static final ConnectionManager getInstance() {
	  if (instance == null) {
	   try {
	    instance = new ConnectionManager();
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
	  DataSources.destroy(ds); // �ر�datasource
	  super.finalize();
	 }
	 
	 public static void main(String[] args) throws SQLException{
		 System.out.println("****************************");
		 Connection conn = ConnectionManager.getInstance().getConnection();
		 System.out.println("====================="+conn);
		 conn.close();
	}
	 
	 

	}
	
	

