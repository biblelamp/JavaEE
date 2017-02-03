<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% // Use of PersonBean in a JSP. %>
<jsp:useBean id="person" class="player.PersonBean" scope="page"/>
<jsp:setProperty name="person" property="*"/>
<html>
<head>
	<title>Using JavaBean</title>
</head>
<body>
	Name: <jsp:getProperty name="person" property="name"/><br/>
    Deceased? <jsp:getProperty name="person" property="deceased"/><br/>
    <br/>
    <form name="beanTest" method="POST" action="UsingJavaBean.jsp">
        Enter a name: <input type="text" name="name" size="30"><br/>
        Choose an option:
        <select name="deceased">
            <option value="false">Alive</option>
            <option value="true">Dead</option>
        </select>
        <input type="submit" value="Test the Bean">
    </form>
</body>
</html>