
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

function materialKeyUp(){
	 $("#sel_materialName").keyup(function(){
		 var sel_fyxm = $("#sel_materialName").val();
		 Common.getMaterialName(sel_fyxm,function(data){			 
			 $('#sel_materialName').autocomplete({
		         	source:data, 
		         	minLength:1,
			        delay:200,
			        select: function( event, ui ) {
			        	console.info(ui.item.value);
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

function commonInit(){
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
		 $('#pin_slr').html(getUser());
		 $('#sel_slr').val(getUser());
		
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
		 });
		 
	 });
}

function bind(){
	
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
		 console.info(l+"|"+h);
		 
		 var text2 = "";
		 for(var i=0;i<h; i++){
			 text2 += text.substring(i*10,(i+1)*10-1)+"<br/>";
		 }
		 console.info("test2:"+text2);
		 
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
		 
		 console.info($('#sel_number').val()!="");
		 console.info($('#sel_je').val()!="");
		 console.info($('#sel_je').val()+"----<");
		 
		 
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
		 console.info("===>"+$(this).val());
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
		console.info($("#hasFpbh").attr("checked")+"1121");
		if( $("#hasFpbh").attr("checked")){
			$('#sel_fpbh').removeAttr("disabled");
			$('#sel_fpbh').val("");
			RECEIPT_HASINVOICE = 1;
		}else{
			$('#sel_fpbh').val("-");
			$('#sel_fpbh').attr("disabled","true");
			RECEIPT_HASINVOICE = 0;
		}
		$('#pin_fpbh').html($("#sel_fpbh").val());
		
	});
}




$(document).ready(function() {
	hasInvoidBind();
	initDate();
	materialKeyUp();
	gydwKeyUp();
	commonInit();
	styleChange();
	bind();
});