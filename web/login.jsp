<%-- 
    Document   : login
    Created on : Jun 5, 2014, 11:49:07 AM
    Author     : deha
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login to Webshop</title>
    </head>
    <body>
        <p>Enter customer number</p>
        <form action="login" method="post">
            <input type="text" value="" name="customerID"/>
            <input type="submit" value="Login"/> 
        </form>
        
        <a href="newUser.jsp">New user? Click here to register.</a>
        <br/><br/><a href="index.jsp">Main Page</a>
    </body>
</html>
