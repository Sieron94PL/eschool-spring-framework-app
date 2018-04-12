<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<link href="<c:url value="/resources/style.css" />" rel="stylesheet">
<body>
	<h1>
		<spring:message code="menu.subjects" />
	</h1>
	<form:form method="post" action="addSubject.html" commandName="subject">
		<table>
			<tr>
				<td><spring:message code="admin.subject.name" /></td>
				<td><input type="text" name="name"></td>
				<td><input type="image" class="image"
					src="<c:url value="/resources/plus-icon-21.png" />" /></td>
			</tr>
		</table>
	</form:form>
	<c:if test="${!empty subjects}">
		<table class="eschoolTable">
			<tr>
				<th>Id</th>
				<th><spring:message code="admin.subject.name" /></th>
			</tr>
			<c:forEach items="${subjects}" var="subject">
				<tr>
					<td>${subject.id}</td>
					<td>${subject.name}</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>