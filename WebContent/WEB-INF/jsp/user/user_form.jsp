<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户信息表单页</title>
</head>
<body>
		<form action="saveUserVo.html" method="post" >
			<input type="hidden" name="id" value="${userVo.id }"/>
			<label>编号:</label><input type="text" name="code" value="${userVo.code }"/>
				<br/>
			<label>姓名:</label><input type="text" name="name" value="${userVo.name }"/>
				<br/>
			<label>年龄:</label><input type="text" name="agent" value="${userVo.agent }"/>
				<br/>
			<label>部门:</label><input type="text" name="depart" value="${userVo.depart }"/>
				<br/>
			<input type="submit" name="submit" value="提交"/>
		</form>
</body>
</html>