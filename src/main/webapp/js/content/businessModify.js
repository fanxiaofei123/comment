$(function() {
	common.showMessage($("#message").val());
	$("#mainForm").validate({
		rules : {
			"title" : "required",
			"subtitle" : "required",
			"price" : "digits",
			"distance" : "digits"
		},
		messages : {
			"title" : "请输入标题！",
			"subtitle" : "请输入副标题",
		}
	});
});

function modify() {
	$("#mainForm").submit();
}

