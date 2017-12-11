package dbase.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dbase.domain.Author;
import dbase.service.AuthorService;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}