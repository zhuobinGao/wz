var yearMonth = "";
var flag = false;
var flag2 = true;
var loginID = "";
var messageResult = "";
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
	            yearMonth = "";
	        }
	        else {
	            table.$('tr.selected').removeClass('selected');
	            $(this).addClass('selected');
	            yearMonth = $(this).context.cells[0].innerText
	        }
	    } );
	
}

function initSelCangKu(){
	Common.getCangKu(function(data){
		$('#sel_cangKu').empty();
		
		for(var i=0;i<data.length;i++){
			$('#sel_cangKu').append("<option value='"+data[i].storeID+"'>"+data[i].storeName+"</option>");
		}
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

function onChecK(){
	$('#onCheck').click(function(){
		if(getStoreHouseID()==""){
			alert("凭证超时,请重新登录！");
			return;
		}
		 
		if($('#sel_date1').val()==""){
			alert("请输入起始日期!");
			return;
		}
		
		if($('#sel_date2').val()==""){
			alert("请输入结束日期!");
			return;
		}
		
		
		var houseID = getStoreHouseID();
		var date1 = $('#sel_date1').val();
		var date2 = $('#sel_date2').val();
		
		
		var param = {
				sqlID: "report_45",
				str0:date1,
				str1:date2,
				str2:houseID,
				strCount:3	
		}
		console.info(param);
		PreStatement.getTabeData(param,function(data){
			
			 var strs;
			 var table = $('#example').DataTable();
			 table.clear().draw();
			 for(var i=0;i<data.length;i++){
			     strs = data[i].split("|");
			     table.row.add( [ strs[0], strs[1], strs[2], strs[3], strs[4] ]).draw();
			 }
			 
		 });
		
		
		
		
	});
	
}

function initBar(){
	$('#cal_bar').attr("style","width: 0%");
	$('#cal_bar').text("0%");
	$('#text_bar').text("");
}

function listener(){
	
	JieSuan.getPMessage(loginID,function(data){
		$('#text_bar').text(data);
	});
	
	JieSuan.getPCount(loginID,function(data){
		$('#cal_bar').attr("style","width: "+data);
		$('#cal_bar').text(data);
	});
	if(flag&flag2){
		$('#barModal').modal('hide');
		alert(messageResult);
		flag = false;
		initBar();
		return;
	}
	
	if(!flag&flag2){
		setTimeout("listener()", 600 );
	}
	
}

function check(){
	if(getStoreHouseID()=="null" || getStoreHouseID()==""){
		return true;
	}
	if(getUserID()=="null" || getUserID()==""){
		return true;
	}
	
	
	return false;
}

function onAddMonth(){
	
	$('#add_save').click(function(){
		if(check()){
			alert("用户凭证过时，请重新登录!");
			return;
		}
		var date = $('#ins_date').val();
		var year = date.substring(0,4);
		var month = date.substring(4,6);
		
		var houseID = getStoreHouseID();
		loginID = getUserID();
		
		if(date==""){
			alert("请选择日期！");
			return;
		}
		console.info(year+"|"+month+"|"+houseID+"|"+loginID);
		flag2 = true;
		flag = false;
		JieSuan.calute(year,month,houseID,loginID,function(data){
			if(data=="OK"){
				flag = true;
				messageResult = "结算成功！";
			}else{
				flag2 = false;
				messageResult =data;
				$('#barModal').modal('hide');
				initBar();
				alert(data);
			}
			$('#barModal').modal('hide');
		});
		$('#cal_bar').attr("style","width: 0%");
		$('#cal_bar').text("0%");
		$('#text_bar').text("");
		
		$('#myModal').modal('hide');
		$('#barModal').modal({backdrop: 'static', keyboard: false});
		listener();
		
		
	});
	
	
}

function onCancelMonth(){
	$('#cancel_save').click(function(){
		if(check()){
			alert("用户凭证过时，请重新登录!");
			return;
		}
		var date = $('#upd_date').val();
		var year = date.substring(0,4);
		var month = date.substring(4,6);
		
		var houseID = getStoreHouseID();
		loginID = getUserID();
		if(date==""){
			alert("请选择日期!");
			return;
		}
		JieSuan.cancelCalute(year,month,houseID,loginID,function(data){
			if(data=="OK"){
				alert("成功取消结算！");
			}else{
				alert("取消结算失败,请重新再试！");
			}
			$('#eidtModal').modal('hide');
		});
		
		//160100453
	});
	
}
//---------------------------------------------------------------


$(document).ready(function($) {
   
	initTable();
	initSelCangKu();  
	initDate();
	onChecK();
	onAddMonth();
	onCancelMonth();
//	$('#barModal').modal({backdrop: 'static', keyboard: false});
//	$('#barModal').modal('hide');
	
});
