package eu.javageek.bookstore.repositories;

import org.springframework.data.repository.CrudRepository;

import eu.javageek.bookstore.domain.Genre;

public interface GenreRepository extends CrudRepository<Genre, Integer> {

}
