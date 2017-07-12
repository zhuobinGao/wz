<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    
    <title>My JSP 'temple.jsp' starting page</title>
    
   
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	
	<link rel="stylesheet" type="text/css" href="<%=basePath%>page/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>page/css/jquery.mmenu.all.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/jquery.dataTables.min.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/buttons.dataTables.min.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/select.dataTables.min.css">
	
	<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="<%=basePath %>page/js/html5shiv.min.js"></script>
      <script src="<%=basePath %>page/js/respond.min.js"></script>
    <![endif]-->
	
	<link rel="stylesheet" href="<%=basePath%>css/bootstrap-datetimepicker.min.css" media="screen">
	<link rel="stylesheet" href="<%=basePath%>css/jquery-ui.min.css" media="screen">
	
	
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/mySkin.css?v=1.0">
	
	<style type="text/css">
.mm-slideout{
	    transition: -webkit-transform .4s ease;
    transition: transform .4s ease;
    transition: transform .4s ease,-webkit-transform .4s ease;
    z-index:inherit;
    
}
tfoot input {
        width: 100%;
        padding: 3px;
        box-sizing: border-box;
    }
    .ui-front {
    z-index: 2000;
}
#project-label {
    display: block;
    font-weight: bold;
    margin-bottom: 1em;
  }
  #project-icon {
    float: left;
    height: 32px;
    width: 32px;
  }
  #project-description {
    margin: 0;
    padding: 0;
  }
	</style>
	
  </head>
  
  <body>
   
 
 
 
 
    
    <div class="container-fluid" >
    	<ol class="breadcrumb" style="margin-top:20px;">
		  <li><a >首页</a></li>
		  <li><a >固定资产管理</a></li>
		  <li class="active">状态列表</li>
		</ol>
    	
    	
    	<form class="form-inline" role="form">
    		
    		
    		
    		
    		
    		
    		<button type="button" class="btn btn-primary" id="s_search"><span class="glyphicon glyphicon-search"></span>&nbsp;&nbsp;查询</button>
    		
    		<button type="button" class="btn btn-primary" id="s_open">
    			<span class="glyphicon glyphicon-pencil"></span>&nbsp;&nbsp;开通
    		</button>
    		
    		
    		
    		<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModalDel" id="delEdit">
    			<span class="glyphicon glyphicon-remove"></span>&nbsp;&nbsp;关闭
    		</button>
    		
    	</form>
    	
    	<hr>
    	
    	
    	<div class="row">
    		<div class="col-md-12">
    		
    			<table id="example"  class="display nowrap tableExample" cellspacing="0" width="100%" style="margin-top:10px;">
                        <thead>
                                <tr>
                                	<th>选择</th>
                                	<th>ID</th>
                                    <th>微信账号</th>
                                  	
                                    <th>申请人</th>
                                    <th>申请时间</th>
                                    <th>预警单位</th>
                                    <th>预警金额</th>
                                    <th>是否开通</th>
                                    
                                    <th>审批人</th>
                                    <th>审批时间</th>
                                    <th>关闭人</th>
                                    <th>关闭时间</th>
                                    <th>消息发送次数</th>
                                    
                                    <th>最后发送时间</th>
                                    
                                    
                                    
                                    
                                </tr>
                        </thead>
                        
                        <tfoot>
                        	<tr>
                        			<th>选择</th>
                        			<th>ID</th>
                                    <th>微信账号</th>
                                  	
                                    <th>申请人</th>
                                    <th>申请时间</th>
                                    <th>预警单位</th>
                                    <th>预警金额</th>
                                    <th>是否开通</th>
                                    <th>审批人</th>
                                    <th>审批时间</th>
                                    
                                    <th>关闭人</th>
                                    <th>关闭时间</th>
                                    <th>消息发送次数</th>
                                    <th>最后发送时间</th>
                                    
                                </tr>
                        
                        </tfoot>
                        
                        <tbody>
                            	<tr>
                            		<td></td>
                            		<td>1</td>
                            		<td>1</td>
                            		
                            		<td>1</td>
                            		<td>1</td>
                            		<td>1</td>
                            		<td>1</td>
                            		<td>1</td>
                            		
                            		<td>1</td>
                            		<td>1</td>
                            		<td>1</td>
                            		<td>1</td>
                            		<td>1</td>
                            		
                            		<td>1</td>
                            	</tr>
                        </tbody>
        
                </table>
                
    		</div>
    		
    		 	
    	</div>

    </div>
    
    
    
   
     
   
   
   
  
    
    <div class="modal fade " id="myDelModal"  tabindex="-1"  role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog ">
    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
	        <h4 class="modal-title" id="myModalLabel">删除客户信息</h4>
	      </div>
	      
	      <div class="modal-body">
	        	确认取消该预警功能？
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
	        <button type="button" class="btn btn-primary" id="d_save" >确认取消</button>
	      </div>
    </div>
  	</div>
 </div>
   
   
   
   
   
   
   
   
   
  
  
  <input type="hidden" id="i_cgs" value="">
  <input type="hidden" id="u_cgs" value="">
  </body>
  
  <script src="<%=basePath %>page/js/jquery-1.12.1.min.js"></script>
   <script src="<%=basePath %>page/js/bootstrap.min.js"></script>
   <script src="<%=basePath %>js/jquery-ui.min.js"></script>
   <script src="<%=basePath %>page/js/jquery.mmenu.all.min.js"></script>
   
   <script src="<%=basePath %>js/jquery.dataTables.min.js"></script>
   
   <script src="<%=basePath %>js/bootstrap-datetimepicker.min.js"></script>
   <script src="<%=basePath %>js/bootstrap-datetimepicker.fr.js"></script>
  
   <script src="<%=basePath %>js/exportJS/dataTables.buttons.min.js"></script>
   <script src="<%=basePath %>js/exportJS/buttons.flash.min.js"></script>
   <script src="<%=basePath %>js/exportJS/jszip.min.js"></script>
   <script src="<%=basePath %>js/exportJS/pdfmake.min.js"></script>
   <script src="<%=basePath %>js/exportJS/vfs_fonts.js"></script>
   <script src="<%=basePath %>js/exportJS/buttons.html5.min.js"></script>
   <script src="<%=basePath %>js/exportJS/buttons.print.min.js"></script>
   
   <script src="<%=basePath %>js/dataTables.select.min.js"></script>
  
   
  
  
 
   <script src="<%=basePath %>/page/page/weixin/js/applyPreList.js?v=1.3"></script>
  
  
</html>
