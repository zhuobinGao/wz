package com.cangku.controler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.cangku.bean.IReceiveBean;
import com.cangku.bean.IRequestBean;
import com.cangku.bean.SelRecAndReq;
import com.gzb.db_source.ConnectionManager;
import com.gzb.util.StringUtil;

public class InAndOutCheckControler {

	private static Logger logger = Logger.getLogger(InAndOutCheckControler.class);
	
	public List<IReceiveBean> getReceiveList(SelRecAndReq param){
		IReceiveBean bean = null;
		List<IReceiveBean> list = new ArrayList<IReceiveBean>();
		List<String> argsList = new ArrayList<String>();
		Connection conn = null;
		PreparedStatement pstm = null, pstm1=null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		StringBuffer appendBuffer = new StringBuffer();
		StringBuffer sqlCountBuffer = new StringBuffer();
		
	
		
		sql.append("select r.receipt_id,m.material_bak,m.material_name,m.material_size,m.material_module,r.receipt_ar_mount,r.receipt_ar_mount_has,\n");
		sql.append("r.receipt_a_dj,r.receipt_a_amount,to_char(r.receipt_filltime,'yyyy-mm-dd') receipt_filltime,to_char(r.receipt_receivedtime,'yyyy-mm-dd') receipt_receivedtime,r.receipt_invoiceno,r.receipt_receipter,r.receipt_purchaser,\n");
		sql.append("s.storehouse_name,r.receipt_supplydept,r.receipt_item,r.receipt_postion,r.receipt_note,r.catory_id,r.catory_name,m.material_id\n");
		sql.append("from bmwz_receipt r left join bmwz_material m on m.material_id=r.receipt_material_id\n");
		sql.append("left join BMWZ_STOREHOUSE s on s.storehouse_id=r.receipt_storageno\n");
		
		sqlCountBuffer.append("select count(1) from bmwz_receipt r left join bmwz_material m on m.material_id=r.receipt_material_id\n");
		sqlCountBuffer.append("left join BMWZ_STOREHOUSE s on s.storehouse_id=r.receipt_storageno where 1=1 \n");
		
		sql.append("where 1=1 \n");
		
		int count = 0;
		
		if(!StringUtil.isEmpty(param.getSel_cgr())){
			appendBuffer.append("and r.receipt_purchaser=? \n");
			argsList.add(param.getSel_cgr());
		}
		
		if(!StringUtil.isEmpty(param.getSel_slr())){
			appendBuffer.append("and r.receipt_receipter=? \n");
			argsList.add(param.getSel_slr());
		}
		
		if(!StringUtil.isEmpty(param.getSel_postrion())){
			appendBuffer.append("and r.receipt_postion like ? \n");
			argsList.add("%"+param.getSel_postrion()+"%");
		}
		
		if(!StringUtil.isEmpty(param.getSel_item())){
			appendBuffer.append("and r.receipt_item=? \n");
			argsList.add(param.getSel_item());
		}
		
		if(!StringUtil.isEmpty(param.getSel_materialName())){
			appendBuffer.append("and m.material_name like ? \n");
			argsList.add("%"+param.getSel_materialName()+"%");
		}
		
		if(!StringUtil.isEmpty(param.getSel_category())){
			appendBuffer.append("and r.catory_id in ( select m.category_id from bmwz_category m start with m.category_name=? connect by m.category_fid=prior m.category_id) \n");
			argsList.add(param.getSel_category());
		}
		
		if(!StringUtil.isEmpty(param.getSel_invoiceNO())){
			appendBuffer.append("and r.receipt_invoiceno like ? \n");
			argsList.add("%"+param.getSel_invoiceNO()+"%");
		}
		
		if(!StringUtil.isEmpty(param.getSel_slbh())){
			appendBuffer.append("and r.receipt_id = ? \n");
			argsList.add(param.getSel_slbh());
		}
		
		if(!StringUtil.isEmpty(param.getSel_gydw())){
			appendBuffer.append("and r.receipt_supplydept like ? \n");
			argsList.add("%"+param.getSel_gydw()+"%");
		}
		
		//receipt_receivedtime
		if(!StringUtil.isEmpty(param.getSel_sDate())){
			appendBuffer.append("and r.receipt_receivedtime >= to_date(?,'yyyy-mm-dd') \n");
			argsList.add(param.getSel_sDate());
		}
		
		if(!StringUtil.isEmpty(param.getSel_eDate())){
			appendBuffer.append("and r.receipt_receivedtime <= to_date(?,'yyyy-mm-dd') \n");
			argsList.add(param.getSel_eDate());
		}
		
		if(!StringUtil.isEmpty(param.getSel_isFP())&&!"-1".equals(param.getSel_isFP())){
			appendBuffer.append("and r.receipt_hasinvoice = ? \n");
			argsList.add(param.getSel_isFP());
		}
		
		
		appendBuffer.append(" and r.receipt_storageno=? ");
		
		/*
		 sql.append("select r.receipt_id,m.material_bak,m.material_name,r.receipt_style,r.receipt_size,r.receipt_ar_mount,r.receipt_ar_mount_has,\n");
		sql.append("r.receipt_a_dj,r.receipt_a_amount,r.receipt_filltime,r.receipt_receivedtime,r.receipt_invoiceno,r.receipt_receipter,r.receipt_purchaser,\n");
		sql.append("s.storehouse_name,r.receipt_supplydept,r.receipt_item,r.receipt_postion,r.receipt_bak,r.catory_id,r.catory_name\n");
		sql.append("from bmwz_receipt r left join bmwz_material m on m.material_id=r.receipt_material_id\n");
		sql.append("left join BMWZ_STOREHOUSE s on s.storehouse_id=r.receipt_storageno\n");
		 * */
		try{
			conn = ConnectionManager.getInstance().getConnection();
			pstm = conn.prepareStatement(sql.toString()+appendBuffer.toString());
			
			System.out.println(sql.toString()+appendBuffer.toString());
			
			pstm1 = conn.prepareStatement(sqlCountBuffer.toString()+appendBuffer.toString());
			int i = 1;
			for(String str : argsList){
				pstm1.setString(i, str);
				pstm.setString(i, str);
				i++;
			}
			pstm1.setString(i, param.getHouseID());
			pstm.setString(i, param.getHouseID());
			
			
			
			rs = pstm1.executeQuery();
			
			if(rs.next()){
				count = rs.getInt(1);
			}
			rs.close();
			bean = new IReceiveBean();
			list.add(bean);
			
			
			System.out.println("======>"+count);
			if(count>1500){
				bean.setSel_bak(count+"");
				return list;
			}
			
			
			rs = pstm.executeQuery();
			
			while(rs.next()){
				bean = new IReceiveBean();
				
				bean.setRECEIPT_ID(rs.getString("receipt_id"));
				bean.setMATERIAL_BAK(rs.getString("material_bak"));
				bean.setRECEIPT_MATERIAL_NAME(rs.getString("material_name"));
				//m.material_size,m.material_module
				bean.setRECEIPT_STYLE(rs.getString("material_size"));
				bean.setRECEIPT_SIZE(rs.getString("material_module"));
				
				bean.setRECEIPT_AR_MOUNT(rs.getString("receipt_ar_mount"));
				bean.setRECEIPT_AR_MOUNT_HAS(rs.getString("receipt_ar_mount_has"));
				bean.setRECEIPT_A_DJ(rs.getString("receipt_a_dj"));
				bean.setRECEIPT_A_AMOUNT(rs.getString("receipt_a_amount"));
				bean.setRECEIPT_FILLTIME(rs.getString("receipt_filltime"));
				
				bean.setRECEIPT_RECEIVEDTIME(rs.getString("receipt_receivedtime"));
				bean.setRECEIPT_INVOICENO(rs.getString("receipt_invoiceno"));
				bean.setRECEIPT_RECEIPTER(rs.getString("receipt_receipter"));
				bean.setRECEIPT_PURCHASER(rs.getString("receipt_purchaser"));
				bean.setRECEIPT_STORAGENO(rs.getString("storehouse_name"));
				
				bean.setRECEIPT_SUPPLYDEPT(rs.getString("receipt_supplydept"));
				bean.setRECEIPT_ITEM(rs.getString("receipt_item"));
				bean.setRECEIPT_POSTION(rs.getString("receipt_postion"));
				bean.setRECEIPT_BAK(rs.getString("receipt_note"));
				bean.setCATORY_ID(rs.getString("catory_id"));
				
				bean.setCATORY_NAME(rs.getString("catory_name"));
				bean.setRECEIPT_MATERIAL_ID(rs.getString("material_id"));
				
				list.add(bean);
			}
		}catch(Exception e){
			logger.error(e.toString());
			return list;
		}finally{
			if(rs!=null)
				try {rs.close();} catch (SQLException e1) {	e1.printStackTrace();}
			if(pstm!=null)
				try {pstm.close();} catch (SQLException e) {e.printStackTrace();}	
			if(pstm1!=null)
				try {pstm1.close();} catch (SQLException e) {e.printStackTrace();}	
			if(conn!=null)
				try {conn.close();	} catch (SQLException e) {e.printStackTrace();}
		}		
		System.out.println(list);
		return list;
	}
	
