var table ;

function initTable(){
	// Setup - add a text input to each footer cell
    $('.tableExample tfoot th').each( function () {
        var title = $(this).text();
        $(this).html( '<input type="text" placeholder="查询 '+title+'" />' );
    } );
	
    // DataTable
   
    table = $('.tableExample').DataTable({
       "ajax":  "../../../servlet/AjaxServlet?dataType=checkStore&houseID="+getStoreHouseID()+"&kc="+$('#kc').val(),
       "deferRender": true,
       "fixedColumns": true,
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
       "order": [[ 0, "asc" ]],
       dom: 'B<"clear">lfrtip',
       buttons: [
            'csv', 'excel', 'print'
       ]
		
   });
    
   // Apply the search
   table.columns().every( function () {
        var that = this;
        $( 'input', this.footer() ).on( 'keyup change', function () {
            if ( that.search() !== this.value ) {
                that.search( this.value ).draw();
            }
        } );
    } );
   
    
}

function onSearch(){
	$('#onSearch').click(function(){
		 table.clear().draw();
		 var myUrl = "../../../servlet/AjaxServlet?dataType=checkStore&houseID="+getStoreHouseID()+"&kc="+$('#kc').val();
		 table.ajax.url(myUrl).load();
	});
}




$(document).ready(function() {
	 
	initTable();
   
    
    onSearch();
    
    
    
    
 
    
} );