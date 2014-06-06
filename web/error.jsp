<%-- 
    Document   : resourceerror
    Created on : 29.May.2014, 01:27:41
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Error</title>
</head>
<body>
    <jsp:useBean id="message" scope="application" class="java.lang.String"/>
    <h3><%= message %></h3>
    <p><a href="user.jsp">Click to go back</a></p>
</body>
</html>
