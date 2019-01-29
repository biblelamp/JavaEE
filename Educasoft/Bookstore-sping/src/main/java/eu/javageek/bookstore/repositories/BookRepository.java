package eu.javageek.bookstore.repositories;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import eu.javageek.bookstore.domain.Book;

public interface BookRepository extends JpaSpecificationExecutor<Book>, CrudRepository<Book, Integer> {

}
