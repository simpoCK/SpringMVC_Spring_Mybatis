<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery-1.8.2.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/user/index.js"></script>
<title>用户信息首页</title>
</head>

<body>

	<table class="table" border="1">
		<tr class="head">
			<th>编号</th>
			<th>姓名</th>
			<th>年龄</th>
			<th>部门</th>
			<th>操作</th>
		</tr>
	</table>
	<a href="userAction/toFormPage.html"><button>新增</button></a>
</body>
</html>