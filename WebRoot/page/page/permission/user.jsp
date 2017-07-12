<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page language="java" import="com.gzb.util.StringUtil" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE>
<html >
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="renderer" content="webkit">


<title>用户管理</title>
<link rel="stylesheet" href="../../css/bootstrap.min.css">
<link rel="stylesheet" href="../../css/jquery-ui.min.css">
<link rel="stylesheet" href="../../css/jquery.dataTables.min.css">
<link rel="stylesheet" href="../../css/buttons.dataTables.min.css">
<link rel="stylesheet" href="../../css/mySkin.css">


<!--[if lt IE 9]>
<script src="js/html5.min.js"></script>
<script src="js/respond.min.js"></script>
<![endif]-->

<style>
.ui-autocomplete {
    max-height: 200px;
    overflow-y: auto;
    /* prevent horizontal scrollbar */
    overflow-x: hidden;
  }
  /* IE 6 doesn't support max-height
   * we use height instead, but this forces the menu to always be this tall
   */
  * html .ui-autocomplete {
    height: 200px;
  }
  
  div.dataTables_wrapper {
      /*  width: 800px;*/
        margin: 0 auto;
  }
  th{
	  min-width:136px;
  }


</style>



</head>

<body>

<div class="container-fluid" style="padding:10px;">
<ol class="breadcrumb">
  <li><a href="#">仓库云</a></li>
  <li><a href="#">权限管理</a></li>
  <li class="active">用户管理</li>
</ol>

<form class="form-inline">
  <div class="form-group">
    <input type="text" class="form-control" id="s_userID" placeholder="登录ID">
  </div>
  <div class="form-group">
   
    <input type="text" class="form-control" id="s_userName" placeholder="用户姓名">
  </div>
  
 
  
  <div class="form-group">
  	<button type="button" class="btn btn-primary" id="s_check">查 询</button>
  </div>
	
 <div class="form-group">
  	<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal">新 增</button>
  </div>
  
  <div class="form-group">
  	<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#eidtModal">编 辑</button>
  </div>

  <div class="form-group">
  	<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#delModal">删 除</button>
  </div>
  

  
  
</form>
<hr/>

<div class="row">
	<div class="col-md-11">
    	<table id="example" class="display tableExample" cellspacing="0" width="100%">
            <thead>
                    <tr>
                       <td>用户登录ID</td>
                       <td>用户姓名</td>
                       <td>用户所在仓库</td>
                       <td>权限属组</td>
                       <td>houseID</td>
                       <td>groupID</td>
                    </tr>
            </thead>
            <tbody>
               
            </tbody>

		</table>
    </div>
    
    
   
</div>

</div>
<!-- container end -->


<div class="container-fluid">

  <!-- Button trigger modal -->


<!--Add Modal -->
<div class="modal fade bs-example-modal-sm" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog modal-md" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h3 class="modal-title" id="myModalLabel"><b>新增用户</b></h3>
        
      </div>
      <div class="modal-body">
      
        	<div class="form-group form-group-sm form-horizontal" >
                   	<label for="vessel" class="control-label">用户登录ID:</label>
                    <input type="text" class="form-control" id="ins_logid" placeholder="用户登录ID" size="40"  >
                       
		    </div>
            
            <div class="form-group form-group-sm form-horizontal" >
                   <label for="vessel" class="control-label">用户姓名</label>
                   <input type="text" class="form-control" id="ins_name" placeholder="用户姓名" size="40">
		    </div>
            
             <div class="form-group form-group-sm form-horizontal" >
                   <label for="vessel" class="control-label">密码</label>
                   <input type="password" class="form-control" id="ins_pwd1" placeholder="密码" size="40">
		    </div>
            
            <div class="form-group form-group-sm form-horizontal" >
                   <label for="vessel" class="control-label">确认密码</label>
                   <input type="password" class="form-control" id="ins_pwd2" placeholder="确认密码" size="40">
		    </div>
            
            <div class="form-group form-group-sm form-horizontal" >
                   <label for="vessel" class="control-label">所在仓库</label>
                   <select class="form-control" id="ins_ck">
                   
                   </select>
		    </div>
            
            <div class="form-group form-group-sm form-horizontal" >
                   <label for="vessel" class="control-label">权限属组</label>
                   <select class="form-control" id="ins_group">
                   
                   </select>
		    </div>
            
            
            
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关 闭</button>
        <button type="button" class="btn btn-primary" id="ins_save">保 存</button>
      </div>
    </div>
  </div>
