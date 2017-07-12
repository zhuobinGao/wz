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


<title>预警设计监控</title>
<link rel="stylesheet" href="../../css/bootstrap.min.css">
<link rel="stylesheet" href="../../css/jquery-ui.min.css">
<link rel="stylesheet" href="../../css/jquery.dataTables.min.css">
<link rel="stylesheet" href="../../css/buttons.dataTables.min.css">



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
      <li><a href="#">仓存管理</a></li>
      <li class="active">预警设计监控</li>
    </ol>
    
    
    <hr/>
    
    <div class="row" >
	<div class="col-lg-12">
         <div class="form-group form-group-sm form-inline">
             <button type="button" class="btn btn-primary" id="refreash" >刷新</button>
          
         </div>
     </div>
	</div>
    
    
    <div class="row">
    	
        <div class="col-md-12">
        	<div class="panel panel-primary">
                  <div class="panel-heading">库存预警</div>
                  <div class="panel-body">
                   		 <table id="example" class="display tableExample" cellspacing="0" width="98%">
                            <thead>
                                    <tr>
                                        <th>物资编号</th>
                                        <th>物资名称</th>
                                        <th>物资规格</th>
                                        <th>计量单位</th>
                                      
                                        <th>库存数量</th>
                                        <th>最小库存</th>
                                        <th>最大库存</th>
                                        <th>下限预警</th>
                                        <th>上限预警</th>
                                        <th>仓库位置</th>
                                    </tr>
                            </thead>
                            
                            <tfoot>
                            	<tr>
                                        <th>物资编号</th>
                                        <th>物资名称</th>
                                        <th>物资规格</th>
                                        <th>计量单位</th>
                                      
                                        <th>库存数量</th>
                                        <th>最小库存</th>
                                        <th>最大库存</th>
                                        <th>下限预警</th>
                                        <th>上限预警</th>
                                        <th>仓库位置</th>
                                    </tr>
                            </tfoot>
                            
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
<script src="js/checkStoreLine.js?v=1.4"></script>


<script type="text/javascript">

	
 function getStoreHouseID(){
		return "<%=session.getValue("cangKuID") %>";
 }



		
</script>

</html>
