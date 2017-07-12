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
    		
    		
    		
    		
    		
    		
    		<button type="button" class="btn btn-primary"><span class="glyphicon glyphicon-search"></span>&nbsp;&nbsp;查询</button>
    		
    		<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myAddModal">
    			<span class="glyphicon glyphicon-pencil"></span>&nbsp;&nbsp;新增
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
                                    <th>状态类别编号</th>
                                  	
                                    <th>状态类别</th>
                                    <th>状态名称</th>
                                    
                                    
                                    
                                    
                                </tr>
                        </thead>
                        
                        <tfoot>
                        	<tr>
                        			<th>选择</th>
                                    <th>状态类别编号</th>
                                  	
                                    <th>状态类别</th>
                                    <th>状态名称</th>
                                </tr>
                        
                        </tfoot>
                        
                        <tbody>
                            	<tr>
                            		<td></td>
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
					    <label for="inputEmail3" class="col-sm-2 control-label">状态类别</label>
					    <div class="col-sm-10">
					      <select class="form-control myselect" id="add_ztlb" >
							  <option value='1000'>故障类型</option>
							  <option value='2000'>维修方法</option>
							  <option value='3000'>维修结果</option>
							  <option value='4000'>固资状态</option>
						  </select>
					    </div>
					</div>
					
					<div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">状态名称</label>
					    <div class="col-sm-10">
					      <input type="text" class="form-control" id="ins_cname" placeholder="状态名称">
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
   
   
   
   
    <div class="modal fade bs-example-modal-lg" id="myEditModal"  tabindex="-1"  role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
	        <h4 class="modal-title" id="myModalLabel">添加船舶资料</h4>
	        <div class="alert alert-danger hidden" id="u_display" role="alert">.111111</div>
	      </div>
	      
	      <div class="modal-body">
	        	<form class="form-horizontal" role="form" id="updateForm">
	        		<div class="form-group">
					    <label for="u_cbdm" class="col-sm-2 control-label">船舶代码<span class="text-danger">(*)</span></label>
					    <div class="col-sm-3">
					      <input type="text" class="form-control" id="u_cbdm" placeholder="船舶代码">
						</div>
						
						<label for="u_cbzwm" class="col-sm-2 control-label">船舶中文名<span class="text-danger">(*)</span></label>
					    <div class="col-sm-3">
					      <input type="text" class="form-control" id="u_cbzwm" placeholder="船舶中文名">
						</div>
						
					</div>
					
					<div class="form-group">
						 <label for="inputtext3" class="col-sm-2 control-label">船舶类型<span class="text-danger">(*)</span></label>
					    <div class="col-sm-3">
					      <select id="u_chlx" class="form-control">
					      		<option value="S">驳船</option>
					      		<option value="B">大船</option>
					      </select>
						</div>
					
					    <label for="u_cgs" class="col-sm-2 control-label">船公司</label>
					    <div class="col-sm-3">
					      <input type="text" class="form-control" id="u_cgs" placeholder="船公司">
						</div>
						
						
						
					</div>
					
					<div class="form-group">
					    <label for="u_csc" class="col-sm-2 control-label">船身长</label>
					    <div class="col-sm-3">
					      <input type="text" class="form-control" id="u_csc" placeholder="船身长" value="0">
						</div>
						
						<label for="u_gd" class="col-sm-2 control-label">高度</label>
					    <div class="col-sm-3">
					      <input type="text" class="form-control" id="u_gd" placeholder="高度" value="0">
						</div>
						
					</div>
					
					<div class="form-group">
					    <label for="u_kd" class="col-sm-2 control-label">宽度</label>
					    <div class="col-sm-3">
					      <input type="text" class="form-control" id="u_kd" placeholder="宽度" value="0">
						</div>
						
						<label for="u_zz" class="col-sm-2 control-label">总重</label>
					    <div class="col-sm-3">
					      <input type="text" class="form-control" id="u_zz" placeholder="总重" value="0">
						</div>
						
					</div>
					
					<div class="form-group">
					    <label for="u_jz" class="col-sm-2 control-label">净重</label>
					    <div class="col-sm-3">
					      <input type="text" class="form-control" id="u_jz" placeholder="净重" value="0">
						</div>
						
						<label for="u_cr" class="col-sm-2 control-label">舱容</label>
					    <div class="col-sm-3">
					      <input type="text" class="form-control" id="u_cr" placeholder="舱容" value="0">
						</div>
						
					</div>
					
					<div class="form-group">
					    <label for="u_bz" class="col-sm-2 control-label">备注</label>
					    <div class="col-sm-3">
					      <input type="text" class="form-control" id="u_bz" placeholder="备注">
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
  
   
  
  
 
   <script src="<%=basePath %>/page/page/asset/js/commonMes.js"></script>
  
  
</html>
