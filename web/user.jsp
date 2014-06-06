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
    <jsp:useBean id="customer" scope="application" class="beans.Customer"/>
    <h3>Hello <%= customer.getCustomerName() + " " + customer.getCustomerSurname() %></h3>
    <%@ page import="beans.Product" %>
    <jsp:useBean id="list" scope="application" class="beans.ProductList"/>
    <form action="buy" method="post">    
        <table border="1" style="width:600px">
            <tr>
                <th>Product Name</th>
                <th>Components</th>
                <th>Price</th>
                <th>Quantity</th>
            </tr>
            <%
                for(int i = 0; i < list.getSize(); i++){
            %>
            <tr>
                <td><%= list.getProduct(i).getName() %></td>
                <td><%= list.getProduct(i).getComponents() %></td>
                <td><%= list.getProduct(i).getPrice() %><input type="hidden" name="price<%= list.getProduct(i).getName() %>" value="<%= list.getProduct(i).getPrice() %>"/></td>
                <td align="center" ><input type="number" name="quantity<%= list.getProduct(i).getName() %>" min="0" size="5" value="0"></td>
            </tr>
            <% 
                } 
            %>
        </table>
        <input type="hidden" value="<%= customer.getCustomerID() %>" name="customerID"/>
        <br /><input type="submit" value="BUY"/> 
    </form>
    <p><br /><a href="profile.jsp">My Profile</a><br />
    <%-- <p><%= request.getAttribute("message") %> --%>
    <p><br /><a href="index.jsp">Main Page</a>
</body>
</html>
