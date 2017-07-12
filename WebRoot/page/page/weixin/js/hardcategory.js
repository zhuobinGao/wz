var editor;
var table;


var myUrl = "../CangKu/AssetServlet?param=searchCategory";

function initTable(){
	
 
	
	
	
	//var table = $('.tableExample').DataTable();
	 table = $('.tableExample').DataTable({
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
        "ajax": myUrl,
      
        "deferRender": true,
      
        "pagingType": "full_numbers",
        "scrollX": true,
        columnDefs: [ {
            orderable: false,
            className: 'select-checkbox',
            targets:   0
        } ],
        select: {
            style:    'os',
            selector: 'td:first-child'
        },
        select: true,
        "lengthMenu": [[10, 20, 100, -1], [10, 20, 100, "All"]],
        "order": [[ 2, "desc" ]],
        dom: 'B<"clear">lfrtip',
        buttons: [  'csv', 'excel', 'pdf', 'print',{
            text: '全选',
            action: function () {
                table.rows().select();
            }
        },
        {
            text: '全不选',
            action: function () {
                table.rows().deselect();
            }
        }   ],
        
        
        initComplete: function () {
            this.api().columns().every( function () {
                var column = this;
                var select = $('<select><option value=""></option></select>')
                    .appendTo( $(column.footer()).empty() )
                    .on( 'change', function () {
                        var val = $.fn.dataTable.util.escapeRegex(
                            $(this).val()
                        );
 
                        column
                            .search( val ? '^'+val+'$' : '', true, false )
                            .draw();
                    } );
 
                column.data().unique().sort().each( function ( d, j ) {
                    select.append( '<option value="'+d+'">'+d+'</option>' )
                } );
            } );
        }
    });
	
	 
	
	    // DataTable
	    
	 
	    // Apply the search
	  
	
}

function initAdd(){
	
	$('#a_sure').click(function(){
		
		$('#add_fcid').empty();
		$('#upd_fcid').empty();
		$.post("../CangKu/AssetServlet?param=fristCategory",function(data){
			$('#add_fcid').append(data);
			$('#upd_fcid').append(data);
		});
		
		if($('#ins_cname').val()==""){
			$("#i_display").text("船舶代码不能为空");
			$("#i_display").attr("class","alert alert-danger");
			return;
		}
		
		var jb=$('input:radio[name="optionsRadios"]:checked').val();
		
	    var fcid = jb==1?'-1':$('#add_fcid').val();
		
		var param = {
			'cname':$('#ins_cname').val(),
			'jb': jb,
			'fcid':$('#add_fcid').val()
		};
		
		console.info(param);
		
		$.post("../CangKu/AssetServlet?param=addCategory",param,function(data){
			console.info(data);
			if(data=="OK"){
				$('#addForm')[0].reset();
				$("#i_display").attr("class","alert alert-danger hidden");
				$('#myAddModal').modal('hide');
				table.ajax.url( myUrl).load();
				
				return;
			}
			$("#i_display").text(data);
			$("#i_display").attr("class","alert alert-danger");

		});

		
	});
	
}

