<%@include file="fragments/head.jsp"%>
<title>Login</title></head><body>
    <%@include file="fragments/menu.jsp"%>
    <form action="${appFolder}/j_spring_security_check" method="post">
        <input type="text" name="login" value="login"/><br />
        <input type="password" name="password" value="password"/><br />
        <input type="submit" />
    </form>
</body></html>
