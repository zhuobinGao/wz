
var materialID = "";
var mytr;
var table;

function bindMaterial(data){
	//data.context.cells[0].innerText;
	$('#upt_wzmc').val(data.context.cells[1].innerText);
	$('#upt_wzlb').val(data.context.cells[2].innerText);
	$('#upt_categoryID').val(data.context.cells[0].innerText);
	$('#upt_wzgg2').val(data.context.cells[3].innerText);
	$('#upt_jldw2').val(data.context.cells[4].innerText);
	$('#upt_wzbh2').val(data.context.cells[5].innerText);
}

function bindImg(materialID){
	$('#image').attr("src","../../Image?materialID="+materialID+"&random="+Math.random());
	
}

function reSetMaterial(){
	$('#upt_wzmc').val('');
	$('#upt_wzlb').val('');
	$('#upt_categoryID').val('');
	$('#upt_wzgg2').val('');
	$('#upt_jldw2').val('');
	$('#upt_wzbh2').val('');
}

function closeAddMaterial(){
	$('#myModal').modal('hide');
	$('#ins_wzmc').val('');
	$('#ins_wzlb').val('');
	$('#ins_wzgg').val('');
	$('#ins_jldw').val('');
	$('#ins_wzbh').val('');
}

function bindCheck(){
	var v = $("#isCheck").is(':checked');
	
	if(v){
		$('#ins_kcxx').attr("disabled",false);
		$('#ins_kcsx').attr("disabled",false);
	}else{
		$('#ins_kcxx').attr("disabled",true);
		$('#ins_kcsx').attr("disabled",true);
	}
}

function checkBoxChange(){
	console.info("fdsfas");
	$('#isCheck').click(function(){
		bindCheck();
		
	});
}

function keyupListener(){
	$( "#s_category" ).keyup(function(){
		 var s_category = $("#s_category").val();
		 Common.getCategoryName(s_category,function(data){			 
			 $('#s_category').autocomplete({
		         	source:data, 
		         	minLength:0,
			        delay:200
			 });
		 });
	 });
	
	 $("#s_gys").keyup(function(){
		 var sel_fyxm = $("#s_gys").val();
		 strs = [sel_fyxm,"1005"];
		 Common.getConfigValue(strs,function(data){			 
			 $('#s_gys').autocomplete({
		         	source:data, 
		         	minLength:0,
			        delay:200
			 });
		 });
	 }); 
	
}

function queryData(){
$('#mCheck').click(function(){
		
		$('#mCheck').text("查询中...");
		$('#myCheck').attr("disabled",true);
		var myUrl = "../../../servlet/AjaxServlet?dataType=checkMaterial&materialName="+$('#s_productName').val()+"&supplyDept="+$('#s_gys').val()+"&categoryName="+$('#s_category').val();
		table.ajax.url(myUrl);
		table.ajax.reload( function ( json ) {
			$('#mCheck').text("查 询");
			$('#myCheck').attr("disabled",false);
		} );	
		
	});
}

function initTable(){
	 $('.tableExample tfoot th').each( function () {
	        var title = $(this).text();
	        $(this).html( '<input type="text" placeholder="查询 '+title+'" />' );
	 } );
	
	 table = $('.tableExample').DataTable({
		"ajax":  "../../../servlet/AjaxServlet?dataType=checkMaterial&materialName="+$('#s_productName').val()+"&supplyDept="+$('#s_gys').val()+"&categoryName="+$('#s_category').val(),
	    "deferRender": true,
//		 "processing": true,
//	     "serverSide": true,
//	        "ajax": {
//	            "url": "../../../servlet/AjaxServlet?dataType=checkMaterial&materialName="+$('#s_productName').val()+"&supplyDept="+$('#s_gys').val()+"&categoryName="+$('#s_category').val(),
//	            "type": "GET"
//	        },
		 
		 "language": {
			    "sProcessing":   "处理中...",
			    "sLengthMenu":   "显示 _MENU_ 项结果",
			    "sZeroRecords":  "没有匹配结果",
			    "sInfo":         "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
			    "sInfoEmpty":    "显示第 0 至 0 项结果，共 0 项",
			    "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
			    "sInfoPostFix":  "",
			    "sSearch":       "搜索:",
			    "sUrl":          "",
			    "sEmptyTable":     "表中数据为空",
			    "sLoadingRecords": "载入中...",
			    "sInfoThousands":  ",",
			    "oPaginate": {
			        "sFirst":    "首页",
			        "sPrevious": "上页",
			        "sNext":     "下页",
			        "sLast":     "末页"
			    },
			    "oAria": {
			        "sSortAscending":  ": 以升序排列此列",
			        "sSortDescending": ": 以降序排列此列"
			    }
	   },
	   "pagingType": "full_numbers",
	   "scrollX": true,
	   "lengthMenu": [[10, 20, 100, -1], [10, 20, 100, "All"]],
	   "order": [[ 0, "desc" ]],
	   dom: 'B<"clear">lfrtip',
	   buttons: [
	        'csv', 'excel', 'pdf', 'print'
	   ]
			
	});
	
//	 table.columns().every( function () {
//	     var that = this;
//	     $( 'input', this.footer() ).on( 'keyup change', function () {
//	         if ( that.search() !== this.value ) {
//	             that.search( this.value ).draw();
//	         }
//	     } );
//     } );
	 
}

