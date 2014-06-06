<%-- 
    Document   : profile
    Created on : Jun 5, 2014, 7:04:34 PM
    Author     : deha
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Profile Page</title>
    </head>
    <body>
        <jsp:useBean id="customer" scope="application" class="beans.Customer"/>
        <h3>Your ID number is <%= customer.getCustomerID() %>. Login with this ID number.</h3>
        <form action="update" method="post">
            <input type="hidden" name="customerID" value="<%= customer.getCustomerID() %>" />
            <p>Name: </p><input type ="text" name="customerName" value="<%= customer.getCustomerName() %>" />
            <p>Surname: </p><input type="text" name="customerSurname" value="<%= customer.getCustomerSurname() %>" />
            <p>Address: </p><input type="text" name="customerAddress" value="<%= customer.getAddress() %>" />
            <br /><br /><input type="submit" value="Update Profile" />
        </form>
        <br /><br />
        <form action="orderhistory" method="post">
            <input type="hidden" name="customerID" value="<%= customer.getCustomerID() %>" />
            <input type="submit" value="Order History" />
        </form>
        <br /><br /><a href="user.jsp">Return to Webshop</a>
    </body>
</html>
