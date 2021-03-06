<%@page isELIgnored="false" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<c:set value="${pageContext.request.contextPath}" var="contextPath" />
<c:set value="${contextPath}/resources" var="resPath" />
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8" />
	<title>IT SITE : Написать статью</title>
	<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
	<script src="${resPath}/assets/ckeditor/ckeditor.js"></script>
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
				<div class="post_section">
					<form:form modelAttribute="article" class="add_article_form" method="POST" action="${contextPath}/articles"> 
					<h2 class="message">Создание статьи</h2>
					<span class="add_category">Категория*</span>
					<select id="categoryId" name="categoryId" class="cd-select">
					<c:if test="${not empty categories}">
						<option value="0" selected>Выберите категорию</option>
						<c:forEach items="${categories}" var="category">
							<option value="${category.id}">${category.name}</option>
						</c:forEach>
					</c:if>
					</select>
					<div class="cleaner"></div>
					<form:label path="title" class="add_title">Заголовок*</form:label><form:input type="text" path="title" class="add_title_input" placeholder="Заголовок статьи" />
					<div class="cleaner_h40"></div>
					<form:textarea path="content" id="content" class="contentarea"></form:textarea>
					<div class="author_add">
						<span class="author_info_title">Данные автора*</span>
						<form:input path="author.firstname" type="text" placeholder="Имя" class="add_author_firstname" />
						<form:input path="author.lastname" type="text" placeholder="Фамилия" name="add_author_lastname" />
						<form:input path="author.email" type="text" placeholder="E-mail" name="add_author_email" />
					</div>
					<input type="submit" class="button_sub" value="Опубликовать" />
					</form:form>
				</div>
			</div>
		</div>
		<div class="cleaner"></div>
	</div> <!-- end of content wrapper -->

	<div id="templatemo_footer_wrapper">
		<div id="templatemo_footer">
			Copyright © 2018 <a href="#">IT SITE</a>
		</div> <!-- end of templatemo_copyright -->
	</div> <!-- end of copyright wrapper -->

	<script type="text/javascript">
		$(document).ready(function() {
			CKEDITOR.replace('content');
			CKEDITOR.config.width = "100%";
			CKEDITOR.config.height = 600;
		});
	</script>
</body>
</html>