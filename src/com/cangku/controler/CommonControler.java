package com.cangku.controler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.cangku.bean.StoreBean;
import com.gzb.db_source.ConnectionManager;
import com.gzb.util.StringUtil;

public class CommonControler {

	private static Logger logger = Logger.getLogger(CommonControler.class); 
	
	
	
	public String getMaterialID(String id){
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = "select * from ( select r.receipt_material_id mid from bmwz_receipt r "
+"where r.receipt_id = ? union all select rq.request_meterial_id mid from bmwz_request rq where rq.request_id = ?)";
		
		String materialID = "";
		
		try{
			conn = ConnectionManager.getInstance().getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, id);
			pstm.setString(2, id);
			rs = pstm.executeQuery();
			if(rs.next()){
				materialID = rs.getString(1);
			}
		}catch(Exception e){
			logger.error(e.toString());
		}finally{
			if(rs!=null)
				try {rs.close();} catch (SQLException e1) {	e1.printStackTrace();}
			if(pstm!=null)
				try {pstm.close();} catch (SQLException e) {e.printStackTrace();}	
			if(conn!=null)
				try {conn.close();	} catch (SQLException e) {e.printStackTrace();}
		}
		System.out.println("===>"+materialID);
		return materialID;
	}
	
	
	
	
	
	public List<String> getConfigValue(String strs[]){
		List<String> list = new ArrayList<String>();
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = "select config_value from bmwz_config where config_type=? and config_value like ? order by 1";
		
		String code = StringUtil.nullToEmpty(strs[1]);
		String str  = StringUtil.nullToEmpty(strs[0]);
		try{
			conn = ConnectionManager.getInstance().getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, code);
			pstm.setString(2, "%"+str+"%");
			rs = pstm.executeQuery();
			while(rs.next()){
				list.add(rs.getString(1));
			}
		}catch(Exception e){
			logger.error(e.toString());
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
	
	
	public List<String> getMaterialName(String str){
		List<String> list = new ArrayList<String>();
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = "select distinct material_name||'-'||m.material_bak from  bmwz_material m where m.material_name like ? or m.material_bak like ? and rownum<10 order by 1";
		try{
			conn = ConnectionManager.getInstance().getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1,  "%"+str+"%");
			pstm.setString(2, str+"%");
			rs = pstm.executeQuery();
			while(rs.next()){
				list.add(rs.getString(1));
			}
			System.out.println(list);
			
			
		}catch(Exception e){
			logger.error(e.toString());
		}finally{
			if(rs!=null)
				try {rs.close();} catch (SQLException e1) {	e1.printStackTrace();}
			if(pstm!=null)
				try {pstm.close();} catch (SQLException e) {e.printStackTrace();}	
			if(conn!=null)
				try {conn.close();	} catch (SQLException e) {e.printStackTrace();}
		}		
		System.out.println(list);
		return list;
	}
	
	
	public List<String> getCategoryName(String str){
		List<String> list = new ArrayList<String>();
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		sql.append("select category_name from bmwz_category\n");
		sql.append("start with category_name like ? \n");
		sql.append("connect by prior category_id = category_fid ");
		try{
			conn = ConnectionManager.getInstance().getConnection();
			pstm = conn.prepareStatement(sql.toString());
			pstm.setString(1,  "%"+str+"%");
			rs = pstm.executeQuery();
			while(rs.next()){
				list.add(rs.getString(1));
			}
		}catch(Exception e){
			logger.error(e.toString());
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
	
	
	public List<String> getDeptName(){
		List<String> list = new ArrayList<String>();
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		sql.append("select d.department_name from bmwz_department d\n");
		sql.append("start with d.department_fid='-1' \n");
		sql.append("connect by prior d.department_id = department_fid ");
		try{
			conn = ConnectionManager.getInstance().getConnection();
			pstm = conn.prepareStatement(sql.toString());
			rs = pstm.executeQuery();
			while(rs.next()){
				list.add(rs.getString(1));
			}
		}catch(Exception e){
			logger.error(e.toString());
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
	
	public List<String> getGYDW(String str){
		List<String> list = new ArrayList<String>();
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		sql.append("select d.department_name from bmwz_department d \n");
		sql.append("start with d.department_fid='-1'\n");
		sql.append("connect by prior d.department_id = d.department_fid ");
		try{
			conn = ConnectionManager.getInstance().getConnection();
			pstm = conn.prepareStatement(sql.toString());
			rs = pstm.executeQuery();
			while(rs.next()){
				list.add(rs.getString(1));
			}
		}catch(Exception e){
			logger.error(e.toString());
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
	
	
	public List<String> getMaterStyle(String str){
		List<String> list = new ArrayList<String>();
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		sql.append("select distinct m.material_size from bmwz_material m \n");
		sql.append("where (m.material_name||'-'||m.material_bak ) =?\n");
		try{
			conn = ConnectionManager.getInstance().getConnection();
			pstm = conn.prepareStatement(sql.toString());
			pstm.setString(1, str);
			rs = pstm.executeQuery();
			while(rs.next()){
				list.add(rs.getString(1));
			}
		}catch(Exception e){
			logger.error(e.toString());
		}finally{
			if(rs!=null)
				try {rs.close();} catch (SQLException e1) {	e1.printStackTrace();}
			if(pstm!=null)
				try {pstm.close();} catch (SQLException e) {e.printStackTrace();}	
			if(conn!=null)
				try {conn.close();	} catch (SQLException e) {e.printStackTrace();}
		}		
		System.out.println(str+":style=>"+list);
		return list;
	}
	
	
	public List<String> getCategoryAndSize(String[] str){
		
		List<String> list = new ArrayList<String>();
		list.add(0,"");
		list.add(1,"");
		list.add(2,"");
		list.add(3,"");
		list.add(4,"");
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		StringBuffer sql = new StringBuffer();
		sql.append("select c.category_name,m.material_module,m.material_bak,m.material_id,m.material_type from bmwz_material m \n");
		sql.append("left join bmwz_category c on m.material_type=c.category_id \n");
		sql.append("where 1=1 and (m.material_name||'-'||m.material_bak)=? and trim(m.material_size)=trim(?) \n");
		
		
		System.out.println(sql+"\n"+str[0]+"|"+str[1]);
		
		try{
			conn = ConnectionManager.getInstance().getConnection();
			pstm = conn.prepareStatement(sql.toString());
			pstm.setString(1, StringUtil.nullToEmpty(str[0]));
			pstm.setString(2, StringUtil.nullToEmpty(str[1]));
			
			rs = pstm.executeQuery();
			if(rs.next()){
				
				list.add(0, rs.getString(1));
				list.add(1, rs.getString(2));
				list.add(2, rs.getString(3));
				list.add(3, rs.getString(4));
				list.add(4, rs.getString(5));
			}
			System.out.println("=====>"+list);
			
			
		}catch(Exception e){
			logger.error(e.toString());
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
	
	
	public List<StoreBean> getCangKu(){
		List<StoreBean> list = new ArrayList<StoreBean>();
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		sql.append("select s.storehouse_id,s.storehouse_name from bmwz_storehouse s order by 1 desc\n");
		StoreBean bean = null;
		try{
			conn = ConnectionManager.getInstance().getConnection();
			pstm = conn.prepareStatement(sql.toString());
			
			rs = pstm.executeQuery();
			while(rs.next()){
				bean = new StoreBean();
				bean.setStoreID(rs.getString("storehouse_id"));
				bean.setStoreName(rs.getString("storehouse_name"));
				System.out.println(bean.getStoreID()+bean.getStoreName());
				list.add(bean);
			}
		}catch(Exception e){
			logger.error(e.toString());
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
	
	
	public List<String> getFeeList(String feeName){
		
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("select rownum,f1.fee_id fid1 ,f1.fee_name fname1,f2.fee_name fname2 ,f2.fee_id fid2,f1.fee_matou\n");
		sqlBuffer.append("from bmwz_feeproject f1 left join bmwz_feeproject f2 on f1.fee_fid=f2.fee_id \n");
		sqlBuffer.append("where 1=1  \n");
		
		String appendSql = " start with f1.fee_fid='-1' connect by prior f1.fee_id = f1.fee_fid";
		
		List<String> list = new ArrayList<String>();
		
		if(!"".equals(StringUtil.nullToEmpty(feeName))){
			sqlBuffer.append(" and f1.fee_name like ? \n");
		}
		System.out.println("===========");
		try{
			conn = ConnectionManager.getInstance().getConnection();
			pstm = conn.prepareStatement(sqlBuffer.toString()+appendSql);
			
			if(!"".equals(StringUtil.nullToEmpty(feeName))){
				pstm.setString(1, "%"+feeName+"%");
			}
			
			rs = pstm.executeQuery();
			while(rs.next()){
				list.add(StringUtil.nullToEmptyAppend(rs.getString(1))+StringUtil.nullToEmptyAppend(rs.getString(2))
						+StringUtil.nullToEmptyAppend(rs.getString(3))+StringUtil.nullToEmptyAppend(rs.getString(4))
						+StringUtil.nullToEmptyAppend(rs.getString(5))+StringUtil.nullToEmptyAppend(rs.getString(6)));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(rs!=null)
				try {rs.close();} catch (SQLException e) {e.printStackTrace();}
			if(pstm!=null)
				try {rs.close();} catch (SQLException e) {e.printStackTrace();}
			if(conn!=null)
				try {rs.close();} catch (SQLException e) {e.printStackTrace();}
			
		}

		return list;
	}
	
	
	
	
	
	
	
	
	
	
}
