function bind(str1,str2){
	$(str1).html($(str2).val());
}

function tongBu(){
	
	bind('#pin_date1','#sel_date1');
	bind('#pin_date2','#sel_date2');
	bind('#pin_materialName','#sel_materialName');
	bind('#pin_style','#sel_style');
	bind('#pin_size','#sel_size');
	
	bind('#pin_ys','#sel_number');
	bind('#pin_ss','#sel_number');
	bind('#pin_je','#sel_je');
	bind('#pin_slr','#sel_slr');
	bind('#pin_cgr','#sel_cgr');
	
	bind('#pin_fpbh','#sel_fpbh');
	bind('#pin_gydw','#sel_gydw');
	bind('#pin_fyxm','#sel_fyxm');
	bind('#pin_fz','#sel_fz');
	
	var je = $('#sel_je').val();
	var number = $('#sel_number').val();
	var dj = je/number;
	$('#pin_dj').html(dj.toFixed(6));
}

function reset(){
	 $("#sel_date1").val("");
	 $("#sel_date2").val("");
	 $("#sel_materialName").val("");
	 $("#sel_category").val("");
	 $("#sel_style").val("");
	 
	 $("#sel_size").val("");
	 $("#sel_number").val("");
	 $("#sel_je").val("");
	 $("#sel_slr").val("");
	 $("#sel_cgr").val("");
	 
	 $("#sel_fpbh").val("");
	 $("#sel_gydw").val("");
	 $("#sel_fyxm").val("");
	 $("#sel_postion").val("");
	 $("#sel_fz").val("");
	 
	 $("#sel_materialID").val("");
	 $("#sel_categoryID").val("");
	 $("#receiveID").val("");
	 $("#pin_slbh").text("");
	 $("#pin_clbh").text("");
	 
	 tongBu();
}

function search(){
	var searchParam = {sel_slbh:$('#in_slbh').val(),houseID:getStoreHouseID()};
	 $("#search").attr("disabled","true");
	 
	 if($('#in_checkOther').attr('checked')=="checked"){
		 $('#checkOther').attr("checked","checked");
		 $("#isOther").val("true");

		 $("#search").removeAttr("disabled");
		 InAndOutCheck.getReceiveListOther(searchParam,function(data){
			 if(data.length<2){
				 alert("无查询记录！");
				 $("#search").removeAttr("disabled");
				 reset();
				 return;
			 }
			 
			 $("#sel_date1").val(data[1].RECEIPT_FILLTIME);
			 $("#sel_date2").val(data[1].RECEIPT_RECEIVEDTIME);
			 $("#sel_materialName").val(data[1].RECEIPT_MATERIAL_NAME+"-"+data[1].MATERIAL_BAK);
			 $("#sel_category").val(data[1].CATORY_NAME);
			 //$("#sel_style").val(data[1].RECEIPT_STYLE);
			
			 
			 
			
			 
			 
			
			 
			 $("#sel_size").val(data[1].RECEIPT_SIZE);
			 $("#sel_number").val(data[1].RECEIPT_AR_MOUNT);
			 $("#sel_je").val(data[1].RECEIPT_A_AMOUNT);
			 $("#sel_slr").val(data[1].RECEIPT_RECEIPTER);
			 $("#sel_cgr").val(data[1].RECEIPT_PURCHASER);
			 
			 $("#sel_fpbh").val(data[1].RECEIPT_INVOICENO);
			 $("#sel_gydw").val(data[1].RECEIPT_SUPPLYDEPT);
			 $("#sel_fyxm").val(data[1].RECEIPT_ITEM);
			 $("#sel_postion").val(data[1].RECEIPT_POSTION);
			 $("#sel_fz").val(data[1].RECEIPT_BAK);
			 
			 $("#sel_materialID").val(data[1].RECEIPT_MATERIAL_ID);
			 $("#sel_categoryID").val(data[1].CATORY_ID);
			 $("#receiveID").val(data[1].RECEIPT_ID);
			 $("#pin_slbh").text(data[1].RECEIPT_ID);
			 $("#pin_clbh").text(data[1].MATERIAL_BAK);
			 
			 initMaterial(data[1].RECEIPT_STYLE,data[1].RECEIPT_MATERIAL_NAME+"-"+data[1].MATERIAL_BAK);
			 
			 $("#search").removeAttr("disabled");
		 });
		 
		 return;
	 }
	 
	 $('#checkOther').attr("checked","");
	 $("#isOther").val("false");
	 
	 InAndOutCheck.getReceiveList(searchParam,function(data){
		
		 if(data.length<2){
			 alert("无查询记录！");
			 reset();
			 $("#search").removeAttr("disabled");
			 return;
		 }
		 
		 $("#sel_date1").val(data[1].RECEIPT_FILLTIME);
		 $("#sel_date2").val(data[1].RECEIPT_RECEIVEDTIME);
		 $("#sel_materialName").val(data[1].RECEIPT_MATERIAL_NAME+"-"+data[1].MATERIAL_BAK);
		 $("#sel_category").val(data[1].CATORY_NAME);
//		 $("#sel_style").val(data[1].RECEIPT_STYLE);
//		 $("#sel_style").empty();
		 
		 
		 
		 var mystyle = data[1].RECEIPT_STYLE;
		 
		 var mymater = data[1].RECEIPT_MATERIAL_NAME+"-"+data[1].MATERIAL_BAK;
		 
		
		 
		 $("#sel_size").val(data[1].RECEIPT_SIZE);
		 $("#sel_number").val(data[1].RECEIPT_AR_MOUNT);
		 $("#sel_je").val(data[1].RECEIPT_A_AMOUNT);
		 $("#sel_slr").val(data[1].RECEIPT_RECEIPTER);
		 $("#sel_cgr").val(data[1].RECEIPT_PURCHASER);
		 
		 $("#sel_fpbh").val(data[1].RECEIPT_INVOICENO);
		 $("#sel_gydw").val(data[1].RECEIPT_SUPPLYDEPT);
		 $("#sel_fyxm").val(data[1].RECEIPT_ITEM);
		 $("#sel_postion").val(data[1].RECEIPT_POSTION);
		 $("#sel_fz").val(data[1].RECEIPT_BAK);
		 
		 $("#sel_materialID").val(data[1].RECEIPT_MATERIAL_ID);
		 $("#sel_categoryID").val(data[1].CATORY_ID);
		 $("#receiveID").val(data[1].RECEIPT_ID);
		 $("#pin_slbh").text(data[1].RECEIPT_ID);
		 $("#pin_clbh").text(data[1].MATERIAL_BAK);
		 

		 initMaterial(data[1].RECEIPT_STYLE,data[1].RECEIPT_MATERIAL_NAME+"-"+data[1].MATERIAL_BAK);
		 
		 $("#search").removeAttr("disabled");
	 });
	
}

