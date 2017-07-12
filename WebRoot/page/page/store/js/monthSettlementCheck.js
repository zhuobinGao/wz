var yearMonth = "";
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

function check(){
	if(getStoreHouseID()=="null" || getStoreHouseID()==""){
		return true;
	}
	if(getUserID()=="null" || getUserID()==""){
		return true;
	}
	if(getUserName()=="null" || getUserName()==""){
		return true;
	}
	return false;
}

function goToVaild(){
	$('#ins_save').click(function(){
		
		if(check()){
			alert("用户凭证过时，请重新登录!");
			return;
		}
		console.info("============");
		var date = $('#ins_date').val();
		var houseID = getStoreHouseID();
		var username = getUserName();
		if(date==""){
			alert("请选择日期！");
			return;
		}
		JieSuan.verify(date,houseID,username,function(data){
			$('#myModal').modal('hide');
			alert(data);
		});
	});
}

function goToUnVaild(){
	$('#upd_save').click(function(){
		if(check()){
			alert("用户凭证过时，请重新登录!");
			return;
		}
		var date = $('#upd_date').val();
		var houseID = getStoreHouseID();
		var username = getUserName();
		if(date==""){
			alert("请选择日期！");
			return;
		}
		JieSuan.unVerify(date,houseID,username,function(data){
			$('#editModal').modal('hide');
			alert(data);
		});
	});
	
}

$(document).ready(function($) {
   
	initTable();
	initSelCangKu();  
	initDate();
	onChecK();
	goToVaild();
	goToUnVaild();
	
});