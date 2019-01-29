package eu.javageek.bookstore.repositories;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import eu.javageek.bookstore.domain.Genre;

public interface GenreRepository extends JpaSpecificationExecutor<Genre>, CrudRepository<Genre, Integer> {

}
