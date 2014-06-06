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
    <title>Admin Page</title>
</head>
<body>
    <h1>Welcome admin!</h1>
    <%@ page import="beans.Component" %>
    <jsp:useBean id="list" scope="application" class="beans.ComponentList"/>
    <table>
        <tr>
            <td>Component Name</td>
            <td>Amount</td>
        </tr>
        <%
            for(int i = 0; i < list.getSize(); i++){
        %>
        <tr>
            <td><%= list.getComponent(i).getName() %></td>
            <td><%= list.getComponent(i).getAmount()%></td>
            <td>
                <form action="add" method="get">
                    <input type="text" value="Enter amount" name="number"/>
                    <input type="hidden" value="<%= list.getComponent(i).getName()%>" name="type"/>
                    <input type="submit" value="ADD"/> 
                </form>
            </td>
        </tr>
        <% 
            } 
        %>
    </table>
    <%-- <p><%= request.getAttribute("message") %> --%>
    <p><a href="index.jsp">Main Page</a>
</body>
</html>
