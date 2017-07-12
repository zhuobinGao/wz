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


<title>库存查询</title>
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
  
  

	th, td { white-space: nowrap; }
    div.dataTables_wrapper {
        width: 98%;
        margin: 0 auto;
    }


</style>



</head>

<body>

<div class="container-fluid" style="padding:10px;">

    <ol class="breadcrumb">
      <li><a href="#">仓库云</a></li>
      <li><a href="#">仓存管理</a></li>
      <li class="active">库存查询</li>
    </ol>
    
    
    <hr/>
    
    <div class="panel panel-primary">
      <div class="panel-heading">查询条件</div>
      <div class="panel-body" >
        
        <div class="row" style="display:none" >
            <form style=" margin-left:15px; margin-bottom:0;">
                <div class="form-group form-group-sm form-inline" style="margin-bottom:0">
                    
                    
                    <div class="row" >
                        <div class="col-md-2">
                            <label  class="control-label">&nbsp;&nbsp;&nbsp;&nbsp;采购人:</label>
                            <input type="text" class="form-control" id="f_vessel" placeholder="采购人" size="25" >
                        </div>
                        
                        <div class="col-md-2">
                            <label  class="control-label">&nbsp;&nbsp;&nbsp;&nbsp;收料人:</label>
                            <input type="text" class="form-control" id="f_vessel" placeholder="收料人" size="25" >
                        </div>
                        
                         <div class="col-md-2">
                            <label  class="control-label">仓库位置:</label>
                            <input type="text" class="form-control" id="f_vessel" placeholder="仓库位置" size="25" >
                        </div>
                        
                         <div class="col-md-2">
                            <label  class="control-label">发票编号:</label>
                            <input type="text" class="form-control" id="f_vessel" placeholder="费用项目" size="25" >
                        </div>
                        
                    </div>
                    
                    <div class="row" style="margin-top:10px;">
                        <div class="col-md-2">
                            <label  class="control-label">&nbsp;&nbsp;&nbsp;&nbsp;领料人:</label>
                            <input type="text" class="form-control" id="f_vessel" placeholder="领料人" size="25" >
                        </div>
                        
                        <div class="col-md-2">
                            <label  class="control-label">领料单位:</label>
                            <input type="text" class="form-control" id="f_vessel" placeholder="领料单位" size="25" >
                        </div>
                        
                         <div class="col-md-2">
                            <label  class="control-label">物资名称:</label>
                            <input type="text" class="form-control" id="f_vessel" placeholder="发票编号" size="25" >
                        </div>
                        
                         <div class="col-md-2">
                            <label  class="control-label">物资规格:</label>
                            <input type="text" class="form-control" id="f_vessel" placeholder="仓库位置" size="25" >
                        </div>
                        
                    </div>
                    
                    
                    <div class="row" style="margin-top:10px;">
                         <div class="col-md-4">
                            <label  class="control-label">供应单位:</label>
                            <input type="text" class="form-control" id="f_vessel" placeholder="供应单位" size="60" >
                         </div>
                         
                        
                         
                         
                    </div>
                    
               </div>
            </form>
        </div>
    	
    	<div class="col-md-1">
    		<select id="kc" class="form-control">
    			<option value='0.000001'>含库存</option>
    			<option value='-1'>包含0库存</option>
    		</select>
    	</div>
    	
    	<div class="col-md-1">
            <button type="button" class="btn btn-primary" id="onSearch" >查  询</button>        
        </div>
    	
      </div>
    </div>


	<div class="row" >
    	<div class="col-md-12">
        	<div class="panel panel-primary">
              <div class="panel-heading">库存情况</div>
                  <div class="panel-body">
                        <table id="example" class="display tableExample stripe row-border order-column" cellspacing="0" width="100%">
                            <thead>
                                    <tr>
                                    	<th>行号</th>
                                        <th>供应单位</th>
                                        <th>类别名称</th>
                                        <th>物资编号</th>
                                        <th>物资名称</th>
                                        <th>物资规格</th>
                                        <th>计量单位</th>
                                        <th>库存数量</th>
                                        <th>单价</th>
                                        <th>金额</th>
                                        <th>仓位</th>
                                        <th>年限</th>
                                        <th>操作</th>
                                    </tr>
                            </thead>
                            
                            <tfoot>
					            <tr>
					            	<th>行号</th>
					                <th>供应单位</th>
                                    <th>类别名称</th>
                                    <th>物资编号</th>
                                    <th>物资名称</th>
                                    <th>物资规格</th>
                                    <th>计量单位</th>
                                    <th>库存数量</th>
                                    <th>单价</th>
                                    <th>金额</th>
                                    <th>仓位</th>
                                    <th>年限</th>
                                    <th>操作</th>
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
<script src="../../js/dataTables.fixedColumns.min.js"></script>
<script src="js/checkStore.js?v=1.6" ></script>


<script type="text/javascript">

	function getStoreHouseID(){
		return "<%=session.getValue("cangKuID") %>";
	}
	
 



		
</script>

</html>
