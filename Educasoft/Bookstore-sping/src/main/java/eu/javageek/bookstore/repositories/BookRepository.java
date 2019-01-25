package eu.javageek.bookstore.repositories;

import org.springframework.data.repository.CrudRepository;

import eu.javageek.bookstore.domain.Book;

public interface BookRepository extends CrudRepository<Book, Integer> {

}
