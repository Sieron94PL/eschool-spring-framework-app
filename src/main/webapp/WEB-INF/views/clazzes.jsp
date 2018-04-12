<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="<c:url value="/resources/style.css" />" rel="stylesheet">
</head>
<body>
	<h1>
		<spring:message code="menu.clazzes" />
	</h1>
	<c:if test="${clazz.id == 0}">
		<form:form method="post" action="addClazz.html" commandName="clazz">
			<table>
				<tr>
					<td><spring:message code="admin.clazz.name" /></td>
					<td><input type="text" name="name"></td>
					<td><input type="image" class="image"
						src="<c:url value="/resources/plus-icon-21.png" />" /></td>
				</tr>
			</table>

		</form:form>
	</c:if>
	<c:if test="${!empty clazzSubjects }">
		<div class="descClass">${clazz.name}</div>
		<br />
		<br />


		<table>
			<div class="classesList">
				<spring:message code="admin.clazz.subjects" />
			</div>
			<c:forEach items="${clazzSubjects}" var="subject">
				<tr>
					<td>${subject.name}</td>
					<td><a
						href="removeSubjectFromClazz/${subject.id}/${clazz.id }"><img
							src="<c:url value="/resources/minus-icon.png" />" height="25"
							width="25"></a></td>
				</tr>
			</c:forEach>
		</table>
		<br />
	</c:if>
	<c:if test="${!empty availableSubjects }">
		<table>
			<div class="classesList">
				<spring:message code="admin.available.subjects" />
			</div>
			<c:forEach items="${availableSubjects}" var="subject">
				<tr>
					<td>${subject.name}</td>
					<td><a href="addSubjectToClazz/${subject.id}/${clazz.id }"><img
							src="<c:url value="/resources/plus-icon-21.png" />" height="25"
							width="25"> </a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>


	<c:if test="${!empty clazzes}">
		<table class="eschoolTable">
			<tr>
				<th>Id</th>
				<th><spring:message code="admin.clazz.name" /></th>
				<th><spring:message code="admin.action" /></th>
			</tr>
			<c:forEach items="${clazzes}" var="clazz">
				<tr>
					<td>${clazz.id}</td>
					<td>${clazz.name}</td>
					<td><a href="clazzes.html?clazzId=${clazz.id}"><img
							src="<c:url value="/resources/tool-icon-20.png" />" height="25"
							width="25"></a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>