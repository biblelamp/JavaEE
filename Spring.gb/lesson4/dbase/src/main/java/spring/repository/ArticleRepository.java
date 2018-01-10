package spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import spring.domain.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
}