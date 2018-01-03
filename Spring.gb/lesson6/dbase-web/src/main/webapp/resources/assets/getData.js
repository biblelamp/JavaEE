// шаблон для размещения описания статьи в списке
var articleBody = "<div class='post_section'><h2><a class='article__title' href=''></a></h2><strong>Дата: </span></strong><span class='article__date'></span> | <strong>Автор: </strong><span class='article__author'></span><p><div class='article__content'></div><div class='cleaner'></div><p><div class='category'>Категория: <span class='article__category'></span></div><div class='button float_r'><a href=' ' class='more'>Читать далее</a></div><div class='cleaner'></div></div><div class='cleaner_h40'></div>";

// данные, которые передаются на сервер
// счетчик страниц (блоков)
var pageCounter = 0;
// количество статей на странице (в блоке)
var number = 3;
// порядок сортировки
var order = "DESC";
// поле для сортировки
var orderBy = "publishedDate";

// функция для размещения полученных данных на странице
function renderingArticles(articles) {

	articles.forEach(function(article) {
		var test = $(articleBody).find(".article__title").attr("href",contextPath+"/articles/"+article["id"]).html(article["title"])
			.end().find(".article__date").html(article["publishedDate"])
			.end().find(".article__author").html(article["author"]["firstname"])
			.end().find(".article__content").html(article["content"].substring(0, 110)+"...")
			.end().find(".article__category").html(article["category"].name)
			.end().find(".more").attr("href",contextPath+"/articles/"+article["id"])
			.end().appendTo("#templatemo_content");
	});
}

// функция для осуществления асинхронного GET запроса
function loadArticles() {

	// формирование строки с данными, которые необходимо передать на сервер в метод listAjax 
	var data = "pageCounter="+pageCounter+"&"+"number="+number+"&"+"order="+order+"&"+"orderBy="+orderBy;

	$.ajax({
		url: url,
		type: 'GET',
		data: data,
		cache: false,
		success: function(articlesResponsive) {
				if (articlesResponsive == 0) {
					console.log("getData: Unsucess!");
				} else {
					console.log("getData: Sucess!");
			    	// если ответ содержит данные, то они размещаются на странице
					// а счетчик страниц(блоков) увеличивается на единицу
					renderingArticles(articlesResponsive["articles"]);
					pageCounter++;
				}
		},
	});
}

$(document).ready(function() {
	// первая страница(блок) статей подгружается при загрузке страницы
	loadArticles();

	$(".btn_load").click(function() {
		// остальные страницы подгружаются при нажатии "Загрузить еще"
		loadArticles();
	})
});