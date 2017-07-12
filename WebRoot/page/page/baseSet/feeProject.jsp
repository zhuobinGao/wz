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


<title>费用项目管理</title>
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
  


</style>



</head>

<body>

<div class="container-fluid" style="padding:10px;">
<ol class="breadcrumb">
  <li><a href="#">仓库云</a></li>
  <li><a href="#">基础信息</a></li>
  <li class="active">费用项目管理</li>
</ol>

<form class="form-inline">
  <div class="form-group">
    <label class="sr-only" for="exampleInputEmail3">费用项目名称</label>
    <input type="text" class="form-control" id="s_name" placeholder="费用项目名称">
  </div>

  
  <div class="form-group">
  	<button type="button" class="btn btn-primary" id="s_search">查 询</button>
  </div>
	
 <div class="form-group">
  	<button type="button" class="btn btn-primary" id="s_newCate" data-toggle="modal" data-target="#myModal">新 增</button>
  </div>
  
  <div class="form-group">
  	<button type="button" class="btn btn-primary" id="s_editCate" data-toggle="modal" data-target="#editModal">编 辑</button>
  </div>

  <div class="form-group">
  	<button type="button" class="btn btn-primary" id="s_delCate" data-toggle="modal" data-target="#delModal">删 除</button>
  </div>
  

  
  
</form>
<hr/>

<div class="row">
	<div class="col-lg-12">
    	<div class="panel panel-primary">
          <div class="panel-heading">费用项目管理</div>
          <div class="panel-body"  >
    
                <table id="example" class="display" cellspacing="0" width="100%">
                    <thead>
                            <tr>
                            	<th>行号</th>
                            	<th>费用项目ID</th>
                                <th>费用项目名称</th>
                                <th>费用项目类别</th>
                                <th>费用项目类别ID</th>
                                <th>使用码头</th>
                            </tr>
                    </thead>
                    <tbody>
                        
                    </tbody>
        
                </table>
           </div>
        </div>
                
                
    </div>
   
</div>






</div>


	
	
<!--Add Modal -->
<div class="modal fade bs-example-modal-lg" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h3 class="modal-title" id="myModalLabel"><b>新增费用项目</b></h3>
        
      </div>
      <div class="modal-body">
      		<input type="hidden" id="ins_categoryID" value="" />
        	<div class="form-group form-group-sm form-inline" >
                    <div class="row">	
                        <div class="col-md-6">		
                            <label for="vessel" class="control-label">费用名称:</label>
                            <input type="text" class="form-control" id="ins_fymc" placeholder="物资名称" size="35" >
                        </div>
                        <div class="col-md-6">		
                            <label for="vessel" class="control-label">费用类别:</label>
                            <select id="ins_fylb" class="form-control">
                            
                            </select>
                        </div>		
                    </div>
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
<div class="modal fade bs-example-modal-lg" id="editModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h3 class="modal-title" id="myModalLabel"><b>编辑费用类别</b></h3>
        
      </div>
      <div class="modal-body">
      		<input type="hidden" id="ins_categoryID" value="" />
        	<div class="form-group form-group-sm form-inline" >
                    <div class="row">	
                        <div class="col-md-6">		
                            <label for="vessel" class="control-label">费用名称:</label>
                            <input type="text" class="form-control" id="upd_fymc" placeholder="费用名称" size="35" >
                        </div>
                       
                    </div>
		    </div>
            

            
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关 闭</button>
        <button type="button" class="btn btn-primary" id="upd_save">保 存</button>
      </div>
    </div>
  </div>
</div>
<!-- Edit Modal End-->

<!--delete Modal -->
<div class="modal fade bs-example-modal-lg" id="delModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h3 class="modal-title" id="myModalLabel"><b>删除费用名称</b></h3>
        
      </div>
      <div class="modal-body">
      
        	<span id="deleteMsg">确认删除</span>
            
            
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关 闭</button>
        <button type="button" class="btn btn-primary" id="delMaterial">删 除</button>
      </div>
    </div>
  </div>
</div>
<!-delete Modal End-->









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

<script src='<%=basePath %>dwr/interface/Common.js'></script>
<script src='<%=basePath %>dwr/interface/PreStatement.js'></script>

<script src="js/feeProject.js"></script>


<script type="text/javascript">

	console.info(Common)


		
</script>

</html>
