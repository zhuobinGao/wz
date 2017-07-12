package com.cangku.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cangku.controler.UserAndPermissionControler;
import com.gzb.db_source.ConnectionManager;
import com.gzb.util.EncrypDES;
import com.gzb.util.StringUtil;

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 6507987223909595459L;

	UserAndPermissionControler controler = new UserAndPermissionControler();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
				
		this.doPost(request, response);

	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println(request.getAttribute("username")+"|"+request.getParameter("username"));
		
		String username = request.getParameter("username")==null? (String)request.getAttribute("username") :request.getParameter("username");
		String pwd = request.getParameter("pwd")==null? (String)request.getAttribute("pwd") :request.getParameter("pwd");
		
		String encPwd = EncrypDES.getenCord(pwd);
		
		String dbpwd = "";
		
		String loginName="",storehouse_id="",storehouse_name="",groupid="",groupname="";
		
		
		
		
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		String sql = "select user_pwd ,c.user_name,c.storehouse_id,s.storehouse_name,g.groupid,g.groupname from bmwz_user c "
					+"left join bmwz_storehouse s on s.storehouse_id=c.storehouse_id "
					+"left join bmwz_group g on g.groupid= c.group_id where user_id=? ";
		
		try{
			conn = ConnectionManager.getInstance().getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, username);
		
			rs = pstm.executeQuery();
			if(rs.next()){
				dbpwd = StringUtil.nullToEmpty(rs.getString("user_pwd"));
				loginName = StringUtil.nullToEmpty(rs.getString("user_name"));
				storehouse_id = StringUtil.nullToEmpty(rs.getString("storehouse_id"));
				storehouse_name = StringUtil.nullToEmpty(rs.getString("storehouse_name"));
				groupid = StringUtil.nullToEmpty(rs.getString("groupid"));
				groupname = StringUtil.nullToEmpty(rs.getString("groupname"));
				
				
			}
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
		
		List<String> groupList =  controler.getPermissionList(groupid);
		
		HttpSession session = request.getSession();
		if(dbpwd.equals(encPwd)){
			
			session.setAttribute("cangKuID", storehouse_id);
			
			session.setAttribute("userID", username);
			session.setAttribute("userName", loginName);
			session.setAttribute("cangKuName", storehouse_name);
			session.setAttribute("groupID", groupid);
			session.setAttribute("groupName", groupname);
			session.setAttribute("groupList", groupList);
			
			session.setAttribute("mes", "");
		
			
			response.sendRedirect("page/index.jsp");   
			
		    
		    return;

			
		}else{
			session.setAttribute("mes", "输入用户名或密码错误!");
		    response.sendRedirect("login/index.jsp");
		    System.out.println("======else=======");
		    return;
		}
		
		
		
	}

}
