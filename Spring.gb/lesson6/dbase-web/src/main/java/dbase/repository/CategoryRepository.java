package dbase.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dbase.domain.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}