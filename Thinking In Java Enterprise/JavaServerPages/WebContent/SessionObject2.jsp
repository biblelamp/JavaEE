<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%-- Session object is transferred --%>
<html>
<head>
	<title>Session object is transferred</title>
</head>
<body>
<h3>Session id: <%=session.getId()%></h3>
<p>Session value for "My dog" <b><%=session.getValue("My dog")%></b></p>
<%
    session.invalidate();
%>
<p><a href='.'>Back</a></p>
</body>
</html>