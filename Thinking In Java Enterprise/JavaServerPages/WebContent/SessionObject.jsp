<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Session Object</title>
</head>
<body>
<h3>Session id: <%= session.getId() %></h3>
<ol>
    <li>This session was created at 
    <%= session.getCreationTime() %>
    </li>
    <li>
        Old MaxInactiveInterval = <%= session.getMaxInactiveInterval() %>
    </li>
    <% session.setMaxInactiveInterval(5); %>
    <li>
        New MaxInactiveInterval= <%= session.getMaxInactiveInterval() %>
    </li>
</ol>

<p>If the session object "My dog" is still around, this value will be non-null:</p>
<ol>
    <li>
        Session value for "My dog" = <%= session.getAttribute("My dog") %>
    </li>
</ol>

<%-- Now add a session object "My dog" --%>

<% session.setAttribute("My dog", new String("Ralph")); %>

<h3>My dog's name is <%= session.getAttribute("My dog") %></h3>

<%-- See if "My dog" wanders to another form --%>

<form method="POST" action="SessionObject2.jsp">
	<input type="submit" name="submit" value="Invalidate">
</form>

<form method="POST" action="SessionObject3.jsp">
	<input type="submit" name="submit" value="Keep Around">
</form>

<p><a href='.'>Back</a></p>
</body>
</html>