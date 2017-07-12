package com.gzb.sendAndReceive;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.cangku.bean.MaterialBean;
import com.gzb.db_source.ConnectionManager;
import com.gzb.prestatement.bean.SqlParams;
import com.gzb.util.StringUtil;
import com.gzb.util.TxtUtil;

public class SendAndReceive {

	private final String OK = "OK";
	
	 private DecimalFormat df = new DecimalFormat("###.00"); 
	

		
		
		
	 
	//收料随机配件
	public String addReceivesOther(List<SqlParams> list){
			
			Connection conn = null;
			PreparedStatement pstm1 = null, pstm2=null,pstm3=null,pstm4=null;
			ResultSet rs = null;
			String sql1="INSERT INTO bmwz_receipt_other(receipt_id,RECEIPT_INVOICENO,RECEIPT_FILLTIME,RECEIPT_RECEIVEDTIME,RECEIPT_STORAGENO,RECEIPT_MATERIAL_ID,RECEIPT_AR_MOUNT,RECEIPT_A_AMOUNT,"+
					"RECEIPT_ITEM, RECEIPT_NOTE ,RECEIPT_SUPPLYDEPT  ,RECEIPT_RECEIPTER , RECEIPT_PURCHASER ,RECEIPT_STATE,RECEIPT_BAK  ,RECEIPT_STYLE ,RECEIPT_SIZE ,RECEIPT_A_DJ ,"+
					"RECEIPT_MATERIAL_NAME ,CATORY_ID ,CATORY_NAME ,RECEIPT_AR_MOUNT_HAS ,RECEIPT_AR_MOUNT_SEND,receipt_postion,RECEIPT_HASINVOICE,INVOICEDATE) "+
					"VALUES(?,?,to_date(?,'yyyy-mm-dd'),to_date(?,'yyyy-mm-dd'),?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?,  decode(?,'1',sysdate,null))";
			String sql2 = "update BMWZ_MATERIAL t set t.MATERIAL_COUNT=t.MATERIAL_COUNT+?,MATERIAL_FLAG='1',MATERIAL_VERSION=MATERIAL_VERSION+1 "+
					"where t.material_id=? and MATERIAL_VERSION=?";
			String sql3 = "select tt.MATERIAL_VERSION from BMWZ_MATERIAL tt where tt.material_id=? ";
			String sql4 = "update bmwz_category set category_isuse='1' where category_id=? ";
			
			Class<?> demo = SqlParams.class;
			Field field = null;
			String mesString = OK;
			int count = 0;
			try{
				conn = ConnectionManager.getInstance().getConnection();
				conn.setAutoCommit(false);
				pstm1 = conn.prepareStatement(sql1);
				pstm2 = conn.prepareStatement(sql2);
				pstm3 = conn.prepareStatement(sql3);
				pstm4 = conn.prepareStatement(sql4);
				
				for(SqlParams params:list){
					for (int i = 0; i < params.strCount; i++) {
						field = demo.getField("str" + i);
						pstm1.setString(i + 1, StringUtil.nullToEmpty((String)field.get(params)));
					}
					pstm3.setString(1, params.str5);
					rs = pstm3.executeQuery();
					if(rs.next()){
						
						
						
						pstm2.setString(1, params.str6);
						pstm2.setString(2, params.str5);
						pstm2.setString(3, rs.getString(1));
						pstm1.executeUpdate();
						count = pstm2.executeUpdate();
						pstm4.setString(1, params.str19);
						pstm4.executeUpdate();
						if(count<1){
							mesString="其他用户在入库该物资，请稍后!";
							break;
						}
						mesString = updateStore(conn,params.str5,params.str4,Double.parseDouble(params.str6));
						if(!mesString.equals(OK)){
							conn.rollback();
							break;
						}
						
					}else{
						conn.rollback();
						mesString="没有找到该物资!";
						break;
					}
				}
				
				if(!"OK".equals(mesString)){
					conn.rollback();
					return mesString;
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
				if(rs!=null)
					try {rs.close();} catch (SQLException e) {e.printStackTrace();}
				if(pstm1!=null)
					try {pstm1.close();} catch (SQLException e) {e.printStackTrace();}
				if(pstm2!=null)
					try {pstm2.close();} catch (SQLException e) {e.printStackTrace();}
				if(pstm1!=null)
					try {pstm2.close();} catch (SQLException e) {e.printStackTrace();}
				if(conn!=null)
					try {conn.close();} catch (SQLException e) {e.printStackTrace();}
			}
			return OK;
		}
		
	 
	//收料
	public String addReceives(List<SqlParams> list){
		
		Connection conn = null;
		PreparedStatement pstm1 = null, pstm2=null,pstm3=null,pstm4=null;
		ResultSet rs = null;
		String sql1="INSERT INTO BMWZ_RECEIPT(receipt_id,RECEIPT_INVOICENO,RECEIPT_FILLTIME,RECEIPT_RECEIVEDTIME,RECEIPT_STORAGENO,RECEIPT_MATERIAL_ID,RECEIPT_AR_MOUNT,RECEIPT_A_AMOUNT,"+
				"RECEIPT_ITEM, RECEIPT_NOTE ,RECEIPT_SUPPLYDEPT  ,RECEIPT_RECEIPTER , RECEIPT_PURCHASER ,RECEIPT_STATE,RECEIPT_BAK  ,RECEIPT_STYLE ,RECEIPT_SIZE ,RECEIPT_A_DJ ,"+
				"RECEIPT_MATERIAL_NAME ,CATORY_ID ,CATORY_NAME ,RECEIPT_AR_MOUNT_HAS ,RECEIPT_AR_MOUNT_SEND,receipt_postion,RECEIPT_HASINVOICE,INVOICEDATE) "+
				"VALUES(?,?,to_date(?,'yyyy-mm-dd'),to_date(?,'yyyy-mm-dd'),?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?,  decode(?,'1',sysdate,null))";
		String sql2 = "update BMWZ_MATERIAL t set t.MATERIAL_COUNT=t.MATERIAL_COUNT+?,MATERIAL_FLAG='1',MATERIAL_VERSION=MATERIAL_VERSION+1 "+
				"where t.material_id=? and MATERIAL_VERSION=?";
		String sql3 = "select tt.MATERIAL_VERSION from BMWZ_MATERIAL tt where tt.material_id=? ";
		String sql4 = "update bmwz_category set category_isuse='1' where category_id=? ";
		
		Class<?> demo = SqlParams.class;
		Field field = null;
		String mesString = OK;
		int count = 0;
		try{
			conn = ConnectionManager.getInstance().getConnection();
			conn.setAutoCommit(false);
			pstm1 = conn.prepareStatement(sql1);
			pstm2 = conn.prepareStatement(sql2);
			pstm3 = conn.prepareStatement(sql3);
			pstm4 = conn.prepareStatement(sql4);
			
			for(SqlParams params:list){
				for (int i = 0; i < params.strCount; i++) {
					field = demo.getField("str" + i);
					pstm1.setString(i + 1, StringUtil.nullToEmpty((String)field.get(params)));
				}
				pstm3.setString(1, params.str5);
				rs = pstm3.executeQuery();
				if(rs.next()){
					
					
					
					pstm2.setString(1, params.str6);
					pstm2.setString(2, params.str5);
					pstm2.setString(3, rs.getString(1));
					pstm1.executeUpdate();
					count = pstm2.executeUpdate();
					pstm4.setString(1, params.str19);
					pstm4.executeUpdate();
					if(count<1){
						mesString="其他用户在入库该物资，请稍后!";
						break;
					}
					mesString = updateStore(conn,params.str5,params.str4,Double.parseDouble(params.str6));
					if(!mesString.equals(OK)){
						conn.rollback();
						break;
					}
					
				}else{
					conn.rollback();
					mesString="没有找到该物资!";
					break;
				}
			}
			
			if(!"OK".equals(mesString)){
				conn.rollback();
				return mesString;
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
			if(rs!=null)
				try {rs.close();} catch (SQLException e) {e.printStackTrace();}
			if(pstm1!=null)
				try {pstm1.close();} catch (SQLException e) {e.printStackTrace();}
			if(pstm2!=null)
				try {pstm2.close();} catch (SQLException e) {e.printStackTrace();}
			if(pstm1!=null)
				try {pstm2.close();} catch (SQLException e) {e.printStackTrace();}
			if(conn!=null)
				try {conn.close();} catch (SQLException e) {e.printStackTrace();}
		}
		return OK;
	}
	
	//发料
	public String addSend(List<SqlParams> list){
		//REQUEST_TAMOUNT,REQUEST_DJ
		String sql1 = "insert into BMWZ_REQUEST(REQUEST_ID,REQUEST_ISSUESTORAGE,REQUEST_STORAGENO,REQUEST_LDPT_ID,REQUEST_FILLTIME,REQUEST_ISSUETIME,REQUEST_METERIAL_ID,"+
				"REQUEST_R_MOUNT,REQUEST_AI_MOUNT,REQUEST_TAMOUNT,REQUEST_ITEM,REQUEST_USAGE,REQUEST_ISSUER,REQUEST_LDPT_MANAGER,REQUEST_REQUESTER,"+
				"REQUEST_SIZE,REQUEST_STYLE,REQUEST_METERIAL_NAME,REQUEST_DJ,request_useaddr,REQUEST_ITEM_ID,MECHANISMID) "+
				" values(?,?,?,?,to_date(?,'yyyy-mm-dd'), to_date(?,'yyyy-mm-dd'),?,?,?,?, ?,?,?,?,?, ?,?,?,?,?,?,?)";
		
		String sql2 = "select tt.MATERIAL_VERSION,tt.material_count from BMWZ_MATERIAL tt where tt.material_id=? ";
		
		String sql3 = "update BMWZ_MATERIAL t set t.MATERIAL_COUNT=t.MATERIAL_COUNT-?,MATERIAL_FLAG='1',MATERIAL_VERSION=MATERIAL_VERSION+1 "+
				"where t.material_id=? and MATERIAL_VERSION=?";
		
		String sql4 = "select t.receipt_id,t.receipt_ar_mount_has,RECEIPT_VERSION,RECEIPT_A_DJ,RECEIPT_A_AMOUNT,RECEIPT_AR_MOUNT from BMWZ_RECEIPT t " +
				"where t.receipt_ar_mount_has>0 and t.receipt_material_id=? and t.RECEIPT_STORAGENO=? and t.receipt_hasinvoice=? order by receipt_id ";
		
		String sql5 = "insert into BMWZ_REQUESTRE(REQUEST_ID,RECEIPT_ID,SEND_NUM) values(?,?,?) ";
		
		String sql6 = "update BMWZ_RECEIPT t set t.receipt_ar_mount_has=?,t.receipt_state=?,RECEIPT_VERSION=RECEIPT_VERSION+1 where t.receipt_id =? and  RECEIPT_VERSION=?";
		
		String sql7 = "select rs.send_num,rs.* from bmwz_requestre rs where rs.receipt_id=? ";
		
		String sql8 = "update bmwz_config c set c.config_bak1=?,c.config_bak2=? where config_type='1008' and config_value=?";
		
//		Map<String,Integer> map = new HashMap<String, Integer>();
		
		double money = 0,dj=0, receMoney=0;
	
		Connection conn = null;
		PreparedStatement pstm1 = null, pstm2=null,pstm3=null,pstm4=null,pstm5=null,pstm6=null,pstm7=null,pstm8=null;
		ResultSet rs = null,rs2=null,rs3=null,rs4=null;
		Class<?> demo = SqlParams.class;
		Field field = null;
		String mesString = OK;
		String recieID = "";
		int count = 0,result=0;double send=0,temp=0;
		
		Map<String, SendBean> map_send = new LinkedHashMap<String,SendAndReceive.SendBean>();
		
		try{
			conn = ConnectionManager.getInstance().getConnection();
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			
			pstm1 = conn.prepareStatement(sql1);
			pstm2 = conn.prepareStatement(sql2);
			pstm3 = conn.prepareStatement(sql3);
			pstm4 = conn.prepareStatement(sql4);
			pstm5 = conn.prepareStatement(sql5);
			pstm6 = conn.prepareStatement(sql6);
//			pstm7 = conn.prepareStatement(sql7);
			pstm8 = conn.prepareStatement(sql8);
			
			for(SqlParams params:list){
				
				pstm2.setString(1, params.str6);
				pstm4.setString(1, params.str6);
				pstm4.setString(2, params.str2);
				pstm4.setString(3, params.str22);
				rs = pstm2.executeQuery();
				if(rs.next()){
//					System.out.println(rs.getString(1));
					send = Double.parseDouble(params.str8);
//					System.out.println("库存比较："+Double.parseDouble(rs.getString(2))+"<"+send+" "+(Double.parseDouble(rs.getString(2))<send )+"|"+params.str6 );
					if(Double.parseDouble(rs.getString(2))<send){
						mesString="库存不足，请重新查询库存!";
						break;
					}
					//TODO
					//事务级别锁，需一次查出
					rs2 = pstm4.executeQuery();
					while(rs2.next() && send>0){
						recieID = rs2.getString(1);
//						System.out.println("recieID="+recieID);
						dj = rs2.getDouble(4);
						receMoney = rs2.getDouble(5);
					   
						if(rs2.getDouble(2) > send ){
							
							pstm5.setString(1, params.str0);
							pstm5.setString(2, rs2.getString(1));
							pstm5.setString(3, send+"");
							
							pstm6.setString(1, StringUtil.formateNumber6(rs2.getDouble(2)-send));
							
							pstm6.setString(2, "HH");
							if(Math.abs(rs2.getDouble(2)-send)<0.000001){
								pstm6.setString(2, "HS");
							}
							
							if(Math.abs(rs2.getDouble(2)-send)>=0.0000001){
								pstm6.setString(2, "HH");
							}
							
							pstm6.setString(3, rs2.getString(1));
							pstm6.setString(4, rs2.getString(3));
							
							
							map_send.put(recieID,new SendBean(dj,  receMoney, 1,send,send));
							send = 0;
							
							
						}else{
							
							pstm5.setString(1, params.str0);
							pstm5.setString(2, rs2.getString(1));
							pstm5.setString(3, rs2.getString(2));
							
							pstm6.setString(1, "0");
							pstm6.setString(2, "HS");
							
							if(Math.abs(rs2.getDouble(2)-send)<0.000001){
								pstm6.setString(2, "HS");
							}
							
							
							
							pstm6.setString(3, rs2.getString(1));
							pstm6.setString(4, rs2.getString(3));
							
							map_send.put(recieID,new SendBean(dj, receMoney, 2,rs2.getDouble(2)));
//							money = getReusltSetByNewConn(sql7,rs2.getString(1),money,receMoney,dj);
							send = send - rs2.getDouble(2);
						}
						
						System.out.println(params.toString());
						
						pstm8.setString(1, params.str13);
						pstm8.setString(2, params.str3);
						pstm8.setString(3, params.str14);
						pstm8.addBatch();
						
						pstm5.addBatch();
//						pstm6.addBatch();
//						pstm1.setDouble(10, money);
						
						result = pstm6.executeUpdate();
						if(result<1){
							mesString="其他用户在入库该物资，请稍后1!";
							conn.rollback();
							return mesString;
						}

					}
					double allSend = Double.parseDouble(params.str8);
					
					
					if(send>0.000001){
						mesString="库存不足【或有非法改动】！";
						break;
					}

					pstm3.setString(1, params.str8);
					pstm3.setString(2, params.str6);
					pstm3.setString(3, rs.getString(1));
					
					for (int i = 0; i < params.strCount; i++) {
						if(i==9 || i==18)continue;
						field = demo.getField("str" + i);
						
						System.out.println((i+1)+":"+StringUtil.nullToEmpty((String)field.get(params)));
						
						pstm1.setString(i + 1, StringUtil.nullToEmpty((String)field.get(params)));
					}
					
					//TODO
					
					money = getMoney(map_send);
					
					pstm1.setDouble(10, money);
					pstm1.setDouble(19, money/allSend);
//					System.out.println("money="+money+",price="+(money/allSend));
					money = 0;
					pstm1.executeUpdate();
					pstm5.executeBatch();
					
					count = pstm3.executeUpdate();
					
					
					mesString = updateStore(conn,params.str6,params.str2,Double.parseDouble("-"+params.str8));
					if(!mesString.equals(OK)){
						conn.rollback();
						break;
					}
					conn.commit();
					pstm5.clearBatch();
//					pstm6.clearBatch();
					
					if(count<1){
						mesString="其他用户在入库该物资，请稍后2!";
						break;
					}
				}else{
					conn.rollback();
					mesString="没有找到该物资!";
					break;
				}
			}
			
			if(!"OK".equals(mesString)){
				conn.rollback();
				return mesString;
			}
			pstm8.executeBatch();
			
			conn.commit();
		}catch(Exception e){
			connRollback(conn);
			e.printStackTrace();
			return e.getMessage();
		}finally{
			rsClose(rs);
			rsClose(rs2);
			rsClose(rs3);
			rsClose(rs4);
			pstmClose(pstm1);
			pstmClose(pstm2);
			pstmClose(pstm3);
			pstmClose(pstm4);
			pstmClose(pstm5);
			pstmClose(pstm6);
			pstmClose(pstm7);
			pstmClose(pstm8);
			connClose(conn);
			
		}
		return OK;
	}
	
	
	public String addSendOther(List<SqlParams> list){
		//REQUEST_TAMOUNT,REQUEST_DJ
		String sql1 = "insert into BMWZ_REQUEST_Other(REQUEST_ID,REQUEST_ISSUESTORAGE,REQUEST_STORAGENO,REQUEST_LDPT_ID,REQUEST_FILLTIME,REQUEST_ISSUETIME,REQUEST_METERIAL_ID,"+
				"REQUEST_R_MOUNT,REQUEST_AI_MOUNT,REQUEST_TAMOUNT,REQUEST_ITEM,REQUEST_USAGE,REQUEST_ISSUER,REQUEST_LDPT_MANAGER,REQUEST_REQUESTER,"+
				"REQUEST_SIZE,REQUEST_STYLE,REQUEST_METERIAL_NAME,REQUEST_DJ,request_useaddr,REQUEST_ITEM_ID) "+
				" values(?,?,?,?,to_date(?,'yyyy-mm-dd'), to_date(?,'yyyy-mm-dd'),?,?,?,?, ?,?,?,?,?, ?,?,?,?,?,?)";
		
		String sql2 = "select tt.MATERIAL_VERSION,tt.material_count from BMWZ_MATERIAL tt where tt.material_id=? ";
		
		String sql3 = "update BMWZ_MATERIAL t set t.MATERIAL_COUNT=t.MATERIAL_COUNT-?,MATERIAL_FLAG='1',MATERIAL_VERSION=MATERIAL_VERSION+1 "+
				"where t.material_id=? and MATERIAL_VERSION=?";
		
		String sql4 = "select t.receipt_id,t.receipt_ar_mount_has,RECEIPT_VERSION,RECEIPT_A_DJ,RECEIPT_A_AMOUNT,RECEIPT_AR_MOUNT from BMWZ_RECEIPT_Other t " +
				"where t.receipt_ar_mount_has>0 and t.receipt_material_id=? and t.RECEIPT_STORAGENO=? and r.receipt_hasinvoice=? order by receipt_id ";
		
		String sql5 = "insert into BMWZ_REQUESTRE(REQUEST_ID,RECEIPT_ID,SEND_NUM) values(?,?,?) ";
		
		String sql6 = "update BMWZ_RECEIPT_Other t set t.receipt_ar_mount_has=?,t.receipt_state=?,RECEIPT_VERSION=RECEIPT_VERSION+1 where t.receipt_id =? and  RECEIPT_VERSION=?";
		
		String sql7 = "select rs.send_num,rs.* from bmwz_requestre rs where rs.receipt_id=? ";
		
//		Map<String,Integer> map = new HashMap<String, Integer>();
		
		double money = 0,dj=0, receMoney=0;
	
		Connection conn = null;
		PreparedStatement pstm1 = null, pstm2=null,pstm3=null,pstm4=null,pstm5=null,pstm6=null,pstm7=null;
		ResultSet rs = null,rs2=null,rs3=null,rs4=null;
		Class<?> demo = SqlParams.class;
		Field field = null;
		String mesString = OK;
		String recieID = "";
		int count = 0,result=0;double send=0,temp=0;
		
		Map<String, SendBean> map_send = new LinkedHashMap<String,SendAndReceive.SendBean>();
		
		try{
			conn = ConnectionManager.getInstance().getConnection();
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			
			pstm1 = conn.prepareStatement(sql1);
			pstm2 = conn.prepareStatement(sql2);
			pstm3 = conn.prepareStatement(sql3);
			pstm4 = conn.prepareStatement(sql4);
			pstm5 = conn.prepareStatement(sql5);
			pstm6 = conn.prepareStatement(sql6);
//			pstm7 = conn.prepareStatement(sql7);
			
			
			for(SqlParams params:list){
				
				pstm2.setString(1, params.str6);
				pstm4.setString(1, params.str6);
				pstm4.setString(2, params.str2);
				pstm4.setString(3, params.str22);
				
				rs = pstm2.executeQuery();
				if(rs.next()){
//					System.out.println(rs.getString(1));
					send = Double.parseDouble(params.str8);
//					System.out.println("库存比较："+Double.parseDouble(rs.getString(2))+"<"+send+" "+(Double.parseDouble(rs.getString(2))<send )+"|"+params.str6 );
					if(Double.parseDouble(rs.getString(2))<send){
						mesString="库存不足，请重新查询库存!";
						break;
					}
					//TODO
					//事务级别锁，需一次查出
					rs2 = pstm4.executeQuery();
					while(rs2.next() && send>0){
						recieID = rs2.getString(1);
//						System.out.println("recieID="+recieID);
						dj = rs2.getDouble(4);
						receMoney = rs2.getDouble(5);
					   
						if(rs2.getDouble(2) > send ){
							
							pstm5.setString(1, params.str0);
							pstm5.setString(2, rs2.getString(1));
							pstm5.setString(3, send+"");
							
							pstm6.setString(1, StringUtil.formateNumber6(rs2.getDouble(2)-send));
							
							pstm6.setString(2, "HH");
							if(Math.abs(rs2.getDouble(2)-send)<0.000001){
								pstm6.setString(2, "HS");
							}
							
							if(Math.abs(rs2.getDouble(2)-send)>=0.0000001){
								pstm6.setString(2, "HH");
							}
							
							pstm6.setString(3, rs2.getString(1));
							pstm6.setString(4, rs2.getString(3));
							
							
							map_send.put(recieID,new SendBean(dj,  receMoney, 1,send,send));
							send = 0;
							
							
						}else{
							
							pstm5.setString(1, params.str0);
							pstm5.setString(2, rs2.getString(1));
							pstm5.setString(3, rs2.getString(2));
							
							pstm6.setString(1, "0");
							pstm6.setString(2, "HS");
							
							if(Math.abs(rs2.getDouble(2)-send)<0.000001){
								pstm6.setString(2, "HS");
							}
							
							
							
							pstm6.setString(3, rs2.getString(1));
							pstm6.setString(4, rs2.getString(3));
							
							map_send.put(recieID,new SendBean(dj, receMoney, 2,rs2.getDouble(2)));
//							money = getReusltSetByNewConn(sql7,rs2.getString(1),money,receMoney,dj);
							send = send - rs2.getDouble(2);
						}
						
						pstm5.addBatch();
//						pstm6.addBatch();
//						pstm1.setDouble(10, money);
						
						result = pstm6.executeUpdate();
						if(result<1){
							mesString="其他用户在入库该物资，请稍后1!";
							conn.rollback();
							return mesString;
						}

					}
					double allSend = Double.parseDouble(params.str8);
					
					
					if(send>0.000001){
						mesString="库存不足【或有非法改动】！";
						break;
					}

					pstm3.setString(1, params.str8);
					pstm3.setString(2, params.str6);
					pstm3.setString(3, rs.getString(1));
					
					for (int i = 0; i < params.strCount; i++) {
						if(i==9 || i==18)continue;
						field = demo.getField("str" + i);
						pstm1.setString(i + 1, StringUtil.nullToEmpty((String)field.get(params)));
					}
					
					//TODO
					
					money = getMoney(map_send);
					
					pstm1.setDouble(10, money);
					pstm1.setDouble(19, money/allSend);
//					System.out.println("money="+money+",price="+(money/allSend));
					money = 0;
					pstm1.executeUpdate();
					pstm5.executeBatch();
					
					count = pstm3.executeUpdate();
					
					
					mesString = updateStore(conn,params.str6,params.str2,Double.parseDouble("-"+params.str8));
					if(!mesString.equals(OK)){
						conn.rollback();
						break;
					}
					conn.commit();
					pstm5.clearBatch();
//					pstm6.clearBatch();
					
					if(count<1){
						mesString="其他用户在入库该物资，请稍后2!";
						break;
					}
				}else{
					conn.rollback();
					mesString="没有找到该物资!";
					break;
				}
			}
			
			if(!"OK".equals(mesString)){
				conn.rollback();
				return mesString;
			}
			conn.commit();
		}catch(Exception e){
			connRollback(conn);
			e.printStackTrace();
			return e.getMessage();
		}finally{
			rsClose(rs);
			rsClose(rs2);
			rsClose(rs3);
			rsClose(rs4);
			pstmClose(pstm1);
			pstmClose(pstm2);
			pstmClose(pstm3);
			pstmClose(pstm4);
			pstmClose(pstm5);
			pstmClose(pstm6);
			pstmClose(pstm7);
			connClose(conn);
			
		}
		return OK;
	}	
	
	
	//撤销收料
	public String clearReceive(SqlParams param){
		System.out.println("=====clearReceive=======");
		System.out.println(param);
		Connection conn = null;
		PreparedStatement pstm1=null,pstm2 = null,pstm3 = null,pstm4=null,pstm5=null;
		ResultSet rs = null,rs2 = null ;
		String sql1 = "select t.receipt_id,t.receipt_material_id,t.RECEIPT_AR_MOUNT,receipt_storageno from bmwz_receipt t where t.receipt_state='CY' and t.receipt_id=?";
		String sql2 = "insert into bmwz_receipt_del select * from bmwz_receipt t where t.receipt_id=? ";
		String sql3 = "select tt.MATERIAL_VERSION from BMWZ_MATERIAL tt where tt.material_id=? ";
		String sql4 = "update BMWZ_MATERIAL t set t.MATERIAL_COUNT=t.MATERIAL_COUNT-?,MATERIAL_FLAG='1',MATERIAL_VERSION=MATERIAL_VERSION+1 "+
				" where t.material_id=? and MATERIAL_VERSION=?";
		
		String sql5 = "delete from bmwz_receipt where receipt_id=?";
		
		try{
			conn = ConnectionManager.getInstance().getConnection();
			conn.setAutoCommit(false);
			
			pstm1 = conn.prepareStatement(sql1);
			pstm2 = conn.prepareStatement(sql2);
			pstm3 = conn.prepareStatement(sql3);
			pstm4 = conn.prepareStatement(sql4);
			pstm5 = conn.prepareStatement(sql5);
			
			pstm1.setString(1, param.str0);
			rs = pstm1.executeQuery();
			
			if(rs.next()){
//				String id = rs.getString(2);
				pstm2.setString(1, param.str0);
				pstm3.setString(1, rs.getString(2));
				pstm5.setString(1, param.str0);
				
				rs2 = pstm3.executeQuery();
				
				if(rs2.next()){
					
					pstm4.setString(1, rs.getString(3));
					pstm4.setString(2, rs.getString(2));
					pstm4.setString(3, rs2.getString(1));
					
					int count = pstm4.executeUpdate();
					if(count<1){
						conn.rollback();
						return "其它用户在更新该物资库存,请稍后!";
					}
					pstm2.executeUpdate();
					pstm4.executeUpdate();
					int c = pstm5.executeUpdate();
					
					
					
					
					if(c<1){
						conn.rollback();
						return "其它用户在更新该物资库存,请稍后!";
					}
					
					String mesString = updateStore(conn,rs.getString(2),rs.getString(4),(rs.getDouble(3)*-1));
					if(!mesString.equals(OK)){
						conn.rollback();
						return mesString;
					}
					
					
				}else{
					conn.rollback();
					return "该物质被删除，质询管理员";
				}
			}else{
				conn.rollback();
				return "该收料单存在出库记录，不能回退!";
			}
			conn.commit(); 
		}catch (Exception e) {
			try {conn.rollback();} catch (SQLException e1) {e1.printStackTrace();}
			e.printStackTrace();
			return e.toString();
		}finally{
			if(rs!=null){try {rs.close();} catch (SQLException e){e.printStackTrace();}}
			if(rs2!=null){try {rs.close();} catch (SQLException e){e.printStackTrace();}}
			if(pstm1!=null){try {pstm1.close();} catch (SQLException e){e.printStackTrace();}}
			if(pstm2!=null){try {pstm2.close();} catch (SQLException e){e.printStackTrace();}}
			if(pstm3!=null){try {pstm3.close();} catch (SQLException e){e.printStackTrace();}}
			if(pstm4!=null){try {pstm4.close();} catch (SQLException e){e.printStackTrace();}}
			if(pstm5!=null){try {pstm4.close();} catch (SQLException e){e.printStackTrace();}}
			if(conn!=null){try {conn.close();} catch (SQLException e){e.printStackTrace();}}
		}
		return OK;
	}
	
	public String clearReceiveOther(SqlParams param){
		System.out.println("=====clearReceive=======");
		System.out.println(param);
		Connection conn = null;
		PreparedStatement pstm1=null,pstm2 = null,pstm3 = null,pstm4=null,pstm5=null;
		ResultSet rs = null,rs2 = null ;
		String sql1 = "select t.receipt_id,t.receipt_material_id,t.RECEIPT_AR_MOUNT,receipt_storageno from bmwz_receipt_other t where t.receipt_state='CY' and t.receipt_id=?";
		String sql2 = "insert into bmwz_receipt_del_other select * from bmwz_receipt_other t where t.receipt_id=? ";
		String sql3 = "select tt.MATERIAL_VERSION from BMWZ_MATERIAL tt where tt.material_id=? ";
		String sql4 = "update BMWZ_MATERIAL t set t.MATERIAL_COUNT=t.MATERIAL_COUNT-?,MATERIAL_FLAG='1',MATERIAL_VERSION=MATERIAL_VERSION+1 "+
				" where t.material_id=? and MATERIAL_VERSION=?";
		
		String sql5 = "delete from bmwz_receipt_other where receipt_id=?";
		
		try{
			conn = ConnectionManager.getInstance().getConnection();
			conn.setAutoCommit(false);
			
			pstm1 = conn.prepareStatement(sql1);
			pstm2 = conn.prepareStatement(sql2);
			pstm3 = conn.prepareStatement(sql3);
			pstm4 = conn.prepareStatement(sql4);
			pstm5 = conn.prepareStatement(sql5);
			
			pstm1.setString(1, param.str0);
			rs = pstm1.executeQuery();
			
			if(rs.next()){
//				String id = rs.getString(2);
				pstm2.setString(1, param.str0);
				pstm3.setString(1, rs.getString(2));
				pstm5.setString(1, param.str0);
				
				rs2 = pstm3.executeQuery();
				
				if(rs2.next()){
					
					pstm4.setString(1, rs.getString(3));
					pstm4.setString(2, rs.getString(2));
					pstm4.setString(3, rs2.getString(1));
					
					int count = pstm4.executeUpdate();
					if(count<1){
						conn.rollback();
						return "其它用户在更新该物资库存,请稍后!";
					}
					pstm2.executeUpdate();
					pstm4.executeUpdate();
					int c = pstm5.executeUpdate();
					
					
					
					
					if(c<1){
						conn.rollback();
						return "其它用户在更新该物资库存,请稍后!";
					}
					
					String mesString = updateStore(conn,rs.getString(2),rs.getString(4),(rs.getDouble(3)*-1));
					if(!mesString.equals(OK)){
						conn.rollback();
						return mesString;
					}
					
					
				}else{
					conn.rollback();
					return "该物质被删除，质询管理员";
				}
			}else{
				conn.rollback();
				return "该收料单存在出库记录，不能回退!";
			}
			conn.commit(); 
		}catch (Exception e) {
			try {conn.rollback();} catch (SQLException e1) {e1.printStackTrace();}
			e.printStackTrace();
			return e.toString();
		}finally{
			if(rs!=null){try {rs.close();} catch (SQLException e){e.printStackTrace();}}
			if(rs2!=null){try {rs.close();} catch (SQLException e){e.printStackTrace();}}
			if(pstm1!=null){try {pstm1.close();} catch (SQLException e){e.printStackTrace();}}
			if(pstm2!=null){try {pstm2.close();} catch (SQLException e){e.printStackTrace();}}
			if(pstm3!=null){try {pstm3.close();} catch (SQLException e){e.printStackTrace();}}
			if(pstm4!=null){try {pstm4.close();} catch (SQLException e){e.printStackTrace();}}
			if(pstm5!=null){try {pstm4.close();} catch (SQLException e){e.printStackTrace();}}
			if(conn!=null){try {conn.close();} catch (SQLException e){e.printStackTrace();}}
		}
		return OK;
	}
	
	
	
	//撤销发料
	public String clearSendOther(SqlParams param){
		Connection conn = null;
		PreparedStatement pstm1=null,pstm2 = null,pstm3=null,pstm4=null,pstm5=null,pstm6=null,pstm7=null,pstm8=null,pstm9=null;
		ResultSet rs = null,rs2 =null ,rs3=null,rs4=null;
		
		String sql1 = "select REQUEST_AI_MOUNT,REQUEST_METERIAL_ID,request_storageno from BMWZ_REQUEST_other where REQUEST_ID=?  ";
		String sql2 = "select tt.MATERIAL_VERSION from BMWZ_MATERIAL tt where tt.material_id=? ";
		String sql3 = "select t.RECEIPT_VERSION ,RECEIPT_AR_MOUNT,t.receipt_id,r.send_num,RECEIPT_AR_MOUNT,RECEIPT_AR_MOUNT_HAS,RECEIPT_AR_MOUNT-RECEIPT_AR_MOUNT_HAS-send_num from bmwz_requestre r left join bmwz_receipt_other t on r.receipt_id=t.receipt_id where request_id=?";
		String sql4 = "update BMWZ_RECEIPT_other t set t.receipt_ar_mount_has=receipt_ar_mount_has+?,t.receipt_state=?,RECEIPT_VERSION=RECEIPT_VERSION+1 where t.receipt_id =? and  RECEIPT_VERSION=?";
		String sql5 = "update BMWZ_MATERIAL t set t.MATERIAL_COUNT=t.MATERIAL_COUNT+?,MATERIAL_FLAG='1',MATERIAL_VERSION=MATERIAL_VERSION+1 "+
				"where t.material_id=? and MATERIAL_VERSION=?";
		
		String sql6 = " select REQUEST_VERSION from BMWZ_REQUEST_other where REQUEST_ID=? ";
		String sql7 = " delete from BMWZ_REQUEST_other where REQUEST_ID=? and REQUEST_VERSION=? ";
		String sql8 = "insert into  bmwz_request_del  select * from bmwz_request r where r.request_id=?";
		
		String sql9 = "delete from bmwz_requestre where request_id=? ";
		
		int result = 0;
		try{
			conn = ConnectionManager.getInstance().getConnection();
			conn.setAutoCommit(false);
			
			pstm1 = conn.prepareStatement(sql1);
			pstm2 = conn.prepareStatement(sql2);
			pstm3 = conn.prepareStatement(sql3);
			pstm4 = conn.prepareStatement(sql4);
			pstm5 = conn.prepareStatement(sql5);
			pstm6 = conn.prepareStatement(sql6);
			pstm7 = conn.prepareStatement(sql7);
			pstm8 = conn.prepareStatement(sql8);
			pstm9 = conn.prepareStatement(sql9);
			
			pstm1.setString(1, param.str0);
			pstm6.setString(1, param.str0);
			pstm7.setString(1, param.str0);
			pstm8.setString(1, param.str0);
			pstm9.setString(1, param.str0);
			
			
			String state = "";
			
			rs = pstm1.executeQuery();
			double send = 0,count=0;
			System.out.println("=============1============");
			if(rs.next()){
				System.out.println("=========================");
				double sum = rs.getDouble(1);
				pstm2.setString(1, rs.getString(2));
				rs2 = pstm2.executeQuery();
				if(rs2.next()){
					pstm3.setString(1, param.str0);
					rs3 = pstm3.executeQuery();
					while(rs3.next()){
						send = rs3.getDouble(4);
						count+=send;
						
						pstm4.setString(1, rs3.getString(4));
						
						
//						System.out.println("rs3.getDouble(7):"+rs3.getDouble(7)+"|"+(Math.abs(rs3.getDouble(7))<0.0000001));
						System.out.println(rs3.getString(3)+"===>hello\t"+Math.abs(rs3.getDouble(7))+"\t"+rs3.getDouble(7));
						if(rs3.getDouble(7)>0){
							state = "HH";
							if(Math.abs(rs3.getDouble(7))<0.00000001){
								state = "CY";
							}
						}else if(Math.abs(rs3.getDouble(7))>0.0000001){
							conn.rollback();
							return "领料单和库存存在数据差异!";
						}else{
							state = "CY";
						}
						pstm4.setString(2, state);
						pstm4.setString(3, rs3.getString(3));
						pstm4.setString(4, rs3.getString(1));
//						pstm4.addBatch();
						
//						System.out.println(rs3.getString(4)+"|"+state+"|"+param.str0+"|"+rs3.getString(1));
						
						result = pstm4.executeUpdate();
						if(result<1){
							conn.rollback();
							return "其它用户正在更新物资库存!";
						}
						
					}
					
					if(Math.abs(count-sum)>0.9){
						conn.rollback();
						return "出库和入库数据不符!";
					}
					
					pstm5.setString(1, sum+"");
					pstm5.setString(2, rs.getString(2));
					pstm5.setString(3, rs2.getString(1));
					
					rs4 = pstm6.executeQuery();
					if(rs4.next()){
						String version = rs4.getString(1);
						pstm7.setString(2, version);
						
						int c7 = pstm7.executeUpdate();
						if(c7<1){
							conn.rollback();
							return "领料单已被其它用户删除了!";
						}
						pstm8.executeUpdate();
//						int c[] = pstm4.executeBatch();
//						for(int i:c){
//							if(i<1){
//								conn.rollback();
//								return "其它用户正在更新物资库存!";
//							}
//						}
						pstm5.executeUpdate();
						
						String mesString = updateStore(conn,rs.getString(2),rs.getString(3),rs.getDouble(1));
						if(!mesString.equals(OK)){
							conn.rollback();
							return mesString;
						}
						
						pstm9.executeUpdate();
						
						
						
						
					}else{
						conn.rollback();
						return "领料单被删除了!";
						
					}
					
				}else{
					conn.rollback();
					return "领料物资已删除!";
				}
			}else{
				conn.rollback();
				System.out.println("=============2============");
				return "领料数据已归档，或已删除!";
			}
			
			
			
			
			
			conn.commit();
		}catch (Exception e) {
			try {conn.rollback();} catch (SQLException e1) {e1.printStackTrace();}
			e.printStackTrace();
			return e.toString();
		}finally{
			if(rs!=null){try {rs.close();} catch (SQLException e){e.printStackTrace();}}
			if(rs2!=null){try {rs2.close();} catch (SQLException e){e.printStackTrace();}}
			if(rs3!=null){try {rs3.close();} catch (SQLException e){e.printStackTrace();}}
			if(pstm1!=null){try {pstm1.close();} catch (SQLException e){e.printStackTrace();}}
			if(pstm2!=null){try {pstm2.close();} catch (SQLException e){e.printStackTrace();}}
			if(pstm3!=null){try {pstm3.close();} catch (SQLException e){e.printStackTrace();}}
			if(conn!=null){try {conn.close();} catch (SQLException e){e.printStackTrace();}}
		}
		return OK;
	}
	
	//撤销发料
	public String clearSend(SqlParams param){
			Connection conn = null;
			PreparedStatement pstm1=null,pstm2 = null,pstm3=null,pstm4=null,pstm5=null,pstm6=null,pstm7=null,pstm8=null,pstm9=null;
			ResultSet rs = null,rs2 =null ,rs3=null,rs4=null;
			
			String sql1 = "select REQUEST_AI_MOUNT,REQUEST_METERIAL_ID,request_storageno from BMWZ_REQUEST where REQUEST_ID=?  ";
			String sql2 = "select tt.MATERIAL_VERSION from BMWZ_MATERIAL tt where tt.material_id=? ";
			String sql3 = "select t.RECEIPT_VERSION ,RECEIPT_AR_MOUNT,t.receipt_id,r.send_num,RECEIPT_AR_MOUNT,RECEIPT_AR_MOUNT_HAS,RECEIPT_AR_MOUNT-RECEIPT_AR_MOUNT_HAS-send_num from bmwz_requestre r left join bmwz_receipt t on r.receipt_id=t.receipt_id where request_id=?";
			String sql4 = "update BMWZ_RECEIPT t set t.receipt_ar_mount_has=receipt_ar_mount_has+?,t.receipt_state=?,RECEIPT_VERSION=RECEIPT_VERSION+1 where t.receipt_id =? and  RECEIPT_VERSION=?";
			String sql5 = "update BMWZ_MATERIAL t set t.MATERIAL_COUNT=t.MATERIAL_COUNT+?,MATERIAL_FLAG='1',MATERIAL_VERSION=MATERIAL_VERSION+1 "+
					"where t.material_id=? and MATERIAL_VERSION=?";
			
			String sql6 = " select REQUEST_VERSION from BMWZ_REQUEST where REQUEST_ID=? ";
			String sql7 = " delete from BMWZ_REQUEST where REQUEST_ID=? and REQUEST_VERSION=? ";
			String sql8 = "insert into  bmwz_request_del  select * from bmwz_request r where r.request_id=?";
			
			String sql9 = "delete from bmwz_requestre where request_id=? ";
			
			int result = 0;
			try{
				conn = ConnectionManager.getInstance().getConnection();
				conn.setAutoCommit(false);
				
				pstm1 = conn.prepareStatement(sql1);
				pstm2 = conn.prepareStatement(sql2);
				pstm3 = conn.prepareStatement(sql3);
				pstm4 = conn.prepareStatement(sql4);
				pstm5 = conn.prepareStatement(sql5);
				pstm6 = conn.prepareStatement(sql6);
				pstm7 = conn.prepareStatement(sql7);
				pstm8 = conn.prepareStatement(sql8);
				pstm9 = conn.prepareStatement(sql9);
				
				pstm1.setString(1, param.str0);
				pstm6.setString(1, param.str0);
				pstm7.setString(1, param.str0);
				pstm8.setString(1, param.str0);
				pstm9.setString(1, param.str0);
				
				
				String state = "";
				
				rs = pstm1.executeQuery();
				double send = 0,count=0;
				if(rs.next()){
					double sum = rs.getDouble(1);
					pstm2.setString(1, rs.getString(2));
					rs2 = pstm2.executeQuery();
					if(rs2.next()){
						pstm3.setString(1, param.str0);
						rs3 = pstm3.executeQuery();
						while(rs3.next()){
							send = rs3.getDouble(4);
							count+=send;
							
							pstm4.setString(1, rs3.getString(4));
							
							
//							System.out.println("rs3.getDouble(7):"+rs3.getDouble(7)+"|"+(Math.abs(rs3.getDouble(7))<0.0000001));
							System.out.println(rs3.getString(3)+"===>hello\t"+Math.abs(rs3.getDouble(7))+"\t"+rs3.getDouble(7));
							if(rs3.getDouble(7)>0){
								state = "HH";
								if(Math.abs(rs3.getDouble(7))<0.00000001){
									state = "CY";
								}
							}else if(Math.abs(rs3.getDouble(7))>0.0000001){
								conn.rollback();
								return "领料单和库存存在数据差异!";
							}else{
								state = "CY";
							}
							pstm4.setString(2, state);
							pstm4.setString(3, rs3.getString(3));
							pstm4.setString(4, rs3.getString(1));
//							pstm4.addBatch();
							
//							System.out.println(rs3.getString(4)+"|"+state+"|"+param.str0+"|"+rs3.getString(1));
							
							result = pstm4.executeUpdate();
							if(result<1){
								conn.rollback();
								return "其它用户正在更新物资库存!";
							}
							
						}
						
						if(Math.abs(count-sum)>0.9){
							conn.rollback();
							return "出库和入库数据不符!";
						}
						
						pstm5.setString(1, sum+"");
						pstm5.setString(2, rs.getString(2));
						pstm5.setString(3, rs2.getString(1));
						
						rs4 = pstm6.executeQuery();
						if(rs4.next()){
							String version = rs4.getString(1);
							pstm7.setString(2, version);
							
							int c7 = pstm7.executeUpdate();
							if(c7<1){
								conn.rollback();
								return "领料单已被其它用户删除了!";
							}
							pstm8.executeUpdate();
//							int c[] = pstm4.executeBatch();
//							for(int i:c){
//								if(i<1){
//									conn.rollback();
//									return "其它用户正在更新物资库存!";
//								}
//							}
							pstm5.executeUpdate();
							
							String mesString = updateStore(conn,rs.getString(2),rs.getString(3),rs.getDouble(1));
							if(!mesString.equals(OK)){
								conn.rollback();
								return mesString;
							}
							
							pstm9.executeUpdate();
							
							
							
							
						}else{
							conn.rollback();
							return "领料单被删除了!";
							
						}
						
					}else{
						conn.rollback();
						return "领料物资已删除!";
					}
				}else{
					conn.rollback();
					System.out.println("=========3========");
					return "领料数据已归档，或已删除!";
				}
				
				
				
				
				
				conn.commit();
			}catch (Exception e) {
				try {conn.rollback();} catch (SQLException e1) {e1.printStackTrace();}
				e.printStackTrace();
				return e.toString();
			}finally{
				if(rs!=null){try {rs.close();} catch (SQLException e){e.printStackTrace();}}
				if(rs2!=null){try {rs2.close();} catch (SQLException e){e.printStackTrace();}}
				if(rs3!=null){try {rs3.close();} catch (SQLException e){e.printStackTrace();}}
				if(pstm1!=null){try {pstm1.close();} catch (SQLException e){e.printStackTrace();}}
				if(pstm2!=null){try {pstm2.close();} catch (SQLException e){e.printStackTrace();}}
				if(pstm3!=null){try {pstm3.close();} catch (SQLException e){e.printStackTrace();}}
				if(conn!=null){try {conn.close();} catch (SQLException e){e.printStackTrace();}}
			}
			return OK;
		}
		
	
	//------------important------------
	private String updateStore(Connection conn,String materID, String storeID, double count){
		
		PreparedStatement pstm1 = null;
		PreparedStatement pstm2 = null;
		ResultSet rs = null;
		
		System.out.println(count);
		
		String sql1 = "select version1 from BMWZ_STORAGECOUNT where storage_id=? and material_id=? ";
		
		String sql2_1 = "update BMWZ_STORAGECOUNT set MATERIAL_COUNT=MATERIAL_COUNT+?,version1=version1+1 where storage_id=? and material_id=? and version1=? ";
		
		String sql2_2 = "insert into BMWZ_STORAGECOUNT(STORAGE_ID,MATERIAL_ID,VERSION1,MATERIAL_COUNT) values(?,?,'1000000',?) ";
		//1000000
		int version = 0,uCount = 0;
		try {
			pstm1 = conn.prepareStatement(sql1);
			pstm1.setString(1, storeID);
			pstm1.setString(2, materID);
			rs = pstm1.executeQuery();
			if(rs.next()){
				version = rs.getInt(1);
				pstm2 = conn.prepareStatement(sql2_1);
				pstm2.setDouble(1, count);
				pstm2.setString(2, storeID);
				pstm2.setString(3, materID);
				pstm2.setInt(4, version);
				uCount = pstm2.executeUpdate();
				if(uCount<1){
					return "其它用户在更新!";
				}
			}else{
				pstm2 = conn.prepareStatement(sql2_2);
				pstm2.setString(1, storeID);
				pstm2.setString(2, materID);
				pstm2.setDouble(3, count);
				pstm2.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return e.toString();
		}
		return OK;
	}
	
	
	private double caluate(double price, double num){
		System.out.println("format:"+(price*num)+"|"+df.format(price*num)+"|"+Double.parseDouble(df.format(price*num)));
		
		return Double.parseDouble(df.format(price*num));
	};
	
	private  double getReusltSetByNewConn(String sql, String param, double money,double receMoney,double dj){
		
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try{
			conn = ConnectionManager.getInstance().getConnection();
			conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, Integer.valueOf(param));
			rs = pstm.executeQuery();
			System.out.println(param+"|\t=1==========================");
			while(rs.next()){
				System.out.println(param+"\tnumbers="+rs.getDouble(1));
				money = money + (receMoney-caluate(dj, rs.getDouble(1)));
			}
			conn.commit();
		}catch(Exception e){
			
			e.printStackTrace();
		}finally{
			if(rs!=null)try {rs.close();} catch (SQLException e) {e.printStackTrace();	}
			if(pstm!=null)try {pstm.close();} catch (SQLException e) {e.printStackTrace();	}
			if(conn!=null)try {conn.close();} catch (SQLException e) {e.printStackTrace();	}
		}
	
		return money;
	}

	private void rsClose(ResultSet rs){
		if(rs!=null)try {rs.close();} catch (SQLException e) {e.printStackTrace();}
	}
	
	private void pstmClose(PreparedStatement pstm){
		if(pstm!=null)try {pstm.close();} catch (SQLException e) {e.printStackTrace();}
	}
	
	private void connClose(Connection conn){
		if(conn!=null)try {conn.close();} catch (SQLException e) {e.printStackTrace();}
	}
	
	private void connRollback(Connection conn){
		if(conn!=null)try{conn.rollback();}catch(Exception e){e.printStackTrace();}
	}
	
	
	private double getMoney(Map<String,SendBean> map) throws Exception{
		
		StringBuffer sql7 = new StringBuffer("select rs.send_num,rs.receipt_id from bmwz_requestre rs where rs.receipt_id in ( " );
		double money = 0,num=0,receMoney=0;
		System.out.println("map1=\n"+map);
		Iterator<String> it = map.keySet().iterator();
		String key = "";
		SendBean bean = null;
		int i = 0;
		Map<String,Double> dMap = new HashMap<String, Double>();
		while(it.hasNext()){
			
			key = it.next();
			System.out.println("key="+key);
			bean = map.get(key);
			dMap.put(key, bean.getReceMoney());
			if(bean.getKind()==1){
				continue;
			}
			if(i==0){
				sql7.append("'").append(key).append("'");
			}else{
				sql7.append(",'").append(key).append("'");
			}
			i++;
		}
		String sql8 = sql7.toString()+")";
		
		
		
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		double temp = 0;
		
		Map<String,Double> mMap = new HashMap<String, Double>();
		
		if(i>0){
			try{
				conn = ConnectionManager.getInstance().getConnection();
				pstm = conn.prepareStatement(sql8);
				rs = pstm.executeQuery();
				while(rs.next()){
					System.out.println("getMoney->num="+rs.getDouble(1));
					
					key = rs.getString(2);
					bean = map.get(key);
					num = rs.getDouble(1);
					
					if(mMap.get(key)==null){
						mMap.put(key, 0d);
					}
					
					mMap.put(key, mMap.get(key)+caluate(bean.getDj(), rs.getDouble(1)));
					
				}
				System.out.println("map2=\n"+map);
				System.out.println("mMap:"+mMap);
				
			}catch(Exception e){
				throw e;
			}finally{
				rsClose(rs);
				pstmClose(pstm);
				connClose(conn);
			}
		}
		
		
		
		it = map.keySet().iterator();
		while(it.hasNext()){
			key = it.next();
			bean = map.get(key);
			
			if(bean.getKind()==1){
				money = money + caluate(bean.getDj(),bean.getSend());
			}else{
				
				System.out.print("calute:"+caluate(bean.getDj(), bean.getSend())+"\t receMoney:"+dMap.get(key));
				
				receMoney = dMap.get(key) - (mMap.get(key)==null?0d:mMap.get(key));
				receMoney = getDoubleWT(receMoney);
				System.out.println( "result:"+receMoney+"\tmMap:"+(mMap.get(key)==null?0d:mMap.get(key)));
				dMap.put(key, receMoney);
				
				System.out.println("money:"+money+"\t"+"receMoney:"+receMoney);
				
				money = money + receMoney;
			}
		}
		
		
		return getDoubleWT(money);
	}
	
	
	private double getDoubleWT(double d){
		return Double.parseDouble(df.format(d));
	}
	
	
	
	private class SendBean{
		
		private double dj;
		private double send;
		private double has;
		private double receMoney;
		private int kind;
		private double hadOut;
		
		public SendBean(double dj,  double receMoney, int kind,double has) {
			super();
			this.dj = dj;
			this.receMoney = receMoney;
			this.kind = kind;
			this.has = has;
			this.send = 0;
		}
		
		public SendBean(double dj,  double receMoney, int kind,double has,double send){
			this(dj, receMoney, kind, has);
			this.send = send;
		}
		
		
		
		public double getHas() {
			return has;
		}

		public void setHas(double has) {
			this.has = has;
		}

		public double getHadOut() {
			return hadOut;
		}

		public void setHadOut(double hadOut) {
			this.hadOut = hadOut;
		}

		public double getDj() {
			return dj;
		}
		public void setDj(double dj) {
			this.dj = dj;
		}
		public double getSend() {
			return send;
		}
		public void setSend(double send) {
			this.send = send;
		}
		public double getReceMoney() {
			return receMoney;
		}
		public void setReceMoney(double receMoney) {
			this.receMoney = receMoney;
		}
		public int getKind() {
			return kind;
		}
		public void setKind(int kind) {
			this.kind = kind;
		}




		@Override
		public String toString() {
			return "SendBean [dj=" + dj + ", send=" + send + ", has=" + has
					+ ", receMoney=" + receMoney + ", kind=" + kind
					+ ", hadOut=" + hadOut + "]\n";
		}

		
		
		
		
	}
	
	
	
	
	
	
}