function initDate(){
	$('.form_date').datetimepicker({
        language:  'fr',
        format: "yyyy-mm-dd",
        autoclose: true,
        minView:2,
        todayBtn: true,
        minuteStep: 1,
        pickerPosition: "bottom-left"
  });
}

function initMaterial(dbStyle,dbMaterialName){
	var sel_fyxm = dbMaterialName;
	
	console.info(dbStyle+"|"+dbMaterialName);
	
	Common.getMaterStyle(sel_fyxm,function(data){
		$("#sel_style").empty();
		 $("#sel_style").append("<option></option>");
		 
		 for(i=0;i<data.length;i++){
			 $("#sel_style").append("<option>"+data[i]+"</option>");
		 }
		 $("#sel_style").val(dbStyle);
		 $('#sel_style').attr("disabled","true");
		 tongBu();
	 });
	
		 
		
	 
}



function materialKeyUp(){
	$("#sel_materialName").keyup(function(){
		 var sel_fyxm = $("#sel_materialName").val();
		 Common.getMaterialName(sel_fyxm,function(data){			 
			 $('#sel_materialName').autocomplete({
		         	source:data, 
		         	minLength:1,
			        delay:200,
			        select: function( event, ui ) {
			        	
			        	$('#sel_style').empty();
			        	$('#pin_materialName').html(ui.item.value);
			        	 Common.getMaterStyle(ui.item.value,function(data){
			        		 $("#sel_style").append("<option></option>");
			        		 
			        		 for(i=0;i<data.length;i++){
			        			 $("#sel_style").append("<option>"+data[i]+"</option>");
			        		 }
			        	 });
			        }
			 });
		 });
	 });
}


function gydwKeyUp(){
	$("#sel_gydw").keyup(function(){
		 var sel_fyxm = $("#sel_gydw").val();
		 strs = [sel_fyxm,"1005"];
		 Common.getConfigValue(strs,function(data){			 
			 $('#sel_gydw').autocomplete({
		         	source:data, 
		         	minLength:0,
			        delay:200,
			        select: function( event, ui ){
			        	$('#pin_gydw').html(ui.item.value);	
			        }
			 });
		 });
	 }); 
}

