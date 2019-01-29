package eu.javageek.bookstore.repositories;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import eu.javageek.bookstore.domain.Author;

public interface AuthorRepository extends JpaSpecificationExecutor<Author>, CrudRepository<Author, Integer> {

}
