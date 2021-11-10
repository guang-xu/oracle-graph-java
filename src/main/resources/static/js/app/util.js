function html_decode(str) {
	var s = "";
	if (str.length == 0) return "";
	s = str.replace(/&lt;/g, "<");
	s = s.replace(/&quot;/g, "\"");
	s = s.replace(/&gt;/g, ">");
	s = s.replace(/ /g, " ");
	s = s.replace(/'/g, "\'");
	s = s.replace(/"/g, "\"");
	s = s.replace(/<br>/g, "\n");
	return s;
}  

function doPostRequestWithForm(formId,URL){
	let data = {};
	$("#"+formId).serializeArray().map(function (val, key) {
				data[val.name] = val.value;
			});
	console.log(data);
	showLoading();
	$.post({
		url:URL,
		contentType:'application/json;charset=utf-8',
		data: JSON.stringify(data),
		dataType:"json",
		//contentType:"form-data",
		success:function(data) {
			console.log(data);
			hiddenLoading();
			//renderGrid(data);
		},
		error:function(){
			console.log("error");
			hiddenLoading();
		}
	});
}