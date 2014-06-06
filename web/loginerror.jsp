<%-- 
    Document   : loginerror
    Created on : Jun 6, 2014, 3:50:33 PM
    Author     : deha
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Error</title>
    </head>
    <body>
        <jsp:useBean id="loginType" scope="application" class="java.lang.String"/>
        <%
            if(loginType.equals("user")){
        %>
                <h3>No such customer ID is registered!</h3>
                <a href="login.jsp">Try again</a>
        <%
            }
            else if(loginType.equals("admin")){
        %>
                <h3>Wrong admin password!</h3>
                <a href="adminlogin.jsp">Try again</a>
        <%
            }
        %>
        <br/><br/><a href="index.jsp">Main Page</a>
    </body>
</html>
