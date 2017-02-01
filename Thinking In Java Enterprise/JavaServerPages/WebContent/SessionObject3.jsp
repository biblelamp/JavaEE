<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%-- Going through the pages of the session object --%>
<html>
<head>
	<title>Going through the pages of the session object</title>
</head>
<body>
<h3>Session id: <%=session.getId()%></h3>
<p>Session value for "My dog" <b><%=session.getValue("My dog")%></b></p>

<form method="POST" action="SessionObject.jsp">
	<input type="submit" name="submit" value="Return">
</form>
<p><a href='.'>Back</a></p>
</body>
</html>