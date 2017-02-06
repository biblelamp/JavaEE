<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="WEB-INF/tijtags.tld" prefix="tijtags"%>
<html>
<head>
	<title>Hello World Tag</title>
</head>
<body>
	<p>
    	<tijtags:HelloWorldTag />
	</p>
	<p>
		<%-- <tijtags:GreetingTag id="randomgreeting" /> --%>
		<jsp:useBean id="randomgreeting" class="tags.GreetingBean" />
		<jsp:getProperty name="randomgreeting" property="greeting" />
	</p>
	<p><a href='.'>Back</a></p>
</body>
</html>