function initCommon(){
	var strs = ["","1004"];
	 Common.getConfigValue(strs,function(data){
		 for(i=0;i<data.length;i++){
			 $("#sel_cgr").append("<option>"+data[i]+"</option>");
		 }
	 });
	 
	 strs = ["","1003"];
	 Common.getConfigValue(strs,function(data){
		 for(i=0;i<data.length;i++){
			 $("#sel_slr").append("<option>"+data[i]+"</option>");
		 }
	 });
	 
	
	 
	 $("#sel_fyxm").keyup(function(){
		 var sel_fyxm = "%"+$("#sel_fyxm").val()+"%";
		
		 var param = {
					sqlID: "fee_11",
					str0:sel_fyxm,
					strCount:1
				};
		 
		 
		 PreStatement.getTabeData(param,function(data){
			 
			 $('#sel_fyxm').autocomplete({
		         	source:data, 
		         	minLength:0,
			        delay:300,
			        select: function( event, ui ){
			        	$('#pin_fyxm').html(ui.item.value);
			        } 
			 });
		 });
	 });
	 
	 
	 
	 
	 $("#sel_style").change(function(){
		 var arrstr = ["",""];
		 arrstr[0] = $('#sel_materialName').val();
		 arrstr[1] = $('#sel_style').find("option:selected").text(); 
		 
		 Common.getCategoryAndSize(arrstr,function(data){
			
			 $('#sel_category').val(data[0]);
			 $('#sel_size').val(data[1]);
			 $('#pin_size').html(data[1]);
			 $('#pin_clbh').html(data[2]);
			 $('#sel_materialID').val(data[3]);
			 $('#sel_categoryID').val(data[4]); 
		 });
		 
	 });
	 
	 $('#sel_date1').change(function(){
		 $('#pin_date1').html($(this).val());
	 });
	 
	 $('#sel_date2').change(function(){
		 $('#pin_date2').html($(this).val());
	 });

	 $('#sel_materialName').keyup(function(){
		 var text = $(this).val();
		 var l = text.length;
		 var h = l/10+1;
		
		 
		 var text2 = "";
		 for(var i=0;i<h; i++){
			 text2 += text.substring(i*10,(i+1)*10-1)+"<br/>";
		 }
		
		 
		 $('#pin_materialName').html($(this).val());
	 });
	 
	 $('#sel_style').change(function(){
		 $('#pin_style').html($(this).val());
	 });
	 
	 $('#sel_size').change(function(){
		 $('#pin_size').html($(this).val());
	 });
	 
	 
	 
	 
	 $('#sel_number').keyup(function(){
		 $('#pin_ys').html($(this).val());
		 $('#pin_ss').html($(this).val());
		 
		
		 
		 
		 if( $('#sel_number').val()!="" &&  $('#sel_je').val()!="" ){
			 var je = $('#sel_je').val();
			 var number = $('#sel_number').val();
			 var dj = je/number;
			 $('#pin_dj').html(dj.toFixed(6));
		 }
		 
	 });
	 
	 $('#sel_je').keyup(function(){
		 $('#pin_je').html($(this).val());
		 
		 if( $('#sel_number').val()!="" && $('#sel_je').val()!="" ){
			 var je = $('#sel_je').val();
			 var number = $('#sel_number').val();
			 var dj = je/number;
			 $('#pin_dj').html(dj.toFixed(6));
		 }
	 });

	 $('#sel_slr').change(function(){
		 $('#pin_slr').html($(this).val());
	 });
	 
	 $('#sel_cgr').change(function(){
		 $('#pin_cgr').html($(this).val());
	 });
	 
	 $('#sel_fpbh').keyup(function(){
		
		 $('#pin_fpbh').html($(this).val());
	 });
	 
	 
	 
	 
	 
	 
	 $('#sel_gydw').keyup(function(){
		
		 $('#pin_gydw').html($(this).val());
	 });
	 
	 $('#sel_fyxm').keyup(function(){
		 $('#pin_fyxm').html($(this).val());
	 });

	
	 
	 $('#sel_fz').keyup(function(){
		 $('#pin_fz').html($(this).val());
	 });
	 
}

