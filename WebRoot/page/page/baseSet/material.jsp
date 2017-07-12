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


<title>物资明细管理</title>
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
.ui-front{
	z-index:1900;
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
  <li><a href="#">基础信息</a></li>
  <li class="active">物资明细管理</li>
</ol>

<form class="form-inline">
  <div class="form-group">
    <label class="sr-only" for="exampleInputEmail3">物资名称(物资编号)</label>
    <input type="text" class="form-control" id="s_productName" placeholder="物资名称(物资编号)">
  </div>
  <div class="form-group">
    <label class="sr-only" for="exampleInputPassword3">供应商</label>
    <input type="text" class="form-control" id="s_gys" placeholder="供应商">
  </div>
  
  <div class="form-group">
    <label class="sr-only" for="exampleInputPassword3">物资类别</label>
     <input type="text" class="form-control" id="s_category" placeholder="物资类别">
  </div>
  
  <div class="form-group">
  	<button type="button" class="btn btn-primary" id="mCheck">查 询</button>
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
  
  <div class="form-group">
  	<button type="button" class="btn btn-success" data-toggle="modal" data-target="#logModal">修改日志</button>
  </div>
  
  
</form>

<hr/>

<div class="row">
	<div class="col-lg-9">
    
    	<div class="panel panel-primary">
          <div class="panel-heading">物资明细管理</div>
          <div class="panel-body"  >
                <table id="exampleMaterial" class="display nowrap tableExample" cellspacing="0" width="100%">
                    <thead>
                            <tr>
                                <th>物资系统ID</th>
                                <th>物资名称</th>
                                <th>物资类别</th>
                                <th>计量单位</th>
                                <th>物资规格</th>
                               
                                <th style="min-width:200px;">物资编号（类别+账页）</th>
                                <th>库存量</th>
                                <th>仓位</th>
                                <th>库存预警(Y/N)</th>
                                <th>库存上限</th>
                                <th>库存下限</th>
                                <th>备注</th>
                            </tr>
                    </thead>
                    <tbody>
                        
                    </tbody>
        
                </table>
            </div>
        </div>
            
    </div>
    
    
    <div class="col-lg-3">
    	
        <div class="panel panel-primary">
          <div class="panel-heading">
          		参考图片
          		<button type="button" class="btn btn-success" id="uploadImg">上传图片 </button>
          </div>
          
          
          <div class="panel-body"  >
        		<input type="file"  id="file" name="file">
          		<hr/>
            	<div class="easyzoom">
            		<a href="#" class="thumbnail"><img id="image" src="../../Image?materialID=123" alt="../..."></a>
                </div>
       
       	</div>
       </div>
    
    
    	
    </div>



</div>

</div>
<!-- container end -->


<div class="container-fluid">

  <!-- Button trigger modal -->


<!--Add Modal -->
<div class="modal fade bs-example-modal-lg" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h3 class="modal-title" id="myModalLabel"><b>新增物资</b></h3>
        
      </div>
      <div class="modal-body">
      		<input type="hidden" id="ins_categoryID" value="" />
        	<div class="form-group form-group-sm form-inline" >
                    <div class="row">	
                        <div class="col-md-6">		
                            <label for="vessel" class="control-label">物资名称:</label>
                            <input type="text" class="form-control" id="ins_wzmc" placeholder="物资名称" size="35" >
                        </div>
                        <div class="col-md-6">		
                            <label for="vessel" class="control-label">物资类别:</label>
                            <input type="text" class="form-control" id="ins_wzlb" placeholder="物资类别" size="35">
                        </div>		
                    </div>
		    </div>
            
            <div class="form-group form-group-sm form-inline" >
                    <div class="row">	
                        <div class="col-md-6">		
                            <label for="vessel" class="control-label">物资规格:</label>
                            <input type="text" class="form-control" id="ins_wzgg" placeholder="物资规格" size="35" >
                        </div>
                        <div class="col-md-6">		
                            <label for="vessel" class="control-label">计量单位:</label>
                            <input type="text" class="form-control" id="ins_jldw" placeholder="计量单位" size="35">
                        </div>		
                    </div>
		    </div>
            
             <div class="form-group form-group-sm form-inline" >
                    <div class="row">	
                        <div class="col-md-6">		
                            <label for="vessel" class="control-label">物资编号:</label>
                            <input type="text" class="form-control" id="ins_wzbh" placeholder="物资编号" size="35" >
                        </div>
                        
                        <div class="col-md-6">		
                            <label for="vessel" class="control-label">物资备注:</label>
                            <input type="text" class="form-control" id="ins_wzbz" placeholder="物资编号" size="35" >
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
<div class="modal fade bs-example-modal-lg" id="eidtModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h3 class="modal-title" id="myModalLabel"><b>编辑物资</b></h3>
        
      </div>
      <div class="modal-body">
      		<input type="hidden" id="upt_materialID" value="" />
      		<input type="hidden" id="upt_categoryID" value="" />
      		
        	<div class="form-group form-group-sm form-inline" >
                    <div class="row">	
                        <div class="col-md-6">		
                            <label for="vessel" class="control-label">物资名称:</label>
                            <input type="text" class="form-control" id="upt_wzmc" placeholder="物资名称" size="35" >
                        </div>
                        <div class="col-md-6">		
                            <label for="vessel" class="control-label">物资类别:</label>
                            <input type="text" class="form-control" id="upt_wzlb" placeholder="物资类别" size="35">
                        </div>		
                    </div>
		    </div>
            
            <div class="form-group form-group-sm form-inline" >
                    <div class="row">	
                        <div class="col-md-6">		
                            <label for="vessel" class="control-label">物资规格:</label>
                            <input type="text" class="form-control" id="upt_wzgg2" placeholder="物资规格" size="35" >
                        </div>
                        <div class="col-md-6">		
                            <label for="vessel" class="control-label">计量单位:</label>
                            <input type="text" class="form-control" id="upt_jldw2" placeholder="计量单位" size="35">
                        </div>		
                    </div>
		    </div>
            
             <div class="form-group form-group-sm form-inline" >
                    <div class="row">	
                        <div class="col-md-6">		
                            <label for="vessel" class="control-label">物资编号:</label>
                            <input type="text" class="form-control" id="upt_wzbh2" placeholder="物资编号" size="35" >
                        </div>
                        
                        <div class="col-md-6">		
                            <label for="vessel" class="control-label">物资备注:</label>
                            <input type="text" class="form-control" id="upt_wzbz2" placeholder="物资编号" size="35" >
                        </div>
                        
                    </div>
		    </div>
            
           
            
            
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关 闭</button>
        <button type="button" class="btn btn-primary" id="upt_save">保 存</button>
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
        <h3 class="modal-title" id="myModalLabel"><b>删除物资</b></h3>
        
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


<div class="modal fade bs-example-modal-lg" id="logModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h3 class="modal-title" id="myModalLabel"><b>修改日志</b></h3>
        
      </div>
      <div class="modal-body">
      
      		<table id="example" class="display tableExample" cellspacing="0" width="100%">
            <thead>
                    <tr>
                        <th>修改人</th>
                        <th>修改内容</th>
                        <th>原值</th>
                        <th>新值</th>
                        <th>修改日期</th>
                       
                        
                    </tr>
            </thead>
            <tbody>
                <tr>
                    <td>1</td><td>8</td><td>1</td><td>1</td><td>1</td>
                </tr>
            </tbody>

		</table>
        	
            
            
      </div>
       <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关 闭</button>
      </div>
     
     
    </div>
  </div>
</div>



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

<script src='<%=basePath %>dwr/interface/Common.js'></script>
<script src='<%=basePath %>dwr/interface/PreStatement.js'></script>
<script src='<%=basePath %>dwr/interface/Baseset.js'></script>
<script src='<%=basePath %>dwr/interface/ImageUtil.js'></script>

<script src="js/material.js?v=1.1"></script>


<script type="text/javascript">



		
</script>

</html>
