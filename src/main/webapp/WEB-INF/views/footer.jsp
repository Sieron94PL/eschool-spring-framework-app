<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<spring:message code="footer.message" />

<sec:authorize access="isAuthenticated()">
	<a href="<c:url value="/logout" />"><spring:message code="menu.logout" /></a>
</sec:authorize>

<sec:authorize access="isAnonymous()">
	<a href="<c:url value="/login" />"><spring:message code="menu.sign.in" /></a>
</sec:authorize>

