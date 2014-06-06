<%-- 
    Document   : adminlogin
    Created on : Jun 6, 2014, 3:37:45 PM
    Author     : deha
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Login to Web-shop</title>
    </head>
    <body>
        <p>Enter admin password</p>
        <form action="adminlogin" method="post">
            <input type="password" value="" name="password"/>
            <input type="submit" value="Login"/> 
        </form>
        <br/><br/><a href="index.jsp">Main Page</a>
    </body>
</html>
