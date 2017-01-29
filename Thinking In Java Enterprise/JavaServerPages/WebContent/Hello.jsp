<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%-- This JSP comment doesn't appear in the generated html --%>
<%-- It's JSP directive: --%>
<%@ page import="java.util.*" %>
<%-- This are declarations: --%>
<%!
    long loadTime= System.currentTimeMillis();
    Date loadDate = new Date();
    int hitCount = 0;
%>
<html>
<head>
	<title>Hello</title>
</head>
<body>
<%-- 
The next few lines are the result of
JSP expression inserted into the generated html;
'=' sign indicates a JSP expression 
--%>

<h3>Hello Page</h3>
<p>This page was loaded <b><%= loadDate %></b></p>
<p>Hello, world! It's <b><%= new Date() %></b></p>
<p>Here's an object: <b><%= new Object() %></b></p>
<p>This page has been up <b><%= (System.currentTimeMillis()-loadTime)/1000 %> seconds</b></p>
<p>Page has been accessed <b><%= ++hitCount %> times since <%= loadDate %></b></p>

<%-- 
"Scriptlets", which writes on the server console
and customer page. Note that you must put ';':
--%>

<%
   System.out.println("Goodbye");
   out.println("Cheerio");
%>
<p><a href='.'>Back</a></p>
</body>
</html>