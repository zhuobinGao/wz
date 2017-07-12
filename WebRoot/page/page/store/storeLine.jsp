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
  <li><a href="#">仓存管理</a></li>
  <li class="active">库存预警设计</li>
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
  	<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal">设置预警</button>
 </div>
  
 
  
  
</form>
<hr/>

<div class="row">
	<div class="col-lg-9">
    
    <div class="alert alert-success in hidden"  role="alert" id="mes1">
		
		<strong>成功<span id="qy1" >启用</span>库存预警功能</strong>
	</div>
	
	<div class="alert alert-danger in hidden" role="alert" id="mes2">
		
		<strong id="qy2">111111</strong>
	</div>
        
        
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
                    
                    <tfoot>
                    	<tr>
                                <th>物资系统ID</th>
                                <th>物资名称</th>
                                <th>物资类别</th>
                                <th>计量单位</th>
                                <th>物资规格</th>
                                <th>物资编号（类别+账页）</th>
                                <th>库存量</th>
                                <th>仓位</th>
                                <th>库存预警(Y/N)</th>
                                <th>库存上限</th>
                                <th>库存下限</th>
                                <th>备注</th>
                            </tr>
                    </tfoot>
                    
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
          		
          </div>
          
          
          <div class="panel-body"  >
            	<div class="easyzoom">
            		<a href="#" class="thumbnail"><img id="image" src="../../Image?materialID=123" alt="../..."></a>
                </div>
       
       	</div>
       </div>
    
    
    	
    </div>



</div>

</div>
<!-- container end -->

<div class="container">
	
</div>



<div class="container-fluid">

  <!-- Button trigger modal -->


<!--Add Modal -->
<div class="modal fade bs-example-modal-lg" style="margin-top:-0px;" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h3 class="modal-title" id="myModalLabel"><b>设置库存预警</b></h3>
        
      </div>
      <div class="modal-body">
      		<input type="hidden" id="ins_categoryID" value="" />
        	<div class="form-group form-group-sm form-inline" >
                    <div class="row">	
                        <div class="col-md-6">		
                            <label for="vessel" class="control-label">物资名称:</label>
                            <input type="text" class="form-control" id="ins_wzmc" placeholder="物资名称" size="35" disabled >
                        </div>
                        <div class="col-md-6">		
                            <label for="vessel" class="control-label">物资类别:</label>
                            <input type="text" class="form-control" id="ins_wzlb" placeholder="物资类别" size="35" disabled>
                        </div>		
                    </div>
		    </div>
            
            <div class="form-group form-group-sm form-inline" >
                    <div class="row">	
                        <div class="col-md-6">		
                            <label for="vessel" class="control-label">物资规格:</label>
                            <input type="text" class="form-control" id="ins_wzgg" placeholder="物资规格" size="35" disabled>
                        </div>
                        <div class="col-md-6">		
                            <label for="vessel" class="control-label">计量单位:</label>
                            <input type="text" class="form-control" id="ins_jldw" placeholder="计量单位" size="35" disabled>
                        </div>		
                    </div>
		    </div>
		    
		     <div class="form-group form-group-sm form-inline" >
		     	<div class="row">	
                        <div class="col-md-6">
					     	<div class="checkbox">
							    <label>
							      <input type="checkbox" id="isCheck"> 启用库存预警
							    </label>
							</div>
						</div>
				</div>
		     
		     	
		     </div>
		     
		     
		     <div class="form-group form-group-sm form-inline" >
		     	<div class="row">	
				    
				     	<div class="col-md-6">
							<label for="vessel" class="control-label">库存数量:</label>
			                <input type="text" class="form-control" id="ins_kc" placeholder="库存数量" size="35" disabled >
						</div>
				     
		     	</div>
		     </div>
		     
		     
            
             <div class="form-group form-group-sm form-inline" >
                    <div class="row">	
                        <div class="col-md-6">		
                            <label for="vessel" class="control-label">库存下限:</label>
                            <input type="text" class="form-control" id="ins_kcxx" placeholder="库存下限" size="35" disabled >
                        </div>
                        
                        <div class="col-md-6">		
                            <label for="vessel" class="control-label">库存上限:</label>
                            <input type="text" class="form-control" id="ins_kcsx" placeholder="库存上限" size="35" disabled >
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
<script src='<%=basePath %>dwr/interface/Report.js'></script>


<script src="js/storeLine.js?v=1.699"></script>


<script type="text/javascript">



		
</script>

</html>
