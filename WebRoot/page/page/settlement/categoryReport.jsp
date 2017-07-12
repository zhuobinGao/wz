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


<title>类别结余明细</title>
<link rel="stylesheet" href="../../css/bootstrap.min.css">
<link rel="stylesheet" href="../../css/jquery-ui.min.css">
<link rel="stylesheet" href="../../css/jquery.dataTables.min.css">
<link rel="stylesheet" href="../../css/buttons.dataTables.min.css">
<link rel="stylesheet" href="../../css/bootstrap-datetimepicker.min.css" media="screen">
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
      <li><a href="#">财务管理</a></li>
      <li class="active">类别结余明细</li>
    </ol>
    
    
    <hr/>
    
    <div class="row" >
	<div class="col-lg-12">
         <div class="form-group form-group-sm form-inline">
         	 <label>查询仓库</label>
         	 <select class="form-control" id="sel_cangKu"></select>
         	 &nbsp;&nbsp;&nbsp;
             <div class="input-group date form_date" data-date="" data-date-format="yyyy-mm-dd" data-link-field="dtp_input1" data-link-format="yyyy-mm-dd">
           		<input id="sel_date1" class="form-control" size="10"  placeholder="选择年月" type="text" value=""  >
           		<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
           		<span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
       		</div>
       		
             <button type="button" class="btn btn-primary" id="selCheck">查 询</button>
          
         </div>
     </div>
	</div>


	<div class="row" >
    	<div class="col-md-9">
        	<div class="panel panel-default">
              <div class="panel-heading">类别结余明细</div>
                  <div class="panel-body">
                        <table id="example" class="display nowrap tableExample" cellspacing="0" width="100%">
                            <thead>
                                    <tr>
                                        <th rowspan="2">行号</th>
                                        <th rowspan="2">类别编号</th>
                                        <th rowspan="2">大类名称</th>
                                        <th rowspan="2">直属类别</th>
                                        <th colspan="2" style="text-align:center;">期初结存</th>
                                        <th colspan="2" style="text-align:center;">进库</th>
                                        <th colspan="2" style="text-align:center;">出库</th>
                                        <th colspan="2" style="text-align:center;">期末结存</th>
                                    </tr>
                                    <tr>
                                        <th>期初数量</th>
                                        <th>期初金额</th>
                                        <th>进库数量</th>
                                        <th>进库金额</th>
                                        <th>出库数量</th>
                                        <th>出库金额</th>
                                        <th>期末数量</th>
                                        <th>期末金额</th>
                                    </tr>
                            </thead>
                            
                            <tbody>
                                
                            </tbody>
            
                        </table>
                  </div>
               </div>
            
        </div>
        
        
        
        <div class="col-md-3">
        	<div class="panel panel-default">
              <div class="panel-heading">统计信息</div>
              
                    <ul class="list-group">
                       <li class="list-group-item">期初总金额:&nbsp;&nbsp;<span class="style_je" id="s_start"></span></li>
                      <li class="list-group-item">进库总金额:&nbsp;&nbsp;<span class="style_je" id="s_in"></span></li>
                      <li class="list-group-item">出库总金额:&nbsp;&nbsp;<span class="style_je" id="s_out"></span></li>
                      <li class="list-group-item">期末总金额:&nbsp;&nbsp;<span class="style_je" id="s_has"></span></li>
                      
                    </ul>
                    
              </div>
        
        </div>
        
        
        
        
    </div>
	









</div>


	








</body>
<script src="../../js/jquery-1.12.0.min.js"></script>
<script src="../../js/bootstrap.min.js"></script>
<script src="../../js/jquery-ui.min.js"></script>
<script src="../../js/jquery.dataTables.min.js"></script>

<script src="../../js/jquery-migrate-1.1.0.min.js"></script>
<script src="../../js/jquery.jqprint-0.3.js"></script>

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

<script type="text/javascript" src="js/categoryReport.js"></script> 



<script type="text/javascript">

	function getCangKu(){
		return "<%=session.getAttribute("cangKuID") %>";
	}



		
</script>

</html>
