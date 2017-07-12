package com.cangku.servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cangku.controler.AjaxControler;

public class AjaxServlet extends HttpServlet {

	
	private static final long serialVersionUID = 2169857945189651737L;


	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");  
      
		
		String dataType = request.getParameter("dataType");
		
		
		AjaxControler controler  = new AjaxControler();
		
		String mes = "";
		
		System.out.println("========>>>"+dataType);
		
		if("checkStore".equals(dataType)){
			mes = controler.getCheckStore(request, response);
		}
		
		if("checkMaterial".equals(dataType)){
			mes = controler.onSearchMaterial(request, response);
//			controler.showParams(request);
		}
		
		if("checkStoreLine".equals(dataType)){
			mes = controler.checkStoreLine(request, response);
		}
		
		if("bmwzSummer".equals(dataType)){
			mes = controler.getSummerMes(request, response);
		}
		
		if("bmwzCountChart".equals(dataType)){
			mes = controler.getYearCount(request, response);
		}
		if("bmwzMoneyChart".equals(dataType)){
			mes = controler.getYearMoney(request, response);
		}
		
		if("bmwzCountMoneyChart".equals(dataType)){
			mes = controler.getYearCountMoney(request, response);
		}
		
		System.out.println(dataType);
		
		
//		
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
		OutputStream osTxt = response.getOutputStream();
		osTxt.write(mes.getBytes("utf-8"));
		
		osTxt.flush();
		
		System.out.println("=======over========"+new Random().nextInt(9999));
		
		
		
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}
	
	

}
