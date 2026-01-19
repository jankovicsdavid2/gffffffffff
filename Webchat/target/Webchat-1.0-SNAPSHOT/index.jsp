<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
    <%
        for (int i = 0; i < 10; i++) {

    %>
        <%= i%>
    <%

        }
    %>
</h1>
<br/>
<a href="hello-servlet">Hello Servlet</a>
<a href="login.html">Login</a>
<a href="Registration.html">Registration</a>
</body>
</html>