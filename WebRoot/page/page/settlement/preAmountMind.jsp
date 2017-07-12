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
  <li><a href="#">财务管理</a></li>
  <li class="active">预付款预警管理</li>
</ol>

<form class="form-inline">
  <div class="form-group">
    <label class="sr-only" for="exampleInputEmail3">是否开通</label>
    <input type="text" class="form-control" id="s_productName" placeholder="物资名称(物资编号)">
  </div>
  
  
  <div class="form-group">
    <label class="sr-only" for="exampleInputPassword3">物资类别</label>
     <input type="text" class="form-control" id="s_category" placeholder="物资类别">
  </div>
  
  <div class="form-group">
  	<button type="button" class="btn btn-primary" id="mCheck">查 询</button>
  </div>
	
 
  
  
</form>
<hr/>

<div class="row">
	<div class="col-lg-12">
    
    	<div class="panel panel-primary">
          <div class="panel-heading">物资明细管理</div>
          <div class="panel-body"  >
                <table id="exampleMaterial" class="display nowrap tableExample" cellspacing="0" width="100%">
                    <thead>
                            <tr>
                                <th>ID</th>
                                <th>微信号</th>
                                <th>申请人</th>
                                <th>申请时间</th>
                                <th>预警单位</th>
                                <th>预警金额</th>
                               
                               
                                <th>账户金额</th>
                                <th>是否开启预警</th>
                                
                                <th>预警发送次数</th>
                                <th>最近发送时间</th>
                                <th>审批人</th>
                                <th>审批时间</th>
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
<!-- container end -->








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
