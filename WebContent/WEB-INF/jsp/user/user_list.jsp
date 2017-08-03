<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<c:if test="${!empty userVoList}">
			<c:forEach items="${userVoList}" var="userVo" varStatus="status">
				<tr class="content">
					<td>${userVo.code}</td>
					<td>${userVo.name}</td>
					<td>${userVo.agent}</td>
					<td>${userVo.depart}</td>
					<td><a href="userAction/toFormPage.html?userId=${userVo.id}">修改</a> <a onclick="removeUserVo(${userVo.id})">删除</a></td>
				</tr>
			</c:forEach>
		</c:if>
