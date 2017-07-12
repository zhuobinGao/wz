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

<title>物资进库</title>
<link rel="stylesheet" href="../../css/bootstrap.min.css">
<link rel="stylesheet" href="../../css/jquery-ui.min.css">
<link rel="stylesheet" href="../../css/jquery.dataTables.min.css">
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
  <li><a href="#">进/出库管理</a></li>
  <li class="active">进库信息调整/回退</li>
</ol>


<hr/>
<div class="row" >
	<div class="col-lg-12">
         <div class="form-group form-group-sm form-inline">
         	 <input type="checkbox" id="in_checkOther" >&nbsp;&nbsp;随机配件
         	 <input type="text" class="form-control" id="in_slbh" placeholder="收料单号" size="25" > 
         
             <button type="button" class="btn btn-primary" id="search">查询</button>
             <button type="button" class="btn btn-primary" id="update">确认修改</button>
             <button type="button" class="btn btn-primary" id="print" >打印</button>
             <button type="button" class="btn btn-primary" id="back">回退</button> 
         </div>
     </div>
</div>


<div class="row">
	
    <!-- left page -->
    <div class="col-md-4" >
    	<div class="panel panel-primary">
          <div class="panel-heading">入库录入表单</div>
          <div class="panel-body">
           			<form style="border:#CCC thin; border-width:1px;">
                            <div class="form-group form-group-sm form-inline">
                                <div class="row">
                                    <div class="col-md-6">
                                        <label  class="control-label">录单日期:</label>
                                        <div class="input-group date form_date" data-date="" data-date-format="yyyy-mm-dd" data-link-field="dtp_input1" data-link-format="yyyy-mm-dd">
		                            		<input id="sel_date1" class="form-control" size="10"  placeholder="选择日期" type="text" value=""  >
		                            		<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
		                            		<span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                       					</div>
                                        
                                        
                                    </div>
                                    
                                    <div class="col-md-6">
                                        <label  class="control-label">收料日期:</label>
                                        <div class="input-group date form_date" data-date="" data-date-format="yyyy-mm-dd" data-link-field="dtp_input1" data-link-format="yyyy-mm-dd">
		                            		<input id="sel_date2" class="form-control" size="10"  placeholder="选择日期" type="text" value=""  >
		                            		<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
		                            		<span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                       					</div>
                                        
                                        
                                    </div>
                                </div>
                             </div>
                             
                              <div class="form-group form-group-sm form-inline">
                                <label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" id="uptMater" >&nbsp;&nbsp;是否修改物资名称</label>
                             </div>
                             
                             <div class="form-group form-group-sm form-inline">
                                <div class="row">
                                     <div class="col-md-6">
                                        <label  class="control-label">物资名称:</label>
                                        <input type="text" class="form-control" id="sel_materialName" placeholder="物资名称" size="25" disabled>
                                    </div>
                                    
                                    
                                     <div class="col-md-6">
                                        <label  class="control-label">物资类别:</label>
                                        <input type="text" class="form-control" id="sel_category" placeholder="物资类别" size="25" disabled >
                                    </div>
                                
                                </div>
                             </div>
                             
                              <div class="form-group form-group-sm form-inline">
                                <div class="row">
                                     <div class="col-md-6">
                                        <label  class="control-label">物资规格:</label>
                                       
                     					 <select class="form-control" id="sel_style" style="width:170px;"  >
											<option value=""></option>
										</select>
                                    </div>
                                    
                                    
                                     <div class="col-md-6">
                                        <label  class="control-label">计量单位:</label>
                                        <input type="text" class="form-control" id="sel_size" placeholder="计量单位" size="25" disabled>
                                    </div>
                                
                                </div>
                             </div>
                             
                             
                             
                             
                             
                              <div class="form-group form-group-sm form-inline">
                                <div class="row">
                                     <div class="col-md-6">
                                        <label  class="control-label">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;数量:</label>
                                        <input type="text" class="form-control" id="sel_number" placeholder="数量" size="25" disabled>
                                    </div>
                                    
                                    
                                     <div class="col-md-6">
                                        <label  class="control-label">&nbsp;&nbsp;&nbsp;&nbsp;总金额:</label>
                                        <input type="text" class="form-control" id="sel_je" placeholder="总金额" size="25" disabled>
                                    </div>
                                
                                </div>
                             </div>
                             
                              <div class="form-group form-group-sm form-inline">
                                <div class="row">
                                     <div class="col-md-6">
                                        <label  class="control-label">&nbsp;&nbsp;&nbsp;&nbsp;收料人:</label>
                                        <select class="form-control" id="sel_slr" style="width:170px;" >
											<option value=""></option>
										</select>
                                    </div>
                                    
                                    
                                     <div class="col-md-6">
                                        <label  class="control-label">&nbsp;&nbsp;&nbsp;&nbsp;采购人:</label>
                                         <select class="form-control" id="sel_cgr" style="width:170px;" >
											<option value=""></option>
										</select>
                                    </div>
                                </div>
                             </div>
                             
                             <div class="form-group form-group-sm form-inline">
                                <label  class="control-label">发票编号:</label>
                                <input type="text" class="form-control" id="sel_fpbh" placeholder="发票编号" size="50" value="-" disabled >
                                <input name="hasFpbh" id="hasFpbh" type="checkbox" value="" />是否含发票 
                             </div>
                             
                              <div class="form-group form-group-sm form-inline">
                                <label  class="control-label">供应单位:</label>
                                <input type="text" class="form-control" id="sel_gydw" placeholder="供应单位" size="75" >
                             </div>
                             
                              <div class="form-group form-group-sm form-inline">
                                <label  class="control-label">费用项目:</label>
                                <input type="text" class="form-control" id="sel_fyxm" placeholder="费用项目" size="75" >
                             </div>
                             
                              <div class="form-group form-group-sm form-inline">
                                <label  class="control-label">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;仓位:</label>
                                <input type="text" class="form-control" id="sel_postion" placeholder="仓位" size="75" >
                             </div>
                             
                              <div class="form-group form-group-sm form-inline">
                                <label  class="control-label">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;附注:</label>
                                <input type="text" class="form-control" id="sel_fz" placeholder="附注" size="75" >
                             </div>
                             
                             
         </form>
          </div>
        </div>
    
    
    	
    </div>
    
    
    
    
    
    
    
    
     <!-- right page -->
    <div class="col-md-7">
    
    	<div class="panel panel-primary">
              <div class="panel-heading">收料单</div>
              <div class="panel-body">
    	
       			 <div style="width:780px; margin:0 auto;  text-align:center;table-layout:fixed; padding:10px;" id="divToPrint" >
                    <p style="font-size:20px; margin-top:30px;">
                        <b>湛&nbsp;&nbsp;江&nbsp;&nbsp;港&nbsp;&nbsp;国&nbsp;&nbsp;际&nbsp;&nbsp;集&nbsp;&nbsp;装&nbsp;&nbsp;箱&nbsp;&nbsp;码&nbsp;&nbsp;头&nbsp;&nbsp;有&nbsp;&nbsp;限&nbsp;&nbsp;公&nbsp;&nbsp;司</b>
                    </p>
           
            
                    <table width="100%" style="table-layout:fixed;">
                        <tr>
                            <td width="35%" style="word-wrap:break-word;" ><p style="font-size:16px;text-algin:left;">供应单位&nbsp;:&nbsp;<span id="pin_gydw"></span></p></td>
                            <td width="30%" style="text-align:center;"><p style="font-size:30px;"><b>收&nbsp;料&nbsp;单</b></p></td>
                            <td width="35%" style="padding-left:40px;">
                                <ul style="text-decoration:none; list-style:none">
                                    <li>收料编号&nbsp;:&nbsp;<span id="pin_slbh"></span></li>
                                    <li style="margin-top:12px;">收料仓库&nbsp;:&nbsp;<span>宝满物资仓</span></li>
                                </ul>
                            </td>
                        </tr>
                        
                        <tr>
                            <td width="35%" style="word-wrap:break-word;" ><p style="font-size:16px;"><span style="display:inline-block">发票编号&nbsp;:&nbsp;</span><span  id="pin_fpbh"></span></p></td>
                            <td width="30%" style="text-align:center;"><p style="font-size:16px;">填单日期&nbsp;:&nbsp;<span id="pin_date1"></span></p></td>
                            <td width="35%" style="padding-left:40px;">
                                <ul style="text-decoration:none; list-style:none">
                                    <li>收料日期&nbsp;:&nbsp;<span id="pin_date2"></span></li>
                                </ul>
                        </tr>
                    </table>
                    
                    <table width="100%" border="1px" id="sTable" style="table-layout:fixed;" >
                        <tr>
                            <td width="90px" height="50px"  rowspan="2" style="text-align:center" >材料编号</td>
                            <td width="150px" rowspan="2" style="text-align:center">材料名称</td>
                            <td width="95px" rowspan="2" style="text-align:center" >规格</td>
                            <td width="40px" rowspan="2" style="text-align:center" >计量单位</td>
                            <td colspan="2" style="text-align:center" >数量</td>
                            <td colspan="2" style="text-align:center" >实际价格</td>
                        </tr>
                        
                        <tr>
                            <td style="text-align:center" >应收</td>
                            <td style="text-align:center" >实收</td>
                            <td style="text-align:center" >单价</td>
                            <td style="text-align:center" >金额</td>
                        </tr>
                        
                        <tr>
                            <td width="90px" height="92px" style="text-align:center" id="pin_clbh"></td>
                            <td width="150px" style="text-align:center; word-wrap:break-word;"  id="pin_materialName"></td>
                            <td width="95px" style="text-align:center; word-wrap:break-word;" id="pin_style" ></td>
                            <td width="40px" style="text-align:center; word-wrap:break-word;" id="pin_size" ></td>
                            
                            <td width="96px" style="text-align:center; word-wrap:break-word;" id="pin_ys" ></td>
                            <td width="96px" style="text-align:center; word-wrap:break-word;" id="pin_ss" ></td>
                            <td width="96px" style="text-align:center; word-wrap:break-word;" id="pin_dj" ></td>
                            <td width="96px" style="text-align:center; word-wrap:break-word;" id="pin_je" style=""></td>
                        </tr>
                        
                        <tr>
                            <td height="70px" style="text-align:center">费用项目</td>
                            <td colspan="2" style="text-align:center"  id="pin_fyxm" style="word-wrap:break-word;"></td>
                            <td colspan='2' style="text-align:center">附注</td>
                            <td colspan="3" style="text-align:center"  id="pin_fz" style="word-wrap:break-word;"></td>
                        </tr>
                        
                    
                    </table>
                    
                    
                     <table width="100%" style="margin-top:5px;">
                        <tr>
                            <td width="35%">供应部门(章):</td>
                            <td width="25%">仓库收料:<span id="pin_slr"></span></td>
                            <td width="20%">采购:<span id="pin_cgr"></span></td>
                            <td width="20%">提运:</td>
                        </tr>
                        <tr>
                            <td></td>
                            <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;签名:</td>
                            <td>签名:</td>
                            <td>签名:</td>
                        </tr>
                        
                        
                     </table>
            		<p style="text-align:left; margin-top:15px;">使用须知:①财务报销(白色联)，&nbsp;&nbsp;②采购查询(红色联)，&nbsp;&nbsp;③领料部门查询(黄色联)。</p>
            
        		</div>
    		  </div>
        </div>
    	   
    
    
    
    </div>
    
   
