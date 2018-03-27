<%@page import="java.util.Date"%>
<%@page import="org.fxf.dto.UserDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
    <%@ page import="org.fxf.constant.SessionKeyConst" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>大众点评后台管理</title>
	<link href="${basePath}/css/all.css" rel="stylesheet" type="text/css"/>
    <link href="${basePath}/css/pop.css" rel="stylesheet" type="text/css"/>
    <link href="${basePath}/css/index.css" rel="stylesheet" type="text/css"/>
    <script src="${basePath}/js/common/jquery-1.8.3.js" type="text/javascript"></script>
    <script src="${basePath}/js/common/common.js" type="text/javascript"></script>
    <script src="${basePath}/js/common/json.js" type="text/javascript"></script>
    <script src="${basePath}/js/system/index.js" type="text/javascript"></script>
</head>
<body>
	<!-- 蒙版DIV -->
		<div id="mengban" style="display:none"></div>
		<input type="hidden" id="basePath" value="${basePath}"/>
		<div class="wishlistBox" style="display: none;left:550px;top:200px;">
		    <div class="personRigTop persongBgimg" style="height:200px;width:480px;">
		        <div class="persongRightTit" style="width:480px;">&nbsp;&nbsp;修改密码</div>
		        <div class="persongRigCon">
		            <form name="redisAddOrEditForm" action="#" method="post">
		                <table class="x-form-table">
		                    <tbody>
		                    <tr class="line">
		                        <td class="left" width="10%"><em class="required">*</em><label>原始密码：</label></td>
		                        <td width="90%">
		                            <input class="normal-input" name="oldPassword" id="oldPassword" style="width: 240px;" type="password"/>
		                        </td>
		                    </tr>
		                    <tr class="line">
		                        <td class="left"><label><em class="required">*</em>新密码：</label></td>
		                        <td>
		                            <input class="normal-input" name="newPassword" id="newPassword" style="width: 240px;" type="password"/>
		                        </td>
		                    </tr>
		                    <tr class="line">
		                        <td class="left"><em class="required">*</em><label>确认新密码：</label></td>
		                        <td>
		                            <input class="normal-input" name="newPasswordAgain" id="newPasswordAgain" style="width: 240px;" type="password"/>
		                        </td>
		                    </tr>
		                    <tr>
		                        <td class="left"></td>
		                        <td class="submit">
		                            <input id="submitVal" class="tabSub" value="提交" onclick="checkForm('${basePath}/comment');" type="button"/>
		                            <input class="tabSub" value="关闭" onclick="closeDiv();" type="reset"/>
		                        </td>
		                    </tr>
		                    </tbody>
		                </table>
		            </form>
		        </div>
		    </div>
		</div>
		
		<form method="post" action="${basePath}/session" id="mainForm">
			<input type="hidden" name="_method" value="DELETE"/>
		    <div id="header">
		        <div class="iheader">
		            <div class="logo"><a href="#"><img src="" alt="" height="88px" width="99px"/></a> </div>
		            <div style="height: 44px;">
		                <div class="wuxianlogo"><img src="" alt="" height="28px" width="275px"/></div>
		                <div class="h_info">
		                    <span class="line"></span>
		                    <%
		                    UserDto username = (UserDto)session.getAttribute(SessionKeyConst.USER_INFO);
		                    %>
							欢迎您！ [ <%=username.getName() %> ]&nbsp; 当前时间：<fmt:formatDate value="<%=new Date() %>" pattern="yyyy-MM-dd HH:mm:ss"/>&nbsp;&nbsp;&nbsp;&nbsp;
		                    <a href="javascript:void(0);" onclick="openAddDiv();">[修改密码]</a>
		                    &nbsp;
		                    <a href="javascript:void(0);" onclick="eixt();">[退出系统]</a>
		                </div>
		            </div>
		            <ul class="nav" id="menuDiv">
		            	<li onclick='clickMenu(this," + value.id + ")'><a><span>系统管理</span></a></li>
		            	<li ><a href="${basePath }/content/index"><span>内容管理</span></a></li>
		            	<li onclick='clickMenu(this," + value.id + ")'><a><span>统计报表</span></a></li>
		            </ul>
		        </div>
		    </div>
		    <div id="container">
		        <table style="vertical-align:top" cellspacing="0" cellpadding="0" bgcolor="#e1e9eb" border="0">
		            <tbody>
		            <tr>
		                <td class="leftTd" style="vertical-align:top" width="150">
		                    <div class="left">
		                        <div class="ileft" id="subMenuDiv">
		                        	<h3 onclick="clickSubMenu(this,'/ad');"><a>广告管理</a></h3>
		                        	<h3 onclick="clickSubMenu(this,'/businesses');"><a>商户管理</a></h3>
		                        	<h3 onclick="clickSubMenu(this,'/orders')"><a>订单查询</a></h3>
		                        	<h3 onclick="clickSubMenu(this,'/comments')"><a>评论查询</a></h3>
		                        </div>
		                    </div>
		                </td>
		                <td width="7">
		                    <div class="pointer"></div>
		                </td>
		                <td style="vertical-align:top" height="600px" width="100%">
		                	<br/><iframe id="mainPage" src="" frameborder="0" height="580px" width="100%">
		                	
		                	</iframe>
		                	<br/>
		                 </td>
		            </tr>
		            </tbody>
		        </table>
		    </div>
		    <div id="footer">
		    </div>
		</form>
</body>
</html>