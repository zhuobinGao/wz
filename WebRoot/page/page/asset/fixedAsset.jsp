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
	
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/jquery.dataTables.min.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/dataTables.bootstrap.min.css">
	
	
	<link rel="stylesheet" type="text/css" href="<%=basePath%>page/css/jquery.mmenu.all.css">
	
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
  .mm-slideout{
	    transition: -webkit-transform .4s ease;
    transition: transform .4s ease;
    transition: transform .4s ease,-webkit-transform .4s ease;
    z-index:inherit;
}
table.dataTable thead th, table.dataTable thead td {
    padding: 10px 18px;
    border-bottom: 1px solid #ddd;
}

td.details-control {
    background: url('<%=basePath%>resources/details_open.png') no-repeat center center;
    cursor: pointer;
}
tr.details td.details-control {
    background: url('<%=basePath%>resources/details_close.png') no-repeat center center;
}

	</style>
	
  </head>
  
  <body>
   
 
 
 
 
    
    <div class="container-fluid" >
    	<ol class="breadcrumb" style="margin-top:20px;">
		  <li><a >首页</a></li>
		  <li><a >固定资产管理</a></li>
		  <li class="active">固定资产表</li>
		</ol>
    	
    	
    	
    	
    	<hr>
    	
    	
    	<div class="row">
    		<div class="col-md-7">
    			
    			
    			
    			
    			
    			
    			
    		<div class="panel panel-primary">
    				
    				 <div class="panel-heading">
    				 		固定资产信息
	    				 	<button type="button" class="btn btn-default" id="s_search" style="margin-left:10px;"><span class="glyphicon glyphicon-search"></span>&nbsp;&nbsp;查询</button>
	    		
				    		<button type="button" class="btn btn-default" data-toggle="modal" data-target="#myAddModal">
				    			<span class="glyphicon glyphicon-pencil"></span>&nbsp;&nbsp;新增
				    		</button>
				    		
				    		<button type="button" class="btn btn-default" id="openEdit">
				    			<span class="glyphicon glyphicon-edit"></span>&nbsp;&nbsp;修改
				    		</button>
				    		
				    		<button type="button" class="btn btn-default" data-toggle="modal" data-target="#myModalDel" id="delEdit">
				    			<span class="glyphicon glyphicon-remove"></span>&nbsp;&nbsp;删除
				    		</button>
				    		
				    		<button type="button" class="btn btn-default" id="repareLog" style="margin-left:10px;" data-toggle="modal" >
				    			<span class="glyphicon glyphicon-log-in"></span>&nbsp;&nbsp;维修登记
				    		</button>
				    		
				    		
				    		
				    		<button type="button" class="btn btn-default" id="edituser">
				    			<span class="glyphicon glyphicon-pencil"></span>&nbsp;&nbsp;变更使用方
				    		</button>
				    		
				    		
				    		<button type="button" class="btn btn-default" id="uptlog" >
				    			<span class="glyphicon glyphicon-file"></span>&nbsp;&nbsp;日志信息
				    		</button>
    				 </div>
    		
    				  <div class="panel-body">
    				  		<table id="example"  class="order-column nowrap tableExample table table-striped table-bordered" cellspacing="0" width="100%" style="margin-top:10px;">
                        <thead>
                        		<tr>
					                <th rowspan="2">选择</th>
					                <th colspan="4">系统资料</th>
					                <th colspan="6">固定资产信息</th>
					                <th colspan="6">维保信息</th>
					                <th colspan="4">使用信息</th>
					                
					                <th colspan="4">记录信息</th>
					            </tr>
                        
                        
                        
                                <tr>
                                
                                    
                                    
                                    <th>电脑编号</th>
                                	<th>资产名称</th>
                                    <th>类别</th>
                                    <th>二级类别</th>
                                    
                                    
                                    
                                    <th>固定资产编号</th>
                                    <th>SN码</th>
                                    <th>厂家</th>
                                    <th>型号</th>
                                    <th>销售方</th>
                                    <th>购置日期</th>
                                   
                                   
                                   
                                    <th>保修年限</th>
                                    <th>延保年限</th>
                                    <th>报修联系人</th>
                                    <th>报修电话</th>
                                    <th>是否在保</th>
                                    <th>报废年限</th>
                                    <th>是否在用</th>
                                    
                                    
                                    
                                    <th>使用人</th>
                                    <th>使用地点</th>
                                    <th>使用部门</th>
                                    
                                    <th>创建人</th>
                                    <th>创建时间</th>
                                    <th>更新人</th>
                                    <th>更新时间</th>
                                    
                                </tr>
                        </thead>
                        
                        <tfoot>
                        	<tr>
                        			<th>选择</th>
                                    
                                    
                                    <th>电脑编号</th>
                                	<th>资产名称</th>
                                    <th>类别</th>
                                    <th>二级类别</th>
                                    
                                    
                                    
                                    <th>固定资产编号</th>
                                    <th>SN码</th>
                                    <th>厂家</th>
                                    <th>型号</th>
                                    <th>销售方</th>
                                    <th>购置日期</th>
                                   
                                   
                                   
                                    <th>保修年限</th>
                                    <th>延保年限</th>
                                    <th>报修联系人</th>
                                    <th>报修电话</th>
                                    <th>是否在保</th>
                                    <th>报废年限</th>
                                    <th>是否在用</th>
                                    
                                    <th>使用人</th>
                                    <th>使用地点</th>
                                    <th>使用部门</th>
                                    
                                    <th>创建人</th>
                                    <th>创建时间</th>
                                    <th>更新人</th>
                                    <th>更新时间</th>
                                    
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
    		
    		<div class="col-md-5">
    			
    			
    			<div class="panel panel-primary">
    				 <div class="panel-heading">
    				 	维修信息
    				 	<button type="button" class="btn btn-default" id="followRecord" style="margin-left:10px;" >
		    				<span class="glyphicon glyphicon-send"></span>&nbsp;&nbsp;跟进维修记录
		    			</button>
    				 </div>
    				 
    				  <div class="panel-body">
    				  		<table id="example2"  class="order-column nowrap tableExample table table-striped table-bordered" cellspacing="0" width="100%" style="margin-top:10px;">
			    				<thead>
			    					<tr>
			    						<th></th>
			    						<th>故障日期</th>
			    						<th>故障类型</th>
			    						
			    						
			    						<th>检修人员</th>
			    						<th>是否排除故障</th>
			    						<th>故障排除日期</th>
			    					</tr>
			    				</thead>
			    				<tfoot>
			    					<tr>
			    						<th></th>
			    						<th>故障日期</th>
			    						<th>故障类型</th>
			    						
			    						
			    						<th>检修人员</th>
			    						<th>是否排除故障</th>
			    						<th>故障排除日期</th>
			    					</tr>
			    				</tfoot>
			    				
			    				<tbody></tbody>
			    				
			    			</table>
    				  </div>
    			
    			</div>
    			
    			
    			
    			
    			<div class="panel panel-primary">
  					  <div class="panel-heading">
  					  		硬件信息&nbsp;&nbsp;&nbsp;
					  		<button type="button" class="btn btn-default" id="s_canEdit">
  								<span class="glyphicon glyphicon-edit"></span>&nbsp;&nbsp;修改
  							</button>
  							<button type="button" class="btn btn-default" id="s_isSave">
  								<span class="glyphicon glyphicon-edit"></span>&nbsp;&nbsp;保存
  							</button>
  							
  							
  					  </div>
					  <div class="panel-body">
					  		<form class="form-horizontal" role="form">
					  			<div class="form-group">
								    <label for="inputEmail3" class="col-sm-2 control-label">操作系统</label>
								    <div class="col-sm-4">
								      <input type="text" class="form-control s_compute" id="s_system" placeholder="操作系统" disabled>
								    </div>
								    
								    <label for="inputEmail3" class="col-sm-2 control-label">CPU</label>
								    <div class="col-sm-4">
								      <input type="text" class="form-control s_compute" id="s_cpu" placeholder="CPU" disabled>
								    </div>
								    
								</div>
								
								
								
								<div class="form-group">
								    <label for="inputEmail3" class="col-sm-2 control-label">硬盘</label>
								    <div class="col-sm-4">
								      <input type="text" class="form-control s_compute" id="s_disk" placeholder="硬盘" disabled>
								    </div>
								    
								    <label for="inputEmail3" class="col-sm-2 control-label">显示器</label>
								    <div class="col-sm-4">
								      <input type="text" class="form-control s_compute" id="s_display" placeholder="显示器" disabled>
								    </div>
								</div>
								
								
								
								<div class="form-group">
								    <label for="inputEmail3" class="col-sm-2 control-label">mac地址</label>
								    <div class="col-sm-4">
								      <input type="text" class="form-control s_compute" id="s_mac" placeholder="mac地址" disabled>
								    </div>
								    
								     <label for="inputEmail3" class="col-sm-2 control-label">信息1</label>
								    <div class="col-sm-4">
								      <input type="text" class="form-control s_compute" id="s_mes1" placeholder="信息1" disabled>
								    </div>
								</div>
								
								
								
								<div class="form-group">
								    <label for="inputEmail3" class="col-sm-2 control-label">信息2</label>
								    <div class="col-sm-4">
								      <input type="text" class="form-control s_compute" id="s_mes2" placeholder="信息2" disabled>
								    </div>
								    
								    <label for="inputEmail3" class="col-sm-2 control-label">信息3</label>
								    <div class="col-sm-4">
								      <input type="text" class="form-control s_compute" id="s_mes3" placeholder="信息3" disabled>
								    </div>
								</div>
								
							
					  		
					  		</form>
					  </div>
				</div>
    		
    		<!--  -->
    		</div>
    	
    	</div>
    	
    	

    </div>
    
   <!--模态框-->
   
   <div class="modal fade bs-example-modal-lg" id="myAddModal"  tabindex="-1"  role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
	        <h4 class="modal-title" id="myModalLabel">添加固定资产信息</h4>
	        <div class="alert alert-danger hidden" id="i_display" role="alert">.111111</div>
	      </div>
	      
	      <div class="modal-body">
	      
	        	<form class="form-horizontal" role="form" id="addForm">
	        		
	        		
	        		
	        		<div class="form-group">
					    <label for="i_cbdm" class="col-sm-2 control-label">资产名称<span class="text-danger">(*)</span></label>
					    <div class="col-sm-4">
					      <input type="text" class="form-control" id="i_zcmc" placeholder="资产名称">
						</div>
					</div>
	        	
	        		<div class="form-group">
					    <label for="i_cbdm" class="col-sm-2 control-label">一级类别<span class="text-danger">(*)</span></label>
					    <div class="col-sm-4">
					     	 <select class="form-control myselect" id="i_yjlb" ></select>
						</div>
						
						<label for="i_cbzwm" class="col-sm-2 control-label">二级类别<span class="text-danger">(*)</span></label>
					    <div class="col-sm-4">
					      	 <select class="form-control myselect" id="i_ejlb" ></select>
						</div>
					</div>
					<ul class="nav nav-tabs" role="tablist">
						  <li role="presentation" class="active"><a href="#home1" role="tab" data-toggle="tab">固定资产信息</a></li>
						  <li role="presentation"><a href="#profile1" role="tab" data-toggle="tab">维保信息</a></li>
						  <li role="presentation"><a href="#messages1" role="tab" data-toggle="tab">使用信息</a></li>
					</ul>
					
					<div class="tab-content">
					  <div role="tabpanel" class="tab-pane active" id="home1" style="padding-top:20px">
					  		
					  		<div class="form-group">
							    <label for="i_cbdm" class="col-sm-2 control-label">固定资产编号</span></label>
							    <div class="col-sm-4">
							      <input type="text" class="form-control" id="i_gdzcbm" placeholder="固定资产编号">
								</div>
								
								<label for="i_cbzwm" class="col-sm-2 control-label">SN码</label>
							    <div class="col-sm-4">
							      <input type="text" class="form-control" id="i_snm" placeholder="SN码">
								</div>
							</div>
							
							<div class="form-group">
							    <label for="i_cbdm" class="col-sm-2 control-label">厂家</span></label>
							    <div class="col-sm-4">
							      <input type="text" class="form-control" id="i_chj" placeholder="厂家">
								</div>
								
								<label for="i_cbzwm" class="col-sm-2 control-label">型号</label>
							    <div class="col-sm-4">
							      <input type="text" class="form-control" id="i_xh" placeholder="型号">
								</div>
							</div>
							
							<div class="form-group">
							    <label for="i_cbdm" class="col-sm-2 control-label">销售方</span></label>
							    <div class="col-sm-4">
							      <input type="text" class="form-control" id="i_xshf" placeholder="销售方">
								</div>
								
								<label for="i_cbzwm" class="col-sm-2 control-label">购置日期</label>
							    <div class="col-sm-4">
							      <div class="input-group date form_date" data-date="" data-date-format="yyyy-mm-dd" data-link-field="dtp_input1" data-link-format="yyyy-mm-dd">
					               		<input id="i_gzrq" class="form-control" size="10"  placeholder="购置日期" type="text" value=""  >
					               		<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
					               		<span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
					        	  </div>
								</div>
							</div>
					  		
					  </div>
					  
					  <div role="tabpanel" class="tab-pane" id="profile1" style="padding-top:20px">
					  
					  		<div class="form-group">
							    <label for="i_cbdm" class="col-sm-2 control-label">保修年限</span></label>
							    <div class="col-sm-4">
							      <select class="form-control myselect" id="i_bxnx">
							      		<option value='-1'></option>
							      		<option value='0'>0</option>
							      		<option value='1'>1</option>
							      		<option value='2'>2</option>
							      		<option value='3'>3</option>
							      		<option value='4'>4</option>
							      		<option value='5'>5</option>
							      </select>
								</div>
								
								<label for="i_cbzwm" class="col-sm-2 control-label">延保年限</label>
							    <div class="col-sm-4">
							      <select class="form-control myselect" id="i_ybnx">
							      		<option value='-1'></option>
							      		<option value='0'>0</option>
							      		<option value='1'>1</option>
							      		<option value='2'>2</option>
							      		<option value='3'>3</option>
							      		<option value='4'>4</option>
							      		<option value='5'>5</option>
							      </select>
								</div>
							</div>
							
							<div class="form-group">
							    <label for="i_cbdm" class="col-sm-2 control-label">报修联系人</span></label>
							    <div class="col-sm-4">
							      <input type="text" class="form-control" id="i_bxlxr" placeholder="报修联系人">
								</div>
								
								<label for="i_cbzwm" class="col-sm-2 control-label">报修联系电话</label>
							    <div class="col-sm-4">
							      <input type="text" class="form-control" id="i_bxdh" placeholder="报修联系电话">
								</div>
							</div>
							
							<div class="form-group">
							    <label for="i_cbdm" class="col-sm-2 control-label">报废年限</span></label>
							    <div class="col-sm-4">
							      <input type="text" class="form-control" id="i_bfnx" placeholder="报废年限">
								</div>
								
								
							</div>
							
							
					  </div>
					  
					  <div role="tabpanel" class="tab-pane" id="messages1" style="padding-top:20px">
					  
					  		<div class="form-group">
							    <label for="i_cbdm" class="col-sm-2 control-label">使用状态</span></label>
							    <div class="col-sm-4">
							      <select class="form-control myselect" id="i_syzt">
							      		
							      </select>
								</div>
								
								<label for="i_cbzwm" class="col-sm-2 control-label">使用人</label>
							    <div class="col-sm-4">
							      	<input type="text" class="form-control" id="i_syr" placeholder="使用人">
								</div>
							</div>
							
							<div class="form-group">
							    <label for="i_cbdm" class="col-sm-2 control-label">使用地点</span></label>
							    <div class="col-sm-4">
							      <input type="text" class="form-control" id="i_sydd" placeholder="使用地点">
								</div>
								
								<label for="i_cbzwm" class="col-sm-2 control-label">使用部门</label>
							    <div class="col-sm-4">
							      	<input type="text" class="form-control" id="i_sybm" placeholder="使用部门">
								</div>
							</div>
							
					  </div>
					
					</div>
	        	
	        	</form>
	        	
	        
	      </div>
	      
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
	        <button type="button" class="btn btn-primary" id="a_sure">保存</button>
	        
	        
	      </div>
    </div>
  </div>
