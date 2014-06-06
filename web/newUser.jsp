<%-- 
    Document   : newUser
    Created on : Jun 5, 2014, 4:09:11 PM
    Author     : deha
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>New user registration</title>
    </head>
    <body>
        <form action="newuser" method="post">
            <p>Name: </p><input type ="text" name="c_name" />
            <p>Surname: </p><input type="text" name="c_surname" />
            <p>Address: </p><input type="text" name="address" />
            <br /><br /><input type="submit" value="Register" />
        </form>
    </body>
</html>
