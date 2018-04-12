<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<link href="<c:url value="/resources/style.css" />" rel="stylesheet">
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						$('#classes')
								.on(
										'change',
										function() {
											var id = document
													.getElementById("classes").value;
											$
													.ajax({
														type : 'GET',
														url : 'http://localhost:8080/eschool/getStudents/'
																+ id, //this is my servlet
														dataType : 'json',
														success : function(data) {
															var students = document
																	.getElementById("students");
															var html = '';
															for (var i = 0; i < data.length; i++) {
																html += '<option value=' + data[i].id + '>'
																		+ data[i].user.firstname
																		+ ' '
																		+ data[i].user.lastname
																		+ '</option>';
															}
															students.innerHTML = html;

															var students = document
																	.getElementById("students").options.length;
															var btn = document
																	.getElementById("btn");
															if (students == 0) {
																btn.disabled = true;
															} else {
																btn.disabled = false;
															}
															console
																	.log(students);

														}
													});
										});

					});

	$(document)
			.ready(
					function() {
						$('#classes')
								.on(
										'change',
										function() {
											var subject_id = document
													.getElementById("subject_id").value;
											var clazz_id = document
													.getElementById("classes").value;
											$
													.ajax({
														type : 'GET',
														url : 'http://localhost:8080/eschool/getStudents/'
																+ clazz_id
																+ '/'
																+ subject_id,
														dataType : 'json',
														success : function(data) {
															var markList = document
																	.getElementById('markList');
															var dataLength = Object
																	.keys(data).length;

															if (dataLength != 0) {
																html = '<table class="eschoolTable">';
																html += '<tr>';
																html += '<th><spring:message code="student.firstname.and.lastname" /></th>';
																html += '<th><spring:message code="mark.marks" /></th>';
																html += '</tr>';
																$
																		.each(
																				data,
																				function(
																						key,
																						value) {
																					html += '<tr>';
																					html += '<td>';
																					html += key
																							+ '</td>';
																					html += '<td style="font-style:italic;">'
																					for (var i = 0; i < value.length; i++) {
																						html += value[i].mark
																								+ '  ';
																					}
																					html += '</td></tr>'
																				});
																html += '</table>';
																markList.innerHTML = html;
															} else {
																markList.innerHTML = '';
															}
														}
													});
										});

					});
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
	<h1>
		<spring:message code="teacher.marks.management" />
	</h1>
	<table>
		<tr style="font-size: 24px;">
			<td>Witaj <strong>${teacher.user.firstname} ${teacher.user.lastname}!</strong></td>
		</tr>
	</table>
	<c:choose>
		<c:when test="${!empty clazzes }">
			<form:form method="post" action="addMark.html" commandName="mark">
				<table>
						<tr>
							<td><input type="hidden" name="subject.id" id="subject_id"
								value="${subject.id}"></td>
						<tr>
							<td><label for="classes"><spring:message
										code="admin.clazz.name" /></label></td>
							<td><select class="select-style" id="classes" name="clazzes">
									<c:forEach items="${clazzes}" var="clazz">
										<option value=${clazz.id }>${clazz.name}</option>
									</c:forEach>
							</select></td>
						</tr>
						<tr>
							<td><label for="students"><spring:message
										code="student.firstname.and.lastname" /></label></td>
							<td><select class="select-style" id="students" name="students">
									<c:forEach items="${students}" var="student">
										<option value=${student.id }>${student.user.firstname }
											${student.user.lastname }</option>
									</c:forEach>
							</select></td>
						</tr>
						<tr>
							<td><label for="mark"><spring:message
										code="student.mark" /></label></td>
							<td><select class="select-style" name="mark">
									<option>2</option>
									<option>2.5</option>
									<option>3</option>
									<option>3.5</option>
									<option>4</option>
									<option>4.5</option>
									<option>5</option>
							</select></td>
						</tr>
						<tr>
							<td><input id="btn" class="button" type="submit" disabled
								value="<spring:message code="teacher.add.mark" />"></td>
						</tr>
				</table>
			</form:form>
			<div id="markList">
				<c:if test="${!empty students }">
					<table class="eschoolTable">
						<tr>
							<th><spring:message code="student.firstname.and.lastname" /></th>
							<th><spring:message code="mark.marks" /></th>
						</tr>
						<c:forEach items="${markList }" var="entry">
							<tr>
								<td>${entry.key.user.firstname }${entry.key.user.lastname }</td>

								<td><c:forEach items="${entry.value}" var="mark">
					${mark.mark } 
				</c:forEach></td>

							</tr>
						</c:forEach>
					</table>
				</c:if>

			</div>
		</c:when>
		<c:otherwise>
			<table clazz="myTable">
				<tr>
					<td><spring:message code="teacher.no.class.allocated" /></td>
				</tr>
			</table>
		</c:otherwise>
	</c:choose>

</body>
</html>