package eu.javageek.bookstore.repositories;

import org.springframework.data.repository.CrudRepository;

import eu.javageek.bookstore.domain.Author;

public interface AuthorRepository extends CrudRepository<Author, Integer> {

}