	public List<IReceiveBean> getReceiveListHis(SelRecAndReq param){
		IReceiveBean bean = null;
		List<IReceiveBean> list = new ArrayList<IReceiveBean>();
		List<String> argsList = new ArrayList<String>();
		Connection conn = null;
		PreparedStatement pstm = null, pstm1=null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		StringBuffer appendBuffer = new StringBuffer();
		StringBuffer sqlCountBuffer = new StringBuffer();
		
	
		
		sql.append("select r.receipt_id,m.material_bak,m.material_name,m.material_size,m.material_module,r.receipt_ar_mount,r.receipt_ar_mount_has,\n");
		sql.append("r.receipt_a_dj,r.receipt_a_amount,to_char(r.receipt_filltime,'yyyy-mm-dd') receipt_filltime,to_char(r.receipt_receivedtime,'yyyy-mm-dd') receipt_receivedtime,r.receipt_invoiceno,r.receipt_receipter,r.receipt_purchaser,\n");
		sql.append("s.storehouse_name,r.receipt_supplydept,r.receipt_item,r.receipt_postion,r.receipt_note,r.catory_id,r.catory_name,m.material_id\n");
		sql.append("from bmwz_receipt_his r left join bmwz_material m on m.material_id=r.receipt_material_id\n");
		sql.append("left join BMWZ_STOREHOUSE s on s.storehouse_id=r.receipt_storageno\n");
		
		sqlCountBuffer.append("select count(1) from bmwz_receipt_his r left join bmwz_material m on m.material_id=r.receipt_material_id\n");
		sqlCountBuffer.append("left join BMWZ_STOREHOUSE s on s.storehouse_id=r.receipt_storageno where 1=1 \n");
		
		sql.append("where 1=1 \n");
		
		int count = 0;
		
		if(!StringUtil.isEmpty(param.getCalDate())){
			appendBuffer.append("and r.receipt_caldate=? \n");
			argsList.add(param.getCalDate());
		}
		
		
		if(!StringUtil.isEmpty(param.getSel_cgr())){
			appendBuffer.append("and r.receipt_purchaser=? \n");
			argsList.add(param.getSel_cgr());
		}
		
		if(!StringUtil.isEmpty(param.getSel_slr())){
			appendBuffer.append("and r.receipt_receipter=? \n");
			argsList.add(param.getSel_slr());
		}
		
		if(!StringUtil.isEmpty(param.getSel_postrion())){
			appendBuffer.append("and r.receipt_postion like ? \n");
			argsList.add("%"+param.getSel_postrion()+"%");
		}
		
		if(!StringUtil.isEmpty(param.getSel_item())){
			appendBuffer.append("and r.receipt_item=? \n");
			argsList.add(param.getSel_item());
		}
		
		if(!StringUtil.isEmpty(param.getSel_materialName())){
			appendBuffer.append("and m.material_name like ? \n");
			argsList.add("%"+param.getSel_materialName()+"%");
		}
		
		if(!StringUtil.isEmpty(param.getSel_category())){
			
			appendBuffer.append("and r.catory_id in ( select m.category_id from bmwz_category m start with m.category_name=? connect by m.category_fid=prior m.category_id) \n");
			
			argsList.add(param.getSel_category());
		}
		
		if(!StringUtil.isEmpty(param.getSel_invoiceNO())){
			appendBuffer.append("and r.receipt_invoiceno like ? \n");
			argsList.add("%"+param.getSel_invoiceNO()+"%");
		}
		
		if(!StringUtil.isEmpty(param.getSel_slbh())){
			appendBuffer.append("and r.receipt_id = ? \n");
			argsList.add(param.getSel_slbh());
		}
		
		if(!StringUtil.isEmpty(param.getSel_gydw())){
			appendBuffer.append("and r.receipt_supplydept like ? \n");
			argsList.add("%"+param.getSel_gydw()+"%");
		}
		
		//receipt_receivedtime
		if(!StringUtil.isEmpty(param.getSel_sDate())){
			appendBuffer.append("and r.receipt_receivedtime >= to_date(?,'yyyy-mm-dd') \n");
			argsList.add(param.getSel_sDate());
		}
		
		if(!StringUtil.isEmpty(param.getSel_eDate())){
			appendBuffer.append("and r.receipt_receivedtime <= to_date(?,'yyyy-mm-dd') \n");
			argsList.add(param.getSel_eDate());
		}
		
		appendBuffer.append(" and r.receipt_storageno=? ");
		
		
		/*
		 sql.append("select r.receipt_id,m.material_bak,m.material_name,r.receipt_style,r.receipt_size,r.receipt_ar_mount,r.receipt_ar_mount_has,\n");
		sql.append("r.receipt_a_dj,r.receipt_a_amount,r.receipt_filltime,r.receipt_receivedtime,r.receipt_invoiceno,r.receipt_receipter,r.receipt_purchaser,\n");
		sql.append("s.storehouse_name,r.receipt_supplydept,r.receipt_item,r.receipt_postion,r.receipt_bak,r.catory_id,r.catory_name\n");
		sql.append("from bmwz_receipt r left join bmwz_material m on m.material_id=r.receipt_material_id\n");
		sql.append("left join BMWZ_STOREHOUSE s on s.storehouse_id=r.receipt_storageno\n");
		 * */
		try{
			conn = ConnectionManager.getInstance().getConnection();
			pstm = conn.prepareStatement(sql.toString()+appendBuffer.toString());
			pstm1 = conn.prepareStatement(sqlCountBuffer.toString()+appendBuffer.toString());
			int i = 1;
			for(String str : argsList){
				pstm1.setString(i, str);
				pstm.setString(i, str);
				i++;
			}
			pstm1.setString(i, param.getHouseID());
			pstm.setString(i, param.getHouseID());
			
			rs = pstm1.executeQuery();
			
			if(rs.next()){
				count = rs.getInt(1);
			}
			rs.close();
			bean = new IReceiveBean();
			list.add(bean);
			
			
			System.out.println("======>"+count);
			if(count>1500){
				bean.setSel_bak(count+"");
				return list;
			}
			
			
			rs = pstm.executeQuery();
			
			while(rs.next()){
				bean = new IReceiveBean();
				
				bean.setRECEIPT_ID(rs.getString("receipt_id"));
				bean.setMATERIAL_BAK(rs.getString("material_bak"));
				bean.setRECEIPT_MATERIAL_NAME(rs.getString("material_name"));
				bean.setRECEIPT_STYLE(rs.getString("material_size"));
				bean.setRECEIPT_SIZE(rs.getString("material_module"));
				
				bean.setRECEIPT_AR_MOUNT(rs.getString("receipt_ar_mount"));
				bean.setRECEIPT_AR_MOUNT_HAS(rs.getString("receipt_ar_mount_has"));
				bean.setRECEIPT_A_DJ(rs.getString("receipt_a_dj"));
				bean.setRECEIPT_A_AMOUNT(rs.getString("receipt_a_amount"));
				bean.setRECEIPT_FILLTIME(rs.getString("receipt_filltime"));
				
				bean.setRECEIPT_RECEIVEDTIME(rs.getString("receipt_receivedtime"));
				bean.setRECEIPT_INVOICENO(rs.getString("receipt_invoiceno"));
				bean.setRECEIPT_RECEIPTER(rs.getString("receipt_receipter"));
				bean.setRECEIPT_PURCHASER(rs.getString("receipt_purchaser"));
				bean.setRECEIPT_STORAGENO(rs.getString("storehouse_name"));
				
				bean.setRECEIPT_SUPPLYDEPT(rs.getString("receipt_supplydept"));
				bean.setRECEIPT_ITEM(rs.getString("receipt_item"));
				bean.setRECEIPT_POSTION(rs.getString("receipt_postion"));
				bean.setRECEIPT_BAK(rs.getString("receipt_note"));
				bean.setCATORY_ID(rs.getString("catory_id"));
				
				bean.setCATORY_NAME(rs.getString("catory_name"));
				bean.setRECEIPT_MATERIAL_ID(rs.getString("material_id"));
				
				list.add(bean);
			}
		}catch(Exception e){
			logger.error(e.toString());
			return list;
		}finally{
			if(rs!=null)
				try {rs.close();} catch (SQLException e1) {	e1.printStackTrace();}
			if(pstm!=null)
				try {pstm.close();} catch (SQLException e) {e.printStackTrace();}	
			if(pstm1!=null)
				try {pstm1.close();} catch (SQLException e) {e.printStackTrace();}	
			if(conn!=null)
				try {conn.close();	} catch (SQLException e) {e.printStackTrace();}
		}		
		
		return list;
	}
	
	
	public List<IReceiveBean> getReceiveListOther(SelRecAndReq param){
		System.out.println("other");
		IReceiveBean bean = null;
		List<IReceiveBean> list = new ArrayList<IReceiveBean>();
		List<String> argsList = new ArrayList<String>();
		Connection conn = null;
		PreparedStatement pstm = null, pstm1=null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		StringBuffer appendBuffer = new StringBuffer();
		StringBuffer sqlCountBuffer = new StringBuffer();
	
		sql.append("select r.receipt_id,m.material_bak,m.material_name,m.material_size,m.material_module,r.receipt_ar_mount,r.receipt_ar_mount_has,\n");
		sql.append("round(r.receipt_a_dj,6) receipt_a_dj, round(r.receipt_a_amount,2) receipt_a_amount,to_char(r.receipt_filltime,'yyyy-mm-dd') receipt_filltime,to_char(r.receipt_receivedtime,'yyyy-mm-dd') receipt_receivedtime,r.receipt_invoiceno,r.receipt_receipter,r.receipt_purchaser,\n");
		sql.append("s.storehouse_name,r.receipt_supplydept,r.receipt_item,r.receipt_postion,r.receipt_note,r.catory_id,r.catory_name,m.material_id\n");
		sql.append("from bmwz_receipt_other r left join bmwz_material m on m.material_id=r.receipt_material_id\n");
		sql.append("left join BMWZ_STOREHOUSE s on s.storehouse_id=r.receipt_storageno\n");
		
		sqlCountBuffer.append("select count(1) from bmwz_receipt r left join bmwz_material m on m.material_id=r.receipt_material_id\n");
		sqlCountBuffer.append("left join BMWZ_STOREHOUSE s on s.storehouse_id=r.receipt_storageno where 1=1 \n");
		
		sql.append("where 1=1 \n");
		
		int count = 0;
		
		if(!StringUtil.isEmpty(param.getSel_cgr())){
			appendBuffer.append("and r.receipt_purchaser=? \n");
			argsList.add(param.getSel_cgr());
		}
		
		if(!StringUtil.isEmpty(param.getSel_slr())){
			appendBuffer.append("and r.receipt_receipter=? \n");
			argsList.add(param.getSel_slr());
		}
		
		if(!StringUtil.isEmpty(param.getSel_postrion())){
			appendBuffer.append("and r.receipt_postion like ? \n");
			argsList.add("%"+param.getSel_postrion()+"%");
		}
		
		if(!StringUtil.isEmpty(param.getSel_item())){
			appendBuffer.append("and r.receipt_item=? \n");
			argsList.add(param.getSel_item());
		}
		
		if(!StringUtil.isEmpty(param.getSel_materialName())){
			appendBuffer.append("and m.material_name like ? \n");
			argsList.add("%"+param.getSel_materialName()+"%");
		}
		
		if(!StringUtil.isEmpty(param.getSel_category())){
			
			appendBuffer.append("and r.catory_id in ( select m.category_id from bmwz_category m start with m.category_name=? connect by m.category_fid=prior m.category_id) \n");
			
			argsList.add(param.getSel_category());
		}
		
		if(!StringUtil.isEmpty(param.getSel_invoiceNO())){
			appendBuffer.append("and r.receipt_invoiceno like ? \n");
			argsList.add("%"+param.getSel_invoiceNO()+"%");
		}
		
		if(!StringUtil.isEmpty(param.getSel_slbh())){
			appendBuffer.append("and r.receipt_id = ? \n");
			argsList.add(param.getSel_slbh());
		}
		
		if(!StringUtil.isEmpty(param.getSel_gydw())){
			appendBuffer.append("and r.receipt_supplydept like ? \n");
			argsList.add("%"+param.getSel_gydw()+"%");
		}
		
		//receipt_receivedtime
		if(!StringUtil.isEmpty(param.getSel_sDate())){
			appendBuffer.append("and r.receipt_receivedtime >= to_date(?,'yyyy-mm-dd') \n");
			argsList.add(param.getSel_sDate());
		}
		
		if(!StringUtil.isEmpty(param.getSel_eDate())){
			appendBuffer.append("and r.receipt_receivedtime <= to_date(?,'yyyy-mm-dd') \n");
			argsList.add(param.getSel_eDate());
		}
		
		appendBuffer.append(" and r.receipt_storageno=? ");
		
		/*
		 sql.append("select r.receipt_id,m.material_bak,m.material_name,r.receipt_style,r.receipt_size,r.receipt_ar_mount,r.receipt_ar_mount_has,\n");
		sql.append("r.receipt_a_dj,r.receipt_a_amount,r.receipt_filltime,r.receipt_receivedtime,r.receipt_invoiceno,r.receipt_receipter,r.receipt_purchaser,\n");
		sql.append("s.storehouse_name,r.receipt_supplydept,r.receipt_item,r.receipt_postion,r.receipt_bak,r.catory_id,r.catory_name\n");
		sql.append("from bmwz_receipt r left join bmwz_material m on m.material_id=r.receipt_material_id\n");
		sql.append("left join BMWZ_STOREHOUSE s on s.storehouse_id=r.receipt_storageno\n");
		 * */
		try{
			conn = ConnectionManager.getInstance().getConnection();
			pstm = conn.prepareStatement(sql.toString()+appendBuffer.toString());
			pstm1 = conn.prepareStatement(sqlCountBuffer.toString()+appendBuffer.toString());
			int i = 1;
			for(String str : argsList){
				pstm1.setString(i, str);
				pstm.setString(i, str);
				i++;
			}
			pstm1.setString(i, param.getHouseID());
			pstm.setString(i, param.getHouseID());
			
			rs = pstm1.executeQuery();
			
			if(rs.next()){
				count = rs.getInt(1);
			}
			rs.close();
			bean = new IReceiveBean();
			list.add(bean);
			
			
			
			if(count>1500){
				bean.setSel_bak(count+"");
				return list;
			}
			
			
			rs = pstm.executeQuery();
			
			while(rs.next()){
				bean = new IReceiveBean();
				
				bean.setRECEIPT_ID(rs.getString("receipt_id"));
				bean.setMATERIAL_BAK(rs.getString("material_bak"));
				bean.setRECEIPT_MATERIAL_NAME(rs.getString("material_name"));
				bean.setRECEIPT_STYLE(rs.getString("receipt_style"));
				bean.setRECEIPT_SIZE(rs.getString("receipt_size"));
				
				bean.setRECEIPT_AR_MOUNT(rs.getString("receipt_ar_mount"));
				bean.setRECEIPT_AR_MOUNT_HAS(rs.getString("receipt_ar_mount_has"));
				bean.setRECEIPT_A_DJ(rs.getString("receipt_a_dj"));
				bean.setRECEIPT_A_AMOUNT(rs.getString("receipt_a_amount"));
				bean.setRECEIPT_FILLTIME(rs.getString("receipt_filltime"));
				
				bean.setRECEIPT_RECEIVEDTIME(rs.getString("receipt_receivedtime"));
				bean.setRECEIPT_INVOICENO(rs.getString("receipt_invoiceno"));
				bean.setRECEIPT_RECEIPTER(rs.getString("receipt_receipter"));
				bean.setRECEIPT_PURCHASER(rs.getString("receipt_purchaser"));
				bean.setRECEIPT_STORAGENO(rs.getString("storehouse_name"));
				
				bean.setRECEIPT_SUPPLYDEPT(rs.getString("receipt_supplydept"));
				bean.setRECEIPT_ITEM(rs.getString("receipt_item"));
				bean.setRECEIPT_POSTION(rs.getString("receipt_postion"));
				bean.setRECEIPT_BAK(rs.getString("receipt_note"));
				bean.setCATORY_ID(rs.getString("catory_id"));
				
				bean.setCATORY_NAME(rs.getString("catory_name"));
				bean.setRECEIPT_MATERIAL_ID(rs.getString("material_id"));
				
				list.add(bean);
			}
		}catch(Exception e){
			logger.error(e.toString());
			return list;
		}finally{
			if(rs!=null)
				try {rs.close();} catch (SQLException e1) {	e1.printStackTrace();}
			if(pstm!=null)
				try {pstm.close();} catch (SQLException e) {e.printStackTrace();}	
			if(pstm1!=null)
				try {pstm1.close();} catch (SQLException e) {e.printStackTrace();}	
			if(conn!=null)
				try {conn.close();	} catch (SQLException e) {e.printStackTrace();}
		}		
		
		return list;
	}
	
	
	
