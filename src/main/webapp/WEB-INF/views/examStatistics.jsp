<%@include file="fragments/head.jsp"%>
<title>Statistics</title></head><body>
<%@include file="fragments/menu.jsp"%>
<table>
    <tr>
        <th>Exam title</th>
        <th>Score</th>
    </tr>
<c:forEach items="${examStatisticsList}" var="examStatistics">
    <tr>
        <td>${examStatistics.exam.name}</td>
        <td>${examStatistics.correctAnswers}/${examStatistics.exam.size}</td>
    </tr>
</c:forEach>
</table></body></html>
