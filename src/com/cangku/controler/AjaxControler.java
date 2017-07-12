package com.cangku.controler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gzb.db_source.ConnectionManager;
import com.gzb.prestatement.MyPreStatement;
import com.gzb.util.StringUtil;

public class AjaxControler {

	private MyPreStatement myPreStat = new MyPreStatement();
	
	private final String defalutDataString = "{\"data\": []}";
	
	public String getCheckStore(HttpServletRequest request, HttpServletResponse response){
		
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		String sql = myPreStat.getSql("store_04");
		
		String houseID = request.getParameter("houseID");
		String hasKC = request.getParameter("kc");
		String result = defalutDataString;
		
		System.out.println("==========>"+hasKC+"|"+houseID);
		
		try{
			conn = ConnectionManager.getInstance().getConnection();
			pstm = conn.prepareStatement(sql.toString());
			pstm.setString(1, hasKC);
			pstm.setString(2, houseID);
			rs = pstm.executeQuery();
			
			result = createJsonDataTablesForKC(rs);
			
			
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
		
		return result;
	}
	
	public String checkStoreLine(HttpServletRequest request, HttpServletResponse response){
		
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		String sql = myPreStat.getSql("store_05");
		String houseID = request.getParameter("houseID");
		String result = defalutDataString;
		
		try{
			conn = ConnectionManager.getInstance().getConnection();
			pstm = conn.prepareStatement(sql.toString());
			
			pstm.setString(1, houseID);
			rs = pstm.executeQuery();
			
			result = createJsonDataTables(rs);
			
			
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
		return result;
	}
	
	
	
	public String onSearchMaterial(HttpServletRequest request, HttpServletResponse response){
		
		
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		
		String materialName = request.getParameter("materialName");
		String supplyDept = request.getParameter("supplyDept");
		String categoryName = request.getParameter("categoryName");
		
		StringBuffer sql = new StringBuffer();
		sql.append("select m.material_id,m.material_name,c.category_name,m.material_module,m.material_size,m.material_bak, \n");
		sql.append("(select nvl(sum(r.receipt_ar_mount_has),0) from bmwz_receipt r where abs(r.receipt_ar_mount_has)>=0.00001 and r.receipt_material_id=m.material_id ) material_count1,\n");
		sql.append("( select wm_concat(to_char(r.receipt_postion)) from bmwz_receipt r where abs(r.receipt_ar_mount_has)>=0.00001 and r.receipt_material_id=m.material_id ) postion, \n");
		sql.append("decode(m.MATERIAL_ISONCHECK,'1','启用','未启用') MATERIAL_ISONCHECK , m.material_minmount,m.material_maxmount,MATERIAL_USED \n");
		sql.append("from bmwz_material m left join bmwz_category c on c.category_id=m.material_type where 1=1 \n");
		
		String result = defalutDataString;
		
		if(!"".equals(materialName)){
			sql.append(" and ( m.material_name like ? or  m.material_bak like ? ) \n");
		}
		
		if(!"".equals(supplyDept)){
			sql.append(" and m.material_id in ( select r.receipt_material_id from bmwz_receipt r where r.receipt_supplydept like ?) \n");
		}
		if(!"".equals(categoryName)){
			sql.append(" and m.material_type in (select c.category_id from bmwz_category c start with category_name like ?  connect by prior category_id = category_fid ) ");
		}
		
		System.out.println(sql);
		
		try{
			conn = ConnectionManager.getInstance().getConnection();
			pstm = conn.prepareStatement(sql.toString());
		  
			int i = 1;
			
			if(!"".equals(materialName)){
				pstm.setString(i++, "%"+materialName+"%");
				pstm.setString(i++, "%"+materialName+"%");
			}
			
			if(!"".equals(supplyDept)){
				pstm.setString(i++, "%"+supplyDept+"%");	
			}
			if(!"".equals(categoryName)){
				pstm.setString(i++, "%"+categoryName+"%");	
			}
			
			System.out.println(sql+"\n");
			System.out.println(materialName+"\t"+supplyDept+"\t"+categoryName);
			
			rs = pstm.executeQuery();
			
			result = createJsonDataTables(rs);

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
		
		
		return result;
	}
	
		
	
	
	public String getSummerMes(HttpServletRequest request, HttpServletResponse response){
		Connection conn = null;
		PreparedStatement pstm1 = null,pstm2=null, pstm3=null, pstm4=null;
		ResultSet rs = null;
		StringBuffer sBuffer = new StringBuffer();
		
		String houseID = request.getParameter("houseID");
		
		String sqlKuCun = "select sum(r.receipt_ar_mount_has) from bmwz_receipt r where r.receipt_ar_mount_has>0.00001 and r.receipt_storageno=? ";
		String sqlSum = "select round(sum(r.receipt_ar_mount_has*r.RECEIPT_A_DJ),2) from bmwz_receipt r where r.receipt_ar_mount_has>0.00001 and r.receipt_storageno=?";
		String sqlRec = "select sum(r.RECEIPT_A_AMOUNT) from bmwz_receipt r where r.receipt_receivedtime>= to_date(to_char(sysdate,'yyyymm')||'01','yyyymmdd') and r.receipt_storageno=?";
		String sqlReq = "select sum(r.REQUEST_TAMOUNT) from bmwz_request r where r.request_issuetime>= to_date(to_char(sysdate,'yyyymm')||'01','yyyymmdd') and r.request_storageno=?";
		
		
		
		try{
			conn = ConnectionManager.getInstance().getConnection();
			pstm1 = conn.prepareStatement(sqlKuCun);
			pstm2 = conn.prepareStatement(sqlSum);
			pstm3 = conn.prepareStatement(sqlRec);
			pstm4 = conn.prepareStatement(sqlReq);
			
			
			pstm1.setString(1, houseID);
			pstm2.setString(1, houseID);
			pstm3.setString(1, houseID);
			pstm4.setString(1, houseID);
			
			rs = pstm1.executeQuery();
			if(rs.next()){
				sqlKuCun = rs.getString(1);
				System.out.println(sqlKuCun+"|"+rs.getString(1));
			}
			rs.close();
			
			rs = pstm2.executeQuery();
			if(rs.next()){
				sqlSum = rs.getString(1);
			}
			rs.close();
			
			rs = pstm3.executeQuery();
			if(rs.next()){
				sqlRec = rs.getString(1);
			}
			rs.close();
			
			rs = pstm4.executeQuery();
			if(rs.next()){
				sqlReq = rs.getString(1);
			}
			rs.close();
			
			sBuffer.append("{");
			sBuffer.append("\"sqlKuCun\":"+sqlKuCun+",");
			sBuffer.append("\"sqlSum\":"+sqlSum+",");
			sBuffer.append("\"sqlRec\":"+sqlRec+",");
			sBuffer.append("\"sqlReq\":"+sqlReq+"");
			
			sBuffer.append("}");
			
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(rs!=null)
				try {rs.close();} catch (SQLException e1) {	e1.printStackTrace();}
			if(pstm1!=null)
				try {pstm1.close();} catch (SQLException e) {e.printStackTrace();}
			if(pstm2!=null)
				try {pstm1.close();} catch (SQLException e) {e.printStackTrace();}	
			if(pstm3!=null)
				try {pstm1.close();} catch (SQLException e) {e.printStackTrace();}	
			if(pstm4!=null)
				try {pstm1.close();} catch (SQLException e) {e.printStackTrace();}	
			if(conn!=null)
				try {conn.close();	} catch (SQLException e) {e.printStackTrace();}
		}	
		
		
		
		
		return sBuffer.toString();
	}
	
	
	
	public String getYearCount(HttpServletRequest request, HttpServletResponse response){
		Connection conn = null;
		PreparedStatement pstm1 = null,pstm2=null;
		ResultSet rs = null;
		StringBuffer sBuffer = new StringBuffer();
		
		String yType = request.getParameter("YType");
		String houseID = request.getParameter("houseID");
		int type = Integer.valueOf(yType);
		
		String appendSql1 = appendSql(type, 0);
		String appendSql2 = appendSql(type, 1);
		
		Set<String> set =new LinkedHashSet<String>();
		
		Map<String, String> map1 = new HashMap<String, String>();
		Map<String, String> map2 = new HashMap<String, String>();
		
		String sql = "select to_char(r.receipt_receivedtime,'yyyymm'),sum(r.RECEIPT_AR_MOUNT) from bmwz_receipt r where 1=1 and r.receipt_storageno=? ";
		
		String endSql = " group by to_char(r.receipt_receivedtime,'yyyymm') order by 1 ";
		
		StringBuffer sBuffer1 = new StringBuffer();
		StringBuffer sBuffer2 = new StringBuffer();
		StringBuffer sBuffer3 = new StringBuffer();
		try{
			conn = ConnectionManager.getInstance().getConnection();
			pstm1 = conn.prepareStatement(sql+appendSql1+endSql);
			pstm2 = conn.prepareStatement(sql+appendSql2+endSql);
			
			System.out.println(sql+appendSql1+endSql);
			System.out.println(sql+appendSql2+endSql);
			
			pstm1.setString(1, houseID);
			pstm2.setString(1, houseID);
			
			rs = pstm1.executeQuery();
			
			while(rs.next()){
				set.add(rs.getString(1));
				map1.put(rs.getString(1), rs.getString(2));
			}
			rs.close();
			rs = pstm2.executeQuery();
			while(rs.next()){
				map2.put(rs.getString(1), rs.getString(2));
			}
			sBuffer1.append("[");
			sBuffer2.append("[");
			sBuffer3.append("[");
			
			String key = "",temp="";
			int num = 0;
			Iterator<String> it = set.iterator();
			while(it.hasNext()){
				key = it.next();
				
				sBuffer1.append("\""+key+"\",");
				temp = map1.get(key);
				temp = temp!=null?temp:"0";
				
				
				sBuffer2.append(temp+",");
				
				num = Integer.valueOf(key)-100;
				
				
				temp = map2.get(num+"");
				temp = temp!=null?temp:"0";
				sBuffer3.append(temp+",");
			}
			
			
			
			
			
			
			sBuffer1.setLength(sBuffer1.length()-1);
		
			sBuffer2.setLength(sBuffer2.length()-1);
			sBuffer3.setLength(sBuffer3.length()-1);
			sBuffer1.append("]");
			sBuffer2.append("]");
			
			
			
			
			
			sBuffer3.append("]");
			
			sBuffer.append("{\"category\":"+sBuffer1.toString());
			sBuffer.append(",\"data1\":"+sBuffer2.toString());
			sBuffer.append(",\"data2\":"+sBuffer3.toString());
			sBuffer.append("}");
		
			Thread.sleep(500);
		
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(rs!=null)
				try {rs.close();} catch (SQLException e1) {	e1.printStackTrace();}
			if(pstm1!=null)
				try {pstm1.close();} catch (SQLException e) {e.printStackTrace();}
			if(conn!=null)
				try {conn.close();	} catch (SQLException e) {e.printStackTrace();}
		}	
		System.out.println(sBuffer);
		
		return sBuffer.toString();
	}
	
	

	public String getYearMoney(HttpServletRequest request, HttpServletResponse response){
		Connection conn = null;
		PreparedStatement pstm1 = null,pstm2=null;
		ResultSet rs = null;
		StringBuffer sBuffer = new StringBuffer();
		
		String yType = request.getParameter("YType");
		String houseID = request.getParameter("houseID");
		int type = Integer.valueOf(yType);
		
		String appendSql1 = appendSql(type, 0);
		String appendSql2 = appendSql(type, 1);
		
		Set<String> set =new LinkedHashSet<String>();
		
		Map<String, String> map1 = new HashMap<String, String>();
		Map<String, String> map2 = new HashMap<String, String>();
		
		String sql = "select to_char(r.receipt_receivedtime,'yyyymm'),sum(r.RECEIPT_A_AMOUNT) from bmwz_receipt r where 1=1 and r.receipt_storageno=? ";
		
		String endSql = " group by to_char(r.receipt_receivedtime,'yyyymm') order by 1 ";
		
		StringBuffer sBuffer1 = new StringBuffer();
		StringBuffer sBuffer2 = new StringBuffer();
		StringBuffer sBuffer3 = new StringBuffer();
		try{
			conn = ConnectionManager.getInstance().getConnection();
			pstm1 = conn.prepareStatement(sql+appendSql1+endSql);
			pstm2 = conn.prepareStatement(sql+appendSql2+endSql);
			
			System.out.println(sql+appendSql1+endSql);
			System.out.println(sql+appendSql2+endSql);
			
			pstm1.setString(1, houseID);
			pstm2.setString(1, houseID);
			
			rs = pstm1.executeQuery();
			
			while(rs.next()){
				set.add(rs.getString(1));
				map1.put(rs.getString(1), rs.getString(2));
			}
			rs.close();
			rs = pstm2.executeQuery();
			while(rs.next()){
				map2.put(rs.getString(1), rs.getString(2));
			}
			sBuffer1.append("[");
			sBuffer2.append("[");
			sBuffer3.append("[");
			
			String key = "",temp="";
			int num = 0;
			Iterator<String> it = set.iterator();
			while(it.hasNext()){
				key = it.next();
				
				sBuffer1.append("\""+key+"\",");
				temp = map1.get(key);
				temp = temp!=null?temp:"0";
				
				
				sBuffer2.append(temp+",");
				
				num = Integer.valueOf(key)-100;
				
				
				temp = map2.get(num+"");
				temp = temp!=null?temp:"0";
				sBuffer3.append(temp+",");
			}
			
			
			
			
			
			
			sBuffer1.setLength(sBuffer1.length()-1);
		
			sBuffer2.setLength(sBuffer2.length()-1);
			sBuffer3.setLength(sBuffer3.length()-1);
			sBuffer1.append("]");
			sBuffer2.append("]");
			
			
			
			
			
			sBuffer3.append("]");
			
			sBuffer.append("{\"category\":"+sBuffer1.toString());
			sBuffer.append(",\"data1\":"+sBuffer2.toString());
			sBuffer.append(",\"data2\":"+sBuffer3.toString());
			sBuffer.append("}");
		
			Thread.sleep(500);
		
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(rs!=null)
				try {rs.close();} catch (SQLException e1) {	e1.printStackTrace();}
			if(pstm1!=null)
				try {pstm1.close();} catch (SQLException e) {e.printStackTrace();}
			if(conn!=null)
				try {conn.close();	} catch (SQLException e) {e.printStackTrace();}
		}	
		System.out.println(sBuffer);
		
		return sBuffer.toString();
	}
	
	
	
	public String getYearCountMoney(HttpServletRequest request, HttpServletResponse response){
		Connection conn = null;
		PreparedStatement pstm1 = null, pstm2=null;
		ResultSet rs = null;
		StringBuffer sBuffer = new StringBuffer();
		String sql1 = "select  r.request_ldpt_id,sum(REQUEST_AI_MOUNT),sum(r.REQUEST_TAMOUNT) from bmwz_request r where 1=1 and r.request_storageno=? and r.request_issuetime>=to_date((to_char(sysdate,'yyyy')||'0101'),'yyyymmdd') group by request_ldpt_id order by 2 desc ,1 desc";
		String sql2 = "select sum(REQUEST_AI_MOUNT),sum(r.REQUEST_TAMOUNT) from bmwz_request r where 1=1 and r.request_issuetime>=to_date((to_char(sysdate,'yyyy')||'0101'),'yyyymmdd') and r.request_storageno=?";
		String YType = request.getParameter("YType");
		String houseID = request.getParameter("houseID");
		Set<String> set = new LinkedHashSet<String>();
		Map<String, String> map1 = new HashMap<String, String>();
		Map<String, String> map2 = new HashMap<String, String>();
		Map<String, String> map = null;
		String key = "";
		
		StringBuffer sBuffer1 = new StringBuffer();
		StringBuffer sBuffer2 = new StringBuffer();
		
		double count = 0, money = 0;
		
		try{
			conn = ConnectionManager.getInstance().getConnection();
			pstm1 = conn.prepareStatement(sql1);
			pstm2 = conn.prepareStatement(sql2);
			System.out.println(sql2);
			pstm1.setString(1, houseID);
			pstm2.setString(1, houseID);
			
			rs = pstm2.executeQuery();
			if(rs.next()){
				count = rs.getDouble(1);
				money = rs.getDouble(2);
			}
			
			
			rs = pstm1.executeQuery();
			while(rs.next()){
				set.add(rs.getString(1));
				map1.put(rs.getString(1), StringUtil.formatDouble2(rs.getDouble(2)/count*500));
				map2.put(rs.getString(1), StringUtil.formatDouble2(rs.getDouble(3)/money*500));
			}
			
			Iterator<String> it = set.iterator();
			
			sBuffer1.append("[");
			sBuffer2.append("[");
			
			while(it.hasNext()){
				key = it.next();
				sBuffer1.append("\""+key+"\",");
				
				if("1".equals(YType)){
					map = map1;
				}else{
					map = map2;
				}
				
				sBuffer2.append("{\"value\":"+map.get(key)+" , \"name\":\""+key+"\"},");
			}
			sBuffer1.setLength(sBuffer1.length()-1);
			sBuffer2.setLength(sBuffer2.length()-1);
			
			sBuffer1.append("]");
			sBuffer2.append("]");
			
			sBuffer.append("{\"category\":"+sBuffer1.toString()+",\"data\":"+sBuffer2.toString()+"}");
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(rs!=null)
				try {rs.close();} catch (SQLException e1) {	e1.printStackTrace();}
			if(pstm1!=null)
				try {pstm1.close();} catch (SQLException e) {e.printStackTrace();}
			if(pstm2!=null)
				try {pstm2.close();} catch (SQLException e) {e.printStackTrace();}
			if(conn!=null)
				try {conn.close();	} catch (SQLException e) {e.printStackTrace();}
		}	
		System.out.println("===9527=>"+sBuffer.toString());
		return sBuffer.toString();
	}
	
	
	
	
	//type==>0：3个月    1：半年    2:1年   yearType===>0 今年   1：上年
	
	public String appendSql(int type, int yearType){
		Connection conn = null;
		PreparedStatement pstm1 = null;
		ResultSet rs = null;
		StringBuffer sBuffer = new StringBuffer();
		
		String sql = "select (to_number(to_char(sysdate,'yyyy'))-1)||to_char(sysdate,'mm')||'01' from dual";
		if(yearType==0){
			sql = "select to_char(sysdate,'yyyymm')||'01' from dual";
		}
		
		
		String strDate = "20160801",maxDate="",minDate="";
		
		try{
			conn = ConnectionManager.getInstance().getConnection();
			pstm1 = conn.prepareStatement(sql);
			rs = pstm1.executeQuery();
			if(rs.next()){
				strDate = rs.getString(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(rs!=null)
				try {rs.close();} catch (SQLException e1) {	e1.printStackTrace();}
			if(pstm1!=null)
				try {pstm1.close();} catch (SQLException e) {e.printStackTrace();}
			if(conn!=null)
				try {conn.close();	} catch (SQLException e) {e.printStackTrace();}
		}	
		
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar calendar=Calendar.getInstance();   
		
		try {
			calendar.setTime(sdf.parse(strDate));
			calendar.add(Calendar.MONTH, 1);
			maxDate = sdf.format(calendar.getTime());
			
			if(type==0){
				calendar.add(Calendar.MONTH, -4);
				minDate = sdf.format(calendar.getTime());
			}
			if(type==1){
				calendar.add(Calendar.MONTH, -7);
				minDate = sdf.format(calendar.getTime());
			}
			if(type==2){
				calendar.add(Calendar.MONTH, -13);
				minDate = sdf.format(calendar.getTime());
			}
			
			
			
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//
		
		
		sBuffer.append(" and r.receipt_receivedtime>=to_date('"+minDate+"','yyyymmdd') ");
		sBuffer.append(" and r.receipt_receivedtime<to_date('"+maxDate+"','yyyymmdd') ");
		
		
		
		return sBuffer.toString();
	}
	
	
	
	
	
	
	
	
	
	private String createJsonDataTables(ResultSet rs) throws SQLException{
		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append("{\"data\": [");
		ResultSetMetaData rsmd = rs.getMetaData();
		int count = rsmd.getColumnCount();
		boolean flag = false;
		int i=1;
		while(rs.next()){
			
			if(flag){
				sBuffer.append(",[");
			}else{
				sBuffer.append("[");
			}
			
			if(!flag){
				flag = true;
			}
			
			for(i=1;i<=count;i++){
				if(i==count){
					sBuffer.append("\""+jsonReplace(rs.getString(i))+"\"");
				}else{
					sBuffer.append("\""+jsonReplace(rs.getString(i))+"\",");
				}
			}
			sBuffer.append("]");
			
		}
		sBuffer.append("]}");
		return sBuffer.toString();
	}
	
	

	private String createJsonDataTablesForKC(ResultSet rs) throws SQLException{
		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append("{\"data\": [");
		ResultSetMetaData rsmd = rs.getMetaData();
		int count = rsmd.getColumnCount();
		boolean flag = false;
		
		String category = "";
		double d = 0;
		int myCount = 1;
		while(rs.next()){
			
			if(!"".equals(category) && !jsonReplace(rs.getString(2)).equals(category)){
				sBuffer.append(",[\""+myCount+"\", \"\", \""+category+"\", \"\", \"\" , \"\", \"\", \"\", \"合计\", \""+StringUtil.formatDouble2(d)+"\", \"\", \"\", \"\"]  ");
				d = 0;
				myCount++;
			}
			
			if(flag){
				sBuffer.append(",[");
			}else{
				sBuffer.append("[");
			}
			
			if(!flag){
				flag = true;
			}
			sBuffer.append("\""+myCount+"\",");
			for(int i=1;i<=count;i++){
				if(i==count){
					sBuffer.append("\""+jsonReplace(rs.getString(i))+"\"");
				}else{
					sBuffer.append("\""+jsonReplace(rs.getString(i))+"\",");
				}
				
				if(i==2){
					category = jsonReplace(rs.getString(i));
					d += Double.parseDouble(rs.getString(9));
				}
				
			}
			sBuffer.append("]");
			myCount++;
		}
		
		sBuffer.append("]}");
		
		
		return sBuffer.toString();
	}
	
	
	
	
	
	
	
	
	
	
	
	private String jsonReplace(String str){
		if(str==null)str="";
		str = str.replaceAll("\"", "");
		return str.trim();
	}
	
	public void showParams(HttpServletRequest request) {  
        Map map = new HashMap();  
        Enumeration paramNames = request.getParameterNames();  
        while (paramNames.hasMoreElements()) {  
            String paramName = (String) paramNames.nextElement();  
  
            String[] paramValues = request.getParameterValues(paramName);  
            if (paramValues.length == 1) {  
                String paramValue = paramValues[0];  
                if (paramValue.length() != 0) {  
                    map.put(paramName, paramValue);  
                }  
            }  
        }  
  
        Set<Map.Entry<String, String>> set = map.entrySet();  
        System.out.println("------------------------------");  
        for (Map.Entry entry : set) {  
            System.out.println(entry.getKey() + ":" + entry.getValue());  
        }  
        System.out.println("------------------------------");  
    }  
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
