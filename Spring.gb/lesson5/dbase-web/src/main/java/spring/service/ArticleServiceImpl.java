package spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.domain.Article;
import spring.repository.ArticleRepository;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepository articleRepo;

    @Transactional(readOnly=true)
    public List<Article> getAll() {
        return articleRepo.findAll();
    }

    @Transactional(readOnly=true)
    public Article get(Long id) {
        return articleRepo.findOne(id);
    }

    @Transactional
    public void save(Article article) {
        articleRepo.save(article);
    }
}