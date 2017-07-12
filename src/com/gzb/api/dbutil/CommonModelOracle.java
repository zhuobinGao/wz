package com.gzb.api.dbutil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.gzb.db_source.ConnectionManager;
import com.gzb.json.impl.JsonImpl;
import com.gzb.util.JsonUtil;
import com.gzb.util.R;

public class CommonModelOracle {

	public String search(String sql){
		Connection conn  = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String result = R.ERROR;
		try{
			conn = ConnectionManager.getInstance().getConnection();
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			result = JsonUtil.tableDataFromRusltSet(rs);
		}catch(Exception e){
			e.printStackTrace();
			result = e.toString();
		}finally{
			if(rs!=null)try {rs.close();} catch (SQLException e) {e.printStackTrace();}
			if(pstm!=null)try {pstm.close();} catch (SQLException e) {e.printStackTrace();}
			if(conn!=null)try {conn.close();} catch (SQLException e) {e.printStackTrace();}
		}
		return result;
	}
	
	public String search(String sql,List<String> list,JsonImpl jsonModel){
		Connection conn  = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String result = "";
		try{
			conn = ConnectionManager.getInstance().getConnection();
			pstm = conn.prepareStatement(sql);
			if(list!=null){
				for(int i=0;i<list.size();i++){
					pstm.setString(i+1, list.get(i));
				}
			}
			
			
			rs = pstm.executeQuery();
			result = jsonModel.encodeResultSet(rs);
		}catch(Exception e){
			e.printStackTrace();
			result = e.toString();
		}finally{
			if(rs!=null)try {rs.close();} catch (SQLException e) {e.printStackTrace();}
			if(pstm!=null)try {pstm.close();} catch (SQLException e) {e.printStackTrace();}
			if(conn!=null)try {conn.close();} catch (SQLException e) {e.printStackTrace();}
		}
		return result;
	}

	
	public PreparedStatement makePreparedStatement(PreparedStatement pstm, List<String> list) throws SQLException{
		if(list!=null){
			for(int i=0;i<list.size();i++){
				pstm.setString(i+1, list.get(i));
			}
		}
		return pstm;
	}
	
	
	
	
	
	
	public String update(String sql, List<String> params){
		
		
		Connection conn  = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		String result = "do not thing";
		
		try{
			conn = ConnectionManager.getInstance().getConnection();
			conn.setAutoCommit(false);
			pstm = conn.prepareStatement(sql);
			for(int i=0; i<params.size(); i++){
				
				pstm.setString(i+1, params.get(i));
			}
			int count = pstm.executeUpdate();
			if(count>=1){
				result = R.OK;
			}
			conn.commit();
		}catch(Exception e){
			result = e.toString();
			e.printStackTrace();
			try {conn.rollback();} catch (SQLException e1) {e1.printStackTrace();}
			
		}finally{
			if(rs!=null)try {rs.close();} catch (SQLException e) {e.printStackTrace();}
			if(pstm!=null)try {pstm.close();} catch (SQLException e) {e.printStackTrace();}
			if(conn!=null)try {conn.close();} catch (SQLException e) {e.printStackTrace();}
		}
		return result;
	}
	
	public String update(String sql, List<String> params, List<Integer> types){
		Connection conn  = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		String result = "do not thing";
		
		try{
			conn = ConnectionManager.getInstance().getConnection();
			conn.setAutoCommit(false);
			pstm = conn.prepareStatement(sql);
			for(int i=0; i<params.size(); i++){
				if("".equals(params.get(i))){
					pstm.setObject(i+1, null);
				}else{
					pstm.setString(i+1, params.get(i));
				}
				
				
			}
			int count = pstm.executeUpdate();
			if(count>=1){
				result = R.OK;
			}
			conn.commit();
		}catch(Exception e){
			result = e.toString();
			e.printStackTrace();
			try {conn.rollback();} catch (SQLException e1) {e1.printStackTrace();}
			
		}finally{
			if(rs!=null)try {rs.close();} catch (SQLException e) {e.printStackTrace();}
			if(pstm!=null)try {pstm.close();} catch (SQLException e) {e.printStackTrace();}
			if(conn!=null)try {conn.close();} catch (SQLException e) {e.printStackTrace();}
		}
		return result;
	}
	
	public String getSql(String sqlID){
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = "select SQL_content from bmwz_sql where sql_id=?";
		String result = "";
		try{
			conn = ConnectionManager.getInstance().getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, sqlID);
			rs = pstm.executeQuery();
			if(rs.next()){
				result = rs.getString(1);
			}
		
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(rs!=null)try {rs.close();} catch (SQLException e1) {	e1.printStackTrace();}
			if(pstm!=null)try {pstm.close();} catch (SQLException e) {e.printStackTrace();}
			if(conn!=null)try {conn.close();} catch (SQLException e) {e.printStackTrace();}	
		}
		
		return result;
	}
	
	
	
	
	
}
