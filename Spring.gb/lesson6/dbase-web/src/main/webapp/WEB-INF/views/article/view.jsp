<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set value="${pageContext.request.contextPath}" var="contextPath" />
<c:set value="${contextPath}/resources" var="resPath"  />
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>IT SITE</title>

<link href="${resPath}/style.css" rel="stylesheet" type="text/css" />
<script src="${resPath}/assets/ckeditor/ckeditor.js"></script>
<script   src="https://code.jquery.com/jquery-3.1.1.min.js"   integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8="   crossorigin="anonymous"></script>
</head>
<body>
<div id="templatemo_header_wrapper">
	<div id="templatemo_header">
    	
       <div id="site_title">
            
        </div>
        
        <div id="templatemo_rss">
            <a href="" target="_parent">SUBSCRIBE<br /><span>OUR FEED</span></a>
    	</div>
    
    </div> <!-- end of header -->
    
    <div id="templatemo_menu">
    
        <ul>
            <li><a href="${contextPath}">Главная</a></li>
            <li><a href="${contextPath}/articles/add">Написать статью</a></li>
        </ul>	
    
    </div> <!-- end of templatemo_menu -->
    
</div> <!-- end of header wrapper -->

<div id="templatemo_main_wrapper">
<div id="templatemo_add_content_wrapper">

	<div id="templatemo_content">
  
  <c:if test="${not empty article}">  
     
         <div class='post_section view'>
         <h2><a class='article__title' href='' ></a>${article.title}</h2>
         <strong>Дата: </span></strong><span class='article__date'><fmt:formatDate pattern="yyyy-MM-dd" 
            value="${article.publishedDate}" /></span> | <strong>Автор: </strong> <span class='article__author'>${article.author.firstname}</span>
         <div class="cleaner"></div>
         <p><div class='article__content view'>${article.content}</div>
         <div class='cleaner'></div>
         <p><div class='category view'>Категория: <span>${article.category.name}</span></div>
        </div>
        
   </c:if>
    </div>
	<div class="cleaner"></div>
</div> <!-- end of content wrapper -->
</div>

<div id="templatemo_footer_wrapper">
	<div id="templatemo_footer">
    
		Copyright © 2016 <a href="#">IT SITE</a> | 
       
        
    </div> <!-- end of templatemo_copyright -->
</div> <!-- end of copyright wrapper -->
</body>