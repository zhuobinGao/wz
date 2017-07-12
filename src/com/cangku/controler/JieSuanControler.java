package com.cangku.controler;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import oracle.net.aso.l;

import com.cangku.bean.MonthSummerReportBean;
import com.cangku.bean.StroreCountBean;
import com.gzb.prestatement.MyPreStatement;
import com.gzb.prestatement.bean.SqlParams;
import com.gzb.setting.material.MCategoryBean;
import com.gzb.util.StringUtil;

public class JieSuanControler {
	
	private String message = "",preYearMon="";
	private String firstDayStr = "", endDayStr="";
	private MyPreStatement preStatement = new MyPreStatement();
	private static Map<String, String> messageMap = new HashMap<String, String>();
	private static Map<String, String> progress = new HashMap<String, String>();
	
	//导入数据   checkIsOn    checkDataVaild importData  toReport createDataAndFile
	
	public String getPMessage(String logid){
		return JieSuanControler.messageMap.get(logid);
	}
	
	public String getPCount(String logid){
		return JieSuanControler.progress.get(logid);
	}
	
	
	public String calute(int year,int month,String materialID,String loginID){
		message = "1.请勿关闭窗口,数据检测中";
		
		
		JieSuanControler.messageMap.put(loginID, message);
		JieSuanControler.progress.put(loginID, "20%");
		
		try{
			if(checkIsOn(year,month,materialID)){
				return message;
			}
			boolean checkFlag = checkDataVaild(year,month,materialID);
			if(!checkFlag){
				message = "数据已经月结,或存在异常!";
				return message;
			}
			Thread.sleep(1500);
			//导入数据   checkIsOn    checkDataVaild importData  toReport createDataAndFile
			message = "2.请勿关闭窗口,数据导入中";
			JieSuanControler.messageMap.put(loginID, message);
			JieSuanControler.progress.put(loginID, "40%");
			
			importData(year,month,materialID, loginID);
			Thread.sleep(1500);
			//数据规约
			message = "3.请勿关闭窗口,数据统计中";
			messageMap.put(loginID, message);
			progress.put(loginID, "60%");
			
			toReport(year,month,materialID);
			Thread.sleep(1500);
			//生成数据与文件
			message = "4.请勿关闭窗口,生成文件与数据中";
			JieSuanControler.messageMap.put(loginID, message);
			JieSuanControler.progress.put(loginID, "80%");
			
			createDataAndFile();
			
			Thread.sleep(1500);
			
			message = "5.已经完成["+endDayStr.replaceAll("-", "").substring(0,6)+"]数据月结";
			JieSuanControler.messageMap.put(loginID, message);
			JieSuanControler.progress.put(loginID, "100%");
			Thread.sleep(1500);
			
			JieSuanControler.messageMap.remove(loginID);
			JieSuanControler.progress.remove(loginID);
			
		}catch(Exception e){
			e.printStackTrace();
			return "请取消结算，尝试重新月结！\n错误："+e.toString();
		}
		return "OK";
		
		
	}
	
	
	public String cancelCalute(int year,int month,String houseID,String loginID){
		
		String returnMes = "";
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month-1);
		
		String nextMonth = StringUtil.getFormatDate(calendar.getTime());
		nextMonth = nextMonth.replaceAll("-", "").substring(0, 6);
		SqlParams params = new SqlParams();
		params.setStr0(nextMonth);
		params.setStr1(houseID);
		params.setStrCount(2);
		