function initEdit(){
	 
	 
	 $('#openEdit').click(function(){
		 
		 
		 var count = table.rows( { selected: true } ).count();
		 if(count<1){
			 alert("请选择一条操作记录!");
			 return;
		 }
		 if(count>1){
			 alert("选择操作记录过多!");
			 return;
		 }
		
		 	$('#add_fcid').empty();
			$('#upd_fcid').empty();
			
			
			$.post("../CangKu/AssetServlet?param=fristCategory",function(data){
				$('#add_fcid').append(data);
				$('#upd_fcid').append(data);
				
				var data = table.rows( { selected: true } ).data();
				 
			 	 console.info(data);
			 	 var tradeType = "";
			 	 console.info(data[0][1]);
				
				 $('#upd_cname').val(data[0][2]);
				 
				 $('#upd_fcid').val(data[0][6]);
				 $('#s_selectID').val(data[0][1]);
				 console.info(data[0][3]);
				 if('是'==data[0][3]){
					 $('#upd_fcid').attr("disabled","disabled");
					 $('#optionsRadios3').removeAttr('checked');
					 $('#optionsRadios4').removeAttr('checked');
					 $('#optionsRadios3').attr('checked','checked');
					 console.info('yes');
				 }else{
					 $('#upd_fcid').removeAttr("disabled");
					 $('#optionsRadios3').removeAttr('checked');
					 $('#optionsRadios4').removeAttr('checked');
					 $('#optionsRadios4').attr('checked','checked');
					 console.info('no');
				 }
				 console.info("3:"+$('#optionsRadios3').attr('checked'))
				 console.info("4:"+$('#optionsRadios4').attr('checked'))
				 
				 //s_selectID
				 
				
			     $('#myEditModal').modal('show');
			});
		 
			
		 
	 });
	 
	 
	 $('#u_save').click(function(){
		 
		 
		 if($('#upd_cname').val()==""){
				$("#u_display").text("船舶代码不能为空");
				$("#u_display").attr("class","alert alert-danger");
				return;
			}
			
			var jb=$('input:radio[name="optionsRadios2"]:checked').val();
			
		    var fcid = jb==1?'-1':$('#upd_fcid').val();
			
			var param = {
				'cname':$('#upd_cname').val(),
				'jb': jb,
				'fcid':$('#upd_fcid').val(),
				'cid':$('#s_selectID').val()
		};
		 
		 
		 
		 $.post("../CangKu/AssetServlet?param=updateCategory",param,function(data){
				if(data=="OK"){
					$('#updateForm')[0].reset();
					$("#u_display").attr("class","alert alert-danger hidden");
					$('#myEditModal').modal('hide');
					table.ajax.url( myUrl).load();
					
					return;
				}
				$("#u_display").text(data);
				$("#u_display").attr("class","alert alert-danger");

			});
	 });
	 
	 
	 
}

function initDel(){
	
	$('#delEdit').click(function(){
		
		var count = table.rows( { selected: true } ).count();
		 if(count<1){
			 alert("请选择一条操作记录!");
			 return;
		 }
		 if(count>1){
			 alert("选择操作记录过多!");
			 return;
		 }
		
		
		 
		
		 $('#myDelModal').modal('show');
	});
	
	 
	 
	 
	 
	
	 $('#d_save').click(function(){
		 var data = table.rows( { selected: true } ).data();
		 var param = {"cid":data[0][1]};
		 $.post("../CangKu/AssetServlet?param=delCategory",param,function(data){
				if(data=="OK"){
					$('#myDelModal').modal('hide');
					table.ajax.url(myUrl).load();
					return;
				}
				alert(data);
			});
	 });
	 
}

function initSearch(){
	$('#s_search').click(function(){
		table.clear();
		table.ajax.url( myUrl).load();
	});
}

function initAutoComplete(){
	
	$('#i_cgs').autocomplete({
	      source: "../Terminal/CommonControler?param=a_searchGuest",
	      
		  minLength: 1,
	      
	      focus: function( event, ui ) {
	    	console.info(ui);
	        $( "#i_cgs" ).val( ui.item.value );
	        return false;
	      },
	      select: function( event, ui ) {
	    	 
	        $( "#i_cgs" ).val( ui.item.value );
	        
	        return false;
	      }
	}).autocomplete( "instance" )._renderItem = function( ul, item ) {
	    console.info(item+"======");
		return $( "<li>" )
	        .append( "<div>" + item.label + "</div>" )
	        .appendTo( ul );
	 };
	
	
	
	
}

function initSelect(){
	
	$('#add_fcid').empty();
	$('#upd_fcid').empty();
	$.post("../CangKu/AssetServlet?param=fristCategory",function(data){
		$('#add_fcid').append(data);
		$('#upd_fcid').append(data);
	});
	
	
	
	
	$('input:radio').click(function(){
		
		
		
		var jb=$('input:radio[name="optionsRadios"]:checked').val();
		
		var jb2=$('input:radio[name="optionsRadios2"]:checked').val();
		
		if(jb=='1'){
			$('#add_fcid').attr("disabled","disabled")
		}else{
			$('#add_fcid').removeAttr("disabled")
		}
		console.info("====="+jb2);
		if(jb2=='1'){
			$('#upd_fcid').attr("disabled","disabled")
		}else{
			$('#upd_fcid').removeAttr("disabled")
		}
		
		
	});
	
	
	
	
}

$(document).ready(function() {
	initTable();
	initAdd();
	initEdit();
	initDel();
	initSearch();
	initAutoComplete();
	initSelect();
});
