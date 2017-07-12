package com.gzb.db_source;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.DataSources;

public class ConnectionSqlServerManager {
	private static ConnectionSqlServerManager instance;

	 public ComboPooledDataSource ds;
	 private static String c3p0Properties = "bm.properties";

	 private ConnectionSqlServerManager() throws Exception {
	  Properties p = new Properties();
	  p.load(this.getClass().getResourceAsStream(c3p0Properties));
	  ds = new ComboPooledDataSource();
	 
	
	  
	  
	 
//	  Key k=td.getKey(p.getProperty("pwd2"));
     String user = "";
     String pwd = "";
	  String url = "";
	  
	  
	  
	  
	  
	  System.out.println(user+"\n"+pwd+"\n"+url);
	  
	  
	  user = "sa";
	  pwd = "ZJTOSCFS2009";
	  url = "jdbc:sqlserver://10.2.3.11:1433;database=Billing";
	  
     ds.setUser(user);
	  ds.setPassword(pwd);
	  ds.setJdbcUrl(url);
	  ds.setDriverClass("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	  
//	  ds.setInitialPoolSize(Integer.parseInt(p.getProperty("initialPoolSize")));
//	  ds.setMinPoolSize(Integer.parseInt(p.getProperty("minPoolSize")));
//	  ds.setMaxPoolSize(Integer.parseInt(p.getProperty("maxPoolSize")));
//	  ds.setMaxStatements(Integer.parseInt(p.getProperty("maxStatements")));
//	  ds.setMaxIdleTime(Integer.parseInt(p.getProperty("maxIdleTime")));
	 }

	 public static final ConnectionSqlServerManager getInstance() {
	  if (instance == null) {
	   try {
	    instance = new ConnectionSqlServerManager();
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
		 Connection conn = ConnectionSqlServerManager.getInstance().getConnection();
		 try {
			conn.close();
			
			for(int i=0;i<5;i++){
				conn = ConnectionSqlServerManager.getInstance().getConnection();
				System.out.println(i+":"+conn);
				conn.close();
			}
			
		} catch (SQLException e) {
			System.out.println("=============");
			e.printStackTrace();
		}
		 System.out.println("sqlServer2008R2"+conn);
	}
	 
}
