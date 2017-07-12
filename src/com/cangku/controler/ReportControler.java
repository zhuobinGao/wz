package com.cangku.controler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.gzb.db_source.ConnectionManager;


public class ReportControler {

	
	public String updateKunCun(String isCheckOn,String DLine, String UpLine, String materialID){
		
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		sql.append("update bmwz_material set material_isoncheck=?, MATERIAL_MINMOUNT=?, MATERIAL_MAXMOUNT=? \n");
		sql.append(" where MATERIAL_ID=? ");
		try{
			conn = ConnectionManager.getInstance().getConnection();
			conn.setAutoCommit(false);
			pstm = conn.prepareStatement(sql.toString());
			pstm.setString(1, isCheckOn);
			pstm.setString(2, DLine);
			pstm.setString(3, UpLine);
			pstm.setString(4, materialID);
			
			System.out.println("======2========");
			
			int count = pstm.executeUpdate();
			if(count<1){
				return "更新失败";
			}
			conn.commit();
		}catch(Exception e){
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
				
			}
			
			return e.toString();
			
		}finally{
			if(rs!=null)
				try {rs.close();} catch (SQLException e1) {	e1.printStackTrace();}
			if(pstm!=null)
				try {pstm.close();} catch (SQLException e) {e.printStackTrace();}	
			if(conn!=null)
				try {conn.close();	} catch (SQLException e) {e.printStackTrace();}
		}	
		return "OK";
		
	}
	
}
