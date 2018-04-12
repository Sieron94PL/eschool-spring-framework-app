<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
</head>
<body>
	<h1>
		<spring:message code="menu.profile" />
	</h1>
	<table>
		<tr>
			<td><spring:message code="register.username" /></td>
			<td><strong>${user.username}</strong></td>
		</tr>
		<tr>
			<td><spring:message code="admin.firstname" /></td>
			<td><strong>${user.firstname}</strong></td>
		</tr>
		<tr>
			<td><spring:message code="admin.lastname" /></td>
			<td><strong>${user.lastname}</strong></td>
		</tr>
		<tr>
			<td><spring:message code="admin.email" /></td>
			<td><strong>${user.email}</strong></td>
		</tr>
		<tr>
		<tr>
			<td><spring:message code="admin.phone" /></td>
			<td><strong>${user.telephone}</strong></td>
		</tr>
		<c:choose>
			<c:when test="${!empty user.student}">
				<td><spring:message code="admin.clazz.name" /></td>
				<c:choose>
					<c:when test="${!empty user.student.clazz}">
						<td><strong>${user.student.clazz.name}</strong></td>
					</c:when>
					<c:otherwise>
						<td><strong><spring:message
									code="student.no.clazz.selected" /></strong></td>
					</c:otherwise>
				</c:choose>
			</c:when>
			<c:otherwise>
				<tr>
					<td><spring:message code="teacher.clazzes" /></td>
					<td><c:choose>
							<c:when test="${!empty user.teacher.clazzes }">
								<c:forEach items="${user.teacher.clazzes }" var="clazz">
									<strong>${clazz.name }</strong>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<strong><spring:message
										code="teacher.no.clazz.selected" /></strong>
							</c:otherwise>
						</c:choose></td>
				</tr>
				<tr>
					<td><spring:message code="admin.subject.name" /></td>
					<c:choose>
						<c:when test="${!empty user.teacher.subject }">
							<td><strong>${user.teacher.subject.name }</strong></td>
						</c:when>
						<c:otherwise>
							<td><strong><spring:message
										code="teacher.no.subject.allocated" /></strong></td>
						</c:otherwise>
					</c:choose>
				</tr>
			</c:otherwise>
		</c:choose>
	</table>
</body>
</html>