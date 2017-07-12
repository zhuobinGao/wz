package com.cangku.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cangku.controler.UserAndPermissionControler;

public class UserFilter implements Filter {

	
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		 HttpServletRequest res=(HttpServletRequest) request;  

	        HttpServletResponse resp=(HttpServletResponse)response;  

	        HttpSession session = res.getSession();
	        String path = res.getContextPath();
        	String basePath = res.getScheme()+"://"+res.getServerName()+":"+res.getServerPort()+path+"/";
        	
        
        	
        	String uri = res.getRequestURI();
        	
        	System.out.println(uri);
        	
        	boolean flag = false;
	        
	        if(session==null || !checkSessionVaild(session)){  
	        	resp.sendRedirect(basePath+"login/index.jsp"); 
	        	return;
	        }  
	        
	        String groupId = (String)session.getAttribute("groupid");
	        
	        if( uri.endsWith(".jsp") && !uri.equals("/CangKu/page/index.jsp") ){
	        	
	        	List<String> list = (List<String>)session.getAttribute("groupList");
	        	flag = false;
	        	System.out.println("uri="+uri);
	        	for(String str : list){
	        		
	        		System.out.println(str+"="+uri);
	        		
	        		if(uri.endsWith(str)){
	        			
	        			flag = flag|true;
	        		}
	        	}
	        	
	        
	        	System.out.println("============>"+flag);
	        	
	        	if(!flag){
	        		resp.sendRedirect(basePath+"page/noPermission.html"); 
	        		return;
	        	}
	        	
	        }
	        
	       
	    
	        chain.doFilter(res, resp);  

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}
	
	private boolean checkSessionVaild(HttpSession session){
		
		
		if(session.getAttribute("cangKuID")==null){
			return false;
		}
		
		if(session.getAttribute("userID")==null){
			return false;
		}
		
		if(session.getAttribute("userName")==null){
			return false;
		}
		
		if(session.getAttribute("cangKuName")==null){
			return false;
		}
		
		if(session.getAttribute("groupID")==null){
			return false;
		}
		
		if(session.getAttribute("groupName")==null){
			return false;
		}
		
		if(session.getAttribute("groupList")==null){
			return false;
		}
		
		
		session.setAttribute("mes", "登录超时!");
		
		
		return true;
	}
	

}
