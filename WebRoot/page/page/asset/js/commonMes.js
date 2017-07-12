var editor;
var table;


var myUrl = "../CangKu/AssetServlet?param=searchStatus";
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
		
		if($('#ins_cname').val()==""){
			$("#i_display").text("状态名不能为空");
			$("#i_display").attr("class","alert alert-danger");
			return;
		}
		
		
	
		
		var param = {
			'i_cname': $('#ins_cname').val(),
			'i_tname':$('#add_ztlb').find("option:selected").text(),
			'i_tid':$('#add_ztlb').val()
			
		};
		
		console.info(param);
		
		$.post("../CangKu/AssetServlet?param=addStatus",param,function(data){
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
		
		
		 
		 var data = table.rows( { selected: true } ).data();
		 
		 	 console.info(data);
		 	 var tradeType = "";
		 	 console.info(data[0][1]);
			 $('#u_cbdm').val(data[0][1]);
			 $('#u_cbzwm').val(data[0][2]);
			 $('#u_chlx').val(data[0][3]);
			 $('#u_cgs').val(data[0][4]);
			 
				
			 $('#u_csc').val(data[0][5]);
			 $('#u_gd').val(data[0][6]);
			 $('#u_kd').val(data[0][7]);
			 $('#u_zz').val(data[0][8]);
			 $('#u_jz').val(data[0][9]);
				
			 $('#u_cr').val(data[0][10]);
			
		     $('#myEditModal').modal('show');
		 
	 });
	 
	 
	 $('#u_save').click(function(){
		 var param = {
					
					'u_cbzwm': $('#u_cbzwm').val(),
					'u_chlx': $('#u_chlx').val(),
					'u_cgs': $('#u_cgs').val(),
					'u_csc': $('#u_csc').val(),
					
					'u_gd': $('#u_gd').val(),
					'u_kd': $('#u_kd').val(),
					'u_zz': $('#u_zz').val(),
					'u_jz': $('#u_jz').val(),
					'u_cr': $('#u_cr').val(),
					
					'u_bz': $('#u_bz').val(),
					'u_cbdm': $('#u_cbdm').val()
		 };
		 
		 $.post("../Terminal/CommonControler?param=updateVessel",param,function(data){
				if(data=="OK"){
					$('#updateForm')[0].reset();
					$("#u_display").attr("class","alert alert-danger hidden");
					$('#myEditModal').modal('hide');
					table.ajax.url(myUrl).load();
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
		 var param = {"id":data[0][1]};
		 
		 var param = {
					'i_cname': data[0][3],
					'i_tid':data[0][1]
					
				};
		 
		 $.post("../CangKu/AssetServlet?param=delStatus",param,function(data){
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



$(document).ready(function() {
	
	initTable();
	initAdd();
	initEdit();
	initDel();
	initSearch();
	initAutoComplete();
});
