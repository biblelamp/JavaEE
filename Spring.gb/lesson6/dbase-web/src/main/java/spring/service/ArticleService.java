package spring.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import spring.domain.Article;

public interface ArticleService {
    public Page<Article> getAll(Pageable pageable);
    public Article get(Long id);
    public void save(Article article);
    public Page<Article> getByCategoryId(Long id, Pageable pageable);
}