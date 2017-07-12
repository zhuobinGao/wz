package com.cangku.controler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.cangku.bean.CategoryBean;
import com.cangku.bean.MaterialBean;
import com.gzb.db_source.ConnectionManager;
import com.gzb.util.StringUtil;

public class BasesetControler {

	private static Logger logger = Logger.getLogger(BasesetControler.class); 
	
	public List<MaterialBean> onSearchMaterial(MaterialBean selBean){
		
		List<MaterialBean> list = new ArrayList<MaterialBean>();
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		MaterialBean bean = null;
		
		StringBuffer sql = new StringBuffer();
		sql.append("select m.material_id,m.material_name,c.category_name,m.material_module,m.material_size,m.material_bak,m.material_count, \n");
		sql.append("( select wm_concat(to_char(r.receipt_postion)) from bmwz_receipt r where r.receipt_ar_mount_has>0 and r.receipt_material_id=m.material_id ) postion, \n");
		sql.append("decode(m.MATERIAL_ISONCHECK,'1','启用','未启用') MATERIAL_ISONCHECK , m.material_minmount,m.material_maxmount,MATERIAL_USED \n");
		sql.append("from bmwz_material m left join bmwz_category c on c.category_id=m.material_type where 1=1 \n");
		
		
		if(!"".equals(selBean.getMaterialName())){
			sql.append("and ( m.material_name like ? or  m.material_bak like ? ) \n");
		}
		
		if(!"".equals(selBean.getSupplyDept())){
			sql.append("and m.material_id in ( select r.receipt_material_id from bmwz_receipt r where r.receipt_supplydept like ?) \n");
		}
		if(!"".equals(selBean.getCategoryName())){
			sql.append("and m.material_type in (select c.category_id from bmwz_category c start with category_name like ?  connect by prior category_id = category_fid)");
		}
		
		try{
			conn = ConnectionManager.getInstance().getConnection();
			pstm = conn.prepareStatement(sql.toString());
		  
			int i = 1;
			
			if(!"".equals(selBean.getMaterialName())){
				pstm.setString(i++, "%"+selBean.getMaterialName()+"%");
				pstm.setString(i++, "%"+selBean.getMaterialName()+"%");
			}
			
			if(!"".equals(selBean.getSupplyDept())){
				pstm.setString(i++, "%"+selBean.getSupplyDept()+"%");	
			}
			if(!"".equals(selBean.getCategoryName())){
				pstm.setString(i++, "%"+selBean.getCategoryName()+"%");	
			}
			rs = pstm.executeQuery();
			
			/*
			 * sql.append("select m.material_id,m.material_name,c.category_name,m.material_type,m.material_size,m.material_bak,m.material_count, \n");
		sql.append("( select wm_concat(to_char(r.receipt_postion)) from bmwz_receipt r where r.receipt_ar_mount_has>0 and r.receipt_material_id=m.material_id ) postion, \n");
		sql.append("from bmwz_material m left join bmwz_category c on c.category_id=m.material_type where 1=1 \n");
			 */
			
			while(rs.next()){
				bean = new MaterialBean();
				
				bean.setUsage(rs.getString("MATERIAL_USED"));
				
				bean.setMaterialID(rs.getString("material_id"));
				bean.setMaterialName(rs.getString("material_name"));
				bean.setCategoryName(rs.getString("category_name"));
				bean.setSize(rs.getString("material_module"));
				bean.setMaterialStyle(rs.getString("material_size"));
				
				bean.setMaterialBak(rs.getString("material_bak"));
				bean.setKuCun(rs.getString("material_count"));
				bean.setPostrion(rs.getString("postion"));
				bean.setIsOnCheck(rs.getString("MATERIAL_ISONCHECK"));
				bean.setUpLine(rs.getString("material_minmount"));
				
				bean.setDownLine(rs.getString("material_maxmount"));
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
	
	public String saveMaterial(MaterialBean bean){
		
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		sql.append("insert into bmwz_material(MATERIAL_ID,MATERIAL_NAME,MATERIAL_TYPE,MATERIAL_SIZE,MATERIAL_MODULE,MATERIAL_USED,MATERIAL_BAK) values(sys_guid(),?,?,?,?,?,? )");
		try{
			conn = ConnectionManager.getInstance().getConnection();
			conn.setAutoCommit(false);
			pstm = conn.prepareStatement(sql.toString());
		    pstm.setString(1, bean.getMaterialName());
			pstm.setString(2, bean.getMaterialType());
			pstm.setString(3, bean.getMaterialStyle());
			pstm.setString(4, bean.getSize());
			
			pstm.setString(5, bean.getUsage());
			pstm.setString(6, bean.getMaterialBak());
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
				logger.error(e1.toString());
			}
			logger.error(e.toString());
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
	
	
	public String updateMaterial(MaterialBean bean){
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		sql.append("update bmwz_material set MATERIAL_NAME = ?,MATERIAL_TYPE=?,MATERIAL_SIZE=?,MATERIAL_MODULE=?,MATERIAL_USED=?,MATERIAL_BAK=?\n");
		sql.append("where MATERIAL_ID=? ");
		try{
			conn = ConnectionManager.getInstance().getConnection();
			conn.setAutoCommit(false);
			pstm = conn.prepareStatement(sql.toString());
			pstm.setString(1, bean.getMaterialName());
			pstm.setString(2, bean.getMaterialType());
			pstm.setString(3, bean.getMaterialStyle());
			pstm.setString(4, bean.getSize());
				
			
			System.out.println(bean.getSize());
			
			pstm.setString(5, bean.getUsage());
			pstm.setString(6, bean.getMaterialBak());
			pstm.setString(7, bean.getMaterialID());
			
			int count = pstm.executeUpdate();
			if(count<1){
				return "已关联出入库，不可修改!";
			}
			conn.commit();
		}catch(Exception e){
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
				logger.error(e1.toString());
			}
			logger.error(e.toString());
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
	
	
	
	
	
	public String delMaterial(String materialID){
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		sql.append("delete from bmwz_material m where m.material_flag=0 and m.material_id=? ");
		try{
			conn = ConnectionManager.getInstance().getConnection();
			conn.setAutoCommit(false);
			pstm = conn.prepareStatement(sql.toString());
		    pstm.setString(1, materialID);
			
			int count = pstm.executeUpdate();
			if(count<1){
				return "已关联出入库，不可删除!";
			}
			conn.commit();
		}catch(Exception e){
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
				logger.error(e1.toString());
			}
			logger.error(e.toString());
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
	
	
	public List<String> getCategoryList(String[] params){
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("select rownum,c.category_id,c.category_name,nvl(c2.category_name,'顶级分类') name2  from bmwz_category c \n");
		sqlBuffer.append("left join bmwz_category c2 on c.category_fid=c2.category_id \n");
		sqlBuffer.append("where 1=1 \n");
		
		String appendSql = "start with c.category_fid=-1 connect by prior c.category_id = c.category_fid\n";
		List<String> list = new ArrayList<String>();
		
		if(params.length<3){
			return null;
		}
		int i = 0;
		if(!"".equals(StringUtil.nullToEmpty(params[0]))){
			sqlBuffer.append(" and c.category_id=? ");
		}
		
		if(!"".equals(StringUtil.nullToEmpty(params[1]))){
			sqlBuffer.append(" and c.category_name like ? ");
		}
		
		if(!"".equals(StringUtil.nullToEmpty(params[2]))){
			sqlBuffer.append(" and nvl(c2.category_name,'顶级分类') like ? ");
		}
		
		
		try{
			conn = ConnectionManager.getInstance().getConnection();
			pstm = conn.prepareStatement(sqlBuffer.toString()+appendSql);
			
			i=1;
			if(!"".equals(StringUtil.nullToEmpty(params[0]))){
				pstm.setString(i, params[0]);
				i++;
			}
			
			if(!"".equals(StringUtil.nullToEmpty(params[1]))){
				pstm.setString(i, params[1]);
				i++;
			}
			
			if(!"".equals(StringUtil.nullToEmpty(params[2]))){
				pstm.setString(i, params[2]);
				i++;
			}
			rs = pstm.executeQuery();
			while(rs.next()){
				list.add(StringUtil.nullToEmptyAppend(rs.getString(1))+StringUtil.nullToEmptyAppend(rs.getString(2))+StringUtil.nullToEmptyAppend(rs.getString(3))+StringUtil.nullToEmptyAppend(rs.getString(4)));
				
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
	
	
	public String getCategoryString(String[] params){
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("select rownum,c.category_id,c.category_name,nvl(c2.category_name,'顶级分类') name2,c.category_fid  from bmwz_category c \n");
		sqlBuffer.append("left join bmwz_category c2 on c.category_fid=c2.category_id \n");
		sqlBuffer.append("where 1=1 \n");
		
		String appendSql = "start with c.category_fid=-1 connect by prior c.category_id = c.category_fid\n";
	
		
		StringBuffer sBuffer = new StringBuffer();
		
		if(params.length<3){
			return null;
		}
		int i = 0;
		if(!"".equals(StringUtil.nullToEmpty(params[0]))){
			sqlBuffer.append(" and c.category_id=? ");
		}
		
		if(!"".equals(StringUtil.nullToEmpty(params[1]))){
			sqlBuffer.append(" and c.category_name like ? ");
		}
		
		if(!"".equals(StringUtil.nullToEmpty(params[2]))){
			sqlBuffer.append(" and nvl(c2.category_name,'顶级分类') like ? ");
		}
		
		
		try{
			conn = ConnectionManager.getInstance().getConnection();
			pstm = conn.prepareStatement(sqlBuffer.toString()+appendSql);
			
			i=1;
			if(!"".equals(StringUtil.nullToEmpty(params[0]))){
				pstm.setString(i, params[0]);
				i++;
			}
			
			if(!"".equals(StringUtil.nullToEmpty(params[1]))){
				pstm.setString(i, params[1]);
				i++;
			}
			
			if(!"".equals(StringUtil.nullToEmpty(params[2]))){
				pstm.setString(i, params[2]);
				i++;
			}
			rs = pstm.executeQuery();
			String name = "";
			String id = "", fid = "";
			CategoryBean bean = null,tempBean=null;
			List<CategoryBean> listCategoryBeans = new ArrayList<CategoryBean>();
			Map<String, CategoryBean> map = new HashMap<String, CategoryBean>();
			while(rs.next()){
				name = rs.getString(3);
				id = rs.getString(2);
				fid = rs.getString(5);
				bean = new CategoryBean(id, name, fid);
				if(fid.equals("-1")){
					listCategoryBeans.add(bean);
				}
				if(map.get(id)==null){
					map.put(id, bean);
				}
				bean = map.get(id);
				
				if(map.get(bean.getFid())!=null){
					tempBean = map.get(bean.getFid());
					tempBean.list.add(bean);
				}

			}
			
			sBuffer.append("<ul><li id='-1'>物资类别<ul>");
			
			for(CategoryBean bean1:listCategoryBeans){
				sBuffer.append(CategoryBean.createString(bean1));
			}
			sBuffer.append("</ul></li></ul>");
		
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
		return sBuffer.toString();
	}
	
	
	public String delCategory(String categroyID){
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" select count(1) from bmwz_receipt r where r.catory_id in (\n");
		sqlBuffer.append("select c.category_id from bmwz_category c   \n");
		sqlBuffer.append("where c.category_id =?  start with c.category_fid='-1' connect by prior c.category_id = c.category_fid )\n");
		
		String delSql = "delete from bmwz_category where category_id=?";
		
		try{
			conn = ConnectionManager.getInstance().getConnection();
			conn.setAutoCommit(false);
			pstm = conn.prepareStatement(sqlBuffer.toString());
			pstm.setString(1, categroyID);
			rs = pstm.executeQuery();
			if(rs.next()){
				if(rs.getInt(1)>0){
					return "已关联出入库，不能删除!";
				}
			}
			pstm.close();
			pstm = conn.prepareStatement(delSql);
			pstm.setString(1, categroyID);
			pstm.executeUpdate();
			conn.commit();
		
			System.out.println("categroyID:"+categroyID);
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
		
		return "OK";
	}
	
	
	public List<String> searchWareHouse(String name){
		List<String> list = new ArrayList<String>();
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		StringBuffer sqlBuffer = new StringBuffer("select  t.storehouse_id,t.storehouse_name,t.storehouse_bak from bmwz_storehouse t where 1=1 \n");
		if(!"".equals(name)){
			sqlBuffer.append("and storehouse_name like ? ");
		}
		
		try{
			conn = ConnectionManager.getInstance().getConnection();
			pstm = conn.prepareStatement(sqlBuffer.toString());
			if(!"".equals(name)){
				pstm.setString(1, name);
			}
			rs = pstm.executeQuery();
			while(rs.next()){
				list.add(StringUtil.nullToEmptyAppend(rs.getString(1))+StringUtil.nullToEmptyAppend(rs.getString(2))+StringUtil.nullToEmptyAppend(rs.getString(3)));
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
	
	
	public String delStoreHouse(String houseID){
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" select 1 from bmwz_receipt r where r.receipt_storageno=? ");
		
		
		String delSql = "delete from bmwz_storehouse where storehouse_id=?";
		
		try{
			conn = ConnectionManager.getInstance().getConnection();
			conn.setAutoCommit(false);
			pstm = conn.prepareStatement(sqlBuffer.toString());
			pstm.setString(1, houseID);
			rs = pstm.executeQuery();
			if(rs.next()){
					return "已关联出入库，不能删除!";
			}
			pstm.close();
			pstm = conn.prepareStatement(delSql);
			pstm.setString(1, houseID);
			pstm.executeUpdate();
			conn.commit();
		
		
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
		
		return "OK";
	}
	
	public List<String> getMechanismList(String name){
		List<String> list = new ArrayList<String>();
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("select t.mechanismid,t.mechanismname,decode(t.mechanismtype,'01','门机','02','岸桥','03','场桥','其他') typename ,t.mechanismtype\n");
		sqlBuffer.append("from BMWZ_MECHANISM t where 1=1 \n");
		
		if(!"".equals(name)){
			sqlBuffer.append("and mechanismname = ? ");
		}
		
		
		
		
		try{
			conn = ConnectionManager.getInstance().getConnection();
			pstm = conn.prepareStatement(sqlBuffer.toString());
			
			if(!"".equals(name)){
				pstm.setString(1, name);
			}
		
			rs = pstm.executeQuery();
			while(rs.next()){
				list.add(StringUtil.nullToEmptyAppend(rs.getString(1))+StringUtil.nullToEmptyAppend(rs.getString(2))+StringUtil.nullToEmptyAppend(rs.getString(3))+StringUtil.nullToEmptyAppend(rs.getString(4)));
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
