<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<link
	href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js"></script>
<script
	src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"></script>

</head>
<body>
	<h1 align="center">
		<spring:message code="register.header" />
	</h1>
		<form:form method="post" action="addUser.html" commandName="user">
			<table align="left">
				<tr>
					<td><form:label path="username">
							<spring:message code="register.username" />
						</form:label></td>
					<td><form:input path="username" /></td>
					<td style="color: red; font-style: italic;"><strong><form:errors
								color="red" path="username" /></strong></td>
					<c:if test="${not empty usernameIsBusy}">
						<td style="color: red; font-style: italic;"><strong><spring:message
									code="register.username.is.busy" /></strong></td>
					</c:if>
				</tr>
				<tr>
					<td><form:label path="password">
							<spring:message code="register.password" />
						</form:label></td>
					<td><form:input type="password" path="password" /></td>
					<td style="color: red; font-style: italic;"><strong><form:errors
								path="password" /></strong></td>
				</tr>
				<tr>
					<td><form:label path="email">
							<spring:message code="register.email" />
						</form:label></td>
					<td><form:input path="email" /></td>
					<td style="color: red; font-style: italic;"><strong><form:errors
								path="email" /></strong></td>
				</tr>
				<tr>
					<td><form:label path="firstname">
							<spring:message code="register.firstname" />
						</form:label></td>
					<td><form:input path="firstname" /></td>
					<td style="color: red; font-style: italic;"><strong><form:errors
								path="firstname" /></strong></td>
				</tr>
				<tr>
					<td><form:label path="lastname">
							<spring:message code="register.lastname" />
						</form:label></td>
					<td><form:input path="lastname" /></td>
					<td style="color: red; font-style: italic;"><strong><form:errors
								path="lastname" /></strong></td>
				</tr>
				<tr>
					<td><form:label path="telephone">
							<spring:message code="register.telephone" />
						</form:label></td>
					<td><form:input path="telephone" /></td>
					<td style="color: red; font-style: italic;"><strong><form:errors
								path="telephone" /></strong></td>
				</tr>
				<c:if test="${not empty invalidCaptcha}">
					<tr>
						<td colspan="2"><div class="error">
								<spring:message code="register.invalid.captcha" />
							</div></td>
					</tr>
				</c:if>
				<tr>
					<td colspan="2"><tags:captcha
							privateKey="6LdZe8QSAAAAAA8DQu_WXpuxQpTVaQM0EYPvO1M5"
							publicKey="6LdZe8QSAAAAANw5tJUftmtx1m45kYk3fw8aNd1N"></tags:captcha>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input name="signup"
						type="submit" class="button" style="width: 300px;"
						value="<spring:message code="register.register"/>" /></td>
				</tr>

			</table>

		</form:form>
</body>
</html>