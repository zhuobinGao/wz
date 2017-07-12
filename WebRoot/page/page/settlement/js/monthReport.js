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
				sqlID: "report_41",
				str0:$('#sel_date1').val(),
				str1:$('#sel_cangKu').val(),
				strCount:2
			};
		
		var param2 = {
				sqlID: "report_42",
				str0:$('#sel_date1').val(),
				str1:$('#sel_cangKu').val(),
				strCount:2
		};
		PreStatement.getTabeData(param2,function(data){
			var strs;
			
			
			strs = data[0].split("|");
			
			if(strs[0]=='null'){
				$('#s_start').html("");
				$('#s_in').html("");
				$('#s_out').html("");
				$('#s_has').html("");
				return;
			}
			
			$('#s_start').html(strs[0]);
			$('#s_in').html(strs[1]);
			$('#s_out').html(strs[2]);
			$('#s_has').html(strs[3]);
		});
		
		
		PreStatement.getTabeData(param,function(data){
			
			 var strs;
			 var table = $('#example').DataTable();
			 table.clear().draw();
			 for(var i=0;i<data.length;i++){
			     strs = data[i].split("|");
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
					  
					  strs[10]
							              
					]).draw();
			 }
			 
		 });
			
			
			
		
		
	});
}

$(document).ready(function() {
	
	initTable();
	initSelCangKu();
	initDate();
	onCheckClick();
	
});