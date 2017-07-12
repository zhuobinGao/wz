var fid = [""];
fid = [];
var id = "";
function initLeftTree(){
	var param = {sqlID:"sys_07",strCount:0	};
	$('#leftTree').html("");
	PreStatement.getTabeData(param,function(data){
		//<option value=''></option>
		var str = "";
		for(var i=0;i<data.length;i++){
			strs = data[i].split("|");
			$('#leftTree').append("<a href='#' class='list-group-item' id='"+strs[0]+"'>"+strs[1]+"</a>");
			console.info(strs);
		}
		
		initSavePermission();
		
	});
}

function initPermission(data){
	$.jstree.destroy();
	$("#rightTree").html(data);
	$('#rightTree').on("load_node.jstree",function(e,data){
		
		console.info(data.length);
	
	}).jstree({	
	"core" : {"themes" : {"variant" : "large"}	},
	"checkbox" : {"keep_selected_style" : false },
		"plugins" : [ "wholerow", "checkbox" ]
	}); 

	$('#rightTree').on("changed.jstree", function (e, data) {
		var i, j, r = [];
		//console.info(data);
		fid = [];
		for(i = 0, j = data.selected.length; i < j; i++) {
		  r.push(data.instance.get_node(data.selected[i]).text+data.selected[i]);
		  fid[i] = data.instance.get_node(data.selected[i]).id;
		}
		$('#event_result').html('Selected1: ' + r.join(', '));
  	 });
}

function initFunction(){
	UserAndPermission.getPermission("-1",function(data){
//		console.info("==>"+data);
		initPermission(data);
	});
}

function initSavePermission(){
	
	$('#leftTree .list-group-item').click(function(){
		$('#leftTree .list-group-item').removeClass("active");
		$(this).addClass("active")
		id = $(this).attr('id');
		
		var param = {sqlID:"sys_11",str0:id,strCount:1};
		UserAndPermission.getPermission(id,function(data){
			initPermission(data);
		});
		
		
	});
	
	$('#s_newCate').click(function(){
		if(id==""){
			alert("请选择权限组!");
			return;
		}
		
		if(fid.length<1){
			alert("请选择功能权限!");
			return;
		}
		
		
		UserAndPermission.savePermission(id,fid,function(data){
			console.info("=========123");
			if("OK"==data){
				alert("已保存权限");
			}else{
				alert("错误："+data);
			}
		});
	});
	
	
}

function initNewGroup(){
	
	$('#ins_save').click(function(){
		var param = {
				sqlID:"group_01",
				str0:$('#ins_logid').val(),
				strCount:1
		}
		
		PreStatement.updateData(param,function(data){
			if("OK"==data){
				alert("已新增用户组");
				initLeftTree();
				$('#myModal').modal('hide');
			}else{
				alert("错误："+data);
			}
			
		});
	});
	
	
}

function initDelGroup(){
	
	$('#del_save').click(function(){
		var param = {
				sqlID:"group_05",
				str0:id,
				strCount:1
		}
		PreStatement.updateData(param,function(data){
			if("OK"==data){
				alert("已删除用户组");
				initLeftTree();
				$('#delModal').modal('hide');
			}else{
				alert("错误："+data);
			}
			
		});
	});
	
}


$(document).ready(function($) {
	initLeftTree();
	initFunction();
	initNewGroup();
	initDelGroup();
	
	
	
})