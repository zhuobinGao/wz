function initSelLLR(){
	$('#sel_llr').change(function(){
		var llr = $('#sel_llr').val();
		var param = {sqlID:"common24",str0:llr,strCount:1	};
		PreStatement.getTabeData(param,function(data){
			//<option value=''></option>
			var str = "";
			
			for(var i=0;i<data.length;i++){
				strs = data[i].split("|");
				$('#sel_llzg').val(strs[1]);
				$('#sel_lldw').val(strs[2]);
				$('#pin_llzg').html($('#sel_llzg').val());
				$('#pin_lldw').html($('#sel_lldw').val());
				continue;
			}
		});
		
		
		
	});
	
}

function initData(data){
	 if(data.length<2){alert("没有查到数据！");reset();return;}
		
	 $("#sel_date1").val(data[1].REQUEST_FILLTIME);
	 $("#sel_date2").val(data[1].REQUEST_ISSUETIME);
	 $("#sel_materialName").val(data[1].MATERIAL_NAME+"-"+data[1].REQUEST_BAK);
	 $("#sel_style").val(data[1].MATERIAL_STYLE);
	 $("#sel_size").val(data[1].MATERIAL_UNIT);
	 
	 $("#sel_ql").val(data[1].REQUEST_R_MOUNT);
	 $("#sel_sf").val(data[1].REQUEST_AI_MOUNT);
	 $("#sel_dj").val(data[1].rEQUEST_DJ);
	 $("#sel_je").val(data[1].REQUEST_TAMOUNT);
	 $("#sel_flr").val(data[1].REQUEST_ISSUER);
	 
	 $("#sel_llr").val(data[1].REQUEST_REQUESTER);
	 $("#sel_llzg").val(data[1].REQUEST_LDPT_MANAGER);
	 $("#sel_lldw").val(data[1].REQUEST_LDPT_ID);
	 $("#sel_fyxm").val(data[1].REQUEST_ITEM_ID);
	 $("#sel_fz").val(data[1].REQUEST_USAGE);
	 
	 $('#feeID').val(data[1].REQUEST_ITEM_ID2);
	 
	 
	 $("#pin_lldw").html(data[1].REQUEST_LDPT_ID);
	 $("#pin_llbh").html(data[1].REQUEST_ID);
	 $("#pin_date1").html(data[1].REQUEST_FILLTIME);
	 $("#pin_date2").html(data[1].REQUEST_ISSUETIME);
	 $("#pin_clbh").html(data[1].REQUEST_BAK);
	 
	 $("#pin_clmc").html(data[1].MATERIAL_NAME+"-"+data[1].REQUEST_BAK);
	 $("#pin_gg").html(data[1].MATERIAL_STYLE);
	 $("#pin_jldw").html(data[1].MATERIAL_UNIT);
	 $("#pin_sum0").html(data[1].REQUEST_R_MOUNT);
	 $("#pin_sum1").html(data[1].REQUEST_AI_MOUNT);
	 
	 $("#pin_sum2").html(data[1].REQUEST_AI_MOUNT);
	 $("#pin_dj").html(data[1].rEQUEST_DJ);
	 $("#pin_je").html(data[1].REQUEST_TAMOUNT);
	 $("#pin_fyxm").html(data[1].REQUEST_ITEM_ID);
	 $("#pin_fz").html(data[1].REQUEST_USAGE);
	 
	 $("#pin_flr").html(data[1].REQUEST_ISSUER);
	 $("#pin_llzg").html(data[1].REQUEST_LDPT_MANAGER);
	 $("#pin_llr").html(data[1].REQUEST_REQUESTER);
	
	 if(data[1].REQUEST_STATE=="0"){
		 $('#otherSave').attr("checked","checked");
	 }else{
		 $("#otherSave").removeAttr("checked");
	 }
	 
}

function initJZChange(){
	$('#sel_jz').change(function(){
		var param = {
				sqlID: "mechan01",
				str0:$('#sel_jz').val(),
				strCount:1
		};
		PreStatement.getTabeData(param,function(data){	
			var temp;
			$('#sel_jzjg').empty();
			$('#sel_jzjg').append("<option value='-1'></option>");
			for(var i=0;i<data.length;i++){
				temp = data[i].split("|");
				$('#sel_jzjg').append("<option value='"+temp[1]+"'>"+temp[0]+"</option>");
			}
		});
		
	});
}

