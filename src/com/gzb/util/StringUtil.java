package com.gzb.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

public class StringUtil {

	
	static java.text.DecimalFormat   df6   =new   java.text.DecimalFormat("0.000000");  
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private static DecimalFormat df2 = new DecimalFormat("#######0.0000000");
	private static DecimalFormat df3 = new DecimalFormat("#######0.00");
	
	
	public static String trimRequest(HttpServletRequest request, String str){
		if("".equals(str))return "";
		if("undefined".equals(str))
			return "";
		return StringUtil.nullToEmpty(request.getParameter(str));
	}
	
	public static boolean isEmptyFromRequest(HttpServletRequest request, String str){
		String str1 = StringUtil.nullToEmpty(request.getParameter(str));
	
		if("undefined".equals(str1))
			return true;
		
		return "".equals(str1);
	}
	
	
	public static String getFormatDate(Date date){
		return sdf.format(date);
	}
	
	public static String formateNumber6(double d){
		
		if(Math.abs(d)<0.0000001){
			return "0";
		}
		
		String str = df6.format(d);
		
		if(str.endsWith("0")){
			str = str.substring(0, str.length()-1);
		}
		if(str.endsWith("0")){
			str = str.substring(0, str.length()-1);
		}
		if(str.endsWith("0")){
			str = str.substring(0, str.length()-1);
		}
		if(str.endsWith("0")){
			str = str.substring(0, str.length()-1);
		}
		
		
		
		return str;
	}

	
	public static String nullToEmpty(String str){
		return str==null?"":str.trim().replace("|", "");
	}
	
	public static String nullToEmptyAppend(String str){
		return ((str==null||str.equals(""))?"null":str.trim().replace("|", ""))+"|";
	}

	public static boolean isEmpty(String str){
		if(str==null)return true;
		if("".equals(str)){return true;}
		return false;
	}
	
	public static double toDouble(String str){
		return Double.valueOf(str);
	}
	
	
	public static String getToday(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(new Date());
	}
	
	public static String getToday(String sdfString){
		SimpleDateFormat sdf = new SimpleDateFormat(sdfString);
		return sdf.format(new Date());
	} 
	
	public static String formatDouble6(double d){
		return df2.format(d);
	}
	
	public static String formatDouble2(double d){
		return df3.format(d);
	}
	
	public static void main(String[] args){
		
//		double d = 0.000000000001;
//		String dd = formateNumber6(d);
//		
//		System.out.println(getToday());
		System.out.println("Hellow XuAi!");
		
	System.out.println(	formatDouble2(.2) );
		
	}
	
	
}
