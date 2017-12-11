package dbase.service;

import java.util.List;

import dbase.domain.Article;

public interface ArticleService {
    public List<Article> getAll();
    public Article get(Long id);
    public void save(Article article);
}