</div>






</div>


	



<input type="hidden" id="sel_materialID" value="" />
<input type="hidden" id="sel_categoryID" value="" />
<input type="hidden" id="receiveID" value="" />
<input type="hidden" id="isOther" value="" />


</body>
<script src="../../js/jquery-1.12.0.min.js"></script>
<script src="../../js/bootstrap.min.js"></script>
<script src="../../js/jquery-ui.min.js"></script>
<script src="../../js/jquery.dataTables.min.js"></script>
<script src="../../js/jquery-migrate-1.1.0.min.js"></script>
<script src="../../js/jquery.jqprint-0.3.js"></script>
<script src="../../js/bootstrap-datetimepicker.min.js"></script>
<script src="../../js/bootstrap-datetimepicker.fr.js"></script>

<script src='<%=basePath %>dwr/engine.js'></script> 
<script src='<%=basePath %>dwr/util.js'></script> 

<script src='<%=basePath %>dwr/interface/Common.js'></script>
<script src='<%=basePath %>dwr/interface/PreStatement.js'></script>
<script src='<%=basePath %>dwr/interface/InAndOutCheck.js'></script> 
<script src='<%=basePath %>dwr/interface/SendAndReceive.js'></script>
<script type="text/javascript" src="js/inWarehouseEdit.js"></script> 




