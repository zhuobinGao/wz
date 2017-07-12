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

<title>物资出库</title>
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
  <li class="active">物资出库</li>
</ol>


<hr/>
<div class="row" >
	<div class="col-lg-12">
         <div class="form-group form-group-sm form-inline">
             <button type="button" class="btn btn-primary" id="saveSend" >确认出库</button>
             <button type="button" class="btn btn-primary" id="print">打印出库单</button>
             <button type="button" class="btn btn-primary" id="reset" >重置</button> 
         </div>
     </div>
</div>

<%
	String date1 = StringUtil.nullToEmpty(request.getParameter("date1"));
	String date2 = StringUtil.nullToEmpty(request.getParameter("date2"));
	
	System.out.println("=========================================>"+date1+"|"+date2);
	date1 = "".equals(date1) ? StringUtil.getToday() : date1;
	date2 = "".equals(date2) ? StringUtil.getToday() : date2;
 %>

<div class="row">
	
    <!-- left page -->
    <div class="col-md-4" >
    	<div class="panel panel-primary">
          <div class="panel-heading">出库录入表单</div>
          <div class="panel-body">
           			<form style="border:#CCC thin; border-width:1px;">
                            <div class="form-group form-group-sm form-inline">
                                <div class="row">
                                    <div class="col-md-6">
                                        <label  class="control-label">录单日期:</label>
                                     	 <div class="input-group date form_date" data-date="" data-date-format="yyyy-mm-dd" data-link-field="dtp_input1" data-link-format="yyyy-mm-dd">
		                            		<input id="sel_date1" class="form-control" size="10"  placeholder="选择日期" type="text" value="<%=date1%>"  >
		                            		<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
		                            		<span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                       					</div>   
                                        
                                     </div>
                                    
                                    <div class="col-md-6">
                                        <label  class="control-label">领料日期:</label>
                                        <div class="input-group date form_date" data-date="" data-date-format="yyyy-mm-dd" data-link-field="dtp_input1" data-link-format="yyyy-mm-dd">
		                            		<input id="sel_date2" class="form-control" size="10"  placeholder="选择日期" type="text" value="<%=date2 %>"  >
		                            		<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
		                            		<span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                       					</div>
                                    </div>
                                </div>
                             </div>
                             
                             <div class="form-group form-group-sm form-inline">
                                <label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" id="checkOther">&nbsp;&nbsp;是否为随机配件</label>
                             </div>
                             
                             <div class="form-group form-group-sm form-inline">
                                <label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" id="otherSave" >&nbsp;&nbsp;是否代保管</label>
                             </div>
                             
                             <div class="form-group form-group-sm form-inline">
                                <label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" id="hasFP" checked="checked" >&nbsp;&nbsp;是否含发票</label>
                             </div>
                             
                             <div class="form-group form-group-sm form-inline">
                                <div class="row">
                                     <div class="col-md-6">
                                        <label  class="control-label">物资名称:</label>
                                        <input type="text" class="form-control" id="sel_materialName" placeholder="物资名称" size="25" >
                                    </div>
                                    
                                    
                                     <div class="col-md-6">
                                        <label  class="control-label">物资规格:</label>
                                     	<select class="form-control" id="sel_style" style="width:170px;" >
											<option value=""></option>
										</select>
                                     </div>
                                
                                </div>
                             </div>
                             
                             <div class="form-group form-group-sm form-inline">
                                <div class="row">
                                     <div class="col-md-6">
                                        <label  class="control-label">库存数量:</label>
                                        <input type="text" class="form-control" id="sel_allNumber" placeholder="库存数量" size="25" disabled>
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
                                        <label  class="control-label">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;请领:</label>
                                        <input type="text" class="form-control" id="sel_ql" placeholder="请领" size="25" >
                                    </div>
                                    
                                    
                                     <div class="col-md-6">
                                        <label  class="control-label">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;实发:</label>
                                        <input type="text" class="form-control" id="sel_sf" placeholder="实发" size="25" >
                                    </div>
                                
                                </div>
                             </div>
                             
                             <!--
                              <div class="form-group form-group-sm form-inline">
                                <label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox">&nbsp;&nbsp;是否为随机配件</label>
                             </div>
                             -->
                             
                             
                              <div class="form-group form-group-sm form-inline">
                                <div class="row">
                                     <div class="col-md-6">
                                        <label  class="control-label">单位成本:</label>
                                        <input type="text" class="form-control" id="sel_dj" placeholder="单位成本" size="25" disabled>
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
                                        <label  class="control-label">&nbsp;&nbsp;&nbsp;&nbsp;发料人:</label>
                                         <select class="form-control" id="sel_flr" style="width:170px;" >
											<option value=""></option>
										</select>
                                    </div>
                                    
                                    
                                     <div class="col-md-6">
                                        <label  class="control-label">&nbsp;&nbsp;&nbsp;&nbsp;领料人:</label>
                                        <select class="form-control" id="sel_llr" style="width:170px;" >
											<option value=""></option>
										</select>
                                    </div>
                                </div>
                             </div>
                             
                              <div class="form-group form-group-sm form-inline">
                                <div class="row">
                                     <div class="col-md-6">
                                        <label  class="control-label">领料主管:</label>
                                      	 <select class="form-control" id="sel_llzg" style="width:170px;" >
											<option value=""></option>
										</select>  
                                      </div>
                                    
                                    
                                     <div class="col-md-6">
                                        <label  class="control-label">领料单位:</label>
                                        <select class="form-control" id="sel_lldw" style="width:170px;" >
											<option value=""></option>
										</select>
                                    </div>
                                </div>
                             </div>
                             
                            
                             <div class="form-group form-group-sm form-inline">
                                <label  class="control-label">费用项目:</label>
                                <input type="text" class="form-control" id="sel_fyxm" placeholder="费用项目" size="75" >
                             </div>
                             
                             <div class="form-group form-group-sm form-inline">
                                <label  class="control-label">机种机构:</label>
                              
                                <select class="form-control" id="sel_jz" >
                                	<option value="-1"></option>
                                	<option value="01">门机</option>
                            		<option value="02">岸桥</option>
                            		<option value="03">场桥</option>
                                </select>
                                <select class="form-control" id="sel_jzjg">
                                
                                </select>
                             </div>
                            
                             
                              <div class="form-group form-group-sm form-inline">
                                <label  class="control-label">详细用途:</label>
                                <input type="text" class="form-control" id="sel_fz" placeholder="详细用途" size="75" >
                             </div>
                             
                             
         </form>
          </div>
        </div>
    
    
    	
    </div>
    

    
     <!-- right page -->
    <div class="col-md-7">
    	<div class="panel panel-primary">
          <div class="panel-heading">出库单</div>
          <div class="panel-body">
       		 <div style="width:780px; margin:0 auto; text-align:center;table-layout:fixed; padding:10px;" id="divToPrint" >
        	<p style="font-size:20px; margin-top:30px;">
            	<b>湛&nbsp;&nbsp;江&nbsp;&nbsp;港&nbsp;&nbsp;国&nbsp;&nbsp;际&nbsp;&nbsp;集&nbsp;&nbsp;装&nbsp;&nbsp;箱&nbsp;&nbsp;码&nbsp;&nbsp;头&nbsp;&nbsp;有&nbsp;&nbsp;限&nbsp;&nbsp;公&nbsp;&nbsp;司</b>
            </p>
           
            
            <table width="100%">
            	<tr>
                	<td width="35%" style="word-wrap:break-word;" ><p style="font-size:16px;">领料单位&nbsp;:&nbsp;<span id="pin_lldw"></span></p></td>
                    <td width="30%" style="text-align:center;"><p style="font-size:30px;"><b>领&nbsp;料&nbsp;单</b></p></td>
                    <td width="35%" style="padding-left:40px;">
                    	<ul style="text-decoration:none; list-style:none">
                    		<li>领料编号&nbsp;:&nbsp;<span id="pin_llbh"></span></li>
							<li style="margin-top:12px;">领料仓库&nbsp;:&nbsp;<span>宝满物资仓</span></li>
                    	</ul>
                    </td>
                </tr>
                
                <tr>
                	<td width="35%" style="word-wrap:break-word;" ><p style="font-size:16px;">(加盖公章)&nbsp;&nbsp;<span></span></p></td>
                    <td width="30%" style="text-align:center;"><p style="font-size:16px;">填单日期&nbsp;:&nbsp;<span id="pin_date1"><%=date1 %></span></p></td>
                    <td width="35%" style="padding-left:40px;">
                    	<ul style="text-decoration:none; list-style:none">
                    		<li>领料日期&nbsp;:&nbsp;<span id="pin_date2"><%=date2 %></span></li>
                    	</ul>
                </tr>
            </table>
            
            <table width="100%" border="1px" id="sTable" style="table-layout:fixed;">
            	<tr>
                	<td height="92px" rowspan="2" style="text-align:center">材料编号</td>
                    <td width="150px"  rowspan="2" style="text-align:center">材料名称</td>
                    <td width="90px" rowspan="2" style="text-align:center">规格</td>
                    <td width="40px" rowspan="2" style="text-align:center">计量单位</td>
                    <td width="220px" colspan="3" style="text-align:center">数量</td>
                    <td rowspan="2" style="text-align:center">计划单位成本</td>
                    <td rowspan="2" style="text-align:center">金额</td>
                </tr>
                
                <tr>
                    <td style="text-align:center">请领</td>
                    <td style="text-align:center">核发</td>
                    <td style="text-align:center">实发</td>
                    
                </tr>
                
                <tr>
                	<td height="92px" style="text-align:center; word-wrap:break-word;" id="pin_clbh"></td>
                    <td style="text-align:center; word-wrap:break-word;" id="pin_clmc"></td>
                    <td  style="text-align:center; word-wrap:break-word;" id="pin_gg"></td>
                    <td  style="text-align:center; word-wrap:break-word;" id="pin_jldw"></td>
                    
                    <td  style="text-align:center; word-wrap:break-word;" id="pin_sum0"></td>
                    <td  style="text-align:center; word-wrap:break-word;" id="pin_sum1"></td>
                    <td  style="text-align:center; word-wrap:break-word;" id="pin_sum2"></td>
                    <td style="text-align:center; word-wrap:break-word;" id="pin_dj"></td>
                    <td style="text-align:center; word-wrap:break-word;" id="pin_je"></td>
                </tr>
                
                <tr>
                	<td height="70px" style="text-align:center; word-wrap:break-word;">费用项目</td>
                    <td colspan="2" style="text-align:center; word-wrap:break-word;" id="pin_fyxm"></td>
                    <td colspan='2' style="text-align:center; word-wrap:break-word;">详细用途</td>
                    <td colspan="4" style="text-align:center; word-wrap:break-word;" id="pin_fz"></td>
                </tr>
            
            </table>
            
            
             <table width="100%" style="margin-top:5px;table-layout:fixed;" >
             	<tr>
                	<td width="23%" rowspan="2" style="vertical-align:text-top">工程主管科室:</td>
                    <td rowspan="2" width="15%" style="vertical-align:text-top">审核:</td>
                    <td width="18%">发料人:<span id="pin_flr"></span></td>
                    <td width="27%">领导单位主管:<span id="pin_llzg"></span></td>
                    <td>领料人:<span id="pin_llr"></span></td>
                </tr>
                <tr>
                	
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;签名:</td>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;签名:</td>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;签名:</td>
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
<input type="hidden" id="requestID" value="" />
<input type="hidden" id="cangKuName" value="<%=session.getAttribute("cangKuName") %>" />
<input type="hidden" id="cangKuID" value="<%=session.getAttribute("cangKuID") %>" />
<input type="hidden" id="feeID" value="" />




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
<script src='<%=basePath %>dwr/interface/SendAndReceive.js'></script>
<script src='<%=basePath %>dwr/interface/InAndOutCheck.js'></script> 
<script type="text/javascript" src="js/outWarehouse.js"></script> 



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
	

	$('#print').click(function(){
		$("#divToPrint").jqprint();
	});

	var cangKuID = "<%=session.getValue("cangKuID") %>";

	function getSendID(){
		var param = {
			sqlID: "sys_05",
			strCount:0
		};
		dwr.engine.setAsync(false);
		PreStatement.getTabeData(param,function(data){
			$("#requestID").val(data[0]);
			
		});
	}

	function setFeeID(name){
		var param = {
			sqlID: "fee_10",
			str0:name,
			strCount:1
		};
		PreStatement.getTabeData(param,function(data){
			$("#feeID").val(data[0]);
		});
	}
	
	function bindMaterial(arrstr){
		Common.getCategoryAndSize(arrstr,function(data){
		
			 $('#sel_size').val(data[1]);
			 $('#pin_jldw').html(data[1]);
			 $('#pin_clbh').html(data[2]);
			 $('#sel_materialID').val(data[3]);
			 $('#sel_categoryID').val(data[4]); 
			 
			
			 
			 if(cangKuID=="null" || cangKuID==null){
				 alert("凭证过时,请重新登录");
				 return;
			 }
			 
			 var hasFP = 1;
			 
			 if($('#hasFP').attr('checked')=="checked"){
			 	hasFP = 1;
			 }else{
			 	hasFP = 0;
			 }
			 
			 
			 
			 
			 var str = [cangKuID,$('#sel_materialID').val(),hasFP];
			 
			 
			 if($('#checkOther').attr('checked')=="checked"){
			 	InAndOutCheck.getMaterialCountOther(str,function(data){
				 	$('#sel_allNumber').val(data);
			 	});
			 	return;
			 }
			 
			 InAndOutCheck.getMaterialCount(str,function(data){
				 $('#sel_allNumber').val(data);
			 });
			 
			 //---------------
		 });
	}
	
	
	function getPriceData(requestID){
		if($('#checkOther').attr('checked')=="checked"){
			InAndOutCheck.getRequestPriceOther(requestID,function(data){
				$('#sel_dj').val(data[0]);
				$('#sel_je').val(data[1]);
				$('#pin_dj').html(data[0]);
				$('#pin_je').html(data[1]);
			});
			return;
		}
		InAndOutCheck.getRequestPrice(requestID,function(data){
				$('#sel_dj').val(data[0]);
				$('#sel_je').val(data[1]);
				$('#pin_dj').html(data[0]);
				$('#pin_je').html(data[1]);
			});
			return;
	
	}
	
	 $("#reset").click(function(){
	 	var date1 = $('#sel_date1').val();
	 	var date2 = $('#sel_date2').val();
	 	window.location.href="outWarehouse.jsp?date1="+date1+"&date2="+date2;
	 });
	 
	 function getUser(){
	 	return "<%=session.getAttribute("userName")%>";
	 }
	 
</script>

</html>