</div>
   
   
    <!--模态框2-->
   
    <div class="modal fade bs-example-modal-lg" id="myEditModal"  tabindex="-1"  role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
	        <h4 class="modal-title" id="myModalLabel">调整固定资产信息</h4>
	        <div class="alert alert-danger hidden" id="u_display" role="alert">.111111</div>
	      </div>
	      
	      <div class="modal-body">
	      		
	        	<form class="form-horizontal" role="form" id="updateForm">
	        		
	        		
	        		
	        		<div class="form-group">
					    <label for="i_cbdm" class="col-sm-2 control-label">资产名称<span class="text-danger">(*)</span></label>
					    <div class="col-sm-4">
					      <input type="text" class="form-control" id="u_zcmc" placeholder="资产名称">
						</div>
					</div>
	        	
	        		<div class="form-group">
					    <label for="u_cbdm" class="col-sm-2 control-label">一级类别<span class="text-danger">(*)</span></label>
					    <div class="col-sm-4">
					      	<select class="form-control myselect" id="u_yjlb"></select>
						</div>
						
						<label for="u_cbzwm" class="col-sm-2 control-label">二级类别<span class="text-danger">(*)</span></label>
					    <div class="col-sm-4">
					      	<select class="form-control myselect" id="u_ejlb"></select>
						</div>
					</div>
					<ul class="nav nav-tabs" role="tablist">
						  <li id="u01" role="presentation" class="active"><a href="#home2" role="tab" data-toggle="tab">固定资产信息</a></li>
						  <li role="presentation"><a href="#profile2" role="tab" data-toggle="tab">维保信息</a></li>
						  <li id="u03" role="presentation"><a href="#messages2" role="tab" data-toggle="tab">使用信息</a></li>
					</ul>
					
					<div class="tab-content">
					  <div role="tabpanel" class="tab-pane active" id="home2" style="padding-top:20px">
					  		
					  		<div class="form-group">
							    <label for="u_cbdm" class="col-sm-2 control-label">固定资产编号</span></label>
							    <div class="col-sm-4">
							      <input type="text" class="form-control" id="u_gdzcbm" placeholder="固定资产编号">
								</div>
								
								<label for="u_cbzwm" class="col-sm-2 control-label">SN码</label>
							    <div class="col-sm-4">
							      <input type="text" class="form-control" id="u_snm" placeholder="SN码">
								</div>
							</div>
							
							<div class="form-group">
							    <label for="u_cbdm" class="col-sm-2 control-label">厂家</span></label>
							    <div class="col-sm-4">
							      <input type="text" class="form-control" id="u_chj" placeholder="厂家">
								</div>
								
								<label for="u_cbzwm" class="col-sm-2 control-label">型号</label>
							    <div class="col-sm-4">
							      <input type="text" class="form-control" id="u_xh" placeholder="型号">
								</div>
							</div>
							
							<div class="form-group">
							    <label for="u_cbdm" class="col-sm-2 control-label">销售方</span></label>
							    <div class="col-sm-4">
							      <input type="text" class="form-control" id="u_xshf" placeholder="销售方">
								</div>
								
								<label for="u_cbzwm" class="col-sm-2 control-label">购置日期</label>
							    <div class="col-sm-4">
							      <div class="input-group date form_date" data-date="" data-date-format="yyyy-mm-dd" data-link-field="dtp_input1" data-link-format="yyyy-mm-dd">
					               		<input id="u_gzrq" class="form-control" size="10"  placeholder="购置日期" type="text" value=""  >
					               		<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
					               		<span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
					        	  </div>
								</div>
							</div>
					  		
					  </div>
					  
					  <div role="tabpanel" class="tab-pane" id="profile2" style="padding-top:20px">
					  
					  		<div class="form-group">
							    <label for="u_cbdm" class="col-sm-2 control-label">保修年限</span></label>
							    <div class="col-sm-4">
							      <select class="form-control myselect" id="u_bxnx">
							      		<option value='-1'></option>
							      		<option value='0'>0</option>
							      		<option value='1'>1</option>
							      		<option value='2'>2</option>
							      		<option value='3'>3</option>
							      		<option value='4'>4</option>
							      		<option value='5'>5</option>
							      </select>
								</div>
								
								<label for="u_cbzwm" class="col-sm-2 control-label">延保年限</label>
							    <div class="col-sm-4">
							      <select class="form-control myselect" id="u_ybnx">
							      		<option value='-1'></option>
							      		<option value='0'>0</option>
							      		<option value='1'>1</option>
							      		<option value='2'>2</option>
							      		<option value='3'>3</option>
							      		<option value='4'>4</option>
							      		<option value='5'>5</option>
							      </select>
								</div>
							</div>
							
							<div class="form-group">
							    <label for="u_cbdm" class="col-sm-2 control-label">报修联系人</span></label>
							    <div class="col-sm-4">
							      <input type="text" class="form-control" id="u_bxlxr" placeholder="报修联系人">
								</div>
								
								<label for="u_cbzwm" class="col-sm-2 control-label">报修联系电话</label>
							    <div class="col-sm-4">
							      <input type="text" class="form-control" id="u_bxdh" placeholder="报修联系电话">
								</div>
							</div>
							
							
							
							
					  </div>
					  
					  <div role="tabpanel" class="tab-pane" id="messages2" style="padding-top:20px">
					  
					  		<div class="form-group">
							    <label for="u_cbdm" class="col-sm-2 control-label">使用状态</span></label>
							    <div class="col-sm-4">
							      <select class="form-control myselect" id="u_syzt">
							      		
							      </select>
								</div>
								
								<label for="u_cbzwm" class="col-sm-2 control-label">使用人</label>
							    <div class="col-sm-4">
							      	<input type="text" class="form-control" id="u_syr" placeholder="使用人">
								</div>
							</div>
							
							<div class="form-group">
							    <label for="u_cbdm" class="col-sm-2 control-label">使用地点</span></label>
							    <div class="col-sm-4">
							      <input type="text" class="form-control" id="u_sydd" placeholder="使用地点">
								</div>
								
								<label for="u_cbzwm" class="col-sm-2 control-label">使用部门</label>
							    <div class="col-sm-4">
							      	<input type="text" class="form-control" id="u_sybm" placeholder="使用部门">
								</div>
							</div>
							
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
   
 
     <!--模态框3-->
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
   
   
    <!--模态框4-->
   
   <div class="modal fade bs-example-modal-lg" id="myAddModal2"  tabindex="-1"  role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
	        <h4 class="modal-title" id="myModalLabel"><b>维修登记</b>&nbsp;&nbsp;<i><span id="wi_title"></span></i></h4>
	        <div class="alert alert-danger hidden" id="wi_display" role="alert">.111111</div>
	      </div>
	      
	      <div class="modal-body">
	      
	        	<form class="form-horizontal" role="form" id="insForm0">
	        		
	        		
	        		
	        		<div class="form-group">
					    <label for="i_cbdm" class="col-sm-2 control-label">故障类型<span class="text-danger">(*)</span></label>
					    <div class="col-sm-4">
					      	<select class="form-control myselect" id="wi_gzlx">
					      	
					      	</select>
						</div>
						
						
					</div>
	        		
	        		<div class="form-group">
	        			
	        		
	        			<label for="wi_jxry" class="col-sm-2 control-label">检修人员<span class="text-danger">(*)</span></label>
					    <div class="col-sm-4">
					    	<input type="text" class="form-control" id="wi_jxry" placeholder="检修人员">
					    </div>
						
						<label for="i_cbzwm" class="col-sm-2 control-label">故障日期<span class="text-danger">(*)</span></label>
					    <div class="col-sm-4">
					      <div class="input-group date form_date" data-date="" data-date-format="yyyy-mm-dd" data-link-field="dtp_input1" data-link-format="yyyy-mm-dd">
			               		<input id="wi_gzrq" class="form-control" size="10"  placeholder="故障日期" type="text" value=""  >
			               		<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
			               		<span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
			        	  </div>
						</div>
						
	        		</div>
	        			        		
	        		<div class="form-group">
	        			<label for="i_cbzwm" class="col-sm-2 control-label">是否修复</label>
	        			<div class="col-sm-4">
		        			<select class="form-control myselect" id="wi_isxf">
						     	<option value="0">否</option> 
						     	<option value="1">是</option> 	
						     </select>
					    </div>
					     
					     <label for="i_cbzwm" class="col-sm-2 control-label">修复日期</label>
					     <div class="col-sm-4">
		        			<div class="input-group date form_date" data-date="" data-date-format="yyyy-mm-dd" data-link-field="dtp_input1" data-link-format="yyyy-mm-dd">
				               		<input id="wi_xfrq" class="form-control" size="10"  placeholder="故障日期" type="text" value=""  >
				               		<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
				               		<span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
				        	  </div>
				        </div>
	        		</div>
	        		
	        		<div class="form-group">
	        			<label for="i_cbzwm" class="col-sm-2 control-label">故障描述</label>
	        			<div class="col-sm-10">
	        				<textarea class="form-control" rows="3" id="wi_gzms" placeholder="故障描述"></textarea>
	        			</div>
	        		</div>
	        	</form>
	        	
	        		<ul class="nav nav-tabs" role="tablist">
							  <li role="presentation" class="active"><a href="#home3" role="tab" data-toggle="tab">自修</a></li>
							  <li role="presentation"><a href="#profile3" role="tab" data-toggle="tab">送修</a></li>
				    </ul>
					
	        		
	        		
		        		<div class="tab-content">
		        			<div role="tabpanel" class="tab-pane active" id="home3" style="padding-top:20px">
		        				
		        				<form class="form-horizontal" role="form" id="insForm1">
			        				<div class="form-group">
			        					<label for="i_cbzwm" class="col-sm-2 control-label">维修人员</label>
			        					<div class="col-sm-4">
						    				<input type="text" class="form-control" id="wi_wxry" placeholder="维修人员">
						    			</div>
						    			
			        					<label for="i_cbdm" class="col-sm-2 control-label">维修结果</label>
									    <div class="col-sm-4">
									      	<select class="form-control myselect" id="wi_wxjg">
									      	
									      	</select>
										</div>
			        				</div>
			        				
			        				<div class="form-group">
		        					<label for="i_cbzwm" class="col-sm-2 control-label">维修方法</label>
				        			<div class="col-sm-10">
				        				<textarea class="form-control" rows="5" id="wi_wxff" placeholder="维修方法"></textarea>
				        			</div>
		        				</div>
		        				
		        				</form>
		        				
		        			</div>
		        			
		        			<div role="tabpanel" class="tab-pane" id="profile3" style="padding-top:20px">
		        				<form class="form-horizontal" role="form" id="insForm2">
		        				<div class="form-group">
		        					<label for="wi_jxry" class="col-sm-2 control-label">送修人员</span></label>
								    <div class="col-sm-4">
								    	<input type="text" class="form-control" id="wi_sxry" placeholder="送修人员">
								    </div>
									
									<label for="i_cbzwm" class="col-sm-2 control-label">送修日期</label>
								    <div class="col-sm-4">
								      <div class="input-group date form_date" data-date="" data-date-format="yyyy-mm-dd" data-link-field="dtp_input1" data-link-format="yyyy-mm-dd">
						               		<input id="wi_sxrq" class="form-control" size="10"  placeholder="送修日期" type="text" value=""  >
						               		<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
						               		<span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
						        	  </div>
									</div>
		        				</div>
		        				
		        				<div class="form-group">
		        					<label for="wi_jxry" class="col-sm-2 control-label">收货人员</span></label>
								    <div class="col-sm-4">
								    	<input type="text" class="form-control" id="wi_shry" placeholder="收货人员">
								    </div>
									
									<label for="i_cbzwm" class="col-sm-2 control-label">收货日期</label>
								    <div class="col-sm-4">
								      <div class="input-group date form_date" data-date="" data-date-format="yyyy-mm-dd" data-link-field="dtp_input1" data-link-format="yyyy-mm-dd">
						               		<input id="wi_shrq" class="form-control" size="10"  placeholder="收货日期" type="text" value=""  >
						               		<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
						               		<span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
						        	  </div>
									</div>
		        				</div>
		        				</form>
		        			</div>
		        			
		        		</div>
	        	
	        
	      </div>
	      
	      <div class="modal-footer">
	        
	        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
	        <button type="button" class="btn btn-primary" id="wi_save">保存</button>
	        
	        
	      </div>
    </div>
  </div>
