<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="<c:url value="/resources/style.css" />" rel="stylesheet">
</head>
<body onload='document.loginForm.username.focus();'>

	<h1 align="center">
		<spring:message code="login.header" />
	</h1>
	<div id="login-box">

		<c:if test="${not empty error}">
			<div class="error">${error}</div>
		</c:if>
		<c:if test="${not empty msg}">
			<div class="msg">${msg}</div>
		</c:if>

		<form name='loginForm' action="<c:url value='/login' />" method='POST'>

			<table>
				<tr>
					<td colspan="2" align="center" style="font-size:30px;"><strong>Login</strong></td>
				</tr>
				<tr>
					<td align="center"><strong><spring:message code="register.username" /></strong></td>
					<td><input width="100%" type='text' name='username'></td>
				</tr>
				<tr>
					<td align="center"><strong><spring:message code="login.password" /></strong></td>
					<td><input width="100%" type='password' name='password' /></td>
				</tr>
				<tr>
					<td colspan='1' align="right"><input class="button"
						type="submit" value="<spring:message code="login.login"/>" /></td>
					<td colspan='2' align="right">

						<button class="button" type="submit"
							style="color: #228B22; background-color: #98FB98; border-color: #00FF00;"
							formaction="<c:url value='/registration' />">
							<spring:message code="menu.registration" />
						</button>

					</td>
				</tr>
			</table>

			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		</form>
	</div>
</body>

</html>