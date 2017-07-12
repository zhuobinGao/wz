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
		  <li class="active">分类管理</li>
		</ol>
    	
    	
    	<form class="form-inline" role="form">
    		
    		
    		
    		
    		
    		
    		<button type="button" class="btn btn-primary"><span class="glyphicon glyphicon-search"></span>&nbsp;&nbsp;查询</button>
    		
    		<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myAddModal">
    			<span class="glyphicon glyphicon-pencil"></span>&nbsp;&nbsp;新增
    		</button>
    		
    		<button type="button" class="btn btn-primary" id="openEdit">
    			<span class="glyphicon glyphicon-edit"></span>&nbsp;&nbsp;修改
    		</button>
    		
    		<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModalDel" id="delEdit">
    			<span class="glyphicon glyphicon-remove"></span>&nbsp;&nbsp;删除
    		</button>
    		
    	</form>
    	
    	<hr>
    	
    	
    	<div class="row">
    		<div class="col-md-12">
    		
    			<table id="example"  class="display nowrap tableExample" cellspacing="0" width="100%" style="margin-top:10px;">
                        <thead>
                                <tr>
                                	<th>选择</th>
                                	<th>类别ID</th>
                                    <th>类别名称</th>
                                  	
                                    <th>一级类别</th>
                                    <th>二级类别</th>
                                    <th>上级类别名称</th>
                                 	<th>上级类别ID</th>
                                </tr>
                        </thead>
                        
                        <tfoot>
                        	<tr>
                        			<th>选择</th>
                        			<th>类别ID</th>
                                    <th>类别名称</th>
                                  	
                                    <th>一级类别</th>
                                    <th>二级类别</th>
                                    <th>上级类别名称</th>
                                    <th>上级类别ID</th>
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
                            	</tr>
                        </tbody>
        
                </table>
                
    		</div>
    	
    	</div>

    </div>
    
    
    
   
     
   
   
   
   <!--模态框-->
   
   <div class="modal fade " id="myAddModal"  tabindex="-1"  role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog ">
    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
	        <h4 class="modal-title" id="myModalLabel">添加船舶资料</h4>
	        <div class="alert alert-danger hidden" id="i_display" role="alert">.111111</div>
	      </div>
	      
	      <div class="modal-body">
	        	<form class="form-horizontal" role="form" id="addForm">
	        		<div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">类别名称</label>
					    <div class="col-sm-10">
					      <input type="text" class="form-control" id="ins_cname" placeholder="类别名称">
					    </div>
					</div>
					
					<div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">类别级别</label>
					    <div class="col-sm-10">
					     	
							    <input type="radio" name="optionsRadios" id="jb1" value="1" checked>
							           一级类别
							    <input type="radio" name="optionsRadios" id="jb2" value="2" >
							           二级类别
							
					    </div>
					</div>
					
					<div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">上级类别</label>
					    <div class="col-sm-10">
					      <select class="form-control myselect" id="add_fcid" disabled>
							  
						  </select>
					    </div>
					</div>
	        	
	        	</form>
	        	
	        
	      </div>
	      
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
	        <button type="button" class="btn btn-primary" id="a_sure">确认添加</button>
	      </div>
    </div>
  </div>
</div>
   
   
   
   
    <div class="modal fade " id="myEditModal"  tabindex="-1"  role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog ">
    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
	        <h4 class="modal-title" id="myModalLabel">编辑船舶资料</h4>
	        <div class="alert alert-danger hidden" id="u_display" role="alert">.111111</div>
	      </div>
	      
	      <div class="modal-body">
	        	<form class="form-horizontal" role="form" id="updateForm">
	        		<div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">类别名称</label>
					    <div class="col-sm-10">
					      <input type="email" class="form-control" id="upd_cname" placeholder="类别名称">
					    </div>
					</div>
					
					<div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">类别级别</label>
					    <div class="col-sm-10">
					     	
							    <input type="radio" name="optionsRadios2" id="optionsRadios3" value="1" >
							           一级类别
							    <input type="radio" name="optionsRadios2" id="optionsRadios4" value="2" >
							           二级类别
							
					    </div>
					</div>
					
					<div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">上级类别</label>
					    <div class="col-sm-10">
					      <select class="form-control myselect" id="upd_fcid" disabled>
							  
						  </select>
					    </div>
					</div>
	        	
	        	</form>
	        	
	        
	      </div>
	      
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
	        <button type="button" class="btn btn-primary" id="u_save">保存</button>
	      </div>
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
	        	确认删除该信息？
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
	        <button type="button" class="btn btn-primary" id="d_save" >确认删除</button>
	      </div>
    </div>
  	</div>
 </div>
   
   
   
   
   
   
   
   
   
  
  
  <input type="hidden" id="i_cgs" value="">
  <input type="hidden" id="s_selectID" value="">
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
  
   
  
  
 
   <script src="<%=basePath %>/page/page/asset/js/hardcategory.js"></script>
  
  
</html>
