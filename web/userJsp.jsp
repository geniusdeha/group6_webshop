<%-- 
    Document   : userjsp
    Created on : 27.May.2014, 12:09:13
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
</head>
<body>
    <h1>This is the user interface!</h1>
    <%@ page import="beans.Product" %>
    <jsp:useBean id="list" scope="application" class="beans.ProductList"/>
    <table>
        <tr>
            <td>Product Name</td>
            <td>Components</td>
        </tr>
        <%
            for(int i = 0; i < list.getSize(); i++){
        %>
        <tr>
            <td><%= list.getProduct(i).getName() %></td>
            <td><%= list.getProduct(i).getComponents() %></td>
            <td><%= list.getProduct(i).getPrice() %></td>
            <td>
                <form action="buy" method="get">
                    <input type="hidden" value="<%= list.getProduct(i).getName()%>" name="type"/>
                    <input type="submit" value="BUY"/> 
                </form>
            </td>
        </tr>
        <% 
            } 
        %>
    </table>
    <p><%= request.getAttribute("message") %>
    <p><a href="index.jsp">BACK</a>
</body>
</html>
