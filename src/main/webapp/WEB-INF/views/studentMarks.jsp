<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
</head>
<body>
	<h1>
		<spring:message code="student.mark.list" />
	</h1>
	<table>
		<tr>
			<td><spring:message code="admin.firstname" /></td>
			<td><strong>${student.user.firstname }</strong></td>
		</tr>
		<tr>
			<td><spring:message code="admin.lastname" /></td>
			<td><strong>${student.user.lastname }</strong></td>
		</tr>
		<tr>
			<td><spring:message code="admin.clazz.name" /></td>
			<td><c:choose>
					<c:when test="${empty student.clazz}">
						<strong><spring:message code="student.no.clazz.selected" /></strong>
					</c:when>
					<c:otherwise>
						<strong>${student.clazz.name }</strong>
					</c:otherwise>
				</c:choose></td>
		</tr>
	</table>
	<c:if test="${!empty marks }">
		<table class="eschoolTable">
			<tr>
				<th><spring:message code="admin.subject.name" /></th>
				<th><spring:message code="teacher.name" /></th>
				<th><spring:message code="mark.marks" /></th>
			</tr>
			<c:forEach items="${marks}" var="entry">
				<tr>
					<td>${entry.key.subject.name }</td>
					<td><c:choose>
							<c:when test="${empty entry.key.user}">
								<spring:message code="student.no.teacher.allocated" />
							</c:when>
							<c:otherwise>
  	${entry.key.user.firstname} ${entry.key.user.lastname}  
  	</c:otherwise>
						</c:choose></td>
					<td><c:forEach items="${entry.value}" var="mark">
		   	${mark.mark }   
		</c:forEach></td>
				</tr>
			</c:forEach>
		</table>
		<table>
			<tr>
				<td><a href="../student/downloadCertificate"><button class="button">
							<spring:message code="student.download.certificate" />
						</button></a></td>
			</tr>
		</table>
	</c:if>


</body>
</html>