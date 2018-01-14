package spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.domain.Article;
import spring.repository.ArticleRepository;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepository articleRepo;

    @Transactional(readOnly=true)
    public Page<Article> getAll(Pageable pageable) {
        Page<Article> articles = articleRepo.findAll(pageable);
        return articles;
    }

    @Transactional(readOnly=true)
    public Article get(Long id) {
        return articleRepo.findOne(id);
    }

    @Transactional
    public void save(Article article) {
        articleRepo.save(article);
    }

    @Transactional(readOnly=true)
    public Page<Article> getByCategoryId(Long id, Pageable pageable) {
        Page<Article> articles = articleRepo.findByCategoryId(id, pageable);
        return articles;
    }
}