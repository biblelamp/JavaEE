<%@ page language="java" isELIgnored="false" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>GetForm Servlet</title>
    <%
        String code = request.getParameter("code");
        String name = request.getParameter("name");
        String price = request.getParameter("price");
    %>
</head>
<body>
    <h2>GetForm Servlet:</h2>
    <table border="0">
    <tr>
        <td>Code</td>
        <td><b>${param["code"]}</b></td>
    </tr>
    <tr>
        <td>Name</td>
        <td><b>${param["name"]}</b></td>
    </tr>
    <tr>
        <td>Price</td>
        <td><b><%= price %></b></td>
    </tr>
    <tr>
        <td>Example</td>
        <td>16+64*2 = <b><c:out value="${16+64*2}" /></b></td>
    </tr>
    </table>
    Go <a href="<%= request.getServletContext().getContextPath() %>">Back</a>
</body>
</html>