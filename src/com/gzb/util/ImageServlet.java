package com.gzb.util;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.jms.Session;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gzb.db_source.ConnectionManager;

public class ImageServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6399780554633345122L;
	private static final String CONTENT_TYPE = "image/jpeg";
	
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 String personID = request.getParameter("materialID");
	        //DataSourceManager 为取数库连接的类
	    
		 	
		 
	        Connection conn = null;
	        ResultSet rs = null;
	        PreparedStatement pstmt = null,pstmt2=null;
	        //用来存储照片数据的缓冲区
	        byte [] buf=null;
	        String sql = "select photo_content from bmwz_photo "
	        		+" where 1=1 and ( MATERIAL_ID=?  or MATERIAL_ID = (select r.receipt_material_id from bmwz_receipt r where r.receipt_id=?) "
	        		+" or MATERIAL_ID = (select rq.request_id from bmwz_request rq where rq.request_id=? ) )";
	        try{
	           
	            
	                conn = ConnectionManager.getInstance().getConnection();
	                if(conn != null){
	                    pstmt = conn.prepareStatement(sql);
	                    pstmt.setString(1,personID);
	                    pstmt.setString(2,personID);
	                    pstmt.setString(3,personID);
	                    rs = pstmt.executeQuery();
	                    if(rs.next()){
	                    	Blob blocco = (Blob)rs.getObject("photo_content");
	                        int length = (int)blocco.length();
	                        buf = blocco.getBytes(1, length);
	                    }else{
	                        rs.close();
	                        pstmt2 = conn.prepareStatement("select photo_content from bmwz_photo where photo_id='defaultID'");
	                        rs = pstmt2.executeQuery();
	                        if(rs.next()){
		                    	Blob blocco = (Blob)rs.getObject("photo_content");
		                        int length = (int)blocco.length();
		                        buf = blocco.getBytes(1, length);
	                        }
	                        
	                    }
	                }
	            
	        }catch(Exception e){
	            e.printStackTrace();
	        }finally{
	            try{
	                if(rs != null)
	                    rs.close();
	                if(pstmt != null)
	                    pstmt.close();
	                if(conn != null)
	                    conn.close();
	            }catch(Exception e){
	                e.printStackTrace();
	            }
	        }if(buf == null)buf = new byte[0];
	        //告诉浏览器输出的是图片
	        response.setContentType(CONTENT_TYPE);
	        //图片输出的输出流
	        System.out.println("1B55D29164694F868698593B9750DA8D");
	        OutputStream out = response.getOutputStream();
	        //将缓冲区的输入输出到页面
	        out.write(buf);
	        //输入完毕，清楚缓冲
	        out.flush();
		
	}

	
	public void init() throws ServletException {
		// Put your code here
	}

}
