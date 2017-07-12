package com.gzb.api.controler;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gzb.api.dbutil.CommonModelSqlServer;
import com.gzb.json.impl.JsonForTable;
import com.gzb.util.StringUtil;

public class PreMindSearch extends CommonModelSqlServer {
 
	
	public String getPreMindList(HttpServletRequest request, HttpServletResponse response){
		StringBuffer sql = new StringBuffer("");
		sql.append("SELECT  mind_id,wxname,applyName,applyDate,c.NameCn,m.mindMoney,case when isopen='1' then '是' else '否' end,\n");
		sql.append("isnull(openman,'') openman,isnull(opendate,'') opendate,isnull(closeman,'') closeman,\n");
		sql.append("isnull(closetime,'') closetime,isnull(sendCount,0) sendCount,isnull(lastSendDate,'') lastSendDate\n");
		sql.append("FROM CUM_Customer_mind m left join CUM_Customer c on c.CustomerId=m.customerid\n");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return search(sql.toString(), null, new JsonForTable());
	}
	
	public String openPreMind(HttpServletRequest request, HttpServletResponse response){
		
		String data = request.getParameter("data");
		String inData[] = data.split(",");
		
		String datetime = StringUtil.getToday("yy/MM/dd HH:mm");
		String user = (String)request.getSession().getAttribute("userName");
		
		
		StringBuffer sql = new StringBuffer();
		sql.append("update CUM_Customer_mind set isopen='1',openman=?,opendate=? where mind_id in ( \n");
		for(int i=0;i<inData.length;i++){
			sql.append("?,");
		}
		sql.setLength(sql.length()-1);
		sql.append(" ) ");
		List<String> list = new ArrayList<>();
		list.add(user);
		list.add(datetime);
		for(int i=0;i<inData.length;i++){
			list.add(inData[i]);
		}
		System.out.println(sql+"\n"+list);
		
		String result = update(sql.toString(), list);
		if("OK".equals(result)){
			result = "成功开通";
		}else{
			result = result+",请稍后再试";
		}
		return result;
	}
	
	
	public String closePreMind(HttpServletRequest request, HttpServletResponse response){
		
		String data = request.getParameter("data");
		String inData[] = data.split(",");
		
		String datetime = StringUtil.getToday("yy/MM/dd HH:mm");
		String user = (String)request.getSession().getAttribute("userName");
		
		
		StringBuffer sql = new StringBuffer();
		sql.append("update CUM_Customer_mind set isopen='0',closeman=?,closetime=? where mind_id in ( \n");
		for(int i=0;i<inData.length;i++){
			sql.append("?,");
		}
		sql.setLength(sql.length()-1);
		sql.append(" ) ");
		List<String> list = new ArrayList<>();
		list.add(user);
		list.add(datetime);
		for(int i=0;i<inData.length;i++){
			list.add(inData[i]);
		}
		System.out.println(sql+"\n"+list);
		
		String result = update(sql.toString(), list);
		if("OK".equals(result)){
			result = "成功取消预警功能";
		}else{
			result = result+",请稍后再试";
		}
		return result;
	}
	
	
	
}
