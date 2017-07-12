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


<title>出库查询</title>
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
      <li><a href="#">进/出库管理</a></li>
      <li class="active">物资出库查询</li>
    </ol>
    
    
    <hr/>
    
    <div class="panel panel-primary">
       <div class="panel-heading">出库查询条件</div>
      <div class="panel-body" >
        
        <div class="row" >
            <form style=" margin-left:15px; margin-bottom:0;">
                <div class="form-group form-group-sm form-inline" style="margin-bottom:0">
                    
                    
                    <div class="row" >
                        <div class="col-md-2">
                            <label  class="control-label">&nbsp;&nbsp;&nbsp;&nbsp;领料人:</label>
                        	 <select class="form-control" id="sel2_llr" style="width:170px;"  >
								<option value=""></option>
							</select>
                        </div>
                        
                        <div class="col-md-2">
                            <label  class="control-label">领料单位:</label>
                        	<select class="form-control" id="sel2_lldw" style="width:170px;"  >
								<option value=""></option>
							</select>
                        </div>
                        
                         <div class="col-md-2">
                            <label  class="control-label">仓库位置:</label>
                            <input type="text" class="form-control" id="sel2_postion" placeholder="仓库位置" size="25" >
                        </div>
                        
                         <div class="col-md-2">
                            <label  class="control-label">费用项目:</label>
                            <input type="text" class="form-control" id="sel2_fyxm" placeholder="费用项目" size="25" >
                        </div>
                        
                    </div>
                    
                    <div class="row" style="margin-top:10px;">
                        <div class="col-md-2">
                            <label  class="control-label">物资名称:</label>
                            <input type="text" class="form-control" id="sel2_materialName" placeholder="物资名称" size="25" >
                        </div>
                        
                        <div class="col-md-2">
                            <label  class="control-label">物资类别:</label>
                            <input type="text" class="form-control" id="sel2_category" placeholder="物资类别" size="25" >
                        </div>
                        
                         <div class="col-md-2">
                            <label  class="control-label">详细用途:</label>
                            <input type="text" class="form-control" id="sel2_xxyt" placeholder="详细用途" size="25" >
                        </div>
                        
                         <div class="col-md-2">
                            <label  class="control-label">领料单号:</label>
                            <input type="text" class="form-control" id="sel2_llbh" placeholder="领料单号" size="25" >
                        </div>
                        
                    </div>
                    
                    
                    <div class="row" style="margin-top:10px;">
                         <div class="col-md-4">
                            <label  class="control-label">供应单位:</label>
                            <input type="text" class="form-control" id="sel2_gydw" placeholder="供应单位" size="60" >
                         </div>
                         
                         <div class="col-md-4">
                            <label  class="control-label">领料日期:</label>
                           <div class="input-group date form_date" data-date="" data-date-format="yyyy-mm-dd" data-link-field="dtp_input1" data-link-format="yyyy-mm-dd">
	                            <input id="sel2_date1" class="form-control" size="10"  placeholder="选择日期" type="text" value=""  >
	                            <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
	                            <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                       		</div>
                            
                            
                            <label  class="control-label">至</label>
                            <div class="input-group date form_date" data-date="" data-date-format="yyyy-mm-dd" data-link-field="dtp_input1" data-link-format="yyyy-mm-dd">
	                            <input id="sel2_date2" class="form-control" size="10"  placeholder="选择日期" type="text" value=""  >
	                            <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
	                            <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                       		</div>
                         </div>
                         
                         <div class="col-md-2">
                             <button type="button" class="btn btn-primary" id="searchInButton" >查  询</button>
                             <button type="button" class="btn btn-primary" id="print" >打  印</button>
                         </div>
                    </div>
                    
               </div>
            </form>
        </div>
    
      </div>
    </div>


	<div class="row" >
    	<div class="col-md-9">
        	 <div class="panel panel-primary">
       <div class="panel-heading">出库查询条件</div>
      <div class="panel-body" >
        
                    <table id="example" class="display nowrap tableExample" cellspacing="0" width="100%">
                        <thead>
                                <tr>
                                    <th>领料编号</th>
                                    <th>物资编号</th>
                                    <th>物资名称</th>
                                    <th>物资规格</th>
                                    <th>计量单位</th>
                                    <th>请领</th>
                                    <th>实发</th>
                                    
                                    <th>金额</th>
                                    <th>计划单位成本</th>
                                    <th>填单日期</th>
                                    <th>领料日期</th>
                                   
                                    <th>发料人</th>
                                    <th>领料人</th>
                                    <th>领料单位</th>
                                    <th>领料单位主管</th>
                                    <th>费用项目</th>
                                    <th>详细用途</th>
                                    <th>对应收料编号</th>
                                    <th>仓库位置</th>
                                    <th>供应单位</th>
                                    <th>机种机构</th>
                                </tr>
                        </thead>
                        
                        <tbody>
                            
                        </tbody>
        
                </table>
                
                </div>
            </div>
                
        </div>
    	
    			
    <div class="col-md-3">
    	
        <div class="panel panel-primary">
          <div class="panel-heading">
          		参考图片
          		<button type="button" class="btn btn-success" id="uploadImg">上传图片 </button>
          </div>
          
          
          <div class="panel-body"  >
        		<input type="file"  id="file" name="file">
          		<hr/>
            	<div class="easyzoom">
            		<a href="#" class="thumbnail"><img id="image" src="../../Image?materialID=123" alt="../..."></a>
                </div>
       
       	</div>
       </div>
    
    
    	
    </div>

   		
    
    </div>
	









