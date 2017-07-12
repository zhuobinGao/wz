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
        dom: 'Blrtip',
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
		
	 
	 //定义领料人
	 
	 var strs = ["","1008"];
	 Common.getConfigValue(strs,function(data){
		 for(i=0;i<data.length;i++){
			 $("#sel_llr").append("<option>"+data[i]+"</option>");
		 }
	 });
	 
	 strs = ["","1009"];
	 Common.getGYDW(function(data){
		 for(i=0;i<data.length;i++){
			 $("#sel_lldw").append("<option>"+data[i]+"</option>");
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
		 var strParam = "param=searchOutHis"
	 			+"&calDate="+$('#sel_caldate').val()
	 			+"&sel_llr="+$('#sel_llr').val()
	 			
	 			+"&sel_lldw="+$('#sel_lldw').val()
	 			+"&sel_postion="+$('#sel_postion').val()
	 			+"&sel_item="+$('#sel_fyxm').val()
	 			+"&sel_materialName="+$('#sel_materialName').val()
	 			+"&sel_category="+$('#sel_category').val()
	 			
	 			+"&sel_xxyt="+$('#sel_xxyt').val()
	 			+"&sel_llbh="+$('#sel_llbh').val()
	 			+"&sel_gydw="+$('#sel_gydw').val()
	 			+"&sel_sDate="+$('#sel_date1').val()
	 			+"&sel_eDate="+$('#sel_date2').val();
		 
		 var myUrl = "../../../servlet/DataRequestServlet?"+strParam;
		 table.clear();
		 table.ajax.url( myUrl).load();
		 
	 });
	
	//end
});