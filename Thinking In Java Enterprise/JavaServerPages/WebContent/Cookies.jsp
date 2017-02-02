<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%-- This program behaves differently in different browsers! --%>
<html>
<head>
	<title>Cookies</title>
</head>
<body>
<h3>Session id: <%= session.getId() %></h3>
<%
Cookie[] cookies = request.getCookies();
for(int i = 0; i < cookies.length; i++) { %>
  Cookie name: <%= cookies[i].getName() %> <br>
  value: <%= cookies[i].getValue() %><br>
  Old max age in seconds: 
  <%= cookies[i].getMaxAge() %><br>
  <% cookies[i].setMaxAge(5); %>
  New max age in seconds:
  <%= cookies[i].getMaxAge() %><br>
<% } %>

<%! int count = 0; int dcount = 0; %>
<% 
  response.addCookie(new Cookie("Bob" + count++, "Dog" + dcount++)); 
%>
<p><a href='.'>Back</a></p>
</body>
</html>