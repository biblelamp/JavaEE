package spring.web.ajax;

import java.util.List;

import spring.domain.Article;

public class ArticlesAjax {
    private List<Article> articles;

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}