</div>
<!--Add Modal End-->


<!--Edit Modal -->
<div class="modal fade bs-example-modal-sm" id="eidtModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog modal-md" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h3 class="modal-title" id="myModalLabel"><b>修改用户</b></h3>
        
      </div>
      <div class="modal-body">
      
        	<div class="form-group form-group-sm form-horizontal" >
                   	<label for="vessel" class="control-label">用户登录ID:</label>
                    <input type="text" class="form-control" id="upd_logid" placeholder="用户登录ID" size="40" disabled >
                       
		    </div>
            
            <div class="form-group form-group-sm form-horizontal" >
                   <label for="vessel" class="control-label">用户姓名</label>
                   <input type="text" class="form-control" id="upd_name" placeholder="用户姓名" size="40">
		    </div>
            
             <div class="form-group form-group-sm form-horizontal" >
                   <label for="vessel" class="control-label">密码</label>
                   <input type="password" class="form-control" id="upd_pwd1" placeholder="密码" size="40">
		    </div>
            
            <div class="form-group form-group-sm form-horizontal" >
                   <label for="vessel" class="control-label">确认密码</label>
                   <input type="password" class="form-control" id="upd_pwd2" placeholder="确认密码" size="40">
		    </div>
            
            <div class="form-group form-group-sm form-horizontal" >
                   <label for="vessel" class="control-label">所在仓库</label>
                   <select class="form-control" id="upd_ck">
                   
                   </select>
		    </div>
            
            <div class="form-group form-group-sm form-horizontal" >
                   <label for="vessel" class="control-label">权限属组</label>
                   <select class="form-control" id="upd_group">
                   
                   </select>
		    </div>
            
            
            
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关 闭</button>
        <button type="button" class="btn btn-primary" id="upd_save">保 存</button>
      </div>
    </div>
  </div>
</div>
<!--Edit Modal End-->


<!--delete Modal -->
<div class="modal fade bs-example-modal-lg" id="delModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h3 class="modal-title" id="myModalLabel"><b>删除用户</b></h3>
        
      </div>
      <div class="modal-body">
      
        	<span id="deleteMsg">确认删除</span>
            
            
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关 闭</button>
        <button type="button" class="btn btn-primary" id="del_save">删 除</button>
      </div>
    </div>
  </div>
</div>
<!--Edit Modal End-->


</div>





</body>
<script src="../../js/jquery-1.12.0.min.js"></script>
<script src="../../js/bootstrap.min.js"></script>
<script src="../../js/jquery-ui.min.js"></script>
<script src="../../js/jquery.dataTables.min.js"></script>


<script src="../../js/exportJS/dataTables.buttons.min.js"></script>
<script src="../../js/exportJS/buttons.flash.min.js"></script>
<script src="../../js/exportJS/jszip.min.js"></script>
<script src="../../js/exportJS/pdfmake.min.js"></script>
<script src="../../js/exportJS/vfs_fonts.js"></script>
<script src="../../js/exportJS/buttons.html5.min.js"></script>
<script src="../../js/exportJS/buttons.print.min.js"></script>

<script src='<%=basePath %>dwr/engine.js'></script> 
<script src='<%=basePath %>dwr/util.js'></script> 

<script src='<%=basePath %>dwr/interface/UserAndPermission.js'></script>
<script src='<%=basePath %>dwr/interface/PreStatement.js'></script>
<script src='<%=basePath %>dwr/interface/EncrypDES.js'></script>
EncrypDES
<script src="js/user.js"></script>


<script type="text/javascript">



		
</script>

</html>
