<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<link rel="stylesheet" type="text/css" href="${basePath}/css/all.css"/>
	<link rel="stylesheet" type="text/css" href="${basePath}/css/pop.css"/>
	<link rel="stylesheet" type="text/css" href="${basePath}/css/main.css"/>
	<script type="text/javascript" src="${basePath}/js/common/jquery-1.8.3.js"></script>
	<script type="text/javascript" src="${basePath}/js/common/common.js"></script>
	<script type="text/javascript" src="${basePath}/js/common/echarts.js"></script>
	<script type="text/javascript" src="${basePath}/js/report/orderCount.js"></script>
</head>
	<body style="background: #e1e9eb;">
		<input type="hidden" id="basePath" value="${basePath}"/>
		<div id="report" style="height:500px;border:1px solid #ccc;padding:10px;"></div>
	</body>
</html>