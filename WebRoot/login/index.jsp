<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page language="java" import="com.gzb.util.StringUtil" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

session.removeAttribute("cangKuID");
session.removeAttribute("userID");
session.removeAttribute("userName");
session.removeAttribute("cangKuName");
session.removeAttribute("groupID");
session.removeAttribute("groupName");

session.removeAttribute("groupList");

%>
<!DOCTYPE html>
<html lang="en" data-ng-app="materialAdmin" class="login-content ng-scope">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">



  <meta charset="UTF-8"> 
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="Generator" content="EditPlus®">
  <meta name="Author" content="">
  <meta name="Keywords" content="">
  <meta name="Description" content="">
  <title>仓库云</title>
 


<link rel="stylesheet" type="text/css" href="index.css" media="all">

<style type="text/css">
	body.login-content::before{
		background:#1b244b;
	}

</style>


</head>



<body class="login-content ng-scope" data-ng-controller="loginCtrl as lctrl">






	
	   <div class="lc-block toggled" id="l-login" data-ng-class="{'toggled':lctrl.login === 1}">
	   	
	   	<h1 class="lean"><b>仓库云</b></h1>
		<form action="../Login" method="post">
		   	<div class="input-group m-b-20">
		   		<span class="input-group-addon">
		   			<i class="zmdi zmdi-account"></i>
		   		</span>
		   		<div class="fg-line">
		   			<input value="" class="form-control" id="username" name="username" placeholder="用户名" regex="^\w{3,16}$" type="text">
		   		</div>
		   	</div>
		
		       <div class="input-group m-b-20">
		   		<span class="input-group-addon">
		   			<i class="zmdi zmdi-male"></i>
		   		</span>
		   		<div class="fg-line">
		   			<input class="form-control" id="pwd" name="pwd" placeholder="密 码" regex="^\w+" type="password">
		   		</div>
		   	</div>
		   	
		   	<div class="clearfix"></div>
		   	
		   	<div class="checkbox">
		   		<label>
		   			<input value="" type="checkbox">
		   			<i class="input-helper">
		   				记住我&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		   				<span style="color:red;"><%=StringUtil.nullToEmpty((String)session.getAttribute("mes")) %></span>
		   			</i>
		   		</label>
		   	</div>
		       
		   	<button type="submit" indepth="true" id="loginButton" class="btn btn-login btn-danger btn-float">
		   		<i class="zmdi zmdi-arrow-forward"></i>
		   	</button>
		       
		    <ul class="login-navigation">
		       	<li class="bgm-red" data-ng-click="lctrl.login = 0; lctrl.register = 1">注册</li>
		       	<li data-block="#l-forget-password" class="bgm-orange" data-ng-click="lctrl.login = 0; lctrl.forgot = 1">忘记密码</li>
		       </ul>
		
		 </form>
	   
	   </div>
		   
    
    <!-- ngIf: lctrl.register === 1 -->

    <!-- ngIf: lctrl.forgot === 1 -->
 
 	
 	
	
	<!-- Angular -->
	
	
	
	
	
	<!-- Angular Modules -->
	
	
	
	
	
	<!-- Common js -->
	
	
	
	<!-- Placeholder for IE9 -->
	<!--[if IE 9 ]>
	    <script src="js/bower_components/jquery-placeholder/jquery.placeholder.min.js"></script>
	<![endif]-->
	<!-- App level -->
	
	
	
	
	
	<!-- Template Modules -->
	




</body>
<script src="js/jquery-1.11.3.min.js"></script>

<script text="text/javascript">
	
	


</script>










</html>
