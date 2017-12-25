package dbase.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import dbase.domain.Article;
import dbase.domain.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}