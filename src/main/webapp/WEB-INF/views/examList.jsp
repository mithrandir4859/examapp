<%@include file="fragments/head.jsp"%>
<title>Exams</title></head><body>
<%@include file="fragments/menu.jsp"%>
<table>
    <tr>
        <th>Exam title</th>
        <th></th>
    </tr>
    <c:forEach items="${examList}" var="exam">
        <tr>
            <td>${exam.name}</td>
            <td><a href="${appFolder}/student/exam/${exam.id}">Take</a></td>
        </tr>
    </c:forEach>
</table></body></html>
