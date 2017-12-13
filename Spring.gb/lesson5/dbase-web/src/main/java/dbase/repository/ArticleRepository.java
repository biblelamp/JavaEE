package dbase.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dbase.domain.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
}