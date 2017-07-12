var storeHouse = "";
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
	            storeHouse = "";
	            $('#upd_ckmc').val("");
	            $('#upd_bak').val("");
	        }
	        else {
	            table.$('tr.selected').removeClass('selected');
	            $(this).addClass('selected');
	            storeHouse = $(this).context.cells[0].innerHTML;      
	            $('#upd_ckmc').val($(this).context.cells[1].innerHTML);
	            $('#upd_bak').val($(this).context.cells[2].innerHTML);
	        }
	    } );
	  
	
}


function search(){
	$('#check').click(function(){
		Baseset.searchWareHouse($('#s_name').val(),function(data){
			 var strs;
			 var table = $('#example').DataTable();
			 table.clear().draw();
			 for(var i=0;i<data.length;i++){
			     strs = data[i].split("|");
			     table.row.add( [ strs[0], strs[1],  strs[2]]).draw();
			 }
		});
	});
	
}

function initEdit(){
	
	
	
	$('#upd_save').click(function(){
		if(storeHouse==""){
			alert("请选择一条数据操作！");
			return;
		}
		var param = {
				sqlID:"storeHouse_03",
				str0:$('#upd_ckmc').val(),
				str1:$('#upd_bak').val(),
				str2:storeHouse,
				strCount:3
		}
		PreStatement.updateData(param,function(data){
			if("OK"==data){
				alert("已修改仓库信息,请手工刷新!");
				$('#editModal').modal('hide');
			}else{
				alert("错误："+data);
			}
		});
		
		
	});
}

function initNew(){
	$('#ins_save').click(function(){
		
		if($('#ins_ckmc').val()==""){
			alert("仓库名不能为空！");
			return;
		}
		
		
		var param = {
				sqlID:"storeHouse_01",
				str0:$('#ins_ckmc').val(),
				str1:$('#ins_bak').val(),
				strCount:2
		}
		PreStatement.updateData(param,function(data){
			if("OK"==data){
				alert("已添加新仓库,请手工刷新!");
				$('#myModal').modal('hide');
			}else{
				alert("错误："+data);
			}
		});
		
		
	});
}

function initDel(){
	
	$('#delMaterial').click(function(){
		if(storeHouse==""){
			alert("请选择一条数据操作！");
			return;
		}
		Baseset.delStoreHouse(storeHouse,function(data){
			
			if("OK"==data){
				alert("已删除仓库！");
				$('#delModal').modal('hide');
				return;
			}
			alert(data);
		});
	})
	
	
	
	
}

$(document).ready(function($) {
	initTable();
	search();
	initEdit();
	initNew();
	initDel();
});