</div>
   
   <!--模态框5-->
   
    <div class="modal fade bs-example-modal-lg" id="myAddModal5"  tabindex="-1"  role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
	        <h4 class="modal-title" id="myModalLabel"><b>跟进维修记录</b><span id="wi_title"></span></h4>
	        <div class="alert alert-danger hidden" id="wi_display" role="alert">.111111</div>
	      </div>
	      
	      <div class="modal-body">
	      
	        	<form class="form-horizontal" role="form" id="uptForm0">
	        		
	        		
	        		
	        		<div class="form-group">
					    <label for="i_cbdm" class="col-sm-2 control-label">故障类型<span class="text-danger">(*)</span></label>
					    <div class="col-sm-4">
					      	<select class="form-control myselect" id="ui_gzlx">
					      	
					      	</select>
						</div>
						
						
					</div>
	        		
	        		<div class="form-group">
	        			
	        		
	        			<label for="ui_jxry" class="col-sm-2 control-label">检修人员<span class="text-danger">(*)</span></label>
					    <div class="col-sm-4">
					    	<input type="text" class="form-control" id="ui_jxry" placeholder="检修人员">
					    </div>
						
						<label for="i_cbzwm" class="col-sm-2 control-label">故障日期<span class="text-danger">(*)</span></label>
					    <div class="col-sm-4">
					      <div class="input-group date form_date" data-date="" data-date-format="yyyy-mm-dd" data-link-field="dtp_input1" data-link-format="yyyy-mm-dd">
			               		<input id="ui_gzrq" class="form-control" size="10"  placeholder="故障日期" type="text" value=""  >
			               		<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
			               		<span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
			        	  </div>
						</div>
						
	        		</div>
	        			        		
	        		<div class="form-group">
	        			<label for="i_cbzwm" class="col-sm-2 control-label">是否修复</label>
	        			<div class="col-sm-4">
		        			<select class="form-control myselect" id="ui_isxf">
						     	<option value="0">否</option> 
						     	<option value="1">是</option> 	
						     </select>
					    </div>
					     
					     <label for="i_cbzwm" class="col-sm-2 control-label">修复日期</label>
					     <div class="col-sm-4">
		        			<div class="input-group date form_date" data-date="" data-date-format="yyyy-mm-dd" data-link-field="dtp_input1" data-link-format="yyyy-mm-dd">
				               		<input id="ui_xfrq" class="form-control" size="10"  placeholder="故障日期" type="text" value=""  >
				               		<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
				               		<span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
				        	  </div>
				        </div>
	        		</div>
	        		
	        		<div class="form-group">
	        			<label for="i_cbzwm" class="col-sm-2 control-label">故障描述</label>
	        			<div class="col-sm-10">
	        				<textarea class="form-control" rows="3" id="ui_gzms" placeholder="故障描述"></textarea>
	        			</div>
	        		</div>
	        	</form>
	        	
	        		<ul class="nav nav-tabs" role="tablist">
							  <li role="presentation" class="active"><a href="#home4" role="tab" data-toggle="tab">新增自修记录</a></li>
							  <li role="presentation"><a href="#profile4" role="tab" data-toggle="tab">新增送修记录</a></li>
							 
				    </ul>
					
	        		
	        		
		        		<div class="tab-content">
		        			<div role="tabpanel" class="tab-pane active" id="home4" style="padding-top:20px">
		        				
		        				<form class="form-horizontal" role="form" id="uptForm1">
			        				<div class="form-group">
			        					<label for="i_cbzwm" class="col-sm-2 control-label">维修人员</label>
			        					<div class="col-sm-4">
						    				<input type="text" class="form-control" id="ui_wxry" placeholder="维修人员">
						    			</div>
						    			
			        					<label for="i_cbdm" class="col-sm-2 control-label">维修结果</label>
									    <div class="col-sm-4">
									      	<select class="form-control myselect" id="ui_wxjg">
									      	
									      	</select>
										</div>
			        				</div>
			        				
			        				<div class="form-group">
		        					<label for="i_cbzwm" class="col-sm-2 control-label">维修方法</label>
				        			<div class="col-sm-10">
				        				<textarea class="form-control" rows="5" id="ui_wxff" placeholder="维修方法"></textarea>
				        			</div>
		        				</div>
		        				
		        				</form>
		        				
		        			</div>
		        			
		        			<div role="tabpanel" class="tab-pane" id="profile4" style="padding-top:20px">
		        				<form class="form-horizontal" role="form" id="uptForm2">
		        				<div class="form-group">
		        					<label for="ui_jxry" class="col-sm-2 control-label">送修人员</span></label>
								    <div class="col-sm-4">
								    	<input type="text" class="form-control" id="ui_sxry" placeholder="送修人员">
								    </div>
									
									<label for="i_cbzwm" class="col-sm-2 control-label">送修日期</label>
								    <div class="col-sm-4">
								      <div class="input-group date form_date" data-date="" data-date-format="yyyy-mm-dd" data-link-field="dtp_input1" data-link-format="yyyy-mm-dd">
						               		<input id="ui_sxrq" class="form-control" size="10"  placeholder="送修日期" type="text" value=""  >
						               		<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
						               		<span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
						        	  </div>
									</div>
		        				</div>
		        				
		        				<div class="form-group">
		        					<label for="ui_jxry" class="col-sm-2 control-label">收货人员</span></label>
								    <div class="col-sm-4">
								    	<input type="text" class="form-control" id="ui_shry" placeholder="收货人员">
								    </div>
									
									<label for="i_cbzwm" class="col-sm-2 control-label">收货日期</label>
								    <div class="col-sm-4">
								      <div class="input-group date form_date" data-date="" data-date-format="yyyy-mm-dd" data-link-field="dtp_input1" data-link-format="yyyy-mm-dd">
						               		<input id="ui_shrq" class="form-control" size="10"  placeholder="收货日期" type="text" value=""  >
						               		<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
						               		<span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
						        	  </div>
									</div>
		        				</div>
		        				</form>
		        			</div>
		        			
		        		</div>
	        	
	        
	      </div>
	      
	      <div class="modal-footer">
	        
	        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
	        <button type="button" class="btn btn-primary" id="ui_save">保存</button>
	        
	        
	      </div>
    </div>
  </div>
