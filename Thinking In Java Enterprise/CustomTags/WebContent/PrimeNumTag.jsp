<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="WEB-INF/tijtags.tld" prefix="tijtags"%>
<html>
<head>
	<title>Prime Num Tag</title>
</head>
<body>
	<ul>
	    <tijtags:PrimeNumTag start="1" end="12">
	        <li><%=value%></li>
	    </tijtags:PrimeNumTag>
	</ul>
	<p><a href='.'>Back</a></p>
</body>
</html>