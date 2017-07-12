package com.gzb.api.servlet;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gzb.api.controler.HisDataSearch;
import com.gzb.api.controler.PreMindSearch;



public class DataRequestServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2096068872369802503L;


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HisDataSearch controler1 = new HisDataSearch();
		PreMindSearch precontroler = new PreMindSearch();
		String path = request.getParameter("param");
		
		
		System.out.println("path:"+path);
		
		String result = "";
		switch (path) {
		case "searchInHis":
			result = controler1.getReceiveHisList(request, response);
			break;
		case "searchOutHis":
			result = controler1.getRequestListHis(request, response);
			break;
		case "searchIn":
			result = controler1.getReceiveList(request, response);
			break;
		case "searchOut":
			result = controler1.getRequestList(request, response);
			break;
		case "searchOutFee":
			result = controler1.getOutFeeItemHis(request, response);
			break;
		case "preList":
			result = precontroler.getPreMindList(request, response);
			break;
		case "openPre":
			result =precontroler.openPreMind(request, response);
			break;
		case "closePre":
			result =precontroler.closePreMind(request, response);
			break;
		default:
			System.out.println("error param");
			break;
		}
		
		System.out.println(result);
		OutputStream os = response.getOutputStream();
		
//		System.out.println(result);
		os.write(result.getBytes("UTF-8"));
		
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

}
