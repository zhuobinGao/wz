var commonValue = "";
var commonTyle = ""
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
        "order": [[ 1, "asc" ]],
        dom: 'Bfrtip',
        buttons: [
             'csv', 'excel', 'pdf', 'print'
        ]
		
    });
	 
	 var table = $('#example').DataTable();
	 
	    $('#example tbody').on( 'click', 'tr', function () {
	        if ( $(this).hasClass('selected') ) {
	            $(this).removeClass('selected');
	            commonValue = "";
	            commonTyle = "";
	            $('#upd_ckmc').val("");
	            $('#upd_bak').val("");
	        }
	        else {
	            table.$('tr.selected').removeClass('selected');
	            $(this).addClass('selected');
	            commonValue = $(this).context.cells[0].innerHTML;      
	            commonTyle = $(this).context.cells[2].innerHTML;
	            $('#upd_xxmc').val(commonValue);
	            $('#upd_xxlb').val(commonTyle);
	            console.info(commonValue);
	        }
	    } );
	  
	
}

function initCheck(){
	
	$('#s_check').click(function(){
		var param = {
				sqlID:"common_01",
				strCount:0
		}
		$('#s_check').html("查询中...");
		
		PreStatement.getTabeData(param,function(data){
			 var strs;
			 var table = $('#example').DataTable();
			 table.clear().draw();
			 for(var i=0;i<data.length;i++){
			     strs = data[i].split("|");
			     table.row.add( [ strs[0], strs[1], strs[2] ]).draw();
			 }
			 $('#s_check').html("查 询");
		})
		
	});
	
}

function initAddInfo(){
	
	$('#ins_save').click(function(){
		
		if($('#ins_xxmc').val()==""){
			alert("信息名称不能为空!");
			return;
		}
		
		var param = {
				sqlID:"common_02",
				str0:$('#ins_xxmc').val(),
				str1:$('#ins_xxlb').val(),
				strCount:2
		}
		
		console.info(param);
		PreStatement.updateData(param,function(data){
			if("OK"==data){
				alert("已新增基础常用信息,请手工刷新!");
				$('#myModal').modal('hide');
			}else{
				alert("错误："+data);
			}
			
		});
		
		
		
	});
	
}

function initEidtInfo(){
	$('#upd_save').click(function(){
		
		if($('#upd_xxmc').val()==""){
			alert("信息名称不能为空!");
			return;
		}
		
		var param = {
				sqlID:"common_03",
				str0:$('#upd_xxmc').val(),
				str1:commonValue,
				str2:$('#upd_xxlb').val(),
				strCount:3
		}
		
		console.info(param);
		PreStatement.updateData(param,function(data){
			if("OK"==data){
				alert("已更新基础常用信息,请手工刷新!");
				$('#editModal').modal('hide');
			}else{
				alert("错误："+data);
			}
			
		});
		
		
		
	});
	
}

function initDelInfo(){
	$('#delCommon').click(function(){
		
		if(commonValue==""){
			alert("请选择操作数据!");
			return;
		}
		
		var param = {
				sqlID:"common_04",
				str0:commonValue,
				str1:commonTyle,
				strCount:2
		}
		console.info(param);
		PreStatement.updateData(param,function(data){
			if("OK"==data){
				alert("已删除基础常用信息,请手工刷新!");
				$('#delModal').modal('hide');
			}else{
				alert("错误："+data);
			}
			
		});	
	});
}



$(document).ready(function($) {
	console.info("==1==");
	initTable();
	initCheck();
	initAddInfo();
	initEidtInfo();
	initDelInfo();
});