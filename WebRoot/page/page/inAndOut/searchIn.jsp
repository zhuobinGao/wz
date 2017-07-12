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
      <li class="active">物资进库查询</li>
    </ol>
    
    
    <hr/>
    
    <div class="panel panel-primary">
      <div class="panel-heading">进库查询条件</div>
      <div class="panel-body" >
        
        <div class="row" >
            <form style=" margin-left:15px; margin-bottom:0;">
                <div class="form-group form-group-sm form-inline" style="margin-bottom:0">
                    
                    
                    <div class="row" >
                        <div class="col-md-2">
                            <label  class="control-label">&nbsp;&nbsp;&nbsp;&nbsp;采购人:</label>
                            
                            <select class="form-control" id="sel2_cgr" style="width:170px;"  >
								<option value=""></option>
							</select>
                            
                            
                            
                        </div>
                        
                        <div class="col-md-2">
                            <label  class="control-label">&nbsp;&nbsp;&nbsp;&nbsp;收料人:</label>
                            <select class="form-control" id="sel2_slr" style="width:170px;" >
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
                            <label  class="control-label">发票编号:</label>
                            <input type="text" class="form-control" id="sel2_fpbh" placeholder="发票编号" size="25" >
                        </div>
                        
                         <div class="col-md-2">
                            <label  class="control-label">收料编号:</label>
                            <input type="text" class="form-control" id="sel2_slbh" placeholder="收料编号" size="25" >
                        </div>
                        
                    </div>
                    
                    
                    <div class="row" style="margin-top:10px;">
                         <div class="col-md-4">
                            <label  class="control-label">供应单位:</label>
                            <input type="text" class="form-control" id="sel2_gydw" placeholder="供应单位" size="60" >
                         </div>
                         
                         <div class="col-md-4">
                            <label  class="control-label">收料日期:</label>
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
                         	<label  class="control-label">是否含发票:</label>
                             <select id="sel2_isFP" class="form-control">
                             	<option value="-1"></option>
                             	<option value="1">已含票</option>
                             	<option value="0">无发票</option>>
                             </select>
                         </div>
                         
                         <div class="col-md-2">
                             <button type="button" class="btn btn-primary" id="searchInButton" autocomplete="off" data-loading-text="查询中...">查  询</button>
                             <button type="button" class="btn btn-primary" id="print" autocomplete="off" data-loading-text="查询中...">打  印</button>
                         </div>
                    </div>
                    
               </div>
            </form>
        </div>
    
      </div>
    </div>


	<div class="row">
    
    	<div class="col-md-9">
        	<div class="panel panel-primary">
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

<div class="container-fluid" style="padding:10px; display:none">
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
<script type="text/javascript" src="js/searchIn.js?v=2017012304"></script> 
<script type="text/javascript" src="js/inWarehouseEdit.js"></script> 

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
