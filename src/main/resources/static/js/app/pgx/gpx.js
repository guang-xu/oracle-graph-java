let __graphQueryList = "";
function init(graphQueryList){
	$("#graphQuery").empty();
	let graphname = $("#graphName").val();
	graphQueryList.forEach(function(item){
		if(item["graphName"] == graphname){
			$("<option></option>").val(item["queryContent"]).text(item["queryName"]).appendTo($("#graphQuery"));
		}
	});
	$("#query").val($("#graphQuery").val());
}

function graphNameChanged(){
	init(__graphQueryList);
}