</div>
   
   <!--模态框6-->
   <div class="modal fade bs-example-modal-lg" id="myLog"  tabindex="-1"  role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
	        <h4 class="modal-title" id="myModalLabel">日志信息</h4>
	      </div>
	      
	      <div class="modal-body">
	      		<ul class="nav nav-tabs" role="tablist">
					  <li role="presentation" class="active"><a href="#home6" role="tab" data-toggle="tab">使用方信息</a></li>
					  <li role="presentation"><a href="#profile6" role="tab" data-toggle="tab">变更日志</a></li>	 
				</ul>
	        	<div class="tab-content">
	        		<div role="tabpanel" class="tab-pane active" id="home6" style="padding-top:20px">
	        				<table id="tablelog1" class="order-column nowrap tableExample2 table table-striped table-bordered" cellspacing="0" width="100%" style="margin-top:10px;">
	        					<thead>
	        						<tr>
	        							<th></th>
	        							<th>更新时间</th> <th>更新人</th>  <th>旧使用人</th> <th>旧使用地址</th> <th>旧使用部门</th>
	        							
	        							 <th>新使用人</th> <th>新使用地址</th> <th>新使用部门</th>
	        							
	        						</tr>
	        					</thead>
	        					
	        					<tbody></tbody>
	        					
	        				</table>
	        		
	        		</div>
	        		
	        		
	        		<div role="tabpanel" class="tab-pane" id="profile6" style="padding-top:20px">
	        				<table id="tablelog2" class="order-column nowrap tableExample2 table table-striped table-bordered" cellspacing="0" width="100%" style="margin-top:10px;">
	        					<thead>
	        						<tr>
	        							<th></th><th>更新时间</th> <th>更新人</th> <th>更新字段</th> <th>旧值</th> <th>新值</th> <th>上次更新时间</th> <th>上次更新人</th>
	        						</tr>
	        					</thead>
	        					
	        					<tbody></tbody>
	        					
	        				</table>
	        		</div>
	        	
	        	</div>
	        	
	        
	      </div>
	      
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
	        <button type="button" class="btn btn-primary" id="a_sure">保存</button>
	        
	        
	      </div>
    </div>
  </div>
</div>
   
   
   
  
  <input type="hidden" id="sel_assetid" value="">
  <input type="hidden" id="sel_check" value="">
  <input type="hidden" id="sel_dnbh" value="">
  </body>
  
   <script src="<%=basePath %>js/jquery-1.12.3.js"></script>
   <script src="<%=basePath %>page/js/bootstrap.min.js"></script>
   
   <script src="<%=basePath %>js/jquery-ui.min.js"></script>
  
   <script src="<%=basePath %>page/js/jquery.mmenu.all.min.js"></script>
   
   <script src="<%=basePath %>js/jquery.dataTables.min.js"></script>
   <script src="<%=basePath %>js/dataTables.bootstrap.min.js"></script>
   
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

   <script src="<%=basePath %>/page/page/asset/js/fixedAsset.js?v=3.5"></script>
  
  
</html>