	public List<IRequestBean> getRequestList(SelRecAndReq param){
		IRequestBean bean = null;
		List<IRequestBean> list = new ArrayList<IRequestBean>();
		List<String> argsList = new ArrayList<String>();
		
		Connection conn = null;
		PreparedStatement pstm = null, pstm1=null;
		ResultSet rs = null;
		
		StringBuffer sql = new StringBuffer();
		StringBuffer appendSql = new StringBuffer();
		StringBuffer sqlCount = new StringBuffer();
		
		sql.append("select r.request_id, m.material_bak,m.material_name,m.material_size,m.material_module,\n");
		sql.append("r.request_r_mount,r.request_ai_mount,round(r.request_dj,6) request_dj,round(r.request_tamount,2) request_tamount,to_char(r.request_filltime,'yyyy-mm-dd') request_filltime,\n");
		sql.append("to_char(r.request_issuetime,'yyyy-mm-dd') request_issuetime,r.REQUEST_ISSUER,r.REQUEST_REQUESTER,r.request_ldpt_id,r.request_ldpt_manager,\n");
		sql.append("f.fee_name,r.request_usage,rs.receipt_id,r1.receipt_postion,r1.receipt_supplydept,r.request_item_id,r.REQUEST_STATE,decode(me.mechanismtype,'01','门机','02','岸桥','03','场桥')||me.mechanismname mename\n");
		sql.append("from bmwz_request r\n");
		sql.append("left join bmwz_material m on m.material_id=r.request_meterial_id\n");
		sql.append("left join bmwz_feeproject f on f.fee_id=r.request_item_id\n");
		sql.append("left join bmwz_requestre rs on rs.request_id=r.request_id\n");
		sql.append("left join bmwz_receipt r1 on r1.receipt_id=rs.receipt_id  \n");
		sql.append("left join bmwz_mechanism me on me.mechanismid=r.mechanismid where 1=1");
		
		
		sqlCount.append("select count(1)\n");
		sqlCount.append("from bmwz_request r\n");
		sqlCount.append("left join bmwz_material m on m.material_id=r.request_meterial_id\n");
		sqlCount.append("left join bmwz_feeproject f on f.fee_id=r.request_item_id\n");
		
		sqlCount.append("left join bmwz_requestre rs on rs.request_id=r.request_id \n");
		sqlCount.append("left join bmwz_receipt r1 on r1.receipt_id=rs.receipt_id where 1=1  \n");
		
		System.out.println("sql:->\n"+sql);
		
		
		if(!StringUtil.isEmpty(param.getSel_llr())){
			appendSql.append("and r.REQUEST_REQUESTER = ? \n");
			argsList.add(param.getSel_llr());
		}
		
		if(!StringUtil.isEmpty(param.getSel_lldw())){
			appendSql.append("and r.request_ldpt_id = ? \n");
			argsList.add(param.getSel_lldw());
		}
				
		if(!StringUtil.isEmpty(param.getSel_postrion())){
			appendSql.append("and r1.receipt_postion like ? \n");
			argsList.add("%"+param.getSel_postrion()+"%");
		}
		
		if(!StringUtil.isEmpty(param.getSel_item())){
			appendSql.append("and f.fee_name = ? \n");
			argsList.add(param.getSel_item());
		}
		
		if(!StringUtil.isEmpty(param.getSel_materialName())){
			appendSql.append("and m.material_name like ? \n");
			argsList.add("%"+param.getSel_materialName()+"%");
		}
		
		if(!StringUtil.isEmpty(param.getSel_category())){
		
			appendSql.append("and m.material_type in ( select m1.category_id from bmwz_category m1 start with m1.category_name=? connect by m1.category_fid=prior m1.category_id) \n");

			argsList.add("%"+param.getSel_category()+"%");
		}
		
		if(!StringUtil.isEmpty(param.getSel_xxyt())){
			appendSql.append("and r.request_usage like ? \n");
			argsList.add("%"+param.getSel_xxyt()+"%");
		}
		System.out.println("===>"+param.getSel_llbh());
		if(!StringUtil.isEmpty(param.getSel_llbh())){
			appendSql.append("and r.request_id = ? \n");
			argsList.add(param.getSel_llbh());
		}
		
		if(!StringUtil.isEmpty(param.getSel_gydw())){
			appendSql.append("and r1.receipt_supplydept like ? \n");
			argsList.add("%"+param.getSel_gydw()+"%");
		}
		
		
		if(!StringUtil.isEmpty(param.getSel_sDate())){
			appendSql.append("and r.request_issuetime >= to_date(?,'yyyy-mm-dd') \n");
			argsList.add(param.getSel_sDate());
		}
		
		if(!StringUtil.isEmpty(param.getSel_eDate())){
			appendSql.append("and r.request_issuetime <= to_date(?,'yyyy-mm-dd') \n");
			argsList.add(param.getSel_eDate());
		}
		
		appendSql.append(" and  r.request_storageno=?  \n");
		
		try{
			conn = ConnectionManager.getInstance().getConnection();
			pstm = conn.prepareStatement(sql.toString()+appendSql.toString()+" order by 1 desc");
			pstm1 = conn.prepareStatement(sqlCount.toString()+appendSql.toString());
			
			System.out.println(sql.toString()+appendSql.toString());
			System.out.println(sqlCount.toString()+appendSql.toString());
			System.out.println(argsList);
			
			int i = 1, count=0;
			for(String str : argsList){
				pstm1.setString(i, str);
				pstm.setString(i, str);
				i++;
			}
			pstm1.setString(i, param.getHouseID());
			pstm.setString(i, param.getHouseID());
			
			rs = pstm1.executeQuery();
			
			if(rs.next()){
				count = rs.getInt(1);
			}
			rs.close();
			bean = new IRequestBean();
			list.add(bean);
			
			
			System.out.println("======>"+count);
			if(count>1500){
				bean.setSel_bak(count+"");
				return list;
			}
			
			
			rs = pstm.executeQuery();
			
			/*
			 * sql.append("select r.request_id, m.material_bak,m.material_name,m.material_size,m.material_module,\n");
		sql.append("r.request_r_mount,r.request_ai_mount,r.request_dj,r.request_tamount,r.request_filltime,\n");
		sql.append("r.request_issuetime,r.REQUEST_ISSUER,r.REQUEST_REQUESTER,r.request_ldpt_id,r.request_ldpt_manager,\n");
		sql.append("f.fee_name,r.request_usage,rs.receipt_id,r1.receipt_postion,r1.receipt_supplydept\n");
			 * */
			
			while(rs.next()){
				bean = new IRequestBean();
				
				bean.setREQUEST_ID(rs.getString("request_id"));
				bean.setREQUEST_BAK(rs.getString("material_bak"));
				bean.setMATERIAL_NAME(rs.getString("material_name"));
				bean.setMATERIAL_STYLE(rs.getString("material_size"));
				bean.setMATERIAL_UNIT(rs.getString("material_module"));
				
				bean.setREQUEST_R_MOUNT(rs.getString("request_r_mount"));
				bean.setREQUEST_AI_MOUNT(rs.getString("request_ai_mount"));
				bean.setrEQUEST_DJ(StringUtil.formatDouble6(rs.getDouble("request_dj")));
				bean.setREQUEST_TAMOUNT(rs.getString("request_tamount"));
				bean.setREQUEST_FILLTIME(rs.getString("request_filltime"));
				
				bean.setREQUEST_ISSUETIME(rs.getString("request_issuetime"));
				bean.setREQUEST_ISSUER(rs.getString("REQUEST_ISSUER"));
				bean.setREQUEST_REQUESTER(rs.getString("REQUEST_REQUESTER"));
				bean.setREQUEST_LDPT_ID(rs.getString("request_ldpt_id"));
				bean.setREQUEST_LDPT_MANAGER(rs.getString("request_ldpt_manager"));
				bean.setREQUEST_ITEM_ID(rs.getString("fee_name"));
				
				//,r.request_item_id,r.REQUEST_STATE
				bean.setREQUEST_USAGE(rs.getString("request_usage"));
				bean.setRECEIPT_ID(rs.getString("receipt_id"));
				bean.setRECEIPT_POSTION(rs.getString("receipt_postion"));
				bean.setRECEIPT_SUPPLY(rs.getString("receipt_supplydept"));
				bean.setREQUEST_ITEM_ID2(rs.getString("request_item_id"));
				bean.setREQUEST_STATE(rs.getString("REQUEST_STATE"));
				
				bean.setMENAME(rs.getString("mename"));
				
				list.add(bean);
			}
			
		}catch(Exception e){
			logger.error(e.toString());
			return list;
		}finally{
			if(rs!=null)
				try {rs.close();} catch (SQLException e1) {	e1.printStackTrace();}
			if(pstm!=null)
				try {pstm.close();} catch (SQLException e) {e.printStackTrace();}	
			if(pstm1!=null)
				try {pstm1.close();} catch (SQLException e) {e.printStackTrace();}	
			if(conn!=null)
				try {conn.close();	} catch (SQLException e) {e.printStackTrace();}
		}		
		
		return list;
	}

	
	public List<IRequestBean> getRequestListHis(SelRecAndReq param){
		IRequestBean bean = null;
		List<IRequestBean> list = new ArrayList<IRequestBean>();
		List<String> argsList = new ArrayList<String>();
		
		Connection conn = null;
		PreparedStatement pstm = null, pstm1=null;
		ResultSet rs = null;
		
		StringBuffer sql = new StringBuffer();
		StringBuffer appendSql = new StringBuffer();
		StringBuffer sqlCount = new StringBuffer();
		
		sql.append("select r.request_id, m.material_bak,m.material_name,m.material_size,m.material_module,\n");
		sql.append("r.request_r_mount,r.request_ai_mount,round(r.request_dj,6) request_dj,round(r.request_tamount,2) request_tamount,to_char(r.request_filltime,'yyyy-mm-dd') request_filltime,\n");
		sql.append("to_char(r.request_issuetime,'yyyy-mm-dd') request_issuetime,r.REQUEST_ISSUER,r.REQUEST_REQUESTER,r.request_ldpt_id,r.request_ldpt_manager,\n");
		sql.append("f.fee_name,r.request_usage,rs.receipt_id,r1.receipt_postion,r1.receipt_supplydept,r.request_item_id,r.REQUEST_STATE,decode(me.mechanismtype,'01','门机','02','岸桥','03','场桥')||me.mechanismname mename\n");
		sql.append("from bmwz_request_his r\n");
		sql.append("left join bmwz_material m on m.material_id=r.request_meterial_id\n");
		sql.append("left join bmwz_feeproject f on f.fee_id=r.request_item_id\n");
		sql.append("left join bmwz_requestre rs on rs.request_id=r.request_id\n");
		sql.append("left join bmwz_receipt r1 on r1.receipt_id=rs.receipt_id  \n");
		sql.append("left join bmwz_mechanism me on me.mechanismid=r.mechanismid where 1=1 ");
		
		
		sqlCount.append("select count(1)\n");
		sqlCount.append("from bmwz_request_his r\n");
		sqlCount.append("left join bmwz_material m on m.material_id=r.request_meterial_id \n");
		sqlCount.append("left join bmwz_feeproject f on f.fee_id=r.request_item_id \n");
		sqlCount.append("left join bmwz_requestre rs on rs.request_id=r.request_id  \n");
		sqlCount.append("left join bmwz_receipt r1 on r1.receipt_id=rs.receipt_id  where 1=1 \n");
		
		System.out.println("caldate:"+param.getCalDate());
		
		if(!StringUtil.isEmpty(param.getCalDate())){
			appendSql.append("and r.request_caldate=? \n");
			argsList.add(param.getCalDate());
		}
		
		
		if(!StringUtil.isEmpty(param.getSel_llr())){
			appendSql.append("and r.REQUEST_REQUESTER = ? \n");
			argsList.add(param.getSel_llr());
		}
		
		if(!StringUtil.isEmpty(param.getSel_lldw())){
			appendSql.append("and r.request_ldpt_id = ? \n");
			argsList.add(param.getSel_lldw());
		}
				
		if(!StringUtil.isEmpty(param.getSel_postrion())){
			appendSql.append("and r1.receipt_postion like ? \n");
			argsList.add("%"+param.getSel_postrion()+"%");
		}
		
		if(!StringUtil.isEmpty(param.getSel_item())){
			appendSql.append("and f.fee_name = ? \n");
			argsList.add(param.getSel_item());
		}
		
		if(!StringUtil.isEmpty(param.getSel_materialName())){
			appendSql.append("and m.material_name like ? \n");
			argsList.add("%"+param.getSel_materialName()+"%");
		}
		
		if(!StringUtil.isEmpty(param.getSel_category())){
			appendSql.append("and m.material_type in ( select m1.category_id from bmwz_category m1 start with m1.category_name=? connect by m1.category_fid=prior m1.category_id) \n");
			argsList.add("%"+param.getSel_category()+"%");
		}
		
		if(!StringUtil.isEmpty(param.getSel_xxyt())){
			appendSql.append("and r.request_usage like ? \n");
			argsList.add("%"+param.getSel_xxyt()+"%");
		}
		System.out.println("===>"+param.getSel_llbh());
		if(!StringUtil.isEmpty(param.getSel_llbh())){
			appendSql.append("and r.request_id = ? \n");
			argsList.add(param.getSel_llbh());
		}
		
		if(!StringUtil.isEmpty(param.getSel_gydw())){
			appendSql.append("and r1.receipt_supplydept like ? \n");
			argsList.add("%"+param.getSel_gydw()+"%");
		}
		
		
		if(!StringUtil.isEmpty(param.getSel_sDate())){
			appendSql.append("and r.request_issuetime >= to_date(?,'yyyy-mm-dd') \n");
			argsList.add(param.getSel_sDate());
		}
		
		if(!StringUtil.isEmpty(param.getSel_eDate())){
			appendSql.append("and r.request_issuetime <= to_date(?,'yyyy-mm-dd') \n");
			argsList.add(param.getSel_eDate());
		}
		
		appendSql.append(" and  r.request_storageno=?  \n");
		
		try{
			conn = ConnectionManager.getInstance().getConnection();
			pstm = conn.prepareStatement(sql.toString()+appendSql.toString()+" order by 1 desc");
			pstm1 = conn.prepareStatement(sqlCount.toString()+appendSql.toString());
			
			System.out.println(sql.toString()+appendSql.toString());
			System.out.println(sqlCount.toString()+appendSql.toString());
			System.out.println(argsList);
			
			int i = 1, count=0;
			for(String str : argsList){
				pstm1.setString(i, str);
				pstm.setString(i, str);
				i++;
			}
			pstm1.setString(i, param.getHouseID());
			pstm.setString(i, param.getHouseID());
			
			rs = pstm1.executeQuery();
			
			if(rs.next()){
				count = rs.getInt(1);
			}
			rs.close();
			bean = new IRequestBean();
			list.add(bean);
			
			
			System.out.println("======>"+count);
			if(count>1500){
				bean.setSel_bak(count+"");
				return list;
			}
			
			
			rs = pstm.executeQuery();
			
			/*
			 * sql.append("select r.request_id, m.material_bak,m.material_name,m.material_size,m.material_module,\n");
		sql.append("r.request_r_mount,r.request_ai_mount,r.request_dj,r.request_tamount,r.request_filltime,\n");
		sql.append("r.request_issuetime,r.REQUEST_ISSUER,r.REQUEST_REQUESTER,r.request_ldpt_id,r.request_ldpt_manager,\n");
		sql.append("f.fee_name,r.request_usage,rs.receipt_id,r1.receipt_postion,r1.receipt_supplydept\n");
			 * */
			
			while(rs.next()){
				bean = new IRequestBean();
				
				bean.setREQUEST_ID(rs.getString("request_id"));
				bean.setREQUEST_BAK(rs.getString("material_bak"));
				bean.setMATERIAL_NAME(rs.getString("material_name"));
				bean.setMATERIAL_STYLE(rs.getString("material_size"));
				bean.setMATERIAL_UNIT(rs.getString("material_module"));
				
				bean.setREQUEST_R_MOUNT(rs.getString("request_r_mount"));
				bean.setREQUEST_AI_MOUNT(rs.getString("request_ai_mount"));
				bean.setrEQUEST_DJ(StringUtil.formatDouble6(rs.getDouble("request_dj")));
				bean.setREQUEST_TAMOUNT(rs.getString("request_tamount"));
				bean.setREQUEST_FILLTIME(rs.getString("request_filltime"));
				
				bean.setREQUEST_ISSUETIME(rs.getString("request_issuetime"));
				bean.setREQUEST_ISSUER(rs.getString("REQUEST_ISSUER"));
				bean.setREQUEST_REQUESTER(rs.getString("REQUEST_REQUESTER"));
				bean.setREQUEST_LDPT_ID(rs.getString("request_ldpt_id"));
				bean.setREQUEST_LDPT_MANAGER(rs.getString("request_ldpt_manager"));
				bean.setREQUEST_ITEM_ID(rs.getString("fee_name"));
				
				//,r.request_item_id,r.REQUEST_STATE
				bean.setREQUEST_USAGE(rs.getString("request_usage"));
				bean.setRECEIPT_ID(rs.getString("receipt_id"));
				bean.setRECEIPT_POSTION(rs.getString("receipt_postion"));
				bean.setRECEIPT_SUPPLY(rs.getString("receipt_supplydept"));
				bean.setREQUEST_ITEM_ID2(rs.getString("request_item_id"));
				bean.setREQUEST_STATE(rs.getString("REQUEST_STATE"));
				
				bean.setMENAME(rs.getString("mename"));
				
				list.add(bean);
			}
			
		}catch(Exception e){
			logger.error(e.toString());
			return list;
		}finally{
			if(rs!=null)
				try {rs.close();} catch (SQLException e1) {	e1.printStackTrace();}
			if(pstm!=null)
				try {pstm.close();} catch (SQLException e) {e.printStackTrace();}	
			if(pstm1!=null)
				try {pstm1.close();} catch (SQLException e) {e.printStackTrace();}	
			if(conn!=null)
				try {conn.close();	} catch (SQLException e) {e.printStackTrace();}
		}		
		
		return list;
	}

