<c:set var="appFolder" value="<%=request.getContextPath()%>" />
<img src="${appFolder}/resources/logo.png" />
<hr />
<ul class="menu">
    <%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
    <security:authorize access="hasRole('STUDENT')">
        <li><a href="${appFolder}/student/exam/">Exams</a></li>
        <li><a href="${appFolder}/student/examStatistics">Stats</a></li>
    </security:authorize>

    <security:authorize access="isAnonymous()">
        <li><a href="${appFolder}/anon/login">Login</a></li>
    </security:authorize>

    <security:authorize access="hasRole('TEACHER')">
        <li>not implemented yet</li>
    </security:authorize>

    <security:authorize access="isAuthenticated()">
        <li><a href="${appFolder}/j_spring_security_logout">Logout</a></li>
    </security:authorize>

</ul>