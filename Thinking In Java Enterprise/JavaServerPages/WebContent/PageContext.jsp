<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%-- Viewing attributes pageContext--%>
<%-- Note that you can include any number of code
within scriptlet tags --%>
<%@ page import="java.util.*" %>
<html>
<head>
	<title>Page Context</title>
</head>
<body>

Servlet Name: <%= config.getServletName() %><br>
Servlet container supports servlet version:

<% 
out.print(application.getMajorVersion() + "."
+ application.getMinorVersion()); %>
<br>
<%
  session.setAttribute("My dog", "Ralph");
  for(int scope = 1; scope <= 4; scope++) {  
%>
    <h3>Scope: <%= scope %> </h3>
<%  
	Enumeration e = pageContext.getAttributeNamesInScope(scope);
    while(e.hasMoreElements()) {
		out.println("\t<li>" + e.nextElement() + "</li>");
	}
  }
%>
<p><a href='.'>Back</a></p>
</body>
</html>