function closeAddMaterial(){
	$('#ins_wzmc').val('');
	$('#ins_wzlb').val('');
	$('#ins_wzgg').val('');
	$('#ins_jldw').val('');
	$('#ins_kc').val('');
	$('#ins_kcxx').val('');
	$('#ins_kcsx').val('');
}

function updateKC(){
	$('#ins_save').click(function(){
		if(materialID==""){alert("请选择一条数据操作！");return;}
		var qy = 0,DLine,UpLine;
		var qymes = "已关闭【"+$('#ins_wzmc').val()+"】的";
		var qymes2 = "未启用";
		if($("#isCheck").is(':checked')){
			qy = 1;
			qymes = "已启用【"+$('#ins_wzmc').val()+"】的";
			qymes2 = "已启用";
		}
		
		DLine = $('#ins_kcxx').val();
		UpLine = $('#ins_kcsx').val();
		
		if(DLine==null || DLine=="") DLine = "0";
		if(UpLine==null || UpLine=="") UpLine = "99999";
		
		 $("#mes1").attr("class","alert alert-success in hidden");
		 $("#mes2").attr("class","alert alert-danger in hidden");
		
		 
		 console.info(Report);
		 
		 
		 
		 
		 Report.updateKunCun(qy,DLine ,UpLine, materialID,function(data){
			 console.info("OK")
			if(data=="OK"){
				$('#qy1').text(qymes);
				console.info($('#qy1').text());
				$("#mes1").removeClass("hidden");
				$('#myModal').modal('hide');
				
				
				
				mytr.context.cells[8].innerText = qymes2;
				mytr.context.cells[9].innerText = UpLine;
				mytr.context.cells[10].innerText = DLine;
				console.info(mytr);
				return;
			}
			$('#qy2').text("错误:"+data);
			$("#mes2").removeClass("hidden");
			$('#myModal').modal('hide');
			
			console.info()
			
			
			
		});
//		
	});
	
}


$(document).ready(function($) {
	keyupListener();
	checkBoxChange();
	initTable();
	queryData();
	
	updateKC();
	
	
	
	
	
	
	
	
	
	$('#exampleMaterial tbody').on( 'click', 'tr', function () {
        if ( $(this).hasClass('selected') ) {
            $(this).removeClass('selected');
            materialID = "";
            mytr = null;
           $('#isCheck').attr("checked",false);
           closeAddMaterial();
        }
        else {
            table.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
            materialID = $(this).context.cells[0].innerText;
            $('#upt_materialID').val(materialID);
            bindMaterial($(this));
            bindImg(materialID);
            
            $('#ins_wzmc').val($(this).context.cells[1].innerText);
            $('#ins_wzlb').val($(this).context.cells[2].innerText);
            $('#ins_wzgg').val($(this).context.cells[3].innerText);
            $('#ins_jldw').val($(this).context.cells[4].innerText);
            $('#ins_kc').val($(this).context.cells[6].innerText);
            
            $('#ins_kcxx').val($(this).context.cells[9].innerText);
            $('#ins_kcsx').val($(this).context.cells[10].innerText);
            
            var flag = false;
            if($(this).context.cells[8].innerText=="启用"){
            	flag = true;
            }
            $('#isCheck').attr("checked",flag);
            
            mytr = $(this);
        }
        bindCheck();
    } );
	
	
	
});