</div>


	

<!-- hidden -->

<div class="container-fluid" style="padding:10px;display:none;">
<ol class="breadcrumb">
  <li><a href="#">仓库云</a></li>
  <li><a href="#">进/出库管理</a></li>
  <li class="active">出库信息调整/回退</li>
</ol>


<hr/>
<div class="row" >
	<div class="col-lg-12">
         <div class="form-group form-group-sm form-inline">
         	 <input type="checkbox" id="out_checkOther" >&nbsp;&nbsp;随机配件
         	 <input type="text" class="form-control" id="in_llbh" placeholder="领料单号" size="25" >
            
         </div>
     </div>
</div>


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
		                            		<input id="sel_date1" class="form-control" size="10"  placeholder="选择日期" type="text" value=""  >
		                            		<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
		                            		<span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                       					</div>   
                                        
                                     </div>
                                    
                                    <div class="col-md-6">
                                        <label  class="control-label">领料日期:</label>
                                        <div class="input-group date form_date" data-date="" data-date-format="yyyy-mm-dd" data-link-field="dtp_input1" data-link-format="yyyy-mm-dd">
		                            		<input id="sel_date2" class="form-control" size="10"  placeholder="选择日期" type="text" value=""  >
		                            		<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
		                            		<span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                       					</div>
                                    </div>
                                </div>
                             </div>
                             
                             <div class="form-group form-group-sm form-inline">
                                <label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" id="otherSave" >&nbsp;&nbsp;是否代保管</label>
                             </div>
                             
                             <div class="form-group form-group-sm form-inline">
                                <div class="row">
                                     <div class="col-md-6">
                                        <label  class="control-label">物资名称:</label>
                                        <input type="text" class="form-control" id="sel_materialName" placeholder="物资名称" size="25" disabled>
                                    </div>
                                    
                                    
                                     <div class="col-md-6">
                                        <label  class="control-label">物资规格:</label>
                                        <input type="text" class="form-control" id="sel_style" placeholder="物资规格" size="25" disabled>
                                     </div>
                                
                                </div>
                             </div>
                             
                             <div class="form-group form-group-sm form-inline">
                                <div class="row">
                                     <div class="col-md-6">
                                        <label  class="control-label">库存数量:</label>
                                        <input type="text" class="form-control" id="sel_allNumber" placeholder="库存数量(不显示)" size="25" disabled>
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
                                        <input type="text" class="form-control" id="sel_ql" placeholder="请领" size="25" disabled>
                                    </div>
                                    
                                    
                                     <div class="col-md-6">
                                        <label  class="control-label">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;实发:</label>
                                        <input type="text" class="form-control" id="sel_sf" placeholder="实发" size="25" disabled>
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
                    <td width="30%" style="text-align:center;"><p style="font-size:16px;">填单日期&nbsp;:&nbsp;<span id="pin_date1"></span></p></td>
                    <td width="35%" style="padding-left:40px;">
                    	<ul style="text-decoration:none; list-style:none">
                    		<li>领料日期&nbsp;:&nbsp;<span id="pin_date2"></span></li>
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
<script src='<%=basePath %>dwr/interface/ImageUtil.js'></script>
<script type="text/javascript" src="js/searchOut.js?v=1.0"></script> 
<script type="text/javascript" src="js/outWarehouseEdit.js?v=2017012301"></script> 

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


		
</script>

</html>
