var feeid = "";

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
	            $('#upd_fymc').val("");
	          
	        }
	        else {
	            table.$('tr.selected').removeClass('selected');
	            $(this).addClass('selected');
	            feeid = $(this).context.cells[1].innerHTML;      
	            $('#upd_fymc').val($(this).context.cells[2].innerHTML);
	        }
	 } );

}

function initCheck(){

	$("#s_search").click(function(){
		var name = $('#s_name').val();
		
		Common.getFeeList(name,function(data){
			 var strs;
			 var table = $('#example').DataTable();
			 table.clear().draw();
			 for(var i=0;i<data.length;i++){
			     strs = data[i].split("|");
			     table.row.add( [ strs[0], strs[1],  strs[2], strs[3], strs[4], strs[5] ]).draw();
			 }
		});
	});
	
}

function initType(){
	
	//ins_fylb
	$('#ins_fylb').html("");
	$('#ins_fylb').append("<option value='-1'>顶层分类</option>");
	var param = {sqlID:"sys_12",strCount:0	};
	PreStatement.getTabeData(param,function(data){
		//<option value=''></option>
		var str = "";
		for(var i=0;i<data.length;i++){
			strs = data[i].split("|");
			$('#ins_fylb').append("<option value='"+strs[0]+"'>"+strs[1]+"</option>");
		}
	});
};


function initAddFee(){
	$('#ins_save').click(function(){
		
		var param = {
				sqlID:"fee_04",
				str0:$('#ins_fymc').val(),
				str1:$('#ins_fylb').val(),
				str2:"宝满码头",
				strCount:3
		}
		
		PreStatement.updateData(param,function(data){
			if("OK"==data){
				alert("已新增费用项目");
				$('#myModal').modal('hide');
			}else{
				alert("错误："+data);
			}
			
		});
	});
}

function initEditFee(){
	$('#upd_save').click(function(){
		if(feeid==""){
			alert("请选择一条数据操作!");
			return;
		}
		var param = {
				sqlID:"fee_12",
				str0:$('#upd_fymc').val(),
				str1:feeid,
				strCount:2
		}
		
		PreStatement.updateData(param,function(data){
			if("OK"==data){
				alert("已修改费用项目");
				$('#editModal').modal('hide');
			}else{
				alert("错误："+data);
			}
			
		});
		
		
	});
}

function initDelFee(){
	$('#delMaterial').click(function(){
		if(feeid==""){
			alert("请选择一条数据操作!");
			return;
		}
		var param = {
				sqlID:"fee_08",
				str0:feeid,
				strCount:1
		}
		
		PreStatement.updateData(param,function(data){
			if("OK"==data){
				alert("已删除费用项目");
				$('#delModal').modal('hide');
			}else{
				alert("错误："+data);
			}
			
		});
		
		
	});
}



$(document).ready(function($) {
   
	initTable();
	initCheck();
	initType();
	initAddFee();
	initEditFee();
	initDelFee();
	
});