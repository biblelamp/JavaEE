<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set value="${pageContext.request.contextPath}" var="contextPath" />
<c:set value="${contextPath}/resources" var="resPath"  />
<!DOCTYPE >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>IT SITE</title>
<script   src="https://code.jquery.com/jquery-3.1.1.min.js"   integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8="   crossorigin="anonymous"></script>
<link href="${resPath}/style.css" rel="stylesheet" type="text/css" />
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
<div id="templatemo_content_wrapper">

	<div id="templatemo_content">
  
    
  </div>
    
    <div id="templatemo_sidebar_one">
    
    	<h4>Категории</h4>
        <ul class="templatemo_list">
          <c:if test="${not empty categories }">
            <c:forEach items="${categories }"  var="category" >
        	<li><a  class="category_reff" href="./categories/${category.id}" >${category.name}</a></li>
        	</c:forEach>
           </c:if>
        </ul>
        
        <div class="cleaner_h40"></div>
    </div>
    
    <div id="templatemo_sidebar_two">
    	
        <div class="banner_250x200">
    		<a href="" target="_parent"><img src="${resPath}/images/250x200_banner.jpg" alt="templates" /></a>
        </div>
        
        <div class="banner_125x125">
        	<a href="" target="_parent"><img src="${resPath}/images/templatemo_ads.jpg" alt="web 1" /></a>
            <a href="" target="_parent"><img src="${resPath}/images/templatemo_ads.jpg" alt="web 2" /></a>
    		<a href="" target="_parent"><img src="${resPath}/images/templatemo_ads.jpg" alt="templates 2" /></a>
    		<a href="" target="_parent"><img src="${resPath}/images/templatemo_ads.jpg" alt="templates 1" /></a>
        </div>
       
        <div class="cleaner_h40"></div>
        
    </div>

	<div class="cleaner"></div>
</div> 
</div>
 <button class="btn_load">Загрузить еще</button>
<div id="templatemo_footer_wrapper">
	<div id="templatemo_footer">
		Copyright © 2016 <a href="#">IT SITE</a> | 
    </div> 
</div> 

<script>
var url="./articles/articles_ajax";
var contextPath="${contextPath}";
</script>
<script src="${resPath}/assets/getData.js"></script>
</body>
</html>