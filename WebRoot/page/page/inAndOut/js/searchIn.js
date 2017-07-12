// JavaScript Document

var imageid = "";
var table2;
function upload(){
	$('#uploadImg').click(function(){
		if(imageid==""){alert("请选择一条数据操作");return;}
		
		
		
		
		var file=dwr.util.getValue("file");
		console.info(file);
		console.info(file.value)
		
	
		
		if(file.value==""){
			alert("请选择上传文件")
			return;
		}
//		
//		if(file.value)
		
		console.info("=====>2"+imageid);
		dwr.engine.setAsync(false); 
		ImageUtil.upload(file,imageid,function(data){
			if(data=="OK"){
				alert("成功保存图片");
				bindImg(imageid);
				return;
			}
			alert(data);
		});
//		FileService.upload(file,file.value,function(result){
//			alert(result);
//		});
	});
	
	
}

function bindImg(imageid){
	$('#image').attr("src","../../Image?materialID="+imageid+"&random="+Math.random());
	console.info();
}


function search2(myslbh){
	var searchParam = {sel_slbh:myslbh,houseID:getStoreHouseID()};
	 $("#search").attr("disabled","true");
	 
	 if($('#in_checkOther').attr('checked')=="checked"){
		 $('#checkOther').attr("checked","checked");
		 $("#isOther").val("true");

		 $("#search").removeAttr("disabled");
		 InAndOutCheck.getReceiveListOther(searchParam,function(data){
			 if(data.length<2){
				
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



$(document).ready(function() {
	upload();
	table2 = $('.tableExample').DataTable({
			 "language": {
	            "lengthMenu": "每页 _MENU_ /条",
	            "zeroRecords": "没有找到任何记录，对不起",
	            "info": "显示 _PAGE_ / _PAGES_ 页",
	            "infoEmpty": "没有任何可用记录",
	            "infoFiltered": "(filtered from _MAX_ total records)",
	            "search":         "检索:",
	            "paginate": {
	                "first":      "第一页",
	                "last":       "最后一页",
	                "next":      "下一页",
	                "previous":   "上一页"
	            }
	        },
	        "pagingType": "full_numbers",
	        "scrollX": true,
	        "lengthMenu": [[10, 20, 100, -1], [10, 20, 100, "All"]],
	        "order": [[ 0, "desc" ]],
	        dom: 'B<"clear">lfrtip',
	        buttons: [
	             'csv', 'excel', 'pdf', 'print'
	        ]
			
	    });
	 
	 var table = $('#example').DataTable();
	 
	    $('#example tbody').on( 'click', 'tr', function () {
	        if ( $(this).hasClass('selected') ) {
	            $(this).removeClass('selected');
	            imageid = "";
	        }
	        else {
	            table.$('tr.selected').removeClass('selected');
	            $(this).addClass('selected');
	            imageid = $(this).context.cells[0].innerHTML;   
	        	dwr.engine.setAsync(false);  
	    		Common.getMaterialID(imageid,function(data){
	    			imageid = data;
	    		});
	    		dwr.engine.setAsync(false); 
	    		
	    		
	    		search2($(this).context.cells[0].innerText)
	        }
	        bindImg(imageid);
	    } );
	 
	 
	 
	 
	 
	 $('.form_date').datetimepicker({
	        language:  'fr',
	        format: "yyyy-mm-dd",
	        autoclose: true,
	        minView:2,
	        todayBtn: true,
	        minuteStep: 1,
	        pickerPosition: "bottom-left"
	    });
	 
	 //定义收料人
	 
	 
	 
	 
	 var strs = ["","1004"];
	 Common.getConfigValue(strs,function(data){
		 for(i=0;i<data.length;i++){
			 $("#sel2_cgr").append("<option>"+data[i]+"</option>");
		 }
	 });
	 
	 strs = ["","1003"];
	 Common.getConfigValue(strs,function(data){
		 for(i=0;i<data.length;i++){
			 $("#sel2_slr").append("<option>"+data[i]+"</option>");
		 }
	 });
	 
	 
	 
	 $("#sel2_fyxm").keyup(function(){
		 var sel_fyxm = $("#sel2_fyxm").val();
		 strs = [sel_fyxm,"1006"];
		 Common.getConfigValue(strs,function(data){
			 
			 $('#sel2_fyxm').autocomplete({
		         	source:data, 
		         	minLength:0,
			        delay:300
			 });
		 });
	 });
	 
	 $("#sel2_gydw").keyup(function(){
		 var sel_fyxm = $("#sel2_gydw").val();
		 strs = [sel_fyxm,"1005"];
		 Common.getConfigValue(strs,function(data){			 
			 $('#sel2_gydw').autocomplete({
		         	source:data, 
		         	minLength:0,
			        delay:200
			 });
		 });
	 }); 
	 
	
	 $("#sel2_materialName").keyup(function(){
		 var sel_fyxm = $("#sel2_materialName").val();
		 Common.getMaterialName(sel_fyxm,function(data){			 
			 $('#sel2_materialName').autocomplete({
		         	source:data, 
		         	minLength:1,
			        delay:200
			 });
		 });
	 });  
	 
	 $("#sel2_category").keyup(function(){
		 var sel_fyxm = $("#sel2_category").val();
		 Common.getCategoryName(sel_fyxm,function(data){			 
			 $('#sel2_category').autocomplete({
		         	source:data, 
		         	minLength:0,
			        delay:200
			 });
		 });
	 });  
	 
	 $('#searchInButton').click(function(){
		 
		 if(check()){
			alert("用户凭证过时，请重新登录!");
			return;
		 }
		 
		 //var $btn = $(this).button('loading')
		 
		
		 
		 var searchParam = {
			sel_cgr:$('#sel2_cgr').val(),	 
			sel_slr:$('#sel2_slr').val(),
			sel_postrion:$('#sel2_postion').val(),
			sel_item:$('#sel2_fyxm').val(),
			sel_materialName:$('#sel2_materialName').val(),
			sel_category:$('#sel2_category').val(),
			sel_invoiceNO:$('#sel2_fpbh').val(),
			sel_slbh:$('#sel2_slbh').val(),
			sel_gydw:$('#sel2_gydw').val(),
			sel_sDate:$('#sel2_date1').val(),
			sel_eDate:$('#sel2_date2').val(),
			sel_isFP:$('#sel2_isFP').val(),
			houseID:getStoreHouseID()
		 };
		 
		 var strParam = "param=searchIn"
	 			+"&sel_cgr="+$('#sel2_cgr').val()
	 			+"&sel_slr="+$('#sel2_slr').val()
	 			
	 			+"&sel_postrion="+$('#sel2_postion').val()
	 			+"&sel_item="+$('#sel2_fyxm').val()
	 			+"&sel_materialName="+$('#sel2_materialName').val()
	 			+"&sel_category="+$('#sel2_category').val()
	 			+"&sel_invoiceNO="+$('#sel2_fpbh').val()
	 			+"&sel_slbh="+$('#sel2_slbh').val()
	 			+"&sel_gydw="+$('#sel2_gydw').val()
	 			+"&sel_sDate="+$('#sel2_date1').val()
	 			+"&sel_eDate="+$('#sel2_date2').val()
	 			+"&sel_isFP="+$('#sel2_isFP').val();
	 			

				var myUrl = "../../../servlet/DataRequestServlet?"+strParam;
				
				table2.clear();
				table2.ajax.url( myUrl).load();
		 
		 
	 });
	 
	 $('#myButton').on('click', function () {
		
	 });
	 
	 
	 

   });