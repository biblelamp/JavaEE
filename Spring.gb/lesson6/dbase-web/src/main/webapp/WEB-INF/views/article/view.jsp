<%@page isELIgnored="false" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:set value="${pageContext.request.contextPath}" var="contextPath" />
<c:set value="${contextPath}/resources" var="resPath" />
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8" />
	<title>IT SITE : ${article.title}</title>
	<link rel="stylesheet" href="${resPath}/style.css" />
</head>
<body>
	<div id="templatemo_header_wrapper">
		<div id="templatemo_header">
			<div id="site_title">
				<a href="${contextPath}">
					<img src="${resPath}/images/templatemo_logo.png" title="Software Blog.">
				</a>
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
					<h2><a class='article__title' href=''></a>${article.title}</h2>
					<strong>Дата: </strong><span class='article__date'><fmt:formatDate pattern="yyyy-MM-dd" value="${article.publishedDate}" /></span> | <strong>Автор: </strong><span class='article__author'>${article.author.firstname}</span>
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
			Copyright © 2018 <a href="#">IT SITE</a>
		</div> <!-- end of templatemo_copyright -->
	</div> <!-- end of copyright wrapper -->
</body>
</html>