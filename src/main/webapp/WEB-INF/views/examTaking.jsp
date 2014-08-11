<%@include file="fragments/head.jsp" %>
<title>Examination</title>
<script type="text/javascript" src="<c:url value='/resources/jquery-1.9.1.js' /> "></script>
<script type="text/javascript" src="<c:url value='/resources/getQuestion.js' /> "></script>
</head><body>
<%@include file="fragments/menu.jsp" %>
<div>${exam.name}</div>

<table>
    <tr>
        <td>
            <div id="insertQuestion"/>
        </td>
        <td>
            <h2 id="countdown"></h2>
        </td>
    </tr>

</table>

<button id="next">Next question</button>
</body></html>
