<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
<link href="<c:url value="/resources/style.css" />" rel="stylesheet">
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
</head>
<body>
	<h1>
		<spring:message code="menu.users" />
	</h1>
	<form:form name="myForm" method="post" action="editUser.html"
		commandName="user">
		<table>
			<tr>
				<td><input type=hidden name="username" value="${user.username}"></td>
			</tr>
			<tr>
				<td><input type=hidden name="password" value="${user.password}"></td>
			</tr>
			<tr>
				<td><spring:message code="admin.firstname" /></td>
				<td><input type="text" name="firstname"
					value="${user.firstname}"></td>
				<td style="color: red; font-style: italic;"><strong><form:errors
							path="lastname" /></strong></td>
			</tr>
			<tr>
				<td><spring:message code="admin.lastname" /></td>
				<td><input type="text" name="lastname" value="${user.lastname}"></td>
				<td style="color: red; font-style: italic;"><strong><form:errors
							path="lastname" /></strong></td>
			</tr>
			<tr>
				<td><spring:message code="admin.email" /></td>
				<td><input type="text" name="email" value="${user.email}"></td>
				<td style="color: red; font-style: italic;"><strong><form:errors
							path="email" /></strong></td>
			</tr>
			<tr>
				<td><spring:message code="admin.phone" /></td>
				<td><input type="text" name="telephone"
					value="${user.telephone}"></td>
				<td style="color: red; font-style: italic;"><strong><form:errors
							path="email" /></strong></td>
			</tr>
			<tr>
				<td><spring:message code="admin.enabled" /></td>
				<td><c:choose>
						<c:when test="${user.enabled==true}">
							<input type="checkbox" name="enabled" checked />
						</c:when>
						<c:otherwise>
							<input type="checkbox" name="enabled" />
						</c:otherwise>
					</c:choose></td>
			</tr>
			<c:choose>
				<c:when
					test="${empty user.student.id and empty user.teacher.id and empty admin }">
					<tr>
						<td><input type="radio" name="roles" value="student_id">
							<spring:message code="admin.student" /></td>
						<td><input type="radio" name="roles" value="teacher_id">
							<spring:message code="admin.teacher" /></td>
						<td><input type="radio" name="roles" value="admin"> <spring:message
								code="admin.admin" /></td>
					</tr>
				</c:when>
				<c:when test="${!empty user.student.id}">
					<tr>
						<td><spring:message code="admin.role" /></td>
						<td><spring:message code="admin.student" /></td>
					</tr>
					<c:choose>
						<c:when test="${!empty user.student.clazz.id}">
							<tr>
								<td><spring:message code="admin.clazz.name" /></td>
								<td>${clazz.name }</td>
							</tr>
						</c:when>
						<c:otherwise>
							<td><label for="studentClazz"><spring:message
										code="admin.select.subject" /></label></td>
							<td><select class="select-style" name="studentClazz">
									<option value="0">--select class--</option>
									<c:forEach items="${clazzes}" var="clazz">
										<option value=${clazz.id }>${clazz.name}</option>
									</c:forEach>
							</select></td>
						</c:otherwise>
					</c:choose>
				</c:when>
				<c:when test="${!empty user.teacher.id }">
					<tr>
						<td><spring:message code="admin.role" /></td>
						<td><spring:message code="admin.teacher" /></td>
					</tr>
					<c:choose>
						<c:when test="${!empty user.teacher.subject}">
							<tr>
								<td><spring:message code="admin.subject.name" /></td>
								<td>${subject.name }</td>
							</tr>
						</c:when>
						<c:otherwise>
							<td><label for="subjects"><spring:message
										code="admin.select.subject" /> </label></td>
							<td><select class="select-style" id="subjects"
								name="subjects">
									<option value="0">--select subject--</option>
									<c:forEach items="${subjects}" var="subject">
										<option value=${subject.id }>${subject.name}</option>
									</c:forEach>
							</select></td>
						</c:otherwise>
					</c:choose>
					<tr>
					<tr>
						<c:if test="${!empty user.teacher.subject}">
							<td><spring:message code="admin.select.clazzes" /></td>
							<td><select class="select-style" name="teacherClazzes"
								multiple>
									<c:forEach items="${teacherClazzes}" var="clazz">
										<option selected value=${clazz.id }>${clazz.name}</option>
									</c:forEach>
									<c:forEach items="${clazzesWithoutTeacher}" var="clazz">
										<option value=${clazz.id }>${clazz.name}</option>
									</c:forEach>
							</select></td>
						</c:if>
					</tr>
				</c:when>
				<c:when test="${!empty admin}">
					<tr>
						<td><spring:message code="admin.role" /></td>
						<td><spring:message code="admin.admin" /></td>
					</tr>
				</c:when>

			</c:choose>
			<tr id="role"></tr>
			<tr id="classes"></tr>
			<c:if test="${!empty edit }">
				<tr>
					<td colspan="2"><input class="button" type="submit"
						value="<spring:message code="admin.edit.user"/>" /></td>
				</tr>
			</c:if>
		</table>
	</form:form>
	<script>
		var rad = document.myForm.roles;
		var role = document.getElementById('role');
		var html = '';
		var classes = document.getElementById("classes");
		for (var i = 0; i < rad.length; i++) {
			rad[i].onclick = function() {
				classes.innerHTML = '';
				if (this.value == 'student_id') {
					html = '';
					html += '<td><label for="studentClazz"><spring:message code="admin.select.clazz" /></label></td>';
					html += '<td><select class="select-style" name="studentClazz">';
					html += '<option value="0">--select class--</option>';
					html += '<c:forEach items="${clazzes}" var="clazz">';
					html += '<option value=${clazz.id }>${clazz.name}</option>';
					html += '</c:forEach>';
					html += '</select></td>';
					role.innerHTML = html;
				} else if (this.value == 'teacher_id') {
					html = '';
					html += '<td><label for="subjects"><spring:message code="admin.select.subject" /></label></td>';
					html += '<td><select id="subjects"  class="select-style" name="subjects">';
					html += '<option value=0>--select subject--</option>';
					html += '<c:forEach items="${subjects}" var="subject">';
					html += '<option value=${subject.id}>${subject.name}</option>';
					html += '</c:forEach>';
					html += '</select></td>';
					role.innerHTML = html;

				}

			};
		};

		$(document)
				.ready(
						function() {
							$('#role')
									.on(
											'change',
											function() {
												var id = document
														.getElementById("subjects").value;
												$
														.ajax({
															type : 'GET',
															url : 'http://localhost:8080/eschool/getClazzes/'
																	+ id,
															dataType : 'json',
															success : function(
																	data) {
																var classes = document
																		.getElementById("classes");
																var html = '';
																html += '<td><label for="teacherClazzes"><spring:message code="admin.select.clazzes"/> </label></td>';
																html += '<td><select name="teacherClazzes" multiple>';
																for (var i = 0; i < data.length; i++) {
																	html += '<option value=' + data[i].id + '>'
																			+ data[i].name
																			+ '</option>';
																}
																html += '</select></td>';
																classes.innerHTML = html;
															}
														});
											});

						});
	</script>

	<c:if test="${!empty users}">
		<table class="eschoolTable">
			<tr>
				<th><spring:message code="admin.firstname" /></th>
				<th><spring:message code="admin.lastname" /></th>
				<th><spring:message code="admin.email" /></th>
				<th><spring:message code="admin.phone" /></th>
				<th><spring:message code="admin.enabled" /></th>
				<th><spring:message code="admin.action" /></th>
				<th><spring:message code="admin.action" /></th>
			</tr>
			<c:forEach items="${users}" var="user">
				<tr>
					<td>${user.firstname}</td>
					<td>${user.lastname}</td>
					<td>${user.email}</td>
					<td>${user.telephone}</td>
					<c:choose>
						<c:when test="${user.enabled == true}">
							<td><img
								src="<c:url value="/resources/accept-tick-icon-12.png" />"
								height="25" width="25"></td>
						</c:when>
						<c:otherwise>
							<td><img src="<c:url value="/resources/ui-02-512.png" />"
								height="25" width="25"></td>
						</c:otherwise>
					</c:choose>
					<td><a href="deleteUser/${user.username}.html"><img
							src="<c:url value="/resources/delete1600.png" />" height="25"
							width="25"></a></td>
					<td><a href="users.html?username=${user.username}"><img
							src="<c:url value="/resources/tool-icon-20.png" />" height="25"
							width="25"></a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>