	public List<IRequestBean> getRequestListOther(SelRecAndReq param){
		IRequestBean bean = null;
		List<IRequestBean> list = new ArrayList<IRequestBean>();
		List<String> argsList = new ArrayList<String>();

		Connection conn = null;
		PreparedStatement pstm = null, pstm1=null;
		ResultSet rs = null;
		
		StringBuffer sql = new StringBuffer();
		StringBuffer appendSql = new StringBuffer();
		StringBuffer sqlCount = new StringBuffer();
		
		sql.append("select r.request_id, m.material_bak,m.material_name,m.material_size,m.material_module,\n");
		sql.append("r.request_r_mount,r.request_ai_mount,round(r.request_dj,6) request_dj,round(r.request_tamount,2) request_tamount,to_char(r.request_filltime,'yyyy-mm-dd') request_filltime,\n");
		sql.append("to_char(r.request_issuetime,'yyyy-mm-dd') request_issuetime,r.REQUEST_ISSUER,r.REQUEST_REQUESTER,r.request_ldpt_id,r.request_ldpt_manager,\n");
		sql.append("f.fee_name,r.request_usage,rs.receipt_id,r1.receipt_postion,r1.receipt_supplydept,r.request_item_id,r.REQUEST_STATE\n");
		sql.append("from bmwz_request_other r\n");
		sql.append("left join bmwz_material m on m.material_id=r.request_meterial_id\n");
		sql.append("left join bmwz_feeproject f on f.fee_id=r.request_item_id\n");
		sql.append("left join bmwz_requestre rs on rs.request_id=r.request_id\n");
		sql.append("left join bmwz_receipt_other r1 on r1.receipt_id=rs.receipt_id where 1=1 \n");
		
		sqlCount.append("select count(1)\n");
		sqlCount.append("from bmwz_request r\n");
		sqlCount.append("left join bmwz_material m on m.material_id=r.request_meterial_id\n");
		sqlCount.append("left join bmwz_feeproject f on f.fee_id=r.request_item_id\n");
		sqlCount.append("left join bmwz_requestre rs on rs.request_id=r.request_id where 1=1 \n");
		
		
		if(!StringUtil.isEmpty(param.getSel_llr())){
			appendSql.append("and r.REQUEST_REQUESTER = ? \n");
			argsList.add(param.getSel_llr());
		}
		
		if(!StringUtil.isEmpty(param.getSel_lldw())){
			appendSql.append("and r.request_ldpt_id = ? \n");
			argsList.add(param.getSel_lldw());
		}
				
		if(!StringUtil.isEmpty(param.getSel_postrion())){
			appendSql.append("and r1.receipt_postion like ? \n");
			argsList.add("%"+param.getSel_postrion()+"%");
		}
		
		if(!StringUtil.isEmpty(param.getSel_item())){
			appendSql.append("and f.fee_name = ? \n");
			argsList.add(param.getSel_item());
		}
		
		if(!StringUtil.isEmpty(param.getSel_materialName())){
			appendSql.append("and m.material_name like ? \n");
			argsList.add("%"+param.getSel_materialName()+"%");
		}
		
		if(!StringUtil.isEmpty(param.getSel_category())){
			appendSql.append("and m.material_type in ( select m1.category_id from bmwz_category m1 start with m1.category_name=? connect by m1.category_fid=prior m1.category_id) \n");
			argsList.add("%"+param.getSel_category()+"%");
		}
		
		if(!StringUtil.isEmpty(param.getSel_xxyt())){
			appendSql.append("and r.request_usage like ? \n");
			argsList.add("%"+param.getSel_xxyt()+"%");
		}
		System.out.println("===>"+param.getSel_llbh());
		if(!StringUtil.isEmpty(param.getSel_llbh())){
			appendSql.append("and r.request_id = ? \n");
			argsList.add(param.getSel_llbh());
		}
		
		if(!StringUtil.isEmpty(param.getSel_gydw())){
			appendSql.append("and r1.receipt_supplydept like ? \n");
			argsList.add("%"+param.getSel_gydw()+"%");
		}
		
		
		if(!StringUtil.isEmpty(param.getSel_sDate())){
			appendSql.append("and r.request_issuetime >= to_date(?,'yyyy-mm-dd') \n");
			argsList.add(param.getSel_sDate());
		}
		
		if(!StringUtil.isEmpty(param.getSel_eDate())){
			appendSql.append("and r.request_issuetime <= to_date(?,'yyyy-mm-dd') \n");
			argsList.add(param.getSel_eDate());
		}
		
		appendSql.append(" and  r.request_storageno=?  \n");
		
		try{
			conn = ConnectionManager.getInstance().getConnection();
			pstm = conn.prepareStatement(sql.toString()+appendSql.toString()+" order by 1 desc");
			pstm1 = conn.prepareStatement(sqlCount.toString()+appendSql.toString());
			
			System.out.println(sql.toString()+appendSql.toString());
			System.out.println(sqlCount.toString()+appendSql.toString());
			System.out.println(argsList);
			
			int i = 1, count=0;
			for(String str : argsList){
				pstm1.setString(i, str);
				pstm.setString(i, str);
				i++;
			}
			pstm1.setString(i, param.getHouseID());
			pstm.setString(i, param.getHouseID());
			
			rs = pstm1.executeQuery();
			
			if(rs.next()){
				count = rs.getInt(1);
			}
			rs.close();
			bean = new IRequestBean();
			list.add(bean);
			
			
			System.out.println("======>"+count);
			if(count>1500){
				bean.setSel_bak(count+"");
				return list;
			}
			
			
			rs = pstm.executeQuery();
			
			/*
			 * sql.append("select r.request_id, m.material_bak,m.material_name,m.material_size,m.material_module,\n");
		sql.append("r.request_r_mount,r.request_ai_mount,r.request_dj,r.request_tamount,r.request_filltime,\n");
		sql.append("r.request_issuetime,r.REQUEST_ISSUER,r.REQUEST_REQUESTER,r.request_ldpt_id,r.request_ldpt_manager,\n");
		sql.append("f.fee_name,r.request_usage,rs.receipt_id,r1.receipt_postion,r1.receipt_supplydept\n");
			 * */
			
			while(rs.next()){
				bean = new IRequestBean();
				
				bean.setREQUEST_ID(rs.getString("request_id"));
				bean.setREQUEST_BAK(rs.getString("material_bak"));
				bean.setMATERIAL_NAME(rs.getString("material_name"));
				bean.setMATERIAL_STYLE(rs.getString("material_size"));
				bean.setMATERIAL_UNIT(rs.getString("material_module"));
				
				bean.setREQUEST_R_MOUNT(rs.getString("request_r_mount"));
				bean.setREQUEST_AI_MOUNT(rs.getString("request_ai_mount"));
				bean.setrEQUEST_DJ(StringUtil.formatDouble6(rs.getDouble("request_dj")));
				System.out.println(bean.getrEQUEST_DJ()+"<==============");
				bean.setREQUEST_TAMOUNT(rs.getString("request_tamount"));
				bean.setREQUEST_FILLTIME(rs.getString("request_filltime"));
				
				bean.setREQUEST_ISSUETIME(rs.getString("request_issuetime"));
				bean.setREQUEST_ISSUER(rs.getString("REQUEST_ISSUER"));
				bean.setREQUEST_REQUESTER(rs.getString("REQUEST_REQUESTER"));
				bean.setREQUEST_LDPT_ID(rs.getString("request_ldpt_id"));
				bean.setREQUEST_LDPT_MANAGER(rs.getString("request_ldpt_manager"));
				bean.setREQUEST_ITEM_ID(rs.getString("fee_name"));
				
				//,r.request_item_id,r.REQUEST_STATE
				bean.setREQUEST_USAGE(rs.getString("request_usage"));
				bean.setRECEIPT_ID(rs.getString("receipt_id"));
				bean.setRECEIPT_POSTION(rs.getString("receipt_postion"));
				bean.setRECEIPT_SUPPLY(rs.getString("receipt_supplydept"));
				bean.setREQUEST_ITEM_ID2(rs.getString("request_item_id"));
				bean.setREQUEST_STATE(rs.getString("REQUEST_STATE"));
				
				list.add(bean);
			}
			
		}catch(Exception e){
			logger.error(e.toString());
			return list;
		}finally{
			if(rs!=null)
				try {rs.close();} catch (SQLException e1) {	e1.printStackTrace();}
			if(pstm!=null)
				try {pstm.close();} catch (SQLException e) {e.printStackTrace();}	
			if(pstm1!=null)
				try {pstm1.close();} catch (SQLException e) {e.printStackTrace();}	
			if(conn!=null)
				try {conn.close();	} catch (SQLException e) {e.printStackTrace();}
		}		
		
		return list;
	}

	
	
	
	
