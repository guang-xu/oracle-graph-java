function showLoading(){
	console.log("xxxxxxxxxxxxxxxxxxxxxx");
	let loading = $(".btn-execute-loading");	
	let unload = $(".btn-execute-unload");
	$(loading).show();
	$(unload).hide();
}

function hiddenLoading(){
	let loading = $(".btn-execute-loading");	
	let unload = $(".btn-execute-unload");
	$(loading).hide();
	$(unload).show();
}