<%-- 
    Document   : receipt
    Created on : Jun 6, 2014, 6:52:19 PM
    Author     : deha
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order Receipt</title>
    </head>
    <body>
        <jsp:useBean id="customerID" scope="application" class="java.lang.String"/>
        <jsp:useBean id="message" scope="application" class="java.lang.String"/>
        <h3><%= message %></h3>
        <p><br /><a href="profile.jsp">My Profile</a><br />
        <p><br /><a href="index.jsp">Main Page</a>
    </body>
</html>
