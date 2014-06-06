<%-- 
    Document   : orderhistory
    Created on : Jun 6, 2014, 6:52:35 PM
    Author     : deha
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order History</title>
    </head>
    <body>
        <h3>Your order history</h3>
        <%@ page import="beans.Order" %>
        <jsp:useBean id="orderhistory" scope="application" class="beans.OrderList"/>
        <table border="1" style="width:600px">
            <tr>
                <th>Order ID</th>
                <th>Products</th>
                <th>Total Price</th>
            </tr>
            <%
                for(int i = 0; i < orderhistory.getSize() ; i++){
            %>
            <tr>
                <td><%= orderhistory.getOrder(i).getOrderID() %></td>
                <td><%= orderhistory.getOrder(i).getProducts() %></td>
                <td><%= orderhistory.getOrder(i).getTotalPrice() %></td>
            </tr>
            <% 
                } 
            %>
        </table>
        <p><br /><a href="profile.jsp">My Profile</a><br />
        <p><br /><a href="index.jsp">Main Page</a>
    </body>
</html>