function hasInvoidBind(){
	$('#pin_fpbh').html($("#sel_fpbh").val());
	$('#hasFpbh').click(function(){
		
		if( $("#hasFpbh").attr("checked")){
			$('#sel_fpbh').removeAttr("disabled");
			$('#sel_fpbh').val("");
			RECEIPT_HASINVOICE = 1;
		}else{
			$('#sel_fpbh').val("-");
			$('#sel_fpbh').attr("disabled","true");
			RECEIPT_HASINVOICE = 0;
		}
		console.info("RECEIPT_HASINVOICE: "+RECEIPT_HASINVOICE);
		$('#pin_fpbh').html($("#sel_fpbh").val());
		
	});
	
	$('#uptMater').click(function(){
		if( $("#uptMater").attr("checked")){
			$('#sel_materialName').removeAttr("disabled");
			$('#sel_style').removeAttr("disabled");
			
			eidtMaterial = 1;
		}else{
			$('#sel_materialName').attr("disabled","true");
			$('#sel_style').attr("disabled","true");
			eidtMaterial = 0;
		}
	
	});
}

function styleChange(){
	$("#sel_style").change(function(){
		 var arrstr = ["",""];
		 arrstr[0] = $('#sel_materialName').val();
		 arrstr[1] = $('#sel_style').find("option:selected").text(); 
		 
		 Common.getCategoryAndSize(arrstr,function(data){
			
			 $('#sel_category').val(data[0]);
			 $('#sel_size').val(data[1]);
			 $('#pin_size').html(data[1]);
			 $('#pin_clbh').html(data[2]);
			 $('#sel_materialID').val(data[3]);
			 $('#sel_categoryID').val(data[4]); 
			 
			 console.info($('#sel_materialID').val()+"\n"+$('#sel_categoryID').val());
		 });
		 
	 });
}

$(document).ready(function() {
	
	initDate();
	materialKeyUp();
	initCommon();
	 
	hasInvoidBind();
	styleChange();
	 
	 //---------------------------
	 
	 $("#search").click(function(){
		 if(check()){
			alert("用户凭证过时，请重新登录!");
			return;
		 }
		 search();
	 });
	 
	 
	 $("#update").click(function(){
		 if(check()){
			alert("用户凭证过时，请重新登录!");
			return;
		 }
		 var param = {
			RECEIPT_FILLTIME:$("#sel_date1").val(),
			RECEIPT_RECEIVEDTIME:$("#sel_date2").val(),
			RECEIPT_RECEIPTER:$("#sel_slr").val(),
			RECEIPT_PURCHASER:$("#sel_cgr").val(),
			RECEIPT_INVOICENO:$("#sel_fpbh").val(),
			RECEIPT_SUPPLYDEPT:$("#sel_gydw").val(),
			
			RECEIPT_ITEM:$("#sel_fyxm").val(),
			RECEIPT_POSTION:$("#sel_postion").val(),
			RECEIPT_NOTE:$("#sel_fz").val(),
			RECEIPT_ID:$("#receiveID").val(),
			isUpdateMaterial:eidtMaterial,
			RECEIPT_MATERIAL_ID:$('#sel_materialID').val()
		 }
		 
		 
		 if($('#in_checkOther').attr('checked')!="checked"){
			 InAndOutCheck.updateReceive(param,function(data){
				 if(data=="OK"){
					 alert("数据已更新");
					 return;
				 }
				 alert(data);
			 });
			 return;
		 }else{
			 InAndOutCheck.updateReceiveOther(param,function(data){
				 if(data=="OK"){
					 alert("数据已更新");
					 return;
				 }
				 alert(data);
			 });
			 return;
		 }
		 
		

	 });
	 
	 $("#back").click(function(){
		 if(check()){
			alert("用户凭证过时，请重新登录!");
			return;
		 }
		 var param = {str0: $("#receiveID").val(),strCount: 0};
		 console.info(InAndOutCheck);
		 
		 if($('#in_checkOther').attr('checked')=="checked"){
			 console.info("Other");
			 SendAndReceive.clearReceiveOther(param,function(data){
				 if(data=="OK"){
					 alert("数据已回退");
					 reset();
					 return;
				 }
				 alert(data);
			 });
			 
			 
			 
			 return;
		 }
		 console.info("noOther");
		 SendAndReceive.clearReceive(param,function(data){
			 if(data=="OK"){
				 alert("数据已回退");
				 reset();
				 return;
			 }
			 alert(data);
		 });
		 
		 
	 });
	 
	 
	 
	 
	 
	 
	 
	 
	 
	
});