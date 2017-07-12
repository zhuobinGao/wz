var categoryID = "";
var categoryName = "";
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
	
}

function initCheck(){

	$("#s_search").click(function(){
		var param = ["","",""];
		param[0] = $("").val();
		param[1] = $("sel_Name").val();
		param[2] = $("sel_FName").val();
		
		Baseset.getCategoryList(param,function(data){
			 var strs;
			 var table = $('#example').DataTable();
			 table.clear().draw();
			 for(var i=0;i<data.length;i++){
			     strs = data[i].split("|");
			     table.row.add( [ strs[0], strs[1],  strs[2], strs[3] ]).draw();
			 }
		});
		
		
		 Baseset.getCategoryString(param,function(data){
			 $.jstree.destroy ();
			
			 $("#leftTree").append(data);
				
			 $('#leftTree').jstree({	}); 
			 $('#leftTree').on("changed.jstree", function (e, data) {
		      		categoryID = data.node.id;
		      		categoryName = data.node.text;
		      		$('#ins_wzlb').val(categoryName);
		      		//upd_wzmc
		      		$('#upd_wzmc').val(categoryName);
		      		console.info(data)
			 });
			 
		 });
		
	});
	
}

function initAdd(){
	$("#ins_save").click(function(){
		if(categoryID==""){
			alert("请在左侧面板选择分类项目！");
			return;
		}
		if($('#ins_wzmc').val()==""){
			alert("分类名称不能为空！");
			return;
		}
		
		var param = {
				sqlID:"catory_01",
				str0:$('#ins_wzmc').val(),
				str1:categoryID,
				str2:"",
				str3:"",
				strCount:4
		}
		PreStatement.updateData(param,function(data){
			if("OK"==data){
				alert("已添加新分类,请手工刷新!");
				$('#myModal').modal('hide');
			}else{
				alert("错误："+data);
			}
		});
	});
	
	
}

function initEdit(){
	$("#upd_save").click(function(){
		if(categoryID==""){
			alert("请在左侧面板选择分类项目！");
			return;
		}
		var param = {
				sqlID:"catory_06",
				str0:$('#upd_wzmc').val(),
				str1:"",
				str2:"",
				str3:categoryID,
				strCount:4
		}
		PreStatement.updateData(param,function(data){
			if("OK"==data){
				alert("已修改分类！请手工刷新");
				$('#editModal').modal('hide');
			}else{
				alert("错误："+data);
			}
			
		});
		
		
		
		
		
		
		
	});
}

function initDel(){
	$("#delMaterial").click(function(){
		if(categoryID==""){
			alert("请在左侧面板选择分类项目！");
			return;
		}
		
		Baseset.delCategory(categoryID,function(data){
			if("OK"==data){
				alert("已删除分类！");
				$('#delModal').modal('hide');
				return;
			}
			alert(data);
		});

	});
}

$(document).ready(function($) {
	
	initTable();
	initCheck();
	initAdd();
	initEdit();
	initDel();
})