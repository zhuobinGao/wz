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


<title>月末库存表</title>
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
      <li class="active">月末库存表</li>
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
    	<div class="col-md-12">
        	<div class="panel panel-default">
              <div class="panel-heading">月末库存表</div>
                  <div class="panel-body">
                        <table id="example" class="display nowrap tableExample" cellspacing="0" width="100%">
                            <thead>
                                     <tr>
                                        
                                        <th>供货单位</th>
                                        <th>所属类别</th>
                                        <th>材料名称</th>
                                        <th>规格型号</th>
                                        <th>数量</th>
                                        <th>计量单位</th>
                                        
                                        <th>单价</th>
                                        <th>金额</th>
                                        <th>仓位</th> 
                                        <th>年限</th> 
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

<script type="text/javascript" src="js/monthStoreReport.js"></script> 


<script type="text/javascript">

	
 function getCangKu(){
		return "<%=session.getAttribute("cangKuID") %>";
	}



		
</script>

</html>
