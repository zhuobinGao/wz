var feeid="";

function initTable(){
	$('#example').DataTable({
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
       dom: 'Bfrtip',
       buttons: [
            'csv', 'excel', 'pdf', 'print'
       ]
		
   });
	
	
	var table = $('#example').DataTable();
	 
	$('#example tbody').on( 'click', 'tr', function () {
	        if ( $(this).hasClass('selected') ) {
	            $(this).removeClass('selected');
	            feeid = "";      
	            $('#upd_').val("");
	            $('#upd_llzg').val("");
	            $('#upd_lldw').val("");
	          
	        }
	        else {
	            table.$('tr.selected').removeClass('selected');
	            $(this).addClass('selected');
	            feeid = $(this).context.cells[0].innerHTML;      
	            $('#upd_llr').val($(this).context.cells[0].innerHTML);
	            $('#upd_llzg').val($(this).context.cells[1].innerHTML);
	            $('#upd_lldw').val($(this).context.cells[2].innerHTML);
	        }
	 } );
	
}

function initCommonData(){
	
	var param = {sqlID:"common21",str0:'1009',strCount:1	};
	PreStatement.getTabeData(param,function(data){
		//<option value=''></option>
		var str = "";
		$('#upd_llzg').append("<option value=''>==========</option>");
		$('#ins_llzg').append("<option value=''>==========</option>");
		for(var i=0;i<data.length;i++){
			strs = data[i].split("|");
			$('#upd_llzg').append("<option value='"+strs[0]+"'>"+strs[0]+"</option>");
			$('#ins_llzg').append("<option value='"+strs[0]+"'>"+strs[0]+"</option>");
		}
	});
	
	param = {sqlID:"common22",strCount:0	};
	PreStatement.getTabeData(param,function(data){
		//<option value=''></option>
		var str = "";
		$('#upd_lldw').append("<option value=''>==========</option>");
		$('#ins_lldw').append("<option value=''>==========</option>");
		for(var i=0;i<data.length;i++){
			strs = data[i].split("|");
			$('#upd_lldw').append("<option value='"+strs[1]+"'>"+strs[1]+"</option>");
			$('#ins_lldw').append("<option value='"+strs[1]+"'>"+strs[1]+"</option>");
		}
	});
	
	
	
}

function initCheck(){
	
	$('#s_check').click(function(){
		var param = {sqlID:"common21",str0:'1008',strCount:1};
		PreStatement.getTabeData(param,function(data){
			 var strs;
			 var table = $('#example').DataTable();
			 table.clear().draw();
			 for(var i=0;i<data.length;i++){
			     strs = data[i].split("|");
			     table.row.add( [ strs[0], strs[1],  strs[2] ]).draw();
			 }
		});
	});
	
}

function initUpdate(){
	$('#upd_save').click(function(){
		if(feeid==""){
			alert("请选择操作记录!");
			return;
		}
		
		var param = {sqlID:"common23",str0:$('#upd_llzg').val(),str1:$('#upd_lldw').val(),str2:$('#upd_llr').val(),strCount:3};
		console.info(param+"1");
		
		PreStatement.updateData(param,function(data){
			console.info(data)
			if("OK"==data){
				alert("已修领料人关联信息,请手工刷新!");
				$('#editModal').modal('hide');
			}else{
				alert("错误："+data);
			}
		});
		
		console.info("====");
	});
}



$(document).ready(function($) {
	initTable();
	initCommonData();
	initCheck();
	initUpdate();
});