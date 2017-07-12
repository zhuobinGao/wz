<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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


<title>物资进库</title>
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
  
  div.container {
        width: 98%;
    }



</style>



</head>

<body>

<div class="container-fluid" style="padding:10px;">

    <ol class="breadcrumb">
      <li><a href="#">仓库云</a></li>
      <li><a href="#">进/出库管理</a></li>
      <li class="active">归档进库明细</li>
    </ol>
    
    
    <hr/>
    
    <div class="panel panel-default">
      <div class="panel-heading">进库查询条件</div>
      <div class="panel-body" >
        
        <div class="row" >
            <form style=" margin-left:15px; margin-bottom:0;">
                <div class="form-group form-group-sm form-inline" style="margin-bottom:0">
                    
                    
                    <div class="row" >
                        <div class="col-md-2">
                            <label  class="control-label">&nbsp;&nbsp;&nbsp;&nbsp;采购人:</label>
                            
                            <select class="form-control" id="sel_cgr" style="width:170px;"  >
								<option value=""></option>
							</select>
                            
                            
                            
                        </div>
                        
                        <div class="col-md-2">
                            <label  class="control-label">&nbsp;&nbsp;&nbsp;&nbsp;收料人:</label>
                            <select class="form-control" id="sel_slr" style="width:170px;" >
								<option value=""></option>
							</select>
                            
                            
                        </div>
                        
                         <div class="col-md-2">
                            <label  class="control-label">仓库位置:</label>
                            <input type="text" class="form-control" id="sel_postion" placeholder="仓库位置" size="25" >
                        </div>
                        
                         <div class="col-md-2">
                            <label  class="control-label">费用项目:</label>
                            <input type="text" class="form-control" id="sel_fyxm" placeholder="费用项目" size="25" >
                        </div>
                        
                    </div>
                    
                    <div class="row" style="margin-top:10px;">
                        <div class="col-md-2">
                            <label  class="control-label">物资名称:</label>
                            <input type="text" class="form-control" id="sel_materialName" placeholder="物资名称" size="25" >
                        </div>
                        
                        <div class="col-md-2">
                            <label  class="control-label">物资类别:</label>
                            <input type="text" class="form-control" id="sel_category" placeholder="物资类别" size="25" >
                        </div>
                        
                         <div class="col-md-2">
                            <label  class="control-label">发票编号:</label>
                            <input type="text" class="form-control" id="sel_fpbh" placeholder="发票编号" size="25" >
                        </div>
                        
                         <div class="col-md-2">
                            <label  class="control-label">收料编号:</label>
                            <input type="text" class="form-control" id="sel_slbh" placeholder="收料编号" size="25" >
                        </div>
                        
                    </div>
                    
                    
                    <div class="row" style="margin-top:10px;">
                         <div class="col-md-2">
                            <label  class="control-label">供应单位:</label>
                            <input type="text" class="form-control" id="sel_gydw" placeholder="供应单位" size="25" >
                         </div>
                         
                         <div class="col-md-2">
                         	<label  class="control-label">结算日期:</label>
                         	<div class="input-group date form_date ym" data-date="" data-date-format="yyyy-mm-dd" data-link-field="dtp_input1" data-link-format="yyyy-mm-dd">
				           		<input id="sel_caldate" class="form-control" size="10"  placeholder="选择年月" type="text" value=""  >
				           		<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
				           		<span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
				       		</div>
                         </div>
                         
                         <div class="col-md-4">
                            <label  class="control-label">收料日期:</label>
                            <div class="input-group date form_date ymd" data-date="" data-date-format="yyyy-mm-dd" data-link-field="dtp_input1" data-link-format="yyyy-mm-dd">
	                            <input id="sel_date1" class="form-control" size="10"  placeholder="选择日期" type="text" value=""  >
	                            <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
	                            <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                       		</div>
                            
                            
                            <label  class="control-label">至</label>
                            <div class="input-group date form_date ymd" data-date="" data-date-format="yyyy-mm-dd" data-link-field="dtp_input1" data-link-format="yyyy-mm-dd">
	                            <input id="sel_date2" class="form-control" size="10"  placeholder="选择日期" type="text" value=""  >
	                            <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
	                            <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                       		</div>
                         </div>
                         
                         <div class="col-md-2">
                             <button type="button" class="btn btn-primary" id="searchInButton" autocomplete="off" data-loading-text="查询中...">查  询</button>
                             <button type="button" class="btn btn-primary" id="myButton" autocomplete="off" data-loading-text="查询中...">打  印</button>
                         </div>
                    </div>
                    
               </div>
            </form>
        </div>
    
      </div>
    </div>


	<div class="row">
    
    	<div class="col-md-12">
        	<div class="panel panel-default">
              <div class="panel-heading">进库查询条件</div>
              <div class="panel-body" >
               
            
                    <table id="example" class="display  nowrap tableExample" cellspacing="0" width="100%" style="margin-top:10px;">
                        <thead>
                                <tr>
                                    <th>收料编号</th>
                                    <th>物资编号</th>
                                    <th>物资名称</th>
                                    <th>物资规格</th>
                                    <th>计量单位</th>
                                    <th>收料数量</th>
                                    <th>剩余数量</th>
                                    <th>单价</th>
                                    <th>金额</th>
                                    <th>填单日期</th>
                                    <th>收料日期</th>
                                    <th>发票编号</th>
                                    <th>收料人</th>
                                    <th>采购人</th>
                                    <th>收料仓库</th>
                                    <th>供应单位</th>
                                    <th>费用项目</th>
                                    <th>仓库位置</th>
                                    <th>所属类别</th>
                                    <th>附注</th>
                                    
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

<script src='<%=basePath %>dwr/util.js'></script> 
<script src='<%=basePath %>dwr/engine.js'></script> 
<script src='<%=basePath %>dwr/interface/Common.js'></script>
<script src='<%=basePath %>dwr/interface/InAndOutCheck.js'></script> 
 <script type="text/javascript" src="js/searchIn.js"></script> 

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
	
	function check(){
		if(getStoreHouseID()=="null" || getStoreHouseID()==""){
			return true;
		}
		if(getUserID()=="null" || getUserID()==""){
			return true;
		}
		if(getUserName()=="null" || getUserName()==""){
			return true;
		}
		return false;
	}


		
</script>

</html>
