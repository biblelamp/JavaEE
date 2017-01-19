<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
         "http://www.w3.org/TR/html4/loose.dtd">

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.text.*" %>

<jsp:useBean id="hello" class="example.JavaBeanHello" scope="page" />

<%!
String getFormattedDate() {
    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
    return sdf.format(new Date());
}
%>
<html>
  <head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <title>JavaBean with JSP</title>
  </head>
  <body>
       <h2>${hello.message}</h2>
       <i>Today is <%= getFormattedDate() %></i>
  </body>
</html>