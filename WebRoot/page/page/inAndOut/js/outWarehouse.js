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

	 $("#sel_materialName").keyup(function(){
		 var sel_fyxm = $("#sel_materialName").val();
		 Common.getMaterialName(sel_fyxm,function(data){			 
			 $('#sel_materialName').autocomplete({
		         	source:data, 
		         	minLength:1,
			        delay:200,
			        select: function( event, ui ) {
			        	
			        	$('#sel_style').empty();
			        	$('#pin_clmc').html(ui.item.value);
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
	 
	 
	 var strs = ["","1007"];
	 Common.getConfigValue(strs,function(data){
		 for(i=0;i<data.length;i++){
			 $("#sel_flr").append("<option>"+data[i]+"</option>");
		 }
		 $('#sel_flr').val(getUser());
		 $('#pin_flr').html(getUser());
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
	 
	 $('#sel_ql').keyup(function(){
		 $('#pin_sum0').html($(this).val());
		 $('#pin_sum1').html($(this).val());
		 $('#pin_sum2').html($(this).val());
		 $('#sel_sf').val($(this).val());
	 });
	 
	 $('#sel_sf').keyup(function(){
		 $('#pin_sum1').html($(this).val());
		 $('#pin_sum2').html($(this).val());
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
	 
	 $('#sel_style').change(function(){
		 var arrstr = ["",""];
		 arrstr[0] = $('#sel_materialName').val();
		 arrstr[1] = $('#sel_style').find("option:selected").text(); 
		 
		 $('#pin_gg').html(arrstr[1]);
		 
		 bindMaterial(arrstr);
	 
	 });
	 
	 
	 $('#hasFP').click(function(){
		 var arrstr = ["",""];
		 arrstr[0] = $('#sel_materialName').val();
		 arrstr[1] = $('#sel_style').find("option:selected").text(); 
		 
		 $('#pin_gg').html(arrstr[1]);
		 
		 bindMaterial(arrstr);
	 
	 });
	 
	 
	 
	 
	 
	 
	 $('#checkOther').change(function(){
		 var arrstr = ["",""];
		 arrstr[0] = $('#sel_materialName').val();
		 arrstr[1] = $('#sel_style').find("option:selected").text();
		 bindMaterial(arrstr);
	 });
	 
	 
	 
	 
	 $('#saveSend').click(function(){
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
			 
			if(parseInt($("#sel_sf").val()) > parseInt($("#sel_allNumber").val()) ){
				
				
				
				alert("实发（"+$("#sel_sf").val()+"）不能大于库存("+$("#sel_allNumber").val()+")");
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
			
			if($("#sel_materialID").val()==""){
				alert("物资获取不正常，请重新输入！");
				return;
			}
			
			if($("#feeID").val()==""){
				alert("费用项目获取不正常，请重新输入！");
				return;
			}
			
			if($("#cangKuID").val()==""){
				alert("会话超时，请重新登录！");
				return;
			}
			
			if($("#cangKuName").val()==""){
				alert("会话超时，请重新登录！");
				return;
			}
		
			var hasFP = 1;
			 
			 if($('#hasFP').attr('checked')=="checked"){
			 	hasFP = 1;
			 }else{
			 	hasFP = 0;
			 }
		 
		 getSendID();
		 var param = {
				 str0:$('#requestID').val(),
				 str1:$('#cangKuName').val(),
				 str2:$('#cangKuID').val(),
				 str3:$('#sel_lldw').val(),
				 str4:$('#sel_date1').val(),
				 
				 str5:$('#sel_date2').val(),
				 str6:$('#sel_materialID').val(),
				 str7:$('#sel_ql').val(),
				 str8:$('#sel_sf').val(),
				 str9:"0.0",
				 
				 str10:$('#sel_fyxm').val(),
				 str11:$('#sel_fz').val(),
				 str12:$('#sel_flr').val(),
				 str13:$('#sel_llzg').val(),
				 str14:$('#sel_llr').val(),
				 
				 str15:$('#sel_size').val(),
				 str16:$('#sel_style').val(),
				 str17:$('#sel_materialName').val(),
				 str18:"0",
				 str19:"宝满码头",
				 
				 str20:$('#feeID').val(),
				 str21:$('#sel_jzjg').val(),
				 str22:hasFP,
				 strCount:22
		 }
		 
		 var listParam = [param];
		 
		 
		 var sqlid = "";
		 
		 if($('#otherSave').attr('checked')=="checked"){
			 sqlid="reqest_02"; 
		 }else{
			 sqlid="reqest_01";
		 }
		 
		 var savaParam = {sqlID:sqlid,str0:$('#requestID').val(),strCount:1};
		 $('#saveSend').attr("disabled","true");
		 
		 if($('#checkOther').attr('checked')=="checked"){
			 
			 SendAndReceive.addSendOther(listParam,function(data){
				 if(data=="OK"){
					 	getPriceData($("#requestID").val());
				 		alert("随机配件发料成功，发料编号为："+$("#requestID").val());
				 		$('#pin_llbh').text($("#requestID").val());
				 		if($('#otherSave').attr('checked')=="checked"){
				 			PreStatement.updateData(savaParam,function(data2){
				 				if(data2!="OK")alert("代管保存"+data2);
				 			});
				 		}
				 		
				 		
				 		return;
				 	}
				 
				 alert(data);
				 $("#saveSend").removeAttr("disabled");
				 
			 });
			 
			 
			 return;
		 }
		 
		 SendAndReceive.addSend(listParam,function(data){
			 if(data=="OK"){
				 	getPriceData($("#requestID").val());
			 		alert("随机配件发料成功，发料编号为："+$("#requestID").val());
			 		$('#pin_llbh').text($("#requestID").val());
			 		if($('#otherSave').attr('checked')=="checked"){
			 			PreStatement.updateData(savaParam,function(data2){
			 				if(data2!="OK")alert("代管保存"+data2);
			 			});
			 		}
			 		
			 		return;
			 	}
			 alert(data);
			 $("#saveSend").removeAttr("disabled");
		 });
		 
		 
		 
		 
		 
		 
		 
	 });
	 
	 
	 
	 
	
	 
	 
	 
	 
	 
	 
	 
	 
	 
});