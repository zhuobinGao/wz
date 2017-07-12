// JavaScript Document
var table;
$(document).ready(function() {
	
	 table = $('.tableExample').DataTable({
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
	 
	 $('.ymd').datetimepicker({
	        language:  'fr',
	        format: "yyyy-mm-dd",
	        autoclose: true,
	        minView:2,
	        todayBtn: true,
	        minuteStep: 1,
	        pickerPosition: "bottom-left"
	    });
	 
	 $('.ym').datetimepicker({
	        language:  'fr',
	        format: 'yyyymm',  
	        weekStart: 1,  
	        autoclose: true,  
	        startView: 3,  
	        minView: 3,  
	        forceParse: false,  
	        pickerPosition: "bottom-left"
	  });
	 
	 
	 //定义收料人
	 
	 
	 
	 
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
		 var sel_fyxm = $("#sel_fyxm").val();
		 strs = [sel_fyxm,"1006"];
		 Common.getConfigValue(strs,function(data){
			 
			 $('#sel_fyxm').autocomplete({
		         	source:data, 
		         	minLength:0,
			        delay:300
			 });
		 });
	 });
	 
	 $("#sel_gydw").keyup(function(){
		 var sel_fyxm = $("#sel_gydw").val();
		 strs = [sel_fyxm,"1005"];
		 Common.getConfigValue(strs,function(data){			 
			 $('#sel_gydw').autocomplete({
		         	source:data, 
		         	minLength:0,
			        delay:200
			 });
		 });
	 }); 
	 
	
	 $("#sel_materialName").keyup(function(){
		 var sel_fyxm = $("#sel_materialName").val();
		 Common.getMaterialName(sel_fyxm,function(data){			 
			 $('#sel_materialName').autocomplete({
		         	source:data, 
		         	minLength:1,
			        delay:200
			 });
		 });
	 });  
	 
	 $("#sel_category").keyup(function(){
		 var sel_fyxm = $("#sel_category").val();
		 Common.getCategoryName(sel_fyxm,function(data){			 
			 $('#sel_category').autocomplete({
		         	source:data, 
		         	minLength:0,
			        delay:200
			 });
		 });
	 });  
	 
	 $('#searchInButton').click(function(){
		 
		 //var $btn = $(this).button('loading')
		 
//		 $('#searchInButton').text("查 询");
		 
//		 var searchParam = {
//			sel_cgr:$('#sel_cgr').val(),	 
//			sel_slr:$('#sel_slr').val(),
//			sel_postrion:$('#sel_postion').val(),
//			sel_item:$('#sel_fyxm').val(),
//			sel_materialName:$('#sel_materialName').val(),
//			sel_category:$('#sel_category').val(),
//			sel_invoiceNO:$('#sel_fpbh').val(),
//			sel_slbh:$('#sel_slbh').val(),
//			sel_gydw:$('#sel_gydw').val(),
//			sel_sDate:$('#sel_date1').val(),
//			sel_eDate:$('#sel_date2').val(),
//			calDate:$('#sel_caldate').val(),
//			houseID:getStoreHouseID()
//		 };
		 var strParam = "param=searchInHis"
			 			+"&sel_cgr="+$('#sel_cgr').val()
			 			+"&sel_slr="+$('#sel_slr').val()
			 			
			 			+"&sel_postrion="+$('#sel_postion').val()
			 			+"&sel_item="+$('#sel_fyxm').val()
			 			+"&sel_materialName="+$('#sel_materialName').val()
			 			+"&sel_category="+$('#sel_category').val()
			 			+"&sel_invoiceNO="+$('#sel_fpbh').val()
			 			+"&sel_slbh="+$('#sel_slbh').val()
			 			+"&sel_gydw="+$('#sel_gydw').val()
			 			+"&sel_sDate="+$('#sel_date1').val()
			 			+"&sel_eDate="+$('#sel_date2').val()
			 			+"&sel_isFP="+$('#sel_isFP').val()
			 			+"&calDate="+$('#sel_caldate').val();
		 
		 var myUrl = "../../../servlet/DataRequestServlet?"+strParam;
		 table.clear();
		 table.ajax.url( myUrl).load();
		 
//		 InAndOutCheck.getReceiveListHis(searchParam,function(data){
//			 var table = $('#example').DataTable();
//			 console.info("=2");
//			 
//			 table.clear().draw();
//			 
//			 
//			 if(data[0].sel_bak>1500){
//				 alert("查询数据量"+data[0].sel_bak+"过大，请缩小范围");
//				 $('#searchInButton').text("查 询");
//				 return;
//			 }
//			
//			 for(var i=1;i<data.length;i++){
//				 table.row.add( [
//								  data[i].RECEIPT_ID,
//								  data[i].MATERIAL_BAK,
//								  data[i].RECEIPT_MATERIAL_NAME,
//								  data[i].RECEIPT_STYLE,
//								  data[i].RECEIPT_SIZE,
//								  
//								  data[i].RECEIPT_AR_MOUNT,
//								  data[i].RECEIPT_AR_MOUNT_HAS,
//								  data[i].RECEIPT_A_DJ,
//								  data[i].RECEIPT_A_AMOUNT,
//								  data[i].RECEIPT_FILLTIME,
//								  
//								  data[i].RECEIPT_RECEIVEDTIME,
//								  data[i].RECEIPT_INVOICENO,
//								  data[i].RECEIPT_RECEIPTER,
//								  data[i].RECEIPT_PURCHASER,
//								  data[i].RECEIPT_STORAGENO,
//								  
//								  data[i].RECEIPT_SUPPLYDEPT,
//								  data[i].RECEIPT_ITEM,
//								  data[i].RECEIPT_POSTION,
//								  data[i].CATORY_NAME,
//								  data[i].RECEIPT_BAK
//								                
//								]).draw();
//			 }
//			 $('#searchInButton').text("查 询");
//		 });
		 
		 
	 });
	 
	 $('#myButton').on('click', function () {
		 var table = $('#example').DataTable();
		// table.row().remove().draw( false );
		 //table.row('tr').remove().draw( true );
		// table.row('.odd').remove().draw( true );
		 table.clear().draw();
		
	 });
	 
	 
	 

   });