	public String updateReceive(IReceiveBean param){
		Connection conn = null;
		PreparedStatement pstm = null, pstmCheck=null, pstmURecieve=null, pstmURequet=null,pstmCheck2=null;
		ResultSet rs = null,checkRs=null;
		StringBuffer sql = new StringBuffer();
		sql.append("update bmwz_receipt r\n");
		sql.append("set r.receipt_filltime=to_date(?,'yyyy-mm-dd'),r.receipt_receivedtime=to_date(?,'yyyy-mm-dd'),\n");
		sql.append("r.receipt_receipter=?,r.receipt_purchaser=?,r.receipt_invoiceno=?,r.receipt_supplydept=?,\n");
		sql.append("r.receipt_item=?,r.receipt_postion=?,r.receipt_note=?\n");
		sql.append("where r.receipt_id=?\n");
		
		
		StringBuffer sqlCheck = new StringBuffer();
		sqlCheck.append("select rq.request_id,count(1) from BMWZ_REQUESTRE rq where rq.request_id in ( \n");
		sqlCheck.append("  select r.request_id from bmwz_request r \n");
		sqlCheck.append("  where r.request_id in (  select a.request_id from BMWZ_REQUESTRE a where a.receipt_id=?  ) \n");
		sqlCheck.append(" ) group by rq.request_id having count(1)>1 \n");
		
		StringBuffer sqlCheck2 = new StringBuffer();
		sqlCheck2.append("select * from bmwz_receipt_his r where r.receipt_id=? ");
		
		StringBuffer sqlUReceive = new StringBuffer();
		sqlUReceive.append("update bmwz_receipt r set \n");
		sqlUReceive.append(" r.receipt_material_id = (select m.material_id from bmwz_material m where m.material_id=?) , \n");
		sqlUReceive.append(" r.receipt_style  = (select m.material_size from bmwz_material m where m.material_id=?), \n");
		sqlUReceive.append(" r.receipt_size  = (select m.material_module from bmwz_material m where m.material_id=?) ,\n");
		sqlUReceive.append(" r.receipt_material_name  = (select m.material_name from bmwz_material m where m.material_id=?) , \n");
		sqlUReceive.append(" r.catory_id  = (select m.material_type from bmwz_material m where m.material_id=?),\n");
		sqlUReceive.append(" r.catory_name = ( select c.category_name from bmwz_category c where c.category_id = ( select m.material_type from bmwz_material m where m.material_id=? )  ) \n");
		sqlUReceive.append(" where r.receipt_id=? \n");
		
		
		StringBuffer sqlURequest = new StringBuffer();
		sqlURequest.append("update bmwz_request r set  \n");
		sqlURequest.append("r.request_meterial_id = (select m.material_id from bmwz_material m where m.material_id=?) , \n");
		sqlURequest.append("r.request_style  = (select m.material_size from bmwz_material m where m.material_id=?), \n");
		sqlURequest.append("r.request_size  = (select m.material_module from bmwz_material m where m.material_id=?) , \n");
		sqlURequest.append("r.request_meterial_name = (select m.material_name from bmwz_material m where m.material_id=?)    \n");
		sqlURequest.append("where r.request_id in ( select rq.request_id from BMWZ_REQUESTRE rq where rq.receipt_id=? ) \n");
		
		
		
		try{
			conn = ConnectionManager.getInstance().getConnection();
			conn.setAutoCommit(false);
			
			pstm = conn.prepareStatement(sql.toString());
			pstmCheck = conn.prepareStatement(sqlCheck.toString());
			pstmCheck2 = conn.prepareStatement(sqlCheck2.toString());
			pstmURecieve = conn.prepareStatement(sqlUReceive.toString());
			pstmURequet = conn.prepareStatement(sqlURequest.toString());
			
			
			
			pstm.setString(1, param.getRECEIPT_FILLTIME());
			pstm.setString(2, param.getRECEIPT_RECEIVEDTIME());
			pstm.setString(3, param.getRECEIPT_RECEIPTER());
			pstm.setString(4, param.getRECEIPT_PURCHASER());
			pstm.setString(5, param.getRECEIPT_INVOICENO());
			
			pstm.setString(6, param.getRECEIPT_SUPPLYDEPT());
			pstm.setString(7, param.getRECEIPT_ITEM());
			pstm.setString(8, param.getRECEIPT_POSTION());
			pstm.setString(9, param.getRECEIPT_NOTE());
			pstm.setString(10, param.getRECEIPT_ID());
			
			
			String materialID = StringUtil.nullToEmpty(param.getRECEIPT_MATERIAL_ID());
			String recevieID = StringUtil.nullToEmpty(param.getRECEIPT_ID());
			boolean flag = false;
			StringBuffer meStringBuffer = new StringBuffer();
			
			if(param.getIsUpdateMaterial()==1){
				pstmCheck.setString(1, recevieID);
				pstmCheck2.setString(1, recevieID);
				
				checkRs = pstmCheck2.executeQuery();
				
				if(checkRs.next()){
					return "该单号已结账，不能修改信息！";
				}
				
				
				rs = pstmCheck.executeQuery();
				while(rs.next()){
					meStringBuffer.append(rs.getString(1)+",");
					flag = true;
				}
				if(flag){
					return "需要回退领料单["+meStringBuffer.toString()+"]";
				}
				
				pstmURecieve.setString(1, materialID);
				pstmURecieve.setString(2, materialID);
				pstmURecieve.setString(3, materialID);
				pstmURecieve.setString(4, materialID);
				pstmURecieve.setString(5, materialID);
				pstmURecieve.setString(6, materialID);
				pstmURecieve.setString(7, recevieID);
				
				pstmURequet.setString(1, materialID);
				pstmURequet.setString(2, materialID);
				pstmURequet.setString(3, materialID);
				pstmURequet.setString(4, materialID);
				pstmURequet.setString(5, recevieID);
				
				
				
				
				pstmURecieve.executeUpdate();
				pstmURequet.executeUpdate();
				
				pstm.executeUpdate();
				conn.commit();
				
			}else{
				pstm.executeUpdate();
				conn.commit();
			}
			
			
			
		}catch(Exception e){
			logger.error(e.toString());
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return e.toString();
		}finally{
			if(checkRs!=null)
				try {checkRs.close();} catch (SQLException e1) {	e1.printStackTrace();}
			if(rs!=null)
				try {rs.close();} catch (SQLException e1) {	e1.printStackTrace();}
			if(pstm!=null)
				try {pstm.close();} catch (SQLException e) {e.printStackTrace();}	
			if(pstmCheck!=null)
				try {pstmCheck.close();} catch (SQLException e) {e.printStackTrace();}	
			if(pstmCheck2!=null)
				try {pstmCheck2.close();} catch (SQLException e) {e.printStackTrace();}	
			if(pstmURecieve!=null)
				try {pstmURecieve.close();} catch (SQLException e) {e.printStackTrace();}	
			if(pstmURequet!=null)
				try {pstmURequet.close();} catch (SQLException e) {e.printStackTrace();}	
			if(conn!=null)
				try {conn.close();	} catch (SQLException e) {e.printStackTrace();}
		}	
		return "OK";
	}
	