		List<String> list = preStatement.getTabeData("report_11", params);
		if(list.size()<1){
			returnMes = "不存在该月数据";
			return returnMes;
		}
		if("0".equals(list.get(0))){
			returnMes = "该月数据已锁定不能更改";
			return returnMes;
		}
		calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month-1);
		
		String nowMonth = StringUtil.getFormatDate(calendar.getTime());
		nowMonth = nowMonth.replaceAll("-", "").substring(0, 6);
		
		params.setStr0(nowMonth);
		params.setStr1(houseID);
		params.setStrCount(2);
		preStatement.updateData("report_12", params);
		preStatement.updateData("report_13", params);
		preStatement.updateData("report_28", params);
		
		int endDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		String bday = StringUtil.getFormatDate(calendar.getTime());
		calendar.set(Calendar.DAY_OF_MONTH, endDay);
		String eday = StringUtil.getFormatDate(calendar.getTime());
		params.setStr0(bday);
		params.setStr1(eday);
		params.setStr2(houseID);
		params.setStrCount(3);
		preStatement.updateData("report_48", params);
		preStatement.updateData("report_49", params);
		
		params.setStr0(houseID);
		params.setStr1(nowMonth);
		params.setStrCount(2);
		preStatement.updateData("report_21", params);
		
		
		return "OK";
	}
	
	public String verify(String date,String houseID,String username){
		String returnMes = "";
		SqlParams params = new SqlParams();
		params.setStr0("0");
		params.setStr1(username);
		params.setStr2(date);
		params.setStr3("1");
		params.setStr4(houseID);
		params.setStrCount(5);
		String mes = preStatement.updateData("report_47", params);
		if("OK".equals(mes)){
			returnMes = "月结数据已成功审核!";
		}else{
			returnMes = "月结数据已审核失败!";
		}
		return returnMes;
	}
	
	public String unVerify(String date,String houseID,String username){
		String returnMes = "";
		SqlParams params = new SqlParams();
		params.setStr0("1");
		params.setStr1("");
		params.setStr2(date);
		params.setStr3("0");
		params.setStr4(houseID);
		params.setStrCount(5);
		String mes = preStatement.updateData("report_47", params);
		if("OK".equals(mes)){
			returnMes = "月结数据已取消审核!";
		}else{
			returnMes = "月结数据已取消审核失败!";
		}
		return returnMes;
		
	}
		
	private boolean checkIsOn(int year,int month,String materialID){
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month-1);
		String nowMonth = StringUtil.getFormatDate(calendar.getTime());
		nowMonth = nowMonth.replaceAll("-", "").substring(0, 6);

		
		calendar.add(Calendar.MONTH, -1);
		preYearMon=StringUtil.getFormatDate(calendar.getTime());
		preYearMon = preYearMon.replaceAll("-", "").substring(0, 6);

		SqlParams params = new SqlParams();
		
		params.setStr0(nowMonth);
		params.setStr1(materialID);
		params.setStrCount(2);
		List<String> list = preStatement.getTabeData("report_08", params);
		if(list.size()>0){
			message = "该月数据已存在结算";
			return true;
		}
		
		
		params.setStr0(preYearMon);
		params.setStr1(materialID);
		params.setStrCount(2);
		

		
		list = preStatement.getTabeData("report_08", params);
		
		params.setStr0(materialID);
		params.setStrCount(1);
		List<String> list2 = preStatement.getTabeData("report_46", params);
		
		if(list.size()<1&&list2.size()>0){
			message = "上个月数据没有结算";
			return true;
		}
		
		if(list2.size()<=0){
			return false;
		}
		
		String str = list.get(0);
		if(!"1".equals(str)&&list2.size()>0){
			message = "上个月数据结算没正常完成";
			return true;
		}
		
		return false;
	}
	
	private boolean checkDataVaild(int year,int month,String materialID){
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month-1);
		
		int endDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		
		firstDayStr = StringUtil.getFormatDate(calendar.getTime());
		
		calendar.set(Calendar.DAY_OF_MONTH, endDay);
		
		
		endDayStr = StringUtil.getFormatDate(calendar.getTime())+" 23:59:59";
		
	

		
		
		
		calendar.add(Calendar.MONTH, -1);
		
		preYearMon=StringUtil.getFormatDate(calendar.getTime());
		preYearMon = preYearMon.replaceAll("-", "").substring(0, 6);
		
		SqlParams params = new SqlParams();
		params.setStr0(firstDayStr);
		params.setStr1(endDayStr);
		params.setStr2(materialID);
		
	
		
		params.setStr3(firstDayStr);
		params.setStr4(endDayStr);
		params.setStr5(materialID);
		
		params.setStrCount(6);
		List<String> list = preStatement.getTabeData("report_01", params);
		int count = 0;
		String str = list.get(0);
		if(str==null){
			return false;
		}
		try{
			count = Integer.parseInt(str);
		}catch(Exception e){
			return false;
		}
		if(count>0){
			return false;
		}
		return true;
	}

	private void importData(int year,int month,String houseID,String loginID){
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month-1);
		String nowMonth = StringUtil.getFormatDate(calendar.getTime());
		SqlParams inertParams = new SqlParams();
		
		String nowYYYYMM = nowMonth.replaceAll("-", "").substring(0, 6);
		
		inertParams.setStr0(nowYYYYMM);
		inertParams.setStr1(houseID);
		inertParams.setStr2(loginID);
		inertParams.setStrCount(3);
		String meString1 = preStatement.updateData("report_09",inertParams);
		
		
		SqlParams params = new SqlParams();
		params.setStr0(nowYYYYMM);
		params.setStr1(endDayStr);
		params.setStr2(houseID);
		params.setStrCount(3);
		String meString = preStatement.updateData("report_02_01", params);
		
		System.out.println(nowYYYYMM+"|"+endDayStr+"|"+houseID);
		
		
		meString = preStatement.updateData("report_03_01", params);
		
		
	}
	
	private void toReport(int year,int month,String houseID){
		// 改造成两层树形结构 materialID2
		SqlParams params = new SqlParams();
		params.setStrCount(-1);
		List<String> datas = preStatement.getTabeData("catory_02", params);
		
		List<String> datas_rec = new ArrayList<String>();
		List<String> datas_req = new ArrayList<String>();
		
		
		String[] strs = null; 
		MCategoryBean bean = null,tempBean = null;
		Map<String,MCategoryBean> rootMap = new LinkedHashMap<String, MCategoryBean>();
		Map<String, MCategoryBean> map = new HashMap<String,MCategoryBean>();
		String catogeryID = null,catogeryFID=null;
		
		for(String temp : datas){
			strs = temp.split("\\|");
			bean = new MCategoryBean(strs[0],strs[1],strs[2],strs[0]);
			catogeryID = strs[0];
			catogeryFID = strs[2];
			if(strs[2].equals("-1")){
				rootMap.put(strs[0],bean);
			}
			map.put(catogeryID, bean);
			tempBean = map.get(catogeryFID);
			if(tempBean!=null){
				tempBean.addMCategoryBean(bean);
			}
		}
		
		Iterator<String> it = map.keySet().iterator();
		String key = "";
		MCategoryBean tbean = null;
		while(it.hasNext()){
			key = it.next();
			tbean = map.get(key);
			catogeryFID = tbean.getCatoryFID();
			if("-1".equals(catogeryFID)){
				continue;
			}
			changeBean(tbean, tbean, map);
		}
		
		
		//-----------------构造每个物资-----------------------
		
		Map<String, MonthSummerReportBean> materialMap = new HashMap<String, MonthSummerReportBean>();
		MonthSummerReportBean materialBean = null;
		String materialID_list = null;

		int lineNumber = 0;
		MonthSummerReportBean summerBean = null;
		Map<String, MonthSummerReportBean> summerMap = new LinkedHashMap<String, MonthSummerReportBean>();
		it = rootMap.keySet().iterator();
		while(it.hasNext()){
			key = it.next();
			tbean = rootMap.get(key);
			summerBean = new MonthSummerReportBean();
			summerBean.setCategoryID(tbean.getCatoryID());
			summerBean.setCategoryBH(tbean.getCatoryBH());
			summerBean.setCategoryName(tbean.getCatoryName());
			summerBean.setLineNumber((lineNumber++)+"");
			summerBean.setYearMonth(endDayStr.replaceAll("-", "").substring(0,6));
			summerBean.setStorehouseNO(houseID);
			summerMap.put(tbean.getCatoryID(), summerBean);	
		}
		
		
		//------------------------
		
		params.setStr0(preYearMon);
		params.setStr1(houseID);
		params.setStrCount(2);
		
		//MonthSummerReportBean 期初结存
		datas.clear();
		datas = preStatement.getTabeData("report_06", params);
		for(String temp:datas){
			strs = temp.split("\\|");
			catogeryID = strs[0];
			
//			tbean = map.get(catogeryID);
//			catogeryID = tbean.getCatoryFID();
			summerBean = summerMap.get(catogeryID);
			
			
			if(summerBean==null){
				continue;
			}
			summerBean.setbCount(summerBean.getbCount()+StringUtil.toDouble(strs[1]));
			summerBean.setbSum(summerBean.getbSum()+StringUtil.toDouble(strs[2]));
		}

		
		
		//处理每个物资的期初结余
		datas = preStatement.getTabeData("report_26", params);

		for(String temp:datas){

			strs = temp.split("\\|");
			catogeryID = strs[0];
			

			materialID_list = strs[3];
			materialBean = materialMap.get(materialID_list);
			if(materialBean==null){
				materialBean = new MonthSummerReportBean(catogeryID, materialID_list, "", endDayStr.replaceAll("-", "").substring(0,6));
				materialMap.put(materialID_list, materialBean);
			}
			
			
			materialBean = materialMap.get(materialID_list);

			
			materialBean.setbCount(materialBean.getbCount()+StringUtil.toDouble(strs[1]));
			materialBean.setbSum(materialBean.getbSum()+StringUtil.toDouble(strs[2]));
		}
		
		
		
		
		
		// 进库
		
		String nowYYYYMM = firstDayStr.replaceAll("-", "").substring(0, 6);
		
		params.setStr0(nowYYYYMM);
//		params.setStr1(endDayStr);
		params.setStr1(houseID);
		params.setStrCount(2);
		//MonthSummerReportBean
		datas.clear();
		datas = preStatement.getTabeData("report_04_01", params);
		datas_rec.clear();
		
		copyListStr(datas,datas_rec);
		
		for(String temp:datas){
			strs = temp.split("\\|");
			catogeryID = strs[2];
		
			tbean = map.get(catogeryID);
			
			if(!summerMap.keySet().contains(catogeryID)){
				catogeryID = tbean.getCatoryFID();
			}
			summerBean = summerMap.get(catogeryID);
			
			
			
			summerBean.setiCount(summerBean.getiCount()+StringUtil.toDouble(strs[0]));
			summerBean.setiSum(summerBean.getiSum()+StringUtil.toDouble(strs[1]));
			
			//处理个个物资的结余进库数据
			
			materialID_list = strs[7];
			materialBean = materialMap.get(materialID_list);
			if(materialBean==null){
				materialBean = new MonthSummerReportBean(catogeryID, materialID_list, strs[4], endDayStr.replaceAll("-", "").substring(0,6));
				materialMap.put(materialID_list, materialBean);
			}
			if(materialID_list.contains("600D1617AD4C4F6E96205EFB55C5D19E")){
				System.out.println("===========================>\n"+temp);
			}
			
			materialBean = materialMap.get(materialID_list);
			materialBean.setCategoryID(catogeryID);
			materialBean.setCategoryBH(map.get(catogeryID).getCatoryBH());
			materialBean.setiCount(materialBean.getiCount()+StringUtil.toDouble(strs[0]));
			materialBean.setiSum(materialBean.getiSum()+StringUtil.toDouble(strs[1]));
			
		}
		
		//出库
		datas.clear();
		params.setStr0(nowYYYYMM);
//		params.setStr1(endDayStr);
		params.setStr1(houseID);
		params.setStrCount(2);
		
		datas = preStatement.getTabeData("report_05_01", params);
		datas_req.clear();

		copyListStr(datas,datas_req);
		
		for(String temp:datas){
			strs = temp.split("\\|");
			catogeryID = strs[2];
			tbean = map.get(catogeryID);
			if(!summerMap.keySet().contains(catogeryID)){
				catogeryID = tbean.getCatoryFID();
			}
			summerBean = summerMap.get(catogeryID);
			summerBean.setoCount(summerBean.getoCount()+StringUtil.toDouble(strs[0]));
			summerBean.setoSum(summerBean.getoSum()+StringUtil.toDouble(strs[1]));
			
			//处理每个物资的结余出库数据
			materialID_list = strs[7];
			materialBean = materialMap.get(materialID_list);
			if(materialBean==null){
				materialBean = new MonthSummerReportBean(catogeryID, materialID_list, strs[4], endDayStr.replaceAll("-", "").substring(0,6));
				materialMap.put(materialID_list, materialBean);
			}
			materialBean = materialMap.get(materialID_list);
			materialBean.setoCount(materialBean.getoCount()+StringUtil.toDouble(strs[0]));
			materialBean.setoSum(materialBean.getoSum()+StringUtil.toDouble(strs[1]));
			
		}
		
		//处理每个物资的结余期初数据
		
		List<SqlParams> paramList_material = new ArrayList<SqlParams>();
		it = materialMap.keySet().iterator();
		
		while(it.hasNext()){
			key = it.next();
			materialBean = materialMap.get(key);
			double ecount = materialBean.getbCount()+materialBean.getiCount()-materialBean.getoCount();
			double esum = materialBean.getbSum()+materialBean.getiSum()-materialBean.getoSum();
			materialBean.seteCount(ecount);
			materialBean.seteSum(esum);
			params = new SqlParams();
			params.setStr0(materialBean.getCategoryID());
			params.setStr1(materialBean.getbCount()+"");
			params.setStr2(materialBean.getbSum()+"");
			params.setStr3(materialBean.getiCount()+"");
			params.setStr4(materialBean.getiSum()+"");
			params.setStr5(materialBean.getoCount()+"");
			params.setStr6(materialBean.getoSum()+"");
			params.setStr7(materialBean.geteCount()+"");
			params.setStr8(materialBean.geteSum()+"");
			params.setStr9(materialBean.getYearMonth());
			params.setStr10(houseID);
			params.setStr11(materialBean.getMaterialID());
			params.setStrCount(12);
			paramList_material.add(params);
		}
		
		
		
		
		
		
		//期末结存,初始化更新参数
		
		List<SqlParams> paramList = new ArrayList<SqlParams>();
		
		it = summerMap.keySet().iterator();
		
		while(it.hasNext()){
			key = it.next();
			summerBean = summerMap.get(key);
			double ecount = summerBean.getbCount()+summerBean.getiCount()-summerBean.getoCount();
			double esum = summerBean.getbSum()+summerBean.getiSum()-summerBean.getoSum();
			summerBean.seteCount(ecount);
			summerBean.seteSum(esum);
			params = new SqlParams();
			params.setStr0(summerBean.getCategoryID());
			params.setStr1(summerBean.getbCount()+"");
			params.setStr2(summerBean.getbSum()+"");
			params.setStr3(summerBean.getiCount()+"");
			params.setStr4(summerBean.getiSum()+"");
			params.setStr5(summerBean.getoCount()+"");
			params.setStr6(summerBean.getoSum()+"");
			params.setStr7(summerBean.geteCount()+"");
			params.setStr8(summerBean.geteSum()+"");
			params.setStr9(summerBean.getYearMonth());
			params.setStr10(houseID);
			params.setStrCount(11);
			paramList.add(params);
		}
	
		preStatement.updateListData("report_07", paramList);
		preStatement.updateListData("report_27", paramList_material);
		params.setStr0(preYearMon.replaceAll("-", "").substring(0, 6));
		params.setStr1(houseID);
		params.setStrCount(2);
		preStatement.updateData("report_10", params);
		
		//写入当月的每个库存
		//datas_rec
		Map<String,StroreCountBean> map_store = new HashMap<String, StroreCountBean>();
		String materialID = "";
		
		StroreCountBean stroreBean = null;
		for(String temp : datas_rec){
			strs = temp.split("\\|");
			materialID = strs[7];
			stroreBean = map_store.get(materialID);
			if(stroreBean==null){
				map_store.put(materialID, new StroreCountBean());
			}
			stroreBean = map_store.get(materialID);
			stroreBean.setSupplyString(strs[3]);
			stroreBean.setMaterialName(strs[4]);
			stroreBean.setMaterialStyle(strs[5]);
			stroreBean.setMaterialSize(strs[6]);
			stroreBean.setMaterialID(materialID);
			stroreBean.setCategoryID(strs[2]);
			stroreBean.setCategoryFID(map.get(strs[2]).getCatoryFID());
			stroreBean.setMaterialCount(stroreBean.getMaterialCount()+Double.parseDouble(strs[0]));
			stroreBean.setSum(stroreBean.getSum()+Double.parseDouble(strs[1]));
		}
		
		for(String temp : datas_req){
			strs = temp.split("\\|");
			materialID = strs[7];
			stroreBean = map_store.get(materialID);
			if(stroreBean==null){
				map_store.put(materialID, new StroreCountBean());
			}
			stroreBean = map_store.get(materialID);
			if(stroreBean.getSupplyString()==null)
			stroreBean.setSupplyString(strs[3]);
			stroreBean.setMaterialName(strs[4]);
			stroreBean.setMaterialStyle(strs[5]);
			stroreBean.setMaterialSize(strs[6]);
			stroreBean.setMaterialID(materialID);
			stroreBean.setCategoryID(strs[2]);
			stroreBean.setCategoryFID(map.get(strs[2]).getCatoryFID());
			stroreBean.setMaterialCount(stroreBean.getMaterialCount()-Double.parseDouble(strs[0]));
			stroreBean.setSum(stroreBean.getSum()-Double.parseDouble(strs[1]));
			
		}
	
		params.setStr0(materialID);
		params.setStr1(preYearMon);
		params.setStrCount(2);
		datas = preStatement.getTabeData("report_18", params);
		for(String temp : datas){
			strs = temp.split("\\|");
			materialID = strs[7];
			stroreBean = map_store.get(materialID);
			if(stroreBean==null){
				map_store.put(materialID, new StroreCountBean());
			}
			stroreBean = map_store.get(materialID);
			if(stroreBean.getSupplyString()==null)
			stroreBean.setSupplyString(strs[3]);
			stroreBean.setMaterialName(strs[4]);
			stroreBean.setMaterialStyle(strs[5]);
			stroreBean.setMaterialSize(strs[6]);
			stroreBean.setMaterialID(materialID);
			stroreBean.setCategoryID(strs[2]);

			stroreBean.setCategoryFID(map.get(strs[2]).getCatoryFID());
			stroreBean.setMaterialCount(stroreBean.getMaterialCount()+Double.parseDouble(strs[0]));
			stroreBean.setSum(stroreBean.getSum()+Double.parseDouble(strs[1]));
		}
		
		List<SqlParams> paramList2 = new ArrayList<SqlParams>();
		it = map_store.keySet().iterator();
		double price = 0 , tempd = 0;
		while(it.hasNext()){
			key = it.next();
			stroreBean = map_store.get(key);
			params = new SqlParams();
			
			params.setStr0(stroreBean.getSupplyString());
			params.setStr1(stroreBean.getMaterialName());
			params.setStr2(stroreBean.getMaterialStyle());
			params.setStr3(stroreBean.getMaterialCount()+"");
			
			params.setStr4(stroreBean.getMaterialSize()+"");
			if(stroreBean.getMaterialCount()==0){
				price = 0;
				continue;
			}else{
				tempd = Double.parseDouble(StringUtil.formatDouble2(stroreBean.getSum()));
				price = tempd/stroreBean.getMaterialCount();
			}
			
			params.setStr5(StringUtil.formatDouble6(price));
			params.setStr6(stroreBean.getSum()+"");
			params.setStr7(stroreBean.getCategoryID());
			params.setStr8(stroreBean.getCategoryFID());
			params.setStr9(endDayStr.replaceAll("-", "").substring(0,6));
			params.setStr10(stroreBean.getMaterialID());
			params.setStr11(houseID);
			params.setStrCount(12);
			paramList2.add(params);
		}
		preStatement.updateListData("report_19", paramList2);
		
		
		
	}
	
	private void changeBean(MCategoryBean tbean,MCategoryBean tbean2,Map<String, MCategoryBean> map){
		MCategoryBean temp = map.get(tbean2.getCatoryFID());
		if("-1".equals(temp.getCatoryFID())){
			tbean.setCatoryFID(temp.getCatoryID());
			temp.addMCategoryBean(tbean);
		}else{
			changeBean(tbean,temp,map);
		}
	}
	
	private void createDataAndFile(){
		
	}
	
	private void copyListStr(List<String> src,List<String> dect){
		for(String str : src){
			dect.add(str);
		}
		
	}
	
	
	
	
	
	
	
}
