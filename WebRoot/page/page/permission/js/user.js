var houseID;
var groupID;
var userID;
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
        "order": [[ 0, "desc" ]],
        dom: 'Bfrtip',
        buttons: [
             'csv', 'excel', 'pdf', 'print'
        ]
		
    });
	 
	 var table = $('#example').DataTable();
	 
	    $('#example tbody').on( 'click', 'tr', function () {
	        if ( $(this).hasClass('selected') ) {
	            $(this).removeClass('selected');
	            userID= "";
	           
	        }
	        else {
	            table.$('tr.selected').removeClass('selected');
	            $(this).addClass('selected');
	            console.info($(this));
	            houseID = $(this).context.cells[4].innerHTML;      
	            groupID = $(this).context.cells[5].innerHTML;
	            userID = $(this).context.cells[0].innerHTML;
	            $('#upd_logid').val($(this).context.cells[0].innerHTML);
	            $('#upd_name').val($(this).context.cells[1].innerHTML);
	            $('#upd_ck').val(houseID);
	            $('#upd_group').val(groupID);
	        }
	    } );
}

function initCangKu(){
	var param = {sqlID:"sys_06",strCount:0};
	
	PreStatement.getTabeData(param,function(data){
		//<option value=''></option>
		var str = "";
		for(var i=0;i<data.length;i++){
			strs = data[i].split("|");
			$("#ins_ck").append("<option value='"+strs[0]+"'>"+strs[1]+"</option>");
			$("#upd_ck").append("<option value='"+strs[0]+"'>"+strs[1]+"</option>");
		}
		
	});
	
}

function initGroup(){
	var param = {sqlID:"sys_07",strCount:0	};
	
	PreStatement.getTabeData(param,function(data){
		//<option value=''></option>
		var str = "";
		for(var i=0;i<data.length;i++){
			strs = data[i].split("|");
			$("#ins_group").append("<option value='"+strs[0]+"'>"+strs[1]+"</option>");
			$("#upd_group").append("<option value='"+strs[0]+"'>"+strs[1]+"</option>");
		}
		
	});
}




function initCheck(){
	
	$('#s_check').click(function(){
		var userID = $('#s_userID').val();
		var userName = $('#s_userName').val();
		$('#s_check').html("查询中...");
		UserAndPermission.getUserList(userID,userName,function(data){
			 var strs;
			 var table = $('#example').DataTable();
			 table.clear().draw();
			 for(var i=0;i<data.length;i++){
			     strs = data[i].split("|");
			     table.row.add( [ strs[0], strs[1], strs[2],strs[3],strs[4],strs[5] ]).draw();
			 }
			 $('#s_check').html("查 询");
		});
		
		
	});
	
}

function initAddUser(){
	$('#ins_save').click(function(){
		
		
		if($('#ins_pwd2').val()!=$('#ins_pwd1').val()){
			alert("两次密码不一致！");
			return;
		}

		var mypwd = "";
		dwr.engine.setAsync(false);  
		EncrypDES.getenCord($('#ins_pwd1').val(),function(data){
			mypwd = data;
		});
		
		console.info("mypwd="+mypwd);
		
		
		
		var param = {
				sqlID:"user_01",
				str0:$('#ins_logid').val(),
				str1:$('#ins_name').val(),
				str2:mypwd,
				str3:$('#ins_ck').val(),
				str4:$('#ins_group').val(),
				strCount:5
		}

//		console.info(param);
		PreStatement.updateData(param,function(data){
			if("OK"==data){
				alert("已新增用户信息,请手工刷新!");
				$('#myModal').modal('hide');
			}else{
				alert("错误："+data);
			}
			
		});
		
		
		
		
		
	});
}

function initEditUser(){
	$('#upd_save').click(function(){
		if(houseID==""){
			alert("请选择一条数据!");
			return ;
		}
		
		if($('#upd_pwd1').val()!=$('#upd_pwd2').val()){
			alert("两次密码不一致！");
			return;
		}

		var mypwd = "";
		dwr.engine.setAsync(false);   
		EncrypDES.getenCord($('#upd_pwd1').val(),function(data){
			mypwd = data;
		});
		

		
		var param = {
				sqlID:"user_03",
				str0:$('#upd_name').val(),
				str1:mypwd,
				str2:$('#upd_ck').val(),
				str3:$('#upd_group').val(),
				str4:$('#upd_logid').val(),
				strCount:5
		}

		console.info(param);
		PreStatement.updateData(param,function(data){
			if("OK"==data){
				alert("已更新用户信息,请手工刷新!");
				$('#eidtModal').modal('hide');
			}else{
				alert("错误："+data);
			}
			
		});
		
		
		
		
		
	});
}

function initDelUser(){
	
	$('#del_save').click(function(){
		if(houseID==""){
			alert("请选择一条数据!");
			return ;
		}
		
		
		var param = {
				sqlID:"user_04",
				str0:userID,
				strCount:1
		};
		PreStatement.updateData(param,function(data){
			if("OK"==data){
				alert("已删除用户信息,请手工刷新!");
				$('#delModal').modal('hide');
			}else{
				alert("错误："+data);
			}
			
		});
	});
	
	
	
}

$(document).ready(function($) {
    console.info("==");
	initTable();
	initCheck();
	initAddUser();
	initEditUser();
	initDelUser();
	initCangKu();
	initGroup();
});
