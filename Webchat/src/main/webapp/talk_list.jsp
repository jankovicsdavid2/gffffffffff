<%@ page import="com.nixs.dtp.examples.webchat.model.User"%>
<%@ page import="javax.naming.Context" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="javax.naming.InitialContext" %>
<%@ page import="javax.sql.DataSource" %>
<%@ page import="com.nixs.dtp.examples.webchat.model.IModel" %>
<%@ page import="com.nixs.dtp.examples.webchat.model.DBModel" %>
<%@ page import="javax.naming.NamingException" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.util.List" %>
<%@ page import="com.nixs.dtp.examples.webchat.model.Talk" %><%--
  Created by IntelliJ IDEA.
  User: student-4322
  Date: 2026. 01. 19.
  Time: 10:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        User user = (User) request.getSession().getAttribute("user");

        List<Talk> talks = (List<Talk>) request.getAttribute("talks");
    %>

    <h1>Hello kedves <%=user.getName()%></h1>
</body>
</html>
