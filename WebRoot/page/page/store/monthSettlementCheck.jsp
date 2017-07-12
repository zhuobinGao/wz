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
<link rel="stylesheet" href="../../css/bootstrap-datetimepicker.min.css" media="screen">


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
  <li><a href="#">仓存管理</a></li>
  <li class="active">数据月结</li>
</ol>

<form class="form-inline">
	<div class="form-group">
		<label>查询仓库</label>
        <select class="form-control" id="sel_cangKu"></select>
	</div>
  <div class="form-group">
    	<div class="input-group date form_date" data-date="" data-date-format="yyyy-mm-dd" data-link-field="dtp_input1" data-link-format="yyyy-mm-dd">
           		<input id="sel_date1" class="form-control" size="10"  placeholder="月结开始时间" type="text" value=""  >
           		<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
           		<span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
       	</div>
  </div>
  <div class="form-group">
  		<div class="input-group date form_date" data-date="" data-date-format="yyyy-mm-dd" data-link-field="dtp_input1" data-link-format="yyyy-mm-dd">
           		<input id="sel_date2" class="form-control" size="10"  placeholder="月结结束时间" type="text" value=""  >
           		<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
           		<span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
       	</div>
    
  </div>
  
  <div class="form-group">
  	<button type="button" class="btn btn-primary" id="onCheck">查 询</button>
  </div> 
  <div class="form-group">
  	<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal">数据审核</button>
  </div>
  
  <div class="form-group">
  	<button type="button" class="btn btn-warning" data-toggle="modal" data-target="#editModal">取消审核</button>
  </div>
  
  
</form>
<hr/>

<div class="row">
	<div class="col-md-10">
    	<table id="example" class="display tableExample" cellspacing="0" width="100%">
            <thead>
                    <tr>
                        <th>结算月份</th>
                        <th>结算仓库</th>
                        <th>结算人</th>
                        <th>是否审核</th>
                        <th>审核人1</th>
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
<div class="modal fade bs-example-modal-lg" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog modal-md" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h3 class="modal-title" id="myModalLabel"><b>数据月结审核</b></h3>
        
      </div>
      <div class="modal-body">
      
        	<div class="form-group form-group-sm form-inline" >
                    <div class="row">	
                        <div class="col-md-12">		
                            <label for="vessel" class="control-label">月结年月:</label>
                            
                            
                            <div class="form-group">
							  		<div class="input-group date form_date" data-date="" data-date-format="yyyy-mm-dd" data-link-field="dtp_input1" data-link-format="yyyy-mm-dd">
							           		<input id="ins_date" class="form-control" size="30"  placeholder="月结时间" type="text" value="" disabled >
							           		<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
							           		<span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
							       	</div>
							    
							  </div>
                            
                            
                        </div>
                       	
                    </div>
		    </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关 闭</button>
        <button type="button" class="btn btn-primary" id="ins_save">确认审核</button>
      </div>
    </div>
  </div>
</div>
<!--Add Modal End-->




<!-- Cancel Modal -->
<div class="modal fade bs-example-modal-lg" id="editModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog modal-md" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h3 class="modal-title" id="myModalLabel"><b>取消月结审核</b></h3>
        
      </div>
      <div class="modal-body">
      
        	<div class="form-group form-group-sm form-inline" >
                    <div class="row">	
                        <div class="col-md-12">		
                            <label for="vessel" class="control-label">月结年月:</label>
                            
                            
                            <div class="form-group">
							  		<div class="input-group date form_date" data-date="" data-date-format="yyyy-mm-dd" data-link-field="dtp_input1" data-link-format="yyyy-mm-dd">
							           		<input id="upd_date" class="form-control" size="30"  placeholder="月结时间" type="text" value="" disabled >
							           		<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
							           		<span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
							       	</div>
							    
							  </div>
                            
                            
                        </div>
                       	
                    </div>
		    </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关 闭</button>
        <button type="button" class="btn btn-primary" id="upd_save">取消审核</button>
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

<script src="../../js/bootstrap-datetimepicker.min.js"></script>
<script src="../../js/bootstrap-datetimepicker.fr.js"></script>

<script src='<%=basePath %>dwr/engine.js'></script> 
<script src='<%=basePath %>dwr/util.js'></script> 

<script src='<%=basePath %>dwr/interface/Common.js'></script>
<script src='<%=basePath %>dwr/interface/PreStatement.js'></script>
<script src='<%=basePath %>dwr/interface/JieSuan.js'></script>

<script src="js/monthSettlementCheck.js"></script>

<script type="text/javascript">

	function getStoreHouseID(){
		return "<%=session.getValue("cangKuID") %>";
	}

	function getUserID(){
		return "<%=session.getValue("userID") %>";
	}
	
	function getUserName(){
		return "<%=session.getValue("userName") %>";
	}
		
</script>

</html>
