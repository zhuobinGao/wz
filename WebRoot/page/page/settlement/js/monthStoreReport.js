function initTable(){
	$('.tableExample').DataTable({
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
      "order": [[ 0, "asc" ]],
      dom: 'B<"clear">lfrtip',
      buttons: [
           'csv', 'excel', 'pdf', 'print'
      ]
		
  });
}

function initSelCangKu(){
	Common.getCangKu(function(data){
		$('#sel_cangKu').empty();
		
		for(var i=0;i<data.length;i++){
			$('#sel_cangKu').append("<option value='"+data[i].storeID+"'>"+data[i].storeName+"</option>");
		}
		$('#sel_cangKu').val(getCangKu());
	})
}

function initDate(){
	$('.form_date').datetimepicker({
        language:  'fr',
        format: 'yyyymm',  
        weekStart: 1,  
        autoclose: true,  
        startView: 3,  
        minView: 3,  
        forceParse: false,  
        pickerPosition: "bottom-left"
  });
}

function onCheckClick(){
	//sel_date1 sel_cangKu
	
	$('#selCheck').click(function(){
		if($('#sel_date1').val()==""){
			alert("年月不能为空！");
			return;
		}
		if($('#sel_cangKu').val()==""){
			alert("仓库不能为空！");
			return;
		}
		var param = {
				sqlID: "report_20",
				str0:$('#sel_cangKu').val(),
				str1:$('#sel_date1').val(),
				
				strCount:2
			};
		
		$('#selCheck').text("查询中...");
		
		
		PreStatement.getTabeData(param,function(data){
			
			 var strs;
			 var table = $('#example').DataTable();
			 table.clear().draw();
			 console.info(data);
			 for(var i=0;i<data.length;i++){
				 
			     strs = data[i].split("|");
			     if(strs[0]=="null"){
			    	 strs[0]="";
			     }
			     
			     table.row.add( [
					  strs[0],
					  strs[1],
					  strs[2],
					  strs[3],
					  strs[4],
					  
					  strs[5],
					  strs[6],
					  strs[7],
					  strs[8],
					  strs[9],
					  
					  strs[10],
					  strs[11]
							              
					]).draw();
			 }
			 $('#selCheck').text("查 询");
		 });
			
			
			
		
		
	});
}




$(document).ready(function() {
	
	initTable();
	initSelCangKu();
	initDate();
	onCheckClick();
	
});