<script type="text/javascript">

	var RECEIPT_HASINVOICE = 0;
	var eidtMaterial = 0;
	
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
	

	$('#print').click(function(){
		$("#divToPrint").jqprint();
	});
	
	
	function getreceiveID(){
		var param = {
			sqlID: "sys_05",
			strCount:0
		};
		dwr.engine.setAsync(false);
		PreStatement.getTabeData(param,function(data){
			$("#receiveID").val(data[0]);
			console.info($("#receiveID").val());
		});
	}

	
	

	$('#saveIn').click(function(){
		 
		
		if($("#sel_date1").val()==""){
			alert("录单日期不能为空");
			return;
		}
		
		if($("#sel_date2").val()==""){
			alert("收料日期不能为空");
			return;
		}
		
		if($("#sel_materialName").val()==""){
			alert("物资名称不能为空");
			return;
		}
		
		if($("#sel_style").val()==""){
			alert("物资规格不能为空");
			return;
		}
		
		if(isNaN($("#sel_number").val())){
			alert("数量请输入数字");
			return;
		}
		
		if($("#sel_number").val()<=0){
			alert("数量必须大于0");
			return;
		}
		 
		if(isNaN($("#sel_je").val())){
			alert("金额请输入数字");
			return;
		}
		
		if($("#sel_je").val()<=0){
			alert("金额必须大于0");
			return;
		} 
		
		if($("#sel_cgr").val()==""){
			alert("采购人不能为空");
			return;
		}
		
		if($("#sel_slr").val()==""){
			alert("收料人不能为空");
			return;
		}
		
		if($("#sel_fpbh").val()==""){
			alert("发票编号不能为空");
			return;
		}
		
		if($("#sel_slr").val()==""){
			alert("供应单位不能为空");
			return;
		}
		if($("#sel_materialID").val()==""){
			alert("未能获取正确的物资信息，请重新操作！");
			return;
		}
		
		
	});
		
		

	 $("#reset").click(function(){
	 	 window.location.href="inWarehouseEdit.jsp";
	 	
	 });
	

		
</script>

</html>