	public String updateReceiveOther(IReceiveBean param){
		Connection conn = null;
		PreparedStatement pstm = null, pstmCheck=null, pstmURecieve=null, pstmURequet=null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		sql.append("update bmwz_receipt_other r\n");
		sql.append("set r.receipt_filltime=to_date(?,'yyyy-mm-dd'),r.receipt_receivedtime=to_date(?,'yyyy-mm-dd'),\n");
		sql.append("r.receipt_receipter=?,r.receipt_purchaser=?,r.receipt_invoiceno=?,r.receipt_supplydept=?,\n");
		sql.append("r.receipt_item=?,r.receipt_postion=?,r.receipt_note=?\n");
		sql.append("where r.receipt_id=?\n");
		
		
		StringBuffer sqlCheck = new StringBuffer();
		sqlCheck.append("select rq.request_id,count(1) from BMWZ_REQUESTRE rq where rq.request_id in ( \n");
		sqlCheck.append("  select r.request_id from bmwz_request_other r \n");
		sqlCheck.append("  where r.request_id in (  select a.request_id from BMWZ_REQUESTRE a where a.receipt_id=?  ) \n");
		sqlCheck.append(" ) group by rq.request_id having count(1)>1 \n");
		
		StringBuffer sqlUReceive = new StringBuffer();
		sqlUReceive.append("update bmwz_receipt_other r set \n");
		sqlUReceive.append(" r.receipt_material_id = (select m.material_id from bmwz_material m where m.material_id=?) , \n");
		sqlUReceive.append(" r.receipt_style  = (select m.material_size from bmwz_material m where m.material_id=?), \n");
		sqlUReceive.append(" r.receipt_size  = (select m.material_module from bmwz_material m where m.material_id=?) ,\n");
		sqlUReceive.append(" r.receipt_material_name  = (select m.material_name from bmwz_material m where m.material_id=?) , \n");
		sqlUReceive.append(" r.catory_id  = (select m.material_type from bmwz_material m where m.material_id=?),\n");
		sqlUReceive.append(" r.catory_name = ( select c.category_name from bmwz_category c where c.category_id = ( select m.material_type from bmwz_material m where m.material_id=? )  ) \n");
		sqlUReceive.append(" where r.receipt_id=? \n");
		
		
		StringBuffer sqlURequest = new StringBuffer();
		sqlURequest.append("update bmwz_request_other r set  \n");
		sqlURequest.append("r.request_meterial_id = (select m.material_id from bmwz_material m where m.material_id=?) , \n");
		sqlURequest.append("r.request_style  = (select m.material_size from bmwz_material m where m.material_id=?), \n");
		sqlURequest.append("r.request_size  = (select m.material_module from bmwz_material m where m.material_id=?) , \n");
		sqlURequest.append("r.request_meterial_name = (select m.material_name from bmwz_material m where m.material_id=?)  ,  \n");
		sqlURequest.append("where r.request_id in ( select rq.request_id from BMWZ_REQUESTRE rq where rq.receipt_id=? ) \n");
		
		try{
			conn = ConnectionManager.getInstance().getConnection();
			conn.setAutoCommit(false);
			
			pstm = conn.prepareStatement(sql.toString());
			pstmCheck = conn.prepareStatement(sqlCheck.toString());
			pstmURecieve = conn.prepareStatement(sqlUReceive.toString());
			pstmURequet = conn.prepareStatement(sqlURequest.toString());
			
			
			
			pstm.setString(1, param.getRECEIPT_FILLTIME());
			pstm.setString(2, param.getRECEIPT_RECEIVEDTIME());
			pstm.setString(3, param.getRECEIPT_RECEIPTER());
			pstm.setString(4, param.getRECEIPT_PURCHASER());
			pstm.setString(5, param.getRECEIPT_INVOICENO());
			
			pstm.setString(6, param.getRECEIPT_SUPPLYDEPT());
			pstm.setString(7, param.getRECEIPT_ITEM());
			pstm.setString(8, param.getRECEIPT_POSTION());
			pstm.setString(9, param.getRECEIPT_NOTE());
			pstm.setString(10, param.getRECEIPT_ID());
			
			
			String materialID = param.getRECEIPT_MATERIAL_ID();
			String recevieID = param.getRECEIPT_ID();
			boolean flag = false;
			StringBuffer meStringBuffer = new StringBuffer();
			
			if(param.getIsUpdateMaterial()==1){
				pstmCheck.setString(1, recevieID);
				rs = pstmCheck.executeQuery();
				while(rs.next()){
					meStringBuffer.append(rs.getString(1)+",");
					flag = true;
				}
				if(flag){
					return "需要回退领料单["+meStringBuffer.toString()+"]";
				}
				
				pstmURecieve.setString(1, materialID);
				pstmURecieve.setString(2, materialID);
				pstmURecieve.setString(3, materialID);
				pstmURecieve.setString(4, materialID);
				pstmURecieve.setString(5, materialID);
				pstmURecieve.setString(6, materialID);
				pstmURecieve.setString(7, recevieID);
				
				pstmURequet.setString(1, materialID);
				pstmURequet.setString(2, materialID);
				pstmURequet.setString(3, materialID);
				pstmURequet.setString(4, materialID);
				pstmURequet.setString(5, recevieID);
				
				pstm.executeUpdate();
				pstmURecieve.executeUpdate();
				pstmURequet.executeUpdate();
				
				conn.commit();
				
			}else{
				pstm.executeUpdate();
				conn.commit();
			}
			
			
			
		}catch(Exception e){
			logger.error(e.toString());
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return e.toString();
		}finally{
			if(rs!=null)
				try {rs.close();} catch (SQLException e1) {	e1.printStackTrace();}
			if(pstm!=null)
				try {pstm.close();} catch (SQLException e) {e.printStackTrace();}	
			if(pstmCheck!=null)
				try {pstmCheck.close();} catch (SQLException e) {e.printStackTrace();}	
			if(pstmURecieve!=null)
				try {pstmURecieve.close();} catch (SQLException e) {e.printStackTrace();}	
			if(pstmURequet!=null)
				try {pstmURequet.close();} catch (SQLException e) {e.printStackTrace();}	
			if(conn!=null)
				try {conn.close();	} catch (SQLException e) {e.printStackTrace();}
		}	
		return "OK";
	}
	
	
	public String getMaterialCount(String[] str){
		System.out.println("getMaterialCount:"+str[0]+"|"+str[1]+"|"+str[2]);
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		sql.append("select nvl(sum(r.receipt_ar_mount_has),0) cou from bmwz_receipt r \n");
		sql.append("where 1=1 and r.receipt_storageno=? and r.receipt_material_id=? and r.receipt_hasinvoice=? \n");
		
		System.out.println(sql.toString());
		String count = "";
		try{
			conn = ConnectionManager.getInstance().getConnection();
			pstm = conn.prepareStatement(sql.toString());
			pstm.setString(1, str[0]);
			pstm.setString(2, str[1]);
			pstm.setString(3, str[2]);
			
			rs = pstm.executeQuery();
			if(rs.next()){
				count = rs.getString(1);
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
		
		return count;
	}
	
	
	public int getMaterialCountOther(String[] str){
		System.out.println("getMaterialCountOther:"+str[0]+"|"+str[1]);
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		sql.append("select nvl(sum(r.receipt_ar_mount_has),0) cou from bmwz_receipt_other r \n");
		sql.append("where 1=1 and r.receipt_storageno=? and r.receipt_material_id=? and r.receipt_hasinvoice=? \n");
		int count = 0;
		try{
			conn = ConnectionManager.getInstance().getConnection();
			pstm = conn.prepareStatement(sql.toString());
			pstm.setString(1, str[0]);
			pstm.setString(2, str[1]);
			pstm.setString(3, str[2]);
			
			rs = pstm.executeQuery();
			if(rs.next()){
				count = rs.getInt(1);
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
		
		return count;
	}
	
	
	public String[] getRequestPrice(String requestID){
		String[] price ={"",""};
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		String sql = "select round(r.request_dj,6),round(r.request_tamount,2) from bmwz_request r where r.request_id=?";
		
		try{
			conn = ConnectionManager.getInstance().getConnection();
			pstm = conn.prepareStatement(sql.toString());
			pstm.setString(1, requestID);
			
			
			rs = pstm.executeQuery();
			if(rs.next()){
				price[0] = rs.getString(1);
				price[1] = rs.getString(2);
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
		
		
		return price;
	}
	
	
	public String[] getRequestPriceOther(String requestID){
		String[] price ={"",""};
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		String sql = "select round(r.request_dj,6),round(r.request_tamount,2) from bmwz_request_other r where r.request_id=?";
		
		try{
			conn = ConnectionManager.getInstance().getConnection();
			pstm = conn.prepareStatement(sql.toString());
			pstm.setString(1, requestID);
			
			
			rs = pstm.executeQuery();
			if(rs.next()){
				price[0] = rs.getString(1);
				price[1] = rs.getString(2);
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
		
		
		return price;
	}
	
	
	
}
