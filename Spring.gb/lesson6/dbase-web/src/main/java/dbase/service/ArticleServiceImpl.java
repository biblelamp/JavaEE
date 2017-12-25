package dbase.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

import dbase.domain.Article;
import dbase.repository.ArticleRepository;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepository articleRepo;

    @Override
    @Transactional(readOnly=true)
    public Page<Article> getAll(Pageable pageable) {
        Page<Article> articles = articleRepo.findAll(pageable);
        return articles;
    }

    @Override
    @Transactional(readOnly=true)
    public Article get(Long id) {
        return articleRepo.findOne(id);
    }

    @Override
    @Transactional
    public void save(Article article) {
        articleRepo.save(article);
    }

    @Override
    @Transactional(readOnly=true)
    public Page<Article> getByCategoryId(Long id, Pageable pageable) {
        Page<Article> articles = articleRepo.findByCategoryId(id, pageable);
        return articles;
    }
}