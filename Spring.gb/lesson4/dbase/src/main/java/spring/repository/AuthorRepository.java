package spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.domain.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}