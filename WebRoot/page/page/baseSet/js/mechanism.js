var mechanismID = "";

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
       "lengthMenu": [[20, 30, 100, -1], [20, 30, 100, "All"]],
       "order": [[ 1, "desc" ]],
       dom: 'Bfrtip',
       buttons: [
            'csv', 'excel', 'pdf', 'print'
       ]
		
   });
	
	var table = $('#example').DataTable();
	 
    $('#example tbody').on( 'click', 'tr', function () {
        if ( $(this).hasClass('selected') ) {
            $(this).removeClass('selected');
            mechanismID= "";
           
        }
        else {
            table.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
            console.info($(this));
            mechanismID = $(this).context.cells[0].innerHTML;      
            $('#upd_jgmc').val($(this).context.cells[1].innerHTML);
            $('#upd_jzmc').val($(this).context.cells[3].innerHTML);
         
        }
    } );
	
	
}

function initAdd(){
	$('#ins_save').click(function(){
		var param = {
				sqlID:"common_05",
				str0:$('#ins_jgmc').val(),
				str1:$('#ins_jzmc').val(),
				strCount:2
		}
		PreStatement.updateData(param,function(data){
			if("OK"==data){
				alert("已添加新机种机构,请手工刷新!");
				$('#myModal').modal('hide');
			}else{
				alert("错误："+data);
			}
		});
		
		
	});
}

function initEdit(){
	$('#upt_save').click(function(){
		if(mechanismID==""){
			alert("请选择一条机种机构数据操作!");
			return;
		}
		var param = {
				sqlID:"common_06",
				str0:$('#upd_jgmc').val(),
				str1:$('#upd_jzmc').val(),
				str2:mechanismID,
				strCount:3
		}
		PreStatement.updateData(param,function(data){
			if("OK"==data){
				alert("已更新机种机构,请手工刷新!");
				$('#eidtModal').modal('hide');
			}else{
				alert("错误："+data);
			}
		});
		
		
	});
}

function initCheck(){
	$('#s_check').click(function(){
		 $('#s_check').html("查询中...");
		Baseset.getMechanismList($('#s_jgmc').val(),function(data){
			 var strs;
			 var table = $('#example').DataTable();
			 console.info(data);
			 table.clear().draw();
			 for(var i=0;i<data.length;i++){
			     strs = data[i].split("|");
			     table.row.add( [ strs[0], strs[1], strs[2],strs[3] ]).draw();
			 }
			 $('#s_check').html("查 询");
		});
		
		
	});
}

function initDel(){
	$('#delMaterial').click(function(){
		if(mechanismID==""){
			alert("请选择一条机种机构数据操作!");
			return;
		}
		var param = {
				sqlID:"common_07",
				str0:mechanismID,
				strCount:1
		}
		PreStatement.updateData(param,function(data){
			if("OK"==data){
				alert("已删除机种机构,请手工刷新!");
				$('#delModal').modal('hide');
			}else{
				alert("错误："+data);
			}
		});
		
		
	});
}

$(document).ready(function($) {
  
	initTable();
	initAdd();
	initEdit();
	initCheck();
	initDel();
	
});