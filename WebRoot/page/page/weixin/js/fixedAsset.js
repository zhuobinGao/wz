var editor;
var table;
var table2;
var logtable1,logtable2;
var myUrl = "../CangKu/AssetServlet?param=searchAsset";

function initTable(){
	//var table = $('.tableExample').DataTable();
	 table = $('#example').DataTable({
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
	 table2 = $('#example2').DataTable({
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
//        "ajax": myUrl,
      
        "deferRender": true,
      
        "pagingType": "full_numbers",
        "scrollX": true,
        columnDefs: [ {
            orderable: false,
            className: 'details-control',
            targets:   0
        } ],
        select: {
            style:    'os',
            selector: 'td:first-child'
        },
        select: true,
        "lengthMenu": [[10, 20, 100, -1], [10, 20, 100, "All"]],
        "order": [[ 1, "desc" ]],
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
	 
	    // Apply the search
	  
	
}

function initTable2(){
	
	
	logtable1 = $('#tablelog1').DataTable({
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
//       "ajax": myUrl,
     
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
       "order": [[ 1, "desc" ]],
       dom: 'B<"clear">lfrtip',
       buttons: [  'csv', 'excel', 'pdf', 'print'],
       
       
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

	
   
	
	logtable2 = $('#tablelog2').DataTable({
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
//      "ajax": myUrl,
    
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
      "order": [[ 1, "desc" ]],
      dom: 'B<"clear">lfrtip',
      buttons: [  'csv', 'excel', 'pdf', 'print'],
      
      
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
	
}

function initViewLog(){
	$('#uptlog').click(function(){
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
		 
		 var id = data[0][1];
		 
		 var url1 = "../CangKu/AssetServlet?param=userLog&id="+id;
		 var url2 = "../CangKu/AssetServlet?param=columnLog&id="+id;
		 
		 logtable1.ajax.url( url1 ).load();
		 logtable2.ajax.url( url2 ).load();
		 
		 $('#myLog').modal('show');
		 
		
	});
}



function tableSelection(){
	table.on( 'select', function ( e, dt, type, indexes ) {
       
        var count = table.rows( { selected: true } ).count();
        if(count!=1){return;}
       
        var rowdata = table.rows( { selected: true } ).data();
       
        var table2url = "../CangKu/AssetServlet?param=searchCheck&id="+rowdata[0][1];
        
        table2.ajax.url( table2url).load();
        
        
        
        $('#s_system').val(rowdata[0][25]);
        $('#s_cpu').val(rowdata[0][26]);
        $('#s_disk').val(rowdata[0][27]);
        $('#s_display').val(rowdata[0][28]);
        $('#s_mac').val(rowdata[0][29]);
        
        $('#s_mes1').val(rowdata[0][30]);
        $('#s_mes2').val(rowdata[0][31]);
        $('#s_mes3').val(rowdata[0][32]);
        $('#sel_assetid').val(rowdata[0][1]);
        
        
        
        
        
    } ).on( 'deselect', function ( e, dt, type, indexes ) {
        var rowData = table.rows( indexes ).data().toArray();
    } );
	
	function format ( d ) {
	    return 'Full name: '+d+' '+d+'<br>'+
	        'Salary: '+d+'<br>'+
	        'The child row can contain any data you wish, including links, images, inner tables etc.';
	}
	
	 var detailRows = [];
	
	 $('#example2 tbody').on( 'click', 'tr td.details-control', function () {
	        var tr = $(this).closest('tr');
	        var row = table2.row( tr );
	        var idx = $.inArray( tr.attr('id'), detailRows );
	 
	        if ( row.child.isShown() ) {
	            tr.removeClass( 'details' );
	            row.child.hide();
	 
	            // Remove from the 'open' array
	            detailRows.splice( idx, 1 );
	        }
	        else {
	            tr.addClass( 'details' );
	            row.child( format( row.data() ) ).show();
	 
	            var checkparam = {"id":row.data()[7]};
	            
	            var checkUrl = "../CangKu/AssetServlet?param=searchCheckDetail";
	            
	            $.post(checkUrl,checkparam,function(data){
	        		
	            	var mydata ="<b>故障描述:</b>"+row.data()[6]+"<br>";
	            	 row.child( mydata+data ).show();
	            	
	        		
	        	});
	            
	            
	            // Add to the 'open' array
	            if ( idx === -1 ) {
	                detailRows.push( tr.attr('id') );
	            }
	        }
	    } );
	
	 table2.on( 'draw', function () {
	        $.each( detailRows, function ( i, id ) {
	            $('#'+id+' td.details-control').trigger( 'click' );
	        } );
	    } );
	
	
	
	
	
	
	
	
	
	
	
}

function initAdd(){
	
	$('#a_sure').click(function(){
		
		if($('#i_cbdm').val()==""){
			$("#i_display").text("资产名称不能为空!");
			$("#i_display").attr("class","alert alert-danger");
			return;
		}
		
		
		
	
		
		var param = {
			'assetname': $('#i_zcmc').val(),
			'fcname': $('#i_yjlb').find("option:selected").text(),
			'scname': $('#i_ejlb').find("option:selected").text(),
			'fixno': $('#i_gdzcbm').val(),
			'snno':  $('#i_snm').val(),
			'factory': $('#i_chj').val(),
			'fixmodel': $('#i_xh').val(),
			'seller': $('#i_xshf').val(),
			'buydate': $('#i_gzrq').val(),
			'keeplife': $('#i_bxnx').val(),
			'extendlife': $('#i_ybnx').val(),
			'repairlxr': $('#i_bxlxr').val(),
			'repairphone': $('#i_bxdh').val(),
			
			'scrapyear': $('#i_bfnx').val(),
			'assetstatus': $('#i_syzt').val(),
			'userman': $('#i_syr').val(),
			'useraddr': $('#i_sydd').val(),
			'userdept': $('#i_sybm').val()
			
		};
		
	
		$.post("../CangKu/AssetServlet?param=addAsset",param,function(data){
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

function updateCheck(){
	var mytabledata;
	$('#followRecord').click(function(){
		var count = table2.rows( { selected: true } ).count();
		 if(count<1){
			 alert("请选择一条维修信息!");
			 return;
		 }
		 if(count>1){
			 alert("选择操作记录过多!");
			 return;
		 }
		 mytabledata = table2.rows( { selected: true } ).data();
		 
		 var isrepair = mytabledata[0][4]=="是"?"1":"0";
		 
		 $('#ui_gzlx').val(mytabledata[0][2]);
		 $('#ui_jxry').val(mytabledata[0][3]);
		 $('#ui_gzrq').val(mytabledata[0][1]);
		 
		 
		 $('#ui_isxf').val(isrepair);
		 $('#ui_xfrq').val(mytabledata[0][5]);
		 $('#ui_gzms').val(mytabledata[0][6]);
		 $('#sel_check').val(mytabledata[0][7]);
		
		 $('#myAddModal5').modal('show'); 
		 
	});
	
	
	$('#ui_save').click(function(){
		if($('#ui_gzlx').val()=="-1"){
			$("#ui_display").text("故障类型不能为空!");
			$("#ui_display").attr("class","alert alert-danger");
			return;
		}
		
		if($('#ui_jxry').val()==""){
			$("#ui_display").text("检修人员不能为空!");
			$("#ui_display").attr("class","alert alert-danger");
			return;
		}
		
		if($('#ui_gzrq').val()==""){
			$("#ui_display").text("故障日期不能为空!");
			$("#ui_display").attr("class","alert alert-danger");
			return;
		}
		
		
		
		mytabledata = table.rows( { selected: true } ).data();
		var repairtype=1;
		if($('#ui_sxrq').val()!="" || $('#ui_shrq').val()!=""){
			repairtype = 0;
		}
		
		var param =  {
				
				"checkid": $('#sel_check').val(),
				"faultname": $('#ui_gzlx').val(),
				"checkman": $('#ui_jxry').val(),
				"faultdate": $('#ui_gzrq').val(),
				"isrepair": $('#ui_isxf').val(),
				"repairdate": $('#ui_xfrq').val(),
				"faultdesc": $('#ui_gzms').val(),
				
				"repairtype": repairtype,
				"repairman": $('#ui_wxry').val(),
				"repairresult":$('#ui_wxjg').val(),
				"repairmethod":$('#ui_wxff').val(),
				"sendman":$('#ui_sxry').val(),
				"senddate":$('#ui_sxrq').val(),
				"receiveman":$('#ui_shry').val(),
				"receivedate":$('#ui_shrq').val()
		};
		$.post("../CangKu/AssetServlet?param=uptRepairLog",param,function(data){
			if(data=="OK"){
				$('#uptForm2')[0].reset();
				$('#uptForm1')[0].reset();
				$('#uptForm0')[0].reset();
				$("#ui_display").attr("class","alert alert-danger hidden");
				$('#myAddModal5').modal('hide');
				var table2url = "../CangKu/AssetServlet?param=searchCheck&id="+mytabledata[0][1];
				
				table2.ajax.url( table2url).load();
				
				return;
			}
			$("#ui_display").text(data);
			$("#ui_display").attr("class","alert alert-danger");

		});
		
		
		
	});
	
}


function addCheck(){

	var mytabledata;
	
	$('#repareLog').click(function(){
		 var count = table.rows( { selected: true } ).count();
		 if(count<1){
			 alert("请选择一条操作记录!");
			 return;
		 }
		 if(count>1){
			 alert("选择操作记录过多!");
			 return;
		 }
		 
		 mytabledata = table.rows( { selected: true } ).data();
		 
		 if(mytabledata[0][17]=="报废"){
			 alert("该资产已报废！");
			 return;
		 }
		 
		 $('#wi_title').text("[编号:"+mytabledata[0][1]+"  名称:"+mytabledata[0][2]+"]");
		 
		 
		 $('#myAddModal2').modal('show');
		 
	});
	
	
	$('#wi_save').click(function(){
		
		if($('#wi_gzlx').val()=="-1"){
			$("#wi_display").text("故障类型不能为空!");
			$("#wi_display").attr("class","alert alert-danger");
			return;
		}
		
		if($('#wi_jxry').val()==""){
			$("#wi_display").text("检修人员不能为空!");
			$("#wi_display").attr("class","alert alert-danger");
			return;
		}
		
		if($('#wi_gzrq').val()==""){
			$("#wi_display").text("故障日期不能为空!");
			$("#wi_display").attr("class","alert alert-danger");
			return;
		}
		
		
		
		mytabledata = table.rows( { selected: true } ).data();
		
		
		var repairtype=1;
		if($('#wi_sxrq').val()!="" || $('#wi_shrq').val()!=""){
			repairtype = 0;
		}
		
		var param =  {
				"assetid": mytabledata[0][1],
				"checkid": "",
				"faultname": $('#wi_gzlx').val(),
				"checkman": $('#wi_jxry').val(),
				"faultdate": $('#wi_gzrq').val(),
				"isrepair": $('#wi_isxf').val(),
				"repairdate": $('#wi_xfrq').val(),
				"faultdesc": $('#wi_gzms').val(),
				
				"repairtype": repairtype,
				"repairman": $('#wi_wxry').val(),
				"repairresult":$('#wi_wxjg').val(),
				"repairmethod":$('#wi_wxff').val(),
				"sendman":$('#wi_sxry').val(),
				"senddate":$('#wi_sxrq').val(),
				"receiveman":$('#wi_shry').val(),
				"receivedate":$('#wi_shrq').val()
		};
		
		$.post("../CangKu/AssetServlet?param=addRepairLog",param,function(data){
			if(data=="OK"){
				$('#insForm2')[0].reset();
				$('#insForm1')[0].reset();
				$('#insForm0')[0].reset();
				$("#wi_display").attr("class","alert alert-danger hidden");
				$('#myAddModal2').modal('hide');
				var table2url = "../CangKu/AssetServlet?param=searchCheck&id="+mytabledata[0][1];
				
				table2.ajax.url( table2url).load();
				
				return;
			}
			$("#wi_display").text(data);
			$("#wi_display").attr("class","alert alert-danger");

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
		 	 var tradeType = "";
			 $('#sel_dnbh').val(data[0][1]);
			
			 $('#u_zcmc').val(data[0][2]);
			
			 $("#u_ejlb option[text='"+data[0][4]+"']").attr("selected", true); 
			 
			 var myerlb = data[0][4];
			 var myyjlb = data[0][3];
			 $.post("../CangKu/AssetServlet?param=fristCategory2",function(data){
					$('#u_yjlb').append(data);
					$('#u_yjlb').val(myyjlb);
				     var param = {"fcid":$('#u_yjlb').val()}
						$.post("../CangKu/AssetServlet?param=secondCategory2",param,function(data){
							$('#u_ejlb').empty();
							$('#u_ejlb').append(data);
							$("#u_ejlb").val(myerlb);
					 });
			 });
			 

			 $('#u_gdzcbm').val(data[0][5]);
			 $('#u_snm').val(data[0][6]);
			 $('#u_chj').val(data[0][7]);
			 $('#u_xh').val(data[0][8]);
			 $('#u_xshf').val(data[0][9]);
			 $('#u_gzrq').val(data[0][10]);
			 $('#u_bxnx').val(data[0][11]);
			 $('#u_ybnx').val(data[0][12]);
			 $('#u_bxlxr').val(data[0][13]);
			 $('#u_bxdh').val(data[0][14]);
			
			 
			 $('#u_syzt').val(data[0][17]);
			 $('#u_syr').val(data[0][18]);
			 $('#u_sydd').val(data[0][19]);
			 $('#u_sybm').val(data[0][20]);
						
			 $('#ul01').removeAttr("class");
			 $('#ul03').removeAttr("class");
			 $('#ul01').attr("class","active");
			 
			 
			 $('#myEditModal a:first').tab('show');
		     $('#myEditModal').modal('show');
		 
	 });
	 
	 
	 $('#u_save').click(function(){
		 var param = {
					'assetname': $('#u_zcmc').val(),
					'fcname': $('#u_yjlb').find("option:selected").text(),
					'scname': $('#u_ejlb').find("option:selected").text(),
					'fixno': $('#u_gdzcbm').val(),
					'snno':  $('#u_snm').val(),
					'factory': $('#u_chj').val(),
					'fixmodel': $('#u_xh').val(),
					'seller': $('#u_xshf').val(),
					'buydate': $('#u_gzrq').val(),
					'keeplife': $('#u_bxnx').val(),
					'extendlife': $('#u_ybnx').val(),
					'repairlxr': $('#u_bxlxr').val(),
					'repairphone': $('#u_bxdh').val(),
					
					'scrapyear': $('#u_bfnx').val(),
					'assetstatus': $('#u_syzt').val(),
					'userman': $('#u_syr').val(),
					'useraddr': $('#u_sydd').val(),
					'userdept': $('#u_sybm').val(),
					'assetid': $('#sel_dnbh').val()
				};
		 
		 $.post("../CangKu/AssetServlet?param=updateAsset",param,function(data){
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
		 $.post("../CangKu/AssetServlet?param=delAsset",param,function(data){
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

function initEditUser(){
	$('#edituser').click(function(){
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
		 
		 	 var tradeType = "";
			 $('#sel_dnbh').val(data[0][1]);
			
			 $('#u_zcmc').val(data[0][2]);
			
			 
			
			 
			 
			 
			 $("#u_ejlb option[text='"+data[0][4]+"']").attr("selected", true); 
			 
			
		     
			 var myerlb = data[0][4];
			 
		     $('#u_yjlb').val(data[0][3]);
		     var param = {"fcid":$('#u_yjlb').val()}
			 $.post("../CangKu/AssetServlet?param=secondCategory2",param,function(data){
					$('#u_ejlb').empty();
					$('#u_ejlb').append(data);
					$("#u_ejlb").val(myerlb);
			 });
		     
		
			 $('#u_gdzcbm').val(data[0][5]);
			 $('#u_snm').val(data[0][6]);
			 $('#u_chj').val(data[0][7]);
			 $('#u_xh').val(data[0][8]);
			 $('#u_xshf').val(data[0][9]);
			 $('#u_gzrq').val(data[0][10]);
			 $('#u_bxnx').val(data[0][11]);
			 $('#u_ybnx').val(data[0][12]);
			 $('#u_bxlxr').val(data[0][13]);
			 $('#u_bxdh').val(data[0][14]);
			
			 
			 $('#u_syzt').val(data[0][17]);
			 $('#u_syr').val(data[0][18]);
			 $('#u_sydd').val(data[0][19]);
			 $('#u_sybm').val(data[0][20]);
						
			
			 
			 $('#myEditModal a:last').tab('show');
			
		     $('#myEditModal').modal('show');
		 
	 });
}


function initAutoComplete(){
	
	$('#i_cgs').autocomplete({
	      source: "../Terminal/CommonControler?param=a_searchGuest",
	      
		  minLength: 1,
	      
	      focus: function( event, ui ) {
	        $( "#i_cgs" ).val( ui.item.value );
	        return false;
	      },
	      select: function( event, ui ) {
	    	 
	        $( "#i_cgs" ).val( ui.item.value );
	        
	        return false;
	      }
	}).autocomplete( "instance" )._renderItem = function( ul, item ) {
		return $( "<li>" )
	        .append( "<div>" + item.label + "</div>" )
	        .appendTo( ul );
	 };
	
	
	
	
}

function initDate(){
	$(".form_date").datetimepicker({
		language:  'fr',
        format: "yyyy-mm-dd",
        autoclose: true,
        minView:2,
        todayBtn: true,
        minuteStep: 1,
        pickerPosition: "bottom-left"
    });
	
}

function initSelect(){
	
	$.post("../CangKu/AssetServlet?param=fristCategory",function(data){
		$('#i_yjlb').append(data);
	});
	
	$.post("../CangKu/AssetServlet?param=fristCategory2",function(data){
		$('#u_yjlb').append(data);
	});
	
	//i_syzt
	
	var myparam = {"tid":"4000"};
	
	$.post("../CangKu/AssetServlet?param=stautsOption",myparam,function(data){
		$('#i_syzt').append(data);
		$('#u_syzt').append(data);
	});
	
	myparam = {"tid":"1000"};
	$.post("../CangKu/AssetServlet?param=stautsOption",myparam,function(data){
		$('#wi_gzlx').append(data);
		$('#ui_gzlx').append(data);
	});
	myparam = {"tid":"3000"};
	$.post("../CangKu/AssetServlet?param=stautsOption",myparam,function(data){
		$('#wi_wxjg').append(data);
		$('#ui_wxjg').append(data);
	});
	
	
	
	
	
	$('#i_yjlb').click(function(){
		var param = {"fcid":$('#i_yjlb').val()}
		$.post("../CangKu/AssetServlet?param=secondCategory",param,function(data){
			$('#i_ejlb').empty();
			$('#i_ejlb').append(data);
			
		});
	});
	
	$('#u_yjlb').click(function(){
		var param = {"fcid":$('#u_yjlb').find("option:selected").text()}
		$.post("../CangKu/AssetServlet?param=secondCategory2",param,function(data){
			$('#u_ejlb').empty();
			$('#u_ejlb').append(data);
			
		});
	});
	
	
	
	
	
	
	
}

function initComputeMes(){
	
	$('#s_canEdit').click(function(){
		
		$('.s_compute').removeAttr("disabled");
		
		
		
	});
	
	$('#s_isSave').click(function(){
		
		
		var id = $('#sel_assetid').val();
		if(id==null)return;
		
		 var param = {
				 "ASSETID":$('#sel_assetid').val()  ,
				 "OSSYSTEM":$('#s_system').val()  ,
				 "CPU":$('#s_cpu').val()  ,
				 "DISK":$('#s_disk').val()  ,
				 "DISPLAY":$('#s_display').val()  ,
				 
				 "MAC":$('#s_mac').val()  ,
				 "MES1":$('#s_mes1').val()  ,
				 "MES2":$('#s_mes2').val()  ,
				 "MES3":$('#s_mes3').val()  , 	 
		 }
		 
		 
		 
		 
		 $.post("../CangKu/AssetServlet?param=uptComputer",param,function(data){
			 	if(data!='OK'){
			 		alert(data);
			 		return;
			 	}
			 	$('.s_compute').attr("disabled","disabled");
		 });
		
		
		
		
	});
	
	
	
	
}



$(document).ready(function() {
	initSelect();
	initDate();
	initTable();
	initTable2();
	initAdd();
	initEdit();
	initDel();
	initSearch();
	initAutoComplete();
	initEditUser();
	addCheck();
	updateCheck();
	tableSelection();
	initViewLog();
	initComputeMes();
});
