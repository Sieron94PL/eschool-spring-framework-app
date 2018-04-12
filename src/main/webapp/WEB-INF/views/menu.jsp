<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="<c:url value="/resources/style.css" />" rel="stylesheet">
<sec:authorize access="hasRole('ROLE_ADMIN')">
	<ul>
		<li><a class="menu" href="/eschool/admin/users.html"><spring:message
					code="menu.users" /></a></li>
		<li><a class="menu" href="/eschool/admin/subjects.html"><spring:message
					code="menu.subjects" /></a></li>
		<li><a class="menu" href="/eschool/admin/clazzes.html"><spring:message
					code="menu.clazzes" /></a></li>
	</ul>
</sec:authorize>


<sec:authorize access="isAnonymous()">

	<ul>
		<li><a class="menu" href="/eschool/login.html"><spring:message
					code="menu.sign.in" /></a></li>
		<li><a class="menu" href="/eschool/registration"><spring:message
					code="menu.registration" /></a></li>
	</ul>
</sec:authorize>



<sec:authorize access="hasRole('ROLE_STUDENT')">

	<ul>
		<li><a class="menu" href="/eschool/student/marks.html"><spring:message
					code="student.mark.list" /></a></li>
		<li><a class="menu" href="/eschool/student/profile.html"><spring:message
					code="menu.profile" /></a></li>
	</ul>

</sec:authorize>

<sec:authorize access="hasRole('ROLE_TEACHER')">
	<ul>
		<li><a class="menu" href="/eschool/teacher/marks.html"><spring:message
					code="teacher.marks.management" /></a></li>
		<li><a class="menu" href="/eschool/teacher/profile.html"><spring:message
					code="menu.profile" /></a></li>
	</ul>
</sec:authorize>
