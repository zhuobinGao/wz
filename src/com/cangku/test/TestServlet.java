package com.cangku.test;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class TestServlet extends HttpServlet {

	private static final long serialVersionUID = 9112555807066923652L;

	 
    private static Logger logger = Logger.getLogger(TestServlet.class); 
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
		
		 System.out.println("===============================");
		 // ��¼debug�������Ϣ    
        logger.debug("This is debug message.");    
        // ��¼info�������Ϣ    
        logger.info("This is info message.");    
        // ��¼error�������Ϣ    
        logger.error("This is error message."); 
        new Date().getTime();
       
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 // ��¼debug�������Ϣ    
        logger.debug("This is debug message.");    
        // ��¼info�������Ϣ    
        logger.info("This is info message.");    
        // ��¼error�������Ϣ    
        logger.error("This is error message."); 
        System.out.println("===============================");
	}

}
