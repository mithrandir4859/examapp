<%@include file="fragments/head.jsp"%>
<title>Error</title></head><body>
<%@include file="fragments/menu.jsp"%>
<c:set var="exception" value="${requestScope['javax.servlet.error.exception']}"/>
<jsp:scriptlet>
  exception.printStackTrace(new java.io.PrintWriter(out));
</jsp:scriptlet>
</body></html>