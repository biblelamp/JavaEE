<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- http://localhost:8080/JavaServerPages/ShowSeconds.jsp -->
<html>
<body>
<h3>The time in seconds is: <%= System.currentTimeMillis()/1000 %></h3>
<p><a href='.'>Back</a></p>
</body>
</html>