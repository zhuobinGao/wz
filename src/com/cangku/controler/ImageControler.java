package com.cangku.controler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.directwebremoting.io.FileTransfer;

import com.gzb.db_source.ConnectionManager;



public class ImageControler {

	public String upload(FileTransfer fileTransfer,String materialID) {
		
		
		String mes = "";
		
		if(fileTransfer.getFilename()==null){
			return "请选择上传的文件";
		}
		
		if(!fileTransfer.getFilename().toUpperCase().endsWith("JPG")||!fileTransfer.getFilename().toUpperCase().endsWith("JPG")){
			return "请选择上传JPG格式的文件";
		}
		
		
		try {
			mes = insertImage(fileTransfer.getInputStream(),materialID);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return mes+e.toString();
		}
		

		return mes;
	}
	
	
	
	 private String insertImage(InputStream inputStream,String materialID){
	      
	        Connection conn = null;
	        PreparedStatement ps = null, ps2=null,ps3=null;
	        PreparedStatement pstm = null, pstm2=null;
	        ResultSet rs = null,rs3=null;
	        OutputStream os = null;   
	        
	        String checkSql = "select photo_id from BMWZ_PHOTO where MATERIAL_ID=? ";
	        
	        String updateSql = "update BMWZ_PHOTO set photo_content=empty_blob() where photo_id=? ";
	        
	        String selsql = "select sys_guid() from dual";
	        
	        String sql = "insert into BMWZ_PHOTO(PHOTO_ID,MATERIAL_ID,photo_content) values(?,?,empty_blob())";
	        
	        String sql2 = "select photo_content from BMWZ_PHOTO where photo_id=? for update";
	        
	        String uuid = "";
	      
	        
	        try {
	        	conn = ConnectionManager.getInstance().getConnection();
	        	conn.setAutoCommit(false);
	        	
	        	
	        	pstm = conn.prepareStatement(checkSql);
	        	pstm2 = conn.prepareStatement(updateSql);
	        	
	        	
	        	pstm.setString(1, materialID);
	        	
	        	rs = pstm.executeQuery();
	        	
	        	if(rs.next()){
	        		uuid = rs.getString(1);
	        		pstm2.setString(1, uuid);
	        		pstm2.executeUpdate();
	        	}else{
	        		ps = conn.prepareStatement(selsql);
	 	            ps2 = conn.prepareStatement(sql);
	 	            rs.close();
	 	            rs = ps.executeQuery();
		            
		            if(rs.next()){
		            	uuid = rs.getString(1);
		            }
		            ps2.setString(1, uuid);
		            ps2.setString(2, materialID); 
	 	            ps2.executeUpdate();
	        	}
	        	
	        	
	           
	            ps3 = conn.prepareStatement(sql2);
	            ps3.setString(1, uuid);
	            
	            
	          
	            rs3=ps3.executeQuery();
	            if (rs3.next()){  
	            	 System.out.println("rs.next():"+123);
		             oracle.sql.BLOB blob = (oracle.sql.BLOB) rs3.getBlob(1);   
		             System.out.println("blob:"+blob);
		             os = blob.getBinaryOutputStream();
		             System.out.println(os);
		             int i = 0;  
		             while ((i = inputStream.read()) != -1) {   
		            	 os.write(i);   
		             }
		             os.flush();
		             os.close();
	            }
	            
	            conn.commit();
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	            return "错误："+e.toString();
	        }finally{
	        	if(rs!=null)
	        		try {rs.close();} catch (SQLException e) {e.printStackTrace();}	
	        	if(rs3!=null)
	        		try {rs3.close();} catch (SQLException e) {e.printStackTrace();}	
				if(ps!=null)
					try {ps.close();} catch (SQLException e) {e.printStackTrace();}	
				if(ps2!=null)
					try {ps2.close();} catch (SQLException e) {e.printStackTrace();}	
				if(ps3!=null)
					try {ps3.close();} catch (SQLException e) {e.printStackTrace();}	
				if(conn!=null)
					try {conn.close();	} catch (SQLException e) {e.printStackTrace();}
	        }
	        
	        return "OK";
	    }
	
}
