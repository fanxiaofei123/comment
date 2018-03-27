<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>系统错误页面</title>
	<script src="${basePath}/js/common/jquery-1.8.3.js" type="text/javascript"></script>
     <script src="${basePath}/js/common/common.js" type="text/javascript"></script>
     <script type="text/javascript">
     	$(function () {
     		common.showMessage('${pageCode.msg}');
     		var topWindow = window;
     		while(topWindow.parent != topWindow) {
     			topWindow = topWindow.parent;
     		}
     		topWindow.location.href = "${basePath}/login";
     	});
	</script>
</head>
<body>

</body>
</html>