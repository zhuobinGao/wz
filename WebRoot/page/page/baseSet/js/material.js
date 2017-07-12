function bindMaterial(data){
	//data.context.cells[0].innerText;
	$('#upt_wzmc').val(data.context.cells[1].innerText);
	$('#upt_wzlb').val(data.context.cells[2].innerText);
	$('#upt_categoryID').val(data.context.cells[0].innerText);
	$('#upt_jldw2').val(data.context.cells[3].innerText);
	$('#upt_wzgg2').val(data.context.cells[4].innerText);
	
	$('#upt_wzbh2').val(data.context.cells[5].innerText);
}

function bindImg(materialID){
	$('#image').attr("src","../../Image?materialID="+materialID+"&random="+Math.random());
	console.info();
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



$(document).ready(function($) {

	$('.tableExample').DataTable({
		"ajax": "../../../servlet/AjaxServlet?dataType=checkMaterial&materialName="+$('#s_productName').val()+"&supplyDept="+$('#s_gys').val()+"&categoryName="+$('#s_category').val(),
	     "deferRender": true,
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
      dom: 'B<"clear">lfrtip',
      buttons: [
           'csv', 'excel', 'pdf', 'print'
      ]
		
   });
	
	var table = $('#exampleMaterial').DataTable();
	console.info(table);
	var materialID = "";
	
    
	
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
	
	
	$( "#ins_wzlb" ).keyup(function(){
		 var s_category = $("#ins_wzlb").val();
		 
		 Common.getCategoryName(s_category,function(data){		
			 
			 $('#ins_wzlb').autocomplete({
		         	source:data, 
		         	minLength:0,
			        delay:200,
			        select:function( event, ui ){
			        	//$('#pin_gydw').html(ui.item.value);	
			        	var param = {
								sqlID: "catory_03",
								str0:ui.item.value,
								strCount:1
							};
			        	
			        	PreStatement.getTabeData(param,function(data){
			        		$('#ins_categoryID').val(data);
			        	});
			        	
			        }
			 });
		 });
	 });
	
	
	$( "#upt_wzlb" ).keyup(function(){
		 var s_category = $("#upt_wzlb").val();
		 
		 Common.getCategoryName(s_category,function(data){		
			 
			 $('#upt_wzlb').autocomplete({
		         	source:data, 
		         	minLength:0,
			        delay:200,
			        select:function( event, ui ){
			        	//$('#pin_gydw').html(ui.item.value);	
			        	var param = {
								sqlID: "catory_03",
								str0:ui.item.value,
								strCount:1
							};
			        	
			        	PreStatement.getTabeData(param,function(data){
			        		$('#upt_categoryID').val(data);
			        	});
			        	
			        }
			 });
		 });
	 });
	
	
	
	
	//getCategoryName

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
	
	
	
	
$('#mCheck').click(function(){
		
		var param = {
				materialName:$('#s_productName').val(),
				supplyDept:$('#s_gys').val(),
				categoryName:$('#s_category').val()
				}
		
		console.info(param);
		 $('#mCheck').text("查询中...");
		
		 var myUrl = "../../../servlet/AjaxServlet?dataType=checkMaterial&materialName="+$('#s_productName').val()+"&supplyDept="+$('#s_gys').val()+"&categoryName="+$('#s_category').val();
		
		 table.ajax.url(myUrl);
		 table.ajax.reload( function ( json ) {
			 $('#mCheck').text("查 询");
			$('#myCheck').attr("disabled",false)
		  } );
		 
		 
		
	});
	


	$('#ins_save').click(function(){
		
		var param = {
				materialName:$('#ins_wzmc').val(),
				materialType:$('#ins_categoryID').val(),	
				materialStyle:$('#ins_wzgg').val(),
				size:$('#ins_jldw').val(),
				usage:$('#ins_wzbz').val(),
				materialBak:$('#ins_wzbh').val()
		}
		
		
		Baseset.saveMaterial(param,function(data){
			if(data=="OK"){
				alert("成功增加新物资");
				closeAddMaterial();
				return;
			}
			alert("错误:"+data);
		});
	});

//materialID
	
	$('#upt_save').click(function(){
		if(materialID==""){alert("请选择一条数据操作");return;}
		
		var param = {
				materialName:$('#upt_wzmc').val(),
				materialType:$('#upt_categoryID').val(),	
				materialStyle:$('#upt_wzgg2').val(),
				size:$('#upt_jldw2').val(),
				usage:$('#upt_wzbz2').val(),
				materialBak:$('#upt_wzbh2').val(),
				materialID:$('#upt_materialID').val()
		}
		console.info(param);
		
		Baseset.updateMaterial(param,function(data){
			if(data=="OK"){
				alert("成功更新物资信息,请重新查询!");
				$('#eidtModal').modal('hide');
				return;
			}
			alert("错误:"+data);
		});
		
	});
	
	$('#delMaterial').click(function(){
		if(materialID==""){alert("请选择一条数据操作");return;}
		
		Baseset.delMaterial(materialID,function(data){
			if(data=="OK"){
				alert("成功删除物资信息,请重新查询!");
				$('#delModal').modal('hide');
				return;
			}
			alert("错误:"+data);
		})
	});
	
	$('#uploadImg').click(function(){
		if(materialID==""){alert("请选择一条数据操作");return;}
		
		
		var file=dwr.util.getValue("file");
		console.info(file);
		console.info(file.value)
		
		if(file.value==""){
			alert("请选择上传文件")
			return;
		}
		
		if(file.value)
		
		
		ImageUtil.upload(file,materialID,function(data){
			if(data=="OK"){
				alert("成功保存图片");
				bindImg(materialID);
				return;
			}
			alert(data);
		});
//		FileService.upload(file,file.value,function(result){
//			alert(result);
//		});
	});
	
	$('#exampleMaterial tbody').on( 'click', 'tr', function () {
        if ( $(this).hasClass('selected') ) {
            $(this).removeClass('selected');
            materialID = "";
        }
        else {
            table.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
            materialID = $(this).context.cells[0].innerText;
            $('#upt_materialID').val(materialID);
            bindMaterial($(this));
            bindImg(materialID);
        }
    } );
	
	
	
});
