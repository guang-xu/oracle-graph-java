$("#graphtable tr:gt(0)").click(function(){
	$("#id").val($(this).find("td").eq(0).html());
	$("#graphName").val($(this).find("td").eq(1).html());
	$("#queryType").val($(this).find("td").eq(2).html());
	$("#queryName").val($(this).find("td").eq(3).html());
	$("#queryAlias").val($(this).find("td").eq(4).html());
	$("#queryContent").val($(this).find("td").eq(5).html());
	$("#createDate").val($(this).find("td").eq(6).html());
	$("#updateDate").val($(this).find("td").eq(7).html());
});