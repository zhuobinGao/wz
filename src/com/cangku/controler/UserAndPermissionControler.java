package com.cangku.controler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import oracle.net.aso.p;

import com.gzb.db_source.ConnectionManager;
import com.gzb.util.StringUtil;

public class UserAndPermissionControler {

	public List<String> getUserList(String userID, String userName){
		
		List<String> list = new ArrayList<String>();
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String str = "";
		StringBuffer sbBuffer = new StringBuffer();
		sbBuffer.append("select u.user_id,u.user_name,h.storehouse_name,g.groupname,u.storehouse_id,g.groupid from bmwz_user u\n");
		sbBuffer.append("left join bmwz_storehouse h on u.storehouse_id=h.storehouse_id\n");
		sbBuffer.append("left join bmwz_group g on g.groupid=u.group_id where 1=1 \n");
		
		if(!"".equals(StringUtil.nullToEmpty(userID))){
			sbBuffer.append(" and user_id = ? \n");
		}
		if(!"".equals(StringUtil.nullToEmpty(userName))){
			sbBuffer.append(" and user_name like ?");
		}
		
		try{
			conn = ConnectionManager.getInstance().getConnection();
			pstm = conn.prepareStatement(sbBuffer.toString());
			int i = 1;
			if(!"".equals(StringUtil.nullToEmpty(userID))){
				pstm.setString(i, userID);
				i++;
			}
			
			if(!"".equals(StringUtil.nullToEmpty(userName))){
				pstm.setString(i, "%"+userName+"%");
			}
			rs = pstm.executeQuery();
			while(rs.next()){
				str = StringUtil.nullToEmptyAppend(rs.getString(1))+StringUtil.nullToEmptyAppend(rs.getString(2))+StringUtil.nullToEmptyAppend(rs.getString(3))+StringUtil.nullToEmptyAppend(rs.getString(4));
				str += ( StringUtil.nullToEmptyAppend(rs.getString(5))+StringUtil.nullToEmptyAppend(rs.getString(6)) );
				list.add(str);
			}
		
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(rs!=null)
				try {rs.close();} catch (SQLException e1) {	e1.printStackTrace();}
			if(pstm!=null)
				try {pstm.close();} catch (SQLException e) {e.printStackTrace();}	
			if(conn!=null)
				try {conn.close();	} catch (SQLException e) {e.printStackTrace();}
		}
		return list;
	}
	
	
	public String getPermission(String groupID){
		
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append("select t.group_id g1,t.function_id,t.isuse,t.pagename,t.showname,t.function_fid fid ,t2.function_id fid2,t2.group_id g2 from BMWZ_PERMISSION_JSP t \n");
		sBuffer.append("left join BMWZ_PERMISSION_JSP t2 on (t2.function_id=t.function_id and t2.group_id=?) \n");
		sBuffer.append("where t.group_id='-1'\n");
		sBuffer.append("start with t.function_fid='-1' \n");
		sBuffer.append("connect by prior t.function_id = t.function_fid\n");
		int i =0,j=0;
		String str1 = "" , str2="";
		boolean flag1 = false, flag2=false;
		try{
			conn = ConnectionManager.getInstance().getConnection();
			pstm = conn.prepareStatement(sBuffer.toString());
			pstm.setString(1, groupID);
			rs = pstm.executeQuery();
			System.out.println(sBuffer.toString()+"\n"+groupID);
			sBuffer.setLength(0);
			
			
			while(rs.next()){

				flag1 = (rs.getString("fid2")!=null)&&!"-1".equals(rs.getString("g2"));
				str1 = flag1?",\"selected\":true":"";
				
				flag2 = (rs.getString("fid2")!=null)&&(!"-1".equals(rs.getString("g2")));
				str2 = flag2?" data-jstree='{\"selected\":true}'":"";
				
				if("-1".equals(rs.getString("fid"))&&i==0){
					sBuffer.append("<ul><li id='"+rs.getString("function_id")+"' data-jstree='{\"opened\":true"+str1+"}'>"+rs.getString("showname"));
					i++;
					continue;
				}
				
				if("-1".equals(rs.getString("fid"))&&i>0){
					sBuffer.append("</ul></li><li id='"+rs.getString("function_id")+"' data-jstree='{\"opened\":true"+str1+"}'>"+rs.getString("showname"));
					i++;
					j=0;
					continue;
				}
				if(!"-1".equals(rs.getString("fid"))&&j==0){
					sBuffer.append("<ul><li id='"+rs.getString("function_id")+"' "+str2+">"+rs.getString("showname")+"</li>");
					j++;
					continue;
				}
				if(!"-1".equals(rs.getString("fid"))&&j>0){
					sBuffer.append("<li id='"+rs.getString("function_id")+"' "+str2+">"+rs.getString("showname")+"</li>");
					continue;
				}
				
				
			}
			sBuffer.append("</li></ul>");
			System.out.println(sBuffer);
			
		}catch(Exception e){
			
		}finally{
			if(rs!=null)
			try {rs.close();} catch (SQLException e1) {	e1.printStackTrace();}
			if(pstm!=null)
				try {pstm.close();} catch (SQLException e) {e.printStackTrace();}	
			if(conn!=null)
				try {conn.close();	} catch (SQLException e) {e.printStackTrace();}
		}
		System.out.println(sBuffer);
		
		return sBuffer.toString();
	}
	
	
	public List<String> getPermissionList(String groupID){
		
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append("select t.group_id g1,t.function_id,t.isuse,t.pagename,t.showname,t.function_fid fid ,t2.function_id fid2,t2.group_id g2 from BMWZ_PERMISSION_JSP t \n");
		sBuffer.append("left join BMWZ_PERMISSION_JSP t2 on (t2.function_id=t.function_id and t2.group_id=?) \n");
		sBuffer.append("where t.group_id='-1'\n");
		sBuffer.append("start with t.function_fid='-1' \n");
		sBuffer.append("connect by prior t.function_id = t.function_fid\n");
		
		List<String> list = new ArrayList<String>();
		int i =0,j=0;
		String str1 = "" , str2="";
		boolean flag1 = false, flag2=false;
	
		try{
			conn = ConnectionManager.getInstance().getConnection();
			pstm = conn.prepareStatement(sBuffer.toString());
			pstm.setString(1, groupID);
			rs = pstm.executeQuery();	
			String pageString = "";
			while(rs.next()){
				
				System.out.println(rs.getString("g2"));
				
				pageString = StringUtil.nullToEmpty(rs.getString("pagename"));
				if(rs.getString("g2")!=null  &&  !"NULL".equalsIgnoreCase(rs.getString("g2"))  &&  !"".equals(pageString)){
					list.add(pageString);
				}

				
			}
			
		}catch(Exception e){
			
		}finally{
			if(rs!=null)
			try {rs.close();} catch (SQLException e1) {	e1.printStackTrace();}
			if(pstm!=null)
				try {pstm.close();} catch (SQLException e) {e.printStackTrace();}	
			if(conn!=null)
				try {conn.close();	} catch (SQLException e) {e.printStackTrace();}
		}
		
		return list;
	}
	
	
	
	
	
	
	public String savePermission(String groupID, String[] fid){
		
		String delSql = "delete from BMWZ_PERMISSION_JSP where group_id=? ";
		String insertSql = "insert into BMWZ_PERMISSION_JSP(group_id,Function_Id) values(?,?)";
		
		
		Connection conn = null;
		PreparedStatement pstm = null,pstm1=null;
		
		try{
			conn = ConnectionManager.getInstance().getConnection();
			conn.setAutoCommit(false);
			pstm = conn.prepareStatement(delSql);
			pstm1 = conn.prepareStatement(insertSql);
			
			pstm.setString(1, groupID);
			
			for(String str : fid){
				pstm1.setString(1, groupID);
				pstm1.setString(2, str);
				pstm1.addBatch();
			}
			int count = pstm.executeUpdate();
			
			pstm1.executeBatch();
			conn.commit();
		}catch(Exception e){
			try {conn.rollback();} catch (SQLException e1) {e1.printStackTrace();}
			e.printStackTrace();
			return "Error";
		}finally{
			if(pstm1!=null)
				try {pstm1.close();} catch (SQLException e) {e.printStackTrace();}	
			if(pstm!=null)
				try {pstm.close();} catch (SQLException e) {e.printStackTrace();}	
			if(conn!=null)
				try {conn.close();	} catch (SQLException e) {e.printStackTrace();}
		}
		return "OK";
	}
	
	
	
}