function reset(){
	 $("#sel_date1").val("");
	 $("#sel_date2").val("");
	 $("#sel_materialName").val("");
	 $("#sel_style").val("");
	 $("#sel_size").val("");
	 
	 $("#sel_ql").val("");
	 $("#sel_sf").val("");
	 $("#sel_dj").val("");
	 $("#sel_je").val("");
	 $("#sel_flr").val("");
	 
	 $("#sel_llr").val("");
	 $("#sel_llzg").val("");
	 $("#sel_lldw").val("");
	 $("#sel_fyxm").val("");
	 $("#sel_fz").val("");
	 
	 $('#feeID').val("");
	 
	 
	 $("#pin_lldw").html("");
	 $("#pin_llbh").html("");
	 $("#pin_date1").html("");
	 $("#pin_date2").html("");
	 $("#pin_clbh").html("");
	 
	 $("#pin_clmc").html("");
	 $("#pin_gg").html("");
	 $("#pin_jldw").html("");
	 $("#pin_sum0").html("");
	 $("#pin_sum1").html("");
	 
	 $("#pin_sum2").html("");
	 $("#pin_dj").html("");
	 $("#pin_je").html("");
	 $("#pin_fyxm").html("");
	 $("#pin_fz").html("");
	 
	 $("#pin_flr").html("");
	 $("#pin_llzg").html("");
	 $("#pin_llr").html("");
	
	
	 $("#otherSave").removeAttr("checked");
	 
	 
	
}

function search(){
	var searchParam = {
			sel_llbh:$('#in_llbh').val(),houseID:getStoreHouseID()
	};
	
	 if($('#out_checkOther').attr('checked')=="checked"){
		 InAndOutCheck.getRequestListOther(searchParam, function(data){
				initData(data);
				//REQUEST_ITEM_ID2 $('#saveSend').attr("disabled","true");
			});
		 return;
	 }
	
	InAndOutCheck.getRequestList(searchParam, function(data){
		initData(data);
		//REQUEST_ITEM_ID2 $('#saveSend').attr("disabled","true");
	});
	
}


function updateRequest(){
	 if(check()){
		alert("用户凭证过时，请重新登录!");
		return;
	 }
	
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
 
	
	if(isNaN($("#sel_ql").val())){
		alert("请领请输入数字");
		return;
	}
	
	if($("#sel_ql").val()<=0){
		alert("请领必须大于0");
		return;
	}
	
	if(isNaN($("#sel_sf").val())){
		alert("实发请输入数字");
		return;
	}
	
	if($("#sel_sf").val()<=0){
		alert("实发必须大于0");
		return;
	}
	 
	
	
	
	
	if($("#sel_flr").val()==""){
		alert("发料人不能为空");
		return;
	}
	
	if($("#sel_llr").val()==""){
		alert("领料人不能为空");
		return;
	}
	
	if($("#sel_llzg").val()==""){
		alert("领料主管不能为空");
		return;
	}
	
	if($("#sel_lldw").val()==""){
		alert("领料单位不能为空");
		return;
	}
	
	if($("#sel_fyxm").val()==""){
		alert("费用项目不能为空");
		return;
	}
	
	
	console.info("$(#feeID).val():"+$("#feeID").val());
	
	if($("#feeID").val()==""){
		alert("费用项目获取不正常，请重新输入！");
		return;
	}
	
	
	 var sqlid = "";
	if($('#out_checkOther').attr('checked')=="checked"){
		 sqlid="reqest_04"; 
	 }else{
		 sqlid="reqest_03";
	 }
	
	var mystr9 = "";
	if($('#sel_jzjg').val()==null || $('#sel_jzjg').val()==""){
		mystr9 = "-1"
	}else{
		mystr9=$('#sel_jzjg').val();
	}
	
	var param = {
			sqlID:sqlid,
			str0:$('#sel_date1').val(),
			str1:$('#sel_date2').val(),
			str2:$('#sel_flr').val(),
			str3:$('#sel_llr').val(),
			str4:$('#sel_llzg').val(),
			
			str5:$('#sel_lldw').val(),
			str6:$('#sel_fyxm').val(),
			str7:$('#feeID').val(),
			str8:$('#sel_fz').val(),
			str9: mystr9,
			str10:$('#pin_llbh').text(),
			strCount:11,
	}
	console.info(param);
	PreStatement.updateData(param,function(data){
		
		
		if(data=="OK"){
			alert("已更新到数据库！");
			return;
		}
		alert(data);
	});
	
	
	
	
	
}

