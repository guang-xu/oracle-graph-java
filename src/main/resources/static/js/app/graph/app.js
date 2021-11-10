const BASE_URI="http://localhost:8080";
const PATH="/api/pgql/graph";

function doGetRequest(){
	renderGrid(null,false);
	let data = {};
	$("#graphform").serializeArray().map(function (val, key) {
				data[val.name] = val.value;
			});
	showLoading();
	 
	$.get({
		url:BASE_URI+PATH,
		//contentType:'application/json;charset=utf-8',
		data: data,
		dataType:"json",
		contentType:"form-data",
		success:function(data) {
			hiddenLoading();
			renderGrid(data,true);
		},
		error:function(){
			hiddenLoading()
			console.log("error");
		}
	});
}

function renderGrid(data,flag){
	if(flag == false) {
		$("#graphtable thead tr").empty();
		$("#graphtable tbody").empty();
		return;
	} else {
		let headers = data["headers"];
		let result = data["result"];
		let exetime = data["exeTimes"];
		let graphName = data["graphName"];
		
		let theader = $("#graphtable thead tr");
		let tbody = $("#graphtable  tbody");
		let pexetime = $("#exetimes");
		
		$(pexetime).text("Execution Times: "+exetime);
		
		headers.forEach(function(val){
			$("<th></th>").text(val).appendTo(theader);
		});
			
		
		result.forEach(function(row){
			let tr = $("<tr></tr>");
			row.forEach(function(item){
				$("<td></td>").text(item.colValue).appendTo(tr);		
			});
			$(tbody).append(tr);
		});
		console.log("headers:\n"+headers);
		//$("#datagrid")
	}
}