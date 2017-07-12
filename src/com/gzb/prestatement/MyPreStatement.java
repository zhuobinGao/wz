package com.gzb.prestatement;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gzb.db_source.ConnectionManager;
import com.gzb.prestatement.bean.SqlParams;
import com.gzb.util.StringUtil;

public class MyPreStatement {

	private final String OK = "OK";
	
	public List<String> getTabeData(SqlParams param){
		return getTabeData(param.sqlID,param);
	}
	
	public String updateData(SqlParams param){
		return updateData(param.sqlID, param);
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
	
	
	
	public List<String> getTabeData(String sqlID, SqlParams param){
		List<String> list = new ArrayList<String>();
		Connection conn = null;
		PreparedStatement pstm = null,pstm_query=null;
		ResultSet rs = null;
		Class<?> demo = SqlParams.class;
		Field field = null;
		StringBuffer sb = new StringBuffer();
		String sql = "select SQL_content from bmwz_sql where sql_id=?";
		
		try{
			conn = ConnectionManager.getInstance().getConnection();
			
			pstm_query = conn.prepareStatement(sql);
			pstm_query.setString(1, sqlID);
			rs = pstm_query.executeQuery();
			if(rs.next()){
				sql = rs.getString(1);
				System.out.println("sql:"+sql+"|"+param.str0);
			}else{
				System.out.println("NO this SQL:"+sqlID);
				return list;
			}
			System.out.print("getTabeData:"+sql);
			pstm = conn.prepareStatement(sql);
			
			
		
			for(int i=0;i<param.strCount;i++){
				
				field = demo.getField("str"+i);
				System.out.println(i+":"+(String)field.get(param));
				pstm.setString(i+1, (String)field.get(param));
			}
			
			rs = pstm.executeQuery();
			ResultSetMetaData rsm = rs.getMetaData();
			int rscount = rsm.getColumnCount();
			while(rs.next()){
				for(int i=0;i<rscount;i++){
					sb.append(StringUtil.nullToEmptyAppend(rs.getString(i+1)));
				}
				sb.deleteCharAt(sb.length()-1);
				list.add(sb.toString());
				sb.setLength(0);
			
			}
			System.out.println("->"+list.size());
			
		}catch(Exception e){
			
			e.printStackTrace();
		}finally{
			if(rs!=null)
				try {
					rs.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
		
			if(pstm_query!=null){
				try {
					pstm_query.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstm!=null)
				try {
					pstm.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		
		return list;
	}
	
	public List<String> getTableDataAddString(String sqlID,SqlParams param, String addSql){
		List<String> list = new ArrayList<String>();
		Connection conn = null;
		PreparedStatement pstm = null,pstm_query=null;
		ResultSet rs = null;
		Class<?> demo = SqlParams.class;
		Field field = null;
		StringBuffer sb = new StringBuffer();
		String sql = "select SQL_content from bmwz_sql where sql_id=?";
		
		try{
			conn = ConnectionManager.getInstance().getConnection();
			
			pstm_query = conn.prepareStatement(sql);
			pstm_query.setString(1, sqlID);
			rs = pstm_query.executeQuery();
			if(rs.next()){
				sql = rs.getString(1);
			}else{
				System.out.println("NO this SQL:"+sqlID);
				return list;
			}
			System.out.print("getTabeData:"+sql+addSql);
			pstm = conn.prepareStatement(sql+addSql);
			
		
			System.out.println("count:"+param.strCount);
			for(int i=0;i<param.strCount;i++){
				field = demo.getField("str"+i);
				System.out.println(i+":"+ (String)field.get(param));
				pstm.setString(i+1, (String)field.get(param));
			}
			
			rs = pstm.executeQuery();
			ResultSetMetaData rsm = rs.getMetaData();
			int rscount = rsm.getColumnCount();
			while(rs.next()){
				for(int i=0;i<rscount;i++){
					sb.append(StringUtil.nullToEmptyAppend(rs.getString(i+1)));
				}
				sb.deleteCharAt(sb.length()-1);
				list.add(sb.toString());
				sb.setLength(0);
			}
		
			
		}catch(Exception e){
			
			e.printStackTrace();
		}finally{
			if(rs!=null)
				try {
					rs.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
		
			if(pstm_query!=null){
				try {
					pstm_query.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstm!=null)
				try {
					pstm.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return list;
	}
	

	public String updateData(String sqlID, SqlParams param){
		Connection conn = null;
		PreparedStatement pstm = null,pstm_query =null;
		ResultSet rs = null;
		Class<?> demo = SqlParams.class;
		Field field = null;
		String sql = "select SQL_content from bmwz_sql where sql_id=?";
		
		System.out.println(sqlID);
		
		
		try{
			conn = ConnectionManager.getInstance().getConnection();
			conn.setAutoCommit(false);
			pstm_query = conn.prepareStatement(sql);
			pstm_query.setString(1, sqlID);
			rs = pstm_query.executeQuery();
			if(rs.next()){
				sql = rs.getString(1);
			}else{
				return "NO THIS SQL";
			}
			System.out.println(sql);
			pstm = conn.prepareStatement(sql);
			for(int i=0;i<param.strCount;i++){
				field = demo.getField("str"+i);
				pstm.setString(i+1, ((String)field.get(param)).replaceAll("|", ""));
			}
			
			pstm.executeUpdate();
			conn.commit();
		}catch(Exception e){
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			return e.getMessage();
		}finally{
			if(rs!=null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstm_query!=null){
				try {
					pstm_query.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstm!=null)
				try {
					pstm.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return OK;
	}
	
	public String updateListData(String sqlID, List<SqlParams> paramList){
		Connection conn = null;
		PreparedStatement pstm = null,pstm_query =null;
		ResultSet rs = null;
		Class<?> demo = SqlParams.class;
		Field field = null;
		String sql = "select SQL_content from bmwz_sql where sql_id=?";
		int record = 0;
		try{
			conn = ConnectionManager.getInstance().getConnection();
			conn.setAutoCommit(false);
			pstm_query = conn.prepareStatement(sql);
			pstm_query.setString(1, sqlID);
			rs = pstm_query.executeQuery();
			if(rs.next()){
				sql = rs.getString(1);
			}else{
				return "NO THIS SQL";
			}
			System.out.println(sql);
			
			
			pstm = conn.prepareStatement(sql);
			
			for(SqlParams param:paramList){
				for (int i = 0; i < param.strCount; i++) {
					field = demo.getField("str" + i);
					pstm.setString(i + 1, StringUtil.nullToEmpty((String)field.get(param)));
				}
				pstm.addBatch();
				record++;
				if(record%50==0){
					pstm.executeBatch();
					pstm.clearBatch();
					record=0;
				}
			}
			if(record>0){
				pstm.executeBatch();
			}
			conn.commit();
		}catch(Exception e){
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			return e.getMessage();
		}finally{
			if(rs!=null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstm_query!=null){
				try {
					pstm_query.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstm!=null)
				try {
					pstm.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return OK;
	}
	
	
	
	
	
}