function goBack(){
	if(check()){
		alert("用户凭证过时，请重新登录!");
		return;
	 }
	
	var requestID = $('#pin_llbh').text();
	
	var param = {str0:requestID}
	
	
	if($('#out_checkOther').attr('checked')=="checked"){
		console.info("clearSendOther");
		SendAndReceive.clearSendOther(param,function(data){
			if(data=="OK"){
				alert("成功回退领料!");
				reset();
				return;
			}
			alert(data);
		});
		return;
	}
	SendAndReceive.clearSend(param,function(data){
		if(data=="OK"){
			alert("成功回退领料!");
			reset();
			return;
		}
		alert(data);
	});
	
	
}












$(document).ready(function() {
	initSelLLR();
	initJZChange();
	 $('.form_date').datetimepicker({
	        language:  'fr',
	        format: "yyyy-mm-dd",
	        autoclose: true,
	        minView:2,
	        todayBtn: true,
	        minuteStep: 1,
	        pickerPosition: "bottom-left"
	  });
	
	 var strs = ["","1007"];
	 Common.getConfigValue(strs,function(data){
		 for(i=0;i<data.length;i++){
			 $("#sel_flr").append("<option>"+data[i]+"</option>");
		 }
	 });
	 
	 strs = ["","1008"];
	 Common.getConfigValue(strs,function(data){
		 for(i=0;i<data.length;i++){
			 $("#sel_llr").append("<option>"+data[i]+"</option>");
		 }
	 });
	 
	 strs = ["","1009"];
	 Common.getConfigValue(strs,function(data){
		 for(i=0;i<data.length;i++){
			 $("#sel_llzg").append("<option>"+data[i]+"</option>");
		 }
	 });
	 
	 
	 Common.getDeptName(function(data){
		 for(i=0;i<data.length;i++){
			 $("#sel_lldw").append("<option>"+data[i]+"</option>");
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
			        	
			        	setFeeID(ui.item.value);
			        	
			        } 
			 });
		 });
	 });
	 
	 $('#sel_date1').change(function(){
		 $('#pin_date1').html($(this).val());
	 });
	 
	 $('#sel_date2').change(function(){
		 $('#pin_date2').html($(this).val());
	 });
	 
	 $('#sel_fz').keyup(function(){
		 var jzjg = $('#sel_jz').find("option:selected").text()+"->"+$('#sel_jzjg').find("option:selected").text();;
		 $('#pin_fz').html($('#sel_fz').val()+"<br/>"+jzjg);
	 });
	 $('#sel_jz').change(function(){
		 var jzjg = $('#sel_jz').find("option:selected").text()+"->"+$('#sel_jzjg').find("option:selected").text();;
		 $('#pin_fz').html($('#sel_fz').val()+"<br/>"+jzjg);
	 });
	 $('#sel_jzjg').change(function(){
		 var jzjg = $('#sel_jz').find("option:selected").text()+"->"+$('#sel_jzjg').find("option:selected").text();;
		 $('#pin_fz').html($('#sel_fz').val()+"<br/>"+jzjg);
	 });
	 
	 
	 
	 
	 $('#sel_flr').change(function(){
		 $('#pin_flr').html($(this).val());
	 });
	 
	 $('#sel_llr').change(function(){
		 $('#pin_llr').html($(this).val());
	 });
	 
	 $('#sel_llzg').change(function(){
		 $('#pin_llzg').html($(this).val());
	 });
	 
	 $('#sel_lldw').change(function(){
		 $('#pin_lldw').html($(this).val());
	 });
	 
	 
	 $('#chenkOut').click(function(){
		 console.info("chenkOut");
		 search();
		 
		 
		 
	 });
	 
	
	$('#update').click(function(){
		if(check()){
			alert("用户凭证过时，请重新登录!");
			return;
		 }
		updateRequest();
	});
	
	$('#back').click(function(){
		if(check()){
			alert("用户凭证过时，请重新登录!");
			return;
		 }
		goBack();
	});
	
});