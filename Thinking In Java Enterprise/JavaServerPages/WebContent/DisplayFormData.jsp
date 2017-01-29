<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%-- Extracting data from HTML forms --%>
<%-- This JSP also generates a form --%>
<%@ page import="java.util.*"%>
<html>
<head>
	<title>Display Form Data</title>
</head>
<body>

<h3>Display Form Data</h3>
<%
    Enumeration flds = request.getParameterNames();
    if (!flds.hasMoreElements()) { // no fields
%>
<form method="POST" action="DisplayFormData.jsp">
<%
    for (int i = 0; i < 10; i++) {
%>
Field<%=i%>: <input type="text" size="20" name="Field<%=i%>" value="Value<%=i%>"><br>
<%
    }
%> 
<input type="submit" name="submit" value="Submit"></form>
<ul>
<%
    } else {
        while (flds.hasMoreElements()) {
            String field = (String) flds.nextElement();
            String value = request.getParameter(field);
%>
	<li><%=field%> = <%=value%></li>
<%
    	}
	}
%>
</ul>
<p><a href='.'>Back</a></p>
</body>
</html>