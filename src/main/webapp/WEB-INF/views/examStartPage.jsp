<%@include file="fragments/head.jsp"%>
<title>Start</title></head><body>
<%@include file="fragments/menu.jsp"%>
<div>${exam.name}</div>
<div>${exam.description}</div>
<form action="${appFolder}/student/exam/${exam.id}" method="post">
    <input type="submit" value="start exam"/>
</form></body></html>
